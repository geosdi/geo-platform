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
package org.geosdi.geoplatform.gui.client.model;

import java.util.ArrayList;
import java.util.List;

import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientRasterInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientVectorInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPLayerClientInfo;
import org.geosdi.geoplatform.gui.impl.map.event.HideLayerEvent;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.IFolderTreeNode;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 * 
 */
public class FolderTreeNode extends GPBeanTreeModel implements IFolderTreeNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3687415822526940729L;

	private List<GPLayerBean> childChecked = new ArrayList<GPLayerBean>();

	public FolderTreeNode(GPFolderClientInfo folder) {
		super.setLabel(folder.getLabel());
		this.modelConverter(folder.getLayers());
	}

	/**
	 * 
	 * @param layersClientInfo
	 */
	public void modelConverter(List<GPLayerClientInfo> layersClientInfo) {
		// TODO Auto-generated method stub
		for (GPLayerClientInfo layer : layersClientInfo) {
			if (layer instanceof ClientRasterInfo)
				super.add(new RasterTreeNode((ClientRasterInfo) layer));
			else if (layer instanceof ClientVectorInfo)
				super.add(new VectorTreeNode((ClientVectorInfo) layer));

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel#getIcon()
	 */
	@Override
	public AbstractImagePrototype getIcon() {
		// TODO Auto-generated method stub
		return LayerResources.ICONS.LayerFolder();
	}

	@Override
	public void notifyCheckEvent(boolean isChecked) {
		// TODO Auto-generated method stub
		if (isChecked)
			for (ModelData child : super.getChildren()) {
				((GPBeanTreeModel) child)
						.notifyCheckEvent(isChildChecked((GPLayerTreeModel) child));
			}
		else
			for (ModelData child : super.getChildren()) {
				if (isChildChecked((GPLayerTreeModel) child))
					GPHandlerManager.fireEvent(new HideLayerEvent(
							(GPLayerBean) child));
			}
	}

	@Override
	public boolean isChecked() {
		return getRootNode().isNodeChecked(this);
	}

	@Override
	public boolean isChildChecked(GPLayerTreeModel child) {
		// TODO Auto-generated method stub
		return getRootNode().isNodeChecked(child);
	}

	/**
	 * 
	 * @return All Children Checked
	 */
	public List<GPLayerBean> getChecked() {
		this.childChecked.clear();
		for (ModelData child : super.getChildren()) {
			if (isChildChecked((GPLayerTreeModel) child))
				this.childChecked.add((GPLayerTreeModel) child);
		}
		return this.childChecked;
	}

	/**
	 * Return Root Node
	 * 
	 * @return GPRootTreeNode
	 */
	public GPRootTreeNode getRootNode() {
		return (GPRootTreeNode) super.getParent();
	}
}
