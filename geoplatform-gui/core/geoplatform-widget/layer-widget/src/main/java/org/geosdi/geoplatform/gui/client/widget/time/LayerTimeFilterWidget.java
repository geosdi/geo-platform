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
package org.geosdi.geoplatform.gui.client.widget.time;

import org.geosdi.geoplatform.gui.client.config.LayerModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.puregwt.filter.IGPFilterWidgetHandler;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformContentPanel;
import org.geosdi.geoplatform.gui.client.widget.GeoPlatformWindow;
import org.geosdi.geoplatform.gui.client.widget.time.panel.strategy.view.IStrategyView;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Nazzareno Sileno <nazzareno.sileno@geosdi.org>
 * @author Vito Salvia <vito.salvia@gmail.com>
 */
public class LayerTimeFilterWidget extends GeoPlatformWindow implements IGPFilterWidgetHandler {

    public final static String LAYER_TIME_DELIMITER = " - [";
    public final static short WIDGET_HEIGHT = 350;
    public final static short WIDGET_WIDTH = 430;
    //
    private GPTreePanel<GPBeanTreeModel> treePanel;
    private GeoPlatformContentPanel currentPanel;

    /**
     * @param theTreePanel
     */
    public LayerTimeFilterWidget(GPTreePanel<GPBeanTreeModel> theTreePanel) {
        super(TRUE);
        checkArgument(theTreePanel != null, "The Parameter treePanel must not be null.");
        this.treePanel = theTreePanel;
        WidgetPropertiesHandlerManager.addHandler(TYPE, this);
    }


    @Override
    protected void onAttach() {
        super.onAttach();
        super.removeAll();
        IStrategyView.StrategyView s = LayerModuleInjector.MainInjector.getInstance().getStrategyPanel();
        this.currentPanel = s.getPanel(this.treePanel);
        super.add(this.currentPanel);
    }

    @Override
    public void addComponent() {
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        this.currentPanel.removeFromParent();
    }

    @Override
    public void initSize() {
        super.setWidth(WIDGET_WIDTH);
        super.setAutoHeight(TRUE);
    }

    @Override
    public void setWindowProperties() {
        super.setHeadingHtml(LayerModuleConstants.INSTANCE.LayerTimeFilterWidget_timeFilderHeadingText());
        super.setCollapsible(TRUE);
        super.setResizable(FALSE);
    }
}