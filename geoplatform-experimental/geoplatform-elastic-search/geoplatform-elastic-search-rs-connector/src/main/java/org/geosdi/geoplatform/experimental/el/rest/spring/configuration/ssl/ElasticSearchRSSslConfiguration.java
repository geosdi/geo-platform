package org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@AllArgsConstructor
@Getter
@ToString
class ElasticSearchRSSslConfiguration implements GPElasticSearchRSSslConfiguration {

    private static final long serialVersionUID = 2890968262483730771L;
    //
    private final String keyStorePath;
    private final String keyStorePassword;

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isSetSecureSocketLayer() {
        return ((this.keyStorePath != null) && !(this.keyStorePath.trim().isEmpty()));
    }
}