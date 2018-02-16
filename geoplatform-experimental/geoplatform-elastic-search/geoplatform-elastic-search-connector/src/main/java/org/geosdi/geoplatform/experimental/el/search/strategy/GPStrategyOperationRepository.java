package org.geosdi.geoplatform.experimental.el.search.strategy;

import org.geosdi.geoplatform.experimental.el.dao.ElasticSearchDAO;
import org.geosdi.geoplatform.experimental.el.search.delete.OperationByPage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class GPStrategyOperationRepository implements IGPStrategyRepository {

    @Autowired
    private List<IGPOperationAsyncStrategy> operationAsyncStrategies;
    private EnumMap<IGPOperationAsyncType.OperationAsyncEnum, IGPOperationAsyncStrategy> asyncStrategyMap = new EnumMap(IGPOperationAsyncType.OperationAsyncEnum.class);

    /**
     * @param operationAsyncEnum
     * @return {@link IGPOperationAsyncStrategy}
     */
    public <GPoperationAsynStrategy extends IGPOperationAsyncStrategy, Type extends IGPOperationAsyncType> GPoperationAsynStrategy getStrategyByType(Type operationAsyncEnum) {
        checkArgument(operationAsyncEnum != null, "The Parameter operationAsyncEnum must not be null");
        return (GPoperationAsynStrategy) this.asyncStrategyMap.get(operationAsyncEnum);
    }

    @Override
    public <GPoperationAsynStrategy extends IGPOperationAsyncStrategy> void registerStrategy(GPoperationAsynStrategy iOperationAsyncStrategy) {
        if (!this.asyncStrategyMap.containsKey(iOperationAsyncStrategy))
            this.asyncStrategyMap.put(iOperationAsyncStrategy.getStrateyType(), iOperationAsyncStrategy);
    }

    @Override
    public <Result extends OperationByPage.IOperationByPageResult, Type extends IGPOperationAsyncType, Page extends OperationByPage> Result getSingleOperation(Type operationAsyncEnum, Page page, ElasticSearchDAO searchDAO) throws Exception {
        if (this.asyncStrategyMap.containsKey(operationAsyncEnum))
            return this.asyncStrategyMap.get(operationAsyncEnum).singleOperation(page, searchDAO);
        return null;
    }

    @Override
    public <Result extends OperationByPage.IOperationByPageResult, Type extends IGPOperationAsyncType, Page extends OperationByPage> Result getParallelOperation(Type operationAsyncEnum, Page page, ElasticSearchDAO searchDAO) throws Exception {
        if (this.asyncStrategyMap.containsKey(operationAsyncEnum))
            return this.asyncStrategyMap.get(operationAsyncEnum).parallerOperation(page, searchDAO);
        return null;
    }

    @PostConstruct
    public void buildMap() {
        this.operationAsyncStrategies.stream().forEach(this::registerStrategy);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        checkNotNull(!this.operationAsyncStrategies.isEmpty(), "Strategy Operation List must not be null.");
    }
}
