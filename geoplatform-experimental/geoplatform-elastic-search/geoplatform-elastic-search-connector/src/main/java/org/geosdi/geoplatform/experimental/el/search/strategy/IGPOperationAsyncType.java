package org.geosdi.geoplatform.experimental.el.search.strategy;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPOperationAsyncType {

    enum OperationAsyncEnum implements IGPOperationAsyncType {

        DELETE_ASYNC, UPDATE_ASYNC;

    }
}
