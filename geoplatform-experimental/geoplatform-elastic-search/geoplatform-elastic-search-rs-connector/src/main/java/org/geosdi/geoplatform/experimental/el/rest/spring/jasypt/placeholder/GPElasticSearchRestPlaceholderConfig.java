package org.geosdi.geoplatform.experimental.el.rest.spring.jasypt.placeholder;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.spring31.properties.EncryptablePropertySourcesPlaceholderConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.net.MalformedURLException;

import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Configuration
class GPElasticSearchRestPlaceholderConfig {

    private static final GPElasticSearchRestResourcesLoader elasticSearchRestResourcesLoader = new GPElasticSearchRestResourcesLoader();

    /**
     * @param elasticSearchRestPooledPBEStringEncryptor
     * @param elasticSearchRestDataDir
     * @param elasticSearchRestFileProp
     * @return {@link PropertySourcesPlaceholderConfigurer}
     * @throws MalformedURLException
     */
    @Bean(name = "elasticSearchRestPropertyConfigurer")
    public static PropertySourcesPlaceholderConfigurer elasticSearchRestPropertyConfigurer(
            @Qualifier(value = "elasticSearchRestPooledPBEStringEncryptor") PooledPBEStringEncryptor elasticSearchRestPooledPBEStringEncryptor,
            @Value("#{systemProperties['GP_ELASTICSEARCH_REST_DATA_DIR']}") String elasticSearchRestDataDir,
            @Value("#{systemProperties['GP_ELASTICSEARCH_REST_FILE_PROP']}") String elasticSearchRestFileProp) throws MalformedURLException {
        EncryptablePropertySourcesPlaceholderConfigurer elasticSearchRestPC = new EncryptablePropertySourcesPlaceholderConfigurer(elasticSearchRestPooledPBEStringEncryptor);
        elasticSearchRestPC.setPlaceholderPrefix("elasticSearchRestConfigurator{");
        elasticSearchRestPC.setPlaceholderSuffix("}");
        elasticSearchRestPC.setNullValue("@null");
        elasticSearchRestPC.setLocations(elasticSearchRestResourcesLoader
                .loadResources(elasticSearchRestDataDir, elasticSearchRestFileProp));
        elasticSearchRestPC.setIgnoreResourceNotFound(TRUE);
        elasticSearchRestPC.setIgnoreUnresolvablePlaceholders(TRUE);
        return elasticSearchRestPC;
    }
}