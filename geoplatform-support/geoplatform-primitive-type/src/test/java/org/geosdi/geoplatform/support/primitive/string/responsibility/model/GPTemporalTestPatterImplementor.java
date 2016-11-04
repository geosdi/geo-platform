package org.geosdi.geoplatform.support.primitive.string.responsibility.model;

import org.geosdi.geoplatform.support.primitive.string.responsibility.bridge.GPBaseTemporalPatternImplementor;

import java.util.Locale;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPTemporalTestPatterImplementor extends GPBaseTemporalPatternImplementor {

    /**
     * @return {@link Locale}
     */
    @Override
    public Locale getLocale() {
        return Locale.ENGLISH;
    }
}
