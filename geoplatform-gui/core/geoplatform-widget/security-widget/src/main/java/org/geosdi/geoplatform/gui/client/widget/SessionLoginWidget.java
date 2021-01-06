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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.GPXMPPClient;
import org.geosdi.geoplatform.gui.client.command.login.basic.BasicLoginRequest;
import org.geosdi.geoplatform.gui.client.command.login.basic.BasicLoginResponse;
import org.geosdi.geoplatform.gui.client.command.session.InvalidateSessionRequest;
import org.geosdi.geoplatform.gui.client.command.session.InvalidateSessionResponse;
import org.geosdi.geoplatform.gui.client.i18n.SecurityModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.SecurityModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.widget.LoginStatus.EnumLoginStatus;
import org.geosdi.geoplatform.gui.client.widget.security.GPSecurityWidget;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Singleton
public class SessionLoginWidget extends GPSecurityWidget {

    private final static int MAX_NUMBER_ATTEMPTS = 5;
    private LoginStatus status;
    private EventType eventOnSuccess;
    private GwtEvent gwtEventOnSuccess = null;
    private String userLogged;
    private int reloginAttempts;

    /**
     *
     * @param eventOnSuccess
     */
    public SessionLoginWidget(EventType eventOnSuccess) {
        super();
        this.eventOnSuccess = eventOnSuccess;
    }

    public SessionLoginWidget() {
        super();
    }

    @Override
    public void addStatusComponent() {
        status = new LoginStatus();
        status.setAutoWidth(true);
        getButtonBar().add(status);
        getButtonBar().add(new FillToolItem());
    }

    @Override
    public void reset() {
        userName.reset();
        password.reset();
        userName.focus();
        status.clearStatus("");
    }

    public void errorConnection() {
        password.reset();
        validate();
        userName.focus();
        status.clearStatus("");
        getButtonBar().enable();
    }

    @Override
    public void onSubmit() {
        if (this.userLogged == null
                || this.userLogged.equals(this.userName.getValue())) {
            status.setBusy(WindowsConstants.INSTANCE.pleaseWaitText());
            getButtonBar().disable();

            ClientCommandDispatcher.getInstance().execute(
                    new GPClientCommand<BasicLoginResponse>(
                    new BasicLoginRequest(userName.getValue(), password.
                    getValue())) {
                private static final long serialVersionUID = 2096093932860992231L;

                @Override
                public void onCommandSuccess(BasicLoginResponse response) {
                    GPAccountLogged.getInstance().setAccountDetail(response.
                            getResult());
                    status.setStatus(
                            LoginStatus.EnumLoginStatus.STATUS_MESSAGE_LOGIN.
                            getValue(),
                            LoginStatus.EnumLoginStatus.STATUS_LOGIN.getValue());
                    userScreen();
                    userLogged = userName.getValue();
                    reloginAttempts = 0;
                    Registry.register(UserSessionEnum.USER_TREE_OPTIONS.name(),
                            response.getResult().getTreeOptions());
                    Registry.register(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.
                            name(), response.getResult());
                    loginXMPPClient(userName.getValue(), password.getValue(),
                            response.getResult().getHostXmppServer());
                }

                @Override
                public void onCommandFailure(Throwable exception) {
                    errorConnection();
                    status.setStatus(
                            LoginStatus.EnumLoginStatus.STATUS_MESSAGE_LOGIN_ERROR.
                            getValue(),
                            LoginStatus.EnumLoginStatus.STATUS_LOGIN_ERROR.
                            getValue());
                    GeoPlatformMessage.infoMessage(SecurityModuleConstants.INSTANCE.loginErrorText(),
                            exception.getMessage());
                    ++reloginAttempts;
                }
            });
        } else if ((this.reloginAttempts + 1) < MAX_NUMBER_ATTEMPTS) {
            ++this.reloginAttempts;
            GeoPlatformMessage.infoMessage(
                    SecurityModuleMessages.INSTANCE.
                    SessionLoginWidget_numberOfAttemptsMessage(MAX_NUMBER_ATTEMPTS - this.reloginAttempts),
                    SecurityModuleConstants.INSTANCE.SessionLoginWidget_differentLoginUserText());
        } else {
            this.resetUserSession();
        }
    }

    private void loginXMPPClient(String username, String password,
            String hostXmppServer) {
        GPXMPPClient xMPPClient = new GPXMPPClient();
        xMPPClient.userXMPPLogin(username, password, hostXmppServer);
    }

    public void resetUserSession() {
        ClientCommandDispatcher.getInstance().execute(
                new GPClientCommand<InvalidateSessionResponse>() {
            private static final long serialVersionUID = 3838394981874885388L;

            {
                super.setCommandRequest(new InvalidateSessionRequest());
            }

            @Override
            public void onCommandSuccess(InvalidateSessionResponse response) {
                Dispatcher.forwardEvent(
                        GeoPlatformEvents.REMOVE_WINDOW_CLOSE_LISTENER);
                GeoPlatformMessage.infoMessage(SecurityModuleConstants.INSTANCE.
                        SessionLoginWidget_applicationLogoutText(),
                        SecurityModuleConstants.INSTANCE.SessionLoginWidget_differentLoginUserText());
                userLogged = null;
                Window.Location.reload();
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                //TODO: In case of fail... what is possible to do??
            }
        });
    }

    private void userScreen() {
        Timer t = new Timer() {
            @Override
            public void run() {
                if (eventOnSuccess != null) {
                    Dispatcher.forwardEvent(eventOnSuccess);
                } else {
                    LayoutManager.getInstance().getViewport().unmask();
                    if (getGwtEventOnSuccess() != null) {
                        TimeoutHandlerManager.fireEvent(getGwtEventOnSuccess());
                    }
                }
                hide();
                reset();
            }
        };
        t.schedule(100);
    }

    /**
     * Set the correct Status Icon Style
     *
     * @param status
     * @param message
     */
    public void setStatusLoginFinder(EnumLoginStatus status,
            EnumLoginStatus message) {
        this.status.setIconStyle(status.getValue());
        this.status.setText(message.getValue());
        getButtonBar().enable();
    }

    public void setEventOnSuccess(EventType eventOnSuccess) {
        this.eventOnSuccess = eventOnSuccess;
    }

    /**
     * @param gwtEventOnSuccess the gwtEventOnSuccess to set
     */
    public void setGwtEventOnSuccess(GwtEvent gwtEventOnSuccess) {
        this.gwtEventOnSuccess = gwtEventOnSuccess;
        this.eventOnSuccess = null;
    }

    @Override
    public void show() {
        getButtonBar().enable();
        validate();
        super.show();
    }

    /**
     * @return the gwtEventOnSuccess
     */
    public GwtEvent getGwtEventOnSuccess() {
        return gwtEventOnSuccess;
    }

    public void setUserLogger(String userLogged) {
        this.userLogged = userLogged;
    }
}