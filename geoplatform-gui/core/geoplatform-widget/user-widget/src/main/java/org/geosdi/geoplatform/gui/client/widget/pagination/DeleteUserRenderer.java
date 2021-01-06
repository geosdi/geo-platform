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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.event.timeout.IManageDeleteUserHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.ManageDeleteUserEvent;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.service.UserRemote;
import org.geosdi.geoplatform.gui.client.service.UserRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.grid.renderer.GPGridCellRenderer;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class DeleteUserRenderer extends GPGridCellRenderer<GPUserManageDetail>
        implements IManageDeleteUserHandler {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final UserRemoteAsync userRemote = UserRemote.Util.getInstance();
    //
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
    public void manageDeleteUser(final GPUserManageDetail user,
            final ListStore<GPUserManageDetail> store) {

        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            @Override
            public void onFailure(Throwable caught) {
                try {
                    throw caught;
                } catch (RpcTokenException e) {
                    // Can be thrown for several reasons:
                    //   - duplicate session cookie, which may be a sign of a cookie
                    //     overwrite attack
                    //   - XSRF token cannot be generated because session cookie isn't
                    //     present
                } catch (Throwable e) {
                    // unexpected
                }
            }

            @Override
            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) userRemote).setRpcToken(token);
                userRemote.deleteUser(user.getId(),
                        new AsyncCallback<Boolean>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                if (caught.getCause() instanceof GPSessionTimeout) {
                                    manageDeleteUserEvent.setUser(user);
                                    manageDeleteUserEvent.setStore(store);
                                    GPHandlerManager.fireEvent(new GPLoginEvent(
                                                    manageDeleteUserEvent));
                                } else {
                                    GeoPlatformMessage.errorMessage(
                                            WindowsConstants.INSTANCE.errorTitleText(),
                                            caught.getMessage());
                                }
                            }

                            @Override
                            public void onSuccess(Boolean result) {
                                store.remove(user);
                                GeoPlatformMessage.infoMessage(
                                        UserModuleConstants.INSTANCE.
                                        DeleteUserRenderer_infoUserSuccesfullyDeletedText(),
                                        "<ul><li>" + user.getUsername() + "</li></ul>");
                            }
                        });
            }
        });
    }
}
