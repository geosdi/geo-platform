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
package org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.view;

import com.google.gwt.i18n.shared.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.config.LayerModuleInjector;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.binding.event.GPBeanTreeModelBindingEvent;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.panel.TimePeriodFormPanel;
import org.geosdi.geoplatform.gui.client.widget.time.panel.TimePeriodPanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Date;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class PeriodViewStrategy extends IStrategyView.AbstractPanelStrategy {

    static DateTimeFormat parseDateFormat = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.ISO_8601);

    @Override
    public GeoPlatformContentPanel buildPanel(GPTreePanel<GPBeanTreeModel> treePanel) {
        parseExtentValue(treePanel);
        TimePeriodFormPanel timePeriodFormPanel = LayerModuleInjector.MainInjector.getInstance().getTimeDimensionFormPanel();
        WidgetPropertiesHandlerManager.fireEvent(new GPBeanTreeModelBindingEvent(treePanel));
        return new TimePeriodPanel(timePeriodFormPanel);
    }

        protected void parseExtentValue(GPTreePanel<GPBeanTreeModel> treePanel) {
            String[] values = (((RasterTreeNode) treePanel.getSelectionModel().getSelectedItem()).getExtent().getValue().split("/"));
            Date dateTo;
            if (values[2] == "PT5M") {
                dateTo = new Date();
                int minutes = dateTo.getMinutes();
                int minutesDiff = minutes % 5 + 10;
                dateTo.setMinutes(minutes - minutesDiff);
                dateTo.setSeconds(0);
            } else {
                dateTo = this.parseDateFormat.parse(values[1]);
            }
            this.valuesMap.put(TypeValueEnum.DATE_FROM, this.parseDateFormat.parse(values[0]));
            this.valuesMap.put(TypeValueEnum.DATE_TO, dateTo);
            this.valuesMap.put(TypeValueEnum.PERIOD, values[2]);
        }


}


