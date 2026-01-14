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

import lombok.Getter;
import lombok.Setter;
import org.geojson.FeatureCollection;
import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSDescribeFeatureTypeRequest;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.server.security.BasicPreemptiveSecurityConnector;
import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import org.geosdi.geoplatform.support.wfs.feature.reader.FeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.GPFeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSGetFeatureStaxReader;
import org.geosdi.geoplatform.xml.wfs.v110.GetFeatureType;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.api.referencing.operation.MathTransform;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.io.File.separator;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.geosdi.geoplatform.connector.WFSConnectorBuilder.newConnector;
import static org.geosdi.geoplatform.connector.server.request.WFSGetFeatureOutputFormat.GEOJSON;
import static org.geosdi.geoplatform.jaxb.GPJAXBContextBuilder.newInstance;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.geosdi.geoplatform.wfs.WFSDescribeFeatureTest.gpJAXBContextBuilder;
import static org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType.RESULTS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class WFSGetFeaturesRequestTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeaturesRequestTest.class);
    //
    private static final QName information = new QName("topp:polygon_loc_hebron_nablus_pcd");
    private static final String informationName = information.getLocalPart()
            .substring(information.getLocalPart().indexOf(":") + 1, information.getLocalPart().length());
    private static final QName states = new QName("topp:states");
    private static final String statesName = states.getLocalPart()
            .substring(states.getLocalPart().indexOf(":") + 1, states.getLocalPart().length());
    private static final QName tigerRoads = new QName("tiger:tiger_roads");
    private static final String tigerRoadsName = tigerRoads.getLocalPart()
            .substring(tigerRoads.getLocalPart().indexOf(":") + 1, tigerRoads.getLocalPart().length());
    //
    private final static FeatureSchemaReader featureReaderXSD = new GPFeatureSchemaReader();
    private static final JacksonSupport JACKSON_SUPPORT = new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE, NON_NULL);

    @Ignore(value = "Geoserver is Down")
    @Test
    public void a_getAllFeaturesTest() throws Exception {
        String wfsURL = "http://geoserver.wfppal.org/geoserver/wfs";
        GPWFSConnectorStore serverConnector = newConnector().withServerUrl(new URL(wfsURL)).build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(information));
        Schema response = request.getResponse();

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, informationName);
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);
        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);

        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(RESULTS.value());
        getFeatureRequest.setMaxFeatures(valueOf(50));

        logger.debug("\n\t@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : {}", getFeatureRequest.getResponseAsString());
        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);
        if(!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION_DTO : {}", featureCollection);
    }

    //@Ignore(value = "FIX PROBLEM TO RETRIEVE ATTRIBUTES WITHOUT GEOMETRY")
    @Test
    public void b_statesTest() throws Exception {
        String wfsURL = "http://150.145.141.92/geoserver/wfs";
        GPWFSConnectorStore serverConnector = newConnector()
                .withServerUrl(new URL(wfsURL))
                .build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(states));
        Schema response = request.getResponse();
        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, statesName);
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);
        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);
        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setPropertyNames(asList(new String[]{"STATE_NAME", "PERSONS"}));
        getFeatureRequest.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(RESULTS.value());

        getFeatureRequest.setMaxFeatures(valueOf(50));
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : {}", getFeatureRequest.getResponseAsString());
        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);
        if(!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION_DTO : {}", featureCollection.getNumberOfFeatures());
    }

    @Test
    public void c_tigerRoadsTest() throws Exception {
        String wfsURL = "http://150.145.141.92/geoserver/wfs";
        GPWFSConnectorStore serverConnector = newConnector()
                .withServerUrl(new URL(wfsURL))
                .build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(tigerRoads));
        Schema response = request.getResponse();

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, tigerRoadsName);
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);
        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);
        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(RESULTS.value());
        getFeatureRequest.setMaxFeatures(valueOf(50));
        logger.debug("@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : \n{}\n", getFeatureRequest.showRequestAsString());
        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if(!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION_DTO : {}", featureCollection);
    }

    @Ignore(value = "Geoserver is Down")
    @Test
    public void d_siteTrTest() throws Exception {
        String wfsURL = "http://150.145.141.241/geoserver/wfs";
        QName siteTRCom = new QName("cite:tr_com");
        GPWFSConnectorStore serverConnector = newConnector()
                .withServerUrl(new URL(wfsURL))
                .build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(siteTRCom));
        Schema response = request.getResponse();

        String localPart = siteTRCom.getLocalPart();
        String name = localPart.substring(localPart.indexOf(":") + 1);

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, name);
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);

        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(RESULTS.value());

        getFeatureRequest.setMaxFeatures(valueOf(50));
        logger.debug("@@@@@@@@@@@@@@@@@@REQUEST_AS_STRING : \n{}\n", getFeatureRequest.showRequestAsString());
        logger.debug("######################RESPONSE_AS_STRING : \n{}\n", getFeatureRequest.formatResponseAsString(2));

        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);
        if(!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        logger.info("###################################FEATURE_COLLECTION : {}\n", featureCollection);

        getFeatureRequest.setGeometryName(layerSchema.getGeometry().getName());
        getFeatureRequest.setBBox(new BBox(10.329274141729897, 44.64877730606194, 10.35673996203874, 44.66831396911103));

        logger.debug("@@@@@@@@@@@@@@@@@@REQUEST_FILTERED_BY_BBOX_AS_STRING : \n{}\n", getFeatureRequest.showRequestAsString());
        logger.debug("######################RESPONSE_FILTERED_BY_BBOX_AS_STRING : \n{}\n", getFeatureRequest.formatResponseAsString(2));

        InputStream si = getFeatureRequest.getResponseAsStream();
        FeatureCollectionDTO featureCollectionByBBOX = featureReaderStAX.read(si);
        if(!featureCollectionByBBOX.isFeaturesLoaded()) {
            featureCollectionByBBOX.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        logger.info("###################################FEATURE_COLLECTION_BY_BBOX : {}\n", featureCollectionByBBOX);
    }

    @Ignore(value = "Test to Prepare XML Files")
    @Test
    public void e_generateXMLFilesTest() throws Exception {
        String wfsURL = "http://150.145.141.92/geoserver/wfs";
        QName layerQName = new QName("sf:restricted");
        GPWFSConnectorStore serverConnector = newConnector().withServerUrl(new URL(wfsURL)).build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(layerQName));
        Schema response = request.getResponse();

        String localPart = layerQName.getLocalPart();
        String name = localPart.substring(localPart.indexOf(":") + 1);

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, name);
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);

        GPJAXBContextBuilder.newInstance().marshal(layerSchema, new File("./target/LayerSchemaSFRestricted.xml"));

        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(RESULTS.value());
        String responseAsString = getFeatureRequest.formatResponseAsString(2);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("./target/GetFeatureSFRestricted.xml"))) {
            writer.write(responseAsString);
        }
    }

    @Test
    public void f_getFeatureMappingTest() throws Exception {
        GetFeatureType getFeatureType = GPJAXBContextBuilder.newInstance().unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<wfs:GetFeature xmlns:wfs=\"http://www.opengis.net/wfs\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" service=\"WFS\" version=\"1.1.0\" maxFeatures=\"10\" xsi:schemaLocation=\"http://www.opengis.net/wfs http://schemas.opengis.net/wfs/1.1.0/wfs.xsd\">\n" +
                        "   <wfs:Query xmlns:cite=\"http://www.opengeospatial.net/cite\" typeName=\"cite:tr_com\" srsName=\"EPSG:3857\">\n" +
                        "      <ogc:Filter xmlns:ogc=\"http://www.opengis.net/ogc\">\n" +
                        "         <ogc:BBOX>\n" +
                        "            <ogc:PropertyName>the_geom</ogc:PropertyName>\n" +
                        "            <gml:Envelope xmlns:gml=\"http://www.opengis.net/gml\" srsName=\"EPSG:3857\">\n" +
                        "               <gml:lowerCorner>1149849.5377215 5566397.1005184</gml:lowerCorner>\n" +
                        "               <gml:upperCorner>1152907.0188525 5569454.5816494</gml:upperCorner>\n" +
                        "            </gml:Envelope>\n" +
                        "         </ogc:BBOX>\n" +
                        "      </ogc:Filter>\n" +
                        "   </wfs:Query>\n" +
                        "</wfs:GetFeature>"),
                GetFeatureType.class);
        logger.info("################################GET_FEATURE_TYPE : {}\n", getFeatureType);
    }

    @Test
    public void g_convertJTSPointFrom3857To4326Test() throws Exception {
        CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:3857");
        CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:4326");
        MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS, false);
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Point lowerCorner = geometryFactory.createPoint(new Coordinate(1149849.5377215, 5566397.1005184));
        Point targetLowerCorner = (Point) JTS.transform(lowerCorner, transform);
        logger.info("############################LOWER_CORNER : {}\n", targetLowerCorner);
        Point upperCorner = geometryFactory.createPoint(new Coordinate(1152907.0188525, 5569454.5816494));
        Point targetUpperCorner = (Point) JTS.transform(upperCorner, transform);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@UPPER_CORNER : {}\n", targetUpperCorner);
    }

    @Test
    public void h_toppTasmaniaRoadsTest() throws Exception {
        String wfsURL = "http://150.145.141.92/geoserver/wfs";
        QName TASMANIA_ROADS = new QName("http://www.openplans.org/topp",
                "tasmania_roads", "topp");
        GPWFSConnectorStore serverConnector = newConnector()
                .withServerUrl(new URL(wfsURL))
                .build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(TASMANIA_ROADS));
        Schema response = request.getResponse();

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, TASMANIA_ROADS.getLocalPart());
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);
        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);
        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(RESULTS.value());
        getFeatureRequest.setMaxFeatures(valueOf(50));
        logger.debug("@@@@@@@@@@@@@@@@@@REQUEST_AS_STRING : \n{}\n", getFeatureRequest.showRequestAsString());
