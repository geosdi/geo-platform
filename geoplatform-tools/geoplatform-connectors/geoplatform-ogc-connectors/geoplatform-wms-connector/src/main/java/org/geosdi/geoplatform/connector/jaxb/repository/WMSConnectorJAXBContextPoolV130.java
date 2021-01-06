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
package org.geosdi.geoplatform.connector.jaxb.repository;

import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.context.pool.WMSJAXBContextPool;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.jaxb.repository.GeoPlatformJAXBContextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

import static org.geosdi.geoplatform.connector.WMSVersion.V130;
import static org.geosdi.geoplatform.wms.v130.WMSContextServiceProviderV130.WMS_CONTEXT_SERVICE_PROVIDER_V130;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WMSConnectorJAXBContextPoolV130 implements GPWMSConnectorJAXBContextPool {

    private static final Logger logger = LoggerFactory.getLogger(WMSConnectorJAXBContextPoolV130.class);

    static {
        try {
            jaxbContext = new WMSJAXBContextPool(WMS_CONTEXT_SERVICE_PROVIDER_V130.getContextPath(), V130);
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error("Failed to Initialize JAXBContext for Class " + WMSConnectorJAXBContextPoolV130.class.getName()
                    + ": @@@@@@@@@@@@@@@@@ " + e);
        }
    }

    //
    private static WMSJAXBContextPool jaxbContext;
    public static final WMSJAXBContext.WMSJAXBContextKey WMS_CONTEXT_POOL_KEY_V130 = new WMSJAXBContext.WMSJAXBContextKey(WMSConnectorJAXBContextPoolV130.class);

    /**
     * @return {@link P}
     */
    @Override
    public <P extends GPBaseJAXBContext> P getJAXBProvider() {
        return (P) jaxbContext;
    }

    /**
     * @return {@link K}
     */
    @Override
    public <K extends GeoPlatformJAXBContextRepository.GeoPlatformJAXBContextKey> K getKeyProvider() {
        return (K) WMS_CONTEXT_POOL_KEY_V130;
    }

    /**
     * @return {@link Version}
     */
    @Override
    public <Version extends WMSVersion> Version getVersion() {
        return (Version) V130;
    }
}