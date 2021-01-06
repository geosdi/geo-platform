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

import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.action.menu.MenuActionCreator;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.client.action.menu.LayerMenuAction;
import org.geosdi.geoplatform.gui.client.action.menu.project.LoadMenuProjects;
import org.geosdi.geoplatform.gui.client.action.temporary.TreeMenuActionFactory;
import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.client.config.LayerModuleInjector;
import org.geosdi.geoplatform.gui.client.mvc.LayerController;
import org.geosdi.geoplatform.gui.client.mvc.ServerController;
import org.geosdi.geoplatform.gui.client.plugin.tree.addlayer.AddRasterLayerPlugin;
import org.geosdi.geoplatform.gui.client.plugin.tree.addlayer.GetMapLayerPlugin;
import org.geosdi.geoplatform.gui.client.plugin.tree.toolbar.AddFolderTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.plugin.tree.toolbar.AddLayerTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.plugin.tree.toolbar.DeleteElementTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.client.plugin.tree.toolbar.SaveTreeToolbarPlugin;
import org.geosdi.geoplatform.gui.plugin.tree.addlayer.AddLayerPluginManager;
import org.geosdi.geoplatform.gui.plugin.tree.toolbar.TreeToolbarPluginManager;

import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LayerWidgetUI implements EntryPoint {

    protected final static Logger logger = Logger.getLogger("LayerWidgetUI");
    //
    @Inject
    static LoadMenuProjects loadMenuProjects;
    private Dispatcher dispatcher;

    /**
     * (non-Javadoc)
     *
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    @Override
    public void onModuleLoad() {
        this.initInjection();
        dispatcher = Dispatcher.get();

        dispatcher.addController(new LayerController());
        dispatcher.addController(new ServerController());

        addLayerWidgetAction();
        addTreeToolbarComponents();
        TreeMenuActionFactory.buildTreeMenuActions();
    }

    /**
     *
     */
    private void initInjection() {
        GWT.create(LayerModuleInjector.MainInjector.class);
    }

    /**
     *
     */
    private void addLayerWidgetAction() {
        MenuActionRegistar menuRegistar = BasicGinInjector.MainInjector.getInstance().getMenuActionRegistar();
        menuRegistar.put("layerMenu",
                new MenuActionCreator() {
                    @Override
                    public MenuAction createAction() {
                        return new LayerMenuAction();
                    }
                });

        menuRegistar.put("manageProjects",
                new MenuActionCreator() {
                    @Override
                    public MenuAction createAction() {
                        return loadMenuProjects;
                    }
                });

    }

    /**
     *
     */
    private void addTreeToolbarComponents() {
        TreeToolbarPluginManager.addToolbarPlugin(new AddFolderTreeToolbarPlugin());
        TreeToolbarPluginManager.addToolbarPlugin(new AddLayerTreeToolbarPlugin());
//        TreeToolbarPluginManager.addToolBarPlugin(new AddVectorTreeToolbarPlugin());
        TreeToolbarPluginManager.addToolbarPlugin(new DeleteElementTreeToolbarPlugin());
        TreeToolbarPluginManager.addToolbarPlugin(new SaveTreeToolbarPlugin());
//        TreeToolbarPluginManager.addToolBarPlugin(new GetMapTreeToolbarPlugin());

        AddLayerPluginManager.addWindowPlugin(new AddRasterLayerPlugin());
        //TODO: Enable when platform support WFS
//        AddLayerPluginManager.addWindowPlugin(new AddVectorLayerPlugin());
        AddLayerPluginManager.addWindowPlugin(new GetMapLayerPlugin());
    }
}