package org.geosdi.geoplatform.support.primitive.bridge.implementor.number;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class IntegerImplementor extends WrapperNumberImplementor<Integer> {

    public IntegerImplementor() {
        super(Integer.class);
    }

    /**
     * @return {@link Set<PrimitiveImplementorKey>}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Integer.class), PrimitiveImplementorKey.forClass(int.class),
                PrimitiveImplementorKey.forClass(Integer[].class), PrimitiveImplementorKey.forClass(int[].class))
                .collect(Collectors.toSet());
    }
}
