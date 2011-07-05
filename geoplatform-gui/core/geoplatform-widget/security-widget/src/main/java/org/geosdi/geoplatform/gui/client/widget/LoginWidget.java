/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import org.geosdi.geoplatform.gui.client.event.ILoginManager;
import org.geosdi.geoplatform.gui.client.event.UserLoginManager;
import org.geosdi.geoplatform.gui.client.widget.LoginStatus.EnumLoginStatus;
import org.geosdi.geoplatform.gui.client.widget.security.GPSecurityWidget;
import org.geosdi.geoplatform.gui.configuration.GenericClientTool;
import org.geosdi.geoplatform.gui.configuration.menubar.MenuInToolBar;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.server.gwt.SecurityRemoteImpl;
import org.geosdi.geoplatform.gui.utility.GeoPlatformUtils;
import org.geosdi.geoplatform.gui.utility.UserLoginEnum;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class LoginWidget extends GPSecurityWidget implements ILoginManager {

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
    public LoginWidget(EventType eventOnSuccess) {
        super();
        this.eventOnSuccess = eventOnSuccess;
        this.generateLoginManager();
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
        if (this.userLogged == null || this.userLogged.equals(this.userName.getValue())) {
            status.setBusy("please wait...");
            getButtonBar().disable();
            SecurityRemoteImpl.Util.getInstance().userLogin(this.userName.getValue(),
                    this.password.getValue(),
                    new AsyncCallback<String>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            errorConnection();
                            status.setStatus(
                                    LoginStatus.EnumLoginStatus.STATUS_MESSAGE_LOGIN_ERROR.getValue(),
                                    LoginStatus.EnumLoginStatus.STATUS_LOGIN_ERROR.getValue());
                            GeoPlatformMessage.infoMessage("Login Error",
                                    caught.getMessage());
                            ++reloginAttempts;
                        }

                        @Override
                        public void onSuccess(String result) {
                            status.setStatus(
                                    LoginStatus.EnumLoginStatus.STATUS_MESSAGE_LOGIN.getValue(),
                                    LoginStatus.EnumLoginStatus.STATUS_LOGIN.getValue());
                            userScreen(result, userLogged == null);
                            userLogged = userName.getValue();
                            reloginAttempts = 0;
                        }
                    });
        } else if((this.reloginAttempts+1) < MAX_NUMBER_ATTEMPTS) {
            ++this.reloginAttempts;
            GeoPlatformMessage.infoMessage("Number of attempts remained: " + (MAX_NUMBER_ATTEMPTS - this.reloginAttempts),
                    "A different user from the previous one is trying to connect to the application.");
        } else {
            GeoPlatformMessage.infoMessage("Application Logout",
                    "A different user from the previous one is trying to connect to the application");
            Dispatcher.forwardEvent(GeoPlatformEvents.USER_LOGOUT);
        }
    }

    public void resetUserSession() {
        SecurityRemoteImpl.Util.getInstance().invalidateSession(new AsyncCallback<Object>() {

            @Override
            public void onFailure(Throwable caught) {
                //TODO: In case of fail... what is possible to do??
            }

            @Override
            public void onSuccess(Object result) {
                userLogged = null;
            }
        });
    }

    private void userScreen(final String name, final boolean changeUseName) {
        Timer t = new Timer() {

            @Override
            public void run() {
                List<GenericClientTool> categories = GeoPlatformUtils.getInstance().getGlobalConfiguration().getToolbarClientTool().getClientTools();
                if (changeUseName) {
                    for (GenericClientTool menuBarCategory : categories) {
                        if (menuBarCategory.getId().equals(UserLoginEnum.USER_MENU.toString())) {
                            ((MenuInToolBar) menuBarCategory).setText(name);
                        }
                    }
                }
                if (getGwtEventOnSuccess() != null) {
                    LayoutManager.getInstance().getViewport().unmask();
                    LayerHandlerManager.fireEvent(getGwtEventOnSuccess());
                } else if (eventOnSuccess != null) {
                    Dispatcher.forwardEvent(eventOnSuccess);
                }
                hide();
                reset();
            }
        };
        t.schedule(100);
    }

    /**
     * Set the correct Status Iconn Style
     * @param status
     * @param message  
     */
    public void setStatusLoginFinder(EnumLoginStatus status,
            EnumLoginStatus message) {
        this.status.setIconStyle(status.getValue());
        this.status.setText(message.getValue());
        getButtonBar().enable();
    }

    @Override
    public void generateLoginManager() {
        UserLoginManager loginManager = new UserLoginManager(this);
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
}
