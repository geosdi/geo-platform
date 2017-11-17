/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.persistence.dao.jpa;

import com.google.common.base.Preconditions;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.criteria.GPCriteriaJpaDAO;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Transactional
public abstract class GPAbstractJpaDAO<T extends Object, ID extends Serializable> extends GPCriteriaJpaDAO<T, ID>
        implements GPBaseJpaDAO<T, ID> {

    public GPAbstractJpaDAO(Class<T> thePersistentClass) {
        super(thePersistentClass);
    }

    /**
     * @param entity
     * @return {@link T}
     */
    @Override
    public T persist(T entity) {
        Preconditions.checkNotNull(entity, "Entity to persist must not be null.");
        this.entityManager.persist(entity);
        return entity;
    }

    /**
     * @param entity
     */
    @Override
    public <S extends T> S update(T entity) {
        Preconditions.checkNotNull(entity, "Entity to update must not be null.");
        return (S) this.entityManager.merge(entity);
    }

    /**
     * @param entities
     * @return {@link Collection<S>}
     * @throws GPDAOException
     */
    @Override
    public <S extends T> Collection<S> update(Iterable<T> entities) throws GPDAOException {
        List<S> updateEntities = new ArrayList<>();
        for (T entity : entities) {
            updateEntities.add(this.update(entity));
        }
        return updateEntities;
    }

    /**
     * @param entities
     * @return {@link Collection<T>}
     */
    @Override
    public Collection<T> persist(Iterable<T> entities) {
        List<T> persistedEntities = new ArrayList<>();
        for (T entity : entities) {
            persistedEntities.add(this.persist(entity));
        }
        return persistedEntities;
    }

    /**
     * @param id
     */
    @Override
    public void delete(ID id) {
        T entity = this.find(id);
        if (entity == null) {
            throw new GPDAOException("The Entity with ID : " + id + " has been already deleted.");
        }
        this.entityManager.remove(entity);
    }

    /**
     * @param criterion
     * @return {@link List<T>}
     * @throws GPDAOException
     */
    @Override
    public List<T> findByCriteria(Criterion... criterion) throws GPDAOException {
        try {
            Criteria criteria = getSession().createCriteria(persistentClass);
            Arrays.stream(criterion).forEach(c -> criteria.add(c));
            return criteria.list();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
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
        Preconditions.checkArgument(id != null, "The Parameter ID must not be null.");
        try {
            CriteriaQuery<T> criteriaQuery = super.createCriteriaQuery();
            Root<T> root = criteriaQuery.from(super.getPersistentClass());
            criteriaQuery.select(root);
            criteriaQuery.where(super.getCriteriaBuilder().equal(root.get("id"), id));
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
    public List<T> findAll() {
        return this.entityManager.createQuery("from " + this.persistentClass.getName()).getResultList();
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
            Root<T> root = criteriaQuery.from(super.getPersistentClass());
            criteriaQuery.select(root);
            return this.entityManager.createQuery(criteriaQuery)
                    .setFirstResult(start)
                    .setMaxResults(end)
                    .getResultList();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param start
     * @param end
     * @param criterion
     * @return {@link List<T>}
     * @throws GPDAOException
     */
    @Override
    public List<T> findAll(int start, int end, Criterion... criterion) throws GPDAOException {
        Criteria crit = getSession().createCriteria(persistentClass);
        try {
            Arrays.stream(criterion).forEach(c -> crit.add(c));
            crit.setFirstResult(start);
            crit.setMaxResults(end);
            return crit.list();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    /**
     * @return {@link Integer}
     */
    @Override
    public Integer removeAll() {
        Query q = this.entityManager.createNativeQuery("delete from "
                + persistentClass.getSimpleName(), persistentClass);
        return q.executeUpdate();
    }

    /**
     * @return {@link Number}
     */
    @Override
    public Number count() {
        CriteriaBuilder criteriaBuilder = super.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(super.getPersistentClass())));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    /**
     * @param criterion
     * @return {@link Number}
     */
    @Override
    public Number count(Criterion criterion) {
        return (Number) getSession().createCriteria(persistentClass).add(criterion)
                .setProjection(Projections.rowCount()).uniqueResult();
    }
}
