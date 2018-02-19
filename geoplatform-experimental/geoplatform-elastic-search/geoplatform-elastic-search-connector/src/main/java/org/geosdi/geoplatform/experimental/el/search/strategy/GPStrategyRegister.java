package org.geosdi.geoplatform.experimental.el.search.strategy;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@FunctionalInterface
public interface GPStrategyRegister {

    /**
     * @param strategy
     */
    <GPoperationAsynStrategy extends IGPOperationAsyncStrategy> void registerStrategy(GPoperationAsynStrategy strategy);
}