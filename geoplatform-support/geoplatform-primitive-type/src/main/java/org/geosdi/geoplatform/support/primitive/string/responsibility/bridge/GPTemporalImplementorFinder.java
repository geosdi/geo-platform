package org.geosdi.geoplatform.support.primitive.string.responsibility.bridge;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPTemporalImplementorFinder {

    /**
     * @return {@link TemporalImplementor}
     */
    <TemporalImplementor extends GPTemporalPatternImplementor> GPTemporalPatternImplementor findTemporalImplementor();
}
