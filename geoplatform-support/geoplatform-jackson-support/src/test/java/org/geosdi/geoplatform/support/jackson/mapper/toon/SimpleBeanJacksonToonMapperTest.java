/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.support.jackson.mapper.toon;

import org.geosdi.geoplatform.support.jackson.GPJacksonSupport;
import org.geosdi.geoplatform.support.jackson.model.SimpleBean;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static dev.toonformat.jtoon.DecodeOptions.DEFAULT;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readString;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAKARTA;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.geosdi.geoplatform.support.jackson.property.GPJsonIncludeFeature.NON_NULL;
import static org.junit.Assert.*;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@FixMethodOrder(value = NAME_ASCENDING)
public class SimpleBeanJacksonToonMapperTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleBeanJacksonToonMapperTest.class);
    //
    private static final GPJacksonToonMapper<SimpleBean> JACKSON_TOON_MAPPER = new GPBaseJacksonToonMapper<>(SimpleBean.class,
            new GPJacksonSupport(JAKARTA, UNWRAP_ROOT_VALUE_DISABLE, FAIL_ON_UNKNOW_PROPERTIES_DISABLE,
                    ACCEPT_SINGLE_VALUE_AS_ARRAY_ENABLE, WRAP_ROOT_VALUE_DISABLE, INDENT_OUTPUT_ENABLE)
                    .configure(WRITE_DATES_AS_TIMESTAMPS_DISABLE)
                    .configure(NON_NULL));

    /**
     * Verifies the {@code writeAsToonValue(File, V)} fix: the value passed as argument must be
     * serialized, NOT the mapper's {@code entityClass}. Before the fix the file contained the
     * serialized {@link Class} instead of the bean.
     */
    @Test
    public void a_writeAsToonValueToFileTest() throws Exception {
        File file = File.createTempFile("simple-bean-value", ".toon");
        file.deleteOnExit();

        JACKSON_TOON_MAPPER.writeAsToonValue(file, simpleBean());

        String content = readString(file.toPath(), UTF_8);
        logger.info("@@@@@@@@@@@@@@@@@@WRITE_AS_TOON_VALUE_FILE_CONTENT :\n{}\n", content);

        assertTrue("The file must contain the serialized bean value", content.contains("gp-toon-node"));
        assertFalse("The file must NOT contain the serialized entityClass", content.contains("SimpleBean.class"));
        assertFalse("The file must NOT contain the entityClass fully qualified name", content.contains("org.geosdi.geoplatform.support.jackson.model.SimpleBean"));
    }

    /**
     * Verifies the {@code read(File, DecodeOptions)} fix: reading a TOON file must round-trip
     * correctly (the internally opened stream is now properly closed via commons-io FileUtils).
     */
    @Test
    public void b_readFromFileTest() throws Exception {
        File file = File.createTempFile("simple-bean-roundtrip", ".toon");
        file.deleteOnExit();

        SimpleBean expected = simpleBean();
        JACKSON_TOON_MAPPER.writeAsToon(file, expected);
        logger.info("@@@@@@@@@@@@@@@@@@TOON_FILE_CONTENT :\n{}\n", readString(file.toPath(), UTF_8));

        SimpleBean actual = JACKSON_TOON_MAPPER.read(file, DEFAULT);
        assertNotNull("Reading the TOON file must not return null", actual);
        assertEquals(expected.getOrigin(), actual.getOrigin());
        assertEquals(expected.getUrl(), actual.getUrl());
        logger.info("@@@@@@@@@@@@@@@@@@READ_FROM_FILE_RESULT : {}\n", actual);
    }

    /**
     * @return {@link SimpleBean}
     */
    static SimpleBean simpleBean() {
        SimpleBean simpleBean = new SimpleBean();
        Map<String, String> args = new LinkedHashMap<>();
        args.put("color", "red");
        args.put("shape", "square");
        simpleBean.setArguments(args);
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Host", "gp-toon-node");
        simpleBean.setHeaders(headers);
        simpleBean.setOrigin("gp-toon-node");
        simpleBean.setUrl("gp-toon-node");
        return simpleBean;
    }
}