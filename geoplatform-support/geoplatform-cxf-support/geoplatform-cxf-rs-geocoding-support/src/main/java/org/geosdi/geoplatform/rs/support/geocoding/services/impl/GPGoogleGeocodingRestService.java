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
package org.geosdi.geoplatform.rs.support.geocoding.services.impl;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.apache.cxf.rs.security.cors.LocalPreflight;
import org.geosdi.geoplatform.rs.support.geocoding.delegate.IGPGoogleGeocodingDelegate;
import org.geosdi.geoplatform.rs.support.geocoding.services.api.IGPGoogleGeocodingRestService;
import org.geosdi.geoplatform.rs.support.request.GPGeocodingAddressRequest;
import org.geosdi.geoplatform.rs.support.request.GPRevertGeocodingRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.ok;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowCredentials = true,
        maxAge = 1,
        allowHeaders = {
                "authorization", "content-type", "X-HTTP-Method-Override"
        },
        exposeHeaders = {
                "authorization", "content-type", "X-HTTP-Method-Override"
        }
)
@Service(value = "googleGeocodingRestService")
public class GPGoogleGeocodingRestService implements IGPGoogleGeocodingRestService {

    @Resource(name = "googleGeocodingDelegate")
    private IGPGoogleGeocodingDelegate googleGeocodingDelegate;

    public GPGoogleGeocodingRestService() {
    }

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response searchAddress(GPGeocodingAddressRequest request) throws Exception {
        return ok(this.googleGeocodingDelegate.searchAddress(request)).build();
    }

    /**
     * @param request
     * @return {@link Response}
     * @throws Exception
     */
    @Override
    public Response searchAddress(GPRevertGeocodingRequest request) throws Exception {
        return ok(this.googleGeocodingDelegate.searchAddress(request)).build();
    }

    /**
     * @return {@link Response}
     */
    @OPTIONS
    @Path("{path : .*}")
    @LocalPreflight
    public Response options() {
        return ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, X-HTTP-Method-Override")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }
}