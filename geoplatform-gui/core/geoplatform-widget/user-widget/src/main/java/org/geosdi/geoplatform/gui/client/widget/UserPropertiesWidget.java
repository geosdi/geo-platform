/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
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

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.event.timeout.IManageInsertUserHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.IManageUpdateUserHandler;
import org.geosdi.geoplatform.gui.client.event.timeout.ManageDeleteUserEvent;
import org.geosdi.geoplatform.gui.client.event.timeout.ManageInsertUserEvent;
import org.geosdi.geoplatform.gui.client.event.timeout.ManageUpdateUserEvent;
import org.geosdi.geoplatform.gui.client.form.binding.UserPropertiesBinding;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.server.gwt.UserRemoteImpl;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class UserPropertiesWidget extends GeoPlatformWindow
        implements IManageInsertUserHandler, IManageUpdateUserHandler {

    private GPUserManageDetail userDetail;
    private GPUserManageDetail clonedUserDetail;
    //
    private ContentPanel centralPanel;
    private Button saveButton;
    private UserPropertiesBinding userPropertiesBinding = new UserPropertiesBinding();
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
        this.centralPanel.setHeaderVisible(false);
        this.saveButton = new Button("Save", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (store.contains(userDetail)) {
                    String resetPassword = userPropertiesBinding.getPassword();
                    if (resetPassword.length() > 0) {
                        userDetail.setPassword(resetPassword);
                    }
                    manageUpdateUser();
                } else {
                    userDetail.setPassword(userPropertiesBinding.getPassword());
                    manageInsertUser();
                }

                userPropertiesBinding.stopMonitoring();
            }
        });
        Button closeButton = new Button("Close", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (store.contains(userDetail)) {
                    userDetail.setName(clonedUserDetail.getName());
                    userDetail.setEmail(clonedUserDetail.getEmail());
                    userDetail.setUsername(clonedUserDetail.getUsername());
                    userDetail.setAuthority(clonedUserDetail.getAuthority());

                    store.getRecord(userDetail).reject(true);
                }
                hide();
            }
        });
        this.centralPanel.add(this.userPropertiesBinding.getWidget());
        this.centralPanel.setSize(325, 255);
        super.add(this.centralPanel);
        super.getButtonBar().add(saveButton);
        super.getButtonBar().add(closeButton);
    }

    public void show(GPUserManageDetail userDetail) {
        this.userDetail = userDetail;
        super.show();

        this.userPropertiesBinding.startMonitoring(saveButton);
    }

    @Override
    public void initSize() {
        super.setSize(340, 270);
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("User Properties");
        super.setResizable(false);
        super.setLayout(new BorderLayout());
        super.setModal(true);
        super.setCollapsible(false);
        super.setPlain(true);
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowShow(WindowEvent we) {
                if (userDetail.getId() != null) {
                    clonedUserDetail = new GPUserManageDetail();
                    clonedUserDetail.setName(userDetail.getName());
                    clonedUserDetail.setEmail(userDetail.getEmail());
                    clonedUserDetail.setUsername(userDetail.getUsername());
                    clonedUserDetail.setAuthority(userDetail.getAuthority());
                }
                userPropertiesBinding.bindModel(userDetail);
            }
        });
    }

    @Override
    public void manageInsertUser() {
        UserRemoteImpl.Util.getInstance().insertUser(userDetail, new AsyncCallback<Long>() {

            @Override
            public void onFailure(Throwable caught) {
                if (caught.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(manageInsertUserEvent));
                } else {
                    GeoPlatformMessage.errorMessage("Error", caught.getMessage());
                }
            }

            @Override
            public void onSuccess(Long result) {
                userDetail.setId(result);
                store.insert(userDetail, 0);
                store.commitChanges();
                hide();

                GeoPlatformMessage.infoMessage("User successfully added",
                        "<ul><li>" + userDetail.getUsername() + "</li></ul>");
            }
        });
        System.out.println("User persisted: " + userDetail);
    }

    @Override
    public void manageUpdateUser() {
        UserRemoteImpl.Util.getInstance().updateUser(userDetail, new AsyncCallback<Long>() {

            @Override
            public void onFailure(Throwable caught) {
                if (caught.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(manageUpdateUserEvent));
                } else {
                    GeoPlatformMessage.errorMessage("Error", caught.getMessage());
                }
            }

            @Override
            public void onSuccess(Long result) {
                store.update(userDetail);
                store.commitChanges();
                hide();

                GeoPlatformMessage.infoMessage("User successfully modify",
                        "<ul><li>" + userDetail.getUsername() + "</li></ul>");
            }
        });
    }

    @Override
    public void reset() {
        userPropertiesBinding.resetFields();
    }
}
