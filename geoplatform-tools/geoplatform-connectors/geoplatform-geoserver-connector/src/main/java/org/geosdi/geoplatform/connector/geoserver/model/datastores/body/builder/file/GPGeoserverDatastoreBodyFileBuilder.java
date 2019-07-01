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