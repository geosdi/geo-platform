/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.geoserver.model.crs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPGeoserverCRSSerializer extends StdSerializer<GPGeoserverCRS> {

    GPGeoserverCRSSerializer() {
        super(GPGeoserverCRS.class);
    }

    /**
     * @param value
     * @param jsonGenerator
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(GPGeoserverCRS value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("$", value.getValue());
        jsonGenerator.writeStringField("@class", value.getType());
        jsonGenerator.writeEndObject();
    }

    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles, using specified type serializer
     * for embedding necessary type information.
     * <p>
     * Default implementation will throw {@link UnsupportedOperationException}
     * to indicate that proper type handling needs to be implemented.
     * <p>
     * For simple datatypes written as a single scalar value (JSON String, Number, Boolean),
     * implementation would look like:
     * <pre>
     *  // note: method to call depends on whether this type is serialized as JSON scalar, object or Array!
     *  typeSer.writeTypePrefixForScalar(value, gen);
     *  serialize(value, gen, provider);
     *  typeSer.writeTypeSuffixForScalar(value, gen);
     * </pre>
     * and implementations for type serialized as JSON Arrays or Objects would differ slightly,
     * as <code>START-ARRAY</code>/<code>END-ARRAY</code> and
     * <code>START-OBJECT</code>/<code>END-OBJECT</code> pairs
     * need to be properly handled with respect to serializing of contents.
     *
     * @param value Value to serialize; can <b>not</b> be null.
     * @param gen Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     * serializing Objects value contains, if any.
     * @param typeSer Type serializer to use for including type information
     */
    @Override
    public void serializeWithType(GPGeoserverCRS value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(value, gen, serializers);
    }
}