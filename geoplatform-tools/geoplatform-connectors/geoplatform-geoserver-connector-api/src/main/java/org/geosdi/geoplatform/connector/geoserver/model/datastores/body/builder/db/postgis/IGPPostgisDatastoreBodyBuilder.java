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
package org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.postgis.GPGeoserverConnectionPostGISValues;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.GPGeoserverDatastoreBodyDatabaseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.Flowable.fromArray;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.postgis.GPGeoserverConnectionPostGISValues.*;
import static org.geosdi.geoplatform.connector.geoserver.model.datastores.GeoserverDatastoreType.POSTGIS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPPostgisDatastoreBodyBuilder extends GPGeoserverDatastoreBodyDatabaseBuilder<IGPPostgisDatastoreBodyBuilder>, IGPBasePostgisDatastoreBodyBuilder<IGPPostgisDatastoreBodyBuilder> {

    /**
     * @param thePort
     * @return {@link IGPPostgisDatastoreBodyBuilder}
     */
    IGPPostgisDatastoreBodyBuilder withPort(@Nonnull(when = NEVER) Integer thePort);

    /**
     * <p>Creates the database if it does not exist yet</p>
     *
     * @param theCreateDatabase
     * @return {@link IGPPostgisDatastoreBodyBuilder}
     */
    IGPPostgisDatastoreBodyBuilder withCreateDatabase(@Nullable Boolean theCreateDatabase);

    /**
     * <p>Extra specifications appeneded to the CREATE DATABASE command</p>
     *
     * @param theCreateDatabaseParams
     * @return {@link IGPPostgisDatastoreBodyBuilder}
     */
    IGPPostgisDatastoreBodyBuilder withCreateDatabaseParams(@Nullable String theCreateDatabaseParams);

    /**
     * <p>Maximum number of prepared statements kept open and cached for each connection in the pool. Set to 0 to have unbounded caching, to -1 to disable caching.</p>
     *
     * @param theMaxOpenPreparedStatements
     * @return {@link IGPPostgisDatastoreBodyBuilder}
     */
    IGPPostgisDatastoreBodyBuilder withMaxOpenPreparedStatements(@Nullable Integer theMaxOpenPreparedStatements);

    /**
     * <p>Null or Empty Strings not permitted. Default Value is : localhost</p>
     *
     * @param theHost
     * @return {@link IGPPostgisDatastoreBodyBuilder}
     */
    IGPPostgisDatastoreBodyBuilder withHost(@Nonnull(when = NEVER) String theHost);

    /**
     * @param theDatabase
     * @return {@link IGPPostgisDatastoreBodyBuilder}
     */
    IGPPostgisDatastoreBodyBuilder withDatabase(@Nullable String theDatabase);

    /**
     * <p>Periodically test the connections are still valid also while idle in the pool.</p>
     *
     * @param theTestWhileIdle
     * @return {@link IGPPostgisDatastoreBodyBuilder}
     */
    IGPPostgisDatastoreBodyBuilder withTestWhileIdle(@Nullable Boolean theTestWhileIdle);

    /**
     * <p>User name to login as.</p>
     *
     * @param theUser
     * @return {@link IGPPostgisDatastoreBodyBuilder}
     */
    IGPPostgisDatastoreBodyBuilder withUser(@Nonnull(when = NEVER) String theUser);

    class GPPostgisDatastoreBodyBuilder extends GeoserverDatastoreBodyDatabaseBuilder<IGPPostgisDatastoreBodyBuilder> implements IGPPostgisDatastoreBodyBuilder {

        private static final Logger logger = LoggerFactory.getLogger(GPPostgisDatastoreBodyBuilder.class);

        static {
            defaultValues = GPGeoserverConnectionPostGISValues.defaultValues();
            requiredValues = GPGeoserverConnectionPostGISValues.requiredValues();
        }

        private static final List<IGPGeoserverConnectionKey> defaultValues;
        private static final List<IGPGeoserverConnectionKey> requiredValues;

        GPPostgisDatastoreBodyBuilder() {
            super(POSTGIS);
        }

        /**
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        public static IGPPostgisDatastoreBodyBuilder postgisDatastoreBodyBuilder() {
            return new GPPostgisDatastoreBodyBuilder().injectDefaultValues();
        }

        /**
         * @param thePort
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withPort(@Nonnull(when = NEVER) Integer thePort) {
            this.connectionParameters.compute(PORT.getConnectionKey(), (k, v) -> ((thePort != null) && (thePort > 0)) ? thePort.toString() : v);
            return self();
        }

        /**
         * <p>Support on the fly geometry simplification</p>
         *
         * @param theSupportOnTheFlyGeometrySemplification
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withSupportOnTheFlyGeometrySemplification(@Nullable Boolean theSupportOnTheFlyGeometrySemplification) {
            this.connectionParameters.compute(SUPPORT_ON_THE_FLY_GEOMETRY_SEMPLIFICATION.getConnectionKey(), (k, v) -> (theSupportOnTheFlyGeometrySemplification != null) ? theSupportOnTheFlyGeometrySemplification.toString() : v);
            return self();
        }

        /**
         * <p>Creates the database if it does not exist yet</p>
         *
         * @param theCreateDatabase
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withCreateDatabase(@Nullable Boolean theCreateDatabase) {
            this.connectionParameters.compute(CREATE_DATABASE.getConnectionKey(), (k, v) -> (theCreateDatabase != null) ? theCreateDatabase.toString() : v);
            return self();
        }

        /**
         * <p>Extra specifications appeneded to the CREATE DATABASE command</p>
         *
         * @param theCreateDatabaseParams
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withCreateDatabaseParams(@Nullable String theCreateDatabaseParams) {
            this.connectionParameters.compute(CREATE_DATABASE_PARAMS.getConnectionKey(), (k, v) -> ((theCreateDatabaseParams != null) && !(theCreateDatabaseParams.trim().isEmpty())) ? theCreateDatabaseParams : v);
            return self();
        }

        /**
         * <p>Maximum number of prepared statements kept open and cached for each connection in the pool. Set to 0 to have unbounded caching, to -1 to disable caching.</p>
         *
         * @param theMaxOpenPreparedStatements
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withMaxOpenPreparedStatements(@Nullable Integer theMaxOpenPreparedStatements) {
            this.connectionParameters.compute(MAX_OPEN_PREPARED_STATEMENTS.getConnectionKey(), (k, v) -> ((theMaxOpenPreparedStatements != null) && (theMaxOpenPreparedStatements > 0)) ? theMaxOpenPreparedStatements.toString() : v);
            return self();
        }

        /**
         * <p>Set to true to have a set of filter functions be translated directly in SQL. Due to differences in the type
         * systems the result might not be the same as evaluating them in memory, including the SQL failing with errors
         * while the in memory version works fine. However this allows to push more of the filter into the database,
         * increasing performance.the postgis table.
         * </p>
         *
         * @param theEncodeFunctions
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withEncodeFunctions(@Nullable Boolean theEncodeFunctions) {
            this.connectionParameters.compute(ENCODE_FUNCTIONS.getConnectionKey(), (k, v) -> (theEncodeFunctions != null) ? theEncodeFunctions.toString() : v);
            return self();
        }

        /**
         * @param theHost
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withHost(@Nonnull(when = NEVER) String theHost) {
            this.connectionParameters.compute(HOST.getConnectionKey(), (k, v) -> ((theHost != null) && !(theHost.trim().isEmpty())) ? theHost : v);
            return self();
        }

        /**
         * <p>Perform only primary filter on bbox.</p>
         *
         * @param theLooseBbox
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withLooseBbox(@Nullable Boolean theLooseBbox) {
            this.connectionParameters.compute(LOOSE_BBOX.getConnectionKey(), (k, v) -> (theLooseBbox != null) ? theLooseBbox.toString() : v);
            return self();
        }

        /**
         * <p>Use the spatial index information to quickly get an estimate of the data bounds.</p>
         *
         * @param theEstimatedExtends
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withEstimatedExtends(@Nullable Boolean theEstimatedExtends) {
            this.connectionParameters.compute(ESTIMATED_EXTENDS.getConnectionKey(), (k, v) -> (theEstimatedExtends != null) ? theEstimatedExtends.toString() : v);
            return self();
        }

        /**
         * @param theDatabase
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withDatabase(@Nullable String theDatabase) {
            this.connectionParameters.compute(DATABASE.getConnectionKey(), (k, v) -> ((theDatabase != null) && !(theDatabase.trim().isEmpty())) ? theDatabase : v);
            return self();
        }

        /**
         * <p>Periodically test the connections are still valid also while idle in the pool.</p>
         *
         * @param theTestWhileIdle
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withTestWhileIdle(@Nullable Boolean theTestWhileIdle) {
            this.connectionParameters.compute(TEST_WHILE_IDLE.getConnectionKey(), (k, v) -> (theTestWhileIdle != null) ? theTestWhileIdle.toString() : v);
            return self();
        }

        /**
         * <p>Use prepared statements.</p>
         *
         * @param thePreparedStatements
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withPreparedStatements(@Nullable Boolean thePreparedStatements) {
            this.connectionParameters.compute(PREPARED_STATEMENTS.getConnectionKey(), (k, v) -> (thePreparedStatements != null) ? thePreparedStatements.toString() : v);
            return self();
        }

        /**
         * <p>The Schema to use. Default value is : public.</p>
         *
         * @param theSchema
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withSchema(@Nullable String theSchema) {
            this.connectionParameters.compute(SCHEMA.getConnectionKey(), (k, v) -> ((theSchema != null) && !(theSchema.trim().isEmpty())) ? theSchema : v);
            return self();
        }

        /**
         * <p>User name to login as.</p>
         *
         * @param theUser
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        public IGPPostgisDatastoreBodyBuilder withUser(@Nonnull(when = NEVER) String theUser) {
            this.connectionParameters.compute(USER.getConnectionKey(), (k, v) -> ((theUser != null) && !(theUser.trim().isEmpty())) ? theUser : v);
            return self();
        }

        /**
         * @return {@link IGPPostgisDatastoreBodyBuilder}
         */
        @Override
        protected final IGPPostgisDatastoreBodyBuilder injectDefaultValues() {
            fromArray(defaultValues.stream().toArray(IGPGeoserverConnectionKey[]::new))
                    .filter(Objects::nonNull)
                    .blockingSubscribe(v -> this.connectionParameters.put(v.getConnectionKey(), v.getDefaultValue().toString()), Throwable::printStackTrace);
            return self();
        }

        /**
         * @throws Exception
         */
        @Override
        protected void checkConnectionParameters() throws Exception {
            for (IGPGeoserverConnectionKey key : requiredValues) {
                logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@{} tries to check if : {} is present in connectionParameters.", this, key);
                String value = this.connectionParameters.get(key.getConnectionKey());
                checkArgument((value != null) && !(value.trim().isEmpty()), "For the Key : " + key.getConnectionKey()
                        + ", the value must not be null or an empty string.");
            }
        }
    }
}