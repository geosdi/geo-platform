/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.gui.client.widget.map.toolbar;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import java.util.Collections;
import java.util.List;
import org.geosdi.geoplatform.gui.action.ToolbarAction;
import org.geosdi.geoplatform.gui.action.ToolbarActionRegistar;
import org.geosdi.geoplatform.gui.action.ToolbarApplicationAction;
import org.geosdi.geoplatform.gui.action.ToolbarMapAction;
import org.geosdi.geoplatform.gui.action.button.GeoPlatformButton;
import org.geosdi.geoplatform.gui.action.button.GeoPlatformToggleButton;
import org.geosdi.geoplatform.gui.action.event.ActionDisabledEvent;
import org.geosdi.geoplatform.gui.action.event.ActionEnabledEvent;
import org.geosdi.geoplatform.gui.action.event.ActionHandler;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.widget.map.GPIconWidgetComponent;
import org.geosdi.geoplatform.gui.client.widget.menu.MenuUtilityBuilder;
import org.geosdi.geoplatform.gui.client.widget.toolbar.GeoPlatformToolbarWidget;
import org.geosdi.geoplatform.gui.configuration.ActionClientTool;
import org.geosdi.geoplatform.gui.configuration.GenericClientTool;
import org.geosdi.geoplatform.gui.configuration.IGeoPlatformToolbarWidget;
import org.geosdi.geoplatform.gui.configuration.IconInToolbar;
import org.geosdi.geoplatform.gui.configuration.MenuClientTool;
import org.geosdi.geoplatform.gui.configuration.menubar.MenuInToolBar;
import org.geosdi.geoplatform.gui.global.security.GPUserGuiComponents;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email  giuseppe.lascaleia@geosdi.org
 */
public class GPMapToolbarWidget extends GeoPlatformToolbarWidget
        implements IGeoPlatformToolbarWidget {

    protected GeoPlatformMap geoPlatformMap;
    private List<GenericClientTool> tools;

    public GPMapToolbarWidget(GeoPlatformMap geoPlatformMap,
                              List<GenericClientTool> tools) {
        this.geoPlatformMap = geoPlatformMap;
        setTools(tools);
        initialize();
    }

    @Override
    public void initialize() {
        for (GenericClientTool tool : tools) {
            tool.buildTool(this);
        }
    }

    /**
     * Add a Vertical Line in the Toolbar
     */
    @Override
    public void addSeparator() {
        this.toolBar.add(new SeparatorToolItem());
    }

    /**
     * Add a new Application Button to the Toolbar. An Application Button is a
     * kind of button that configure a particular action for the Application.
     *
     * @param tool component UI binded at the action
     */
    @Override
    public void addApplicationButton(ActionClientTool tool) {
        ToolbarApplicationAction action = (ToolbarApplicationAction) this.getAction(tool.getId());

        final Button button = new Button();
        button.setText(action.getButtonName());
        this.prepareButton(button, action, tool);

        this.toolBar.add(button);
    }

    /**
     * Add a new Map Button to the Toolbar. A Map Button is a kind of button
     * that interacts with the map.
     *
     * @param tool component UI binded at the action
     */
    @Override
    public void addMapButton(ActionClientTool tool) {
        ToolbarMapAction action = (ToolbarMapAction) this.getAction(tool.getId());

        final GeoPlatformButton button = new GeoPlatformButton();
        button.setAction(action);
        this.prepareButton(button, action, tool);

        this.checkPermission(action);

        this.toolBar.add(button);
    }

    /**
     * Add a new Map ToggleButton to the Toolbar. A Map ToggleButton is a kind
     * of button that interacts with the map.
     *
     * @param tool component UI binded at the action
     */
    @Override
    public void addMapToggleButton(ActionClientTool tool) {
        ToolbarMapAction action = (ToolbarMapAction) this.getAction(tool.getId());

        final GeoPlatformToggleButton button = new GeoPlatformToggleButton();
        button.setAction(action);
        this.prepareButton(button, action, tool);

        this.checkPermission(action);

        this.toolBar.add(button);
    }

    @Override
    public void addGoogleIcon(IconInToolbar tool) {
        GPIconWidgetComponent widgetIcon = new GPIconWidgetComponent(this.toolBar);

        this.toolBar.add(new FillToolItem());
        this.toolBar.add(widgetIcon.createWidgetComponent(BasicWidgetResources.ICONS.googleWhite().createImage(),
                                                          tool.getText()));
    }

    @Override
    public void addMenuInToolBar(MenuInToolBar tool) {
        Button buttonItem = new Button(
                GPUserGuiComponents.getInstance().getUserName());
        buttonItem.setIcon(BasicWidgetResources.ICONS.logged_user());
        buttonItem.setId(tool.getId());

        Menu menu = new Menu();
        MenuUtilityBuilder.buildTools(menu, tool.getTools());
        buttonItem.setMenu(menu);

        this.toolBar.add(buttonItem);
    }

    /**
     * Create a Button with a Menu
     *
     * @param tool component UI binded at the action
     */
    public void addMenuButton(MenuClientTool tool) {
        ToolbarAction action = this.getAction(tool.getId());

        Button button = new Button();
        button.setId(tool.getId());
//        button.setText(action.getButtonName());
        button.setIcon(action.getImage());
        button.setEnabled(tool.isEnabled());

        button.setMenu(this.createMenu(tool.getActionTools()));

        toolBar.add(button);
    }

    /**
     *
     * @param actionTools
     * @return Menu
     */
    private Menu createMenu(List<ActionClientTool> actionTools) {
        Menu menu = new Menu();
        for (ActionClientTool actionTool : actionTools) {
            MenuBaseAction action = (MenuBaseAction) MenuActionRegistar.get(
                    actionTool.getId());

            MenuItem item = new MenuItem(action.getTitle());
            item.addSelectionListener(action);
            item.setIcon(action.getImage());
            menu.add(item);
        }
        return menu;
    }

    /**
     * Retrieve the action from Register component 
     * 
     * @param id
     * @return action
     */
    private ToolbarAction getAction(String id) {
        ToolbarAction action = ToolbarActionRegistar.get(id, geoPlatformMap);
        if (action == null) {
            throw new NullPointerException("The action with ID " + id + " is non existent");
        }
        return action;
    }

    // TODO
    private void checkPermission(ToolbarAction action) {
//        if (GPUserGuiComponents.getInstance().
//                hasComponentPermission(action.getId())) {
//        }
    }

    private void prepareButton(final Button button, ToolbarAction action,
                               GenericClientTool tool) {
        button.setId(action.getId());
        button.setToolTip(action.getTooltip());
        button.setIcon(action.getImage());
        button.addSelectionListener(action);

        action.addActionHandler(new ActionHandler() {

            @Override
            public void onActionEnabled(ActionEnabledEvent event) {
                button.setEnabled(true);
            }

            @Override
            public void onActionDisabled(ActionDisabledEvent event) {
                button.setEnabled(false);
            }
        });

        action.setEnabled(tool.isEnabled());
    }

    /**
     * @return the tools
     */
    public List<GenericClientTool> getTools() {
        return tools;
    }

    /**
     * @param tools
     *            the tools to set
     */
    public void setTools(List<GenericClientTool> tools) {
        Collections.sort(tools);
        this.tools = tools;
    }
}
