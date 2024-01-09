/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.attribute;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.reactivex.rxjava3.functions.Consumer;
import org.geosdi.geoplatform.connector.geoserver.model.jackson.serializer.rx.GPGeoserverRXConsumerSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Objects;

import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPFeatureTypeAttributesSerializer extends StdSerializer<GPFeatureTypeAttributes> {

    private static final Logger logger = LoggerFactory.getLogger(GPFeatureTypeAttributesSerializer.class);

    GPFeatureTypeAttributesSerializer() {
        super(GPFeatureTypeAttributes.class);
    }

    /**
     * @param value
     * @param gen
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(GPFeatureTypeAttributes value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Consumer<IGPFeatureTypeAttribute> featureTypeAttributeConsumer = new GPFeatureAttributeConsumer(gen);
        gen.writeStartObject();
        gen.writeFieldName("attribute");
        gen.writeStartArray();
        if ((value.getValues()) != null && !(value.getValues().isEmpty())) {
            fromIterable(value.getValues())
                    .doOnComplete(() -> logger.info("################RX terminated its work for : {}\n", this.getClass().getSimpleName()))
                    .filter(Objects::nonNull)
                    .subscribe(featureTypeAttributeConsumer, Throwable::printStackTrace);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

    static class GPFeatureAttributeConsumer extends GPGeoserverRXConsumerSerializer<IGPFeatureTypeAttribute> {

        /**
         * @param theJsonGenerator
         */
        GPFeatureAttributeConsumer(@Nonnull(when = NEVER) JsonGenerator theJsonGenerator) {
           super(theJsonGenerator);
        }

        /**
         * Consume the given value.
         *
         * @param featureTypeAttribute the value
         * @throws Throwable if the implementation wishes to throw any type of exception
         */
        @Override
        public void accept(IGPFeatureTypeAttribute featureTypeAttribute) throws Throwable {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", featureTypeAttribute.getName());
            jsonGenerator.writeNumberField("minOccurs", featureTypeAttribute.getMinOccurs());
            jsonGenerator.writeNumberField("maxOccurs", featureTypeAttribute.getMaxOccurs());
            jsonGenerator.writeBooleanField("nillable", featureTypeAttribute.isNillable());
            jsonGenerator.writeStringField("binding", featureTypeAttribute.getBinding());
            jsonGenerator.writeEndObject();
        }
    }
}