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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.jws.WebService;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.geosdi.geoplatform.configurator.wfs.GPWFSConfigurator;
import org.geosdi.geoplatform.connector.GPWFSConnector;
import org.geosdi.geoplatform.connector.WFSConnectorBuilder;
import org.geosdi.geoplatform.connector.server.request.WFSDescribeFeatureTypeRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.exception.ServerInternalFault;
import org.geosdi.geoplatform.gui.responce.AttributeDTO;
import org.geosdi.geoplatform.gui.responce.LayerSchemaDTO;
import org.geosdi.geoplatform.xml.xsd.v2001.LocalElement;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.geosdi.geoplatform.xml.xsd.v2001.TopLevelComplexType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @email francesco.izzi@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GPWFSService")
public class GPWFSServiceImpl implements GPWFSService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private GPWFSConfigurator wfsConfigurator;

    @Override
    public LayerSchemaDTO describeFeatureType(String serverUrl, String typeName)
            throws ResourceNotFoundFault, IllegalParameterFault {
        logger.debug("\n*** WFS DescribeFeatureType for layer {} ***", typeName);
        serverUrl = serverUrl.replace("wms", "wfs");

        if (!this.wfsConfigurator.matchDefaultDataSource(serverUrl)) {
            throw new ResourceNotFoundFault(
                    "Edit Mode cannot be applied to the server with url "
                    + wfsConfigurator.getDefaultWFSDataSource());
        }

        LayerSchemaDTO layerSchema = new LayerSchemaDTO();
        layerSchema.setTypeName(typeName);
        List<AttributeDTO> attributeList;
        try {
            GPWFSConnector serverConnector = this.createWFSConnector(serverUrl);
            WFSDescribeFeatureTypeRequest<Schema> request = serverConnector.createDescribeFeatureTypeRequest();

            QName name = new QName(typeName);
            request.setTypeName(Arrays.asList(name));
            request.setOutputFormat("text/xml; subtype=gml/3.1.1");

            Schema response = request.getResponse();
            layerSchema.setTargetNamespace(response.getTargetNamespace());

            List<TopLevelComplexType> topLevelComplexTypeElements = response.getTopLevelComplexTypeElements();
            TopLevelComplexType topLevelComplexType = topLevelComplexTypeElements.get(0);

            List<Object> particles = topLevelComplexType.getComplexContent().getExtension().getSequence().getParticle();

            Object geom = particles.get(0);
            LocalElement geomElement = ((JAXBElement<LocalElement>) geom).getValue();
            AttributeDTO geometryAttribute = new AttributeDTO();
            Class typeClass = GeometryBinding.getGMLGeometry(geomElement.getType().getLocalPart());
            geometryAttribute.setType(typeClass.getSimpleName());
            geometryAttribute.setName(geomElement.getName());
            layerSchema.setGeometry(geometryAttribute);

            attributeList = this.createAttributes(particles);
            layerSchema.setAttributes(attributeList);

        } catch (NullPointerException ex) {
            // data.getSchema(typeName) throws this exception
            // if the layer is not a feature
            logger.error(
                    "\n### NullPointerException - The layer isn't a feature: {} ###",
                    ex.getMessage());
            return null;
        } catch (ServerInternalFault ex) {
            logger.error("\n### ServerInternalFault: {} ###", ex.getMessage());
        } catch (IOException ex) {
            logger.error("\n### IOException: {} ###", ex.getMessage());
            throw new ResourceNotFoundFault(
                    "Error to execute the WFS DescribeFeatureType for the layer " + typeName);
        }

        return layerSchema;

    }

    private GPWFSConnector createWFSConnector(String serverUrl) throws IllegalParameterFault {
        GPWFSConnector serverConnector;
        try {
            URL url = new URL(serverUrl);
            WFSConnectorBuilder builder = WFSConnectorBuilder.newConnector().withServerUrl(url);
            serverConnector = builder.build();
        } catch (MalformedURLException ex) {
            logger.error("### MalformedURLException: {}", ex.getMessage());
            throw new IllegalParameterFault("Malformed URL");
        }
        return serverConnector;
    }

    /**
     * Create the Feature attributes, except the geometry attribute.
     */
    private List<AttributeDTO> createAttributes(List<Object> particles) {
        List<AttributeDTO> attributes = new ArrayList<AttributeDTO>(
                particles.size() - 1);

        for (int i = 1; i < particles.size(); i++) {
            Object p = particles.get(i);
            LocalElement l = ((JAXBElement<LocalElement>) p).getValue();

            AttributeDTO att = new AttributeDTO();
            att.setName(l.getName());
            att.setType(l.getType().getLocalPart());
            logger.debug("\n*** {} is of type {}", att.getName(), att.getType());

            attributes.add(att);
        }

        return attributes;
    }

    @Override
    public LayerSchemaDTO getFeature(String featureID) throws ResourceNotFoundFault {
        // TODO
        return null;
    }
}
