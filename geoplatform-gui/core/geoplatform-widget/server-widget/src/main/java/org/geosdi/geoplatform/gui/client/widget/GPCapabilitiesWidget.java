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

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.client.i18n.ServerModuleConstants;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPCapabilitiesWidget extends GeoPlatformWindow {

    private GridLayersWidget gridLayers;
    private TreePanel tree;

    public GPCapabilitiesWidget(boolean lazy) {
        super(lazy);
    }

    /**
     * @param lazy    If true the component will not build in Construction Fase
     *                otherwise set to False
     * @param theTree
     */
    public GPCapabilitiesWidget(boolean lazy, TreePanel theTree) {
        super(lazy);
        tree = theTree;
    }

    @Override
    public void addComponent() {
        gridLayers = new GridLayersWidget(tree);
        super.add(gridLayers.getFormPanel());
    }

    @Override
    public void reset() {
        gridLayers.resetComponents();
        if (super.isCollapsed()) {
            super.setExpanded(true);
        }
    }

    @Override
    public void initSize() {
        super.setSize(600, 530);
        super.setHeadingHtml(ServerModuleConstants.INSTANCE.GPCapabilitiesWidget_headingText());
    }

    @Override
    public void setWindowProperties() {
        super.setResizable(false);
        super.addWindowListener(new WindowListener() {

            @Override
            public void windowShow(WindowEvent we) {
                gridLayers.loadServers();
            }

        });
        super.addWidgetListener(new WidgetListener() {

            @Override
            public void widgetAttached(ComponentEvent ce) {
                gridLayers.getGrid().setHeight(400);
            }

        });
        super.setLayout(new FitLayout());
        super.setModal(false);
        super.setCollapsible(true);
        super.setPlain(true);
    }
}