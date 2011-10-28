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
package org.geosdi.geoplatform.gui.client;

import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.action.menu.MenuActionCreator;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.client.action.menu.LayerMenuAction;
import org.geosdi.geoplatform.gui.client.mvc.LayerController;

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToKML;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToPDF;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToShpZip;
import org.geosdi.geoplatform.gui.client.action.menu.ExportoToTIFF;
import org.geosdi.geoplatform.gui.client.action.menu.ZoomToLayerExtentAction;
import org.geosdi.geoplatform.gui.client.action.menu.project.LoadMenuProjects;
import org.geosdi.geoplatform.gui.client.mvc.ServerController;
import org.geosdi.geoplatform.gui.client.plugin.AddFolderTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.plugin.AddRasterTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.plugin.AddVectorTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.plugin.DeleteElementTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.plugin.GetMapTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.plugin.PrintLayersTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.plugin.SaveTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.plugin.tree.TreeToolbarPluginManager;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class LayerWidgetUI implements EntryPoint {

    private Dispatcher dispatcher;

    /*
     * (non-Javadoc)
     *
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    @Override
    public void onModuleLoad() {
        dispatcher = Dispatcher.get();

        dispatcher.addController(new LayerController());
        dispatcher.addController(new ServerController());

        addLayerWidgetAction();
        addTreeToolbarComponents();

        dispatcher.fireEvent(GeoPlatformEvents.INIT_OGC_MODULES_WIDGET);
    }

    private void addLayerWidgetAction() {
        MenuActionRegistar.put("layerMenu", new MenuActionCreator() {

            @Override
            public MenuAction createAction() {
                return new LayerMenuAction();
            }
        });

        MenuActionRegistar.put("ZoomToLayerExtent", new MenuActionCreator() {

            @Override
            public MenuAction createAction() {
                return new ZoomToLayerExtentAction();
            }
        });

        MenuActionRegistar.put("exportToKML", new MenuActionCreator() {

            @Override
            public MenuAction createAction() {
                return new ExportoToKML();
            }
        });

        MenuActionRegistar.put("exportToPDF", new MenuActionCreator() {

            @Override
            public MenuAction createAction() {
                return new ExportoToPDF();
            }
        });


        MenuActionRegistar.put("exportToTIFF", new MenuActionCreator() {

            @Override
            public MenuAction createAction() {
                return new ExportoToTIFF();
            }
        });

        MenuActionRegistar.put("exportToShpZip", new MenuActionCreator() {

            @Override
            public MenuAction createAction() {
                return new ExportoToShpZip();
            }
        });

        MenuActionRegistar.put("loadProjects", new MenuActionCreator() {

            @Override
            public MenuAction createAction() {
                return new LoadMenuProjects();
            }
        });


    }

    private void addTreeToolbarComponents() {
        TreeToolbarPluginManager.addToolBarPlugin(new AddFolderTreeToolbarPlugin());
        TreeToolbarPluginManager.addToolBarPlugin(new AddRasterTreeToolbarPlugin());
        TreeToolbarPluginManager.addToolBarPlugin(new AddVectorTreeToolbarPlugin());
        TreeToolbarPluginManager.addToolBarPlugin(new DeleteElementTreeToolbarPlugin());
        TreeToolbarPluginManager.addToolBarPlugin(new SaveTreeToolbarPlugin());
        //No entrypoint in print module then I declared here the plugin
        TreeToolbarPluginManager.addToolBarPlugin(new PrintLayersTreeToolbarPlugin());
        TreeToolbarPluginManager.addToolBarPlugin(new GetMapTreeToolbarPlugin());

    }
}