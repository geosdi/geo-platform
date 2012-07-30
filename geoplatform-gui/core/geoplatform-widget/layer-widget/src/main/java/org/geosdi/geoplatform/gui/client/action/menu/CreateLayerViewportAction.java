/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.common.collect.Lists;
import java.util.List;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.client.model.FolderTreeNode;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.MapHandlerManager;
import org.geosdi.geoplatform.gui.configuration.map.puregwt.event.CreateViewportEvent;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class CreateLayerViewportAction extends MenuAction {

    private TreePanel treePanel;
    private CreateViewportEvent createViewportEvent = new CreateViewportEvent();

    public CreateLayerViewportAction(final TreePanel treePanel) {
        super("CreateLayerViewport");
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        List<GPLayerBean> layerList;
        GPBeanTreeModel item = (GPBeanTreeModel) this.treePanel.getSelectionModel().getSelectedItem();
        if (item instanceof FolderTreeNode) {
            throw new IllegalArgumentException("The CreateLayerViewportAction can "
                    + "take only layer and not folder");
        } else {
            layerList = Lists.newArrayList((GPLayerBean) item);
            createViewportEvent.setLayerList(layerList);
            createViewportEvent.setViewportName(((GPLayerBean) item).getLabel());
            MapHandlerManager.fireEvent(createViewportEvent);
        }
    }
}
