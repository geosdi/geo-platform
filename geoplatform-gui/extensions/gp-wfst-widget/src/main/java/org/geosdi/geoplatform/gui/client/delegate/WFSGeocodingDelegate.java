package org.geosdi.geoplatform.gui.client.delegate;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressDTO;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.GeocodingHandlerManager;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.ClearGeocodingGridEvent;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.ClearLayerEvent;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.PopulateGeocodingGridEvent;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.UnMaskGeocodingGridEvent;
import org.geosdi.geoplatform.gui.client.service.WFSGeocodingService;
import org.geosdi.geoplatform.gui.client.service.response.WFSAddressStore;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
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
    private static final ClearLayerEvent CLEAR_LAYER_EVENT = new ClearLayerEvent();
    static WFSGeocodingService wfsGeocodingService = GWT.create(WFSGeocodingService.class);

    @Override
    public void searchAddress(String address) {
        wfsGeocodingService.searchAddress(address, new MethodCallback<WFSAddressStore>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GeocodingHandlerManager.fireEvent(CLEAR_GEOCODING_GRID_EVENT);
                GeocodingHandlerManager.fireEvent(UN_MASK_GEOCODING_GRID_EVENT);
                GeocodingHandlerManager.fireEvent(CLEAR_LAYER_EVENT);
                GeoPlatformMessage.errorMessage("Error",method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, WFSAddressStore wfsAddressStore) {
                GeocodingHandlerManager.fireEvent(new PopulateGeocodingGridEvent(wfsAddressStore));
//                GeocodingHandlerManager.fireEvent(CLEAR_LAYER_EVENT);
            }
        });
    }
}
