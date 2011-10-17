//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//</editor-fold>
package org.geosdi.geoplatform.services;

import com.googlecode.genericdao.search.Search;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.core.dao.GPFolderDAO;
import org.geosdi.geoplatform.core.dao.GPLayerDAO;
import org.geosdi.geoplatform.core.model.GPUserProjects;
import org.geosdi.geoplatform.request.RequestByUserProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.geosdi.geoplatform.core.dao.GPProjectDAO;
import org.geosdi.geoplatform.core.dao.GPUserDAO;
import org.geosdi.geoplatform.core.dao.GPUserProjectsDAO;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayer;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.responce.FolderDTO;
import org.geosdi.geoplatform.responce.ProjectDTO;
import org.springframework.security.acls.domain.BasePermission;

/**
 * @author Michele Santomauro
 * @email michele.santomauro@geosdi.org
 * 
 */
class ProjectServiceImpl {

    final private static Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    // DAO
    private GPProjectDAO projectDao;
    private GPUserDAO userDao;
    private GPUserProjectsDAO userProjectsDao;
    private GPFolderDAO folderDao;
    private GPLayerDAO layerDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param projectDao
     *            the projectDao to set
     */
    public void setProjectDao(GPProjectDAO projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * @param userDao
     *            the userDao to set
     */
    public void setUserDao(GPUserDAO userDao) {
        this.userDao = userDao;
    }

    /**
     * @param userProjectsDao
     *          the userProjectsDao to set
     */
    public void setUserProjectsDao(GPUserProjectsDAO userProjectsDao) {
        this.userProjectsDao = userProjectsDao;
    }

    /**
     * @param folderDao 
     *            the folderDao to set
     */
    public void setFolderDao(GPFolderDAO folderDao) {
        this.folderDao = folderDao;
    }

    /**
     * @param layerDao
     *            the layerDao to set
     */
    public void setLayerDao(GPLayerDAO layerDao) {
        this.layerDao = layerDao;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Project">
    // ==========================================================================
    // === Project
    // ==========================================================================
    public Long saveProject(String username, GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault {
        logger.trace("\n\t@@@ saveProject @@@");
        this.checkProject(project);

        GPUser user = userDao.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundFault("User with username \"" + username + "\" not found");
        }

        GPUserProjects userProject = new GPUserProjects();
        userProject.setUser(user);
        userProject.setProject(project);

        projectDao.persist(project);
        userProjectsDao.persist(userProject);

        return project.getId(); // Remark: return only the entity ID of Project
    }

    @Deprecated
    public Long insertProject(GPProject project) throws IllegalParameterFault {
        logger.trace("\n\t@@@ insertProject @@@");
        this.checkProject(project);

        projectDao.persist(project);
        return project.getId();
    }

    public Long updateProject(GPProject project)
            throws ResourceNotFoundFault, IllegalParameterFault {
        logger.trace("\n\t@@@ updateProject @@@");
        this.checkProject(project); // TODO assert

        GPProject origProject = projectDao.find(project.getId());
        if (origProject == null) {
            throw new ResourceNotFoundFault("Project not found", project.getId());
        }
        this.checkProject(origProject); // TODO assert

        // Update all properties (except the creationDate)
        origProject.setName(project.getName());
        origProject.setNumberOfElements(project.getNumberOfElements());
        origProject.setShared(project.isShared());

        projectDao.merge(origProject);

        return origProject.getId();
    }

    public boolean deleteProject(Long projectId) throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ deleteProject @@@");

        GPProject project = projectDao.find(projectId);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectId);
        }
        this.checkProjectLog(project); // TODO assert

        return projectDao.removeById(projectId);
    }

    public GPProject getProjectDetail(Long projectId) throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ getProjectDetail @@@");

        GPProject project = projectDao.find(projectId);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectId);
        }
        this.checkProjectLog(project); // TODO assert

        return project;
    }

    public int getNumberOfElementsProject(Long projectId) throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ getNumberOfElementsProject @@@");

        GPProject project = this.getProjectDetail(projectId);

        return project.getNumberOfElements();
    }

