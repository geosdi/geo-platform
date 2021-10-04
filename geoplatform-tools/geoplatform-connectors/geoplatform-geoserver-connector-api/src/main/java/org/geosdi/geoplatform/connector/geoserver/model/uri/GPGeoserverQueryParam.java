package org.geosdi.geoplatform.connector.geoserver.model.uri;

import org.apache.http.client.utils.URIBuilder;

import javax.annotation.Nonnull;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface GPGeoserverQueryParam<B extends Object> extends Serializable {

    /**
     * @return {@link String}
     */
    String getKey();

    /**
     * @return {@link B}
     */
    B getValue();

    /**
     * @return {@link String}
     */
    String formatValue();

    /**
     * @param uriBuilder
     */
    default void addQueryParam(URIBuilder uriBuilder) {
        checkArgument(uriBuilder != null , "The Parameter uriBuilder must not be null");
        uriBuilder.addParameter(this.getKey(), this.formatValue());
    }

    abstract class GeoserverQueryParam<B extends Object> implements GPGeoserverQueryParam<B> {

        private static final long serialVersionUID = 6543849896113392890L;
        //
        private final String key;
        private final B value;

        /**
         * @param theKey
         * @param theValue
         */
        protected GeoserverQueryParam(@Nonnull(when = NEVER) String theKey, @Nonnull(when = NEVER) B theValue) {
            checkArgument(theKey != null && !(theKey.trim().isEmpty()), "The Parameter key must not be null");
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
        public B getValue() {
            return value;
        }
    }
}