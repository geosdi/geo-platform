/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2020 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.connector.api.pool;

import org.geosdi.geoplatform.connector.api.AbstractConnectorBuilder;
import org.geosdi.geoplatform.connector.api.GeoPlatformConnector;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractConnectorBuilderPool<B extends AbstractConnectorBuilderPool, C extends GeoPlatformConnector> extends AbstractConnectorBuilder<B, C> {

    protected AbstractConnectorBuilderPool() {
    }

    /**
     * @return {@link C}
     * @throws Exception
     */
    @Override
    public C build() throws Exception {
        checkNotNull(serverUrl, "WMS Server URL must not be null.");
        checkArgument((this.version != null) && !(this.version.trim().isEmpty()), "The Parameter wmsVersion must not be null or an empty string.");
        return this.internalBuild(new GPPoolConnectorKey(this.serverUrl, this.pooledConnectorConfig, this.securityConnector, this.proxyConfiguration, this.sslConnectionSocketFactory, this.version));
    }

    /**
     * @param key
     * @return {@link C}
     * @throws Exception
     */
    protected abstract C internalBuild(@Nonnull(when = NEVER) GPPoolConnectorKey key) throws Exception;
}