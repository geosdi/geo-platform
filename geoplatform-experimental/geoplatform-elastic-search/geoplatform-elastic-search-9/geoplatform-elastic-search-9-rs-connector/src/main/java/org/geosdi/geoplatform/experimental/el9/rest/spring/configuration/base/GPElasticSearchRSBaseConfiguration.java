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
package org.geosdi.geoplatform.experimental.el9.rest.spring.configuration.base;

import org.apache.hc.core5.http.HttpHost;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.pool.GPElasticSearchRSPoolConfiguration;
import org.geosdi.geoplatform.experimental.el.rest.spring.configuration.ssl.GPElasticSearchRSSslConfiguration;
import org.geosdi.geoplatform.experimental.el9.rest.spring.configuration.auth.GPElasticSearchRSAuthConfiguration;

import static java.lang.Long.valueOf;
import static java.time.Duration.ofSeconds;

/**
 * Configurazione della connessione a ElasticSearch: nodi, tempi, pool, credenziali e SSL. Usa le stesse proprietà
 * del connettore per ElasticSearch 7.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPElasticSearchRSBaseConfiguration extends GPElasticSearchRSPoolConfiguration {

    /**
     * @return {@link HttpHost} i nodi di ElasticSearch
     */
    HttpHost[] getHttpHosts();

    /**
     * @return {@link Integer} il tempo massimo per aprire una connessione, in millisecondi
     */
    Integer getConnectionTimeout();

    /**
     * @return {@link Integer} il tempo massimo di attesa della risposta, in millisecondi
     */
    Integer getSocketTimeout();

    /**
     * @return {@link GPElasticSearchRSAuthConfiguration}
     */
    GPElasticSearchRSAuthConfiguration getAuthConfig();

    /**
     * @return {@link GPElasticSearchRSSslConfiguration}
     */
    GPElasticSearchRSSslConfiguration getSslConfig();

    /**
     * @return {@link Integer} 2 secondi, in millisecondi
     */
    default Integer defaultConnectionTimeout() {
        return valueOf(ofSeconds(2).toMillis()).intValue();
    }

    /**
     * @return {@link Integer} 30 secondi, in millisecondi
     */
    default Integer defaultSocketTimeout() {
        return valueOf(ofSeconds(30).toMillis()).intValue();
    }
}
