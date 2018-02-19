package org.geosdi.geoplatform.experimental.el.search.strategy;

import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.operation.OperationByPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EnumMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
class GPStrategyOperationRepository implements GPStrategyRepository {

    @Autowired
    private List<IGPOperationAsyncStrategy> operationStrategies;
    private EnumMap<IGPOperationAsyncType.OperationAsyncType, IGPOperationAsyncStrategy> strategyRepository = new EnumMap(IGPOperationAsyncType.OperationAsyncType.class);

    /**
     * @param operationAsyncEnum
     * @return {@link IGPOperationAsyncStrategy}
     */
    public <GPoperationAsynStrategy extends IGPOperationAsyncStrategy, Type extends IGPOperationAsyncType> GPoperationAsynStrategy getStrategyByType(Type operationAsyncEnum) {
        checkArgument(operationAsyncEnum != null, "The Parameter operationAsyncEnum must not be null");
        return (GPoperationAsynStrategy) this.strategyRepository.get(operationAsyncEnum);
    }

    @Override
    public <GPoperationAsynStrategy extends IGPOperationAsyncStrategy> void registerStrategy(GPoperationAsynStrategy strategy) {
        if (!this.strategyRepository.containsKey(strategy))
            this.strategyRepository.put(strategy.getStrateyType(), strategy);
    }

    @Override
    public <Result extends OperationByPage.IOperationByPageResult, Type extends IGPOperationAsyncType, Page extends OperationByPage> Result getSingleOperation(Type theType,
            Page page, ElasticSearchDAO searchDAO) throws Exception {
        IGPOperationAsyncStrategy strategy = this.getStrategyByType(theType);
        checkArgument(strategy != null, "The Strategy for Type : " + theType + " doesn't exist.");
        return strategy.singleOperation(page, searchDAO);
    }

    @Override
    public <Result extends OperationByPage.IOperationByPageResult, Type extends IGPOperationAsyncType, Page extends OperationByPage> Result getParallelOperation(Type theType,
            Page page, ElasticSearchDAO searchDAO) throws Exception {
        IGPOperationAsyncStrategy strategy = this.getStrategyByType(theType);
        checkArgument(strategy != null, "The Strategy for Type : " + theType + " doesn't exist.");
        return strategy.parallerOperation(page, searchDAO);
    }

    protected final void registerStrategies() {
        this.operationStrategies.stream().forEach(this::registerStrategy);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "strategyRepository=" + strategyRepository +
                '}';
    }

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        checkNotNull(!this.operationStrategies.isEmpty(), "There are no Strategies Operation to Register.");
    }
}