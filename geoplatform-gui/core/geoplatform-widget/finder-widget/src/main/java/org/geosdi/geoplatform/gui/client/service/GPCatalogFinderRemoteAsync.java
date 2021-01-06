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
package org.geosdi.geoplatform.gui.client.service;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.client.model.SummaryRecord;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;

/**
 * @author Michele Santomauro - CNR IMAA geoSDI Group
 * @email michele.santomauro@geosdi.org
 */
public interface GPCatalogFinderRemoteAsync {

    void getAllCSWServers(String organizationName,
            AsyncCallback<ArrayList<GPCSWServerBeanModel>> callback)
            throws GeoPlatformException;

    void searchCSWServers(PagingLoadConfig config,
            String searchText,
            String organization,
            AsyncCallback<PagingLoadResult<GPCSWServerBeanModel>> callback);

    void saveServerCSW(String alias,
            String serverUrl,
            String organization,
            AsyncCallback<GPCSWServerBeanModel> callback);

    void deleteServerCSW(Long serverID,
            AsyncCallback<Boolean> callback);

    void searchSummaryRecords(PagingLoadConfig config,
            CatalogFinderBean catalogFinder,
            AsyncCallback<PagingLoadResult<SummaryRecord>> callback);

    void searchFullRecords(PagingLoadConfig config,
            CatalogFinderBean catalogFinder,
            AsyncCallback<PagingLoadResult<FullRecord>> callback);

    void getRecordById(Long serverID,
            String identifier,
            String moduleName,
            AsyncCallback<String> callback);
}
