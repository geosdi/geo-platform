package org.geosdi.geoplatform.support.async.spring.task;

import java.util.concurrent.Future;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPAsyncTask<S extends Object, R extends Object> {

    /**
     * @param source
     * @return {@link R}
     * @throws Exception
     */
    Future<R> async(S source) throws Exception;

    /**
     * @param <T>
     * @return {@link GPAsyncTaskType}
     */
    <T extends GPAsyncTaskType> T getAsyncTaskType();

    /**
     *
     */
    interface GPAsyncTaskType {

        /**
         * <p>Returns the Type of {@link GPAsyncTask}</p>
         *
         * @return {@link String}
         */
        String getType();
    }
}
