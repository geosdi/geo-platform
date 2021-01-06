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
public class VisitorDeleteElement extends AbstractVisitTree implements IVisitor {

    private GPBeanTreeModel endPosition;
    private GPBeanTreeModel tmpElement;
    private int tmpIndex;
    private GPRootTreeNode rootElement;
    private boolean stopIterating;
    private Map<FolderTreeNode, Integer> folderDescendantMap = Maps.<FolderTreeNode, Integer>newHashMap();

    public VisitorDeleteElement(GPRootTreeNode root) {
        this.rootElement = root;
    }

    public VisitorDeleteElement() {
    }

    public void deleteElement(GPBeanTreeModel removedElement,
            GPBeanTreeModel parentElementRemoved, int indexElementRemoved) {
        this.endPosition = super.getNextUnvisitedElement(removedElement);
        this.rootElement = super.findRootElement(parentElementRemoved);
        parentElementRemoved.remove(removedElement);
        this.preorderTraversal((removedElement instanceof FolderTreeNode)
                ? ((FolderTreeNode) removedElement).getNumberOfDescendants() : 0);
        this.folderDescendantMap.clear();
        this.updateNumberOfDescendants(parentElementRemoved, (removedElement instanceof FolderTreeNode)
                ? ((FolderTreeNode) removedElement).getNumberOfDescendants() : 0);
    }

    private void updateNumberOfDescendants(GPBeanTreeModel parentElementRemoved, int numberOfDescendantRemoved) {
        if (parentElementRemoved instanceof FolderTreeNode) {
            ((FolderTreeNode) parentElementRemoved).setNumberOfDescendants(
                    ((FolderTreeNode) parentElementRemoved).getNumberOfDescendants() - 1 - numberOfDescendantRemoved);
            this.getFolderDescendantMap().put((FolderTreeNode) parentElementRemoved, ((FolderTreeNode) parentElementRemoved).getNumberOfDescendants());
            System.out.println(parentElementRemoved.getLabel() + " has " + ((FolderTreeNode) parentElementRemoved).getNumberOfDescendants()
                    + " number of descendants.");
        }
        if (parentElementRemoved.getParent() != null && !(parentElementRemoved.getParent() instanceof GPRootTreeNode)) {
            this.updateNumberOfDescendants((GPBeanTreeModel) parentElementRemoved.getParent(), numberOfDescendantRemoved);
        }
    }

    private void preorderTraversal(int numberOfDescendant) {
        assert (this.rootElement != null) : "VisitorDeleteElement on preorderTraversal: impossible to visit tree, the root element is null";
        this.rootElement.setzIndex(this.rootElement.getzIndex() - 1 - numberOfDescendant);
        this.tmpElement = this.rootElement;
        this.tmpIndex = this.tmpElement.getzIndex();
//        System.out.println("this.tmpZindex: " + this.tmpIndex);
        while (!this.isPreorderExitCondition()) {
            this.tmpElement.accept(this);
            this.tmpElement = super.getNextUnvisitedElement(this.tmpElement);
        }
        this.resetVisit();
    }

    private void resetVisit() {
        this.endPosition = null;
        this.tmpIndex = -1;
        this.stopIterating = false;
        this.tmpElement = null;
    }

    private boolean isPreorderExitCondition() {
        return this.tmpElement == null || this.tmpElement.equals(this.endPosition);
    }

    @Override
    public void visitRoot(AbstractRootTreeNode root) {
        //Do nothing on root element
    }

    @Override
    public void visitFolder(AbstractFolderTreeNode folder) {
        ((FolderTreeNode) folder).setzIndex(--this.tmpIndex);
        if (!((FolderTreeNode) folder).isLoaded()) {
            this.tmpIndex = this.tmpIndex - ((FolderTreeNode) folder).getNumberOfDescendants();
        }
        System.out.println("VisitorDelElem Folder set zIndex: " + folder.getzIndex()
                + " to the folder: " + folder.getLabel());
        List<ModelData> childrens = folder.getChildren();
        for (int i = 0; i < childrens.size() && !this.stopIterating; i++) {
            this.tmpElement = (GPBeanTreeModel) childrens.get(i);
            if (this.endPosition != null && this.isPreorderExitCondition()) {
                this.tmpElement = this.getPrecedingElement(this.endPosition);
                this.stopIterating = true;
                return;
            }
            this.tmpElement.accept(this);
        }

    }

    private void visitLeaf(GPLayerBean leaf) {
        ((GPBeanTreeModel) leaf).setzIndex(--this.tmpIndex);
        System.out.println("VisitorDelElem Leaf set zIndex: " + this.tmpIndex
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

    /**
     * @return the folderDescendantMap
     */
    public Map<FolderTreeNode, Integer> getFolderDescendantMap() {
        return folderDescendantMap;
    }

    /**
     * @param folderDescendantMap the folderDescendantMap to set
     */
    public void setFolderDescendantMap(Map<FolderTreeNode, Integer> folderDescendantMap) {
        this.folderDescendantMap = folderDescendantMap;
    }
}
