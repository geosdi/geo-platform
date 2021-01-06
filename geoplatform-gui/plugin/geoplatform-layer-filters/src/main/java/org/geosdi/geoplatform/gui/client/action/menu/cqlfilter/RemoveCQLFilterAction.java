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
package org.geosdi.geoplatform.gui.client.action.menu.cqlfilter;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.LayerFiltersResources;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.memento.save.storage.AbstractMementoOriginalProperties;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.impl.map.event.CQLFilterLayerMapEvent;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class RemoveCQLFilterAction extends MenuBaseAction {

    private GPTreePanel<GPBeanTreeModel> treePanel;
    private final CQLFilterLayerMapEvent cqlFilterLayerMapEvent = new CQLFilterLayerMapEvent();

    public RemoveCQLFilterAction(GPTreePanel<GPBeanTreeModel> treePanel) {
        super("RemoveCQLFilter", AbstractImagePrototype.create(
                LayerFiltersResources.ICONS.cqlFilterDelete()));
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GPBeanTreeModel itemSelected = this.treePanel.getSelectionModel().getSelectedItem();
        if (itemSelected instanceof AbstractFolderTreeNode) {
            throw new IllegalArgumentException(
                    "The CQL Filter can't be applied to a folder");
        }
        GPLayerTreeModel layerSelected = (GPLayerTreeModel) treePanel.getSelectionModel().getSelectedItem();
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        AbstractMementoOriginalProperties memento = mementoSave.copyOriginalProperties(
                layerSelected);
        layerSelected.setCqlFilter("");
        mementoSave.putOriginalPropertiesInCache(memento);
        cqlFilterLayerMapEvent.setLayerBean(layerSelected);
        GPHandlerManager.fireEvent(cqlFilterLayerMapEvent);
        treePanel.refresh(layerSelected);
    }
}