//    
//    /**
//     * 
//     * @param request
//     * @return only root folders owned by user
//     */
//    public List<FolderDTO> getFoldersByRequest(RequestById request) {
//        Search searchCriteria = new Search(GPFolder.class);
//
//        searchCriteria.setMaxResults(request.getNum());
//        searchCriteria.setPage(request.getPage());
//        searchCriteria.addSortAsc("position");
//        searchCriteria.addFilterEqual("user.id", request.getId());
//        searchCriteria.addFilterEqual("permissionMask", BasePermission.ADMINISTRATION.getMask());
//        searchCriteria.addFilterNull("parent.id");
//
//        List<GPFolder> foundUserFolders = folderDao.search(searchCriteria);
//        return convertToFolderList(foundUserFolders);
//    }
//
//    /**
//     * 
//     * @param userId
//     * @return only root folders owned by user
//     */
//    public List<FolderDTO> getFoldersByUserId(Long userId) {
//        Search searchCriteria = new Search(GPUserFolders.class);
//
//        searchCriteria.addSortAsc("position");
//        searchCriteria.addFilterEqual("user.id", userId);
//        searchCriteria.addFilterEqual("permissionMask", BasePermission.ADMINISTRATION.getMask());
//        searchCriteria.addFilterNull("parent.id");
//
//        List<GPUserFolders> foundUserFolders = userProjectsDao.search(searchCriteria);
//        return convertToUserFolderList(foundUserFolders);
//    }
//
//    /**
//     * 
//     * @param request
//     * @return count only root folders owned by user
//     */
//    public long getUserFoldersCount(Long userId) {
//        Search searchCriteria = new Search(GPUserFolders.class);
//
//        searchCriteria.addFilterEqual("user.id", userId);
//        searchCriteria.addFilterEqual("permissionMask", BasePermission.ADMINISTRATION.getMask());
//        searchCriteria.addFilterNull("parent.id");
//
//        return userProjectsDao.count(searchCriteria);
//    }
//
//    // TODO Check
//    /**
//     * 
//     * @param request
//     * @return folders owned by user and shared with his
//     */
//    public List<FolderDTO> getAllUserFolders(RequestById request) {
//        Search searchCriteria = new Search(GPUserFolders.class);
//
//        searchCriteria.setMaxResults(request.getNum());
//        searchCriteria.setPage(request.getPage());
////        searchCriteria.addSortAsc("folder.name");
//        searchCriteria.addFilterEqual("user.id", request.getId());
//        searchCriteria.addFilterNull("parent.id");
//
//        List<GPUserFolders> foundUserFolders = userProjectsDao.search(searchCriteria);
//        return convertToUserFolderList(foundUserFolders);
//    }
//
//    // TODO Check
    /**
     * 
     * @param projectId
     * @return root folders of a project
     */
    public List<FolderDTO> getRootFoldersByProjectId(Long projectId) {
        Search searchCriteria = new Search(GPFolder.class);

        searchCriteria.addFilterEqual("project.id", projectId);
        searchCriteria.addFilterNull("parent.id");
        searchCriteria.addSortDesc("position");
        List<GPFolder> foundUserFolders = folderDao.search(searchCriteria);
        return FolderDTO.convertToFolderDTOList(foundUserFolders);
    }

    public ProjectDTO getElements(Long projectId) throws ResourceNotFoundFault {
        GPProject project = projectDao.find(projectId);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectId);
        }
        ProjectDTO projectDTO = new ProjectDTO(project);

        // Root Folders
        Search searchCriteria = new Search(GPFolder.class);
        searchCriteria.addFilterEqual("project.id", projectId);
        searchCriteria.addFilterNull("parent.id");
        searchCriteria.addSortDesc("position");
        List<GPFolder> rootFolders = folderDao.search(searchCriteria);
        logger.debug("\n*** rootFolders:\n" + rootFolders);

        List<FolderDTO> rootFoldersDTO = FolderDTO.convertToFolderDTOList(rootFolders);
        projectDTO.setRootFolders(rootFoldersDTO);

        // Sub Folders
        searchCriteria = new Search(GPFolder.class);
        searchCriteria.addFilterEqual("project.id", projectId);
        searchCriteria.addFilterNotNull("parent.id");
