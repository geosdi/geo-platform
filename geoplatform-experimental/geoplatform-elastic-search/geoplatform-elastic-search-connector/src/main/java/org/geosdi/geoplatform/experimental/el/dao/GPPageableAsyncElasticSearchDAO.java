package org.geosdi.geoplatform.experimental.el.dao;

import net.jcip.annotations.Immutable;
import org.elasticsearch.search.sort.SortOrder;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.search.bool.IBooleanSearch;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.geosdi.geoplatform.experimental.el.search.strategy.IGPOperationAsyncType.OperationAsyncType;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

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
        private GPElasticSearchUpdateHandler updateHandler;

        /**
         * @param theField
         * @param theSortOrder
         * @param theFrom
         * @param theSize
         * @param theOperationType
         * @param theQueryList
         */
        public PageAsync(String theField, SortOrder theSortOrder, int theFrom, int theSize, @Nonnull(when = NEVER) OperationAsyncType theOperationType, IBooleanSearch... theQueryList) {
            super(theField, theSortOrder, theFrom, theSize, theQueryList);
            checkNotNull("The Operation Type must not be null", theOperationType != null);
            this.operationType = theOperationType;
        }

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
        public PageAsync(String theField, SortOrder theSortOrder, int theFrom, int theSize,
                         @Nonnull(when = NEVER) OperationAsyncType theOperationType, GPElasticSearchUpdateHandler theUpdateHandler,
                         IBooleanSearch... theQueryList) {
            this(theField, theSortOrder, theFrom, theSize, theOperationType, theQueryList);
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
