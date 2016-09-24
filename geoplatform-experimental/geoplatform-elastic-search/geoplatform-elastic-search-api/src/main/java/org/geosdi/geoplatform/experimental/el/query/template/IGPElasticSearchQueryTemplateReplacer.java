package org.geosdi.geoplatform.experimental.el.query.template;

import com.google.common.base.Preconditions;

import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryTemplateReplacer {

    /**
     * @param queryTemplate
     * @param queryParameters
     * @param <V>
     * @return {@link String}
     */
    <V> String replace(String queryTemplate, Map<String, V> queryParameters);

    /**
     *
     */
    class GPElasticSearchQueryTemplateReplacer implements IGPElasticSearchQueryTemplateReplacer {

        private final IGPElasticSearchQueryTemplate queryTemplate;

        public GPElasticSearchQueryTemplateReplacer(IGPElasticSearchQueryTemplate theQueryTemplate) {
            Preconditions.checkArgument((theQueryTemplate != null), "The Parameter QueryTemplate must " +
                    "not be null.");
            this.queryTemplate = theQueryTemplate;
        }

        /**
         * @param queryTemplate
         * @param queryParameters
         * @return {@link String}
         * @throws Exception
         */
        @Override
        public <V> String replace(String queryTemplate, Map<String, V> queryParameters) {
            StringBuilder queryTemplateBuilder = new StringBuilder(queryTemplate);
            for (Map.Entry<String, V> entry : queryParameters.entrySet()) {
                int start;
                String pattern = this.queryTemplate.getPrefix() + entry.getKey() + this.queryTemplate.getSuffix();
                String value = entry.getValue().toString();
                while ((start = queryTemplateBuilder.indexOf(pattern)) != -1) {
                    queryTemplateBuilder.replace(start, start + pattern.length(), value);
                }
            }
            return queryTemplateBuilder.toString();
        }
    }
}
