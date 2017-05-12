package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.filter.v110.ComparisonOpsType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface WFSQueryRestrictionRepository<T extends ComparisonOpsType> {

    /**
     * @param operatorType
     * @return {@link QueryRestrictionStrategy<T>}
     */
    QueryRestrictionStrategy<T> getQueryRestrictionStrategy(OperatorType operatorType);
}
