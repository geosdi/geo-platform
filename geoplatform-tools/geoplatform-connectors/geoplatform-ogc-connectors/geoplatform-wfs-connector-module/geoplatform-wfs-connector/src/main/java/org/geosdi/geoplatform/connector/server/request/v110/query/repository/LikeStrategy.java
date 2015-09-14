package org.geosdi.geoplatform.connector.server.request.v110.query.repository;

import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LikeStrategy extends ContainsStrategy {

    /**
     * @return {@link OperatorType}
     */
    @Override
    public OperatorType forOperatorType() {
        return OperatorType.LIKE;
    }
}
