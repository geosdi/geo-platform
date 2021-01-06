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
package org.geosdi.geoplatform.gui.client.widget.pagination.projects;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.*;
import org.geosdi.geoplatform.gui.action.button.GPSecureButton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.action.projects.DeleteProjectAction;
import org.geosdi.geoplatform.gui.client.action.projects.GPProjectAction;
import org.geosdi.geoplatform.gui.client.action.projects.ShareProjectAction;
import org.geosdi.geoplatform.gui.client.command.layer.basic.SearchProjectsRequest;
import org.geosdi.geoplatform.gui.client.command.layer.basic.SearchProjectsResponse;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.MementoPersistenceConstants;
import org.geosdi.geoplatform.gui.client.i18n.buttons.ButtonsConstants;
import org.geosdi.geoplatform.gui.client.model.memento.puregwt.event.PeekCacheEvent;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.service.LayerRemote;
import org.geosdi.geoplatform.gui.client.service.LayerRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.form.GPProjectManagementWidget;
import org.geosdi.geoplatform.gui.client.widget.grid.pagination.GPPagingToolBar;
import org.geosdi.geoplatform.gui.client.widget.grid.pagination.listview.GPListViewSearchPanel;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.global.security.IGPAccountDetail;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.layers.projects.event.GPDefaultProjectTreeEvent;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;
import org.geosdi.geoplatform.gui.shared.GPTrustedLevel;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

import static com.google.gwt.user.client.ui.AbstractImagePrototype.create;
import static org.geosdi.geoplatform.gui.client.LayerResources.ICONS;
import static org.geosdi.geoplatform.gui.client.i18n.status.SearchStatusConstants.INSTANCE;
import static org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH;
import static org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus.STATUS_SEARCH;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPProjectSearchPanel extends GPListViewSearchPanel<GPClientProject> {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final LayerRemoteAsync layerRemote = LayerRemote.Util.getInstance();
    //
    private final GPDefaultProjectTreeEvent defaultProjectEvent = new GPDefaultProjectTreeEvent();
    private final GPDefaultProjectSelector selector;
    //
    private final GPProjectManagementWidget projectManagementWidget;
    //Protect this
    private GPSecureButton deleteButton;
    private GPSecureButton editButton;
    private GPSecureButton shareButton;

    public GPProjectSearchPanel(GPProjectManagementWidget projectManagementWidget) {
        super(true, 12);
        this.projectManagementWidget = projectManagementWidget;
        this.selector = new GPDefaultProjectSelector();
    }

    @Override
    public void finalizeInitOperations() {
        super.finalizeInitOperations();
        super.selectButton.setText(LayerModuleConstants.INSTANCE.GPProjectSearchPanel_selectButtonText());
        super.search.setFieldLabel(LayerModuleConstants.INSTANCE.GPProjectSearchPanel_searchlabelText());
        GPProjectAction action = new GPProjectAction(GPTrustedLevel.HIGH, this);
        GPSecureButton addProjectButton = new GPSecureButton(ButtonsConstants.INSTANCE.addText(), create(ICONS.projectAdd()), action);
        super.addButton(1, addProjectButton);
        addProjectButton.disable();
        this.editButton = new GPSecureButton(ButtonsConstants.INSTANCE.editText(), create(BasicWidgetResources.ICONS.edit()), action);
        this.editButton.disable();
        super.addButton(2, this.editButton);
        this.deleteButton = new GPSecureButton(ButtonsConstants.INSTANCE.deleteText(), create(ICONS.projectDelete()), new DeleteProjectAction(GPTrustedLevel.HIGH, this));
        this.deleteButton.disable();
        super.addButton(3, this.deleteButton);
        ShareProjectAction shareProjectAction = new ShareProjectAction(GPTrustedLevel.HIGH, this);
        this.shareButton = new GPSecureButton(ButtonsConstants.INSTANCE.shareText(), create(ICONS.arrowRefresh()), shareProjectAction);
        this.shareButton.disable();
        super.addButton(4, this.shareButton);
    }

    @Override
    public void setListViewProperties() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tpl for=\".\">");
        sb.append("<div class='project-box' style='padding-top: 4px;border: none'>");
        sb.append("<div class='thumbd'>{image}</div>");
        sb.append("<div>");
        sb.append(LayerModuleConstants.INSTANCE.GPProjectSearchPanel_listViewNameText());
        sb.append(": {name}</div>");
        sb.append("<div>");
        sb.append(LayerModuleConstants.INSTANCE.GPProjectSearchPanel_listViewPropertiesText());
        sb.append(": {numberOfElements} <B>{shared}</B></div>");
