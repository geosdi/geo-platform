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
package org.geosdi.geoplatform.gui.client.widget.toolbar;

import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.google.common.collect.Lists;
import org.geosdi.geoplatform.gui.client.widget.LayerTreeWidget;
import org.geosdi.geoplatform.gui.client.widget.tree.toolbar.GPTreeToolbar;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.plugin.tree.toolbar.ITreeToolbarPlugin;
import org.geosdi.geoplatform.gui.plugin.tree.toolbar.TreeToolbarPluginManager;
import org.geosdi.geoplatform.gui.plugin.tree.toolbar.TreeToolbarRegion;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LayerTreeToolbar extends GPTreeToolbar {

    private boolean initialized;

    /**
     * @param layerTree
     */
    @Inject
    public LayerTreeToolbar(LayerTreeWidget layerTree) {
        super(layerTree.getTree());
    }

    @Override
    public void buildToolbar() {
        if (!initialized) {
            initialize();
            this.initialized = true;
        }
    }

    /**
     * Create the Toolbar for LayerTreeWidget
     */
    @Override
    public void initialize() {
        this.addElementsByRegion(TreeToolbarRegion.START_REGION);
        this.addSeparator();
        this.addElementsByRegion(TreeToolbarRegion.MIDDLE_REGION);
        this.addSeparator();
        this.addElementsByRegion(TreeToolbarRegion.END_REGION);
    }

    /**
     * @param region
     */
    private void addElementsByRegion(TreeToolbarRegion region) {
        List<ITreeToolbarPlugin> pluginsExcluded = Lists.<ITreeToolbarPlugin>newArrayList();
        for (ITreeToolbarPlugin element : TreeToolbarPluginManager.getToolbarPluginByRegion(region)) {
            String id = element.getId();
            Boolean permission = GPAccountLogged.getInstance().hasComponentPermission(id);
            if (permission == null) { // Element will not be visible
                // The element will removed from toolbarPlugin and not added to toolbar
                pluginsExcluded.add(element);
                continue;
            } else if (!permission) { // Element will always be visible as disabled
                // The element will removed from toolbarPlugin (method setEnableByStatus will not be called) and added to toolbar
                pluginsExcluded.add(element);
            }
            this.toolBar.add(element.getWidget(tree));
        }
        TreeToolbarPluginManager.removeToolbarPlugins(pluginsExcluded);
    }

    /**
     * Add a Vertical Line in the Toolbar
     */
    public void addSeparator() {
        this.toolBar.add(new SeparatorToolItem());
    }
}