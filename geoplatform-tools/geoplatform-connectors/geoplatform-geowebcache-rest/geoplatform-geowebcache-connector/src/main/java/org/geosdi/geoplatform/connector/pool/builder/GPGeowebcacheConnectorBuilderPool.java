/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.pool.builder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.geosdi.geoplatform.connector.GeowebcacheVersion;
import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorConfig;
import org.geosdi.geoplatform.connector.pool.factory.GPGeowebcacheConnectorFactory;
import org.geosdi.geoplatform.connector.pool.key.IGPPoolGeowebcacheConnectorKey;
import org.geosdi.geoplatform.connector.store.GPGeowebcacheConnectorStore;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.geosdi.geoplatform.connector.GeowebcacheVersion.fromString;
import static org.geosdi.geoplatform.connector.pool.key.IGPPoolGeowebcacheConnectorKey.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeowebcacheConnectorBuilderPool extends AbstractConnectorBuilder<GPGeowebcacheConnectorBuilderPool, GPGeowebcacheConnectorStore> {

    static {
        geowebcacheConnectorPool = new GenericKeyedObjectPool<>(new GPGeowebcacheConnectorFactory(), new GPPoolConnectorConfig());
    }

    private static final GenericKeyedObjectPool<IGPPoolGeowebcacheConnectorKey, GPGeowebcacheConnectorStore> geowebcacheConnectorPool;
    //
    private JacksonSupport jacksonSupport;

    private GPGeowebcacheConnectorBuilderPool() {
    }

    /**
     * @return {@link GPGeowebcacheConnectorBuilderPool}
     */
    public static GPGeowebcacheConnectorBuilderPool geowebcacheConnectorBuilderPool() {
        return new GPGeowebcacheConnectorBuilderPool();
    }

    /**
     * @param theJacksonSupport
     * @return {@link GPGeowebcacheConnectorBuilderPool}
     */
    public GPGeowebcacheConnectorBuilderPool withJacksonSupport(JacksonSupport theJacksonSupport) {
        this.jacksonSupport = theJacksonSupport;
        return self();
    }

    /**
     * @return {@link GPGeowebcacheConnectorStore}
     * @throws Exception
     */
    @Override
    public GPGeowebcacheConnectorStore build() throws Exception {
        checkNotNull(serverUrl, "Geowebcache Connector Server URL must not be null.");
        GeowebcacheVersion v = fromString(this.version);
        IGPPoolGeowebcacheConnectorKey key = of(serverUrl, pooledConnectorConfig, securityConnector, v.getVersion(), this::toJacksonSupport);
        GPGeowebcacheConnectorStore geoserverConnectorStore = geowebcacheConnectorPool.borrowObject(key);
        return geoserverConnectorStore;
    }

    /**
     * @return {@link JacksonSupport}
     */
    ObjectMapper toJacksonSupport() {
        return ((this.jacksonSupport != null) ? this.jacksonSupport.getDefaultMapper() : new GPJacksonSupport().getDefaultMapper());
    }
}