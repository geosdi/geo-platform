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
package org.geosdi.geoplatform.wmc.jaxb.v110.comparison;

import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;
import org.geosdi.geoplatform.wmc.support.v110.jaxb.context.pool.WMCJAXBContextPoolV110;
import org.geosdi.geoplatform.xml.wmc.v110.ViewContextType;
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
public abstract class AbstractWMCComparisonTest {

    private static final ThreadFactory WMCComparisonThreadFactory = new ThreadFactory() {

        private final AtomicInteger threadID = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.privilegedThreadFactory().newThread(r);
            thread.setName("WMCComparisonThread - " + threadID.getAndIncrement());
            thread.setDaemon(Boolean.TRUE);
            return thread;
        }
    };
    //
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private static File file;

    @BeforeClass
    public static void loadFile() throws Exception {
        String wmcFileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/WMC-v110.xml";
        file = new File(wmcFileString);
    }

    protected int defineNumThreads() {
        return 150;
    }

    protected long executeMultiThreadsTasks(GPBaseJAXBContext jaxbContext)
            throws Exception {
        long time = 0;

        int numThreads = defineNumThreads();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads,
                WMCComparisonThreadFactory);

        List<Callable<Long>> tasks = new ArrayList<>(
                numThreads);
        for (int i = 0; i < numThreads; i++) {
            if (jaxbContext instanceof WMCJAXBContextPoolV110) {
                tasks.add(new WMCPooledTask(jaxbContext));
            } else {
                tasks.add(new WMCSimpleTask(jaxbContext));
            }
        }

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

    private class WMCSimpleTask implements Callable<Long> {

        final GPBaseJAXBContext jaxbContext;

        public WMCSimpleTask(GPBaseJAXBContext theJaxbContext) {
            this.jaxbContext = theJaxbContext;
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            ViewContextType viewContextType = ((JAXBElement<ViewContextType>) jaxbContext
                    .acquireUnmarshaller().unmarshal(file)).getValue();
            StringWriter writer = new StringWriter();

            jaxbContext.acquireMarshaller().marshal(viewContextType, writer);
            logger.debug("\n{}\n", writer);

            return System.currentTimeMillis() - start;
        }
    }

    private class WMCPooledTask extends WMCSimpleTask {

        public WMCPooledTask(GPBaseJAXBContext theJaxbContext) {
            super(theJaxbContext);
        }

        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();

            ViewContextType viewContextType = (ViewContextType) ((WMCJAXBContextPoolV110) jaxbContext).unmarshal(file);
            StringWriter writer = new StringWriter();
            ((WMCJAXBContextPoolV110) jaxbContext).marshal(viewContextType, writer);
            logger.debug("\n{}\n", writer);

            return System.currentTimeMillis() - start;
        }
    }
}
