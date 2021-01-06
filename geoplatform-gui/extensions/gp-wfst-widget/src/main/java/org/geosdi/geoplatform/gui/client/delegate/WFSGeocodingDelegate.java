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
package org.geosdi.geoplatform.gui.client.delegate;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressDTO;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.GeocodingHandlerManager;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.*;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.WFSZoomEvent;
import org.geosdi.geoplatform.gui.client.service.WFSGeocodingService;
import org.geosdi.geoplatform.gui.client.service.request.GPGeocodingAddressRequestDTO;
import org.geosdi.geoplatform.gui.client.service.response.FeatureCollectionResponse;
import org.geosdi.geoplatform.gui.client.service.response.WFSAddressStore;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Singleton
public class WFSGeocodingDelegate implements IWFSGeocodingDelegate {

    private static final UnMaskGeocodingGridEvent UN_MASK_GEOCODING_GRID_EVENT = new UnMaskGeocodingGridEvent();
    private static final ClearGeocodingGridEvent CLEAR_GEOCODING_GRID_EVENT = new ClearGeocodingGridEvent();
    private static final WFSZoomEvent WFS_ZOOM_EVENT = new WFSZoomEvent();
    private static final RemoveMarkerEvent REMOVE_MARKER_EVENT = new RemoveMarkerEvent();
    //
    private static final ClearLayerEvent CLEAR_LAYER_EVENT = new ClearLayerEvent();
    static WFSGeocodingService wfsGeocodingService = GWT.create(WFSGeocodingService.class);
    @Inject
    private GPEventBus eventBus;

    /**
     *
     * @param gpGeocodingAddressRequestDTO
     */
    @Override
    public void searchAddress(GPGeocodingAddressRequestDTO gpGeocodingAddressRequestDTO) {
        wfsGeocodingService.searchAddress(gpGeocodingAddressRequestDTO, new MethodCallback<FeatureCollectionResponse>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GeocodingHandlerManager.fireEvent(CLEAR_GEOCODING_GRID_EVENT);
                GeocodingHandlerManager.fireEvent(UN_MASK_GEOCODING_GRID_EVENT);
                GeocodingHandlerManager.fireEvent(CLEAR_LAYER_EVENT);
                GeoPlatformMessage.errorMessage("Error",method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, FeatureCollectionResponse featureCollectionResponse) {
                GeocodingHandlerManager.fireEvent(REMOVE_MARKER_EVENT);
                eventBus.fireEvent(WFS_ZOOM_EVENT);
                GeocodingHandlerManager.fireEvent(new PopulateGeocodingGridEvent(featureCollectionResponse));
            }
        });
    }
}
