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
package org.geosdi.geoplatform.core.delegate.impl.acl;

import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.core.acl.AclClass;
import org.geosdi.geoplatform.core.acl.AclEntry;
import org.geosdi.geoplatform.core.acl.AclObjectIdentity;
import org.geosdi.geoplatform.core.acl.AclSid;
import org.geosdi.geoplatform.core.acl.GeoPlatformPermission;
import org.geosdi.geoplatform.core.acl.GuiComponent;
import org.geosdi.geoplatform.core.acl.dao.AclClassDAO;
import org.geosdi.geoplatform.core.acl.dao.AclEntryDAO;
import org.geosdi.geoplatform.core.acl.dao.AclObjectIdentityDAO;
import org.geosdi.geoplatform.core.acl.dao.AclSidDAO;
import org.geosdi.geoplatform.core.acl.dao.GuiComponentDAO;
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.core.dao.GPAuthorityDAO;
import org.geosdi.geoplatform.core.dao.GPOrganizationDAO;
import org.geosdi.geoplatform.core.delegate.api.acl.AclDelegate;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.organization.WSPutRolePermissionRequest;
import org.geosdi.geoplatform.request.organization.WSSaveRoleRequest;
import org.geosdi.geoplatform.response.collection.GuiComponentsPermissionMapData;
import org.geosdi.geoplatform.response.role.WSGetRoleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.Permission;
import org.springframework.stereotype.Component;

