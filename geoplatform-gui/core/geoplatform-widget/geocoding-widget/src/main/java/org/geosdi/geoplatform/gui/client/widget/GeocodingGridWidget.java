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

import java.util.ArrayList;
import java.util.List;

import org.geosdi.geoplatform.gui.client.GeocodingEvents;
import org.geosdi.geoplatform.gui.client.model.GeocodingBean;
import org.geosdi.geoplatform.gui.client.model.GeocodingKeyValue;
import org.geosdi.geoplatform.gui.client.widget.grid.GeoPlatformGridWidget;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.event.dom.client.KeyCodes;

/**
 * @author giuseppe
 * 
 */
public class GeocodingGridWidget extends GeoPlatformGridWidget<GeocodingBean> {

    private FormPanel formPanel;
    private TextField<String> search;

    public GeocodingGridWidget() {
        super(false);
        initFormPanel();
    }

    private void initFormPanel() {
        // TODO Auto-generated method stub
        formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);
        formPanel.setFrame(true);
        formPanel.setLayout(new FlowLayout());

        FieldSet searchFieldSet = new FieldSet();
        searchFieldSet.setHeading("Search");

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(60);
        searchFieldSet.setLayout(layout);

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
                if ((event.getKeyCode() == 13)
                        && (!search.getValue().equals(""))) {
                    Dispatcher.forwardEvent(
                            GeocodingEvents.BEGIN_GEOCODING_SEARCH,
                            search.getValue());
                }
            }
        });

        BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
        data.setMargins(new Margins(5, 5, 5, 5));

        searchFieldSet.add(search, data);

        formPanel.add(searchFieldSet);

        FieldSet locations = new FieldSet();
        locations.setHeading("Locations");
        locations.setCollapsible(true);

        locations.add(this.grid);

        formPanel.add(locations);
    }

    @Override
    public void setGridProperties() {
        // TODO Auto-generated method stub
        grid.setAutoExpandColumn(GeocodingKeyValue.DESCRIPTION.getValue());
        grid.setBorders(false);

        grid.getView().setForceFit(true);
        grid.setLoadMask(true);

        grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        grid.addListener(Events.CellDoubleClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                Dispatcher.forwardEvent(
                        GeoPlatformEvents.REGISTER_GEOCODING_LOCATION,
                        grid.getSelectionModel().getSelectedItem());
            }
        });
    }

    @Override
    public ColumnModel prepareColumnModel() {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
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
        this.grid.getView().getBody().mask("Loading Locations");
    }

    /**
     * Remove mask effect from the Grid
     */
    public void unMaskGrid() {
        this.grid.getView().getBody().unmask();
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
        Dispatcher.forwardEvent(GeoPlatformEvents.RemoveMarker);
    }
}
