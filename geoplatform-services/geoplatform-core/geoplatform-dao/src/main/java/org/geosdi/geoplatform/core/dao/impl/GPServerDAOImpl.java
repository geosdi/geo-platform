/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.core.dao.impl;

import com.google.common.collect.Lists;
import org.geosdi.geoplatform.core.dao.GPServerDAO;
import org.geosdi.geoplatform.core.model.GPCapabilityType;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GeoPlatformServer;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.GPAbstractJpaDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Repository(value = "serverDAO")
@Profile(value = "jpa")
class GPServerDAOImpl extends GPAbstractJpaDAO<GeoPlatformServer, Long> implements GPServerDAO {

    GPServerDAOImpl() {
        super(GeoPlatformServer.class);
    }

    /**
     * @param idOrganization
     * @param type
     * @return {@link List<GeoPlatformServer>}
     * @throws GPDAOException
     */
    @Override
    public List<GeoPlatformServer> findAll(Long idOrganization, GPCapabilityType type) throws GPDAOException {
        checkArgument(idOrganization != null, "The Parameter idOrganization must not be null.");
        checkArgument(type != null, "The Parameter type must not ne null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GeoPlatformServer> criteriaQuery = super.createCriteriaQuery();
            Root<GeoPlatformServer> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("organization").get("id"), idOrganization),
                    builder.equal(root.get("serverType"), type));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param type
     * @return {@link List<GeoPlatformServer>}
     * @throws GPDAOException
     */
    @Override
    public List<GeoPlatformServer> findAll(GPCapabilityType type) throws GPDAOException {
        checkArgument(type != null, "The Parameter type must not ne null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GeoPlatformServer> criteriaQuery = super.createCriteriaQuery();
            Root<GeoPlatformServer> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get("serverType"), type));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param id
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean removeById(Long id) throws GPDAOException {
        return (super.deleteByID(id) == 1 ? TRUE : FALSE);
    }

    /**
     * @param serverName
     * @return {@link List<GeoPlatformServer>}
     * @throws GPDAOException
     */
    @Override
    public List<GeoPlatformServer> findByServerName(String serverName) throws GPDAOException {
        checkArgument(((serverName != null) && !(serverName.isEmpty())), "The Parameter serveName must not be null or an empty string.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GeoPlatformServer> criteriaQuery = super.createCriteriaQuery();
            Root<GeoPlatformServer> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get("name"), serverName));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param serverUrl
     * @return {@link GeoPlatformServer}
     * @throws GPDAOException
     */
    @Override
    public GeoPlatformServer findByServerUrl(String serverUrl) throws GPDAOException {
        checkArgument(((serverUrl != null) && !(serverUrl.isEmpty())), "The Parameter serverUrl must not be null or an empty string.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GeoPlatformServer> criteriaQuery = super.createCriteriaQuery();
            Root<GeoPlatformServer> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get("serverUrl"), serverUrl));
            List<GeoPlatformServer> servers = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((servers != null) && !(servers.isEmpty()) ? servers.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param organizationName
     * @param type
     * @param titleOrAliasName
     * @return {@link Integer}
     * @throws GPDAOException
     */
    @Override
    public Number countServers(String organizationName, GPCapabilityType type, String titleOrAliasName) throws GPDAOException {
        checkArgument((organizationName != null) && !(organizationName.isEmpty()), "The Parameter organizationName must not ne null or an empty string.");
        checkArgument(type != null, "The Parameter type must not ne null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<GeoPlatformServer> root = criteriaQuery.from(this.persistentClass);
            List<Predicate> predicates = Lists.newArrayList();
            if ((titleOrAliasName != null) && !(titleOrAliasName.isEmpty())) {
                predicates.add(builder.or(builder.like(builder.lower(root.get("title")), titleOrAliasName.toLowerCase()),
                        builder.like(builder.lower(root.get("aliasName")), titleOrAliasName.toLowerCase())));
            }
            Join<GeoPlatformServer, GPOrganization> join = root.join("organization");
            predicates.add(builder.equal(join.get("name"), organizationName));
            predicates.add(builder.equal(root.get("serverType"), type));
            criteriaQuery.select(builder.count(root));
            criteriaQuery.where(predicates.stream().toArray(size -> new Predicate[size]));
            return this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param page
     * @param size
     * @param organizationName
     * @param titleOrAliasName
     * @return {@link List<GeoPlatformServer>}
     * @throws GPDAOException
     */
    @Override
    public List<GeoPlatformServer> searchPagebleServers(Integer page, Integer size, String organizationName, GPCapabilityType type, String titleOrAliasName) throws GPDAOException {
        checkArgument((organizationName != null) && !(organizationName.isEmpty()), "The Parameter organizationName must not ne null or an empty string.");
        checkArgument(type != null, "The Parameter type must not ne null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GeoPlatformServer> criteriaQuery = super.createCriteriaQuery();
            Root<GeoPlatformServer> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            List<Predicate> predicates = Lists.newArrayList();
            if ((titleOrAliasName != null) && !(titleOrAliasName.isEmpty())) {
                predicates.add(builder.or(builder.like(builder.lower(root.get("title")), titleOrAliasName.toLowerCase()),
                        builder.like(builder.lower(root.get("aliasName")), titleOrAliasName.toLowerCase())));
            }
            Join<GeoPlatformServer, GPOrganization> join = root.join("organization");
            predicates.add(builder.equal(join.get("name"), organizationName));
            predicates.add(builder.equal(root.get("serverType"), type));
            criteriaQuery.where(predicates.stream().toArray(s -> new Predicate[s]))
                    .orderBy(builder.asc(root.get("aliasName")));
            TypedQuery<GeoPlatformServer> typedQuery = this.entityManager.createQuery(criteriaQuery);
            Integer firstResult = (page == 0) ? 0 : ((page * size));
            typedQuery.setFirstResult(firstResult);
            typedQuery.setMaxResults(size);
            return typedQuery.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }
}