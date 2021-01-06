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
package org.geosdi.geoplatform.gui.client.widget.wfs.map.store;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import org.geosdi.geoplatform.gui.impl.map.store.GPMapLayersStore;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.WMS;

import java.util.Map.Entry;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSMapLayersStore extends GPMapLayersStore<GPLayerBean, Layer> {

    protected final static Logger logger = Logger.getLogger("WFSMapLayersStore");

    private final WFSMapLayerBuilder layerBuilder;

    /**
     * @param theMapWidget
     */
    public WFSMapLayersStore(MapWidget theMapWidget) {
        super(theMapWidget);
        this.layerBuilder = new WFSMapLayerBuilder(theMapWidget);
    }

    /**
     * @param key
     * @return {@link Boolean}
     */
    @Override
    public boolean containsLayer(GPLayerBean key) {
        return this.layers.containsKey(key);
    }

    /**
     * @param value
     * @return {@link GPLayerBean}
     */
    @Override
    public GPLayerBean getLayer(Layer value) {
        GPLayerBean layerToReturn = null;
        for (Entry<GPLayerBean, Layer> layer : GPSharedUtils.safeCollection(this.layers.entrySet())) {
            if (layer.getValue().getId().equals(value.getId())) {
                layerToReturn = layer.getKey();
                break;
            }
        }
        return layerToReturn;
    }

    /**
     * @param key
     * @return {@link Layer}
     */
    @Override
    public Layer getLayer(GPLayerBean key) {
        return this.layers.get(key);
    }

    /**
     * @param layerBean
     */
    @Override
    public void onDisplayLayer(GPLayerBean layerBean) {
        super.displayLayer(layerBean);
    }

    /**
     * @param layerBean
     */
    @Override
    public void onHideLayer(GPLayerBean layerBean) {
        this.hideLayer(layerBean);
    }

    /**
     * @param layerBean
     */
    @Override
    public void onRemoveLayer(GPLayerBean layerBean) {
        this.removeLayer(layerBean);
    }

    /**
     * @param layer
     */
    @Override
    public void reloadLayer(GPLayerBean layer) {
        WMS wmsLayer = (WMS) this.layers.get(layer);
        if (wmsLayer != null) {
            logger.finest("REDRAW layer: " + layer.getTitle());
            wmsLayer.redraw(true);
        }
    }

    /**
     * @param vectorBean
     */
    @Override
    public void displayVector(GPVectorBean vectorBean) {
    }

    /**
     * @param rasterBean
     */
    @Override
    public void displayRaster(GPRasterBean rasterBean) {
        final WMS layer;
        if (containsLayer(rasterBean)) {
            layer = (WMS) this.layers.get(rasterBean);
            logger.fine("################################LAYER_RETRIVED_FROM_CACHE : " + layer.getName());
            if (!layer.isVisible() || parseInt(layer.getZIndex().toString()) != rasterBean.getzIndex()) {
                layer.setZIndex(rasterBean.getzIndex());
                Scheduler.get().scheduleDeferred(new Command() {

                    @Override
                    public void execute() {
                        layer.setIsVisible(true);
                        layer.redraw(true);
                        logger.info("#####################LAYER_ZINDEX : " + layer.getZIndex().toString());
                    }
                });
            }
        } else {
            layer = (WMS) this.layerBuilder.buildLayer(rasterBean);
            logger.fine("################################LAYER_ADDED_IN_CACHE : " + layer.getName());
            this.layers.put(rasterBean, layer);
            this.mapWidget.getMap().addLayer(layer);
            layer.setZIndex(rasterBean.getzIndex());
            layer.redraw(true);
        }
    }

    @Override
    public void hideLayer(GPLayerBean layerBean) {
        final Layer layer = getLayer(layerBean);
        if (layer != null) {
            Scheduler.get().scheduleDeferred(new Command() {

                @Override
                public void execute() {
                    layer.setIsVisible(false);
                }
            });
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.impl.map.store.ILayersStore#removeLayer(org
     * .geosdi.geoplatform.gui.model.GPLayerBean)
     */
    @Override
    public void removeLayer(GPLayerBean layerBean) {
    }

    /**
     * @param layerBean
     * @param newStyle
     */
    @Override
    public void onChangeStyle(GPRasterBean layerBean,
            String newStyle) {
    }

    /**
     * @param layerBean
     * @param singleTileRequest
     */
    @Override
    public void onChangeSingleTileRequest(GPRasterBean layerBean,
            boolean singleTileRequest) {
    }

    /**
     * @param layerBean
     */
    @Override
    public void onChangeCqlFilter(GPLayerTreeModel layerBean) {
    }

    /**
     * @param layerBean
     */
    @Override
    public void onChangeTimeFilter(GPLayerTreeModel layerBean) {
    }

    /**
     * @param layerBean
     */
    @Override
    public void changeOpacity(GPRasterBean layerBean) {
    }

    /**
     * @param layerBean
     * @param maxScale
     */
    @Override
    public void changeMaxScale(GPRasterBean layerBean, Float maxScale) {
    }

    /**
     * @param layerBean
     * @param minScale
     */
    @Override
    public void changeMinScale(GPRasterBean layerBean, Float minScale) {
    }

    @Override
    public void resetStore() {
        for (Layer layer : layers.values()) {
            if (this.mapWidget.getMap().getLayer(layer.getId()) != null) {
                this.mapWidget.getMap().removeLayer(layer);
            }
        }
        this.layers.clear();
    }

    /**
     * @param layerBean
     */
    @Override
    public void onReloadLayer(GPLayerBean layerBean) {
    }

    /**
     * @param projection
     */
    @Override
    public void onChangeBaseLayer(Projection projection) {
    }

}
