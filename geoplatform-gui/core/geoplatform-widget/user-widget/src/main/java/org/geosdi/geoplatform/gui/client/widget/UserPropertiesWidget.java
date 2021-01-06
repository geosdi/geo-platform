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

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.Date;
import java.util.List;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.event.timeout.IManageInsertUserHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.IManageUpdateUserHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.ManageInsertUserEvent;
import org.geosdi.geoplatform.gui.client.event.timeout.ManageUpdateUserEvent;
import org.geosdi.geoplatform.gui.client.form.binding.UserPropertiesBinding;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.service.UserRemote;
import org.geosdi.geoplatform.gui.client.service.UserRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.tab.GeoPlatformTabItem;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class UserPropertiesWidget extends GeoPlatformTabItem
        implements IManageInsertUserHandler, IManageUpdateUserHandler {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final UserRemoteAsync userRemote = UserRemote.Util.getInstance();
    //
    private GPUserManageDetail user;
    //
    private ContentPanel centralPanel;
    private Button saveButton;
    private UserPropertiesBinding userPropertiesBinding;
    private ListStore<GPUserManageDetail> store;
    //
    private final ManageInsertUserEvent manageInsertUserEvent = new ManageInsertUserEvent();
    private final ManageUpdateUserEvent manageUpdateUserEvent = new ManageUpdateUserEvent();
    //
    private UserPropertiesManagerWidget userPropertiesManagerWidget;

    public UserPropertiesWidget() {
        super(UserModuleConstants.INSTANCE.UserPropertiesWidget_headingText(),
                Boolean.FALSE);
//        super.setSize(340, 350);
        TimeoutHandlerManager.addHandler(IManageInsertUserHandler.TYPE, this);
        TimeoutHandlerManager.addHandler(IManageUpdateUserHandler.TYPE, this);
    }

    @Override
    public void addComponents() {
        this.addCentralPanel();
    }

    private void addCentralPanel() {
        this.centralPanel = new ContentPanel(new FlowLayout());
        this.centralPanel.setHeaderVisible(Boolean.FALSE);
        this.saveButton = new Button(ButtonsConstants.INSTANCE.saveText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.save()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        if (store.contains(user)) {
                            String resetPassword = userPropertiesBinding.getPassword();
                            if (resetPassword != null && resetPassword.length() > 0) {
                                user.setPassword(resetPassword);
                            }
                            manageUpdateUser();
                        } else {
                            user.setPassword(userPropertiesBinding.getPassword());
                            manageInsertUser();
                        }
                    }
                });
        this.userPropertiesBinding = new UserPropertiesBinding(store, saveButton);

        Button closeButton = new Button(ButtonsConstants.INSTANCE.closeText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.cancel()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        userPropertiesManagerWidget.hide();
                    }
                });
        this.centralPanel.add(this.userPropertiesBinding.getWidget());
        this.centralPanel.setSize(325, 335);
        this.centralPanel.getButtonBar().setHeight(52);
        this.centralPanel.getButtonBar().add(saveButton);
        this.centralPanel.getButtonBar().add(closeButton);
        super.add(this.centralPanel);
    }

    public void setData(GPUserManageDetail userDetail, List<String> roles) {
        this.user = userDetail;
        this.userPropertiesBinding.populateRoles(roles);
    }

    @Override
    protected void setWidgetProperties() {
        super.setWidgetProperties();
        super.setLayout(new FitLayout());
        this.addWidgetListener(new WidgetListener() {

            @Override
            public void widgetAttached(ComponentEvent ce) {
                super.widgetAttached(ce);
                if (user != null) {
                    userPropertiesBinding.bindModel(user);
                }
            }
        });
    }

    @Override
    public void manageInsertUser() {
        user.setCreationDate(new Date());
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
                userRemote.insertUser(user,
                        GPAccountLogged.getInstance().getOrganization(),
                        new AsyncCallback<Long>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                if (caught.getCause() instanceof GPSessionTimeout) {
                                    GPHandlerManager.fireEvent(new GPLoginEvent(
                                                    manageInsertUserEvent));
                                } else {
                                    GeoPlatformMessage.errorMessage(
                                            WindowsConstants.INSTANCE.
                                            errorTitleText(),
                                            caught.getMessage());
                                }
                            }

                            @Override
                            public void onSuccess(Long result) {
                                user.setId(result);
                                store.insert(user, 0);
                                store.commitChanges();
                                userPropertiesManagerWidget.hide();

                                // TODO statusbar...
                                GeoPlatformMessage.infoMessage(
                                        UserModuleConstants.INSTANCE.
                                        infoUserSuccesfullyAddedText(),
                                        "<ul><li>" + user.getUsername() + "</li></ul>");
                            }
                        });
            }
        });
    }

    @Override
    public void manageUpdateUser() {
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
                userRemote.updateUser(user, new AsyncCallback<Long>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        if (caught.getCause() instanceof GPSessionTimeout) {
                            GPHandlerManager.fireEvent(new GPLoginEvent(
                                    manageUpdateUserEvent));
                        } else {
                            GeoPlatformMessage.errorMessage(
                                    WindowsConstants.INSTANCE.
                                    errorTitleText(), caught.getMessage());
                        }
                    }

                    @Override
                    public void onSuccess(Long result) {
                        store.commitChanges();
                        userPropertiesManagerWidget.hide();

                        // TODO statusbar...
                        GeoPlatformMessage.infoMessage(
                                UserModuleConstants.INSTANCE.
                                infoUserSuccesfullyModifiedText(),
                                "<ul><li>" + user.getUsername() + "</li></ul>");
                    }
                });
            }
        });
    }

    public void setStore(ListStore<GPUserManageDetail> store) {
        this.store = store;
    }

    @Override
    public void reset() {
        store.rejectChanges();
        userPropertiesBinding.unBindModel();
        userPropertiesBinding.resetFields();
    }

    @Override
    public final void subclassCallToInit() {
        super.init();
    }

    public void setWindowToClose(
            UserPropertiesManagerWidget userPropertiesManagerWidget) {
        this.userPropertiesManagerWidget = userPropertiesManagerWidget;
    }
}
