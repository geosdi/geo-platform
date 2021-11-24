/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.jackson;

import org.geosdi.geoplatform.connector.geoserver.model.srs.GPGeoserverResponseSRS;
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
public class GPGeoserverResponseSRSJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverResponseSRSJacksonTest.class);

    @Test
    public void a_marshalGPGeoserverResponseSRSAsXmlStringTest() throws Exception {
        logger.info("###################GP_GEOSERVER_RESPONSE_SRS : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPGeoserverResponseSRSJacksonTest::toResponseSRS));
    }

    @Test
    public void b_unmarshallGPGeoserverResponseSRSFromXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@GP_GEOSERVER_RESPONSE_SRS : {}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<responseSRS>\n"
                        + "    <string>0</string>\n"
                        + "    <string>1</string>\n"
                        + "    <string>2</string>\n"
                        + "    <string>3</string>\n"
                        + "    <string>4</string>\n"
                        + "    <string>5</string>\n"
                        + "    <string>6</string>\n"
                        + "    <string>7</string>\n"
                        + "    <string>8</string>\n"
                        + "    <string>9</string>\n"
                        + "    <string>10</string>\n"
                        + "    <string>11</string>\n"
                        + "    <string>12</string>\n"
                        + "    <string>13</string>\n"
                        + "    <string>14</string>\n"
                        + "</responseSRS>"), GPGeoserverResponseSRS.class));
    }

    @Test
    public void c_marshallGPGeoserverResponseSRSAsJsonStringTest() throws Exception {
        logger.info("################GP_GEOSERVER_RESPONSE_SRS : \n{}\n", jacksonSupport.writeAsString(GPGeoserverResponseSRSJacksonTest::toResponseSRS));
    }

    @Test
    public void d_unmarshallGPGeoserverResponseSRSFromJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@GP_GEOSERVER_RESPONSE_SRS : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"responseSRS\" : {\n"
                        + "    \"string\" : [ \"0\", \"1\", \"2\", \"3\", \"4\", \"5\", \"6\", \"7\", \"8\"," +
                        " \"9\", \"10\", \"11\", \"12\", \"13\", \"14\" ]\n" + "  }\n" + "}"), GPGeoserverResponseSRS.class));
    }

    /**
     * @param number
     * @return {@link GPGeoserverResponseSRS}
     */
    public static GPGeoserverResponseSRS toResponseSRS(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        GPGeoserverResponseSRS responseSRS = new GPGeoserverResponseSRS();
        responseSRS.setValues(iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(String::valueOf)
                .collect(toList()));
        return responseSRS;
    }

    /**
     * @return {@link GPGeoserverResponseSRS}
     */
    public static GPGeoserverResponseSRS toResponseSRS() {
        return toResponseSRS(15);
    }
}