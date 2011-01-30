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
package org.geosdi.geoplatform.gui.client.widget.map.store;

import org.geosdi.geoplatform.gui.client.widget.legend.GPLegendWidget;
import org.geosdi.geoplatform.gui.client.widget.scale.GPScaleWidget;
import org.geosdi.geoplatform.gui.impl.map.GeoPlatformMap;
import org.geosdi.geoplatform.gui.impl.map.store.GPLayersStore;
import org.geosdi.geoplatform.gui.impl.map.store.ILayersStore;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.GPVectorBean;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.WMS;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class LayersStore extends GPLayersStore<GPLayerBean, Layer> implements
		ILayersStore<Layer> {

	private LayerBuilder layerBuilder;
	private GPLegendWidget legendWidget;

	public LayersStore(GeoPlatformMap theMapWidget) {
		super(theMapWidget);
		// TODO Auto-generated constructor stub
		this.layerBuilder = new LayerBuilder(theMapWidget);
		this.legendWidget = new GPLegendWidget();
		GPScaleWidget.display("Scale");
	}

	@Override
	public boolean containsLayer(GPLayerBean key) {
		// TODO Auto-generated method stub
		return this.layers.containsKey(key);
	}

	@Override
	public Layer getLayer(GPLayerBean key) {
		// TODO Auto-generated method stub
		return this.layers.get(key);
	}

	@Override
	public void onDisplayLayer(GPLayerBean layerBean) {
		// TODO Auto-generated method stub
		layerBean.acceptForDisplay(this);
	}

	@Override
	public void onHideLayer(GPLayerBean layerBean) {
		// TODO Auto-generated method stub
		layerBean.acceptForHide(this);
	}

	@Override
	public void onRemoveLayer(GPLayerBean layerBean) {
		// TODO Auto-generated method stub
		layerBean.acceptForRemove(this);
	}

	@Override
	public void visitForDisplay(GPVectorBean vectorBean) {
		// TODO Auto-generated method stub
		this.legendWidget.addLegend(vectorBean);

		if (containsLayer(vectorBean)) {
			WMS layer = (WMS) this.layers.get(vectorBean);
			layer.setIsVisible(true);
			layer.redraw();
		} else {
			WMS layer = (WMS) this.layerBuilder.buildLayer(vectorBean);

			this.layers.put(vectorBean, layer);

			this.mapWidget.getMap().addLayer(layer);
			this.mapWidget.getMap().setLayerZIndex(layer,
					vectorBean.getzIndex());
		}
	}

	@Override
	public void visitForHide(GPVectorBean vectorBean) {
		// TODO Auto-generated method stub
		WMS layer = (WMS) getLayer(vectorBean);
		if (layer != null)
			layer.setIsVisible(false);
		this.legendWidget.hideLegenItem(vectorBean);
	}

	@Override
	public void visitForRemove(GPVectorBean vectorBean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitForDisplay(GPRasterBean rasterBean) {
		// TODO Auto-generated method stub
		this.legendWidget.addLegend(rasterBean);

		if (containsLayer(rasterBean)) {
			WMS layer = (WMS) this.layers.get(rasterBean);
			layer.setIsVisible(true);
			layer.redraw();
			System.out.println("TEST *********** " + layer.getName() + " - "
					+ layer.getZIndex());
		} else {
			WMS layer = (WMS) this.layerBuilder.buildLayer(rasterBean);

			this.layers.put(rasterBean, layer);

			this.mapWidget.getMap().addLayer(layer);
//			this.mapWidget.getMap().setLayerZIndex(layer,
//					rasterBean.getzIndex());

			System.out.println("TEST *********** " + layer.getName() + " - "
					+ layer.getZIndex());
		}
	}

	@Override
	public void visitForHide(GPRasterBean rasterBean) {
		// TODO Auto-generated method stub
		WMS layer = (WMS) getLayer(rasterBean);
		if (layer != null)
			layer.setIsVisible(false);
		this.legendWidget.hideLegenItem(rasterBean);
	}

	@Override
	public void visitForRemove(GPRasterBean rasterBean) {
		// TODO Auto-generated method stub

	}
}
