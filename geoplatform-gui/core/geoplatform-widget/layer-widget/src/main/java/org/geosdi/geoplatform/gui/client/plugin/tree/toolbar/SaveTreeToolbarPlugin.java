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
package org.geosdi.geoplatform.gui.client.plugin.tree.toolbar;

import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.action.tree.ToolbarLayerTreeAction;
import org.geosdi.geoplatform.gui.client.action.toolbar.SaveTreeAction;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;
import org.geosdi.geoplatform.gui.plugin.tree.toolbar.TreeToolbarPluginButton;
import org.geosdi.geoplatform.gui.plugin.tree.toolbar.TreeToolbarRegion;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class SaveTreeToolbarPlugin extends TreeToolbarPluginButton {

    @Override
    public String getId() {
        return "SAVE_TREE";
    }

    @Override
    public TreeToolbarRegion getRegion() {
        return TreeToolbarRegion.MIDDLE_REGION;
    }

    @Override
    protected ToolbarLayerTreeAction getTreeAction(TreePanel treePanel) {
        return new SaveTreeAction(treePanel, this);
    }

    @Override
    protected boolean isInitialEnabled() {
        return false;
    }

    @Override
    public boolean setEnabledByStatus(TreeStatusEnum status) {
        boolean condition = false;
        if (status == TreeStatusEnum.SAVE_CACHE_EMPTY) {
            super.button.setEnabled(condition);
        } else if (status == TreeStatusEnum.SAVE_CACHE_NOT_EMPTY) {
            condition = true;
            super.button.setEnabled(condition);
        }
        return condition;
    }
}
