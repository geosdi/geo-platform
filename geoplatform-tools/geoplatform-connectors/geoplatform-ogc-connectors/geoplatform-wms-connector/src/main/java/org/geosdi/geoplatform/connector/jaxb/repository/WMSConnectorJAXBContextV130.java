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
package org.geosdi.geoplatform.connector.jaxb.repository;

import jakarta.xml.bind.JAXBException;
import org.geosdi.geoplatform.connector.WMSVersion;
import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext.WMSJAXBContextKey;
import org.geosdi.geoplatform.connector.jaxb.context.WMSJAXBContext;
import org.geosdi.geoplatform.jakarta.GPBaseJakartaContext;
import org.geosdi.geoplatform.jakarta.repository.GeoPlatformJakartaContextRepository.GeoPlatformJakartaContextKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.geosdi.geoplatform.connector.WMSVersion.V130;
import static org.geosdi.geoplatform.wms.v130.WMSContextServiceProviderV130.WMS_CONTEXT_SERVICE_PROVIDER_V130;

/**
 * Contesto JAXB {@code jakarta.xml.bind} dei binding WMS 1.3.0, registrato nel repository dei connettori con la
 * chiave {@link #WMS_CONTEXT_KEY_V130}.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public final class WMSConnectorJAXBContextV130 implements GPWMSConnectorJAXBContext {

    private static final Logger logger = LoggerFactory.getLogger(WMSConnectorJAXBContextV130.class);
    //
    private static final WMSJAXBContext jakartaContext = createContext();
    public static final WMSJAXBContextKey WMS_CONTEXT_KEY_V130 = new WMSJAXBContextKey(WMSConnectorJAXBContextV130.class);

    /**
     * Usato dal repository dei connettori, che crea il provider per riflessione.
     */
    protected WMSConnectorJAXBContextV130() {
    }

    /**
     * @return il contesto dei binding WMS 1.3.0, o null se non è stato possibile crearlo
     */
    private static WMSJAXBContext createContext() {
        try {
            return new WMSJAXBContext(V130, WMS_CONTEXT_SERVICE_PROVIDER_V130.getContextClasses());
        } catch (JAXBException ex) {
            logger.error("Impossibile creare il contesto jakarta dei binding WMS 1.3.0", ex);
            return null;
        }
    }

    /**
     * @param <P> il tipo del contesto
     * @return {@link P}
     */
    @Override
    public <P extends GPBaseJakartaContext> P getJakartaProvider() {
        return (P) jakartaContext;
    }

    /**
     * @param <K> il tipo della chiave
     * @return {@link K}
     */
    @Override
    public <K extends GeoPlatformJakartaContextKey> K getKeyProvider() {
        return (K) WMS_CONTEXT_KEY_V130;
    }

    /**
     * @param <Version> il tipo della versione
     * @return {@link Version}
     */
    @Override
    public <Version extends WMSVersion> Version getVersion() {
        return (Version) V130;
    }
}
