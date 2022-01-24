/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.jackson.rule;

import org.geosdi.geoplatform.connector.geoserver.model.security.rule.GPGeoserverRule;
import org.geosdi.geoplatform.connector.geoserver.model.security.rule.GeoserverRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static java.lang.String.valueOf;
import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.emptyJacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPGeoserverRuleJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverRuleJacksonTest.class);

    @Test
    public void a_marshallGPGeoserverRuleAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_RULE : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverRuleJacksonTest::toGeoserverRule));
    }

    @Test
    public void b_unmarshallGPGeoserverRuleFromXmlStringTest() throws Exception {
        logger.info("####################GP_GEOSERVER_RULE : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<rule resource=\"*.*.w\">GROUP_ADMIN,ADMIN</rule>"), GPGeoserverRule.class));
    }

    @Test
    public void c_marshallGPGeoserverRuleAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@GP_GEOSERVER_RULE : \n{}\n", emptyJacksonSupport.writeAsString(GPGeoserverRuleJacksonTest::toGeoserverRule));
    }

    @Test
    public void d_unmarshallGPGeoserverRuleFromJsonStringTest() throws Exception {
        logger.info("##################GP_GEOSERVER_RULE : {}\n", emptyJacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"*.*.w\" : \"GROUP_ADMIN,ADMIN\"\n"
                        + "}"), GeoserverRule.class));
    }

    /**
     * @return {@link GeoserverRule}
     */
    public static GeoserverRule toGeoserverRule() {
        GeoserverRule rule = new GPGeoserverRule();
        rule.setResource("*.*.w");
        rule.setValue("GROUP_ADMIN,ADMIN");
        return rule;
    }

    /**
     * @param value
     * @return {@link GeoserverRule}
     */
    public static GeoserverRule toGeoserverRule(int value) {
        GeoserverRule rule = new GPGeoserverRule();
        rule.setResource("RESOURCE#".concat(valueOf(value)));
        rule.setValue("VALUE#".concat(valueOf(value)));
        return rule;
    }
}