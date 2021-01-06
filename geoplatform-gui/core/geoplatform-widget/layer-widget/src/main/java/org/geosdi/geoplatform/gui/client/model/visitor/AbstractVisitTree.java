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
package org.geosdi.geoplatform.gui.client.model.visitor;

import com.extjs.gxt.ui.client.data.ModelData;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email  nazzareno.sileno@geosdi.org
 */
public abstract class AbstractVisitTree {

    protected int numberOfElements = -1;

    protected GPBeanTreeModel getPrecedingElement(GPBeanTreeModel element) {
        GPBeanTreeModel precedingElement = null;

        GPBeanTreeModel parent = (GPBeanTreeModel) element.getParent();
        int elementIndex = parent.indexOf(element);
        if (elementIndex == 0) { // Element is the first child
            precedingElement = parent;
        } else if (elementIndex > 0) { // Element is the second or subsequent child (i.e. has a previuos sibling)
            precedingElement = this.findDeepestElementInNode(
                    (GPBeanTreeModel) parent.getChild(elementIndex - 1));
        }
        return precedingElement;
    }

    protected GPBeanTreeModel getNextUnvisitedElement(GPBeanTreeModel element) {
        GPBeanTreeModel unvisitedElement = null;
        if (!element.isLeaf() && element.getChild(0) != null) { // Element isn't a leaf (i.e. a folder not empty) // TODO: enough a sigle condition (the latter)
            unvisitedElement = (GPBeanTreeModel) element.getChild(0);
        } else { // Element is a leaf (i.e. an empty folder or a layer)
            unvisitedElement = this.getFollowingElement(element);
        }
        return unvisitedElement;
    }

    private GPBeanTreeModel getFollowingElement(GPBeanTreeModel element) {
        GPBeanTreeModel followingElement = null;
//        System.out.println("Element label: " + element.getLabel());
        GPBeanTreeModel parent = (GPBeanTreeModel) element.getParent();
        if (parent != null && parent.getChild(parent.indexOf(element) + 1) != null) { // Element has a next sibling
            return (GPBeanTreeModel) parent.getChild(parent.indexOf(element) + 1);
        } else if (parent == null || (parent != null && parent instanceof GPRootTreeNode)) { // Element is root or is the last child of root
            return null; // Returning null because we don't have a next element
        } else { // Element hasn't a next sibling and the parent isn't the root
            followingElement = this.getFollowingElement(parent);
        }
        return followingElement;
    }

    protected GPBeanTreeModel findDeepestElementInNode(GPBeanTreeModel node) {
        GPBeanTreeModel deepestNode = node;
        if (!node.isLeaf()) {
            GPBeanTreeModel lastChild = (GPBeanTreeModel) node.getChildren().
                    get(node.getChildCount() - 1);
            deepestNode = this.findDeepestElementInNode(lastChild);
        }
        return deepestNode;
    }

    protected void countNumberOfElements(GPBeanTreeModel element) {
        if (element instanceof FolderTreeNode) {
            this.numberOfElements = ((FolderTreeNode) element).getNumberOfDescendants();
        } else if (element instanceof GPRootTreeNode) {
            List<ModelData> childrens = element.getChildren();
            for (int i = 0; i < childrens.size(); i++) {
                this.numberOfElements += 1 + ((FolderTreeNode) childrens.get(i)).getNumberOfDescendants();
            }
            ++this.numberOfElements; // Adding the root element
        }
    }
//    protected void countNumberOfElements(GPBeanTreeModel element) {
//        if (element instanceof FolderTreeNode
//                && !((FolderTreeNode) element).isLoaded()) {
//            this.numberOfElements = this.numberOfElements + 1 + ((FolderTreeNode) element).getNumberOfDescendants();
//        } else {
//            List<ModelData> childrens = element.getChildren();
//            ++this.numberOfElements;
//            for (int i = 0; i < childrens.size(); i++) {
//                this.countNumberOfElements((GPBeanTreeModel) childrens.get(i));
//            }
//        }
//    }

    protected GPRootTreeNode findRootElement(GPBeanTreeModel element) {
        assert (element != null) : "AbstractVisitTree on findRootElement the passed element could not be null.";
        GPRootTreeNode root = null;
        if (element instanceof GPRootTreeNode) {
            root = (GPRootTreeNode) element;
        } else {
            root = this.findRootElement((GPBeanTreeModel) element.getParent());
        }
        return root;
    }
}
