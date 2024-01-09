/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.action;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.command.gui.GetAllGuiComponentIDsRequest;
import org.geosdi.geoplatform.gui.client.command.gui.GetAllGuiComponentIDsResopnse;
import org.geosdi.geoplatform.gui.client.command.role.GetAllRolesRequest;
import org.geosdi.geoplatform.gui.client.command.role.GetAllRolesResponse;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.widget.ManageRolesWidget;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class ManageRolesMenuAction extends MenuBaseAction {

    private final ManageRolesWidget rolesWidget = new ManageRolesWidget();

    public ManageRolesMenuAction() {
        super(UserModuleConstants.INSTANCE.ManageRolesMenuAction_titleText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.role()));
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        this.retrieveRoles();
    }

    private void retrieveRoles() {
        final GetAllRolesRequest getAllRolesRequest = new GetAllRolesRequest();
        getAllRolesRequest.setOrganization(GPAccountLogged.getInstance().getOrganization());
        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<GetAllRolesResponse>() {

            {
                super.setCommandRequest(getAllRolesRequest);
            }

            /**
             * @param response
             */
            @Override
            public void onCommandSuccess(GetAllRolesResponse response) {
                rolesWidget.setRoles(response.getResult());
                if (rolesWidget.getGuiComponentIDs() == null) {
                    retrieveGuiComponentIDs();
                } else {
                    rolesWidget.show();
                }
            }

            /**
             * @param exception
             */
            @Override
            public void onCommandFailure(Throwable exception) {
                GeoPlatformMessage.errorMessage(UserModuleConstants.INSTANCE.ManageRolesMenuAction_errorRetrievingRoleTitleText(), exception.getMessage());
            }
        });
    }

    private void retrieveGuiComponentIDs() {
        final GetAllGuiComponentIDsRequest getAllGuiComponentIDsRequest = new GetAllGuiComponentIDsRequest();
        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<GetAllGuiComponentIDsResopnse>() {

            {
                super.setCommandRequest(getAllGuiComponentIDsRequest);
            }

            /**
             * @param response
             */
            @Override
            public void onCommandSuccess(GetAllGuiComponentIDsResopnse response) {
                rolesWidget.setGuiComponentIDs(response.getResult());
                rolesWidget.show();
            }

            /**
             * @param exception
             */
            @Override
            public void onCommandFailure(Throwable exception) {
                GeoPlatformMessage.errorMessage(UserModuleConstants.INSTANCE.ManageRolesMenuAction_errorRetrievingPermissionsIDsTitleText(), exception.getMessage());
            }
        });
    }
}