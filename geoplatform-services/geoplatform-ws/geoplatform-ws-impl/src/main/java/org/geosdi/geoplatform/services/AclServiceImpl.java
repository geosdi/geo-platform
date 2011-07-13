//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
//</editor-fold>
package org.geosdi.geoplatform.services;

import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.geosdi.geoplatform.core.acl.AclEntry;
import org.geosdi.geoplatform.core.acl.AclObjectIdentity;
import org.geosdi.geoplatform.core.acl.AclSid;
import org.geosdi.geoplatform.core.acl.GuiComponent;
import org.geosdi.geoplatform.core.acl.GuiComponentPermission;
import org.geosdi.geoplatform.core.acl.dao.AclClassDAO;
import org.geosdi.geoplatform.core.acl.dao.AclEntryDAO;
import org.geosdi.geoplatform.core.acl.dao.AclObjectIdentityDAO;
import org.geosdi.geoplatform.core.acl.dao.AclSidDAO;
import org.geosdi.geoplatform.core.acl.dao.GuiComponentDAO;
import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 *
 */
class AclServiceImpl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // DAO
    private GPUserDAO userDao;
    private GPAuthorityDAO authorityDao;
    // ACL DAO
    private AclClassDAO classDao;
    private AclSidDAO sidDao;
    private AclObjectIdentityDAO objectIdentityDao;
    private AclEntryDAO entryDao;
    private GuiComponentDAO guiComponentDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param userDao
     *          the userDao to set
     */
    public void setUserDao(GPUserDAO userDao) {
        this.userDao = userDao;
    }

    /**
     * @param authorityDao
     *          the authorityDao to set
     */
    public void setAuthorityDao(GPAuthorityDAO authorityDao) {
        this.authorityDao = authorityDao;
    }

    /**
     * @param classDao
     *          the classDao to set
     */
    public void setClassDao(AclClassDAO classDao) {
        this.classDao = classDao;
    }

    /**
     * @param sidDao
     *          the sidDao to set
     */
    public void setSidDao(AclSidDAO sidDao) {
        this.sidDao = sidDao;
    }

    /**
     * @param objectIdentityDao
     *          the objectIdentityDao to set
     */
    public void setObjectIdentityDao(AclObjectIdentityDAO objectIdentityDao) {
        this.objectIdentityDao = objectIdentityDao;
    }

    /**
     * @param entryDao
     *          the entryDao to set
     */
    public void setEntryDao(AclEntryDAO entryDao) {
        this.entryDao = entryDao;
    }

    /**
     * @param guiComponentDao
     *          the guiComponentDao to set
     */
    public void setGuiComponentDao(GuiComponentDAO guiComponentDao) {
        this.guiComponentDao = guiComponentDao;
    }
    //</editor-fold>

    /**
     * Retrieve the persmission on the GUI Component for a user
     * 
     * It is based on users with disjoined authorities
     * 
     * @param userId
     * @return HashMap, Permissions on GUI Components for a user, with:
     *      key = ID Component
     *      value = Permission
     * @throws ResourceNotFoundFault
     *      if the user not found
     */
    public GuiComponentsPermissionMapData getUserGuiComponentVisible(long userId)
            throws ResourceNotFoundFault {
        // Retrieve the user
        GPUser user = userDao.find(userId);
        if (user == null) {
            throw new ResourceNotFoundFault("User not found", userId);
        }

        GuiComponentsPermissionMapData mapComponentPermission = new GuiComponentsPermissionMapData();

        // Retrieve the Authorities of the User
        List<GPAuthority> authorities = authorityDao.findByUsername(user.getUsername());
        logger.trace("\n*** #Authorities: {} ***", authorities.size());
        // For each Autorities (disjoined)
        for (GPAuthority authority : authorities) {
            String nameAuthority = authority.getAuthority();
            logger.trace("\n*** nameAuthority: {} ***", nameAuthority);
            // Retrieve the Sid corresponding to the Authority
            AclSid sid = sidDao.findBySid(nameAuthority, false);
            logger.trace("\n*** AclSid:\n{}\n***", sid);
            // Retrieve the ACEs of the Sid
            List<AclEntry> entries = entryDao.findBySid(sid.getId());
            logger.trace("\n*** #Entries: {} ***", entries.size());
            // For each ACEs
            // (ACL has a single ACE for User+GuiComponent,
            // because there is a singe Permission)
            for (AclEntry entry : entries) {
                logger.trace("\n*** AclEntry:\n{}\n***", entry);
                if (entry.getMask().equals(GuiComponentPermission.VISIBLE.getMask())) {
                    AclObjectIdentity objectIdentity = entry.getAclObject();
                    logger.trace("\n*** AclObjectIdentity:\n{}\n***", objectIdentity);
                    GuiComponent gc = guiComponentDao.find(objectIdentity.getObjectId());
                    logger.trace("\n*** GuiComponent:\n{}\n***", gc);
                    logger.debug("\n*** ComponentId: {} ***\n*** Granting: {} ***",
                            gc.getComponentId(), entry.isGranting());
                    mapComponentPermission.getGuiComponentsPermissionMap().put(gc.getComponentId(), entry.isGranting());
                }
            }
        }
        return mapComponentPermission;
    }
}
