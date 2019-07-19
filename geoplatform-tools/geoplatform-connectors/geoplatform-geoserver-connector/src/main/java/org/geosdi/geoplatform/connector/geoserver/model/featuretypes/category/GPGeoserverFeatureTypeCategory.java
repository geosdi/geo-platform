package org.geosdi.geoplatform.connector.geoserver.model.featuretypes.category;

import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.avaialable.GPGeoserverFeatureTypesList;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.configured.GPGeoserverEmptyFeatureTypes;
import org.geosdi.geoplatform.connector.geoserver.model.featuretypes.configured.GPGeoserverFeatureTypes;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum GPGeoserverFeatureTypeCategory implements IGPGeoserverFeatureTypeCategory {

    configured {
        /**
         * @return {@link Class<GPGeoserverFeatureTypes>}
         */
        @Override
        public Class<GPGeoserverFeatureTypes> toModel() {
            return GPGeoserverFeatureTypes.class;
        }

        /**
         * @return {@link Class<GPGeoserverEmptyFeatureTypes>}
         */
        @Override
        public Class<GPGeoserverEmptyFeatureTypes> toEmptyModel() {
            return GPGeoserverEmptyFeatureTypes.class;
        }
    }, available {
        /**
         * @return {@link Class<GPGeoserverFeatureTypesList>}
         */
        @Override
        public Class<GPGeoserverFeatureTypesList> toModel() {
            return GPGeoserverFeatureTypesList.class;
        }

        /**
         * @return {@link Class<GPGeoserverFeatureTypesList>}
         */
        @Override
        public Class<GPGeoserverFeatureTypesList> toEmptyModel() {
            return GPGeoserverFeatureTypesList.class;
        }
    }, available_with_geom {
        /**
         * @return {@link Class<GPGeoserverFeatureTypesList>}
         */
        @Override
        public Class<GPGeoserverFeatureTypesList> toModel() {
            return GPGeoserverFeatureTypesList.class;
        }

        /**
         * @return {@link Class<GPGeoserverFeatureTypesList>}
         */
        @Override
        public Class<GPGeoserverFeatureTypesList> toEmptyModel() {
            return GPGeoserverFeatureTypesList.class;
        }
    }, all {
        /**
         * @return {@link Class<GPGeoserverFeatureTypesList>}
         */
        @Override
        public Class<GPGeoserverFeatureTypesList> toModel() {
            return GPGeoserverFeatureTypesList.class;
        }

        /**
         * @return {@link Class<GPGeoserverFeatureTypesList>}
         */
        @Override
        public Class<GPGeoserverFeatureTypesList> toEmptyModel() {
            return GPGeoserverFeatureTypesList.class;
        }
    };
}