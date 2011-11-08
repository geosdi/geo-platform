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
@ContextConfiguration(locations = {"applicationContext-Test.xml"})
public class TestSpringProperties {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private GPCacheManager cacheManager;

    @Test
    public void testGPCacheManager() {
        // create the spring container using the AppConfig
        // @Configuration class
//        ApplicationContext ctx =
//                new AnnotationConfigApplicationContext(GPCacheManager.class);
//        GPCacheManager bean = ctx.getBean(GPCacheManager.class);
        logger.info("Beaninstance ***************************: " + this.cacheManager.getCacheManager().getDiskStorePath());
//        assertThat(jetBean.getName(), equalTo("Gulf Stream G550"));
//        URL gulfstream;
//        try {
//            gulfstream =
//                    new URL("http://www.gulfstream.com/products/g550/");
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            fail("error creating URL");
//            throw new RuntimeException("error creating URL");
//        }
//        assertThat(jetBean.getUrl(), equalTo(gulfstream));
    }
}