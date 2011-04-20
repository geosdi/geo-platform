package org.geosdi.geoplatform.gui.client.listener;

import com.extjs.gxt.ui.client.event.EventType;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorPosition;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.store.TreeStoreEvent;
import org.geosdi.geoplatform.gui.client.LayerEvents;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;

public class GPDNDListener implements Listener<TreeStoreEvent<GPBeanTreeModel>> {

    private VisitorDisplayHide checkerVisitor;
    private VisitorPosition visitor = new VisitorPosition();
    private GPBeanTreeModel changedElement;
    private GPBeanTreeModel parentDestination;
    private int newIndex;
    private boolean activeDrop = false;
    private boolean folderDrop = false;

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
        if (be.getParent() != null && Store.Add.equals(be.getType()) && this.folderDrop == false) {
            TreeStore<GPBeanTreeModel> treeStore = (TreeStore<GPBeanTreeModel>) be.getSource();
            this.parentDestination = be.getParent();
            this.newIndex = be.getIndex();
            this.changedElement = treeStore.getChild(parentDestination, newIndex);
            if (this.changedElement instanceof FolderTreeNode) {
                this.folderDrop = true;
            }
        } else if (activeDrop && LayerEvents.GP_DROP.equals(be.getType())) {
            visitor.fixPosition(changedElement, parentDestination, newIndex);
            this.activeDrop = false;
            this.folderDrop = false;
            this.checkerVisitor.realignViewState(changedElement);
        }
    }

    private void manageDropActivation(EventType eventType) {
        if (LayerEvents.GP_DRAG_START.equals(eventType)) {
            this.activeDrop = true;
            this.folderDrop = false;
        } else if (LayerEvents.GP_DRAG_LOST.equals(eventType)) {
            this.activeDrop = false;
        }
    }
}
