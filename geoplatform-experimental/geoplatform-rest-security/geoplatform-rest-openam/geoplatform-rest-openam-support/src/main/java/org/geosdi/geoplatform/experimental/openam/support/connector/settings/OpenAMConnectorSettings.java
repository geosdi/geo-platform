package org.geosdi.geoplatform.experimental.openam.support.connector.settings;

import com.google.common.base.Preconditions;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.geosdi.geoplatform.experimental.rs.security.connector.settings.GPConnectorSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
@Component(value = "openAMConnectorSettings")
public class OpenAMConnectorSettings implements GPConnectorSettings {

    private static final long serialVersionUID = 6121921445780578968L;
    //
    @Value("openAMConnectorConfigurator{openam.connector.scheme:@null}")
    private String scheme;
    @Value("openAMConnectorConfigurator{openam.connector.host:@null}")
    private String host;
    @Value("openAMConnectorConfigurator{openam.connector.path:@null}")
    private String path;
    @Value("openAMConnectorConfigurator{openam.connector.max_total_connections:@null}")
    private Integer maxTotalConnections;
    @Value("openAMConnectorConfigurator{openam.connector.default_max_per_route:@null}")
    private Integer defaultMaxPerRoute;

    @Override
    public String getScheme() {
        return this.scheme = ((this.scheme != null)
                && !(this.scheme.isEmpty())) ? this.scheme : "http";
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    /**
     * @return the maxTotalConnections
     */
    @Override
    public Integer getMaxTotalConnections() {
        return this.maxTotalConnections = ((this.maxTotalConnections != null)
                ? this.maxTotalConnections : this.defaultMaxTotalConnections());
    }

    /**
     * @return the defaultMaxPerRoute
     */
    @Override
    public Integer getDefaultMaxPerRoute() {
        return this.defaultMaxPerRoute = ((this.defaultMaxPerRoute != null)
                ? this.defaultMaxPerRoute : this.defaultMaxPerRoute());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(((this.host != null)
                && !(this.host.isEmpty())), "The Host Parameter must not be "
                + "null or an empty value.");

        Preconditions.checkArgument(((this.path != null)
                && !(this.path.isEmpty())), "The Path Parameter must not be "
                + "null or an empty value.");
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {" + "scheme = "
                + getScheme()
                + ", host = " + host
                + ", path = " + path
                + ", maxTotalConnections = " + getMaxTotalConnections()
                + ", defaultMaxPerRoute = " + getDefaultMaxPerRoute()
                + '}';
    }
}
