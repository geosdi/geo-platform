package org.geosdi.geoplatform.experimental.el.dao;

import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.experimental.el.api.model.Document;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class PageResult<D extends Document> implements GPElasticSearchDAO.IPageResult<D> {

    private final Long total;
    private final List<D> results;

    public PageResult(Long theTotal, List<D> theResults) {
        this.total = theTotal;
        this.results = theResults;
    }

    /**
     * <p>The Total Results Number</p>
     *
     * @return {@link Long}
     */
    @Override
    public Long getTotal() {
        return this.total;
    }

    /**
     * @return {@link List <D>}
     */
    @Override
    public List<D> getResults() {
        return this.results;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  total = " + total +
                ", results = " + results +
                '}';
    }
}
