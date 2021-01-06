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
package org.geosdi.geoplatform.gui.client.widget.store.executor.catalog;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.widget.store.executor.LayerTreeStoreExecutor;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.GPShortLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.puregwt.grid.event.DeselectGridRecordEvent;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogExecutor extends LayerTreeStoreExecutor implements
        ICatalogExecutor {

    private final DeselectGridRecordEvent deselectGridRecord = new DeselectGridRecordEvent();

    public CatalogExecutor(GPTreePanel<GPBeanTreeModel> thetree) {
        super(thetree);
    }

    @Override
    public void addRasterLayersFromCatalog(
            List<? extends GPShortLayerBean> layers) {
        Map<String, List<GPShortLayerBean>> layerMap = this.getLayerMapDataSource(
                layers);

        StringBuilder existingLayers = new StringBuilder();
        for (Map.Entry<String, List<GPShortLayerBean>> e : layerMap.entrySet()) {
            existingLayers = this.addShortRasterLayers(e.getValue(),
                    existingLayers);
        }

        LayerHandlerManager.fireEvent(deselectGridRecord);
        this.createAlertMessage(existingLayers);
    }

    private Map<String, List<GPShortLayerBean>> getLayerMapDataSource(
            List<? extends GPShortLayerBean> layers) {
        Map<String, List<GPShortLayerBean>> layerMap = Maps.<String, List<GPShortLayerBean>>newHashMap();

        for (GPShortLayerBean layer : layers) {
            String dataSource = layer.getLayerDataSource();
            System.out.println("### " + dataSource);

            List<GPShortLayerBean> layersByDataSource = layerMap.get(dataSource);
            if (layersByDataSource == null) {
                layersByDataSource = Lists.<GPShortLayerBean>newArrayList();
                layerMap.put(dataSource, layersByDataSource);
            }
            layersByDataSource.add(layer);
        }

        return layerMap;
    }

    private StringBuilder addShortRasterLayers(
            List<? extends GPShortLayerBean> layers,
            StringBuilder existingLayers) {
        if (layers == null || layers.isEmpty()) { // TODO assert
            return null;
        }

        GPBeanTreeModel parentDestination = this.tree.getSelectionModel().getSelectedItem();
        List<GPBeanTreeModel> layerList = Lists.<GPBeanTreeModel>newArrayList();

        for (GPShortLayerBean layer : layers) {
            boolean duplicatedLayer = this.checkDuplicatedLayer(layer,
                    parentDestination);
            if (duplicatedLayer) {
                String aliasForCopiedLayer = this.generateUnduplicateAliasForLayer(
                        layer, parentDestination);
                layerList.add(this.generateRasterTreeNode(layer,
                        aliasForCopiedLayer));
                existingLayers.append(layer.getLayerLabel()).append("\n");
            } else {
                layerList.add(this.generateRasterTreeNode(layer));
            }
        }

        this.manageLayersInsertion(layerList, parentDestination,
                layers.get(0).getLayerDataSource());

        return existingLayers;
    }

    private String generateUnduplicateAliasForLayer(GPShortLayerBean layer,
            GPBeanTreeModel parentDestination) {
        String originalName = layer.getLayerTitle();
        return buildAlias(originalName, parentDestination);
    }

    private boolean checkDuplicatedLayer(GPShortLayerBean layer,
            GPBeanTreeModel parentDestination) {
        for (ModelData element : parentDestination.getChildren()) {
            if (element != null && element instanceof GPLayerTreeModel
                    && ((GPLayerTreeModel) element).getTitle().equals(
                            layer.getLayerTitle())) {
                return true;
            }
        }
        return false;
    }

    private RasterTreeNode generateRasterTreeNode(GPShortLayerBean layer,
            String rasterAlias) {
        RasterTreeNode rasterTreeNode = this.generateRasterTreeNode(layer);
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(
                rasterTreeNode);
        rasterTreeNode.setAlias(rasterAlias);
        mementoSave.putOriginalPropertiesInCache(memento);
        return rasterTreeNode;
    }

    private RasterTreeNode generateRasterTreeNode(GPShortLayerBean layer) {
        RasterTreeNode raster = new RasterTreeNode();
        raster.setLayerType(layer.getLayerType());
        raster.setName(layer.getLayerName());
        raster.setTitle(layer.getLayerTitle());
        raster.setLabel(layer.getLayerLabel());
        raster.setDataSource(layer.getLayerDataSource());
        raster.setBbox(layer.getBBox());
        raster.setCrs(layer.getCrs());
        raster.setChecked(false);
        raster.setOpacity(1.0f);
        raster.setSingleTileRequest(false);
        raster.setStyles(new ArrayList<GPStyleStringBeanModel>(0));
        return raster;
    }

}
