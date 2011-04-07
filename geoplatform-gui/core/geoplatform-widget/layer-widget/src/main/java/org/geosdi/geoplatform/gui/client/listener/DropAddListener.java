package org.geosdi.geoplatform.gui.client.listener;

import org.geosdi.geoplatform.gui.client.model.visitor.VisitorPosition;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.store.TreeStoreEvent;

public class DropAddListener implements
		Listener<TreeStoreEvent<GPBeanTreeModel>> {

	private VisitorPosition visitor = new VisitorPosition();

	@SuppressWarnings("unchecked")
	@Override
	public void handleEvent(TreeStoreEvent<GPBeanTreeModel> be) {
		// TODO: Manage the drop and the add on the store
		if (be.getParent() != null) {
			GPBeanTreeModel parentDestination = be.getParent();
			int index = be.getIndex();
			TreeStore<GPBeanTreeModel> treeStore = (TreeStore<GPBeanTreeModel>) be
					.getSource();
			GPBeanTreeModel changedElement = treeStore.getChild(
					parentDestination, index);

			System.out.println("Changed element: " + changedElement);
			System.out.println("Parent destinazione: " + parentDestination);
			System.out.println("Indice destinazione: " + index);
			System.out.println("Source that fired the event: " + treeStore);

			visitor.fixPosition(changedElement, parentDestination, index);
		}
	}

}
