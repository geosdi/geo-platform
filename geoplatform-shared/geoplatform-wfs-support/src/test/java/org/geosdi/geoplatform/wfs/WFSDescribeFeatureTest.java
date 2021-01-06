/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.wfs;

import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSDescribeFeatureTypeRequest;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.support.wfs.feature.reader.FeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.GPFeatureSchemaReader;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.net.URL;
import java.util.Arrays;

import static org.geosdi.geoplatform.connector.WFSConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;

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
            serverConnector = newConnector().withServerUrl(new URL(wfsURL))
                    .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                            .withMaxTotalConnections(150)
                            .withDefaultMaxPerRoute(80)
                            .withMaxRedirect(20)
                            .build()).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("#######################EXCEPTION : {}", ex.getMessage());
        }
    }

    //
    private static final QName statesName = new QName("topp:states");
    private static final QName siteTRCom = new QName("cite:tr_com");
    private static final QName sfRoads = new QName("sf:roads");
    //
    private static GPWFSConnectorStore serverConnector;
    protected static final GPJAXBContextBuilder gpJAXBContextBuilder = GPJAXBContextBuilder.newInstance();
    private static final FeatureSchemaReader schemaReader = new GPFeatureSchemaReader();

    @Test
    public void describeToppStatesTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        String localPart = statesName.getLocalPart();
        request.setTypeName(Arrays.asList(statesName));
        logger.info("\n{}\n", request.showRequestAsString());
        Schema s = request.getResponse();
        String name = localPart.substring(localPart.indexOf(":") + 1);
        QName qName = new QName("org.geosdi.geoplatform.connector.wfs.response", "LayerSchemaDTO");
        JAXBElement<LayerSchemaDTO> root = new JAXBElement<>(qName, LayerSchemaDTO.class, schemaReader.getFeature(s, name));
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(root, writer);
        logger.info("######################LAYER_SCHEMA_TOPP_STATES_XML : \n{}\n", writer);
    }

    @Test
    public void describeSFRoadTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        String localPart = sfRoads.getLocalPart();
        request.setTypeName(Arrays.asList(sfRoads));
        Schema s = request.getResponse();
        String name = localPart.substring(localPart.indexOf(":") + 1);
        QName qName = new QName("org.geosdi.geoplatform.connector.wfs.response", "LayerSchemaDTO");
        JAXBElement<LayerSchemaDTO> root = new JAXBElement<>(qName, LayerSchemaDTO.class, schemaReader.getFeature(s, name));
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(root, writer);
        logger.info("######################LAYER_SCHEMA_SF_ROAD_XML : \n{}\n", writer);
    }

    @Ignore(value = "Geoserver is Down")
    @Test
    public void describeSiteTrTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> request =
                newConnector()
                        .withServerUrl(new URL("http://150.145.141.241/geoserver/wfs"))
                        .build()
                        .createDescribeFeatureTypeRequest();

        String localPart = siteTRCom.getLocalPart();
        request.setTypeName(Arrays.asList(siteTRCom));
        logger.debug("#########################SCHEMA_AS_STRING : \n{}\n", request.formatResponseAsString(2));
        Schema s = request.getResponse();
        String name = localPart.substring(localPart.indexOf(":") + 1);
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(schemaReader.getFeature(s, name), writer);
        logger.info("######################LAYER_SCHEMA_SITE_COM_XML : \n{}\n", writer);
    }

    @Test
    public void describeLayerPercorsiNavetteTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> request =
                newConnector()
                        .withServerUrl(new URL("http://mappe-dpc.protezionecivile.it/gssitdpc/wfs"))
                        .build()
                        .createDescribeFeatureTypeRequest();
        QName percorsiNavette = new QName("PianoCampiFlegrei:CF_PercorsiNavette");
        String localPart = percorsiNavette.getLocalPart();
        request.setTypeName(Arrays.asList(percorsiNavette));
        logger.debug("#########################SCHEMA_AS_STRING : \n{}\n", request.formatResponseAsString(2));
        Schema s = request.getResponse();
        String name = localPart.substring(localPart.indexOf(":") + 1);
        JAXBElement<LayerSchemaDTO> root = new JAXBElement<>(percorsiNavette, LayerSchemaDTO.class, schemaReader.getFeature(s, name));
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(root, writer);
        logger.info("######################LAYER_SCHEMA_PERCORSI_NAVETTE_XML : \n{}\n", writer);
    }
}