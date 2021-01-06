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
package org.geosdi.geoplatform.gui.client.widget.wfs.map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocoding;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.GeocodingHandlerManager;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.IWFSLayerMarkerGridHandler;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.FeatureMapWidthEvent;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.IncreaseWidthEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.WFSGPHandlerManager;
import org.geosdi.geoplatform.gui.client.widget.map.marker.advanced.GeocodingVectorMarker;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.event.WFSHasLayerChangedHandler;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.layer.WFSGeocodingVectorMarker;
import org.geosdi.geoplatform.gui.client.widget.wfs.map.store.WFSMapLayersStore;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.impl.map.GPMapModel;
import org.geosdi.geoplatform.gui.impl.map.event.LayerMapChangedHandler;
import org.geosdi.geoplatform.gui.impl.map.store.IMapLayersStore;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.layer.Layer;

import static org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocodingKeyValue.LATITUDE;
import static org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocodingKeyValue.LONGITUDE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSMapModel extends GPMapModel implements WFSHasLayerChangedHandler,IWFSLayerMarkerGridHandler {

    private final WFSGeocodingVectorMarker geocodingVectorMarker;

    public WFSMapModel(MapWidget theMapWidget) {
        super(theMapWidget);
        this.geocodingVectorMarker = new WFSGeocodingVectorMarker(this.mapWidget.getMap(),"WFSGeocoding-Marker-Vector-Layer");
        GeocodingHandlerManager.addHandler(IWFSLayerMarkerGridHandler.TYPE, this);
    }

    @Override
    public HandlerRegistration addLayerChangedHandler() {
        return WFSGPHandlerManager.addHandler(LayerMapChangedHandler.TYPE, this.layersStore);
    }

    @Override
    protected final IMapLayersStore createStore() {
        return new WFSMapLayersStore(this.mapWidget);
    }

    @Override
    public void addMarker(WFSAddressGeocoding wfsAddressGeocoding) {
        this.mapWidget.getMap().addLayer(this.geocodingVectorMarker.getMarkerLayer());
        this.geocodingVectorMarker.removeMarker();
        LonLat center = new LonLat((double)wfsAddressGeocoding.get(LONGITUDE.getValue()), (double)wfsAddressGeocoding.get(LATITUDE.getValue()));
        center.transform(GPCoordinateReferenceSystem.WGS_84.getCode(),
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode());
        this.geocodingVectorMarker.addMarker(center, Boolean.TRUE);
    }

    @Override
    public void clearLayer() {
        this.geocodingVectorMarker.removeMarker();
        if(this.mapWidget.getMap().getLayerByName("WFSGeocoding-Marker-Vector-Layer") != null)
            this.mapWidget.getMap().removeLayer(this.geocodingVectorMarker.getMarkerLayer());
    }

    @Override
    public void removeMarker() {
        this.geocodingVectorMarker.removeMarker();
    }
}