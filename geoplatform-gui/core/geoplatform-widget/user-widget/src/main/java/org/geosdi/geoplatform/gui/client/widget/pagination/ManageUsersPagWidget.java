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
package org.geosdi.geoplatform.gui.client.widget.pagination;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail.GPUserManageDetailKeyValue;
import org.geosdi.geoplatform.gui.client.service.UserRemote;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.UserPropertiesWidget;
import org.geosdi.geoplatform.gui.client.widget.grid.pagination.grid.GPGridSearchWidget;
import org.geosdi.geoplatform.gui.server.gwt.UserRemoteImpl;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 * 
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ManageUsersPagWidget extends GPGridSearchWidget<GPUserManageDetail> {

    private UserPropertiesWidget userPropertiesWidget;

    public ManageUsersPagWidget() {
        super(true);
    }

    @Override
    public void finalizeInitOperations() {
        super.finalizeInitOperations();
        super.selectButton.setText("Modify User");
        super.search.setFieldLabel("Find User");
        this.userPropertiesWidget = new UserPropertiesWidget(super.store);
        super.addButton(1, new Button("Add User",
                                      BasicWidgetResources.ICONS.logged_user(),
                                      new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                showUserPropertiesWidget(true);
            }
        }));
    }

    @Override
    public void show() {
        super.init();
        super.show();
    }

    @Override
    public void setWindowProperties() {
        super.setHeading("GeoPlatform Users Management");
        super.setSize(600, 490);

        super.addWindowListener(new WindowListener() {

            @Override
            public void windowShow(WindowEvent we) {
                searchText = "";
                loader.load(0, getPageSize());
            }
        });
    }

    @Override
    public void createStore() {
        super.toolBar = new PagingToolBar(super.getPageSize());

        super.proxy = new RpcProxy<PagingLoadResult<GPUserManageDetail>>() {

            @Override
            protected void load(Object loadConfig,
                                AsyncCallback<PagingLoadResult<GPUserManageDetail>> callback) {

                UserRemote.Util.getInstance().searchUsers(
                        (PagingLoadConfig) loadConfig,
                        searchText, callback);
            }
        };

        super.loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
        super.loader.setRemoteSort(false);

        super.store = new ListStore<GPUserManageDetail>(loader);

        super.store.setMonitorChanges(true);

        super.toolBar.bind(loader);
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig nameColumn = new ColumnConfig();
        nameColumn.setId(GPUserManageDetailKeyValue.NAME.toString());
        nameColumn.setHeader("Name");
        configs.add(nameColumn);

        ColumnConfig usernameColumn = new ColumnConfig();
        usernameColumn.setId(GPUserManageDetailKeyValue.USERNAME.toString());
        usernameColumn.setHeader("Username");
        usernameColumn.setWidth(120);
        configs.add(usernameColumn);

        CheckColumnConfig enabledColumn = new CheckColumnConfig();
        enabledColumn.setId(GPUserManageDetailKeyValue.ENABLED.toString());
        enabledColumn.setHeader("Enabled");
        enabledColumn.setWidth(50);
        enabledColumn.setFixed(true);
        configs.add(enabledColumn);

        CheckColumnConfig tempColumn = new CheckColumnConfig();
        tempColumn.setId(GPUserManageDetailKeyValue.TEMPORARY.toString());
        tempColumn.setHeader("Temporary");
        tempColumn.setWidth(65);
        tempColumn.setFixed(true);
        configs.add(tempColumn);

        ColumnConfig roleColumn = new ColumnConfig();
        roleColumn.setId(GPUserManageDetailKeyValue.AUTORITHY.toString());
        roleColumn.setHeader("Role");
        roleColumn.setWidth(80);
        configs.add(roleColumn);

        ColumnConfig delColumn = new ColumnConfig();
        delColumn.setId("delColumn");
        delColumn.setHeader("Delete");
        delColumn.setWidth(40);
        delColumn.setFixed(true);
        delColumn.setResizable(false);
        delColumn.setSortable(false);
        delColumn.setRenderer(new DeleteUserRenderer());
        configs.add(delColumn);

        return new ColumnModel(configs);
    }

    @Override
    public void setGridProperties() {
        super.widget.setSize(530, 250);
        super.widget.setAutoExpandColumn(
                GPUserManageDetailKeyValue.NAME.toString());
    }

    @Override
    public void executeSelect() {
        this.showUserPropertiesWidget(false);
    }

    private void showUserPropertiesWidget(final boolean isNewUser) {
        searchStatus.setBusy("Retrive roles");

        UserRemoteImpl.Util.getInstance().getAllRoles(new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                setSearchStatus(
                        SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR,
                        "Error retrieving roles");
            }

            @Override
            public void onSuccess(ArrayList<String> result) {
                setSearchStatus(SearchStatus.EnumSearchStatus.STATUS_SEARCH,
                                SearchStatus.EnumSearchStatus.STATUS_MESSAGE_SEARCH);

                GPUserManageDetail userDetail;
                if (isNewUser) {
                    userDetail = new GPUserManageDetail();
                } else {
                    userDetail = widget.getSelectionModel().getSelectedItem();
                }
                userPropertiesWidget.show(userDetail, result);
            }
        });
    }
}
