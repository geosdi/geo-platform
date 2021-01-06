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
package org.geosdi.geoplatform.model.rest;

import java.util.HashMap;
import java.util.List;
import org.geosdi.geoplatform.gui.shared.GPRole;
import org.geosdi.geoplatform.initializer.GuiComponentIDs;
import org.geosdi.geoplatform.request.organization.WSPutRolePermissionRequest;
import org.geosdi.geoplatform.request.organization.WSSaveRoleRequest;
import org.geosdi.geoplatform.response.collection.GuiComponentsPermissionMapData;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class RSRolePermissionTest extends BasicRestServiceTest {
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
        
        saveRolesRest();
    }
    
    @Test
    public void updateRolePermissionTestRest() throws Exception {
        GuiComponentsPermissionMapData userMapData = new GuiComponentsPermissionMapData();
        userMapData.setPermissionMap(new HashMap<>(
                GuiComponentIDs.MAP_USER));
        
        Assert.assertTrue(gpWSClient.updateRolePermission(
                new WSPutRolePermissionRequest(userMapData,
                        GPRole.USER.getRole(), organizationNameRSTest)));
        
        GuiComponentsPermissionMapData wsUserMapData = gpWSClient.getRolePermission(
                GPRole.USER.getRole(), organizationNameRSTest);
        
        logger.trace("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@RETRIEVE GuiComponents for"
                + " USER_ROLE : {}\n\n", wsUserMapData);
        
        Assert.assertEquals(51, wsUserMapData.getPermissionMap().size());
        
        GuiComponentsPermissionMapData viewerMapData = new GuiComponentsPermissionMapData();
        viewerMapData.setPermissionMap(new HashMap<String, Boolean>(
                GuiComponentIDs.MAP_VIEWER));
        
        Assert.assertTrue(gpWSClient.updateRolePermission(
                new WSPutRolePermissionRequest(viewerMapData,
                        GPRole.VIEWER.getRole(), organizationNameRSTest)));
        
        GuiComponentsPermissionMapData wsViewerMapData = gpWSClient.getRolePermission(
                GPRole.VIEWER.getRole(), organizationNameRSTest);
        
        logger.trace("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@RETRIEVE GuiComponents for"
                + " VIEWER_ROLE : {}\n\n", wsViewerMapData);
        
        Assert.assertEquals(50, wsViewerMapData.getPermissionMap().size());
    }
    
    @Test
    public void getAllRolesTestRest() throws Exception {
        List<String> roles = gpWSClient.getAllRoles(organizationNameRSTest)
                .getRoles();
        logger.trace("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ROLES_FOUND for {} ,"
                + "are : {}\n\n", organizationNameRSTest, roles);
        
        Assert.assertEquals(3, roles.size());
    }
    
    @Test
    public void getRolePermissionTestRest() throws Exception {
        GuiComponentsPermissionMapData wsAdminMapData = gpWSClient.getRolePermission(
                GPRole.ADMIN.getRole(), organizationNameRSTest);
        
        logger.trace("\n\n@@@@@@@@@@@@@@@@@@@@@@@@@@@RETRIEVE GuiComponents for"
                + " ADMIN_ROLE : {}\n\n", wsAdminMapData);
        Assert.assertEquals(0, wsAdminMapData.getPermissionMap().size());
    }
    
    final void saveRolesRest() throws Exception {
        Assert.assertTrue(gpWSClient.saveRole(new WSSaveRoleRequest(
                GPRole.ADMIN.getRole(), organizationNameRSTest)));
        Assert.assertTrue(gpWSClient.saveRole(new WSSaveRoleRequest(
                GPRole.USER.getRole(), organizationNameRSTest)));
        Assert.assertTrue(gpWSClient.saveRole(new WSSaveRoleRequest(
                GPRole.VIEWER.getRole(), organizationNameRSTest)));
    }
    
}
