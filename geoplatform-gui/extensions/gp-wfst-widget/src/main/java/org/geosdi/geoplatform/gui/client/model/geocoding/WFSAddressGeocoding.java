package org.geosdi.geoplatform.gui.client.model.geocoding;

import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

import static org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocodingKeyValue.DESCRIPTION;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSAddressGeocoding extends GeoPlatformBeanModel {
    
    private static final long serialVersionUID = 4716349916993349631L;
    //

    public WFSAddressGeocoding(WFSAddressDTO wfsAddressDTO) {
        set(DESCRIPTION.getValue(),wfsAddressDTO.getDisplayName() );
    }



}
