package org.geosdi.geoplatform.core.binding;

import com.google.common.base.Preconditions;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IBinder<TO extends Object, FROM extends Object, B extends IBinder> {

    /**
     * @param from
     * @return
     */
    B withFrom(FROM from);

    /**
     * @return
     */
    TO bind() throws Exception;

    abstract class AbstractBinder<TO extends Object, FROM extends Object, B extends IBinder> implements IBinder<TO, FROM, B> {

        protected FROM from;

        protected AbstractBinder(){}

        @Override
        public B withFrom(FROM from) {
            this.from = from;
            return self();
        }

        protected B self() {
            return (B) this;
        }

        protected void checkArguments() {
            Preconditions.checkNotNull(from, "The FROM Parameter must not be null.");
        }
    }

}
