package org.geosdi.geoplatform.experimental.el.rest.spring.jasypt.pbe.properties;

import org.geosdi.geoplatform.jasypt.support.env.GPPBESystemEnvProperties;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchRestPBEProperties extends GPPBESystemEnvProperties.GPBasePBESystemEnvProperties {

    /**
     * @param thePropertyEnvName
     */
    public GPElasticSearchRestPBEProperties(@Nonnull(when = NEVER) String thePropertyEnvName) {
        super(thePropertyEnvName);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}