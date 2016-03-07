package org.geosdi.geoplatform.experimental.el.condition;

import org.geosdi.geoplatform.experimental.el.api.model.Document;

import java.util.function.Predicate;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface PredicateCondition<D extends Document> extends Predicate<D> {

    class EmptyPredicateCondition<D extends Document> implements PredicateCondition<D> {

        /**
         * Evaluates this predicate on the given argument.
         *
         * @param d the input argument
         * @return {@code true} if the input argument matches the predicate,
         * otherwise {@code false}
         */
        @Override
        public boolean test(D d) {
            return Boolean.TRUE;
        }
    }
}
