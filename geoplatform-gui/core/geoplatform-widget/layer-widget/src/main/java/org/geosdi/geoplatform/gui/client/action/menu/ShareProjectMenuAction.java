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
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseSecureAction;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadDefaultProjectRequest;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadDefaultProjectResponse;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.form.GPProjectManagementWidget;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Singleton
public class ShareProjectMenuAction extends MenuBaseSecureAction {

    private GPProjectManagementWidget projectManagementWidget;

    @Inject
    public ShareProjectMenuAction(
            GPProjectManagementWidget projectManagementWidget) {
        super(GPTrustedLevel.HIGH, LayerModuleConstants.INSTANCE.ShareProjectMenuAction_titleText(),
                AbstractImagePrototype.create(LayerResources.ICONS.arrowRefresh()));
        this.projectManagementWidget = projectManagementWidget;
    }

    @Override
    public void componentSelected(MenuEvent e) {
        final LoadDefaultProjectRequest loadDefaultProjectRequest = GWT.
                <LoadDefaultProjectRequest>create(LoadDefaultProjectRequest.class);
        
        ClientCommandDispatcher.getInstance().execute(
                new GPClientCommand<LoadDefaultProjectResponse>() {
            private static final long serialVersionUID = 3109256773218160485L;

            {
                super.setCommandRequest(loadDefaultProjectRequest);
            }

            @Override
            public void onCommandSuccess(LoadDefaultProjectResponse response) {
                projectManagementWidget.showSharingPanel(response.getResult());
            }

            @Override
            public void onCommandFailure(Throwable caught) {
                if (caught.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(null));
                } else {
                    GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                            ShareProjectMenuAction_errorSharingTitleText(),
                            WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            LayerModuleConstants.INSTANCE.ShareProjectMenuAction_statusErrorSharingText(),
                            SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                }
            }
        });
    }
}
