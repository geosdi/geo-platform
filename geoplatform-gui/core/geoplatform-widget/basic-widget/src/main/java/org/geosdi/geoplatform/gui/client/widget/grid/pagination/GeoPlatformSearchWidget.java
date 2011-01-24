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
package org.geosdi.geoplatform.gui.client.widget.grid.pagination;

import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;
import com.extjs.gxt.ui.client.event.WindowListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

/**
 * @author giuseppe
 * 
 */
public abstract class GeoPlatformSearchWidget<T extends GeoPlatformBeanModel>
		extends Window {

	private VerticalPanel vp;
	protected FormPanel formPanel;
	protected ListStore<T> store;
	protected Grid<T> grid;
	protected TextField<String> search;
	protected RpcProxy<PagingLoadResult<T>> proxy;
	protected PagingLoader<PagingLoadResult<ModelData>> loader;
	protected PagingToolBar toolBar;
	protected Button select;
	protected Button cancel;
	protected SearchStatus searchStatus;

	protected String searchText;

	private boolean initialized;

	/**
	 * 
	 * @param lazy
	 */
	public GeoPlatformSearchWidget(boolean lazy) {
		if (!lazy)
			init();
	}

	private void init() {
		// TODO Auto-generated method stub
		if (!isInitialized()) {
			initWindow();
			initVerticalPanel();
			initFormPanel();
			add(vp);
			this.initialized = true;
		}
	}

	private void initWindow() {
		setModal(true);
		setResizable(false);
		setLayout(new FlowLayout());
		setPlain(true);
		setMaximizable(false);

		addWindowListener(new WindowListener() {

			@Override
			public void windowHide(WindowEvent we) {
				cancel();
			}

		});

		setWindowProperties();
	}

	private void initVerticalPanel() {
		vp = new VerticalPanel();
		vp.setSpacing(10);
		createStore();
		initGrid();
	}

	private void initFormPanel() {
		formPanel = new FormPanel();
		formPanel.setHeaderVisible(false);
		formPanel.setFrame(true);
		formPanel.setLayout(new FlowLayout());

		FieldSet searchFieldSet = new FieldSet();
		searchFieldSet.setHeading("Search");

		FormLayout layout = new FormLayout();
		layout.setLabelWidth(80);
		searchFieldSet.setLayout(layout);

		search = new TextField<String>();
		search.setFieldLabel("Find");

		search.addKeyListener(new KeyListener() {

			@Override
			public void componentKeyUp(ComponentEvent event) {
				if ((event.getKeyCode() == 8) && (search.getValue() == null)) {
					reset();
				}
			}

			@Override
			public void componentKeyPress(ComponentEvent event) {
				if ((event.getKeyCode() == 13)) {
					searchText = search.getValue() == null ? "" : search
							.getValue();
					loader.load(0, 25);
				}
			}

		});

		BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
		data.setMargins(new Margins(5, 5, 5, 5));

		searchFieldSet.add(search, data);

		formPanel.add(searchFieldSet);

		formPanel.add(this.grid);

		this.searchStatus = new SearchStatus();
		searchStatus.setAutoWidth(true);

		formPanel.getButtonBar().add(this.searchStatus);

		formPanel.getButtonBar().add(new LabelToolItem("    "));

		formPanel.setButtonAlign(HorizontalAlignment.RIGHT);

		select = new Button("Select", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				select();
			}
		});

		select.setIcon(BasicWidgetResources.ICONS.select());
		select.disable();

		formPanel.addButton(this.select);

		cancel = new Button("Cancel", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				cancel();
			}
		});

		cancel.setIcon(BasicWidgetResources.ICONS.cancel());

		formPanel.addButton(cancel);

		formPanel.setBottomComponent(this.toolBar);

		vp.add(formPanel);
	}

	private void initGrid() {
		ColumnModel cm = prepareColumnModel();

		grid = new Grid<T>(store, cm);
		grid.setBorders(true);

		grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		grid.addListener(Events.CellClick, new Listener<BaseEvent>() {

			public void handleEvent(BaseEvent be) {
				if (grid.getSelectionModel().getSelection().size() > 0)
					select.enable();
				else
					select.disable();
			}
		});

		grid.addListener(Events.CellDoubleClick, new Listener<BaseEvent>() {

			public void handleEvent(BaseEvent be) {
				select();
			}
		});

		setGridProperties();
	}

	/**
	 * Remove all beans from the Store and after Hide the window
	 */
	@SuppressWarnings("deprecation")
	public void cancel() {
		super.close();
		reset();
	}

	public void reset() {
		this.search.reset();
		this.store.removeAll();
		this.toolBar.clear();
		this.select.disable();
		this.searchStatus.clearStatus("");
	}

	public void clearGridElements() {
		this.store.removeAll();
		this.toolBar.clear();
	}

	/**
	 * Set the correct Status Iconn Style
	 */
	public void setSearchStatus(EnumSearchStatus status,
			EnumSearchStatus message) {
		this.searchStatus.setIconStyle(status.getValue());
		this.searchStatus.setText(message.getValue());
	}

	public abstract void setWindowProperties();

	public abstract void createStore();

	public abstract void setGridProperties();

	public abstract ColumnModel prepareColumnModel();

	public abstract void select();

	/**
	 * @return the initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

}
