package org.geosdi.geoplatform.cxf.rs.support.geocoding.delegate;

import com.google.maps.model.GeocodingResult;
import org.geojson.LngLatAlt;
import org.geojson.Point;
import org.geosdi.geoplatform.cxf.rs.support.geocoding.response.GPGeocodingLocality;
import org.geosdi.geoplatform.cxf.rs.support.geocoding.response.GPGeocodingResult;
import org.geosdi.geoplatform.cxf.rs.support.geocoding.response.IGPGeocodingResult;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.google.spring.services.geocoding.GPGeocodingService;
import org.slf4j.Logger;

import javax.annotation.Resource;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPCXFGeocodingDelegate implements IGPCXFGeocodingDelegate {

    @GeoPlatformLog
    private static Logger logger;
    //
    @Resource(name = "gpGeocodingService")
    private GPGeocodingService gpGeocodingService;

    /**
     * @param address
     * @return {@link IGPGeocodingResult}
     * @throws Exception
     */
    @Override
    public IGPGeocodingResult searchAddress(String address, String lang) throws Exception {
        if ((address == null) || (address.isEmpty())) {
            throw new IllegalParameterFault("The Parameter address must not be null or an empty string.");
        }
        logger.debug("#####################TRYING To find Address : {} with lang : {}\n", address, lang);
        GeocodingResult[] geocodingResults = this.gpGeocodingService.newRequest()
                .address(address).language(lang).await();
        return new GPGeocodingResult(new Long(geocodingResults.length),
                Arrays.stream(geocodingResults)
                        .filter(geocodingResult -> (geocodingResult != null) && (geocodingResult.geometry != null))
                        .map(geocodingResult -> new GPGeocodingLocality(geocodingResult.formattedAddress,
                                new Point(new LngLatAlt(geocodingResult.geometry.location.lng,
                                        geocodingResult.geometry.location.lat))))
                        .collect(toList()));
    }
}
