package org.geosdi.geoplatform.experimental.el.dao;

import org.elasticsearch.action.bulk.BulkResponse;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage.OperationByPageSearch;
import org.geosdi.geoplatform.experimental.el.search.operation.responsibility.IGPOperationHandlerManager;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class PageableAsyncElasticSearchDAO<D extends Document> extends GPBaseElasticSearchDAO<D> {

    @Resource(name = "operationHandlerManager")
    private IGPOperationHandlerManager operationHandlerManager;

    /**
     * @param page
     * @return {@link BulkResponse}
     * @throws Exception
     */
    @Override
    public <Result extends OperationByPage.IOperationByPageResult, P extends PageAsync> Result operationByPage(P page) throws Exception {
        checkNotNull(page, "Parameter Page must not be null.");
        return operationHandlerManager.operation(new OperationByPageSearch(page), this);
    }

    /**
     * @param page
     * @return {@link CompletableFuture<BulkResponse>}
     * @throws Exception
     */
    @Override
    public <Result extends OperationByPage.IOperationByPageResult, P extends PageAsync> CompletableFuture<Result> operationByPageAsync(P page) throws Exception {
        return CompletableFuture.supplyAsync(() -> {

            try {
                return operationByPage(page);
            } catch (Exception ex) {
                logger.error("@@@@@@@@@@@@@@@@@@@@@@@@PageableElasticSearchDAO#operationByPageAsync error : {}\n",
                        ex.getMessage());
                throw new IllegalStateException(ex);
            }
        }, this.elasticSearchExecutor);
    }
}