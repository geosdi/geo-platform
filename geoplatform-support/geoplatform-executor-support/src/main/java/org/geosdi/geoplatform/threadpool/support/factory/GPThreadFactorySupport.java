package org.geosdi.geoplatform.threadpool.support.factory;

import java.util.concurrent.ThreadFactory;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPThreadFactorySupport extends ThreadFactory {

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
