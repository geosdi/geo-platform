/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
