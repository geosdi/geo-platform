package org.geosdi.geoplatform.support.primitive.bridge.implementor.number;

import org.geosdi.geoplatform.support.primitive.bridge.implementor.PrimitiveImplementor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class ShortPrimitive extends WrapperNumberImplementor<Short> {

    public ShortPrimitive() {
        super(Short.class);
    }

    /**
     * @return {@link Set< PrimitiveImplementor.PrimitiveImplementorKey >}
     */
    @Override
    protected Set<PrimitiveImplementor.PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Short.class), PrimitiveImplementorKey.forClass(short.class),
                PrimitiveImplementorKey.forClass(Short[].class), PrimitiveImplementorKey.forClass(short[].class))
                .collect(Collectors.toSet());
    }
}
