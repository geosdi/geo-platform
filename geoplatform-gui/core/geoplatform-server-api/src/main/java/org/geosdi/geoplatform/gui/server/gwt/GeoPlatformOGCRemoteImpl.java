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

import java.util.ArrayList;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.model.server.GPLayerGrid;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;
import org.geosdi.geoplatform.gui.server.command.basic.BasicCapabilitiesCommand;
import org.geosdi.geoplatform.gui.server.service.IOGCService;
import org.geosdi.geoplatform.gui.server.spring.xsrf.GPAutoInjectingXsrfTokenServiceServlet;
import org.geosdi.geoplatform.gui.service.server.GeoPlatformOGCRemote;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class GeoPlatformOGCRemoteImpl extends GPAutoInjectingXsrfTokenServiceServlet
        implements GeoPlatformOGCRemote {

    private static final long serialVersionUID = 7340579377487014548L;
    //
    @Autowired
    private IOGCService ogcService;

    @Override
    public ArrayList<GPServerBeanModel> loadServers(String organizationName)
            throws GeoPlatformException {
        return ogcService.loadServers(organizationName);
    }

    @Override
    public GPServerBeanModel getServerDetails(Long idServer)
            throws GeoPlatformException {
        return ogcService.getServerDetails(idServer);
    }

    /**
     * @see BasicCapabilitiesCommand
     * @return
     * @throws GeoPlatformException
     * @deprecated
     */
    @Deprecated
    @Override
    public ArrayList<? extends GPLayerGrid> getCapabilities(String serverUrl,
            Long idServer)
            throws GeoPlatformException {
        return ogcService.getCapabilities(serverUrl,
                super.getThreadLocalRequest(),
                idServer);
    }

    @Override
    public GPServerBeanModel saveServer(Long id, String aliasServerName,
            String urlServer, String organization) throws GeoPlatformException {
        return ogcService.saveServer(id, aliasServerName, urlServer,
                organization);
    }

    @Override
    public ArrayList<String> findDistinctLayersDataSource()
            throws GeoPlatformException {
        return ogcService.findDistinctLayersDataSource(
                this.getThreadLocalRequest());
    }

    @Override
    public Boolean deleteServer(Long idServer) throws GeoPlatformException {
        return ogcService.deleteServer(idServer);
    }
}
