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
package org.geosdi.geoplatform.gui.client.widget.map.store;

import com.extjs.gxt.ui.client.Registry;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.History;
import org.geosdi.geoplatform.gui.client.widget.map.event.LayerRangeEvent;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.impl.map.store.GPMapLayersStore;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.featureinfo.event.FeatureInfoAddLayer;
import org.geosdi.geoplatform.gui.puregwt.featureinfo.event.FeatureInfoRefreshEvent;
import org.geosdi.geoplatform.gui.puregwt.featureinfo.event.FeatureInfoRemoveLayer;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.event.CleanLegendEvent;
import org.geosdi.geoplatform.gui.puregwt.layers.event.DisplayLegendEvent;
import org.geosdi.geoplatform.gui.puregwt.layers.event.HideLegendEvent;
import org.geosdi.geoplatform.gui.puregwt.layers.event.ReloadLegendEvent;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MapLayersStore extends GPMapLayersStore<GPLayerBean, Layer> {

    protected final static Logger logger = Logger.getLogger("MapLayersStore");
    //
    private final MapLayerBuilder layerBuilder;
    private final DisplayLegendEvent displayLegendEvent = new DisplayLegendEvent();
    private final HideLegendEvent hideLegendEvent = new HideLegendEvent();
    private final ReloadLegendEvent reloadLegendEvent = new ReloadLegendEvent();
    private final CleanLegendEvent cleanLegend = new CleanLegendEvent();
    private final FeatureInfoAddLayer featureInfoAddLayer = new FeatureInfoAddLayer();
    private final FeatureInfoRemoveLayer featureInfoRemoveLayer = new FeatureInfoRemoveLayer();

    /**
     * @param theMapWidget
     */
    public MapLayersStore(MapWidget theMapWidget) {
        super(theMapWidget);
        this.layerBuilder = new MapLayerBuilder(theMapWidget);
    }

    @Override
    public boolean containsLayer(GPLayerBean key) {
        return this.layers.containsKey(key);
    }

    @Override
    public GPLayerBean getLayer(Layer value) {
        for (Entry<GPLayerBean, Layer> layer : GPSharedUtils.safeCollection(this.layers.entrySet())) {
            if (layer.getValue().getId().equals(value.getId())) {
                return layer.getKey();
            }
        }
        return null;
    }

    @Override
    public Layer getLayer(GPLayerBean key) {
        return this.layers.get(key);
    }

    @Override
    public void onDisplayLayer(GPLayerBean layerBean) {
        super.displayLayer(layerBean);
    }

    @Override
    public void onHideLayer(GPLayerBean layerBean) {
        this.hideLayer(layerBean);
    }

    @Override
    public void onRemoveLayer(GPLayerBean layerBean) {
        this.removeLayer(layerBean);
    }

    @Override
    public void reloadLayer(GPLayerBean layer) {
        WMS wmsLayer = (WMS) this.layers.get(layer);
        if (wmsLayer != null) {
            logger.finest("REDRAW layer: " + layer.getTitle());
            wmsLayer.redraw(true);
            this.reloadLegendEvent.setLayerBean(layer);
            LayerHandlerManager.fireEvent(this.reloadLegendEvent);
        }
    }

    @Override
    public void displayVector(GPVectorBean vectorBean) {
        displayLegendEvent.setLayerBean(vectorBean);
        LayerHandlerManager.fireEvent(displayLegendEvent);
        if (containsLayer(vectorBean)) {
            final WMS layer = (WMS) this.layers.get(vectorBean);
            if (!layer.isVisible() || Integer.parseInt(
                    layer.getZIndex().toString())
                    != vectorBean.getzIndex()) {
                layer.setZIndex(vectorBean.getzIndex());
                Scheduler.get().scheduleDeferred(new Command() {
                    @Override
                    public void execute() {
                        layer.setIsVisible(true);
                        layer.redraw(true);
                    }
                });
            }
        } else {
            WMS layer = (WMS) this.layerBuilder.buildLayer(vectorBean);
            this.layers.put(vectorBean, layer);
            this.mapWidget.getMap().addLayer(layer);
            layer.setZIndex(vectorBean.getzIndex());
        }
    }

    @Override
    public void displayRaster(final GPRasterBean rasterBean) {
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        final WMS layer;
        if (containsLayer(rasterBean)) {
            layer = (WMS) this.layers.get(rasterBean);
            if (!layer.isVisible() || Integer.parseInt(layer.getZIndex().toString()) != rasterBean.getzIndex()) {
                layer.setZIndex(rasterBean.getzIndex());
                Scheduler.get().scheduleDeferred(new Command() {

                    @Override
                    public void execute() {
                        layer.setIsVisible(true);
                        layer.redraw(true);
                        if(layer.isInRange()) {
                            displayLegendEvent.setLayerBean(rasterBean);
                            LayerHandlerManager.fireEvent(displayLegendEvent);
                        } else {
                            hideLegendEvent.setLayerBean(rasterBean);
                            LayerHandlerManager.fireEvent(hideLegendEvent);
                        }
                    }
                });
                History.newItem("#" + accountDetail.getUsername() + "-" + rasterBean.getName() + "-VISIBLE");
            }
        } else {
            layer = (WMS) this.layerBuilder.buildLayer(rasterBean);
            this.layers.put(rasterBean, layer);
            this.mapWidget.getMap().addLayer(layer);
            layer.setZIndex(rasterBean.getzIndex());
            layer.redraw(true);
            if(layer.isInRange()) {
                displayLegendEvent.setLayerBean(rasterBean);
                LayerHandlerManager.fireEvent(displayLegendEvent);
            } else {
                hideLegendEvent.setLayerBean(rasterBean);
                LayerHandlerManager.fireEvent(hideLegendEvent);
            }
            History.newItem("#" + accountDetail.getUsername() + "-" + rasterBean.getName() + "-ADDED");
        }
        featureInfoAddLayer.setLayer(layer);
        MapHandlerManager.fireEvent(featureInfoAddLayer);
    }

    @Override
    public void hideLayer(GPLayerBean layerBean) {
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        final Layer layer = getLayer(layerBean);
        if (layer != null) {
            Scheduler.get().scheduleDeferred(new Command() {

                @Override
                public void execute() {
                    layer.setIsVisible(false);
                }
            });
            History.newItem("#" + accountDetail.getUsername() + "-" + layerBean.getName() + "-NOT-VISIBLE");
            featureInfoRemoveLayer.setLayer(layer);
            MapHandlerManager.fireEvent(featureInfoRemoveLayer);
        }
        this.hideLegendEvent.setLayerBean(layerBean);
        LayerHandlerManager.fireEvent(this.hideLegendEvent);
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
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        Layer layer = getLayer(layerBean);
        if (layer != null) {
            this.mapWidget.getMap().removeLayer(layer);
            History.newItem("#" + accountDetail.getUsername() + "-" + layerBean.getName() + "-REMOVED");
            featureInfoRemoveLayer.setLayer(layer);
            MapHandlerManager.fireEvent(featureInfoRemoveLayer);
        }
        this.layers.remove(layerBean);
        this.hideLegendEvent.setLayerBean(layerBean);
        LayerHandlerManager.fireEvent(this.hideLegendEvent);
    }

    @Override
    public void onChangeStyle(GPRasterBean layerBean, String newStyle) {
        WMS layer = (WMS) this.layers.get(layerBean);
        if ((layer != null) && (layer.isVisible())) {
            WMSParams params = new WMSParams();
            params.setStyles(newStyle);
            layer.mergeNewParams(params);
            this.reloadLegendEvent.setLayerBean(layerBean);
            LayerHandlerManager.fireEvent(this.reloadLegendEvent);
        }
    }

    @Override
    public void onChangeSingleTileRequest(GPRasterBean layerBean, boolean singleTileRequest) {
        WMS layer = (WMS) this.layers.get(layerBean);
        if (layer != null && layer.isSingleTile() != singleTileRequest) {
            boolean isVisible = layer.isVisible();
            this.removeLayer(layerBean);
            layer.destroy();
            if (isVisible) {
                this.displayRaster(layerBean);
            }
        }
    }

    @Override
    public void onChangeCqlFilter(GPLayerTreeModel layerBean) {
        WMS layer = (WMS) this.layers.get(layerBean);
        if ((layer != null) && (layer.isVisible())) {
            WMSParams params;
            if (layerBean.getCqlFilter() == null || layerBean.getCqlFilter().trim().equals("")) {
                params = layer.getParams();
                params.removeCQLFilter();
            } else {
                params = new WMSParams();
                params.setCQLFilter(layerBean.getCqlFilter());
            }
            layer.mergeNewParams(params);
        }
    }

    @Override
    public void onChangeTimeFilter(GPLayerTreeModel layerBean) {
        WMS layer = (WMS) this.layers.get(layerBean);
        if ((layer != null) && (layer.isVisible())) {
            WMSParams params;
            if (layerBean.getTimeFilter() == null || layerBean.getTimeFilter().trim().equals("")) {
                params = layer.getParams();
                params.removeTimeFilter();
            } else {
                params = new WMSParams();
                if (layerBean.getVariableTimeFilter() != null) {
                    params.setTimeFilter(layerBean.getVariableTimeFilter());
                } else {
                    params.setTimeFilter(layerBean.getTimeFilter());
                }
            }
            layer.mergeNewParams(params);
            MapHandlerManager.fireEvent(new FeatureInfoRefreshEvent(layer));
        }
    }

    @Override
    public void changeOpacity(GPRasterBean layerBean) {
        Layer layer = getLayer(layerBean);
        if ((layer != null) && (layer.isVisible())) {
            layer.setOpacity(layerBean.getOpacity());
        }
    }

    @Override
    public void changeMaxScale(GPRasterBean layerBean, Float maxScale) {
        WMS layer = (WMS) this.layers.get(layerBean);
        if (layer != null) {
            WMSOptions options = layer.getOptions();
            if (maxScale == null) {
                options.unsetMaxScale();
            } else {
                options.setMaxScale(maxScale);
            }
            layer.addOptions(options);
            layer.calculateInRange();
            layer.redraw(TRUE);
            if (!(layer.isInRange())) {
                layer.setIsVisible(FALSE);
                layer.redraw(TRUE);
                this.hideLegendEvent.setLayerBean(layerBean);
                LayerHandlerManager.fireEvent(this.hideLegendEvent);
            } else if((layer.isInRange())) {
                layer.setIsVisible(TRUE);
                layer.redraw(TRUE);
                this.displayLegendEvent.setLayerBean(layerBean);
                LayerHandlerManager.fireEvent(this.displayLegendEvent);
            }
            logger.log(Level.FINEST, "#################maxScale : " + layer.isInRange());
//            this.reloadLegendEvent.setLayerBean(layerBean);
//            LayerHandlerManager.fireEvent(this.reloadLegendEvent);
//            this.updateLayerLabel(layerBean, layer.isInRange());
        }
    }

    @Override
    public void changeMinScale(GPRasterBean layerBean, Float minScale) {
        WMS layer = (WMS) this.layers.get(layerBean);
        if (layer != null) {
            WMSOptions options = layer.getOptions();
            if (minScale == null) {
                options.unsetMinScale();
            } else {
                options.setMinScale(minScale);
            }
            layer.addOptions(options);
            layer.calculateInRange();
            layer.redraw(TRUE);
            if (!(layer.isInRange())) {
                layer.setIsVisible(FALSE);
                layer.redraw(TRUE);
                this.hideLegendEvent.setLayerBean(layerBean);
                LayerHandlerManager.fireEvent(this.hideLegendEvent);
            } else if((layer.isInRange())) {
                layer.setIsVisible(TRUE);
                layer.redraw(TRUE);
                this.displayLegendEvent.setLayerBean(layerBean);
                LayerHandlerManager.fireEvent(this.displayLegendEvent);
            }
            logger.log(Level.FINEST, "#################maxScale : " + layer.isInRange());
//            this.reloadLegendEvent.setLayerBean(layerBean);
//            LayerHandlerManager.fireEvent(this.reloadLegendEvent);
//            this.updateLayerLabel(layerBean, layer.isInRange());
        }
    }

    /**
     * @param layerBean
     * @param inRange
     */
    private void updateLayerLabel(GPLayerBean layerBean, boolean inRange) {
        logger.log(Level.FINEST, "####################Called updateLayerLabel with Value : " + inRange);
        LayerRangeEvent layerRangeEvent = new LayerRangeEvent(layerBean, inRange);
        LayerHandlerManager.fireEvent(layerRangeEvent);
    }

    @Override
    public void resetStore() {
        for (Layer layer : layers.values()) {
            this.mapWidget.getMap().removeLayer(layer);
        }
        this.layers.clear();
        LayerHandlerManager.fireEvent(cleanLegend);
    }

    @Override
    public void onReloadLayer(GPLayerBean layerBean) {
        this.reloadLayer(layerBean);
    }

    @Override
    public void onChangeBaseLayer(Projection projection) {
        for (GPLayerBean layer : super.layers.keySet()) {
            this.layerBuilder.generateBoundsTransformationFromMap(layer);
        }
    }
}