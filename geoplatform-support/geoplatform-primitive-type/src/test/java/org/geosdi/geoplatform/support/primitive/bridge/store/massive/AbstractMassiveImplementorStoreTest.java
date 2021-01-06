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
package org.geosdi.geoplatform.support.primitive.bridge.store.massive;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.support.primitive.bridge.implementor.PrimitiveImplementor;
import org.geosdi.geoplatform.support.primitive.bridge.store.GPPrimitiveImplementorStore;
import org.geosdi.geoplatform.support.primitive.bridge.store.PrimitiveImplementorStore;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractMassiveImplementorStoreTest {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractMassiveImplementorStoreTest.class);
    //
    protected static final ThreadFactory PrimitiveImplementorThreadFactory = new ThreadFactory() {

        private final AtomicInteger threadID = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.privilegedThreadFactory().newThread(r);
            thread.setName("PrimitiveImplementorThread - " + threadID.getAndIncrement());
            thread.setDaemon(Boolean.TRUE);
            return thread;
        }
    };
    private static final PrimitiveImplementorStore<PrimitiveImplementor<?>> primitiveImplementorStore = new GPPrimitiveImplementorStore();

    /**
     * @return int
     */
    protected int defineNumThreads() {
        return 10;
    }

    /**
     * @return int
     */
    protected int defineNumTasks() {
        return 300;
    }

    /**
     * @param numbers
     * @param primitiveTaskType
     * @return {@link Collection<Callable<Long>}
     */
    protected Collection<Callable<Long>> createPrimitiveTask(int numbers, PrimitiveTaskType primitiveTaskType) {
        Preconditions.checkArgument((numbers > 0), "The Number of Users must be greather than 0.");
        return IntStream.iterate(0, n -> n + 1)
                .limit(numbers)
                .boxed()
                .map(new PrimitiveTaskFunction(primitiveTaskType)).collect(Collectors.toList());
    }

    /**
     * @param numbers
     * @param taskType
     * @return {@link Long}
     * @throws Exception
     */
    protected Long executePrimitiveTask(int numbers, PrimitiveTaskType taskType) throws Exception {
        long time = 0;
        ExecutorService executor = Executors.newFixedThreadPool(defineNumThreads(), PrimitiveImplementorThreadFactory);
        Collection<Callable<Long>> tasks = this.createPrimitiveTask(numbers, taskType);
        List<Future<Long>> results = executor.invokeAll(tasks);
        executor.shutdown();
        boolean flag = executor.awaitTermination(5, TimeUnit.SECONDS);

        if (flag) {
            for (Future<Long> future : results) {
                time += future.get();
            }
        } else {
            throw new InterruptedException("Some Threads are not executed.");
        }
        return time;
    }

    static class AllImplementorsTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            Set<PrimitiveImplementor<?>> implementors = primitiveImplementorStore.getAllImplementors();
            logger.info("###########################SIZE_IMPLEMENTORS : {}\n", implementors.size());
            return System.currentTimeMillis() - start;
        }
    }

    static class IntegerPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Integer.class);
            logger.info("###########################INTEGER_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(int.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(int[].class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Integer.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Integer[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class BigDecimalPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(BigDecimal.class);
            logger.info("###########################BIG_DECIMAL_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(BigDecimal.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(BigDecimal[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class BigIntegerPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(BigInteger.class);
            logger.info("###########################BIG_INTEGER_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(BigInteger.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(BigInteger[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class BooleanPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Boolean.class);
            logger.info("###########################BOOLEAN_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Boolean.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Boolean[].class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(boolean.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(boolean[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class BytePrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Byte.class);
            logger.info("###########################BYTE_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Byte.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Byte[].class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(byte.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(byte[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class DoublePrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Double.class);
            logger.info("###########################DOUBLE_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Double.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Double[].class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(double.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(double[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class FloatPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Float.class);
            logger.info("###########################FLOAT_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Float.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Float[].class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(float.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(float[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class LongPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Long.class);
            logger.info("###########################LONG_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Long.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Long[].class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(long.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(long[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class NumberPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Number.class);
            logger.info("###########################NUMBER_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Number.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Number[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class ShortPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Short.class);
            logger.info("###########################SHORT_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Short.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Short[].class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(short.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(short[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class CharacterPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Character.class);
            logger.info("###########################CHARACTER_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Character.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Character[].class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(char.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(char[].class));
            return System.currentTimeMillis() - start;
        }
    }

    static class StringPrimitiveTask implements Callable<Long> {

        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call() throws Exception {
            long start = System.currentTimeMillis();
            PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(String.class);
            logger.info("###########################STRING_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(String.class));
            Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(String[].class));
            return System.currentTimeMillis() - start;
        }
    }
}
