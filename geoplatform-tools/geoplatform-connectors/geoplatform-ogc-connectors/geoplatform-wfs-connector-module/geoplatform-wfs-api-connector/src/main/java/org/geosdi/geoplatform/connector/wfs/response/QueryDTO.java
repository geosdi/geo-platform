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
package org.geosdi.geoplatform.connector.wfs.response;

import org.geosdi.geoplatform.gui.shared.wfs.OperatorType;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@XmlRootElement(name = "QueryDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class QueryDTO implements Serializable {

    private static final long serialVersionUID = 8374546992812250827L;
    //
    private String matchOperator;
    @XmlElementWrapper(name = "queryRestrictionList")
    @XmlElement(name = "queryRestriction")
    private List<QueryRestrictionDTO> queryRestrictionList;

    public QueryDTO() {
    }

    /**
     * @return {@link String}
     */
    public String getMatchOperator() {
        return matchOperator = ((this.matchOperator != null) && !(this.matchOperator.isEmpty()) ?
                this.matchOperator : OperatorType.EQUAL.toString());
    }

    /**
     * @param theMatchOperator
     */
    public void setMatchOperator(String theMatchOperator) {
        this.matchOperator = theMatchOperator;
    }

    /**
     * @return {@link List<QueryRestrictionDTO>}
     */
    public List<QueryRestrictionDTO> getQueryRestrictionList() {
        return queryRestrictionList;
    }

    /**
     * @param theQueryRestrictionList
     */
    public void setQueryRestrictionList(List<QueryRestrictionDTO> theQueryRestrictionList) {
        this.queryRestrictionList = theQueryRestrictionList;
    }

    /**
     * @return {@link Boolean}
     */
    public boolean isSetQueryRestrictionList() {
        return ((this.queryRestrictionList != null) && !(this.queryRestrictionList.isEmpty()));
    }

    @Override
    public String toString() {
        return "QueryDTO {"
                + "matchOperator = " + matchOperator
                + ", queryRestrictionList = " + queryRestrictionList +
                '}';
    }
}