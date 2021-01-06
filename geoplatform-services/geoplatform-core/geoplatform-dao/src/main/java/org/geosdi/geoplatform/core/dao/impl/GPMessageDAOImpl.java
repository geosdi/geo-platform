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
package org.geosdi.geoplatform.core.dao.impl;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.geosdi.geoplatform.core.dao.GPMessageDAO;
import org.geosdi.geoplatform.core.model.GPMessage;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Transactional
public class GPMessageDAOImpl extends BaseDAO<GPMessage, Long>
        implements GPMessageDAO {

    @Override
    public List<GPMessage> findAll() {
        return super.findAll();
    }

    @Override
    public GPMessage find(Long id) {
        return super.find(id);
    }

    @Override
    public GPMessage[] find(Long... ids) {
        return super.find(ids);
    }

    @Override
    public void persist(GPMessage... entities) {
        super.persist(entities);
    }

    @Override
    public GPMessage save(GPMessage entity) {
        return super.save(entity);
    }

    @Override
    public GPMessage merge(GPMessage entity) {
        return super.merge(entity);
    }

    @Override
    public GPMessage[] merge(GPMessage... entities) {
        return super.merge(entities);
    }

    @Override
    public boolean remove(GPMessage entity) {
        return super.remove(entity);
    }

    @Override
    public boolean removeById(Long id) {
        return super.removeById(id);
    }

    @Override
    public List<GPMessage> search(ISearch search) {
        return super.search(search);
    }

    @Override
    public int count(ISearch search) {
        return super.count(search);
    }

    @Override
    public List<GPMessage> findAllMessagesByRecipient(Long recipientID) {
        Search search = new Search();
        search.addFilterEqual("recipient.id", recipientID);
        search.addSortDesc("creationDate");
        return search(search);
    }

    @Override
    public List<GPMessage> findUnreadMessagesByRecipient(Long recipientID) {
        Search search = new Search();
        search.addFilterEqual("recipient.id", recipientID);
        search.addFilterEqual("isRead", false);
        search.addSortDesc("creationDate");
        return search(search);
    }

    @Override
    public boolean markMessageAsRead(Long messageID) {
        Query query = em().createQuery("UPDATE Message m SET m.isRead = true WHERE m.id=:messageID").
                setParameter("messageID", messageID);
        int recordsUpdated = query.executeUpdate();

        if (recordsUpdated != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean markAllMessagesAsReadByRecipient(Long recipientID) {
        Search search = new Search();
        search.addFilterEqual("recipient.id", recipientID);
        search.addFilterEqual("isRead", false);
        List<GPMessage> unreadMessages = search(search);

        for (GPMessage message : unreadMessages) {
            message.setRead(true);
        }
        super.merge(unreadMessages.toArray(new GPMessage[unreadMessages.size()]));

        return true;
    }

    @Override
    public boolean markMessagesAsReadByDate(Long recipientID, Date toDate) {
        Search search = new Search();
        search.addFilterEqual("recipient.id", recipientID);
        search.addFilterLessOrEqual("creationDate", toDate);
        search.addFilterEqual("isRead", false);
        List<GPMessage> unreadMessages = search(search);

        for (GPMessage message : unreadMessages) {
            message.setRead(true);
        }
        super.merge(unreadMessages.toArray(new GPMessage[unreadMessages.size()]));

        return true;
    }
}
