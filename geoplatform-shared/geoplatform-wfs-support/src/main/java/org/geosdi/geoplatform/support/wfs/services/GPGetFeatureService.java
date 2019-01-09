/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.wfs.services;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.connector.GPWFSConnectorStore;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.connector.wfs.response.FeatureCollectionDTO;
import org.geosdi.geoplatform.connector.wfs.response.FeatureDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.connector.wfs.response.QueryDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.support.wfs.feature.reader.WFSGetFeatureStaxReader;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGetFeatureService extends AbstractFeatureService implements GetFeaureService {

    /**
     * @param layerSchema
     * @param fid
     * @param headerParams
     * @return {@link FeatureDTO}
     * @throws Exception
     */
    @Override
    public FeatureDTO getFeature(LayerSchemaDTO layerSchema, String fid, Map<String, String> headerParams) throws Exception {
        Preconditions.checkArgument((fid != null) && !(fid.isEmpty()),
                "The Parameter FID must not be null or an Empty String.");
        WFSGetFeatureRequest request = this.createRequest(layerSchema, headerParams);
        request.setFeatureIDs(Arrays.asList(fid));
        FeatureCollectionDTO featureCollection = this.getFeatureCollection(request, layerSchema);
        List<FeatureDTO> features = featureCollection.getFeatures();
        Preconditions.checkArgument((features != null) && (features.size() == 1),
                "The Parameter Features must not be null and must have size == 1.");
        return features.get(0);
    }

    /**
     * @param layerSchema
     * @param bBox
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO getFeature(LayerSchemaDTO layerSchema, BBox bBox, Map<String, String> headerParams)
            throws Exception {
        Preconditions.checkArgument(bBox != null, "The Parameter bBox must not be null.");
        WFSGetFeatureRequest request = this.createRequest(layerSchema, headerParams);
        request.setBBox(bBox);
        return this.getFeatureCollection(request, layerSchema);
    }

    /**
     * @param layerSchema
     * @param maxFeatures
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO getFeature(LayerSchemaDTO layerSchema, int maxFeatures, Map<String, String> headerParams)
            throws Exception {
        Preconditions.checkArgument(maxFeatures > 0, "The Parameter maxFeatures must be > 0.");
        WFSGetFeatureRequest request = this.createRequest(layerSchema, headerParams);
        request.setMaxFeatures(BigInteger.valueOf(maxFeatures));
        return this.getFeatureCollection(request, layerSchema);
    }

    /**
     * @param layerSchema
     * @param maxFeatures
     * @param queryDTO
     * @param headerParams
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    @Override
    public FeatureCollectionDTO getFeature(LayerSchemaDTO layerSchema, int maxFeatures, QueryDTO queryDTO,
            Map<String, String> headerParams) throws Exception {
        maxFeatures = (maxFeatures > 0) ? maxFeatures : 100;

        WFSGetFeatureRequest request = this.createRequest(layerSchema, headerParams);
        request.setMaxFeatures(BigInteger.valueOf(maxFeatures));
        request.setQueryDTO(queryDTO);
        return this.getFeatureCollection(request, layerSchema);
    }

    /**
     * @param layerSchema
     * @param headerParams
     * @return {@link WFSGetFeatureRequest}
     * @throws Exception
     */
    private WFSGetFeatureRequest createRequest(LayerSchemaDTO layerSchema, Map<String, String> headerParams) throws Exception {
        Preconditions.checkArgument(layerSchema != null, "The Parameter LayerSchema must not be null.");
        String typeName = layerSchema.getTypeName();
        assert (typeName != null);
        logger.debug("\n*** WFS GetFeature for layer {} ***", typeName);
        if (!typeName.contains(":")) {
            throw new IllegalArgumentException("typeName must contain the char \":\"");
        }

        String serverURL = layerSchema.getScope();
        assert (serverURL != null);
        serverURL = serverURL.replace("wms", "wfs");
        if (!this.wfsConfigurator.matchDefaultDataSource(serverURL)) {
            throw new IllegalStateException(
                    "Edit Mode cannot be applied to the server with url " + wfsConfigurator.getDefaultWFSDataSource());
        }

        GPWFSConnectorStore serverConnector = ((headerParams != null) && (headerParams.size() > 0)) ?
                super.createWFSConnector(serverURL, headerParams) : super.createWFSConnector(serverURL);
        WFSGetFeatureRequest request = serverConnector.createGetFeatureRequest();
        QName qName = new QName(typeName);
        request.setTypeName(qName);
        request.setSRS("EPSG:4326");
        request.setResultType(ResultTypeType.RESULTS.value());
        return request;
    }

    /**
     * @param request
     * @param layerSchema
     * @return {@link FeatureCollectionDTO}
     * @throws Exception
     */
    private FeatureCollectionDTO getFeatureCollection(WFSGetFeatureRequest request, LayerSchemaDTO layerSchema) throws Exception {
        FeatureCollectionDTO featureCollection = null;
        try {
            InputStream is = request.getResponseAsStream();
            if (is == null) { // TODO check if the is can be null
                logger.error("\n### The layer \"{}\" isn't a feature ###", layerSchema.getTypeName());
            }
            final WFSGetFeatureStaxReader featureReaderStAX = new WFSGetFeatureStaxReader(layerSchema);
            featureCollection = featureReaderStAX.read(is);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(
                    "Error to execute the WFS GetFeature for the layer " + layerSchema.getTypeName());
        }
        return featureCollection;
    }
}
