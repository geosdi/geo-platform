/**
 *
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.support.jackson;

import org.geosdi.geoplatform.support.jackson.builder.JacksonSupportBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.lang.Boolean.TRUE;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.DEFAULT;
import static org.geosdi.geoplatform.support.jackson.annotation.JacksonXmlAnnotationIntrospectorBuilder.JAXB;
import static org.geosdi.geoplatform.support.jackson.builder.JacksonSupportBuilder.GPJacksonSupportBuilder.builder;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class GPJacksonSupportBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPJacksonSupportBuilderTest.class);
    //
    private static final JacksonSupportBuilder sharedBuilder = builder(TRUE);

    @Order(value = 0)
    @Test
    public void a_threadLocalScopedValueIsolationTest() throws Exception {
        Callable<JacksonSupport> task1 = () -> sharedBuilder
                .withLocale(Locale.ITALY)
                .withDateFormat(new SimpleDateFormat("dd/MM/yyyy"))
                .withTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Rome")))
                .withIntespectorBuilder(DEFAULT)
                .configure(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT_DISABLE, UNWRAP_ROOT_VALUE_ENABLE)
                .build();

        Callable<JacksonSupport> task2 = () -> sharedBuilder
                .withLocale(Locale.FRANCE)
                .withTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Paris")))
                .withDateFormat(new SimpleDateFormat("yyyy/MM/dd"))
                .withIntespectorBuilder(JAXB)
                .configure(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT_ENABLE, UNWRAP_ROOT_VALUE_DISABLE)
                .build();

        try (ExecutorService executor = newFixedThreadPool(2)) {
            Future<JacksonSupport> future1 = executor.submit(task1);
            Future<JacksonSupport> future2 = executor.submit(task2);

            JacksonSupport jacksonSupport1 = future1.get();
            JacksonSupport jacksonSupport2 = future2.get();

            assertEquals(Locale.ITALY, jacksonSupport1.getDefaultMapper().serializationConfig().getLocale());
            assertEquals(Locale.FRANCE, jacksonSupport2.getDefaultMapper().serializationConfig().getLocale());
        }
    }

    @Order(value = 1)
    @Test
    public void b_threadLocalScopedValueIsolationTest() throws Exception {
        Callable<JacksonSupportBuilder> task1 = () -> sharedBuilder
                .withLocale(Locale.CANADA)
                .withDateFormat(new SimpleDateFormat("dd/MM/yyyy"))
                .withTimeZone(TimeZone.getTimeZone(ZoneId.of("America/Vancouver")))
                .withIntespectorBuilder(DEFAULT)
                .configure(FAIL_ON_NULL_FOR_PRIMITIVES_ENABLE, UNWRAP_ROOT_VALUE_ENABLE);

        Callable<JacksonSupportBuilder> task2 = () -> sharedBuilder
                .withLocale(Locale.CHINA)
                .withTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Shanghai")))
                .withDateFormat(new SimpleDateFormat("yyyy/MM/dd"))
                .withIntespectorBuilder(JAXB)
                .configure(FAIL_ON_NULL_FOR_PRIMITIVES_DISABLE, UNWRAP_ROOT_VALUE_DISABLE);

        try (ExecutorService executor = newFixedThreadPool(2)) {
            Future<JacksonSupportBuilder> future1 = executor.submit(task1);
            Future<JacksonSupportBuilder> future2 = executor.submit(task2);

            JacksonSupportBuilder builder1 = future1.get();
            JacksonSupportBuilder builder2 = future2.get();

            JacksonSupport jacksonSupport1 = builder1.withLocale(Locale.UK).configure(AUTO_CLOSE_SOURCE_ENABLE).build();
            JacksonSupport jacksonSupport2 = builder2.withLocale(Locale.GERMANY).build();

            assertEquals(Locale.UK, jacksonSupport1.getDefaultMapper().serializationConfig().getLocale());
            assertEquals(Locale.GERMANY, jacksonSupport2.getDefaultMapper().serializationConfig().getLocale());
        }
    }
}