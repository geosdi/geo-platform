package org.geosdi.geoplatform.connector.wfs.services;

import org.geosdi.geoplatform.connector.server.request.v110.query.factory.QueryRestrictionStrategy;
import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.filter.v110.ComparisonOpsType;

import javax.xml.bind.JAXBElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class QueryRestrictionMockStrategy implements QueryRestrictionStrategy<ComparisonOpsType> {

    /**
     * @param queryRestrictionDTO
     * @return {@link JAXBElement <ComparisonOpsType>}
     */
    @Override
    public JAXBElement<ComparisonOpsType> create(QueryRestrictionDTO queryRestrictionDTO) {
        return null;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isValidStrategy() {
        return Boolean.FALSE;
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public OperatorType forOperatorType() {
        return null;
    }
}
