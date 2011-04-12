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

import java.util.List;

import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

import com.extjs.gxt.ui.client.data.ModelData;

public class VisitorPosition implements IVisitor {

    private int tmpIndex = -1;
    private int numberOfElements = -1;
    private GPBeanTreeModel startPosition;
    private GPBeanTreeModel endPosition;
    private GPBeanTreeModel tmpElement;

    public void fixPosition(GPBeanTreeModel changedElement,
            GPBeanTreeModel parentDestination, int newIndex) {
        GPBeanTreeModel oldParent = (GPBeanTreeModel) changedElement.getParent();
        int oldPosition = oldParent.getChildren().indexOf(changedElement);

        int oldZIndex = changedElement.getzIndex();
        int newZIndex = parentDestination.getzIndex() - newIndex-1;

        System.out.println("New index: " + newZIndex);
        System.out.println("Old position: " + oldZIndex);
        if (newZIndex < oldZIndex) {
            this.startPosition = this.getPrecedingElement(changedElement);
            oldParent.remove(changedElement);
            changedElement.setParent(parentDestination);
            parentDestination.insert(changedElement, newIndex);
            this.endPosition = this.getNextUnvisitedElement(this.findDeepestElementInNode(changedElement));
        } else if (newZIndex > oldZIndex) {
            this.endPosition = this.getNextUnvisitedElement(this.findDeepestElementInNode(changedElement));
            oldParent.remove(changedElement);
            changedElement.setParent(parentDestination);
            parentDestination.insert(changedElement, newIndex);
            this.startPosition = this.getPrecedingElement(changedElement);
        } else {
            return;
        }
        System.out.println("Start Position: " + this.startPosition.toString());
        System.out.println("End position: " + this.endPosition.toString());
        this.preorderTraversal();
        System.out.println("Modifica terminata");
    }

    private GPBeanTreeModel getPrecedingElement(GPBeanTreeModel element) {
        GPBeanTreeModel parent = (GPBeanTreeModel) element.getParent();
        GPBeanTreeModel precedingElement = null;
        if (parent.indexOf(element) - 1 == -1) {
            precedingElement = parent;
        } else if (parent.indexOf(element) - 1 != -1) {
            precedingElement = this.findDeepestElementInNode((GPBeanTreeModel) parent.getChild(parent.indexOf(element) - 1));
        }
        return precedingElement;
    }

    private GPBeanTreeModel getNextUnvisitedElement(GPBeanTreeModel element) {
        GPBeanTreeModel unvisitedElement = null;
        //TODO: Verificare nel caso in cui l'elemento è una folder vuota
        if (!element.isLeaf()) {//IS FOLDER
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
            System.out.println("Il padre non è uguale a null ed è istanza di root");
            return null;
        } else {
            followingElement = this.getFollowingElement(parent);
        }
        return followingElement;
    }

    private GPBeanTreeModel findDeepestElementInNode(GPBeanTreeModel node) {
        GPBeanTreeModel deepestNode = node;
        if (!node.isLeaf()) {
            GPBeanTreeModel tmpNode = (GPBeanTreeModel) node.getChildren().get(
                    node.getChildren().size() - 1);
            deepestNode = this.findDeepestElementInNode(tmpNode);
        }
        return deepestNode;
    }

    private void preorderTraversal() {
        assert (this.startPosition != null) : "You need to specify a startPosition before call this method";
        this.tmpIndex = this.startPosition.getzIndex();
        this.tmpElement = this.getNextUnvisitedElement(this.startPosition);
        while (this.tmpElement != null && !this.tmpElement.equals(this.endPosition)) {
            this.tmpElement.accept(this);
            this.tmpElement = this.getNextUnvisitedElement(this.tmpElement);
        }
        this.resetVisit();
    }

    private void resetVisit() {
        this.startPosition = null;
        this.endPosition = null;
        this.tmpIndex = -1;
        this.tmpElement = null;
    }

    @Override
    public void visitRoot(AbstractRootTreeNode root) {
        if (this.numberOfElements != -1 && this.startPosition == null) {
            root.setzIndex(this.numberOfElements);
            List<ModelData> childrens = root.getChildren();
            for (int i = 0; i < childrens.size(); i++) {
                ((GPBeanTreeModel) childrens.get(i)).accept(this);
            }
        } else if (this.numberOfElements == -1) {
            this.numberOfElements = 0;
            this.countNumberOfElements(root);
            this.tmpIndex = this.numberOfElements;
            this.visitRoot(root);
        }
    }

    private void countNumberOfElements(GPBeanTreeModel element) {
        ++this.numberOfElements;
        List<ModelData> childrens = element.getChildren();
        for (int i = 0; i < childrens.size(); i++) {
            this.countNumberOfElements((GPBeanTreeModel) childrens.get(i));
        }
    }

    @Override
    public void visitFolder(AbstractFolderTreeNode folder) {
        folder.setzIndex(--this.tmpIndex);
        System.out.println("Visitor Folder set zIndex: " + this.tmpIndex
                + " to the folder: " + folder.getLabel());
        List<ModelData> childrens = folder.getChildren();
        for (int i = 0; i < childrens.size(); i++) {
            this.tmpElement = (GPBeanTreeModel) childrens.get(i);
            this.tmpElement.accept(this);
        }
    }

    private void visitLeaf(GPLayerBean leaf) {
        ((GPBeanTreeModel) leaf).setzIndex(--this.tmpIndex);
        System.out.println("Visitor Leaf set zIndex: " + this.tmpIndex
                + " to the leaf: " + leaf.getLabel());
    }

    @Override
    public void visitVector(GPVectorBean vector) {
        this.visitLeaf(vector);
    }

    @Override
    public void visitRaster(GPRasterBean raster) {
        this.visitLeaf(raster);
    }
}
