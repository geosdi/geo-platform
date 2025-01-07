/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server.command.layer.refresh;

import jakarta.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.gui.client.command.layer.refresh.LayerRefreshCommandRequest;
import org.geosdi.geoplatform.gui.client.command.layer.refresh.LayerRefreshCommandResponse;
import org.geosdi.geoplatform.gui.server.command.GPLayerCommand;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Lazy
@Component(value = "command.layer.refresh.LayerRefreshCommand")
class LayerRefreshCommand extends GPLayerCommand<LayerRefreshCommandRequest, LayerRefreshCommandResponse> {


    /**
     * @param request
     * @param httpServletRequest
     * @return
     */
    @Override
    public LayerRefreshCommandResponse execute(LayerRefreshCommandRequest request,
            HttpServletRequest httpServletRequest) {
        logger.debug("#####################Executing {} Command with Parameter: {} - {} - {}\n",
                this.getClass().getSimpleName(), request.getLayerUUID(), request.getSecondToRefresh(),
                request.getEmiteResource());
        this.layerService.setLayerRefreshTime(request.getEmiteResource(), request.getLayerUUID(),
                request.getSecondToRefresh(), httpServletRequest);
        return new LayerRefreshCommandResponse(Boolean.TRUE);
    }
}
