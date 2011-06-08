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
public class ExportoToKML extends MenuAction {

    private TreePanel treePanel;

    public ExportoToKML() {
        super("ExportToKML");
    }

    public ExportoToKML(TreePanel treePanel) {
        super("ExportToKML");
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GPBeanTreeModel item = (GPBeanTreeModel) this.treePanel.getSelectionModel().getSelectedItem();

        if (item instanceof RasterTreeNode) {
            String dataSource = ((RasterTreeNode) item).getDataSource();

            // kml preview
            final String kmlUrl = dataSource + "/kml?layers=" + item.getLabel()+"&legend=true&format_options=kmattr:false";
            System.out.println(kmlUrl);
            Window.open(kmlUrl, kmlUrl, kmlUrl);
        }

    }
}
