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
package org.geosdi.geoplatform.gui.client.widget.tree.grid;

import org.geosdi.geoplatform.gui.model.tree.grid.GPTreeGridBeanModel;

import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public abstract class GeoPlatformTreeGridWidget<T extends GPTreeGridBeanModel> {

	protected TreeStore<T> store;
	protected TreeGrid<T> tree;
	protected ColumnModel columnModel;

	/**
	 * 
	 */
	public GeoPlatformTreeGridWidget() {
		this.store = new TreeStore<T>();
		this.createColumnModel();
		this.createTreeGrid();
	}

	private void createTreeGrid() {
		this.tree = new TreeGrid<T>(store, columnModel);
		setTreeGridProperties();
	}

	/**
	 * Create ColumnModel with all Column For Column with GeoPlatform Column
	 * Render we can have this code :
	 * 
	 * <code> 
	 * 		ColumnConfig gpColumnRender = new ColumnConfig(
	 *				GPKeyTreeGridModel.LABEL_NODE_VALUE.toString(),
	 *				"GeoPlatform-Renderer", 150);
     *
	 *		gpColumnRender.setRenderer(new GridCellRenderer<T>() {
     *
	 *			@Override
	 *			public Object render(T model, String property, ColumnData config,
	 *					int rowIndex, int colIndex, ListStore<T> store, Grid<T> grid) {
	 *				// TODO Auto-generated method stub
	 *				return model.getWidget();
	 *			}
	 *		});
	 * </code>
	 * 
	 */
	public abstract void createColumnModel();

	/**
	 * Set TreeGrid Properties such as Borders, RowHeight etc
	 * 
	 */
	public abstract void setTreeGridProperties();

}
