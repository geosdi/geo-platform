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
package org.geosdi.geoplatform.gui.client.widget.menu;

import com.extjs.gxt.ui.client.widget.menu.DateMenu;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.action.menu.MenuCheckAction;
import org.geosdi.geoplatform.gui.action.menu.OAuth2MenuBaseAction;
import org.geosdi.geoplatform.gui.action.menu.event.MenuActionChangeIconEvent;
import org.geosdi.geoplatform.gui.action.menu.handler.MenuActionChangeIconHandler;
import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetConstants;
import org.geosdi.geoplatform.gui.configuration.*;
import org.geosdi.geoplatform.gui.configuration.menubar.IGeoPlatformMenubar;
import org.geosdi.geoplatform.gui.configuration.menubar.OAuth2MenuBarClientTool;
import org.geosdi.geoplatform.gui.impl.menu.binder.GPMenuActionBinder;
import org.geosdi.geoplatform.gui.impl.menu.binder.MenuActionBinder;

import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @TODO Think a way to build with Gin
 * @email nazzareno.sileno@geosdi.org
 */
public class GPMenuBarBuilder implements IGeoPlatformMenubar {

    private static final GPMenuBarBuilder INSTANCE = new GPMenuBarBuilder();
    private final MenuActionBinder menuActionBinder;
    private final MenuActionRegistar menuRegistar = BasicGinInjector.MainInjector.getInstance().getMenuActionRegistar();

    private GPMenuBarBuilder() {
        this.menuActionBinder = new GPMenuActionBinder(this);
    }

    public static GPMenuBarBuilder getIstance() {
        return GPMenuBarBuilder.INSTANCE;
    }

    /**
     * @param tools
     * @param menu
     */
    public void buildTools(Menu menu, List<? extends GPMenuGenericTool> tools) {
        this.menuActionBinder.bindTools(menu, tools);
    }

    /**
     * Add a Separator in Menu
     *
     * @param menu
     */
    @Override
    public void addMenuSeparator(Menu menu) {
        menu.add(new SeparatorMenuItem());
    }

    /**
     * Add Simple MenuItem to Menu
     *
     * @param tool
     * @param menu
     */
    @Override
    public void addMenuItem(GPMenuItem tool, Menu menu) {
        MenuBaseAction action = (MenuBaseAction) menuRegistar.get(tool.getId());
        this.menuActionBinder.bindMenuBaseAction(action, tool, menu);
    }

    /**
     * Add CheckMenuItem to a Menu
     *
     * @param tool
     * @param menu
     */
    @Override
    public void addCheckMenuItem(GPCheckMenuItem tool, final Menu menu) {
        MenuCheckAction action = (MenuCheckAction) menuRegistar.get(tool.getId());
        this.menuActionBinder.bindMenuCheckAction(action, tool, menu);
    }

    /**
     * Add a DateMenu Item to Menu
     *
     * @param tool
     * @param menu
     */
    @Override
    public void addDateMenu(GPDateMenuItem tool, Menu menu) {
        MenuItem date = new MenuItem(BasicWidgetConstants.INSTANCE.MenuUtility_chooseADateText());
        menu.add(date);
        date.setSubMenu(new DateMenu());
    }

    /**
     * Add a MenuItem with sub menu
     *
     * @param tool
     * @param menu
     */
    @Override
    public void addOAuth2MenuItem(OAuth2MenuBarClientTool tool, Menu menu) {
        OAuth2MenuBaseAction action = (OAuth2MenuBaseAction) menuRegistar.get(tool.getId());
        final MenuItem item = new MenuItem(tool.getText());
        if (action != null) {
            action.setId(tool.getId());
            item.setIcon(action.getImage());
            item.setItemId(action.getId());
            item.addSelectionListener(action);

            this.menuActionBinder.addMenuActionEnableHandler(action, item);
            action.addMenuActionChangeIconHandler(new MenuActionChangeIconHandler() {

                @Override
                public void onActionChangeIcon(MenuActionChangeIconEvent event) {
                    item.setIcon(event.getImage());
                }
            });

            action.setEnabled(tool.isEnabled());
            action.setGoogleAuthUrl(tool.getGoogleAuthUrl());
            action.setGoogleClientId(tool.getGoogleClientId());
            action.setScope(tool.getScope());
        }
        menu.add(item);
    }

    /**
     * Add a MenuItem with sub menu
     *
     * @param tool
     * @param menu
     */
    @Override
    public void addGroupMenuItem(GPGroupMenuItem tool, Menu menu) {
        this.menuActionBinder.bindGroupMenuItem(tool, menu);
    }
}
