package org.geosdi.geoplatform.services.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat;

import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML;
import static org.geosdi.geoplatform.connector.server.request.WMSFeatureInfoFormat.GML_AS_STORE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public enum WMSGetFeatureInfoResponseFormat implements GPWMSGetFeatureInfoResponseFormat {

    GEOJSON {
        /**
         * @return {@link WMSFeatureInfoFormat}
         */
        @Override
        public WMSFeatureInfoFormat toWMSFeatureInfoFormat() {
            return GML;
        }

        /**
         * @return {@link GPWMSGetFeatureInfoReponseErrorStrategy}
         */
        @Override
        public GPWMSGetFeatureInfoReponseErrorStrategy toWMSGetFeatureInfoResponseErrorStrategy() {
            return new WMSGeoJsonResponseErrorStrategy();
        }
    },
    FEATURE_STORE {
        /**
         * @return {@link WMSFeatureInfoFormat}
         */
        @Override
        public WMSFeatureInfoFormat toWMSFeatureInfoFormat() {
            return GML_AS_STORE;
        }

        /**
         * @return {@link GPWMSGetFeatureInfoReponseErrorStrategy}
         */
        @Override
        public GPWMSGetFeatureInfoReponseErrorStrategy toWMSGetFeatureInfoResponseErrorStrategy() {
            return new WMSFeatureStoreResponseErrorStrategy();
        }
    };

    /**
     * @param format
     * @return {@link WMSGetFeatureInfoResponseFormat}
     */
    @JsonCreator
    public static WMSGetFeatureInfoResponseFormat forFormat(String format) {
        Optional<WMSGetFeatureInfoResponseFormat> optional = stream(WMSGetFeatureInfoResponseFormat.values())
                .filter(v -> ((format != null) && !(format.trim().isEmpty()))
                        ? v.name().equalsIgnoreCase(format) : FALSE)
                .findFirst();
        return ((optional != null) && !(optional.equals(empty()))) ? optional.get() : null;
    }
}