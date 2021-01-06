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
package org.geosdi.geoplatform.gui.client.widget.tree.builder;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.ModelData;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadDefaultProjectElementsRequest;
import org.geosdi.geoplatform.gui.client.command.layer.basic.LoadDefaultProjectElementsResponse;
import org.geosdi.geoplatform.gui.client.config.MementoModuleInjector;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.i18n.windows.WindowsConstants;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.client.model.GPRootTreeNode;
import org.geosdi.geoplatform.gui.client.model.memento.save.IMementoSave;
import org.geosdi.geoplatform.gui.client.model.projects.GPClientProject;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorDisplayHide;
import org.geosdi.geoplatform.gui.client.model.visitor.VisitorPosition;
import org.geosdi.geoplatform.gui.client.puregwt.timeout.event.GPBuildTreeEvent;
import org.geosdi.geoplatform.gui.client.widget.SearchStatus;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreeStore;
import org.geosdi.geoplatform.gui.client.widget.tree.panel.GinTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.store.GinTreeStore;
import org.geosdi.geoplatform.gui.client.widget.tree.visitor.GinVisitorDisplayHide;
import org.geosdi.geoplatform.gui.command.api.ClientCommandDispatcher;
import org.geosdi.geoplatform.gui.command.api.GPClientCommand;
import org.geosdi.geoplatform.gui.command.api.GPCommandRequest;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.IGPFolderElements;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.configuration.users.options.member.UserSessionEnum;
import org.geosdi.geoplatform.gui.impl.map.event.GPLoginEvent;
import org.geosdi.geoplatform.gui.impl.map.event.ResetMapStoreEvent;
import org.geosdi.geoplatform.gui.impl.view.LayoutManager;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;
import org.geosdi.geoplatform.gui.puregwt.featureinfo.event.CleanFeatureInfoCacheEvent;
import org.geosdi.geoplatform.gui.shared.util.GPSharedUtils;
import org.geosdi.geoplatform.gui.utility.GPSessionTimeout;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Boolean.FALSE;
import static java.util.logging.Level.WARNING;

