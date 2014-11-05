/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services.core.api.resources;

import org.geosdi.geoplatform.request.organization.WSPutRolePermissionRequest;
import org.geosdi.geoplatform.request.organization.WSSaveRoleRequest;
import org.geosdi.geoplatform.responce.collection.GuiComponentsPermissionMapData;
import org.geosdi.geoplatform.responce.role.GPGetRoleResponse;
import org.geosdi.geoplatform.responce.role.WSGetRoleResponse;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPACLResource {

    // <editor-fold defaultstate="collapsed" desc="ACL">
    // ==========================================================================
    // === ACL
    // ==========================================================================
    /**
     * Retrieve all Organization Roles.
     *
     * @param organization organization name
     *
     * @return {@link GPGetRoleResponse} GetRoleResponse with all Roles
     *
     * @throws Exception
     */
    WSGetRoleResponse getAllRoles(String organization) throws Exception;

    /**
     * Retrieve GUI Component permissions for an Account.
     * <p/>
     * It is based on accounts with disjoined authorities.
     *
     * @param accountID account ID
     *
     * @return Map that contains GUI Components permissions, with: <ul> <li> key
     * = ID Component </li> <li> value = Permission </li> </ul>
     *
     * @throws Exception if the account is not found
     */
    GuiComponentsPermissionMapData getAccountPermission(Long accountID)
            throws Exception;

    /**
     * Retrieve the GUI Component permissions for a Role (Authority).
     *
     * @param role role (authority) name
     * @param organization organization name
     *
     * @return Map that contains GUI Components permissions, with: key = ID
     * Component value = Permission
     * 
     * @throws Exception if the role (authority) is not found
     */
    GuiComponentsPermissionMapData getRolePermission(String role,
            String organization) throws Exception;

    /**
     * Update the permission of a role (authority).
     *
     * @param putRolePermissionReq
     *
     * @return if the update was successful
     * 
     * @throws Exception if the role (authority) is not found
     */
    Boolean updateRolePermission(WSPutRolePermissionRequest putRolePermissionReq)
            throws Exception;

    /**
     * Save a new role (authority).
     *
     * @param saveRoleReq
     *
     * @return if the saving was successful
     * @throws Exception if the role (authority) already exist
     */
    Boolean saveRole(WSSaveRoleRequest saveRoleReq) throws Exception;
    // </editor-fold>
}
