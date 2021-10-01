package org.geosdi.geoplatform.connector.geoserver.model.uri;

import org.apache.hc.core5.net.URIBuilder;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GPGeoserverQueryParam extends Serializable {

    /**
     * @return {@link String}
     */
    String getKey();

    /**
     * @return {@link String}
     */
    String getValue();

    default  void addQueryParam(URIBuilder uriBuilder) {
        checkArgument(uriBuilder != null , "The Parameter uriBuilder must not be null");
        System.out.println("##################"+this.getKey());
        System.out.println(this.getValue());
        uriBuilder.addParameter(this.getKey(), this.getValue());
    }

    abstract class GeoserverQueryParam implements GPGeoserverQueryParam {

        private static final long serialVersionUID = 6543849896113392890L;
        //
        private final String key;
        private final String value;

        public GeoserverQueryParam(@Nonnull(when = NEVER) String theKey, @Nonnull(when = NEVER) String theValue) {
            checkArgument(theKey != null && !(theKey.trim().isEmpty()), "The Parameter key must not be null");
            checkArgument(theValue != null && !(theValue.trim().isEmpty()), "The Parameter value must not be null");
            this.key = theKey;
            this.value = theValue;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getKey() {
            return key;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getValue() {
            return value;
        }
    }
}