package org.geosdi.geoplatform.support.primitive.bridge.store.bridge.store;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.geosdi.geoplatform.support.primitive.bridge.store.bridge.finder.GPPrimitiveImplementorFinder;
import org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.PrimitiveImplementor;
import org.geosdi.geoplatform.support.bridge.finder.GPImplementorFinder;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor.GPPrimitiveImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPPrimitiveImplementorStore implements PrimitiveImplementorStore<PrimitiveImplementor<?>> {

    private static final long serialVersionUID = 3360923993359516804L;
    //
    private static final Logger logger = LoggerFactory.getLogger(GPPrimitiveImplementorStore.class);
    private static final GPImplementorFinder<PrimitiveImplementor<?>> finder = new GPPrimitiveImplementorFinder<>();
    private static final Map<PrimitiveImplementor.PrimitiveImplementorKey, PrimitiveImplementor> primitiveImplementors = Maps.newHashMap();

    static {
        for (PrimitiveImplementor<?> primitiveImplementor : finder.getValidImplementors()) {
            primitiveImplementor.getKey().getImplementorKey().stream().forEach(key -> primitiveImplementors.put(key, primitiveImplementor));
        }
        logger.debug("@@@@@@@@@@@@@@@@@@@@@@{} up with {} values.\n\n", GPPrimitiveImplementorStore.class.getSimpleName(),
                primitiveImplementors.size());
    }

    /**
     * @param classe
     * @return {@link Boolean}
     */
    @Override
    public Boolean isPrimitiveOrWrapper(Class<?> classe) throws Exception {
        Preconditions.checkArgument(classe != null, "The Parameter classe must not be null.");
        return primitiveImplementors.containsKey(PrimitiveImplementor.PrimitiveImplementorKey.forClass(classe));
    }

    /**
     * @param classe
     * @return {@link PrimitiveImplementor}
     */
    @Override
    public PrimitiveImplementor getPrimitiveImplementorForClass(Class<?> classe) {
        Preconditions.checkArgument(classe != null, "The Parameter classe must not be null.");
        return primitiveImplementors.get(PrimitiveImplementor.PrimitiveImplementorKey.forClass(classe));
    }

    /**
     * @param key
     * @return {@link GPPrimitiveImplementor}
     * @throws Exception
     */
    @Override
    public PrimitiveImplementor getImplementorByKey(GPImplementor.GPImplementorKey key) throws Exception {
        Preconditions.checkArgument((key != null), "The Parameter key must not be null");
        return primitiveImplementors.get(key);
    }

    /**
     * @return {@link Set<GPPrimitiveImplementor<?>}
     */
    @Override
    public Set<PrimitiveImplementor<?>> getAllImplementors() {
        return Collections.unmodifiableSet(finder.getAllImplementors());
    }

    /**
     * @return {@link Collection<GPPrimitiveImplementor<?>}
     */
    @Override
    public Collection<PrimitiveImplementor<?>> getValidImplementors() {
        return Collections.unmodifiableCollection(finder.getValidImplementors());
    }
}
