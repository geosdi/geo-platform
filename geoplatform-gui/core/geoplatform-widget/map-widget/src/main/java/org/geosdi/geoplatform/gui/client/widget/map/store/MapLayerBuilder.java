/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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

import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.impl.map.store.AbstractMapLayerBuilder;
import org.geosdi.geoplatform.gui.impl.map.store.GPMapLayerBuilder;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class MapLayerBuilder extends AbstractMapLayerBuilder<GPLayerBean> implements
        GPMapLayerBuilder {

    public MapLayerBuilder(GeoPlatformMap theMapWidget) {
        super(theMapWidget);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.impl.map.store.GPLayerBuilder#buildRaster(
     * org.geosdi.geoplatform.gui.model.GPRasterBean)
     */
    @Override
    public WMS buildRaster(GPRasterBean rasterBean) {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers(rasterBean.getName());
        if (!rasterBean.getStyles().isEmpty()) {
            wmsParams.setStyles(rasterBean.getStyles().get(0).getStyleString());
        }
        wmsParams.setIsTransparent(true);

        if (rasterBean.getBbox() != null) {

            Bounds bbox = new Bounds(rasterBean.getBbox().getLowerLeftX(),
                    rasterBean.getBbox().getLowerLeftY(),
                    rasterBean.getBbox().getUpperRightX(),
                    rasterBean.getBbox().getUpperRightY());

            bbox.transform(new Projection(rasterBean.getCrs()), new Projection(
                    mapWidget.getMap().getProjection()));

            wmsParams.setMaxExtent(bbox);
        }

        WMSOptions wmsOption = new WMSOptions();
        wmsOption.setIsBaseLayer(false);
        wmsOption.setDisplayInLayerSwitcher(false);
        wmsOption.setDisplayOutsideMaxExtent(true);
        wmsOption.setBuffer(0);
        wmsOption.setRatio(1);

        WMS layer = new WMS(rasterBean.getLabel(), rasterBean.getDataSource(),
                wmsParams, wmsOption);

        layer.setOpacity(rasterBean.getOpacity());

        return layer;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.impl.map.store.GPLayerBuilder#buildVector(
     * org.geosdi.geoplatform.gui.model.GPVectorBean)
     */
    @Override
    public WMS buildVector(GPVectorBean vectorBean) {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        wmsParams.setLayers(vectorBean.getName());
        wmsParams.setStyles("");
        wmsParams.setIsTransparent(true);

        if (vectorBean.getBbox() != null) {

            Bounds bbox = new Bounds(vectorBean.getBbox().getLowerLeftX(),
                    vectorBean.getBbox().getLowerLeftY(),
                    vectorBean.getBbox().getUpperRightX(),
                    vectorBean.getBbox().getUpperRightY());

            bbox.transform(new Projection(vectorBean.getCrs()), new Projection(
                    mapWidget.getMap().getProjection()));

            wmsParams.setMaxExtent(bbox);
        }

        WMSOptions wmsOption = new WMSOptions();
        wmsOption.setIsBaseLayer(false);
        wmsOption.setDisplayInLayerSwitcher(false);
        wmsOption.setDisplayOutsideMaxExtent(true);
        wmsOption.setBuffer(0);
        wmsOption.setRatio(1);

        String dataSource = vectorBean.getDataSource();

        dataSource = dataSource.replaceAll("wfs", "wms");

        return new WMS(vectorBean.getLabel(), dataSource, wmsParams, wmsOption);
    }
}
