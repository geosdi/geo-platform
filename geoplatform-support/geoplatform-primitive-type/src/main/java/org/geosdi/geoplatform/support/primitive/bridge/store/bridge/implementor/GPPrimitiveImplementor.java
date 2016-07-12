package org.geosdi.geoplatform.support.primitive.bridge.store.bridge.implementor;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPPrimitiveImplementor<P extends Object> implements PrimitiveImplementor<P> {

    private final Class<P> primitiveClass;
    private GPImplementorKey<Set<PrimitiveImplementorKey>> key;

    public GPPrimitiveImplementor(Class<P> thePrimitiveClass) {
        this.primitiveClass = thePrimitiveClass;
    }

    /**
     * @return {@link String}
     */
    @Override
    public final String getPrimitiveName() {
        return this.primitiveClass.getSimpleName().toLowerCase();
    }

    /**
     * @return {@link Class<P>}
     */
    @Override
    public final Class<P> getPrimitiveClass() {
        return this.primitiveClass;
    }

    /**
     * @return {@link GPImplementorKey<Set<Class<?>>>}
     */
    @Override
    public final GPImplementorKey<Set<PrimitiveImplementorKey>> getKey() {
        return this.key = ((this.key == null) ? () -> prepareKey() : this.key);
    }

    /**
     * @return {@link Set<PrimitiveImplementorKey>}
     */
    protected abstract Set<PrimitiveImplementorKey> prepareKey();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "  key = " + key +
                ", primitiveClass = " + primitiveClass +
                ", primitiveName = " + getPrimitiveName() +
                ", operators = " + getOperatorLoader().load() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GPPrimitiveImplementor<?> that = (GPPrimitiveImplementor<?>) o;
        return primitiveClass.equals(that.primitiveClass);

    }

    @Override
    public int hashCode() {
        return primitiveClass.hashCode();
    }
}
