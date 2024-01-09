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
package org.geosdi.geoplatform.connector.jackson.rule;

import org.geosdi.geoplatform.connector.geoserver.model.security.rule.GPGeoserverRules;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverRulesJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverRulesJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverRulesAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_RULES : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverRulesJacksonTest::toGeoserverRules));
    }

    @Test
    public void b_unmarshallGPGeoserverRulesFromXmlStringTest() throws Exception {
        logger.info("####################GP_GEOSERVER_RULES : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<rules>\n"
                        + "    <rule resource=\"RESOURCE#0\">VALUE#0</rule>\n"
                        + "    <rule resource=\"RESOURCE#1\">VALUE#1</rule>\n"
                        + "    <rule resource=\"RESOURCE#2\">VALUE#2</rule>\n"
                        + "    <rule resource=\"RESOURCE#3\">VALUE#3</rule>\n"
                        + "    <rule resource=\"RESOURCE#4\">VALUE#4</rule>\n"
                        + "    <rule resource=\"RESOURCE#5\">VALUE#5</rule>\n"
                        + "    <rule resource=\"RESOURCE#6\">VALUE#6</rule>\n"
                        + "    <rule resource=\"RESOURCE#7\">VALUE#7</rule>\n"
                        + "    <rule resource=\"RESOURCE#8\">VALUE#8</rule>\n"
                        + "    <rule resource=\"RESOURCE#9\">VALUE#9</rule>\n"
                        + "</rules>"), GPGeoserverRules.class));
    }

    @Test
    public void c_marshallGPGeoserverRulesAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_RULES : \n{}\n", emptyJacksonSupport.writeAsString(GPGeoserverRulesJacksonTest::toGeoserverRules));
    }

    @Test
    public void d_unmarshallGPGeoserverRulesFromJsonStringTest() throws Exception {
        logger.info("###################GP_GEOSERVER_RULES : {}\n", emptyJacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"RESOURCE#0\" : \"VALUE#0\",\n"
                        + "  \"RESOURCE#1\" : \"VALUE#1\",\n"
                        + "  \"RESOURCE#2\" : \"VALUE#2\",\n"
                        + "  \"RESOURCE#3\" : \"VALUE#3\",\n"
                        + "  \"RESOURCE#4\" : \"VALUE#4\",\n"
                        + "  \"RESOURCE#5\" : \"VALUE#5\",\n"
                        + "  \"RESOURCE#6\" : \"VALUE#6\",\n"
                        + "  \"RESOURCE#7\" : \"VALUE#7\",\n"
                        + "  \"RESOURCE#8\" : \"VALUE#8\",\n"
                        + "  \"RESOURCE#9\" : \"VALUE#9\"\n"
                        + "}"), GPGeoserverRules.class));
    }

    @Test
    public void e_unmarshallGPGeoserverRulesFromJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@GP_GEOSERVER_RULES : {}\n", emptyJacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "   \"*.*.w\":\"GROUP_ADMIN,ADMIN\",\n"
                        + "   \"*.*.r\":\"*\"\n" + "}"), GPGeoserverRules.class));
    }

    @Test
    public void f_unmarshallGPGeoserverRulesEmptyJacksonTest() throws Exception {
        logger.info("{}\n", emptyJacksonSupport.getDefaultMapper().readValue(new StringReader("{}"), GPGeoserverRules.class));
    }

    /**
     * @return {@link GPGeoserverRules}
     */
    public static GPGeoserverRules toGeoserverRules() {
        return toGeoserverGroups(10);
    }

    /**
     * @param number
     * @return {@link GPGeoserverRules}
     */
    public static GPGeoserverRules toGeoserverGroups(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        GPGeoserverRules geoserverRules = new GPGeoserverRules();
        geoserverRules.setRules(iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(GPGeoserverRuleJacksonTest::toGeoserverRule)
                .collect(toList()));
        return geoserverRules;
    }
}