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
package org.geosdi.geoplatform.gui.client.widget.expander;

import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus.EnumSearchStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.expander.GPTreeExpanderNotifier;
import org.geosdi.geoplatform.gui.client.widget.tree.form.GPTreeFormWidget;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.IGPNode;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPLayerExpander extends GPTreeExpanderNotifier<IGPNode> {

    private GPTreeFormWidget widget;

    public GPLayerExpander(TreePanel tree, GPTreeFormWidget theWidget) {
        super(tree);
        this.widget = theWidget;
    }

    @Override
    protected void execute() {
        this.widget.show();
    }

    @Override
    protected void defineStatusBarCancelMessage() {
        LayoutManager.getInstance().getStatusMap().setStatus(
                LayerModuleConstants.INSTANCE.GPLayerExpander_statusOperationCancelledText(),
                // TODO change message wrt widget that use this expander
                // AddFolderWidget ---> folder
                //   LoadWmsGetMapFromUrlWidget --> layer
                //   LoadKmlFromUrlWidget --> layer
                EnumSearchStatus.STATUS_SEARCH_ERROR.toString());
    }

    @Override
    protected boolean checkNode() {
        boolean result = false;
        if (super.selectedElement instanceof FolderTreeNode) {
            result = ((FolderTreeNode) super.selectedElement).getId() == null;
        }
        return result;
    }
}
