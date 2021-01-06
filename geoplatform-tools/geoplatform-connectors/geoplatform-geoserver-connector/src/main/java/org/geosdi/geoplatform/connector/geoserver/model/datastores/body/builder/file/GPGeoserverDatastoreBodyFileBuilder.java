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
package org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.file;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.GeoserverDatastoreType;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.GPGeoserverCreateDatastoreBodyBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.URI;
import java.nio.charset.Charset;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.file.GPGeoserverConnectionFileValues.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverDatastoreBodyFileBuilder<Builder extends GPGeoserverDatastoreBodyFileBuilder<?>> extends GPGeoserverCreateDatastoreBodyBuilder<Builder> {

    /**
     * <p>Only memory map a file one, then cache and reuse the map.</p>
     *
     * @param theCacheAndReuseMemoryMaps
     * @return {@link Builder}
     */
    Builder withCacheAndReuseMemoryMaps(@Nullable Boolean theCacheAndReuseMemoryMaps);

    /**
     * <p>URI to a the namespace.</p>
     *
     * @param theNamespace
     * @return {@link Builder}
     */
    Builder withNamespace(@Nullable URI theNamespace);

    /**
     * <p>Character used to decode strings from the DBF file.</p>
     *
     * @param theCharset
     * @return {@link Builder}
     */
    Builder withCharset(@Nullable Charset theCharset);

    /**
     * <p>Enable/Disable the automatic creation of spatial index.</p>
     *
     * @param theCreateSpatialIndex
     * @return {@link Builder}
     */
    Builder withCreateSpatialIndex(@Nullable Boolean theCreateSpatialIndex);

    /**
     * <p>Enable/Disable the use of spatial index for local shapefiles.</p>
     *
     * @param theEnableSpatialIndex
     * @return {@link Builder}
     */
    Builder withEnableSpatialIndex(@Nullable Boolean theEnableSpatialIndex);

    /**
     * <p>Enable/Disable the use of memory-mapped io.</p>
     *
     * @param theMemoryMappedBuffer
     * @return {@link Builder}
     */
    Builder withMemoryMappedBuffer(@Nullable Boolean theMemoryMappedBuffer);

    abstract class GeoserverDatastoreBodyFileBuilder<Builder extends GPGeoserverDatastoreBodyFileBuilder<?>> extends GPCreateDatastoreBodyBuilder<Builder> implements GPGeoserverDatastoreBodyFileBuilder<Builder> {

        /**
         * @param theType
         */
        protected GeoserverDatastoreBodyFileBuilder(@Nonnull(when = NEVER) GeoserverDatastoreType theType) {
            super(theType);
        }

        /**
         * <p>Only memory map a file one, then cache and reuse the map.</p>
         *
         * @param theCacheAndReuseMemoryMaps
         * @return {@link Builder}
         */
        @Override
        public Builder withCacheAndReuseMemoryMaps(@Nullable Boolean theCacheAndReuseMemoryMaps) {
            this.connectionParameters.compute(CACHE_ADN_REUSE_MEMORY_MAPS.getConnectionKey(), (k, v) -> (theCacheAndReuseMemoryMaps != null) ? theCacheAndReuseMemoryMaps.toString() : v);
            return self();
        }

        /**
         * <p>URI to a the namespace.</p>
         *
         * @param theNamespace
         * @return {@link Builder}
         */
        @Override
        public Builder withNamespace(@Nullable URI theNamespace) {
            this.connectionParameters.compute(NAMESPACE.getConnectionKey(), (k, v) -> (theNamespace != null) ? theNamespace.toASCIIString() : v);
            return self();
        }

        /**
         * <p>Character used to decode strings from the DBF file.</p>
         *
         * @param theCharset
         * @return {@link Builder}
         */
        @Override
        public Builder withCharset(@Nullable Charset theCharset) {
            this.connectionParameters.compute(CHARSET.getConnectionKey(), (k, v) -> (theCharset != null) ? theCharset.toString() : v);
            return self();
        }

        /**
         * <p>Enable/Disable the automatic creation of spatial index.</p>
         *
         * @param theCreateSpatialIndex
         * @return {@link Builder}
         */
        @Override
        public Builder withCreateSpatialIndex(@Nullable Boolean theCreateSpatialIndex) {
            this.connectionParameters.compute(CREATE_SPATIAL_INDEX.getConnectionKey(), (k, v) -> (theCreateSpatialIndex != null) ? theCreateSpatialIndex.toString() : v);
            return self();
        }

        /**
         * <p>Enable/Disable the use of spatial index for local shapefiles.</p>
         *
         * @param theEnableSpatialIndex
         * @return {@link Builder}
         */
        @Override
        public Builder withEnableSpatialIndex(@Nullable Boolean theEnableSpatialIndex) {
            this.connectionParameters.compute(ENABLE_SPATIAL_INDEX.getConnectionKey(), (k, v) -> (theEnableSpatialIndex != null) ? theEnableSpatialIndex.toString() : v);
            return self();
        }

        /**
         * <p>Enable/Disable the use of memory-mapped io.</p>
         *
         * @param theMemoryMappedBuffer
         * @return {@link Builder}
         */
        @Override
        public Builder withMemoryMappedBuffer(@Nullable Boolean theMemoryMappedBuffer) {
            this.connectionParameters.compute(MEMORY_MAPPED_BUFFER.getConnectionKey(), (k, v) -> (theMemoryMappedBuffer != null) ? theMemoryMappedBuffer.toString() : v);
            return self();
        }
    }
}