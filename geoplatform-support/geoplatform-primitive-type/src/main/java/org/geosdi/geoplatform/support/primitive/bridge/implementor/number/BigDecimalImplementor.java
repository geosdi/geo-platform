package org.geosdi.geoplatform.support.primitive.bridge.implementor.number;

import org.geosdi.geoplatform.support.primitive.bridge.implementor.PrimitiveImplementor;

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
     * @return {@link Set<org.geosdi.geoplatform.support.primitive.bridge.implementor.PrimitiveImplementor.PrimitiveImplementorKey>}
     */
    @Override
    protected Set<PrimitiveImplementor.PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(BigDecimal.class),
                PrimitiveImplementorKey.forClass(BigDecimal[].class)).collect(Collectors.toSet());
    }
}
