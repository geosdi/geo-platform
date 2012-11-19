/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.persistence.dao.jpa;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.geosdi.geoplatform.persistence.dao.GPBaseDAO;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Transactional
public abstract class GPAbstractJpaDAO<T extends Object, ID extends Serializable>
        implements GPBaseDAO<T, ID> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private Class< T> persistentClass;
    //
    protected EntityManager entityManager;

    public GPAbstractJpaDAO(Class<T> thePersistentClass) {
        Preconditions.checkNotNull(thePersistentClass);
        this.persistentClass = thePersistentClass;
    }

    @PersistenceContext
    public void setEm(final EntityManager theEntityManager) {
        Preconditions.checkNotNull(theEntityManager);
        this.entityManager = theEntityManager;
    }

    @Override
    public T persist(T entity) {
        Preconditions.checkNotNull(entity);

        this.entityManager.persist(entity);
        return entity;
    }

    @Override
    public void update(T entity) {
        this.entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        T entity = this.find(id);

        if (entity == null) {
            throw new GPDAOException(
                    "The Entity with ID : " + id + " has been already deleted.");
        }

        this.entityManager.remove(entity);
    }

    @Override
    public List<T> findByCriteria(Criterion... criterion) throws GPDAOException {
        try {
            Criteria criteria = getSession().createCriteria(persistentClass);
            for (Criterion c : criterion) {
                criteria.add(c);
            }
            return criteria.list();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    @Override
    public T find(Long id) throws GPDAOException {
        Preconditions.checkArgument(id != null);

        try {
            Criteria crit = getSession().createCriteria(persistentClass);
            return (T) crit.add(Restrictions.idEq(id)).uniqueResult();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    @Override
    public List<T> findAll() {
        return this.entityManager.createQuery(
                "from " + this.persistentClass.getName()).getResultList();
    }

    @Override
    public List<T> findAll(int start, int end) throws GPDAOException {
        try {
            Criteria crit = getSession().createCriteria(persistentClass);
            crit.setFirstResult(start);
            crit.setMaxResults(end);
            return crit.list();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    protected Class< T> getPersistentClass() {
        if (logger.isDebugEnabled()) {
            logger.debug("@@@@@@@@@@@@@@@@@@@@@ Persistent Class : "
                    + this.persistentClass);
        }
        return persistentClass;
    }

    protected Session getSession() {
        return (Session) this.entityManager.getDelegate();
    }
}
