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
package org.geosdi.geoplatform.core.delegate.api.viewport;

import org.geosdi.geoplatform.core.model.GPViewport;
import org.geosdi.geoplatform.exception.IllegalParameterFault;
import org.geosdi.geoplatform.exception.ResourceNotFoundFault;
import org.geosdi.geoplatform.request.viewport.InsertViewportRequest;
import org.geosdi.geoplatform.request.viewport.ManageViewportRequest;
import org.geosdi.geoplatform.response.viewport.WSGetViewportResponse;
import org.geosdi.geoplatform.services.core.api.resources.GPViewportResource;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface ViewportDelegate extends GPViewportResource {

    //<editor-fold defaultstate="collapsed" desc="Viewport">
    // =========================================================================
    // === Viewport
    // =========================================================================
    @Override
    GPViewport getDefaultViewport(Long accountProjectID) throws
            ResourceNotFoundFault;

    @Override
    WSGetViewportResponse getAccountProjectViewports(Long accountProjectID)
            throws ResourceNotFoundFault;

    @Override
    GPViewport getViewportById(Long idViewport) throws IllegalParameterFault,
            ResourceNotFoundFault;

    @Override
    Long updateViewport(GPViewport viewport) throws ResourceNotFoundFault,
            IllegalParameterFault;

    @Override
    Boolean deleteViewport(Long viewportID) throws ResourceNotFoundFault;

    @Override
    Long insertViewport(InsertViewportRequest insertViewportReq)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    void saveOrUpdateViewportList(ManageViewportRequest request)
            throws ResourceNotFoundFault, IllegalParameterFault;

    @Override
    void replaceViewportList(ManageViewportRequest request)
            throws ResourceNotFoundFault, IllegalParameterFault;
    //</editor-fold>

}
