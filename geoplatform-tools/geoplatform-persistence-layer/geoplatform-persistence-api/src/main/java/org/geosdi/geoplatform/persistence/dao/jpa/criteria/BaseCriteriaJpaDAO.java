package org.geosdi.geoplatform.persistence.dao.jpa.criteria;

import org.geosdi.geoplatform.persistence.dao.jpa.GPBaseJpaDAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.Serializable;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface BaseCriteriaJpaDAO<T extends Object, ID extends Serializable> extends GPBaseJpaDAO<T, ID> {

    /**
     * @return {@link CriteriaQuery<T>}
     */
    CriteriaQuery<T> createCriteriaQuery();

    /**
     * @return {@link CriteriaDelete<T>}
     */
    CriteriaDelete<T> createCriteriaDelete();

    /**
     * @return {@link CriteriaUpdate<T>}
     */
    CriteriaUpdate<T> createCriteriaUpdate();

    /**
     * @return {@link CriteriaBuilder}
     */
    CriteriaBuilder getCriteriaBuilder();
}
