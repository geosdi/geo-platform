/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.view.event.GeoPlatformEvents;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class ZoomToLayerExtentAction extends MenuAction {

    private TreePanel treePanel;

    public ZoomToLayerExtentAction() {
        super("ZoomToLayerExtent");
    }

    public ZoomToLayerExtentAction(TreePanel treePanel) {
        super("ZoomToLayerExtent");
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GPBeanTreeModel item = (GPBeanTreeModel) this.treePanel.getSelectionModel().getSelectedItem();

        if (item instanceof RasterTreeNode) {
            BboxClientInfo bbox = ((RasterTreeNode) item).getBbox();
            Dispatcher.forwardEvent(GeoPlatformEvents.ZOOM_TO_MAX_EXTEND, bbox);
        }
    }
}
