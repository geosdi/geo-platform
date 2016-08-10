package org.geosdi.geoplatform.persistence.dao.jpa;

import org.geosdi.geoplatform.persistence.dao.GPBaseDAO;
import org.hibernate.Cache;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPBaseJpaDAO<T extends Object, ID extends Serializable> extends GPBaseDAO<T, ID> {

    /**
     * @param theEntityManager
     */
    void setEm(EntityManager theEntityManager);

    /**
     * @return {@link SessionFactory}
     */
    SessionFactory getSessionFactory();

    /**
     * @return {@link Cache}
     */
    Cache getCache();
}
