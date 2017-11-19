package org.geosdi.geoplatform.persistence.dao.hibernate;

import org.geosdi.geoplatform.persistence.dao.GPBaseDAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface BaseHibernateCriteriaDAO<T extends Object, ID extends Serializable> extends GPBaseDAO<T, ID> {

    /**
     * @return {@link CriteriaQuery<T>}
     */
    CriteriaQuery<T> createCriteriaQuery();

    /**
     * @param classe
     * @param <V>
     * @return {@link CriteriaQuery<V>}
     * @throws Exception
     */
    <V> CriteriaQuery<V> createCriteriaQuery(Class<V> classe) throws Exception;

    /**
     * @return {@link CriteriaDelete<T>}
     */
    CriteriaDelete<T> createCriteriaDelete();

    /**
     * @param classe
     * @param <V>
     * @return {@link CriteriaDelete<V>}
     * @throws Exception
     */
    <V> CriteriaDelete<V> createCriteriaDelete(Class<V> classe) throws Exception;

    /**
     * @return {@link CriteriaUpdate<T>}
     */
    CriteriaUpdate<T> createCriteriaUpdate();

    /**
     * @param classe
     * @param <V>
     * @return {@link CriteriaUpdate<V>}
     * @throws Exception
     */
    <V> CriteriaUpdate<V> createCriteriaUpdate(Class<V> classe) throws Exception;

    /**
     * @return {@link CriteriaBuilder}
     */
    CriteriaBuilder criteriaBuilder();
}
