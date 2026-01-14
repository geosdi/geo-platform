/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import tools.jackson.core.StreamWriteFeature;
import tools.jackson.core.json.JsonWriteFeature;
import tools.jackson.databind.json.JsonMapper;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static javax.annotation.meta.When.NEVER;
import static tools.jackson.core.json.JsonWriteFeature.WRITE_NAN_AS_STRINGS;
import static tools.jackson.core.json.JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPJsonGeneratorFeature implements JacksonSupportConfigFeature<Object, JsonMapper.Builder> {

    AUTO_CLOSE_TARGET_ENABLE() {
        @Override
        public Boolean getValue() {
            return TRUE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.AUTO_CLOSE_TARGET;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }
    }, AUTO_CLOSE_TARGET_DISABLE() {
        @Override
        public Boolean getValue() {
            return FALSE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.AUTO_CLOSE_TARGET;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }
    }, AUTO_CLOSE_JSON_CONTENT_ENABLE() {
        @Override
        public Boolean getValue() {
            return TRUE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.AUTO_CLOSE_CONTENT;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }
    }, AUTO_CLOSE_JSON_CONTENT_DISABLE() {
        @Override
        public Boolean getValue() {
            return FALSE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.AUTO_CLOSE_CONTENT;
        }

        /**
         * @param builder
         */
        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }
    }, QUOTE_NON_NUMERIC_NUMBERS_ENABLE() {
        @Override
        public Boolean getValue() {
            return TRUE;
        }

        @Override
        public JsonWriteFeature getFeature() {
            return WRITE_NAN_AS_STRINGS;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, QUOTE_NON_NUMERIC_NUMBERS_DISABLE() {
        @Override
        public Boolean getValue() {
            return FALSE;
        }

        @Override
        public JsonWriteFeature getFeature() {
            return WRITE_NAN_AS_STRINGS;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, WRITE_NUMBERS_AS_STRINGS_ENABLE() {
        @Override
        public Boolean getValue() {
            return TRUE;
        }

        @Override
        public JsonWriteFeature getFeature() {
            return WRITE_NUMBERS_AS_STRINGS;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, WRITE_NUMBERS_AS_STRINGS_DISABLE() {
        @Override
        public Boolean getValue() {
            return FALSE;
        }

        @Override
        public JsonWriteFeature getFeature() {
            return WRITE_NUMBERS_AS_STRINGS;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, WRITE_BIGDECIMAL_AS_PLAIN_ENABLE() {
        @Override
        public Boolean getValue() {
            return TRUE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.WRITE_BIGDECIMAL_AS_PLAIN;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, WRITE_BIGDECIMAL_AS_PLAIN_DISABLE() {
        @Override
        public Boolean getValue() {
            return FALSE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.WRITE_BIGDECIMAL_AS_PLAIN;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, FLUSH_PASSED_TO_STREAM_ENABLE() {
        @Override
        public Boolean getValue() {
            return TRUE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.FLUSH_PASSED_TO_STREAM;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, FLUSH_PASSED_TO_STREAM_DISABLE() {
        @Override
        public Boolean getValue() {
            return FALSE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.FLUSH_PASSED_TO_STREAM;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, ESCAPE_NON_ASCII_ENABLE() {
        @Override
        public Boolean getValue() {
            return TRUE;
        }

        @Override
        public JsonWriteFeature getFeature() {
            return JsonWriteFeature.ESCAPE_NON_ASCII;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, ESCAPE_NON_ASCII_DISABLE() {
        @Override
        public Boolean getValue() {
            return FALSE;
        }

        @Override
        public JsonWriteFeature getFeature() {
            return JsonWriteFeature.ESCAPE_NON_ASCII;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, STRICT_DUPLICATE_DETECTION_ENABLE() {
        @Override
        public Boolean getValue() {
            return TRUE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.STRICT_DUPLICATE_DETECTION;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, STRICT_DUPLICATE_DETECTION_DISABLE() {
        @Override
        public Boolean getValue() {
            return FALSE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.STRICT_DUPLICATE_DETECTION;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, IGNORE_UNKNOWN_ENABLE() {
        @Override
        public Boolean getValue() {
            return TRUE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.IGNORE_UNKNOWN;
        }

        @Override
        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }

    }, IGNORE_UNKNOWN_DISABLE() {
        @Override
        public Boolean getValue() {
            return FALSE;
        }

        @Override
        public StreamWriteFeature getFeature() {
            return StreamWriteFeature.IGNORE_UNKNOWN;
        }

        public void configureMapper(@Nonnull(when = NEVER) JsonMapper.Builder builder) {
            checkArgument(builder != null, "The Parameter builder must not be null.");
            builder.configure(getFeature(), getValue());
        }
    };
}