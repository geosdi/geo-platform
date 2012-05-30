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
package org.geosdi.geoplatform.gui.client.widget.components.filters.container;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.tips.QuickTip;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.puregwt.event.CatalogStatusBarEvent;
import org.geosdi.geoplatform.gui.client.puregwt.handler.LoadFirstServersHandler;
import org.geosdi.geoplatform.gui.client.widget.components.form.CSWServerFormWidget;
import org.geosdi.geoplatform.gui.client.widget.statusbar.GPCatalogStatusBar.GPCatalogStatusBarType;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.containers.pagination.GeoPlatformPagingToolBar;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.impl.containers.pagination.grid.GridLayoutPaginationContainer;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel;
import org.geosdi.geoplatform.gui.model.server.GPCSWServerBeanModel.GPCSWServerKeyValue;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.server.gwt.GPCatalogFinderRemoteImpl;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class CSWServerPaginationContainer
        extends GridLayoutPaginationContainer<GPCSWServerBeanModel>
        implements LoadFirstServersHandler {

    private CatalogFinderBean catalogFinder;
    private EventBus bus;
    private ActionEnableEvent enableEvent = new ActionEnableEvent(false);
    //
    private CSWServerFormWidget serverForm;
    private CheckBoxSelectionModel<GPCSWServerBeanModel> sm;
    private TextField<String> searchField;
    private Button deleteServerButton;

    @Inject
    public CSWServerPaginationContainer(CatalogFinderBean theCatalogFinder,
            EventBus theBus) {
        super(true);
        catalogFinder = theCatalogFinder;
        bus = theBus;
        serverForm = new CSWServerFormWidget(this, bus);
        
        bus.addHandler(LoadFirstServersHandler.TYPE, this);
    }

    @Override
    public void setGridProperties() {
        super.widget.setSize(400, 350);
        super.widget.setAutoExpandColumn(GPCSWServerKeyValue.ALIAS.toString());

        super.panel.setLayout(new FlowLayout());
        this.createSearchComponent();
        this.createButtons();

        super.widget.setSelectionModel(sm);
        super.widget.addPlugin(sm);

        // ADD URL tip
        new QuickTip(super.widget);
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
                    loader.load(0, getPageSize());
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
                        serverForm.showForm();
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

        deleteServerButton.disable();
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig aliasColumn = new ColumnConfig();
        aliasColumn.setId(GPCSWServerKeyValue.ALIAS.toString());
        aliasColumn.setHeader("Alias");
        aliasColumn.setFixed(true);
        aliasColumn.setResizable(false);
        aliasColumn.setRenderer(new GridCellRenderer<GPCSWServerBeanModel>() {

            @Override
            public Object render(GPCSWServerBeanModel model, String property,
                    ColumnData config, int rowIndex, int colIndex,
                    ListStore<GPCSWServerBeanModel> store,
                    Grid<GPCSWServerBeanModel> grid) {
                String url = model.getUrlServer();
                return "<div qtitle='Server URL'"
                        + " qtip='" + url + "'>" + model.getAlias() + "</div>";
            }
        });
        configs.add(aliasColumn);

        ColumnConfig titleColumn = new ColumnConfig();
        titleColumn.setId(GPCSWServerKeyValue.TITLE.toString());
        titleColumn.setHeader("Title");
        titleColumn.setWidth(100);
        titleColumn.setFixed(true);
        titleColumn.setResizable(false);
        configs.add(titleColumn);

        sm = new CheckBoxSelectionModel<GPCSWServerBeanModel>();
        sm.setSelectionMode(Style.SelectionMode.SINGLE);
        sm.addSelectionChangedListener(new SelectionChangedListener<GPCSWServerBeanModel>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<GPCSWServerBeanModel> se) {
                GPCSWServerBeanModel selectedServer = se.getSelectedItem();
                if (selectedServer == null) {
                    deleteServerButton.disable();
                } else {
                    deleteServerButton.enable();
                    catalogFinder.setServerID(selectedServer.getId());
                }

                enableEvent.setEnabled(deleteServerButton.isEnabled());
                bus.fireEvent(enableEvent);
            }
        });

        ColumnConfig checkColumn = sm.getColumn();
        checkColumn.setId("cheked");
        checkColumn.setWidth(30);
        checkColumn.setFixed(true);
        checkColumn.setResizable(false);
        configs.add(checkColumn);

        return new ColumnModel(configs);
    }

    @Override
    public void createStore() {
        super.toolBar = new GeoPlatformPagingToolBar(super.getPageSize());

        super.proxy = new RpcProxy<PagingLoadResult<GPCSWServerBeanModel>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<GPCSWServerBeanModel>> callback) {
                String searchText = searchField.getValue() == null ? "" : searchField.getValue();
                GPCatalogFinderRemoteImpl.Util.getInstance().searchCSWServers(
                        (PagingLoadConfig) loadConfig, searchText, callback);
            }
        };

        super.loader = new BasePagingLoader<PagingLoadResult<GPCSWServerBeanModel>>(
                proxy);
        super.loader.setRemoteSort(false);

        super.store = new ListStore<GPCSWServerBeanModel>(loader);

        super.toolBar.bind(loader);
    }

    @Override
    protected void onLoaderLoad(LoadEvent le) {
        bus.fireEvent(new CatalogStatusBarEvent("Servers correctly loaded",
                GPCatalogStatusBarType.STATUS_OK));
        widget.unmask();
    }

    @Override
    protected void onLoaderLoadException(LoadEvent le) {
        if (le.exception instanceof GeoPlatformException) {
            // No result
            System.out.println("\n*** " + le.exception.getMessage()); // TODO logger
            bus.fireEvent(new CatalogStatusBarEvent("There are no servers",
                    GPCatalogStatusBarType.STATUS_NOT_OK));
        } else {
            String errorMessage = "The services are down, report to the administator";
            GeoPlatformMessage.errorMessage("Connection error", errorMessage);
            bus.fireEvent(new CatalogStatusBarEvent(errorMessage,
                    GPCatalogStatusBarType.STATUS_ERROR));
        }
        resetGrid();
        widget.unmask();
    }

    @Override
    protected void onLoaderBeforeLoad(LoadEvent le) {
        super.onLoaderBeforeLoad(le);
        widget.mask("Loading CSW servers");
    }

    private void executeDeleteServer() {
        super.widget.mask("Deleting server");

        final GPCSWServerBeanModel selectedServer = sm.getSelectedItem();
        GPCatalogFinderRemoteImpl.Util.getInstance().deleteServerCSW(
                selectedServer.getId(),
                new AsyncCallback<Boolean>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        System.out.println("\n*** Error on deleting server: " + caught.getMessage()); // TODO logger
                        bus.fireEvent(new CatalogStatusBarEvent("Error on deleting server",
                                GPCatalogStatusBarType.STATUS_ERROR));

                        widget.unmask();
                    }

                    @Override
                    public void onSuccess(Boolean result) {
                        store.remove(selectedServer);
                        bus.fireEvent(new CatalogStatusBarEvent("Server correctly deleted",
                                GPCatalogStatusBarType.STATUS_OK));
                        widget.unmask();
                    }
                });
    }

    @Override
    public void loadFirstServers() {
        loader.load(0, super.getPageSize());
    }

    public void reset() {
        this.searchField.reset();
        this.resetGrid();
    }

    private void resetGrid() {
        this.store.removeAll();
        this.toolBar.clear();
    }

    /**
     * TODO : MAKE IMPROVEMENT FOR PAGINATION HERE
     *
     * Add the server to store.
     *
     * @param server to add
     */
    public void addNewServer(GPCSWServerBeanModel server) {
        this.store.insert(server, 0);
    }

    /**
     * Verify if the server URL is already exist into the store.
     *
     * @param url server URL to check
     *
     * @return server founded or null
     */
    public GPCSWServerBeanModel containsServer(String url) {
        for (GPCSWServerBeanModel server : store.getModels()) {
            if (server.getUrlServer().equalsIgnoreCase(url)) {
                return server;
            }
        }
        return null;
    }
}
