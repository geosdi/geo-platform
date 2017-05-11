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
public class WFSGetFeaturePeUinsStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeaturePeUinsStaxReaderTest.class);
    //
    private static LayerSchemaDTO peUinsLayerSchema;
    private static GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    private static File getFeaturePeUins;

    @BeforeClass
    public static void beforeClass() throws Exception {
        peUinsLayerSchema = jaxbContextBuilder.unmarshal(new File("./src/test/resources/reader/LayerSchemaPeUins.xml"),
                LayerSchemaDTO.class);
        getFeaturePeUins = Paths.get("./src/test/resources/reader/GetFeaturePeUins.xml").toFile();
        Assert.assertNotNull("The LayerSchemaDTO for peUins must not be null.", peUinsLayerSchema);
        Assert.assertNotNull("The File getFeaturePeUins must not be null.", getFeaturePeUins);
    }

    @Test
    public void setPeUinsLayerSchemaStaxReaderTest() throws Exception {
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(peUinsLayerSchema);
        FeatureCollectionDTO featureCollectionDTO = featureReaderStAX.read(getFeaturePeUins);
        for (FeatureDTO featureDTO : featureCollectionDTO.getFeatures()) {
            logger.info("###############################FEATURE : {}\n", featureDTO);
        }
    }
}
