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
package org.geosdi.geoplatform.gui.client.widget.cql;

import org.geosdi.geoplatform.gui.client.i18n.LayerFiltersModuleConstants;
import org.geosdi.geoplatform.gui.client.puregwt.CQLFilterHandlerManager;
import org.geosdi.geoplatform.gui.client.puregwt.CheckDataSourceHandler;
import org.geosdi.geoplatform.gui.client.widget.tab.GeoPlatformTabWidget;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CQLFilterTabWidget extends GeoPlatformTabWidget implements ICQLFilterTab, CheckDataSourceHandler
{

    public final static short TAB_WIDGET_WIDTH = CQLFilterWidget.WIDGET_WIDTH - 15;
    public final static short TAB_WIDGET_HEIGHT = CQLFilterWidget.WIDGET_HEIGHT - 97;

    public final static short TAB_FIELDSET_WIDTH = 350;
    private final GPTreePanel<GPBeanTreeModel> treePanel;
    private CQLFilterAdvancedTab advancedTab;
    private CQLFilterBasicTab basicTab;

    public CQLFilterTabWidget(boolean lazy, GPTreePanel<GPBeanTreeModel> treePanel) {
        super(lazy);
        this.treePanel = treePanel;
        CQLFilterHandlerManager.addHandler(CheckDataSourceHandler.TYPE, this);
    }

    @Override
    public void addComponents() {
        super.add(this.basicTab);
        super.add(this.advancedTab);
    }

    @Override
    public void initTab() {
    }

    @Override
    public void setWidgetProperties() {
        setSize(CQLFilterTabWidget.TAB_WIDGET_WIDTH, CQLFilterTabWidget.TAB_WIDGET_HEIGHT);
        super.setResizeTabs(true);
        super.setAnimScroll(true);
        super.setTabScroll(true);
        super.setCloseContextMenu(Boolean.FALSE);
    }

    @Override
    public void createTabItems() {
        this.advancedTab = new CQLFilterAdvancedTab(LayerFiltersModuleConstants.INSTANCE.
                CQLFilterTabWidget_advancedTabTitleText(), this.treePanel);
        this.basicTab = new CQLFilterBasicTab(LayerFiltersModuleConstants.INSTANCE.
                CQLFilterTabWidget_filterBasicTabTitleText(), this.treePanel);
    }

    @Override
    public String getCQLFilterExpression() {
        String result;
        if (advancedTab.isVisible()) {
            result = advancedTab.getCQLFilterExpression();
        } else {
            result = basicTab.getCQLFilterExpression();
        }
        return result;
    }

    @Override
    public void setCQLValue(String cqlFilter) {
        if (cqlFilter != null) {
            this.advancedTab.setCQLValue(cqlFilter);
            super.setSelection(this.advancedTab);
        }
    }

    /**
     *
     * @param checkDataSource
     */
    @Override
    public void checkDataSource(boolean checkDataSource) {
        this.basicTab.setEnabled(checkDataSource);
        this.setSelection(checkDataSource ? this.basicTab : this.advancedTab);
    }
}
