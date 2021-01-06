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