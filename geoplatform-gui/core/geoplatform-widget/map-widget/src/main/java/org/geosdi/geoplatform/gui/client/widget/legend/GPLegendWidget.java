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
package org.geosdi.geoplatform.gui.client.widget.legend;

import org.geosdi.geoplatform.gui.model.GPLayerBean;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.user.client.ui.Image;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI Group
 * 
 */
public class GPLegendWidget extends Window {

	private static final String GET_LEGEND_REQUEST = "?REQUEST=GetLegendGraphic&VERSION=1.0.0&FORMAT=image/png&WIDTH=20&HEIGHT=20&LAYER=";

	/**
	 * 
	 */
	public GPLegendWidget() {

		setMaximizable(true);
		setHeading("Legend Window");
		setHeight(300);
		setWidth(200);
		setScrollMode(Scroll.AUTOY);
	}

	public void addLegend(GPLayerBean layerBean) {
		if (getItemByItemId(layerBean.getLabel()) == null) {
			ContentPanel cp = new ContentPanel();
			cp.setHeading(layerBean.getLabel());
			cp.setId(layerBean.getLabel());
			Image image = new Image(layerBean.getDataSource()
					+ GET_LEGEND_REQUEST + layerBean.getLabel());
			System.out.println(image.getUrl().toString());
			cp.add(image);
			add(cp);
			repaint();
		} else {
			System.out.println("getItemByItemId(layerBean.getLabel()) != null");
			ContentPanel cpImage  = (ContentPanel) getItemByItemId(layerBean.getLabel());
			cpImage.setVisible(true);
			repaint();
		}
		repaint();
	}
	
	public void hideLegenItem(GPLayerBean layerBean){
		if(getItemByItemId(layerBean.getLabel()) != null){
			System.out.println("layerBean.getLabel()) != null");
			ContentPanel cp  = (ContentPanel) getItemByItemId(layerBean.getLabel());
			cp.setVisible(false);
			repaint();
		} 
		repaint();
		
	}

}
