package org.geosdi.geoplatform.wfs.reader;

import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
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
public class WFSGetFeatureCreateLayerStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureCreateLayerStaxReaderTest.class);
    //
    private static LayerSchemaDTO createLayerSchema;
    private static GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    private static File getFeatureCreateLayer;

    @BeforeClass
    public static void beforeClass() throws Exception {
        createLayerSchema = jaxbContextBuilder.unmarshal(new File("./src/test/resources/reader/LayerSchemaCreateLayer.xml"),
                LayerSchemaDTO.class);
        getFeatureCreateLayer = Paths.get("./src/test/resources/reader/GetFeatureCreateLayer.xml").toFile();
        Assert.assertNotNull("The LayerSchemaDTO for createLayer must not be null.", createLayerSchema);
        Assert.assertNotNull("The File getFeatureCreateLayer must not be null.", getFeatureCreateLayer);
    }

    @Test
    public void createLayerStaxReaderTest() throws Exception {
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(createLayerSchema);
        FeatureCollectionDTO featureCollectionDTO = featureReaderStAX.read(getFeatureCreateLayer);
        Assert.assertTrue(!(featureCollectionDTO == null));
    }
}
