package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import org.geosdi.geoplatform.connector.wfs.response.QueryRestrictionDTO;
import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.filter.v110.ComparisonOpsType;
import org.geosdi.geoplatform.xml.filter.v110.ObjectFactory;

import javax.xml.bind.JAXBElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface QueryRestrictionStrategy<T extends ComparisonOpsType> {

    final ObjectFactory filterFactory = new ObjectFactory();

    /**
     *
     * @param queryRestrictionDTO
     * @return {@link JAXBElement<T>}
     */
    JAXBElement<T> create(QueryRestrictionDTO queryRestrictionDTO);

    /**
     * @return {@link Boolean}
     */
    Boolean isValidStrategy();

    /**
     * @return {@link OperatorType}
     */
    OperatorType forOperatorType();
}
