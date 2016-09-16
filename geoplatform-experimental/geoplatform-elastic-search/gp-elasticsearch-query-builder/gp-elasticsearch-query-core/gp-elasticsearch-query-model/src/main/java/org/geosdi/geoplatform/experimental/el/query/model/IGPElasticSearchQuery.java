package org.geosdi.geoplatform.experimental.el.query.model;

import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator;
import org.geosdi.geoplatform.experimental.el.query.param.extra.IGPElasticSearchQueryExtraParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.key.IGPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.experimental.el.query.param.value.IGPElasticSearchQueryParamValue;
import org.joda.time.DateTime;

import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IGPElasticSearchQuery extends Document {

    /**
     * @return {@link String}
     */
    String getQueryName();

    /**
     * @param theQueryName
     */
    void setQueryName(String theQueryName);

    /**
     * @return {@link String}
     */
    String getDescription();

    /**
     * @param theDescription
     */
    void setDescription(String theDescription);

    /**
     * @return {@link DateTime}
     */
    DateTime getCreationDate();

    /**
     * @param theCreationDate
     */
    void setCreationDate(DateTime theCreationDate);

    /**
     * @return {@link String}
     */
    String getQueryTemplate();

    /**
     * @param theQueryTemplate
     */
    void setQueryTemplate(String theQueryTemplate);

    /**
     * @param <IS>
     * @return {@link IS}
     */
    <IS extends GPBaseIndexCreator.GPIndexSettings> IS getIndexSettings();

    /**
     * @param theIndexSettings
     * @param <IS>
     */
    <IS extends GPBaseIndexCreator.GPIndexSettings> void setIndexSettings(IS theIndexSettings);

    /**
     * @param <K>
     * @param <V>
     * @return {@link Map<K, V>}
     */
    <K extends IGPElasticSearchQueryParamKey, V extends IGPElasticSearchQueryParamValue> Map<K, V> getQueryParameters();

    /**
     * @param theQueryParameters
     */
    void setQueryParameters(Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> theQueryParameters);

    /**
     * @param theQueryParameter
     * @param <V>
     */
    <V extends IGPElasticSearchQueryParamValue> void addQueryParameter(V theQueryParameter);

    /**
     * @param theQueryParameter
     * @param <V>
     * @return {@link Boolean}
     */
    <V extends IGPElasticSearchQueryParamValue> Boolean removeQueryParameter(V theQueryParameter);

    /**
     * @param <K>
     * @param <V>
     * @return {@link Map<K, V>}
     */
    <K extends IGPElasticSearchQueryParamKey, V extends IGPElasticSearchQueryExtraParamValue> Map<K, V> getQueryExtraParamters();

    /**
     * @param theQueryExtraParameters
     */
    void setQueryExtraParameters(Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryExtraParamValue> theQueryExtraParameters);

    /**
     * @param theQueryExtraParameter
     * @param <V>
     */
    <V extends IGPElasticSearchQueryExtraParamValue> void addQueryExtraParameter(V theQueryExtraParameter);

    /**
     * @param theQueryExtraParameter
     * @param <V>
     * @return {@link Boolean}
     */
    <V extends IGPElasticSearchQueryExtraParamValue> Boolean removeQueryExtraParameter(V theQueryExtraParameter);
}
