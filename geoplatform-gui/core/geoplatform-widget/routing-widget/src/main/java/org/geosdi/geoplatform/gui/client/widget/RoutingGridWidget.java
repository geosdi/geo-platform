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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import java.util.ArrayList;
import java.util.List;
import org.geosdi.geoplatform.gui.client.i18n.RoutingModuleConstants;
import org.geosdi.geoplatform.gui.client.model.Directions;
import org.geosdi.geoplatform.gui.client.model.Directions.DirectionsKeyValue;
import org.geosdi.geoplatform.gui.client.widget.grid.GeoPlatformGridWidget;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class RoutingGridWidget extends GeoPlatformGridWidget<Directions> {

    private FieldSet directionsField;

    public RoutingGridWidget() {
        super(false);
        initWidget();
    }

    private void initWidget() {
        directionsField = new FieldSet();
        directionsField.setHeadingHtml(RoutingModuleConstants.INSTANCE.
                RoutingGridWidget_directionsText());
        directionsField.setCollapsible(false);

        directionsField.add(this.grid);
    }

    @Override
    public void setGridProperties() {
        grid.setBorders(false);

        grid.getView().setForceFit(true);
        grid.setLoadMask(true);

        grid.setAutoExpandColumn(DirectionsKeyValue.ROUTE.getValue());
    }

    @Override
    public ColumnModel prepareColumnModel() {
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

        ColumnConfig dirColumn = new ColumnConfig(
                DirectionsKeyValue.ROUTE.getValue(),
                RoutingModuleConstants.INSTANCE.RoutingGridWidget_directionsText(), 100);
        dirColumn.setMenuDisabled(true);
        dirColumn.setSortable(false);

        columns.add(dirColumn);

        return new ColumnModel(columns);
    }

    @Override
    public void createStore() {
        this.store = new ListStore<Directions>();
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
        this.grid.getView().getBody().mask(RoutingModuleConstants.INSTANCE.RoutingGridWidget_loadingMaskText());
    }

    /**
     * Remove mask effect from the Grid
     */
    public void unMaskGrid() {
        this.grid.getView().getBody().unmask();
    }

    /**
     *
     * @param beans {@link ArrayList} of Directions to fill the Store
     */
    public void fillStore(List<Directions> beans) {
        this.store.add(beans);
    }

    /**
     * @return the directionsField
     */
    public FieldSet getDirectionsField() {
        return directionsField;
    }
}
