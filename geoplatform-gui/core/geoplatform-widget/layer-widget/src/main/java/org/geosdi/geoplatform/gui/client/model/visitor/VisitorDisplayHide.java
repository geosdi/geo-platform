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

import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.impl.map.event.DisplayLayerEvent;
import org.geosdi.geoplatform.gui.impl.map.event.HideLayerEvent;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email  nazzareno.sileno@geosdi.org
 */
public class VisitorDisplayHide implements IVisitor {

    private TreePanel treePanel;

    public VisitorDisplayHide(TreePanel treePanel){
        this.treePanel = treePanel;
    }

    @Override
    public void visitRoot(AbstractRootTreeNode root) {
        //Do nothing on root
    }

    @Override
    public void visitFolder(AbstractFolderTreeNode folder) {
//        folder.setChecked(folder.isChecked() ? false : true);
//        for (ModelData element : folder.getChildren()) {
//            ((GPBeanTreeModel)element).accept(this);
//        }
        if (folder.isChecked()) {
            System.out.println("Folder e' checkata e ora tolgo il check");
            folder.setChecked(false);
            this.hideChildrens(folder);
        } else {
            System.out.println("Folder non e' checkata e ora la checkko");
            folder.setChecked(true);
            this.showChildrens(folder);
        }
    }

    private void visitLeaf(GPLayerBean layer) {
        GPBeanTreeModel element = (GPBeanTreeModel) layer;
        if (element.isChecked()) {
            element.setChecked(false);
            GPHandlerManager.fireEvent(new HideLayerEvent(layer));
        } else {
            element.setChecked(true);
            this.setParentsFolderChecked(element);
            GPHandlerManager.fireEvent(new DisplayLayerEvent(layer));
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
        System.out.println("Ho messo checked su: " + parent.getLabel());
        parent.setChecked(true);
        this.treePanel.setChecked(parent, true);
        if (!(parent instanceof GPRootTreeNode)) {
            this.setParentsFolderChecked(parent);
        }
    }

    //TODO: Nascondere i figli anche dei livelli inferiori
    private void hideChildrens(AbstractFolderTreeNode folder) {
        for (Object element : folder.getChildren()) {
            GPBeanTreeModel child = (GPBeanTreeModel)element;
            if (child instanceof GPLayerBean && child.isChecked()) {
                GPHandlerManager.fireEvent(new HideLayerEvent(((GPLayerBean) element)));
            } else if (child instanceof FolderTreeNode){
                hideChildrens((AbstractFolderTreeNode)child);
            }
        }
    }

    //TODO: Mostrare i figli anche dei livelli inferiori
    private void showChildrens(AbstractFolderTreeNode folder) {
        for (Object element : folder.getChildren()) {
            GPBeanTreeModel child = (GPBeanTreeModel)element;
            if (child instanceof GPLayerBean && child.isChecked()) {
                GPHandlerManager.fireEvent(new DisplayLayerEvent(((GPLayerBean) element)));
            } else if(child instanceof FolderTreeNode){
                showChildrens((AbstractFolderTreeNode)child);
            }
        }
    }
}
