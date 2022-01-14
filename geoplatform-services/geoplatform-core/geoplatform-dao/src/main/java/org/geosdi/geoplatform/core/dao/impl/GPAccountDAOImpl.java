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
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.GPAbstractJpaDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Repository(value = "accountDAO")
@Profile(value = "jpa")
class GPAccountDAOImpl extends GPAbstractJpaDAO<GPAccount, Long> implements GPAccountDAO {

    GPAccountDAOImpl() {
        super(GPAccount.class);
    }

    /**
     * @param id
     * @return {@link Boolean}
     * @throws Exception
     */
    @Override
    public Boolean removeById(Long id) throws GPDAOException {
        return (super.deleteByID(id) == 1 ? TRUE : FALSE);
    }

    /**
     * @param organization
     * @return {@link List<GPAccount>}
     * @throws Exception
     */
    @Override
    public List<GPAccount> findByOrganization(String organization) throws GPDAOException {
        checkArgument((organization != null) && !(organization.trim().isEmpty()), "The Paramater organization must not be null or an Empty String.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPAccount> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccount> root = criteriaQuery.from(super.getPersistentClass());
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("organization").get("name"), organization));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param username
     * @return {@link GPUser}
     * @throws Exception
     */
    @Override
    public GPUser findByUsername(String username) throws GPDAOException {
        checkArgument((username != null) && !(username.trim().isEmpty()), "The Parameter username must not be null or an empty String.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPAccount> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccount> root = criteriaQuery.from(this.persistentClass);
            Root<GPUser> userRoot = builder.treat(root, GPUser.class);
            criteriaQuery.select(root);
            criteriaQuery.where(super.criteriaBuilder().equal(userRoot.get("username"), username));
            List<GPAccount> accounts = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((accounts != null) && !(accounts.isEmpty()) ? (GPUser) accounts.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param email
     * @return {@link GPUser}
     * @throws Exception
     */
    @Override
    public GPUser findByEmail(String email) throws GPDAOException {
        checkArgument((email != null) && !(email.trim().isEmpty()), "The Parameter email must not be null or an empty String.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPAccount> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccount> root = criteriaQuery.from(this.persistentClass);
            Root<GPUser> userRoot = builder.treat(root, GPUser.class);
            criteriaQuery.select(root);
            criteriaQuery.where(super.criteriaBuilder().equal(userRoot.get("emailAddress"), email));
            List<GPAccount> accounts = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((accounts != null) && !(accounts.isEmpty()) ? (GPUser) accounts.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param appID
     * @return {@link GPApplication}
     * @throws Exception
     */
    @Override
    public GPApplication findByAppID(String appID) throws GPDAOException {
        checkArgument((appID != null) && !(appID.trim().isEmpty()), "" + "The Parameter appID must not be null or an empty String.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPAccount> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccount> root = criteriaQuery.from(this.persistentClass);
            Root<GPApplication> applicationRoot = builder.treat(root, GPApplication.class);
            criteriaQuery.select(root);
            criteriaQuery.where(super.criteriaBuilder().equal(applicationRoot.get("appID"), appID));
            List<GPAccount> applications = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((applications != null) && !(applications.isEmpty()) ? (GPApplication) applications.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param naturalID
     * @return {@link GPAccount}
     * @throws Exception
     */
    @Override
    public GPAccount findByNaturalID(String naturalID) throws GPDAOException {
        checkArgument((naturalID != null) && !(naturalID.trim().isEmpty()), "The Parameter naturalID must not be null or an empty String.");
        try {
            GPAccount account = this.findByUsername(naturalID);
            return ((account != null) ? account : this.findByAppID(naturalID));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @return {@link Integer}
     * @throws GPDAOException
     */
    @Override
    public Integer expireTempAccount() throws GPDAOException {
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaUpdate<GPAccount> criteriaUpdate = super.createCriteriaUpdate();
            Root<GPAccount> root = criteriaUpdate.from(this.persistentClass);
            criteriaUpdate.where(builder.equal(root.get("accountTemporary"), TRUE),
                    builder.equal(root.get("accountNonExpired"), TRUE));
            criteriaUpdate.set(root.get("accountTemporary"), FALSE);
            criteriaUpdate.set(root.get("accountNonExpired"), FALSE);
            return this.entityManager.createQuery(criteriaUpdate).executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param page
     * @param size
     * @param organizationName
     * @param userID
     * @param nameLike
     * @return {@link List<GPAccount>}
     * @throws GPDAOException
     */
    @Override
    public List<GPAccount> searchPagebleUsersByOrganization(Integer page, Integer size, String organizationName, Long userID, String nameLike) throws GPDAOException {
        checkArgument((organizationName != null) && !(organizationName.trim().isEmpty()), "The Paramater organizationName must not be null or an Empty String.");
        checkArgument(userID != null, "The Parameter userID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPAccount> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccount> root = criteriaQuery.from(this.persistentClass);
            Root<GPUser> userRoot = builder.treat(root, GPUser.class);
            criteriaQuery.select(root);
            List<Predicate> predicates = Lists.newArrayList();
            if ((nameLike != null) && !(nameLike.trim().isEmpty()))
                predicates.add(builder.like(builder.lower(userRoot.get("username")), nameLike.toLowerCase()));
            predicates.add(builder.equal(root.join("organization").get("name"), organizationName));
            predicates.add(builder.notEqual(root.get("id"), userID));
            predicates.add(builder.isNotNull(userRoot.get("username")));
            criteriaQuery.where(predicates.stream().toArray(s -> new Predicate[s]))
                    .orderBy(builder.asc(userRoot.get("username")));
            Query typedQuery = this.entityManager.createQuery(criteriaQuery);
            Integer firstResult = (page == 0) ? 0 : ((page * size));
            typedQuery.setFirstResult(firstResult);
            typedQuery.setMaxResults(size);
            return typedQuery.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param page
     * @param size
     * @param organizationName
     * @param userID
     * @param nameLike
     * @return {@link List<GPAccount>}
     * @throws GPDAOException
     */
    @Override
    public List<GPAccount> searchPagebleEnabledUsersByOrganization(Integer page, Integer size, String organizationName, Long userID, String nameLike) throws GPDAOException {
        checkArgument((organizationName != null) && !(organizationName.trim().isEmpty()), "The Paramater organizationName must not be null or an Empty String.");
        checkArgument(userID != null, "The Parameter userID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPAccount> criteriaQuery = super.createCriteriaQuery();
            Root<GPAccount> root = criteriaQuery.from(this.persistentClass);
            Root<GPUser> userRoot = builder.treat(root, GPUser.class);
            criteriaQuery.select(root);
            List<Predicate> predicates = Lists.newArrayList();
            if ((nameLike != null) && !(nameLike.trim().isEmpty()))
                predicates.add(builder.like(builder.lower(userRoot.get("username")), nameLike.toLowerCase()));
            predicates.add(builder.equal(root.get("enabled"), TRUE));
            predicates.add(builder.equal(root.join("organization").get("name"), organizationName));
            predicates.add(builder.notEqual(root.get("id"), userID));
            predicates.add(builder.isNotNull(userRoot.get("username")));
            criteriaQuery.where(predicates.stream().toArray(Predicate[]::new))
                    .orderBy(builder.asc(userRoot.get("username")));
            Query typedQuery = this.entityManager.createQuery(criteriaQuery);
            Integer firstResult = (page == 0) ? 0 : ((page * size));
            typedQuery.setFirstResult(firstResult);
            typedQuery.setMaxResults(size);
            return typedQuery.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param nameLike
     * @return {@link Number}
     * @throws GPDAOException
     */
    @Override
    public Number countAccounts(String nameLike) throws GPDAOException {
        checkArgument(((nameLike != null) && !(nameLike.trim().isEmpty())), "The Parameter nameLike must not be null or an empty string.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<GPAccount> root = criteriaQuery.from(this.persistentClass);
            Root<GPUser> userRoot = builder.treat(root, GPUser.class);
            Root<GPApplication> applicationRoot = builder.treat(root, GPApplication.class);
            criteriaQuery.select(builder.count(root));
            criteriaQuery.where(builder.or(builder.like(builder.lower(userRoot.get("username")), nameLike.toLowerCase()),
                    builder.like(builder.lower(applicationRoot.get("appID")), nameLike.toLowerCase())));
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param organizationName
     * @param nameLike
     * @return {@link Number}
     * @throws GPDAOException
     */
    @Override
    public Number countUsers(String organizationName, String nameLike) throws GPDAOException {
        checkArgument((organizationName != null) && !(organizationName.trim().isEmpty()), "The Paramater organizationName must not be null or an Empty String.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<GPAccount> root = criteriaQuery.from(this.persistentClass);
            Root<GPUser> userRoot = builder.treat(root, GPUser.class);
            criteriaQuery.select(builder.count(root));
            List<Predicate> predicates = Lists.newArrayList();
            predicates.add(builder.equal(root.join("organization").get("name"), organizationName));
            predicates.add(builder.isNotNull(userRoot.get("username")));
            if ((nameLike != null) && !(nameLike.isEmpty()))
                predicates.add(builder.like(builder.lower(userRoot.get("username")), nameLike.toLowerCase()));
            criteriaQuery.where(predicates.stream().toArray(size -> new Predicate[size]));
            return this.entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }
}