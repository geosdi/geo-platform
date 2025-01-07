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
package org.geosdi.geoplatform.gui.server.command;

import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import jakarta.servlet.http.HttpServletRequest;
import org.geosdi.geoplatform.gui.client.command.SearchFullRecordsRequest;
import org.geosdi.geoplatform.gui.client.command.SearchFullRecordsResponse;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.responce.FullRecordDTO;
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
@Component(value = "command.SearchFullRecordsCommand")
class SearchFullRecordsCommand extends BaseCSWCommand<SearchFullRecordsRequest, SearchFullRecordsResponse> {

    /**
     * @param request
     * @param httpServletRequest
     * @return
     */
    @Override
    public SearchFullRecordsResponse execute(SearchFullRecordsRequest request, HttpServletRequest httpServletRequest) {
        logger.debug("#####################Executing {} Command", this.getClass().getSimpleName());
        logger.debug("PagingLoadConfig : {}", request.getConfig());
        logger.debug("CatalogFinder : {}", request.getCatalogFinder());
        try {
            int recordsCount = geoPlatformCSWClient.getRecordsCount(request.getCatalogFinder());
            if (recordsCount == 0) {
                logger.info("############################## No Full Record found.\n");
                return new SearchFullRecordsResponse(new BasePagingLoadResult<FullRecord>(new ArrayList<FullRecord>(0), request.getConfig().getOffset(), recordsCount));
            } else {
                List<FullRecordDTO> recordList = geoPlatformCSWClient.searchFullRecords(request.getConfig().getLimit(), request.getConfig().getOffset() + 1, request.getCatalogFinder());
                return new SearchFullRecordsResponse(new BasePagingLoadResult<FullRecord>(recordList.stream()
                        .filter(Objects::nonNull)
                        .map(SearchFullRecordsCommand::convertFullRecordDTO)
                        .collect(toCollection(ArrayList::new)), request.getConfig().getOffset(), recordsCount));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GeoPlatformException(ex.getMessage());
        }
    }
}