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
package org.geosdi.geoplatform.initializer;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * @author giuseppe
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class BootstrapInitializerTest extends BaseInitializerTest {

    /**
     * Put the DB into a substantial state.
     */
    @Test
    public void a_testBootstrap() {
        logger.trace("\n\t@@@ testBootstrap @@@");

        assertNotNull("accountDAO is NULL", accountDAO);
        assertNotNull("authorityDAO is NULL", authorityDAO);
        assertNotNull("projectDAO is NULL", projectDAO);
        assertNotNull("userProjectsDAO is NULL", accountProjectDAO);
        assertNotNull("folderDAO is NULL", folderDAO);
        assertNotNull("layerDAO is NULL", layerDAO);
//        Assert.assertNotNull("styleDAO is NULL", styleDAO);
        assertNotNull("serverDAO is NULL", serverDAO);
        assertNotNull("organizationDAO is NULL", organizationDAO);
        assertNotNull("messageDAO is NULL", messageDAO);
        // ACL
        assertNotNull("classDAO is NULL", classDAO);
        assertNotNull("sidDAO is NULL", sidDAO);
        assertNotNull("objectIdentityDAO is NULL", objectIdentityDAO);
        assertNotNull("entryDAO is NULL", entryDAO);
        assertNotNull("guiComponentDAO is NULL", guiComponentDAO);

        super.removeAll();

//        Assert.assertEquals("All Styles doesn't REMOVED", 0, styleDAO.findAll().size());
        assertEquals("All projectDAO doesn't REMOVED", 0, projectDAO.findAll().size());
        assertEquals("All AccountProject doesn't REMOVED", 0, accountProjectDAO.findAll().size());
        assertEquals("All Layers doesn't REMOVED", 0, layerDAO.findAll().size());
        assertEquals("All Folders doesn't REMOVED", 0, folderDAO.findAll().size());
        assertEquals("All Authorities doesn't REMOVED", 0, authorityDAO.findAll().size());
        assertEquals("All Accounts doesn't REMOVED", 0, accountDAO.findAll().size());
        assertEquals("All Servers doesn't REMOVED", 0, serverDAO.findAll().size());
        assertEquals("All Organizations doesn't REMOVED", 0, organizationDAO.findAll().size());
        assertEquals("All Messages doesn't REMOVED", 0, messageDAO.findAll().size());
        // ACL
        assertEquals("All Classes doesn't REMOVED", 0, classDAO.findAll().size());
        assertEquals("All Sids doesn't REMOVED", 0, sidDAO.findAll().size());
        assertEquals("All ObjectIdentities doesn't REMOVED", 0, objectIdentityDAO.findAll().size());
        assertEquals("All Entries doesn't REMOVED", 0, entryDAO.findAll().size());
        assertEquals("All GuiComponents doesn't REMOVED", 0, guiComponentDAO.findAll().size());
        super.insertData();
    }

    @Test
    public void b_findInternalPublicProjectTest() throws Exception {
        assertTrue(this.projectDAO.findInternalPublic(10, 0).size() == 0);
    }

    @Test
    public void c_findExternalPublicProjectTest() throws Exception {
        assertTrue(this.projectDAO.findExternalPublic(10, 0).size() == 0);
    }

    @Test
    public void d_countInternalPublicProjectTest() throws Exception {
        logger.info("\n\t@@@ Count: {} @@@ ", this.projectDAO.getTotalInternalPublic());
    }

    @Test
    public void e_countExternalPublicProjectTest() throws Exception {
        logger.info("\n\t@@@ Count: {} @@@ ", this.projectDAO.getTotalExternalPublic());
    }
}