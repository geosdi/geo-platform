package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.time;

import com.google.common.collect.Maps;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IStrategyLayerOperation {


    /**
     * @return
     */
    ILayerTimeOperationStrategy getStrategy(Boolean value);


    interface ILayerTimeOperationStrategy {

        /**
         *
         */
        void getApplyOperation();

    }


    @Singleton
    class StrategyLayerOperationOperation implements IStrategyLayerOperation {

        final Map<Boolean, ILayerTimeOperationStrategy> operationMap = Maps.newHashMap();

        @Inject
        public StrategyLayerOperationOperation() {
            this.operationMap.put(Boolean.FALSE, new SingleLayerTimeOperation());
            this.operationMap.put(Boolean.TRUE, new MultipleLayerTimeOperation());
        }


        /**
         * @param value
         * @return
         */
        @Override
        public ILayerTimeOperationStrategy getStrategy(Boolean value) {
            return this.operationMap.get(value);
        }
    }

}
