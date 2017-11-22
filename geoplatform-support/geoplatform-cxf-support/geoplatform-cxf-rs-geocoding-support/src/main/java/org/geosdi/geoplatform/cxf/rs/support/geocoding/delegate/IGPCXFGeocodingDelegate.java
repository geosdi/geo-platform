package org.geosdi.geoplatform.cxf.rs.support.geocoding.delegate;

import org.geosdi.geoplatform.cxf.rs.support.geocoding.response.IGPGeocodingResult;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPCXFGeocodingDelegate {

    /**
     * @param lang
     * @param address
     * @return {@link IGPGeocodingResult}
     * @throws Exception
     */
    IGPGeocodingResult searchAddress(String address, String lang) throws Exception;
}
