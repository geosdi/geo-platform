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
public class ExportoToCSV extends MenuAction {

    private TreePanel treePanel;

    public ExportoToCSV(TreePanel treePanel) {
        super("ExportToCSV");
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
                    + ((RasterTreeNode) item).getName() + "&maxFeatures=50&outputFormat=csv";
            Window.open(gmlUrl, gmlUrl, gmlUrl);
        }
    }
}
