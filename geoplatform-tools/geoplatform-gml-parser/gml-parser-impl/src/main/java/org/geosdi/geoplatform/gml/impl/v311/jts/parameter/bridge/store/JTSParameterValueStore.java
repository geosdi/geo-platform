package org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.store;

import org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.finder.JTSParameterValueFinder;
import org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.implementor.JTSParameterEnum;
import org.geosdi.geoplatform.gml.impl.v311.jts.parameter.bridge.implementor.JTSParameterValue;
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
public class JTSParameterValueStore implements GPImplementorStore<JTSParameterValue<? extends Object>> {

    private static final long serialVersionUID = 6888382924751232089L;
    //
    private static final Logger logger = LoggerFactory.getLogger(JTSParameterValueStore.class);
    private static final GPImplementorFinder<JTSParameterValue<? extends Object>> finder = new JTSParameterValueFinder();
    private static final Map<GPImplementorKey<JTSParameterEnum>, JTSParameterValue<? extends Object>> jtsParameterValueImplementors;

    static {
        jtsParameterValueImplementors = finder.getValidImplementors()
                .stream()
                .collect(Collectors.toMap(GPImplementor::getKey, identity()));
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", JTSParameterValueStore.class.getSimpleName(),
                jtsParameterValueImplementors.size());
    }

    /**
     * @param key
     * @return {@link JTSParameterValue}
     * @throws Exception
     */
    @Override
    public JTSParameterValue<? extends Object> getImplementorByKey(GPImplementorKey key) throws Exception {
        checkArgument((key != null), "The Parameter key must not be null");
        return jtsParameterValueImplementors.get(key);
    }

    /**
     * @return {@link Set<JTSParameterValue>}
     */
    @Override
    public Set<JTSParameterValue<? extends Object>> getAllImplementors() {
        return Collections.unmodifiableSet(finder.getAllImplementors());
    }

    /**
     * @return {@link JTSParameterValue}
     */
    @Override
    public Collection<JTSParameterValue<? extends Object>> getValidImplementors() {
        return Collections.unmodifiableCollection(finder.getValidImplementors());
    }
}