package org.geosdi.geoplatform.rs.support.response.binder;

import org.geosdi.geoplatform.rs.support.binder.GPBinder;
import org.geosdi.geoplatform.rs.support.response.GPStore;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPStoreBinder<E extends Object, TO extends GPStore<E>, FROM extends Object, B extends GPStoreBinder>
        extends GPBinder<TO, FROM, B> {

    abstract class GPBaseStoreBinder<E extends Object, TO extends GPStore<E>, FROM extends Object, B extends GPStoreBinder>
            extends GPBaseBinder<TO, FROM, B> implements GPStoreBinder<E, TO, FROM, B> {

        protected GPBaseStoreBinder() {
        }
    }
}