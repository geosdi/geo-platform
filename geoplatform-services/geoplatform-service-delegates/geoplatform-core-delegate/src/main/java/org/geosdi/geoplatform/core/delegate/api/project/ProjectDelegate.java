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
package org.geosdi.geoplatform.core.delegate.api.project;

import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.PutAccountsProjectRequest;
import org.geosdi.geoplatform.request.RequestByAccountProjectIDs;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.request.project.CloneProjectRequest;
import org.geosdi.geoplatform.request.project.ImportProjectRequest;
import org.geosdi.geoplatform.request.project.SaveProjectRequest;
import org.geosdi.geoplatform.response.*;
import org.geosdi.geoplatform.services.core.api.resources.GPAccountProjectResource;
import org.geosdi.geoplatform.services.core.api.resources.GPFolderProjectResource;
import org.geosdi.geoplatform.services.core.api.resources.GPProjectResource;

import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ProjectDelegate extends GPProjectResource, GPAccountProjectResource, GPFolderProjectResource {

    //<editor-fold defaultstate="collapsed" desc="Project">
    // =========================================================================
    // === Project
    // =========================================================================    
    @Override
    Long saveProject(SaveProjectRequest saveProjectRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    Long insertProject(GPProject project) throws IllegalParameterFault;

    @Override
    Long cloneProject(CloneProjectRequest cloneProjectRequest) throws Exception;

    /**
     * @param request
     * @return {@link ProjectDTOContainer}
     * @throws Exception
     */
    @Override
     ProjectDTOContainer findInternalPublicProjects(PaginatedSearchRequest request) throws Exception;

    /**
     * @param request
     * @return {@link ProjectDTOContainer}
     * @throws Exception
     */
    @Override
    ProjectDTOContainer findExternalPublicProjects(PaginatedSearchRequest request) throws Exception;

    @Override
    Long updateProject(GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    Boolean deleteProject(Long projectID) throws ResourceNotFoundFault;

    @Override
    GPProject getProjectDetail(Long projectID) throws ResourceNotFoundFault;

    @Override
    Integer getNumberOfElementsProject(Long projectID) throws
            ResourceNotFoundFault;

    /**
     * @param projectID
     * @return {@link ShortProjectDTO}
     * @throws ResourceNotFoundFault
     */
    ShortProjectDTO getShortProject(Long projectID) throws ResourceNotFoundFault;

    @Override
    void setProjectShared(Long projectID) throws ResourceNotFoundFault;

    @Override
    Boolean setProjectOwner(RequestByAccountProjectIDs request)
            throws ResourceNotFoundFault;

    @Override
    GPAccountProject getProjectOwner(Long projectID) throws
            ResourceNotFoundFault;

    @Override
    GPProject getDefaultProject(Long accountID) throws ResourceNotFoundFault;

    @Override
    ProjectDTO getDefaultProjectDTO(Long accountID) throws ResourceNotFoundFault;

    @Override
    GPProject updateDefaultProject(Long accountID, Long projectID) throws
            ResourceNotFoundFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AccountProject">
    // =========================================================================
    // === AccountProject
    // =========================================================================
    @Override
    Long insertAccountProject(GPAccountProject accountProject) throws
            IllegalParameterFault;

    @Override
    Long updateAccountProject(GPAccountProject accountProject)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    Boolean deleteAccountProject(Long accountProjectID)
            throws ResourceNotFoundFault;

    @Override
    GPAccountProject getAccountProject(Long accountProjectID)
            throws ResourceNotFoundFault;

    @Override
    WSGetAccountProjectsResponse getAccountProjectsByAccountID(Long accountID);

    @Override
    WSGetAccountProjectsResponse getAccountProjectsByProjectID(Long projectID);

    @Override
    GPAccountProject getAccountProjectByAccountAndProjectIDs(Long accountID,
            Long projectID) throws ResourceNotFoundFault;

    @Override
    Long getAccountProjectsCount(Long accountID, SearchRequest request)
            throws ResourceNotFoundFault;

    @Override
    GPAccountProject getDefaultAccountProject(Long accountID) throws
            ResourceNotFoundFault;

    @Override
    List<ProjectDTO> searchAccountProjects(Long accountID,
            PaginatedSearchRequest request) throws ResourceNotFoundFault;

    @Override
    Boolean saveAccountProjectProperties(AccountProjectPropertiesDTO accountProjectProperties) throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    ShortAccountDTOContainer getAccountsByProjectID(Long projectID)
            throws ResourceNotFoundFault;

    @Override
    ShortAccountDTOContainer getAccountsToShareByProjectID(Long projectID)
            throws ResourceNotFoundFault;

    @Override
    Boolean updateAccountsProjectSharing(PutAccountsProjectRequest apRequest)
            throws ResourceNotFoundFault, IllegalParameterFault;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder / Project">
    // =========================================================================
    // === Folder / Project
    // =========================================================================
    @Override
    ProjectDTO getProjectWithRootFolders(Long projectID, Long accountID)
            throws ResourceNotFoundFault;

    @Override
    ProjectDTO getProjectWithExpandedFolders(Long projectID, Long accountID)
            throws ResourceNotFoundFault;

    @Override
    ProjectDTO exportProject(Long projectID) throws ResourceNotFoundFault;

    @Override
    Long importProject(ImportProjectRequest impRequest) throws Exception;
    //</editor-fold>
}
