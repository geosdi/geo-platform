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
package org.geosdi.geoplatform.gui.client.config.provider;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocoding;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.GeocodingHandlerManager;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.IWFSLayerMarkerGridHandler;
import org.geosdi.geoplatform.gui.client.widget.map.marker.advanced.GeocodingVectorMarker;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.WFSMapModel;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.factory.baselayer.GPBaseLayerFactory;
import org.geosdi.geoplatform.gui.factory.map.DefaultMapFactory;
import org.geosdi.geoplatform.gui.factory.map.GeoPlatformMapFactory;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;

import static org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocodingKeyValue.LATITUDE;
import static org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocodingKeyValue.LONGITUDE;
import static org.geosdi.geoplatform.gui.global.enumeration.BaseLayerValue.OPEN_STREET_MAP;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class MapWidgetProvider implements Provider<MapWidget> {

    private final GeoPlatformMapFactory mapFactory = GWT.create(DefaultMapFactory.class);
    private WFSMapModel mapModel;

    @Override
    public MapWidget get() {
        MapWidget mapWidget = this.mapFactory.createMap("100%", "100%",
                GPBaseLayerFactory.getBaseLayer(OPEN_STREET_MAP), -1);
        mapWidget.getElement().getFirstChildElement().getStyle().setZIndex(0);
        this.mapModel = new WFSMapModel(mapWidget);
        this.mapModel.addLayerChangedHandler();
        return mapWidget;
    }


}
