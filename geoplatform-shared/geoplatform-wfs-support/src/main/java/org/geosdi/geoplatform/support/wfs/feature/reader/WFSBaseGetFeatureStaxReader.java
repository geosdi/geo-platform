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
package org.geosdi.geoplatform.support.wfs.feature.reader;

import org.geosdi.geoplatform.connector.wfs.response.GeometryAttributeDTO;
import org.geosdi.geoplatform.connector.wfs.response.LayerSchemaDTO;
import org.geosdi.geoplatform.stax.reader.AbstractStaxStreamReader;
import org.geosdi.geoplatform.stax.reader.builder.GPXmlStreamReaderBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.stream.XMLStreamReader;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WFSBaseGetFeatureStaxReader<F, G, R> extends AbstractStaxStreamReader<R> implements GPWFSGetFeatureStaxReader {

    protected final LayerSchemaDTO layerSchema;

    /**
     * @param theXmlStreamBuilder
     * @param theLayerSchema
     */
    protected WFSBaseGetFeatureStaxReader(@Nonnull(when = NEVER) GPXmlStreamReaderBuilder theXmlStreamBuilder, @Nonnull(when = NEVER) LayerSchemaDTO theLayerSchema) {
        super(theXmlStreamBuilder);
        checkArgument(theLayerSchema != null, "The LayerSchema must not be null.");
        this.layerSchema = theLayerSchema;
    }

    /**
     * @param object
     * @return {@link R}
     * @throws Exception
     */
    @Override
    public final R read(@Nonnull(when = NEVER) Object object) throws Exception {
        XMLStreamReader reader = super.acquireReader(object);
        GeometryAttributeDTO geometryAtt = layerSchema.getGeometry();
        checkArgument(geometryAtt != null, "The Parameter geometryAttribute must not be null.");
        String geometryName = geometryAtt.getName();
        checkArgument((geometryName != null) && !(geometryName.trim().isEmpty()), "The Parameter geometryName must not be null or an empty string.");
        return this.internalRead(reader, geometryName, layerSchema.getAttributeNames());
    }

    /**
     * @param reader
     * @param geometryName
     * @param attributeNames
     * @return {@link R}
     * @throws Exception
     */
    protected abstract R internalRead(XMLStreamReader reader, String geometryName, List<String> attributeNames) throws Exception;

    /**
     * @return {@link F}
     * @throws Exception
     */
    protected abstract F readFID() throws Exception;

    /**
     * @return {@link G}
     * @throws Exception
     */
    protected abstract G readGeometry() throws Exception;

    /**
     * @param feature
     * @param featureName
     * @param attributeNames
     * @param nextTag
     * @param prefix
     * @param geometryName
     * @throws Exception
     */
    protected abstract void readAttributes(@Nonnull(when = NEVER) F feature, @Nonnull(when = NEVER) String featureName, @Nullable List<String> attributeNames,
            @Nonnull(when = NEVER) Boolean nextTag, @Nonnull(when = NEVER) String prefix, @Nonnull(when = NEVER) String geometryName) throws Exception;
}