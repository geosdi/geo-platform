package org.geosdi.geoplatform.websocket.support.spring.jasypt.placeholder;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPWebSocketServerPlaceholderConfig {

    private static final Logger logger = LoggerFactory.getLogger(GPWebSocketServerPlaceholderConfig.class);
    public static final GPWebSocketServerPlaceholderResourcesLoader placeholderResourcesLoader = new GPWebSocketServerPlaceholderResourcesLoader();

    /**
     * @param geoPlatformWebSocketServerPooledPBEStringEncryptor
     * @param geoPlatformConfigDataDir
     * @param geoPlatformWebSocketServerFileProp
     * @return {@link EncryptablePropertySourcesPlaceholderConfigurer}
     * @throws Exception
     */
    @Bean(name = "geoPlatformWebSocketServerPropertyConfigurer")
    public static EncryptablePropertySourcesPlaceholderConfigurer geoPlatformWebSocketServerPropertyConfigurer(
            @Qualifier(value = "geoPlatformWebSocketServerPooledPBEStringEncryptor") PooledPBEStringEncryptor geoPlatformWebSocketServerPooledPBEStringEncryptor,
            @Value("#{systemProperties['GP_DATA_DIR']}") String geoPlatformConfigDataDir,
            @Value("#{systemProperties['GP_WEBSOCKET_SERVER_FILE_PROP']}") String geoPlatformWebSocketServerFileProp)
            throws Exception {
        logger.debug("####################################GEO_PLATFORM_WEBSOCKET_SERVER_PLACEHOLDER_CONFIG START_UP\n");

        EncryptablePropertySourcesPlaceholderConfigurer geoPlatformWebSocketServerPC = new EncryptablePropertySourcesPlaceholderConfigurer(geoPlatformWebSocketServerPooledPBEStringEncryptor);
        geoPlatformWebSocketServerPC.setPlaceholderPrefix("geoPlatformWebSocketServerConfigurator{");
        geoPlatformWebSocketServerPC.setPlaceholderSuffix("}");
        geoPlatformWebSocketServerPC.setNullValue("@null");

        geoPlatformWebSocketServerPC.setLocations(placeholderResourcesLoader.loadResources(geoPlatformConfigDataDir, geoPlatformWebSocketServerFileProp));
        geoPlatformWebSocketServerPC.setIgnoreResourceNotFound(TRUE);
        geoPlatformWebSocketServerPC.setIgnoreUnresolvablePlaceholders(TRUE);
        return geoPlatformWebSocketServerPC;
    }
}