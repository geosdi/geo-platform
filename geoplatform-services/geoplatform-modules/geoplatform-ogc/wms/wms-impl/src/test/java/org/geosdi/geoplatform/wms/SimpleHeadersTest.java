package org.geosdi.geoplatform.wms;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SimpleHeadersTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleHeadersTest.class);
    //
    private static String value;

    @BeforeClass
    public static void beforeClaas() throws Exception {
        value = "key1:value1;key2:value2;key3:value3";
    }

    @Test
    public void spitTest() throws Exception {
        Map<String, String> headers = Pattern.compile(";").splitAsStream(value)
                .map(v -> v.split(":"))
                .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1], (v1, v2) -> v1, LinkedHashMap::new));
        logger.info("######################HEADERS : {}\n", headers);
        List<String> values = headers.entrySet().stream()
                .map(entry -> String.join("=", entry.getKey(), entry.getValue()))
                .collect(toList());
        logger.info("######################ALL_STRING : {}\n", String.join(";", values));
    }
}
