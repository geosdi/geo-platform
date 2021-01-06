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
package org.geosdi.geoplatform.core.delegate.impl.viewport;

import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.core.dao.GPAccountProjectDAO;
import org.geosdi.geoplatform.core.dao.GPViewportDAO;
import org.geosdi.geoplatform.core.delegate.api.viewport.ViewportDelegate;
import org.geosdi.geoplatform.core.model.GPAccountProject;
import org.geosdi.geoplatform.core.model.GPViewport;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.viewport.InsertViewportRequest;
import org.geosdi.geoplatform.request.viewport.ManageViewportRequest;
import org.geosdi.geoplatform.response.viewport.WSGetViewportResponse;
import org.geosdi.geoplatform.services.development.EntityCorrectness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Viewport service delegate.
 *
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Component(value = "gpViewportDelegate")
public class GPViewportDelegate implements ViewportDelegate {

    @Autowired
    private GPViewportDAO viewportDao;
    @Autowired
    private GPAccountProjectDAO accountProjectDao;

    //<editor-fold defaultstate="collapsed" desc="Viewport">
    // =========================================================================
    // === Viewport
    // =========================================================================
    @Override
    public GPViewport getDefaultViewport(Long accountProjectID) throws
            ResourceNotFoundFault {
        List<GPViewport> viewportList = viewportDao.findByAccountProjectID(
                accountProjectID);
        if (viewportList == null) {
            throw new ResourceNotFoundFault(
                    "Viewport not found using the accountProject id",
                    accountProjectID);
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

    @Override
    public WSGetViewportResponse getAccountProjectViewports(
            Long accountProjectID) throws ResourceNotFoundFault {
        List<GPViewport> viewportList = viewportDao.findByAccountProjectID(
                accountProjectID);
        if (viewportList == null) {
            throw new ResourceNotFoundFault(
                    "Viewport not found using the accountProject id",
                    accountProjectID);
        }
        return new WSGetViewportResponse(viewportList);
    }

    @Override
    public GPViewport getViewportById(Long idViewport) throws
            IllegalParameterFault, ResourceNotFoundFault {
        if (idViewport == null) {
            throw new IllegalParameterFault(
                    "The GPViewport ID must not be null.");
        }

        GPViewport viewport = this.viewportDao.find(idViewport);

        if (viewport == null) {
            throw new ResourceNotFoundFault("The GPViewport with ID "
                    + idViewport + " is not present in DB.");
        }

        return viewport;
    }

    @Override
    public Long updateViewport(GPViewport viewport) throws ResourceNotFoundFault,
            IllegalParameterFault {
        GPViewport orig = viewportDao.find(viewport.getId());
        if (orig == null) {
            throw new ResourceNotFoundFault("Viewport not found",
                    viewport.getId());
        }
        EntityCorrectness.checkViewport(orig); // TODO assert
        return updateAndMergeFields(orig, viewport);
    }

    @Override
    public Boolean deleteViewport(Long viewportID) throws ResourceNotFoundFault {
        GPViewport viewport = viewportDao.find(viewportID);
        if (viewport == null) {
            throw new ResourceNotFoundFault("Viewport not found", viewportID);
        }
        EntityCorrectness.checkViewportLog(viewport); // TODO assert

        return viewportDao.remove(viewport);
    }

    @Override
    public Long insertViewport(InsertViewportRequest insertViewportReq) throws
            ResourceNotFoundFault, IllegalParameterFault {
        if (insertViewportReq == null) {
            throw new IllegalParameterFault(
                    "The InsertViewportRequest must not "
                    + "be null.");
        }
        return insertViewport(insertViewportReq.getAccountProjectID(),
                insertViewportReq.getViewport());
    }

    @Override
    public void saveOrUpdateViewportList(ManageViewportRequest request) throws
            ResourceNotFoundFault, IllegalParameterFault {
        if (request == null) {
            throw new IllegalParameterFault("The ManageViewportRequest "
                    + "must not be null.");
        }

        Long accountProjectID = request.getAccountProjectID();
        ArrayList<GPViewport> viewportList = request.getViewportList();

        GPAccountProject accountProject = this.accountProjectDao.find(
                accountProjectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProject not found",
                    accountProjectID);
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

    @Override
    public void replaceViewportList(ManageViewportRequest request) throws
            ResourceNotFoundFault, IllegalParameterFault {
        if (request == null) {
            throw new IllegalParameterFault("The ManageViewportRequest must "
                    + "not be null.");
        }
        Long accountProjectID = request.getAccountProjectID();
        ArrayList<GPViewport> viewportList = request.getViewportList();

        GPAccountProject accountProject = this.accountProjectDao.find(
                accountProjectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProject not found",
                    accountProjectID);
        }
        List<GPViewport> oldViewportList = this.viewportDao.findByAccountProjectID(
                accountProjectID);
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

    private Long insertViewport(Long accountProjectID, GPViewport viewport)
            throws ResourceNotFoundFault, IllegalParameterFault {
        GPAccountProject accountProject = this.accountProjectDao.find(
                accountProjectID);
        if (accountProject == null) {
            throw new ResourceNotFoundFault("AccountProject not found",
                    accountProjectID);
        }
        viewport.setAccountProject(accountProject);
        EntityCorrectness.checkViewport(viewport);
        viewportDao.persist(viewport);
        return viewport.getId();
    }
}
