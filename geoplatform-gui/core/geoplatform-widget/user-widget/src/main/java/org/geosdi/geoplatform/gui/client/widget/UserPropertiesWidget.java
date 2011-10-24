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
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.form.binding.UserPropertiesBinding;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.server.gwt.UserRemoteImpl;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class UserPropertiesWidget extends GeoPlatformWindow {

    private GPUserManageDetail userDetail;
    private GPUserManageDetail clonedUserDetail;
    private ContentPanel centralPanel;
    private UserPropertiesBinding userPropertiesBinding = new UserPropertiesBinding();
    private ListStore<GPUserManageDetail> store;

    public UserPropertiesWidget(ListStore<GPUserManageDetail> store) {
        super(true);
        this.store = store;
    }

    @Override
    public void addComponent() {
        this.addCentralPanel();
    }

    private void addCentralPanel() {
        this.centralPanel = new ContentPanel(new FlowLayout());
        this.centralPanel.setHeaderVisible(false);
        Button saveButton = new Button("Save", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                //Add code here to manage save operations on DB
                if (store.contains(userDetail)) {
                    UserRemoteImpl.Util.getInstance().updateUser(userDetail, new AsyncCallback<Long>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            throw new UnsupportedOperationException("Not supported yet.");
                        }

                        @Override
                        public void onSuccess(Long result) {
                            store.update(userDetail);
                            store.commitChanges();
                            hide();
                        }
                    });
                } else {
                    UserRemoteImpl.Util.getInstance().insertUser(userDetail, new AsyncCallback<Long>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            throw new UnsupportedOperationException("Not supported yet.");
                        }

                        @Override
                        public void onSuccess(Long result) {
                            store.add(userDetail);
                            store.commitChanges();
                            hide();
                        }
                    });
                }
            }
        });
        Button closeButton = new Button("Close", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (store.contains(userDetail)) {
                    userDetail.setAuthority(clonedUserDetail.getAuthority());
                    userDetail.setName(clonedUserDetail.getName());
                    userDetail.setUsername(clonedUserDetail.getUsername());
                    userDetail.setEmail(clonedUserDetail.getEmail());
                    
                    store.getRecord(userDetail).reject(true);
                }
                hide();
            }
        });
        this.centralPanel.add(this.userPropertiesBinding.getWidget());
        this.centralPanel.setSize(315, 250);
        super.add(this.centralPanel);
        super.getButtonBar().add(saveButton);
        super.getButtonBar().add(closeButton);
    }

    public void show(GPUserManageDetail userDetail) {
        this.userDetail = userDetail;
        super.show();
    }

    @Override
    public void initSize() {
        super.setSize(330, 270);
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
                    clonedUserDetail.setAuthority(userDetail.getAuthority());
                    clonedUserDetail.setName(userDetail.getName());
                    clonedUserDetail.setUsername(userDetail.getUsername());
                    clonedUserDetail.setEmail(userDetail.getEmail());
                }
                userPropertiesBinding.bindModel(userDetail);
            }
        });
    }
}
