package org.geosdi.geoplatform.gui.client.config.provider.tree.menu;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import org.geosdi.geoplatform.gui.client.action.menu.tree.WFSZoomToLayerExtentAction;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSTreeRootMenuProvider implements Provider<Menu> {

    @Override
    public Menu get() {
        Menu menu = new Menu();
        return menu;
    }
}