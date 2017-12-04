package org.geosdi.geoplatform.connector.wps;

import org.geosdi.geoplatform.connector.server.request.WPSGetCapabilitiesRequest;
import org.geosdi.geoplatform.xml.wps.v100.WPSCapabilitiesType;
import org.junit.Test;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WPSGetCapabilitiesTest extends WPSTestConfigurator {

    @Test
    public void wpsGetCapabilitiesV100Test() throws Exception {
        WPSGetCapabilitiesRequest<WPSCapabilitiesType> request = wpsServerConnector.createGetCapabilitiesRequest();
        String responseAsString = request.formatResponseAsString(2);
        logger.info("WPS_100 GetCapabilities @@@@@@@@@@@@@@@@@@@@@@@ \n{}\n", responseAsString);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("target/WPSGetCapabilitiesV100.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void wpsHttpsGetCapabilitiesV100Test() throws Exception {
        System.setProperty("jsse.enableSNIExtension", "false");
        WPSGetCapabilitiesRequest<WPSCapabilitiesType> request = wpsHttpsServerConnector.createGetCapabilitiesRequest();
        String responseAsString = request.formatResponseAsString(2);
        logger.info("WPS_100 Https GetCapabilities @@@@@@@@@@@@@@@@@@@@@@@ \n{}\n", responseAsString);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("target/WPSHttpsGetCapabilitiesV100.xml"))) {
            writer.write(responseAsString);
        }
    }
}
