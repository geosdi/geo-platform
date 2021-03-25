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

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.store.StoreSorter;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.ServerWidgetResources;
import org.geosdi.geoplatform.gui.client.command.user.GetUserAuthoritiesRequest;
import org.geosdi.geoplatform.gui.client.command.user.GetUserAuthoritiesResponse;
import org.geosdi.geoplatform.gui.client.event.timeout.DisplayGetCapabilitiesEvent;
import org.geosdi.geoplatform.gui.client.event.timeout.IDisplayGetCapabilitiesHandler;
import org.geosdi.geoplatform.gui.client.i18n.ServerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.ServerModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.status.SearchStatusConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.form.ManageServerWidget;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPClientCommandExecutor;
import org.geosdi.geoplatform.gui.command.capabilities.basic.BasicCapabilitiesRequest;
import org.geosdi.geoplatform.gui.command.capabilities.basic.BasicCapabilitiesResponse;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.server.GPLayerGrid;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel.GPServerKeyValue;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.oauth2.IGPOAuth2CapabilitiesHandler;
import org.geosdi.geoplatform.gui.puregwt.oauth2.OAuth2HandlerManager;
import org.geosdi.geoplatform.gui.puregwt.oauth2.event.GPOAuth2GEBLoginEvent;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;
import org.geosdi.geoplatform.gui.service.server.GeoPlatformOGCRemote;
import org.geosdi.geoplatform.gui.service.server.GeoPlatformOGCRemoteAsync;
import org.geosdi.geoplatform.gui.shared.GPRole;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.gui.utility.oauth2.EnumOAuth2;

