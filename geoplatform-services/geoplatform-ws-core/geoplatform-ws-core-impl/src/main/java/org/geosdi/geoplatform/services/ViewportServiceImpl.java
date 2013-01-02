/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.core.dao.GPAccountProjectDAO;
import org.geosdi.geoplatform.core.dao.GPViewportDAO;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPViewport;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Viewport service delegate.
 *
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
class ViewportServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(ViewportServiceImpl.class);
    // DAO
    private GPViewportDAO viewportDao;
    private GPAccountProjectDAO accountProjectDao;

    //<editor-fold defaultstate="collapsed" desc="Setter methods">
    /**
     * @param viewportDao the viewportDao to set
     */
    public void setViewportDao(GPViewportDAO viewportDao) {
        this.viewportDao = viewportDao;
    }

    /**
     * @param accountProjectDao the accountProjectDao to set
     */
    public void setAccountProjectDao(GPAccountProjectDAO accountProjectDao) {
        this.accountProjectDao = accountProjectDao;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Viewport">
    // =========================================================================
    // === Viewport
    // =========================================================================
    /**
     * @see GeoPlatformService#getDefaultViewport(java.lang.Long)
     */
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

    /**
     * @see GeoPlatformService#getAccountProjectViewports(java.lang.Long)
     */
    public ArrayList<GPViewport> getAccountProjectViewports(Long accountProjectID) throws ResourceNotFoundFault {
        List<GPViewport> viewportList = viewportDao.findByAccountProjectID(accountProjectID);
        if (viewportList == null) {
            throw new ResourceNotFoundFault("Viewport not found using the accountProject id", accountProjectID);
        }
        return Lists.newArrayList(viewportList);
    }

    /**
     * @see
     * GeoPlatformService#updateViewport(org.geosdi.geoplatform.core.model.GPViewport)
     */
    public Long updateViewport(GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPViewport orig = viewportDao.find(viewport.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Viewport not found", viewport.getId());
        }
        EntityCorrectness.checkViewport(orig); // TODO assert
        return updateAndMergeFields(orig, viewport);
    }

    /**
     * @see GeoPlatformService#deleteViewport(java.lang.Long)
     */
    public boolean deleteViewport(Long viewportID) throws ResourceNotFoundFault {
        GPViewport viewport = viewportDao.find(viewportID);
        if (viewport == null) {
            throw new ResourceNotFoundFault("Viewport not found", viewportID);
        }
        EntityCorrectness.checkViewportLog(viewport); // TODO assert

        return viewportDao.remove(viewport);
    }

    /**
     * @see GeoPlatformService#insertViewport(java.lang.Long,
     * org.geosdi.geoplatform.core.model.GPViewport)
     */
    public Long insertViewport(Long accountProjectID, GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPAccountProject accountProject = this.accountProjectDao.find(accountProjectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProject not found", accountProjectID);
        }
        viewport.setAccountProject(accountProject);
        EntityCorrectness.checkViewport(viewport);
        viewportDao.persist(viewport);
        return viewport.getId();
    }

    /**
     * @see GeoPlatformService#saveOrUpdateViewportList(java.lang.Long,
     * java.util.ArrayList)
     */
    public void saveOrUpdateViewportList(Long accountProjectID, ArrayList<GPViewport> viewportList)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPAccountProject accountProject = this.accountProjectDao.find(accountProjectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProject not found", accountProjectID);
        }
        for (GPViewport viewport : viewportList) {
            long idViewport = 0;
            if (viewport != null && viewport.getId() != null) {
                GPViewport orig = viewportDao.find(viewport.getId());
                if (orig != null) {
                    EntityCorrectness.checkViewport(orig); // TODO assert
                    viewport.setAccountProject(accountProject);
                    idViewport = this.updateAndMergeFields(orig, viewport);
                }
            } else if (idViewport == 0) {
                idViewport = this.insertViewport(accountProjectID, viewport);
            }
        }
    }

    /**
     * @see GeoPlatformService#replaceViewportList(java.lang.Long,
     * java.util.ArrayList)
     */
    public void replaceViewportList(Long accountProjectID, ArrayList<GPViewport> viewportList)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPAccountProject accountProject = this.accountProjectDao.find(accountProjectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProject not found", accountProjectID);
        }
        List<GPViewport> oldViewportList = this.viewportDao.findByAccountProjectID(accountProjectID);
        for (GPViewport viewport : oldViewportList) {
            this.viewportDao.remove(viewport);
        }
        if (viewportList != null) {
            for (GPViewport viewport : viewportList) {
                this.insertViewport(accountProjectID, viewport);
            }
        }
    }
    //</editor-fold>

    /**
     ***************************************************************************
     */
    private long updateAndMergeFields(GPViewport orig, GPViewport viewport) {
        // Update all properties (except the accountProject)
        orig.setName(viewport.getName());
        orig.setBbox(viewport.getBbox());
        orig.setDescription(viewport.getDescription());
        orig.setIsDefault(viewport.isIsDefault());
        orig.setZoomLevel(viewport.getZoomLevel());

        viewportDao.merge(orig);
        return orig.getId();
    }
}
