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
package org.geosdi.geoplatform.persistence.configuration.dao.jpa;

import org.geosdi.geoplatform.persistence.configuration.dao.GPBaseSearchDAO;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.hibernate.Session;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Boolean.FALSE;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Transactional
public abstract class GenericJPASearchDAO<T extends Object> implements GPBaseSearchDAO<T> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected Class<T> persistentClass;
    //
    @PersistenceContext
    protected EntityManager entityManager;
    private volatile SearchSession searchSession;

    /**
     * @param thePersistentClass
     */
    public GenericJPASearchDAO(Class<T> thePersistentClass) {
        checkNotNull(thePersistentClass);
        this.persistentClass = thePersistentClass;
    }

    @Override
    public final SearchSession searchSession() throws Exception {
        return this.searchSession = ((this.searchSession != null) ? this.searchSession : Search.session(this.entityManager));
    }

    @Override
    public T persist(T entity) throws GPDAOException {
        checkNotNull(entity);
        try {
            this.entityManager.persist(entity);
            return entity;
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
                    .filter(e -> (e != null))
                    .map(e -> persist(e))
                    .collect(toList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

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
                    .filter(e -> (e != null))
                    .map(e -> (S) update(e))
                    .collect(toList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

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
     * @return {@link Session}
     */
    protected final Session getSession() {
        return (Session) this.entityManager.getDelegate();
    }
}
