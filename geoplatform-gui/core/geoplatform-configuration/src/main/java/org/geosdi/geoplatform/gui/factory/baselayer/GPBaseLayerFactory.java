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
package org.geosdi.geoplatform.gui.factory.baselayer;

import org.geosdi.geoplatform.gui.baselayer.store.GPBaseLayerStore;
import org.geosdi.geoplatform.gui.baselayer.store.IGPBaseLayerStore;
import org.geosdi.geoplatform.gui.global.baselayer.GPBaseLayerValue;
import org.gwtopenmaps.openlayers.client.layer.Layer;

import java.util.Map;

import static org.geosdi.geoplatform.gui.global.enumeration.BaseLayerValue.GOOGLE_SATELLITE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseLayerFactory implements BaseLayerFactory {

    private static final IGPBaseLayerStore GP_BASE_LAYER_STORE = new GPBaseLayerStore();

    public static Layer getBaseLayer(GPBaseLayerValue enumLayer) {
        GPBaseLayerFactory factory = new GPBaseLayerFactory();
        Layer layer = factory.findBaseLayer(enumLayer);
        return layer != null ? layer : factory.getDefaultBaseLayer();
    }

    /**
     * @param value
     * @param layerCreator
     */
    @Override
    public void registerBaseLayer(GPBaseLayerValue value, GPBaseLayerCreator layerCreator) {
        GP_BASE_LAYER_STORE.registerBaseLayer(value, layerCreator);
    }

    /**
     * @param value
     */
    @Override
    public void removeBaseLayer(GPBaseLayerValue value) {
        GP_BASE_LAYER_STORE.removeBaseLayer(value);
    }

    @Override
    public Layer getDefaultBaseLayer() {
        return GP_BASE_LAYER_STORE.findBaseLayer(GOOGLE_SATELLITE);
    }

    @Override
    public Layer findBaseLayer(GPBaseLayerValue enumLayer) {
        return GP_BASE_LAYER_STORE.findBaseLayer(enumLayer);
    }

    @Override
    public Map<GPBaseLayerValue, GPBaseLayerCreator> lookupBaseLayers() {
        return GP_BASE_LAYER_STORE.lookupBaseLayers();
    }
}
