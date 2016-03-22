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
