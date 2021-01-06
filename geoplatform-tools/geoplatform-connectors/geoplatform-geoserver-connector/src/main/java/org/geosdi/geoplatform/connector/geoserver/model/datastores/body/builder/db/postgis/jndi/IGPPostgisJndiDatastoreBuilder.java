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
package org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis.jndi;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.db.postgis.IGPBasePostgisDatastoreBodyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.Flowable.fromArray;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.db.postgis.jndi.GPGeoserverConnectionPostGISJndiValues.*;
import static org.geosdi.geoplatform.connector.geoserver.model.datastores.GeoserverDatastoreType.POSTGIS_JNDI;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPPostgisJndiDatastoreBuilder extends IGPBasePostgisDatastoreBodyBuilder<IGPPostgisJndiDatastoreBuilder> {

    /**
     * @param theJndiReferenceName
     * @return {@link IGPPostgisJndiDatastoreBuilder}
     */
    IGPPostgisJndiDatastoreBuilder withJndiReferenceName(@Nonnull(when = NEVER) String theJndiReferenceName);

    class GPPostgisJndiDatastoreBuilder extends GPBasePostgisDatastoreBodyBuilder<IGPPostgisJndiDatastoreBuilder> implements IGPPostgisJndiDatastoreBuilder {

        private static final Logger logger = LoggerFactory.getLogger(GPPostgisJndiDatastoreBuilder.class);

        static {
            requiredValues = requiredValues();
            defaultValues = defaultValues();
        }

        private static final List<IGPGeoserverConnectionKey> requiredValues;
        private static final List<IGPGeoserverConnectionKey> defaultValues;

        GPPostgisJndiDatastoreBuilder() {
            super(POSTGIS_JNDI);
        }

        /**
         * @return {@link IGPPostgisJndiDatastoreBuilder}
         */
        public static IGPPostgisJndiDatastoreBuilder postgisJndiDatastoreBuilder() {
            return new GPPostgisJndiDatastoreBuilder().injectDefaultValues();
        }

        /**
         * @param theJndiReferenceName
         * @return {@link IGPPostgisJndiDatastoreBuilder}
         */
        @Override
        public IGPPostgisJndiDatastoreBuilder withJndiReferenceName(@Nonnull(when = NEVER) String theJndiReferenceName) {
            this.connectionParameters.compute(JNDI_REFERENCE_MAME.getConnectionKey(), (k, v) -> ((theJndiReferenceName != null) && !(theJndiReferenceName.trim().isEmpty())) ? theJndiReferenceName : v);
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
                checkArgument((value != null) && !(value.trim().isEmpty()), "For the Key : " + key.getConnectionKey() + ", the value must not be null or an empty string.");
            }
        }

        /**
         * @return {@link IGPPostgisJndiDatastoreBuilder}
         */
        @Override
        protected IGPPostgisJndiDatastoreBuilder injectDefaultValues() {
            fromArray(defaultValues.stream().toArray(IGPGeoserverConnectionKey[]::new))
                    .filter(Objects::nonNull)
                    .blockingSubscribe(v -> this.connectionParameters.put(v.getConnectionKey(), v.getDefaultValue().toString()), Throwable::printStackTrace);
            return self();
        }
    }
}