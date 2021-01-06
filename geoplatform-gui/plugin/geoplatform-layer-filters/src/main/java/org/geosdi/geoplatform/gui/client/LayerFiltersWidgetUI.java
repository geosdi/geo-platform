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
package org.geosdi.geoplatform.gui.client;

import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.EntryPoint;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.action.tree.menu.TreeMenuActionCreator;
import org.geosdi.geoplatform.gui.action.tree.menu.TreeMenuActionRegistar;
import org.geosdi.geoplatform.gui.client.action.menu.cqlfilter.AddModifyCQLFilterAction;
import org.geosdi.geoplatform.gui.client.action.menu.cqlfilter.RemoveCQLFilterAction;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.impl.tree.menu.config.TreeMenuGinInjector;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class LayerFiltersWidgetUI implements EntryPoint {

    /**
     * (non-Javadoc)
     *
     * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
     */
    @Override
    public void onModuleLoad() {
        TreeMenuActionRegistar registar = TreeMenuGinInjector.MainInjector.getInstance().getTreeMenuActionRegistar();

        registar.put("ADD_MODIFY_CQL_FILTER", new TreeMenuActionCreator() {
            @Override
            public MenuAction createAction(TreePanel treePanel) {
                return new AddModifyCQLFilterAction(
                        (GPTreePanel<GPBeanTreeModel>) treePanel);
            }
        });

        registar.put("REMOVE_CQL_FILTER", new TreeMenuActionCreator() {
            @Override
            public MenuAction createAction(TreePanel treePanel) {
                return new RemoveCQLFilterAction(
                        (GPTreePanel<GPBeanTreeModel>) treePanel);
            }
        });
    }
}