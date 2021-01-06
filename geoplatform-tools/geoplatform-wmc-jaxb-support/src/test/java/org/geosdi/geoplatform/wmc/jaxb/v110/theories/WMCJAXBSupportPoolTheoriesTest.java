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
package org.geosdi.geoplatform.wmc.jaxb.v110.theories;

import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.pool.WMCJAXBContextPoolV110;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.repository.WMCJAXBContextProviderPoolV110;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.repository.WMCJAXBContextSupport;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.StringWriter;
import java.net.URL;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(Theories.class)
public class WMCJAXBSupportPoolTheoriesTest extends AbstractWMCJAXBTheoriesTest {

    static {
        wmcJAXBContextPool = WMCJAXBContextSupport
                .getProvider(WMCJAXBContextProviderPoolV110.WMC_CONTEXT_POOL_KEY_V110);
    }

    //
    private final static WMCJAXBContextPoolV110 wmcJAXBContextPool;

    @Theory
    public void wmcJaxbSupportPoolTest(String fileName) throws Exception {
        String wmcFileString = dirFiles + fileName;
        File wmcFile = new File(wmcFileString);

        Object o = wmcJAXBContextPool.unmarshal(wmcFile);

        logger.info("#####################{}\n", (o instanceof JAXBElement) ? ((JAXBElement) o).getValue() : o);
        StringWriter writer = new StringWriter();
        wmcJAXBContextPool.marshal((o instanceof JAXBElement) ? ((JAXBElement) o).getValue() : o, writer);
        logger.info("###########################\n{}\n\n", writer);
    }

    @Theory
    public void wmcJaxbSupportPoolURLTest(String fileName) throws Exception {
        Object o = wmcJAXBContextPool.unmarshal(new URL(baseRepoURL.concat(fileName)));

        logger.info("#####################{}\n", (o instanceof JAXBElement) ? ((JAXBElement) o).getValue() : o);
        StringWriter writer = new StringWriter();
        wmcJAXBContextPool.marshal((o instanceof JAXBElement) ? ((JAXBElement) o).getValue() : o, writer);
        logger.info("###########################\n{}\n\n", writer);
    }
}
