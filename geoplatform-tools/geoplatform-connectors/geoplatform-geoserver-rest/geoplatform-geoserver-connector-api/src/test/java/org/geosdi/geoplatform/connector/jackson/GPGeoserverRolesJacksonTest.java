/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.connector.geoserver.model.security.role.GPGeoserverRoles;
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
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.emptyJacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPGeoserverRolesJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverRolesJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverRolesAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_ROLES : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverRolesJacksonTest::toGeoserverRoles));
    }

    @Test
    public void b_unmarshallGPGeoserverRolesFromXmlStringTest() throws Exception {
        logger.info("####################GP_GEOSERVER_ROLES : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<roles>\n"
                        + "    <role>0</role>\n"
                        + "    <role>1</role>\n"
                        + "    <role>2</role>\n"
                        + "    <role>3</role>\n"
                        + "    <role>4</role>\n"
                        + "    <role>5</role>\n"
                        + "    <role>6</role>\n"
                        + "    <role>7</role>\n"
                        + "    <role>8</role>\n"
                        + "    <role>9</role>\n"
                        + "</roles>"), GPGeoserverRoles.class));
    }

    @Test
    public void c_marshallGPGeoserverRolesAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_ROLES : \n{}\n", emptyJacksonSupport.writeAsString(GPGeoserverRolesJacksonTest::toGeoserverRoles));
    }

    @Test
    public void d_unmarshallGPGeoserverRolesFromJsonStringTest() throws Exception {
        logger.info("#####################GP_GEOSEVER_ROLES : {}\n", emptyJacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n" + "  \"roles\" : [ \"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\", \"9\" ]\n" + "}"), GPGeoserverRoles.class));
    }

    /**
     * @return {@link GPGeoserverUsers}
     */
    public static GPGeoserverRoles toGeoserverRoles() {
        return toGeoserverRoles(10);
    }

    /**
     * @param number
     * @return {@link GPGeoserverRoles}
     */
    public static GPGeoserverRoles toGeoserverRoles(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        GPGeoserverRoles roles = new GPGeoserverRoles();
        roles.setRoles(iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(String::valueOf)
                .collect(toList()));
        return roles;
    }
}