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
package org.geosdi.geoplatform.connector.wfs.jaxb.comparison;

import org.geosdi.geoplatform.connector.jaxb.context.WFSJAXBContext;
import org.geosdi.geoplatform.connector.jaxb.context.pool.WFSJAXBContextPool;
import org.geosdi.geoplatform.xml.wfs.v110.WFSCapabilitiesType;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.junit.FixMethodOrder;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import java.util.concurrent.TimeUnit;

import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class WFSContextComparisonTest extends AbstractWFSComparisonTest {

    @Test
    public void a_wfsPooledDescribeFeatureTest() throws Exception {
        logger.info("WFSPooledDescribeFeatureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContextPool(JAXBContext.newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.DESCRIBE_FEATURE_POOLED)));
    }

    @Test
    public void b_wfsSimpleDescribeFeatureTest() throws Exception {
        logger.info("WFSSimpleDescribeFeatureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContext(JAXBContext.newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.DESCRIBE_FEATURE_SIMPLE)));
    }


    @Test
    public void c_wfsPooledDescribeFeatureSecureTest() throws Exception {
        logger.info("WFSPooledDescribeFeatureSecureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContextPool(JAXBContext.newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.DESCRIBE_FEATURE_SECURE_POOLED)));
    }

    @Test
    public void d_wfsSimpleDescribeFeatureSecureTest() throws Exception {
        logger.info("WFSSimpleDescribeFeatureSecureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContext(JAXBContext.newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.DESCRIBE_FEATURE_SECURE_SIMPLE)));
    }

    @Test
    public void e_wfsPooledGetCapabilitiesTest() throws Exception {
        logger.info("WFSPooledGetCapabilitiesTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContextPool(JAXBContext.newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.GET_CAPABILITIES_POOLED)));
    }

    @Test
    public void f_wfsSimpleGetCapabilitiesTest() throws Exception {
        logger.info("WFSSimpleGetCapabilitiesTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContext(JAXBContext.newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.GET_CAPABILITIES_SIMPLE)));
    }

    @Test
    public void g_wfsPooledGetCapabilitiesSecureTest() throws Exception {
        logger.info("WFSPooledGetCapabilitiesSecureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContextPool(JAXBContext.newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.GET_CAPABILITIES_SECURE_POOLED)));
    }

    @Test
    public void h_wfsSimpleGetCapabilitiesSecureTest() throws Exception {
        logger.info("WFSSimpleGetCapabilitiesSecureTest : Executed {} threads in {} s \n",
                super.defineNumThreads(),
                TimeUnit.MILLISECONDS.toSeconds(executeMultiThreadsTasks(
                        new WFSJAXBContext(JAXBContext.newInstance(Schema.class, WFSCapabilitiesType.class)),
                        WFSTaskType.GET_CAPABILITIES_SECURE_SIMPLE)));
    }
}