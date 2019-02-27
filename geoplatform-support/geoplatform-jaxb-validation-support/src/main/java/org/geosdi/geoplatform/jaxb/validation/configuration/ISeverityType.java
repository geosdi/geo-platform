package org.geosdi.geoplatform.jaxb.validation.configuration;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ISeverityType {
    
    /**
     * @return {@link String}
     */
    String getSeverityType();

    /**
     *
     */
    enum SeverityType implements ISeverityType {
        WARNING("WARNING"), ERROR("ERROR"), FATAL_ERROR("FATAL_ERROR");

        private final String severityType;

        SeverityType(String theSeverityType) {
            this.severityType = theSeverityType;
        }


        /**
         * @return {@link String}
         */
        @Override
        public String getSeverityType() {
            return this.severityType;
        }

        @Override
        public String toString() {
            return this.severityType;
        }
    }
}