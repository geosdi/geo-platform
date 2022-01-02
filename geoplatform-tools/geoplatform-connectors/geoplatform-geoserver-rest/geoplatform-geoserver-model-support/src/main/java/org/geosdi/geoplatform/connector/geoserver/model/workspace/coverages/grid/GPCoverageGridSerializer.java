/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
class GPCoverageGridSerializer extends StdSerializer<GPCoverageGrid> {

    GPCoverageGridSerializer() {
        super(GPCoverageGrid.class);
    }

    /**
     * @param value
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     */
    @Override
    public void serialize(GPCoverageGrid value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("@dimension", value.getDimension());
        jsonGenerator.writeStringField("crs", value.getCrs());
        jsonGenerator.writeFieldName("range");
        if (value.isSetRange()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("high", value.getRange().getHigh());
            jsonGenerator.writeStringField("low", value.getRange().getLow());
            jsonGenerator.writeEndObject();
        } else
            jsonGenerator.writeObject(null);
        jsonGenerator.writeFieldName("transform");
        if (value.isSetTransform()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("scaleX", value.getTransform().getScaleX());
            jsonGenerator.writeNumberField("scaleY", value.getTransform().getScaleY());
            jsonGenerator.writeNumberField("shearX", value.getTransform().getShearX());
            jsonGenerator.writeNumberField("shearY", value.getTransform().getShearY());
            jsonGenerator.writeNumberField("translateX", value.getTransform().getTranslateX());
            jsonGenerator.writeNumberField("translateY", value.getTransform().getTranslateY());
            jsonGenerator.writeEndObject();
        } else
            jsonGenerator.writeObject(null);
        jsonGenerator.writeEndObject();
    }
}