package org.geosdi.geoplatform.experimental.el.query.template;

import com.google.common.base.Preconditions;
import net.jcip.annotations.Immutable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryTemplate {

    IGPElasticSearchQueryTemplate DEFAULT_QUERY_TEMPLATE = new GPElasticSearchQueryTemplate("${", "}");

    /**
     * @return {@link String}
     */
    String getPrefix();

    /**
     * @return {@link String}
     */
    String getSuffix();

    /**
     *
     */
    @Immutable
    class GPElasticSearchQueryTemplate implements IGPElasticSearchQueryTemplate {

        private final String prefix;
        private final String suffix;

        public GPElasticSearchQueryTemplate(String thePrefix, String theSuffix) {
            Preconditions.checkArgument((thePrefix != null) && !(thePrefix.isEmpty()),
                    "The Parameter Prefix must not be null or an Empty String.");
            Preconditions.checkArgument((theSuffix != null) && !(theSuffix.isEmpty()),
                    "The Parameter Suffix must not be null or an Empty String.");
            this.prefix = thePrefix;
            this.suffix = theSuffix;
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


        @Override
        public String toString() {
            return getClass().getSimpleName() + "{" +
                    " prefix = '" + prefix + '\'' +
                    ", suffix = '" + suffix + '\'' +
                    '}';
        }
    }
}
