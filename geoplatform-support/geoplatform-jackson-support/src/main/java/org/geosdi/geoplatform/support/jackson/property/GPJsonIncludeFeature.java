package org.geosdi.geoplatform.support.jackson.property;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPJsonIncludeFeature implements JacksonSupportConfigFeature<JsonInclude.Include> {

    ALWAYS {
        /**
         * @return {@link Boolean}
         */
        @Override
        public Boolean getValue() {
            return Boolean.TRUE;
        }

        /**
         * @return {@link JsonInclude.Include}
         */
        @Override
        public JsonInclude.Include getFeature() {
            return JsonInclude.Include.ALWAYS;
        }

        /**
         * @param mapper
         */
        @Override
        public void configureMapper(ObjectMapper mapper) {
            mapper.setSerializationInclusion(getFeature());
        }
    },
    NON_NULL {
        /**
         * @return {@link Boolean}
         */
        @Override
        public Boolean getValue() {
            return Boolean.TRUE;
        }

        /**
         * @return {@link JsonInclude.Include}
         */
        @Override
        public JsonInclude.Include getFeature() {
            return JsonInclude.Include.NON_NULL;
        }

        /**
         * @param mapper
         */
        @Override
        public void configureMapper(ObjectMapper mapper) {
            mapper.setSerializationInclusion(getFeature());
        }
    },
    NON_ABSENT {
        /**
         * @return {@link Boolean}
         */
        @Override
        public Boolean getValue() {
            return Boolean.TRUE;
        }

        /**
         * @return {@link JsonInclude.Include}
         */
        @Override
        public JsonInclude.Include getFeature() {
            return JsonInclude.Include.NON_ABSENT;
        }

        /**
         * @param mapper
         */
        @Override
        public void configureMapper(ObjectMapper mapper) {
            mapper.setSerializationInclusion(getFeature());
        }
    },
    NON_EMPTY {
        /**
         * @return {@link Boolean}
         */
        @Override
        public Boolean getValue() {
            return Boolean.TRUE;
        }

        /**
         * @return {@link JsonInclude.Include}
         */
        @Override
        public JsonInclude.Include getFeature() {
            return JsonInclude.Include.NON_EMPTY;
        }

        /**
         * @param mapper
         */
        @Override
        public void configureMapper(ObjectMapper mapper) {
            mapper.setSerializationInclusion(getFeature());
        }
    },
    NON_DEFAULT {
        /**
         * @return {@link Boolean}
         */
        @Override
        public Boolean getValue() {
            return Boolean.TRUE;
        }

        /**
         * @return {@link JsonInclude.Include}
         */
        @Override
        public JsonInclude.Include getFeature() {
            return JsonInclude.Include.NON_DEFAULT;
        }

        /**
         * @param mapper
         */
        @Override
        public void configureMapper(ObjectMapper mapper) {
            mapper.setSerializationInclusion(getFeature());
        }
    },
    USE_DEFAULTS {
        /**
         * @return {@link Boolean}
         */
        @Override
        public Boolean getValue() {
            return Boolean.TRUE;
        }

        /**
         * @return {@link JsonInclude.Include}
         */
        @Override
        public JsonInclude.Include getFeature() {
            return JsonInclude.Include.USE_DEFAULTS;
        }

        /**
         * @param mapper
         */
        @Override
        public void configureMapper(ObjectMapper mapper) {
            mapper.setSerializationInclusion(getFeature());
        }
    }
}
