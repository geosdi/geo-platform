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
                checkArgument((value != null) && !(value.trim().isEmpty()), "For the Key : " + key.getConnectionKey()
                        + ", the value must not be null or an empty string.");
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