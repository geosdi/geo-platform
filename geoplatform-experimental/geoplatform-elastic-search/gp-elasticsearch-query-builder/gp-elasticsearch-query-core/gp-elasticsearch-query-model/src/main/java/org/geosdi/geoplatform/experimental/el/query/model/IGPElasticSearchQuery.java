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
