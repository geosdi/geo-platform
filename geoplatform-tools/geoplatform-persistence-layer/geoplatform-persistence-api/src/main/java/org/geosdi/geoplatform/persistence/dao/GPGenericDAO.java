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
package org.geosdi.geoplatform.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import net.jcip.annotations.Immutable;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;

import java.util.Collection;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPGenericDAO<T extends Object> {

    /**
     * @param entity
     * @return {@link T}
     */
    T persist(T entity) throws GPDAOException;

    /**
     * @param entities
     * @return {@link Collection<T>}
     */
    Collection<T> persist(Iterable<T> entities) throws GPDAOException;

    /**
     * @param entity
     * @param <S>
     * @return {@link S}
     */
    <S extends T> S update(T entity) throws GPDAOException;

    /**
     * @param entities
     * @param <S>
     * @return {@link Collection<S>}
     * @throws GPDAOException
     */
    <S extends T> Collection<S> update(Iterable<T> entities) throws GPDAOException;

    /**
     * <p>For this method remember that we assume that the Table name is it Class Name.
     * If it is different remember to override this method to implement the correct
     * way to delete all Entities.
     * </p>
     *
     * @return {@link Integer}
     */
    Integer removeAll() throws GPDAOException;

    /**
     *
     */
    interface IGPPageResult<T> {

        /**
         * <p>The Total Results Number</p>
         *
         * @return {@link Long}
         */
        Long getTotal();

        /**
         * @return {@link List<T>}
         */
        List<T> getResults();
    }

    /**
     *
     */
    @AllArgsConstructor
    @Getter
    @ToString
    @Immutable
    class GPPageResult<T extends Object> implements IGPPageResult<T> {

        private final Long total;
        private final List<T> results;
    }
}
