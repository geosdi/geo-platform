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