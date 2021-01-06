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
package org.geosdi.geoplatform.gui.plugin.tree.toolbar;

import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.action.tree.ToolbarLayerTreeAction;
import org.geosdi.geoplatform.gui.global.security.GPAccountLogged;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public abstract class TreeToolbarPluginButton implements ITreeToolbarPlugin<Button> {

    protected Button button;

    /**
     * Method call only if the permission in not null, that create and join the
     * button with the relative action on tree.
     *
     * @param treePanel
     * @return the button to add into Layer Toolbar
     */
    @Override
    public final Button getWidget(TreePanel treePanel) {
        if (button == null) {
            String id = this.getId();
            Boolean permission = GPAccountLogged.getInstance().hasComponentPermission(id);

            ToolbarLayerTreeAction action = this.getTreeAction(treePanel);
            action.setId(id);

            button = new Button();
            button.setId(id);
            button.setToolTip(action.getTooltip());
            button.setIcon(action.getImage());
            button.addSelectionListener(action);

            button.setEnabled(this.isInitialEnabled() && permission);
        }
        return button;
    }

    protected abstract ToolbarLayerTreeAction getTreeAction(TreePanel treePanel);

    /**
     * Override for change the default setting that initially enable the plugin.
     *
     * @return if the plugin must be enabled
     */
    protected boolean isInitialEnabled() {
        return true;
    }

    /**
     * * Override for change the default setting that enable always the plugin
     * regardless of the state.
     *
     * @param status the status of the tree
     * @return if the plugin is enabled or not
     */
    @Override
    public boolean setEnabledByStatus(TreeStatusEnum status) {
        button.setEnabled(true);
        return true;
    }
}
