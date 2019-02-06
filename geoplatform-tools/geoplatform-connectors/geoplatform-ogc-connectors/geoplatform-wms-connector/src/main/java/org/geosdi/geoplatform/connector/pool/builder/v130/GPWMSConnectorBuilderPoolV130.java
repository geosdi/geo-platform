package org.geosdi.geoplatform.connector.pool.builder.v130;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorConfig;
import org.geosdi.geoplatform.connector.api.pool.GPPoolConnectorKey;
import org.geosdi.geoplatform.connector.pool.builder.GPWMSConnectorBuilderPool;
import org.geosdi.geoplatform.connector.pool.factory.v130.GPWMSConnectorFactoryV130;
import org.geosdi.geoplatform.connector.server.v130.IGPWMSConnectorStoreV130;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.connector.WMSVersion.V130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPWMSConnectorBuilderPoolV130 extends GPWMSConnectorBuilderPool<GPWMSConnectorBuilderPoolV130, IGPWMSConnectorStoreV130> {

    static {
        wmsConnectorPoolV130 = new GenericKeyedObjectPool<GPPoolConnectorKey, IGPWMSConnectorStoreV130>(new GPWMSConnectorFactoryV130(), new GPPoolConnectorConfig());
    }

    private static final GenericKeyedObjectPool<GPPoolConnectorKey, IGPWMSConnectorStoreV130> wmsConnectorPoolV130;

    protected GPWMSConnectorBuilderPoolV130() {
        this.withVersion(V130.getVersion());
    }

    /**
     * @param key
     * @return
     * @throws Exception
     */
    @Override
    protected IGPWMSConnectorStoreV130 internalBuild(@Nonnull(when = NEVER) GPPoolConnectorKey key) throws Exception {
        checkArgument(key != null, "The Parameter key must not be null.");
        IGPWMSConnectorStoreV130 wmsConnectorStoreV130 = wmsConnectorPoolV130.borrowObject(key);
        wmsConnectorPoolV130.returnObject(key, wmsConnectorStoreV130);
        return wmsConnectorStoreV130;
    }
}