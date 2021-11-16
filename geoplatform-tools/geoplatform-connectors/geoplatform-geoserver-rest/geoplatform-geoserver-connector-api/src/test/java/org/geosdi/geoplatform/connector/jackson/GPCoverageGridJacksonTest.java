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

import org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;

import static org.geosdi.geoplatform.connector.geoserver.styles.sld.GeoserverStyleSLDV100Request.JACKSON_JAXB_XML_SUPPORT;
import static org.geosdi.geoplatform.connector.jackson.GPGeoserverJacksonTest.jacksonSupport;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GPCoverageGridJacksonTest {

    private static final Logger logger = LoggerFactory.getLogger(GPCoverageGridJacksonTest.class);

    @Test
    public void a_marshallGPCoverageGridAsXmlStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_COVERAGE_GRID : \n{}\n", JACKSON_JAXB_XML_SUPPORT.writeAsString(GPCoverageGridJacksonTest::toCoverageGrid));
    }

    @Test
    public void b_unmarshallGPCoverageGridFromXmlStringTest() throws Exception {
        logger.info("####################GP_COVERAGE_GRID : \n{}\n", JACKSON_JAXB_XML_SUPPORT.getDefaultMapper()
                .readValue(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                        + "<grid dimension=\"2\">\n"
                        + "    <crs>EPSG:26713</crs>\n"
                        + "    <range>\n"
                        + "        <high>634 477</high>\n"
                        + "        <low>0 0</low>\n"
                        + "    </range>\n"
                        + "    <transform>\n"
                        + "        <scaleX>30.0</scaleX>\n"
                        + "        <scaleY>-30.0</scaleY>\n"
                        + "        <shearX>0.0</shearX>\n"
                        + "        <shearY>0.0</shearY>\n"
                        + "        <translateX>589995.0</translateX>\n"
                        + "        <translateY>4927995.0</translateY>\n"
                        + "    </transform>\n"
                        + "</grid>"), GPCoverageGrid.class));
    }

    @Test
    public void c_marshallGPCoverageGridAsJsonStringTest() throws Exception {
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_COVERAGE_GRID : \n{}\n", jacksonSupport.writeAsString(GPCoverageGridJacksonTest::toCoverageGrid));
    }

    @Test
    public void d_unmarshallGPCoverageGridFromJsonStringTest() throws Exception {
        logger.info("####################GP_COVERAGE_GRID : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"grid\" : {\n"
                        + "    \"@dimension\" : \"2\",\n"
                        + "    \"crs\" : \"EPSG:26713\",\n"
                        + "    \"range\" : {\n"
                        + "      \"high\" : \"634 477\",\n"
                        + "      \"low\" : \"0 0\"\n"
                        + "    },\n"
                        + "    \"transform\" : {\n"
                        + "      \"scaleX\" : 30.0,\n"
                        + "      \"scaleY\" : -30.0,\n"
                        + "      \"shearX\" : 0.0,\n"
                        + "      \"shearY\" : 0.0,\n"
                        + "      \"translateX\" : 589995.0,\n"
                        + "      \"translateY\" : 4927995.0\n"
                        + "    }\n"
                        + "  }\n"
                        + "}"), GPCoverageGrid.class));
    }

    @Test
    public void e_marshallGPCoverageGridAsJsonStringTest() throws Exception {
        IGPCoverageGrid coverageGrid = toCoverageGrid();
        coverageGrid.setTransform(null);
        coverageGrid.setRange(null);
        logger.info("@@@@@@@@@@@@@@@@@@@@GP_COVERAGE_GRID : \n{}\n", jacksonSupport.getDefaultMapper().writeValueAsString(coverageGrid));
    }

    @Test
    public void f_unmarshallGPCoverageGridFromJsonStringTest() throws Exception {
        logger.info("####################GP_COVERAGE_GRID : {}\n", jacksonSupport.getDefaultMapper()
                .readValue(new StringReader("{\n"
                        + "  \"grid\" : {\n"
                        + "    \"@dimension\" : \"2\",\n"
                        + "    \"crs\" : \"EPSG:26713\",\n"
                        + "    \"range\" : null,\n"
                        + "    \"transform\" : null\n"
                        + "  }\n"
                        + "}"), GPCoverageGrid.class));
    }

    /**
     * @return {@link IGPCoverageGrid}
     */
    public static IGPCoverageGrid toCoverageGrid() {
        IGPCoverageGrid grid = new GPCoverageGrid();
        grid.setDimension("2");
        grid.setCrs("EPSG:26713");
        IGPCoverageGridRange gridRange = new GPCoverageGridRange();
        gridRange.setHigh("634 477");
        gridRange.setLow("0 0");
        grid.setRange(gridRange);
        IGPCoverageGridTransformation gridTransformation = new GPCoverageGridTransformation();
        gridTransformation.setScaleX(30d);
        gridTransformation.setScaleY(-30d);
        gridTransformation.setShearX(0d);
        gridTransformation.setShearY(0d);
        gridTransformation.setTranslateX(589995d);
        gridTransformation.setTranslateY(4927995d);
        grid.setTransform(gridTransformation);
        return grid;
    }
}