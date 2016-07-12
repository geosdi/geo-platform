package org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.number;

import org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.PrimitiveImplementor;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class FloatImplementor extends WrapperNumberImplementor<Float> {

    public FloatImplementor() {
        super(Float.class);
    }

    /**
     * @return {@link Set<  PrimitiveImplementor.PrimitiveImplementorKey  >}
     */
    @Override
    protected Set<PrimitiveImplementor.PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Float.class), PrimitiveImplementorKey.forClass(float.class),
                PrimitiveImplementorKey.forClass(Float[].class), PrimitiveImplementorKey.forClass(float[].class))
                .collect(Collectors.toSet());
    }
}
