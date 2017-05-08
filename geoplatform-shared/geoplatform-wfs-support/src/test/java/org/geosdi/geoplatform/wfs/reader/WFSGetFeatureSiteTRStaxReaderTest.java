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
public class WFSGetFeatureSiteTRStaxReaderTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeatureSiteTRStaxReaderTest.class);
    //
    private static LayerSchemaDTO siteTRLayerSchema;
    private static GPJAXBContextBuilder jaxbContextBuilder = GPJAXBContextBuilder.newInstance();

    @BeforeClass
    public static void beforeClass() throws Exception {
        siteTRLayerSchema = jaxbContextBuilder.unmarshal(new File("./src/test/resources/reader/LayerSchemaSiteTR.xml"),
                LayerSchemaDTO.class);
        Assert.assertNotNull("The LayerSchemaDTO for SiteTR must not be null.", siteTRLayerSchema);
    }

    @Test
    public void siteTRStaxReaderTest() throws Exception {
        File getFeatureSiteTR = Paths.get("./src/test/resources/reader/GetFeatureSiteTR.xml").toFile();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(siteTRLayerSchema);
        FeatureCollectionDTO featureCollectionDTO = featureReaderStAX.read(getFeatureSiteTR);
        for (FeatureDTO featureDTO : featureCollectionDTO.getFeatures()) {
            logger.info("###############################FEATURE : {}\n", featureDTO);
        }
    }
}
