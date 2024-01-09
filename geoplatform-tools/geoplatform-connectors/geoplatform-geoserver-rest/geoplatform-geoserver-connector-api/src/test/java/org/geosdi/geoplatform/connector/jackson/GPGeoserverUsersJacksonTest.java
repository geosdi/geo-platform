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

import org.geosdi.geoplatform.connector.geoserver.model.security.user.GPGeoserverUsers;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.iterate;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverUsersJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverUsersJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverUsersAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_USERS : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverUsersJacksonTest::toGeoserverUsers));
    }

    @Test
    public void b_unmarshallGPGeoserverUsersFromXmlStringTest() throws Exception {
        logger.info("###################GP_GEOSERVER_USERS : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<users>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#0</userName>\n"
                        + "        <enabled>true</enabled>\n"
                        + "    </user>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#1</userName>\n"
                        + "        <enabled>false</enabled>\n"
                        + "    </user>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#2</userName>\n"
                        + "        <enabled>true</enabled>\n"
                        + "    </user>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#3</userName>\n"
                        + "        <enabled>false</enabled>\n"
                        + "    </user>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#4</userName>\n"
                        + "        <enabled>true</enabled>\n"
                        + "    </user>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#5</userName>\n"
                        + "        <enabled>false</enabled>\n"
                        + "    </user>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#6</userName>\n"
                        + "        <enabled>true</enabled>\n"
                        + "    </user>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#7</userName>\n"
                        + "        <enabled>false</enabled>\n"
                        + "    </user>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#8</userName>\n"
                        + "        <enabled>true</enabled>\n"
                        + "    </user>\n"
                        + "    <user>\n"
                        + "        <userName>username_test#9</userName>\n"
                        + "        <enabled>false</enabled>\n"
                        + "    </user>\n"
                        + "</users>"), GPGeoserverUsers.class));
    }

    @Test
    public void c_marshallGPGeoserverUsersAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_USERS : \n{}\n", jacksonSupport.writeAsString(GPGeoserverUsersJacksonTest::toGeoserverUsers));
    }

    @Test
    public void d_unmarshallGPGeoserverUsersFromJsonStringTest() throws Exception {
        logger.info("#####################GP_GEOSERVER_USERS : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"users\" : {\n"
                        + "    \"users\" : [ {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#0\",\n"
                        + "        \"enabled\" : true\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#1\",\n"
                        + "        \"enabled\" : false\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#2\",\n"
                        + "        \"enabled\" : true\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#3\",\n"
                        + "        \"enabled\" : false\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#4\",\n"
                        + "        \"enabled\" : true\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#5\",\n"
                        + "        \"enabled\" : false\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#6\",\n"
                        + "        \"enabled\" : true\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#7\",\n"
                        + "        \"enabled\" : false\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#8\",\n"
                        + "        \"enabled\" : true\n"
                        + "      }\n"
                        + "    }, {\n"
                        + "      \"user\" : {\n"
                        + "        \"userName\" : \"username_test#9\",\n"
                        + "        \"enabled\" : false\n"
                        + "      }\n"
                        + "    } ]\n"
                        + "  }\n"
                        + "}"), GPGeoserverUsers.class));
    }

    /**
     * @return {@link GPGeoserverUsers}
     */
    public static GPGeoserverUsers toGeoserverUsers() {
        return toGeoserverUsers(10);
    }

    /**
     * @param number
     * @return {@link GPGeoserverUsers}
     */
    public static GPGeoserverUsers toGeoserverUsers(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        GPGeoserverUsers users = new GPGeoserverUsers();
        users.setUsers(iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(GPGeoserverUserJacksonTest::toGeoserverUser)
                .collect(toList()));
        return users;
    }
}