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
package org.geosdi.geoplatform.support.jackson;

import org.geosdi.geoplatform.support.jackson.builder.JacksonSupportBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Boolean.TRUE;
import static java.util.Locale.*;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static org.geosdi.geoplatform.support.jackson.builder.JacksonSupportBuilder.GPJacksonSupportBuilder.builder;
import static org.geosdi.geoplatform.support.jackson.property.GPJacksonSupportEnum.*;
import static org.junit.jupiter.api.Assertions.*;
import static tools.jackson.databind.DeserializationFeature.UNWRAP_ROOT_VALUE;

/**
 * Tests focused on the thread-safe builder ({@code GPJacksonSupportThreadSafeBuilder}):
 * per-thread isolation of the mutating operations, absence of state leakage into the shared
 * root builder, and propagation of every configured field (including coercion) through the
 * copy-on-write chain.
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class GPJacksonSupportThreadSafeBuilderTest {

    private static final Logger logger = LoggerFactory.getLogger(GPJacksonSupportThreadSafeBuilderTest.class);

    /**
     * Two concurrent chains starting from the SAME shared thread-safe builder must produce mappers
     * with independent config features (not just an independent Locale).
     */
    @Order(value = 0)
    @Test
    public void a_concurrentConfigFeatureIsolationTest() throws Exception {
        JacksonSupportBuilder sharedBuilder = builder(TRUE);

        Callable<JacksonSupport> task1 = () -> sharedBuilder
                .withLocale(ITALY)
                .configure(UNWRAP_ROOT_VALUE_ENABLE)
                .build();

        Callable<JacksonSupport> task2 = () -> sharedBuilder
                .withLocale(FRANCE)
                .configure(UNWRAP_ROOT_VALUE_DISABLE)
                .build();

        try (ExecutorService executor = newFixedThreadPool(2)) {
            Future<JacksonSupport> future1 = executor.submit(task1);
            Future<JacksonSupport> future2 = executor.submit(task2);

            JacksonSupport support1 = future1.get();
            JacksonSupport support2 = future2.get();

            assertEquals(ITALY, support1.getDefaultMapper().serializationConfig().getLocale());
            assertEquals(FRANCE, support2.getDefaultMapper().serializationConfig().getLocale());
            assertTrue(support1.getDefaultMapper().deserializationConfig().isEnabled(UNWRAP_ROOT_VALUE),
                    "task1 mapper must have UNWRAP_ROOT_VALUE enabled");
            assertFalse(support2.getDefaultMapper().deserializationConfig().isEnabled(UNWRAP_ROOT_VALUE),
                    "task2 mapper must have UNWRAP_ROOT_VALUE disabled");
        }
    }

    /**
     * A configure() applied in a chain must NOT leak into the shared root builder: a subsequent
     * minimal chain from the same shared builder must fall back to the Jackson default.
     */
    @Order(value = 1)
    @Test
    public void b_sharedBuilderNotPollutedTest() throws Exception {
        JacksonSupportBuilder sharedBuilder = builder(TRUE);

        JacksonSupport polluting = sharedBuilder
                .withLocale(US)
                .configure(UNWRAP_ROOT_VALUE_ENABLE)
                .build();
        assertTrue(polluting.getDefaultMapper().deserializationConfig().isEnabled(UNWRAP_ROOT_VALUE));

        JacksonSupport fresh = sharedBuilder
                .withLocale(UK)
                .build();

        assertFalse(fresh.getDefaultMapper().deserializationConfig().isEnabled(UNWRAP_ROOT_VALUE),
                "The shared builder must not retain the UNWRAP_ROOT_VALUE feature set by a previous chain");
    }

    /**
     * The coercion consumer must actually reach {@code build()} through the copy-on-write chain.
     * A side-effecting consumer lets us verify it without depending on the coercion API details:
     * if the copy constructor drops it, the consumer is never invoked.
     */
    @Order(value = 2)
    @Test
    public void c_coercionConfigPropagatedTest() throws Exception {
        JacksonSupportBuilder sharedBuilder = builder(TRUE);
        AtomicInteger coercionInvocations = new AtomicInteger(0);

        JacksonSupport support = sharedBuilder
                .withLocale(ITALY)
                .withAllCoercionConfigFeature(cfg -> coercionInvocations.incrementAndGet())
                .build();

        assertNotNull(support);
        logger.info("@@@@@@@@@@@@@@@@@@COERCION_INVOCATIONS : {}\n", coercionInvocations.get());
        assertEquals(1, coercionInvocations.get(),
                "The coercion consumer must be applied exactly once at build time");
    }

    /**
     * Fragile path: invoking a mutating method OTHER than withLocale as the very first operation
     * directly on the shared instance. If that mutation leaks into the shared root, a subsequent
     * independent chain will be polluted.
     */
    @Order(value = 3)
    @Test
    public void d_mutatorFirstOnSharedBuilderTest() throws Exception {
        JacksonSupportBuilder sharedBuilder = builder(TRUE);

        // First operation on the shared instance is configure(...) (NOT withLocale).
        sharedBuilder.configure(UNWRAP_ROOT_VALUE_ENABLE);

        // A completely independent chain that never asks for UNWRAP_ROOT_VALUE.
        JacksonSupport fresh = sharedBuilder
                .withLocale(GERMANY)
                .build();

        boolean polluted = fresh.getDefaultMapper().deserializationConfig().isEnabled(UNWRAP_ROOT_VALUE);
        logger.info("@@@@@@@@@@@@@@@@@@FRESH_MAPPER_UNWRAP_ROOT_VALUE (mutator-first) : {}\n", polluted);
        assertFalse(polluted,
                "A mutator invoked first on the shared instance must not leak into subsequent chains");
    }

    /**
     * Same fragile path but with a NON-configure mutator ({@code withTimeZone}) as the very first
     * operation on the shared instance, and NO withLocale before it. Demonstrates the leak was not
     * specific to configure.
     */
    @Order(value = 4)
    @Test
    public void e_withTimeZoneFirstOnSharedBuilderTest() throws Exception {
        JacksonSupportBuilder sharedBuilder = builder(TRUE);

        // First operation on the shared instance is withTimeZone(...) (NOT withLocale).
        sharedBuilder.withTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Tokyo")));

        // A completely independent chain that never sets Asia/Tokyo.
        JacksonSupport fresh = sharedBuilder
                .withLocale(GERMANY)
                .build();

        String freshTimeZone = fresh.getDefaultMapper().serializationConfig().getTimeZone().getID();
        logger.info("@@@@@@@@@@@@@@@@@@FRESH_MAPPER_TIMEZONE (withTimeZone-first) : {}\n", freshTimeZone);
        assertNotEquals("Asia/Tokyo", freshTimeZone,
                "withTimeZone invoked first on the shared instance must not leak into subsequent chains");
    }

    /**
     * Counts the forks by reference identity (no production-side counter needed): copy-on-first-write
     * must fork EXACTLY ONCE per chain. The first mutator on the frozen root returns a new instance;
     * every subsequent mutator reuses the same (unfrozen) instance. A second, independent chain from
     * the same root forks its own distinct copy (the root is never mutated).
     */
    @Order(value = 5)
    @Test
    public void f_forkOncePerChainTest() throws Exception {
        JacksonSupportBuilder sharedBuilder = builder(TRUE);

        // First mutator on the frozen root -> ONE fork (a brand new instance).
        JacksonSupportBuilder afterLocale = sharedBuilder.withLocale(ITALY);
        assertNotSame(sharedBuilder, afterLocale,
                "the first mutator must fork a copy off the frozen root");

        // Every subsequent mutator reuses the same (unfrozen) instance -> NO further forks.
        JacksonSupportBuilder afterConfigure = afterLocale.configure(UNWRAP_ROOT_VALUE_ENABLE);
        JacksonSupportBuilder afterTimeZone = afterConfigure.withTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Rome")));
        assertSame(afterLocale, afterConfigure,
                "configure() after the first fork must mutate in place, not fork again");
        assertSame(afterLocale, afterTimeZone,
                "withTimeZone() after the first fork must mutate in place, not fork again");

        // A second, independent chain from the same root forks its OWN distinct copy.
        JacksonSupportBuilder otherChain = sharedBuilder.withLocale(FRANCE);
        assertNotSame(sharedBuilder, otherChain,
                "another chain must fork its own copy off the root");
        assertNotSame(afterLocale, otherChain,
                "distinct chains must not share the same forked instance");

        logger.info("@@@@@@@@@@@@@@@@@@FORK_IDENTITIES root={} chain1={} chain2={}\n",
                System.identityHashCode(sharedBuilder), System.identityHashCode(afterLocale), System.identityHashCode(otherChain));
    }
}