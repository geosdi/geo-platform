package org.geosdi.geoplatform.support.google.spring.configuration.jasypt.pbe;

import org.geosdi.geoplatform.jasypt.support.env.GPPBESystemEnvProperties;

import javax.annotation.Nonnull;

import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGoogleServicePBEProperties extends GPPBESystemEnvProperties.GPBasePBESystemEnvProperties {

    /**
     * @param thePropertyEnvName
     */
    public GPGoogleServicePBEProperties(@Nonnull(when = NEVER) String thePropertyEnvName) {
        super(thePropertyEnvName);
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}