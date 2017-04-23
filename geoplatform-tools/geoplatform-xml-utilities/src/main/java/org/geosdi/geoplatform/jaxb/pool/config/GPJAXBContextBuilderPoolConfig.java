package org.geosdi.geoplatform.jaxb.pool.config;

import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJAXBContextBuilderPoolConfig extends GenericKeyedObjectPoolConfig {

    {
        super.setMaxIdlePerKey(10);
        super.setMaxTotal(50);
        super.setMinIdlePerKey(1);
        super.setJmxEnabled(Boolean.FALSE);
        super.setTimeBetweenEvictionRunsMillis(100L * 60L * 20L);
        super.setNumTestsPerEvictionRun(15);
        super.setMinEvictableIdleTimeMillis(100L * 60L * 10L);
    }
}
