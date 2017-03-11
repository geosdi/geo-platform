package org.geosdi.geoplatform.support.jackson.reader;

import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.model.SimpleBean;
import org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SimpleBeanJacksonReaderTest {

    private static final GPJacksonReaderSupport<SimpleBean> JACKSON_READER_SUPPORT = new GPBaseJacksonReaderSupport<>(new GPJacksonSupport(UNWRAP_ROOT_VALUE_DISABLE,
            FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
            ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE,
            WRAP_ROOT_VALUE_DISABLE,
            INDENT_OUTPUT_ENABLE)
            .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE)
            .configure(GPJsonIncludeFeature.NON_NULL),
            SimpleBean.class);

    @Test
    public void readJsonFromURLTest() throws Exception {
        SimpleBean simpleBean = JACKSON_READER_SUPPORT.read(new URL("https://httpbin.org/get?color=red&shape=square"));
        System.out.println("" + simpleBean.getHeaders().size());
        Assert.assertNotNull(simpleBean);
        Assert.assertTrue(simpleBean.getArguments().size() == 2);
        Assert.assertTrue(simpleBean.getHeaders().size() == 3);
        Assert.assertNotNull(simpleBean.getOrigin());
        Assert.assertNotNull(simpleBean.getUrl());
    }

    @Test
    public void readJsonFromStringTest() throws Exception {
        SimpleBean simpleBean = JACKSON_READER_SUPPORT.read("{\n" +
                "  \"args\": {\n" +
                "    \"color\": \"red\", \n" +
                "    \"shape\": \"square\"\n" +
                "  }, \n" +
                "  \"headers\": {\n" +
                "    \"Accept\": \"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\", \n" +
                "    \"Accept-Encoding\": \"gzip, deflate, br\", \n" +
                "    \"Accept-Language\": \"it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3\", \n" +
                "    \"Host\": \"httpbin.org\", \n" +
                "    \"Upgrade-Insecure-Requests\": \"1\", \n" +
                "    \"User-Agent\": \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:52.0) Gecko/20100101 Firefox/52.0\"\n" +
                "  }, \n" +
                "  \"origin\": \"82.61.27.192\", \n" +
                "  \"url\": \"https://httpbin.org/get?color=red&shape=square\"\n" +
                "}");
        Assert.assertNotNull(simpleBean);
    }

    @Test
    public void readJsonFromFileTest() throws Exception {
        SimpleBean simpleBean = JACKSON_READER_SUPPORT.read(new File("./src/test/resources/simple_bean.json"));
        Assert.assertNotNull(simpleBean);
    }
}
