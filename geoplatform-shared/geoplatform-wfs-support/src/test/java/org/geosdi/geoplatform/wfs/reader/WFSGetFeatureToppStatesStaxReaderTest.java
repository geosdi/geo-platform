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
public class WFSGetFeatureToppStatesStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureToppStatesStaxReaderTest.class);
    //
    private static LayerSchemaDTO toppStatesLayerSchema;
    private static GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();
    private static File getFeatureToppStates;

    @BeforeClass
    public static void beforeClass() throws Exception {
        toppStatesLayerSchema = jaxbContextBuilder.unmarshal(new File("./src/test/resources/reader/LayerSchemaToppStates.xml"),
                LayerSchemaDTO.class);
        getFeatureToppStates = Paths.get("./src/test/resources/reader/GetFeatureToppStates.xml").toFile();
        Assert.assertNotNull("The LayerSchemaDTO for topp:states must not be null.", toppStatesLayerSchema);
        Assert.assertNotNull("The File getFeatureToppStates must not be null.", getFeatureToppStates);
    }

    @Test
    public void toppStatesStaxReaderTest() throws Exception {
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(toppStatesLayerSchema);
        FeatureCollectionDTO featureCollectionDTO = featureReaderStAX.read(getFeatureToppStates);
        for (FeatureDTO featureDTO : featureCollectionDTO.getFeatures()) {
            logger.info("###############################FEATURE : {}\n", featureDTO);
        }
    }
}
