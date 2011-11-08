package org.geosdi.geoplatform;

import org.junit.Test;
import org.geosdi.geoplatform.cache.GPCacheManager;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext-Test.xml"})
public class TestSpringProperties {
    
    @Autowired
    private GPCacheManager cacheManager;

    @Test
    public void testGPCacheManager() {
        // create the spring container using the AppConfig
        // @Configuration class
//        ApplicationContext ctx =
//                new AnnotationConfigApplicationContext(GPCacheManager.class);
//        GPCacheManager bean = ctx.getBean(GPCacheManager.class);
        System.out.println("Beaninstance: " + this.cacheManager.toString());
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