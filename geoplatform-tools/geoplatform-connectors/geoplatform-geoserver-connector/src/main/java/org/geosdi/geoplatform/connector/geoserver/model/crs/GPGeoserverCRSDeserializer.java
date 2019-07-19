package org.geosdi.geoplatform.connector.geoserver.model.crs;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.fasterxml.jackson.core.JsonToken.START_OBJECT;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverCRSDeserializer extends StdDeserializer<Object> {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverCRSDeserializer.class);

    public GPGeoserverCRSDeserializer() {
        super(Object.class);
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
     * Jackson consumes the two tokens (the <tt>@class</tt> field name
     * and its value) in order to learn the class and select the deserializer.
     * Thus, the stream is pointing to the FIELD_NAME for the first field
     * after the @class. Thus, if you want your method to work correctly
     * both with and without polymorphism, you must begin your method with:
     * <pre>
     *       if (p.getCurrentToken() == JsonToken.START_OBJECT) {
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
     * Note that this method is never called for JSON null literal,
     * and thus deserializers need (and should) not check for it.
     *
     * @param jsonParser Parsed used for reading JSON content
     * @param ctxt       Context that can be used to access information about
     *                   this deserialization activity.
     * @return Deserialized value
     */
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String nodeValue = jsonParser.getText();
        return (!nodeValue.equals(START_OBJECT.asString()) ? jsonParser.getText() : loadGPGeoserverCRS(jsonParser));
    }

    /**
     * @param jsonParser
     * @return {@link IGPGeoserverCRS}
     * @throws IOException
     */
    private IGPGeoserverCRS loadGPGeoserverCRS(JsonParser jsonParser) throws IOException {
        String crsType = null;
        String crsValue = null;
        do {
            String propertyName = jsonParser.currentName();
            logger.debug("######################PROPERTY_NAME_FOUND : {}\n", propertyName);
            switch (propertyName) {
                case "$": {
                    crsValue = jsonParser.getText();
                    break;
                }
                case "@class": {
                    crsType = jsonParser.getText();
                    break;
                }
            }
        } while (jsonParser.nextToken() != JsonToken.END_OBJECT);
        return ((crsType != null) && (crsValue != null)) ? new GPGeoserverCRS(crsValue, crsType) : null;
    }
}