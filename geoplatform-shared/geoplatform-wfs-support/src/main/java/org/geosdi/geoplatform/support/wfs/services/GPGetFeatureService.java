/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.support.wfs.services;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.wfs.responce.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.responce.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.responce.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSGetFeatureStaxReader;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
//@Service(value = "gpGetFeatureService")
public class GPGetFeatureService extends AbstractFeatureService
        implements GetFeaureService {

    @Override
    public FeatureDTO getFeature(LayerSchemaDTO layerSchema, String fid)
            throws Exception {
        assert (fid != null);

        WFSGetFeatureRequest request = this.createRequest(layerSchema);
        request.setFeatureIDs(Arrays.asList(fid));

        FeatureCollectionDTO featureCollection = this.getFeatureCollection(
                request, layerSchema);
        List<FeatureDTO> features = featureCollection.getFeatures();
        assert (features != null);
        assert (features.size() == 1);
        return features.get(0);
    }

    @Override
    public FeatureCollectionDTO getFeature(LayerSchemaDTO layerSchema, BBox bBox)
            throws Exception {
        assert (bBox != null);

        WFSGetFeatureRequest request = this.createRequest(layerSchema);
        request.setBBox(bBox);

        return this.getFeatureCollection(request, layerSchema);
    }

    @Override
    public FeatureCollectionDTO getFeature(LayerSchemaDTO layerSchema,
            int maxFeatures)
            throws Exception {
        assert (maxFeatures > 0);

        WFSGetFeatureRequest request = this.createRequest(layerSchema);
        request.setMaxFeatures(BigInteger.valueOf(maxFeatures));

        return this.getFeatureCollection(request, layerSchema);
    }

    private WFSGetFeatureRequest createRequest(LayerSchemaDTO layerSchema)
            throws Exception {
        assert (layerSchema != null);

        String typeName = layerSchema.getTypeName();
        assert (typeName != null);
        logger.debug("\n*** WFS GetFeature for layer {} ***", typeName);
        if (!typeName.contains(":")) {
            throw new IllegalArgumentException(
                    "typeName must contain the char \":\"");
        }

        String serverURL = layerSchema.getScope();
        assert (serverURL != null);
        serverURL = serverURL.replace("wms", "wfs");
        if (!this.wfsConfigurator.matchDefaultDataSource(serverURL)) {
            throw new IllegalStateException(
                    "Edit Mode cannot be applied to the server with url "
                    + wfsConfigurator.getDefaultWFSDataSource());
        }

        GPWFSConnectorStore serverConnector = super.createWFSConnector(serverURL);
        WFSGetFeatureRequest request = serverConnector.createGetFeatureRequest();

        QName qName = new QName(typeName);
        request.setTypeName(qName);
        request.setSRS("EPSG:4326");
        request.setResultType(ResultTypeType.RESULTS.value());
        request.setMaxFeatures(BigInteger.valueOf(1000L)); // TODO pass it as argument?

        return request;
    }

    private FeatureCollectionDTO getFeatureCollection(
            WFSGetFeatureRequest request, LayerSchemaDTO layerSchema)
            throws Exception {

        FeatureCollectionDTO featureCollection = null;
        try {
            InputStream is = request.getResponseAsStream();
            if (is == null) { // TODO check if the is can be null
                logger.error("\n### The layer \"{}\" isn't a feature ###",
                        layerSchema.getTypeName());
            }

            final WFSGetFeatureStaxReader featureReaderStAX
                    = new WFSGetFeatureStaxReader(layerSchema);

            featureCollection = featureReaderStAX.read(is);

        } catch (IOException ex) {
            logger.error("\n### IOException: {} ###", ex.getMessage());
            throw new IllegalStateException(
                    "Error to execute the WFS GetFeature for the layer "
                    + layerSchema.getTypeName());
        }

        return featureCollection;
    }

}
