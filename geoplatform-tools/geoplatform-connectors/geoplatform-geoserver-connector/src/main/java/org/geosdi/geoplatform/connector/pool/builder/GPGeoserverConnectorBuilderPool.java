/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import org.geosdi.geoplatform.connector.GeoserverVersion;
import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorConfig;
import org.geosdi.geoplatform.connector.pool.factory.GPGeoserverConnectorFactory;
import org.geosdi.geoplatform.connector.pool.key.IGPPoolGeoserverConnectorKey;
import org.geosdi.geoplatform.connector.store.GPGeoserverConnectorStore;
import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.geosdi.geoplatform.connector.GeoserverVersion.fromString;
import static org.geosdi.geoplatform.connector.pool.key.IGPPoolGeoserverConnectorKey.of;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPGeoserverConnectorBuilderPool extends AbstractConnectorBuilder<GPGeoserverConnectorBuilderPool, GPGeoserverConnectorStore> {

    static {
        geoserverConnectorPool = new GenericKeyedObjectPool<>(new GPGeoserverConnectorFactory(), new GPPoolConnectorConfig());
    }

    private static final GenericKeyedObjectPool<IGPPoolGeoserverConnectorKey, GPGeoserverConnectorStore> geoserverConnectorPool;
    //
    private JacksonSupport jacksonSupport;

    private GPGeoserverConnectorBuilderPool() {
    }

    /**
     * @return {@link GPGeoserverConnectorBuilderPool}
     */
    public static GPGeoserverConnectorBuilderPool geoserverConnectorBuilderPool() {
        return new GPGeoserverConnectorBuilderPool();
    }

    /**
     * @param theJacksonSupport
     * @return {@link GPGeoserverConnectorBuilderPool}
     */
    public GPGeoserverConnectorBuilderPool withJacksonSupport(JacksonSupport theJacksonSupport) {
        this.jacksonSupport = theJacksonSupport;
        return self();
    }

    /**
     * @return {@link GPGeoserverConnectorStore}
     * @throws Exception
     */
    @Override
    public GPGeoserverConnectorStore build() throws Exception {
        checkNotNull(serverUrl, "Geoserver Connector Server URL must not be null.");
        GeoserverVersion v = fromString(this.version);
        IGPPoolGeoserverConnectorKey key = of(serverUrl, pooledConnectorConfig, securityConnector, v.getVersion(), this::toJacksonSupport);
        GPGeoserverConnectorStore geoserverConnectorStore = geoserverConnectorPool.borrowObject(key);
        return geoserverConnectorStore;
    }

    /**
     * @return {@link JacksonSupport}
     */
    ObjectMapper toJacksonSupport() {
        return ((this.jacksonSupport != null) ? this.jacksonSupport.getDefaultMapper() : new GPJacksonSupport().getDefaultMapper());
    }
}