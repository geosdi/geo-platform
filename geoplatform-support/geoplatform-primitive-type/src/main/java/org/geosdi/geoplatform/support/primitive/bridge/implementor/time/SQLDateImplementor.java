package org.geosdi.geoplatform.support.primitive.bridge.implementor.time;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class SQLDateImplementor extends GPTimeImplementor<Date> {

    public SQLDateImplementor() {
        super(Date.class);
    }

    /**
     * @return {@link Set < PrimitiveImplementorKey >}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Date.class),
                PrimitiveImplementorKey.forClass(Date[].class)).collect(Collectors.toSet());
    }
}
