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
package org.geosdi.geoplatform.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import org.geosdi.geoplatform.configurator.wfs.GPWFSConfigurator;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.wfs.WFSDataStoreFactory;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 */
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GPWFSService")
public class GPWFSServiceImpl implements GPWFSService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private GPWFSConfigurator wfsConfigurator;

    @Override
    public LayerSchemaDTO describeFeatureType(String serverUrl,
            String typeName)
            throws ResourceNotFoundFault {

        if (!this.wfsConfigurator.matchDefaultDataSource(serverUrl)) {
            throw new ResourceNotFoundFault("Edit Mode can not be applied "
                    + "to the server with url " + serverUrl);
        }

        LayerSchemaDTO layerSchema = new LayerSchemaDTO();
        List<AttributeDTO> attributeList = new ArrayList<AttributeDTO>();
        try {

            serverUrl += "?service=WFS&version=1.0.0&request=GetCapabilities";

            Map connectionParameters = new HashMap();
            connectionParameters.put(WFSDataStoreFactory.URL.key, serverUrl);

            DataStore data = DataStoreFinder.getDataStore(connectionParameters);
            SimpleFeatureType schema = data.getSchema(typeName);

            layerSchema.setTargetNamespace(schema.getName().getNamespaceURI());

            // Populate only the geometry attribute (always is the first)
            // Use a list of attributes for future uses
            for (AttributeDescriptor desk : schema.getAttributeDescriptors()) {
                if ("the_geom".equals(desk.getLocalName())) {
                    AttributeDTO attribute = new AttributeDTO();

                    attribute.setName(desk.getLocalName());
                    attribute.setValue(
                            desk.getType().getBinding().getSimpleName());

                    attributeList.add(attribute);
                    break;
                }
            }

            layerSchema.setAttributes(attributeList);

        } catch (IOException ex) {
            logger.error("\n### IOException: {} ###", ex.getMessage());
            throw new ResourceNotFoundFault("The Layer " + typeName + ""
                    + " is not a vector");
        }


        return layerSchema;

    }

    @Override
    public LayerSchemaDTO getFeature(String featureID) throws ResourceNotFoundFault {
        // TODO
        return null;
    }
}
