package org.geosdi.geoplatform.services.response;

import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;

import static javax.annotation.meta.When.NEVER;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWMSGetFeatureInfoResponse extends Serializable {

    /**
     * @return {@link List<Object>}
     */
    List<Object> getFeatures();

    /**
     * @param theFeature
     */
    void addFeature(@Nonnull(when = NEVER) Object theFeature);

    interface GPWMSGetFeatureInfoObjectResponse extends Serializable {

        /**
         * @return {@link Object}
         */
        Object getResponse();

        /**
         * @return {@link String}
         */
        String getLayerName();

        /**
         * @param theResponse
         * @param theLayerName
         * @return {@link GPWMSGetFeatureInfoObjectResponse}
         */
        static GPWMSGetFeatureInfoObjectResponse toResponse(Object theResponse, String theLayerName) {
            return new WMSGetFeatureInfoObjectResponse(theResponse, theLayerName);
        }

        @ToString
        @Getter
        @Immutable
        @XmlAccessorType(FIELD)
        class WMSGetFeatureInfoObjectResponse implements GPWMSGetFeatureInfoObjectResponse {

            private static final long serialVersionUID = -985006208177418887L;
            //
            private final Object response;
            private final String layerName;

            /**
             * @param theResponse
             * @param theLayerName
             */
            WMSGetFeatureInfoObjectResponse(Object theResponse, String theLayerName) {
                this.response = theResponse;
                this.layerName = theLayerName;
            }
        }
    }
}