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
package org.geosdi.geoplatform.wfs.reader;

import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.reader.stax.GetFeatureGeoJsonStaxGml3Reader;
import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.jaxb.IGPJAXBContextBuilder;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSGetFeatureStaxReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.geojson.WFSGetFeatureGeoJsonStaxReader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.File;
import java.nio.file.Paths;

import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.jaxb.jakarta.GPJAXBJakartaContextBuilder.jakartaContextBuilder;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAKARTA;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.Assert.assertNotNull;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WFSGetFeatureToppTasmaniaRoadsStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureToppTasmaniaRoadsStaxReaderTest.class);
    //
    private static LayerSchemaDTO toppTasmaniaRoadsLayerSchema;
    private static IGPJAXBContextBuilder jakartaContextBuilder = jakartaContextBuilder();
    private static File getFeatureToppTasmaniaRoads;
    private static final StopWatch stopWatch = new StopWatch("wfsGetFeatureToppTasmaniaRoad");
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(JAKARTA, UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);

    @BeforeClass
    public static void beforeClass() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "reader")
                .collect(joining(separator, "", separator));
        toppTasmaniaRoadsLayerSchema = jakartaContextBuilder.unmarshal(new File(basePath.concat("LayerSchemaToppTasmaniaRoads.xml")), LayerSchemaDTO.class);
        getFeatureToppTasmaniaRoads = Paths.get(basePath.concat("GetFeatureToppTasmaniaRoads.xml")).toFile();
        assertNotNull("The LayerSchemaDTO for topp:tasmania_roads must not be null.", toppTasmaniaRoadsLayerSchema);
        assertNotNull("The File getFeatureToppTasmaniaRoads must not be null.", getFeatureToppTasmaniaRoads);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        logger.info(stopWatch.prettyPrint());
    }

    @Test
    public void a_toppTasmaniaRoadsSchemaStaxReaderTest() throws Exception {
        stopWatch.start("wfsGetFeatureJTS");
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(toppTasmaniaRoadsLayerSchema);
        FeatureCollectionDTO featureCollectionDTO = featureReaderStAX.read(getFeatureToppTasmaniaRoads);
        stopWatch.stop();
        for (FeatureDTO featureDTO : featureCollectionDTO.getFeatures()) {
            logger.info("###############################FEATURE : {}\n", featureDTO);
        }
    }

    @Test
    public void b_toppTasmaniaRoadsSchemaGeoJsonStaxReaderTest() throws Exception {
        stopWatch.start("wfsGetFeatureGeoJson");
        WFSGetFeatureGeoJsonStaxReader featureGeoJsonStaxReader = new WFSGetFeatureGeoJsonStaxReader(toppTasmaniaRoadsLayerSchema);
        FeatureCollection featureCollection = featureGeoJsonStaxReader.read(getFeatureToppTasmaniaRoads);
        stopWatch.stop();
        logger.info("####################FEATURE_COLLECTION : \n{}\n", JACKSON_SUPPORT
                .getDefaultMapper().writeValueAsString(featureCollection));
    }

    @Test
    public void c_toppTasmaniaRoadsGeoJsonStaxReaderTest() throws Exception {
        stopWatch.start("wfsGetFeatureGeoJsonWithoutSchema");
        GetFeatureGeoJsonStaxGml3Reader geoJsonStaxReader = new GetFeatureGeoJsonStaxGml3Reader();
        FeatureCollection featureCollection = geoJsonStaxReader.read(getFeatureToppTasmaniaRoads);
        stopWatch.stop();
        logger.info("####################FEATURE_COLLECTION_WITHOUT_LAYER_SCHEMA : \n{}\n", JACKSON_SUPPORT
                .getDefaultMapper().writeValueAsString(featureCollection));
    }
}