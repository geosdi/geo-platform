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

import java.util.List;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.PutAccountsProjectRequest;
import org.geosdi.geoplatform.request.RequestByAccountProjectIDs;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.AccountProjectPropertiesDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.geosdi.geoplatform.responce.ShortAccountDTOContainer;
import org.geosdi.geoplatform.responce.WSGetAccountProjectsResponse;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPAccountProjectResource {

    // <editor-fold defaultstate="collapsed" desc="AccountProject">
    // ==========================================================================
    // === AccountProject
    // ==========================================================================
    /**
     * Insert an AccountProject.
     *
     * @param accountProject the AccountProject to insert
     * @return the AccountProject ID
     *
     * @throws Exception if AccountProject is not valid
     */
    Long insertAccountProject(GPAccountProject accountProject)
            throws Exception;

    /**
     * Update an AccountProject, the Account and the Project attached.
     *
     * @param accountProject the AccountProject to update
     * @return the AccountProject ID
     *
     * @throws Exception if AccountProject, Account or Project not found or if
     * AccountProject, Account or Project are not valid
     */
    Long updateAccountProject(GPAccountProject accountProject)
            throws Exception;

    /**
     * Delete an AccountProject by ID.
     *
     * @param accountProjectID the AccountProject to delete
     * @return true id the AccountProject was deleted
     *
     * @throws Exception if AccountProject not found
     */
    Boolean deleteAccountProject(Long accountProjectID) throws Exception;

    /**
     * Retrieve an AccountProject by ID.
     *
     * @param accountProjectID the AccountProject ID
     * @return the AccountProject to retrieve
     *
     * @throws Exception if AccountProject not found
     */
    GPAccountProject getAccountProject(Long accountProjectID) throws Exception;

    /**
     * Retrieve the AccountProjects of an Account.
     *
     * @param accountID the Account ID
     * @return List of AccountProject
     */
    WSGetAccountProjectsResponse getAccountProjectsByAccountID(Long accountID);

    /**
     * Retrieve the AccountProjects for a Project.
     *
     * @param projectID the Project ID
     * @return List of AccountProject
     */
    WSGetAccountProjectsResponse getAccountProjectsByProjectID(Long projectID);

    /**
     * Retrieve an AccountProject of an Account for a Project.
     *
     * @param accountID the Account ID
     * @param projectID the Project ID
     * @return the AccountProject to retrieve
     *
     * @throws Exception if AccountProject not found
     */
    GPAccountProject getAccountProjectByAccountAndProjectIDs(Long accountID,
            Long projectID) throws Exception;

    /**
     * Retrieve the number of AccountProjects of an Account, founded by search
     * request.
     *
     * @param accountID the Account ID
     * @param request the request that wrap the username (for User) or the
     * string ID (for Application)
     * @return the number of AccountProjects found
     *
     * @throws Exception if Account not found
     */
    Long getAccountProjectsCount(Long accountID, SearchRequest request)
            throws Exception;

    /**
     * Retrieve the default AccountProject of an Account, or null if the Account
     * don't have a default Project.
     *
     * @param accountID the Account ID
     * @return the AccountProject to retrieve or null
     *
     * @throws Exception if Account not found
     */
    GPAccountProject getDefaultAccountProject(Long accountID)
            throws Exception;

    /**
     * Retrieve all the Account Projects in paginated way. There are two Project
     * type, first that the Account is the owner, and latter related at a shared
     * Project.
     * <p/>
     * Each Project result, if shared, contain the own Account owner.
     *
     * @param accountID the Account ID
     * @param request the request that wrap Project ID, Project name and Account
     * ID
     * @return the paginated list of Projects of the Account
     *
     * @throws Exception if Account not found
     */
    List<ProjectDTO> searchAccountProjects(Long accountID,
            PaginatedSearchRequest request) throws Exception;

    /**
     * Retrieve the Account owner of a Project.
     *
     * @param projectID the Project ID
     * @return the Account owner
     *
     * @throws Exception if Project not found
     */
    GPAccountProject getProjectOwner(Long projectID) throws Exception;

    /**
     * Set an Account owner for a Project.
     *
     * @param request the request that wrap Project ID and Account ID
     * @return true if the Account owner was changed
     *
     * @throws Exception if Project or Account not found
     */
    Boolean setProjectOwner(RequestByAccountProjectIDs request)
            throws Exception;

    /**
     * Retrieve the default Project of an Account. If the Account don't have a
     * default Project, return null.
     *
     * @param accountID the Account ID
     * @return the default Project or null
     *
     * @throws Exception if Account not found
     */
    GPProject getDefaultProject(Long accountID) throws Exception;

    /**
     * Retrieve the default Project of an Account. The Project result, if
     * shared, contain the own Account owner. If the Account don't have a
     * default Project, return null.
     *
     * @param accountID the Account ID
     * @return the default Project or null
     *
     * @throws Exception if Account not found
     */
    ProjectDTO getDefaultProjectDTO(Long accountID) throws Exception;

    /**
     * Update the default Project for an Account.
     *
     * @param accountID the Account ID
     * @param projectID the Project ID
     * @return true if the default Project was updated
     *
     * @throws Exception if Account or Project not found
     */
    GPProject updateDefaultProject(Long accountID, Long projectID)
            throws Exception;

    /**
     * Save the Project information and optionally set it as default for an
     * Account.
     *
     * @param accountProjectProperties request that wrap Project information and
     * Account ID
     * @return true if the properties was saved
     *
     * @throws Exception if Project or Account not found or if Project name is
     * invalid
     */
    Boolean saveAccountProjectProperties(
            AccountProjectPropertiesDTO accountProjectProperties)
            throws Exception;

    /**
     * Retrieve all Accounts of a (shared) Project.
     *
     * @param projectID the Project ID
     * @return the Account owner and of the Accounts to which the Project is
     * shared
     *
     * @throws Exception if Project not found
     */
    ShortAccountDTOContainer getAccountsByProjectID(Long projectID)
            throws Exception;

    /**
     * Retrieve all Accounts to which it is possible to share a Project.
     *
     * @param projectID the Project ID
     * @return the Accounts to which the Project is not shared
     *
     * @throws Exception if Project not found
     */
    ShortAccountDTOContainer getAccountsToShareByProjectID(Long projectID)
            throws Exception;

    /**
     * Update all at once the relations of sharing for a Project. The Account
     * owner ID relation must be present, otherwise the Project will be unshare.
     *
     * @param apRequest {@link PutAccountsProjectRequest}
     *
     * @return true if the relations of sharing are updated
     * @throws ResourceNotFoundFault if Project not found or an Account not
     * found
     * 
     * @throws Exception
     */
    Boolean updateAccountsProjectSharing(PutAccountsProjectRequest apRequest)
            throws Exception;
    // </editor-fold>

}
