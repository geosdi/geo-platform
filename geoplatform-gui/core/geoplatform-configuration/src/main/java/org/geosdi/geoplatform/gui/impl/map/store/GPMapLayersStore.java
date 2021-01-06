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
package org.geosdi.geoplatform.gui.impl.map.store;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.impl.tree.DisplayLayersManager;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.layer.Layer;

import java.util.List;
import java.util.Map;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPMapLayersStore<K extends GPLayerBean, T extends Layer> implements IMapLayersStore<T> {

    protected MapWidget mapWidget;
    protected Map<K, T> layers = Maps.<K, T>newHashMap();
    private final DisplayLayersManager displayLayers;

    /**
     * @param theMapWidget
     * @Constructor
     */
    public GPMapLayersStore(MapWidget theMapWidget) {
        this.mapWidget = theMapWidget;
        this.displayLayers = new DisplayLayersManager(this);
    }

    /**
     * @param layer
     */
    protected void displayLayer(GPLayerBean layer) {
        this.displayLayers.forwardRequest(layer);
    }

    /**
     * @return List<T>
     */
    @Override
    public List<T> getLayers() {
        return Lists.<T>newArrayList(this.layers.values());
    }
}