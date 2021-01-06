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
package org.geosdi.geoplatform.gui.client.widget.grid;

import com.extjs.gxt.ui.client.data.ModelData;
import java.util.List;

import org.geosdi.geoplatform.gui.configuration.grid.IGeoPlatformGrid;

import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import java.util.logging.Logger;

/**
 * @author giuseppe
 *
 */
public abstract class GeoPlatformGridWidget<T extends ModelData>
        implements IGeoPlatformGrid<T> {

    protected final static Logger logger = Logger.getLogger("");

    protected ListStore<T> store;
    protected Grid<T> grid;
    private boolean initialized;

    /**
     *
     * @param lazy
     */
    public GeoPlatformGridWidget(boolean lazy) {
        if (!lazy) {
            init();
        }
    }

    public void init() {
        if (!isInitialized()) {
            createStore();
            initGrid();
            this.initialized = true;
        }
    }

    /**
     *
     * @param models Beans Model to fill the Store
     */
    public GeoPlatformGridWidget(List<T> models) {
        createStore();
        this.store.add(models);
        initGrid();
    }

    private void initGrid() {
        ColumnModel cm = prepareColumnModel();

        grid = new Grid<T>(store, cm);
        grid.setBorders(true);

        setGridProperties();
    }

    public abstract void setGridProperties();

    public abstract ColumnModel prepareColumnModel();

    public abstract void createStore();

    /**
     * @return the store
     */
    @Override
    public ListStore<T> getStore() {
        return store;
    }

    /**
     * @return the grid
     */
    @Override
    public Grid<T> getGrid() {
        return grid;
    }

    @Override
    public void fillStore(List<T> models) {
        this.store.add(models);
    }

    @Override
    public void cleanUpTheStore() {
        this.store.removeAll();
    }

    /**
     * @return the initialized
     */
    public boolean isInitialized() {
        return initialized;
    }
}
