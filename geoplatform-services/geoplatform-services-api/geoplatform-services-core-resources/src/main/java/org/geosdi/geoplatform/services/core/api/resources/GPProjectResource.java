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

import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.project.CloneProjectRequest;
import org.geosdi.geoplatform.request.project.SaveProjectRequest;
import org.geosdi.geoplatform.response.ProjectDTOContainer;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPProjectResource {

    // <editor-fold defaultstate="collapsed" desc="Project">
    // ==========================================================================
    // === Project
    // ==========================================================================

    /**
     * Save a Project and its Account owner.
     *
     * @param saveProjectRequest
     * @return the Project ID
     * @throws Exception if the Account not found or if the Project is not valid
     */
    Long saveProject(SaveProjectRequest saveProjectRequest) throws Exception;

    /**
     * Save a Project.
     *
     * @param project the Project to save
     * @return the Project ID
     * @throws Exception if the Project is not valid
     * @see #saveProject(java.lang.String,
     * org.geosdi.geoplatform.core.model.GPProject, boolean)
     * @deprecated only for test purpose
     */
    @Deprecated
    Long insertProject(GPProject project) throws Exception;

    /**
     * @param cloneProjectRequest
     * @return
     * @throws Exception
     */
    Long cloneProject(CloneProjectRequest cloneProjectRequest) throws Exception;

    /**
     * @param request
     * @return {@link ProjectDTOContainer}
     * @throws Exception
     */
    ProjectDTOContainer findInternalPublicProjects(PaginatedSearchRequest request) throws Exception;

    /**
     * @param request
     * @return {@link ProjectDTOContainer}
     * @throws Exception
     */
    ProjectDTOContainer findExternalPublicProjects(PaginatedSearchRequest request) throws Exception;

    /**
     * Update a Project.
     *
     * @param project the Project to update
     * @return the Project ID
     * @throws Exception             if the Project not found
     * @throws IllegalParameterFault if the Project is not valid
     */
    Long updateProject(GPProject project) throws Exception;

    /**
     * Delete a Project.
     *
     * @param projectID the Project to delete
     * @return true if the Project was deleted
     * @throws Exception if the Project not found
     */
    Boolean deleteProject(Long projectID) throws Exception;

    /**
     * Retrieve a Project.
     *
     * @param projectID the Project ID
     * @return the Project to retrieve
     * @throws Exception if the Project not found
     */
    GPProject getProjectDetail(Long projectID) throws Exception;

    /**
     * Retrieve the number of elements of a Project.
     *
     * @param projectID the Project ID
     * @return the number of elements to retrieve
     * @throws Exception if Project not found
     */
    Integer getNumberOfElementsProject(Long projectID) throws Exception;

    /**
     * Set a Project as shared.
     *
     * @param projectID the Project ID
     * @throws Exception if Project not found
     */
    void setProjectShared(Long projectID) throws Exception;
    // </editor-fold>

}
