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
package org.geosdi.geoplatform.gui.client.widget;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.Widget;
import org.geosdi.geoplatform.gui.client.i18n.GeocodingModuleConstants;
import org.geosdi.geoplatform.gui.client.plugin.factory.geocoding.GeoPlatformGeocoderFactory;
import org.geosdi.geoplatform.gui.configuration.geocoding.plugin.GeocoderPluginType;
import org.geosdi.geoplatform.gui.configuration.geocoding.plugin.IGPGeocoderPluginManager;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class GeocoderManagementWidget extends ContentPanel {

    private boolean initialized;
    private IGPGeocoderPluginManager geocoderPluginManager;

    public GeocoderManagementWidget() {
        setHeadingHtml(
                GeocodingModuleConstants.INSTANCE.GeocoderManagementWidget_headingText());
        setLayout(new FitLayout());

        setLayoutOnChange(true);

        addWidgetListener(new WidgetListener() {

            @Override
            public void widgetResized(ComponentEvent ce) {
                if ((getHeight() > 0)
                        && (geocoderPluginManager.isPluginManagerInitialized())) {
                    geocoderPluginManager.managePluginsProperties(getHeight());
                }

            }

        });

        setScrollMode(Scroll.AUTOY);
    }

    /**
     * Build Geocoding Management Widget with specific Geocoder Plugin Manager
     *
     * @param geocoderType
     */
    public void buildGeocoderManagementWidget(GeocoderPluginType geocoderType) {
        this.geocoderPluginManager = GeoPlatformGeocoderFactory.getDefaultPluginManager(
                geocoderType);

        this.geocoderPluginManager.buildPlugins();

        if (!initialized) {
            add((Widget) geocoderPluginManager.getWidget());
            this.initialized = true;
        }
    }

}
