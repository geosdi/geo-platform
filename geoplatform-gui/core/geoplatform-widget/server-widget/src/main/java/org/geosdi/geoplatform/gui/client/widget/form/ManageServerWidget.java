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

import com.extjs.gxt.ui.client.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Record;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid.ClicksToEdit;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
    private Button deleteServerButton = new Button("Delete Server");
    private GPCheckColumnConfig checkColumn;

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

        checkColumn = new GPCheckColumnConfig("delete",
                "Delete", 55, store, this.deleteServerButton);
        CellEditor checkBoxEditor = new CellEditor(new CheckBox());
        checkColumn.setEditor(checkBoxEditor);
        //This is very important: add checkColumn to the zero position!
        configs.add(0, checkColumn);
        final ColumnModel columnModel = new ColumnModel(configs);
        final Grid<GPServerBeanModel> grid = new Grid<GPServerBeanModel>(store, columnModel);
        RowEditor<GPServerBeanModel> rowEditor = new RowEditor<GPServerBeanModel>() {

            @Override
            protected void onEnter(ComponentEvent ce) {
                Record record = store.getRecord(grid.getSelectionModel().getSelectedItem());
                record.reject(true);
                super.onEnter(ce);
            }

            @Override
            protected void onHide() {
                super.onHide();
                //System.out.println("Hiding row editor and verifing the check status");
                checkColumn.manageDeleteButton();
            }
        };
        rowEditor.setClicksToEdit(ClicksToEdit.TWO);

        grid.setAutoExpandColumn("urlServer");
        grid.setBorders(true);
        grid.addPlugin(checkColumn);
        grid.addPlugin(rowEditor);
        grid.getAriaSupport().setLabelledBy(super.getHeader().getId() + "-label");
        grid.setHeight(300);
        super.add(grid);
        this.addButtonsToTheWindow(rowEditor);
    }

    private void addButtonsToTheWindow(final RowEditor<GPServerBeanModel> rowEditor) {
        ToolBar toolBar = new ToolBar();
        Button addServerButton = new Button("Add Server");
        addServerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                GPServerBeanModel server = new GPServerBeanModel();
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
        deleteServerButton.setEnabled(false);
        deleteServerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                rowEditor.stopEditing(true);
                List<GPServerBeanModel> serverList = store.getModels();
                String check = null;
                int i = 0;
                for (GPServerBeanModel gPServerBeanModel : serverList) {
                    check = checkColumn.getCheckState(gPServerBeanModel, "delete", i, 0);
                    if (check.equals("-on")) {
                        operation.deleteServer(gPServerBeanModel);
                    }
                    i++;
                }
                checkColumn.manageDeleteButton();
            }
        });
        toolBar.add(deleteServerButton);
        super.setTopComponent(toolBar);
        super.setButtonAlign(HorizontalAlignment.RIGHT);
        super.addButton(new Button("Reset", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                rowEditor.stopEditing(true);
                store.rejectChanges();
                checkColumn.manageDeleteButton();
            }
        }));
        super.addButton(new Button("Save", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                rowEditor.stopEditing(true);
                List<Record> modifiedElements = store.getModifiedRecords();
                System.out.println("Modified elements number: " + modifiedElements.size());
                for (Record record : modifiedElements) {
                    operation.updateInsertServer(record);
                }
            }
        }));
    }

    public void initSize() {
        super.setModal(true);
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

        private void deleteServer(final GPServerBeanModel server) {
            if (server.getId() != null) {
                GeoPlatformOGCRemote.Util.getInstance().deleteServer(server.getId(), new AsyncCallback<Boolean>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        GeoPlatformMessage.errorMessage("Error on Deleting Server",
                                "Error " + caught.getMessage() + " on server url: " + server.get("urlServer").toString());
                        LayoutManager.getInstance().getStatusMap().setStatus(
                                "Delete Server Error. " + caught.getMessage(),
                                EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        store.remove(server);
                        checkColumn.manageDeleteButton();
                        LayoutManager.getInstance().getStatusMap().setStatus(
                                "Server deleted succesfully", EnumSearchStatus.STATUS_SEARCH.toString());
                        //TODO: refresh the displayServerWidget
                        //displayServerWidget.addServer(server);
                    }
                });
            } else {
                store.remove(server);
            }
        }

        private void updateInsertServer(final Record record) {
            final GPServerBeanModel server = (GPServerBeanModel) record.getModel();
            
            GeoPlatformOGCRemote.Util.getInstance().insertServer(
                    server.getId(), record.get("alias").toString(),
                    record.get("urlServer").toString().trim(),
                    new AsyncCallback<GPServerBeanModel>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            GeoPlatformMessage.errorMessage("Error on Saving Server",
                                    caught.getMessage());
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                    "Save Server Error. " + caught.getMessage(),
                                    EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
                        }

                        @Override
                        public void onSuccess(GPServerBeanModel serverSaved) {
                            store.remove(server);
                            store.insert(serverSaved, 0);
                            LayoutManager.getInstance().getStatusMap().setStatus(
                                "Server added succesfully", EnumSearchStatus.STATUS_SEARCH.toString());
                        }
                    });
        }
    }
}
