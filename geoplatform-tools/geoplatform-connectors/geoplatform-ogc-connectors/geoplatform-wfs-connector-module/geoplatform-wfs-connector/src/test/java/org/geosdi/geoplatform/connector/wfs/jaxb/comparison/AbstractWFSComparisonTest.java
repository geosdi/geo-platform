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

import org.geosdi.geoplatform.jaxb.GPBaseJAXBContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class AbstractWFSComparisonTest extends WFSBaseComparisonTest {

    /**
     * @return {@link Integer}
     */
    protected int defineNumThreads() {
        return 150;
    }

    /**
     * @param jaxbContext
     * @param wfsTaskType
     * @return {@link Long}
     * @throws Exception
     */
    long executeMultiThreadsTasks(GPBaseJAXBContext jaxbContext, WFSTaskType wfsTaskType) throws Exception {
        long time = 0;
        int numThreads = defineNumThreads();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads, WFSComparisonThreadFactory);
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

    /**
     * @param jaxbContext
     * @param tasks
     * @param wfsTaskType
     * @param numThreads
     */
    private void fillTasksList(GPBaseJAXBContext jaxbContext, List<Callable<Long>> tasks, WFSTaskType wfsTaskType, int numThreads) {
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
}