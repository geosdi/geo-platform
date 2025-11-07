/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson.property;

import tools.jackson.core.StreamReadFeature;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.MapperFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.ConfigFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.cfg.MapperBuilder;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static tools.jackson.core.StreamReadFeature.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPJacksonSupportEnum implements JacksonSupportConfigFeature<Object, MapperBuilder<?, ?>> {

    AUTO_CLOSE_SOURCE_ENABLE(TRUE) {
        @Override
        public StreamReadFeature getFeature() {
            return StreamReadFeature.AUTO_CLOSE_SOURCE;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, AUTO_CLOSE_SOURCE_DISABLE(FALSE) {
        @Override
        public StreamReadFeature getFeature() {
            return StreamReadFeature.AUTO_CLOSE_SOURCE;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, UNWRAP_ROOT_VALUE_ENABLE(TRUE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.UNWRAP_ROOT_VALUE;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, UNWRAP_ROOT_VALUE_DISABLE(FALSE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.UNWRAP_ROOT_VALUE;
        }

        /**
         * @param builder
         */

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, FAIL_ON_IGNORED_PROPERTIES_ENABLE(TRUE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, FAIL_ON_IGNORED_PROPERTIES_DISABLE(FALSE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, FAIL_ON_UNKNOW_PROPERTIES_ENABLE(TRUE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, ACCEPT_EMPTY_STRING_AS_NULL_OBJECT_ENABLE(TRUE) {
        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }

        /**
         * @return {@link DeserializationFeature}
         */
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
        }
    }, ACCEPT_EMPTY_STRING_AS_NULL_OBJECT_DISABLE(FALSE) {
        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }

        /**
         * @return {@link DeserializationFeature}
         */
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
        }
    }, FAIL_ON_UNKNOW_PROPERTIES_DISABLE(FALSE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, FAIL_ON_NULL_FOR_PRIMITIVES_ENABLE(TRUE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, FAIL_ON_NULL_FOR_PRIMITIVES_DISABLE(FALSE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE(TRUE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, ACCEPT_SINGLE_VALUE_AS_ARRAY_DISABLE(FALSE) {
        @Override
        public DeserializationFeature getFeature() {
            return DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, READ_DATE_AS_TIMESTAMP_AS_NANOSECONDS_ENABLE(TRUE) {
        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }

        /**
         * @return {@link DateTimeFeature}
         */
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS;
        }
    }, READ_DATE_AS_TIMESTAMP_AS_NANOSECONDS_DISABLE(FALSE) {
        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }

        /**
         * @return {@link DateTimeFeature}
         */
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS;
        }
    }, WRAP_ROOT_VALUE_ENABLE(TRUE) {
        @Override
        public SerializationFeature getFeature() {
            return SerializationFeature.WRAP_ROOT_VALUE;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, WRAP_ROOT_VALUE_DISABLE(FALSE) {
        @Override
        public SerializationFeature getFeature() {
            return SerializationFeature.WRAP_ROOT_VALUE;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, INDENT_OUTPUT_ENABLE(TRUE) {
        @Override
        public SerializationFeature getFeature() {
            return SerializationFeature.INDENT_OUTPUT;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, INDENT_OUTPUT_DISABLE(FALSE) {
        @Override
        public SerializationFeature getFeature() {
            return SerializationFeature.INDENT_OUTPUT;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, WRITE_DATES_AS_TIMESTAMPS_ENABLE(TRUE) {
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, WRITE_DATES_AS_TIMESTAMPS_DISABLE(FALSE) {
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, WRITE_DATE_KEYS_AS_TIMESTAMPS_ENABLED(TRUE) {
        /**
         * @return {@link ConfigFeature}
         */
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, WRITE_DATE_KEYS_AS_TIMESTAMPS_DISABLED(FALSE) {
        /**
         * @return {@link ConfigFeature}
         */
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, WRITE_DATES_WITH_ZONE_ID_ENABLE(TRUE) {
        /**
         * @return {@link DateTimeFeature}
         */
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.WRITE_DATES_WITH_ZONE_ID;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, WRITE_DATES_WITH_ZONE_ID_DISABLE(FALSE) {
        /**
         * @return {@link DateTimeFeature}
         */
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.WRITE_DATES_WITH_ZONE_ID;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, WRITE_DURATIONS_AS_TIMESTAMPS_ENABLE(TRUE) {
        /**
         * @return {@link DateTimeFeature}
         */
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.WRITE_DURATIONS_AS_TIMESTAMPS;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, WRITE_DURATIONS_AS_TIMESTAMPS_DISABLE(FALSE) {
        /**
         * @return {@link DateTimeFeature}
         */
        @Override
        public DateTimeFeature getFeature() {
            return DateTimeFeature.WRITE_DURATIONS_AS_TIMESTAMPS;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, USE_WRAPPER_NAME_AS_PROPERTY_NAME_ENABLE(TRUE) {
        @Override
        public MapperFeature getFeature() {
            return MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, USE_WRAPPER_NAME_AS_PROPERTY_NAME_DISABLE(FALSE) {
        @Override
        public MapperFeature getFeature() {
            return MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, FAIL_ON_EMPTY_BEANS_ENABLE(TRUE) {
        /**
         * @return {@link SerializationFeature}
         */
        @Override
        public SerializationFeature getFeature() {
            return SerializationFeature.FAIL_ON_EMPTY_BEANS;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }
    }, FAIL_ON_EMPTY_BEANS_DISABLE(FALSE) {
        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(this.getFeature(), this.getValue());
        }

        /**
         * @return {@link SerializationFeature}
         */
        @Override
        public SerializationFeature getFeature() {
            return SerializationFeature.FAIL_ON_EMPTY_BEANS;
        }
    },  STRICT_DUPLICATE_DETECTION_ENABLE(TRUE) {
        @Override
        public StreamReadFeature getFeature() {
            return STRICT_DUPLICATE_DETECTION;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, STRICT_DUPLICATE_DETECTION_DISABLE(FALSE) {
        @Override
        public StreamReadFeature getFeature() {
            return STRICT_DUPLICATE_DETECTION;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }
    }, USE_FAST_DOUBLE_PARSER_ENABLE(TRUE) {
        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

        /**
         * @return {@link StreamReadFeature}
         */
        @Override
        public StreamReadFeature getFeature() {
            return USE_FAST_DOUBLE_PARSER;
        }
    }, USE_FAST_DOUBLE_PARSER_DISABLE(FALSE) {
        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

        /**
         * @return {@link StreamReadFeature}
         */
        @Override
        public StreamReadFeature getFeature() {
            return USE_FAST_DOUBLE_PARSER;
        }
    }, USE_FAST_BIG_NUMBER_PARSER_ENABLE(TRUE) {
        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

        /**
         * @return {@link StreamReadFeature}
         */
        @Override
        public StreamReadFeature getFeature() {
            return USE_FAST_BIG_NUMBER_PARSER;
        }
    }, USE_FAST_BIG_NUMBER_PARSER_DISABLE(FALSE) {
        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) MapperBuilder<?, ?> builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

        /**
         * @return {@link StreamReadFeature}
         */
        @Override
        public StreamReadFeature getFeature() {
            return USE_FAST_BIG_NUMBER_PARSER;
        }
    };

    private final Boolean state;

    /**
     * @param theState
     */
    GPJacksonSupportEnum(@Nonnull(when = NEVER) Boolean theState) {
        checkArgument(theState != null, "The Parameter state must not be null.");
        this.state = theState;
    }


    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean getValue() {
        return this.state;
    }
}
