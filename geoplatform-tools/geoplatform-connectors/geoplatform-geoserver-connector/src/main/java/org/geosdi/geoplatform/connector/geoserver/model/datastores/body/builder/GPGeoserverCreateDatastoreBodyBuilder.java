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