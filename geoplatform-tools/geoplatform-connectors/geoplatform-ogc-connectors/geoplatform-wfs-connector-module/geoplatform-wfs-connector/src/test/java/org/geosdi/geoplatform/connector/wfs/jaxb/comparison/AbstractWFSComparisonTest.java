/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import javax.xml.bind.JAXBElement;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class AbstractWFSComparisonTest {

    enum WFSTaskType {

        DESCRIBE_FEATURE_SIMPLE,
        DESCRIBE_FEATURE_POOLED,
        DESCRIBE_FEATURE_SECURE_SIMPLE,
        DESCRIBE_FEATURE_SECURE_POOLED,
        GET_CAPABILITIES_SIMPLE,
        GET_CAPABILITIES_POOLED,
        GET_CAPABILITIES_SECURE_SIMPLE,
        GET_CAPABILITIES_SECURE_POOLED;
    }

    private static final ThreadFactory WFSComparisonThreadFactory = new ThreadFactory() {

        private final AtomicInteger threadID = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.privilegedThreadFactory().newThread(r);
            thread.setName("WFSComparisonThread - " + threadID.getAndIncrement());
            thread.setDaemon(Boolean.TRUE);
            return thread;
        }

    };
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private static File describeFeatureFile;
    private static File describeSecureFeatureFile;
    private static File getCapabilitiesFile;
    private static File getCapabilitiesSecureFile;

    @BeforeClass
    public static void loadFile() throws Exception {
        String describeFeatureFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/wfsDescribeFeaturev110.xml";
        describeFeatureFile = new File(describeFeatureFileString);
        String describeSecureFeatureFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/wfsSecureDescribeFeaturev110.xml";
        describeSecureFeatureFile = new File(describeSecureFeatureFileString);
        String getCapabilitiesFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/wfsGetCapabilitiesv110.xml";
        getCapabilitiesFile = new File(getCapabilitiesFileString);
        String getCapabilitiesSecureFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/wfsSecureGetCapabilitiesv110.xml";
        getCapabilitiesSecureFile = new File(getCapabilitiesSecureFileString);
    }

    protected int defineNumThreads() {
        return 150;
    }

    long executeMultiThreadsTasks(GPBaseJAXBContext jaxbContext,
            WFSTaskType wfsTaskType) throws Exception {
        long time = 0;

        int numThreads = defineNumThreads();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads,
                WFSComparisonThreadFactory);

        List<Callable<Long>> tasks = new ArrayList<Callable<Long>>(numThreads);

        fillTasksList(jaxbContext, tasks, wfsTaskType, numThreads);

        List<Future<Long>> results = executor.invokeAll(tasks);
        executor.shutdown();

        boolean flag = executor.awaitTermination(1, TimeUnit.MINUTES);

        if (flag) {
            for (Future<Long> future : results) {
                time += future.get();
            }
        } else {
            throw new InterruptedException("Some Threads are not executed.");
        }

        return time;
    }

    private void fillTasksList(GPBaseJAXBContext jaxbContext,
            List<Callable<Long>> tasks,
            WFSTaskType wfsTaskType, int numThreads) {
        switch (wfsTaskType) {
            case DESCRIBE_FEATURE_SIMPLE:
                for (int i = 0; i < numThreads; i++) {
                    tasks.add(new WFSDescribeFeatureSimpleTask(jaxbContext));
                }
                break;
            case DESCRIBE_FEATURE_POOLED:
                for (int i = 0; i < numThreads; i++) {
                    tasks.add(new WFSDescribeFeaturePooledTask(jaxbContext));
                }
                break;
            case DESCRIBE_FEATURE_SECURE_SIMPLE:
                for (int i = 0; i < numThreads; i++) {
                    tasks.add(new WFSSecureDescribeFeatureSimpleTask(jaxbContext));
                }
                break;
            case DESCRIBE_FEATURE_SECURE_POOLED:
                for (int i = 0; i < numThreads; i++) {
                    tasks.add(new WFSSecureDescribeFeaturePooledTask(jaxbContext));
                }
                break;
            case GET_CAPABILITIES_SIMPLE:
                for (int i = 0; i < numThreads; i++) {
                    tasks.add(new WFSGetCapabilitiesSimpleTask(jaxbContext));
                }
                break;
            case GET_CAPABILITIES_POOLED:
                for (int i = 0; i < numThreads; i++) {
                    tasks.add(new WFSGetCapabilitiesPooledTask(jaxbContext));
                }
                break;
            case GET_CAPABILITIES_SECURE_SIMPLE:
                for (int i = 0; i < numThreads; i++) {
                    tasks.add(new WFSSecureGetCapabilitiesSimpleTask(jaxbContext));
                }
                break;
            case GET_CAPABILITIES_SECURE_POOLED:
                for (int i = 0; i < numThreads; i++) {
                    tasks.add(new WFSSecureGetCapabilitiesPooledTask(jaxbContext));
                }
                break;
        }
    }

    private class WFSDescribeFeatureSimpleTask implements Callable<Long> {

        final GPBaseJAXBContext jaxbContext;

        public WFSDescribeFeatureSimpleTask(GPBaseJAXBContext theJaxbContext) {
            this.jaxbContext = theJaxbContext;
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Schema schema = ((JAXBElement<Schema>) jaxbContext
                    .acquireUnmarshaller().unmarshal(describeFeatureFile)).getValue();
            StringWriter writer = new StringWriter();

            jaxbContext.acquireMarshaller().marshal(schema, writer);
            logger.debug("WFSDescribeFeature : \n{}\n", writer);

            return System.currentTimeMillis() - start;
        }

    }

    private class WFSDescribeFeaturePooledTask extends WFSDescribeFeatureSimpleTask {

        public WFSDescribeFeaturePooledTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Schema schema = (Schema) ((WFSJAXBContextPool) jaxbContext).unmarshal(describeFeatureFile);
            StringWriter writer = new StringWriter();
            ((WFSJAXBContextPool) jaxbContext).marshal(schema, writer);
            logger.debug("\n{}\n", writer);

            return System.currentTimeMillis() - start;
        }

    }

    private class WFSSecureDescribeFeatureSimpleTask extends WFSDescribeFeatureSimpleTask {

        public WFSSecureDescribeFeatureSimpleTask(
                GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Schema secureSchema = ((JAXBElement<Schema>) jaxbContext
                    .acquireUnmarshaller().unmarshal(describeSecureFeatureFile)).getValue();
            StringWriter writer = new StringWriter();

            jaxbContext.acquireMarshaller().marshal(secureSchema, writer);
            logger.debug("WFSSecureDescribeFeature : \n{}\n", writer);

            return System.currentTimeMillis() - start;
        }

    }

    private class WFSSecureDescribeFeaturePooledTask extends WFSDescribeFeatureSimpleTask {

        public WFSSecureDescribeFeaturePooledTask(
                GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            Schema secureSchema = (Schema) ((WFSJAXBContextPool) jaxbContext).unmarshal(describeSecureFeatureFile);
            StringWriter writer = new StringWriter();
            ((WFSJAXBContextPool) jaxbContext).marshal(secureSchema, writer);
            logger.debug("\n{}\n", writer);

            return System.currentTimeMillis() - start;
        }

    }

    private class WFSGetCapabilitiesSimpleTask extends WFSDescribeFeatureSimpleTask {

        public WFSGetCapabilitiesSimpleTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            WFSCapabilitiesType getCapabilities = (WFSCapabilitiesType) jaxbContext
                    .acquireUnmarshaller().unmarshal(getCapabilitiesFile);
            StringWriter writer = new StringWriter();

            jaxbContext.acquireMarshaller().marshal(getCapabilities, writer);
            logger.debug("WFSGetCapabilities : \n{}\n", writer);

            return System.currentTimeMillis() - start;
        }

    }

    private class WFSGetCapabilitiesPooledTask extends WFSDescribeFeatureSimpleTask {

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

    private class WFSSecureGetCapabilitiesSimpleTask extends WFSDescribeFeatureSimpleTask {

        public WFSSecureGetCapabilitiesSimpleTask(
                GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            WFSCapabilitiesType getSecureCapabilities = (WFSCapabilitiesType) jaxbContext
                    .acquireUnmarshaller().unmarshal(getCapabilitiesSecureFile);
            StringWriter writer = new StringWriter();

            jaxbContext.acquireMarshaller().marshal(getSecureCapabilities, writer);
            logger.debug("WFSSecureGetCapabilities : \n{}\n", writer);

            return System.currentTimeMillis() - start;
        }

    }

    private class WFSSecureGetCapabilitiesPooledTask extends WFSDescribeFeatureSimpleTask {

        public WFSSecureGetCapabilitiesPooledTask(
                GPBaseJAXBContext theJaxbContext) {
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
