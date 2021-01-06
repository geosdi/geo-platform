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
package org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db;

import org.geosdi.geoplatform.connector.geoserver.model.datastores.GeoserverDatastoreType;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.GPGeoserverCreateDatastoreBodyBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.GPGeoserverConnectionDatabaseValues.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPDatastoreBodyBaseDatabaseBuilder<Builder extends GPDatastoreBodyBaseDatabaseBuilder<?>> extends GPGeoserverCreateDatastoreBodyBuilder<Builder> {

    /**
     * <p>Namespace prefix</p>
     *
     * @param theNamespace
     * @return {@link Builder}
     */
    Builder withNamespace(@Nonnull(when = NEVER) String theNamespace);

    /**
     * <p>Number of records read with each iteraction with the dbms</p>
     *
     * @param theFetchSize
     * @return {@link Builder}
     */
    Builder withFetchSize(@Nullable Integer theFetchSize);

    /**
     * <p>Number of records inserted in the same batch (default, 1). For optimal performance, set to 100.</p>
     *
     * @param theBatchInsertSize
     * @return {@link Builder}
     */
    Builder withBatchInsertSize(@Nullable Integer theBatchInsertSize);

    /**
     * <p>Expose primary key columns as attributes of the feature type</p>
     *
     * @param theExposePrimaryKeys
     * @return {@link Builder}
     */
    Builder withExposePrimaryKeys(@Nullable Boolean theExposePrimaryKeys);

    /**
     * <p>The optional table containing primary key structure and sequence associations. Can be expressed as ‘schema.name’ or just ‘name’</p>
     *
     * @param thePrimaryKeyMetadataTable
     * @return {@link Builder}
     */
    Builder withPrimaryKeyMetadataTable(@Nullable String thePrimaryKeyMetadataTable);

    /**
     * <p>SQL statement executed when the connection is grabbed from the pool</p>
     *
     * @param theSessionStartUpSql
     * @return {@link Builder}
     */
    Builder withSessionStartUpSql(@Nullable String theSessionStartUpSql);

    /**
     * <p>SQL statement executed when the connection is released to the pool</p>
     *
     * @param theSessionCloseUpSql
     * @return {@link Builder}
     */
    Builder withSessionCloseUpSql(@Nullable String theSessionCloseUpSql);

    /**
     * <p>Name of JDBCReaderCallbackFactory to enable on the data store</p>
     *
     * @param theCallbackFactory
     * @return {@link Builder}
     */
    Builder withCallbackFactory(@Nullable String theCallbackFactory);

    abstract class DatastoreBodyBaseDatabaseBuilder<Builder extends GPDatastoreBodyBaseDatabaseBuilder<?>> extends GPCreateDatastoreBodyBuilder<Builder> implements GPDatastoreBodyBaseDatabaseBuilder<Builder> {

        /**
         * @param theType
         */
        protected DatastoreBodyBaseDatabaseBuilder(@Nonnull(when = NEVER) GeoserverDatastoreType theType) {
            super(theType);
        }

        /**
         * <p>Namespace prefix</p>
         *
         * @param theNamespace
         * @return {@link Builder}
         */
        @Override
        public Builder withNamespace(@Nonnull(when = NEVER) String theNamespace) {
            this.connectionParameters.compute(NAMESPACE.getConnectionKey(), (k, v) -> ((theNamespace != null) && !(theNamespace.trim().isEmpty()) ? theNamespace : v));
            return self();
        }

        /**
         * <p>Number of records read with each iteraction with the dbms</p>
         *
         * @param theFetchSize
         * @return {@link Builder}
         */
        @Override
        public Builder withFetchSize(@Nullable Integer theFetchSize) {
            this.connectionParameters.compute(FETCH_SIZE.getConnectionKey(), (k, v) -> ((theFetchSize != null) && (theFetchSize > 0)) ? theFetchSize.toString() : v);
            return self();
        }

        /**
         * <p>Number of records inserted in the same batch (default, 1). For optimal performance, set to 100.</p>
         *
         * @param theBatchInsertSize
         * @return {@link Builder}
         */
        @Override
        public Builder withBatchInsertSize(@Nullable Integer theBatchInsertSize) {
            this.connectionParameters.compute(BATCH_INSERT_SIZE.getConnectionKey(), (k, v) -> ((theBatchInsertSize != null) && (theBatchInsertSize > 0)) ? theBatchInsertSize.toString() : v);
            return self();
        }

        /**
         * <p>Expose primary key columns as attributes of the feature type</p>
         *
         * @param theExposePrimaryKeys
         * @return {@link Builder}
         */
        @Override
        public Builder withExposePrimaryKeys(@Nullable Boolean theExposePrimaryKeys) {
            this.connectionParameters.compute(EXPOSE_PRIMARY_KEYS.getConnectionKey(), (k, v) -> (theExposePrimaryKeys != null) ? theExposePrimaryKeys.toString() : v);
            return self();
        }

        /**
         * <p>The optional table containing primary key structure and sequence associations. Can be expressed as ‘schema.name’ or just ‘name’</p>
         *
         * @param thePrimaryKeyMetadataTable
         * @return {@link Builder}
         */
        @Override
        public Builder withPrimaryKeyMetadataTable(@Nullable String thePrimaryKeyMetadataTable) {
            this.connectionParameters.compute(PRIMARY_KEY_METADATA_TABLE.getConnectionKey(), (k, v) -> ((thePrimaryKeyMetadataTable != null) && !(thePrimaryKeyMetadataTable.trim().isEmpty()) ? thePrimaryKeyMetadataTable : v));
            return self();
        }

        /**
         * <p>SQL statement executed when the connection is grabbed from the pool</p>
         *
         * @param theSessionStartUpSql
         * @return {@link Builder}
         */
        @Override
        public Builder withSessionStartUpSql(@Nullable String theSessionStartUpSql) {
            this.connectionParameters.compute(SESSION_STARTUP_SQL.getConnectionKey(), (k, v) -> ((theSessionStartUpSql != null) && !(theSessionStartUpSql.trim().isEmpty()) ? theSessionStartUpSql : v));
            return self();
        }

        /**
         * <p>SQL statement executed when the connection is released to the pool</p>
         *
         * @param theSessionCloseUpSql
         * @return {@link Builder}
         */
        @Override
        public Builder withSessionCloseUpSql(@Nullable String theSessionCloseUpSql) {
            this.connectionParameters.compute(SESSION_CLOSEUP_SQL.getConnectionKey(), (k, v) -> ((theSessionCloseUpSql != null) && !(theSessionCloseUpSql.trim().isEmpty())) ? theSessionCloseUpSql : v);
            return self();
        }

        /**
         * <p>Name of JDBCReaderCallbackFactory to enable on the data store</p>
         *
         * @param theCallbackFactory
         * @return {@link Builder}
         */
        @Override
        public Builder withCallbackFactory(@Nullable String theCallbackFactory) {
            this.connectionParameters.compute(CALLBACK_FACTORY.getConnectionKey(), (k, v) -> ((theCallbackFactory != null) && !(theCallbackFactory.trim().isEmpty()) ? theCallbackFactory : v));
            return self();
        }
    }
}