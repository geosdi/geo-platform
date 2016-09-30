package org.geosdi.geoplatform.experimental.el.query.rest.request;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQueryExecutionRequest extends Serializable {

    /**
     * @return {@link String}
     */
    String getQueryID();

    /**
     * @param theQueryID
     */
    void setQueryID(String theQueryID);

    /**
     * @param <V>
     * @return {@link Map<String, V>}
     */
    <V> Map<String, V> getQueryTemplateParameters();

    /**
     * @param theQueryTemplateParameters
     * @param <V>
     */
    <V> void setQueryTemplateParameters(Map<String, V> theQueryTemplateParameters);

    /**
     * @return {@link String}
     */
    default String getKeyValidator() {
        return null;
    }
}
