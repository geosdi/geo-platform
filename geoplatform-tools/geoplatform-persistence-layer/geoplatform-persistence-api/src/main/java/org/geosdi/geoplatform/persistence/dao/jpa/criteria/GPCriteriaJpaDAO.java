package org.geosdi.geoplatform.persistence.dao.jpa.criteria;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.persistence.dao.GPAbstractBaseDAO;
import org.hibernate.Cache;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPCriteriaJpaDAO<T extends Object, ID extends Serializable> extends GPAbstractBaseDAO<T, ID>
        implements BaseCriteriaJpaDAO<T, ID> {

    protected EntityManager entityManager;

    public GPCriteriaJpaDAO(Class<T> thePersistentClass) {
        super(thePersistentClass);
    }

    /**
     * @return {@link CriteriaQuery <T>}
     */
    @Override
    public CriteriaQuery<T> createCriteriaQuery() {
        return this.getCriteriaBuilder().createQuery(this.persistentClass);
    }

    /**
     * @return {@link CriteriaDelete <T>}
     */
    @Override
    public CriteriaDelete<T> createCriteriaDelete() {
        return this.getCriteriaBuilder().createCriteriaDelete(this.persistentClass);
    }

    /**
     * @return {@link CriteriaUpdate <T>}
     */
    @Override
    public CriteriaUpdate<T> createCriteriaUpdate() {
        return this.getCriteriaBuilder().createCriteriaUpdate(this.persistentClass);
    }

    /**
     * @return {@link CriteriaBuilder}
     */
    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }

    /**
     * @param theEntityManager
     */
    @PersistenceContext
    @Override
    public void setEm(EntityManager theEntityManager) {
        Preconditions.checkNotNull(theEntityManager);
        this.entityManager = theEntityManager;
    }

    /**
     * @return {@link SessionFactory}
     */
    @Override
    public final SessionFactory getSessionFactory() {
        return getSession().getSessionFactory();
    }

    /**
     * @return {@link Cache}
     */
    @Override
    public Cache getCache() {
        return getSessionFactory().getCache();
    }

    /**
     * @return {@link Session}
     */
    protected final Session getSession() {
        return (Session) this.entityManager.getDelegate();
    }

    /**
     * @return {@link Criteria}
     */
    protected final Criteria createCriteria() {
        return this.getSession().createCriteria(persistentClass);
    }
}
