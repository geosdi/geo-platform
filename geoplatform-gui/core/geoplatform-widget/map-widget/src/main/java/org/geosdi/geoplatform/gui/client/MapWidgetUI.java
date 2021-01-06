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
package org.geosdi.geoplatform.gui.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import org.geosdi.geoplatform.gui.action.ToolbarAction;
import org.geosdi.geoplatform.gui.action.ToolbarActionCreator;
import org.geosdi.geoplatform.gui.action.ToolbarActionRegistar;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.action.menu.MenuActionCreator;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.client.action.menu.ViewportAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.MeasureAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.MeasureAreaAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.ZoomInAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.ZoomNextAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.ZoomOutAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.ZoomPreviousAction;
import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.client.mvc.MapController;
import org.geosdi.geoplatform.gui.client.widget.map.MapLayoutWidget;
import org.geosdi.geoplatform.gui.featureinfo.action.GetFeatureInfoAction;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class MapWidgetUI implements EntryPoint {

    private Dispatcher dispatcher;

    /**
     * (non-Javadoc)
     *
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    @Override
    public void onModuleLoad() {
        dispatcher = Dispatcher.get();
        dispatcher.addController(new MapController());
        addMapToolbarAction();
    }

    private void addMapToolbarAction() {
        ToolbarActionRegistar toolbarRegistar = BasicGinInjector.MainInjector.getInstance().getToolbarActionRegistar();
        toolbarRegistar.put("ZoomIn",
                new ToolbarActionCreator() {
            @Override
            public ToolbarAction createActionTool(GeoPlatformMap mapWidget) {
                return new ZoomInAction(mapWidget);
            }
        });

        toolbarRegistar.put("ZoomOut",
                new ToolbarActionCreator() {
            @Override
            public ToolbarAction createActionTool(GeoPlatformMap mapWidget) {
                return new ZoomOutAction(mapWidget);
            }
        });

        toolbarRegistar.put("ZoomPrevious",
                new ToolbarActionCreator() {
            @Override
            public ToolbarAction createActionTool(GeoPlatformMap mapWidget) {
                return new ZoomPreviousAction(mapWidget);
            }
        });

        toolbarRegistar.put("ZoomNext",
                new ToolbarActionCreator() {
            @Override
            public ToolbarAction createActionTool(GeoPlatformMap mapWidget) {
                return new ZoomNextAction(mapWidget);
            }
        });

        toolbarRegistar.put("GetFeatureInfo",
                new ToolbarActionCreator() {
            @Override
            public ToolbarAction createActionTool(GeoPlatformMap mapWidget) {
                return new GetFeatureInfoAction(mapWidget);
            }
        });

        toolbarRegistar.put("Measure",
                new ToolbarActionCreator() {
            @Override
            public ToolbarAction createActionTool(GeoPlatformMap mapWidget) {
                return new MeasureAction(mapWidget);
            }
        });

        toolbarRegistar.put("MeasureArea",
                new ToolbarActionCreator() {
            @Override
            public ToolbarAction createActionTool(GeoPlatformMap mapWidget) {
                return new MeasureAreaAction(mapWidget);
            }
        });

        MenuActionRegistar menuRegistar = BasicGinInjector.MainInjector.getInstance().getMenuActionRegistar();

        menuRegistar.put("viewport",
                new MenuActionCreator() {
            @Override
            public MenuAction createAction() {
                MapLayoutWidget mapLayoutWidget = Registry.get(
                        MapRegistryEnum.MAP_LAYOUT_WIDGET.toString());
                return new ViewportAction(mapLayoutWidget.getMap());
            }
        });
    }
}
