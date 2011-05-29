//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
package org.geosdi.geoplatform;

import java.util.List;
import junit.framework.Assert;
import org.geosdi.geoplatform.core.acl.AclClass;
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
import org.geosdi.geoplatform.core.model.GPAuthority;
import org.geosdi.geoplatform.core.model.GPUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 *
 */
public class AclDAOTest extends BaseDAOTest {

    @Autowired
    protected AclClassDAO classDAO;
    @Autowired
    protected AclSidDAO sidDAO;
    @Autowired
    protected AclObjectIdentityDAO objectIdentityDAO;
    @Autowired
    protected AclEntryDAO entryDAO;
    @Autowired
    protected GuiComponentDAO guiComponentDAO;

    @Test
    public void testCheckAclDAOs() {
        logger.trace("\n\t@@@ testCheckAclDAOs @@@");
        Assert.assertNotNull(classDAO);
        Assert.assertNotNull(sidDAO);
        Assert.assertNotNull(objectIdentityDAO);
        Assert.assertNotNull(entryDAO);
        Assert.assertNotNull(guiComponentDAO);
    }

    @Test
    public void testManageAcl() {
        logger.trace("\n\t@@@ testManageAcl @@@");
        super.removeAllAuthorities();
        super.removeAllUsers();
        removeAllAcl();

        Assert.assertEquals("All Classes doesn't REMOVED", 0, classDAO.findAll().size());
        Assert.assertEquals("All Sids doesn't REMOVED", 0, sidDAO.findAll().size());
        Assert.assertEquals("All ObjectIdentities doesn't REMOVED", 0, objectIdentityDAO.findAll().size());
        Assert.assertEquals("All Entries doesn't REMOVED", 0, entryDAO.findAll().size());
        Assert.assertEquals("All GuiComponents doesn't REMOVED", 0, guiComponentDAO.findAll().size());

        // Insert Users and Authorities ACL
        insertData();
        insertGuiComponents();
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
            logger.debug("\n*** AclEntry to REMOVE:\n{}\n***", e);
            boolean removed = entryDAO.remove(e);
            Assert.assertTrue("Old AclEntry NOT removed", removed);
        }
    }

    private void removeAllObjectIdentities() {
        List<AclObjectIdentity> objectIdentities = objectIdentityDAO.findAll();
        for (AclObjectIdentity oi : objectIdentities) {
            logger.debug("\n*** AclObjectIdentity to REMOVE:\n{}\n***", oi);
            boolean removed = objectIdentityDAO.remove(oi);
            Assert.assertTrue("Old AclObjectIdentity NOT removed", removed);
        }
    }

    private void removeAllSids() {
        List<AclSid> sids = sidDAO.findAll();
        for (AclSid s : sids) {
            logger.debug("\n*** AclSid to REMOVE:\n{}\n***", s);
            boolean removed = sidDAO.remove(s);
            Assert.assertTrue("Old AclSid NOT removed", removed);
        }
    }

    private void removeAllClasses() {
        List<AclClass> classes = classDAO.findAll();
        for (AclClass c : classes) {
            logger.debug("\n*** AclClass to REMOVE:\n{}\n***", c);
            boolean removed = classDAO.remove(c);
            Assert.assertTrue("Old AclClass NOT removed", removed);
        }
    }

    private void removeAllGuiComponents() {
        List<GuiComponent> guiComponents = guiComponentDAO.findAll();
        for (GuiComponent gc : guiComponents) {
            logger.debug("\n*** GuiComponent to REMOVE:\n{}\n***", gc);
            boolean removed = guiComponentDAO.remove(gc);
            Assert.assertTrue("Old GuiComponent NOT removed", removed);
        }
    }
    //</editor-fold>

    private void insertGuiComponents() {
        // Unique class of Object Identities
        AclClass gcClass = new AclClass(GuiComponent.class.getName());
        //
        logger.debug("\n*** AclClass to INSERT:\n{}\n***", gcClass);
        classDAO.persist(gcClass);

        // Owner of all Object Identities
        AclSid superUser = new AclSid(true, nameSuperUser);
        // Users of interest
        AclSid admin = new AclSid(false, roleAdmin);
        AclSid user = new AclSid(false, roleUser);
        //
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", admin);
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", user);
        logger.debug("\n*** AclSid to INSERT:\n{}\n***", superUser);
        sidDAO.persist(superUser, admin, user);

        // Gui Components
        GuiComponent gc1 = new GuiComponent("GeoPlatformInfoApp");
        GuiComponent gc2 = new GuiComponent("GetFeatureInfo");
        GuiComponent gc3 = new GuiComponent("Measure");
        GuiComponent gc4 = new GuiComponent("MeasureArea");
        //
        logger.debug("\n*** GuiComponent to INSERT:\n{}\n***", gc1);
        logger.debug("\n*** GuiComponent to INSERT:\n{}\n***", gc2);
        logger.debug("\n*** GuiComponent to INSERT:\n{}\n***", gc3);
        logger.debug("\n*** GuiComponent to INSERT:\n{}\n***", gc4);
        guiComponentDAO.persist(gc1, gc2, gc3, gc4);

        // Object Identities
        AclObjectIdentity objGc1 = new AclObjectIdentity(gcClass, gc1.getId(), superUser);
        AclObjectIdentity objGc2 = new AclObjectIdentity(gcClass, gc2.getId(), superUser);
        AclObjectIdentity objGc3 = new AclObjectIdentity(gcClass, gc3.getId(), superUser);
        AclObjectIdentity objGc4 = new AclObjectIdentity(gcClass, gc4.getId(), superUser);
        //
        logger.debug("\n*** AclObjectIdentity to INSERT:\n{}\n***", objGc1);
        logger.debug("\n*** AclObjectIdentity to INSERT:\n{}\n***", objGc2);
        logger.debug("\n*** AclObjectIdentity to INSERT:\n{}\n***", objGc3);
        logger.debug("\n*** AclObjectIdentity to INSERT:\n{}\n***", objGc4);
        objectIdentityDAO.persist(objGc1, objGc2, objGc3, objGc4);

        // ACE
        int visible = GuiComponentPermission.VISIBLE.getMask();
        // ACEs of ACL#1 - GeoPlatformInfoApp - VISIBLE at ADMIN; USER
        AclEntry entry1Obj1 = new AclEntry(objGc1, 1, admin, visible, true, true, true);
        AclEntry entry2Obj1 = new AclEntry(objGc1, 2, user, visible, true, true, true);
        // ACEs of ACL#2 - GetFeatureInfo - VISIBLE at ADMIN; USER
        AclEntry entry1Obj2 = new AclEntry(objGc2, 1, admin, visible, true, true, true);
        AclEntry entry2Obj2 = new AclEntry(objGc2, 2, user, visible, true, true, true);
        // ACEs of ACL#3 - Measure - VISIBLE at ADMIN
        AclEntry entry1Obj3 = new AclEntry(objGc3, 1, admin, visible, true, true, true);
        AclEntry entry2Obj3 = new AclEntry(objGc3, 2, user, visible, false, true, true);
        // ACEs of ACL#4 - MeasureArea - VISIBLE at ADMIN
        AclEntry entry1Obj4 = new AclEntry(objGc4, 1, admin, visible, true, true, true);
        AclEntry entry2Obj4 = new AclEntry(objGc4, 2, user, visible, false, true, true);
        //
        logger.debug("\n*** AclEntry to INSERT:\n{}\n***", entry1Obj1);
        logger.debug("\n*** AclEntry to INSERT:\n{}\n***", entry2Obj1);
        logger.debug("\n*** AclEntry to INSERT:\n{}\n***", entry1Obj2);
        logger.debug("\n*** AclEntry to INSERT:\n{}\n***", entry2Obj2);
        logger.debug("\n*** AclEntry to INSERT:\n{}\n***", entry1Obj3);
        logger.debug("\n*** AclEntry to INSERT:\n{}\n***", entry2Obj3);
        logger.debug("\n*** AclEntry to INSERT:\n{}\n***", entry1Obj4);
        logger.debug("\n*** AclEntry to INSERT:\n{}\n***", entry2Obj4);
        entryDAO.persist(entry1Obj1, entry2Obj1, entry1Obj2, entry2Obj2,
                entry1Obj3, entry2Obj3, entry1Obj4, entry2Obj4);
    }
}
