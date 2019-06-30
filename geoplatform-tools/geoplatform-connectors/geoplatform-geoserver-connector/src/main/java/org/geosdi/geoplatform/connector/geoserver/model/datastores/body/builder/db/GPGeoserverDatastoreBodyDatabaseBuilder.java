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
public interface GPGeoserverDatastoreBodyDatabaseBuilder<Builder extends GPGeoserverDatastoreBodyDatabaseBuilder<?>> extends GPGeoserverCreateDatastoreBodyBuilder<Builder> {

    /**
     * <p>The optional table containing primary key structure and sequence associations. Can be expressed as ‘schema.name’ or just ‘name’</p>
     *
     * @param thePrimaryKeyMetadataTable
     * @return {@link Builder}
     */
    Builder withPrimaryKeyMetadataTable(@Nullable String thePrimaryKeyMetadataTable);

    /**
     * <p>Name of JDBCReaderCallbackFactory to enable on the data store</p>
     *
     * @param theCallbackFactory
     * @return {@link Builder}
     */
    Builder withCallbackFactory(@Nullable String theCallbackFactory);

    /**
     * <p>Number of connections checked by the idle connection evictor for each of its runs (defaults to 3)</p>
     *
     * @param theEvictionTestsForRun
     * @return {@link Builder}
     */
    Builder withEvictionTestsForRun(@Nullable Integer theEvictionTestsForRun);

    /**
     * <p>Number of records inserted in the same batch (default, 1). For optimal performance, set to 100.</p>
     *
     * @param theBatchInsertSize
     * @return {@link Builder}
     */
    Builder withBatchInsertSize(@Nullable Integer theBatchInsertSize);

    /**
     * <p>Number of records read with each iteraction with the dbms</p>
     *
     * @param theFetchSize
     * @return {@link Builder}
     */
    Builder withFetchSize(@Nullable Integer theFetchSize);

    /**
     * <p>Number of seconds the connection pool will wait before timing out attempting to get a new connection (default, 20 seconds)</p>
     *
     * @param theConenctionTimeout
     * @return {@link Builder}
     */
    Builder withConnectionTimeout(@Nullable Integer theConenctionTimeout);

    /**
     * <p>Namespace prefix</p>
     *
     * @param theNamespace
     * @return {@link Builder}
     */
    Builder withNamespace(@Nonnull(when = NEVER) String theNamespace);

    /**
     * <p>Maximum number of open connections</p>
     *
     * @param theMaxConnections
     * @return {@link Builder}
     */
    Builder withMaxConnections(@Nullable Integer theMaxConnections);

    /**
     * <p>Periodically test the connections are still valid also while idle in the pool</p>
     *
     * @param theTestWithIdle
     * @return {@link Builder}
     */
    Builder withTestWithIdle(@Nullable Boolean theTestWithIdle);

    /**
     * <p>Number of seconds a connection needs to stay idle for the evictor to consider closing it</p>
     *
     * @param theMaxConnectionIdleTime
     * @return {@link Builder}
     */
    Builder withMaxConnectionIdleTime(@Nullable Integer theMaxConnectionIdleTime);

    /**
     * <p>SQL statement executed when the connection is grabbed from the pool</p>
     *
     * @param theSessionStartUpSql
     * @return {@link Builder}
     */
    Builder withSessionStartUpSql(@Nullable String theSessionStartUpSql);

    /**
     * <p>Check connection is alive before using it</p>
     *
     * @param theValidateConnections
     * @return {@link Builder}
     */
    Builder withValidateConnections(@Nullable Boolean theValidateConnections);

    /**
     * <p>Password used to login</p>
     *
     * @param thePassword
     * @return {@link Builder}
     */
    Builder withPassword(@Nullable String thePassword);

    /**
     * <p>Expose primary key columns as attributes of the feature type</p>
     *
     * @param theExposePrimaryKeys
     * @return {@link Builder}
     */
    Builder withExposePrimaryKeys(@Nullable Boolean theExposePrimaryKeys);

    /**
     * <p>Minimum number of pooled connection</p>
     *
     * @param theMinConnections
     * @return {@link Builder}
     */
    Builder withMinConnections(@Nullable Integer theMinConnections);

    /**
     * <p>Number of seconds between idle object evitor runs (default, 300 seconds)</p>
     *
     * @param theEvictorRunPeriodicy
     * @return {@link Builder}
     */
    Builder withEvictorRunPeriodicy(@Nullable Integer theEvictorRunPeriodicy);

    /**
     * <p>SQL statement executed when the connection is released to the pool</p>
     *
     * @param theSessionCloseUpSql
     * @return {@link Builder}
     */
    Builder withSessionCloseUpSql(@Nullable String theSessionCloseUpSql);