//        searchCriteria.addSortDesc("position");
        List<GPFolder> subFolders = folderDao.search(searchCriteria);

        Map<String, GPFolder> subFoldersMap = new HashMap<String, GPFolder>(subFolders.size());
        for (GPFolder folder : subFolders) {
            String key = this.createParentChildKey(folder.getParent(), folder);
            logger.debug("\n*** key: " + key + "\n*** subFolder ***\n" + folder);
            subFoldersMap.put(key, folder);
        }

        this.fillFolderList(rootFoldersDTO, subFoldersMap);

        // TODO
        // Sub Layers
//        searchCriteria = new Search(GPFolder.class);
//        searchCriteria.addFilterEqual("project.id", projectId);
//        List<GPLayer> subLayers = layerDao.search(searchCriteria);
//
//        Map<String, GPLayer> subLayersMap = new HashMap<String, GPLayer>(subLayers.size());
//        for (GPLayer layer : subLayers) {
//            String key = this.createParentChildKey(layer.getFolder(), layer);
//            logger.debug("\n*** key: " + key + "\n*** layer ***\n" + layer);
//            subLayersMap.put(key, layer);
//        }

        return projectDTO;
    }

    private String createParentChildKey(GPFolder parent, GPFolder child) {
        return parent.getId() + ":" + child.getId();
    }

    private String createParentChildKey(GPFolder parent, GPLayer child) {
        if (child instanceof GPRasterLayer) {
            return parent.getId() + ":" + child.getId() + ":R";
        } else {
            return parent.getId() + ":" + child.getId() + ":V";
        }
    }

    private void fillFolderList(List<FolderDTO> folders, Map<String, GPFolder> map) {
        logger.debug("\n*** fillFolderList - Map size: " + map.size());
        if (!map.isEmpty()) {
            List<FolderDTO> childsDTO = null;
            for (FolderDTO folder : folders) {
                logger.debug("\n*** fillFolderList - folder: " + folder);
                List<GPFolder> childs = this.getChilds(folder.getId(), map);
                if (childs.size() > 0) {
                    childsDTO = FolderDTO.convertToFolderDTOList(childs);
                    folder.setElementList(childsDTO);
                }
            }

            if (childsDTO != null) {
                this.fillFolderList(childsDTO, map);
            }
        }
    }

    private List<GPFolder> getChilds(Long parentId, Map<String, GPFolder> map) {
        List<GPFolder> childs = new ArrayList<GPFolder>();
        List<String> childsKeyHit = new ArrayList<String>();

        for (Map.Entry<String, GPFolder> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(parentId.toString())) {
                logger.debug("*** getChilds - HIT: " + key);
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

//    // TODO Check
//    /**
//     * 
//     * @param userId
//     * @return count all folders and sub-folders owned by user and shared with his
//     */
//    public int getAllUserFoldersCount(Long userId) {
//        Search searchCriteria = new Search(GPUserFolders.class);
//
//        searchCriteria.addFilterEqual("user.id", userId);
////        searchCriteria.addFilterNull("parent.id");
//
//        return userProjectsDao.count(searchCriteria);
//    }
//    
    public void setProjectShared(Long projectId) throws ResourceNotFoundFault {
        GPProject project = projectDao.find(projectId);
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", projectId);
        }

        project.setShared(true);

        projectDao.merge(project);
    }

    public boolean setProjectOwner(RequestByUserProject request, boolean force)
            throws ResourceNotFoundFault {
        GPProject project = projectDao.find(request.getProjectId());
        if (project == null) {
            throw new ResourceNotFoundFault("Project not found", request.getProjectId());
        }

        GPUser user = userDao.find(request.getUserId());
        if (user == null) {
            throw new ResourceNotFoundFault("User not found", request.getUserId());
        }

        // TODO: implement the logic described in this method's javadoc

        project.setShared(false);

        projectDao.merge(project);

        return true;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="UserProjects">
    // ==========================================================================
    // === UserProjects
    // ==========================================================================
    public Long insertUserProject(GPUserProjects userProject) throws IllegalParameterFault {
        logger.trace("\n\t@@@ insertUserProject @@@");
        this.checkUserProject(userProject);

        userProjectsDao.persist(userProject);
        return userProject.getId();
    }

    public Long updateUserProject(GPUserProjects userProject)
            throws ResourceNotFoundFault, IllegalParameterFault {
        logger.trace("\n\t@@@ updateUserProject @@@");
        this.checkUserProject(userProject); // TODO assert

        GPUserProjects origUserProject = userProjectsDao.find(userProject.getId());
        if (origUserProject == null) {
            throw new ResourceNotFoundFault("UserProject not found", userProject.getId());
        }
        this.checkUserProject(origUserProject); // TODO assert

        // Update all properties (except the user and project reference)
        origUserProject.setChecked(userProject.isChecked());
        origUserProject.setPermissionMask(userProject.getPermissionMask());

        userProjectsDao.merge(origUserProject);

        return origUserProject.getId();
    }

    public boolean deleteUserProject(Long userProjectId)
            throws ResourceNotFoundFault {
        logger.trace("\n\t@@@ deleteUserProject @@@");

        GPUserProjects userProject = userProjectsDao.find(userProjectId);
        if (userProject == null) {
            throw new ResourceNotFoundFault("UserProject not found", userProjectId);
        }
        this.checkUserProjectLog(userProject); // TODO assert

        return projectDao.removeById(userProjectId);
    }

    public GPUserProjects getUserProject(Long userProjectId)
            throws ResourceNotFoundFault {
        GPUserProjects userProject = userProjectsDao.find(userProjectId);
        if (userProject == null) {
            throw new ResourceNotFoundFault("UserProject not found", userProjectId);
        }
        this.checkUserProjectLog(userProject); // TODO assert

        return userProject;
    }

    public List<GPUserProjects> getUserProjectsByUserId(Long userId) {
        List<GPUserProjects> userProjectsList = userProjectsDao.findByUserId(userId);

        this.checkUserProjectListLog(userProjectsList); // TODO assert

        return userProjectsList;
    }

    public List<GPUserProjects> getUserProjectsByProjectId(Long projectId) {
        List<GPUserProjects> userProjectsList = userProjectsDao.findByProjectId(projectId);

        this.checkUserProjectListLog(userProjectsList); // TODO assert

        return userProjectsList;
    }

    public GPUserProjects getUserProjectByUserAndProjectId(Long userId, Long projectId)
            throws ResourceNotFoundFault {
        GPUserProjects userProject = userProjectsDao.find(userId, projectId);
        if (userProject == null) {
            throw new ResourceNotFoundFault("UserProjects not found for with id:\"" + userId + "\" and project with id:\"" + projectId + "\"");
        }
        this.checkUserProjectLog(userProject); // TODO assert

        return userProject;
    }
    //</editor-fold>

    // TODO assert
    private void checkProject(GPProject project) throws IllegalParameterFault {
        if (project == null) {
            throw new IllegalParameterFault("Project must be NOT NULL");
        }
        if (project.getName() == null) {
            throw new IllegalParameterFault("Project \"name\" must be NOT NULL");
        }
        if (project.getNumberOfElements() < 0) {
            throw new IllegalParameterFault("Project \"numberOfElements\" must be greater or equal 0");
        }
    }

    // TODO assert
    private void checkUserProject(GPUserProjects userProject) throws IllegalParameterFault {
        if (userProject == null) {
            throw new IllegalParameterFault("UserProject must be NOT NULL");
        }
        if (userProject.getProject() == null) {
            throw new IllegalParameterFault("Project must be NOT NULL");
        }
        if (userProject.getUser() == null) {
            throw new IllegalParameterFault("User must be NOT NULL");
        }
        if ((userProject.getPermissionMask() < BasePermission.READ.getMask())
                || (userProject.getPermissionMask() > BasePermission.ADMINISTRATION.getMask())) {
            throw new IllegalParameterFault("PermissionMask NOT allowed");
        }
    }

    // TODO assert
    private void checkProjectLog(GPProject project) {
        try {
            this.checkProject(project);
        } catch (IllegalParameterFault ex) {
            logger.error("\n--- " + ex.getMessage() + " ---");
        }
    }

    // TODO assert
    private void checkUserProjectLog(GPUserProjects userProject) {
        try {
            this.checkUserProject(userProject);
        } catch (IllegalParameterFault ex) {
            logger.error("\n--- " + ex.getMessage() + " ---");
        }
    }

    // TODO assert
    private void checkUserProjectListLog(List<GPUserProjects> userProjects) {
        for (GPUserProjects up : userProjects) {
            this.checkUserProjectLog(up);
        }
    }
}