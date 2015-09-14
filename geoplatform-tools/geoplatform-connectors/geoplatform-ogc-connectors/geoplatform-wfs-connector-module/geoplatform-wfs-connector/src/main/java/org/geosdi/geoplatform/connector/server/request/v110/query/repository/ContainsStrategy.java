package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ContainsStrategy extends MatchStrategy {

    /**
     * @return {@link OperatorType}
     */
    @Override
    public OperatorType forOperatorType() {
        return OperatorType.CONTAINS;
    }

    @Override
    protected Object matchExpression(String content) {
        return "%".concat(content).concat("%");
    }
}
