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