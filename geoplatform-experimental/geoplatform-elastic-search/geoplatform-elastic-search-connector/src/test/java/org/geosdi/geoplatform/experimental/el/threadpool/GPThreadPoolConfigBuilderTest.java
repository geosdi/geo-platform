package org.geosdi.geoplatform.experimental.el.threadpool;

import org.geosdi.geoplatform.experimental.el.threadpool.builder.GPElasticSearchThreadPoolConfigBuilder;
import org.geosdi.geoplatform.experimental.el.threadpool.builder.GPThreadPoolConfigBuilder.GPThreadPoolConfig;
import org.geosdi.geoplatform.experimental.el.threadpool.factory.GPDecoratorThreadFactory;
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
        GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
                .threadPoolConfigBuilder().withQueueCapacity(30).build();
        Assert.assertTrue(threadPoolConfig.getQueue() instanceof LinkedBlockingDeque);
        Assert.assertEquals(30, ((BlockingDeque) threadPoolConfig.getQueue()).remainingCapacity());
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithoutQueueCapacityConfigTest() throws Exception {
        GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
                .threadPoolConfigBuilder().withQueueCapacity(null).build();
        Assert.assertTrue(threadPoolConfig.getQueue() instanceof SynchronousQueue);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithKeepAliveConfigTest() throws Exception {
        GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
                .threadPoolConfigBuilder().withKeepAlive(60000l, TimeUnit.MILLISECONDS).build();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithoutKeepAliveConfigTest() throws Exception {
        GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
                .threadPoolConfigBuilder().withKeepAlive(-1l, TimeUnit.MILLISECONDS).build();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithNullKeepAliveConfigTest() throws Exception {
        GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
                .threadPoolConfigBuilder().withKeepAlive(null, null).build();
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithThreadFactoryConfigTest() throws Exception {
        GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
                .threadPoolConfigBuilder().withThreadFactory(r -> Executors.privilegedThreadFactory().newThread(r)).build();
        Assert.assertTrue(threadPoolConfig.getThreadFactory() instanceof GPDecoratorThreadFactory);
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@THREAD_POOL_CONFIG : {}\n", threadPoolConfig);
    }

    @Test
    public void createThreadPoolWithAllParametersConfigTest() throws Exception {
        GPThreadPoolConfig threadPoolConfig = GPElasticSearchThreadPoolConfigBuilder
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
}