    abstract class GeoserverDatastoreBodyDatabaseBuilder<Builder extends GPGeoserverDatastoreBodyDatabaseBuilder<?>> extends GPCreateDatastoreBodyBuilder<Builder> implements GPGeoserverDatastoreBodyDatabaseBuilder<Builder> {

        /**
         * @param theType
         */
        protected GeoserverDatastoreBodyDatabaseBuilder(@Nonnull(when = NEVER) GeoserverDatastoreType theType) {
            super(theType);
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

        /**
         * <p>Number of connections checked by the idle connection evictor for each of its runs (defaults to 3)</p>
         *
         * @param theEvictionTestsForRun
         * @return {@link Builder}
         */
        @Override
        public Builder withEvictionTestsForRun(@Nullable Integer theEvictionTestsForRun) {
            this.connectionParameters.compute(EVICTION_TESTS_FOR_RUN.getConnectionKey(), (k, v) -> ((theEvictionTestsForRun != null) && (theEvictionTestsForRun > 0)) ? theEvictionTestsForRun.toString() : v);
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
         * <p>Number of seconds the connection pool will wait before timing out attempting to get a new connection (default, 20 seconds)</p>
         *
         * @param theConenctionTimeout
         * @return {@link Builder}
         */
        @Override
        public Builder withConnectionTimeout(@Nullable Integer theConenctionTimeout) {
            this.connectionParameters.compute(CONNECTION_TIMEOUT.getConnectionKey(), (k, v) -> ((theConenctionTimeout != null) && (theConenctionTimeout > 0)) ? theConenctionTimeout.toString() : v);
            return self();
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
         * <p>Maximum number of open connections</p>
         *
         * @param theMaxConnections
         * @return {@link Builder}
         */
        @Override
        public Builder withMaxConnections(@Nullable Integer theMaxConnections) {
            this.connectionParameters.compute(MAX_CONNECTIONS.getConnectionKey(), (k, v) -> ((theMaxConnections != null) && (theMaxConnections > 0)) ? theMaxConnections.toString() : v);
            return self();
        }

        /**
         * <p>Periodically test the connections are still valid also while idle in the pool</p>
         *
         * @param theTestWithIdle
         * @return {@link Builder}
         */
        @Override
        public Builder withTestWithIdle(@Nullable Boolean theTestWithIdle) {
            this.connectionParameters.compute(TEST_WITH_IDLE.getConnectionKey(), (k, v) -> (theTestWithIdle != null) ? theTestWithIdle.toString() : v);
            return self();
        }

        /**
         * <p>Number of seconds a connection needs to stay idle for the evictor to consider closing it</p>
         *
         * @param theMaxConnectionIdleTime
         * @return {@link Builder}
         */
        @Override
        public Builder withMaxConnectionIdleTime(@Nullable Integer theMaxConnectionIdleTime) {
            this.connectionParameters.compute(MAX_CONNECTION_IDLE_TIME.getConnectionKey(), (k, v) -> ((theMaxConnectionIdleTime != null) && (theMaxConnectionIdleTime > 0)) ? theMaxConnectionIdleTime.toString() : v);
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
         * <p>Check connection is alive before using it</p>
         *
         * @param theValidateConnections
         * @return {@link Builder}
         */
        @Override
        public Builder withValidateConnections(@Nullable Boolean theValidateConnections) {
            this.connectionParameters.compute(VALIDATE_CONNECTIONS.getConnectionKey(), (k, v) -> (theValidateConnections != null) ? theValidateConnections.toString() : v);
            return self();
        }

        /**
         * <p>Password used to login</p>
         *
         * @param thePassword
         * @return {@link Builder}
         */
        @Override
        public Builder withPassword(@Nullable String thePassword) {
            this.connectionParameters.compute(PASSWD.getConnectionKey(), (k, v) -> ((thePassword != null) && !(thePassword.trim().isEmpty()) ? thePassword : v));
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
         * <p>Minimum number of pooled connection</p>
         *
         * @param theMinConnections
         * @return {@link Builder}
         */
        @Override
        public Builder withMinConnections(@Nullable Integer theMinConnections) {
            this.connectionParameters.compute(MIN_CONNECTIONS.getConnectionKey(), (k, v) -> ((theMinConnections != null) && (theMinConnections > 0) ? theMinConnections.toString() : v));
            return self();
        }

        /**
         * <p>Number of seconds between idle object evitor runs (default, 300 seconds)</p>
         *
         * @param theEvictorRunPeriodicy
         * @return {@link Builder}
         */
        @Override
        public Builder withEvictorRunPeriodicy(@Nullable Integer theEvictorRunPeriodicy) {
            this.connectionParameters.compute(EVICTOR_RUN_PERIODICY.getConnectionKey(), (k, v) -> ((theEvictorRunPeriodicy != null) && (theEvictorRunPeriodicy > 0)) ? theEvictorRunPeriodicy.toString() : v);
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
    }
}