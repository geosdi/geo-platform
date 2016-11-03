package org.geosdi.geoplatform.support.primitive.bridge.implementor.time;

import java.util.Calendar;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class CalendarImplementor extends GPTimeImplementor<Calendar> {

    public CalendarImplementor() {
        super(Calendar.class);
    }

    /**
     * @return {@link Set < PrimitiveImplementorKey >}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(Calendar.class),
                PrimitiveImplementorKey.forClass(Calendar[].class)).collect(Collectors.toSet());
    }
}
