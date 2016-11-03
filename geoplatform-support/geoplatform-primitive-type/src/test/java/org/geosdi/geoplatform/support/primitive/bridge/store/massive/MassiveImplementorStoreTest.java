package org.geosdi.geoplatform.support.primitive.bridge.store.massive;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class MassiveImplementorStoreTest extends AbstractMassiveImplementorStoreTest {

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
