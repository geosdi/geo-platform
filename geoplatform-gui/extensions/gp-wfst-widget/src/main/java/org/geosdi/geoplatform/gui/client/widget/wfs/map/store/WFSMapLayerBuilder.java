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

import com.extjs.gxt.ui.client.Registry;
import org.geosdi.geoplatform.gui.client.widget.wfs.viewport.WFSViewportUtility;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.enumeration.GlobalRegistryEnum;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.impl.map.store.AbstractMapLayerBuilder;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import java.util.logging.Level;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSMapLayerBuilder extends AbstractMapLayerBuilder<GPLayerBean> {

    public WFSMapLayerBuilder(MapWidget theMapWidget) {
        super(theMapWidget);
    }

    @Override
    public WMS buildRaster(GPRasterBean rasterBean) {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        this.addAuthTuple(wmsParams);
        wmsParams.setLayers(rasterBean.getName());
        if (!rasterBean.getStyles().isEmpty()) {
            wmsParams.setStyles(rasterBean.getStyles().get(0).getStyleString());
        }
        wmsParams.setTransparent(TRUE);
        final String cqlFilter = rasterBean.getCqlFilter();
        if (GPSharedUtils.isNotEmpty(cqlFilter)) {
            wmsParams.setCQLFilter(rasterBean.getCqlFilter());
        }

        if (rasterBean.getVariableTimeFilter() != null) {
            wmsParams.setTimeFilter(rasterBean.getVariableTimeFilter());
        } else {
            wmsParams.setTimeFilter(rasterBean.getTimeFilter());
        }

        WMSOptions wmsOption = new WMSOptions();
        Bounds bbox = this.generateBoundsTransformationFromMap(rasterBean);
        if (bbox != null) {
            wmsOption.setMaxExtent(bbox);
        }

        wmsOption.setIsBaseLayer(FALSE);
        wmsOption.setDisplayInLayerSwitcher(FALSE);
        wmsOption.setDisplayOutsideMaxExtent(TRUE);
        wmsOption.setBuffer(0);
        wmsOption.setRatio(1);

        if (super.mapWidget.getMap().getProjection().equals(GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode())) {
            wmsOption.setProjection(GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        } else if (super.mapWidget.getMap().getProjection().equals(GPCoordinateReferenceSystem.WGS_84.getCode())) {
            wmsOption.setProjection(GPCoordinateReferenceSystem.WGS_84.getCode());
        }
        if (rasterBean.getMaxScale() != null) {
            logger.log(Level.FINE, "buildRaster Max Scale: " + rasterBean.getMaxScale());
            wmsOption.setMaxScale(rasterBean.getMaxScale());
        }
        if (rasterBean.getMinScale() != null) {
            logger.log(Level.FINE, "buildRaster Min Scale: " + rasterBean.getMinScale());
            wmsOption.setMinScale(rasterBean.getMinScale());
        }

        WMS layer = new WMS(rasterBean.getLabel(), rasterBean.getDataSource(), wmsParams, wmsOption);
        layer.setOpacity(rasterBean.getOpacity());
        layer.setSingleTile(rasterBean.isSingleTileRequest());
        return layer;
    }

    public Bounds generateBoundsTransformationFromMap(GPLayerBean layerBean) {
        Bounds bounds = null;
        if (layerBean.getBbox() != null) {
            BBoxClientInfo bbox = layerBean.getBbox();
            bounds = WFSViewportUtility.generateBoundsFromBBOX(bbox);
            bounds.transform(new Projection(layerBean.getCrs()), new Projection(
                    mapWidget.getMap().getProjection()));
        }
        return bounds;
    }

    @Override
    public WMS buildVector(GPVectorBean vectorBean) {
        WMSParams wmsParams = new WMSParams();
        wmsParams.setFormat("image/png");
        this.addAuthTuple(wmsParams);
        wmsParams.setLayers(vectorBean.getName());
        wmsParams.setStyles("");
        wmsParams.setTransparent(TRUE);
        final String cqlFilter = vectorBean.getCqlFilter();
        if (GPSharedUtils.isNotEmpty(cqlFilter)) {
            wmsParams.setCQLFilter(vectorBean.getCqlFilter());
        }

        WMSOptions wmsOption = new WMSOptions();
        if (vectorBean.getBbox() != null) {
            BBoxClientInfo bbox = vectorBean.getBbox();
            Bounds bounds = WFSViewportUtility.generateBoundsFromBBOX(bbox);

            bounds.transform(new Projection(vectorBean.getCrs()), new Projection(
                    mapWidget.getMap().getProjection()));

            wmsOption.setMaxExtent(bounds);
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

    /**
     * TODO : MORE CONTROL HERE
     *
     * @param wmsParams
     */
    private void addAuthTuple(WMSParams wmsParams) {
        IGPAccountDetail accountDetail = Registry.get(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
        String authkey = accountDetail.getAuthkey();
        if (authkey != null) {
            wmsParams.setParameter(GlobalRegistryEnum.AUTH_KEY.getValue(), authkey);
        }
    }
}
