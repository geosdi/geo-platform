/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.widget;

import java.util.List;

import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.dnd.DND.Feedback;
import com.extjs.gxt.ui.client.dnd.TreePanelDropTarget;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.store.TreeStoreEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel.TreeNode;
import org.geosdi.geoplatform.gui.model.tree.LayerEvents;

public class GPTreePanelDropTarget extends TreePanelDropTarget {

    private GPBeanTreeModel target;
    private GPBeanTreeModel source;

    public GPTreePanelDropTarget(TreePanel<GPBeanTreeModel> tree) {
        super(tree);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void showFeedback(DNDEvent e) {
        super.showFeedback(e);
        TreeNode overItem = tree.findNode(e.getTarget());
        if (overItem == null) {
            e.getStatus().setStatus(false);
        }
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
        this.source = (GPBeanTreeModel) sourceTreeModel;
        this.target = (GPBeanTreeModel) targetTreeModel;
        if (this.target instanceof GPRootTreeNode) {
//            System.out.println("Target instanceof GPRootTreeNode");
            condition = false;// Elements above root not allowed
        } else if (this.target instanceof FolderTreeNode && !((FolderTreeNode) this.target).isLoaded()
                && !this.tree.isExpanded(target)) {
//            System.out.println("The Folders must be expanded before drop on it");
            condition = false;//The Folders must be expanded before drop on it
        } else if (this.target.isLeaf() && this.target instanceof FolderTreeNode) {
//            System.out.println("Insert elements into empty folders");
            Feedback feedbackToChange;
            if (!(this.target.getParent() instanceof GPRootTreeNode)
                    || this.source instanceof FolderTreeNode) {
                feedbackToChange = Feedback.BOTH;
            } else {
                feedbackToChange = Feedback.APPEND;
            }
            boolean dropLeaf = super.isAllowDropOnLeaf();
            Feedback feedback = super.getFeedback();
            super.setAllowDropOnLeaf(true);
            super.setFeedback(feedbackToChange);
            super.showFeedback(e);
            super.setAllowDropOnLeaf(dropLeaf);
            super.setFeedback(feedback);
            condition = true;// Insert elements into empty folders
        } else if (!(source instanceof FolderTreeNode)
                && target.getParent() instanceof GPRootTreeNode) {
//            System.out.println("Leafs without a folder not allowed");
            condition = false;// Leafs without a folder not allowed
//        } else if (source instanceof GPLayerTreeModel
//                && this.duplicateLayerCondition(target, (GPLayerTreeModel) source)) {
////            System.out.println("Duplicated layers are not allowed on the same folder");
//            condition = false;//Duplicated layers are not allowed on the same folder
        }
        return condition;
    }

//    private boolean duplicateLayerCondition(GPBeanTreeModel target, GPLayerTreeModel layer) {
//        boolean duplicatedLayer = Boolean.FALSE;
//        GPBeanTreeModel parent = target;
//        if (target instanceof GPLayerTreeModel) {
//            parent = (GPBeanTreeModel) target.getParent();
//        }
//        for (ModelData element : parent.getChildren()) {
//            if (element != null && element instanceof GPLayerTreeModel
//                    && !element.equals(layer) && ((GPLayerTreeModel) element).getName().equals(layer.getName())) {
//                duplicatedLayer = true;
//                break;
//            }
//        }
//        return duplicatedLayer;
//    }
    @Override
    protected void onDragDrop(DNDEvent event) {
        super.onDragDrop(event);
        TreeStoreEvent be = new TreeStoreEvent(tree.getStore());
        fireEvent(LayerEvents.GP_DROP, be);
    }
}
