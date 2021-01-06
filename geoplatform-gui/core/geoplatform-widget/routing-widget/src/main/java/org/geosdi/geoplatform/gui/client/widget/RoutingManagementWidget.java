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
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import org.geosdi.geoplatform.gui.client.i18n.RoutingModuleConstants;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class RoutingManagementWidget extends ContentPanel {

    private FormPanel formPanel;
    private RoutingSearchWidget pointsWidget;
    private RoutingGridWidget routingGridWidget;

   
    public RoutingManagementWidget() {
        setHeadingHtml(RoutingModuleConstants.INSTANCE.RoutingManagementWidget_headingText());
        setLayout(new FitLayout());

        this.initWidget();

        addWidgetListener(new WidgetListener() {
            @Override
            public void widgetResized(ComponentEvent ce) {
                if (getHeight() > 0) {
                    routingGridWidget.getGrid().setHeight(getHeight() - 260);
                }

            }
        });

        add(this.formPanel);

        setScrollMode(Scroll.AUTOY);
    }

    private void initWidget() {
        formPanel = new FormPanel();
        formPanel.setHeaderVisible(false);
        formPanel.setFrame(true);
        formPanel.setLayout(new FlowLayout());

        setLayoutOnChange(true);

        this.pointsWidget = new RoutingSearchWidget();

        this.formPanel.add(this.pointsWidget.getFieldSet());

        this.routingGridWidget = new RoutingGridWidget();

        this.formPanel.add(this.routingGridWidget.getDirectionsField());

        this.pointsWidget.setGridWidget(routingGridWidget);
    }

    /**
     * @return the routingGridWidget
     */
    public RoutingGridWidget getRoutingGridWidget() {
        return routingGridWidget;
    }
}
