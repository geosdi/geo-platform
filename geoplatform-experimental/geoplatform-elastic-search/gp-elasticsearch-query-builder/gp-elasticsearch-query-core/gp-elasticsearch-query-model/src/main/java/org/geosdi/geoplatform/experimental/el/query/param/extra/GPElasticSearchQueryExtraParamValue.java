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
package org.geosdi.geoplatform.experimental.el.query.param.extra;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.geosdi.geoplatform.experimental.el.query.param.key.GPElasticSearchQueryParamKey;
import org.geosdi.geoplatform.experimental.el.query.param.key.IGPElasticSearchQueryParamKey;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryExtraParamValue implements IGPElasticSearchQueryExtraParamValue {

    private static final long serialVersionUID = 2177631736241219137L;
    //
    private Object value;
    @JsonDeserialize(as = GPElasticSearchQueryParamKey.class)
    private IGPElasticSearchQueryParamKey keyValue;

    public GPElasticSearchQueryExtraParamValue() {
    }

    /**
     * @return {@link Object}
     */
    @Override
    public Object getValue() {
        return this.value;
    }

    /**
     * @param theValue
     */
    @Override
    public void setValue(Object theValue) {
        this.value = theValue;
    }

    /**
     * @return {@link GPElasticSearchQueryParamKey}
     */
    @Override
    public IGPElasticSearchQueryParamKey getKeyValue() {
        return this.keyValue;
    }

    /**
     * @param theKeyValue
     */
    @Override
    public void setKeyValue(IGPElasticSearchQueryParamKey theKeyValue) {
        this.keyValue = theKeyValue;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " value = " + this.value +
                ", keyValue = " + this.keyValue +
                '}';
    }
}
