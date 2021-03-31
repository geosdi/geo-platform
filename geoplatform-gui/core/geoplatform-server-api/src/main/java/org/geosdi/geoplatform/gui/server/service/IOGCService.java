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
package org.geosdi.geoplatform.gui.server.service;

import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.server.GPLayerGrid;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface IOGCService {

    /**
     * @param httpServletRequest
     * @return {@link ArrayList<String>}
     * @throws GeoPlatformException
     */
    ArrayList<String> findDistinctLayersDataSource(HttpServletRequest httpServletRequest) throws GeoPlatformException;

    /**
     * @param organizationName
     * @return {@link ArrayList<String>}
     * @throws GeoPlatformException
     */
    ArrayList<GPServerBeanModel> loadServers(String organizationName) throws GeoPlatformException;

    /**
     * @param idServer
     * @return {@link GPServerBeanModel}
     * @throws GeoPlatformException
     */
    GPServerBeanModel getServerDetails(Long idServer) throws GeoPlatformException;

    /**
     * @param idServer
     * @return {@link Boolean}
     * @throws GeoPlatformException
     */
    Boolean deleteServer(Long idServer) throws GeoPlatformException;

    /**
     * @param serverUrl
     * @param httpServletRequest
     * @param idServer
     * @return {@link ArrayList<String>}
     * @throws GeoPlatformException
     */
    ArrayList<? extends GPLayerGrid> getCapabilities(String serverUrl, HttpServletRequest httpServletRequest, Long idServer) throws GeoPlatformException;

    /**
     * @param serverUrl
     * @param httpServletRequest
     * @param idServer
     * @return {@link ArrayList<String>}
     * @throws GeoPlatformException
     */
    ArrayList<? extends GPLayerGrid> getCapabilitiesAuth(String serverUrl, HttpServletRequest httpServletRequest, Long idServer) throws GeoPlatformException;

    /**
     * @param id
     * @param aliasServerName
     * @param urlServer
     * @param organization
     * @param username
     * @param password
     * @param proxy
     * @return {@link GPServerBeanModel}
     * @throws GeoPlatformException
     */
    GPServerBeanModel saveServer(Long id, String aliasServerName, String urlServer, String organization, String username, String password, boolean proxy) throws GeoPlatformException;
}