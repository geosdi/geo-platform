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
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class ExportoToPDF extends MenuAction {

    private TreePanel treePanel;

    public ExportoToPDF() {
        super("ExportToPDF");
    }

    public ExportoToPDF(TreePanel treePanel) {
        super("ExportToPDF");
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GPBeanTreeModel item = (GPBeanTreeModel) this.treePanel.getSelectionModel().getSelectedItem();

        if (item instanceof RasterTreeNode) {
            String dataSource = ((RasterTreeNode) item).getDataSource();

            // kml preview
            final String pdfUrl = dataSource + 
                    "/reflect?&layers=" 
                    + item.getLabel()
                    + "&width=1024&format=application/pdf&format_options=dpi:600";

            System.out.println(pdfUrl);

            Window.open(pdfUrl, pdfUrl, pdfUrl);
        }

    }
}
