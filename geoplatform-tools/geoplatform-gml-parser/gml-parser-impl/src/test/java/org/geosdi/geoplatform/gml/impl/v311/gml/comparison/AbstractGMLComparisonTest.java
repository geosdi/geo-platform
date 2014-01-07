/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gml.impl.v311.gml.comparison;

import com.vividsolutions.jts.geom.Geometry;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.geosdi.geoplatform.gml.api.jaxb.context.GMLJAXBContext;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractGMLComparisonTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected static File file;

    @BeforeClass
    public static void loadFile() throws Exception {
        String fileString = new File(".").getCanonicalPath() + File.separator
                + "src/test/resources/GeometryCollection.xml";
        file = new File(fileString);
    }

    protected int defineNumThreads() {
        return 150;
    }

    protected long executeMultiThreadsTasks(GMLJAXBContext jaxbContext) throws Exception {
        long time = 0;

        int numThreads = defineNumThreads();

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        List<GMLSextanteTask> tasks = new ArrayList<GMLSextanteTask>(
                numThreads);
        for (int i = 0; i < numThreads; i++) {
            tasks.add(new GMLSextanteTask(jaxbContext));
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

    private long executeSingleTask(GMLJAXBContext jaxbContext) throws Exception {
        long start = System.currentTimeMillis();

        synchronized (this) {
            Geometry geometry = (Geometry) jaxbContext.acquireUnmarshaller().unmarshal(
                    file);
        }

        return System.currentTimeMillis() - start;
    }

    private class GMLSextanteTask implements Callable<Long> {

        private GMLJAXBContext jaxbContext;

        public GMLSextanteTask(GMLJAXBContext theJaxbContext) {
            this.jaxbContext = theJaxbContext;
        }

        @Override
        public Long call() throws Exception {
            return executeSingleTask(jaxbContext);
        }
    }
}
