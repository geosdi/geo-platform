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
package org.geosdi.geoplatform.support.jackson;

import org.geosdi.geoplatform.request.organization.WSPutRolePermissionRequest;
import org.geosdi.geoplatform.request.organization.WSSaveRoleRequest;
import org.geosdi.geoplatform.response.collection.GuiComponentsPermissionMapData;
import org.geosdi.geoplatform.response.role.WSGetRoleResponse;
import static org.geosdi.geoplatform.support.jackson.GPBaseJacksonSupportTest.jacksonSupport;
import org.junit.Test;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPJacksonRolePermissionsSupportTest extends GPBaseJacksonSupportTest {
    
    @Test
    public void saveRoleRequestDataMappingTest() throws Exception {
        WSSaveRoleRequest saveRoleRequest = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        SAVE_ROLE_REQUEST_DATA_JSON), WSSaveRoleRequest.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@@@@@@@@@@@SAVE_ROLE_REQUEST_DATA_MAPPING"
                + " : {}\n\n", saveRoleRequest);
        
        super.marshall(saveRoleRequest);
    }
    
    @Test
    public void guiComponentsPermissionDataMappingTest() throws Exception {
        GuiComponentsPermissionMapData guiComponents = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        GUI_COMPONENTS_PERMISSION_DATA_JSON),
                GuiComponentsPermissionMapData.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@@@GUI_COMPONENTS_PERMISSION_DATA_MAPPING"
                + " : {}\n\n", guiComponents);
        
        super.marshall(guiComponents);
    }
    
    @Test
    public void putRolePermissionDataMappingTest() throws Exception {
        WSPutRolePermissionRequest putPermissionReques = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        PUT_ROLE_PERMISSION_REQUEST_DATA_JSON),
                WSPutRolePermissionRequest.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@PUT_ROLE_PERMISSION_REQUEST_DATA_MAPPING"
                + " : {}\n\n", putPermissionReques);
        
        super.marshall(putPermissionReques);
    }
    
    @Test
    public void getRoleResponseDataMappingTest() throws Exception {
        WSGetRoleResponse getRoleResponse = jacksonSupport.getDefaultMapper().readValue(
                Thread.currentThread().getContextClassLoader().getResourceAsStream(
                        GET_ROLE_RESPONSE_DATA_JSON),
                WSGetRoleResponse.class);
        
        logger.info("\n\n@@@@@@@@@@@@@@@@GET_ROLE_RESPONSE_REQUEST_DATA_MAPPING"
                + " : {}\n\n", getRoleResponse);
        
        super.marshall(getRoleResponse);
    }
}
