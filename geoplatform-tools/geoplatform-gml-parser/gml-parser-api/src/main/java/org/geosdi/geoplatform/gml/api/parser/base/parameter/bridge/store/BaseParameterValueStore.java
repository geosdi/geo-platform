package org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.store;

import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.finder.BaseParameterValueFinder;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor.BaseParameterEnum;
import org.geosdi.geoplatform.gml.api.parser.base.parameter.bridge.implementor.BaseParameterValue;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor.GPImplementorKey;
import org.geosdi.geoplatform.support.bridge.store.GPImplementorStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.function.Function.identity;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class BaseParameterValueStore implements GPImplementorStore<BaseParameterValue<? extends Object>> {

    private static final long serialVersionUID = -2136562582385932866L;
    //
    private static final Logger logger = LoggerFactory.getLogger(BaseParameterValueStore.class);
    private static final GPImplementorFinder<BaseParameterValue<? extends Object>> finder = new BaseParameterValueFinder();
    private static final Map<GPImplementorKey<BaseParameterEnum>, BaseParameterValue<? extends Object>> baseParameterValueImplementors;

    static {
        baseParameterValueImplementors = finder.getValidImplementors()
                .stream()
                .collect(Collectors.toMap(GPImplementor::getKey, identity()));
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", BaseParameterValueStore.class.getSimpleName(),
                baseParameterValueImplementors.size());
    }

    /**
     * @param key
     * @return {@link BaseParameterValue}
     * @throws Exception
     */
    @Override
    public BaseParameterValue<? extends Object> getImplementorByKey(GPImplementorKey key) throws Exception {
        checkArgument((key != null), "The Parameter key must not be null");
        return baseParameterValueImplementors.get(key);
    }

    /**
     * @return {@link Set<BaseParameterValue>}
     */
    @Override
    public Set<BaseParameterValue<? extends Object>> getAllImplementors() {
        return Collections.unmodifiableSet(finder.getAllImplementors());
    }

    /**
     * @return {@link Collection<BaseParameterValue>}
     */
    @Override
    public Collection<BaseParameterValue<? extends Object>> getValidImplementors() {
        return Collections.unmodifiableCollection(finder.getValidImplementors());
    }
}