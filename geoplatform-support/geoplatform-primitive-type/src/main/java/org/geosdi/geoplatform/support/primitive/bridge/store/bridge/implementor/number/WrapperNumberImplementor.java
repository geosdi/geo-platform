package org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.number;

import org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.GPPrimitiveImplementor;
import org.geosdi.geoplatform.support.primitive.bridge.store.operator.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class WrapperNumberImplementor<P extends Number> extends GPPrimitiveImplementor<P> {

    protected GPOperator.GPOperatorLoader operatorLoader;

    public WrapperNumberImplementor(Class<P> thePrimitiveClass) {
        super(thePrimitiveClass);
    }

    /**
     * @return {@link GPOperator.GPOperatorLoader}
     */
    @Override
    public GPOperator.GPOperatorLoader getOperatorLoader() {
        return this.operatorLoader = ((this.operatorLoader != null)
                ? this.operatorLoader : () -> Stream.of(new EqualToOperator(),
                new NotEqualToOperator(), new GreaterThanOperator(),
                new GreaterThanOrEqualToOperator(), new LessThanOperator(),
                new LessThanOrEqualToOperator()).collect(Collectors.toList()));
    }
}
