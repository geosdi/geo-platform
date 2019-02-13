package org.geosdi.geoplatform.connector.wms.bridge.store;

import org.geosdi.geoplatform.connector.bridge.implementor.GPWMSGetFeatureInfoReader;
import org.geosdi.geoplatform.connector.bridge.store.GPWMSGetFeatureInfoReaderStore;
import org.geosdi.geoplatform.connector.bridge.store.WMSGetFeatureInfoReaderStore;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class GPWMSGetFeatureInfoReaderStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSGetFeatureInfoReaderStoreTest.class);
    //
    private static final WMSGetFeatureInfoReaderStore store = new GPWMSGetFeatureInfoReaderStore();

    @Test
    public void a_loadWMSGetFeatureReaderASTextPlainTest() throws Exception {
        GPWMSGetFeatureInfoReader<?> textPlainGetFeatureReader = store.getImplementorByKey(TEXT_PLAIN);
        Assert.assertNotNull(textPlainGetFeatureReader);
        logger.info("################################TEXT_PLAIN_READER : {}\n", textPlainGetFeatureReader);
    }

    @Test
    public void b_loadWMSGetFeatureReaderASTextHtmlTest() throws Exception {
        GPWMSGetFeatureInfoReader<?> textHtmlGetFeatureReader = store.getImplementorByKey(TEXT_HTML);
        Assert.assertNotNull(textHtmlGetFeatureReader);
        logger.info("################################TEXT_HTML_READER : {}\n", textHtmlGetFeatureReader);
    }

    @Test
    public void c_loadWMSGetFeatureReaderASJsonTest() throws Exception {
        GPWMSGetFeatureInfoReader<?> jsonGetFeatureReader = store.getImplementorByKey(JSON);
        Assert.assertNotNull(jsonGetFeatureReader);
        logger.info("################################JSON_READER : {}\n", jsonGetFeatureReader);
    }

    @Test
    public void d_loadWMSGetFeatureReaderASGmlTest() throws Exception {
        GPWMSGetFeatureInfoReader<?> gmlGetFeatureReader = store.getImplementorByKey(GML);
        Assert.assertNotNull(gmlGetFeatureReader);
        logger.info("################################GML_READER : {}\n", gmlGetFeatureReader);
    }
}