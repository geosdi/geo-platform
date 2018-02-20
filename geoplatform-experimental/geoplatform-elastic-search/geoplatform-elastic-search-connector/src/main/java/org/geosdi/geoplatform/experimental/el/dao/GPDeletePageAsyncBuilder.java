package org.geosdi.geoplatform.experimental.el.dao;

import org.geosdi.geoplatform.experimental.el.search.builder.IGPPageAsyncBuilder;

import static org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType.OperationAsyncType.DELETE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPDeletePageAsyncBuilder extends IGPPageAsyncBuilder.GPPageAsyncBuilder {

    private GPDeletePageAsyncBuilder() {
        super(DELETE);
    }

    /**
     * @param <AsyncBuilder>
     * @return {@link AsyncBuilder}
     */
    public static <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder deletePageAsyncBuilder() {
        return (AsyncBuilder) new GPDeletePageAsyncBuilder();
    }

    /**
     * @return {@link GPPageableAsyncElasticSearchDAO.PageAsync }
     */
    @Override
    public GPPageableAsyncElasticSearchDAO.PageAsync build() {
        return new GPPageableAsyncElasticSearchDAO.PageAsync(this.field, this.sortOrder,
                this.from, this.size, this.operationAsyncType, this.booleanSearch);
    }
}
