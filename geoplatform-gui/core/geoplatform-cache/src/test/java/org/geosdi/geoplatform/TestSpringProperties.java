package org.geosdi.geoplatform;

import org.junit.Test;
import org.geosdi.geoplatform.cache.GPCacheManager;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSpringProperties {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private GPCacheManager cacheManager;

    @Test
    public void testGPCacheManager() {
        logger.info("Beaninstance: " + this.cacheManager.getDiskStorePath());
    }
}