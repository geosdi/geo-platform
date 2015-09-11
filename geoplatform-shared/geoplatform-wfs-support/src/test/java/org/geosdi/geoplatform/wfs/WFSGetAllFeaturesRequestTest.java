/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p/>
 * Copyright (C) 2008-2015 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p/>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p/>
 * ====================================================================
 * <p/>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p/>
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
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.support.wfs.feature.reader.FeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.GPFeatureSchemaReader;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSGetFeatureStaxReader;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.util.Arrays;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSGetAllFeaturesRequestTest {

    private static final Logger logger = LoggerFactory.getLogger(WFSGetAllFeaturesRequestTest.class);
    //
    private static final QName information = new QName("admin:admin_shp_06banisuhela_crisis_information_poly");
    private static final String informationName = information.getLocalPart().substring(
            information.getLocalPart().indexOf(":") + 1, information.getLocalPart().length());
    //
    private final String wfsURL = "http://geoserver.wfppal.org/geoserver/wfs";
    GPWFSConnectorStore serverConnector;
    FeatureSchemaReader featureReaderXSD = new GPFeatureSchemaReader();

    @Before
    public void setUp() throws Exception {
        this.serverConnector = WFSConnectorBuilder.newConnector().withServerUrl(new URL(wfsURL)).build();
    }

    //@Ignore(value = "Server can be down")
    @Test
    public void getAllFeaturesTest() throws Exception {

        WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();
        request.setTypeName(Arrays.asList(information));
        Schema response = request.getResponse();

        LayerSchemaDTO layerSchema = featureReaderXSD.getFeature(response,
                informationName);
        if (layerSchema == null) {
            throw new IllegalStateException("The Layer Schema is null.");
        }
        layerSchema.setScope(wfsURL);

        logger.info("\n\t##################################LAYER_SCHEMA : {}", layerSchema);

        WFSGetFeatureRequest getFeatureRequest = serverConnector.createGetFeatureRequest();
        getFeatureRequest.setTypeName(new QName(layerSchema.getTypeName()));
        getFeatureRequest.setSRS("EPSG:4326");
        getFeatureRequest.setResultType(ResultTypeType.RESULTS.value());

        getFeatureRequest.setMaxFeatures(BigInteger.valueOf(50));

        logger.info("\n\t@@@@@@@@@@@@@@@@@@RESPONSE_AS_STRING : {}", getFeatureRequest.getResponseAsString());

        InputStream is = getFeatureRequest.getResponseAsStream();
        if (is == null) { // TODO check if the is can be null
            logger.error("\n### The layer \"{}\" isn't a feature ###", layerSchema.getTypeName());
        }

        final WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);

        FeatureCollectionDTO featureCollection = featureReaderStAX.read(is);

        if (!featureCollection.isFeaturesLoaded()) {
            featureCollection.setErrorMessage(getFeatureRequest.getResponseAsString());
        }

        logger.info("\n\t@@@@@@@@@@@@@@@@@@@@@@@@@@@FEATURE_COLLECTION_DTO : {}", featureCollection);
    }
}
