package org.geosdi.geoplatform.support.primitive.bridge.implementor.number;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LongImplementor extends WrapperNumberImplementor<Long> {

    public LongImplementor() {
        super(Long.class);
    }

    /**
     * @return {@link Set<PrimitiveImplementorKey>}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Long.class), PrimitiveImplementorKey.forClass(long.class),
                PrimitiveImplementorKey.forClass(Long[].class), PrimitiveImplementorKey.forClass(long[].class))
                .collect(Collectors.toSet());
    }
}
