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

import org.geosdi.geoplatform.connector.jaxb.context.WPSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.context.WPSJAXBContext.WPSJAXBContextKey;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.geosdi.geoplatform.jaxb.repository.GeoPlatformJAXBContextRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.xml.wps.v100.context.WPSContextServiceProviderV100.WPS_CONTEXT_SERVICE_PROVIDER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WPSConnectorJAXBContext implements GeoPlatformJAXBContextProvider {

    private static final Logger logger = LoggerFactory.getLogger(WPSConnectorJAXBContext.class);

    static {
        try {
            jaxbContext = new WPSJAXBContext(WPS_CONTEXT_SERVICE_PROVIDER.getContextPath());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("Failed to Initialize JAXBContext for Class "
                    + WPSConnectorJAXBContext.class.getName()
                    + ": @@@@@@@@@@@@@@@@@ " + ex);
        }
    }

    //
    private static WPSJAXBContext jaxbContext;
    public static final WPSJAXBContextKey WPS_CONTEXT_KEY = new WPSJAXBContextKey();

    protected WPSConnectorJAXBContext() {
    }

    @Override
    public <P extends GPBaseJAXBContext> P getJAXBProvider() {
        return (P) jaxbContext;
    }

    @Override
    public <K extends GeoPlatformJAXBContextRepository.GeoPlatformJAXBContextKey> K getKeyProvider() {
        return (K) WPS_CONTEXT_KEY;
    }
}
