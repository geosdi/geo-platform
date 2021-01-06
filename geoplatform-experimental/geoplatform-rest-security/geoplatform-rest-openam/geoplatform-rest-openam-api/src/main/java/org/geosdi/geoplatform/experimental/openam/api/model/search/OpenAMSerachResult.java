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
package org.geosdi.geoplatform.experimental.openam.api.model.search;

import org.geosdi.geoplatform.experimental.openam.api.model.users.search.OpenAMSearchUsersResult;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlTransient
@XmlSeeAlso(value = {OpenAMSearchUsersResult.class})
public abstract class OpenAMSerachResult<Result> implements Serializable {

    private static final long serialVersionUID = -1528645783234609167L;
    //
    private long resultCount;
    private long pagedResultsCookie;
    private String totalPagedResultsPolicy;
    private long totalPagedResults;
    private long remainingPagedResults;

    public OpenAMSerachResult() {
    }

    /**
     * @return {@link Long}
     */
    public long getResultCount() {
        return resultCount;
    }

    /**
     * @param resultCount
     */
    public void setResultCount(long resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * @return {@link Long}
     */
    public long getPagedResultsCookie() {
        return pagedResultsCookie;
    }

    /**
     * @param pagedResultsCookie
     */
    public void setPagedResultsCookie(long pagedResultsCookie) {
        this.pagedResultsCookie = pagedResultsCookie;
    }

    /**
     * @return {@link Long}
     */
    public String getTotalPagedResultsPolicy() {
        return totalPagedResultsPolicy;
    }

    /**
     * @param totalPagedResultsPolicy
     */
    public void setTotalPagedResultsPolicy(String totalPagedResultsPolicy) {
        this.totalPagedResultsPolicy = totalPagedResultsPolicy;
    }

    /**
     * @return {@link Long}
     */
    public long getTotalPagedResults() {
        return totalPagedResults;
    }

    /**
     * @param totalPagedResults
     */
    public void setTotalPagedResults(long totalPagedResults) {
        this.totalPagedResults = totalPagedResults;
    }

    /**
     * @return {@link Long}
     */
    public long getRemainingPagedResults() {
        return remainingPagedResults;
    }

    /**
     * @return {@link Long}
     */
    public void setRemainingPagedResults(long remainingPagedResults) {
        this.remainingPagedResults = remainingPagedResults;
    }

    /**
     * @return {@link List<Result>}
     */
    public abstract List<Result> getResult();

    /**
     * @param theResult
     */
    public abstract void setResult(List<Result> theResult);

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " result = " + getResult() +
                ", resultCount = " + resultCount +
                ", pagedResultsCookie = " + pagedResultsCookie +
                ", totalPagedResultsPolicy = '" + totalPagedResultsPolicy + '\'' +
                ", totalPagedResults = " + totalPagedResults +
                ", remainingPagedResults = " + remainingPagedResults +
                '}';
    }
}
