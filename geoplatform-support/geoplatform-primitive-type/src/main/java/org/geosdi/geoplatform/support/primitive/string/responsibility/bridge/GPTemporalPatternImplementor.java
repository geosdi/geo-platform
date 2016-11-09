package org.geosdi.geoplatform.support.primitive.string.responsibility.bridge;

import java.util.Locale;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPTemporalPatternImplementor {

    /**
     * @return {@link Set<String>}
     */
    Set<String> getPatterns();

    /**
     * @param thePattern
     */
    void addPattern(String thePattern);

    /**
     * @return {@link Locale}
     */
    default Locale getLocale() {
        return Locale.getDefault();
    }
}
