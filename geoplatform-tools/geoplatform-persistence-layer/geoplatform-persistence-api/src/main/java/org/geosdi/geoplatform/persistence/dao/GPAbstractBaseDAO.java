package org.geosdi.geoplatform.persistence.dao;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPAbstractBaseDAO<T extends Object, ID extends Serializable> implements GPBaseDAO<T, ID> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected final Class<T> persistentClass;

    public GPAbstractBaseDAO(Class<T> thePersistentClass) {
        Preconditions.checkNotNull(thePersistentClass, "The Persistent Class must not be null.");
        this.persistentClass = thePersistentClass;
    }

    /**
     * @return {@link Class<T>}
     */
    protected final Class<T> getPersistentClass() {
        logger.debug("@@@@@@@@@@@@@@@@@@@@@ Persistent Class : "
                + this.persistentClass);
        return persistentClass;
    }
}
