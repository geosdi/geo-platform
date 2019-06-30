package org.geosdi.geoplatform.connector.geoserver.model.datastores.body.builder;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.GeoserverDatastoreType;
import org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.geoserver.model.datastores.body.IGPGeoserverCreateDatastoreBody.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverCreateDatastoreBodyBuilder<Builder extends GPGeoserverCreateDatastoreBodyBuilder<?>> extends Serializable {

    /**
     * @param theName
     * @return {@link Builder}
     */
    Builder withName(@Nonnull(when = NEVER) String theName);

    /**
     * @param theDescription
     * @return {@link Builder}
     */
    Builder withDescription(@Nullable String theDescription);

    /**
     * @param theEnabled
     * @return {@link Builder}
     */
    Builder withEnabled(@Nullable Boolean theEnabled);

    /**
     * @param <CreateDatastoreBody>
     * @return {@link CreateDatastoreBody}
     * @throws Exception
     */
    <CreateDatastoreBody extends IGPGeoserverCreateDatastoreBody> CreateDatastoreBody build() throws Exception;

    abstract class GPCreateDatastoreBodyBuilder<Builder extends GPGeoserverCreateDatastoreBodyBuilder<?>> implements GPGeoserverCreateDatastoreBodyBuilder<Builder> {

        protected final GeoserverDatastoreType type;
        protected String name;
        protected String description;
        protected Boolean enabled;
        protected final Map<String, String> connectionParameters = Maps.newHashMap();

        /**
         * @param theType
         */
        protected GPCreateDatastoreBodyBuilder(@Nonnull(when = NEVER) GeoserverDatastoreType theType) {
            checkArgument(theType != null, "The Parameter type must not be null.");
            this.type = theType;
        }

        /**
         * @param theName
         * @return {@link Builder}
         */
        @Override
        public Builder withName(@Nonnull(when = NEVER) String theName) {
            this.name = theName;
            return self();
        }

        /**
         * @param theDescription
         * @return {@link Builder}
         */
        @Override
        public Builder withDescription(@Nullable String theDescription) {
            this.description = theDescription;
            return self();
        }

        /**
         * @param theEnabled
         * @return {@link Builder}
         */
        @Override
        public Builder withEnabled(@Nullable Boolean theEnabled) {
            this.enabled = theEnabled;
            return self();
        }

        /**
         * @return {@link IGPGeoserverCreateDatastoreBody}
         * @throws Exception
         */
        @Override
        public final IGPGeoserverCreateDatastoreBody build() throws Exception {
            checkArgument((this.name != null) && !(this.name.trim().isEmpty()), "The Parameter name must not be null or an empty string.");
            checkConnectionParameters();
            return of(this.name, this.description, this.enabled, this.connectionParameters);
        }

        /**
         * @return {@link Builder}
         */
        protected Builder self() {
            return (Builder) this;
        }

        /**
         * @throws Exception
         */
        protected abstract void checkConnectionParameters() throws Exception;

        /**
         * @return {@link Builder}
         */
        protected abstract Builder injectDefaultValues();

        @Override
        public String toString() {
            return getClass().getSimpleName() + "{" +
                    "type = " + type +
                    '}';
        }
    }
}