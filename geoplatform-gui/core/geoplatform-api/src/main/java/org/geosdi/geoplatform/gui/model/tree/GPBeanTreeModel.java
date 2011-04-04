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
package org.geosdi.geoplatform.gui.model.tree;

import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public abstract class GPBeanTreeModel extends BaseTreeModel {

	public enum GPKeyTreeModel {

		LABEL_VALUE("label");

		private String value;

		GPKeyTreeModel(String theValue) {
			this.value = theValue;
		}

		public String toString() {
			return this.value;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2095233644130779285L;

	private String label;
	
	private int zIndex;

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
		set(GPKeyTreeModel.LABEL_VALUE.toString(), this.label);
	}

	/**
	 * Notify Check Changed Event on the Map
	 * 
	 * @param isChecked
	 */
	public void notifyCheckEvent(boolean isChecked) {
		// TODO Auto-generated method stub

	}

	public abstract AbstractImagePrototype getIcon();

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	public int getzIndex() {
		return zIndex;
	}
	
	public abstract void accept(IVisitor visitor);
}
