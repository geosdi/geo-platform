package org.geosdi.geoplatform.experimental.el.threadpool.factory;

import java.util.concurrent.ThreadFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchThreadFactory extends ThreadFactory {

    /**
     * @return {@link String}
     */
    String getThreadNamePrefix();

    /**
     * @return {@link Boolean}
     */
    Boolean isDaemon();

    /**
     * @return {@link Integer}
     */
    Integer getPriority();

    /**
     * @return {@link Integer}
     */
    Integer nextThreadID();
}
