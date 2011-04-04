package org.geosdi.geoplatform.gui.client.widget;

import java.util.List;

import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.dnd.DND.Feedback;
import com.extjs.gxt.ui.client.dnd.TreePanelDropTarget;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.TreeNode;

public class GPTreePanelDropTarget extends TreePanelDropTarget {

	public GPTreePanelDropTarget(TreePanel<GPBeanTreeModel> tree) {
		super(tree);
	}

	@Override
	protected void showFeedback(DNDEvent e) {
		super.showFeedback(e);
		TreeNode overItem = tree.findNode(e.getTarget());
		TreeModel targetTreeModel = (TreeModel) overItem.getModel();
		List<TreeModel> data = e.getData();
		for (final TreeModel tm : data) {
			TreeModel m = tm.get("model");
			if (!canDropOn(m, targetTreeModel, e)) {
				e.getStatus().setStatus(false);
			}
		}
	}

	// Verifying drop condition
	private boolean canDropOn(TreeModel sourceTreeModel,
			TreeModel targetTreeModel, DNDEvent e) {
		boolean condition = true;
		GPBeanTreeModel source = (GPBeanTreeModel) sourceTreeModel;
		GPBeanTreeModel target = (GPBeanTreeModel) targetTreeModel;
		if (target instanceof GPRootTreeNode) {
			condition = false;// Elements above root not allowed
		} else if (target.isLeaf() && target instanceof FolderTreeNode) {
			boolean dropLeaf = super.isAllowDropOnLeaf();
			Feedback feedback = super.getFeedback();
			super.setAllowDropOnLeaf(true);
			super.setFeedback(Feedback.APPEND);
			super.showFeedback(e);
			super.setAllowDropOnLeaf(dropLeaf);
			super.setFeedback(feedback);
			condition = true;// Insert elements into empty folders
		} else if (!(source instanceof FolderTreeNode)
				&& target.getParent() instanceof GPRootTreeNode) {
			condition = false;// Leafs without a folder not allowed
		}
		return condition;
	}
}
