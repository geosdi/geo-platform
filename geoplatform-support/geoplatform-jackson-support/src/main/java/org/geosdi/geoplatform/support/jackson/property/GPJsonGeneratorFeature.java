/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.fasterxml.jackson.core.json.JsonWriteFeature.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPJsonGeneratorFeature implements JacksonSupportConfigFeature<JsonGenerator.Feature> {

    AUTO_CLOSE_TARGET_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.AUTO_CLOSE_TARGET;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    AUTO_CLOSE_TARGET_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.AUTO_CLOSE_TARGET;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    AUTO_CLOSE_JSON_CONTENT_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    AUTO_CLOSE_JSON_CONTENT_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    QUOTE_FIELD_NAMES_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return QUOTE_FIELD_NAMES.mappedFeature();
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }
            },
    QUOTE_FIELD_NAMES_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonWriteFeature.QUOTE_FIELD_NAMES.mappedFeature();
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }
            },
    QUOTE_NON_NUMERIC_NUMBERS_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return WRITE_NAN_AS_STRINGS.mappedFeature();
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    QUOTE_NON_NUMERIC_NUMBERS_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return WRITE_NAN_AS_STRINGS.mappedFeature();
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    WRITE_NUMBERS_AS_STRINGS_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return WRITE_NUMBERS_AS_STRINGS.mappedFeature();
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    WRITE_NUMBERS_AS_STRINGS_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return WRITE_NUMBERS_AS_STRINGS.mappedFeature();
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    WRITE_BIGDECIMAL_AS_PLAIN_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    WRITE_BIGDECIMAL_AS_PLAIN_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    FLUSH_PASSED_TO_STREAM_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    FLUSH_PASSED_TO_STREAM_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    ESCAPE_NON_ASCII_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return ESCAPE_NON_ASCII.mappedFeature();
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    ESCAPE_NON_ASCII_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return ESCAPE_NON_ASCII.mappedFeature();
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    STRICT_DUPLICATE_DETECTION_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    STRICT_DUPLICATE_DETECTION_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    IGNORE_UNKNOWN_ENABLE() {

                @Override
                public Boolean getValue() {
                    return TRUE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.IGNORE_UNKNOWN;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            },
    IGNORE_UNKNOWN_DISABLE() {

                @Override
                public Boolean getValue() {
                    return FALSE;
                }

                @Override
                public JsonGenerator.Feature getFeature() {
                    return JsonGenerator.Feature.IGNORE_UNKNOWN;
                }

                @Override
                public void configureMapper(ObjectMapper mapper) {
                    mapper.configure(getFeature(), getValue());
                }

            }
}