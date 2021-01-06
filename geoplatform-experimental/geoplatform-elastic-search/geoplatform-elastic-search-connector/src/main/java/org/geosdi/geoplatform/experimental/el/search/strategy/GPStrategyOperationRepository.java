/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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