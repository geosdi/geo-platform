package org.geosdi.geoplatform.experimental.el.dao.store;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.jcip.annotations.Immutable;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Immutable
public class PageStore implements IPageStore {

    private static final long serialVersionUID = 3727187743670579349L;
    //
    private final Long total;
    private final List results;

    @JsonCreator
    public PageStore(@JsonProperty(value = "total") Long total,
            @JsonProperty(value = "results") List results) {
        this.total = total;
        this.results = results;
    }

    /**
     * @return {@link Long}
     */
    @Override
    public Long getTotal() {
        return this.total;
    }

    /**
     * @return {@link List}
     */
    @Override
    public List getResults() {
        return this.results;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "total = " + total +
                ", results = " + results +
                '}';
    }
}
