package org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.number;

import org.geosdi.geoplatform.support.primitive.bridge.store.operator.EqualToOperator;
import org.geosdi.geoplatform.support.primitive.bridge.store.operator.GPOperator;
import org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.GPPrimitiveImplementor;
import org.geosdi.geoplatform.support.primitive.bridge.store.operator.NotEqualToOperator;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BooleanImplementor extends GPPrimitiveImplementor<Boolean> {

    private GPOperator.GPOperatorLoader operatorLoader;

    public BooleanImplementor() {
        super(Boolean.class);
    }

    /**
     * @return {@link GPOperator.GPOperatorLoader}
     */
    @Override
    public GPOperator.GPOperatorLoader getOperatorLoader() {
        return this.operatorLoader = ((this.operatorLoader != null)
                ? this.operatorLoader : () -> Stream.of(new EqualToOperator(),
                new NotEqualToOperator()).collect(Collectors.toList()));
    }

    /**
     * @return {@link Set<PrimitiveImplementorKey>}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Boolean.class), PrimitiveImplementorKey.forClass(boolean.class),
                PrimitiveImplementorKey.forClass(Boolean[].class), PrimitiveImplementorKey.forClass(boolean[].class))
                .collect(Collectors.toSet());
    }
}
