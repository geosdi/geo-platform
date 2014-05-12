/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2014 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.WidgetListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
@Singleton
public class LayerManagementWidget extends GeoPlatformContentPanel {

    private final LayerTreePanel layerTreePanel;
    private final GPLegendPanel legendPanel;

    @Inject
    public LayerManagementWidget(LayerTreePanel theLayerTreePanel,
            GPLegendPanel theLegendPanel) {
        super(true);
        
        this.layerTreePanel = theLayerTreePanel;
        this.legendPanel = theLegendPanel;
    }

    @Override
    public void addComponent() {
        BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH);
        northData.setMargins(new Margins(5, 5, 0, 5));

        super.add(this.layerTreePanel, northData);

        BorderLayoutData southData = new BorderLayoutData(LayoutRegion.SOUTH,
                180);
        southData.setMargins(new Margins(5, 5, 5, 5));

        super.add(this.legendPanel, southData);
    }

    @Override
    public void initSize() {
    }

    @Override
    public void setPanelProperties() {
        setHeadingHtml(LayerModuleConstants.INSTANCE.LayerManagementWidget_headingText());
        setLayout(new BorderLayout());

        setLayoutOnChange(true);

        addWidgetListener(new WidgetListener() {
            @Override
            public void widgetResized(ComponentEvent ce) {
                if (getHeight() > 0) {
                    layerTreePanel.setHeight(getHeight() - 220);
                }
            }
        });

        setScrollMode(Scroll.NONE);
    }

    /**
     * @return the layerTree
     */
    public LayerTreeWidget getLayerTree() {
        return layerTreePanel.getLayerTree();
    }

    /**
     * @return the legendPanel
     */
    public GPLegendPanel getLegendPanel() {
        return legendPanel;
    }
}
