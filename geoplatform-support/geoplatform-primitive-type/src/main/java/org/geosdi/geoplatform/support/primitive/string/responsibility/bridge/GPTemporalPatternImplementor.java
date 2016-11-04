package org.geosdi.geoplatform.support.primitive.string.responsibility.bridge;

import java.util.Locale;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPTemporalPatternImplementor {

    /**
     * @return {@link String[]}
     */
    String[] getPatterns();

    /**
     * @return {@link Locale}
     */
    default Locale getLocale() {
        return Locale.getDefault();
    }
}
