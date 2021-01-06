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
package org.geosdi.geoplatform.wms;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

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
                .collect(toMap(parts -> parts[0], parts -> parts[1], (v1, v2) -> v1, LinkedHashMap::new));
        logger.info("######################HEADERS : {}\n", headers);
        List<String> values = headers.entrySet().stream()
                .map(entry -> String.join("=", entry.getKey(), entry.getValue()))
                .collect(toList());
        logger.info("######################ALL_STRING : {}\n", String.join(";", values));
    }

    @Test
    public void simpleTest() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("ke1", "value1");
        map.put("key2", "value2");
        logger.info("{}\n", map);
    }

    @Test
    public void convertStringToMapTest() throws Exception {
        String value = "{key2=value2, ke1=value1, key=value}";
        value = value.replace("{", "").replace("}", "");
        Map<String, String> map = Pattern.compile(",").splitAsStream(value)
                .map(v -> v.split("="))
                .collect(toMap(parts -> parts[0], parts -> parts[1], (v1, v2) -> v1, LinkedHashMap::new));
        logger.info("{}\n", map);
    }
}
