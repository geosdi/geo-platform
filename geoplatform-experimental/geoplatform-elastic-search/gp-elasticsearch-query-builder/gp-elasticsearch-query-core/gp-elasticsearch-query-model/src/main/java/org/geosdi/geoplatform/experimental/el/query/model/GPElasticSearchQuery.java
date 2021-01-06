/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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