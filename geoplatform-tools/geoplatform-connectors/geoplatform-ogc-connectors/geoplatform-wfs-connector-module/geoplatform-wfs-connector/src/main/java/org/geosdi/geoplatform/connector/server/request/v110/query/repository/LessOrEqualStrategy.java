package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.filter.v110.BinaryComparisonOpType;

import javax.xml.bind.JAXBElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LessOrEqualStrategy extends GenericBinaryStrategy {

    @Override
    protected JAXBElement<BinaryComparisonOpType> create(BinaryComparisonOpType binaryComparisonOpType) {
        return filterFactory.createPropertyIsLessThanOrEqualTo(binaryComparisonOpType);
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public final OperatorType forOperatorType() {
        return OperatorType.LESS_OR_EQUAL;
    }
}
