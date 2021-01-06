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
package org.geosdi.geoplatform.support.primitive.bridge.store;

import org.geosdi.geoplatform.support.primitive.bridge.implementor.PrimitiveImplementor;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPPrimitiveImplementorStoreTest {

    private static final Logger logger = LoggerFactory.getLogger(GPPrimitiveImplementorStoreTest.class);
    //
    private static final PrimitiveImplementorStore<PrimitiveImplementor<?>> primitiveImplementorStore = new GPPrimitiveImplementorStore();

    @Test
    public void loadAllPrimitiveImplementorsTest() throws Exception {
        Set<PrimitiveImplementor<?>> primitiveImplementors = primitiveImplementorStore.getAllImplementors();
        logger.info("##########################PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementors.size());
    }

    @Test
    public void loadIntegerPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Integer.class);
        logger.info("###########################INTEGER_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(int.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(int[].class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Integer.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Integer[].class));
    }

    @Test
    public void loadBigDecimalPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(BigDecimal.class);
        logger.info("###########################BIG_DECIMAL_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(BigDecimal.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(BigDecimal[].class));
    }

    @Test
    public void loadBigIntegerPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(BigInteger.class);
        logger.info("###########################BIG_INTEGER_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(BigInteger.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(BigInteger[].class));
    }

    @Test
    public void loadBooleanPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Boolean.class);
        logger.info("###########################BOOLEAN_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Boolean.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Boolean[].class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(boolean.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(boolean[].class));
    }

    @Test
    public void loadBytePrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Byte.class);
        logger.info("###########################BYTE_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Byte.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Byte[].class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(byte.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(byte[].class));
    }

    @Test
    public void loadDoublePrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Double.class);
        logger.info("###########################DOUBLE_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Double.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Double[].class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(double.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(double[].class));
    }

    @Test
    public void loadFloatPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Float.class);
        logger.info("###########################FLOAT_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Float.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Float[].class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(float.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(float[].class));
    }

    @Test
    public void loadLongPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Long.class);
        logger.info("###########################LONG_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Long.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Long[].class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(long.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(long[].class));
    }

    @Test
    public void loadNumberPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Number.class);
        logger.info("###########################NUMBER_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Number.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Number[].class));
    }

    @Test
    public void loadShortPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Short.class);
        logger.info("###########################SHORT_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Short.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Short[].class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(short.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(short[].class));
    }

    @Test
    public void loadCharacterPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(Character.class);
        logger.info("###########################CHARACTER_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Character.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(Character[].class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(char.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(char[].class));
    }

    @Test
    public void loadStringPrimitiveImplementorTest() throws Exception {
        PrimitiveImplementor primitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(String.class);
        logger.info("###########################STRING_PRIMITIVE_IMPLEMENTORS : {}\n", primitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(String.class));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(String[].class));
    }

    @Test
    public void loadDateTimePrimitiveImplementorTest() throws Exception {
        DateTime dateTime = DateTime.now();
        PrimitiveImplementor dateTimePrimitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(dateTime.getClass());
        logger.info("########################DATE_TIME_IMPLEMENTOR : {}\n", dateTimePrimitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(dateTime.getClass()));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(dateTime.toLocalDateTime().getClass()));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(dateTime.toMutableDateTime().getClass()));
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(dateTime.toInstant().getClass()));
    }

    @Test
    public void loadDatePrimitiveImplementorTest() throws Exception {
        Date date = new Date();
        PrimitiveImplementor dateTimePrimitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(date.getClass());
        logger.info("########################DATE_IMPLEMENTOR : {}\n", dateTimePrimitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(date.getClass()));
    }

    @Test
    public void loadSQLDatePrimitiveImplementorTest() throws Exception {
        java.sql.Date sqlDate = new java.sql.Date(1478183430660l);
        PrimitiveImplementor dateTimePrimitiveImplementor = primitiveImplementorStore.getPrimitiveImplementorForClass(sqlDate.getClass());
        logger.info("########################SQL_DATE_IMPLEMENTOR : {}\n", dateTimePrimitiveImplementor);
        Assert.assertTrue(primitiveImplementorStore.isPrimitiveOrWrapper(sqlDate.getClass()));
    }
}
