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
package org.geosdi.geoplatform.gui.client.widget.wfs.builder;

import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.impl.map.store.AbstractMapLayerBuilder;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import javax.inject.Inject;

/**
 * <p>A Special Class that showTree WMS to display in {@link MapWidget}</p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FeatureMapLayerBuilder extends AbstractMapLayerBuilder<GPLayerBean> {

    @Inject
    public FeatureMapLayerBuilder(MapWidget theMapWidget) {
        super(theMapWidget);
    }

    @Override
    public WMS buildRaster(GPRasterBean rasterBean) {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");

        wmsParams.setLayers(rasterBean.getName());
        if (!rasterBean.getStyles().isEmpty()) {
            wmsParams.setStyles(rasterBean.getStyles().get(0).getStyleString());
        }
        wmsParams.setTransparent(Boolean.TRUE);

        WMSOptions wmsOption = new WMSOptions();
        Bounds bbox = this.generateBoundsTransformationFromMap(rasterBean);
        if (bbox != null) {
            wmsOption.setMaxExtent(bbox);
        }

        wmsOption.setIsBaseLayer(Boolean.FALSE);
        wmsOption.setDisplayInLayerSwitcher(Boolean.FALSE);
        wmsOption.setDisplayOutsideMaxExtent(Boolean.TRUE);
        wmsOption.setBuffer(0);
        wmsOption.setRatio(1);

        WMS layer = new WMS(rasterBean.getLabel(), rasterBean.getDataSource(),
                wmsParams, wmsOption);

        layer.setSingleTile(rasterBean.isSingleTileRequest());
        layer.setOpacity(rasterBean.getOpacity());

        return layer;
    }

    @Override
    public WMS buildVector(GPVectorBean vectorBean) {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");

        wmsParams.setLayers(vectorBean.getName());
        wmsParams.setStyles("");
        wmsParams.setTransparent(Boolean.TRUE);
        final String cqlFilter = vectorBean.getCqlFilter();
        if (cqlFilter != null && !cqlFilter.equals("")) {
            wmsParams.setCQLFilter(vectorBean.getCqlFilter());
        }

        WMSOptions wmsOption = new WMSOptions();
        Bounds bbox = this.generateBoundsTransformationFromMap(vectorBean);
        if (bbox != null) {
            wmsOption.setMaxExtent(bbox);
        }
        wmsOption.setIsBaseLayer(false);
        wmsOption.setDisplayInLayerSwitcher(false);
        wmsOption.setDisplayOutsideMaxExtent(true);
        wmsOption.setBuffer(0);
        wmsOption.setRatio(1);

        String dataSource = vectorBean.getDataSource();

        dataSource = dataSource.replaceAll("wfs", "wms");

        return new WMS(vectorBean.getLabel(), dataSource, wmsParams, wmsOption);
    }

    private Bounds generateBoundsTransformationFromMap(GPLayerBean layerBean) {
        Bounds bounds = null;
        if (layerBean.getBbox() != null) {
            BBoxClientInfo bbox = layerBean.getBbox();
            bounds = new Bounds(bbox.getLowerLeftX(), bbox.getLowerLeftY(),
                    bbox.getUpperRightX(), bbox.getUpperRightY());
            if (!layerBean.getCrs().equals(
                    super.mapWidget.getMap().getProjection())) {

                bounds.transform(new Projection(layerBean.getCrs()),
                        new Projection(
                                mapWidget.getMap().getProjection()));
            }
        }
        return bounds;
    }
}
