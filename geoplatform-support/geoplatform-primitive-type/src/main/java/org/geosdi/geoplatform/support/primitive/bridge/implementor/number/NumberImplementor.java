package org.geosdi.geoplatform.support.primitive.bridge.implementor.number;

import org.geosdi.geoplatform.support.primitive.bridge.implementor.PrimitiveImplementor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class NumberImplementor extends WrapperNumberImplementor<Number> {

    public NumberImplementor() {
        super(Number.class);
    }

    /**
     * @return {@link Set<  PrimitiveImplementor.PrimitiveImplementorKey  >}
     */
    @Override
    protected Set<PrimitiveImplementor.PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Number.class),
                PrimitiveImplementorKey.forClass(Number[].class)).collect(Collectors.toSet());
    }
}
