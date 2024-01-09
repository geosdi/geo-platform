/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.security.user.GPGeoserverUser;
import org.geosdi.geoplatform.connector.geoserver.model.security.user.GeoserverUser;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;

import java.io.StringReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverUserJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverUserJacksonTest.class);

    @Test
    public void a_marshallGeoserverUserAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@GEOSERVER_USER : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverUserJacksonTest::toGeoserverUser));
    }

    @Test
    public void b_unmarshallGeoserverUserFromXmlStringTest() throws Exception {
        logger.info("#################GEOSERVER_USER : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<user>\n"
                        + "    <userName>username_test</userName>\n"
                        + "    <enabled>true</enabled>\n"
                        + "</user>"), GPGeoserverUser.class));
    }

    @Test
    public void c_marshallGeoserverUserAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@GEOSERVER_USER : \n{}\n", jacksonSupport.writeAsString(GPGeoserverUserJacksonTest::toGeoserverUser));
    }

    @Test
    public void d_unmarshallGeoserverUserFromJsonStringTest() throws Exception {
        logger.info("##################GEOSERVER_USER : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"user\" : {\n"
                        + "    \"userName\" : \"username_test\",\n"
                        + "    \"enabled\" : true\n"
                        + "  }\n"
                        + "}"), GeoserverUser.class));
    }

    /**
     * @return {@link GeoserverUser}
     */
    public static GeoserverUser toGeoserverUser() {
        GeoserverUser user = new GPGeoserverUser();
        user.setUserName("username_test");
        user.setEnabled(TRUE);
        return user;
    }

    /**
     * @param value
     * @return {@link  GeoserverUser}
     */
    public static GeoserverUser toGeoserverUser(@Nonnull(when = NEVER) Integer value) {
        checkArgument(value != null, "The Parameter value must not be null.");
        GeoserverUser user = new GPGeoserverUser();
        user.setUserName("username_test#" + value);
        user.setEnabled(value % 2 == 0 ? TRUE : FALSE);
        return user;
    }
}