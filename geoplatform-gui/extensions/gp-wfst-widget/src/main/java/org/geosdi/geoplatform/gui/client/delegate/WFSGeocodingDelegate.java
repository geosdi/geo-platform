package org.geosdi.geoplatform.gui.client.delegate;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.geosdi.geoplatform.gui.client.service.WFSGeocodingService;
import org.geosdi.geoplatform.gui.client.service.response.WFSAddressStore;

import javax.inject.Singleton;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Singleton
public class WFSGeocodingDelegate implements IWFSGeocodingDelegate {

    static WFSGeocodingService wfsGeocodingService = GWT.create(WFSGeocodingService.class);

    @Override
    public void searchAddress(String address) {
        wfsGeocodingService.searchAddress(address, new MethodCallback<WFSAddressStore>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log("@@@@@@@@@ERROR: " + method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, WFSAddressStore wfsAddressStore) {
                GWT.log("@@@@@@SUCCESS: " + wfsAddressStore);
            }
        });
    }
}
