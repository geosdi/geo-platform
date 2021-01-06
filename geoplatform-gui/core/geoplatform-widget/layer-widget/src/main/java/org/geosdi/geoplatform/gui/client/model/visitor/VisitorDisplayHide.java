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
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.impl.map.event.DisplayLayerMapEvent;
import org.geosdi.geoplatform.gui.impl.map.event.HideLayerMapEvent;
import org.geosdi.geoplatform.gui.impl.map.event.RemoveLayerMapEvent;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class VisitorDisplayHide implements IVisitor {

    private TreePanel treePanel;
    private boolean isInternalFolderCheck;
    private boolean isInternalLeafCheck;
    private boolean isCacheableCheck = true;

    public VisitorDisplayHide(TreePanel treePanel) {
        this.treePanel = treePanel;
    }

    @Override
    public void visitRoot(AbstractRootTreeNode root) {
        //Do nothing on root
    }

    @Override
    public void visitFolder(AbstractFolderTreeNode folder) {
        if (folder.isChecked()) {
            //System.out.println("Folder e' checkata e ora tolgo il check");
            folder.setChecked(false);
            this.hideChildrens(folder);
        } else {
            //System.out.println("Folder non e' checkata e ora la checko: ");
            folder.setChecked(true);
            if (!this.isInternalFolderCheck && this.isAllParentsChecked(folder)) {
                this.showChildrens(folder);
            }
        }
    }

    private void visitLeaf(GPLayerBean layer) {
        GPBeanTreeModel element = (GPBeanTreeModel) layer;
        if (element.isChecked()) {
            element.setChecked(false);
            GPHandlerManager.fireEvent(new HideLayerMapEvent(layer));
        } else {
            element.setChecked(true);
            if (!this.isInternalLeafCheck) {
                GPHandlerManager.fireEvent(new DisplayLayerMapEvent(layer));
                this.setParentsFolderChecked(element);
            }
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

    private void setParentsFolderChecked(GPBeanTreeModel element) {
        GPBeanTreeModel parent = (GPBeanTreeModel) element.getParent();
        if (!parent.isChecked() && !(parent instanceof GPRootTreeNode)) {
            this.isInternalFolderCheck = true;
            this.treePanel.setChecked(parent, true);
            this.isInternalFolderCheck = false;
        }
        for (ModelData item : parent.getChildren()) {
            GPBeanTreeModel gpBean = (GPBeanTreeModel) item;
            if (gpBean.isChecked() && !(gpBean instanceof FolderTreeNode)
                    && !gpBean.equals(element)) {
                //System.out.println("Visualizzo anche il layer: " + gpBean.getLabel());
                GPHandlerManager.fireEvent(new DisplayLayerMapEvent(
                        ((GPLayerBean) gpBean)));
            } else if (gpBean.isChecked() && gpBean instanceof FolderTreeNode
                    && !gpBean.equals(element)) {
                this.showChildrens((FolderTreeNode) gpBean);
            }
        }
        //System.out.println("ParentsFolderChecked ha checkato: " + parent.getLabel());
        if (!(parent instanceof GPRootTreeNode)) {
            this.setParentsFolderChecked(parent);
        }
    }

    private void hideChildrens(AbstractFolderTreeNode folder) {
        for (Iterator it = folder.getChildren().iterator(); it.hasNext();) {
            GPBeanTreeModel child = (GPBeanTreeModel) it.next();
            if (child instanceof GPLayerBean && child.isChecked()) {
                GPHandlerManager.fireEvent(new HideLayerMapEvent(
                        ((GPLayerBean) child)));
            } else if (child instanceof FolderTreeNode && child.isChecked()) {
                this.hideChildrens((AbstractFolderTreeNode) child);
            }
        }
    }

    private void showChildrens(AbstractFolderTreeNode folder) {
        for (Object element : folder.getChildren()) {
            GPBeanTreeModel child = (GPBeanTreeModel) element;
            if (child instanceof GPLayerBean && child.isChecked()) {
                GPHandlerManager.fireEvent(new DisplayLayerMapEvent(
                        ((GPLayerBean) element)));
            } else if (child instanceof FolderTreeNode && child.isChecked()) {
                showChildrens((AbstractFolderTreeNode) child);
            }
        }
    }

    /**
     *
     * @return This method shows the checked layers on map
     */
    public void realignViewState(GPBeanTreeModel element) {
        this.isCacheableCheck = false;
        //System.out.println("Realigning view");
        if (element.isChecked()) {
            element.setChecked(false);
            if (element instanceof FolderTreeNode) {
                this.treePanel.setExpanded(element, true, true);
                this.isInternalFolderCheck = true;
                this.treePanel.setChecked(element, true);
                this.isInternalFolderCheck = false;
            } else if (element instanceof GPLayerTreeModel && this.isAllParentsChecked(
                    element)) {
                this.treePanel.setChecked(element, true);
            } else if (element instanceof GPLayerTreeModel) {
                this.isInternalLeafCheck = true;
                this.treePanel.setChecked(element, true);
                this.isInternalLeafCheck = false;
            }
        }
        for (ModelData item : element.getChildren()) {
            GPBeanTreeModel gpBean = (GPBeanTreeModel) item;
            if (gpBean instanceof FolderTreeNode || gpBean.isChecked()) {
                //System.out.println("Riconosco che devo fare check su: " + gpBean.getLabel());
                this.realignViewState(gpBean);
            }
        }
        this.isCacheableCheck = true;
    }

    public void enableCheckedComponent(GPBeanTreeModel element) {
        this.isCacheableCheck = false;
        for (ModelData item : element.getChildren()) {
            GPBeanTreeModel gpBean = (GPBeanTreeModel) item;
            if (gpBean.isChecked() && (gpBean instanceof FolderTreeNode || this.isAllParentsChecked(
                    gpBean))) {
                this.isInternalFolderCheck = true;
                gpBean.setChecked(false);
                this.treePanel.setChecked(gpBean, true);
                this.isInternalFolderCheck = false;
            } else if (gpBean.isChecked()) {//this is a leaf that cannot Be Displayed
                this.isInternalLeafCheck = true;
                gpBean.setChecked(false);
                this.treePanel.setChecked(gpBean, true);
                this.isInternalLeafCheck = false;
            }
        }
        this.isCacheableCheck = true;
    }

    private boolean isAllParentsChecked(GPBeanTreeModel element) {
        boolean condition = false;
        if (element.getParent() instanceof GPRootTreeNode) {
            return true;
        } else {
            condition = ((GPBeanTreeModel) element.getParent()).isChecked();
            if (condition) {
                condition = this.isAllParentsChecked(
                        (GPBeanTreeModel) element.getParent());
            }
        }
        return condition;
    }

    private List<GPBeanTreeModel> getVisibleLayersOnTree(List<ModelData> layers,
            List<GPBeanTreeModel> visibleLayers) {
        for (Iterator<ModelData> it = layers.iterator(); it.hasNext();) {
            GPBeanTreeModel element = (GPBeanTreeModel) it.next();
            if (element instanceof FolderTreeNode && element.isChecked()
                    && element.getChildCount() != 0) {
                this.getVisibleLayersOnTree(element.getChildren(), visibleLayers);
            } else if (element.isChecked() && element instanceof GPLayerTreeModel) {
                visibleLayers.add(element);
            }
        }
        return visibleLayers;
    }

    public List<GPBeanTreeModel> getVisibleLayersOnTree() {
        List<GPBeanTreeModel> visibleLayers = Lists.<GPBeanTreeModel>newArrayList();
        GPRootTreeNode root = (GPRootTreeNode) this.treePanel.getStore().getRootItems().get(
                0);
        assert (root != null) : "VisitorDisplayHide on getVisibleLayers(): Impossible to retrieve root element";
        return this.getVisibleLayersOnTree(root.getChildren(), visibleLayers);
    }

    public void removeVisibleLayers(GPBeanTreeModel element) {
        boolean isAllParentsChecked = this.isAllParentsChecked(element);
        if (isAllParentsChecked && element instanceof FolderTreeNode) {
            this.removeChildrens((FolderTreeNode) element);
        } else if (isAllParentsChecked && element.isChecked()) {
            GPHandlerManager.fireEvent(new RemoveLayerMapEvent(
                    (GPLayerBean) element));
        }
    }

    private void removeChildrens(AbstractFolderTreeNode folder) {
        for (Iterator it = folder.getChildren().iterator(); it.hasNext();) {
            GPBeanTreeModel child = (GPBeanTreeModel) it.next();
            if (child instanceof GPLayerBean && child.isChecked()) {
                GPHandlerManager.fireEvent(new RemoveLayerMapEvent(
                        ((GPLayerBean) child)));
            } else if (child instanceof FolderTreeNode && child.isChecked()) {
                this.removeChildrens((AbstractFolderTreeNode) child);
            }
        }
    }

    /**
     * @return the isCacheableCheck
     */
    public boolean isCacheableCheck() {
        return isCacheableCheck;
    }
}
