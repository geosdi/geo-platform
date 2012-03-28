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
package org.geosdi.geoplatform.gui.client.widget.components.search.pagination;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.LoadEvent;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.model.SummaryRecord;
import org.geosdi.geoplatform.gui.client.model.SummaryRecord.SummaryRecordKeyValue;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.global.GeoPlatformException;
import org.geosdi.geoplatform.gui.impl.containers.pagination.grid.GridLayoutPaginationContainer;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.server.gwt.GPCatalogFinderRemoteImpl;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class SummaryRecordsContainer
        extends GridLayoutPaginationContainer<SummaryRecord> {

    private CatalogFinderBean catalogFinder;
    //
    private CheckBoxSelectionModel<SummaryRecord> sm;
    private RowExpander rowExpander;

    @Inject
    public SummaryRecordsContainer(CatalogFinderBean theCatalogFinder) {
        super(true, 10);
        catalogFinder = theCatalogFinder;

        super.setWidth(550);
        super.setStyleAttribute("padding-top", "10px");
        super.setStyleAttribute("padding-bottom", "10px");
    }

    @Override
    public void setGridProperties() {
        super.widget.setHeight(250);
        super.widget.getView().setForceFit(true);
//        super.widget.setLoadMask(true);

        super.widget.setSelectionModel(this.sm);

        super.widget.addPlugin(this.rowExpander);
        super.widget.addPlugin(this.sm);
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        XTemplate tpl = XTemplate.create(
                "<p><b>Abstract:</b> {ABSTRACT_TEXT}</p><br>"
                + "<p><b>Subjects:</b><br></p>"
                + "<tpl for=\"SUBJECTS\">"
                + "<div>{.}</div>"
                + "</tpl>");

        rowExpander = new RowExpander(tpl);

        configs.add(rowExpander);

        ColumnConfig titleColumn = new ColumnConfig();
        titleColumn.setId(SummaryRecordKeyValue.TITLE.toString());
        titleColumn.setHeader("Title");
        titleColumn.setWidth(490);
        titleColumn.setFixed(true);
        titleColumn.setResizable(false);
        configs.add(titleColumn);

        this.sm = new CheckBoxSelectionModel<SummaryRecord>();
        sm.setSelectionMode(SelectionMode.MULTI);
        configs.add(sm.getColumn());


        return new ColumnModel(configs);
    }

    @Override
    public void createStore() {
        super.toolBar = new PagingToolBar(super.getPageSize());

        super.proxy = new RpcProxy<PagingLoadResult<SummaryRecord>>() {

            @Override
            protected void load(Object loadConfig,
                    AsyncCallback<PagingLoadResult<SummaryRecord>> callback) {
                GPCatalogFinderRemoteImpl.Util.getInstance().searchSummaryRecords(
                        (PagingLoadConfig) loadConfig, catalogFinder, callback);
            }
        };

        super.loader = new BasePagingLoader<PagingLoadResult<SummaryRecord>>(proxy);
        super.loader.setRemoteSort(false);

        super.store = new ListStore<SummaryRecord>(loader);
//        super.store.setMonitorChanges(true);

        super.toolBar.bind(loader);
        super.toolBar.disable();
    }

    @Override
    public void setUpLoadListener() {
        super.loader.addLoadListener(new LoadListener() {

            @Override
            public void loaderBeforeLoad(LoadEvent le) {
                widget.mask("Loading Summary Records");
            }

            @Override
            public void loaderLoad(LoadEvent le) {
                if (!toolBar.isEnabled()) {
                    toolBar.enable();
                }
                widget.unmask();
                // TODO Set status message on main windows
                System.out.println("\n*** Summary Records correctly loaded ***");
            }

            @Override
            public void loaderLoadException(LoadEvent le) {
                if (le.exception instanceof GeoPlatformException) { // TODO If server not found?
                    // No result
                    System.out.println("\n*** " + le.exception.getMessage());
                } else {
                    GeoPlatformMessage.errorMessage("Connection error",
                            "The services are down, report to the administator");
                }
                reset();
                widget.unmask();
            }
        });
    }

    public void searchSummaryRecords() {
        super.loader.load();
    }

    public void reset() {
        this.store.removeAll();
        this.toolBar.clear();
        this.toolBar.disable();
    }
}
