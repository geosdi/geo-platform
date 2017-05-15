package org.geosdi.geoplatform.wfs.reader;

import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSGetFeatureStaxReader;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetFeatureToppTasmaniaRoadsStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureToppTasmaniaRoadsStaxReaderTest.class);
    //
    private static LayerSchemaDTO toppTasmaniaRoadsLayerSchema;
    private static GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    private static File getFeatureToppTasmaniaRoads;

    @BeforeClass
    public static void beforeClass() throws Exception {
        toppTasmaniaRoadsLayerSchema = jaxbContextBuilder.unmarshal(new File("./src/test/resources/reader/LayerSchemaToppTasmaniaRoads.xml"),
                LayerSchemaDTO.class);
        getFeatureToppTasmaniaRoads = Paths.get("./src/test/resources/reader/GetFeatureToppTasmaniaRoads.xml").toFile();
        Assert.assertNotNull("The LayerSchemaDTO for topp:tasmania_roads must not be null.", toppTasmaniaRoadsLayerSchema);
        Assert.assertNotNull("The File getFeatureToppTasmaniaRoads must not be null.", getFeatureToppTasmaniaRoads);
    }

    @Test
    public void toppTasmaniaRoadsSchemaStaxReaderTest() throws Exception {
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(toppTasmaniaRoadsLayerSchema);
        FeatureCollectionDTO featureCollectionDTO = featureReaderStAX.read(getFeatureToppTasmaniaRoads);
        for (FeatureDTO featureDTO : featureCollectionDTO.getFeatures()) {
            logger.info("###############################FEATURE : {}\n", featureDTO);
        }
    }
}
