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
package org.geosdi.geoplatform.connector.server.request.v110;

import java.util.Arrays;
import javax.xml.bind.JAXBElement;
import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.AbstractGetFeatureRequest;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;
import org.geosdi.geoplatform.xml.filter.v110.GmlObjectIdType;
import org.geosdi.geoplatform.xml.wfs.v110.FeatureCollectionType;
import org.geosdi.geoplatform.xml.wfs.v110.GetFeatureType;
import org.geosdi.geoplatform.xml.wfs.v110.QueryType;
import org.geosdi.geoplatform.xml.wfs.v110.ResultTypeType;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class WFSGetFeatureRequestV110
        extends AbstractGetFeatureRequest<FeatureCollectionType> {

    protected org.geosdi.geoplatform.xml.filter.v110.ObjectFactory filterFactory;

    public WFSGetFeatureRequestV110(GPServerConnector server) {
        super(server);
        filterFactory = new org.geosdi.geoplatform.xml.filter.v110.ObjectFactory();
    }

    @Override
    protected Object createRequest() throws IllegalParameterFault {
        if (typeName == null) {
            throw new IllegalArgumentException("typeName must not be null.");
        }

        GetFeatureType request = new GetFeatureType();

        QueryType query = new QueryType();
        query.setTypeName(Arrays.asList(typeName));
        request.getQuery().add(query);

        if (featureIDs != null && !featureIDs.isEmpty()) {
            FilterType filter = new FilterType();

            for (String featureID : featureIDs) {
                // Chech featureID (only one single query is permitted)
                int ind = featureID.lastIndexOf(".");
                String title = featureID.substring(0, ind - 1);
//                System.out.println("\n*** Layer title (from featureID): " + title);
                if (!typeName.getLocalPart().contains(title)) {
                    throw new IllegalArgumentException("featureID must be referer to typeName (one single query).");
                }

                // Add featureID to filter
                GmlObjectIdType obj = new GmlObjectIdType();
                obj.setId(featureID);

                JAXBElement<GmlObjectIdType> gmlObjectId = filterFactory.createGmlObjectId(obj);
                filter.getId().add(gmlObjectId);
            }

            query.setFilter(filter);
        }

        if (resultType != null) {
            request.setResultType(ResultTypeType.fromValue(resultType));
        }

        if (outputFormat != null) {
            request.setOutputFormat(outputFormat);
        }

        if (maxFeatures != null) {
            request.setMaxFeatures(maxFeatures);
        }

//        logger.info("\n\n\n{}\n\n\n", request);

        return request;
    }
}
