/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.core.dao.impl;

import org.geosdi.geoplatform.core.dao.GPMessageDAO;
import org.geosdi.geoplatform.core.model.GPMessage;
import org.geosdi.geoplatform.persistence.dao.exception.GPDAOException;
import org.geosdi.geoplatform.persistence.dao.jpa.GPAbstractJpaDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 */
@Repository(value = "messageDAO")
@Profile(value = "jpa")
class GPMessageDAOImpl extends GPAbstractJpaDAO<GPMessage, Long> implements GPMessageDAO {

    public GPMessageDAOImpl() {
        super(GPMessage.class);
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
     * @param recipientID
     * @return {@link List<GPMessage>}
     * @throws GPDAOException
     */
    @Override
    public List<GPMessage> findAllMessagesByRecipient(Long recipientID) throws GPDAOException {
        checkArgument(recipientID != null, "The Parameter recipientID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPMessage> criteriaQuery = super.createCriteriaQuery();
            Root<GPMessage> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("recipient").get("id"), recipientID))
                    .orderBy(builder.desc(root.get("creationDate")));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param recipientID
     * @return {@link List<GPMessage>}
     * @throws GPDAOException
     */
    @Override
    public List<GPMessage> findUnreadMessagesByRecipient(Long recipientID) throws GPDAOException {
        checkArgument(recipientID != null, "The Parameter recipientID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaQuery<GPMessage> criteriaQuery = super.createCriteriaQuery();
            Root<GPMessage> root = criteriaQuery.from(this.persistentClass);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.join("recipient").get("id"), recipientID),
                    builder.equal(root.get("isRead"), FALSE))
                    .orderBy(builder.desc(root.get("creationDate")));
            return this.entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param messageID
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean markMessageAsRead(Long messageID) throws GPDAOException {
        checkArgument(messageID != null, "The Parameter messageID must not be null.");
        try {
            CriteriaBuilder builder = super.criteriaBuilder();
            CriteriaUpdate<GPMessage> criteriaUpdate = super.createCriteriaUpdate();
            Root<GPMessage> root = criteriaUpdate.from(this.persistentClass);
            criteriaUpdate.where(builder.equal(root.get("id"), messageID));
            criteriaUpdate.set(root.get("isRead"), TRUE);
            return (this.entityManager.createQuery(criteriaUpdate).executeUpdate() == 1 ? TRUE : FALSE);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param recipientID
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean markAllMessagesAsReadByRecipient(Long recipientID) throws GPDAOException {
        checkArgument(recipientID != null, "The Parameter recipientID must not be null.");
        try {
            Query query = this.entityManager.createQuery("UPDATE Message m set m.isRead=true " +
                    "where m.isRead=false and m.recipient.id IN (\n" +
                    "  SELECT r.id \n" +
                    "  FROM GPAccount r \n" +
                    "  WHERE r.id=:recipientID)");
            query.setParameter("recipientID", recipientID);
            return (query.executeUpdate() != -1) ? TRUE : FALSE;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }

    /**
     * @param recipientID
     * @param toDate
     * @return {@link Boolean}
     * @throws GPDAOException
     */
    @Override
    public Boolean markMessagesAsReadByDate(Long recipientID, Date toDate) throws GPDAOException {
        checkArgument(recipientID != null, "The Parameter recipientID must not be null.");
        checkArgument(toDate != null, "The Parameter toDate must not be null.");
        try {
            Query query = this.entityManager.createQuery("UPDATE Message m set m.isRead=true " +
                    "where m.isRead=false and m.creationDate<=:toDate and m.recipient.id IN (\n" +
                    "  SELECT r.id \n" +
                    "  FROM GPAccount r \n" +
                    "  WHERE r.id=:recipientID)");
            query.setParameter("toDate", toDate);
            query.setParameter("recipientID", recipientID);
            return (query.executeUpdate() != -1) ? TRUE : FALSE;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GPDAOException(ex);
        }
    }
}
