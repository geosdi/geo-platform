/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.geosdi.geoplatform.core.dao.GPAccountProjectDAO;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.GPAbstractJpaDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.springframework.security.acls.domain.BasePermission.ADMINISTRATION;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Repository(value = "accountProjectDAO")
@Profile(value = "jpa")
class GPAccountProjectDAOImpl extends GPAbstractJpaDAO<GPAccountProject, Long> implements GPAccountProjectDAO {

    GPAccountProjectDAOImpl() {
        super(GPAccountProject.class);
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
     * @param accountID
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean removeByAccountID(Long accountID) throws GPDAOException {
        checkArgument(accountID != null, "The Parameter accountID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaDelete<GPAccountProject> criteriaDelete = super.createCriteriaDelete();
            Root<GPAccountProject> root = criteriaDelete.from(super.getPersistentClass());
            criteriaDelete.where(builder.equal(root.join("account").get("id"), accountID));
            return ((this.entityManager.createQuery(criteriaDelete).executeUpdate() == 1) ? TRUE : FALSE);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean removeByProjectID(Long projectID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaDelete<GPAccountProject> criteriaDelete = super.createCriteriaDelete();
            Root<GPAccountProject> root = criteriaDelete.from(super.getPersistentClass());
            criteriaDelete.where(builder.equal(root.join("project").get("id"), projectID));
            return ((this.entityManager.createQuery(criteriaDelete).executeUpdate() == 1) ? TRUE : FALSE);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param accountID
     * @param projectID
     * @return {@link GPAccountProject}
     * @throws GPDAOException
     */
    @Override
    public GPAccountProject find(Long accountID, Long projectID) throws GPDAOException {
        checkArgument(accountID != null, "The Parameter accountID must not be null.");
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        try {
            CriteriaQuery<GPAccountProject> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccountProject> root = criteriaQuery.from(this.persistentClass);
            CriteriaBuilder criteriaBuilder = super.criteriaBuilder();
            criteriaQuery.select(root);
            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.join("account").get("id"), accountID),
                    criteriaBuilder.equal(root.join("project").get("id"), projectID)));
            List<GPAccountProject> accountProjects = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((accountProjects != null) && !(accountProjects.isEmpty()) ? accountProjects.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param accountID
     * @return {@link GPAccountProject}
     * @throws GPDAOException
     */
    @Override
    public GPAccountProject findDefaultProjectByAccountID(Long accountID) throws GPDAOException {
        checkArgument(accountID != null, "The Parameter accountID must not be null.");
        try {
            CriteriaQuery<GPAccountProject> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccountProject> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            CriteriaBuilder criteriaBuilder = super.criteriaBuilder();
            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.join("account").get("id"), accountID),
                    criteriaBuilder.equal(root.get("defaultProject"), TRUE)));
            List<GPAccountProject> accountProjects = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((accountProjects != null) && !(accountProjects.isEmpty()) ? accountProjects.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param accountID
     * @param projectID
     * @return {@link GPAccountProject}
     * @throws GPDAOException
     */
    @Override
    public GPAccountProject forceAsDefaultProject(Long accountID, Long projectID) throws GPDAOException {
        GPAccountProject oldDefault = this.findDefaultProjectByAccountID(accountID);
        if (oldDefault != null) {
            oldDefault.setDefaultProject(FALSE);
            super.update(oldDefault);
        }
        GPAccountProject newDefault = this.find(accountID, projectID);
        if (newDefault == null)
            return null;
        newDefault.setDefaultProject(TRUE);
        return super.update(newDefault);
    }

    /**
     * @param accountID
     * @return {@link List<GPAccountProject>}
     * @throws GPDAOException
     */
    @Override
    public List<GPAccountProject> findByAccountID(Long accountID) throws GPDAOException {
        checkArgument(accountID != null, "The Parameter accountID must not be null.");
        try {
            CriteriaQuery<GPAccountProject> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccountProject> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(super.criteriaBuilder().equal(root.join("account").get("id"), accountID));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param accountID
     * @return {@link List<GPAccountProject>}
     * @throws GPDAOException
     */
    @Override
    public List<GPAccountProject> findByOwnerAccountID(Long accountID) throws GPDAOException {
        checkArgument(accountID != null, "The Parameter accountID must not be null.");
        try {
            CriteriaQuery<GPAccountProject> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccountProject> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            CriteriaBuilder criteriaBuilder = super.criteriaBuilder();
            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.join("account").get("id"), accountID),
                    criteriaBuilder.equal(root.get("permissionMask"), ADMINISTRATION.getMask())));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @return {@link List<GPAccountProject>}
     * @throws GPDAOException
     */
    @Override
    public List<GPAccountProject> findByProjectID(Long projectID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null.");
        try {
            CriteriaQuery<GPAccountProject> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccountProject> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(super.criteriaBuilder().equal(root.join("project").get("id"), projectID));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @return {@link GPAccountProject}
     * @throws GPDAOException
     */
    @Override
    public GPAccountProject findOwnerByProjectID(Long projectID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null");
        try {
            CriteriaQuery<GPAccountProject> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccountProject> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            CriteriaBuilder criteriaBuilder = super.criteriaBuilder();
            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.join("project").get("id"), projectID),
                    criteriaBuilder.equal(root.get("permissionMask"), ADMINISTRATION.getMask())));
            List<GPAccountProject> accountProjects = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((accountProjects != null) && !(accountProjects.isEmpty()) ? accountProjects.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param projectID
     * @return {@link List<GPAccountProject>}
     * @throws GPDAOException
     */
    @Override
    public List<GPAccountProject> findNotOwnersByProjectID(Long projectID) throws GPDAOException {
        checkArgument(projectID != null, "The Parameter projectID must not be null");
        try {
            CriteriaQuery<GPAccountProject> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccountProject> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            CriteriaBuilder criteriaBuilder = super.criteriaBuilder();
            criteriaQuery.where(criteriaBuilder.and(criteriaBuilder.equal(root.join("project").get("id"), projectID),
                    criteriaBuilder.notEqual(root.get("permissionMask"), ADMINISTRATION.getMask())));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param accountID
     * @param nameProject
     * @return {@link Number}
     * @throws GPDAOException
     */
    @Override
    public Number count(Long accountID, String nameProject) throws GPDAOException {
        checkArgument(accountID != null, "The Parameter accountID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<GPAccountProject> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(builder.count(root));
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.join("account").get("id"), accountID));
            if ((nameProject != null) && !(nameProject.trim().isEmpty()))
                predicates.add(builder.like(builder.lower(root.get("project").get("name")), nameProject.toLowerCase()));
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
     * @param accountID
     * @param projectName
     * @return {@link List < GPAccountProject >}
     * @throws GPDAOException
     */
    @Override
    public List<GPAccountProject> searchAccountProjectsByAccountID(Integer page, Integer size, Long accountID, String projectName) throws GPDAOException {
        checkArgument(accountID != null, "The Parameter accountID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPAccountProject> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccountProject> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.join("account").get("id"), accountID));
            if ((projectName != null) && !(projectName.trim().isEmpty()))
                predicates.add(builder.like(builder.lower(root.get("project").get("name")), projectName.toLowerCase()));
            criteriaQuery.where(predicates.stream().toArray(Predicate[]::new))
                    .orderBy(builder.asc(root.get("defaultProject")));
            TypedQuery<GPAccountProject> typedQuery = this.entityManager.createQuery(criteriaQuery);
            if ((page != null) && (size != null)) {
                Integer firstResult = (page == 0) ? 0 : ((page * size));
                typedQuery.setFirstResult(firstResult);
                typedQuery.setMaxResults(size);
            }
            return typedQuery.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }
}