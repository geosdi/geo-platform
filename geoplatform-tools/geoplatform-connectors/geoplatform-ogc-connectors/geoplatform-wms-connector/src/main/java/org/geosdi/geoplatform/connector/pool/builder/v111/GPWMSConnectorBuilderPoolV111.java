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
package org.geosdi.geoplatform.connector.pool.builder.v111;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorConfig;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorKey;
import org.geosdi.geoplatform.connector.pool.builder.GPWMSConnectorBuilderPool;
import org.geosdi.geoplatform.connector.pool.factory.v111.GPWMSConnectorFactoryV111;
import org.geosdi.geoplatform.connector.server.v111.IGPWMSConnectorStoreV111;

import javax.annotation.Nonnull;
import javax.annotation.meta.When;

import static com.google.common.base.Preconditions.checkArgument;
import static org.geosdi.geoplatform.connector.WMSVersion.V111;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSConnectorBuilderPoolV111 extends GPWMSConnectorBuilderPool<GPWMSConnectorBuilderPoolV111, IGPWMSConnectorStoreV111> {

    static {
        wmsConnectorPoolV111 = new GenericKeyedObjectPool<GPPoolConnectorKey, IGPWMSConnectorStoreV111>(new GPWMSConnectorFactoryV111(), new GPPoolConnectorConfig());
    }

    private static final GenericKeyedObjectPool<GPPoolConnectorKey, IGPWMSConnectorStoreV111> wmsConnectorPoolV111;

    protected GPWMSConnectorBuilderPoolV111() {
        this.withVersion(V111.getVersion());
    }

    /**
     * @return {@link GPWMSConnectorBuilderPoolV111}
     */
    public static GPWMSConnectorBuilderPoolV111 wmsConnectorBuilderPoolV111() {
        return new GPWMSConnectorBuilderPoolV111();
    }

    /**
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    protected IGPWMSConnectorStoreV111 internalBuild(@Nonnull(when = When.NEVER) GPPoolConnectorKey key) throws Exception {
        checkArgument(key != null, "The Parameter key must not be null.");
        IGPWMSConnectorStoreV111 wmsConnectorStoreV111 = wmsConnectorPoolV111.borrowObject(key);
        wmsConnectorPoolV111.returnObject(key, wmsConnectorStoreV111);
        return wmsConnectorStoreV111;
    }
}