/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.user.client.Window;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;
import org.geosdi.geoplatform.gui.client.model.RasterTreeNode;
import org.geosdi.geoplatform.gui.client.model.VectorTreeNode;
import org.geosdi.geoplatform.gui.configuration.map.client.geometry.BboxClientInfo;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class ExportoToShpZip extends MenuAction {

    private TreePanel treePanel;

    public ExportoToShpZip() {
        super("ExportToShpZip");
    }

    public ExportoToShpZip(TreePanel treePanel) {
        super("ExportToShpZip");
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
        GPBeanTreeModel item = (GPBeanTreeModel) this.treePanel.getSelectionModel().getSelectedItem();

        if (item instanceof RasterTreeNode) {
            String dataSource = ((RasterTreeNode) item).getDataSource();

            // shape-zip
            //TODO: separate server url with server wms and server wfs url and wcs ?
            final String shpZipURL = dataSource.replaceAll("wms", "")
                     + 
                    "ows?service=WFS&version=1.0.0&request=GetFeature&typeName=" 
                    + item.getLabel()+"&maxFeatures=50&outputFormat=SHAPE-ZIP";

            System.out.println(shpZipURL);

            Window.open(shpZipURL, shpZipURL, shpZipURL);
        } {
        Info.display("WARNING", "Il layer selezionato non è un layer vettoriale");
    }

    }
}
