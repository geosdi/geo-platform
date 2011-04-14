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
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;

public class GPDNDListener implements Listener<TreeStoreEvent<GPBeanTreeModel>> {

    private VisitorPosition visitor = new VisitorPosition();
    private GPBeanTreeModel changedElement;
    private GPBeanTreeModel parentDestination;
    private int newIndex;
    private boolean activeDrop = false;


    /*We need to manager three kinds of DND Events:
     *  LayerEvents.GP_DRAG_START
     *  LayerEvents.GP_DRAG_LOST
     *  LayerEvents.GP_DROP
     * and the Store.Add event to enable or disable the visitor
     */
    @SuppressWarnings("unchecked")
    @Override
    public void handleEvent(TreeStoreEvent<GPBeanTreeModel> be) {
        this.manageDropActivation(be.getType());
        if (be.getParent() != null && Store.Add.equals(be.getType())) {
            //!(be.getSource() instanceof GPTreePanelDropTarget) &&
            this.parentDestination = be.getParent();
            this.newIndex = be.getIndex();
            TreeStore<GPBeanTreeModel> treeStore = (TreeStore<GPBeanTreeModel>) be.getSource();
            this.changedElement = treeStore.getChild(parentDestination, newIndex);

            System.out.println("Changed element: " + changedElement);
            System.out.println("Parent destinazione: " + parentDestination);
            System.out.println("Indice destinazione: " + newIndex);
            System.out.println("Source that fired the event: " + treeStore);

            if (this.changedElement instanceof FolderTreeNode
                    && (parentDestination instanceof FolderTreeNode || parentDestination instanceof GPRootTreeNode)) {
                visitor.fixPosition(changedElement, parentDestination, newIndex);
                this.activeDrop = false;
            }
        } else if (activeDrop && LayerEvents.GP_DROP.equals(be.getType())) {
            visitor.fixPosition(changedElement, parentDestination, newIndex);
            this.activeDrop = false;
        }
    }

    private void manageDropActivation(EventType eventType) {
        if (LayerEvents.GP_DRAG_START.equals(eventType)) {
            this.activeDrop = true;
        } else if (LayerEvents.GP_DRAG_LOST.equals(eventType)) {
            this.activeDrop = false;
        }
    }
}
