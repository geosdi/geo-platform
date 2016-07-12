package org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.number;

import java.math.BigInteger;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BigIntegerImplementor extends WrapperNumberImplementor<BigInteger> {

    public BigIntegerImplementor() {
        super(BigInteger.class);
    }

    /**
     * @return {@link Set<PrimitiveImplementorKey>}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(BigInteger.class),
                PrimitiveImplementorKey.forClass(BigInteger[].class)).collect(Collectors.toSet());
    }
}
