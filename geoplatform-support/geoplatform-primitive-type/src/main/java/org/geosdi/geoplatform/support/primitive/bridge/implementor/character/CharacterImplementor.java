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
public class CharacterImplementor extends GPPrimitiveImplementor<Character> {

    private GPOperator.GPOperatorLoader operatorLoader;

    public CharacterImplementor() {
        super(Character.class);
    }

    /**
     * @return {@link GPOperator.GPOperatorLoader}
     */
    @Override
    public GPOperator.GPOperatorLoader getOperatorLoader() {
        return this.operatorLoader = ((this.operatorLoader != null)
                ? this.operatorLoader : () -> Stream.of(new EqualToOperator(),
                new NotEqualToOperator(), new GreaterThanOperator(),
                new GreaterThanOrEqualToOperator(), new LessThanOperator(),
                new LessThanOrEqualToOperator()).collect(Collectors.toList()));
    }

    /**
     * @return {@link Set<PrimitiveImplementorKey>}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Character.class), PrimitiveImplementorKey.forClass(char.class),
                PrimitiveImplementorKey.forClass(char[].class), PrimitiveImplementorKey.forClass(Character[].class))
                .collect(Collectors.toSet());
    }
}
