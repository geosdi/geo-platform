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

import java.util.List;
import org.geosdi.geoplatform.gui.client.model.EPSGLayerData;
import org.geosdi.geoplatform.gui.client.service.PublisherRemote;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.server.service.IPublisherService;
import org.geosdi.geoplatform.gui.server.spring.xsrf.GPAutoInjectingXsrfTokenServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class PublisherRemoteImpl extends GPAutoInjectingXsrfTokenServiceServlet
        implements PublisherRemote {

    private static final long serialVersionUID = 5204638800999412388L;
    //
    @Autowired
    private IPublisherService publisherService;

    @Override
    @Deprecated
    public String publishLayerPreview(List<String> layerList,
            String workspace) throws GeoPlatformException {
        return publisherService.publishLayerPreview(super.
                getThreadLocalRequest(), layerList, workspace);
    }

    @Override
    @Deprecated
    public String processEPSGResult(List<EPSGLayerData> previewLayerList,
            String workspace) throws GeoPlatformException {
        return publisherService.processEPSGResult(super.getThreadLocalRequest(),
                previewLayerList, workspace);
    }

    @Override
    public void kmlPreview(String url) throws GeoPlatformException {
        publisherService.kmlPreview(super.getThreadLocalRequest(), url);
    }

    @Override
    public boolean createWorkspace(String workspaceName, boolean silent) throws GeoPlatformException {
        return this.publisherService.createWorkspace(workspaceName, silent,
                super.getThreadLocalRequest());
    }

}
