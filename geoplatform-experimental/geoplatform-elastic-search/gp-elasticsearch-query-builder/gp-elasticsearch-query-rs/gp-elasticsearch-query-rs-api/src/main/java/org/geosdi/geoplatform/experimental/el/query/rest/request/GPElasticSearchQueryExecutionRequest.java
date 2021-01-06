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
package org.geosdi.geoplatform.experimental.el.query.rest.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "GPElasticSearchQueryExecutionRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPElasticSearchQueryExecutionRequest implements IGPElasticSearchQueryExecutionRequest {

    private static final long serialVersionUID = -556033516816317816L;
    //
    private String queryID;
    private Map<String, Object> queryTemplateParameters;

    public GPElasticSearchQueryExecutionRequest() {
    }

    /**
     * @return {@link String}
     */
    @Override
    public String getQueryID() {
        return this.queryID;
    }

    /**
     * @param theQueryID
     */
    @Override
    public void setQueryID(String theQueryID) {
        this.queryID = theQueryID;
    }

    /**
     * @return {@link Map<String, Object>}
     */
    @Override
    public Map<String, Object> getQueryTemplateParameters() {
        return this.queryTemplateParameters;
    }

    /**
     * @param theQueryTemplateParameters
     */
    @Override
    public <V> void setQueryTemplateParameters(Map<String, V> theQueryTemplateParameters) {
        this.queryTemplateParameters = (Map<String, Object>) theQueryTemplateParameters;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                " queryID = '" + queryID + '\'' +
                ", queryTemplateParameters = " + queryTemplateParameters +
                '}';
    }
}
