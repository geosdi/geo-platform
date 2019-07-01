package org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.file.shape;

import org.geosdi.geoplatform.connector.geoserver.model.connection.key.IGPGeoserverConnectionKey;
import org.geosdi.geoplatform.connector.geoserver.model.connection.key.file.shape.GPGeoserverConnectionShapeFilesDirValues;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder.file.GPGeoserverDatastoreBodyFileBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static io.reactivex.Flowable.fromArray;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.connection.key.file.shape.GPGeoserverConnectionShapeFilesDirValues.URL;
import static org.geosdi.geoplatform.connector.geoserver.model.datastores.GeoserverDatastoreType.SHAPEFILES_DIRECTORY;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPShapeFilesDirDatastoreBodyBuilder extends GPGeoserverDatastoreBodyFileBuilder<IGPShapeFilesDirDatastoreBodyBuilder> {

    /**
     * @param theUrl
     * @return {@link IGPShapeFilesDirDatastoreBodyBuilder}
     */
    IGPShapeFilesDirDatastoreBodyBuilder withUrl(@Nonnull(when = NEVER) java.net.URL theUrl);

    class GPShapeFilesDirDatastoreBodyBuilder extends GeoserverDatastoreBodyFileBuilder<IGPShapeFilesDirDatastoreBodyBuilder> implements IGPShapeFilesDirDatastoreBodyBuilder {

        private static final Logger logger = LoggerFactory.getLogger(GPShapeFilesDirDatastoreBodyBuilder.class);

        static {
            defaultValues = GPGeoserverConnectionShapeFilesDirValues.defaultValues();
            requiredValues = GPGeoserverConnectionShapeFilesDirValues.requiredValues();
        }

        private static final List<IGPGeoserverConnectionKey> defaultValues;
        private static final List<IGPGeoserverConnectionKey> requiredValues;

        GPShapeFilesDirDatastoreBodyBuilder() {
            super(SHAPEFILES_DIRECTORY);
        }

        /**
         * @return {@link IGPShapeFilesDirDatastoreBodyBuilder}
         */
        public static IGPShapeFilesDirDatastoreBodyBuilder shapeFilesDirDatastoreBodyBuilder() {
            return new GPShapeFilesDirDatastoreBodyBuilder().injectDefaultValues();
        }

        /**
         * @param theUrl
         * @return {@link IGPShapeFilesDirDatastoreBodyBuilder}
         */
        @Override
        public IGPShapeFilesDirDatastoreBodyBuilder withUrl(@Nonnull(when = NEVER) java.net.URL theUrl) {
            this.connectionParameters.compute(URL.getConnectionKey(), (k, v) -> (theUrl != null) ? theUrl.toString() : v);
            return self();
        }

        /**
         * @return {@link IGPShapeFilesDirDatastoreBodyBuilder}
         */
        @Override
        protected IGPShapeFilesDirDatastoreBodyBuilder injectDefaultValues() {
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