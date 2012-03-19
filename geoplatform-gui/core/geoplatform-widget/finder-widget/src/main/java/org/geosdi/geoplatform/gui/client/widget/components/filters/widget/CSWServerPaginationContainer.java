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
package org.geosdi.geoplatform.gui.client.widget.components.filters.widget;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.widget.components.form.CSWServerFormWidget;
import org.geosdi.geoplatform.gui.impl.containers.pagination.grid.EditorGridLayoutPaginationContainer;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel.GPCSWServerKeyValue;
import org.geosdi.geoplatform.gui.server.gwt.GPCatalogFinderRemoteImpl;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class CSWServerPaginationContainer
        extends EditorGridLayoutPaginationContainer<GPCSWServerBeanModel> {
    
    private CSWServerFormWidget serverForm = new CSWServerFormWidget();
    private TextField<String> searchField;
    private String searchText;
    private Button deleteServerButton;
    private CheckColumnConfig checkColumn;
    
    public CSWServerPaginationContainer() {
        super(true);
    }
    
    @Override
    public void setGridProperties() {
        super.widget.setSize(350, 350);
        super.widget.setAutoExpandColumn(GPCSWServerKeyValue.ALIAS.toString());
        
        super.panel.setLayout(new FlowLayout());
        this.createSearchComponent();
        this.createButtons();
        
        super.widget.addPlugin(this.checkColumn);
    }
    
    private void createSearchComponent() {
        searchField = new TextField<String>();
        searchField.setFieldLabel("Find Server");
        
        searchField.addKeyListener(new KeyListener() {
            
            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                     || (event.getKeyCode() == KeyCodes.KEY_DELETE))
                        && (searchField.getValue() == null)) {
                    reset();
                }
            }
            
            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER)) {
                    searchText = searchField.getValue() == null ? "" : searchField.getValue();
                    loader.load(0, 25);
                }
            }
        });
        
        FieldSet searchFieldSet = new FieldSet();
        searchFieldSet.setBorders(false);
        searchFieldSet.setSize(350, 40);
        
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(80);
        searchFieldSet.setLayout(layout);
        
        searchFieldSet.add(searchField);
        
        super.panel.add(searchFieldSet);
    }
    
    private void createButtons() {
        super.panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        Button newServerButton = new Button("New Server",
                new SelectionListener<ButtonEvent>() {
                    
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        executeNewServer();
                    }
                });
        newServerButton.setIcon(BasicWidgetResources.ICONS.done());
        newServerButton.setToolTip("Create a new CSW Server");
        super.panel.addButton(newServerButton);
        
        deleteServerButton = new Button("Delete Server",
                new SelectionListener<ButtonEvent>() {
                    
                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        executeDeleteServer();
                    }
                });
        deleteServerButton.setIcon(BasicWidgetResources.ICONS.delete());
        deleteServerButton.setToolTip("Delete a CSW Server selected");
        super.panel.addButton(deleteServerButton);
    }
    
    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        
        ColumnConfig aliasColumn = new ColumnConfig();
        aliasColumn.setId(GPCSWServerKeyValue.ALIAS.toString());
        aliasColumn.setHeader("Alias");
        aliasColumn.setFixed(true);
        aliasColumn.setResizable(false);
        configs.add(aliasColumn);
        
        ColumnConfig titleColumn = new ColumnConfig();
        titleColumn.setId(GPCSWServerKeyValue.TITLE.toString());
        titleColumn.setHeader("Title");
        titleColumn.setWidth(100);
        titleColumn.setFixed(true);
        titleColumn.setResizable(false);
        configs.add(titleColumn);
        
        checkColumn = new CheckColumnConfig();
        checkColumn.setId("cheked");
        checkColumn.setHeader("-"); // TODO DEL
        checkColumn.setWidth(30);
        checkColumn.setFixed(true);
        checkColumn.setResizable(false);
        
        CellEditor checkBoxEditor = new CellEditor(new CheckBox());
        checkColumn.setEditor(checkBoxEditor);
        
        configs.add(checkColumn);
        
        return new ColumnModel(configs);
    }
    
    @Override
    public void createStore() {
        super.toolBar = new PagingToolBar(super.getPageSize());
        
        super.proxy = new RpcProxy<PagingLoadResult<GPCSWServerBeanModel>>() {
            
            @Override
            protected void load(Object loadConfig,
                    AsyncCallback<PagingLoadResult<GPCSWServerBeanModel>> callback) {
                GPCatalogFinderRemoteImpl.Util.getInstance().searchCSWServers(
                        (PagingLoadConfig) loadConfig, searchText, callback);
            }
        };
        
        super.loader = new BasePagingLoader<PagingLoadResult<GPCSWServerBeanModel>>(
                proxy);
        super.loader.setRemoteSort(false);
        
        super.store = new ListStore<GPCSWServerBeanModel>(loader);
        
        super.store.setMonitorChanges(true);
        
        super.toolBar.bind(loader);
    }
    
    @Override
    public void setUpLoadListener() {
    }
    
    private void executeNewServer() {
        serverForm.showForm();
    }
    
    private void executeDeleteServer() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public void reset() {
        this.searchField.reset();
        this.store.removeAll();
        this.toolBar.clear();
//        this.selectButton.disable();
    }
}
