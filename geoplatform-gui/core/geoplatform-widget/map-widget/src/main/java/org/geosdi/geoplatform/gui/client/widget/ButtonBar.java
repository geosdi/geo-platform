/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-plartform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2010 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import org.geosdi.geoplatform.gui.action.GeoPlatformButtonObserver;
import org.geosdi.geoplatform.gui.action.GeoPlatformToolbarAction;
import org.geosdi.geoplatform.gui.action.ToolbarActionRegistar;
import org.geosdi.geoplatform.gui.action.ToolbarApplicationAction;
import org.geosdi.geoplatform.gui.action.ToolbarMapAction;
import org.geosdi.geoplatform.gui.action.button.GeoPlatformToggleButton;
import org.geosdi.geoplatform.gui.action.menu.MenuActionRegistar;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.widget.map.MapLayoutWidget;
import org.geosdi.geoplatform.gui.configuration.ActionClientTool;
import org.geosdi.geoplatform.gui.configuration.GenericClientTool;
import org.geosdi.geoplatform.gui.configuration.MenuClientTool;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToggleButton;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

/**
 * @author giuseppe
 * 
 */
public class ButtonBar extends LayoutContainer {

	public static final String TOOLBAR_SEPARATOR = "ToolbarSeparator";

	private VerticalPanel vp;
	private ToolBar toolBar;
	private MapLayoutWidget mapLayoutWidget;

	private GeoPlatformButtonObserver buttonObserver;

	/**
	 * Constructor
	 * 
	 * @param mapLayoutWidget
	 */
	public ButtonBar(MapLayoutWidget mapLayoutWidget) {
		super();
		this.vp = new VerticalPanel();
		this.toolBar = new ToolBar();
		this.mapLayoutWidget = mapLayoutWidget;
		this.mapLayoutWidget.setButtonBar(this);
		this.buttonObserver = new GeoPlatformButtonObserver();
		initialize();
	}

	/**
	 * Widget initialize
	 */
	private void initialize() {
		List<GenericClientTool> tools = this.mapLayoutWidget.getTools();
		for (GenericClientTool tool : tools) {
			String id = tool.getId();
			if (id.equals(TOOLBAR_SEPARATOR)) {
				addSeparator();
			} else if (tool instanceof MenuClientTool) {
				addMenuButton((MenuClientTool) tool,
						(ToolbarApplicationAction) ToolbarActionRegistar.get(
								id, mapLayoutWidget));
			} else {
				GeoPlatformToolbarAction action = ToolbarActionRegistar.get(id,
						mapLayoutWidget);

				action.setEnabled(((ActionClientTool) tool).isEnabled());
				action.setId(id);

				if (action instanceof ToolbarApplicationAction)
					addApplicationButton((ToolbarApplicationAction) action);

				if (action instanceof ToolbarMapAction) {
					if (((ActionClientTool) tool).getType().equals("toggle")) {
						addMapToogleButton((ToolbarMapAction) action);
					} else {
						addMapButton((ToolbarMapAction) action);
					}
				}
			}
		}
		vp.add(toolBar);
		add(vp);
	}

	/**
	 * Create a Button with a Menu
	 * 
	 * @param tool
	 */
	public void addMenuButton(MenuClientTool tool,
			ToolbarApplicationAction action) {
		Button button = new Button();
		button.setId(tool.getId());
		button.setWidth(60);
		button.setText(action.getButtonName());
		button.setIcon(action.getImage());
		button.setEnabled(tool.isEnabled());

		button.setMenu(createMenu(tool.getActionTools()));

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
			MenuBaseAction action = (MenuBaseAction) MenuActionRegistar
					.get(actionTool.getId());
			MenuItem item = new MenuItem(action.getTitle());
			item.addSelectionListener(action);
			item.setIcon(action.getImage());
			menu.add(item);
		}
		return menu;
	}

	/**
	 * Add a Vertical Line in the Toolbar
	 */
	public void addSeparator() {
		this.toolBar.add(new SeparatorToolItem());
	}

	/**
	 * Add a new Map Button to the Toolbar. A Map Button is a kind of button
	 * that interacts with the map.
	 * 
	 * @param action
	 */
	public void addMapButton(ToolbarMapAction action) {
		Button button = new Button();
		button.setId(action.getId());
		button.setToolTip(action.getTooltip());
		button.setIcon(action.getImage());
		button.addSelectionListener(action);
		button.setEnabled(action.isEnabled());
		this.toolBar.add(button);
	}

	/**
	 * Add a new Application Button to the Toolbar. An Application Button is a
	 * kind of button that configure a particular action for the Application.
	 * 
	 * @param action
	 */
	public void addApplicationButton(ToolbarApplicationAction action) {
		Button button = new Button();
		button.setId(action.getId());
		button.setText(action.getButtonName());
		button.setIcon(action.getImage());
		button.addSelectionListener(action);
		button.setEnabled(action.isEnabled());
		this.toolBar.add(button);
	}

	/**
	 * Add a new Map ToggleButton to the Toolbar. A Map ToggleButton is a kind
	 * of button that interacts with the map.
	 * 
	 * @param action
	 */
	public void addMapToogleButton(ToolbarMapAction action) {
		GeoPlatformToggleButton button = new GeoPlatformToggleButton();
		button.setAction(action);
		button.setId(action.getId());
		button.setToolTip(action.getTooltip());
		button.setIcon(action.getImage());
		button.addSelectionListener(action);
		button.setEnabled(action.isEnabled());

		this.toolBar.add(button);
	}

	/**
	 * @return the toolBar
	 */
	public ToolBar getToolBar() {
		return toolBar;
	}

	/**
	 * Checks for a Togglr Button pressed
	 * 
	 * @return boolean
	 */
	public boolean isTogglePressed() {
		return this.buttonObserver.isButtonPressed();
	}

	/**
	 * 
	 * @param button
	 */
	public void setPressedButton(ToggleButton button) {
		this.buttonObserver.setButtonPressed(button);
	}

	public Button getPressedButton() {
		return this.buttonObserver.getButtonPressed();
	}

	public void changeButtonState() {
		this.buttonObserver.changeButtonState();
	}
}
