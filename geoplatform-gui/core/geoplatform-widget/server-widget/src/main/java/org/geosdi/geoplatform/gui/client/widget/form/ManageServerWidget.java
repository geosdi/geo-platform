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

    private boolean initialized;
    private PerformOperation operation = new PerformOperation();
    private ListStore<GPServerBeanModel> store;// = new ListStore<GPServerBeanModel>();
    private Map<GPServerBeanModel, Record> recordMap = new HashMap<GPServerBeanModel, Record>();

    public ManageServerWidget(ListStore<GPServerBeanModel> store, boolean lazy) {
        this.store = store;
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
                Record record = store.getRecord(server);
                record.set("alias", "New Server");
                record.set("urlServer", "");
                store.update(server);
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
                    operation.updateInsertServer(record);
                }
                //store.commitChanges();
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

        private void updateInsertServer(final Record record) {
            final GPServerBeanModel server = (GPServerBeanModel) record.getModel();
            GeoPlatformOGCRemote.Util.getInstance().insertServer(
                    server.getId(), record.get("alias").toString(),
                    record.get("urlServer").toString(),
                    new AsyncCallback<GPServerBeanModel>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            GeoPlatformMessage.errorMessage("Error on Saving Server",
                                    "Error " + caught.getMessage() + " on server url: " + record.get("urlServer").toString());
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "Save Server Error. " + caught.getMessage(),
                                    EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                        }

                        @Override
                        public void onSuccess(GPServerBeanModel serverSaved) {
                            server.setId(serverSaved.getId());
                            Map<String, Object> changes = record.getChanges();
                            if (changes.get("alias") != null) {
                                server.setAlias(record.get("alias").toString());
                            }
                            if (changes.get("urlServer") != null) {
                                server.setUrlServer(record.get("urlServer").toString());
                            }
                            System.out.println("Alias: " + record.get("alias"));
                            System.out.println("Url server: " + record.get("urlServer"));
                            System.out.println("Server updated ok");
                            record.commit(true);
                            store.update(server);
//                            displayServerWidget.addServer(server);
                        }
                    });
        }
    }
}