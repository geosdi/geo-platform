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

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.featureinfo.cache.FeatureInfoFlyWeight;
import org.geosdi.geoplatform.gui.featureinfo.cache.IGPFeatureInfoElement;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.service.server.GeoPlatformOGCRemote;
import org.gwtopenmaps.openlayers.client.Map;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPFeatureInfoCaller {

    private boolean loaded;
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
        LayoutManager.get().getStatusMap().setStatus("Loading Feature Info Data Sources.",
                EnumSearchStatus.STATUS_SEARCH.toString());
        GeoPlatformOGCRemote.Util.getInstance().findDistinctLayersDataSource(new AsyncCallback<ArrayList<String>>() {

            @Override
            public void onFailure(Throwable caught) {
                GeoPlatformMessage.errorMessage("Get Feature Info Error",
                        "Problems to perform Get Feature Info");
            }

            @Override
            public void onSuccess(ArrayList<String> result) {
                for (String string : result) {
                    IGPFeatureInfoElement element = FeatureInfoFlyWeight.getInstance().get(string);
                    map.addControl(element.getElementControl());
                }
                activateFeatureInfoControl();
                loaded = true;
                LayoutManager.get().getStatusMap().setStatus("Feature Info Function loaded succesfully.",
                EnumSearchStatus.STATUS_SEARCH.toString());
            }
        });
    }

    /**
     * 
     */
    public void activateFeatureInfoControl() {
        for (Iterator<IGPFeatureInfoElement> it = FeatureInfoFlyWeight.getInstance().getCollection().iterator(); it.hasNext();) {
            it.next().getElementControl().activate();
        }
    }

    /**
     * 
     */
    public void deactivateFeatureInfoControl() {
        for (Iterator<IGPFeatureInfoElement> it = FeatureInfoFlyWeight.getInstance().getCollection().iterator(); it.hasNext();) {
            it.next().getElementControl().deactivate();
        }
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