//        sb.append("<div>{message}</div>");
        sb.append("<div>");
        sb.append(LayerModuleConstants.INSTANCE.GPProjectSearchPanel_listViewVersionText());
        sb.append(": {version}</div>");
        sb.append("<div>{creationDate}</div>");
        sb.append("</div></tpl>");
        getListView().setTemplate(sb.toString());
        getListView().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getListView().setSize(630, 340);
    }

    @Override
    public void setPanelProperties() {
        super.setHeaderVisible(Boolean.FALSE);
        super.setSize(GPProjectManagementWidget.COMPONENT_WIDTH, GPProjectManagementWidget.COMPONENT_HEIGHT);
    }

    public void loadData() {
        super.init();
        searchText = "";
        loader.load(0, getPageSize());
    }

    @Override
    public void createStore() {
        super.toolBar = new GPPagingToolBar(super.getPageSize());
        super.proxy = new RpcProxy<PagingLoadResult<GPClientProject>>() {

            @Override
            protected void load(final Object loadConfig,
                                final AsyncCallback<PagingLoadResult<GPClientProject>> callback) {
                final SearchProjectsRequest searchProjectsRequest = GWT.
                        <SearchProjectsRequest>create(SearchProjectsRequest.class);

                searchProjectsRequest.setConfig((PagingLoadConfig) loadConfig);
                searchProjectsRequest.setSearchText(searchText);
                searchProjectsRequest.setImageURL(create(ICONS.gpProject()).getHTML());

                ClientCommandDispatcher.getInstance().execute(
                        new GPClientCommand<SearchProjectsResponse>() {

                            private static final long serialVersionUID = 3109256773218160485L;

                            {
                                super.setCommandRequest(searchProjectsRequest);
                            }

                            @Override
                            public void onCommandSuccess(SearchProjectsResponse response) {
                                callback.onSuccess(response.getResult());
                                toolBar.enable();
                            }

                            @Override
                            public void onCommandFailure(Throwable caught) {
                                clearWidgetElements();
                                try {
                                    throw caught;
                                } catch (GeoPlatformException e) {
                                    if (caught.getCause() instanceof GPSessionTimeout) {
                                        GPHandlerManager.fireEvent(new GPLoginEvent(null));
                                    } else {
                                        setSearchStatus(STATUS_NO_SEARCH, INSTANCE.STATUS_MESSAGE_NOT_SEARCH());
                                    }
                                } catch (Throwable e) {
                                    LayoutManager.getInstance().getStatusMap().setStatus(
                                            LayerModuleConstants.INSTANCE.GPProjectManagementWidget_headingText(),
                                            STATUS_NO_SEARCH.toString());
                                }
                            }
                        });
            }
        };
        super.loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);
        super.loader.setRemoteSort(false);
        super.store = new ListStore<GPClientProject>(loader);
        super.store.setMonitorChanges(true);
        super.toolBar.bind(loader);
    }

    @Override
    public void executeSelect() {
        if (getListView().getSelectionModel().getSelectedItem().isDefaultProject()) {
            GeoPlatformMessage.alertMessage(LayerModuleConstants.INSTANCE.
                            GPProjectSearchPanel_alertDefaultProjectSelectedTitleText(),
                    LayerModuleConstants.INSTANCE.
                            GPProjectSearchPanel_alertDefaultProjectSelectedBodyText());
            getListView().getSelectionModel().deselectAll();
        } else if (!MementoModuleInjector.MainInjector.getInstance().getMementoSave().isEmpty()) {
            GeoPlatformMessage.confirmMessage(
                    MementoPersistenceConstants.INSTANCE.MementoSaveCacheManager_unsavedOperationsText(),
                    MementoPersistenceConstants.INSTANCE.MementoSaveCacheManager_unsavedOperationMessageText(),
                    new Listener<MessageBoxEvent>() {
                        @Override
                        public void handleEvent(MessageBoxEvent be) {
                            if (Dialog.YES.equals(be.getButtonClicked().getItemId())) {
                                PeekCacheEvent peekCacheEvent = new PeekCacheEvent();
                                LayerHandlerManager.fireEvent(peekCacheEvent);
                            } else {
                                MementoModuleInjector.MainInjector.getInstance().getMementoSave().clear();
                                selector.selectDefaultProject();
                            }
                        }
                    });
        } else {
            selector.selectDefaultProject();
        }
    }

    @Override
    public void changeSelection(SelectionChangedEvent<GPClientProject> se) {
        GPClientProject clientProject = se.getSelectedItem();
        if (clientProject != null) {
            super.selectButton.enable();
            IGPAccountDetail accountInSession = Registry.get(
                    UserSessionEnum.ACCOUNT_DETAIL_IN_SESSION.name());
            if (clientProject.getOwner() == null || clientProject.getOwner().getId().equals(
                    accountInSession.getId())) {
                deleteButton.enable();
                this.editButton.enable();
                this.shareButton.enable();
            } else {
                this.shareButton.disable();
                this.editButton.disable();
                deleteButton.disable();
            }
        } else {
            selectButton.disable();
            deleteButton.disable();
            editButton.disable();
            this.shareButton.disable();
        }
    }

    /**
     * @return the defaultProjectEvent
     */
    public GPDefaultProjectTreeEvent getDefaultProjectEvent() {
        return defaultProjectEvent;
    }

    /**
     * @return Boolean
     */
    public boolean isDefaultSelectedProject() {
        return this.getSelectionModel().getSelectedItem().isDefaultProject();
    }

    public void deleteProject() {
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
                ((HasRpcToken) layerRemote).setRpcToken(token);
                layerRemote.deleteProject(
                        getSelectionModel().getSelectedItem().getId(),
                        new AsyncCallback<Object>() {

                            @Override
                            public void onFailure(Throwable caught) {
                            }

                            @Override
                            public void onSuccess(Object result) {
                                GeoPlatformMessage.infoMessage(
                                        LayerModuleConstants.INSTANCE.
                                                deleteProjectTitleText(),
                                        LayerModuleMessages.INSTANCE.
                                                GPProjectSearchPanel_projectRemovedMessage(
                                                        getSelectionModel().getSelectedItem().getName()));
                                store.remove(
                                        getSelectionModel().getSelectedItem());
                            }
                        });
            }
        });
    }

    public void shareProject(GPClientProject clientProject) {
        projectManagementWidget.showSharingPanel(clientProject);
    }

    /**
     * Internal Class Delegate to Select Default Project and Rebuild GPTreePanel
     */
    private class GPDefaultProjectSelector {

        private void selectDefaultProject() {
            searchStatus.setBusy(LayerModuleConstants.INSTANCE.
                    GPProjectSearchPanel_statusSettingDefaultProjectText());
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
                    ((HasRpcToken) layerRemote).setRpcToken(token);
                    layerRemote.setDefaultProject(getListView().getSelectionModel().getSelectedItem().getId(),
                            new AsyncCallback<Object>() {

                                /**
                                 * TODO MANAGE FOR SESSION TIMEOUT EXCEPTION *
                                 */
                                @Override
                                public void onFailure(Throwable caught) {
                                    GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                                                    GPProjectSearchPanel_settingDefaultProjectErrorTitleText(),
                                            caught.getMessage());
                                }

                                @Override
                                public void onSuccess(Object result) {
                                    setSearchStatus(STATUS_SEARCH, EnumProjectMessage.DEFAUTL_PROJECT_MESSAGE);
                                    //                            store.commitChanges();
                                    loadData();
                                    TimeoutHandlerManager.fireEvent(defaultProjectEvent);
                                }
                            });
                }
            });
        }
    }
}
