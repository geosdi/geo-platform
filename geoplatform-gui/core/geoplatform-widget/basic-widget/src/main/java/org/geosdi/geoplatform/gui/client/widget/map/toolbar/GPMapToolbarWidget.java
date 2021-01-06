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
package org.geosdi.geoplatform.gui.client.widget.map.toolbar;

import com.extjs.gxt.ui.client.widget.WidgetComponent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import org.geosdi.geoplatform.gui.action.*;
import org.geosdi.geoplatform.gui.action.button.GeoPlatformButton;
import org.geosdi.geoplatform.gui.action.button.GeoPlatformToggleButton;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.config.BasicGinInjector;
import org.geosdi.geoplatform.gui.client.widget.map.GPIconWidgetComponent;
import org.geosdi.geoplatform.gui.client.widget.menu.GPMenuBarBuilder;
import org.geosdi.geoplatform.gui.client.widget.toolbar.GeoPlatformToolbarWidget;
import org.geosdi.geoplatform.gui.configuration.MenuClientTool;
import org.geosdi.geoplatform.gui.configuration.WidgetGenericTool;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableEvent;
import org.geosdi.geoplatform.gui.configuration.action.event.ActionEnableHandler;
import org.geosdi.geoplatform.gui.configuration.toolbar.*;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;

import java.util.Collections;
import java.util.List;

