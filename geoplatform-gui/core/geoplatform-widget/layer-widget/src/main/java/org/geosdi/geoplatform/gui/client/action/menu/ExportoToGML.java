/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Window;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class ExportoToGML extends MenuAction {

    private TreePanel treePanel;

    public ExportoToGML() {
        super("ExportToGML");
    }

    public ExportoToGML(TreePanel treePanel) {
        super("ExportToGML");
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GPBeanTreeModel item = (GPBeanTreeModel) this.treePanel.getSelectionModel().getSelectedItem();

        if (item instanceof RasterTreeNode) {
            String dataSource = ((RasterTreeNode) item).getDataSource();

            // gml preview (we actually want it only for vector layers)
            dataSource = dataSource.replaceAll("wms", "wfs");
            String gmlUrl =
                    dataSource + "?service=WFS&version=1.0.0&request=GetFeature&typeName="
                    + item.getLabel() + "&maxFeatures=50";
            Window.open(gmlUrl, gmlUrl, gmlUrl);
        }
    }
}
