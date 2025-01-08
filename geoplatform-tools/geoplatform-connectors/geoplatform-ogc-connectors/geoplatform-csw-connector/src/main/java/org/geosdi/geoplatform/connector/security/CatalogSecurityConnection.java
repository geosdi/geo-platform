/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.net.ssl.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;
import static java.util.Base64.getEncoder;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CatalogSecurityConnection implements GPCatalogSecurityConnection {

    private static final Logger logger = LoggerFactory.getLogger(CatalogSecurityConnection.class);

    public CatalogSecurityConnection() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0],
                    new TrustManager[]{new DefaultTrustManager()},
                    new SecureRandom());
            SSLContext.setDefault(ctx);
        } catch (Exception ex) {
            logger.error("EXCEPTION @@@@@@@@@@@@@@@@@@@@@@ " + ex);
        }

    }

    /**
     * @param url
     * @return {@link HttpURLConnection}
     * @throws Exception
     */
    @Override
    public HttpURLConnection getSecureConnection(@Nonnull(when = NEVER) URL url) throws Exception {
        checkArgument(url != null, "The Parameter url must not be null.");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier((arg, arg1) -> TRUE);
        return conn;
    }

    /**
     * @param url
     * @param user
     * @param pass
     * @return {@link HttpURLConnection}
     * @throws Exception
     */
    @Override
    public HttpURLConnection getSecureConnectionWithAuth(@Nonnull(when = NEVER) URL url, @Nonnull(when = NEVER) String user, @Nonnull(when = NEVER) String pass) throws Exception {
        checkArgument(url != null, "The Parameter url must not be null.");
        checkArgument((user != null) && !(user.trim().isEmpty()), "The Parameter user must not be null or an empty string.");
        checkArgument((pass != null) && !(pass.trim().isEmpty()), "The Parameter password must not be null or an empty string.");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setHostnameVerifier((arg, arg1) -> TRUE);
        String userpass = user + ":" + pass;
        String basicAuth = "Basic " + Arrays.toString(getEncoder().encode(userpass.getBytes()));
        conn.setRequestProperty("Authorization", basicAuth);
        return conn;
    }

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}