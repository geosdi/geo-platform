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
package org.geosdi.geoplatform.gui.client.widget.form;

import java.util.ArrayList;
import java.util.List;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.geosdi.geoplatform.gui.client.ServerWidgetResources;
import org.geosdi.geoplatform.gui.client.widget.DisplayServerWidget;
import org.geosdi.geoplatform.gui.client.widget.EnumSearchServer;
import org.geosdi.geoplatform.gui.client.widget.SaveStatus.EnumSaveStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;
import org.geosdi.geoplatform.gui.service.server.GeoPlatformOGCRemote;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ManageServerWidgetOLD extends GeoPlatformFormWidget<GPServerBeanModel> {

    private List<GPServerBeanModel> serverList = new ArrayList<GPServerBeanModel>();
    private DisplayServerWidget displayServerWidget;
    private ListStore<GPServerBeanModel> store = new ListStore<GPServerBeanModel>();

    public ManageServerWidgetOLD(DisplayServerWidget displayServerWidget) {
        super(true);
        this.displayServerWidget = displayServerWidget;
    }

    public void loadServers() {
//        this.searchStatus.setBusy("Loading Server...");

        GeoPlatformOGCRemote.Util.getInstance().loadServers(new AsyncCallback<ArrayList<GPServerBeanModel>>() {

            @Override
            public void onFailure(Throwable caught) {
//                setSearchStatus(EnumSearchStatus.STATUS_SEARCH_ERROR,
//                        EnumSearchStatus.STATUS_MESSAGE_SEARCH_ERROR);
                GeoPlatformMessage.errorMessage("Server Service",
                        "An Error occured loading Servers.");
            }

            @Override
            public void onSuccess(ArrayList<GPServerBeanModel> result) {
                serverList = result;
                store.add(result);
            }
        });
    }

    @Override
    public void addComponentToForm() {
        super.formPanel.setLayout(new FitLayout());
        super.setIcon(ServerWidgetResources.ICONS.addServer());
        super.setLayout(new FlowLayout());
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig aliasColumn = new ColumnConfig();
        aliasColumn.setId("alias");
        aliasColumn.setHeader("Server Alias");
        aliasColumn.setWidth(220);

        TextField<String> aliasTextfield = new TextField<String>();
        aliasTextfield.setAllowBlank(false);
        aliasColumn.setEditor(new CellEditor(aliasTextfield));
        configs.add(aliasColumn);

        ColumnConfig urlColumn = new ColumnConfig();
        urlColumn.setId("urlServer");
        urlColumn.setHeader("URL server");
        urlColumn.setWidth(400);

        TextField<String> urlTextfield = new TextField<String>();
        urlTextfield.setAllowBlank(false);
//        urlTextfield.setEmptyText("http://");
        urlColumn.setEditor(new CellEditor(urlTextfield));
        configs.add(urlColumn);

//        RpcProxy<ArrayList<GPServerBeanModel>> proxy = new RpcProxy<ArrayList<GPServerBeanModel>>() {
//
//            @Override
//            protected void load(Object loadConfig, AsyncCallback<ArrayList<GPServerBeanModel>> callback) {
//                
//            }
//        }
//
//
//        ListLoader loader = new com.extjs.gxt.ui.client.data.BaseListLoader<D>(null)


        ColumnModel columnModel = new ColumnModel(configs);

        final RowEditor<GPServerBeanModel> rowEditor = new RowEditor<GPServerBeanModel>();
        final Grid<GPServerBeanModel> grid = new Grid<GPServerBeanModel>(store, columnModel);
        grid.setAutoExpandColumn("urlServer");
        grid.setBorders(true);
        grid.addPlugin(rowEditor);
        grid.getAriaSupport().setLabelledBy(super.getHeader().getId() + "-label");
        super.formPanel.add(grid);

        ToolBar toolBar = new ToolBar();
        Button addServerButton = new Button("Add Server");
        addServerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                GPServerBeanModel server = new GPServerBeanModel();
                server.setAlias("New Server");
                server.setUrlServer("http://");
                rowEditor.stopEditing(false);
                store.insert(server, 0);
                System.out.println("Server to string: " + server.toString());
                rowEditor.startEditing(store.indexOf(server), true);

            }
        });
        toolBar.add(addServerButton);
        Button deleteServerButton = new Button("Delete Server");
        addServerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            //TODO: add code to enable the button only when a row is selected

            @Override
            public void componentSelected(ButtonEvent ce) {
                //TODO: add code to remove the server from list and from db
            }
        });
        
        toolBar.add(deleteServerButton);
        super.setTopComponent(toolBar);
        super.formPanel.setButtonAlign(HorizontalAlignment.RIGHT);
        super.formPanel.addButton(new Button("Reset", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                store.rejectChanges();
            }
        }));

        super.formPanel.addButton(new Button("Save", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                store.commitChanges();
                //TODO: add call to the services for save new server
            }
        }));
    }

    @Override
    public void initSize() {
        super.setHeading("Server Manager");
        super.setBorders(false);
        super.setSize(600, 300);
    }

//    @Override
//    public void reset() {
//        this.save.disable();
//        this.serverUrlTextField.clear();
//        this.serverNameTextField.clear();
//        this.saveStatus.clearStatus("");
//    }
    @Override
    public void initSizeFormPanel() {
        this.formPanel.setHeaderVisible(false);
        this.formPanel.setSize(600, 300);
    }

    private void clearComponents() {
        super.hide();
    }

    @Override
    public void show() {
        if (!isInitialized()) {
            this.loadServers();
            super.init();
        }
        super.show();
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Internal Class for Business Logic
     * 
     */
    private class PerformOperation {

        private void addServer(String serverName, String serverURL) {
            GPServerBeanModel oldServer = displayServerWidget.containsServer(serverURL);
            if (oldServer != null) {
                notifyServerPresence(serverURL, oldServer);
            } else {
                saveServer(serverName, serverURL);
            }
        }

        private void saveServer(String serverName, String serverURL) {
            GeoPlatformOGCRemote.Util.getInstance().insertServer(
                    null, serverName.trim(),
                    serverURL.trim(),
                    new AsyncCallback<GPServerBeanModel>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            setStatus(EnumSaveStatus.STATUS_NO_SAVE.getValue(),
                                    EnumSaveStatus.STATUS_MESSAGE_NOT_SAVE.getValue());
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "Save Server Error. " + caught.getMessage(),
                                    EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                        }

                        @Override
                        public void onSuccess(GPServerBeanModel server) {
//                            clearComponents();
                            displayServerWidget.addServer(server);
                        }
                    });
        }

        private void notifyServerPresence(String serverUrl, GPServerBeanModel oldServer) {
            setStatus(EnumSaveStatus.STATUS_NO_SAVE.getValue(),
                    EnumSearchServer.STATUS_MESSAGE_SERVER_EXISTING.toString());
            LayoutManager.getInstance().getStatusMap().setStatus(
                    "Save Server",
                    EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
            GeoPlatformMessage.alertMessage("Server Present",
                    "The Server with url : " + serverUrl
                    + " is already present in Combo Box with the name "
                    + oldServer.getAlias() + ".");
        }
    }
}
