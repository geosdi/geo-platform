package org.geosdi.geoplatform.experimental.el.search.builder;

import org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPDeletePageAsyncBuilder extends IGPPageAsyncBuilder.GPPageAsyncBuilder {

    private GPDeletePageAsyncBuilder() {
        super(IGPOperationAsyncType.OperationAsyncType.DELETE);
    }

    public static <AsyncBuilder extends IGPPageAsyncBuilder> AsyncBuilder gpDeletePageAsyncBuilder() {
        return (AsyncBuilder) new GPDeletePageAsyncBuilder();
    }

}
