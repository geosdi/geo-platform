package org.geosdi.geoplatform.gui.client.listener;

import com.extjs.gxt.ui.client.event.EventType;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorPosition;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.store.TreeStoreEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.LayerEvents;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.GPLayerSaveCache;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveDragDrop;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.exception.GPSessionTimeout;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;

public class GPDNDListener implements Listener<TreeStoreEvent<GPBeanTreeModel>>, ISave<MementoSaveDragDrop> {

    private VisitorDisplayHide checkerVisitor;
    private VisitorPosition visitor = new VisitorPosition();
    private GPBeanTreeModel changedElement;
    private GPBeanTreeModel parentDestination;
    private int newIndex;
    private boolean isActiveDrop = false;
    private boolean isFolderDrop = false;
    private PeekCacheEvent peekCacheEvent = new PeekCacheEvent();

    public GPDNDListener(VisitorDisplayHide visitorDisplayHide) {
        this.checkerVisitor = visitorDisplayHide;
    }


    /*We need to manager three kinds of DND Events:
     *  LayerEvents.GP_DRAG_START
     *  LayerEvents.GP_DRAG_LOST
     *  LayerEvents.GP_DROP
     * and the Store.Add event, in this way we can manage the visit on tree
     */
    @SuppressWarnings("unchecked")
    @Override
    public void handleEvent(TreeStoreEvent<GPBeanTreeModel> be) {
        this.manageDropActivation(be.getType());
        if (be.getParent() != null && Store.Add.equals(be.getType()) && this.isFolderDrop == false) {
            TreeStore<GPBeanTreeModel> treeStore = (TreeStore<GPBeanTreeModel>) be.getSource();
            this.parentDestination = be.getParent();
            this.newIndex = be.getIndex();
            this.changedElement = treeStore.getChild(parentDestination, newIndex);
            if (this.changedElement instanceof FolderTreeNode) {
                this.isFolderDrop = true;
            }
        } else if (this.isActiveDrop && LayerEvents.GP_DROP.equals(be.getType())) {
            this.visitor.fixPosition(changedElement, parentDestination, newIndex);
            MementoSaveDragDrop mementoSaveDND = new MementoSaveDragDrop(this);
            mementoSaveDND.setRefBaseElement(changedElement);
            mementoSaveDND.setNewZIndex(changedElement.getzIndex());
            mementoSaveDND.setRefNewParent((parentDestination instanceof FolderTreeNode) ? (FolderTreeNode) parentDestination : null);
            mementoSaveDND.setDescendantMap(this.visitor.getFolderDescendantMap());
            GPLayerSaveCache.getInstance().add(mementoSaveDND);
            this.isActiveDrop = false;
            this.isFolderDrop = false;
            this.checkerVisitor.realignViewState(changedElement);
        }
    }

    private void manageDropActivation(EventType eventType) {
        if (LayerEvents.GP_DRAG_START.equals(eventType)) {
            this.isActiveDrop = true;
            this.isFolderDrop = false;
        } else if (LayerEvents.GP_DRAG_LOST.equals(eventType)) {
            this.isActiveDrop = false;
        }
    }

    @Override
    public void executeSave(final MementoSaveDragDrop memento) {
        //Warning: The following conversion is absolutely necessary!
        memento.convertMementoToWs();
        if (memento.getRefBaseElement() instanceof FolderTreeNode) {
            LayerRemote.Util.getInstance().saveDragAndDropFolderAndTreeModifications(memento,
                    new AsyncCallback<Boolean>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            if (caught.getCause() instanceof GPSessionTimeout) {
                                GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                            } else {
                                LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                                GeoPlatformMessage.errorMessage("Save Folder Drag&Drop Operation Error",
                                        "Problems on saving the new tree state after folder drag&drop operation");
                            }
                        }

                        @Override
                        public void onSuccess(Boolean result) {
                            GPLayerSaveCache.getInstance().remove(memento);
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "Folder Drag&Drop operation saved successfully.",
                                    EnumSearchStatus.STATUS_SEARCH.toString());
                            LayerHandlerManager.fireEvent(peekCacheEvent);
                        }
                    });
        } else if (memento.getRefBaseElement() instanceof GPLayerBean) {
            LayerRemote.Util.getInstance().saveDragAndDropLayerAndTreeModifications(memento,
                    new AsyncCallback<Boolean>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            if (caught.getCause() instanceof GPSessionTimeout) {
                                GPHandlerManager.fireEvent(new GPLoginEvent(peekCacheEvent));
                            } else {
                                LayerHandlerManager.fireEvent(new DisplayLayersProgressBarEvent(false));
                                GeoPlatformMessage.errorMessage("Save Layer Drag&Drop Operation Error",
                                        "Problems on saving the new tree state after layer drag&drop operation");
                            }
                        }

                        @Override
                        public void onSuccess(Boolean result) {
                            GPLayerSaveCache.getInstance().remove(memento);
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "Layer Drag&Drop operation saved successfully.",
                                    EnumSearchStatus.STATUS_SEARCH.toString());
                            LayerHandlerManager.fireEvent(peekCacheEvent);
                        }
                    });
        }
    }
}
