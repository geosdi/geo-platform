package org.geosdi.geoplatform.support.primitive.string.responsibility.bridge;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseTemporalImplementorFinder implements GPTemporalImplementorFinder {

    /**
     * @return {@link TemporalImplementor}
     */
    @Override
    public <TemporalImplementor extends GPTemporalPatternImplementor> GPTemporalPatternImplementor findTemporalImplementor() {
        Iterator<GPTemporalPatternImplementor> it = ServiceLoader.load(GPTemporalPatternImplementor.class).iterator();
        return (it.hasNext() ? it.next() : new GPBaseTemporalPatternImplementor());
    }
}
