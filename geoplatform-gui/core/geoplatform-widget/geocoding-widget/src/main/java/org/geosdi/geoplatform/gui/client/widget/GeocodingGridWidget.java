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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.client.model.GeocodingKeyValue;
import org.geosdi.geoplatform.gui.client.model.geocoding.GPGeocodingServiceBean;
import org.geosdi.geoplatform.gui.client.model.geocoding.GPGeocodingSeviceKeyValue;
import org.geosdi.geoplatform.gui.client.service.GeocodingRemote;
import org.geosdi.geoplatform.gui.client.service.GeocodingRemoteAsync;
import org.geosdi.geoplatform.gui.client.widget.grid.GeoPlatformGridWidget;
import org.geosdi.geoplatform.gui.client.widget.map.ReverseGeoCoderProvider;
import org.geosdi.geoplatform.gui.client.widget.map.event.geocoding.GeocodingSearchEventHandler;
import org.geosdi.geoplatform.gui.client.widget.map.event.geocoding.RegisterGeocodingLocationEvent;
import org.geosdi.geoplatform.gui.client.widget.map.marker.puregwt.event.GPGeocodingRemoveMarkerEvent;
import org.geosdi.geoplatform.gui.configuration.geocoding.plugin.IGPGeocoderPlugin;
import org.geosdi.geoplatform.gui.configuration.grid.IGeoPlatformGrid;
import org.geosdi.geoplatform.gui.configuration.map.client.GPCoordinateReferenceSystem;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.geocoder.GeoCoderPerformOperation;
import org.geosdi.geoplatform.gui.puregwt.geocoding.GPGeocodingHandlerManager;

