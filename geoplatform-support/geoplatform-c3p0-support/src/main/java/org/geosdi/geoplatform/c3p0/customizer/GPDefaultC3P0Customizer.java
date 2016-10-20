package org.geosdi.geoplatform.c3p0.customizer;

import com.mchange.v2.c3p0.ConnectionCustomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPDefaultC3P0Customizer implements ConnectionCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(GPDefaultC3P0Customizer.class);
    //
    private AtomicInteger activeConnections = new AtomicInteger(0);
    private AtomicInteger acquiredConnections = new AtomicInteger(0);

    @Override
    public void onAcquire(Connection connection, String parentDataSourceIdentityToken) throws Exception {
        logger.debug("onAcquire: Connection acquired from database : " + connection
                + " [" + parentDataSourceIdentityToken + "]");
        logger.debug("onAcquire: Total Open Connections in Pool : "
                + acquiredConnections.incrementAndGet());
    }

    @Override
    public void onDestroy(Connection connection, String parentDataSourceIdentityToken) throws Exception {
        logger.debug("onDestroy: Connection closed with database : " + connection + " ["
                + parentDataSourceIdentityToken + "]");
        logger.debug("onDestroy: Total Open Connections in Pool : "
                + acquiredConnections.decrementAndGet());
    }

    @Override
    public void onCheckOut(Connection connection, String parentDataSourceIdentityToken) throws Exception {
        logger.debug("onCheckOut: Connection from pool provide to application : "
                        + connection + " [" + parentDataSourceIdentityToken + "]");
        logger.debug("onCheckOut: Total Active Connections in Pool : "
                + activeConnections.incrementAndGet());
    }

    @Override
    public void onCheckIn(Connection connection, String parentDataSourceIdentityToken) throws Exception {
        logger.debug("onCheckIn: Connection returned to pool "
                + "from application : "
                + connection + " [" + parentDataSourceIdentityToken + "]");
        logger.debug("onCheckIn: Total Active Connections in Pool : "
                + activeConnections.decrementAndGet());
    }
}
