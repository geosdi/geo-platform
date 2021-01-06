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
package org.geosdi.geoplatform.core.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Francesco Izzi - geoSDI
 *
 */
@Embeddable
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GPLayerInfo implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7418444235711934950L;
    //
    @Column(nullable = true)
    private boolean queryable = false;
    // The character ; separated list of keywords
    @Column(length = 500)
    private String keywords;

    /**
     * @return the queryable
     */
    public boolean isQueryable() {
        return queryable;
    }

    /**
     * @param queryable the queryable to set
     */
    public void setQueryable(boolean queryable) {
        this.queryable = queryable;
    }

    /**
     * @return the keywords
     */
    public List<String> getKeywords() {
        return ((this.keywords == null) ? new ArrayList<>(0) : asList(keywords.split(";")));
    }

    /**
     * @param keywords the keywords to set
     */
    public void setKeywords(List<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            this.keywords = null;
            return;
        }

        StringBuilder str = new StringBuilder();
        for (String key : keywords) {
            str.append(key).append(";");
        }
        str.deleteCharAt(str.length() - 1);
        this.keywords = str.toString();
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GPLayerInfo {");
        str.append("queryable=").append(queryable);
        str.append(", keywords=").append(keywords).append("}");
        return str.toString();
    }
}
