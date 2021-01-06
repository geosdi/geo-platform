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
package org.geosdi.geoplatform.gui.client.widget.viewport;

import java.util.List;
import org.geosdi.geoplatform.gui.client.i18n.MapModuleConstants;
import org.geosdi.geoplatform.gui.configuration.map.client.GPClientViewport;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BBoxClientInfo;
import org.geosdi.geoplatform.gui.factory.map.GPApplicationMap;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.Projection;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ViewportUtility {

    public static void gotoViewportLocation(Map map, GPClientViewport viewport) {
        BBoxClientInfo bbox = viewport.getBbox();
        Bounds bounds = generateBoundsFromBBOX(bbox);
        LonLat center = bounds.getCenterLonLat();
        if (GPApplicationMap.getInstance().getApplicationMap().getMap().getProjection().equals(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode())) {
            center.transform(GPCoordinateReferenceSystem.WGS_84.getCode(),
                    GPCoordinateReferenceSystem.EPSG_GOOGLE.getCode());
        }

        double zoomLevel = viewport.getZoomLevel();
        map.setCenter(center, (int) zoomLevel);
    }

    public static GPClientViewport generateViewportFromMap(Map map) {
        Projection currentProjection = new Projection(map.getProjection());
        Projection destinationProjection = new Projection(
                GPCoordinateReferenceSystem.WGS_84.getCode());
        Bounds bounds = map.getExtent().transform(currentProjection, destinationProjection);
        BBoxClientInfo bbox = generateBBOXFromBounds(bounds);
        GPClientViewport viewport = new GPClientViewport(MapModuleConstants.INSTANCE.ViewportUtility_newViewportBeanNameText(),
                MapModuleConstants.INSTANCE.ViewportUtility_newViewportDescriptionText(),
                bbox, map.getZoom(), Boolean.FALSE);
        return viewport;
    }

    public static Bounds calculateMaxBound(List<GPLayerBean> layerList, Map map) {
        Bounds bound = null;
        if (layerList != null || layerList.size() > 0) {
            BBoxClientInfo bbox = layerList.get(0).getBbox();
            bound = generateBoundsFromBBOX(bbox);
            Projection currentProjection = new Projection(layerList.get(0).getCrs());
            Projection destinationProjection = new Projection(
                    GPCoordinateReferenceSystem.WGS_84.getCode());
            bound.transform(currentProjection, destinationProjection);
            for (GPLayerBean layerBean : layerList) {
                bbox = layerBean.getBbox();
                Bounds layerBounds = generateBoundsFromBBOX(bbox);
                currentProjection = new Projection(layerBean.getCrs());
                layerBounds.transform(currentProjection, destinationProjection);
                bound.extend(layerBounds);
            }
        }
        return bound;
    }

    public static BBoxClientInfo generateBBOXFromBounds(Bounds bounds) {
        return new BBoxClientInfo(bounds.getLowerLeftX(), bounds.getLowerLeftY(),
                bounds.getUpperRightX(), bounds.getUpperRightY());
    }

    public static Bounds generateBoundsFromBBOX(BBoxClientInfo bbox) {
        return new Bounds(bbox.getLowerLeftX(), bbox.getLowerLeftY(),
                bbox.getUpperRightX(), bbox.getUpperRightY());
    }
}
