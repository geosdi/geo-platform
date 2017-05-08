/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.wfs;

import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.WFSConnectorBuilder;
import org.geosdi.geoplatform.connector.server.request.WFSDescribeFeatureTypeRequest;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.support.wfs.feature.reader.FeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.GPFeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSGetFeatureStaxReader;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetFeaturesRequestTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetFeaturesRequestTest.class);
    //
    private static final QName information = new QName("admin:admin_shp_06banisuhela_crisis_information_poly");
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


    @Test
    public void getAllFeaturesTest() throws Exception {
        String wfsURL = "http://geoserver.wfppal.org/geoserver/wfs";
        GPWFSConnectorStore serverConnector = WFSConnectorBuilder.newConnector().withServerUrl(new URL(wfsURL)).build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(Arrays.asList(information));
        Schema response = request.getResponse();

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, informationName);
        if (layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);

        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);

        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(ResultTypeType.RESULTS.value());

        getFeatureRequest.setMaxFeatures(BigInteger.valueOf(50));

        logger.debug("\n\t@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : {}", getFeatureRequest.getResponseAsString());

        InputStream is = getFeatureRequest.getResponseAsStream();

        final WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);

        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if (!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }

        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION_DTO : {}", featureCollection);
    }

    //@Ignore(value = "FIX PROBLEM TO RETRIEVE ATTRIBUTES WITHOUT GEOMETRY")
    @Test
    public void statesTest() throws Exception {
        String wfsURL = "http://150.145.141.92/geoserver/wfs";
        GPWFSConnectorStore serverConnector = WFSConnectorBuilder.newConnector().withServerUrl(new URL(wfsURL)).build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(Arrays.asList(states));
        Schema response = request.getResponse();

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, statesName);
        if (layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);

        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);

        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setPropertyNames(Arrays.asList(new String[]{"STATE_NAME", "PERSONS"}));
        getFeatureRequest.setBBox(new BBox(-75.102613, 40.212597, -72.361859, 41.512517));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(ResultTypeType.RESULTS.value());

        getFeatureRequest.setMaxFeatures(BigInteger.valueOf(50));

        logger.debug("\n\t@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : {}", getFeatureRequest.getResponseAsString());

        InputStream is = getFeatureRequest.getResponseAsStream();

        final WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);

        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if (!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }

        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION_DTO : {}", featureCollection);
    }

    @Test
    public void tigerRoadsTest() throws Exception {
        String wfsURL = "http://150.145.141.92/geoserver/wfs";
        GPWFSConnectorStore serverConnector = WFSConnectorBuilder.newConnector().withServerUrl(new URL(wfsURL)).build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(Arrays.asList(tigerRoads));
        Schema response = request.getResponse();

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, tigerRoadsName);
        if (layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);

        logger.debug("\n\t##################################LAYER_SCHEMA : {}", layerSchema);

        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(ResultTypeType.RESULTS.value());

        getFeatureRequest.setMaxFeatures(BigInteger.valueOf(50));

        logger.debug("\n\t@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : {}", getFeatureRequest.showRequestAsString());

        InputStream is = getFeatureRequest.getResponseAsStream();

        final WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);

        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if (!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
        logger.debug("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION_DTO : {}", featureCollection);
    }

    @Test
    public void siteTrTest() throws Exception {
        String wfsURL = "http://150.145.133.241/geoserver/wfs";
        QName siteTRCom = new QName("cite:tr_com");
        GPWFSConnectorStore serverConnector = WFSConnectorBuilder.newConnector().withServerUrl(new URL(wfsURL)).build();
        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(Arrays.asList(siteTRCom));
        Schema response = request.getResponse();

        String localPart = siteTRCom.getLocalPart();
        String name = localPart.substring(localPart.indexOf(":") + 1);

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response, name);
        if (layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);

        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(ResultTypeType.RESULTS.value());

        getFeatureRequest.setMaxFeatures(BigInteger.valueOf(50));
//
//        logger.debug("\n\t@@@@@@@@@@@@@@@@@@REQUEST_AS_STRING : {}", getFeatureRequest.showRequestAsString());
//        logger.debug("######################RESPONSE_AS_STRING : \n{}\n", getFeatureRequest.formatResponseAsString(2));

        InputStream is = getFeatureRequest.getResponseAsStream();
        WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);
        if (!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }
       for(FeatureDTO featureDTO : featureCollection.getFeatures()) {
            logger.info("##############################FEATURE : {}\n", featureDTO);
       }
    }
}
