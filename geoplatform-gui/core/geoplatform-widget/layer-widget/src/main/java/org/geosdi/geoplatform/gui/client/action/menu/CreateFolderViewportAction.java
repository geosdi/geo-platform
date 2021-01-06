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

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.List;
import org.geosdi.geoplatform.gui.action.menu.MenuBaseAction;
import org.geosdi.geoplatform.gui.client.BasicWidgetResources;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.event.CreateViewportEvent;
import org.geosdi.geoplatform.gui.configuration.message.GeoPlatformMessage;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CreateFolderViewportAction extends MenuBaseAction {

    private TreePanel treePanel;
    private Listener<MessageBoxEvent> message;
    private Listener executor;
    private FolderTreeNode selectedElement;
    private CreateViewportEvent createViewportEvent = new CreateViewportEvent();

    public CreateFolderViewportAction(final TreePanel treePanel) {
        super("CreateViewport", 
                AbstractImagePrototype.create(BasicWidgetResources.ICONS.viewport()));
        this.treePanel = treePanel;
        this.executor = new Listener() {
            @Override
            public void handleEvent(BaseEvent be) {
                execute(selectedElement.getChildren(), selectedElement.getLabel());
                treePanel.removeListener(GeoPlatformEvents.GP_NODE_EXPANDED, this);
            }
        };
        this.message = new Listener<MessageBoxEvent>() {
            @Override
            public void handleEvent(MessageBoxEvent be) {
                if (be.getButtonClicked().getItemId().equalsIgnoreCase(Dialog.YES)) {
                    treePanel.addListener(GeoPlatformEvents.GP_NODE_EXPANDED, executor);
                    treePanel.setExpanded(selectedElement, true);
                    System.out.println("Selected element: " + selectedElement);
                } else {
                    treePanel.removeListener(GeoPlatformEvents.GP_NODE_EXPANDED, executor);
                }
            }
        };
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GPBeanTreeModel item = (GPBeanTreeModel) this.treePanel.getSelectionModel().getSelectedItem();
        if (item instanceof FolderTreeNode) {
            FolderTreeNode selectedFolder = (FolderTreeNode) item;
            if (!selectedFolder.isLoaded()) {
                this.selectedElement = (FolderTreeNode) item;
                this.confirmExpandingMessage();
            } else {
                this.execute(selectedFolder.getChildren(), selectedFolder.getLabel());
            }
        } else {
            throw new IllegalArgumentException("The CreateFolderViewportAction can "
                    + "take only folder and not layer");
        }
    }

    private List<GPLayerBean> generateLayerList(List<ModelData> modelDataList) {
        List<GPLayerBean> layerList = Lists.<GPLayerBean>newArrayList();
        for (ModelData data : modelDataList) {
            if (data instanceof GPLayerBean) {
                layerList.add((GPLayerBean) data);
            }
        }
        return layerList;
    }

    private void execute(List<ModelData> modelDataList, String folderName) {
        List<GPLayerBean> layerList = generateLayerList(modelDataList);
        if (layerList.size() > 0) {
            createViewportEvent.setLayerList(generateLayerList(modelDataList));
            createViewportEvent.setViewportName(folderName);
            MapHandlerManager.fireEvent(createViewportEvent);
        } else {
            GeoPlatformMessage.errorMessage(LayerModuleConstants.INSTANCE.
                    CreateFolderViewportAction_errorNoLayerTitleText(),
                    LayerModuleConstants.INSTANCE.CreateFolderViewportAction_errorNoLayerBodyText());
        }
    }

    /**
     * Confirm Expand Operation on the Folder Element.
     */
    private void confirmExpandingMessage() {
        GeoPlatformMessage.confirmMessage(LayerModuleConstants.INSTANCE.
                CreateFolderViewportAction_confirmExpandTitleText(),
                LayerModuleConstants.INSTANCE.
                CreateFolderViewportAction_confirmExpandBodyText(),
                this.message);
    }
}
