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
package org.geosdi.geoplatform.gui.client.widget.store.executor;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.MementoSaveBuilder;
import org.geosdi.geoplatform.gui.client.model.memento.save.MementoSaveOperations;
import org.geosdi.geoplatform.gui.client.model.memento.save.bean.MementoSaveAddedLayers;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorAddElement;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.store.GPTreeStoreOperations;
import org.geosdi.geoplatform.gui.client.widget.tree.store.executor.GenericTreeStoreExecutor;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPStyleStringBeanModel;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class BaseTreeStoreExecutor extends GenericTreeStoreExecutor<MementoSaveAddedLayers> {

    protected VisitorAddElement visitorAdd = new VisitorAddElement();

    public BaseTreeStoreExecutor(GPTreePanel<GPBeanTreeModel> thetree) {
        super(thetree);
    }

    /**
     * @param memento
     */
    @Override
    public void executeSave(MementoSaveAddedLayers memento) {
        MementoSaveOperations.mementoSaveAddedLayer(memento,
                LayerModuleConstants.INSTANCE.GPTreeStoreWidget_mementoSuccessMessageText(),
                LayerModuleConstants.INSTANCE.GPTreeStoreWidget_mementoFailMessageText());
    }

    @Override
    public void manageLayersInsertion(List<GPBeanTreeModel> layerList, GPBeanTreeModel parentDestination, String urlServer) {
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

    protected void manageAddLayerFromSource(List<GPBeanTreeModel> layerList, GPLayerBean layer, GPTreeStoreOperations sourceLayer) {
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

    /**
     * @param layer
     * @return {@link RasterTreeNode}
     */
    protected RasterTreeNode generateRasterTreeNode(GPLayerBean layer) {
        RasterTreeNode raster = new RasterTreeNode();
        raster.setChecked(false);
        raster.setOpacity(1.0f);
        raster.setSingleTileRequest(false);
        raster.setAbstractText(layer.getAbstractText());
        raster.setBbox(layer.getBbox());
        raster.setTitle(layer.getTitle());
        raster.setCrs(layer.getCrs());
        raster.setDataSource(layer.getDataSource());
        raster.setLabel(layer.getLabel());
        raster.setLayerType(layer.getLayerType());
        raster.setName(layer.getName());
        raster.setStyles(layer.getStyles());
        if(layer instanceof GPRasterBean) {
            raster.setMaxScale(((GPRasterBean) layer).getMaxScale());
            raster.setMinScale(((GPRasterBean) layer).getMinScale());
            if (((GPRasterBean) layer).isTemporalLayer()) {
                raster.setDimension(((GPRasterBean) layer).getDimension());
                raster.setExtent(((GPRasterBean) layer).getExtent());
                raster.getExtent().setRange(!((GPRasterBean) layer).getExtent().getValue().contains("/P"));
            }
        }
        return raster;
    }

    protected RasterTreeNode duplicateRaster(GPLayerBean layer) {
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
        if(layer instanceof GPRasterBean) {
            raster.setMaxScale(((GPRasterBean) layer).getMaxScale());
            raster.setMinScale(((GPRasterBean) layer).getMinScale());
            if (((GPRasterBean) layer).isTemporalLayer()) {
                raster.setDimension(((GPRasterBean) layer).getDimension());
                raster.setExtent(((GPRasterBean) layer).getExtent());
                raster.getExtent().setRange(!((GPRasterBean) layer).getExtent().getValue().contains("/P"));
            }
        }
        return raster;
    }

    protected String recursivelySearchAlias(List<ModelData> elements, String modifiedName, int suffix) {
        for (ModelData element : elements) {
            if (element != null && element instanceof GPLayerTreeModel && ((GPLayerTreeModel) element).getAlias() != null && ((GPLayerTreeModel) element).getAlias().equals(
                    modifiedName)) {
                modifiedName = modifiedName.substring(0, modifiedName.lastIndexOf('(') + 1) + ++suffix + ')';
                return this.recursivelySearchAlias(elements, modifiedName, suffix);
            }
        }
        return modifiedName;
    }
}