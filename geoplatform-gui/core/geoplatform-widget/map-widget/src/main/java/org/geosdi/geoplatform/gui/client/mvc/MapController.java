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
package org.geosdi.geoplatform.gui.client.mvc;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import org.geosdi.geoplatform.gui.client.MapWidgetEvents;
import org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformController;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class MapController extends GeoPlatformController {

    public MapController() {
        registerEventTypes(MapWidgetEvents.ATTACH_MAP_WIDGET,
                MapWidgetEvents.ATTACH_TOOLBAR, MapWidgetEvents.ERASE_FEATURE,
                MapWidgetEvents.DRAW_FEATURE, GeoPlatformEvents.UPDATE_CENTER,
                GeoPlatformEvents.USER_UPDATE_HIS_NAME);
    }

    @Override
    public void initialize() {
        this.view = new MapView(this);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * com.extjs.gxt.ui.client.mvc.Controller#handleEvent(com.extjs.gxt.ui.client
     * .mvc.AppEvent)
     */
    @Override
    public void handleEvent(AppEvent event) {
        if (event.getType() == MapWidgetEvents.ERASE_FEATURE) {
            onEraseFeature(event);
        }

        if (event.getType() == GeoPlatformEvents.UPDATE_CENTER) {
            onUpdateCenter();
        }

        if (event.getType() == MapWidgetEvents.DRAW_FEATURE) {
            onDrawFeature(event);
        }

        if (event.getType() == GeoPlatformEvents.USER_UPDATE_HIS_NAME) {
            onUserChangeHisName(event);
        }

        forwardToView(view, event);
    }

    private void onDrawFeature(AppEvent event) {
        ((MapView) this.view).drawFeature((VectorFeature) event.getData());
    }

    public void onRedrawVectorLayer() {
        ((MapView) this.view).redrawVectorLayer();
    }

    /**
     *
     * @param event
     */
    private void onEraseFeature(AppEvent event) {
        ((MapView) this.view).eraseFeature((VectorFeature) event.getData());
    }

    /**
     * Update Center Widget.
     */
    private void onUpdateCenter() {
        ((MapView) this.view).updateMapSize();
    }

    /**
     * Update the name of the user into the toolbar.
     */
    private void onUserChangeHisName(AppEvent event) {
        ((MapView) this.view).userChangeHisName(event);
    }
}
