package org.geosdi.geoplatform.experimental.el.search.builder;

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
}
