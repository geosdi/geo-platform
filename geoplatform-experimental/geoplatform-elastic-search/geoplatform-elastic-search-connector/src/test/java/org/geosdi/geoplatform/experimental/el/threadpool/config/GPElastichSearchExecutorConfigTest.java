/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.experimental.el.threadpool.config;

import jakarta.annotation.Resource;
import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-Executor-Test.xml"})
public class GPElastichSearchExecutorConfigTest {

    @GeoPlatformLog
    static Logger logger;
    //
    private static List<Callable<String>> tasks;
    //
    @Resource(name = "elasticSearchExecutor")
    private ExecutorService elasticSearchExecutor;

    @BeforeClass
    public static void beforeClass() throws Exception {
        tasks = IntStream.iterate(0, n -> n + 1)
                .limit(1000)
                .boxed()
                .map(v -> new Task())
                .collect(Collectors.toList());
    }

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(this.elasticSearchExecutor);
        Assert.assertTrue(this.elasticSearchExecutor instanceof ThreadPoolExecutor);
    }

    @Test
    public void elasticSearchExecutorTest() {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@GP_ELASTIC_SEARCH_EXECUTOR : {}\n", this.elasticSearchExecutor);
    }

    @Test
    public void printThreadPoolKeepAliveTest() {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@KEEP_ALIVE_SECONDS : {}\n",
                ((ThreadPoolExecutor) this.elasticSearchExecutor).getKeepAliveTime(TimeUnit.SECONDS));
    }

    @Test
    public void simpleTest() throws Exception {
        CompletableFuture.supplyAsync(() -> {

            try {
                return tasks;
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        }, this.elasticSearchExecutor);
    }

    static class Task implements Callable<String> {

        private static final AtomicInteger counter = new AtomicInteger(0);

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public String call() throws Exception {
            String name = Thread.currentThread().getName() + counter.incrementAndGet();
            logger.info("########################CODICE ESEGUITO {}\n", name);
            return name;
        }
    }
}
