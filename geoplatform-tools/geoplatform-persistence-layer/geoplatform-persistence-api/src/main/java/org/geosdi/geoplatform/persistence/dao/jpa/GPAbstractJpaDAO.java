/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.persistence.dao.jpa;

import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.criteria.GPCriteriaJpaDAO;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Boolean.FALSE;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Transactional(transactionManager = "gpTransactionManager")
public abstract class GPAbstractJpaDAO<T extends Object, ID extends Serializable> extends GPCriteriaJpaDAO<T, ID> implements GPBaseJpaDAO<T, ID> {

    public GPAbstractJpaDAO(Class<T> thePersistentClass) {
        super(thePersistentClass);
    }

    /**
     * @param entity
     * @return {@link T}
     */
    @Override
    public T persist(T entity) throws GPDAOException {
        checkNotNull(entity, "Entity to persist must not be null.");
        try {
            this.entityManager.persist(entity);
            return entity;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param entity
     */
    @Override
    public <S extends T> S update(T entity) throws GPDAOException {
        checkNotNull(entity, "Entity to update must not be null.");
        try {
            return (S) this.entityManager.merge(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param entities
     * @return {@link Collection<S>}
     * @throws GPDAOException
     */
    @Override
    public <S extends T> Collection<S> update(Iterable<T> entities) throws GPDAOException {
        checkNotNull(entities != null, "The Parameter entities must not be null.");
        try {
            return stream(entities.spliterator(), FALSE)
                    .filter(Objects::nonNull)
                    .map(e -> (S) update(e))
                    .collect(toList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param entities
     * @return {@link Collection<T>}
     */
    @Override
    public Collection<T> persist(Iterable<T> entities) throws GPDAOException {
        checkNotNull(entities != null, "The Parameter entities must not be null.");
        try {
            return stream(entities.spliterator(), FALSE)
                    .filter(Objects::nonNull)
                    .map(this::persist)
                    .collect(toList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param id
     * @return {@link Integer}
     * @throws GPDAOException
     */
    @Override
    public Integer delete(ID id) throws GPDAOException {
        checkArgument(id != null, "The Parameter ID must not be null.");
        try {
            CriteriaDelete<T> criteriaDelete = this.createCriteriaDelete();
            Root<T> root = criteriaDelete.from(super.getPersistentClass());
            criteriaDelete.where(this.criteriaBuilder().equal(root.get("id"), id));
            return this.entityManager.createQuery(criteriaDelete).executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param id
     * @return {@link T}
     * @throws GPDAOException
     */
    @Override
    public T find(ID id) throws GPDAOException {
        checkArgument(id != null, "The Parameter ID must not be null.");
        try {
            CriteriaQuery<T> criteriaQuery = super.createCriteriaQuery();
            Root<T> root = criteriaQuery.from(super.getPersistentClass());
            criteriaQuery.select(root);
            criteriaQuery.where(super.criteriaBuilder().equal(root.get("id"), id));
            List<T> entities = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((entities != null) && !(entities.isEmpty()) ? entities.get(0) : null);
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    /**
     * @return {@link List<T>}
     */
    @Override
    public List<T> findAll() throws GPDAOException {
        try {
            CriteriaQuery<T> criteriaQuery = super.createCriteriaQuery();
            Root<T> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param start
     * @param end
     * @return {@link List<T>}
     * @throws GPDAOException
     */
    @Override
    public List<T> findAll(int start, int end) throws GPDAOException {
        try {
            CriteriaQuery<T> criteriaQuery = super.createCriteriaQuery();
            Root<T> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            return this.entityManager.createQuery(criteriaQuery)
                    .setFirstResult((start == 0) ? 0 : ((start * end)))
                    .setMaxResults(end)
                    .getResultList();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer removeAll() throws GPDAOException {
        try {
            return this.entityManager.createQuery("delete from " + persistentClass.getSimpleName()).executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @return {@link Number}
     */
    @Override
    public Number count() {
        CriteriaBuilder criteriaBuilder = super.criteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(super.getPersistentClass())));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
