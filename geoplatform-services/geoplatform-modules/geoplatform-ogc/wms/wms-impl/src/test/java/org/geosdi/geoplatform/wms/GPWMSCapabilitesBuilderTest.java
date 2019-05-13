package org.geosdi.geoplatform.wms;

import org.geosdi.geoplatform.response.RasterLayerDTO;
import org.geosdi.geoplatform.services.builder.GPWMSCapabilitesBuilder;
import org.geosdi.geoplatform.services.builder.IGPWMSCapabilitesBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSCapabilitesBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPWMSCapabilitesBuilderTest.class);
    //
    private static final IGPWMSCapabilitesBuilder wmsCapabilitiesBuilder = new GPWMSCapabilitesBuilder();
    
    @Test
    public void loadWMSCapabilitiesTest() throws Exception {
        List<RasterLayerDTO> rasterLayers = wmsCapabilitiesBuilder.loadWMSCapabilities("http://150.145.141.180/geoserver/wms", null, null);
        checkArgument(rasterLayers.size() > 0);
        for (RasterLayerDTO rasterLayerDTO : rasterLayers.get(0).getSubLayerList()) {
            logger.info("######################LAYER_NAME : {} - STYLE_LIST : {}\n", rasterLayerDTO, rasterLayerDTO.getStyleList());
        }
    }
}