/**
 * <p>
 * This Class build the LayerTreeWidget doing {@link GPCommandRequest} Command
 * on Server
 * </p>
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Singleton
public class LayerTreeBuilder implements GPCompositeBuilder {

    private final static Logger logger = Logger.getLogger("LayerTreeBuilder");
    //
    private final GPBuildTreeEvent buildEvent = new GPBuildTreeEvent();
    private final GPTreeStore store;
    private final GPRootTreeNode root;
    private final VisitorDisplayHide visitorDisplay;
    private final GPTreePanel tree;
    private final LoadDefaultProjectElementsRequest loadDefaultProjectElementsRequest = new LoadDefaultProjectElementsRequest();
    private final CleanFeatureInfoCacheEvent clearFeatureInfoEvent = new CleanFeatureInfoCacheEvent();
    private boolean initialized;

    @Inject
    public LayerTreeBuilder(GinTreeStore theStore, GPRootTreeNode theRoot,
            GinVisitorDisplayHide theVisitorDisplay, GinTreePanel theTree) {
        this.store = theStore.get();
        this.root = theRoot;
        this.visitorDisplay = theVisitorDisplay.get();
        this.tree = theTree.get();
    }

    @Override
    public void buildTree() {
        if (!initialized) {
            Registry.register(UserSessionEnum.TREE_LOADED.name(), initialized);

            LayoutManager.getInstance().getStatusMap().setBusy(
                    LayerModuleConstants.INSTANCE.statusLoadingTreeElementsText());

            final LoadDefaultProjectElementsRequest loadDefaultProjectElementsRequest = GWT.
                    <LoadDefaultProjectElementsRequest>create(LoadDefaultProjectElementsRequest.class);
            ClientCommandDispatcher.getInstance().execute(new GPClientCommand<LoadDefaultProjectElementsResponse>() {

                private static final long serialVersionUID = 3109256773218160485L;

                {
                    super.setCommandRequest(loadDefaultProjectElementsRequest);
                }

                @Override
                public void onCommandSuccess(LoadDefaultProjectElementsResponse response) {
                    onBuildSuccess(response.getResult());
                }

                @Override
                public void onCommandFailure(Throwable caught) {
                    if (caught.getCause() instanceof GPSessionTimeout) {
                        GPHandlerManager.fireEvent(new GPLoginEvent(buildEvent));
                    } else {
                        GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.errorLoadingTitleText(),
                                WindowsConstants.INSTANCE.errorMakingConnectionBodyText());
                        LayoutManager.getInstance().getStatusMap().setStatus(
                                LayerModuleConstants.INSTANCE.statusErrorLoadingTreeElementsText(),
                                SearchStatus.EnumSearchStatus.STATUS_NO_SEARCH.toString());
                        logger.log(WARNING, "Error loading tree elements: " + caught.toString()
                                + " data: " + caught.getMessage());
                    }
                }

            });
        }
    }

    /**
     * @param clientProject
     */
    protected final void onBuildSuccess(GPClientProject clientProject) {
        GWT.log("########################onBuildSuccess " + clientProject);
        Registry.register(UserSessionEnum.CURRENT_PROJECT_ON_TREE.name(), clientProject);
        root.setId(clientProject.getId());
        root.setLabel(clientProject.getName());
        root.setCreationDate(clientProject.getCreationDate());
        root.setProjectElements(clientProject.getNumberOfElements());
        root.setInternalPublic(clientProject.isInternalPublic());
        root.setExternalPublic(clientProject.isExternalPublic());
        root.setProjectMessage(clientProject.getDefaultProjectLabel());
        root.setProjectName(clientProject.getName());
        root.setProjectVersion(clientProject.getVersion());
        root.setProjectShared(clientProject.isProjectShared());
        root.modelConverter(clientProject.getRootFolders());
        store.add(root, Boolean.TRUE);
        visitorDisplay.enableCheckedComponent(root);
        initialized = Boolean.TRUE;
        tree.setExpanded(root, Boolean.TRUE);
        insertElementsOfTheRootFolders(clientProject.getRootFolders());
        LayoutManager.getInstance().getStatusMap().setStatus(
                LayerModuleConstants.INSTANCE.statusSuccessLoadingTreeElementsText(),
                SearchStatus.EnumSearchStatus.STATUS_SEARCH.toString());
        Registry.register(UserSessionEnum.TREE_LOADED.name(), initialized);
    }

    @Override
    public void rebuildTree() {
        this.initialized = FALSE;
        IMementoSave mementoSave = MementoModuleInjector.MainInjector.getInstance().getMementoSave();
        mementoSave.clear();
        GPHandlerManager.fireEvent(new ResetMapStoreEvent());
        MapHandlerManager.fireEvent(this.clearFeatureInfoEvent);
        this.root.removeAll();
        this.store.removeAll();
        this.buildTree();
    }

    public void insertElementsOnTree(FolderTreeNode parentFolder, List<IGPFolderElements> folderElements) {
        final VisitorPosition visitorPosition = new VisitorPosition();
        parentFolder.modelConverter(folderElements);
        List<GPBeanTreeModel> childrenList = Lists.<GPBeanTreeModel>newArrayList();
        visitorPosition.assignTmpIndex(parentFolder);
        for (Iterator<ModelData> it = parentFolder.getChildren().iterator(); it.hasNext(); ) {
            GPBeanTreeModel element = (GPBeanTreeModel) it.next();
            element.accept(visitorPosition);
            childrenList.add(element);
        }

        tree.getStore().insert(parentFolder, childrenList, 0, Boolean.TRUE);
        visitorDisplay.enableCheckedComponent(parentFolder);
        parentFolder.setLoading(FALSE);
        parentFolder.setLoaded(Boolean.TRUE);
        tree.refreshIcon(parentFolder);
        tree.fireEvent(GeoPlatformEvents.GP_NODE_EXPANDED);
    }

    private void insertElementsOfTheRootFolders(List<GPFolderClientInfo> folderClientList) {
        int i = 0;
        for (GPFolderClientInfo folderElement : GPSharedUtils.safeList(folderClientList)) {
            this.insertElementsOnTree((FolderTreeNode) this.root.getChild(i), folderElement);
            i++;
        }
    }

    private void insertElementsOnTree(FolderTreeNode parentFolder, GPFolderClientInfo folderClientInfo) {
        if (!folderClientInfo.getFolderElements().isEmpty()) {
            this.insertElementsOnTree(parentFolder, folderClientInfo.getFolderElements());
            if (parentFolder.isExpanded()) {
                tree.setExpanded(parentFolder, Boolean.TRUE);
            }
            int i = 0;
            for (IGPFolderElements folderElement : GPSharedUtils.safeList(folderClientInfo.getFolderElements())) {
                if (folderElement instanceof GPFolderClientInfo) {
                    this.insertElementsOnTree((FolderTreeNode) parentFolder.getChild(i), ((GPFolderClientInfo) folderElement));
                }
                i++;
            }
        }
    }
}