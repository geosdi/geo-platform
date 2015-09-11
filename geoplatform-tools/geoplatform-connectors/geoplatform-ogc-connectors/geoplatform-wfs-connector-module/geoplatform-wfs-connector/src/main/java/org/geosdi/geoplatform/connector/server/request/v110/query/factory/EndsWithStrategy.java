package org.geosdi.geoplatform.connector.server.request.v110.query.factory;

import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class EndsWithStrategy extends MatchStrategy {

    @Override
    protected Object matchExpression(String content) {
        return content.concat("%");
    }

    /**
     * @return {@link OperatorType}
     */
    @Override
    public OperatorType forOperatorType() {
        return OperatorType.ENDS_WITH;
    }
}
