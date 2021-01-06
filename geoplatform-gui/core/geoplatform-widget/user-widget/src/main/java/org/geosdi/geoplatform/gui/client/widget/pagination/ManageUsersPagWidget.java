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
package org.geosdi.geoplatform.gui.client.widget.pagination;

import com.extjs.gxt.ui.client.Registry;
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
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.UserModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.i18n.status.SearchStatusConstants;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail;
import org.geosdi.geoplatform.gui.client.model.GPUserManageDetail.GPUserManageDetailKeyValue;
import org.geosdi.geoplatform.gui.client.service.UserRemote;
import org.geosdi.geoplatform.gui.client.service.UserRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.UserPropertiesManagerWidget;
import org.geosdi.geoplatform.gui.client.widget.UserPropertiesWidget;
import org.geosdi.geoplatform.gui.client.widget.grid.pagination.grid.GPGridSearchWidget;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.model.user.GPSimpleUserKeyValue;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Singleton
public class ManageUsersPagWidget extends GPGridSearchWidget<GPUserManageDetail> {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final UserRemoteAsync userRemote = UserRemote.Util.getInstance();
    //
    @Inject
    private UserPropertiesWidget userPropertiesWidget;
    //
    private UserPropertiesManagerWidget userPropertiesManagerWidget;

    public ManageUsersPagWidget() {
        super(true);
    }

    @Override
    public void finalizeInitOperations() {
        super.finalizeInitOperations();
        //Using defered binding will be select the right widget to show
        this.userPropertiesManagerWidget = GWT.create(
                UserPropertiesManagerWidget.class);
        this.userPropertiesWidget.setWindowToClose(
                this.userPropertiesManagerWidget);
        //
        super.selectButton.setText(UserModuleConstants.INSTANCE.
                ManageUsersPagWidget_modifyUserText());
        super.search.setFieldLabel(UserModuleConstants.INSTANCE.
                ManageUsersPagWidget_findUserText());
        this.userPropertiesWidget.setStore(super.store);
        super.addButton(1, new Button(UserModuleConstants.INSTANCE.
                ManageUsersPagWidget_addUserText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.logged_user()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        showUserPropertiesWidget(true);
                    }

                }));
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml(UserModuleConstants.INSTANCE.
                ManageUsersPagWidget_headingText());
        super.setSize(670, 490);

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
            protected void load(final Object loadConfig,
                    final AsyncCallback<PagingLoadResult<GPUserManageDetail>> callback) {

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
                        userRemote.searchUsers(
                                (PagingLoadConfig) loadConfig, searchText,
                                GPAccountLogged.getInstance().getOrganization(),
                                callback);
                    }
                });
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
        List<ColumnConfig> configs = Lists.<ColumnConfig>newArrayList();

        ColumnConfig nameColumn = new ColumnConfig();
        nameColumn.setId(GPSimpleUserKeyValue.NAME.toString());
        nameColumn.setHeaderHtml(UserModuleConstants.INSTANCE.nameFieldText());
        configs.add(nameColumn);

        ColumnConfig usernameColumn = new ColumnConfig();
        usernameColumn.setId(GPSimpleUserKeyValue.USERNAME.toString());
        usernameColumn.setHeaderHtml(
                UserModuleConstants.INSTANCE.usernameFieldText());
        usernameColumn.setWidth(120);
        configs.add(usernameColumn);

        CheckColumnConfig enabledColumn = new CheckColumnConfig();
        enabledColumn.setId(GPUserManageDetailKeyValue.ENABLED.toString());
        enabledColumn.setHeaderHtml(
                UserModuleConstants.INSTANCE.enabledFieldLabelText());
        enabledColumn.setWidth(50);
        enabledColumn.setFixed(true);
        configs.add(enabledColumn);

        CheckColumnConfig tempColumn = new CheckColumnConfig();
        tempColumn.setId(GPUserManageDetailKeyValue.TEMPORARY.toString());
        tempColumn.setHeaderHtml(
                UserModuleConstants.INSTANCE.temporaryFieldLabelText());
        tempColumn.setWidth(65);
        tempColumn.setFixed(true);
        configs.add(tempColumn);

        ColumnConfig roleColumn = new ColumnConfig();
        roleColumn.setId(GPSimpleUserKeyValue.AUTORITHY.toString());
        roleColumn.setHeaderHtml(
                UserModuleConstants.INSTANCE.userRoleLabelText());
        roleColumn.setWidth(80);
        configs.add(roleColumn);

        ColumnConfig trustedLevelColumn = new ColumnConfig();
        trustedLevelColumn.setId(GPSimpleUserKeyValue.TRUSTED_LEVEL.toString());
        trustedLevelColumn.setHeaderHtml(UserModuleConstants.INSTANCE.
                ManageUsersPagWidget_trustedFieldLabelText());
        trustedLevelColumn.setWidth(70);
        configs.add(trustedLevelColumn);

        ColumnConfig delColumn = new ColumnConfig();
        delColumn.setId("delColumn");
        delColumn.setHeaderHtml(ButtonsConstants.INSTANCE.deleteText());
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
        super.widget.setSize(600, 250);
        super.widget.setAutoExpandColumn(GPSimpleUserKeyValue.NAME.toString());
        super.widget.setColumnLines(true);
        super.widget.setStripeRows(true);
    }

    @Override
    public void executeSelect() {
        this.showUserPropertiesWidget(Boolean.FALSE);
    }

    private void showUserPropertiesWidget(final boolean isNewUser) {
        searchStatus.setBusy(UserModuleConstants.INSTANCE.
                ManageUsersPagWidget_statusRetrievingRolesText());

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
                userRemote.getAllRoles(
                        GPAccountLogged.getInstance().getOrganization(),
                        new AsyncCallback<ArrayList<String>>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                setSearchStatus(
                                        SearchStatus.EnumSearchStatus.STATUS_SEARCH_ERROR,
                                        UserModuleConstants.INSTANCE.
                                        ManageUsersPagWidget_statusErrorRetrievingRolesText());
                            }

                            @Override
                            public void onSuccess(ArrayList<String> result) {
                                setSearchStatus(
                                        SearchStatus.EnumSearchStatus.STATUS_SEARCH,
                                        SearchStatusConstants.INSTANCE.STATUS_MESSAGE_SEARCH());

                                GPUserManageDetail userDetail;
                                if (isNewUser) {
                                    userDetail = new GPUserManageDetail();
                                    Registry.register(
                                            UserSessionEnum.SEARCHED_USER.name(),
                                            null);
                                } else {
                                    userDetail = widget.getSelectionModel().getSelectedItem();
                                    Registry.register(
                                            UserSessionEnum.SEARCHED_USER.name(),
                                            userDetail);
                                }
                                userPropertiesWidget.setData(userDetail, result);
                                userPropertiesManagerWidget.show();
                            }

                        });
            }
        });
    }

}
