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
package org.geosdi.geoplatform.services.geocoding;

import io.swagger.annotations.Api;
import org.apache.cxf.annotations.FastInfoset;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.geosdi.geoplatform.response.*;
import org.geosdi.geoplatform.services.rs.path.GPServiceRSPathConfig;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Public interface to define the service operations mapped via REST and SOAP
 * using {@link http://cxf.apache.org/} Framework.
 *
 * @author Giuseppe La Scaleia - CNR IMAA - geoSDI
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * @author Nazzareno Sileno - CNR IMAA - geoSDI
 */
@CrossOriginResourceSharing(
        allowOrigins = {
                "http://127.0.0.1:9001"
        },
        allowCredentials = true,
        maxAge = 1,
        allowHeaders = {
                "X-custom-1", "X-custom-2"
        },
        exposeHeaders = {
                "X-custom-3", "X-custom-4"
        }
)
@Path(value = GPServiceRSPathConfig.DEFAULT_GEOCODING_RS_SERVICE_PATH)
@Api(value = GPServiceRSPathConfig.GP_GEOCODING_SERVICE_RS_PATH,
        description = "GeoPlatform Geocoding REST Service Core")
@Consumes(value = {MediaType.APPLICATION_JSON})
@Produces(value = {MediaType.APPLICATION_JSON})
@FastInfoset(serializerMinAttributeValueSize = 200, serializerMaxAttributeValueSize = 400,
        serializerMinCharacterContentChunkSize = 100, serializerAttributeValueMapMemoryLimit = 200,
        serializerMaxCharacterContentChunkSize = 300, serializerCharacterContentChunkMapMemoryLimit = 200)
public interface GeoPlatformGeocodingService{

    @GET
    @Path(value = GPServiceRSPathConfig.SEARCH_ADDRESS_PATH)
    List<AddressDTO> gpSearchAddress(@QueryParam(value = "address") String address) throws Exception;

}
