package org.geosdi.geoplatform.rs.support.binder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPBinder<TO extends Object, FROM extends Object, B extends GPBinder> {

    /**
     * @return {@link TO}
     * @throws Exception
     */
    TO bind() throws Exception;

    /**
     * @param theFrom
     * @return {@link B}
     */
    B withFrom(FROM theFrom);

    /**
     * @param <TO>
     * @param <FROM>
     * @param <B>
     */
    abstract class GPBaseBinder<TO extends Object, FROM extends Object, B extends GPBinder> implements GPBinder<TO, FROM, B> {

        protected FROM from;

        /**
         * @param theFrom
         * @return {@link B}
         */
        @Override
        public B withFrom(FROM theFrom) {
            this.from = theFrom;
            return self();
        }

        /**
         * @throws Exception
         */
        protected void checkArguments() throws Exception {
            checkNotNull(from, "The FROM Parameter must not be null.");
        }

        /**
         * @return {@link B}
         */
        protected B self() {
            return (B) this;
        }
    }
}