/**
 * Create all items of the Map Toolbar.
 * <p/>
 * Each kind of item is builded in different way, through a distinct method of
 * {@link IGeoPlatformToolbar} interface.
 * <p/>
 * The build phase was started into this constructor, calling for every item
 * {@link WidgetGenericTool<IGeoPlatformToolbar>} the buildTool methods passing this reference
 * object. <br /> So the item inside the create method call the respective
 * method in GPMapToolbarWidget for the final build.
 *
 * @author Giuseppe La Scaleia - <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class GPMapToolbarWidget extends GeoPlatformToolbarWidget implements IGeoPlatformToolbar {

    protected GeoPlatformMap geoPlatformMap;
    private List<WidgetGenericTool<IGeoPlatformToolbar>> tools;

    /**
     * @param geoPlatformMap
     * @param tools
     */
    public GPMapToolbarWidget(GeoPlatformMap geoPlatformMap, List<WidgetGenericTool<IGeoPlatformToolbar>> tools) {
        this.geoPlatformMap = geoPlatformMap;
        this.tools = tools;
        postConstruct();
    }

    protected final void postConstruct() {
        Collections.sort(tools);
        this.initialize();
    }

    @Override
    public void initialize() {
        for (WidgetGenericTool<IGeoPlatformToolbar> tool : tools) {
            tool.buildTool(this);
        }
    }

    /**
     * Add a vertical line into Toolbar.
     */
    @Override
    public void addSeparator() {
        this.toolBar.add(new SeparatorToolItem());
    }

    /**
     * Add an Application Button into Toolbar, that configure a particular
     * action for the Application.
     *
     * @param tool component UI binded at the action
     */
    @Override
    public void addApplicationButton(ToolbarActionTool tool) {
        ToolbarApplicationAction action = (ToolbarApplicationAction) this.getAction(tool.getId());
        final Button button = new Button();
        button.setText(action.getButtonName());
        this.prepareButton(button, action, tool);
        this.toolBar.add(button);
    }

    /**
     * Add a Button into Toolbar, that interacts with the map.
     *
     * @param tool component UI binded at the action
     */
    @Override
    public void addMapButton(ToolbarActionTool tool) {
        ToolbarMapAction action = (ToolbarMapAction) this.getAction(tool.getId());
        final GeoPlatformButton button = new GeoPlatformButton();
        button.setAction(action);
        this.prepareButton(button, action, tool);
        this.toolBar.add(button);
    }

    /**
     * Add a ToggleButton Item into Toolbar, that interacts with the map.
     *
     * @param tool component UI binded at the action
     */
    @Override
    public void addMapToggleButton(ToolbarActionTool tool) {
        ToolbarMapAction action = (ToolbarMapAction) this.getAction(tool.getId());
        final GeoPlatformToggleButton button = new GeoPlatformToggleButton();
        button.setAction(action);
        this.prepareButton(button, action, tool);
        this.toolBar.add(button);
    }

    /**
     * Add a Icon Item into Toolbar.
     *
     * @param tool component UI binded at the action
     */
    @Override
    public void addIconInToolbar(IconInToolbar tool) {
        GPIconWidgetComponent gpWidgetIcon = new GPIconWidgetComponent(this.toolBar);
        Image image = new Image(BasicWidgetResources.ICONS.googleWhite());
        WidgetComponent widgetComponent = gpWidgetIcon.createWidgetComponent(image, tool.getText());
        this.toolBar.add(widgetComponent);
    }

    /**
     * Add a Button Item into Toolbar with a Menu.
     *
     * @param tool component UI binded at the action
     */
    @Override
    public void addMenuInToolBar(MenuInToolBar tool) {
        Button buttonItem = new Button(GPAccountLogged.getInstance().getName() + " @ " + GPAccountLogged.getInstance().getOrganization());
        buttonItem.setIcon(AbstractImagePrototype.create(BasicWidgetResources.ICONS.logged_user()));
        buttonItem.setId(tool.getId());
        Menu menu = new Menu();
        GPMenuBarBuilder.getIstance().buildTools(menu, tool.getTools());
        buttonItem.setMenu(menu);
        this.toolBar.add(buttonItem);
    }

    /**
     * Add a Button Item into Toolbar with a Menu list.
     *
     * @param tool component UI binded at the action
     */
    @Override
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
     * Add a separator Item into Toolbar, so any Item added after will be insert
     * in the right Toolbar region.
     */
    @Override
    public void addFillToolItem() {
        this.toolBar.add(new FillToolItem());
    }

    /**
     * @param tool
     */
    @Override
    public void addMapToogleButton(ToolbarActionEditorTool tool) {
        ToolbarMapAction action = (ToolbarMapAction) this.getAction(tool.getId());
        final GeoPlatformToggleButton button = new GeoPlatformToggleButton();
        button.setAction(action);
        this.prepareButton(button, action, tool);
        ((IOpenEditorMapAction) action).setActionTools(tool.getTools());
        this.toolBar.add(button);
    }

    /**
     * Create a Menu with an Item from each Action.
     *
     * @param actionTools the list of Action
     * @return the Menu
     */
    private Menu createMenu(List<ToolbarActionTool> actionTools) {
        Menu menu = new Menu();
        MenuActionRegistar menuRegistar = BasicGinInjector.MainInjector.getInstance().getMenuActionRegistar();
        for (ToolbarActionTool actionTool : actionTools) {
            MenuBaseAction action = (MenuBaseAction) menuRegistar.get(actionTool.getId());
            MenuItem item = new MenuItem(action.getTitle());
            item.addSelectionListener(action);
            item.setIcon(action.getImage());
            menu.add(item);
        }
        return menu;
    }

    /**
     * Retrieve the Action with a unique ID.
     *
     * @param id the Action ID
     * @return the Action joined
     */
    private ToolbarAction getAction(String id) {
        ToolbarActionRegistar toolbarRegistar = BasicGinInjector.MainInjector.getInstance().getToolbarActionRegistar();
        ToolbarAction action = toolbarRegistar.get(id, geoPlatformMap);
        if (action == null) {
            throw new NullPointerException("The action with ID " + id + " not exist");
        }
        return action;
    }

    /**
     * Prepare a Button from a Item and set an enable handler Action.
     *
     * @param button the Button to prepare
     * @param action the enable Action
     * @param tool   the Item of reference
     */
    private void prepareButton(final Button button, ToolbarAction action, WidgetGenericTool<IGeoPlatformToolbar> tool) {
        button.setId(action.getId());
        button.setToolTip(action.getTooltip());
        if (action.getImage() != null) {
            button.setIcon(action.getImage());
        }
        if ((tool.getText() != null) && !(tool.getText().isEmpty())) {
            button.setText(tool.getText());
        }
        button.addSelectionListener(action);
        action.addActionEnableHandler(new ActionEnableHandler() {

            @Override
            public void onActionEnabled(ActionEnableEvent event) {
                button.setEnabled(event.isEnabled());
            }

        });

        action.setEnabled(tool.isEnabled());
    }

    /**
     * @return the tools
     */
    public List<WidgetGenericTool<IGeoPlatformToolbar>> getTools() {
        return Collections.unmodifiableList(tools);
    }
}