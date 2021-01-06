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
package org.geosdi.geoplatform.gui.client.widget.wfs;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.ModelKeyProvider;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.google.common.collect.Lists;
import org.geosdi.geoplatform.connector.wfs.response.AttributeDTO;
import org.geosdi.geoplatform.gui.client.MapRegistryEnum;
import org.geosdi.geoplatform.gui.client.model.binder.ILayerSchemaBinder;
import org.geosdi.geoplatform.gui.client.model.wfs.FeatureDetail;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.FeatureStatusBarEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.map.MapLayoutWidget;
import org.geosdi.geoplatform.gui.client.widget.wfs.statusbar.FeatureStatusBar.FeatureStatusBarType;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class ShowFeatureAttributesWidget extends GeoPlatformContentPanel {

    public static final String ID = WFSWidgetNames.SHOW_FEATURES.name();
    private static final ColumnModel mockColumnModel;
    private VectorFeature currentFeature;

    static {
        mockColumnModel = new ColumnModel(new ArrayList<ColumnConfig>());
    }

    private final GPEventBus bus;
    //
    @Inject
    private ILayerSchemaBinder layerSchemaBinder;
    private ListStore<FeatureDetail> store;
    private Grid<FeatureDetail> grid;
    private MapLayoutWidget mapLayoutWidget;

    @Inject
    public ShowFeatureAttributesWidget(GPEventBus bus) {
        super(TRUE);
        this.bus = bus;
        super.setMonitorWindowResize(TRUE);
        addWidgetListener(new WidgetListener() {

            @Override
            public void widgetResized(ComponentEvent ce) {

                if ((getWidth() > 0)) {
                    grid.setWidth(getWidth());
                }
            }

        });
        this.mapLayoutWidget = Registry.get(MapRegistryEnum.MAP_LAYOUT_WIDGET.toString());
    }

    public void reconfigureEditorGrid() {
        this.grid.reconfigure(store, this.prepareColumnModel());
    }

    @Override
    protected void beforeRender() {
        super.beforeRender();
        this.grid.reconfigure(store, this.prepareColumnModel());
    }

    @Override
    protected void afterRender() {
        super.afterRender();
        super.setId(ID);
    }

    @Override
    public void addComponent() {
        this.createStore();
        this.createGrid();
    }

    @Override
    public void initSize() {
    }

    protected void manageGridHeight() {
        this.grid.setHeight(super.getHeight() - 25);
    }


    @Override
    public void setPanelProperties() {

    }

    @Override
    public void reset() {
        store.removeAll();
        super.setVScrollPosition(0);
        this.mapLayoutWidget.eraseFeatures();
    }

    private void createStore() {
        store = new ListStore<FeatureDetail>();
        store.setKeyProvider(new ModelKeyProvider<FeatureDetail>() {

            @Override
            public String getKey(FeatureDetail model) {
                return model.getFeatureID();
            }
        });
    }

    private void createGrid() {
        this.grid = new Grid<FeatureDetail>(store, mockColumnModel);
        this.grid.setBorders(TRUE);
        this.grid.setStripeRows(TRUE);
        this.grid.setColumnLines(TRUE);
        this.grid.setColumnResize(TRUE);
        this.grid.setHeight(295);
        this.grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        grid.addListener(Events.CellClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                if(currentFeature != null)
                    mapLayoutWidget.eraseFeature(currentFeature);
                currentFeature = grid.getSelectionModel().getSelectedItem().getVectorFeature();
                mapLayoutWidget.drawFeature(currentFeature);
            }

        });

        super.add(this.grid);
    }

    private ColumnModel prepareColumnModel() {
        List<AttributeDTO> attributesDTO = this.layerSchemaBinder.getLayerSchemaDTO().getAttributes();
        List<ColumnConfig> configs = Lists.<ColumnConfig>newArrayListWithCapacity(attributesDTO.size());
        for (final AttributeDTO att : attributesDTO) {
            ColumnConfig valueColumn = new ColumnConfig();
            String name = att.getName();
            valueColumn.setId(name);
            valueColumn.setHeaderHtml(name);
            valueColumn.setWidth(100);
            valueColumn.setToolTip("Datatype: " + att.getType());
            configs.add(valueColumn);
        }
        return new ColumnModel(configs);
    }

    public void postInstances(List<FeatureDetail> instaces) {
        checkArgument(instaces != null, "The Parameter instances must not be null.");
        int numFeature = instaces.size();
        this.populateStore(instaces);
        grid.unmask();
        bus.fireEvent(new FeatureStatusBarEvent("Features Loaded " + numFeature, FeatureStatusBarType.STATUS_OK));
    }

    private void populateStore(List<FeatureDetail> attValues) {
        store.removeAll();
        store.add(attValues);
    }

    public void resetInstances() {
        this.reset();
    }

    public void maskAttributes(boolean mask) {
        if (mask) {
            this.grid.mask("Retrieving feature attributes");
        } else {
            this.grid.unmask();
        }
    }
}