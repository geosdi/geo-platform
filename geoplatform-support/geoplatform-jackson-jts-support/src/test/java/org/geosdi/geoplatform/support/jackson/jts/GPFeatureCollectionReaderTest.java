package org.geosdi.geoplatform.support.jackson.jts;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class GPFeatureCollectionReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPFeatureCollectionReaderTest.class);
    //
    private static final GPJacksonSupport JTS_JACKSON_SUPPORT = new GPJacksonJTSSupport();
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);

    @Test
    public void a_readFeatureCollectionTest() throws Exception {
        GeometryCollection geometryCollection = JTS_JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new File("./src/test/resources/geojson/FeatureCollection.json"), GeometryCollection.class);
        JTS_JACKSON_SUPPORT.getDefaultMapper().writeValue(new File("./target/GeometryCollection.json"), geometryCollection);
    }

    @Test
    public void b_parseFeatureTest() throws Exception {
        FeatureCollection featureCollection = JACKSON_SUPPORT.getDefaultMapper()
                .readValue(new File("./src/test/resources/geojson/FeatureCollection.json"), FeatureCollection.class);
        for (Feature feature : featureCollection.getFeatures()) {
            String featureAsString = JACKSON_SUPPORT.getDefaultMapper().writeValueAsString(feature);
            logger.info("##########################JTS_GEOMETRY : {}\n", JTS_JACKSON_SUPPORT
                    .getDefaultMapper().readValue(featureAsString, Geometry.class));
        }
    }
}
