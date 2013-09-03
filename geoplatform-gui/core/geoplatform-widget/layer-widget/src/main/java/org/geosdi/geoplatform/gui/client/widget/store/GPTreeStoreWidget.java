/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.store;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.MementoSaveBuilder;
import org.geosdi.geoplatform.gui.client.model.memento.save.MementoSaveOperations;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.store.GenericTreeStoreWidget;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPShortLayerBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;
import org.geosdi.geoplatform.gui.puregwt.grid.event.DeselectGridElementEvent;
import org.geosdi.geoplatform.gui.puregwt.grid.event.DeselectGridRecordEvent;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.LayersProgressTextEvent;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPTreeStoreWidget extends GenericTreeStoreWidget
        implements ISave<MementoSaveAddedLayers> {

    private LayersProgressTextEvent layersTextEvent = new LayersProgressTextEvent();
    private VisitorAddElement visitorAdd = new VisitorAddElement();
    private DeselectGridElementEvent deselectGridElement = new DeselectGridElementEvent();
    private DeselectGridRecordEvent deselectGridRecord = new DeselectGridRecordEvent();

    /*
     * @param theTree
     */
    public GPTreeStoreWidget(GPTreePanel<GPBeanTreeModel> theTree) {
        super(theTree);
    }

    @Override
    public void executeSave(final MementoSaveAddedLayers memento) {
        MementoSaveOperations.mementoSaveAddedLayer(memento,
                LayerModuleConstants.INSTANCE.GPTreeStoreWidget_mementoSuccessMessageText(),
                LayerModuleConstants.INSTANCE.GPTreeStoreWidget_mementoFailMessageText());
    }

    @Override
    public void addRasterLayersFromCapabilities(List<GPRasterBean> layers) {
        this.addRasterLayers(layers, GPTreeStoreOperations.LAYERS_FROM_WMS_CAPABILITIES);
        LayerHandlerManager.fireEvent(deselectGridElement);
    }

    @Override
    public void addVectorLayersFromCapabilities(List<GPVectorBean> layers) {
        this.changeProgressBarMessage(LayerModuleConstants.INSTANCE.
                GPTreeStoreWidget_progressBarMessageText());
    }

    @Override
    public void addRasterLayersFromPublisher(List<? extends GPLayerBean> layers) {
        this.addRasterLayers(layers, GPTreeStoreOperations.LAYERS_FROM_PUBLISHER);
    }

    @Override
    public void addVectorLayersFromPublisher(List<GPVectorBean> layers) {
        throw new UnsupportedOperationException("You need to implement this functionality");
    }

    @Override
    public void addLayersFromCopyMenu(List<? extends GPLayerBean> layers) {
        List<GPRasterBean> rasterBeanList = Lists.<GPRasterBean>newArrayList();
        List<GPVectorBean> vectorBeanList = Lists.<GPVectorBean>newArrayList();
        for (GPLayerBean layer : layers) {
            if (layer instanceof GPRasterBean) {
                rasterBeanList.add((GPRasterBean) layer);
            } else if (layer instanceof GPVectorBean) {
                vectorBeanList.add((GPVectorBean) layer);
            }
        }
        if (!rasterBeanList.isEmpty()) {
            this.addRasterLayersFromCopyMenu(rasterBeanList);
        }
        if (!vectorBeanList.isEmpty()) {
            this.addVectorLayersFromCopyMenu(vectorBeanList);
        }
    }

    @Override
    public void addRasterLayersFromCopyMenu(List<GPRasterBean> layers) {
        this.addRasterLayers(layers, GPTreeStoreOperations.LAYERS_FROM_COPY_MENU);
    }

    @Override
    public void addVectorLayersFromCopyMenu(List<GPVectorBean> layers) {
        throw new UnsupportedOperationException("You need to implement this functionality");
    }

    @Override
    public void addRasterLayersFromCatalog(List<? extends GPShortLayerBean> layers) {
        Map<String, List<GPShortLayerBean>> layerMap = this.getLayerMapDataSource(layers);

        StringBuilder existingLayers = new StringBuilder();
        for (Map.Entry<String, List<GPShortLayerBean>> e : layerMap.entrySet()) {
            existingLayers = this.addShortRasterLayers(e.getValue(), existingLayers);
        }

        LayerHandlerManager.fireEvent(deselectGridRecord);
        this.createAlertMessage(existingLayers);
    }

    private void addRasterLayers(List<? extends GPLayerBean> layers,
            GPTreeStoreOperations sourceLayer) {
        if (layers == null || layers.isEmpty()) { // TODO assert
            return;
        }

        //TODO: Check the utility of this code
        //this.changeProgressBarMessage("Loading " + layers.size() + " Raster Layers into the Store");
        GPBeanTreeModel parentDestination = this.tree.getSelectionModel().getSelectedItem();
        // TODO The parent is always expanded?
        System.out.println("*** Parent expanded: " + super.tree.isExpanded(parentDestination));
        if (!super.tree.isExpanded(parentDestination)) {
            super.tree.setExpanded(parentDestination, true);
        }

        List<GPBeanTreeModel> layerList = Lists.<GPBeanTreeModel>newArrayList();
        StringBuilder existingLayers = new StringBuilder();
        for (GPLayerBean layer : layers) {
            boolean duplicatedLayer = this.checkDuplicatedLayer(layer, parentDestination);
            if (duplicatedLayer) {
                String aliasForCopiedLayer = this.generateUnduplicateAliasForLayer(
                        layer, parentDestination);
                layerList.add(this.duplicateRaster(layer, aliasForCopiedLayer));
                existingLayers.append(layer.getLabel()).append("\n");
            } else {
                this.manageAddLayerFromSource(layerList, layer, sourceLayer);
            }
        }

        this.manageLayersInsertion(layerList, parentDestination,
                layers.get(0).getDataSource());
        this.createAlertMessage(existingLayers);
    }

    private StringBuilder addShortRasterLayers(List<? extends GPShortLayerBean> layers,
            StringBuilder existingLayers) {
        if (layers == null || layers.isEmpty()) { // TODO assert
            return null;
        }

        GPBeanTreeModel parentDestination = this.tree.getSelectionModel().getSelectedItem();
        List<GPBeanTreeModel> layerList = Lists.<GPBeanTreeModel>newArrayList();

        for (GPShortLayerBean layer : layers) {
            boolean duplicatedLayer = this.checkDuplicatedLayer(layer, parentDestination);
            if (duplicatedLayer) {
                String aliasForCopiedLayer = this.generateUnduplicateAliasForLayer(
                        layer, parentDestination);
                layerList.add(this.generateRasterTreeNode(layer, aliasForCopiedLayer));
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

    private String generateUnduplicateAliasForLayer(GPLayerBean layer,
            GPBeanTreeModel parentDestination) {
        String originalName;
        if (layer.getAlias() != null) {
            originalName = layer.getAlias();
        } else {
            originalName = layer.getTitle();
        }
        return this.buildAlias(originalName, parentDestination);
    }

    private String buildAlias(String originalName, GPBeanTreeModel parentDestination) {
        final String COPY_STRING = " - "
                + LayerModuleConstants.INSTANCE.GPTreeStoreWidget_copyStringText() + " (";
        int suffix = 1;
        int copyIndex = originalName.indexOf(COPY_STRING);
        String modifiedName;
        if (copyIndex != -1) {
            String intValue = originalName.substring(
                    copyIndex + COPY_STRING.length(), originalName.lastIndexOf(')'));
            suffix = Integer.parseInt(intValue) + 1;
            modifiedName = originalName.substring(0, originalName.lastIndexOf('(') + 1)
                    + suffix + ')';
        } else {
            modifiedName = originalName + COPY_STRING + suffix + ")";
        }
        return this.recursivelySearchAlias(parentDestination.getChildren(),
                modifiedName, suffix);
    }

    private String recursivelySearchAlias(List<ModelData> elements, String modifiedName, int suffix) {
        for (ModelData element : elements) {
            if (element != null && element instanceof GPLayerTreeModel
                    && ((GPLayerTreeModel) element).getAlias() != null
                    && ((GPLayerTreeModel) element).getAlias().equals(modifiedName)) {
                modifiedName = modifiedName.substring(0,
                        modifiedName.lastIndexOf('(') + 1)
                        + ++suffix + ')';
                return this.recursivelySearchAlias(elements, modifiedName, suffix);
            }
        }
        return modifiedName;
    }

    private boolean checkDuplicatedLayer(GPLayerBean layer, GPBeanTreeModel parentDestination) {
        for (ModelData element : parentDestination.getChildren()) {
            if (element != null && element instanceof GPLayerTreeModel
                    && ((GPLayerTreeModel) element).getTitle().equals(layer.getTitle())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDuplicatedLayer(GPShortLayerBean layer, GPBeanTreeModel parentDestination) {
        for (ModelData element : parentDestination.getChildren()) {
            if (element != null && element instanceof GPLayerTreeModel
                    && ((GPLayerTreeModel) element).getTitle().equals(layer.getLayerTitle())) {
                return true;
            }
        }
        return false;
    }

    private void manageAddLayerFromSource(List<GPBeanTreeModel> layerList,
            GPLayerBean layer, GPTreeStoreOperations sourceLayer) {
        switch (sourceLayer) {
            case LAYERS_FROM_COPY_MENU:
                layerList.add(this.duplicateRaster(layer));
                break;

            case LAYERS_FROM_WMS_CAPABILITIES:
            case LAYERS_FROM_PUBLISHER:
                layerList.add(this.generateRasterTreeNode(layer));
                break;
            default:
                System.out.println("### No Tree Store Operation ###");
        }
    }

    private void changeProgressBarMessage(String message) {
        layersTextEvent.setMessage(message);
        LayerHandlerManager.fireEvent(layersTextEvent);
    }

    private void manageLayersInsertion(List<GPBeanTreeModel> layerList,
            GPBeanTreeModel parentDestination, String urlServer) {
        if (layerList.size() > 0) {
            this.tree.getStore().insert(parentDestination, layerList, 0, true);
            this.visitorAdd.insertLayerElements(layerList, parentDestination);

            MementoSaveAddedLayers mementoSaveLayer = new MementoSaveAddedLayers(this);
            mementoSaveLayer.setAddedLayers(MementoSaveBuilder.generateMementoLayerList(layerList));
            mementoSaveLayer.setDescendantMap(this.visitorAdd.getFolderDescendantMap());
            IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
            mementoSave.add(mementoSaveLayer);
        }
    }

    private void createAlertMessage(StringBuilder existingLayers) {
        if (existingLayers.length() != 0) {
            GeoPlatformMessage.alertMessage(LayerModuleConstants.INSTANCE.GPTreeStoreWidget_renameAlertTitleText(),
                    LayerModuleMessages.INSTANCE.GPTreeStoreWidget_renameAlertBodyMessage(existingLayers.toString()));
        }
    }

    private RasterTreeNode generateRasterTreeNode(GPLayerBean layer) {
        RasterTreeNode raster = new RasterTreeNode();
        raster.setChecked(false);
        raster.setOpacity(1.0f);
        raster.setAbstractText(layer.getAbstractText());
        raster.setBbox(layer.getBbox());
        raster.setTitle(layer.getTitle());
        raster.setCrs(layer.getCrs());
        raster.setDataSource(layer.getDataSource());
        raster.setLabel(layer.getLabel());
        raster.setLayerType(layer.getLayerType());
        raster.setName(layer.getName());
        raster.setStyles(layer.getStyles());
        return raster;
    }

    private RasterTreeNode generateRasterTreeNode(GPShortLayerBean layer, String rasterAlias) {
        RasterTreeNode rasterTreeNode = this.generateRasterTreeNode(layer);
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(rasterTreeNode);
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
        raster.setStyles(new ArrayList<GPStyleStringBeanModel>(0));
        return raster;
    }

    private RasterTreeNode duplicateRaster(GPLayerBean layer, String alias) {
        RasterTreeNode raster = this.duplicateRaster(layer);
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(raster);
        raster.setAlias(alias);
        mementoSave.putOriginalPropertiesInCache(memento);
        return raster;
    }

    private RasterTreeNode duplicateRaster(GPLayerBean layer) {
        RasterTreeNode raster = new RasterTreeNode();
        raster.setAbstractText(layer.getAbstractText());
        raster.setCrs(layer.getCrs());
        raster.setDataSource(layer.getDataSource());
        raster.setLabel(layer.getLabel());
        raster.setName(layer.getName());
        raster.setTitle(layer.getTitle());
        raster.setBbox(layer.getBbox());
        raster.setLayerType(layer.getLayerType());
        raster.setStyles(Lists.<GPStyleStringBeanModel>newArrayList(layer.getStyles()));
        return raster;
    }

    private Map<String, List<GPShortLayerBean>> getLayerMapDataSource(List<? extends GPShortLayerBean> layers) {
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
}
