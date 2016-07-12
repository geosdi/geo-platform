package org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.number;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BigDecimalImplementor extends WrapperNumberImplementor<BigDecimal> {

    public BigDecimalImplementor() {
        super(BigDecimal.class);
    }

    /**
     * @return {@link Set<PrimitiveImplementorKey>}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(BigDecimal.class),
                PrimitiveImplementorKey.forClass(BigDecimal[].class)).collect(Collectors.toSet());
    }
}
