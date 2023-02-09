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
package org.geosdi.geoplatform.core.acl.dao.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.geosdi.geoplatform.core.acl.AclEntry;
import org.geosdi.geoplatform.core.acl.dao.AclEntryDAO;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.GPAbstractJpaDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
@Repository(value = "entryDAO")
@Profile(value = "jpa")
class AclEntryDAOImpl extends GPAbstractJpaDAO<AclEntry, Long> implements AclEntryDAO {

    AclEntryDAOImpl() {
        super(AclEntry.class);
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
     * @param sidId
     * @return {@link List<AclEntry>}
     * @throws GPDAOException
     */
    @Override
    public List<AclEntry> findBySid(Long sidId) throws GPDAOException {
        checkArgument(sidId != null, "The Parameter sidId must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<AclEntry> criteriaQuery = super.createCriteriaQuery();
            Root<AclEntry> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("aclSid").get("id"), sidId));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param objectIdentityId
     * @return {@link List<AclEntry>}
     * @throws GPDAOException
     */
    @Override
    public List<AclEntry> findByObjectIdentity(Long objectIdentityId) throws GPDAOException {
        checkArgument(objectIdentityId != null, "The Parameter objectIdentityId must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<AclEntry> criteriaQuery = super.createCriteriaQuery();
            Root<AclEntry> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("aclObject").get("id"), objectIdentityId))
                    .orderBy(builder.asc(root.get("aceOrder")));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }
}