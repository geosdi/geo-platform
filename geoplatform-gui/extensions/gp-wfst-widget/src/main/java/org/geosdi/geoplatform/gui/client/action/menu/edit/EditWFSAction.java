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
package org.geosdi.geoplatform.gui.client.action.menu.edit;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.action.menu.edit.responsibility.LayerTypeHandlerManager;
import org.geosdi.geoplatform.gui.client.action.menu.strategy.IActionStrategy;
import org.geosdi.geoplatform.gui.client.command.datasource.CheckDataSourceRequest;
import org.geosdi.geoplatform.gui.client.command.datasource.CheckDataSourceResponse;
import org.geosdi.geoplatform.gui.client.config.FeatureInjector;
import org.geosdi.geoplatform.gui.client.i18n.BasicWidgetModuleMessages;
import org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetConstants;
import org.geosdi.geoplatform.gui.client.puregwt.action.IEditWFSActionHandler;
import org.geosdi.geoplatform.gui.client.puregwt.map.initializer.event.BindLayersEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.event.BuildLayerTreeEvent;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPEventBus;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.geosdi.geoplatform.gui.client.i18n.WFSTWidgetMessages.INSTANCE;

/**
 * @author Giuseppe La Scaleia <giuseppe.lascaleia@geosdi.org>
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class EditWFSAction extends MenuBaseAction implements IEditWFSAction, IEditWFSActionHandler {

    private final TreePanel<GPBeanTreeModel> treePanel;
    private final LayerTypeHandlerManager layerTypeHandlerManager;
    private final GPEventBus bus;
    private final BindLayersEvent bindLayersEvent = new BindLayersEvent();
    private final CheckDataSourceRequest checkDataSourceRequest = GWT.<CheckDataSourceRequest>create(CheckDataSourceRequest.class);
    private IActionStrategy actionStrategy;
    private GPLayerBean layer;

    /**
     * @param treePanel
     */
    public EditWFSAction(TreePanel<GPBeanTreeModel> treePanel) {
        super(WFSTWidgetConstants.INSTANCE.EditWFSAction_titleText(),
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.vector()));
        this.treePanel = treePanel;
        this.bus = FeatureInjector.MainInjector.getInstance().getEventBus();
        this.layerTypeHandlerManager = FeatureInjector.MainInjector.getInstance().getLayerTypeHandlerManager();
        this.actionStrategy = FeatureInjector.MainInjector.getInstance().getActionStrategy();
        this.bus.addHandler(IEditWFSActionHandler.TYPE, this);
    }

    @Override
    public void componentSelected(MenuEvent e) {
        GPBeanTreeModel itemSelected = this.treePanel.getSelectionModel().getSelectedItem();
        this.checkDataSourceRequest.setDatasource(((GPLayerTreeModel)itemSelected).getDataSource());

        ClientCommandDispatcher.getInstance().execute(new GPClientCommand<CheckDataSourceResponse>() {
            {
                super.setCommandRequest(checkDataSourceRequest);
            }

            @Override
            public void onCommandSuccess(final CheckDataSourceResponse response) {
                if (response.getResult()) {
                    actionStrategy.setWidgetType(IActionStrategy.WidgetType.EDIT_WFS_ACTION);
                    layer = (GPLayerBean) treePanel.getSelectionModel().getSelectedItem();
                    LayoutManager.getInstance().getStatusMap().setBusy(INSTANCE.checkingIfLayerIsAVectorMessage(layer.getName()));
                    layerTypeHandlerManager.forwardLayerType(layer);
                } else
                    GeoPlatformMessage.errorMessage(BasicWidgetModuleMessages.INSTANCE.errorDataSource(),
                            BasicWidgetModuleMessages.INSTANCE.datasourceNotMatches());
            }

            @Override
            public void onCommandFailure(Throwable exception) {
                GeoPlatformMessage.errorMessage(BasicWidgetModuleMessages.INSTANCE.errorDataSource(), exception.getMessage());
            }
        });

    }

    @Override
    public void afterStrategy() {
//        bindLayersTree(this.layer);
        getAllLayer(this.layer);
    }

    @Override
    public void bindLayersTree(GPLayerBean layerToExclude) {
        if (this.treePanel instanceof GPTreePanel) {
            List<GPLayerBean> layers = ((GPTreePanel) this.treePanel).getVisibleLayersOnTree();
            layers.remove(layerToExclude);

            if (!layers.isEmpty()) {
                /**
                 * TODO Put this in Model
                 */
                Collections.sort(layers, new Comparator<GPLayerBean>() {

                    @Override
                    public int compare(GPLayerBean layer1, GPLayerBean layer2) {
                        //ascending order
                        return layer1.getzIndex() - layer2.getzIndex();
                    }

                });
                this.bindLayersEvent.setLayers(layers);
                this.bus.fireEvent(bindLayersEvent);
            }
        }
    }

    /**
     * @param layerToExclude
     */
    private void getAllLayer(GPLayerBean layerToExclude) {
        if (this.treePanel instanceof GPTreePanel) {
            List<GPLayerBean> layers = ((GPTreePanel) this.treePanel).getAllLayersOnTree();
            layers.remove(layerToExclude);
            GPBeanTreeModel root = treePanel.getStore().getRootItems().get(0);
            this.bus.fireEvent(new BuildLayerTreeEvent(root, layers));
        }
    }
}