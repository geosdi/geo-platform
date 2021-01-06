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
package org.geosdi.geoplatform.wmc.support.v110.jaxb.repository;

import org.geosdi.geoplatform.jaxb.provider.GeoPlatformJAXBContextProvider;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.pool.WMCJAXBContextPoolV110;
import org.geosdi.geoplatform.xml.wmc.WMCServiceProviderV110;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WMCJAXBContextProviderPoolV110 implements GeoPlatformJAXBContextProvider {

    static {
        try {
            jaxbContext = new WMCJAXBContextPoolV110(
                    WMCServiceProviderV110.loadContextPath());
        } catch (JAXBException e) {
            LoggerFactory.getLogger(WMCJAXBContextProviderPoolV110.class).error(
                    "Failed to Initialize JAXBContext for Class "
                            + WMCJAXBContextProviderPoolV110.class.getName()
                            + ": @@@@@@@@@@@@@@@@@ " + e);
        }
    }

    //
    private static WMCJAXBContextPoolV110 jaxbContext;
    public static final WMCJAXBContextPoolV110.WMCJAXBContextPoolKeyV110 WMC_CONTEXT_POOL_KEY_V110 = new WMCJAXBContextPoolV110.WMCJAXBContextPoolKeyV110();

    protected WMCJAXBContextProviderPoolV110() {
    }

    @Override
    public WMCJAXBContextPoolV110 getJAXBProvider() {
        return jaxbContext;
    }

    @Override
    public WMCJAXBContextPoolV110.WMCJAXBContextPoolKeyV110 getKeyProvider() {
        return WMC_CONTEXT_POOL_KEY_V110;
    }
}
