package org.geosdi.geoplatform.support.primitive.bridge.store.massive;

import com.google.common.base.Preconditions;

import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class PrimitiveTaskFunction implements Function<Integer, Callable<Long>> {

    private final PrimitiveTaskType primitiveTaskType;

    public PrimitiveTaskFunction(PrimitiveTaskType thePrimitiveTaskType) {
        Preconditions.checkArgument(thePrimitiveTaskType != null, "The Parameter Primitive Task Type must " +
                "not be null.");
        this.primitiveTaskType = thePrimitiveTaskType;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param integer the function argument
     * @return the function result
     */
    @Override
    public Callable<Long> apply(Integer integer) {
        switch (this.primitiveTaskType) {
            case INTEGER:
                return new AbstractMassiveImplementorStoreTest.IntegerPrimitiveTask();
            case BIGDECIMAL:
                return new AbstractMassiveImplementorStoreTest.BigDecimalPrimitiveTask();
            case BIGINTEGER:
                return new AbstractMassiveImplementorStoreTest.BigIntegerPrimitiveTask();
            case BOOLEAN:
                return new AbstractMassiveImplementorStoreTest.BooleanPrimitiveTask();
            case BYTE:
                return new AbstractMassiveImplementorStoreTest.BytePrimitiveTask();
            case DOUBLE:
                return new AbstractMassiveImplementorStoreTest.DoublePrimitiveTask();
            case FLOAT:
                return new AbstractMassiveImplementorStoreTest.FloatPrimitiveTask();
            case LONG:
                return new AbstractMassiveImplementorStoreTest.LongPrimitiveTask();
            case NUMBER:
                return new AbstractMassiveImplementorStoreTest.NumberPrimitiveTask();
            case SHORT:
                return new AbstractMassiveImplementorStoreTest.ShortPrimitiveTask();
            case CHARACTER:
                return new AbstractMassiveImplementorStoreTest.CharacterPrimitiveTask();
            case STRING:
                return new AbstractMassiveImplementorStoreTest.StringPrimitiveTask();
            default:
                return new AbstractMassiveImplementorStoreTest.IntegerPrimitiveTask();
        }
    }
}
