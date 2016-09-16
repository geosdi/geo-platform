package org.geosdi.geoplatform.experimental.el.query.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.Maps;
import org.geosdi.geoplatform.experimental.el.index.GPBaseIndexCreator.GPIndexSettings;
import org.geosdi.geoplatform.experimental.el.index.settings.GPBaseIndexSettings;
import org.geosdi.geoplatform.experimental.el.query.model.adapter.QueryExtraParametersAdapter;
import org.geosdi.geoplatform.experimental.el.query.model.adapter.QueryParametersAdapter;
import org.geosdi.geoplatform.experimental.el.query.param.extra.IGPElasticSearchQueryExtraParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.key.GPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.experimental.el.query.param.key.IGPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.experimental.el.query.param.value.GPElasticSearchQueryParamValue;
import org.geosdi.geoplatform.experimental.el.query.param.value.IGPElasticSearchQueryParamValue;
import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "gpElasticSearchQuery")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "queryName", "description", "creationDate",
        "queryTemplate", "indexSettings", "queryParameters", "queryExtraParameters"})
public class GPElasticSearchQuery implements IGPElasticSearchQuery {

    private static final long serialVersionUID = -3089952462846720695L;
    //
    private String id;
    private String queryName;
    private String description;
    private DateTime creationDate;
    private String queryTemplate;
    @JsonDeserialize(as = GPBaseIndexSettings.class)
    private GPIndexSettings indexSettings;
    @XmlJavaTypeAdapter(value = QueryParametersAdapter.class)
    private Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> queryParameters = Maps.newHashMap();
    @XmlJavaTypeAdapter(value = QueryExtraParametersAdapter.class)
    private Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryExtraParamValue> queryExtraParameters = Maps.newHashMap();

    public GPElasticSearchQuery() {
    }

    /**
     * @return {@link String} ElasticSearch ID
     */
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * @param theID
     */
    @Override
    public void setId(String theID) {
        this.id = theID;
    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public Boolean isIdSetted() {
        return ((this.id != null) && !(this.id.isEmpty()));
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getQueryName() {
        return this.queryName;
    }

    /**
     * @param theQueryName
     */
    @Override
    public void setQueryName(String theQueryName) {
        this.queryName = theQueryName;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * @param theDescription
     */
    @Override
    public void setDescription(String theDescription) {
        this.description = theDescription;
    }

    /**
     * @return {@link DateTime}
     */
    @Override
    public DateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @param theCreationDate
     */
    @Override
    public void setCreationDate(DateTime theCreationDate) {
        this.creationDate = theCreationDate;
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getQueryTemplate() {
        return this.queryTemplate;
    }

    /**
     * @param theQueryTemplate
     */
    @Override
    public void setQueryTemplate(String theQueryTemplate) {
        this.queryTemplate = theQueryTemplate;
    }

    /**
     * @return {@link IS}
     */
    @Override
    public <IS extends GPIndexSettings> IS getIndexSettings() {
        return (IS) this.indexSettings;
    }

    /**
     * @param theIndexSettings
     */
    @Override
    public <IS extends GPIndexSettings> void setIndexSettings(IS theIndexSettings) {
        this.indexSettings = theIndexSettings;
    }

    /**
     * @return {@link Map<GPElasticSearchQueryParamKey, GPElasticSearchQueryParamValue>}
     */
    @Override
    public Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> getQueryParameters() {
        return this.queryParameters;
    }

    /**
     * @param theQueryParameters
     */
    @Override
    public void setQueryParameters(Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryParamValue> theQueryParameters) {
        this.queryParameters = theQueryParameters;
    }

    /**
     * @param theQueryParameter
     */
    @Override
    public void addQueryParameter(IGPElasticSearchQueryParamValue theQueryParameter) {
        if ((theQueryParameter != null) && !(this.queryParameters.containsKey(theQueryParameter.getKeyValue())))
            this.queryParameters.put(theQueryParameter.getKeyValue(), theQueryParameter);
    }

    /**
     * @param theQueryParameter
     * @return {@link Boolean}
     */
    @Override
    public <V extends IGPElasticSearchQueryParamValue> Boolean removeQueryParameter(V theQueryParameter) {
        return (theQueryParameter != null) ? this.queryParameters.remove(theQueryParameter.getKeyValue(),
                theQueryParameter) : Boolean.FALSE;
    }

    /**
     * @return {@link Map <K, V>}
     */
    @Override
    public Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryExtraParamValue> getQueryExtraParamters() {
        return this.queryExtraParameters;
    }

    /**
     * @param theQueryExtraParameters
     */
    @Override
    public void setQueryExtraParameters(Map<IGPElasticSearchQueryParamKey, IGPElasticSearchQueryExtraParamValue> theQueryExtraParameters) {
        this.queryExtraParameters = theQueryExtraParameters;
    }

    /**
     * @param theQueryExtraParameter
     */
    @Override
    public void addQueryExtraParameter(IGPElasticSearchQueryExtraParamValue theQueryExtraParameter) {
        if ((theQueryExtraParameter != null) && !(this.queryExtraParameters.containsKey(theQueryExtraParameter.getKeyValue())))
            this.queryExtraParameters.put(theQueryExtraParameter.getKeyValue(), theQueryExtraParameter);
    }

    /**
     * @param theQueryExtraParameter
     * @return {@link Boolean}
     */
    @Override
    public <V extends IGPElasticSearchQueryExtraParamValue> Boolean removeQueryExtraParameter(V theQueryExtraParameter) {
        return (theQueryExtraParameter != null) ? this.queryExtraParameters.remove(theQueryExtraParameter.getKeyValue(),
                theQueryExtraParameter) : Boolean.FALSE;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " id = '" + id + '\'' +
                ", queryName = '" + queryName + '\'' +
                ", description = '" + description + '\'' +
                ", creationDate = " + creationDate +
                ", queryTemplate = '" + queryTemplate + '\'' +
                ", indexSettings = " + indexSettings +
                ", queryParameters = " + queryParameters +
                ", queryExtraParameters = " + queryExtraParameters +
                '}';
    }
}