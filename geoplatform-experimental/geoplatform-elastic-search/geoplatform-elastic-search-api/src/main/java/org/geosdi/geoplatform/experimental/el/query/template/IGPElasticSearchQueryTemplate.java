package org.geosdi.geoplatform.experimental.el.query.template;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryTemplate {

    IGPElasticSearchQueryTemplate DEFAULT_QUERY_TEMPLATE = new GPElasticSearchQueryTemplate("${", "}",
            new Character('$'), ":-");

    /**
     * @return {@link String}
     */
    String getPrefix();

    /**
     * @return {@link String}
     */
    String getSuffix();

    /**
     * @return {@link Character}
     */
    Character getEscape();

    /**
     * @return {@link String}
     */
    String getValueDelimiter();

    @Immutable
    class GPElasticSearchQueryTemplate implements IGPElasticSearchQueryTemplate {

        private final String prefix;
        private final String suffix;
        private final Character escape;
        private final String valueDelimiter;

        public GPElasticSearchQueryTemplate(String thePrefix, String theSuffix,
                Character theEscape, String theValueDelimiter) {
            Preconditions.checkArgument((thePrefix != null) && !(thePrefix.isEmpty()),
                    "The Parameter Prefix must not be null or an Empty String.");
            Preconditions.checkArgument((theSuffix != null) && !(theSuffix.isEmpty()),
                    "The Parameter Suffix must not be null or an Empty String.");
            Preconditions.checkArgument((theEscape != null),
                    "The Parameter Escape must not be null.");
            Preconditions.checkArgument((theValueDelimiter != null) && !(theValueDelimiter.isEmpty()),
                    "The Parameter Value Delimiter must not be null or an Empty String.");
            this.prefix = thePrefix;
            this.suffix = theSuffix;
            this.escape = theEscape;
            this.valueDelimiter = theValueDelimiter;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getPrefix() {
            return this.prefix;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getSuffix() {
            return this.suffix;
        }

        /**
         * @return {@link Character}
         */
        @Override
        public Character getEscape() {
            return this.escape;
        }

        /**
         * @return {@link String}
         */
        @Override
        public String getValueDelimiter() {
            return this.valueDelimiter;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "{" +
                    " prefix = '" + prefix + '\'' +
                    ", suffix = '" + suffix + '\'' +
                    ", escape = " + escape +
                    ", valueDelimiter = '" + valueDelimiter + '\'' +
                    '}';
        }
    }
}
