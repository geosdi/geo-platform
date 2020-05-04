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

import org.geosdi.geoplatform.request.project.ImportProjectRequest;
import org.geosdi.geoplatform.response.ProjectDTO;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPFolderProjectResource {

    // <editor-fold defaultstate="collapsed" desc="Folder / Project">
    /**
     * Retrieve only the root folders (top-level folders) of a Project.
     * <p/>
     * Return the Account owner only if the Account that load the project is not
     * the Account owner.
     *
     * @param projectID the Project ID
     * @param accountID
     * @return the root folders
     * 
     * @throws Exception if Project not found
     */
    ProjectDTO getProjectWithRootFolders(Long projectID,  Long accountID)
            throws Exception;

    /**
     * <p>
     * Retrieve a Project. Retrieve also the root folders (top-level folders)
     * with content (sub-folders and layers), so all expanded folders (at any
     * level) in cascade.
     * <p/>
     * <p>
     * Return the Account owner only if the Account that load the project is not
     * the Account owner.
     * </p>
     *
     * @param projectID the Project ID
     * @param accountID
     * @return the Project to retrieve
     * 
     * @throws Exception if Project not found
     */
    ProjectDTO getProjectWithExpandedFolders(Long projectID, Long accountID)
            throws Exception;

    /**
     * Export a Project with its contents (folders and layers).
     *
     * @param projectID the Project ID
     * @return the Project to export
     * 
     * @throws Exception if Project not found
     */
    ProjectDTO exportProject(Long projectID) throws Exception;

    /**
     * Import a Project with its contents (folders and layers) for an Account
     * owner.
     *
     * @param impRequest
     *
     * @return the Project ID
     * @throws Exception if Project and its contents is not valid or
     * if Account not found
     */
    Long importProject(ImportProjectRequest impRequest) throws Exception;
    // </editor-fold>
}
