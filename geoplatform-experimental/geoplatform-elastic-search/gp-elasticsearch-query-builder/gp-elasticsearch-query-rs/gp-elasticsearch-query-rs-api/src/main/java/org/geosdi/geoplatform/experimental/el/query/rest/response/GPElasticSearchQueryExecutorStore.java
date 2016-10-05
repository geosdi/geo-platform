package org.geosdi.geoplatform.experimental.el.query.rest.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@XmlRootElement(name = "GPElasticSearchQueryExecutorStore")
@XmlAccessorType(XmlAccessType.FIELD)
public class GPElasticSearchQueryExecutorStore<V extends Object> implements IGPElasticSearchQueryExecutorStore<V> {

    private static final long serialVersionUID = -1424696684053893265L;
    //
    private Long total;
    private Collection<V> executionResults;

    public GPElasticSearchQueryExecutorStore() {
    }

    public GPElasticSearchQueryExecutorStore(Long total, Collection<V> executionResults) {
        this.total = total;
        this.executionResults = executionResults;
    }

    /**
     * @return {@link Long}
     */
    @Override
    public Long getTotal() {
        return this.total;
    }

    /**
     * @param theTotal
     */
    @Override
    public void setTotal(Long theTotal) {
        this.total = theTotal;
    }

    /**
     * @return {@link Collection<V>}
     */
    @Override
    public Collection<V> getExecutionResults() {
        return this.executionResults;
    }

    /**
     * @param theExecutionResults
     */
    @Override
    public void setExecutionResults(Collection<V> theExecutionResults) {
        this.executionResults = theExecutionResults;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                " total = " + total +
                ", executionResults = " + executionResults +
                '}';
    }
}
