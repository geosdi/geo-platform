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
package org.geosdi.geoplatform.core.acl.dao.impl;

import org.geosdi.geoplatform.core.acl.AclObjectIdentity;
import org.geosdi.geoplatform.core.acl.dao.AclObjectIdentityDAO;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.GPAbstractJpaDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
@Repository(value = "objectIdentityDAO")
@Profile(value = "jpa")
class AclObjectIdentityDAOImpl extends GPAbstractJpaDAO<AclObjectIdentity, Long> implements AclObjectIdentityDAO {

    AclObjectIdentityDAOImpl() {
        super(AclObjectIdentity.class);
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
     * @param objectIdClass
     * @param objectIdIdentity
     * @return {@link AclObjectIdentity}
     * @throws GPDAOException
     */
    @Override
    public AclObjectIdentity findByObjectId(Long objectIdClass, Long objectIdIdentity) throws GPDAOException {
        checkArgument(objectIdClass != null, "The Parameter objectIdClass must not be null.");
        checkArgument(objectIdIdentity != null, "The Parameter objectIdIdentity must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<AclObjectIdentity> criteriaQuery = super.createCriteriaQuery();
            Root<AclObjectIdentity> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("aclClass").get("id"), objectIdClass),
                    builder.equal(root.get("objectId"), objectIdIdentity));
            List<AclObjectIdentity> objectIdentities = this.entityManager.createQuery(criteriaQuery).getResultList();
            return ((objectIdentities != null) && !(objectIdentities.isEmpty()) ? objectIdentities.get(0) : null);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }
}