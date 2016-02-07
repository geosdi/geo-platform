package org.geosdi.geoplatform.experimental.el.sequence;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum BaseSequenceSettings implements IGPSequenceGenerator.GPSequenceSettings {

    GP_SEQUENCE {
        /**
         * @return {@link String} Index Name
         */
        @Override
        public String getSequenceName() {
            return "gp_sequence";
        }

        /**
         * @return {@link String} Index Type
         */
        @Override
        public String getSequenceType() {
            return "gp_sequence_type";
        }
    }
}
