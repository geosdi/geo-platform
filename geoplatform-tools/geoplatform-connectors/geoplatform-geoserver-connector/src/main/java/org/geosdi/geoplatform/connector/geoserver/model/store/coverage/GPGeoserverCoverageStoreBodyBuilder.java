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
package org.geosdi.geoplatform.connector.geoserver.model.store.coverage;

import javax.annotation.Nullable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGeoserverCoverageStoreBodyBuilder<Builder extends GPGeoserverCoverageStoreBodyBuilder> {

    /**
     * @param theCoverage
     * @return {@link Builder}
     */
    Builder withCoverage(String theCoverage);

    /**
     * @param theDescription
     * @return {@link Builder}
     */
    Builder withDescription(@Nullable String theDescription);

    /**
     * @param theType
     * @param <Type>
     * @return {@link Builder}
     */
    <Type extends GPCoverageStoreType> Builder withType(Type theType);

    /**
     * @param theEnabled
     * @return {@link Builder}
     */
    Builder withEnabled(Boolean theEnabled);

    /**
     * @param theUrl
     * @return {@link Builder}
     */
    Builder withUrl(String theUrl);

    /**
     * @param theWorkspace
     * @return {@link Builder}
     */
    Builder withWorkspace(String theWorkspace);

    /**
     * @return {@link IGPGeoserverCoverageStoreBody}
     * @throws Exception
     */
    IGPGeoserverCoverageStoreBody build() throws Exception;

    abstract class BaseCoverageStoreBuilder<Builder extends GPGeoserverCoverageStoreBodyBuilder> implements GPGeoserverCoverageStoreBodyBuilder<Builder> {

        protected String coverage;
        protected String workspace;
        protected String description;
        protected Boolean enabled;
        protected GPCoverageStoreType type;
        protected String url;

        BaseCoverageStoreBuilder() {
        }

        /**
         * @param theCoverage
         * @return {@link Builder}
         */
        @Override
        public Builder withCoverage(String theCoverage) {
            this.coverage = theCoverage;
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
         * @param theType
         * @return {@link Builder}
         */
        @Override
        public <Type extends GPCoverageStoreType> Builder withType(Type theType) {
            this.type = theType;
            return self();
        }

        /**
         * @param theEnabled
         * @return {@link Builder}
         */
        @Override
        public Builder withEnabled(Boolean theEnabled) {
            this.enabled = theEnabled;
            return self();
        }

        /**
         * @param theUrl
         * @return {@link Builder}
         */
        @Override
        public Builder withUrl(String theUrl) {
            return null;
        }

        /**
         * @param theWorkspace
         * @return {@link Builder}
         */
        @Override
        public Builder withWorkspace(String theWorkspace) {
            return null;
        }

        /**
         * @return {@link Builder}
         */
        protected abstract Builder self();
    }
}