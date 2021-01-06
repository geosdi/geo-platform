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
