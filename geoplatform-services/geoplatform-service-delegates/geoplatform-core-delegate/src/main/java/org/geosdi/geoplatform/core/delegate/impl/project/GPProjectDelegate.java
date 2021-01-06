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
package org.geosdi.geoplatform.core.delegate.impl.project;

import com.google.common.collect.Lists;
import com.googlecode.genericdao.search.Search;
import org.geosdi.geoplatform.core.dao.*;
import org.geosdi.geoplatform.core.delegate.api.project.ProjectDelegate;
import org.geosdi.geoplatform.core.delegate.impl.project.function.GPFolderFunction;
import org.geosdi.geoplatform.core.delegate.impl.project.function.GPLayerFunction;
import org.geosdi.geoplatform.core.model.*;
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
import org.geosdi.geoplatform.response.factory.AccountDTOFactory;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Boolean.TRUE;
import static org.geosdi.geoplatform.core.binding.IGPProjectBinder.GPProjectBinder.newGProjectBinder;
import static org.geosdi.geoplatform.response.ProjectDTO.convertToProjectDTOList;

/**
 * Project service delegate.
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 * @author Michele Santomauro
 * @email michele.santomauro@geosdi.org
 */
@Component(value = "gpProjectDelegate")
public class GPProjectDelegate implements ProjectDelegate {

    private static final Logger logger = LoggerFactory.getLogger(
            GPProjectDelegate.class);
    //
    @Autowired
    private GPProjectDAO projectDao;
    @Autowired
    private GPAccountDAO accountDao;
    @Autowired
    private GPAccountProjectDAO accountProjectDao;
    @Autowired
    private GPFolderDAO folderDao;
    @Autowired
    private GPLayerDAO layerDao;
    //
    private int position;

    //<editor-fold defaultstate="collapsed" desc="Project">
    // =========================================================================
    // === Project
    // ========================================================================= 
    @Override
    public Long saveProject(SaveProjectRequest saveProjectRequest) throws
            ResourceNotFoundFault, IllegalParameterFault {
        if (saveProjectRequest == null) {
            throw new IllegalParameterFault("The SaveProjetRequest must not "
                    + "be null.");
        }
        String accountNaturalID = saveProjectRequest.getAccountNaturalID();
        GPProject project = saveProjectRequest.getProject();
        boolean defaultProject = saveProjectRequest.isDefaultProject();

        EntityCorrectness.checkProject(project); // TODO assert

        GPAccount account = accountDao.findByNaturalID(accountNaturalID);
        if (account == null) {
            throw new ResourceNotFoundFault(
                    "Account with naturalID \"" + accountNaturalID + "\" not found");
        }
        EntityCorrectness.checkAccountLog(account); // TODO assert

        // The Account that save the Project is the owner
        GPAccountProject accountProject = new GPAccountProject();
        accountProject.setAccount(account);
        accountProject.setProject(project);
        accountProject.setDefaultProject(defaultProject);

        // Reset default project
        if (defaultProject) {
            GPAccountProject oldDefault = accountProjectDao.findDefaultProjectByAccountID(
                    account.getId());
            if (oldDefault != null) {
                oldDefault.setDefaultProject(false);
                accountProjectDao.merge(oldDefault);
            }
        }

        projectDao.persist(project);
        accountProjectDao.persist(accountProject);

        return project.getId();
    }

    @Override
    public Long insertProject(GPProject project) throws IllegalParameterFault {
        EntityCorrectness.checkProject(project); // TODO assert

        projectDao.persist(project);
        return project.getId();
    }

    @Override
    public Long updateProject(GPProject project) throws ResourceNotFoundFault, IllegalParameterFault {
        EntityCorrectness.checkProject(project); // TODO assert

        GPProject origProject = this.getProjectByID(project.getId());
        EntityCorrectness.checkProjectLog(origProject); // TODO assert

        // Update all properties (except the creationDate)
        origProject.setName(project.getName());
        origProject.setNumberOfElements(project.getNumberOfElements());
        origProject.setShared(project.isShared());
        origProject.setExternalPublic(project.isExternalPublic());
        origProject.setInternalPublic(project.isInternalPublic());
        projectDao.merge(origProject);
        return origProject.getId();
    }

    @Override
    public Boolean deleteProject(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        return projectDao.removeById(projectID);
    }

