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
package org.geosdi.geoplatform.connector.server.request.v202;

import org.geosdi.geoplatform.connector.server.GPServerConnector;
import org.geosdi.geoplatform.connector.server.request.CatalogGetRecords;
import org.geosdi.geoplatform.connector.server.request.v202.responsibility.GetRecordsRequestManager;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.xml.csw.OutputSchema;
import org.geosdi.geoplatform.xml.csw.TypeName;
import org.geosdi.geoplatform.xml.csw.v202.*;
import org.geosdi.geoplatform.xml.filter.v110.FilterType;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * GetRecords CSW_202 request 2.0.2 version
 * 
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class CatalogGetRecordsV202 extends CatalogGetRecords<GetRecordsResponseType, GetRecordsType> {

    private GetRecordsRequestManager catalogRequestManager = new GetRecordsRequestManager();

    public CatalogGetRecordsV202(GPServerConnector server) {
        super(server);
    }

    @Override
    protected GetRecordsType createRequest() throws IllegalParameterFault {
        GetRecordsType request = new GetRecordsType();

        request.setResultType(resultType != null
                ? ResultType.fromValue(resultType) : ResultType.HITS);

        request.setOutputSchema(outputSchema != null
                ? outputSchema.toString() : OutputSchema.CSW_V202.toString());

        // The default 'output format' is the MIME type "application/xml"
        request.setOutputFormat("application/xml");

        QueryType query = new QueryType();
        request.setAbstractQuery(query);

        List<QName> typNameList = new ArrayList<>();
        typNameList.add(typeName != null
                ? typeName.getQName() : TypeName.RECORD_V202.getQName());
        query.setTypeNames(typNameList);

        ElementSetNameType elementSetNameType = new ElementSetNameType();
        elementSetNameType.setValue(elementSetName != null
                ? ElementSetType.fromValue(elementSetName) : ElementSetType.SUMMARY);
        query.setElementSetName(elementSetNameType);

        FilterType filterType = new FilterType();
        catalogRequestManager.filterGetRecordsRequest(this, filterType);

        logger.debug("\n*** {} ***", filterType);

        if (filterType.isSetLogicOps()
                || filterType.isSetComparisonOps() || filterType.isSetSpatialOps()
                || constraint != null) {
            if (constraintLanguage == null) {
                throw new IllegalArgumentException(
                        "If there is at least one filter criteria, "
                        + "'Constraint Language' must not be null.");
            }
            if (constraintLanguageVersion == null) {
                throw new IllegalArgumentException(
                        "'Constraint Language Version' must not be null.");
            }

            QueryConstraintType queryConstraintType = new QueryConstraintType();
            queryConstraintType.setVersion(constraintLanguageVersion.getVersion());

            switch (constraintLanguage) {
                case FILTER:
                    queryConstraintType.setFilter(filterType);
                    break;

                case CQL_TEXT:
                    queryConstraintType.setCqlText(constraint);
                    break;
            }

            query.setConstraint(queryConstraintType);
        }

        if (startPosition != null) {
            request.setStartPosition(startPosition);
        }

        if (maxRecords != null) {
            request.setMaxRecords(maxRecords);
        }

        return request;
    }
}
