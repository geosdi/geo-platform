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
public class WFSGetFeatureSFRestrictedTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureSFRestrictedTest.class);
    //
    private static LayerSchemaDTO sfRestrictedLayerSchema;
    private static GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    private static File getFeatureSFRestricted;

    @BeforeClass
    public static void beforeClass() throws Exception {
        sfRestrictedLayerSchema = jaxbContextBuilder.unmarshal(new File("./src/test/resources/reader/LayerSchemaSFRestricted.xml"),
                LayerSchemaDTO.class);
        getFeatureSFRestricted = Paths.get("./src/test/resources/reader/GetFeatureSFRestricted.xml").toFile();
        Assert.assertNotNull("The LayerSchemaDTO for sfRestricted must not be null.", sfRestrictedLayerSchema);
        Assert.assertNotNull("The File getFeatureSFRestricted must not be null.", getFeatureSFRestricted);
    }

    @Test
    public void sfRestrictedSchemaStaxReaderTest() throws Exception {
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(sfRestrictedLayerSchema);
        FeatureCollectionDTO featureCollectionDTO = featureReaderStAX.read(getFeatureSFRestricted);
        for (FeatureDTO featureDTO : featureCollectionDTO.getFeatures()) {
            logger.info("###############################FEATURE : {}\n", featureDTO);
        }
    }
}
