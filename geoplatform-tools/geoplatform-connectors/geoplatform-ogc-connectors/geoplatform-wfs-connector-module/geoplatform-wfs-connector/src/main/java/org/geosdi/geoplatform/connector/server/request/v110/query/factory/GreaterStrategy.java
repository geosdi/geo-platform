package org.geosdi.geoplatform.connector.server.request.v110.query.factory;

import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;
import org.geosdi.geoplatform.xml.filter.v110.BinaryComparisonOpType;

import javax.xml.bind.JAXBElement;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GreaterStrategy extends GenericBinaryStrategy {

    @Override
    protected JAXBElement<BinaryComparisonOpType> create(BinaryComparisonOpType binaryComparisonOpType) {
        return filterFactory.createPropertyIsGreaterThan(binaryComparisonOpType);
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public final OperatorType forOperatorType() {
        return OperatorType.GREATER;
    }
}
