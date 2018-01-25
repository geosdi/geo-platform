/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2018 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.impl.tree.menu.strategy;

import com.extjs.gxt.ui.client.widget.menu.AdapterMenuItem;
import com.extjs.gxt.ui.client.widget.menu.DateMenu;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Maps;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import java.util.Map;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.action.menu.MenuCheckAction;
import org.geosdi.geoplatform.gui.action.tree.menu.TreeMenuActionRegistar;
import org.geosdi.geoplatform.gui.client.i18n.ConfigurationModuleConstants;
import org.geosdi.geoplatform.gui.configuration.GPCheckMenuItem;
import org.geosdi.geoplatform.gui.configuration.GPDateMenuItem;
import org.geosdi.geoplatform.gui.configuration.GPGroupMenuItem;
import org.geosdi.geoplatform.gui.configuration.GPMenuGenericTool;
import org.geosdi.geoplatform.gui.configuration.GPMenuItem;
import org.geosdi.geoplatform.gui.configuration.composite.menu.store.StoreCompositeKey;
import org.geosdi.geoplatform.gui.configuration.composite.menu.strategy.GPTreeMenuStrategy;
import org.geosdi.geoplatform.gui.impl.menu.binder.GPMenuActionBinder;
import org.geosdi.geoplatform.gui.impl.menu.binder.MenuActionBinder;
import org.geosdi.geoplatform.gui.impl.tree.menu.config.TreeMenuGinInjector;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class AbstractTreeMenuStrategy implements GPTreeMenuStrategy {

    static {
        registar = TreeMenuGinInjector.MainInjector.getInstance().getTreeMenuActionRegistar();
    }
    private static final TreeMenuActionRegistar registar;
    //
    private final Map<StoreCompositeKey, Menu> menuRegistar = Maps.<StoreCompositeKey, Menu>newHashMap();
    private final TreePanel treePanel;
    private final MenuActionBinder menuActionBinder;

    public AbstractTreeMenuStrategy(TreePanel theTreePanel) {
        this.treePanel = theTreePanel;
        this.menuActionBinder = new GPMenuActionBinder(this);
    }

    protected final Menu buildTools(Menu menu,
            List<? extends GPMenuGenericTool> tools) {
        this.menuActionBinder.bindTools(menu, tools);

        return menu;
    }

    protected final Menu buildMenu(StoreCompositeKey key,
            List<? extends GPMenuGenericTool> tools) {

        if (this.menuRegistar.get(key) != null) {
            return this.menuRegistar.get(key);
        }

        return this.buildTools(this.bindMenu(key), tools);
    }

    @Override
    public void addMenuItem(GPMenuItem tool, Menu menu) {
        MenuAction action = (MenuAction) registar.get(tool.getId(),
                treePanel);

        this.menuActionBinder.bindMenuBaseAction(action, tool, menu);
    }

    @Override
    public void addCheckMenuItem(GPCheckMenuItem tool,
            final Menu menu) {
        MenuCheckAction action = (MenuCheckAction) registar.get(tool.getId(),
                treePanel);

        this.menuActionBinder.bindMenuCheckAction(action, tool, menu);
    }

    @Override
    public void addDateMenu(GPDateMenuItem tool,
            Menu menu) {
        MenuItem date = new MenuItem(ConfigurationModuleConstants.INSTANCE.AbstractTreeMenuStrategy_chooseADateText());
        menu.add(date);
        date.setSubMenu(new DateMenu());
    }

    @Override
    public void addGroupMenuItem(GPGroupMenuItem tool,
            Menu menu) {
        this.menuActionBinder.bindGroupMenuItem(tool, menu);
    }

    @Override
    public void addWidget(Widget widget, Menu menu) {
        menu.add(new AdapterMenuItem(widget));
    }

    @Override
    public Menu bindMenu(StoreCompositeKey key) {
        Menu menu = new Menu();
        this.menuRegistar.put(key, menu);

        return menu;
    }

    @Override
    public TreePanel getTree() {
        return this.treePanel;
    }
}
