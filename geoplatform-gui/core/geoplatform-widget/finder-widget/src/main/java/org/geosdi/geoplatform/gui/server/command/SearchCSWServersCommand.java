/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.server.command;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import jakarta.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.gui.client.command.SearchCSWServersRequest;
import org.geosdi.geoplatform.gui.client.command.SearchCSWServersResponse;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.request.PaginatedSearchRequest;
import org.geosdi.geoplatform.request.SearchRequest;
import org.geosdi.geoplatform.responce.ServerCSWDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toCollection;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Lazy
@Component(value = "command.SearchCSWServersCommand")
class SearchCSWServersCommand extends BaseCSWCommand<SearchCSWServersRequest, SearchCSWServersResponse> {

    /**
     * @param request
     * @param httpServletRequest
     * @return
     */
    @Override
    public SearchCSWServersResponse execute(SearchCSWServersRequest request, HttpServletRequest httpServletRequest) {
        logger.debug("#####################Executing {} Command", this.getClass().getSimpleName());
        logger.debug("Search Text: {}", request.getSearchText());
        logger.debug("Organization: {}", request.getOrganization());
        SearchRequest srq = new SearchRequest(request.getSearchText());
        try {
            int serversCount = geoPlatformCSWClient.getCSWServersCount(srq, request.getOrganization());
            ArrayList<GPCSWServerBeanModel> searchServers;
            if (serversCount == 0) {
                logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@No catalog found ***");
                searchServers = new ArrayList<GPCSWServerBeanModel>(0);
            } else {
                int start = request.getConfig().getOffset();
                int page = start == 0 ? start : start / request.getConfig().getLimit();
                PaginatedSearchRequest psr = new PaginatedSearchRequest(request.getSearchText(),
                        request.getConfig().getLimit(), page);
                List<ServerCSWDTO> serverList = geoPlatformCSWClient.searchCSWServers(psr, request.getOrganization());
                searchServers = serverList.stream()
                        .filter(Objects::nonNull)
                        .map(SearchCSWServersCommand::convertServerDTO)
                        .collect(toCollection(ArrayList::new));
            }
            return new SearchCSWServersResponse(new BasePagingLoadResult<GPCSWServerBeanModel>(searchServers, request.getConfig().getOffset(), serversCount));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeoPlatformException(ex);
        }
    }
}