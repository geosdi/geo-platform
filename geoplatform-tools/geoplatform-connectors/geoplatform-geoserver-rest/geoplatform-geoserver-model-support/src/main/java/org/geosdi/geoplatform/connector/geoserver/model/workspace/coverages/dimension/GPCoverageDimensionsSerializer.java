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
package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.dimension;

import io.reactivex.rxjava3.functions.Consumer;
import org.geosdi.geoplatform.connector.geoserver.model.jackson.serializer.rx.GPGeoserverRXConsumerSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

import javax.annotation.Nonnull;
import java.util.Objects;

import static io.reactivex.rxjava3.core.Observable.fromIterable;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPCoverageDimensionsSerializer extends StdSerializer<GPCoverageDimensions> {

    private static final Logger logger = LoggerFactory.getLogger(GPCoverageDimensionsSerializer.class);

    GPCoverageDimensionsSerializer() {
        super(GPCoverageDimensions.class);
    }

    /**
     * @param value
     * @param gen
     * @param provider
     * @throws JacksonException
     */
    @Override
    public void serialize(GPCoverageDimensions value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        Consumer<IGPCoverageDimension> coverageDimensionConsumer = new GPCoverageDimensionsConsumer(gen);
        gen.writeStartObject();
        gen.writeName("coverageDimension");
        gen.writeStartArray();
        if ((value.getCoverageDimension()) != null && !(value.getCoverageDimension().isEmpty())) {
            fromIterable(value.getCoverageDimension())
                    .doOnComplete(() -> logger.info("################RX terminated its work for : {}\n", this.getClass().getSimpleName()))
                    .filter(Objects::nonNull)
                    .subscribe(coverageDimensionConsumer, Throwable::printStackTrace);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

    static class GPCoverageDimensionsConsumer extends GPGeoserverRXConsumerSerializer<IGPCoverageDimension> {

        /**
         * @param theJsonGenerator
         */
        GPCoverageDimensionsConsumer(@Nonnull(when = NEVER) JsonGenerator theJsonGenerator) {
            super(theJsonGenerator);
        }

        @Override
        public void accept(IGPCoverageDimension coverageDimension) throws Throwable {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringProperty("description", coverageDimension.getDescription());
            jsonGenerator.writeStringProperty("name", coverageDimension.getName());
            jsonGenerator.writeName("range");
            jsonGenerator.writeStartArray();
            if (coverageDimension.isSetRange()) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberProperty("max", coverageDimension.getRange().getMax());
                jsonGenerator.writeNumberProperty("min", coverageDimension.getRange().getMin());
                jsonGenerator.writeEndObject();
            } else
                jsonGenerator.writePOJO(null);
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }
    }
}