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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.LayerFiltersResources;
import org.geosdi.geoplatform.gui.client.command.datasource.CheckDataSourceRequest;
import org.geosdi.geoplatform.gui.client.command.datasource.CheckDataSourceResponse;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetModuleMessages;
import org.geosdi.geoplatform.gui.client.puregwt.CQLFilterHandlerManager;
import org.geosdi.geoplatform.gui.client.puregwt.event.GPCheckDataSourceEvent;
import org.geosdi.geoplatform.gui.client.widget.cql.CQLFilterWidget;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.model.tree.AbstractFolderTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class AddModifyCQLFilterAction extends MenuBaseAction {

    private GPTreePanel<GPBeanTreeModel> treePanel;
    private CQLFilterWidget cqlFilterWidget;
    private final CheckDataSourceRequest checkDataSourceRequest = GWT.<CheckDataSourceRequest>create(CheckDataSourceRequest.class);

    public AddModifyCQLFilterAction(GPTreePanel<GPBeanTreeModel> treePanel) {
        super("AddModifyCQLFilter", AbstractImagePrototype.create(
                LayerFiltersResources.ICONS.cqlFilter()));
        this.treePanel = treePanel;
        this.cqlFilterWidget = new CQLFilterWidget(Boolean.TRUE, treePanel);
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        final GPBeanTreeModel itemSelected = this.treePanel.getSelectionModel().getSelectedItem();
        if (itemSelected instanceof AbstractFolderTreeNode) {
            throw new IllegalArgumentException("The CQL Filter can't be applied to a folder");
        }
        this.checkDataSourceRequest.setDatasource(((GPLayerTreeModel)itemSelected).getDataSource());
        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<CheckDataSourceResponse>() {
            {
                super.setCommandRequest(checkDataSourceRequest);
            }

            @Override
            public void onCommandSuccess(final CheckDataSourceResponse response) {
                cqlFilterWidget.show();
                CQLFilterHandlerManager.fireEvent(new GPCheckDataSourceEvent(response.getResult()));

/*                if(response.getResult())
                    cqlFilterWidget.show();
                else
                    GeoPlatformMessage.errorMessage(BasicWidgetModuleMessages.INSTANCE.errorDataSource(),
                            BasicWidgetModuleMessages.INSTANCE.datasourceNotMatches());*/
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                GeoPlatformMessage.errorMessage(BasicWidgetModuleMessages.INSTANCE.errorDataSource(), exception.getMessage());
            }
        });
    }
}
