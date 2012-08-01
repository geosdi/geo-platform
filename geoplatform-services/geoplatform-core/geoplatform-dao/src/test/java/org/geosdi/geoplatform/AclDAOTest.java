/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.configurator.gui.GuiComponentIDs;
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
import org.geosdi.geoplatform.core.model.GPUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class AclDAOTest extends BaseDAOTest {

    @Autowired
    protected AclClassDAO classDAO;
    //
    @Autowired
    protected AclSidDAO sidDAO;
    //
    @Autowired
    protected AclObjectIdentityDAO objectIdentityDAO;
    //
    @Autowired
    protected AclEntryDAO entryDAO;
    //
    @Autowired
    protected GuiComponentDAO guiComponentDAO;
    // ACL
    private static final String emailSuperUserTestAcl = "super_user_test_acl";
    private static final String emailAdminTestAcl = "admin_acl_test";
    private static final String emailUserTestAcl = "user_acl_test";
    //
    private AclClass gcClass;
    private AclSid superUser;
    private AclSid admin;
    private AclSid user;
    private AclSid viewer;
    private AclSid sigv;

    @Test
    public void testCheckAclDAOs() {
        logger.trace("\n\t@@@ testCheckAclDAOs @@@");
        Assert.assertNotNull("organizationDAO is NULL", super.organizationDAO);
        Assert.assertNotNull("accountDAO is NULL", super.accountDAO);
//        Assert.assertNotNull("authorityDAO is NULL", super.authorityDAO);
        //
        Assert.assertNotNull("classDAO is NULL", classDAO);
        Assert.assertNotNull("sidDAO is NULL", sidDAO);
        Assert.assertNotNull("objectIdentityDAO is NULL", objectIdentityDAO);
        Assert.assertNotNull("entryDAO is NULL", entryDAO);
        Assert.assertNotNull("guiComponentDAO is NULL", guiComponentDAO);
    }

    @Test
    public void testManageAcl() {
        logger.trace("\n\t@@@ testManageAcl @@@");
        this.removeUserByEmail(emailSuperUserTestAcl);
        this.removeUserByEmail(emailAdminTestAcl);
        this.removeUserByEmail(emailUserTestAcl);
        this.removeAllAcl();

        Assert.assertEquals("All Classes doesn't REMOVED", 0, classDAO.findAll().size());
        Assert.assertEquals("All Sids doesn't REMOVED", 0, sidDAO.findAll().size());
        Assert.assertEquals("All ObjectIdentities doesn't REMOVED", 0, objectIdentityDAO.findAll().size());
        Assert.assertEquals("All Entries doesn't REMOVED", 0, entryDAO.findAll().size());
        Assert.assertEquals("All GuiComponents doesn't REMOVED", 0, guiComponentDAO.findAll().size());

        // Persist or merge Organization
        super.organizationTest = organizationDAO.findByName("geoSDI");
        if (organizationTest == null) {
            super.organizationTest = super.createOwnOrganization();
        }
        logger.debug("\n*** Organization to SAVE:\n{}\n***", organizationTest);
        organizationDAO.save(organizationTest);

        // Insert Users and Authorities ACL
        // ACL Data
        super.insertUser(emailSuperUserTestAcl, organizationTest, GPRole.ADMIN, GPRole.USER);
        super.insertUser(emailAdminTestAcl, organizationTest, GPRole.ADMIN);
        super.insertUser(emailUserTestAcl, organizationTest, GPRole.USER);
        // Insert ACL data
        this.insertGuiComponents();
    }

    private void removeUserByEmail(String email) {
        GPUser usr = accountDAO.findByEmail(email);
        if (usr != null) {
            accountDAO.remove(usr);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Remove all ACL data">
    private void removeAllAcl() {
        removeAllEntries();
        removeAllObjectIdentities();
        removeAllSids();
        removeAllClasses();
        removeAllGuiComponents();
    }

    private void removeAllEntries() {
        List<AclEntry> entries = entryDAO.findAll();
        for (AclEntry e : entries) {
            logger.trace("\n*** AclEntry to REMOVE:\n{}\n***", e);
            boolean removed = entryDAO.remove(e);
            Assert.assertTrue("Old AclEntry NOT removed", removed);
        }
    }

    private void removeAllObjectIdentities() {
        List<AclObjectIdentity> objectIdentities = objectIdentityDAO.findAll();
        for (AclObjectIdentity oi : objectIdentities) {
            logger.trace("\n*** AclObjectIdentity to REMOVE:\n{}\n***", oi);
            boolean removed = objectIdentityDAO.remove(oi);
            Assert.assertTrue("Old AclObjectIdentity NOT removed", removed);
        }
    }

    private void removeAllSids() {
        List<AclSid> sids = sidDAO.findAll();
        for (AclSid s : sids) {
            logger.trace("\n*** AclSid to REMOVE:\n{}\n***", s);
            boolean removed = sidDAO.remove(s);
            Assert.assertTrue("Old AclSid NOT removed", removed);
        }
    }

    private void removeAllClasses() {
        List<AclClass> classes = classDAO.findAll();
        for (AclClass c : classes) {
            logger.trace("\n*** AclClass to REMOVE:\n{}\n***", c);
            boolean removed = classDAO.remove(c);
            Assert.assertTrue("Old AclClass NOT removed", removed);
        }
    }

    private void removeAllGuiComponents() {
        List<GuiComponent> guiComponents = guiComponentDAO.findAll();
        for (GuiComponent gc : guiComponents) {
            logger.trace("\n*** GuiComponent to REMOVE:\n{}\n***", gc);
            boolean removed = guiComponentDAO.remove(gc);
            Assert.assertTrue("Old GuiComponent NOT removed", removed);
        }
    }
    //</editor-fold>

    private void insertGuiComponents() {
        // Unique class of Object Identities
        this.gcClass = new AclClass(GuiComponent.class.getName());
        //
        logger.debug("\n*** AclClass to INSERT:\n{}\n***", gcClass);
        classDAO.persist(gcClass);

        this.createSids();

        Map<String, GuiComponent> gcMap = this.createGuiComponents();

        Map<String, AclObjectIdentity> objIdMap = this.createObjectIdentities(gcMap);

        this.createEntries(objIdMap);
    }

    private void createSids() {
        // Owner of all Object Identities
        this.superUser = new AclSid(true, emailSuperUserTestAcl);
        // Users of interest
        this.admin = new AclSid(false, GPRole.ADMIN.toString(), organizationTest);
        this.user = new AclSid(false, GPRole.USER.toString(), organizationTest);
        this.viewer = new AclSid(false, GPRole.VIEWER.toString(), organizationTest);
        //
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", superUser);
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", admin);
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", user);
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", viewer);
        //
        this.sigv = new AclSid(true, "SIGV");
        //
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", sigv);
        //
        sidDAO.persist(superUser, admin, user, viewer, sigv);
    }

    private Map<String, GuiComponent> createGuiComponents() {
        Map<String, GuiComponent> gcMap = new HashMap<String, GuiComponent>();
        // Gui Components
        for (String ID : GuiComponentIDs.LIST_ALL) {
            gcMap.put(ID, new GuiComponent(ID));
        }
        for (String ID : GuiComponentIDs.LIST_OWN_SIGV) {
            gcMap.put(ID, new GuiComponent(ID));
        }
        //
        guiComponentDAO.persist(gcMap.values().toArray(new GuiComponent[gcMap.size()]));

        return gcMap;
    }

    private Map<String, AclObjectIdentity> createObjectIdentities(Map<String, GuiComponent> gcMap) {
        Map<String, AclObjectIdentity> objIdMap = new HashMap<String, AclObjectIdentity>();
        // Object Identities
        for (String componentID : GuiComponentIDs.LIST_ALL) {
            Long id = gcMap.get(componentID).getId();
            // SuperUser is the owner of all Object Identities
            objIdMap.put(componentID, new AclObjectIdentity(gcClass, id, superUser));
        }
        for (String componentID : GuiComponentIDs.LIST_OWN_SIGV) {
            Long id = gcMap.get(componentID).getId();
            // SuperUser is the owner of all Object Identities
            objIdMap.put(componentID, new AclObjectIdentity(gcClass, id, superUser));
        }
        //
        objectIdentityDAO.persist(objIdMap.values().toArray(new AclObjectIdentity[objIdMap.size()]));

        return objIdMap;
    }

    private void createEntries(Map<String, AclObjectIdentity> objIdMap) {
        // ACE
        int enable = GeoPlatformPermission.ENABLE.getMask();
        //
        Map<String, AclEntry> entriesMap = new HashMap<String, AclEntry>();
        // Admin
        for (String componentID : GuiComponentIDs.LIST_ALL) {
            entriesMap.put(GPRole.ADMIN + componentID,
                           new AclEntry(objIdMap.get(componentID), 1, admin, enable, true));
        }
        // User
        for (Map.Entry<String, Boolean> e : GuiComponentIDs.MAP_USER.entrySet()) {
            if (e.getValue() != null) {
                entriesMap.put(GPRole.USER + e.getKey(),
                               new AclEntry(objIdMap.get(e.getKey()), 2, user, enable, e.getValue()));
            }
        }
        // Viewer
        for (Map.Entry<String, Boolean> e : GuiComponentIDs.MAP_VIEWER.entrySet()) {
            if (e.getValue() != null) {
                // Ace Order is 3 because the entries of admin and user should be added before
                entriesMap.put(GPRole.VIEWER + e.getKey(),
                               new AclEntry(objIdMap.get(e.getKey()), 3, viewer, enable, e.getValue()));
            }
        }
        // SIGV Application
        for (Map.Entry<String, Boolean> e : GuiComponentIDs.MAP_APPLICATION_SIGV.entrySet()) {
            if (e.getValue() != null) {
                entriesMap.put("SIGV" + e.getKey(),
                               new AclEntry(objIdMap.get(e.getKey()), 4, sigv, enable, e.getValue()));
            }
        }
        //
        entryDAO.persist(entriesMap.values().toArray(new AclEntry[entriesMap.size()]));
    }
}
