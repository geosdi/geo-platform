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
package org.geosdi.geoplatform.persistence.dao.jpa.criteria;

import org.geosdi.geoplatform.persistence.dao.GPAbstractBaseDAO;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static javax.annotation.meta.When.NEVER;

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
     * @param id
     * @return {@link Integer}
     * @throws GPDAOException
     */
    @Override
    public Integer deleteByID(ID id) throws GPDAOException {
        checkArgument(id != null, "The Parameter ID must not be null.");
        try {
            CriteriaDelete<T> criteriaDelete = this.createCriteriaDelete();
            Root<T> root = criteriaDelete.from(super.getPersistentClass());
            criteriaDelete.where(this.criteriaBuilder().equal(root.get("id"), id));
            return entityManager.createQuery(criteriaDelete).executeUpdate();
        } catch (HibernateException ex) {
            logger.error("HibernateException : " + ex);
            throw new GPDAOException(ex);
        }
    }

    /**
     * @return {@link CriteriaQuery<T>}
     */
    @Override
    public CriteriaQuery<T> createCriteriaQuery() {
        return this.criteriaBuilder().createQuery(this.persistentClass);
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
    @Override
    public CriteriaDelete<T> createCriteriaDelete() {
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
    @Override
    public CriteriaUpdate<T> createCriteriaUpdate() {
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

    /**
     * @return {@link CriteriaBuilder}
     */
    @Override
    public CriteriaBuilder criteriaBuilder() {
        return this.entityManager.getCriteriaBuilder();
    }

    /**
     * @param theEntityManager
     */
    @PersistenceContext
    @Override
    public void setEm(@Nonnull(when = NEVER) EntityManager theEntityManager) {
        checkNotNull(theEntityManager);
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
}