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