/**
 * ACL service delegate.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@Component(value = "gpAclDelegate")
public class GPAclDelegate implements AclDelegate {

    private static final Logger logger = LoggerFactory.getLogger(
            GPAclDelegate.class);
    //
    @Autowired
    private GPAccountDAO accountDao;
    @Autowired
    private GPAuthorityDAO authorityDao;
    @Autowired
    private GPOrganizationDAO organizationDao;
    @Autowired
    private AclClassDAO classDao;
    @Autowired
    private AclSidDAO sidDao;
    @Autowired
    private AclObjectIdentityDAO objectIdentityDao;
    @Autowired
    private AclEntryDAO entryDao;
    @Autowired
    private GuiComponentDAO guiComponentDao;

    @Override
    public WSGetRoleResponse getAllRoles(String organization) throws
            ResourceNotFoundFault {
        List<AclSid> sids = sidDao.findByPrincipal(false, organization);

        WSGetRoleResponse getRoles = new WSGetRoleResponse();

        for (AclSid sid : sids) {
            getRoles.addRole(sid.getSid());
        }
        return getRoles;
    }

    @Override
    public List<String> getAllGuiComponentIDs() {
        return guiComponentDao.findAllGuiComponentIDs();
    }

    @Override
    public GuiComponentsPermissionMapData getApplicationPermission(String appID)
            throws ResourceNotFoundFault {
        // Retrieve the account
        GPApplication application = accountDao.findByAppID(appID);
        if (application == null) {
            throw new ResourceNotFoundFault(
                    "Application not found with appID \"" + appID + "\"");
        }

        GuiComponentsPermissionMapData mapComponentPermission = new GuiComponentsPermissionMapData();
        Map<String, Boolean> permissionMap = mapComponentPermission.getPermissionMap();

        // Retrieve the Sid corresponding to the Application ID
        AclSid sid = this.findSid(appID, true, null);
        // Retrieve the ACEs of the Sid
        List<AclEntry> entries = entryDao.findBySid(sid.getId());
        logger.trace("\n*** #Entries: {} ***", entries.size());
        // For each ACEs
        // (ACL has a single ACE for Role+GuiComponent,
        // because there is a singe Permission)
        for (AclEntry entry : entries) {
            logger.trace("\n*** AclEntry:\n{}\n***", entry);
            if (entry.getMask().equals(GeoPlatformPermission.ENABLE.getMask())) {
                AclObjectIdentity objectIdentity = entry.getAclObject();
                logger.trace("\n*** AclObjectIdentity:\n{}\n***", objectIdentity);
                GuiComponent gc = guiComponentDao.find(
                        objectIdentity.getObjectId());
                logger.trace("\n*** GuiComponent:\n{}\n***", gc);
                logger.debug("\n*** ComponentId: {} ***\n*** Granting: {} ***",
                        gc.getComponentId(), entry.isGranting());

                permissionMap.put(gc.getComponentId(), entry.isGranting());
            }
        }

        return mapComponentPermission;
    }

    @Override
    public GuiComponentsPermissionMapData getAccountPermission(Long accountID)
            throws ResourceNotFoundFault {
        // Retrieve the account
        GPAccount account = accountDao.find(accountID);
        if (account == null) {
            throw new ResourceNotFoundFault("Account not found", accountID);
        }

        GuiComponentsPermissionMapData mapComponentPermission = new GuiComponentsPermissionMapData();

        // Retrieve the Authorities of the Account
        List<GPAuthority> authorities = authorityDao.findByAccountNaturalID(
                account.getNaturalID());
        logger.trace("\n*** #Authorities: {} ***", authorities.size());
        // For each Autorities (disjoined)
        for (GPAuthority authority : authorities) {
            String nameAuthority = authority.getAuthority();
            logger.trace("\n*** nameAuthority: {} ***", nameAuthority);

            this.elaborateGuiComponentACEs(nameAuthority,
                    account.getOrganization().getName(),
                    mapComponentPermission.getPermissionMap());
        }
        return mapComponentPermission;
    }

    @Override
    public GuiComponentsPermissionMapData getRolePermission(String role,
            String organization)
            throws ResourceNotFoundFault {
        GuiComponentsPermissionMapData mapComponentPermission = new GuiComponentsPermissionMapData();

        this.elaborateGuiComponentACEs(role, organization,
                mapComponentPermission.getPermissionMap());

        return mapComponentPermission;
    }

    @Override
    public Boolean updateRolePermission(
            WSPutRolePermissionRequest putRolePermissionReq)
            throws ResourceNotFoundFault {
        if (putRolePermissionReq == null) {
            throw new IllegalArgumentException("The WSPutRolePermissionRequest "
                    + "must not be null.");
        }
        String role = putRolePermissionReq.getRole();
        String organization = putRolePermissionReq.getOrganization();
        GuiComponentsPermissionMapData mapComponentPermission = putRolePermissionReq.getMapComponentPermission();

        logger.debug("\n*** Role: {} ***", role);
        // Retrieve the Sid corresponding to the Role (Authority) name
        AclSid sid = this.findSid(role, false, organization);

        AclClass clazz = classDao.findByClass(GuiComponent.class.getName());
        Permission permission = GeoPlatformPermission.ENABLE;

        Map<String, Boolean> permissionMap = mapComponentPermission.getPermissionMap();
        for (Map.Entry<String, Boolean> entry : permissionMap.entrySet()) {
            String componentId = entry.getKey();
            Boolean granting = entry.getValue();
            logger.debug("\n*** Entry to manage: {} - {} ***", componentId,
                    granting);

            GuiComponent gc = guiComponentDao.findByComponentId(componentId);
            if (gc == null) {
                throw new ResourceNotFoundFault(
                        "GuiComponent \"" + componentId + "\" not found");
            }
            logger.trace("\n*** GuiComponent:\n{}\n***", gc);

            AclObjectIdentity obj = objectIdentityDao.findByObjectId(
                    clazz.getId(), gc.getId());

            List<AclEntry> aces = entryDao.findByObjectIdentity(obj.getId());
            boolean managed = this.manageExistingEntry(aces, sid, permission,
                    granting); // UPDATE or DELETE the entry
            if (!managed) { // ADD new entry
                Integer aceOrder = ((aces != null) && (aces.size() > 0))
                        ? (aces.get(aces.size() - 1).getAceOrder() + 1) : 0;
                AclEntry e = new AclEntry(obj, aceOrder, sid,
                        permission.getMask(), granting);
                entryDao.persist(e);
                logger.debug("\n*** ACE added ***");
            }
        }

        return true;
    }

    @Override
    public Boolean saveRole(WSSaveRoleRequest saveRoleReq) throws
            IllegalParameterFault {
        if (saveRoleReq == null) {
            throw new IllegalParameterFault("The WSSaveRoleRequest must not "
                    + "be null.");
        }
        String organization = saveRoleReq.getOrganization();
        String role = saveRoleReq.getRole();

        AclSid sid = sidDao.findBySid(role, false, organization);
        if (sid != null) {
            throw new IllegalParameterFault(
                    "Authority (Role) \"" + role + "\" already exist");
        }

        GPOrganization org = organizationDao.findByName(organization);
        if (org == null) {
            throw new IllegalParameterFault(
                    "Organization \"" + organization + "\" not found");
        }

        sid = new AclSid(false, role, org);
        sidDao.persist(sid);
        return true;
    }

    private void elaborateGuiComponentACEs(String sidName, String organization,
            Map<String, Boolean> permissionMap)
            throws ResourceNotFoundFault {
        // Retrieve the Sid corresponding to the Role (Authority) name and the relative organization
        AclSid sid = this.findSid(sidName, false, organization);
        // Retrieve the ACEs of the Sid
        List<AclEntry> entries = entryDao.findBySid(sid.getId());
        logger.trace("\n*** #Entries: {} ***", entries.size());
        // For each ACEs
        // (ACL has a single ACE for Role+GuiComponent,
        // because there is a singe Permission)
        for (AclEntry entry : entries) {
            logger.trace("\n*** AclEntry:\n{}\n***", entry);
            if (entry.getMask().equals(GeoPlatformPermission.ENABLE.getMask())) {
                AclObjectIdentity objectIdentity = entry.getAclObject();
                logger.trace("\n*** AclObjectIdentity:\n{}\n***", objectIdentity);
                GuiComponent gc = guiComponentDao.find(
                        objectIdentity.getObjectId());
                logger.trace("\n*** GuiComponent:\n{}\n***", gc);
                logger.debug("\n*** ComponentId: {} ***\n*** Granting: {} ***",
                        gc.getComponentId(), entry.isGranting());

                permissionMap.put(gc.getComponentId(), entry.isGranting());
            }
        }

//        for (String componentID : GuiComponentIDs.LIST_ALL) {
//            if (!permissionMap.containsKey(componentID)) {
//                logger.debug("\n*** NONE added: {} ***", componentID);
//                permissionMap.put(componentID, null);
//            }
//        }
    }

    private boolean manageExistingEntry(List<AclEntry> aces, AclSid sid,
            Permission permission, Boolean granting) {
        for (AclEntry ace : aces) {
            if (ace.getAclSid().equals(sid)
                    && ace.getMask().equals(permission.getMask())) {
                if (granting == null) {
                    entryDao.remove(ace);
                    logger.debug("\n*** ACE deleted ***");
                    return true;
                } else {
                    ace.setGranting(granting);
                    entryDao.merge(ace);
                    logger.debug("\n*** ACE updated ***");
                    return true;
                }
            }
        }
        return false;
    }

    private AclSid findSid(String sidName, boolean principal,
            String organization)
            throws ResourceNotFoundFault {
        AclSid sid;
        if (organization == null) {
            sid = sidDao.findBySid(sidName, principal);
        } else {
            sid = sidDao.findBySid(sidName, principal, organization);
        }

        if (sid == null) {
            throw new ResourceNotFoundFault(
                    "Authority (Role) \"" + sidName + "\" not found");
        }
        logger.trace("\n*** AclSid:\n{}\n***", sid);
        return sid;
    }

}
