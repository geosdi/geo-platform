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
package org.geosdi.geoplatform.stax.reader.factory;

import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.lang.Boolean.TRUE;
import static java.lang.System.clearProperty;
import static java.lang.System.getProperty;
import static java.lang.System.setProperty;
import static java.util.Map.of;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static javax.xml.stream.XMLInputFactory.IS_COALESCING;
import static org.geosdi.geoplatform.stax.reader.factory.GPXMLInputFactoryJDKBuilder.XML_INPUT_FACTORY_KEY;
import static org.geosdi.geoplatform.stax.reader.factory.XMLInputFactoryJDKBuilder.JDK_BUILDER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Verifica che {@link XMLInputFactoryJDKBuilder} crei sempre la factory della JDK senza toccare la proprietà
 * di sistema {@code javax.xml.stream.XMLInputFactory}, anche con più thread insieme.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class XMLInputFactoryJDKBuilderTest {

    private static final String JDK_FACTORY = "com.sun.xml.internal.stream.XMLInputFactoryImpl";
    private static final String OTHER_FACTORY = "com.ctc.wstx.stax.WstxInputFactory";

    @After
    public void tearDown() {
        clearProperty(XML_INPUT_FACTORY_KEY);
    }

    @Test
    public void builderMustNotSetSystemProperty() {
        clearProperty(XML_INPUT_FACTORY_KEY);
        assertEquals(JDK_FACTORY, JDK_BUILDER.defaultFactory().getClass().getName());
        assertEquals(JDK_FACTORY, JDK_BUILDER.withProp(of(IS_COALESCING, TRUE)).getClass().getName());
        assertNull(getProperty(XML_INPUT_FACTORY_KEY));
    }

    @Test
    public void builderMustKeepPropertySetByOthers() {
        setProperty(XML_INPUT_FACTORY_KEY, OTHER_FACTORY);
        assertEquals(JDK_FACTORY, JDK_BUILDER.defaultFactory().getClass().getName());
        assertEquals(JDK_FACTORY, JDK_BUILDER.withProp(of(IS_COALESCING, TRUE)).getClass().getName());
        assertEquals(OTHER_FACTORY, getProperty(XML_INPUT_FACTORY_KEY));
    }

    @Test
    public void builderMustAlwaysReturnJdkFactoryWithManyThreads() throws Exception {
        ExecutorService executor = newFixedThreadPool(16);
        try {
            List<Callable<String>> tasks = new ArrayList<>();
            for (int i = 0; i < 2000; i++) {
                tasks.add((i % 2 == 0)
                        ? () -> JDK_BUILDER.defaultFactory().getClass().getName()
                        : () -> JDK_BUILDER.withProp(of(IS_COALESCING, TRUE)).getClass().getName());
            }
            for (Future<String> future : executor.invokeAll(tasks)) {
                assertEquals(JDK_FACTORY, future.get());
            }
        } finally {
            executor.shutdownNow();
        }
    }
}
