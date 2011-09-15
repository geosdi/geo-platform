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
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.RowEditorEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.HashMap;
import java.util.Map;
import org.geosdi.geoplatform.gui.client.ServerWidgetResources;
import org.geosdi.geoplatform.gui.client.widget.DisplayServerWidget;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.server.GPServerBeanModel;
import org.geosdi.geoplatform.gui.service.server.GeoPlatformOGCRemote;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class ManageServerWidget extends Window {

    private DisplayServerWidget displayServerWidget;
    private boolean initialized;
    private PerformOperation operation = new PerformOperation();
    private ListStore<GPServerBeanModel> store;// = new ListStore<GPServerBeanModel>();
    private Map<GPServerBeanModel, Record> recordMap = new HashMap<GPServerBeanModel, Record>();

    public ManageServerWidget(DisplayServerWidget displayServerWidget, boolean lazy) {
        this.displayServerWidget = displayServerWidget;
        this.store = displayServerWidget.getStore();
        if (!lazy) {
            init();
        }
    }

    private void loadServers() {
        this.mask("Loading Servers");
        LayoutManager.getInstance().getStatusMap().setBusy("Loading Layers.....");
        GeoPlatformOGCRemote.Util.getInstance().loadServers(new AsyncCallback<ArrayList<GPServerBeanModel>>() {

            @Override
            public void onFailure(Throwable caught) {
                ManageServerWidget.this.unmask();
                GeoPlatformMessage.errorMessage("Server Service",
                        "An Error occured loading Servers.");
            }

            @Override
            public void onSuccess(ArrayList<GPServerBeanModel> result) {
                ManageServerWidget.this.unmask();
                store.removeAll();
                store.add(result);
            }
        });
    }

    private void clearComponents() {
        super.hide();
    }

    private void addComponentToForm() {
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


        final ColumnModel columnModel = new ColumnModel(configs);

        final RowEditor<GPServerBeanModel> rowEditor = new RowEditor<GPServerBeanModel>() {

            @Override
            public void stopEditing(boolean saveChanges) {
                super.stopEditing(saveChanges);
            }
        };
        Grid<GPServerBeanModel> grid = new Grid<GPServerBeanModel>(store, columnModel);
        grid.setAutoExpandColumn("urlServer");
        grid.setBorders(true);
        grid.addPlugin(rowEditor);
        grid.getAriaSupport().setLabelledBy(super.getHeader().getId() + "-label");
        grid.setHeight(300);
        super.add(grid);

        ToolBar toolBar = new ToolBar();
        Button addServerButton = new Button("Add Server");
        addServerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                GPServerBeanModel server = new GPServerBeanModel();
                server.setAlias("New Server");
//                server.setUrlServer("http://");
                rowEditor.stopEditing(false);
                store.insert(server, 0);
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
        super.setButtonAlign(HorizontalAlignment.RIGHT);
        super.addButton(new Button("Reset", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                store.rejectChanges();
            }
        }));

        super.addButton(new Button("Save", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                List<Record> modifiedElements = store.getModifiedRecords();
                System.out.println("Modified elements number: " + modifiedElements.size());
                for (Record record : modifiedElements) {
                    GPServerBeanModel server = (GPServerBeanModel) record.getModel();
                    Record localRecord = recordMap.get(server);
                    if (localRecord != null) {
                        Map<String, Object> changes = localRecord.getChanges();
                        System.out.println("Alias: " + changes.get("alias"));
                        System.out.println("Url server: " + changes.get("urlServer"));
                        if (changes.get("alias") != null) {
                            server.setAlias(changes.get("alias").toString());
                        }
                        if (changes.get("urlServer") != null) {
                            server.setUrlServer(changes.get("urlServer").toString());
                        }
                    }
                    operation.addServer(server.getId(), server.getAlias(), server.getUrlServer());
                }
                store.commitChanges();
                for (GPServerBeanModel element : store.getModels()) {
                    System.out.println("Element: " + element.getAlias());
                }
            }
        }));
    }

    public void initSize() {
        super.setHeading("Server Manager");
        super.setBorders(false);
        super.setSize(600, 300);
    }

    @Override
    public void show() {
        if (!initialized) {
            this.init();
        }
        super.show();
        this.loadServers();
    }

    private void init() {
        this.initSize();
        this.addComponentToForm();
        this.initialized = true;
    }

    /**
     * Internal Class for Business Logic
     * 
     */
    private class PerformOperation {

        private void addServer(Long id, String serverName, String serverURL) {
//            GPServerBeanModel oldServer = displayServerWidget.containsServer(serverURL);
//            if (oldServer != null) {
//                notifyServerPresence(serverURL, oldServer);
//                this.updateServer(oldServer.getId(), serverName, serverURL);
//            } else {
//                this.saveServer(null, serverName, serverURL);
//            }
            this.updateServer(id, serverName, serverURL);
        }

        private void saveServer(Long id, String serverName, String serverURL) {
            GeoPlatformOGCRemote.Util.getInstance().insertServer(
                    id, serverName.trim(), serverURL.trim(),
                    new AsyncCallback<GPServerBeanModel>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "Save Server Error. " + caught.getMessage(),
                                    EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                        }

                        @Override
                        public void onSuccess(GPServerBeanModel server) {
//                            clearComponents();
//                            displayServerWidget.addServer(server);
                        }
                    });
        }

        private void notifyServerPresence(String serverUrl, GPServerBeanModel oldServer) {
//            LayoutManager.getInstance().getStatusMap().setStatus(
//                    "Save Server",
//                    EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
            GeoPlatformMessage.alertMessage("Server Present",
                    "The Server with url : " + serverUrl
                    + " is already present in Combo Box with the name "
                    + oldServer.getAlias() + ".");
        }

        private void updateServer(Long id, String serverName, String serverURL) {
            GeoPlatformOGCRemote.Util.getInstance().insertServer(
                    id, serverName.trim(), serverURL.trim(),
                    new AsyncCallback<GPServerBeanModel>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "Save Server Error. " + caught.getMessage(),
                                    EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                        }

                        @Override
                        public void onSuccess(GPServerBeanModel server) {
                            System.out.println("Server updated ok");
//                            TODO: Implement the server update
//                            displayServerWidget.addServer(server);
                        }
                    });
        }
    }
}