    @Override
    public GPProject getProjectDetail(Long projectID) throws
            ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        return project;
    }

    @Override
    public Integer getNumberOfElementsProject(Long projectID) throws
            ResourceNotFoundFault {
        GPProject project = this.getProjectDetail(projectID);

        return project.getNumberOfElements();
    }

    /**
     * @param projectID
     * @return {@link ShortProjectDTO}
     * @throws ResourceNotFoundFault
     */
    @Override
    public ShortProjectDTO getShortProject(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectDetail(projectID);
        return new ShortProjectDTO(project.getVersion(), project.getNumberOfElements(), project.isInternalPublic(), project.isExternalPublic());
    }

    @Override
    public void setProjectShared(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert
        project.setShared(true);

        projectDao.merge(project);
    }

    @Override
    public Boolean setProjectOwner(RequestByAccountProjectIDs request) throws
            ResourceNotFoundFault {
        GPProject project = this.getProjectByID(request.getProjectID());
        EntityCorrectness.checkProjectLog(project); // TODO assert

        GPAccount owner = this.getAccountByID(request.getAccountID());
        EntityCorrectness.checkAccountLog(owner); // TODO assert

        // Reset the owner
        GPAccountProject accountOwner = accountProjectDao.findOwnerByProjectID(
                project.getId());
        EntityCorrectness.checkAccountProjectLog(accountOwner); // TODO assert
        accountOwner.setPermissionMask(BasePermission.READ.getMask());
        accountProjectDao.merge(accountOwner);

        // Set the new owner
        List<GPAccountProject> accounts = accountProjectDao.findNotOwnersByProjectID(
                project.getId());
        boolean exist = false;
        for (GPAccountProject accountProject : accounts) {
            EntityCorrectness.checkAccountProjectLog(accountProject); // TODO assert
            if (owner.equals(accountProject.getAccount())) {
                accountProject.setPermissionMask(
                        BasePermission.ADMINISTRATION.getMask());
                exist = true;
                break;
            }
        }
        accountProjectDao.merge(accounts.toArray(
                new GPAccountProject[accounts.size()]));

        if (!exist) {
            GPAccountProject ownerProject = new GPAccountProject();
            ownerProject.setAccount(owner);
            ownerProject.setProject(project);
            ownerProject.setPermissionMask(
                    BasePermission.ADMINISTRATION.getMask());
            accountProjectDao.persist(ownerProject);
        }

        return TRUE;
    }

    @Override
    public GPAccountProject getProjectOwner(Long projectID) throws
            ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        GPAccountProject accountOwner = accountProjectDao.findOwnerByProjectID(
                projectID);
        EntityCorrectness.checkAccountProjectLog(accountOwner);

        return accountOwner;
    }

    @Override
    public GPProject getDefaultProject(Long accountID) throws
            ResourceNotFoundFault {
        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        GPAccountProject defaultAccountProject = accountProjectDao.findDefaultProjectByAccountID(
                accountID);
        if (defaultAccountProject == null) {
            return null;
        }

        GPProject project = defaultAccountProject.getProject();
        EntityCorrectness.checkProjectLog(project); // TODO assert

        return project;
    }

    @Override
    public ProjectDTO getDefaultProjectDTO(Long accountID) throws
            ResourceNotFoundFault {
        GPProject project = this.getDefaultProject(accountID);
        if (project == null) {
            return null;
        }

        ProjectDTO projectDTO;
        if (project.isShared()) {
            GPAccountProject ownerProject = accountProjectDao.findOwnerByProjectID(
                    project.getId());
            GPAccount owner = ownerProject.getAccount();

            projectDTO = new ProjectDTO(project, true, owner);
        } else {
            projectDTO = new ProjectDTO(project, true);
        }

        return projectDTO;
    }

    @Override
    public GPProject updateDefaultProject(Long accountID, Long projectID) throws
            ResourceNotFoundFault {
        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        GPAccountProject defaultAccountProject = accountProjectDao.forceAsDefaultProject(
                accountID, projectID);
        if (defaultAccountProject == null) {
            return null;
        }
        return project;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AccountProject">
    // =========================================================================
    // === AccountProject
    // =========================================================================
    @Override
    public Long insertAccountProject(GPAccountProject accountProject) throws
            IllegalParameterFault {
        EntityCorrectness.checkAccountProject(accountProject);

        accountProjectDao.persist(accountProject);
        return accountProject.getId();
    }

    @Override
    public Long updateAccountProject(GPAccountProject accountProject) throws
            ResourceNotFoundFault, IllegalParameterFault {
        EntityCorrectness.checkAccountProject(accountProject); // TODO assert
        accountProjectDao.merge(accountProject);

        return accountProject.getId();
    }

    @Override
    public Boolean deleteAccountProject(Long accountProjectID) throws
            ResourceNotFoundFault {
        GPAccountProject accountProject = this.getAccountProjectByID(
                accountProjectID);
        EntityCorrectness.checkAccountProjectLog(accountProject); // TODO assert

        return accountProjectDao.removeById(accountProjectID);
    }

    @Override
    public GPAccountProject getAccountProject(Long accountProjectID) throws
            ResourceNotFoundFault {
        GPAccountProject accountProject = this.getAccountProjectByID(
                accountProjectID);
        EntityCorrectness.checkAccountProjectLog(accountProject); // TODO assert

        return accountProject;
    }

    @Override
    public WSGetAccountProjectsResponse getAccountProjectsByAccountID(
            Long accountID) {
        List<GPAccountProject> accountProjectsList = accountProjectDao.findByAccountID(
                accountID);
        EntityCorrectness.checkAccountProjectListLog(accountProjectsList); // TODO assert
        return new WSGetAccountProjectsResponse(accountProjectsList);
    }

    @Override
    public WSGetAccountProjectsResponse getAccountProjectsByProjectID(
            Long projectID) {
        List<GPAccountProject> accountProjectsList = accountProjectDao.findByProjectID(
                projectID);
        EntityCorrectness.checkAccountProjectListLog(accountProjectsList); // TODO assert

        return new WSGetAccountProjectsResponse(accountProjectsList);
    }

    @Override
    public GPAccountProject getAccountProjectByAccountAndProjectIDs(
            Long accountID, Long projectID) throws ResourceNotFoundFault {
        GPAccountProject accountProject = accountProjectDao.find(accountID,
                projectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault(
                    "AccountProjects not found for with id:\"" + accountID
                            + "\" and project with id:\"" + projectID + "\"");
        }
        EntityCorrectness.checkAccountProjectLog(accountProject); // TODO assert

        return accountProject;
    }

    @Override
    public Long getAccountProjectsCount(Long accountID, SearchRequest request)
            throws ResourceNotFoundFault {
        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        Search searchCriteria = new Search(GPAccountProject.class);
        searchCriteria.addFilterEqual("account.id", accountID);
        if (request != null && request.getNameLike() != null) {
            searchCriteria.addFilterILike("project.name", request.getNameLike());
        }

        return Long.valueOf(accountProjectDao.count(searchCriteria));
    }

    @Override
    public GPAccountProject getDefaultAccountProject(Long accountID) throws
            ResourceNotFoundFault {
        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        return accountProjectDao.findDefaultProjectByAccountID(accountID);
    }

    @Override
    public List<ProjectDTO> searchAccountProjects(Long accountID,
            PaginatedSearchRequest request) throws ResourceNotFoundFault {
        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        Search searchCriteria = new Search(GPAccountProject.class);
        searchCriteria.addFilterEqual("account.id", accountID);
        searchCriteria.addSortDesc("defaultProject"); // First the default Project
        if (request != null) {
            searchCriteria.setMaxResults(request.getNum());
            searchCriteria.setPage(request.getPage());

            if (request.getNameLike() != null) {
                searchCriteria.addFilterILike("project.name",
                        request.getNameLike());
            }
        }

        List<GPAccountProject> accountProjectList = accountProjectDao.search(
                searchCriteria);
        EntityCorrectness.checkAccountProjectListLog(accountProjectList); // TODO assert

        List<ProjectDTO> projectDTOList = new ArrayList<ProjectDTO>(
                accountProjectList.size());
        for (GPAccountProject accountProject : accountProjectList) {
            GPProject project = accountProject.getProject();
            EntityCorrectness.checkProjectLog(project); // TODO assert

            ProjectDTO projectDTO;
            if (project.isShared()) {
                GPAccount owner = accountProjectDao.findOwnerByProjectID(
                        project.getId()).getAccount();
                projectDTO = new ProjectDTO(project,
                        accountProject.isDefaultProject(), owner);
            } else {
                projectDTO = new ProjectDTO(project,
                        accountProject.isDefaultProject());
            }
            projectDTOList.add(projectDTO);
        }

        return projectDTOList;
    }

    @Override
    public Boolean saveAccountProjectProperties(AccountProjectPropertiesDTO accountProjectProperties) throws ResourceNotFoundFault, IllegalParameterFault {
        GPProject project = this.getProjectByID(accountProjectProperties.getProjectID());
        EntityCorrectness.checkProjectLog(project); // TODO assert
        String projectName = accountProjectProperties.getProjectName();
        EntityCorrectness.ckeckString(projectName, "project name"); // TODO assert
        Integer version = accountProjectProperties.getProjectVersion();
        EntityCorrectness.checkInteger(version, "version");
        project.setName(projectName);
        project.setVersion(version);
        project.setShared(accountProjectProperties.isShared());
        project.setInternalPublic(accountProjectProperties.isInternalPublic());
        project.setExternalPublic(accountProjectProperties.isExternalPublic());
        project.setDescription(accountProjectProperties.getProjectDescription());
        project.setImagePath(accountProjectProperties.getPathImage());
        projectDao.merge(project);

        if (accountProjectProperties.isDefaultProject()) {
            GPAccount account = this.getAccountByID(accountProjectProperties.getAccountID());
            EntityCorrectness.checkAccountLog(account); // TODO assert
            accountProjectDao.forceAsDefaultProject(account.getId(), project.getId());
        }
        return TRUE;
    }

    @Override
    public ShortAccountDTOContainer getAccountsByProjectID(Long projectID)
            throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        List<GPAccountProject> accountProjectList = accountProjectDao.findByProjectID(
                projectID);

        List<GPAccount> accountList = new ArrayList<GPAccount>(
                accountProjectList.size());
        for (GPAccountProject accountProject : accountProjectList) {
            GPAccount account = accountProject.getAccount();
            accountList.add(account);
        }

        return new ShortAccountDTOContainer(AccountDTOFactory
                .buildShortAccountDTOList(accountList));
    }

    @Override
    public ShortAccountDTOContainer getAccountsToShareByProjectID(Long projectID)
            throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        // Retrieve info for obtain Accounts of shared Projects
        List<GPAccountProject> accountProjects = accountProjectDao.findByProjectID(
                projectID);
        EntityCorrectness.checkAccountProjectListLog(accountProjects); // TODO assert

        // Retrieve the Organization related to the shared Project
        // Note: a Project will have at least one Account, the owner of the Project
        GPOrganization organization = accountProjects.get(0).getAccount().getOrganization();

        // Retrieve all Accounts of the Organization
        List<GPAccount> accounts = accountDao.findByOrganization(
                organization.getName());
        EntityCorrectness.checkAccountListLog(accounts); // TODO assert

        // Retrieve the Accounts to share the Project
        for (GPAccountProject accountProject : accountProjects) {
            accounts.remove(accountProject.getAccount());
        }

        return new ShortAccountDTOContainer(
                AccountDTOFactory.buildShortAccountDTOList(
                        accounts));
    }

    @Override
    public Boolean updateAccountsProjectSharing(
            PutAccountsProjectRequest apRequest) throws ResourceNotFoundFault,
            IllegalParameterFault {
        if (apRequest == null) {
            throw new IllegalParameterFault(
                    "The PutAccountsProjectRequest must "
                            + "not be null.");
        }
        Long projectID = apRequest.getProjectID();
        List<Long> accountIDsProject = apRequest.getAccountIDsProject();

        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        if (accountIDsProject == null || accountIDsProject.isEmpty()) {
            logger.trace("\n*** There aren't relations of sharing to update");
            return Boolean.FALSE;
        }

        Long ownerID = accountProjectDao.findOwnerByProjectID(projectID).getAccount().getId();

        // The Account owner relation's project will not be managed
        boolean checkOwner = accountIDsProject.remove(ownerID);
        // Unshare project if the Account owner is not present
        if (!checkOwner || accountIDsProject.isEmpty()) {
            logger.trace("\n*** The project will be unshare");
            this.unshareProject(project);
            return TRUE;
        }

        logger.debug("\n*** Update all relations of sharing");

        List<GPAccountProject> accountProjectList = accountProjectDao.findNotOwnersByProjectID(
                projectID);
        Map<Long, GPAccountProject> sharingMap = new HashMap<Long, GPAccountProject>(
                accountProjectList.size());
        for (GPAccountProject accountProject : accountProjectList) {
            sharingMap.put(accountProject.getAccount().getId(), accountProject);
        }

        for (Long accountID : accountIDsProject) {
            GPAccountProject accountProject = sharingMap.remove(accountID);
            // Create a new relation of sharing
            if (accountProject == null) {
                GPAccount newAccount = this.getAccountByID(accountID);

                GPAccountProject newAccountProject = new GPAccountProject();
                newAccountProject.setAccountAndProject(newAccount, project);
                newAccountProject.setPermissionMask(
                        BasePermission.READ.getMask());
                logger.trace(
                        "\n*** Create a new relation of sharing for Account \"{}\"",
                        newAccount.getNaturalID());
                accountProjectDao.persist(newAccountProject);
            }
        }

        // Delete the remaining relations of sharing
        for (Map.Entry<Long, GPAccountProject> e : sharingMap.entrySet()) {
            logger.trace(
                    "\n*** Delete the relation of sharing for Account \"{}\"",
                    e.getValue().getAccount().getNaturalID());
            accountProjectDao.remove(e.getValue());
        }

        if (!project.isShared() && !accountIDsProject.isEmpty()) {
            project.setShared(true);
            projectDao.merge(project);
        }

        return TRUE;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Folder / Project">
    // =========================================================================
    // === Folder / Project
    @Override
    public ProjectDTO getProjectWithRootFolders(Long projectID, Long accountID)
            throws ResourceNotFoundFault {
        ProjectDTO projectDTO = this.retrieveProjectDTO(projectID, accountID);

        // Root Folders
        List<GPFolder> rootFolders = folderDao.searchRootFolders(projectID);
        logger.debug("\n*** rootFolders:\n{}", rootFolders);

        List<FolderDTO> rootFoldersDTO = FolderDTO.convertToFolderDTOList(
                rootFolders);
        projectDTO.setRootFolders(rootFoldersDTO);

        return projectDTO;
    }

    @Override
    public ProjectDTO getProjectWithExpandedFolders(Long projectID,
            Long accountID) throws ResourceNotFoundFault {
        ProjectDTO projectDTO = this.retrieveProjectDTO(projectID, accountID);

        // Root Folders
        List<GPFolder> rootFolders = folderDao.searchRootFolders(projectID);
        logger.debug("\n*** rootFolders:\n{}", rootFolders);

        List<FolderDTO> rootFoldersDTO = FolderDTO.convertToFolderDTOList(rootFolders);
        projectDTO.setRootFolders(rootFoldersDTO);

        this.fillProjectExpandedFolders(projectID, rootFoldersDTO);

        return projectDTO;
    }

    @Override
    public ProjectDTO exportProject(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert
        ProjectDTO projectDTO = new ProjectDTO(project);

        // Root Folders
        List<GPFolder> rootFolders = folderDao.searchRootFolders(projectID);
        logger.debug("\n*** rootFolders:\n{}", rootFolders);

        List<FolderDTO> rootFoldersDTO = FolderDTO.convertToFolderDTOList(
                rootFolders);
        projectDTO.setRootFolders(rootFoldersDTO);

        Map<Long, FolderDTO> mapProjectFolders = new HashMap<Long, FolderDTO>();
        for (FolderDTO rootFolder : rootFoldersDTO) {
            mapProjectFolders.put(rootFolder.getId(), rootFolder);
        }

        // Sub Folders
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.addFilterEqual("project.id", projectID);
        searchCriteria.addFilterNotNull("parent.id");
        List<GPFolder> subFolders = folderDao.search(searchCriteria);

        Map<String, GPFolder> subFoldersMap = new HashMap<String, GPFolder>(
                subFolders.size());
        for (GPFolder folder : subFolders) {
            String key = this.createParentChildKey(folder.getParent(), folder);
            logger.debug("\n*** key: {}\n*** subFolder ***\n{}", key, folder);
            subFoldersMap.put(key, folder);
        }

        mapProjectFolders = this.fillProjectFolders(rootFoldersDTO,
                subFoldersMap, mapProjectFolders);

        // Sub Layers
        searchCriteria = new Search(GPLayer.class);
        searchCriteria.addFilterEqual("project.id", projectID);
        List<GPLayer> subLayers = layerDao.search(searchCriteria);

        for (GPLayer layer : subLayers) {
            FolderDTO parent = mapProjectFolders.get(layer.getFolder().getId());
            if (parent == null) { // TODO assert: only for test purpose
                throw new ResourceNotFoundFault("Parent folder not found",
                        layer.getFolder().getId());
            }

            ShortLayerDTO layerDTO;
            if (layer instanceof GPRasterLayer) {
                layerDTO = new RasterLayerDTO((GPRasterLayer) layer);
            } else {
                layerDTO = new VectorLayerDTO((GPVectorLayer) layer);
            }
            parent.addLayer(layerDTO);
        }

        return projectDTO;
    }

    @Override
    public Long cloneProject(CloneProjectRequest cloneProjectRequest) throws Exception {
        GPAccount gpAccount = accountDao.find(cloneProjectRequest.getAccountID());
        if (gpAccount == null) {
            throw new ResourceNotFoundFault(
                    "Account not found for with id:\"" + cloneProjectRequest.getAccountID());
        }
        GPProject gpProject = projectDao.find(cloneProjectRequest.getGpProjectID());
        gpProject.setShared(false);
        if (gpProject == null) {
            throw new ResourceNotFoundFault(
                    "Project not found for with id:\"" + cloneProjectRequest.getGpProjectID());
        }
        try {
            // Root Folders
            List<GPFolder> rootFolders = folderDao.searchRootFolders(cloneProjectRequest.getGpProjectID());
            logger.debug("\n*** rootFolders:\n{}", rootFolders);
            GPProject projectCloned = newGProjectBinder()
                    .withNameProject(cloneProjectRequest.getNameProject())
                    .withFrom(gpProject).bind();
            GPAccountProject gpAccountProject = new GPAccountProject();
            gpAccountProject.setAccount(gpAccount);
            gpAccountProject.setProject(projectCloned);
            gpAccountProject.setDefaultProject(false);
            projectDao.persist(projectCloned);
            accountProjectDao.persist(gpAccountProject);
            Map<Long, GPFolder> folderMap = IntStream.iterate(0, n -> n + 1).limit(rootFolders.size()).boxed()
                    .collect(Collectors.toMap(i -> rootFolders.get(i).getId(),
                            i -> new GPFolderFunction(projectCloned, folderDao, null).apply(rootFolders.get(i))));

            // Sub Folders
            Search searchCriteria = new Search(GPFolder.class);
            searchCriteria.addFilterEqual("project.id", cloneProjectRequest.getGpProjectID());
            searchCriteria.addFilterNotNull("parent.id");
            List<GPFolder> subFolders = folderDao.search(searchCriteria);
            Collections.sort(subFolders, Comparator.comparingInt(f -> f.getLevel()));

            IntStream.iterate(0, n -> n + 1).limit(subFolders.size()).boxed().forEach(i ->
            {
                GPFolder folderCloned = new GPFolderFunction(projectCloned, folderDao, folderMap.get(subFolders.get(i).getParent().getId())).apply(subFolders.get(i));
                folderMap.put(subFolders.get(i).getId(), folderCloned);
            });

            // Sub Layers
            searchCriteria = new Search(GPLayer.class);
            searchCriteria.addFilterEqual("project.id", cloneProjectRequest.getGpProjectID());
            List<GPLayer> subLayers = layerDao.search(searchCriteria);

            IntStream.iterate(0, n -> n + 1).limit(subLayers.size()).boxed().forEach(i -> {
                new GPLayerFunction(projectCloned, layerDao, folderMap.get(subLayers.get(i).getFolder().getId())).apply(subLayers.get(i));
            });

            return projectCloned.getId();
        } catch (IllegalParameterFault e) {
            throw new IllegalParameterFault(e.getMessage());
        }
    }

    /**
     * @param request
     * @return {@link ProjectDTOContainer}
     * @throws Exception
     */
    @Override
    public ProjectDTOContainer findInternalPublicProjects(PaginatedSearchRequest request) throws Exception {
        if(request == null) {
            throw new IllegalParameterFault("The Parameter PaginatedSearchRequest must not be null.");
        }
        logger.trace("#######################Called {}#findInternalPublicProjects with request : {}\n", this, request);
        int total = this.projectDao.getTotalInternalPublic();
        return new ProjectDTOContainer(total > 0 ? convertToProjectDTOList(this.projectDao.findInternalPublic(request.getNum(), request.getPage())) :
                Lists.newArrayList(), total);
    }

    /**
     * @param request
     * @return {@link ProjectDTOContainer}
     * @throws Exception
     */
    @Override
    public ProjectDTOContainer findExternalPublicProjects(PaginatedSearchRequest request) throws Exception {
        if(request == null) {
            throw new IllegalParameterFault("The Parameter PaginatedSearchRequest must not be null.");
        }
        logger.trace("#######################Called {}#findExternalPublicProjects with request : {}\n", this, request);
        int total = this.projectDao.getTotalExternalPublic();
        return new ProjectDTOContainer(total > 0 ? convertToProjectDTOList(this.projectDao.findExternalPublic(request.getNum(), request.getPage())) :
                Lists.newArrayList(), total);
    }

    @Override
    public Long importProject(ImportProjectRequest impRequest) throws Exception {
        if (impRequest == null) {
            throw new IllegalParameterFault("The ImportProjectRequest must "
                    + "not be null.");
        }
        Long accountID = impRequest.getAccountID();
        ProjectDTO projectDTO = impRequest.getProjectDTO();

        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        GPProject project = ProjectDTO.convertToGPProject(projectDTO);
        projectDao.persist(project);

        List<FolderDTO> rootFolders = projectDTO.getRootFolders();
        if (rootFolders == null) { // TODO assert
            throw new IllegalParameterFault("Project have not root folders");
        }

        int numberOfElements = 0;
        position = 0;
        for (int i = rootFolders.size() - 1; i >= 0; i--) {
            FolderDTO folderDTO = rootFolders.get(i);
            GPFolder folder = FolderDTO.convertToGPFolder(project, null,
                    folderDTO);

            List<AbstractElementDTO> childs = folderDTO.getElementList();
            int numberOfDescendants = this.persistElementList(project, folder,
                    childs);

            logger.trace("\n\n*** Folder {} - Desc = {} ***\n\n",
                    folder.getName(), numberOfDescendants);
            folder.setNumberOfDescendants(numberOfDescendants);

            folder.setPosition(++position);
            logger.trace("*** Folder {} - pos = {}", folder.getName(),
                    folder.getPosition());

            EntityCorrectness.checkFolder(folder); // TODO assert
            folderDao.persist(folder);

            numberOfElements += numberOfDescendants + 1;
        }

        logger.trace("\n\n *** numberOfElements = {} ***\n\n", numberOfElements);
        project.setNumberOfElements(numberOfElements);
        EntityCorrectness.checkProject(project); // TODO assert
        projectDao.merge(project);

        GPAccountProject accountProject = new GPAccountProject();
        accountProject.setAccountAndProject(account, project);
        accountProjectDao.persist(accountProject);

        return project.getId();
    }
    //</editor-fold>

    /**
     * **************************************************************************
     */
    private GPProject getProjectByID(Long projectID) throws
            ResourceNotFoundFault {
        GPProject project = projectDao.find(projectID);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectID);
        }
        return project;
    }

    private GPAccount getAccountByID(Long accountID) throws
            ResourceNotFoundFault {
        GPAccount account = accountDao.find(accountID);
        if (account == null) {
            throw new ResourceNotFoundFault("Account not found", accountID);
        }
        return account;
    }

    private GPAccountProject getAccountProjectByID(Long accountProjectID)
            throws ResourceNotFoundFault {
        GPAccountProject accountProject = accountProjectDao.find(
                accountProjectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProject not found",
                    accountProjectID);
        }
        return accountProject;
    }

    private String createParentChildKey(GPFolder parent, GPFolder child) {
        return parent.getId() + ":" + child.getId();
    }

    private Map<Long, FolderDTO> fillProjectFolders(List<FolderDTO> folders,
            Map<String, GPFolder> mapRemaining, Map<Long, FolderDTO> mapAll) {
        logger.debug("\n*** fillFolderList - Map size: {}", mapRemaining.size());
        if (!mapRemaining.isEmpty()) {
            List<FolderDTO> childsDTO;
            for (FolderDTO folder : folders) {
                logger.debug("\n*** fillFolderList - folder: {}", folder);
                List<GPFolder> childs = this.getChilds(folder.getId(),
                        mapRemaining);
                if (childs.size() > 0) {
                    childsDTO = FolderDTO.convertToFolderDTOList(childs);
                    folder.addFolders(childsDTO);
                    //
                    for (FolderDTO childDTO : childsDTO) {
                        mapAll.put(childDTO.getId(), childDTO);
                    }
                    this.fillProjectFolders(childsDTO, mapRemaining, mapAll);
                }
            }

        }
        return mapAll;
    }

    private List<GPFolder> getChilds(Long parentID, Map<String, GPFolder> map) {
        List<GPFolder> childs = new ArrayList<GPFolder>();
        List<String> childsKeyHit = new ArrayList<String>();

        for (Map.Entry<String, GPFolder> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(parentID.toString())) {
                logger.debug("*** getChilds - HIT: {}", key);
                childsKeyHit.add(key);
                GPFolder childFolder = map.get(key);
                childs.add(childFolder);
            }
        }

        // Important: must be execute outer first for otherwise will throw ConcurrentModificationException
        for (String key : childsKeyHit) {
            map.remove(key);
        }

        return childs;
    }

    private int persistElementList(GPProject project, GPFolder parent, List<AbstractElementDTO> elementList) throws Exception {
        int numberOfDescendants = 0;

        for (int i = elementList.size() - 1; i >= 0; i--) {
            IElementDTO element = elementList.get(i);

            if (element instanceof FolderDTO) { // Folder
                FolderDTO folderDTO = (FolderDTO) element;
                GPFolder folder = FolderDTO.convertToGPFolder(project, parent,
                        folderDTO);

                List<AbstractElementDTO> childs = folderDTO.getElementList();

                int descendantsIth = this.persistElementList(project, folder,
                        childs);

                logger.trace("\n\n*** FOLDER\t{} ***\n\n", folder.getName());
                logger.trace("\n\n*** Desc = {} + DescIth = {} ***\n",
                        numberOfDescendants, descendantsIth);
                folder.setNumberOfDescendants(descendantsIth);

                folder.setPosition(++position);
                logger.trace("*** DescIth {} - pos = {}", folder.getName(),
                        folder.getPosition());

                EntityCorrectness.checkFolder(folder); // TODO assert
                folderDao.persist(folder);

                numberOfDescendants += descendantsIth + 1;

            } else { // Layer
                GPLayer layer;
                if (element instanceof RasterLayerDTO) {
                    layer = RasterLayerDTO.convertToGPRasterLayer(project,
                            parent,
                            (RasterLayerDTO) element);
                } else {
                    layer = VectorLayerDTO.convertToGPVectorLayer(project,
                            parent,
                            (VectorLayerDTO) element);
                }

                layer.setPosition(++position);
                logger.trace("*** Layer {} - pos = {}", layer.getTitle(),
                        layer.getPosition());

                EntityCorrectness.checkLayer(layer); // TODO assert
                layerDao.persist(layer);

                numberOfDescendants++;
            }
        }
        logger.trace("\n@@@ ELEMENT {}  ---  DESCENDANTS = {} @@@\n\n\n",
                parent.getName(), numberOfDescendants);
        return numberOfDescendants;
    }

    private void fillProjectExpandedFolders(Long projectID,
            List<FolderDTO> folders) {
        logger.debug("\n*** fillExpandedFolder - size: {}", folders.size());

        List<FolderDTO> childsDTO;
        for (FolderDTO folder : folders) {
            logger.debug("\n*** fillExpandedFolder - folder: {}", folder);
            if (folder.isExpanded()) {
                // Sub Layers
                Search searchCriteria = new Search(GPLayer.class);
                searchCriteria.addFilterEqual("project.id", projectID);
                searchCriteria.addFilterEqual("folder.id", folder.getId());
                List<GPLayer> subLayers = layerDao.search(searchCriteria);

                for (GPLayer layer : subLayers) {
                    ShortLayerDTO layerDTO;
                    if (layer instanceof GPRasterLayer) {
                        layerDTO = new RasterLayerDTO((GPRasterLayer) layer);
                    } else {
                        layerDTO = new VectorLayerDTO((GPVectorLayer) layer);
                    }
                    folder.addLayer(layerDTO);
                }

                // Sub folders
                searchCriteria = new Search(GPFolder.class);
                searchCriteria.addFilterEqual("project.id", projectID);
                searchCriteria.addFilterEqual("parent.id", folder.getId());
                List<GPFolder> childs = folderDao.search(searchCriteria);
                if (childs.size() > 0) {
                    childsDTO = FolderDTO.convertToFolderDTOList(childs);
                    folder.addFolders(childsDTO);

                    this.fillProjectExpandedFolders(projectID, childsDTO);
                }
            }
        }
    }

    private void unshareProject(GPProject project) {
        if (project.isShared()) {
            logger.debug("\n*** Delete all relations of sharing");

            List<GPAccountProject> accountProjectList = accountProjectDao.findNotOwnersByProjectID(
                    project.getId());
            for (GPAccountProject accountProject : accountProjectList) {
                accountProjectDao.remove(accountProject);
            }

            project.setShared(false);
            projectDao.merge(project);
        }
    }

    private GPAccount retrieveProjectOwner(Long projectID) {
        GPAccountProject accountOwner = accountProjectDao.findOwnerByProjectID(
                projectID);
        EntityCorrectness.checkAccountProjectLog(accountOwner); // TODO assert

        GPAccount owner = accountOwner.getAccount();
        EntityCorrectness.checkAccountLog(owner); // TODO assert

        return owner;
    }

    private ProjectDTO retrieveProjectDTO(Long projectID, Long accountID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert
        logger.trace("\n*** The project to retrieve is {}", project.getName());

        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert
        logger.trace("\n*** The account that retrieve the project is {}",
                account.getNaturalID());

        GPAccount owner = this.retrieveProjectOwner(projectID);
        logger.trace("\n*** The account owner of the project is {}",
                owner.getNaturalID());

        ProjectDTO projectDTO;
        if (accountID.equals(owner.getId())) {
            projectDTO = new ProjectDTO(project);
        } else {
            projectDTO = new ProjectDTO(project, owner);
        }
        return projectDTO;
    }
}
