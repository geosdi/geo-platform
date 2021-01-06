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
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.LayerResources;
import org.geosdi.geoplatform.gui.client.config.LayerModuleInjector;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.widget.tree.panel.GinTreePanel;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

import javax.inject.Inject;
import java.util.List;

import static java.lang.Boolean.TRUE;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CopyLayerAction extends MenuBaseAction {

    private final TreePanel<GPBeanTreeModel> tree;

    @Inject
    public CopyLayerAction(GinTreePanel ginTreePanel) {
        super("CopyLayer", AbstractImagePrototype.create(LayerResources.ICONS.copy()));
        this.tree = ginTreePanel.get();
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        final List<GPBeanTreeModel> selectedItems = this.tree.getSelectionModel().getSelectedItems();
        final List<GPLayerTreeModel> selectedLayers = Lists.<GPLayerTreeModel>newArrayList();
        for (GPBeanTreeModel element : selectedItems) {
            if (element instanceof FolderTreeNode) {
                throw new IllegalArgumentException("Folder copy is not allowed");
            } else if (element instanceof GPLayerTreeModel) {
                selectedLayers.add((GPLayerTreeModel) element);
            }
        }

        PasteLayerAction pasteLayerAction = LayerModuleInjector.MainInjector.getInstance().getPasteLayerAction();
        pasteLayerAction.layersToCopy.clear();
        pasteLayerAction.layersToCopy.addAll(selectedLayers);
        pasteLayerAction.setMustBeEnabled(TRUE);
        pasteLayerAction.setEnabled(TRUE);
    }
}