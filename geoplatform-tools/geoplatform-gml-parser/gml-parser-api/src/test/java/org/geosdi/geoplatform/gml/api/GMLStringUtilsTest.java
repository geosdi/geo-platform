package org.geosdi.geoplatform.gml.api;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GMLStringUtilsTest {

    private static int testDataCount = 5000;
    private static int testDataBlockCount = 100;
    private static String separator = "1234";
    private static int fixedSize = 23;
    private static Integer randomMaxSize = null;
    private static final List<String> testData = new LinkedList<String>();
    private static final List<Integer> testDataSize = new LinkedList<Integer>();

    private static final StopWatch stopWatch = new StopWatch();

    @BeforeClass
    public static void generateTestData() {
        for (int i = 0; i < testDataCount; i++) {
            String data = "";
            for (int j = 0; j < testDataBlockCount; j++) {
                int size = fixedSize;
                if (randomMaxSize != null) {
                    size = (RandomUtils.nextInt() % randomMaxSize) + 1;
                }
                data += RandomStringUtils.random(size, "ABCDEFGHIJKLMNOPQRSTUVXYZ");
                data += separator;
            }
            data += RandomStringUtils.random(23, "ABCDEFGHIJKLMNOPQRSTUVXYZ");
            testData.add(data);
            testDataSize.add(data.split(separator).length);
        }
    }

    @AfterClass
    public static void printTestSummery() {
        System.out.println(stopWatch.prettyPrint());
    }

    @Test
    public void javaSplit() throws Exception {
        Pattern p = Pattern.compile(separator);
        stopWatch.start("javaSplit");
        Iterator<Integer> ref = testDataSize.iterator();
        for (String data : testData) {
            String[] elements = p.split(data);
            assertEquals((int) ref.next(), elements.length);
            for (String element : elements) {
                assertTrue(element.length() < testDataBlockCount);
            }
        }
        stopWatch.stop();
    }

    @Test
    public void apacheCommonLangSplit() throws Exception {
        stopWatch.start("Apache Common Lang Split");
        for (String data : testData) {
            String[] elements = StringUtils.split(data, separator);
            for (String element : elements) {
                assertTrue(element.length() < testDataBlockCount);
            }
        }
        stopWatch.stop();
    }

    @Test
    public void guavaSplitterSplit() throws Exception {
        stopWatch.start("Google Guava Splitter");
        Splitter spiltter = Splitter.onPattern(separator);
        Iterator<Integer> ref = testDataSize.iterator();
        for (String data : testData) {
            Iterable<String> elements = spiltter.split(data);
            int count = 0;
            for (String element : elements) {
                count++;
                assertTrue(element.length() < testDataBlockCount);
            }
            assertEquals((int) ref.next(), count);
        }
        stopWatch.stop();
    }
}