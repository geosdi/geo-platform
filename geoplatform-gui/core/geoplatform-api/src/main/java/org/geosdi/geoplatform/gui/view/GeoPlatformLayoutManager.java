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
package org.geosdi.geoplatform.gui.view;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Status;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.client.i18n.ApiModuleMessages;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.event.ScaleChangeEvent;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 * @author giuseppe
 *
 */
public abstract class GeoPlatformLayoutManager {

    protected Viewport viewport;
    protected ContentPanel east;
    protected ContentPanel west;
    protected ContentPanel south;
    protected TabPanel southTabPanel;
    protected ContentPanel center;
    protected ContentPanel north;
    protected Status statusMap;

    public GeoPlatformLayoutManager() {
        initLayoutManager();
    }

    /**
     * Build The Main GeoPlatform UI
     */
    private void initLayoutManager() {
        viewport = new Viewport();
        viewport.setLayout(new BorderLayout());
        createEast();
        createWest();
        createCenter();
        createSouth();
    }

    /**
     * Create North Panel in Main UI
     *
     * @param size
     */
    public void createNorth(float size) {
        this.north = new ContentPanel();
        this.north.setHeaderVisible(false);
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, size);
        data.setMargins(new Margins(0, 5, 0, 5));

        this.north.setData("layoutData", data);

        viewport.add(north);
    }

    /**
     * Create West Panel in Main UI
     */
    private void createWest() {
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST, 350);
        data.setMargins(new Margins(5, 0, 5, 5));
        west = new ContentPanel();
        west.setHeaderVisible(false);
        west.setBodyBorder(false);
        west.setLayout(new AccordionLayout());
        west.setScrollMode(Scroll.NONE);
        west.hide();
        viewport.add(west, data);
    }

    /**
     * Create East Panel in Main UI
     */
    private void createEast() {
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.EAST, 350);
        data.setMargins(new Margins(5, 0, 5, 5));
        east = new ContentPanel();
        east.setHeaderVisible(false);
        east.setBodyBorder(false);
        east.setLayout(new AccordionLayout());
        east.setScrollMode(Scroll.NONE);
        east.hide();
        viewport.add(east, data);
    }

    /**
     * Create Center Panel in Main UI
     */
    private void createCenter() {
        center = new ContentPanel();

        center.setHeaderVisible(false);
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
        data.setMargins(new Margins(5, 5, 5, 5));

        center.setLayoutOnChange(true);

        center.addWidgetListener(new WidgetListener() {

            @Override
            public void widgetResized(ComponentEvent ce) {
                Dispatcher.forwardEvent(GeoPlatformEvents.UPDATE_CENTER);
                MapHandlerManager.fireEvent(new ScaleChangeEvent(
                        XDOM.getViewportSize()));
            }
        });

        ToolBar toolBar = new ToolBar();

        statusMap = new Status();
        statusMap.setText(ApiModuleMessages.INSTANCE.
                GeoPlatformLayoutManager_wellcomeMessage(GWT.getModuleName()));
        statusMap.setWidth(150);
        toolBar.add(statusMap);
        toolBar.add(new FillToolItem());

        center.setBottomComponent(toolBar);

        viewport.add(center, data);
    }

    /**
     * Create South Panel in Main UI
     */
    private void createSouth() {
        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH,
                200,
                200, 200);
        southData.setMargins(new Margins(5, 0, 5, 5));
        southData.setSplit(true);
        southData.setCollapsible(true);
        southData.setFloatable(false);

        south = new ContentPanel();
        south.setBodyBorder(false);
        south.setLayout(new FitLayout());
        south.setLayoutOnChange(true);
        south.setScrollMode(Scroll.AUTOY);
        south.setHeaderVisible(false);
//        south.setScrollMode(Scroll.NONE);
//        south.setHeaderVisible(true);
        this.southTabPanel = new TabPanel();
        this.southTabPanel.setAutoWidth(true);
        this.southTabPanel.setAutoHeight(true);
        south.add(this.southTabPanel);

        south.hide();

        viewport.add(south, southData);
    }

    /**
     * @return the east
     */
    public ContentPanel getEast() {
        return east;
    }

    /**
     * @return the viewport
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * @return the south
     */
    public TabPanel getSouth() {
        return southTabPanel;
    }

    /**
     * @return the center
     */
    public ContentPanel getCenter() {
        return center;
    }

    /**
     *
     * @return the west
     */
    public ContentPanel getWest() {
        return west;
    }

    /**
     * @return the north If North is null GeoPlatform set North Size to 50 for
     * default
     *
     */
    public ContentPanel getNorth() {
        if (north == null) {
            createNorth(50);
        }
        return north;
    }

    /**
     *
     * @return Status Map component
     */
    public Status getStatusMap() {
        return statusMap;
    }

    /**
     * Remove North Panel from Viewport
     */
    public void removeNorth() {
        this.viewport.remove(north);
        this.viewport.layout();
    }

    /**
     * Reset BorderLayout North Component
     */
    public void resetNorthBorderLayout() {
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.NORTH, 23);
        data.setMargins(new Margins(0, 5, 0, 5));

        this.north.setData("layoutData", data);

        this.north.layout();

        this.viewport.layout();
    }
}
