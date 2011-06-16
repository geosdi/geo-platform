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
package org.geosdi.geoplatform.gui.featureinfo.widget;

import java.util.Collection;
import java.util.Iterator;
import org.geosdi.geoplatform.gui.featureinfo.cache.FeatureInfoFlyWeight;
import org.geosdi.geoplatform.gui.featureinfo.cache.IGPFeatureInfoElement;
import org.gwtopenmaps.openlayers.client.Map;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPFeatureInfoCaller {

    private boolean loaded;
    private FeatureInfoFlyWeight featureCache = new FeatureInfoFlyWeight();
    private Map map;

    public GPFeatureInfoCaller(Map theMap) {
        this.map = theMap;
    }

    public void load() {
        if (!isLoaded()) {
            loadUserServers();
        } else {
            activateFeatureInfoControl();
        }
    }

    private void loadUserServers() {
        IGPFeatureInfoElement element = this.featureCache.get(
                "http://maps.telespazio.it/dpc/dpc-wms");

        this.map.addControl(element.getElementControl());
        element.getElementControl().activate();


        IGPFeatureInfoElement element1 = this.featureCache.get(
                "http://dpc.geosdi.org/geoserver/wms");

        this.map.addControl(element1.getElementControl());
        element1.getElementControl().activate();
        this.loaded = true;
    }

    /**
     * 
     */
    public void activateFeatureInfoControl() {
        for (Iterator<IGPFeatureInfoElement> it = featureCache.getCollection().iterator(); it.hasNext();) {
            it.next().getElementControl().activate();
        }
    }

    /**
     * 
     */
    public void deactivateFeatureInfoControl() {
        for (Iterator<IGPFeatureInfoElement> it = featureCache.getCollection().iterator(); it.hasNext();) {
            it.next().getElementControl().deactivate();
        }
    }

    /**
     * 
     * @return  
     *          Collection<IGPFeatureInfoElement>
     */
    public Collection<IGPFeatureInfoElement> getCollection() {
        return this.featureCache.getCollection();
    }

    /**
     * 
     * @return  
     *         boolean
     */
    public boolean isLoaded() {
        return loaded;
    }
}
