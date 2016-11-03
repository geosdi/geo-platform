package org.geosdi.geoplatform.support.primitive.bridge.implementor;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.support.bridge.implementor.GPImplementor;
import org.geosdi.geoplatform.support.primitive.operator.GPOperator;

import java.util.Set;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface PrimitiveImplementor<P extends Object> extends GPImplementor<Set<PrimitiveImplementor.PrimitiveImplementorKey>> {

    /**
     * @return {@link Class<P>}
     */
    Class<P> getPrimitiveClass();

    /**
     * @return {@link String}
     */
    String getPrimitiveName();

    /**
     * @return {@link Boolean}
     */
    @Override
    default Boolean isValid() {
        return Boolean.TRUE;
    }

    /**
     * @return {@link org.geosdi.geoplatform.support.primitive.operator.GPOperator.GPOperatorLoader}
     */
    GPOperator.GPOperatorLoader getOperatorLoader();

    /**
     *
     */
    class PrimitiveImplementorKey implements GPImplementor.GPImplementorKey<Class<?>> {

        private final Class<?> classe;

        public PrimitiveImplementorKey(Class<?> theClasse) {
            Preconditions.checkNotNull(theClasse != null, "The Parameter classe must not be null.");
            this.classe = theClasse;
        }

        /**
         * @return {@link Class<?>}
         */
        @Override
        public Class<?> getImplementorKey() {
            return this.classe;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PrimitiveImplementorKey that = (PrimitiveImplementorKey) o;
            return classe.equals(that.classe);

        }

        @Override
        public int hashCode() {
            return classe.hashCode();
        }

        /**
         * @param theClasse
         * @return {@link PrimitiveImplementorKey}
         */
        public static PrimitiveImplementorKey forClass(Class<?> theClasse) {
            return new PrimitiveImplementorKey(theClasse);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " {" +
                    " classe = " + classe +
                    '}';
        }
    }
}
