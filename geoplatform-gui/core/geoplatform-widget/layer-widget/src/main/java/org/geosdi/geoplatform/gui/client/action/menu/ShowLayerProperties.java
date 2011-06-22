/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.geosdi.geoplatform.gui.client.action.menu;

import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import org.geosdi.geoplatform.gui.action.menu.MenuAction;

/**
 *
 * @author Francesco Izzi - CNR IMAA geoSDI Group
 * @mail francesco.izzi@geosdi.org
 */
public class ShowLayerProperties extends MenuAction {

    private TreePanel treePanel;

    public ShowLayerProperties() {
        super("LayerProperties");
    }

    public ShowLayerProperties(TreePanel treePanel) {
        super("LayerProperties");
        this.treePanel = treePanel;
    }

    @Override
    public void componentSelected(MenuEvent ce) {
    }
}
