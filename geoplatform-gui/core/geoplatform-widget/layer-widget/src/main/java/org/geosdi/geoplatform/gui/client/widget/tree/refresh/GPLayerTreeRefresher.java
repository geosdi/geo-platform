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
package org.geosdi.geoplatform.gui.client.widget.tree.refresh;

import com.google.inject.Inject;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadRootElementsRequest;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadRootElementsResponse;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.model.projects.GPShortClientProject;
import org.geosdi.geoplatform.gui.client.puregwt.refresh.GPCompositeRefreshHandler;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;

import java.util.logging.Logger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPLayerTreeRefresher implements GPCompositeRefreshHandler {

    private static final Logger logger = Logger.getLogger("GPLayerTreeRefresher");
    //
    private final LoadRootElementsRequest loadRootElementsRequest = new LoadRootElementsRequest();
    private final GPRootTreeNode root;

    @Inject
    public GPLayerTreeRefresher(GPRootTreeNode theRoot) {
        this.root = theRoot;
    }

    @Override
    public void refreshRootElements() {
        this.loadRootElementsRequest.setProjectId(this.root.getId());
        GPClientCommandExecutor.executeCommand(new GPClientCommand<LoadRootElementsResponse>() {

            {
                super.setCommandRequest(loadRootElementsRequest);
            }

            @Override
            public void onCommandSuccess(LoadRootElementsResponse response) {
                GPShortClientProject gpShortClientProject = response.getResult();
                root.setProjectElements(gpShortClientProject.getNumberOfElements());
                root.setProjectVersion(gpShortClientProject.getVersion());
                root.setInternalPublic(gpShortClientProject.isInternalPublic());
                root.setExternalPublic(gpShortClientProject.isExternalPublic());
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                GeoPlatformMessage.errorMessage("Error", exception.getMessage());
            }
        });
    }
}
