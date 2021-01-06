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

import org.geosdi.geoplatform.connector.jaxb.context.pool.WFSJAXBContextPool;
import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.xml.wfs.v110.WFSCapabilitiesType;
import org.geosdi.geoplatform.xml.xsd.v2001.Schema;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.StringWriter;
import java.util.concurrent.Callable;

import static com.google.common.base.Preconditions.checkArgument;
import static java.io.File.separator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSBaseComparisonTest implements GPWFSComparisonTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private static File describeFeatureFile;
    private static File describeSecureFeatureFile;
    private static File getCapabilitiesFile;
    private static File getCapabilitiesSecureFile;

    @BeforeClass
    public static void loadFile() throws Exception {
        String basePath = of(new File(".").getCanonicalPath(), "src", "test", "resources")
                .collect(joining(separator, "", separator));
        describeFeatureFile = new File(basePath.concat("wfsDescribeFeaturev110.xml"));
        describeSecureFeatureFile = new File(basePath.concat("wfsSecureDescribeFeaturev110.xml"));
        getCapabilitiesFile = new File(basePath.concat("wfsGetCapabilitiesv110.xml"));
        getCapabilitiesSecureFile = new File(basePath.concat("wfsSecureGetCapabilitiesv110.xml"));
    }

    protected class WFSDescribeFeatureSimpleTask implements Callable<Long> {

        final GPBaseJAXBContext jaxbContext;

        /**
         * @param theJaxbContext
         */
        public WFSDescribeFeatureSimpleTask(@Nonnull(when = NEVER) GPBaseJAXBContext theJaxbContext) {
            checkArgument(theJaxbContext != null, "The Parameter jaxbContext must not be null.");
            this.jaxbContext = theJaxbContext;
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            Schema schema = ((JAXBElement<Schema>) jaxbContext.acquireUnmarshaller().unmarshal(describeFeatureFile)).getValue();
            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(schema, writer);
            logger.debug("WFSDescribeFeature : \n{}\n", writer);
            return System.currentTimeMillis() - start;
        }
    }

    protected class WFSDescribeFeaturePooledTask extends WFSDescribeFeatureSimpleTask {

        /**
         * @param theJaxbContext
         */
        public WFSDescribeFeaturePooledTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            Schema schema = ((WFSJAXBContextPool) jaxbContext).unmarshal(describeFeatureFile);
            StringWriter writer = new StringWriter();
            ((WFSJAXBContextPool) jaxbContext).marshal(schema, writer);
            logger.debug("\n{}\n", writer);
            return System.currentTimeMillis() - start;
        }
    }

    protected class WFSSecureDescribeFeatureSimpleTask extends WFSDescribeFeatureSimpleTask {

        /**
         * @param theJaxbContext
         */
        public WFSSecureDescribeFeatureSimpleTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            Schema secureSchema = ((JAXBElement<Schema>) jaxbContext.acquireUnmarshaller().unmarshal(describeSecureFeatureFile)).getValue();
            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(secureSchema, writer);
            logger.debug("WFSSecureDescribeFeature : \n{}\n", writer);
            return System.currentTimeMillis() - start;
        }
    }

    protected class WFSSecureDescribeFeaturePooledTask extends WFSDescribeFeatureSimpleTask {

        /**
         * @param theJaxbContext
         */
        public WFSSecureDescribeFeaturePooledTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            Schema secureSchema = ((WFSJAXBContextPool) jaxbContext).unmarshal(describeSecureFeatureFile);
            StringWriter writer = new StringWriter();
            ((WFSJAXBContextPool) jaxbContext).marshal(secureSchema, writer);
            logger.debug("\n{}\n", writer);
            return System.currentTimeMillis() - start;
        }
    }

    protected class WFSGetCapabilitiesSimpleTask extends WFSDescribeFeatureSimpleTask {

        /**
         * @param theJaxbContext
         */
        public WFSGetCapabilitiesSimpleTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            WFSCapabilitiesType getCapabilities = (WFSCapabilitiesType) jaxbContext.acquireUnmarshaller().unmarshal(getCapabilitiesFile);
            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(getCapabilities, writer);
            logger.debug("WFSGetCapabilities : \n{}\n", writer);
            return System.currentTimeMillis() - start;
        }
    }

    protected class WFSGetCapabilitiesPooledTask extends WFSDescribeFeatureSimpleTask {

        /**
         * @param theJaxbContext
         */
        public WFSGetCapabilitiesPooledTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            WFSCapabilitiesType getCapabilities = (WFSCapabilitiesType) ((WFSJAXBContextPool) jaxbContext).unmarshal(getCapabilitiesFile);
            StringWriter writer = new StringWriter();
            ((WFSJAXBContextPool) jaxbContext).marshal(getCapabilities, writer);
            logger.debug("\n{}\n", writer);
            return System.currentTimeMillis() - start;
        }
    }

    protected class WFSSecureGetCapabilitiesSimpleTask extends WFSDescribeFeatureSimpleTask {

        /**
         * @param theJaxbContext
         */
        public WFSSecureGetCapabilitiesSimpleTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            WFSCapabilitiesType getSecureCapabilities = (WFSCapabilitiesType) jaxbContext.acquireUnmarshaller().unmarshal(getCapabilitiesSecureFile);
            StringWriter writer = new StringWriter();
            jaxbContext.acquireMarshaller().marshal(getSecureCapabilities, writer);
            logger.debug("WFSSecureGetCapabilities : \n{}\n", writer);
            return System.currentTimeMillis() - start;
        }
    }

    protected class WFSSecureGetCapabilitiesPooledTask extends WFSDescribeFeatureSimpleTask {

        /**
         * @param theJaxbContext
         */
        public WFSSecureGetCapabilitiesPooledTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            WFSCapabilitiesType getSecureCapabilities = (WFSCapabilitiesType) ((WFSJAXBContextPool) jaxbContext).unmarshal(getCapabilitiesSecureFile);
            StringWriter writer = new StringWriter();
            ((WFSJAXBContextPool) jaxbContext).marshal(getSecureCapabilities, writer);
            logger.debug("\n{}\n", writer);
            return System.currentTimeMillis() - start;
        }
    }
}
