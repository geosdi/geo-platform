/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.List;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
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
public class CreateFolderViewportAction extends MenuAction {

    private TreePanel treePanel;
    private Listener<MessageBoxEvent> message;
    private Listener executor;
    private FolderTreeNode selectedElement;
    private CreateViewportEvent createViewportEvent = new CreateViewportEvent();

    public CreateFolderViewportAction(final TreePanel treePanel) {
        super("CreateViewport");
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
        List<GPLayerBean> layerList = Lists.newArrayList();
        for (ModelData data : modelDataList) {
            if (data instanceof GPLayerBean) {
                layerList.add((GPLayerBean) data);
            }
        }
        return layerList;
    }

    private void execute(List<ModelData> modelDataList, String folderName) {
        createViewportEvent.setLayerList(generateLayerList(modelDataList));
        createViewportEvent.setViewportName(folderName);
        MapHandlerManager.fireEvent(createViewportEvent);
    }

    /**
     * Confirm Expand Operation on the Folder Element.
     */
    private void confirmExpandingMessage() {
        GeoPlatformMessage.confirmMessage("Expand Folder",
                "The folder you are trying to put elements "
                + "must be expanded before the adding operation. "
                + "Do you want to expand it?",
                this.message);
    }
}
