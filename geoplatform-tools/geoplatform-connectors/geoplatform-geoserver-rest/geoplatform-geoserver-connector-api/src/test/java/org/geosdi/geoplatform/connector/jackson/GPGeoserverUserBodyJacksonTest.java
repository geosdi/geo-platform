/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.geoserver.model.security.user.GPGeoserverUserBody;
import org.geosdi.geoplatform.connector.geoserver.model.security.user.GeoserverUserBody;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.StringReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverUserBodyJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverUserBodyJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverCreateUserAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_CREATE_USER : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(
                GPGeoserverUserBodyJacksonTest::toGeoserverUserBody));
    }

    @Test
    public void b_unmarshallGPGeoserverCreateUserFromXmlStringTest() throws Exception {
        logger.info("####################GP_GEOSERVER_CREATE_USER : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<user>\n"
                        + "    <userName>username_test</userName>\n"
                        + "    <enabled>true</enabled>\n"
                        + "    <password>password_test</password>\n"
                        + "</user>"), GPGeoserverUserBody.class));
    }

    @Test
    public void c_marshallGPGeoserverCreateUserAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_CREATE_USER : \n{}\n", jacksonSupport.writeAsString(
                GPGeoserverUserBodyJacksonTest::toGeoserverUserBody));
    }

    @Test
    public void d_unmarshallGPGeoserverCreateUserFromJsonStringTest() throws Exception {
        logger.info("####################GP_GEOSERVER_CREATE_USER : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"user\" : {\n"
                        + "    \"userName\" : \"username_test\",\n"
                        + "    \"enabled\" : true,\n"
                        + "    \"password\" : \"password_test\"\n"
                        + "  }\n"
                        + "}"), GeoserverUserBody.class));
    }

    /**
     * @return {@link GeoserverUserBody}
     */
    public static GeoserverUserBody toGeoserverUserBody() {
        GeoserverUserBody createUser = new GPGeoserverUserBody();
        createUser.setUserName("username_test");
        createUser.setPassword("password_test");
        createUser.setEnabled(TRUE);
        return createUser;
    }

    /**
     * @param theUserName
     * @param thePassword
     * @return {@link GeoserverUserBody}
     */
    public static GPGeoserverUserBody toGeoserverUserBody(@Nonnull(when = NEVER) String theUserName, @Nonnull(when = NEVER) String thePassword) {
        checkArgument((theUserName != null) && !(theUserName.trim().isEmpty()), "The Parameter userName must not be null or an empty string.");
        checkArgument((thePassword != null) && !(thePassword.trim().isEmpty()), "The Parameter userName must not be null or an empty string.");
        GPGeoserverUserBody createUser = new GPGeoserverUserBody();
        createUser.setUserName(theUserName);
        createUser.setPassword(thePassword);
        createUser.setEnabled(TRUE);
        return createUser;
    }
}