package org.geosdi.geoplatform.jasypt.support.env;

import com.google.common.base.Preconditions;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPBESystemEnvProperties {

    /**
     * @return {@link String}
     */
    String getPassword();

    /**
     *
     */
    class GPBasePBESystemEnvProperties implements GPPBESystemEnvProperties {

        private final String propertyEnvName;

        public GPBasePBESystemEnvProperties(String thePropertyEnvName) {
            Preconditions.checkArgument((thePropertyEnvName != null) && !(thePropertyEnvName.isEmpty()),
                    "The parameter PropertyEnvName must not be null or an Empty String.");
            this.propertyEnvName = thePropertyEnvName;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getPassword() {
            return System.getProperty(this.propertyEnvName);
        }
    }
}
