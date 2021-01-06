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
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class VisitorPosition extends AbstractVisitTree
        implements IVisitor {

    private int tmpIndex = -1;
    private boolean stopIterating;
    private GPBeanTreeModel startPosition;
    private GPBeanTreeModel endPosition;
    private GPBeanTreeModel tmpElement;
    private Map<FolderTreeNode, Integer> folderDescendantMap = Maps.<FolderTreeNode, Integer>newHashMap();

    public void fixPosition(GPBeanTreeModel changedElement,
            GPBeanTreeModel parentDestination, int newIndex) {
        GPBeanTreeModel oldParent = (GPBeanTreeModel) changedElement.getParent();
        int oldZIndex = changedElement.getzIndex();
        System.out.println("Old zIndex: " + oldZIndex);
        System.out.println("parentDestination.getzIndex(): " + parentDestination.getzIndex());
        System.out.println("New Index: " + newIndex);
        //int newZIndex = parentDestination.getzIndex() - newIndex - 1;
        int numElementsMoved = 1; // Iff changedElement is a layer
        int newZIndex = this.getNewZIndex(parentDestination, newIndex);
        System.out.println("New zIndex: " + newZIndex);
        if (newZIndex < oldZIndex) {
            System.out.println("Executing: newIndex < oldIndex");
            this.startPosition = super.getPrecedingElement(changedElement);
            oldParent.remove(changedElement);
            changedElement.setParent(parentDestination);
            parentDestination.insert(changedElement, newIndex);
            this.endPosition = super.getNextUnvisitedElement(super.findDeepestElementInNode(changedElement));
        } else if (newZIndex > oldZIndex) {
            System.out.println("Executing: newIndex > oldIndex");
            this.endPosition = super.getNextUnvisitedElement(super.findDeepestElementInNode(changedElement));
            oldParent.remove(changedElement);
            changedElement.setParent(parentDestination);
            parentDestination.insert(changedElement, newIndex);
            this.startPosition = super.getPrecedingElement(changedElement);
        } else {
            oldParent.remove(changedElement);
            changedElement.setParent(parentDestination);
            parentDestination.insert(changedElement, newIndex);

            if (changedElement instanceof FolderTreeNode) {
                numElementsMoved = ((FolderTreeNode) changedElement).getNumberOfDescendants() + 1;
            }
            this.updateNumberOfDescendants(oldParent, parentDestination, numElementsMoved);
            System.out.println("In FixPosition: returning without index changes"
                    + "for element: " + changedElement.getLabel());
            return;
        }

        if (changedElement instanceof FolderTreeNode) {
            numElementsMoved = ((FolderTreeNode) changedElement).getNumberOfDescendants() + 1;
        }
        this.updateNumberOfDescendants(oldParent, parentDestination, numElementsMoved);

        System.out.println(this.startPosition == null ? "Start Position: null" : "Start Position: " + this.startPosition.getLabel());
        System.out.println(this.endPosition == null ? "End position: untill the end of the Tree" : "End position: " + this.endPosition.getLabel());
        this.preorderTraversal();
        System.out.println("End modification");
    }

    private void updateNumberOfDescendants(GPBeanTreeModel oldParent, GPBeanTreeModel parentDestination,
            int numElementsMoved) {
        this.folderDescendantMap.clear();
        this.updateNumberOfDescendants(oldParent, -numElementsMoved);
        this.updateNumberOfDescendants(parentDestination, numElementsMoved);
    }

    private void updateNumberOfDescendants(GPBeanTreeModel parent, int numElementsMoved) {
        while (parent instanceof FolderTreeNode) {
            FolderTreeNode parentFolder = (FolderTreeNode) parent;
            parentFolder.setNumberOfDescendants(parentFolder.getNumberOfDescendants() + numElementsMoved);
            this.folderDescendantMap.put(parentFolder, parentFolder.getNumberOfDescendants());
            parent = (GPBeanTreeModel) parent.getParent();
        }
    }

    private int getNewZIndex(GPBeanTreeModel parentDestination, int newIndex) {
        int newZIndex = 0;
        if (parentDestination.getChild(newIndex) != null) {
            newZIndex = ((GPBeanTreeModel) parentDestination.getChild(newIndex)).getzIndex();
        } else if (newIndex > 0) {
            newZIndex = ((GPBeanTreeModel) parentDestination.getChild(newIndex - 1)).getzIndex() + 1;
        } else {
            newZIndex = parentDestination.getzIndex();
        }
        return newZIndex;
    }

    private void preorderTraversal() {
        assert (this.startPosition != null) : "You need to specify a startPosition before call this method";

        if (this.startPosition instanceof FolderTreeNode && !((FolderTreeNode) this.startPosition).isLoaded()) {
            this.tmpIndex = this.startPosition.getzIndex() - ((FolderTreeNode) this.startPosition).getNumberOfDescendants();
        } else {
            this.tmpIndex = this.startPosition.getzIndex();
        }
        this.tmpElement = super.getNextUnvisitedElement(this.startPosition);
        //System.out.println(this.tmpElement == null ? null : "In preorder Traversal tmpElement: " + this.tmpElement.getLabel());
        while (!this.isPreorderExitCondition()) {
            this.tmpElement.accept(this);
            this.tmpElement = super.getNextUnvisitedElement(this.tmpElement);
        }
        this.resetVisit();
    }

    private boolean isPreorderExitCondition() {
        return this.tmpElement == null || this.tmpElement.equals(this.endPosition);
    }

    private void resetVisit() {
        this.tmpIndex = -1;
        this.stopIterating = false;
        this.startPosition = null;
        this.endPosition = null;
        this.tmpElement = null;
    }

    @Override
    public void visitRoot(AbstractRootTreeNode root) {
        if (super.numberOfElements != -1 && this.startPosition == null) {
            root.setzIndex(super.numberOfElements);
            //System.out.println("Root zIndex: " + this.numberOfElements);
            List<ModelData> childrens = root.getChildren();
            for (int i = 0; i < childrens.size(); i++) {
                ((GPBeanTreeModel) childrens.get(i)).accept(this);
            }
        } else if (super.numberOfElements == -1) {
            super.numberOfElements = 0;
            super.countNumberOfElements(root);
            this.tmpIndex = super.numberOfElements;
            System.out.println("Number of elements on tree: " + super.numberOfElements);
            this.visitRoot(root);
        }
    }

    @Override
    public void visitFolder(AbstractFolderTreeNode folder) {
        ((FolderTreeNode) folder).setzIndex(--this.tmpIndex);
        if (!((FolderTreeNode) folder).isLoaded()) {
            this.tmpIndex = this.tmpIndex - ((FolderTreeNode) folder).getNumberOfDescendants();
        }
        System.out.println("VisitorPos Folder set zIndex: " + folder.getzIndex()
                + " to the folder: " + folder.getLabel());
        List<ModelData> childrens = folder.getChildren();
        for (int i = 0; i < childrens.size() && !this.stopIterating; i++) {
            this.tmpElement = (GPBeanTreeModel) childrens.get(i);
            if (this.endPosition != null && this.isPreorderExitCondition()) {
                this.tmpElement = this.getPrecedingElement(this.endPosition);
                this.stopIterating = Boolean.TRUE;
                return;
            }
            this.tmpElement.accept(this);
        }

    }

    private void visitLeaf(GPLayerBean leaf) {
        ((GPBeanTreeModel) leaf).setzIndex(--this.tmpIndex);
        System.out.println("VisitorPos Leaf set zIndex: " + this.tmpIndex
                + " to the leaf: " + leaf.getLabel());
    }

    public void assignTmpIndex(GPBeanTreeModel element) {
        assert (this.tmpIndex == -1) : "The temporary index is accessed from other method?";
        if (element instanceof GPRootTreeNode) {
            this.tmpIndex = 0;
        } else {
            this.tmpIndex = element.getzIndex();
        }
    }

    @Override
    public void visitVector(GPVectorBean vector) {
        this.visitLeaf(vector);
    }

    @Override
    public void visitRaster(GPRasterBean raster) {
        this.visitLeaf(raster);
    }

    /**
     * @return the folderDescendantMap
     */
    public Map<FolderTreeNode, Integer> getFolderDescendantMap() {
        return folderDescendantMap;
    }
}
