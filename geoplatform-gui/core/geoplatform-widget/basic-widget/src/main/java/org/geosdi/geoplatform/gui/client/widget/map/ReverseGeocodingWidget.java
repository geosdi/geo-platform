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
package org.geosdi.geoplatform.gui.client.widget.map;

import org.geosdi.geoplatform.gui.client.i18n.template.PopupTemplateConstants;
import org.geosdi.geoplatform.gui.client.widget.map.event.reversegeocoding.ReverseGeocodingEventHandler;
import org.geosdi.geoplatform.gui.client.widget.map.marker.advanced.ReverseGeocodingVectorMarker;
import org.geosdi.geoplatform.gui.client.widget.map.popup.PopupMapWidget;
import org.geosdi.geoplatform.gui.client.widget.map.popup.template.PopupTemplate;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.factory.map.GPApplicationMap;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.model.IGeoPlatformLocation;
import org.geosdi.geoplatform.gui.puregwt.GPToolbarActionHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.event.UpdateModelEvent;
import org.geosdi.geoplatform.gui.puregwt.geocoding.GPGeocodingHandlerManager;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.event.MapClickListener;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 *
 */
public abstract class ReverseGeocodingWidget implements
        ReverseGeocodingEventHandler {

    protected GeoPlatformMap mapWidget;
    protected ReverseGeocodingDispatch dispatcher;
    private boolean activated;
    /**
     * TODO : Think a way to have this in configuration *
     */
    private final ReverseGeocodingVectorMarker rGMarker; //new ReverseGeocodingMarker();
    private final PopupMapWidget popupWidget = new PopupMapWidget(
            "GP-Reverse-GeoCoder-Popup");
    private MapClickListener listener;
    private LonLat lonlat;
    private boolean busy;

    /**
     *
     * @param theMapWidget
     * @param layerName
     * @param source
     */
    public ReverseGeocodingWidget(GeoPlatformMap theMapWidget, String layerName,
            GPGeoCoderProvider source) {
        this.mapWidget = theMapWidget;
        this.rGMarker = new ReverseGeocodingVectorMarker(this.mapWidget.getMap(),
                layerName, source.getProvider());
        GPGeocodingHandlerManager.addHandlerToSource(
                ReverseGeocodingEventHandler.TYPE,
                source.getProvider(), this);
        this.dispatcher = this.createDispatcher();
        this.createListener();
    }

    @Override
    public final void activateComponent(boolean flag) {
        if (flag) {
            register();
        } else {
            unregister();
        }
    }

    @Override
    public final void register() {
        registerChild();
        if (!activated) {
            this.mapWidget.getMap().addLayer(this.rGMarker.getMarkerLayer());
            this.activated = true;
        }
        this.rGMarker.addControl();
        this.mapWidget.getMap().addMapClickListener(listener);
    }

    @Override
    public final void unregister() {
        unregisterChild();
        this.rGMarker.removeControl();
        this.clearWidgetStatus();
    }

    @Override
    public final void onAddMarkerByLatLon(LonLat theLonLat) {
        this.lonlat = theLonLat;
        addMarker();
    }

    /**
     * Clear Widget : 1) Remove Marker Layer from Map 2) MapClickListener from
     * Map 3) Popup from Map 4) Marker from rGMarcker
     *
     */
    public void clearWidgetStatus() {
        this.removeMapElements();
//        this.mapWidget.getMap().removeLayer(this.rGMarker.getMarkerLayer(),
//                false);
        this.mapWidget.getMap().removeMapClickListener(listener);
    }

    /**
     *
     * @param bean
     */
    public void onRequestSuccess(IGeoPlatformLocation theBean) {
        this.mapWidget.getMap().removePopup(this.popupWidget.getPopup());
        if (!theBean.getDescription().equalsIgnoreCase(
                PopupTemplate.ZERO_RESULTS.toString())) {
            this.popupWidget.setContentHTML(
                    PopupTemplate.IMAGE_RESULT_FOUND.toString()
                    + "<br />" + theBean.getDescription());
        } else {
            this.popupWidget.setContentHTML(
                    PopupTemplate.IMAGE_RESULT_NOT_FOUND.toString()
                    + "<br /> "
                    + PopupTemplate.ZERO_RESULTS.toString());
        }

        this.mapWidget.getMap().addPopup(this.popupWidget.getPopup());
        this.busy = false;

        GPToolbarActionHandlerManager.fireEvent(new UpdateModelEvent(theBean));
    }

    /**
     *
     * @param message
     */
    public void onRequestFailure(String message) {
        this.popupWidget.setContentHTML(
                PopupTemplate.IMAGE_SERVICE_ERROR.toString() + "<br />" + message);
        this.mapWidget.getMap().addPopupExclusive(this.popupWidget.getPopup());
        this.busy = false;
    }

    /**
     * This method must be implemented in subClass to create the Dispatcher to
     * forward Request for Reverse Geocoding.
     *
     * @return
     */
    public abstract ReverseGeocodingDispatch createDispatcher();

    /**
     * This method must be implemented by subclass for: <ul> <li>Display A
     * message after Widget is registered</li> <li>Add Control to the Map</li>
     * </ul>
     */
    public abstract void registerChild();

    /**
     * This method must be implemented by subclass for: <ul> <li>Display A
     * message after Widget is unregistered</li> <li>Remove Control to the
     * Map</li> </ul>
     */
    public abstract void unregisterChild();

    /**
     * This method is called by Dispatcher for RPC Call Failure
     */
    public abstract void displayErrorMessage();

    @Override
    public void removePopup() {
        if (this.popupWidget.isPopupVisible()) {
            this.mapWidget.getMap().removePopup(this.popupWidget.getPopup());
        }
    }

    private void removeMapElements() {
        removePopup();
        this.rGMarker.removeMarker();
    }

    private void createListener() {
        this.listener = new MapClickListener() {

            @Override
            public void onClick(MapClickEvent mapClickEvent) {
                lonlat = mapClickEvent.getLonLat();
                addMarker();
            }

        };
    }

    private void addMarker() {
        if (!busy) {
            busy = true;
            removeMapElements();
            sendRequest();
        } else {
            displayErrorMessage();
        }
    }

    /**
     * Send Request to Reverse Geocoding Service
     */
    private void sendRequest() {
        this.rGMarker.addMarker(this.lonlat, true);
        addPopupAndFireEvent();
    }

    @Override
    public final void onUpdateReverseGeocoding(LonLat ll) {
        this.lonlat = ll;
        updateMarker();
    }

    private void updateMarker() {
        if (!busy) {
            busy = true;
            sendRequestForUpdate();
        } else {
            displayErrorMessage();
        }
    }

    private void sendRequestForUpdate() {
        this.mapWidget.getMap().removePopup(this.popupWidget.getPopup());
        addPopupAndFireEvent();
    }

    private void addPopupAndFireEvent() {
        popupWidget.setLonLat(this.lonlat);
        this.popupWidget.setContentHTML(PopupTemplate.IMAGE_LOADING.toString()
                + PopupTemplateConstants.INSTANCE.MESSAGE_LOADING());
        this.mapWidget.getMap().addPopup(popupWidget.getPopup());

        if (dispatcher == null) {
            throw new NullPointerException(
                    "Dispatcher can't be null. Istantiate it.");
        }

        this.dispatcher.processRequest(this);
    }

    /**
     * @return the lonlat with the Map Projection
     */
    public LonLat getLonlat() {
        LonLat lt = new LonLat(this.lonlat.lon(), this.lonlat.lat());

        if (GPApplicationMap.getInstance().getApplicationMap().getMap().getProjection().equals(
                GPCoordinateReferenceSystem.GOOGLE_MERCATOR.getCode())) {
            lt.transform(GPCoordinateReferenceSystem.EPSG_GOOGLE.getCode(),
                    GPCoordinateReferenceSystem.WGS_84.getCode());
        } else {
            //nothing to do  
        }
        return lt;
    }

    /**
     *
     * @return PopupMapWidget
     */
    public PopupMapWidget getPopupWidget() {
        return popupWidget;
    }

}
