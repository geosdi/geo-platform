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
            String pdfUrl = "";


            if (dataSource.contains("geoserver")) {
                pdfUrl = dataSource
                        + "/reflect?&layers="
                        + ((RasterTreeNode) item).getName()
                        + "&width=1024&format=application/pdf&format_options=dpi:600";

            } else {
                pdfUrl = dataSource
                        + "?service=WMS&request=GetMap&version=1.1.1&format=application/pdf&width=1024&height=768&srs=EPSG:4326&layers="
                        + ((RasterTreeNode) item).getName()
                        + "&bbox=" 
                        + ((RasterTreeNode) item).getBbox().getLowerLeftX() 
                        + "," + ((RasterTreeNode) item).getBbox().getLowerLeftY() 
                        + "," + ((RasterTreeNode) item).getBbox().getUpperRightX() 
                        + "," + ((RasterTreeNode) item).getBbox().getUpperRightY();
            }



            System.out.println(pdfUrl);

            Window.open(pdfUrl, pdfUrl, pdfUrl);
        }

    }
}
