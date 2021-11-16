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
package org.geosdi.geoplatform.connector.geoserver.model.about.version;

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
class GPGeoserverAboutVersionSerializer extends StdSerializer<GPGeoserverAboutVersion> {

    private static final Logger logger = LoggerFactory.getLogger(GPGeoserverAboutVersionSerializer.class);

    GPGeoserverAboutVersionSerializer() {
        super(GPGeoserverAboutVersion.class);
    }

    /**
     * @param value
     * @param gen
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(GPGeoserverAboutVersion value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Consumer<IGPGeoserverVersionResource> versionResourceConsumer = new GPGeoserverVersionResourceConsumer(gen);
        gen.writeStartObject();
        gen.writeFieldName("resource");
        gen.writeStartArray();
        if ((value.getResources()) != null && !(value.getResources().isEmpty())) {
            fromIterable(value.getResources())
                    .doOnComplete(() -> logger.info("################RX terminated its work for : {}\n", this.getClass().getSimpleName())).filter(Objects::nonNull)
                    .subscribe(versionResourceConsumer, Throwable::printStackTrace);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

    static class GPGeoserverVersionResourceConsumer extends GPGeoserverRXConsumerSerializer<IGPGeoserverVersionResource> {

        /**
         * @param theJsonParser
         */
        GPGeoserverVersionResourceConsumer(@Nonnull(when = NEVER) JsonGenerator theJsonParser) {
            super(theJsonParser);
        }

        /**
         * Performs this operation on the given argument.
         *
         * @param value the input argument
         */
        @Override
        public void accept(IGPGeoserverVersionResource value) throws Throwable{
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("@name", value.getName());
            jsonGenerator.writeStringField("Build-Timestamp", value.getBuildTimestamp());
            jsonGenerator.writeStringField("Version", value.getVersion());
            jsonGenerator.writeStringField("Git-Revision", value.getGitRevision());
            jsonGenerator.writeEndObject();
        }
    }
}