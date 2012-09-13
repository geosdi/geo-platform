/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.services;

import com.googlecode.genericdao.search.Search;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.core.dao.GPAccountProjectDAO;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.model.GPAccount;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPApplication;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPOrganization;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.core.model.GPVectorLayer;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.RequestByAccountProjectIDs;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.AccountProjectPropertiesDTO;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.IElementDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.geosdi.geoplatform.responce.RasterLayerDTO;
import org.geosdi.geoplatform.responce.ShortAccountDTO;
import org.geosdi.geoplatform.responce.ShortLayerDTO;
import org.geosdi.geoplatform.responce.VectorLayerDTO;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.acls.domain.BasePermission;

/**
 * @author Michele Santomauro
 * @email michele.santomauro@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
class ProjectServiceImpl {

    final private static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    // DAO
    private GPProjectDAO projectDao;
    private GPAccountDAO accountDao;
    private GPAccountProjectDAO accountProjectDao;
    private GPFolderDAO folderDao;
    private GPLayerDAO layerDao;
    //
//    private int numberOfElements;
    private int position;
    private AccountServiceImpl accountServiceDelegate;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param projectDao the projectDao to set
     */
    public void setProjectDao(GPProjectDAO projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * @param accountDao the accountDao to set
     */
    public void setAccountDao(GPAccountDAO accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @param accountProjectDao the accountProjectDao to set
     */
    public void setAccountProjectDao(GPAccountProjectDAO accountProjectDao) {
        this.accountProjectDao = accountProjectDao;
    }

    /**
     * @param folderDao the folderDao to set
     */
    public void setFolderDao(GPFolderDAO folderDao) {
        this.folderDao = folderDao;
    }

    /**
     * @param layerDao the layerDao to set
     */
    public void setLayerDao(GPLayerDAO layerDao) {
        this.layerDao = layerDao;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Project">
    // ==========================================================================
    // === Project
    // ==========================================================================
    public Long saveProject(String stringID, GPProject project, boolean defaultProject)
            throws ResourceNotFoundFault, IllegalParameterFault {
        EntityCorrectness.checkProject(project); // TODO assert

        GPAccount account = accountDao.findByStringID(stringID);
        if (account == null) {
            throw new ResourceNotFoundFault("Account with stringID \"" + stringID + "\" not found");
        }
        EntityCorrectness.checkAccountLog(account); // TODO assert

        GPAccountProject accountProject = new GPAccountProject();
        accountProject.setAccount(account);
        accountProject.setProject(project);

        projectDao.persist(project);
        accountProjectDao.persist(accountProject);

        if (defaultProject) {
            account.setDefaultProjectID(project.getId());
            accountDao.merge(account);
        }

        return project.getId(); // Remark: return only the entity ID of Project
    }

    @Deprecated
    public Long insertProject(GPProject project) throws IllegalParameterFault {
        EntityCorrectness.checkProject(project); // TODO assert

        projectDao.persist(project);
        return project.getId();
    }

    public Long updateProject(GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault {
        EntityCorrectness.checkProject(project); // TODO assert

        GPProject origProject = this.getProjectByID(project.getId());
        EntityCorrectness.checkProjectLog(origProject); // TODO assert

        // Update all properties (except the creationDate)
        origProject.setName(project.getName());
        origProject.setNumberOfElements(project.getNumberOfElements());
        origProject.setShared(project.isShared());

        projectDao.merge(origProject);

        return origProject.getId();
    }

    public boolean deleteProject(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        accountDao.resetDefaultProject(projectID);

        return projectDao.removeById(projectID);
    }

    public GPProject getProjectDetail(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        return project;
    }

    public int getNumberOfElementsProject(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectDetail(projectID);

        return project.getNumberOfElements();
    }

    public List<FolderDTO> getRootFoldersByProjectID(Long projectID)
            throws ResourceNotFoundFault {
        List<GPFolder> foundAccountFolders = folderDao.searchRootFolders(projectID);
        return FolderDTO.convertToFolderDTOList(foundAccountFolders);
    }

    public ProjectDTO exportProject(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert
        ProjectDTO projectDTO = new ProjectDTO(project);

        // Root Folders
        List<GPFolder> rootFolders = folderDao.searchRootFolders(projectID);
        logger.debug("\n*** rootFolders:\n{}", rootFolders);

        List<FolderDTO> rootFoldersDTO = FolderDTO.convertToFolderDTOList(rootFolders);
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

        Map<String, GPFolder> subFoldersMap = new HashMap<String, GPFolder>(subFolders.size());
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
                throw new ResourceNotFoundFault("Parent folder not found", layer.getFolder().getId());
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

    public Long importProject(ProjectDTO projectDTO, Long accountID)
            throws IllegalParameterFault, ResourceNotFoundFault {
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
            GPFolder folder = FolderDTO.convertToGPFolder(project, null, folderDTO);

            List<IElementDTO> childs = folderDTO.getElementList();
            int numberOfDescendants = this.persistElementList(project, folder, childs);

            logger.trace("\n\n*** Folder {} - Desc = {} ***\n\n", folder.getName(), numberOfDescendants);
            folder.setNumberOfDescendants(numberOfDescendants);

            folder.setPosition(++position);
            logger.trace("*** Folder {} - pos = {}", folder.getName(), folder.getPosition());

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

    public void setProjectShared(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert
        project.setShared(true);

        projectDao.merge(project);
    }

    public boolean setProjectOwner(RequestByAccountProjectIDs request, boolean force)
            throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(request.getProjectID());
        EntityCorrectness.checkProjectLog(project); // TODO assert

        GPAccount owner = this.getAccountByID(request.getAccountID());
        EntityCorrectness.checkAccountLog(owner); // TODO assert

        // Reset the owner
        GPAccountProject accountOwner = accountProjectDao.findOwnerByProjectID(project.getId());
        EntityCorrectness.checkAccountProjectLog(accountOwner); // TODO assert
        accountOwner.setPermissionMask(BasePermission.READ.getMask());
        accountProjectDao.merge(accountOwner);

        // Set the new owner
        List<GPAccountProject> accounts = accountProjectDao.findNotOwnersByProjectID(project.getId());
        boolean exist = false;
        for (GPAccountProject accountProject : accounts) {
            EntityCorrectness.checkAccountProjectLog(accountProject); // TODO assert
            if (owner.equals(accountProject.getAccount())) {
                accountProject.setPermissionMask(BasePermission.ADMINISTRATION.getMask());
                exist = true;
                break;
            }
        }
        accountProjectDao.merge(accounts.toArray(new GPAccountProject[accounts.size()]));

        if (!exist) {
            GPAccountProject ownerProject = new GPAccountProject();
            ownerProject.setAccount(owner);
            ownerProject.setProject(project);
            ownerProject.setPermissionMask(BasePermission.ADMINISTRATION.getMask());
            accountProjectDao.persist(ownerProject);
        }

        return true;
    }

    /**
     * @see GeoPlatformService#getProjectOwner(java.lang.Long)
     */
    public GPAccount getProjectOwner(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        GPAccountProject accountOwner = accountProjectDao.findOwnerByProjectID(projectID);
        EntityCorrectness.checkAccountProjectLog(accountOwner); // TODO assert

        GPAccount owner = accountOwner.getAccount();
        EntityCorrectness.checkAccountLog(owner); // TODO assert

        return owner; // TODO
    }

    /**
     * @see GeoPlatformService#getDefaultProject(java.lang.Long)
     */
    public GPProject getDefaultProject(Long accountID) throws ResourceNotFoundFault {
        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        Long defaultProjectID = account.getDefaultProjectID();
        if (defaultProjectID == null) {
            return null;
        }

        GPProject project = this.getProjectByID(defaultProjectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        return project;
    }

    /**
     * @see GeoPlatformService#getDefaultProjectDTO(java.lang.Long)
     */
    public ProjectDTO getDefaultProjectDTO(Long accountID) throws ResourceNotFoundFault {
        GPProject project = this.getDefaultProject(accountID);
        ProjectDTO projectDTO = new ProjectDTO(project);

        if (projectDTO.isShared()) {
            GPAccount owner = accountProjectDao.findOwnerByProjectID(projectDTO.getId())
                    .getAccount();

            ShortAccountDTO ownerDTO = ShortAccountDTO.convertToShortAccountDTO(owner);
            projectDTO.setOwner(ownerDTO);
        }

        return projectDTO;
    }

    public void updateDefaultProject(Long accountID, Long projectID) throws ResourceNotFoundFault {
        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        account.setDefaultProjectID(projectID);

        accountDao.merge(account);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AccountProject">
    // ==========================================================================
    // === AccountProject
    // ==========================================================================
    public Long insertAccountProject(GPAccountProject accountProject) throws IllegalParameterFault {
        EntityCorrectness.checkAccountProject(accountProject);

        accountProjectDao.persist(accountProject);
        return accountProject.getId();
    }

    public Long updateAccountProject(GPAccountProject accountProject)
            throws ResourceNotFoundFault, IllegalParameterFault {
        EntityCorrectness.checkAccountProject(accountProject); // TODO assert

        GPAccountProject orig = this.getAccountProjectByID(accountProject.getId());
        EntityCorrectness.checkAccountProject(orig); // TODO assert

        // Update all properties (except the account and project reference)
        orig.setPermissionMask(accountProject.getPermissionMask());
        orig.setBaseLayer(accountProject.getBaseLayer());

        GPAccount account = accountProject.getAccount();
        if (account instanceof GPUser) {
            this.accountServiceDelegate.updateUser((GPUser) account);
        } else if (account instanceof GPApplication) {
            this.accountServiceDelegate.updateApplication((GPApplication) account);
        }
        this.updateProject(accountProject.getProject());

        accountProjectDao.merge(orig);

        return orig.getId();
    }

    public boolean deleteAccountProject(Long accountProjectID)
            throws ResourceNotFoundFault {
        GPAccountProject accountProject = this.getAccountProjectByID(accountProjectID);
        EntityCorrectness.checkAccountProjectLog(accountProject); // TODO assert

        return projectDao.removeById(accountProjectID);
    }

    public GPAccountProject getAccountProject(Long accountProjectID)
            throws ResourceNotFoundFault {
        GPAccountProject accountProject = this.getAccountProjectByID(accountProjectID);
        EntityCorrectness.checkAccountProjectLog(accountProject); // TODO assert

        return accountProject;
    }

    public List<GPAccountProject> getAccountProjectsByAccountID(Long accountID) {
        List<GPAccountProject> accountProjectsList = accountProjectDao.findByAccountID(accountID);
        EntityCorrectness.checkAccountProjectListLog(accountProjectsList); // TODO assert

        return accountProjectsList;
    }

    public List<GPAccountProject> getAccountProjectsByProjectID(Long projectID) {
        List<GPAccountProject> accountProjectsList = accountProjectDao.findByProjectID(projectID);
        EntityCorrectness.checkAccountProjectListLog(accountProjectsList); // TODO assert

        return accountProjectsList;
    }

    public GPAccountProject getAccountProjectByAccountAndProjectIDs(Long accountID, Long projectID)
            throws ResourceNotFoundFault {
        GPAccountProject accountProject = accountProjectDao.find(accountID, projectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProjects not found for with id:\"" + accountID + "\" and project with id:\"" + projectID + "\"");
        }
        EntityCorrectness.checkAccountProjectLog(accountProject); // TODO assert

        return accountProject;
    }

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

    /**
     * @see GeoPlatformService#searchAccountProjects(java.lang.Long,
     * org.geosdi.geoplatform.request.PaginatedSearchRequest)
     */
    public List<ProjectDTO> searchAccountProjects(Long accountID, PaginatedSearchRequest request)
            throws ResourceNotFoundFault {
        GPAccount account = this.getAccountByID(accountID);
        EntityCorrectness.checkAccountLog(account); // TODO assert

        Search searchCriteria = new Search(GPAccountProject.class);
        searchCriteria.addFilterEqual("account.id", accountID);
        if (request != null) {
            searchCriteria.setMaxResults(request.getNum());
            searchCriteria.setPage(request.getPage());

            if (request.getNameLike() != null) {
                searchCriteria.addFilterILike("project.name", request.getNameLike());
            }
        }

        List<GPAccountProject> accountProjectList = accountProjectDao.search(searchCriteria);
        EntityCorrectness.checkAccountProjectListLog(accountProjectList); // TODO assert

        List<GPProject> projects = new ArrayList<GPProject>(accountProjectList.size());
        for (GPAccountProject accountProject : accountProjectList) {
            projects.add(accountProject.getProject());
        }
        EntityCorrectness.checkProjectListLog(projects); // TODO assert

        List<ProjectDTO> projectDTO = ProjectDTO.convertToProjectDTOList(projects);
        for (ProjectDTO project : projectDTO) {
            if (project.isShared()) {
                GPAccount owner = accountProjectDao.findOwnerByProjectID(accountID).getAccount();
                ShortAccountDTO ownerDTO = ShortAccountDTO.convertToShortAccountDTO(owner);
                project.setOwner(ownerDTO);
            }
        }

        return projectDTO;
    }

    /**
     *
     * @param accountProjectProperties
     *
     * @return True only if the project is a candidate to be the default, False
     * Otherwise
     *
     * @throws ResourceNotFoundFault
     * @throws IllegalParameterFault
     */
    public boolean saveAccountProjectProperties(AccountProjectPropertiesDTO accountProjectProperties)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPProject project = this.getProjectByID(accountProjectProperties.getProjectID());
        EntityCorrectness.checkProjectLog(project); // TODO assert

        project.setName(accountProjectProperties.getProjectName());
        project.setShared(accountProjectProperties.isShared());
        projectDao.merge(project);

        if (accountProjectProperties.isDefaultProject()) {
            GPAccount account = this.getAccountByID(accountProjectProperties.getAccountID());
            EntityCorrectness.checkAccountLog(account); // TODO assert

            account.setDefaultProjectID(project.getId());
            accountDao.merge(account);
            return true;
        }
        return false;
    }

    /**
     * @see GeoPlatformService#getAccountsBySharedProjectID(java.lang.Long)
     */
    List<ShortAccountDTO> getAccountsBySharedProjectID(Long projectID)
            throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        List<GPAccountProject> accoutProjectList = accountProjectDao.findByProjectID(projectID);

        List<GPAccount> accountList = new ArrayList<GPAccount>(accoutProjectList.size());
        for (GPAccountProject accountProject : accoutProjectList) {
            GPAccount account = accountProject.getAccount();
            accountList.add(account);
        }
        return ShortAccountDTO.convertToShortAccountDTOList(accountList);
    }

    /**
     * @see GeoPlatformService#getAccountsToShareByProjectID(java.lang.Long)
     */
    List<ShortAccountDTO> getAccountsToShareByProjectID(Long projectID)
            throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        // Retrieve info for obtain Accounts of shared Projects
        List<GPAccountProject> accountProjects = accountProjectDao.findByProjectID(projectID);
        EntityCorrectness.checkAccountProjectListLog(accountProjects); // TODO assert

        // Retrieve the Organization related to the shared Project
        // Note: a Project will have at least one Account, the owner of the Project
        GPOrganization organization = accountProjects.get(0).getAccount().getOrganization();

        // Retrieve all Accounts of the Organization
        List<GPAccount> accounts = accountDao.findByOrganization(organization.getName());
        EntityCorrectness.checkAccountListLog(accounts); // TODO assert

        // Retrieve the Accounts to share the Project
        for (GPAccountProject accountProject : accountProjects) {
            accounts.remove(accountProject.getAccount());
        }
        List<ShortAccountDTO> accountsDTO = ShortAccountDTO.convertToShortAccountDTOList(accounts);

        return accountsDTO;
    }

    /**
     * @todo Optimize SQL queries
     *
     * @see GeoPlatformService#updateAccountsProjectSharing(java.lang.Long,
     * java.util.List)
     */
    boolean updateAccountsProjectSharing(Long projectID, List<Long> accountIDsProject)
            throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert

        if (accountIDsProject != null && !accountIDsProject.isEmpty()) {
            // The Account owner relation's project will not be managed
            Long ownerID = accountProjectDao.findOwnerByProjectID(projectID).getAccount().getId();
            accountIDsProject.remove(ownerID);

            logger.debug("\n*** Update all relations of sharing");

            List<GPAccountProject> accountProjectList = accountProjectDao.findNotOwnersByProjectID(projectID);
            Map<Long, GPAccountProject> sharingMap = new HashMap<Long, GPAccountProject>(accountProjectList.size());
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
                    newAccountProject.setPermissionMask(BasePermission.READ.getMask());
                    logger.debug("\n*** Create a new relation of sharing for Account \"{}\"",
                                 newAccount.getStringID());
                    accountProjectDao.persist(newAccountProject);
                }
            }

            // Delete the remaining relations of sharing
            for (Map.Entry<Long, GPAccountProject> e : sharingMap.entrySet()) {
                logger.debug("\n*** Delete the relation of sharing for Account \"{}\"",
                             e.getValue().getAccount().getStringID());
                accountProjectDao.remove(e.getValue());
            }

            if (!project.isShared() && !accountIDsProject.isEmpty()) {
                project.setShared(true);
                projectDao.merge(project);
            }

        } else {
            if (project.isShared()) {
                logger.debug("\n*** Delete all relations of sharing");

                List<GPAccountProject> accountProjectList = accountProjectDao.findNotOwnersByProjectID(projectID);
                for (GPAccountProject accountProject : accountProjectList) {
                    accountProjectDao.remove(accountProject);
                }

                project.setShared(false);
                projectDao.merge(project);
            }
        }

        return true;
    }
    //</editor-fold>

    private GPProject getProjectByID(Long projectID) throws ResourceNotFoundFault {
        GPProject project = projectDao.find(projectID);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectID);
        }
        return project;
    }

    private GPAccount getAccountByID(Long accountID) throws ResourceNotFoundFault {
        GPAccount account = accountDao.find(accountID);
        if (account == null) {
            throw new ResourceNotFoundFault("Account not found", accountID);
        }
        return account;
    }

    private GPAccountProject getAccountProjectByID(Long accountProjectID)
            throws ResourceNotFoundFault {
        GPAccountProject accountProject = accountProjectDao.find(accountProjectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProject not found", accountProjectID);
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
                List<GPFolder> childs = this.getChilds(folder.getId(), mapRemaining);
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

    private int persistElementList(GPProject project, GPFolder parent,
            List<IElementDTO> elementList) throws IllegalParameterFault {
        int numberOfDescendants = 0;

        for (int i = elementList.size() - 1; i >= 0; i--) {
            IElementDTO element = elementList.get(i);

            if (element instanceof FolderDTO) { // Folder
                FolderDTO folderDTO = (FolderDTO) element;
                GPFolder folder = FolderDTO.convertToGPFolder(project, parent,
                        folderDTO);

                List<IElementDTO> childs = folderDTO.getElementList();

                int descendantsIth = this.persistElementList(project, folder, childs);

                logger.trace("\n\n*** FOLDER\t{} ***\n\n", folder.getName());
                logger.trace("\n\n*** Desc = {} + DescIth = {} ***\n", numberOfDescendants, descendantsIth);
                folder.setNumberOfDescendants(descendantsIth);

                folder.setPosition(++position);
                logger.trace("*** DescIth {} - pos = {}", folder.getName(), folder.getPosition());

                EntityCorrectness.checkFolder(folder); // TODO assert
                folderDao.persist(folder);

                numberOfDescendants += descendantsIth + 1;

            } else { // Layer
                GPLayer layer;
                if (element instanceof RasterLayerDTO) {
                    layer = RasterLayerDTO.convertToGPRasterLayer(project, parent,
                            (RasterLayerDTO) element);
                } else {
                    layer = VectorLayerDTO.convertToGPVectorLayer(project, parent,
                            (VectorLayerDTO) element);
                }

                layer.setPosition(++position);
                logger.trace("*** Layer {} - pos = {}", layer.getTitle(), layer.getPosition());

                EntityCorrectness.checkLayer(layer); // TODO assert
                layerDao.persist(layer);

                numberOfDescendants++;
            }
        }
        logger.trace("\n@@@ ELEMENT {}  ---  DESCENDANTS = {} @@@\n\n\n", parent.getName(), numberOfDescendants);
        return numberOfDescendants;
    }

    public ProjectDTO getExpandedElementsByProjectID(Long projectID) throws ResourceNotFoundFault {
        GPProject project = this.getProjectByID(projectID);
        EntityCorrectness.checkProjectLog(project); // TODO assert
        ProjectDTO projectDTO = new ProjectDTO(project);

        // Root Folders
        List<GPFolder> rootFolders = folderDao.searchRootFolders(projectID);
        logger.debug("\n*** rootFolders:\n{}", rootFolders);

        List<FolderDTO> rootFoldersDTO = FolderDTO.convertToFolderDTOList(rootFolders);
        projectDTO.setRootFolders(rootFoldersDTO);

        this.fillProjectExpandedFolders(projectID, rootFoldersDTO);

        return projectDTO;
    }

    private void fillProjectExpandedFolders(Long projectID, List<FolderDTO> folders) {
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

    protected void setAccountService(AccountServiceImpl accountServiceDelegate) {
        this.accountServiceDelegate = accountServiceDelegate;
    }
}
