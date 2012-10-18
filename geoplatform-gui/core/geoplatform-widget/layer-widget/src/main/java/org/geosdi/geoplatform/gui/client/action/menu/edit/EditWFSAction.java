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
package org.geosdi.geoplatform.gui.client.action.menu.edit;

import com.extjs.gxt.ui.client.event.MenuEvent;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.LayerTypeHandlerManager;
import org.geosdi.geoplatform.gui.client.config.FeatureInjector;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

/**
 *
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class EditWFSAction extends MenuBaseAction {

    private GPTreePanel<GPBeanTreeModel> treePanel;
    private LayerTypeHandlerManager layerTypeHandlerManager;

    public EditWFSAction(GPTreePanel<GPBeanTreeModel> treePanel) {
        super("Edit WFS Mode", LayerResources.ICONS.vector());
        this.treePanel = treePanel;
        this.layerTypeHandlerManager = FeatureInjector.MainInjector.getInstance().getLayerTypeHandlerManager();
    }

    @Override
    public void componentSelected(MenuEvent e) {
        final GPLayerTreeModel item = (GPLayerTreeModel) this.treePanel.getSelectionModel().getSelectedItem();
        
        System.out.println("ECCOLO @@@@@@@@@@@@@@@ " + item.getLayerType());

        LayoutManager.getInstance().getStatusMap().setStatus(
                "Checking if " + item.getName() + " is a Vector Layer.",
                SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());

        this.layerTypeHandlerManager.forwardLayerType(item);
    }
}
