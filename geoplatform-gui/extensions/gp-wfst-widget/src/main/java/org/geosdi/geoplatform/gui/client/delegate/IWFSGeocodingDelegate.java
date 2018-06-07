package org.geosdi.geoplatform.gui.client.delegate;

import org.geosdi.geoplatform.gui.client.service.request.GPGeocodingAddressRequestDTO;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IWFSGeocodingDelegate {

    /**
     * @param gpGeocodingAddressRequestDTO
     */
    void searchAddress(GPGeocodingAddressRequestDTO gpGeocodingAddressRequestDTO);

}
