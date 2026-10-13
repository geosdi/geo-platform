/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.namespace.QName;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Arrays;

import static java.net.URLDecoder.decode;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.geosdi.geoplatform.connector.jaxb.repository.JAXBContextConnectorRepository.getProvider;
import static org.geosdi.geoplatform.connector.jaxb.repository.WFSConnectorJAXBContext.WFS_CONTEXT_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSDescribeFeatureTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSDescribeFeatureTest.class);
    //
    private static final QName statesName = new QName("topp:states");
    private static final QName siteTRCom = new QName("cite:tr_com");
    private static final QName sfRoads = new QName("sf:roads");
    //
    protected static final GPJAXBContextBuilder gpJAXBContextBuilder = GPJAXBContextBuilder.newInstance();
    private static final FeatureSchemaReader schemaReader = new GPFeatureSchemaReader();
    //
    private GPWFSConnectorStore serverConnector;

    @Before
    public void setUp() throws Exception {
        this.serverConnector = mock(GPWFSConnectorStore.class);
    }

    @Test
    public void a_describeToppStatesTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> mockRequest = this.mockDescribeFeatureTypeRequest("toppStates.xsd");
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        String localPart = statesName.getLocalPart();
        request.withTypeName(Arrays.asList(statesName));
        logger.info("\n{}\n", request.showRequestAsString());
        Schema s = request.getResponse();
        assertNotNull("the DescribeFeatureType schema must not be null", s);
        String name = localPart.substring(localPart.indexOf(":") + 1);
        LayerSchemaDTO layerSchema = schemaReader.getFeature(s, name);
        assertLayerSchema(layerSchema, "topp:states", "MultiPolygon", 22);
        QName qName = new QName("org.geosdi.geoplatform.connector.wfs.response", "LayerSchemaDTO");
        JAXBElement<LayerSchemaDTO> root = new JAXBElement<>(qName, LayerSchemaDTO.class, layerSchema);
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(root, writer);
        logger.info("######################LAYER_SCHEMA_TOPP_STATES_XML : \n{}\n", writer);
        this.verifyDescribeFeatureTypeRequest(mockRequest, statesName);
    }

    @Test
    public void b_describeSFRoadTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> mockRequest = this.mockDescribeFeatureTypeRequest("sfRoads.xsd");
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        String localPart = sfRoads.getLocalPart();
        request.withTypeName(Arrays.asList(sfRoads));
        Schema s = request.getResponse();
        assertNotNull("the DescribeFeatureType schema must not be null", s);
        String name = localPart.substring(localPart.indexOf(":") + 1);
        LayerSchemaDTO layerSchema = schemaReader.getFeature(s, name);
        assertLayerSchema(layerSchema, "sf:roads", "MultiLineString", 2);
        QName qName = new QName("org.geosdi.geoplatform.connector.wfs.response", "LayerSchemaDTO");
        JAXBElement<LayerSchemaDTO> root = new JAXBElement<>(qName, LayerSchemaDTO.class, layerSchema);
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(root, writer);
        logger.info("######################LAYER_SCHEMA_SF_ROAD_XML : \n{}\n", writer);
        this.verifyDescribeFeatureTypeRequest(mockRequest, sfRoads);
    }

    @Test
    public void c_describeSiteTrTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> mockRequest = this.mockDescribeFeatureTypeRequest("citeTrCom.xsd");
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        String localPart = siteTRCom.getLocalPart();
        request.withTypeName(Arrays.asList(siteTRCom));
        logger.debug("#########################SCHEMA_AS_STRING : \n{}\n", request.formatResponseAsString(2));
        Schema s = request.getResponse();
        assertNotNull("the DescribeFeatureType schema must not be null", s);
        String name = localPart.substring(localPart.indexOf(":") + 1);
        LayerSchemaDTO layerSchema = schemaReader.getFeature(s, name);
        assertLayerSchema(layerSchema, "cite:tr_com", "LineString", 9);
        QName qName = new QName("org.geosdi.geoplatform.connector.wfs.response", "LayerSchemaDTO");
        JAXBElement<LayerSchemaDTO> root = new JAXBElement<>(qName, LayerSchemaDTO.class, layerSchema);
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(root, writer);
        logger.info("######################LAYER_SCHEMA_SITE_COM_XML : \n{}\n", writer);
        this.verifyDescribeFeatureTypeRequest(mockRequest, siteTRCom);
    }

    @Test
    public void d_describeLayerPercorsiNavetteTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> mockRequest = this.mockDescribeFeatureTypeRequest("cfPercorsiNavette.xsd");
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        QName percorsiNavette = new QName("PianoCampiFlegrei:CF_PercorsiNavette");
        String localPart = percorsiNavette.getLocalPart();
        request.withTypeName(Arrays.asList(percorsiNavette));
        logger.debug("#########################SCHEMA_AS_STRING : \n{}\n", request.formatResponseAsString(2));
        Schema s = request.getResponse();
        assertNotNull("the DescribeFeatureType schema must not be null", s);
        String name = localPart.substring(localPart.indexOf(":") + 1);
        LayerSchemaDTO layerSchema = schemaReader.getFeature(s, name);
        assertLayerSchema(layerSchema, "PianoCampiFlegrei:CF_PercorsiNavette", "MultiLineString", 4);
        JAXBElement<LayerSchemaDTO> root = new JAXBElement<>(percorsiNavette, LayerSchemaDTO.class, layerSchema);
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(root, writer);
        logger.info("######################LAYER_SCHEMA_PERCORSI_NAVETTE_XML : \n{}\n", writer);
        this.verifyDescribeFeatureTypeRequest(mockRequest, percorsiNavette);
    }

    @Test
    public void e_describeLayerDtsupStromboliTest() throws Exception {
        String serverURL = "https://servizi.protezionecivile.it/webgis/sitdpc/gpServerProxy?targetURL=https://insar.irea.cnr.it/geoserver/wms%26v=dpc%26p=4WzL06EA";
        serverURL = decode(serverURL.replaceAll("wms", "wfs"), UTF_8.name());
        logger.info("########################SERVER_URL : {}\n", serverURL);
        assertEquals("https://servizi.protezionecivile.it/webgis/sitdpc/gpServerProxy?targetURL=https://insar.irea.cnr.it/geoserver/wfs&v=dpc&p=4WzL06EA", serverURL);
        WFSDescribeFeatureTypeRequest<Schema> mockRequest = this.mockDescribeFeatureTypeRequest("dtsupStromboli.xsd");
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        QName dtsupStromboli = new QName("geonode:dtsup_stromboli");
        String localPart = dtsupStromboli.getLocalPart();
        request.withTypeName(Arrays.asList(dtsupStromboli));
        logger.info("{}\n", request.showRequestAsString());
        logger.info("#########################SCHEMA_AS_STRING : \n{}\n", request.getResponseAsString());
        Schema s = request.getResponse();
        assertNotNull("the DescribeFeatureType schema must not be null", s);
        String name = localPart.substring(localPart.indexOf(":") + 1);
        LayerSchemaDTO layerSchema = schemaReader.getFeature(s, name);
        assertLayerSchema(layerSchema, "geonode:dtsup_stromboli", "Point", 4);
        JAXBElement<LayerSchemaDTO> root = new JAXBElement<>(dtsupStromboli, LayerSchemaDTO.class, layerSchema);
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(root, writer);
        logger.info("######################LAYER_SCHEMA_DTSUP_STROMBOLI_XML : \n{}\n", writer);
        this.verifyDescribeFeatureTypeRequest(mockRequest, dtsupStromboli);
    }

    @Test
    public void f_describeAdminSHPComuniTest() throws Exception {
        WFSDescribeFeatureTypeRequest<Schema> mockRequest = this.mockDescribeFeatureTypeRequest("adminShpComuni.xsd");
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        QName value = new QName("admin:admin_shp_comuni");
        String localPart = value.getLocalPart();
        request.withTypeName(Arrays.asList(value));
        Schema s = request.getResponse();
        assertNotNull("the DescribeFeatureType schema must not be null", s);
        String name = localPart.substring(localPart.indexOf(":") + 1);
        LayerSchemaDTO layerSchema = schemaReader.getFeature(s, name);
        assertLayerSchema(layerSchema, "admin:admin_shp_comuni", "MultiPolygon", 7);
        QName qName = new QName("org.geosdi.geoplatform.connector.wfs.response", "LayerSchemaDTO");
        JAXBElement<LayerSchemaDTO> root = new JAXBElement<>(qName, LayerSchemaDTO.class, layerSchema);
        StringWriter writer = new StringWriter();
        gpJAXBContextBuilder.marshal(root, writer);
        logger.info("######################LAYER_SCHEMA_ADMIN_SHP_COMUNI_XML : \n{}\n", writer);
        this.verifyDescribeFeatureTypeRequest(mockRequest, value);
    }

    /**
     * @param xsdFileName the DescribeFeatureType response stored in src/test/resources/describe
     * @return {@link WFSDescribeFeatureTypeRequest<Schema>}
     * @throws Exception
     */
    private WFSDescribeFeatureTypeRequest<Schema> mockDescribeFeatureTypeRequest(String xsdFileName) throws Exception {
        String resource = "/describe/".concat(xsdFileName);
        String schemaAsString;
        try (InputStream inputStream = getClass().getResourceAsStream(resource)) {
            assertNotNull("The resource " + resource + " must not be null", inputStream);
            schemaAsString = new String(inputStream.readAllBytes(), UTF_8);
        }
        Schema schema;
        try (InputStream inputStream = getClass().getResourceAsStream(resource)) {
            schema = (Schema) JAXBIntrospector.getValue(getProvider(WFS_CONTEXT_KEY).acquireUnmarshaller().unmarshal(inputStream));
        }
        WFSDescribeFeatureTypeRequest<Schema> mockRequest = mock(WFSDescribeFeatureTypeRequest.class, RETURNS_SELF);
        doReturn(schema).when(mockRequest).getResponse();
        doReturn(schemaAsString).when(mockRequest).getResponseAsString();
        doReturn(schemaAsString).when(mockRequest).formatResponseAsString(anyInt());
        doReturn("DescribeFeatureType request for ".concat(xsdFileName)).when(mockRequest).showRequestAsString();
        doReturn(mockRequest).when(this.serverConnector).createDescribeFeatureTypeRequest();
        return mockRequest;
    }

    /**
     * @param mockRequest
     * @param typeName
     * @throws Exception
     */
    private void verifyDescribeFeatureTypeRequest(WFSDescribeFeatureTypeRequest<Schema> mockRequest, QName typeName) throws Exception {
        verify(this.serverConnector).createDescribeFeatureTypeRequest();
        verify(mockRequest).withTypeName(Arrays.asList(typeName));
        verify(mockRequest).getResponse();
    }

    /**
     * @param layerSchema
     * @param typeName
     * @param geometryType
     * @param attributesSize
     */
    private static void assertLayerSchema(LayerSchemaDTO layerSchema, String typeName, String geometryType, int attributesSize) {
        assertNotNull("The LayerSchemaDTO must not be null", layerSchema);
        assertEquals(typeName, layerSchema.getTypeName());
        assertNotNull("The Geometry Attribute must not be null", layerSchema.getGeometry());
        assertEquals(geometryType, layerSchema.getGeometry().getType());
        assertEquals(attributesSize, layerSchema.getAttributes().size());
    }
}