import java.util.ArrayList;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class DisplayServerWidget implements IDisplayGetCapabilitiesHandler {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final GeoPlatformOGCRemoteAsync geoPlatformOGCRemote = GeoPlatformOGCRemote.Util.getInstance();
    //
    private ToolBar toolbar;
    private ComboBox<GPServerBeanModel> comboServer;
    private final ListStore<GPServerBeanModel> store = new ListStore<GPServerBeanModel>();
    private SearchStatus searchStatus;
    private Button manageServersButton;
    private final GridLayersWidget gridWidget;
    private final ManageServerWidget manageServersWidget;
    private final PerformGetcapabilities loadCapabilities;
    private final GetUserAuthoritiesRequest getAuthoritiesRequest = new GetUserAuthoritiesRequest();

    /**
     * @Constructor @param theGridWidget
     */
    public DisplayServerWidget(GridLayersWidget theGridWidget) {
        TimeoutHandlerManager.addHandler(IDisplayGetCapabilitiesHandler.TYPE, this);
        init();
        this.gridWidget = theGridWidget;
        this.manageServersWidget = new ManageServerWidget(this, true);
        this.loadCapabilities = new PerformGetcapabilities();
    }

    private void init() {
        this.createComponents();
        this.createToolBar();
    }

    private void createComponents() {
        StoreSorter<GPServerBeanModel> storeSorter = new StoreSorter<GPServerBeanModel>() {

            @Override
            public int compare(Store<GPServerBeanModel> store,
                    GPServerBeanModel m1,
                    GPServerBeanModel m2, String property) {
                if (m1.getAlias() != null && m2.getAlias() != null) {
                    return m1.getAlias().toLowerCase().compareTo(
                            m2.getAlias().toLowerCase());
                }
                return 0;
            }

        };
        this.store.setStoreSorter(storeSorter);
        this.comboServer = new ComboBox<GPServerBeanModel>();

        comboServer.setEmptyText(ServerModuleConstants.INSTANCE.
                DisplayServerWidget_comboServerEmptyText());
        comboServer.setDisplayField(GPServerKeyValue.ALIAS.getValue());
        comboServer.setTemplate(getTemplate());
        comboServer.setWidth(250);
        comboServer.setEditable(Boolean.TRUE);
        comboServer.setForceSelection(Boolean.TRUE);
        comboServer.setStore(this.store);
        comboServer.setTypeAhead(true);
        comboServer.setTriggerAction(TriggerAction.ALL);

        this.comboServer.addSelectionChangedListener(new SelectionChangedListener<GPServerBeanModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<GPServerBeanModel> se) {
                changeSelection(se.getSelectedItem());
            }

        });

        this.manageServersButton = new Button(ServerModuleConstants.INSTANCE.
                DisplayServerWidget_manageServerButtonText(),
                AbstractImagePrototype.create(ServerWidgetResources.ICONS.addServer()),
                new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        manageServersWidget.show();
                    }

                });
        this.activateManageServersButton();
    }

    @Override
    public void activateManageServersButton() {
        GPClientCommandExecutor.executeCommand(new GPClientCommand<GetUserAuthoritiesResponse>() {

            private static final long serialVersionUID = -2316524074209342256L;

            {
                super.setCommandRequest(getAuthoritiesRequest);
            }

            @Override
            public void onCommandSuccess(GetUserAuthoritiesResponse response) {
                manageServersButton.disable();
                for (String role : response.getResult()) {
                    System.out.println("Role: " + role);
                    if (role.equals(GPRole.ADMIN.getRole())) { // TODO SecureButton
                        manageServersButton.enable();
                        return;
                    }
                }
            }

            @Override
            public void onCommandFailure(Throwable caught) {
                if (caught.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(new DisplayGetCapabilitiesEvent()));
                } else {
                    manageServersButton.setEnabled(false);
                    GeoPlatformMessage.errorMessage(WindowsConstants.INSTANCE.errorTitleText(),
                            WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            ServerModuleConstants.INSTANCE.DisplayServerWidget_statusErrorOpeningWindowText(),
                            EnumSearchStatus.STATUS_NO_SEARCH.toString());
                }
            }
        });
    }

    private void createToolBar() {
        this.toolbar = new ToolBar();
        this.searchStatus = new SearchStatus();
        searchStatus.setAutoWidth(true);
        this.toolbar.add(this.searchStatus);
        this.toolbar.add(this.comboServer);
        this.toolbar.add(new SeparatorToolItem());
        toolbar.add(new FillToolItem());
        this.toolbar.add(this.manageServersButton);
    }

    /**
     * @return String
     */
    private native String getTemplate() /*-{
     return  [
     '<tpl for=".">',
     '<div class="x-combo-list-item" qtip="{urlServer}" qtitle="Server">{alias}</div>',
     '</tpl>'
     ].join("");
     }-*/;

    /**
     * Set the correct Status Iconn Style
     *
     * @param status
     * @param message
     */
    public void setSearchStatus(Enum status, String message) {
        this.searchStatus.setIconStyle(status.toString());
        this.searchStatus.setText(message);
    }

    /**
     * Load All Server from WS
     */
    public void loadServers() {
        this.searchStatus.setBusy(
                ServerModuleConstants.INSTANCE.loadingServersText());
        this.store.removeAll();
        this.comboServer.clear();
        this.gridWidget.cleanStore();

        xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void onSuccess(XsrfToken token) {
                ((HasRpcToken) geoPlatformOGCRemote).setRpcToken(token);
                geoPlatformOGCRemote.loadServers(GPAccountLogged.getInstance().getOrganization(),
                        new AsyncCallback<ArrayList<GPServerBeanModel>>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                setSearchStatus(EnumSearchStatus.STATUS_SEARCH_ERROR,
                                        SearchStatusConstants.INSTANCE.STATUS_MESSAGE_SEARCH_ERROR());
                                GeoPlatformMessage.errorMessage(ServerModuleConstants.INSTANCE.
                                                serverServiceText(),
                                        ServerModuleConstants.INSTANCE.errorLoadingServerBodyText());
                            }

                            @Override
                            public void onSuccess(ArrayList<GPServerBeanModel> result) {
                                if (result.isEmpty()) {
                                    setSearchStatus(EnumSearchStatus.STATUS_NO_SEARCH,
                                            SearchStatusConstants.INSTANCE.STATUS_MESSAGE_NOT_SEARCH());
                                    GeoPlatformMessage.alertMessage(ServerModuleConstants.INSTANCE.serverServiceText(),
                                            ServerModuleConstants.INSTANCE
                                                    .DisplayServerWidget_alerThereAreNoServerText());
                                } else {
                                    setSearchStatus(EnumSearchStatus.STATUS_SEARCH,
                                            EnumSearchServer.STATUS_MESSAGE_LOAD.toString());
                                    GWT.log("" +result);
                                    store.add(result);
                                    store.sort(GPServerKeyValue.ALIAS.getValue(), Style.SortDir.ASC);
                                }
                            }

                        });
            }
        });
    }

    public void resetComponents() {
        this.store.removeAll();
        this.comboServer.setRawValue("");
        this.comboServer.clearSelections();
    }

    /**
     * @param selected
     */
    private void changeSelection(GPServerBeanModel selected) {
        this.gridWidget.cleanComponentForSelection();
        LayoutManager.getInstance().getStatusMap().setBusy(
                WindowsConstants.INSTANCE.loadingLayersText());
        if (selected != null) {
            this.gridWidget.maskGrid();
        }
        this.loadCapabilities.checkSelectedServer(selected);
    }

    /**
     * @param server
     */
    public void addServer(GPServerBeanModel server) {
        this.store.add(server);
        this.comboServer.setValue(server);
    }

    /**
     * Verify if the Server Url is already present in Store
     *
     * @param urlServer
     * @return boolean
     */
    public GPServerBeanModel containsServer(String urlServer) {
        for (GPServerBeanModel server : store.getModels()) {
            if (server.getUrlServer().equalsIgnoreCase(urlServer.trim())) {
                return server;
            }
        }
        return null;
    }

    /**
     * @return the toolbar
     */
    public ToolBar getToolbar() {
        return toolbar;
    }

    /**
     * @param layers
     */
    private void fillGrid(ArrayList<? extends GPLayerGrid> layers) {
        gridWidget.unMaskGrid();
        gridWidget.fillStore(layers);
        LayoutManager.getInstance().getStatusMap().setStatus(
                ServerModuleConstants.INSTANCE.DisplayServerWidget_statusLayerLoadedCorrectlyText(),
                EnumSearchStatus.STATUS_SEARCH.toString());
    }

    /**
     * Internal class
     */
    private class PerformGetcapabilities implements IGPOAuth2CapabilitiesHandler {

        private GPServerBeanModel selectedServer;

        public PerformGetcapabilities() {
            OAuth2HandlerManager.addHandler(IGPOAuth2CapabilitiesHandler.TYPE, this);
        }

        private void checkSelectedServer(GPServerBeanModel selected) {
            this.selectedServer = selected;
            if (selected != null) {
                if (selected.isLayersLoaded()) {
                    fillGrid(selected.getLayers());
                } else {
                    loadCapabilitiesFromWS();
                }
            }
        }

        @Override
        public void loadCapabilitiesFromWS() {
            final BasicCapabilitiesRequest capabilitiesRequest = GWT.create(BasicCapabilitiesRequest.class);
            capabilitiesRequest.setIdServer(selectedServer.getId());
            capabilitiesRequest.setServerUrl(selectedServer.getUrlServer());

            ClientCommandDispatcher.getInstance().execute(new GPClientCommand<BasicCapabilitiesResponse>() {

                private static final long serialVersionUID = -5938478884870425893L;

                {
                    super.setCommandRequest(capabilitiesRequest);
                }

                @Override
                public void onCommandSuccess(BasicCapabilitiesResponse response) {
                    selectedServer.setLayers(response.getResult());
                    fillGrid(response.getResult());
                }

                @Override
                public void onCommandFailure(Throwable exception) {
                    gridWidget.unMaskGrid();
                    LayoutManager.getInstance().getStatusMap().clearStatus("");

                    if (selectedServer.getUrlServer().contains(EnumOAuth2.GEB_STRING.getValue())) {
                        GeoPlatformMessage.infoMessage(ServerModuleConstants.INSTANCE.
                                googleSignOnRequiredTitleText(), ServerModuleConstants.INSTANCE.
                                googleSignOnRequiredBodyText());
                        OAuth2HandlerManager
                                .fireEvent(new GPOAuth2GEBLoginEvent(EnumOAuth2.LOAD_CAPABILITIES.getValue()));
                    } else {
                        GeoPlatformMessage.errorMessage(ServerModuleConstants.INSTANCE.
                                serverServiceText(), exception.getMessage());
                        LayoutManager.getInstance().getStatusMap().setStatus(ServerModuleMessages.INSTANCE
                                        .DisplayServerWidget_serverErrorMessage(exception.getMessage()),
                                EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                    }
                }

            });
        }

    }

    public ListStore<GPServerBeanModel> getStore() {
        return this.store;
    }
}