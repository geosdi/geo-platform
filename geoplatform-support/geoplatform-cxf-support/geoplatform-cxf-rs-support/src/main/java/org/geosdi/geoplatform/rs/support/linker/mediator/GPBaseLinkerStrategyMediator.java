package org.geosdi.geoplatform.rs.support.linker.mediator;

import org.geosdi.geoplatform.rs.support.linker.GPLinkerStrategy;
import org.geosdi.geoplatform.rs.support.request.linker.GPBaseLinkerRequest;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static javax.annotation.meta.When.NEVER;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseLinkerStrategyMediator<Request extends GPBaseLinkerRequest> implements GPLinkerStrategyMediator<Request> {

    private final Map<Class<Request>, Map<GPLinkerStrategy.IGPLinkerStrategyKey, GPLinkerStrategy>> strategies;

    /**
     * @param theStrategies
     */
    protected GPBaseLinkerStrategyMediator(@Nonnull(when = NEVER) List<GPLinkerStrategy> theStrategies) {
        checkArgument(((theStrategies != null) && !(theStrategies.isEmpty())), "There are no class of ActaLinkerStrategy in the classpath.");
        this.strategies = theStrategies
                .stream()
                .filter(Objects::nonNull)
                .collect(groupingBy(GPLinkerStrategy::forRequest, toMap(GPLinkerStrategy::getStrategyKey, identity())));
    }

    /**
     * @param theRequest
     * @return {@link Map<GPLinkerStrategy.IGPLinkerStrategyKey, GPLinkerStrategy>}
     * @throws Exception
     */
    @Override
    public Map<GPLinkerStrategy.IGPLinkerStrategyKey, GPLinkerStrategy> findStrategies(@Nonnull(when = NEVER) Request theRequest) throws Exception {
        checkArgument(theRequest != null, "The Parameter request must not be null.");
        return this.strategies.get(theRequest.getClass());
    }
}