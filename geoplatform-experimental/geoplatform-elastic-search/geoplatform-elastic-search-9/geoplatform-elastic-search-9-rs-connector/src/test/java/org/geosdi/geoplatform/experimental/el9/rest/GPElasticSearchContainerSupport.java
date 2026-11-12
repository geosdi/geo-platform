/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.experimental.el9.rest;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.time.Duration;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;

/**
 * Avvia ElasticSearch 9 in Docker e prepara le proprietà del connettore come le legge un'applicazione: file nella
 * cartella di {@code GP_ELASTICSEARCH_REST_DATA_DIR} e password cifrata con jasypt.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
final class GPElasticSearchContainerSupport {

    static final String IMAGE = "docker.elastic.co/elasticsearch/elasticsearch:9.5.6";
    static final String PBE_KEY_PROPERTY = "GP_ELASTICSEARCH_REST_PBE_KEY";
    static final String USER = "elastic";
    static final String PASSWORD = "gp-test-password";
    private static final String PBE_KEY = "gp-elasticsearch-test-key";
    private static final String FILE_PROP = "gp.elastic-search-rest-9-test.properties";

    /**
     * Classe di sole funzioni statiche.
     */
    private GPElasticSearchContainerSupport() {
    }

    /**
     * @param theSecureSocketLayer true per l'HTTPS con il certificato generato da ElasticSearch, false per l'HTTP
     * @return {@link ElasticsearchContainer} il container avviato, con utente e password
     */
    static ElasticsearchContainer startContainer(boolean theSecureSocketLayer) {
        ElasticsearchContainer container = new ElasticsearchContainer(IMAGE)
                .withPassword(PASSWORD)
                .withEnv("ES_JAVA_OPTS", "-Xms512m -Xmx512m");
        if (!theSecureSocketLayer) {
            container.withEnv("xpack.security.http.ssl.enabled", "false")
                    .waitingFor(Wait.forHttp("/_cluster/health").forPort(9200).withBasicCredentials(USER, PASSWORD)
                            .forStatusCode(200).withStartupTimeout(Duration.ofMinutes(3)));
        }
        container.start();
        return container;
    }

    /**
     * Scrive le proprietà del connettore e imposta le system property che il connettore legge.
     *
     * @param theHosts           i nodi
     * @param theTrustStore      il truststore; null vuol dire nessun SSL
     * @param theTrustStorePass  la password del truststore; null senza SSL
     * @throws Exception se il file non si scrive
     */
    static void writeConnectorProperties(@Nonnull(when = NEVER) String theHosts, @Nullable Path theTrustStore,
            @Nullable String theTrustStorePass) throws Exception {
        checkArgument(theHosts != null, "Il parametro hosts non deve essere null.");
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(PBE_KEY);
        Properties properties = new Properties();
        properties.setProperty("gp.elasticsearch.rest.hosts", theHosts);
        properties.setProperty("gp.elasticsearch.rest.username", USER);
        properties.setProperty("gp.elasticsearch.rest.password", "ENC(" + encryptor.encrypt(PASSWORD) + ")");
        properties.setProperty("gp.elasticsearch.rest.connection.timeout", "5");
        properties.setProperty("gp.elasticsearch.rest.socket.timeout", "60");
        if (theTrustStore != null) {
            properties.setProperty("gp.elasticsearch.rest.key_store", theTrustStore.toString());
            properties.setProperty("gp.elasticsearch.rest.key_store.password", theTrustStorePass);
        }
        Path dataDir = Files.createTempDirectory("gp-elasticsearch-9-test");
        try (OutputStream out = Files.newOutputStream(dataDir.resolve(FILE_PROP))) {
            properties.store(out, "Proprietà dei test del connettore per ElasticSearch 9");
        }
        System.setProperty("GP_ELASTICSEARCH_REST_DATA_DIR", dataDir.toString());
        System.setProperty("GP_ELASTICSEARCH_REST_FILE_PROP", FILE_PROP);
        System.setProperty(PBE_KEY_PROPERTY, PBE_KEY);
    }

    /**
     * @param theContainer il container con l'HTTPS
     * @param thePassword  la password del truststore
     * @return {@link Path} un truststore PKCS12 con il certificato della CA di ElasticSearch
     * @throws Exception se il truststore non si scrive
     */
    static Path writeTrustStore(@Nonnull(when = NEVER) ElasticsearchContainer theContainer, @Nonnull(when = NEVER) String thePassword) throws Exception {
        checkArgument(theContainer != null, "Il parametro container non deve essere null.");
        checkArgument(thePassword != null, "Il parametro password non deve essere null.");
        byte[] caCert = theContainer.caCertAsBytes().orElseThrow(() -> new IllegalStateException("ElasticSearch non ha il certificato della CA."));
        KeyStore trustStore = KeyStore.getInstance("PKCS12");
        trustStore.load(null, thePassword.toCharArray());
        trustStore.setCertificateEntry("elasticsearch-ca", CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(caCert)));
        Path path = Files.createTempFile("gp-elasticsearch-9-truststore", ".p12");
        try (OutputStream out = Files.newOutputStream(path)) {
            trustStore.store(out, thePassword.toCharArray());
        }
        return path;
    }
}
