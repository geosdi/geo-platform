package org.geosdi.geoplatform.shared;

import org.geosdi.geoplatform.connector.server.security.BasicPreemptiveSecurityConnector;
import org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

import static org.geosdi.geoplatform.connector.server.config.GPPooledConnectorConfigBuilder.PooledConnectorConfigBuilder.pooledConnectorConfigBuilder;
import static org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStoreBuilder.geoserverConnectorBuilder;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Configuration
class GeoServerConnectorConfig {

    /**
     * @param geoserverUrl
     * @param username
     * @param password
     * @return {@link GPGeoserverConnectorStore}
     * @throws Exception
     */
    @Bean(name = "geoserverConnectorStore")
    public GPGeoserverConnectorStore geoserverConnectorStore(@Value(value = "configurator{geoserver_url}") String geoserverUrl,
            @Value(value = "configurator{geoserver_username}") String username, @Value(value = "configurator{geoserver_password}") String password) throws Exception {
        return geoserverConnectorBuilder().withServerUrl(new URL(geoserverUrl.contains("/rest") ? geoserverUrl : geoserverUrl.concat("/rest")))
                .withPooledConnectorConfig(pooledConnectorConfigBuilder()
                        .withMaxTotalConnections(80)
                        .withDefaultMaxPerRoute(40)
                        .withMaxRedirect(10)
                        .build())
                .withClientSecurity(new BasicPreemptiveSecurityConnector(username, password))
                .build();
    }

}