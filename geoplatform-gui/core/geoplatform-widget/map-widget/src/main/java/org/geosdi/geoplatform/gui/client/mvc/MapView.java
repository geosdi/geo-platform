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

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import org.geosdi.geoplatform.gui.client.MapRegistryEnum;
import org.geosdi.geoplatform.gui.client.MapWidgetEvents;
import org.geosdi.geoplatform.gui.client.widget.MapToolbar;
import org.geosdi.geoplatform.gui.client.widget.map.GPGeocodingEventHandler;
import org.geosdi.geoplatform.gui.client.widget.map.MapLayoutWidget;
import org.geosdi.geoplatform.gui.client.widget.scale.GPMapToolsWidget;
import org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformView;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.utility.GeoPlatformUtils;
import org.gwtopenmaps.openlayers.client.feature.VectorFeature;
import org.gwtopenmaps.openlayers.client.layer.Layer;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class MapView extends GeoPlatformView {

    private MapLayoutWidget mapLayout;
    private GPGeocodingEventHandler geocoderWidget;
    private MapToolbar buttonBar;
    private GPMapToolsWidget mapToolsWidget;

    public MapView(Controller controller) {
        super(controller);
        this.mapLayout = new MapLayoutWidget();
        Registry.register(MapRegistryEnum.MAP_LAYOUT_WIDGET.toString(),
                this.mapLayout);
//        this.revGeoWidget = new ReverseGeocodingWidget(this.mapLayout);
    }

    @Override
    public void initialize() {
        /**
         * TODO : Think a way to have this component in Geocoding Module *
         */
        this.geocoderWidget = new GPGeocodingEventHandler(this.mapLayout);
        /**
         * ****************************************************************
         */
        this.mapToolsWidget = new GPMapToolsWidget(this.mapLayout);
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * com.extjs.gxt.ui.client.mvc.View#handleEvent(com.extjs.gxt.ui.client.mvc.AppEvent)
     */
    @Override
    protected void handleEvent(AppEvent event) {
        if (event.getType() == MapWidgetEvents.ATTACH_MAP_WIDGET) {
            this.mapLayout.onAddToCenterPanel();
        }
        if (event.getType() == MapWidgetEvents.ATTACH_TOOLBAR) {
            onAttachToolbar();
        }
    }

    /**
     * Activate Draw Control on Map.
     */
    public void deactivateDrawControl() {
        this.mapLayout.deactivateDrawFeature();
    }

    /**
     * Deactivate Draw Control on Map.
     */
    public void activateDrawControl() {
        this.mapLayout.activateDrawFeature();
    }

    /**
     * Attach GeoPlatform Toolbar to a LayoutManager.
     *
     * @param event
     */
    private void onAttachToolbar() {
        this.buttonBar = new MapToolbar(mapLayout,
                GeoPlatformUtils.getInstance().
                getGlobalConfiguration().getToolbarContainerTool().getGroupTools());

        if (this.buttonBar.getItemsCount() > 0) {
            LayoutManager.addComponentToNorth(buttonBar.getToolBar());
        }

        LayoutManager.normalizeNorthPanel();
    }

    /**
     * Erase single Feature in MapLayout Vector Layer.
     *
     * @param event
     */
    public void eraseFeature(VectorFeature vf) {
        this.mapLayout.eraseFeature(vf);
    }

    /**
     * Update Map Size.
     */
    public void updateMapSize() {
        this.mapLayout.updateMapSize();
    }

    /**
     * Add Layer to the Map.
     *
     * @param layer
     */
    public void addLayer(Layer layer) {
        this.mapLayout.getMap().addLayer(layer);
    }

    /**
     * Redraw Vector Layer.
     */
    public void redrawVectorLayer() {
        this.mapLayout.redrawVectorLayer();
    }

    /**
     * Draw Feature on the Map.
     *
     * @param feature
     */
    public void drawFeature(VectorFeature feature) {
        this.mapLayout.drawFeature(feature);
    }

    /**
     * Update the name of the user into the toolbar.
     *
     * @param event contain the new name
     */
    public void userChangeHisName(AppEvent event) {
        ToolBar toolBar = buttonBar.getToolBar();
        Component component = toolBar.getItemByItemId("USER_MENU");
        Button button = (Button) component;
        String domain = button.getHtml();
        button.setText(event.getData().toString() + domain.substring(
                domain.indexOf("@") - 1));
    }
}
