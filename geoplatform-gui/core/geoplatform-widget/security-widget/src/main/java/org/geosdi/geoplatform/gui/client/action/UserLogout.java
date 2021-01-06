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
package org.geosdi.geoplatform.gui.client.action;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.command.session.InvalidateSessionRequest;
import org.geosdi.geoplatform.gui.client.command.session.InvalidateSessionResponse;
import org.geosdi.geoplatform.gui.client.i18n.SecurityModuleConstants;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class UserLogout extends MenuBaseAction {



    public UserLogout() {
        super(SecurityModuleConstants.INSTANCE.UserLogout_tileText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.logout()));
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GeoPlatformMessage.confirmMessage(SecurityModuleConstants.INSTANCE.
                UserLogout_logoutMessageTitleText(),
                SecurityModuleConstants.INSTANCE.
                UserLogout_logoutMessageBodyText(),
                new Listener<MessageBoxEvent>() {
            @Override
            public void handleEvent(MessageBoxEvent be) {
                if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
                    Dispatcher.forwardEvent(
                            GeoPlatformEvents.REMOVE_WINDOW_CLOSE_LISTENER);
                    invalidateSession();
                }
            }
        });
    }

    private void invalidateSession() {
        GWT.log("Invalidate Sessione");
        final InvalidateSessionRequest invalidateSessionRequest = GWT.create(InvalidateSessionRequest.class);
        GWT.log(""+invalidateSessionRequest);

        ClientCommandDispatcher.getInstance().execute(
                new GPClientCommand<InvalidateSessionResponse>() {
            private static final long serialVersionUID = 3838394981874885388L;

            {
                super.setCommandRequest(invalidateSessionRequest);
            }

            @Override
            public void onCommandSuccess(InvalidateSessionResponse response) {
//                Window.Location.reload();
//                String url = GWT.getHostPageBaseURL() + "/cas/logout";
//                Window.open(url, null, "_self");
                closeSession(response);
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                GWT.log(""+exception.getMessage());
                System.out.println("Error on invalidating the session!!!");
                //TODO: In case of fail... what is possible to do??
            }
        });
    }

    protected void closeSession(InvalidateSessionResponse response) {
        response.executeInvalidateSession();
    }

}
