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
package org.geosdi.geoplatform.gui.client.action.menu.strategy;

import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.wfs.FeatureWidget;
import org.geosdi.geoplatform.gui.client.widget.wfs.ShowFeaturesWidget;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IActionStrategy {

    /**
     *
     */
    void showWidget();

    /**
     * @return {@link WidgetType}
     */
    WidgetType getWidgetType();

    /**
     * @param widgetType
     */
    void setWidgetType(WidgetType widgetType);

    enum WidgetType {
        EDIT_WFS_ACTION, SHOW_FEATURES;
    }

    @Singleton
    class ActionStrategy implements IActionStrategy {

        private Map<WidgetType, GeoPlatformWindow> platformWindowMap = new HashMap<>();
        private WidgetType widgetType;

        @Inject
        public ActionStrategy(FeatureWidget theFeatureWidget, ShowFeaturesWidget showFeaturesWidget) {
            this.platformWindowMap.put(WidgetType.EDIT_WFS_ACTION, theFeatureWidget);
            this.platformWindowMap.put(WidgetType.SHOW_FEATURES, showFeaturesWidget);
        }

        /**
         *
         */
        @Override
        public void showWidget() {
            this.platformWindowMap.get(this.widgetType).show();
        }

        /**
         * @return {@link WidgetType}
         */
        public WidgetType getWidgetType() {
            return this.widgetType;
        }

        /**
         * @param widgetType
         */
        @Override
        public void setWidgetType(WidgetType widgetType) {
            this.widgetType = widgetType;
        }
    }

}
