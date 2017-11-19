package org.geosdi.geoplatform.persistence.dao.hibernate;

import org.geosdi.geoplatform.persistence.dao.GPAbstractBaseDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
abstract class GPHibernateCriteriaDAO<T extends Object, ID extends Serializable> extends GPAbstractBaseDAO<T, ID>
        implements GPBaseHibernateDAO<T, ID> {

    @Autowired
    private SessionFactory sessionFactory;

    public GPHibernateCriteriaDAO(Class<T> thePersistentClass) {
        super(thePersistentClass);
    }

    /**
     * @return {@link Session}
     */
    public final Session currentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * @return {@link CriteriaBuilder}
     */
    public final CriteriaBuilder criteriaBuilder() {
        return this.sessionFactory.getCriteriaBuilder();
    }

    /**
     * @return {@link CriteriaQuery<T>}
     */
    public final CriteriaQuery<T> createCriteriaQuery() {
        return this.criteriaBuilder().createQuery(super.getPersistentClass());
    }

    /**
     * @param classe
     * @return {@link CriteriaQuery <V>}
     * @throws Exception
     */
    @Override
    public <V> CriteriaQuery<V> createCriteriaQuery(Class<V> classe) throws Exception {
        checkArgument(classe != null, "The Parameter classe must not be null.");
        return criteriaBuilder().createQuery(classe);
    }

    /**
     * @return {@link CriteriaDelete<T>}
     */
    public final CriteriaDelete<T> createCriteriaDelete() {
        return this.criteriaBuilder().createCriteriaDelete(this.persistentClass);
    }

    /**
     * @param classe
     * @return {@link CriteriaDelete <V>}
     * @throws Exception
     */
    @Override
    public <V> CriteriaDelete<V> createCriteriaDelete(Class<V> classe) throws Exception {
        checkArgument(classe != null, "The Parameter classe must not be null.");
        return criteriaBuilder().createCriteriaDelete(classe);
    }

    /**
     * @return {@link CriteriaUpdate<T>}
     */
    public final CriteriaUpdate<T> createCriteriaUpdate() {
        return this.criteriaBuilder().createCriteriaUpdate(this.persistentClass);
    }

    /**
     * @param classe
     * @return {@link CriteriaUpdate <V>}
     * @throws Exception
     */
    @Override
    public <V> CriteriaUpdate<V> createCriteriaUpdate(Class<V> classe) throws Exception {
        checkArgument(classe != null, "The Parameter classe must not be null.");
        return criteriaBuilder().createCriteriaUpdate(classe);
    }
}
