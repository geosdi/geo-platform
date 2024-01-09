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

import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.GPCoverageDimension;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.GPCoverageDimensions;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.IGPCoverageDimension;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.range.GPCoverageDimensionRange;
import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension.range.IGPCoverageDimensionRange;
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
public class GPCoverageDimensionsJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPCoverageDimensionsJacksonTest.class);

    @Test
    public void a_marshallGPCoverageDimensionsAsXmlStringTest() throws Exception {
        GPCoverageDimensions coverageDimensions = toCovarageDimensions(15);
        logger.info("@@@@@@@@@@@@@@@@@@@GP_COVERAGE_DIMENSIONS : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .writeValueAsString(coverageDimensions));
    }

    @Test
    public void b_unmarshallGPCoverageDimensionsFromXmlStringTest() throws Exception {
        GPCoverageDimensions coverageDimensions = JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<dimensions>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#0</description>\n"
                        + "        <name>NAME#0</name>\n"
                        + "        <range>\n"
                        + "            <max>0.0</max>\n"
                        + "            <min>-1.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#1</description>\n"
                        + "        <name>NAME#1</name>\n"
                        + "        <range>\n"
                        + "            <max>1.0</max>\n"
                        + "            <min>0.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#2</description>\n"
                        + "        <name>NAME#2</name>\n"
                        + "        <range>\n"
                        + "            <max>2.0</max>\n"
                        + "            <min>1.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#3</description>\n"
                        + "        <name>NAME#3</name>\n"
                        + "        <range>\n"
                        + "            <max>3.0</max>\n"
                        + "            <min>2.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#4</description>\n"
                        + "        <name>NAME#4</name>\n"
                        + "        <range>\n"
                        + "            <max>4.0</max>\n"
                        + "            <min>3.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#5</description>\n"
                        + "        <name>NAME#5</name>\n"
                        + "        <range>\n"
                        + "            <max>5.0</max>\n"
                        + "            <min>4.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#6</description>\n"
                        + "        <name>NAME#6</name>\n"
                        + "        <range>\n"
                        + "            <max>6.0</max>\n"
                        + "            <min>5.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#7</description>\n"
                        + "        <name>NAME#7</name>\n"
                        + "        <range>\n"
                        + "            <max>7.0</max>\n"
                        + "            <min>6.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#8</description>\n"
                        + "        <name>NAME#8</name>\n"
                        + "        <range>\n"
                        + "            <max>8.0</max>\n"
                        + "            <min>7.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#9</description>\n"
                        + "        <name>NAME#9</name>\n"
                        + "        <range>\n"
                        + "            <max>9.0</max>\n"
                        + "            <min>8.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#10</description>\n"
                        + "        <name>NAME#10</name>\n"
                        + "        <range>\n"
                        + "            <max>10.0</max>\n"
                        + "            <min>9.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#11</description>\n"
                        + "        <name>NAME#11</name>\n"
                        + "        <range>\n"
                        + "            <max>11.0</max>\n"
                        + "            <min>10.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#12</description>\n"
                        + "        <name>NAME#12</name>\n"
                        + "        <range>\n"
                        + "            <max>12.0</max>\n"
                        + "            <min>11.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#13</description>\n"
                        + "        <name>NAME#13</name>\n"
                        + "        <range>\n"
                        + "            <max>13.0</max>\n"
                        + "            <min>12.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "    <coverageDimension>\n"
                        + "        <description>DESCRIPTION#14</description>\n"
                        + "        <name>NAME#14</name>\n"
                        + "        <range>\n"
                        + "            <max>14.0</max>\n"
                        + "            <min>13.0</min>\n"
                        + "        </range>\n"
                        + "    </coverageDimension>\n"
                        + "</dimensions>"), GPCoverageDimensions.class);
        logger.info("#####################GP_COVERAGE_DIMENSIONS : {}\n", coverageDimensions);
    }

    @Test
    public void c_marshallGPCoverageDimensionsAsJsonStringTest() throws Exception {
        GPCoverageDimensions coverageDimensions = toCovarageDimensions(3);
        logger.info("@@@@@@@@@@@@@@@@@@@GP_COVERAGE_DIMENSIONS : \n{}\n", jacksonSupport.getDefaultMapper()
                .writeValueAsString(coverageDimensions));
    }

    @Test
    public void d_unmarshallGPCoverageDimensionsFromJsonStringTest() throws Exception {
        logger.info("###################GP_COVERAGE_DIMENSIONS : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"dimensions\" : {\n"
                        + "    \"coverageDimension\" : [ {\n"
                        + "      \"description\" : \"DESCRIPTION#0\",\n"
                        + "      \"name\" : \"NAME#0\",\n"
                        + "      \"range\" : {\n"
                        + "        \"max\" : 0.0,\n"
                        + "        \"min\" : -1.0\n"
                        + "      }\n" + "    }, {\n"
                        + "      \"description\" : \"DESCRIPTION#1\",\n"
                        + "      \"name\" : \"NAME#1\",\n"
                        + "      \"range\" : null\n"
                        + "    }, {\n"
                        + "      \"description\" : \"DESCRIPTION#2\",\n"
                        + "      \"name\" : \"NAME#2\",\n"
                        + "      \"range\" : {\n"
                        + "        \"max\" : 2.0,\n"
                        + "        \"min\" : 1.0\n"
                        + "      }\n"
                        + "    } ]\n"
                        + "  }\n"
                        + "}"), GPCoverageDimensions.class));
    }

    /**
     * @param number
     * @return {@link GPCoverageDimensions}
     */
    public static GPCoverageDimensions toCovarageDimensions(int number) {
        checkArgument(number > 0, "The Parameter number must be greather than zero.");
        GPCoverageDimensions coverageDimensions = new GPCoverageDimensions();
        coverageDimensions.setCoverageDimension(iterate(0, n -> n + 1)
                .limit(number)
                .boxed()
                .map(GPCoverageDimensionsJacksonTest::toCoverageDimension)
                .collect(toList()));
        return coverageDimensions;
    }

    /**
     * @param value
     * @return {@link IGPCoverageDimension}
     */
    public static IGPCoverageDimension toCoverageDimension(Integer value) {
        checkArgument(value != null, "The Parameter value must not be null.");
        IGPCoverageDimension coverageDimension = new GPCoverageDimension();
        coverageDimension.setName("NAME#" + value);
        coverageDimension.setDescription("DESCRIPTION#" + value);
        IGPCoverageDimensionRange range = new GPCoverageDimensionRange();
        range.setMax(value.doubleValue());
        range.setMin(value.doubleValue() - 1);
        coverageDimension.setRange(value % 2 == 0 ? range : null);
        return coverageDimension;
    }
}