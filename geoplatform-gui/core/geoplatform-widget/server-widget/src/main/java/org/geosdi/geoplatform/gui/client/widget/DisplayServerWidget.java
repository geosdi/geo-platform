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

import org.geosdi.geoplatform.gui.utility.oauth2.EnumOAuth2;
import java.util.ArrayList;

import org.geosdi.geoplatform.gui.client.ServerWidgetResources;

import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;
import org.geosdi.geoplatform.gui.client.event.timeout.DisplayGetCapabilitiesEvent;
import org.geosdi.geoplatform.gui.client.event.timeout.IDisplayGetCapabilitiesHandler;
import org.geosdi.geoplatform.gui.client.widget.form.ManageServerWidget;
import org.geosdi.geoplatform.gui.exception.GPSessionTimeout;
import org.geosdi.geoplatform.gui.global.security.GPRole;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.model.server.GPLayerGrid;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel.GPServerKeyValue;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.oauth2.IGPOAuth2CapabilitiesHandler;
import org.geosdi.geoplatform.gui.puregwt.oauth2.OAuth2HandlerManager;
import org.geosdi.geoplatform.gui.puregwt.oauth2.event.GPOAuth2GEBLoginEvent;
import org.geosdi.geoplatform.gui.puregwt.session.TimeoutHandlerManager;
import org.geosdi.geoplatform.gui.server.gwt.ServerRemoteImpl;
import org.geosdi.geoplatform.gui.service.server.GeoPlatformOGCRemote;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class DisplayServerWidget implements IDisplayGetCapabilitiesHandler {

    private ToolBar toolbar;
    private ComboBox<GPServerBeanModel> comboServer;
    private ListStore<GPServerBeanModel> store = new ListStore<GPServerBeanModel>();
    private SearchStatus searchStatus;
    private Button manageServersButton;
    private GridLayersWidget gridWidget;
    private ManageServerWidget manageServersWidget;
    private PerformGetcapabilities loadCapabilities;

    /**
     * @Constructor
     * @param theGridWidget 
     */
    public DisplayServerWidget(GridLayersWidget theGridWidget) {
        TimeoutHandlerManager.addHandler(IDisplayGetCapabilitiesHandler.TYPE, this);
        init();
        this.gridWidget = theGridWidget;
        this.manageServersWidget = new ManageServerWidget(store, true);
        this.loadCapabilities = new PerformGetcapabilities();
    }

    private void init() {
        this.createComponents();
        this.createToolBar();
    }

    private void createComponents() {
        this.comboServer = new ComboBox<GPServerBeanModel>();

        comboServer.setEmptyText("Select a Server...");
        comboServer.setDisplayField(GPServerKeyValue.ALIAS.getValue());
        comboServer.setTemplate(getTemplate());
        comboServer.setWidth(250);
        comboServer.setEditable(false);
        comboServer.setStore(this.store);
        comboServer.setTypeAhead(true);
        comboServer.setTriggerAction(TriggerAction.ALL);

        this.comboServer.addSelectionChangedListener(new SelectionChangedListener<GPServerBeanModel>() {

            @Override
            public void selectionChanged(
                    SelectionChangedEvent<GPServerBeanModel> se) {
                changeSelection(se.getSelectedItem());
            }
        });

        this.manageServersButton = new Button("Manage Servers",
                ServerWidgetResources.ICONS.addServer(),
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
        ServerRemoteImpl.Util.getInstance().getUserAuthorities(new AsyncCallback<List<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                if (caught.getCause() instanceof GPSessionTimeout) {
                    GPHandlerManager.fireEvent(new GPLoginEvent(new DisplayGetCapabilitiesEvent()));
                } else {
                    manageServersButton.setEnabled(false);
                    GeoPlatformMessage.errorMessage("Error",
                            "An error occurred while making the requested operation.\n"
                            + "Verify network connections and try again."
                            + "\nIf the problem persists contact your system administrator.");
                    LayoutManager.getInstance().getStatusMap().setStatus(
                            "Error opening Get Capabilities window.",
                            EnumSearchStatus.STATUS_NO_SEARCH.toString());
                    System.out.println("Error opening Get Capabilities window: " + caught.toString()
                            + " data: " + caught.getMessage());
                }
            }

            @Override
            public void onSuccess(List<String> result) {
                manageServersButton.setEnabled(false);
                for (String role : result) {
                    System.out.println("Role: " + role);
                    if (role.equals(GPRole.ADMIN.toString())) {
                        manageServersButton.setEnabled(true);
                        return;
                    }
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
     *
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
     * @param status
     * @param message
     */
    public void setSearchStatus(Enum status,
            Enum message) {
        this.searchStatus.setIconStyle(status.toString());
        this.searchStatus.setText(message.toString());
    }

    /**
     * Load All Server from WS
     */
    public void loadServers() {
        this.searchStatus.setBusy("Loading Server...");

        GeoPlatformOGCRemote.Util.getInstance().loadServers(new AsyncCallback<ArrayList<GPServerBeanModel>>() {

            @Override
            public void onFailure(Throwable caught) {
                setSearchStatus(EnumSearchStatus.STATUS_SEARCH_ERROR,
                        EnumSearchStatus.STATUS_MESSAGE_SEARCH_ERROR);
                GeoPlatformMessage.errorMessage("Server Service",
                        "An Error occured loading Servers.");
            }

            @Override
            public void onSuccess(ArrayList<GPServerBeanModel> result) {
                if (result.isEmpty()) {
                    setSearchStatus(EnumSearchStatus.STATUS_NO_SEARCH,
                            EnumSearchStatus.STATUS_MESSAGE_NOT_SEARCH);
                    GeoPlatformMessage.alertMessage("Server Service",
                            "There are no Servers.");
                } else {
                    setSearchStatus(EnumSearchStatus.STATUS_SEARCH,
                            EnumSearchServer.STATUS_MESSAGE_LOAD);
                    store.add(result);
                }
            }
        });
    }

    public void resetComponents() {
        this.store.removeAll();
        this.comboServer.setRawValue("");
        this.comboServer.clearSelections();
    }

    /**
     * 
     * @param selected
     */
    private void changeSelection(GPServerBeanModel selected) {
        this.gridWidget.cleanComponentForSelection();
        LayoutManager.getInstance().getStatusMap().setBusy("Loading Layers.....");
        this.gridWidget.maskGrid();
        
        this.loadCapabilities.checkSelectedServer(selected);
    }

    /**
     * 
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
     * @return 
     *         boolean
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
     * 
     * @param layers 
     */
    private void fillGrid(ArrayList<? extends GPLayerGrid> layers) {
        gridWidget.unMaskGrid();
        gridWidget.fillStore(layers);
        LayoutManager.getInstance().getStatusMap().setStatus(
                "Layers have been loaded correctly by the service",
                EnumSearchStatus.STATUS_SEARCH.toString());
    }

    /**
     * Internal class
     * 
     */
    private class PerformGetcapabilities implements IGPOAuth2CapabilitiesHandler {

        private GPServerBeanModel selectedServer;

        public PerformGetcapabilities() {
            OAuth2HandlerManager.addHandler(IGPOAuth2CapabilitiesHandler.TYPE, this);
        }

        private void checkSelectedServer(GPServerBeanModel selected) {
            this.selectedServer = selected;
            if (selected.isLayersLoaded()) {
                fillGrid(selected.getLayers());
            } else {
                loadCapabilitiesFromWS();
            }
        }

        @Override
        public void loadCapabilitiesFromWS() {
            GeoPlatformOGCRemote.Util.getInstance().getCapabilities(selectedServer.getId(),
                    new AsyncCallback<ArrayList<? extends GPLayerGrid>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            gridWidget.unMaskGrid();
                            LayoutManager.getInstance().getStatusMap().clearStatus("");

                            if (selectedServer.getUrlServer().contains(EnumOAuth2.GEB_STRING.getValue())) {
                                GeoPlatformMessage.infoMessage("Google sign on required", "Is necessary to sign on Google account for access the Google Earth Builder functionality");
                                OAuth2HandlerManager.fireEvent(new GPOAuth2GEBLoginEvent(EnumOAuth2.LOAD_CAPABILITIES.getValue()));
                            } else {
                                GeoPlatformMessage.errorMessage("Server Service",
                                        "An error occured loading layers.");
                                LayoutManager.getInstance().getStatusMap().setStatus(
                                        "An error occured loading layers from the service.",
                                        EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                            }
                        }

                        @Override
                        public void onSuccess(
                                ArrayList<? extends GPLayerGrid> result) {
                            selectedServer.setLayers(result);
                            fillGrid(result);
                        }
                    });
        }
    }

    public ListStore<GPServerBeanModel> getStore() {
        return this.store;
    }
}
