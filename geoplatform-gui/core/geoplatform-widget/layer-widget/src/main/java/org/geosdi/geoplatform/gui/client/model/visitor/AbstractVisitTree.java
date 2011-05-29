/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
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
        GPBeanTreeModel parent = (GPBeanTreeModel) element.getParent();
        GPBeanTreeModel precedingElement = null;
        if (parent.indexOf(element) - 1 == -1) {
            precedingElement = parent;
        } else if (parent.indexOf(element) - 1 != -1) {
            precedingElement = this.findDeepestElementInNode((GPBeanTreeModel) parent.getChild(parent.indexOf(element) - 1));
        }
        return precedingElement;
    }

    protected GPBeanTreeModel getNextUnvisitedElement(GPBeanTreeModel element) {
        GPBeanTreeModel unvisitedElement = null;
        if (!element.isLeaf() && element.getChild(0) != null) {//IS FOLDER
            unvisitedElement = (GPBeanTreeModel) element.getChild(0);
        } else {
            unvisitedElement = this.getFollowingElement(element);
        }
        return unvisitedElement;
    }

    private GPBeanTreeModel getFollowingElement(GPBeanTreeModel element) {
        GPBeanTreeModel followingElement = null;
        GPBeanTreeModel parent = (GPBeanTreeModel) element.getParent();
        int indexElement = parent.indexOf(element);
        if (parent != null && parent.getChild(indexElement + 1) != null) {
            return (GPBeanTreeModel) parent.getChild(indexElement + 1);
        } else if (parent != null && parent instanceof GPRootTreeNode) {
            //System.out.println("Il padre non e' uguale a null ed e' istanza di root");
            return element;//Returning null because we don't have a next element
        } else {
            followingElement = this.getFollowingElement(parent);
        }
        return followingElement;
    }

    protected GPBeanTreeModel findDeepestElementInNode(GPBeanTreeModel node) {
        GPBeanTreeModel deepestNode = node;
        if (!node.isLeaf()) {
            GPBeanTreeModel tmpNode = (GPBeanTreeModel) node.getChildren().get(
                    node.getChildren().size() - 1);
            deepestNode = this.findDeepestElementInNode(tmpNode);
        }
        return deepestNode;
    }

    protected void countNumberOfElements(GPBeanTreeModel element) {
        List<ModelData> childrens = element.getChildren();
        if (childrens.isEmpty() && element instanceof FolderTreeNode
                && !((FolderTreeNode) element).isLoaded()) {
            this.numberOfElements = this.numberOfElements + 1 + ((FolderTreeNode) element).getNumberOfDescendants();
        } else {
            ++this.numberOfElements;
            for (int i = 0; i < childrens.size(); i++) {
                this.countNumberOfElements((GPBeanTreeModel) childrens.get(i));
            }
        }
    }

    protected GPRootTreeNode findRootElement(GPBeanTreeModel element) {
        GPRootTreeNode root = null;
        if (element instanceof GPRootTreeNode) {
            root = (GPRootTreeNode) element;
        } else {
            root = this.findRootElement((GPBeanTreeModel) element.getParent());
        }
        return root;
    }
}
