/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.widget;

import java.util.List;

import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.configuration.IMenuBarContainerTool;
import org.geosdi.geoplatform.gui.configuration.menubar.CheckMenuClientTool;
import org.geosdi.geoplatform.gui.configuration.menubar.DateMenuClientTool;
import org.geosdi.geoplatform.gui.configuration.menubar.GroupMenuClientTool;
import org.geosdi.geoplatform.gui.configuration.menubar.MenuBarCategory;
import org.geosdi.geoplatform.gui.configuration.menubar.MenuBarClientTool;

import com.extjs.gxt.ui.client.widget.menu.CheckMenuItem;
import com.extjs.gxt.ui.client.widget.menu.DateMenu;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuBar;
import com.extjs.gxt.ui.client.widget.menu.MenuBarItem;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;

/**
 * @author giuseppe
 * 
 */
public class MenuBarWidget {

    public static final String MENU_BAR_SEPARATOR = "MenuBarSeparator";
    private MenuBar bar;
    private IMenuBarContainerTool menuBarContainerTool;

    public MenuBarWidget(IMenuBarContainerTool menuBarContainerTool) {
        this.bar = new MenuBar();
        bar.setBorders(true);
        bar.setStyleAttribute("borderTop", "none");
        this.menuBarContainerTool = menuBarContainerTool;
        initialize();
    }

    /**
     * Widget initialize
     */
    private void initialize() {
        for (MenuBarCategory category : this.menuBarContainerTool.getCategories()) {
            Menu menu = new Menu();
            this.bar.add(new MenuBarItem(category.getText(), menu));
            addCategory(category, menu);
        }

    }

    /**
     * Add MenuBarItem to MenuBar
     *
     * @param category
     */
    public void addCategory(MenuBarCategory category, Menu menu) {
        // TODO Auto-generated method stub
        buildTools(menu, category.getTools());
    }

    /**
     *
     * @param tools
     * @param menu
     */
    public void buildTools(Menu menu, List<MenuBarClientTool> tools) {
        for (MenuBarClientTool tool : tools) {
            if (tool.getId().equals(MENU_BAR_SEPARATOR)) {
                addMenuSeparator(menu);
            } else {
                checkToolType(tool, menu);
            }
        }
    }

    /**
     *
     * @param tool
     * @param menu
     */
    private void checkToolType(MenuBarClientTool tool, Menu menu) {
        // TODO Auto-generated method stub
        if (tool instanceof CheckMenuClientTool) {
            addCheckMenuItem((CheckMenuClientTool) tool, menu);
        } else if (tool instanceof DateMenuClientTool) {
            addDateMenu(menu);
        } else if (tool instanceof GroupMenuClientTool) {
            addGroupMenuItem((GroupMenuClientTool) tool, menu);
        } else {
            addMenuItem(tool, menu);
        }
    }

    /**
     * Add Simple MenuItem to Menu
     *
     * @param tool
     * @param menu
     */
    public void addMenuItem(MenuBarClientTool tool, Menu menu) {
        // TODO Auto-generated method stub
        MenuBaseAction action = (MenuBaseAction) MenuActionRegistar.get(tool.getId());

        MenuItem item = new MenuItem(tool.getText());

        if (action != null) {
            action.setId(tool.getId());
            item.setIcon(action.getImage());
            item.setItemId(action.getId());
            item.addSelectionListener(action);
        }
        menu.add(item);
    }

    /**
     * Add a MenuItem with sub menu
     *
     * @param tool
     */
    public void addGroupMenuItem(GroupMenuClientTool tool, Menu menu) {
        // TODO Auto-generated method stub
        MenuItem item = new MenuItem(tool.getText());
        menu.add(item);
        Menu subMenu = new Menu();
        buildTools(subMenu, tool.getTools());
        item.setSubMenu(subMenu);
    }

    /**
     * Add a DateMenu Item to Menu
     *
     * @param menu
     */
    public void addDateMenu(Menu menu) {
        // TODO Auto-generated method stub
        MenuItem date = new MenuItem("Choose a Date");
        menu.add(date);
        date.setSubMenu(new DateMenu());
    }

    /**
     * Add CheckMenuItem to a Menu
     *
     * @param tool
     * @param menu
     */
    public void addCheckMenuItem(CheckMenuClientTool tool, Menu menu) {
        // TODO Auto-generated method stub
        MenuAction action = MenuActionRegistar.get(tool.getId());
        action.setId(tool.getId());
        CheckMenuItem item = new CheckMenuItem(tool.getText());
        item.setItemId(action.getId());
        item.setChecked(tool.isChecked());

        if (action != null) {
            item.addSelectionListener(action);
        }
        menu.add(item);
    }

    /**
     * Add a Separator in Menu
     */
    public void addMenuSeparator(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(new SeparatorMenuItem());
    }

    /**
     * @return the bar
     */
    public MenuBar getBar() {
        return bar;
    }
}
