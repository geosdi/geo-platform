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
package org.geosdi.geoplatform.gui.client.widget.components.search.pagination;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.core.XTemplate;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.RowExpander;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.RpcTokenException;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.action.menu.ShowFullMetadataAction;
import org.geosdi.geoplatform.gui.client.i18n.CatalogFinderConstants;
import org.geosdi.geoplatform.gui.client.model.AbstractRecord.RecordKeyValue;
import org.geosdi.geoplatform.gui.client.model.FullRecord;
import org.geosdi.geoplatform.gui.client.puregwt.event.StatusWidgetEvent;
import org.geosdi.geoplatform.gui.client.service.GPCatalogFinderRemote;
import org.geosdi.geoplatform.gui.client.service.GPCatalogFinderRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.statusbar.GPCatalogStatusBar.GPCatalogStatusBarType;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.containers.pagination.grid.GridLayoutPaginationContainer;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.geosdi.geoplatform.gui.puregwt.grid.event.DeselectGridRecordHandler;
import org.geosdi.geoplatform.gui.puregwt.layers.LayerHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.progressbar.layers.event.DisplayLayersProgressBarEvent;
import org.geosdi.geoplatform.gui.responce.CatalogFinderBean;
import org.geosdi.geoplatform.gui.service.gwt.xsrf.GPXsrfTokenService;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class RecordsContainer extends GridLayoutPaginationContainer<FullRecord>
        implements RecordsContainerSelectionListener, DeselectGridRecordHandler {

    private static final XsrfTokenServiceAsync xsrf = GPXsrfTokenService.Util.getInstance();
    private static final GPCatalogFinderRemoteAsync catalogFinderRemote = GPCatalogFinderRemote.Util.getInstance();
    //
    private final GPEventBus bus;
    private final CatalogFinderBean catalogFinder;
    private CheckBoxSelectionModel<FullRecord> selectionModel;
    private RowExpander rowExpander;
    private boolean selectionContainer;
    private final CatalogMetadataSelectionManager metadataSelection;
    private final DisplayLayersProgressBarEvent hideProgressBar = new DisplayLayersProgressBarEvent(
            false);

    @Inject
    public RecordsContainer(CatalogFinderBean theCatalogFinder,
            MetadataSelectionManager theMetadataSelector,
            GPEventBus theBus) {
        super(true, 10);
        super.setWidth(550);
        super.setStyleName("records-Container");

        this.catalogFinder = theCatalogFinder;
        this.metadataSelection = theMetadataSelector;
        this.bus = theBus;

        LayerHandlerManager.addHandler(DeselectGridRecordHandler.TYPE, this);
    }

    @Override
    public void setGridProperties() {
        super.widget.setHeight(250);
        super.widget.getView().setForceFit(true);

        super.widget.setSelectionModel(this.selectionModel);

        super.widget.addPlugin(this.rowExpander);
        super.widget.addPlugin(this.selectionModel);

        super.widget.setContextMenu(this.createRecordContextMenu());
    }

    private Menu createRecordContextMenu() {
        ShowFullMetadataAction showFullMetadata = new ShowFullMetadataAction(
                this);

        MenuItem fullMetadata = new MenuItem();
        fullMetadata.addSelectionListener(showFullMetadata);
        fullMetadata.setText(showFullMetadata.getTitle());
        fullMetadata.setIcon(showFullMetadata.getImage());

        Menu menu = new Menu();
        menu.add(fullMetadata);

        return menu;
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = Lists.<ColumnConfig>newArrayList();

        StringBuilder templateBuilder = new StringBuilder();
        templateBuilder.append("<p><b>");
        templateBuilder.append(
                CatalogFinderConstants.INSTANCE.RecordsContainer_xTemplateAbstractText());
        templateBuilder.append(":</b> {ABSTRACT_TEXT}</p><br>");
        templateBuilder.append("<p><b>");
        templateBuilder.append(
                CatalogFinderConstants.INSTANCE.RecordsContainer_xTemplateKeywordsText());
        templateBuilder.append(":</b><br></p>");
        templateBuilder.append("<tpl for=\"SUBJECTS\">");
        templateBuilder.append("<div>{.}</div>");
        templateBuilder.append("</tpl>");

        XTemplate tpl = XTemplate.create(templateBuilder.toString());

        rowExpander = new RowExpander(tpl);
        configs.add(rowExpander);

        ColumnConfig titleColumn = new ColumnConfig();
        titleColumn.setId(RecordKeyValue.TITLE.toString());
        titleColumn.setHeaderHtml(
                CatalogFinderConstants.INSTANCE.RecordsContainer_titleColumnHeaderText());
        titleColumn.setWidth(490);
        titleColumn.setFixed(true);
        titleColumn.setResizable(false);
        configs.add(titleColumn);

        selectionModel = new CheckBoxSelectionModel<FullRecord>();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        configs.add(selectionModel.getColumn());

        return new ColumnModel(configs);
    }

    @Override
    public void createStore() {
        super.proxy = new RpcProxy<PagingLoadResult<FullRecord>>() {

            @Override
            protected void load(final Object loadConfig,
                    final AsyncCallback<PagingLoadResult<FullRecord>> callback) {
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
                        ((HasRpcToken) catalogFinderRemote).setRpcToken(token);
                        catalogFinderRemote.searchFullRecords(
                                (PagingLoadConfig) loadConfig, catalogFinder,
                                callback);
                    }

                });
            }

        };

        super.loader = new BasePagingLoader<PagingLoadResult<FullRecord>>(proxy);
        super.loader.setRemoteSort(false);

        super.store = new ListStore<FullRecord>(loader);
    }

    @Override
    protected void onLoaderBeforeLoad(LoadEvent le) {
        this.metadataSelection.clearRecordsExcludedList();
        widget.mask(
                CatalogFinderConstants.INSTANCE.RecordsContainer_gridLoadingMaskText());
    }

    @Override
    protected void onLoaderLoad(LoadEvent le) {
        BasePagingLoadResult result = (BasePagingLoadResult) le.getData();

        if (result.getTotalLength() == 0) {
            getBus().fireEvent(new StatusWidgetEvent(
                    CatalogFinderConstants.INSTANCE.RecordsContainer_eventNoRecordsLoaderText(),
                    GPCatalogStatusBarType.STATUS_NOT_OK));
        } else {
            getBus().fireEvent(new StatusWidgetEvent(
                    CatalogFinderConstants.INSTANCE.RecordsContainer_eventRecordsCorrectlyLoaderText(),
                    GPCatalogStatusBarType.STATUS_OK));
        }

        widget.unmask();
    }

    @Override
    protected void onLoaderLoadException(LoadEvent le) {
        System.out.println("\n*** " + le.exception); // TODO logger
        GeoPlatformMessage.errorMessage(
                CatalogFinderConstants.INSTANCE.RecordsContainer_errorServiceDownTitleText(),
                CatalogFinderConstants.INSTANCE.RecordsContainer_errorServiceDownBodyText());
        getBus().fireEvent(new StatusWidgetEvent(
                CatalogFinderConstants.INSTANCE.RecordsContainer_errorServiceDownBodyText(),
                GPCatalogStatusBarType.STATUS_ERROR));

        this.reset();
        widget.unmask();
    }

    public void searchRecords() {
        super.loader.load(0, super.getPageSize());
    }

    @Override
    public void reset() {
        super.reset();
        this.metadataSelection.clearRecordsExcludedList();
    }

    @Override
    protected void afterRender() {
        super.afterRender();
        this.addRecordsContainerSelectionListener();
    }

    @Override
    public void addRecordsContainerSelectionListener() {
        if (selectionContainer) {
            this.metadataSelection.bindContainer(this);

            this.selectionModel.addListener(Events.BeforeSelect,
                    new Listener<SelectionEvent<FullRecord>>() {

                        @Override
                        public void handleEvent(SelectionEvent<FullRecord> se) {
                            FullRecord record = se.getModel();
                            if (!(record.isPossibleAddOnTreeOperation())) {
                                se.setCancelled(true);

                                metadataSelection.addRecordExcluded(record);
                                metadataSelection.fireCatalogRecordToolTip(
                                        record);
                            }
                        }

                    });

            this.selectionModel.addSelectionChangedListener(
                    new SelectionChangedListener<FullRecord>() {

                        @Override
                        public void selectionChanged(
                                SelectionChangedEvent<FullRecord> se) {
                                    metadataSelection.manageSelectionChanged(se);
                                }

                    });
        }
    }

    @Override
    public void setSelectionContainer(boolean enable) {
        this.selectionContainer = enable;
    }

    public List<FullRecord> getSelectedRecords() {
        return selectionModel.getSelectedItems();
    }

    public FullRecord getSelectedRecord() {
        return selectionModel.getSelectedItem();
    }

    public CatalogMetadataSelectionManager getMetadataSelection() {
        return metadataSelection;
    }

    @Override
    public void deselectRecords() {
        super.widget.getSelectionModel().deselectAll();

        LayerHandlerManager.fireEvent(hideProgressBar);
    }

    /**
     * @return the bus
     */
    public GPEventBus getBus() {
        return bus;
    }

}