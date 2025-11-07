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
package org.geosdi.geoplatform.connector.server.request.kvp;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.std.StdDeserializer;

import static org.geosdi.geoplatform.connector.server.request.kvp.GPWMSRequestKvpReader.WMS_REQUEST_KEY_VALUE_PAIR;
import static org.geosdi.geoplatform.connector.server.request.kvp.GPWMSRequestKvpReader.WMS_SERVICE_KEY_VALUE_PAIR;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSRequestKeyValuePairDeserializer extends StdDeserializer<WMSGetMapBaseRequestKeyValuePair> {

    public GPWMSRequestKeyValuePairDeserializer() {
        super(WMSGetMapBaseRequestKeyValuePair.class);
    }

    /**
     * Method that can be called to ask implementation to deserialize
     * JSON content into the value type this serializer handles.
     * Returned instance is to be constructed by method itself.
     * <p>
     * Pre-condition for this method is that the parser points to the
     * first event that is part of value to deserializer (and which
     * is never JSON 'null' literal, more on this below): for simple
     * types it may be the only value; and for structured types the
     * Object start marker or a FIELD_NAME.
     * </p>
     * <p>
     * The two possible input conditions for structured types result
     * from polymorphism via fields. In the ordinary case, Jackson
     * calls this method when it has encountered an OBJECT_START,
     * and the method implementation must advance to the next token to
     * see the first field name. If the application configures
     * polymorphism via a field, then the object looks like the following.
     * <pre>
     *      {
     *          "@class": "class name",
     *          ...
     *      }
     *  </pre>
     * Jackson consumes the two tokens (the {@code @class} field name
     * and its value) in order to learn the class and select the deserializer.
     * Thus, the stream is pointing to the FIELD_NAME for the first field
     * after the @class. Thus, if you want your method to work correctly
     * both with and without polymorphism, you must begin your method with:
     * <pre>
     *       if (p.currentToken() == JsonToken.START_OBJECT) {
     *         p.nextToken();
     *       }
     *  </pre>
     * This results in the stream pointing to the field name, so that
     * the two conditions align.
     * <p>
     * Post-condition is that the parser will point to the last
     * event that is part of deserialized value (or in case deserialization
     * fails, event that was not recognized or usable, which may be
     * the same event as the one it pointed to upon call).
     * <p>
     * <strong>Handling null values (JsonToken.VALUE_NULL)</strong>
     * <br>
     * : Note that this method is never called for the JSON {@code null} literal to avoid
     * every deserializer from having to handle null values. Instead, the
     * {@link ValueDeserializer#getNullValue(DeserializationContext)} method
     * is called to produce a null value. To influence null handling,
     * custom deserializers should override
     * {@link ValueDeserializer#getNullValue(DeserializationContext)}
     * and usually also {@link ValueDeserializer#getNullAccessPattern()}.
     *
     * @param p Parser used for reading JSON content
     * @param ctxt Context that can be used to access information about
     * this deserialization activity.
     * @return Deserialized value
     */
    @Override
    public WMSGetMapBaseRequestKeyValuePair deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        JsonNode node = p.readValueAsTree();
        String key = node.get("key").asText();
        String value = node.get("value").asText();
        return switch ((key != null) ? key : "") {
            case "SERVICE" -> WMS_SERVICE_KEY_VALUE_PAIR;
            case "VERSION" -> new WMSVersionKeyValuePair(value);
            case "REQUEST" -> WMS_REQUEST_KEY_VALUE_PAIR;
            case "LAYERS" -> new WMSLayersKeyValuePair(value);
            case "SRS" -> new WMSSrsKeyValuePair(value);
            case "CRS" -> new WMSCrsKeyValuePair(value);
            case "WIDTH" -> new WMSWidthKeyValuePair(value);
            case "HEIGHT" -> new WMSHeightKeyValuePair(value);
            case "BBOX" -> new WMSBoundingBoxKeyValuePair(value);
            case "STYLES" -> new WMSStylesKeyValuePair(value);
            default -> new WMSGetMapBaseRequestKeyValuePair<String>(key) {

                /**
                 * @return {@link String}
                 */
                @Override
                public String toValue() {
                    return value;
                }
            };
        };
    }
}