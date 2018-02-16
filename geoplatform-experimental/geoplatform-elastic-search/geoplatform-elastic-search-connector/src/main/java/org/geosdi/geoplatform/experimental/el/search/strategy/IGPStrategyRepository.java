package org.geosdi.geoplatform.experimental.el.search.strategy;

import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IGPStrategyRepository extends InitializingBean, IGPStrategyBuildMap {

    /**
     * @param operationAsyncEnum
     * @return {@link IGPOperationAsyncStrategy}
     */
    <GPoperationAsynStrategy extends IGPOperationAsyncStrategy, Type extends IGPOperationAsyncType> GPoperationAsynStrategy getStrategyByType(Type operationAsyncEnum);

    /**
     * @param operationAsyncEnum
     * @param <Result>
     * @param <Type>
     * @return {@link OperationByPage.IOperationByPageResult}
     */
    <Result extends OperationByPage.IOperationByPageResult, Type extends IGPOperationAsyncType, Page extends OperationByPage> Result getSingleOperation(Type operationAsyncEnum, Page page, ElasticSearchDAO searchDAO) throws Exception;

    /**
     * @param operationAsyncEnum
     * @param <Result>
     * @param <Type>
     * @return {@link OperationByPage.IOperationByPageResult}
     */
    <Result extends OperationByPage.IOperationByPageResult, Type extends IGPOperationAsyncType, Page extends OperationByPage> Result getParallelOperation(Type operationAsyncEnum, Page page, ElasticSearchDAO searchDAO) throws Exception;
}
