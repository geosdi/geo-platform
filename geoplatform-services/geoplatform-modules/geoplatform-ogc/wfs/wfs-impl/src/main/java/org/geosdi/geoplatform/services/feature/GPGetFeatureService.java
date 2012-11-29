/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services.feature;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import org.geosdi.geoplatform.connector.GPWFSConnector;
import org.geosdi.geoplatform.connector.server.request.WFSGetFeatureRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.ServerInternalFault;
import org.geosdi.geoplatform.feature.reader.WFSGetFeatureStaxReader;
import org.geosdi.geoplatform.gui.responce.FeatureCollectionDTO;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.geosdi.geoplatform.gui.shared.bean.BBox;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;
import org.springframework.stereotype.Service;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Service(value = "gpGetFeatureService")
public class GPGetFeatureService extends AbstractFeatureService
        implements GetFeaureService {

    @Override
    public FeatureCollectionDTO getFeature(LayerSchemaDTO layerSchema, BBox bBox)
            throws ResourceNotFoundFault, IllegalParameterFault {
        assert (layerSchema != null);
        assert (bBox != null);

        String typeName = layerSchema.getTypeName();
        assert (typeName != null);
        logger.debug("\n*** WFS GetFeature for layer {} ***", typeName);
        if (!typeName.contains(":")) {
            throw new IllegalParameterFault(
                    "typeName must contain the char \":\"");
        }

        String serverURL = layerSchema.getScope();
        assert (serverURL != null);
        serverURL = serverURL.replace("wms", "wfs");
        if (!this.wfsConfigurator.matchDefaultDataSource(serverURL)) {
            throw new ResourceNotFoundFault(
                    "Edit Mode cannot be applied to the server with url "
                    + wfsConfigurator.getDefaultWFSDataSource());
        }

        FeatureCollectionDTO featureCollection = null;
        try {
            GPWFSConnector serverConnector = super.createWFSConnector(serverURL);
            WFSGetFeatureRequest request = serverConnector.createGetFeatureRequest();

            QName qName = new QName(typeName);
            request.setTypeName(qName);
            request.setBBox(bBox);
            request.setSRS("EPSG:4326");
            request.setResultType(ResultTypeType.RESULTS.value());
            request.setMaxFeatures(BigInteger.valueOf(1000L)); // TODO pass it as argument?

            InputStream is = request.getResponseAsStream();
            if (is == null) { // TODO check if the is can be null
                logger.error("\n### The layer \"{}\" isn't a feature ###", typeName);
            }

            final WFSGetFeatureStaxReader featureReaderStAX =
                    new WFSGetFeatureStaxReader(layerSchema);

            featureCollection = featureReaderStAX.read(is);

        } catch (ServerInternalFault ex) {
            logger.error("\n### ServerInternalFault: {} ###", ex.getMessage());
        } catch (XMLStreamException ex) {
            logger.error("\n### XMLStreamException: {} ###", ex.getMessage());
        } catch (IOException ex) {
            logger.error("\n### IOException: {} ###", ex.getMessage());
            throw new ResourceNotFoundFault(
                    "Error to execute the WFS GetFeature for the layer " + layerSchema.getTypeName());
        }

        return featureCollection;
    }
}
