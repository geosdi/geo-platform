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
package org.geosdi.geoplatform.gui.client.widget.map.store;

import org.geosdi.geoplatform.gui.client.widget.scale.GPScaleWidget;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.impl.map.store.GPMapLayersStore;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.event.DisplayLegendEvent;
import org.geosdi.geoplatform.gui.puregwt.layers.event.HideLegendEvent;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.WMS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class MapLayersStore extends GPMapLayersStore<GPLayerBean, Layer> {

    private MapLayerBuilder layerBuilder;
    private DisplayLegendEvent displayLegend = new DisplayLegendEvent();

    public MapLayersStore(GeoPlatformMap theMapWidget) {
        super(theMapWidget);
        // TODO Auto-generated constructor stub
        this.layerBuilder = new MapLayerBuilder(theMapWidget);
        GPScaleWidget.display("Scale");
    }

    @Override
    public boolean containsLayer(GPLayerBean key) {
        // TODO Auto-generated method stub
        return this.layers.containsKey(key);
    }

    @Override
    public Layer getLayer(GPLayerBean key) {
        // TODO Auto-generated method stub
        return this.layers.get(key);
    }

    @Override
    public void onDisplayLayer(GPLayerBean layerBean) {
        // TODO Auto-generated method stub
        super.displayLayer(layerBean);
    }

    @Override
    public void onHideLayer(GPLayerBean layerBean) {
        // TODO Auto-generated method stub
        this.hideLayer(layerBean);
    }

    @Override
    public void onRemoveLayer(GPLayerBean layerBean) {
        // TODO Auto-generated method stub
        this.removeLayer(layerBean);
    }

    @Override
    public void displayVector(GPVectorBean vectorBean) {
        displayLegend.setLayerBean(vectorBean);
        LayerHandlerManager.fireEvent(displayLegend);
        if (containsLayer(vectorBean)) {
            WMS layer = (WMS) this.layers.get(vectorBean);
            if (!layer.isVisible() || Integer.parseInt(layer.getZIndex()) != vectorBean.getzIndex()) {
                layer.setZIndex(vectorBean.getzIndex());
                layer.setIsVisible(true);
                //layer.redraw();
            }
        } else {
            WMS layer = (WMS) this.layerBuilder.buildLayer(vectorBean);
            this.layers.put(vectorBean, layer);
            this.mapWidget.getMap().addLayer(layer);
            layer.setZIndex(vectorBean.getzIndex());
        }
    }

    @Override
    public void displayRaster(GPRasterBean rasterBean) {
        displayLegend.setLayerBean(rasterBean);
        LayerHandlerManager.fireEvent(displayLegend);
        if (containsLayer(rasterBean)) {
            WMS layer = (WMS) this.layers.get(rasterBean);
            if (!layer.isVisible() || Integer.parseInt(layer.getZIndex()) != rasterBean.getzIndex()) {
                layer.setZIndex(rasterBean.getzIndex());
                layer.setIsVisible(true);
                //layer.redraw();
            }
        } else {
            WMS layer = (WMS) this.layerBuilder.buildLayer(rasterBean);
            this.layers.put(rasterBean, layer);
            this.mapWidget.getMap().addLayer(layer);
            layer.setZIndex(rasterBean.getzIndex());
        }
    }

    @Override
    public void hideLayer(GPLayerBean layerBean) {
        Layer layer = getLayer(layerBean);
        if (layer != null) {
            layer.setIsVisible(false);
        }

        LayerHandlerManager.fireEvent(new HideLegendEvent(layerBean));
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
        // TODO Auto-generated method stub
        Layer layer = getLayer(layerBean);
        if (layer != null) {
            this.mapWidget.getMap().removeLayer(layer);
        }
        this.layers.remove(layerBean);

        LayerHandlerManager.fireEvent(new HideLegendEvent(layerBean));
    }
}
