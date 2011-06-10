/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
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
package org.geosdi.geoplatform.gui.client;

import org.geosdi.geoplatform.gui.action.GeoPlatformToolbarAction;
import org.geosdi.geoplatform.gui.action.ToolbarActionCreator;
import org.geosdi.geoplatform.gui.action.ToolbarActionRegistar;
import org.geosdi.geoplatform.gui.client.action.toolbar.GetFeatureInfoAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.MeasureAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.MeasureAreaAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.ZoomInAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.ZoomNextAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.ZoomOutAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.ZoomPreviousAction;
import org.geosdi.geoplatform.gui.client.mvc.MapController;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class MapWidgetUI implements EntryPoint {

    private Dispatcher dispatcher;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    @Override
    public void onModuleLoad() {
        // TODO Auto-generated method stub
        dispatcher = Dispatcher.get();

        dispatcher.addController(new MapController());

        addMapToolbarAction();

        dispatcher.dispatch(MapWidgetEvents.INIT_MAP_WIDGET);

    }

    private void addMapToolbarAction() {
        // TODO Auto-generated method stub
        ToolbarActionRegistar.put("ZoomIn", new ToolbarActionCreator() {

            @Override
            public GeoPlatformToolbarAction createActionTool(
                    GeoPlatformMap mapWidget) {
                // TODO Auto-generated method stub
                return new ZoomInAction(mapWidget);
            }
        });

        ToolbarActionRegistar.put("ZoomOut", new ToolbarActionCreator() {

            @Override
            public GeoPlatformToolbarAction createActionTool(
                    GeoPlatformMap mapWidget) {
                // TODO Auto-generated method stub
                return new ZoomOutAction(mapWidget);
            }
        });

        ToolbarActionRegistar.put("ZoomPrevious", new ToolbarActionCreator() {

            @Override
            public GeoPlatformToolbarAction createActionTool(
                    GeoPlatformMap mapWidget) {
                // TODO Auto-generated method stub
                return new ZoomPreviousAction(mapWidget);
            }
        });

        ToolbarActionRegistar.put("ZoomNext", new ToolbarActionCreator() {

            @Override
            public GeoPlatformToolbarAction createActionTool(
                    GeoPlatformMap mapWidget) {
                // TODO Auto-generated method stub
                return new ZoomNextAction(mapWidget);
            }
        });

        ToolbarActionRegistar.put("GetFeatureInfo", new ToolbarActionCreator() {

            @Override
            public GeoPlatformToolbarAction createActionTool(
                    GeoPlatformMap mapWidget) {
                // TODO Auto-generated method stub
                return new GetFeatureInfoAction(mapWidget);
            }
        });

        ToolbarActionRegistar.put("Measure", new ToolbarActionCreator() {

            @Override
            public GeoPlatformToolbarAction createActionTool(
                    GeoPlatformMap mapWidget) {
                // TODO Auto-generated method stub
                return new MeasureAction(mapWidget);
            }
        });
        
        ToolbarActionRegistar.put("MeasureArea", new ToolbarActionCreator() {

            @Override
            public GeoPlatformToolbarAction createActionTool(
                    GeoPlatformMap mapWidget) {
                return new MeasureAreaAction(mapWidget);
            }
        }); 
    }
}
