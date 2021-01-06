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
package org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.geopkg;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.geopkg.GPGeoserverConnectionGeoPackageValues;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.GPGeoserverDatastoreBodyDatabaseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.Flowable.fromArray;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.geopkg.GPGeoserverConnectionGeoPackageValues.DATABASE;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.geopkg.GPGeoserverConnectionGeoPackageValues.USER;
import static org.geosdi.geoplatform.connector.geoserver.model.datastores.GeoserverDatastoreType.GEOPACKAGE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPGeopackageDatastoreBodyBuilder extends GPGeoserverDatastoreBodyDatabaseBuilder<IGPGeopackageDatastoreBodyBuilder> {

    /**
     * @param theDatabase
     * @return {@link IGPGeopackageDatastoreBodyBuilder}
     */
    IGPGeopackageDatastoreBodyBuilder withDatabase(@Nonnull(when = NEVER) File theDatabase);

    /**
     * <p>User name to login as.</p>
     *
     * @param theUser
     * @return {@link IGPGeopackageDatastoreBodyBuilder}
     */
    IGPGeopackageDatastoreBodyBuilder withUser(@Nullable String theUser);

    class GPGeopackageDatastoreBodyBuilder extends GeoserverDatastoreBodyDatabaseBuilder<IGPGeopackageDatastoreBodyBuilder> implements IGPGeopackageDatastoreBodyBuilder {

        private static final Logger logger = LoggerFactory.getLogger(GPGeopackageDatastoreBodyBuilder.class);

        static {
            defaultValues = GPGeoserverConnectionGeoPackageValues.defaultValues();
            requiredValues = GPGeoserverConnectionGeoPackageValues.requiredValues();
        }

        private static final List<IGPGeoserverConnectionKey> defaultValues;
        private static final List<IGPGeoserverConnectionKey> requiredValues;

        GPGeopackageDatastoreBodyBuilder() {
            super(GEOPACKAGE);
        }

        /**
         * @return {@link IGPGeopackageDatastoreBodyBuilder}
         */
        public static IGPGeopackageDatastoreBodyBuilder geopackageDatastoreBodyBuilder() {
            return new GPGeopackageDatastoreBodyBuilder().injectDefaultValues();
        }

        /**
         * @param theDatabase
         * @return {@link IGPGeopackageDatastoreBodyBuilder}
         */
        @Override
        public IGPGeopackageDatastoreBodyBuilder withDatabase(@Nonnull(when = NEVER) File theDatabase) {
            this.connectionParameters.compute(DATABASE.getConnectionKey(), (k, v) -> (theDatabase != null) ? theDatabase.getPath() : v);
            return self();
        }

        /**
         * <p>User name to login as.</p>
         *
         * @param theUser
         * @return {@link IGPGeopackageDatastoreBodyBuilder}
         */
        @Override
        public IGPGeopackageDatastoreBodyBuilder withUser(@Nullable String theUser) {
            this.connectionParameters.compute(USER.getConnectionKey(), (k, v) -> ((theUser != null) && !(theUser.trim().isEmpty())) ? theUser : v);
            return self();
        }

        /**
         * @return {@link IGPGeopackageDatastoreBodyBuilder}
         */
        @Override
        protected IGPGeopackageDatastoreBodyBuilder injectDefaultValues() {
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
            for (IGPGeoserverConnectionKey connectionKey : requiredValues) {
                logger.trace("@@@@@@@@@@@@@@@@@@@@@@@@@{} tries to check if : {} is present in connectionParameters.", this, connectionKey);
                String value = this.connectionParameters.get(connectionKey.getConnectionKey());
                checkArgument((value != null) && !(value.trim().isEmpty()), "For the Key : " + connectionKey.getConnectionKey()
                        + ", the value must not be null or an empty string.");
            }
        }
    }
}