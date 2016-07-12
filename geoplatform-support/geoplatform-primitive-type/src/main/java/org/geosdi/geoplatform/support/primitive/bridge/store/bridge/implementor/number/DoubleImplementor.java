package org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.number;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class DoubleImplementor extends WrapperNumberImplementor<Double> {

    public DoubleImplementor() {
        super(Double.class);
    }

    /**
     * @return {@link Set<PrimitiveImplementorKey>}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Double.class), PrimitiveImplementorKey.forClass(double.class),
                PrimitiveImplementorKey.forClass(Double[].class), PrimitiveImplementorKey.forClass(double[].class))
                .collect(Collectors.toSet());
    }
}
