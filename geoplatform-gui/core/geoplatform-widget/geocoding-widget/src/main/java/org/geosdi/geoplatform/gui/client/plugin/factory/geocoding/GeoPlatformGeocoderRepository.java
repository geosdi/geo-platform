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
package org.geosdi.geoplatform.gui.client.plugin.factory.geocoding;

import com.google.common.collect.Maps;
import java.util.Map;
import org.geosdi.geoplatform.gui.configuration.geocoding.plugin.GeocoderPluginType;
import org.geosdi.geoplatform.gui.configuration.geocoding.plugin.IGPGeocoderPluginManager;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 *
 */
public class GeoPlatformGeocoderRepository {

    private Map<GeocoderPluginType, IGPGeocoderPluginManager> plugins;
    private static GeoPlatformGeocoderRepository instance;

    private GeoPlatformGeocoderRepository() {
        this.plugins = Maps.newHashMap();
    }

    public static synchronized GeoPlatformGeocoderRepository getInstance() {
        if (instance == null) {
            instance = new GeoPlatformGeocoderRepository();
        }

        return instance;
    }
    
    /**
     * 
     * @param geocoderManager 
     */
    public synchronized void bindPlugin(IGPGeocoderPluginManager geocoderManager) {
        if (plugins.get(geocoderManager.getGecoderPluginType()) == null) {
            this.plugins.put(geocoderManager.getGecoderPluginType(), geocoderManager);
        }
    }

    /**
     * Find GeoCoderPlugin Manager in the Map
     * 
     * @param GeoCoder Type
     * @return IGPGeocoderPluginManager
     */
    public synchronized IGPGeocoderPluginManager findPlugin(GeocoderPluginType type) {
        return plugins.get(type);
    }

    /**
     * 
     * @param GeocoderPluginType
     * @return true only if Member Manager registered with type is found
     */
    public synchronized boolean removePlugin(GeocoderPluginType type) {
        return this.plugins.remove(type) != null;
    }
}
