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
package org.geosdi.geoplatform.gui.client.widget.tree.properties.expand;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadFolderElementsRequest;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadFolderElementsResponse;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.timeout.event.GPExpandTreeNodeEvent;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.builder.LayerTreeBuilder;
import org.geosdi.geoplatform.gui.client.widget.tree.panel.GinTreePanel;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class GPTreeBeforeExapand implements TreeBeforeExpand {

    private final GPTreePanel tree;
    private final LayerTreeBuilder treeBuilder;
    private final LoadFolderElementsRequest loadFolderElementsRequest = new LoadFolderElementsRequest();

    @Inject
    public GPTreeBeforeExapand(GinTreePanel theTree,
            LayerTreeBuilder theTreeBuilder) {
        this.tree = theTree.get();
        this.treeBuilder = theTreeBuilder;
    }

    @Override
    public void beforeExpand() {
        tree.addListener(Events.BeforeExpand,
                new Listener<TreePanelEvent<ModelData>>() {

                    @Override
                    public void handleEvent(TreePanelEvent<ModelData> be) {
                        if ((be.getItem() instanceof FolderTreeNode)
                        && (!((FolderTreeNode) be.getItem()).isLoaded())
                        && (((FolderTreeNode) be.getItem()).getId() != null)) {
                            final FolderTreeNode parentFolder = (FolderTreeNode) be.getItem();
                            parentFolder.setLoading(Boolean.TRUE);
                            LayoutManager.getInstance().getStatusMap().setBusy(
                                    LayerModuleConstants.INSTANCE.statusLoadingTreeElementsText());

                            loadFolderElementsRequest.setFolderID(parentFolder.getId());

                            ClientCommandDispatcher.getInstance().execute(new GPClientCommand<LoadFolderElementsResponse>() {

                                private static final long serialVersionUID = 3109256773218160485L;

                                {
                                    super.setCommandRequest(loadFolderElementsRequest);
                                }

                                @Override
                                public void onCommandSuccess(LoadFolderElementsResponse response) {
                                    treeBuilder.insertElementsOnTree(
                                            parentFolder, response.getResult());
                                    LayoutManager.getInstance().getStatusMap().setStatus(
                                            LayerModuleConstants.INSTANCE.statusSuccessLoadingTreeElementsText(),
                                            SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
                                }

                                @Override
                                public void onCommandFailure(Throwable caught) {
                                    if (caught.getCause() instanceof GPSessionTimeout) {
                                        GPHandlerManager.fireEvent(new GPLoginEvent(
                                                        new GPExpandTreeNodeEvent(parentFolder)));
                                    } else {
                                        parentFolder.setLoading(Boolean.FALSE);
                                        GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.errorLoadingTitleText(),
                                                WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                                        LayoutManager.getInstance().getStatusMap().setStatus(
                                                LayerModuleConstants.INSTANCE.statusErrorLoadingTreeElementsText(),
                                                SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                                        System.out.println("Error loading tree elements: " + caught.toString()
                                                + " data: " + caught.getMessage());
                                    }
                                }

                            });
                        }
                    }

                });
    }

}
