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

import java.util.List;
import org.geosdi.geoplatform.core.dao.GPAccountProjectDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.geosdi.geoplatform.core.dao.GPViewportDAO;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPViewport;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.services.development.EntityCorrectness;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
class ViewportServiceImpl {

    final private static Logger logger = LoggerFactory.getLogger(ViewportServiceImpl.class);
    // DAO
    private GPViewportDAO viewportDao;
    private GPAccountProjectDAO accountProjectDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param viewportDao
     * the viewportDao to set
     */
    public void setViewportDao(GPViewportDAO viewportDao) {
        this.viewportDao = viewportDao;
    }

    /**
     *
     * @param accountProjectDao
     */
    public void setAccountProjectDao(GPAccountProjectDAO accountProjectDao) {
        this.accountProjectDao = accountProjectDao;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Folder">
    // ==========================================================================
    // === Folder
    // ==========================================================================
    public Long insertViewport(Long accountProjectId, GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault {

        GPAccountProject accountProject = this.accountProjectDao.find(accountProjectId);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProject not found", accountProjectId);
        }
        viewport.setAccountProject(accountProject);
        EntityCorrectness.checkViewport(viewport);
        viewportDao.persist(viewport);
        return viewport.getId();
    }

    public Long updateViewport(GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPViewport orig = viewportDao.find(viewport.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Viewport not found", viewport.getId());
        }
        EntityCorrectness.checkViewport(orig); // TODO assert

        // Update all properties (except the accountProject)
        orig.setName(viewport.getName());
        orig.setBbox(viewport.getBbox());
        orig.setDescription(viewport.getDescription());
        orig.setIsDefault(viewport.isIsDefault());
        orig.setZoomLevel(viewport.getZoomLevel());

        viewportDao.merge(orig);
        return orig.getId();
    }

    public boolean deleteViewport(Long viewportID) throws ResourceNotFoundFault {
        GPViewport viewport = viewportDao.find(viewportID);
        if (viewport == null) {
            throw new ResourceNotFoundFault("Viewport not found", viewportID);
        }
        EntityCorrectness.checkViewportLog(viewport); // TODO assert

        return viewportDao.remove(viewport);
    }

//    public List<FolderDTO> searchFolders(PaginatedSearchRequest searchRequest) {
//        Search searchCriteria = new Search(GPFolder.class);
//        searchCriteria.setMaxResults(searchRequest.getNum());
//        searchCriteria.setPage(searchRequest.getPage());
//        searchCriteria.addSortAsc("name");
//
//        String like = searchRequest.getNameLike();
//        if (like != null) {
//            searchCriteria.addFilterILike("name", like);
//        }
//
//        List<GPFolder> foundFolder = folderDao.search(searchCriteria);
//        return FolderDTO.convertToFolderDTOList(foundFolder);
//    }
//    public long getFoldersCount(SearchRequest searchRequest) {
//        Search searchCriteria = new Search(GPFolder.class);
//        if (searchRequest != null && searchRequest.getNameLike() != null) {
//            searchCriteria.addFilterILike("name", searchRequest.getNameLike());
//        }
//
//        return folderDao.count(searchCriteria);
//    }
    public List<GPViewport> getViewports() {
        return viewportDao.findAll();
    }

    public GPViewport getDefaultViewport(Long accountProjectID) throws ResourceNotFoundFault {
        List<GPViewport> viewportList = viewportDao.findByAccountProjectID(accountProjectID);
        if (viewportList == null) {
            throw new ResourceNotFoundFault("Viewport not found using the accountProject id", accountProjectID);
        }
        GPViewport viewport = null;
        for (GPViewport viewportElement : viewportList) {
            if (viewportElement.isIsDefault()) {
                viewport = viewportElement;
                break;
            }
        }
        return viewport;
    }
    //</editor-fold>
}
