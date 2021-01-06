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

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MassiveImplementorStoreTest extends AbstractMassiveImplementorStoreTest {

    @Test
    public void massiveLoadAllImplementorsTest() throws Exception {
        logger.info("massiveLoadAllImplementorsTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.ALL_IMPLEMENTORS)));
    }

    @Test
    public void massiveIntegerImplementorTest() throws Exception {
        logger.info("massiveIntegerImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.INTEGER)));
    }

    @Test
    public void massiveBigDecimalImplementorTest() throws Exception {
        logger.info("massiveBigDecimalImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.BIGDECIMAL)));
    }

    @Test
    public void massiveBigIntegerImplementorTest() throws Exception {
        logger.info("massiveBigIntegerImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.BIGINTEGER)));
    }

    @Test
    public void massiveBooleanImplementorTest() throws Exception {
        logger.info("massiveBooleanImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.BOOLEAN)));
    }

    @Test
    public void massiveByteImplementorTest() throws Exception {
        logger.info("massiveByteImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.BYTE)));
    }

    @Test
    public void massiveDoubleImplementorTest() throws Exception {
        logger.info("massiveDoubleImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.DOUBLE)));
    }

    @Test
    public void massiveFloatImplementorTest() throws Exception {
        logger.info("massiveFloatImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.FLOAT)));
    }

    @Test
    public void massiveLongImplementorTest() throws Exception {
        logger.info("massiveLongImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.LONG)));
    }

    @Test
    public void massiveNumberImplementorTest() throws Exception {
        logger.info("massiveNumberImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.NUMBER)));
    }

    @Test
    public void massiveShortImplementorTest() throws Exception {
        logger.info("massiveShortImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.SHORT)));
    }

    @Test
    public void massiveCharacterImplementorTest() throws Exception {
        logger.info("massiveCharacterImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.CHARACTER)));
    }

    @Test
    public void massiveStringImplementorTest() throws Exception {
        logger.info("massiveStringImplementorTest : Executed {} threads for {} Tasks in {} s \n",
                super.defineNumThreads(), super.defineNumTasks(),
                TimeUnit.MILLISECONDS.toSeconds(super.executePrimitiveTask(super.defineNumTasks(),
                        PrimitiveTaskType.STRING)));
    }
}
