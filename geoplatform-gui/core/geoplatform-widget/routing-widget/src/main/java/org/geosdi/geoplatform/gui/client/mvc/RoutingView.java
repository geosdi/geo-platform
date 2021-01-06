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
package org.geosdi.geoplatform.gui.client.mvc;

import java.util.ArrayList;

import org.geosdi.geoplatform.gui.client.RoutingEvents;
import org.geosdi.geoplatform.gui.client.model.Directions;
import org.geosdi.geoplatform.gui.client.widget.RoutingManagementWidget;
import org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformView;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.routing.RoutingHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.routing.event.RoutingActivationEvent;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.widget.grid.Grid;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class RoutingView extends GeoPlatformView {

    private RoutingManagementWidget routingManagement;
    private RoutingActivationEvent activationEvent;

    public RoutingView(Controller controller) {
        super(controller);
        this.routingManagement = new RoutingManagementWidget();
    }

    /**
     * (non-Javadoc)
     *
     * @see com.extjs.gxt.ui.client.mvc.View#initialize()
     */
    @Override
    protected void initialize() {
        this.activationEvent = new RoutingActivationEvent();
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformView#handleEvent(com.extjs.gxt.ui.client.mvc.AppEvent)
     */
    @Override
    protected void handleEvent(AppEvent event) {
        if (event.getType() == RoutingEvents.SHOW_ROUTING_WIDGET) {
            onShowRoutingWidget();
        }

        if (event.getType() == RoutingEvents.HIDE_ROUTING_WIDGET) {
            onHideRoutingWidget();
        }
    }

    /**
     * Show Routing Widget
     */
    private void onShowRoutingWidget() {
        if (!LayoutManager.isWestVisible()) {
            LayoutManager.manageWest(true);
        }
        LayoutManager.addComponentToWest(routingManagement);
        this.activationEvent.setActivate(Boolean.TRUE);

        RoutingHandlerManager.fireEvent(this.activationEvent);
    }

    /**
     * Hide Routing Widget
     */
    private void onHideRoutingWidget() {
        if (LayoutManager.isWidgetPresentOnWest(routingManagement)) {
            LayoutManager.removeComponentFromWest(routingManagement);
            this.activationEvent.setActivate(Boolean.FALSE);
            if (!LayoutManager.isOneWidgetVisibleAtWest()) {
                LayoutManager.manageWest(false);
            }

            RoutingHandlerManager.fireEvent(this.activationEvent);
        }
    }

    /**
     * @return the routingManagement
     */
    public RoutingManagementWidget getRoutingManagement() {
        return routingManagement;
    }

    /**
     * Mask RoutingGridWidget
     */
    public void maskRoutingGrid() {
        this.routingManagement.getRoutingGridWidget().maskGrid();
    }

    /**
     * Un Mask RoutingGridWidget
     */
    public void unMaskRoutingGrid() {
        this.routingManagement.getRoutingGridWidget().unMaskGrid();
    }

    /**
     * Clean the Store
     */
    public void cleanStore() {
        this.routingManagement.getRoutingGridWidget().getStore().removeAll();
    }

    /**
     * Fill RoutingGridWidget Store
     *
     * @param beans
     */
    public void fillStore(ArrayList<Directions> beans) {
        this.routingManagement.getRoutingGridWidget().fillStore(beans);
    }

    public Grid<Directions> getGrid() {
        return this.routingManagement.getRoutingGridWidget().getGrid();
    }
}
