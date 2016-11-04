package org.geosdi.geoplatform.support.primitive.string.responsibility.bridge;

import java.util.Arrays;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseTemporalPatternImplementor implements GPTemporalPatternImplementor {

    private final String[] patterns;

    public GPBaseTemporalPatternImplementor() {
        this.patterns = new String[]{"yyyy.MM.dd G 'at' HH:mm:ss z", "EEE, MMM d, ''yy", "h:mm a",
                "hh 'o''clock' a, zzzz", "K:mm a, z", "yyyyy.MMMMM.dd GGG hh:mm aaa",
                "EEE, d MMM yyyy HH:mm:ss Z", "yyMMddHHmmssZ", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                "dd.MM.yy", "H:mm", "H:mm:ss:SSS", "dd/MM/YY", "dd/MM/YYYY", "dd-MM-YY", "dd-MM-YYYY"};
    }

    /**
     * @return {@link String[]}
     */
    @Override
    public String[] getPatterns() {
        return this.patterns;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "patterns = " + Arrays.toString(patterns) +
                ", locale = " + getLocale() +
                '}';
    }
}
