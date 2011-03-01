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
package org.geosdi.geoplatform.gui.client.widget.search;

import java.util.List;

import org.geosdi.geoplatform.gui.client.widget.search.routing.GPComboBox;
import org.geosdi.geoplatform.gui.configuration.mvc.GeoPlatformController;
import org.geosdi.geoplatform.gui.model.GeoPlatformBeanModel;

import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public abstract class ComboSearchWidget<T extends GeoPlatformBeanModel, C extends GeoPlatformController> {

	/**
	 * 
	 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
	 * @email giuseppe.lascaleia@geosdi.org
	 * 
	 */
	public enum TypeImage {

		IMAGE_LOADING(GWT.getModuleBaseURL() + "/gp-images/loading.gif"), IMAGE_RESULT_FOUND(
				GWT.getModuleBaseURL() + "/gp-images/ok.png"), IMAGE_RESULT_NOT_FOUND(
				GWT.getModuleBaseURL() + "/gp-images/not_found.png"), IMAGE_SERVICE_ERROR(
				GWT.getModuleBaseURL() + "/gp-images/error.png");

		private String value;

		TypeImage(String theValue) {
			this.value = theValue;
		}

		public String toString() {
			return this.value;
		}

	}

	protected C controller;
	protected FlexTable tableWidget;
	protected ListStore<T> store;
	protected GPComboBox<T> combo;
	protected Image loadImage;
	protected Image display;

	/**
	 * @Default Constructor
	 * 
	 */
	public ComboSearchWidget() {
		this.init();
	}

	/**
	 * @Constructor
	 * 
	 * @param theController
	 */
	public ComboSearchWidget(C theController) {
		this.controller = theController;
		this.init();
	}

	private void init() {
		this.createCombo();
		this.initWidget();
	}

	/**
	 * Create ComboBox
	 * 
	 */
	private void createCombo() {
		// TODO Auto-generated method stub
		this.store = new ListStore<T>();
		this.combo = new GPComboBox<T>();
		this.combo.setStore(store);

		setWidgetProperties();
		setComboToolTip();

		setComboSelectionChangedListener();

	}

	/**
	 * Create FormPanel
	 * 
	 */
	private void initWidget() {
		tableWidget = new FlexTable();

		tableWidget.setCellSpacing(8);
		tableWidget.setCellPadding(4);

		tableWidget.getCellFormatter().setHorizontalAlignment(1, 1,
				HasHorizontalAlignment.ALIGN_CENTER);

		tableWidget.setWidget(1, 1, this.combo);

		this.loadImage = new Image();
		this.loadImage.setVisible(false);

		tableWidget.getCellFormatter().setHorizontalAlignment(1, 2,
				HasHorizontalAlignment.ALIGN_CENTER);

		tableWidget.setWidget(1, 2, this.loadImage);

		this.display = new Image();
		this.display
				.setUrl(GWT.getModuleBaseURL() + "/gp-images/help-icon.png");

		this.display.setTitle("Reload Results after Combo Collapse.");

		this.display.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if (store.getModels().size() > 0) {
					combo.focus();
					combo.expand();
				}
			}
		});

		tableWidget.setWidget(1, 3, this.display);
	}

	/**
	 * 
	 */
	private void setComboSelectionChangedListener() {
		// TODO Auto-generated method stub
		this.combo
				.addSelectionChangedListener(new SelectionChangedListener<T>() {

					@Override
					public void selectionChanged(SelectionChangedEvent<T> se) {
						// TODO Auto-generated method stub
						changeSelection(se);
					}
				});
	}

	/**
	 * 
	 * @param type
	 */
	public void loadImage(TypeImage type, boolean visible) {
		this.loadImage.setUrl(type.toString());
		this.loadImage.setVisible(visible);
	}

	/**
	 * 
	 * @param models
	 */
	public void fillStore(List<T> models) {
		this.store.add(models);
	}

	/**
	 * Clear the Store
	 */
	public void clearStore() {
		this.store.removeAll();
	}

	/**
	 * Clear Widget State
	 */
	public void clearWidget() {
		clearStore();
		this.combo.collapse();
		this.combo.setRawValue("");
		this.loadImage.setUrl("");
		this.loadImage.setVisible(false);
	}

	/**
	 * This method set Properties on ComboBox such us
	 * <ul>
	 * <li>Define a KeyListener on ComboBox</li>
	 * <li>Define the Display Field on ComboBox</li>
	 * </ul>
	 * 
	 */
	public abstract void setWidgetProperties();

	public abstract void setComboToolTip();

	public abstract void changeSelection(SelectionChangedEvent<T> se);

	public void expand() {
		combo.focus();
		combo.expand();
	}

	/**
	 * @return the tableWidget
	 */
	public FlexTable getTableWidget() {
		return tableWidget;
	}
}
