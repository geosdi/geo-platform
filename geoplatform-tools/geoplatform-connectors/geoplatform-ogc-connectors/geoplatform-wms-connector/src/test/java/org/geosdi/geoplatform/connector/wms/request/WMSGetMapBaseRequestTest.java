package org.geosdi.geoplatform.connector.wms.request;

import org.geosdi.geoplatform.connector.server.request.GPWMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.GPWMSGetMapBaseRequest;
import org.geosdi.geoplatform.connector.server.request.WMSBoundingBox;
import org.geosdi.geoplatform.connector.server.request.WMSGetMapBaseRequest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WMSGetMapBaseRequestTest {

    private static final Logger logger = LoggerFactory.getLogger(WMSGetMapBaseRequestTest.class);
    //
    private static final GPWMSBoundingBox wmsBoundinBox = new WMSBoundingBox(-130d, 24d, -66d, 50d);

    @Test
    public void a_wmsBoundigBoxKeyValuePairTest() throws Exception {
        assertTrue(wmsBoundinBox.toWMSKeyValuePair().equalsIgnoreCase("BBOX=-130.0,24.0,-66.0,50.0"));
        logger.info("######################BOUNDING_BOX_KVP : {}\n", wmsBoundinBox.toWMSKeyValuePair());
    }

    @Test
    public void b_wmsGetMapKeyValuePairTest() throws Exception {
        GPWMSGetMapBaseRequest wmsGetMapBaseRequest = new WMSGetMapBaseRequest(wmsBoundinBox, Arrays.asList("topp:states"),
                "EPSG:4326", "550", "250");
        assertTrue(wmsGetMapBaseRequest.toWMSKeyValuePair().equalsIgnoreCase("LAYERS=topp:states&SRS=EPSG:4326&BBOX=-130.0,24.0,-66.0,50.0&WIDTH=550&HEIGHT=250"));
        logger.info("#########################GET_MAP_KVP : {}\n", wmsGetMapBaseRequest.toWMSKeyValuePair());
    }
}