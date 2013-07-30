/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2013 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import java.util.List;
import org.geosdi.geoplatform.gui.client.event.timeout.IManageInsertUserHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.IManageUpdateUserHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.ManageInsertUserEvent;
import org.geosdi.geoplatform.gui.client.event.timeout.ManageUpdateUserEvent;
import org.geosdi.geoplatform.gui.client.form.binding.UserPropertiesBinding;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.server.gwt.UserRemoteImpl;
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
public class UserPropertiesWidget extends GeoPlatformWindow
        implements IManageInsertUserHandler, IManageUpdateUserHandler {

    private GPUserManageDetail user;
    //
    private ContentPanel centralPanel;
    private Button saveButton;
    private UserPropertiesBinding userPropertiesBinding;
    private ListStore<GPUserManageDetail> store;
    //
    private ManageInsertUserEvent manageInsertUserEvent = new ManageInsertUserEvent();
    private ManageUpdateUserEvent manageUpdateUserEvent = new ManageUpdateUserEvent();

    public UserPropertiesWidget(ListStore<GPUserManageDetail> store) {
        super(true);
        this.store = store;
        TimeoutHandlerManager.addHandler(IManageInsertUserHandler.TYPE, this);
        TimeoutHandlerManager.addHandler(IManageUpdateUserHandler.TYPE, this);
    }

    @Override
    public void addComponent() {
        this.addCentralPanel();
    }

    private void addCentralPanel() {
        this.centralPanel = new ContentPanel(new FlowLayout());
        this.centralPanel.setHeaderVisible(Boolean.FALSE);
        this.saveButton = new Button("Save",
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

        Button closeButton = new Button("Close",
                new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }

        });
        this.centralPanel.add(this.userPropertiesBinding.getWidget());
        this.centralPanel.setSize(325, 335);
        super.add(this.centralPanel);
        super.getButtonBar().add(saveButton);
        super.getButtonBar().add(closeButton);
    }

    public void show(GPUserManageDetail userDetail, List<String> roles) {
        this.user = userDetail;
        super.show();
        this.userPropertiesBinding.populateRoles(roles);
    }

    @Override
    public void initSize() {
        super.setSize(340, 350);
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml("User Properties");
        super.setLayout(new BorderLayout());
        super.setResizable(false);
        super.setModal(true);
        super.setCollapsible(false);
        super.setPlain(true);
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowShow(WindowEvent we) {
                userPropertiesBinding.bindModel(user);
            }

        });
    }

    @Override
    public void manageInsertUser() {
        user.setCreationDate(new Date());
        UserRemoteImpl.Util.getInstance().insertUser(user,
                GPAccountLogged.getInstance().getOrganization(),
                new AsyncCallback<Long>() {

            @Override
            public void onFailure(Throwable caught) {
                if (caught.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(
                            manageInsertUserEvent));
                } else {
                    GeoPlatformMessage.errorMessage("Error", caught.getMessage());
                }
            }

            @Override
            public void onSuccess(Long result) {
                user.setId(result);
                store.insert(user, 0);
                store.commitChanges();

                hide();

                // TODO statusbar...
                GeoPlatformMessage.infoMessage("User successfully added",
                        "<ul><li>" + user.getUsername() + "</li></ul>");
            }

        });
    }

    @Override
    public void manageUpdateUser() {
        UserRemoteImpl.Util.getInstance().updateUser(user,
                new AsyncCallback<Long>() {

            @Override
            public void onFailure(Throwable caught) {
                if (caught.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(
                            manageUpdateUserEvent));
                } else {
                    GeoPlatformMessage.errorMessage("Error", caught.getMessage());
                }
            }

            @Override
            public void onSuccess(Long result) {
                store.commitChanges();
                hide();

                // TODO statusbar...
                GeoPlatformMessage.infoMessage("User successfully modify",
                        "<ul><li>" + user.getUsername() + "</li></ul>");
            }

        });
    }

    @Override
    public void reset() {
        store.rejectChanges();
        userPropertiesBinding.unBindModel();
        userPropertiesBinding.resetFields();
    }

}
