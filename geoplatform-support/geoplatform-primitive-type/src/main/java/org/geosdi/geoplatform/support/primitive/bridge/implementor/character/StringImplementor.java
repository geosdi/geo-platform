package org.geosdi.geoplatform.support.primitive.bridge.implementor.character;

import org.geosdi.geoplatform.support.primitive.bridge.implementor.GPPrimitiveImplementor;
import org.geosdi.geoplatform.support.primitive.operator.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class StringImplementor extends GPPrimitiveImplementor<String> {

    private GPOperator.GPOperatorLoader operatorLoader;

    public StringImplementor() {
        super(String.class);
    }

    /**
     * @return {@link GPOperator.GPOperatorLoader}
     */
    @Override
    public GPOperator.GPOperatorLoader getOperatorLoader() {
        return this.operatorLoader = ((this.operatorLoader != null)
                ? this.operatorLoader : () -> Stream.of(new EqualToOperator(),
                new NotEqualToOperator(), new StartsWithOperator(),
                new EndsWithOperator(), new ContainsOperator())
                .collect(Collectors.toList()));
    }

    /**
     * @return {@link Set<PrimitiveImplementorKey>}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(String.class),
                PrimitiveImplementorKey.forClass(String[].class)).collect(Collectors.toSet());
    }
}
