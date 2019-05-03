package org.geosdi.geoplatform.support.mail.spring.configuration.velocity;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPVelocityParserPollSize extends Serializable {

    /**
     * @return {@link Integer}
     */
    Integer getPoolSize();
}