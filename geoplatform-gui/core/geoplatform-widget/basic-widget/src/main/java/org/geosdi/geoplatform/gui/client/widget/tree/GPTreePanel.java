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
package org.geosdi.geoplatform.gui.client.widget.tree;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.client.widget.map.event.LayerRangeEventHandler;
import org.geosdi.geoplatform.gui.client.widget.tree.decorator.GPTreeCheckDecorator;
import org.geosdi.geoplatform.gui.client.widget.tree.decorator.GPTreeIconDecorator;
import org.geosdi.geoplatform.gui.client.widget.tree.decorator.GPTreeLabelDecorator;
import org.geosdi.geoplatform.gui.client.widget.tree.decorator.GPTreeLayerPresenceDecorator;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Nazzareno Sileno  <nazzareno.sileno@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
public class GPTreePanel<T extends GPBeanTreeModel> extends TreePanel<T> implements GPTreeLabelDecorator<T>,
        GPTreeIconDecorator<T>, GPTreeCheckDecorator<T>, GPTreeLayerPresenceDecorator, LayerRangeEventHandler {

    private final static Logger logger = Logger.getLogger("GPTreePanel");

    /**
     * @param store
     */
    public GPTreePanel(GPTreeStore<T> store) {
        super(store);
    }

    /**
     * Swap Old Model with New Model
     *
     * @param oldModel
     * @param newModel
     */
    public void swapModelInstance(T oldModel, T newModel) {
        ((GPTreeStore) this.store).swapModelInstance(oldModel, newModel);
    }

    /**
     *
     */
    public void addLayerRangeEventHandler() {
        LayerHandlerManager.addHandler(TYPE, this);
    }

    /**
     * @param model
     */
    @Override
    public void refresh(T model) {
        super.refresh(model);
    }

    /**
     * @param model
     */
    @Override
    public void refreshLabel(T model) {
        if (rendered) {
            TreeNode node = findNode(model);
            if (node != null && node.getElement() != null) {
                view.onTextChange(node, getText(model));
            }
        }
    }

    /**
     * @param model
     */
    @Override
    public void refreshIcon(T model) {
        if (rendered) {
            TreeNode node = findNode(model);
            if (node != null && node.getElement() != null) {
                view.onIconStyleChange(node, calculateIconStyle(model));
            }
        }
    }

    /**
     * @param model
     */
    @Override
    public void refreshCheck(T model) {
        if (rendered) {
            TreeNode node = findNode(model);
            if (node != null && node.getElement() != null) {
                setChecked(node.getModel(), isChecked(model));
            }
        }
    }

    /**
     * @param layerBean
     */
    @Override
    public void inRange(GPLayerBean layerBean) {
        logger.log(Level.FINEST, "inRange: " + layerBean);
        this.changeTextElementStyle(layerBean, "black");
    }

    /**
     * @param layerBean
     */
    @Override
    public void outRange(GPLayerBean layerBean) {
        logger.log(Level.FINEST, "outRange: " + layerBean);
        this.changeTextElementStyle(layerBean, "gray");
    }

    /**
     * @param layerBean
     * @param textColor
     */
    protected void changeTextElementStyle(GPLayerBean layerBean, String textColor) {
        T layerElement = this.store.findModel((T) layerBean);
        TreeNode node = findNode(layerElement);
        if (node != null && node.getElement() != null) {
            view.getTextElement(node).getStyle().setColor(textColor);
            logger.log(Level.FINEST, "Changed Style to layer: " + node.toString());
        }
    }

    /**
     * @param model
     * @param node
     * @param deep
     */
    @Override
    protected void onExpand(T model, TreeNode node, boolean deep) {
        super.onExpand(model, node, deep);
        //This code is usefull to ripristinate the layer label style for max - min scale
        if (node != null && !node.isLeaf()) {
            for (ModelData child : GPSharedUtils.safeList(model.getChildren())) {
                if (child instanceof GPRasterBean && super.isChecked((T) child)) {
                    this.activeRasterMinMaxScaleLabel((GPRasterBean) child);
                }
            }
        }
    }

    /**
     * @return {@link List<GPLayerBean>}
     */
    @Override
    public List<GPLayerBean> getVisibleLayersOnTree() {
        List<GPLayerBean> visibleLayers = Lists.<GPLayerBean>newArrayList();
        AbstractRootTreeNode root = (AbstractRootTreeNode) this.getStore().getRootItems().get(0);
        checkArgument(root != null, "GPTreePanel on getVisibleLayers(): Impossible to retrieve root element");
        return this.getVisibleLayersOnTree(root.getChildren(), visibleLayers);
    }

    /**
     * @return {@link List<GPLayerBean>}
     */
    @Override
    public <T extends GPLayerBean> List<T> getAllLayersOnTree() {
        List<T> allLayers = Lists.newArrayList();
        AbstractRootTreeNode root = (AbstractRootTreeNode) this.getStore().getRootItems().get(0);
        checkArgument(root != null, "GPTreePanel on getVisibleLayers() : Impossible to retrieve root element");
        return this.getAllLayersOnTree(root.getChildren(), allLayers);
    }

    /**
     * @return {@link List<GPLayerBean>}
     */
    @Override
    public List<GPLayerBean> getAllLayersOnTreeByDataSource(String dataSource) {
        List<GPLayerBean> allLayers = Lists.<GPLayerBean>newArrayList();
        AbstractRootTreeNode root = (AbstractRootTreeNode) this.getStore().getRootItems().get(0);
        checkArgument(root != null, "GPTreePanel on getVisibleLayers(): Impossible to retrieve root element");
        return this.getAllLayersOnTreeByDataSource(root.getChildren(), allLayers, dataSource);
    }

    /**
     * @param layers
     * @param visibleLayers
     * @return {@link List<GPLayerBean>}
     */
    protected List<GPLayerBean> getVisibleLayersOnTree(List<ModelData> layers, List<GPLayerBean> visibleLayers) {
        for (Iterator<ModelData> it = layers.iterator(); it.hasNext(); ) {
            GPBeanTreeModel element = (GPBeanTreeModel) it.next();
            if (element instanceof AbstractFolderTreeNode && element.isChecked() && element.getChildCount() != 0) {
                this.getVisibleLayersOnTree(element.getChildren(), visibleLayers);
            } else if (element.isChecked() && element instanceof GPLayerTreeModel) {
                visibleLayers.add((GPLayerBean) element);
            }
        }
        return visibleLayers;
    }

    /**
     * @param layers
     * @param allLayers
     * @return {@link List<GPLayerBean>}
     */
    protected <T extends GPLayerBean> List<T> getAllLayersOnTree(List<ModelData> layers, List<T> allLayers) {
        for (Iterator<ModelData> it = layers.iterator(); it.hasNext(); ) {
            GPBeanTreeModel element = (GPBeanTreeModel) it.next();
            if (element instanceof AbstractFolderTreeNode && element.getChildCount() != 0) {
                this.getAllLayersOnTree(element.getChildren(), allLayers);
            } else if (element instanceof GPLayerTreeModel) {
                allLayers.add((T) element);
            }
        }
        return allLayers;
    }

    /**
     * @param layers
     * @param allLayers
     * @return {@link List<GPLayerBean>}
     */
    protected List<GPLayerBean> getAllLayersOnTreeByDataSource(List<ModelData> layers, List<GPLayerBean> allLayers, String dataSource) {
        for (Iterator<ModelData> it = layers.iterator(); it.hasNext(); ) {
            GPBeanTreeModel element = (GPBeanTreeModel) it.next();
            if (element instanceof AbstractFolderTreeNode && element.getChildCount() != 0) {
                this.getAllLayersOnTree(element.getChildren(), allLayers);
            } else if (element instanceof GPLayerTreeModel && ((GPLayerTreeModel) element).getDataSource().equals(dataSource)) {
                allLayers.add((GPLayerBean) element);
            }
        }
        return allLayers;
    }

    /**
     * @param rasterBean
     */
    protected void activeRasterMinMaxScaleLabel(GPRasterBean rasterBean) {
        if (rasterBean.getMaxScale() != null || rasterBean.getMinScale() != null) {
            this.outRange(rasterBean);
        }
    }
}