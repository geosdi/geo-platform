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
package org.geosdi.geoplatform.connector.wms.request.theories;

import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.kvp.GPWMSKeyValuePairBuilder.GPWMSBaseKeyValuePairBuilder;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequestBuilder.builder;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class GPWMSGetMapBaseRequestBuilderTheoriesTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetMapBaseRequestBuilderTheoriesTest.class);
    //
    private static final GPWMSBaseKeyValuePairBuilder<GPWMSGetMapBaseRequest> builder = builder();

    @DataPoints
    public static String[] data() {
        return new String[]{"http://150.145.141.180/geoserver/topp/wms?service=WMS&version=1.1.0&request=GetMap&layers=topp%3Atasmania_roads&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers",
                "service=WMS&version=1.1.0&request=GetMap&layers=topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers",
                "service=WMS&version=1.1.0&request=GetMap&layers=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers",
                "&version=1.1.0&  &layers=test,prova,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers",
                "&layers=test,prova,admin:prova,admin:test,topp:states,siti_protetti:zsc,retenatura:zsc,topp%3Atasmania_roads,topp:states,siti_protetti:zsc,retenatura:zsc&bbox=145.19754%2C-43.423512%2C148.27298000000002%2C-40.852802&width=768&height=641&srs=EPSG%3A4326&format=application/openlayers"};
    }

    @Theory
    public void wmsGetMapBaseRequestBuilderTest(@Nonnull(when = NEVER) String theURL) throws Exception {
        checkArgument((theURL != null) && !(theURL.trim().isEmpty()), "The Parameter url must not be null or an empty string.");
        logger.info("#######################Trying to build for URL : {}\n", theURL);
        logger.info("######################GP_WMS_GET_MAP_BASE_REQUEST : {}\n", builder.withKeyValuePair(theURL).build());
    }
}