/**
 * 
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GeocodingGridWidget extends GeoPlatformGridWidget<GeocodingBean>
        implements IGPGeocoderPlugin<FormPanel> {

    private FormPanel formPanel;
    private TextField<String> search;
    private ComboBox<GPGeocodingServiceBean> geocodingServiceCombo;
    private FieldSet searchFieldSet;
    private FieldSet locations;
    private GeocodingPerformOperation operation = new GeocodingPerformOperation();
    private GPGeocodingRemoveMarkerEvent event = new GPGeocodingRemoveMarkerEvent();

    public GeocodingGridWidget() {
        super(false);
        initFormPanel();
    }

    private void initFormPanel() {
        formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);
        formPanel.setFrame(true);
        formPanel.setLayout(new FlowLayout());

        searchFieldSet = new FieldSet();
        searchFieldSet.setHeading("Search");

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(60);
        searchFieldSet.setLayout(layout);

        addSearchTextField();

        addComboGeocodingServices();

        formPanel.add(searchFieldSet);

        locations = new FieldSet();
        locations.setHeading("Locations");
        locations.setCollapsible(true);

        locations.add(this.grid);

        formPanel.add(locations);
    }

    private void addSearchTextField() {
        search = new TextField<String>();
        search.setFieldLabel("Find");

        search.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyUp(ComponentEvent event) {
                if (((event.getKeyCode() == KeyCodes.KEY_BACKSPACE)
                        || (event.getKeyCode() == KeyCodes.KEY_DELETE))
                        && (search.getValue() == null)) {
                    removeMarkersOnMap();
                    cleanUpTheStore();
                }
            }

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if ((event.getKeyCode() == KeyCodes.KEY_ENTER)
                        && (!search.getValue().equals(""))) {
                    ComboBox<GPGeocodingServiceBean> comboBox = (ComboBox<GPGeocodingServiceBean>) searchFieldSet.getItemByItemId("geocodingServiceSelector");
                    GPGeocodingServiceBean selectedBean = comboBox.getValue();
                    String selectedGeocodingService = selectedBean.getGeocodingService();
                    operation.onBeginGeocodingSearch(search.getValue());
                }
            }
        });

        searchFieldSet.add(search);
    }

    private void addComboGeocodingServices() {
        List<GPGeocodingServiceBean> serviceList = new ArrayList<GPGeocodingServiceBean>();
        serviceList.add(new GPGeocodingServiceBean(
                ReverseGeoCoderProvider.GOOGLE));
        serviceList.add(new GPGeocodingServiceBean(
                ReverseGeoCoderProvider.YAHOO));

        ListStore<GPGeocodingServiceBean> serviceStore = new ListStore<GPGeocodingServiceBean>();
        serviceStore.add(serviceList);

        geocodingServiceCombo = new ComboBox<GPGeocodingServiceBean>();
        geocodingServiceCombo.setId("geocodingServiceSelector");
        geocodingServiceCombo.setFieldLabel("Name");
        geocodingServiceCombo.setDisplayField(GPGeocodingSeviceKeyValue.GEOCODINGSERVICE.getValue());
        geocodingServiceCombo.setToolTip("Geocoding service selector");
        geocodingServiceCombo.setForceSelection(true);
        geocodingServiceCombo.setEditable(false);
        geocodingServiceCombo.setStore(serviceStore);
        geocodingServiceCombo.setTypeAhead(true);
        geocodingServiceCombo.setTriggerAction(TriggerAction.ALL);

        searchFieldSet.add(geocodingServiceCombo);

        geocodingServiceCombo.addListener(Events.Select,
                new Listener<FieldEvent>() {

                    @Override
                    public void handleEvent(FieldEvent fe) {
                        if (search.getValue() != null) {
                            ComboBox<GPGeocodingServiceBean> comboBox = (ComboBox<GPGeocodingServiceBean>) fe.getComponent();
                            GPGeocodingServiceBean selectedBean = comboBox.getValue();
                            String selectedGeocodingService = selectedBean.getGeocodingService();
                            operation.onBeginGeocodingSearch(search.getValue());
                        }
                    }
                });

        geocodingServiceCombo.setValue(new GPGeocodingServiceBean(
                ReverseGeoCoderProvider.GOOGLE));
    }

    @Override
    public void setGridProperties() {
        grid.setAutoExpandColumn(GeocodingKeyValue.DESCRIPTION.getValue());
        grid.setBorders(false);

        grid.getView().setForceFit(true);
        grid.setLoadMask(true);

        grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        grid.addListener(Events.CellDoubleClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                GPGeocodingHandlerManager.fireEvent(new RegisterGeocodingLocationEvent(grid.getSelectionModel().getSelectedItem(),
                        GPCoordinateReferenceSystem.WGS_84, geocodingServiceCombo.getValue().getProvider()));
            }
        });
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId(GeocodingKeyValue.DESCRIPTION.getValue());
        column.setHeader("Location");
        column.setWidth(240);
        configs.add(column);

        return new ColumnModel(configs);
    }

    @Override
    public void createStore() {
        store = new ListStore<GeocodingBean>();
    }

    /**
     * Clean The Store
     */
    public void cleanUpTheStore() {
        if (this.grid.getView().getBody().isMasked()) {
            unMaskGrid();
        }
        this.store.removeAll();
    }

    /**
     * Create mask effect on Grid
     */
    public void maskGrid() {
        if (this.grid.getView().getBody() != null) {
            this.grid.getView().getBody().mask("Loading Locations");
        }
    }

    /**
     * Remove mask effect from the Grid
     */
    public void unMaskGrid() {
        if (this.grid.getView().getBody() != null) {
            this.grid.getView().getBody().unmask();
        }
    }

    /**
     * 
     * @param beans
     *            {@link ArrayList} of GeocodingBean to fill the Store
     */
    public void fillStore(ArrayList<GeocodingBean> beans) {
        this.store.add(beans);
    }

    /**
     * @return the formPanel
     */
    public FormPanel getFormPanel() {
        return formPanel;
    }

    private void removeMarkersOnMap() {
        GPGeocodingHandlerManager.fireEvent(event);
    }

    @Override
    public FormPanel getWidget() {
        return this.getFormPanel();
    }

    @Override
    public void widgetResized(int dimension) {
        this.grid.setHeight(dimension);
    }

    /**
     * Method to set With for Field Set for Advanced Plugin Geocoder
     * 
     * @param size
     */
    public void setFieldsSetWith(int size) {
        this.searchFieldSet.setWidth(size);
        this.locations.setWidth(size);
    }

    /**
     * Internal Class for Business Logic
     * 
     */
    private class GeocodingPerformOperation extends GeoCoderPerformOperation<GeocodingGridWidget> implements
            GeocodingSearchEventHandler {

        private GeocodingRemoteAsync geocodingService = GeocodingRemote.Util.getInstance();

        public GeocodingPerformOperation() {
            super(GeocodingGridWidget.this);
            GPGeocodingHandlerManager.addHandler(
                    GeocodingSearchEventHandler.TYPE, this);
        }

        /**
         * Invoke Geocoding Service for Geo-Location
         * 
         * @param event
         */
        @Override
        public void onBeginGeocodingSearch(String searchValue) {
            checkWidgetStatus();
            findLocations(searchValue);
        }

        /**
         *
         */
        @Override
        public void checkWidgetStatus() {
            GeoPlatformMessage.checkGridWidgetStatus(
                    (IGeoPlatformGrid) gridWidget,
                    "Geocoding - Service",
                    "Geocoding Service is demanding too much time, probably "
                    + "the connection problem, do you want to stop it?");
        }

        /**
         * 
         * @param location
         *            to find
         */
        @Override
        public void findLocations(String location) {
            ComboBox<GPGeocodingServiceBean> comboBox = (ComboBox<GPGeocodingServiceBean>) searchFieldSet.getItemByItemId("geocodingServiceSelector");
            GPGeocodingServiceBean selectedBean = comboBox.getValue();
            String provider = selectedBean.getGeocodingService();

            gridWidget.maskGrid();
            cleanStore();
            this.geocodingService.findLocations(location, provider,
                    new AsyncCallback<ArrayList<GeocodingBean>>() {

                        @Override
                        public void onSuccess(ArrayList<GeocodingBean> result) {
                            ArrayList<GeocodingBean> beans = (ArrayList<GeocodingBean>) result;
                            gridWidget.unMaskGrid();
                            if (result != null && result.size() > 0) {
                                gridWidget.fillStore(beans);
                            } else {
                                GeoPlatformMessage.alertMessage("Geocoding - Service",
                                        "There are no results for your search.");
                            }

                        }

                        @Override
                        public void onFailure(Throwable caught) {
                            gridWidget.unMaskGrid();
                            grid.getView().refresh(false);
                            GeoPlatformMessage.errorMessage("Geocoding - Service",
                                    "There is a problem with Geocoding Service Provider");
                        }
                    });
        }

        @Override
        public void onSearch(String value) {
            search.setValue(value);
            operation.onBeginGeocodingSearch(value);
        }
    }
}
