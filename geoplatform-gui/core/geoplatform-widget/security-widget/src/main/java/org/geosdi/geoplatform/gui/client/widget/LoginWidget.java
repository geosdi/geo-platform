/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import org.geosdi.geoplatform.gui.client.GPXMPPClient;
import org.geosdi.geoplatform.gui.client.event.ILoginManager;
import org.geosdi.geoplatform.gui.client.event.UserLoginManager;
import org.geosdi.geoplatform.gui.client.widget.LoginStatus.EnumLoginStatus;
import org.geosdi.geoplatform.gui.client.widget.security.GPAdvancedSecurityWidget;
import org.geosdi.geoplatform.gui.global.enumeration.BaseLayerEnum;
import org.geosdi.geoplatform.gui.global.enumeration.GlobalRegistryEnum;
import org.geosdi.geoplatform.gui.global.security.GPAccountGuiComponents;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.server.gwt.SecurityRemoteImpl;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class LoginWidget extends GPAdvancedSecurityWidget implements ILoginManager {

    private LoginStatus status;
    private EventType eventOnSuccess;
    private String loginFailureMessage;
    private SessionLoginWidget sessionLoginWidget;

    /**
     *
     * @param eventOnSuccess
     */
    public LoginWidget(EventType eventOnSuccess) {
        super();
        this.eventOnSuccess = eventOnSuccess;
        this.generateLoginManager();
        this.sessionLoginWidget = new SessionLoginWidget(eventOnSuccess);
    }

    @Override
    protected void addStatusComponent() {
        status = new LoginStatus();
        status.setAutoWidth(Boolean.TRUE);
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
        status.setBusy("please wait...");
        login.setEnabled(Boolean.FALSE);
        super.showProgressBar();
        super.loginError.setText("");
        SecurityRemoteImpl.Util.getInstance().userLogin(
                this.userName.getValue(),
                this.password.getValue(),
                new AsyncCallback<IGPAccountDetail>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        loginFailureMessage = caught.getMessage();
                        errorConnection();
                        status.setStatus(
                                LoginStatus.EnumLoginStatus.STATUS_MESSAGE_LOGIN_ERROR.getValue(),
                                LoginStatus.EnumLoginStatus.STATUS_LOGIN_ERROR.getValue());
                    }

                    @Override
                    public void onSuccess(IGPAccountDetail result) {
                        loginFailureMessage = "";
                        GPAccountGuiComponents.getInstance().setAccountDetail(result);
                        status.setStatus(
                                LoginStatus.EnumLoginStatus.STATUS_MESSAGE_LOGIN.getValue(),
                                LoginStatus.EnumLoginStatus.STATUS_LOGIN.getValue());
                        sessionLoginWidget.setUserLogger(userName.getValue());
                        Registry.register(GlobalRegistryEnum.AUTH_KEY.getValue(), result.getAuthkey());
                        Registry.register(GlobalRegistryEnum.BASE_LAYER.getValue(), result.getBaseLayer());
                        loginXMPPClient(userName.getValue(), password.getValue(), result.getHostXmppServer());
                    }
                });
    }

    @Override
    public void loginDone() {
        if (loginFailureMessage.equals("")) {
            Timer t = new Timer() {

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
            this.getElement().getStyle().clearDisplay();
            LoginWidget.super.progressBar.hide();
            super.loginError.setText("Nome utente o password errati");
        }
    }

    private void loginXMPPClient(String username, String password, String hostXmppServer) {
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
    public void generateLoginManager() {
        UserLoginManager loginManager = new UserLoginManager(this);
    }

    /**
     * @param gwtEventOnSuccess the gwtEventOnSuccess to set
     */
    public void setGwtEventOnSuccess(GwtEvent gwtEventOnSuccess) {
        this.sessionLoginWidget.setGwtEventOnSuccess(gwtEventOnSuccess);
    }

    public void show() {
        this.login.setEnabled(Boolean.TRUE);
        validate();
        super.setVisible(Boolean.TRUE);
        RootPanel.get().add(this);
    }

    public void showSessionExpiredLogin() {
        this.sessionLoginWidget.show();
    }
}
