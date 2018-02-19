package org.geosdi.geoplatform.experimental.el.dao;

import net.jcip.annotations.Immutable;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType.OperationAsyncType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType.OperationAsyncType.DELETE;
import static org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType.OperationAsyncType.UPDATE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPPageableAsyncElasticSearchDAO<D extends Document> {

    /**
     * @param page
     * @param <P>
     * @return {@link org.elasticsearch.action.bulk.BulkResponse}
     * @throws Exception
     */
    <Result extends OperationByPage.IOperationByPageResult, P extends PageAsync> Result operationByPage(P page) throws Exception;

    /**
     * @param page
     * @param <P>
     * @return {@link CompletableFuture<org.elasticsearch.action.bulk.BulkResponse>}
     * @throws Exception
     */
    <Result extends OperationByPage.IOperationByPageResult, P extends PageAsync> CompletableFuture<Result> operationByPageAsync(P page) throws Exception;

    /**
     *
     */
    @Immutable
    class PageAsync extends GPPageableElasticSearchDAO.MultiFieldsSearch {

        private final OperationAsyncType operationType;
        private final GPElasticSearchUpdateHandler updateHandler;

        /**
         * <p>
         * The Parameter {@link GPElasticSearchUpdateHandler} is optional and must be specified only
         * if {@link OperationAsyncType} type is {@link OperationAsyncType#UPDATE}.
         * </p>
         *
         * @param theFrom
         * @param theSize
         * @param theOperationType
         * @param theUpdateHandler
         */
        public PageAsync(int theFrom, int theSize, @Nonnull(when = NEVER) OperationAsyncType theOperationType,
                @Nullable GPElasticSearchUpdateHandler theUpdateHandler) {
            this(null, null, theFrom, theSize, theOperationType, theUpdateHandler, null);
        }

        /**
         * @param from
         * @param size
         * @param theOperationType
         * @param theUpdateHandler
         * @param queryList
         */
        public PageAsync(String field, SortOrder sortOrder, int from, int size, @Nonnull(when = NEVER) OperationAsyncType theOperationType,
                @Nullable GPElasticSearchUpdateHandler theUpdateHandler, IBooleanSearch... queryList) {
            super(field, sortOrder, from, size, queryList);
            checkArgument(((theOperationType == UPDATE) && (theUpdateHandler != null))
                            || ((theOperationType == DELETE) && (theUpdateHandler == null)),
                    "The Parameters are wrong.");
            this.operationType = theOperationType;
            this.updateHandler = theUpdateHandler;
        }


        /**
         * @return {@link OperationAsyncType}
         */
        public OperationAsyncType getOperationType() {
            return operationType;
        }

        /**
         * @return {@link GPElasticSearchUpdateHandler}
         */
        public GPElasticSearchUpdateHandler getUpdateHandler() {
            return updateHandler;
        }
    }
}
