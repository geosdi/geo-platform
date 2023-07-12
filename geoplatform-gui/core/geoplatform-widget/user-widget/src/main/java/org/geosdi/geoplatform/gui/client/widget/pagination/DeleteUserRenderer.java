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
package org.geosdi.geoplatform.gui.client.widget.pagination;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.command.user.crud.DeleteUserRequest;
import org.geosdi.geoplatform.gui.client.command.user.crud.DeleteUserResponse;
import org.geosdi.geoplatform.gui.client.event.timeout.IManageDeleteUserHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.ManageDeleteUserEvent;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.widget.grid.renderer.GPGridCellRenderer;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class DeleteUserRenderer extends GPGridCellRenderer<GPUserManageDetail> implements IManageDeleteUserHandler {

    private final ManageDeleteUserEvent manageDeleteUserEvent = new ManageDeleteUserEvent();

    public DeleteUserRenderer() {
        TimeoutHandlerManager.addHandler(IManageDeleteUserHandler.TYPE, this);
    }

    @Override
    public Object render(final GPUserManageDetail user, String property,
            ColumnData config, final int rowIndex,
            final int colIndex, final ListStore<GPUserManageDetail> store,
            Grid<GPUserManageDetail> grid) {

        Button button = new Button("", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {

                GeoPlatformMessage.confirmMessage(UserModuleConstants.INSTANCE.
                        DeleteUserRenderer_deleteUserTitleText(),
                        UserModuleMessages.INSTANCE.confirmDeleteUserBodyMessage(
                                user.getUsername()),
                        new Listener<MessageBoxEvent>() {

                            @Override
                            public void handleEvent(MessageBoxEvent be) {
                                if (Dialog.YES.equals(
                                        be.getButtonClicked().getItemId())) {
                                    manageDeleteUser(user, store);
                                }
                            }
                        });
            }
        });
        button.setToolTip(
                UserModuleConstants.INSTANCE.DeleteUserRenderer_deleteUserTitleText());
        button.setIcon(
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.delete()));
        button.setAutoWidth(true);

        return button;
    }

    @Override
    public void manageDeleteUser(final GPUserManageDetail user, final ListStore<GPUserManageDetail> store) {
        final DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setUserID(user.getId());
        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<DeleteUserResponse>() {

            {
                super.setCommandRequest(deleteUserRequest);
            }

            /**
             * @param response
             */
            @Override
            public void onCommandSuccess(DeleteUserResponse response) {
                store.remove(user);
                GeoPlatformMessage.infoMessage(UserModuleConstants.INSTANCE.DeleteUserRenderer_infoUserSuccesfullyDeletedText(), "<ul><li>" + user.getUsername() + "</li></ul>");
            }

            /**
             * @param exception
             */
            @Override
            public void onCommandFailure(Throwable exception) {
                if (exception.getCause() instanceof GPSessionTimeout) {
                    manageDeleteUserEvent.setUser(user);
                    manageDeleteUserEvent.setStore(store);
                    GPHandlerManager.fireEvent(new GPLoginEvent(
                            manageDeleteUserEvent));
                } else {
                    GeoPlatformMessage.errorMessage(WindowsConstants.INSTANCE.errorTitleText(), exception.getMessage());
                }
            }
        });
    }
}