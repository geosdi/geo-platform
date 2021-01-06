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
package org.geosdi.geoplatform.experimental.el.threadpool;

import org.geosdi.geoplatform.threadpool.support.builder.GPThreadPoolConfigBuilder;
import org.geosdi.geoplatform.threadpool.support.builder.GPThreadPoolSupportConfigBuilder;
import org.geosdi.geoplatform.threadpool.support.factory.GPDecoratorThreadFactory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPThreadPoolConfigBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPThreadPoolConfigBuilderTest.class);

    @Test
    public void createThreadPoolWithQueueCapacityConfigTest() throws Exception {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPThreadPoolSupportConfigBuilder
                .threadPoolConfigBuilder().withQueueCapacity(30).build();
        Assert.assertTrue(threadPoolConfig.getQueue() instanceof LinkedBlockingDeque);
        Assert.assertEquals(30, ((BlockingDeque) threadPoolConfig.getQueue()).remainingCapacity());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithoutQueueCapacityConfigTest() throws Exception {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPThreadPoolSupportConfigBuilder
                .threadPoolConfigBuilder().withQueueCapacity(null).build();
        Assert.assertTrue(threadPoolConfig.getQueue() instanceof SynchronousQueue);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithKeepAliveConfigTest() throws Exception {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPThreadPoolSupportConfigBuilder
                .threadPoolConfigBuilder().withKeepAlive(60000l, TimeUnit.MILLISECONDS).build();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithoutKeepAliveConfigTest() throws Exception {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPThreadPoolSupportConfigBuilder
                .threadPoolConfigBuilder().withKeepAlive(-1l, TimeUnit.MILLISECONDS).build();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithNullKeepAliveConfigTest() throws Exception {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPThreadPoolSupportConfigBuilder
                .threadPoolConfigBuilder().withKeepAlive(null, null).build();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithThreadFactoryConfigTest() throws Exception {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPThreadPoolSupportConfigBuilder
                .threadPoolConfigBuilder().withThreadFactory(r -> Executors.privilegedThreadFactory().newThread(r)).build();
        Assert.assertTrue(threadPoolConfig.getThreadFactory() instanceof GPDecoratorThreadFactory);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithAllParametersConfigTest() throws Exception {
        GPThreadPoolConfigBuilder.GPThreadPoolConfig threadPoolConfig = GPThreadPoolSupportConfigBuilder
                .threadPoolConfigBuilder().withThreadNamePrefix("Test-")
                .withCorePoolSize(4).withMaxPoolSize(1000)
                .withQueueCapacity(1000).withKeepAlive(60000l, TimeUnit.MILLISECONDS)
                .withIsDaemon(Boolean.TRUE).withPriority(Thread.MAX_PRIORITY)
                .withThreadFactory(r -> Executors.privilegedThreadFactory().newThread(r)).build();
        Assert.assertTrue(threadPoolConfig.getThreadFactory() instanceof GPDecoratorThreadFactory);
        Assert.assertTrue(threadPoolConfig.getThreadNamePrefix().equals("Test-"));
        Assert.assertTrue(threadPoolConfig.getCorePoolSize().equals(4));
        Assert.assertTrue(threadPoolConfig.getMaxPoolSize().equals(1000));
        Assert.assertTrue(threadPoolConfig.getQueueCapacity().equals(1000));
        Assert.assertTrue(threadPoolConfig.getKeepAlive().intValue() == 60);
        Assert.assertTrue(threadPoolConfig.isDaemon() == Boolean.TRUE);
        Assert.assertTrue(threadPoolConfig.getPriority() == Thread.MAX_PRIORITY);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void simpleTest() {
        logger.info("###############################{}", TimeUnit.SECONDS.convert(30000l, TimeUnit.MILLISECONDS));
    }
}
