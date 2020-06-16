package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.operation;

import com.google.common.collect.Maps;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IStrategyDateOperation {


    /**
     * @return
     */
    ITypeOperationStrategy getStrategy(Boolean value);


    interface ITypeOperationStrategy {

        /**
         * @param store
         * @param from
         * @param to
         */
        void getApplyOperation(List<Date> store, Date from, Date to);

    }


    @Singleton
    class StrategyDateOperation implements IStrategyDateOperation {

        final Map<Boolean, ITypeOperationStrategy> operationMap = Maps.newHashMap();

        @Inject
        public StrategyDateOperation() {
            this.operationMap.put(Boolean.FALSE, new PeriodSingleDateOperation());
            this.operationMap.put(Boolean.TRUE, new PeriodWithRangeOperation());
        }


        /**
         * @param value
         * @return
         */
        @Override
        public ITypeOperationStrategy getStrategy(Boolean value) {
            return this.operationMap.get(value);
        }
    }

}
