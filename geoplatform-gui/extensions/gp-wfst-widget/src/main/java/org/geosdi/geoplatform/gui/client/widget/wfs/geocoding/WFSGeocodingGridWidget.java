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
package org.geosdi.geoplatform.gui.client.widget.wfs.geocoding;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.google.gwt.user.client.ui.Widget;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetConstants;
import org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocoding;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.GeocodingHandlerManager;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.IGeocodingGridHandler;
import org.geosdi.geoplatform.gui.client.puregwt.geocoding.event.AddMarkerEvent;
import org.geosdi.geoplatform.gui.client.service.response.FeatureCollectionResponse;
import org.geosdi.geoplatform.gui.client.service.response.FeatureDTO;
import org.geosdi.geoplatform.gui.client.widget.grid.GeoPlatformGridWidget;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.geosdi.geoplatform.gui.client.model.geocoding.WFSAddressGeocodingKeyValue.DESCRIPTION;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSGeocodingGridWidget extends GeoPlatformGridWidget<WFSAddressGeocoding> implements IGeocodingGridHandler {

    private final WFSTWidgetConstants wfstWidgetConstants;

    @Inject
    public WFSGeocodingGridWidget(WFSTWidgetConstants wfstWidgetConstants) {
        super(Boolean.TRUE);
        this.wfstWidgetConstants = wfstWidgetConstants;
        super.init();
        GeocodingHandlerManager.addHandler(IGeocodingGridHandler.TYPE, this);
    }

    public Widget getWidget(){
        return this.grid;
    }

    @Override
    public void populateGrid(FeatureCollectionResponse featureCollectionResponse) {
        clearStore();
        for(FeatureDTO featureDTO : featureCollectionResponse.featureCollectionDTOS){
            this.store.add(new WFSAddressGeocoding(featureDTO));
        }
        unMaskGrid();
    }

    @Override
    public void setGridProperties() {
        grid.setAutoExpandColumn(DESCRIPTION.getValue());
        grid.setBorders(false);
        grid.getView().setForceFit(true);
        grid.setLoadMask(true);
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        grid.addListener(Events.CellClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                GeocodingHandlerManager.fireEvent(new AddMarkerEvent(grid.getSelectionModel().getSelectedItem()));
            }

        });
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        ColumnConfig column = new ColumnConfig();
        column.setId(DESCRIPTION.getValue());
        column.setHeaderHtml(
                this.wfstWidgetConstants.locationLabel());
        column.setWidth(240);
        configs.add(column);
        return new ColumnModel(configs);
    }

    public void maskGrid() {
        if (this.grid.getView().getBody() != null) {
            this.grid.getView().getBody().mask(
                    this.wfstWidgetConstants.loadingLabel());
        }
    }

    public void unMaskGrid() {
        if (this.grid.getView().getBody() != null) {
            this.grid.getView().getBody().unmask();
        }
    }

    public void clearStore(){
        this.store.removeAll();
    }

    @Override
    public void updateHeight(int height) {
        grid.setHeight(height);
    }

    @Override
    public void createStore() {
        store = new ListStore<WFSAddressGeocoding>();
    }
}