package org.geosdi.geoplatform.persistence.dao.jpa.criteria;

import org.geosdi.geoplatform.persistence.dao.jpa.GPAbstractJpaDAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPCriteriaJpaDAO<T extends Object, ID extends Serializable> extends GPAbstractJpaDAO<T, ID>
        implements BaseCriteriaJpaDAO<T, ID> {

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
}
