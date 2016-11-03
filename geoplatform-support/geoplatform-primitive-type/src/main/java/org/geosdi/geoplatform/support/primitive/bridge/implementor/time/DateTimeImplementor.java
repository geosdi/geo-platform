package org.geosdi.geoplatform.support.primitive.bridge.implementor.time;

import org.joda.time.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class DateTimeImplementor extends GPTimeImplementor<DateTime> {

    public DateTimeImplementor() {
        super(DateTime.class);
    }

    /**
     * @return {@link Set < PrimitiveImplementorKey >}
     */
    @Override
    protected Set<PrimitiveImplementorKey> prepareKey() {
        return Stream.of(PrimitiveImplementorKey.forClass(DateTime.class),
                PrimitiveImplementorKey.forClass(DateTime[].class), PrimitiveImplementorKey.forClass(LocalDate.class),
                PrimitiveImplementorKey.forClass(LocalDate[].class),
                PrimitiveImplementorKey.forClass(LocalDateTime.class),
                PrimitiveImplementorKey.forClass(LocalDateTime[].class),
                PrimitiveImplementorKey.forClass(Instant.class),
                PrimitiveImplementorKey.forClass(Instant[].class),
                PrimitiveImplementorKey.forClass(MutableDateTime.class),
                PrimitiveImplementorKey.forClass(MutableDateTime[].class)).collect(Collectors.toSet());
    }
}
