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
package org.geosdi.geoplatform.connector.geoserver.model.workspace.coverages.grid;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

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
     * @param gen
     * @param provider
     * @throws JacksonException
     */
    @Override
    public void serialize(GPCoverageGrid value, JsonGenerator gen, SerializationContext provider) throws JacksonException {
        gen.writeStartObject();
        gen.writeStringProperty("@dimension", value.getDimension());
        gen.writeStringProperty("crs", value.getCrs());
        gen.writeName("range");
        if (value.isSetRange()) {
            gen.writeStartObject();
            gen.writeStringProperty("high", value.getRange().getHigh());
            gen.writeStringProperty("low", value.getRange().getLow());
            gen.writeEndObject();
        } else
            gen.writePOJO(null);
        gen.writeName("transform");
        if (value.isSetTransform()) {
            gen.writeStartObject();
            gen.writeNumberProperty("scaleX", value.getTransform().getScaleX());
            gen.writeNumberProperty("scaleY", value.getTransform().getScaleY());
            gen.writeNumberProperty("shearX", value.getTransform().getShearX());
            gen.writeNumberProperty("shearY", value.getTransform().getShearY());
            gen.writeNumberProperty("translateX", value.getTransform().getTranslateX());
            gen.writeNumberProperty("translateY", value.getTransform().getTranslateY());
            gen.writeEndObject();
        } else
            gen.writePOJO(null);
        gen.writeEndObject();
    }
}