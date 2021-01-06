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
package org.geosdi.geoplatform.gui.client.widget.grid.pagination.grid;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import org.geosdi.geoplatform.gui.client.widget.grid.pagination.GeoPlatformSearchPanel;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public abstract class GPGridSearchPanel<T extends GeoPlatformBeanModel>
        extends GeoPlatformSearchPanel<Grid<T>, T> {

    public GPGridSearchPanel(boolean lazy) {
        super(lazy);
    }

    public GPGridSearchPanel(boolean lazy, int pageSize) {
        super(lazy, pageSize);
    }

    @Override
    public void initWidget() {
        ColumnModel cm = prepareColumnModel();
        super.widget = new Grid<T>(store, cm);
        super.widget.setBorders(true);

        super.widget.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        super.widget.addListener(Events.CellClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                if (widget.getSelectionModel().getSelection().size() > 0) {
                    actionAfterSelect();
                } else {
                    actionAfterDeselect();
                }
            }
        });

        super.widget.addListener(Events.CellDoubleClick, new Listener<BaseEvent>() {

            @Override
            public void handleEvent(BaseEvent be) {
                executeSelect();
            }
        });

        setGridProperties();
    }

    protected void actionAfterSelect(){
        selectButton.enable();
    }

    protected void actionAfterDeselect(){
        selectButton.disable();
    }

    public abstract void setGridProperties();

    public abstract ColumnModel prepareColumnModel();

}
