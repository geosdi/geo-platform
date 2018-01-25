/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author giuseppe
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class BootstrapInitializerTest extends BaseInitializerTest {

    /**
     * Put the DB into a substantial state.
     */
    @Test
    public void testBootstrap() {
        logger.trace("\n\t@@@ testBootstrap @@@");

        Assert.assertNotNull("accountDAO is NULL", accountDAO);
        Assert.assertNotNull("authorityDAO is NULL", authorityDAO);
        Assert.assertNotNull("projectDAO is NULL", projectDAO);
        Assert.assertNotNull("userProjectsDAO is NULL", accountProjectDAO);
        Assert.assertNotNull("folderDAO is NULL", folderDAO);
        Assert.assertNotNull("layerDAO is NULL", layerDAO);
//        Assert.assertNotNull("styleDAO is NULL", styleDAO);
        Assert.assertNotNull("serverDAO is NULL", serverDAO);
        Assert.assertNotNull("organizationDAO is NULL", organizationDAO);
        Assert.assertNotNull("messageDAO is NULL", messageDAO);
        // ACL
        Assert.assertNotNull("classDAO is NULL", classDAO);
        Assert.assertNotNull("sidDAO is NULL", sidDAO);
        Assert.assertNotNull("objectIdentityDAO is NULL", objectIdentityDAO);
        Assert.assertNotNull("entryDAO is NULL", entryDAO);
        Assert.assertNotNull("guiComponentDAO is NULL", guiComponentDAO);

        super.removeAll();

//        Assert.assertEquals("All Styles doesn't REMOVED", 0, styleDAO.findAll().size());
        Assert.assertEquals("All projectDAO doesn't REMOVED", 0, projectDAO.findAll().size());
        Assert.assertEquals("All AccountProject doesn't REMOVED", 0, accountProjectDAO.findAll().size());
        Assert.assertEquals("All Layers doesn't REMOVED", 0, layerDAO.findAll().size());
        Assert.assertEquals("All Folders doesn't REMOVED", 0, folderDAO.findAll().size());
        Assert.assertEquals("All Authorities doesn't REMOVED", 0, authorityDAO.findAll().size());
        Assert.assertEquals("All Accounts doesn't REMOVED", 0, accountDAO.findAll().size());
        Assert.assertEquals("All Servers doesn't REMOVED", 0, serverDAO.findAll().size());
        Assert.assertEquals("All Organizations doesn't REMOVED", 0, organizationDAO.findAll().size());
        Assert.assertEquals("All Messages doesn't REMOVED", 0, messageDAO.findAll().size());
        // ACL
        Assert.assertEquals("All Classes doesn't REMOVED", 0, classDAO.findAll().size());
        Assert.assertEquals("All Sids doesn't REMOVED", 0, sidDAO.findAll().size());
        Assert.assertEquals("All ObjectIdentities doesn't REMOVED", 0, objectIdentityDAO.findAll().size());
        Assert.assertEquals("All Entries doesn't REMOVED", 0, entryDAO.findAll().size());
        Assert.assertEquals("All GuiComponents doesn't REMOVED", 0, guiComponentDAO.findAll().size());

        super.insertData();
    }
}