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
package org.geosdi.geoplatform.connector.pool.builder;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorConfig;
import org.geosdi.geoplatform.connector.pool.factory.GPGeowebcacheConnectorFactory;
import org.geosdi.geoplatform.connector.pool.key.IGPPoolGeowebcacheConnectorKey;
import org.geosdi.geoplatform.connector.store.GPGeowebcacheConnectorStore;
import org.geosdi.geoplatform.support.jackson.JacksonSupport;
import tools.jackson.databind.ObjectMapper;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Boolean.FALSE;
import static org.geosdi.geoplatform.connector.GeowebcacheVersion.fromString;
import static org.geosdi.geoplatform.connector.pool.key.IGPPoolGeowebcacheConnectorKey.of;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAXB;
import static org.geosdi.geoplatform.support.jackson.builder.JacksonSupportBuilder.GPJacksonSupportBuilder.builder;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;

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
        var v = fromString(this.version);
        var key = of(serverUrl, pooledConnectorConfig, securityConnector, v.getVersion(), this::toJacksonSupport);
        return geowebcacheConnectorPool.borrowObject(key);
    }

    /**
     * @return {@link JacksonSupport}
     */
    ObjectMapper toJacksonSupport() {
        this.jacksonSupport = ((this.jacksonSupport != null) ? this.jacksonSupport : builder(FALSE)
                .withIntespectorBuilder(JAXB)
                .configure(NON_NULL).build());
        return this.jacksonSupport.getDefaultMapper();
    }
}