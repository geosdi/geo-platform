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
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import org.geosdi.geoplatform.gui.client.GPXMPPClient;
import org.geosdi.geoplatform.gui.client.command.login.basic.BasicLoginRequest;
import org.geosdi.geoplatform.gui.client.command.login.basic.BasicLoginResponse;
import org.geosdi.geoplatform.gui.client.config.SecurityGinInjector;
import org.geosdi.geoplatform.gui.client.event.ILoginManager;
import org.geosdi.geoplatform.gui.client.event.UserLoginManager;
import org.geosdi.geoplatform.gui.client.i18n.SecurityModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.widget.LoginStatus.EnumLoginStatus;
import org.geosdi.geoplatform.gui.client.widget.security.GPAdvancedSecurityWidget;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class LoginWidget extends GPAdvancedSecurityWidget implements ILoginManager {
    
    private LoginStatus status;
    private EventType eventOnSuccess;
    private String loginFailureMessage = "";
//    private SessionLoginWidget sessionLoginWidget;

    /**
     *
     * @param eventOnSuccess
     */
    @UiConstructor
    public LoginWidget(EventType eventOnSuccess) {
        super();
        this.eventOnSuccess = eventOnSuccess;
        this.generateLoginManager();
        SecurityGinInjector.MainInjector.getInstance().getSessionLoginWidget().
                setEventOnSuccess(eventOnSuccess);
    }
    
    @UiFactory
    LoginWidget makeLoginWidget() {
        return new LoginWidget(eventOnSuccess);
    }
    
    @Override
    protected void addStatusComponent() {
        status = new LoginStatus();
        status.setAutoWidth(Boolean.TRUE);
    }
    
    public void setEventOnSuccess(EventType eventOnSuccess) {
        this.eventOnSuccess = eventOnSuccess;
    }
    
    @Override
    public void reset() {
        userName.setValue("");
        password.setValue("");
        userName.setFocus(Boolean.TRUE);
        status.clearStatus("");
    }
    
    public void errorConnection() {
        password.setValue("");
        validate();
        userName.setFocus(Boolean.TRUE);
        status.clearStatus("");
        login.setEnabled(Boolean.TRUE);
    }
    
    @Override
    public void onSubmit() {
        status.setBusy(WindowsConstants.INSTANCE.pleaseWaitText());
        login.setEnabled(Boolean.FALSE);
        super.showProgressBar();
        super.loginError.setText("");
        
        ClientCommandDispatcher.getInstance().execute(
                new GPClientCommand<BasicLoginResponse>(new BasicLoginRequest(
                this.userName.getValue(), this.password.getValue())) {
            
            private static final long serialVersionUID = -1178797454775088815L;
            
            @Override
            public void onCommandSuccess(BasicLoginResponse response) {
                loginFailureMessage = "";
                executeLoginOperations(response.getResult());
                loginXMPPClient(userName.getValue(), password.getValue(),
                        response.getResult().getHostXmppServer());
            }
            
            @Override
            public void onCommandFailure(Throwable exception) {
                loginFailureMessage = exception.getMessage();
                errorConnection();
                status.setStatus(
                        LoginStatus.EnumLoginStatus.STATUS_MESSAGE_LOGIN_ERROR.
                        getValue(),
                        LoginStatus.EnumLoginStatus.STATUS_LOGIN_ERROR.
                        getValue());
            }
            
        });
    }
    
    private void executeLoginOperations(IGPAccountDetail resultDetails) {
        status.setStatus(
                LoginStatus.EnumLoginStatus.STATUS_MESSAGE_LOGIN.getValue(),
                LoginStatus.EnumLoginStatus.STATUS_LOGIN.getValue());
        GPAccountLogged.getInstance().setAccountDetail(resultDetails);
        SecurityGinInjector.MainInjector.getInstance().getSessionLoginWidget().
                setUserLogger(resultDetails.getUsername());
        Registry.register(UserSessionEnum.USER_TREE_OPTIONS.name(),
                resultDetails.getTreeOptions());
        Registry.register(UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name(),
                resultDetails);
        History.newItem("#" + resultDetails.getUsername() + "-login-sucess");
    }
    
    @Override
    public void loginDone() {
        if (loginFailureMessage != null && loginFailureMessage.equals("")) {
            final Timer t = new Timer() {
                
                @Override
                public void run() {
                    Dispatcher.forwardEvent(eventOnSuccess);
                    LayoutManager.getInstance().getViewport().unmask();
                    reset();
                    LoginWidget.super.progressBar.hide();
                }
                
            };
            t.schedule(2000);
        } else {
            System.out.println("Login failure message: " + loginFailureMessage);
            this.getParent().getElement().getStyle().clearDisplay();
            LoginWidget.super.progressBar.hide();
            super.loginError.setText(
                    SecurityModuleConstants.INSTANCE.LoginWidget_loginUsernamePasswordErrorText());
            login.setEnabled(Boolean.TRUE);
        }
    }
    
    private void loginXMPPClient(String username, String password,
            String hostXmppServer) {
        GPXMPPClient xMPPClient = new GPXMPPClient();
        xMPPClient.userXMPPLogin(username, password, hostXmppServer);
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
        this.login.setEnabled(Boolean.TRUE);
    }
    
    @Override
    public final void generateLoginManager() {
        UserLoginManager loginManager = new UserLoginManager(this);
    }
    
}
