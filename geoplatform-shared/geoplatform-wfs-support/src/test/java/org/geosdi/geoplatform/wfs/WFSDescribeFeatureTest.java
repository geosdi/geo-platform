package org.geosdi.geoplatform.wfs;

import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.WFSConnectorBuilder;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.WFSDescribeFeatureTypeRequest;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.support.wfs.feature.reader.GPFeatureSchemaReader;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.net.URL;
import java.util.Arrays;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSDescribeFeatureTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSDescribeFeatureTest.class);
    //
    private static final String wfsURL = "http://150.145.141.92/geoserver/wfs";

    static {
        try {
            serverConnector = WFSConnectorBuilder.newConnector().withServerUrl(
                    new URL(wfsURL)).withPooledConnectorConfig(
                    new GPServerConnector.BasePooledConnectorConfig(150, 80)).build();
        } catch (Exception ex) {
            logger.error("#######################EXCEPTION : {}", ex.getMessage());
        }
    }

    //
    private static final QName statesName = new QName("topp:states");
    private static final QName sfRoads = new QName("sf:roads");
    //
    private static GPWFSConnectorStore serverConnector;
    private static final GPJAXBContextBuilder gpJAXBContextBuilder = GPJAXBContextBuilder.newInstance();

    @Test
    public void describeToppStatesTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();

        String localPart = statesName.getLocalPart();
        request.setTypeName(Arrays.asList(statesName));
        Schema s = request.getResponse();

        String name = localPart.substring(localPart.indexOf(":") + 1);
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(new GPFeatureSchemaReader().getFeature(s, name), writer);

        logger.info("######################LAYER_SCHEMA_TOPP_STATES_XML : {}\n", writer);
    }

    @Test
    public void describeSFRoadTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();

        String localPart = sfRoads.getLocalPart();
        request.setTypeName(Arrays.asList(sfRoads));
        Schema s = request.getResponse();

        String name = localPart.substring(localPart.indexOf(":") + 1);
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(new GPFeatureSchemaReader().getFeature(s, name), writer);

        logger.info("######################LAYER_SCHEMA_SF_ROAD_XML : {}\n", writer);
    }
}
