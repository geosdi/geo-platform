package org.geosdi.geoplatform.persistence.dao.hibernate;

import org.hibernate.Session;

import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPBaseHibernateDAO<T extends Object, ID extends Serializable> extends BaseHibernateCriteriaDAO<T, ID> {

    /**
     * @return {@link Session}
     */
    Session currentSession();
}
