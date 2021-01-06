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
package org.geosdi.geoplatform.gui.client.plugin;

import com.extjs.gxt.ui.client.widget.TabPanel;
import org.geosdi.geoplatform.gui.configuration.geocoding.plugin.GPAdvancedGeocoderPlugin;
import org.geosdi.geoplatform.gui.configuration.geocoding.plugin.GeocoderPluginType;
import org.geosdi.geoplatform.gui.plugin.geocoding.GPGeocoderPluginManager;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 *
 */
public class AdvancedGeocoderPluginManager
        extends GPGeocoderPluginManager<GPAdvancedGeocoderPlugin> {
    
    private TabPanel tabPanel;
    
    public AdvancedGeocoderPluginManager(GeocoderPluginType theType) {
        super(theType);
        this.initPluginManager();
    }
    
    @Override
    public void addPlugin(GPAdvancedGeocoderPlugin plugin) {
        this.getGeocoderPlugins().add(plugin);
    }
    
    @Override
    public void removePlugin(GPAdvancedGeocoderPlugin plugin) {
        this.getGeocoderPlugins().remove(plugin);
    }
    
    @Override
    public void managePluginsProperties(int dimension) {
        for (GPAdvancedGeocoderPlugin plugin : getGeocoderPlugins()) {
            plugin.widgetResized(dimension);
        }
    }
    
    @Override
    public void buildPlugins() {
        if (!isPluginManagerInitialized()) {
            for (GPAdvancedGeocoderPlugin plugin : getGeocoderPlugins()) {
                this.tabPanel.add(plugin.getWidget());
            }
            super.initialized = true;
        }
    }
    
    @Override
    public TabPanel getWidget() {
        return this.tabPanel;
    }
    
    private void initPluginManager() {
        this.buildTabPanel();
        if (super.type.equals(GeocoderPluginType.ADVANCED_WITH_GOOGLE)) {
            addPlugin(new GPGoogleGeocoderPlugin());
        }
    }
    
    private void buildTabPanel() {
        this.tabPanel = new TabPanel();
        this.tabPanel.setAutoWidth(true);
    }
}
