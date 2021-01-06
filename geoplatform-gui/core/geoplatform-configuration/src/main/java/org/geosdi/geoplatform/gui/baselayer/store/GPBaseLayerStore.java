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
package org.geosdi.geoplatform.gui.baselayer.store;

import org.geosdi.geoplatform.gui.factory.baselayer.GPBaseLayerCreator;
import org.geosdi.geoplatform.gui.global.baselayer.GPBaseLayerValue;
import org.gwtopenmaps.openlayers.client.layer.Layer;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import static org.geosdi.geoplatform.gui.global.enumeration.BaseLayerValue.GOOGLE_SATELLITE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPBaseLayerStore extends AbstractBaseLayerStore {

    private static final Logger logger = Logger.getLogger("GPBaseLayerStore");

    public GPBaseLayerStore() {
        for (Map.Entry<GPBaseLayerValue, GPBaseLayerCreator> entry : this.baseLayerMap.entrySet()) {
            logger.info("###########################KEY : " + entry.getKey());
        }
    }

    /**
     * @return {@link Layer}
     */
    @Override
    public Layer getDefaultBaseLayer() {
        return this.findBaseLayer(GOOGLE_SATELLITE);
    }

    /**
     * @param theKey
     * @return {@link Layer}
     */
    @Override
    public Layer findBaseLayer(GPBaseLayerValue theKey) {
        return (baseLayerMap.get(theKey) != null)
                ? baseLayerMap.get(theKey).createBaseLayer() : null;
    }

    /**
     * @return {@link Map <GPBaseLayerValue,  GPBaseLayerCreator >}
     */
    @Override
    public Map<GPBaseLayerValue, GPBaseLayerCreator> lookupBaseLayers() {
        return Collections.unmodifiableMap(this.baseLayerMap);
    }

    /**
     * @param value
     * @param layerCreator
     */
    @Override
    public void registerBaseLayer(GPBaseLayerValue value, GPBaseLayerCreator layerCreator) {
        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@REGISTER : " + baseLayerMap.containsKey(value));
        this.baseLayerMap.put(value, layerCreator);
    }

    /**
     * @param value
     */
    @Override
    public void removeBaseLayer(GPBaseLayerValue value) {
        if (this.baseLayerMap.containsKey(value))
            this.baseLayerMap.remove(value);
    }
}
