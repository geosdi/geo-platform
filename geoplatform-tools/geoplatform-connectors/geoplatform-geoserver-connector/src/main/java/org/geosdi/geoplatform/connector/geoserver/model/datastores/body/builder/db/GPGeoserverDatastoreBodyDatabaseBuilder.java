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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.GPGeoserverConnectionDatabaseValues.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverDatastoreBodyDatabaseBuilder<Builder extends GPGeoserverDatastoreBodyDatabaseBuilder<?>> extends GPDatastoreBodyBaseDatabaseBuilder<Builder> {

    /**
     * <p>Number of connections checked by the idle connection evictor for each of its runs (defaults to 3)</p>
     *
     * @param theEvictionTestsForRun
     * @return {@link Builder}
     */
    Builder withEvictionTestsForRun(@Nullable Integer theEvictionTestsForRun);

    /**
     * <p>Number of seconds the connection pool will wait before timing out attempting to get a new connection (default, 20 seconds)</p>
     *
     * @param theConenctionTimeout
     * @return {@link Builder}
     */
    Builder withConnectionTimeout(@Nullable Integer theConenctionTimeout);

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

    abstract class GeoserverDatastoreBodyDatabaseBuilder<Builder extends GPGeoserverDatastoreBodyDatabaseBuilder<?>> extends DatastoreBodyBaseDatabaseBuilder<Builder> implements GPGeoserverDatastoreBodyDatabaseBuilder<Builder> {

        /**
         * @param theType
         */
        protected GeoserverDatastoreBodyDatabaseBuilder(@Nonnull(when = NEVER) GeoserverDatastoreType theType) {
            super(theType);
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
    }
}