//        logger.debug("@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : \n{}\n", getFeatureRequest.formatResponseAsString(2));
        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if(!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION_DTO : {}", featureCollection);
    }

    @Test
    public void i_statesFeatureAsJsonTest() throws Exception {
        String wfsURL = "http://150.145.141.92/geoserver/wfs";
        GPWFSConnectorStore serverConnector = newConnector()
                .withServerUrl(new URL(wfsURL))
                .build();
        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(states);
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setOutputFormat(GEOJSON);
        getFeatureRequest.setResultType(RESULTS.value());

        getFeatureRequest.setMaxFeatures(valueOf(50));
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@RESPONSE_AS_JSON_STRING : {}", getFeatureRequest.getResponseAsString());
        InputStream is = getFeatureRequest.getResponseAsStream();
        logger.trace("###########################GEOJSON_FEATURE_COLLECTION : {}\n", JACKSON_SUPPORT
                .getDefaultMapper().readValue(is, FeatureCollection.class));
    }

    @Ignore
    @Test
    public void l_percorsiNavetteTest() throws Exception {
        String wfsURL = "http://mappe-dpc.protezionecivile.it/gssitdpc/wfs";
        GPWFSConnectorStore serverConnector = newConnector()
                .withServerUrl(new URL(wfsURL))
                .build();
        QName percorsiNavette = new QName("PianoCampiFlegrei:CF_PercorsiNavette");
        String localPart = percorsiNavette.getLocalPart();
        String name = localPart.substring(localPart.indexOf(":") + 1);
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(percorsiNavette));
        Schema response = request.getResponse();
        logger.info("#################SCHEMA : {}\n", response);

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, name);
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);
        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);
        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(RESULTS.value());
        getFeatureRequest.setMaxFeatures(valueOf(50));
        logger.debug("@@@@@@@@@@@@@@@@@@REQUEST_AS_STRING : \n{}\n", getFeatureRequest.showRequestAsString());
        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if(!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        JAXBElement<FeatureCollectionDTO> root = new JAXBElement<>(percorsiNavette, FeatureCollectionDTO.class, featureCollection);
        gpJAXBContextBuilder.marshal(root, new File(of(new File(".").getCanonicalPath(), "target", "PercorsiNavette")
                .collect(joining(separator, "", ".xml"))));
        getFeatureRequest.setOutputFormat(GEOJSON);
        InputStream isJson = getFeatureRequest.getResponseAsStream();
        FeatureCollection featureCollectionJson = JACKSON_SUPPORT
                .getDefaultMapper().readValue(isJson, FeatureCollection.class);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "PercorsiNavette")
                .collect(joining(separator, "", ".json"))), featureCollectionJson);
    }

    @Ignore(value = "Serivce is unavailable")
    @Test
    public void m_ospedaliTest() throws Exception {
        String wfsURL = "https://servizi.protezionecivile.it/geoserver/wfs";
        GPWFSConnectorStore serverConnector = newConnector()
                .withClientSecurity(new BasicPreemptiveSecurityConnector("MAIDNT78M23G942L", "mdonato"))
                .withServerUrl(new URL(wfsURL))
                .build();
        QName ospedali = new QName("PNSRS:CAL_4_6_ospedali");
        String localPart = ospedali.getLocalPart();
        String name = localPart.substring(localPart.indexOf(":") + 1);
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(ospedali));
        Schema response = request.getResponse();
        logger.info("#################SCHEMA : {}\n", response);

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, name);
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);
        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);
        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(RESULTS.value());
        getFeatureRequest.setMaxFeatures(valueOf(2));
        logger.debug("@@@@@@@@@@@@@@@@@@REQUEST_AS_STRING : \n{}\n", getFeatureRequest.showRequestAsString());
        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if(!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        JAXBElement<FeatureCollectionDTO> root = new JAXBElement<>(ospedali, FeatureCollectionDTO.class, featureCollection);
        gpJAXBContextBuilder.marshal(root, new File(of(new File(".").getCanonicalPath(), "target", "Ospedali")
                .collect(joining(separator, "", ".xml"))));
        getFeatureRequest.setOutputFormat(GEOJSON);
        InputStream isJson = getFeatureRequest.getResponseAsStream();
        FeatureCollection featureCollectionJson = JACKSON_SUPPORT.getDefaultMapper().readValue(isJson, FeatureCollection.class);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "Ospedali")
                .collect(joining(separator, "", ".json"))), featureCollectionJson);
    }

    @Test
    public void n_unmarshallOspedaliTest() throws Exception {
        String ospedaliBasePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "reader", "Ospedali.xml")
                .collect(joining(separator));
        FeatureCollectionDTOWrapper featureCollectionDTOWrapper = gpJAXBContextBuilder.unmarshal(new StreamSource(ospedaliBasePath), FeatureCollectionDTOWrapper.class);
        logger.info("#####################OSPEDALI : {}\n", featureCollectionDTOWrapper.getFeatureCollectionDTO());
    }

    @Test
    public void o_unmarshallOspedaliTest() throws Exception {
        String ospedaliBasePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "reader", "OspedaliWithEntryNull.xml")
                .collect(joining(separator));
        FeatureCollectionDTOWrapper featureCollectionDTOWrapper = gpJAXBContextBuilder.unmarshal(new StreamSource(ospedaliBasePath), FeatureCollectionDTOWrapper.class);
        for (FeatureDTO featureDTO : featureCollectionDTOWrapper.getFeatureCollectionDTO().getFeatures()) {
            logger.info("@@@@@@@@@@@@@@@@FeatureID : {} - attributes : {}\n", featureDTO.getFID(), featureDTO.getAttributes());
        }
    }

    @Test
    public void p_itGrandiDigheTest() throws Exception {
        String wfsURL = "https://servizi.protezionecivile.it/geoserver/wfs";
        GPWFSConnectorStore serverConnector = newConnector()
                .withClientSecurity(new BasicPreemptiveSecurityConnector("MAIDNT78M23G942L", "mdonato"))
                .withServerUrl(new URL(wfsURL))
                .build();
        QName grandiDighe = new QName("IDROGEOLOGICO:IT_grandi_dighe_MIT2019");
        String localPart = grandiDighe.getLocalPart();
        String name = localPart.substring(localPart.indexOf(":") + 1);
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(grandiDighe));
        Schema response = request.getResponse();
        logger.info("#################SCHEMA : {}\n", response);

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, name);
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);
        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);
        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setBBox(new BBox(14.131237640976908, 36.56356461583572, 15.821758881211283, 37.143760728459014));
        getFeatureRequest.setQueryDTO(newInstance().unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<QueryDTO>\n"
                + "    <matchOperator>ANY</matchOperator>\n"
                + "    <queryRestrictionList>\n"
                + "        <queryRestriction>\n"
                + "            <attribute>\n"
                + "                <type>string</type>\n"
                + "                <name>diga</name>\n"
                + "                <value></value>\n"
                + "                <maxOccurs>1</maxOccurs>\n"
                + "                <minOccurs>0</minOccurs>\n"
                + "                <nillable>true</nillable>\n"
                + "            </attribute>\n"
                + "            <operator>EQUAL</operator>\n"
                + "            <restriction>SANTA ROSALIA</restriction>\n"
                + "        </queryRestriction>\n"
                + "        <queryRestriction>\n"
                + "            <attribute>\n"
                + "                <type>string</type>\n"
                + "                <name>diga</name>\n"
                + "                <value></value>\n"
                + "                <maxOccurs>1</maxOccurs>\n"
                + "                <minOccurs>0</minOccurs>\n"
                + "                <nillable>true</nillable>\n"
                + "            </attribute>\n"
                + "            <operator>EQUAL</operator>\n"
                + "            <restriction>MONTE CAVALLARO</restriction>\n"
                + "        </queryRestriction>\n"
                + "    </queryRestrictionList>\n"
                + "</QueryDTO>"), QueryDTO.class));
        getFeatureRequest.setGeometryName(layerSchema.getGeometry().getName());
        getFeatureRequest.setResultType(RESULTS.value());
        getFeatureRequest.setMaxFeatures(valueOf(50));
        logger.debug("@@@@@@@@@@@@@@@@@@REQUEST_AS_STRING : \n{}\n", getFeatureRequest.showRequestAsString());
        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if(!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        JAXBElement<FeatureCollectionDTO> root = new JAXBElement<>(grandiDighe, FeatureCollectionDTO.class, featureCollection);
        gpJAXBContextBuilder.marshal(root, new File(of(new File(".").getCanonicalPath(), "target", "GrandiDighe")
                .collect(joining(separator, "", ".xml"))));
        getFeatureRequest.setOutputFormat(GEOJSON);
        InputStream isJson = getFeatureRequest.getResponseAsStream();
        FeatureCollection featureCollectionJson = JACKSON_SUPPORT.getDefaultMapper().readValue(isJson, FeatureCollection.class);
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "GrandiDighe")
                .collect(joining(separator, "", ".json"))), featureCollectionJson);
    }

    @Test
    public void q_unmarshallGrandiDigheTest() throws Exception {
        String ospedaliBasePath = of(new File(".").getCanonicalPath(), "src", "test", "resources", "reader", "GrandiDighe.xml")
                .collect(joining(separator));
        FeatureCollectionDTOWrapper featureCollectionDTOWrapper = gpJAXBContextBuilder.unmarshal(new StreamSource(ospedaliBasePath), FeatureCollectionDTOWrapper.class);
        logger.info("#####################GRANDI_DIGHE : {}\n", featureCollectionDTOWrapper.getFeatureCollectionDTO());
    }

    @Test
    public void r_shpAdminComuniTest() throws Exception {
        String wfsURL = "https://prosit.geosdi.org/geoserver/wfs";
        GPWFSConnectorStore serverConnector = newConnector().withServerUrl(new URL(wfsURL)).build();
        QName shpComuni = new QName("admin:admin_shp_comuni");
        String localPart = shpComuni.getLocalPart();
        String name = localPart.substring(localPart.indexOf(":") + 1);
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(asList(shpComuni));
        Schema response = request.getResponse();
        logger.info("#################SCHEMA : {}\n", response);

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, name);
        if(layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);
        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);
        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setBBox(new BBox(14.403076171875002, 38.83542884007305, 19.368896484375004, 40.94671366508002));
        getFeatureRequest.setGeometryName(layerSchema.getGeometry().getName());
        getFeatureRequest.setResultType(RESULTS.value());
        getFeatureRequest.setCqlFilter("(COMUNE like 'AVIGLIANO' OR PRO_COM = 77014 OR COMUNE like 'T%')");
        getFeatureRequest.setMaxFeatures(valueOf(50));
        logger.debug("@@@@@@@@@@@@@@@@@@REQUEST_AS_STRING : \n{}\n", getFeatureRequest.showRequestAsString());
        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if(!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        JAXBElement<FeatureCollectionDTO> root = new JAXBElement<>(shpComuni, FeatureCollectionDTO.class, featureCollection);
        gpJAXBContextBuilder.marshal(root, new File(of(new File(".").getCanonicalPath(), "target", "AdminShpComuni")
                .collect(joining(separator, "", ".xml"))));
        getFeatureRequest.setOutputFormat(GEOJSON);
        InputStream isJson = getFeatureRequest.getResponseAsStream();
        FeatureCollection featureCollectionJson = JACKSON_SUPPORT.getDefaultMapper().readValue(isJson, FeatureCollection.class);
        logger.info("@@@@@@@@@@@@@@@@@@@@@NUMBER_OF_FEATURES : {}\n", featureCollectionJson.getFeatures().size());
        JACKSON_SUPPORT.getDefaultMapper().writeValue(new File(of(new File(".").getCanonicalPath(), "target", "AdminShpComuni")
                .collect(joining(separator, "", ".json"))), featureCollectionJson);
    }

    @Getter
    @Setter
    @XmlRootElement(name = "FeatureCollectionDTOWrapper")
    @XmlAccessorType(XmlAccessType.FIELD)
    static class FeatureCollectionDTOWrapper implements Serializable {

        private static final long serialVersionUID = 4057175498085166638L;
        //
        @XmlElement(name = "FeatureCollectionDTO")
        private FeatureCollectionDTO featureCollectionDTO;
    }
}