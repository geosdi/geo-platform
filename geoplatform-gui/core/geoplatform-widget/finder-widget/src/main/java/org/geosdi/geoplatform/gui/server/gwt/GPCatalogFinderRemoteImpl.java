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
package org.geosdi.geoplatform.gui.server.gwt;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.client.model.SummaryRecord;
import org.geosdi.geoplatform.gui.client.service.GPCatalogFinderRemote;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.server.IGPCatalogFinderService;
import org.geosdi.geoplatform.gui.server.spring.xsrf.GPAutoInjectingXsrfTokenServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
public class GPCatalogFinderRemoteImpl extends GPAutoInjectingXsrfTokenServiceServlet
        implements GPCatalogFinderRemote {

    private static final long serialVersionUID = 8756054653877871843L;
    //
    @Autowired
    private IGPCatalogFinderService gpCatalogFinderService;

    @Override
    public ArrayList<GPCSWServerBeanModel> getAllCSWServers(
            String organizationName) throws GeoPlatformException {
        return gpCatalogFinderService.getAllCSWServers(
                organizationName, super.getThreadLocalRequest());
    }

    @Override
    public PagingLoadResult<GPCSWServerBeanModel> searchCSWServers(
            PagingLoadConfig config,
            String searchText,
            String organization)
            throws GeoPlatformException {
        return gpCatalogFinderService.searchCSWServers(config, searchText,
                organization,
                super.getThreadLocalRequest());
    }

    @Override
    public GPCSWServerBeanModel saveServerCSW(String alias,
            String serverUrl,
            String organization)
            throws GeoPlatformException {
        return gpCatalogFinderService.saveServerCSW(alias, serverUrl,
                organization,
                super.getThreadLocalRequest());
    }

    @Override
    public boolean deleteServerCSW(Long serverID) throws GeoPlatformException {
        return gpCatalogFinderService.deleteServerCSW(serverID,
                super.getThreadLocalRequest());
    }

    @Override
    public PagingLoadResult<SummaryRecord> searchSummaryRecords(
            PagingLoadConfig config,
            CatalogFinderBean catalogFinder)
            throws Exception {
        return gpCatalogFinderService.searchSummaryRecords(config, catalogFinder,
                super.getThreadLocalRequest());
    }

    @Override
    public PagingLoadResult<FullRecord> searchFullRecords(
            PagingLoadConfig config,
            CatalogFinderBean catalogFinder)
            throws Exception {
        return gpCatalogFinderService.searchFullRecords(config, catalogFinder,
                super.getThreadLocalRequest());
    }

    @Override
    public String getRecordById(Long serverID,
            String identifier,
            String moduleName) throws Exception {
        return gpCatalogFinderService.getRecordById(serverID, identifier,
                moduleName,
                super.getThreadLocalRequest());
    }
}
