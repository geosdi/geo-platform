/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.map;

import org.geosdi.geoplatform.gui.client.widget.map.event.geocoding.GeocodingEventHandler;
import org.geosdi.geoplatform.gui.client.widget.map.marker.advanced.GeocodingVectorMarker;
import org.geosdi.geoplatform.gui.client.widget.map.popup.PopupMapWidget;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.model.IGeoPlatformLocation;
import org.geosdi.geoplatform.gui.puregwt.GPToolbarActionHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.event.UpdateModelAndButtonEvent;
import org.geosdi.geoplatform.gui.puregwt.geocoding.GPGeocodingHandlerManager;
import org.gwtopenmaps.openlayers.client.LonLat;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPGeocodingWidget implements GeocodingEventHandler {

    private GeoPlatformMap mapWidget;
    private PopupMapWidget popupWidget = new PopupMapWidget("GP-GeoCoder-Popup");
    /** TODO : Think a way to have this in configuration **/
    private GeocodingVectorMarker geocoderMarker = new GeocodingVectorMarker(); //new GeocodingMarker();

    public GPGeocodingWidget(GeoPlatformMap theMapWidget) {
        this.mapWidget = theMapWidget;
        GPGeocodingHandlerManager.addHandler(GeocodingEventHandler.TYPE, this);
    }

    @Override
    public void register() {
        this.mapWidget.getMap().addLayer(geocoderMarker.getMarkerLayer());
        this.geocoderMarker.addControl(this.mapWidget.getMap());
    }

    @Override
    public void unregister() {
        this.geocoderMarker.removeControl(this.mapWidget.getMap());
        this.geocoderMarker.removeMarker();
        this.mapWidget.getMap().removeLayer(this.geocoderMarker.getMarkerLayer());
        this.mapWidget.getMap().removePopup(popupWidget.getPopup());
    }

    @Override
    public void onRegisterGeocodingLocation(IGeoPlatformLocation bean,
            GPCoordinateReferenceSystem crs, Object provider) {
        LonLat center = new LonLat(bean.getLon(), bean.getLat());

        if (!crs.getCode().equals(this.mapWidget.getMap().getProjection())) {
            center.transform(crs.getCode(), this.mapWidget.getMap().getProjection());
        }

        this.geocoderMarker.setProvider(provider);
        this.geocoderMarker.addMarker(center, this.mapWidget.getMap());
        GPToolbarActionHandlerManager.fireEvent(new UpdateModelAndButtonEvent(bean));
    }

    @Override
    public void removeMarker() {
        this.geocoderMarker.removeMarker();
    }

    @Override
    public void activateComponent(boolean flag) {
        if (flag) {
            register();
        } else {
            unregister();
        }
    }
}
