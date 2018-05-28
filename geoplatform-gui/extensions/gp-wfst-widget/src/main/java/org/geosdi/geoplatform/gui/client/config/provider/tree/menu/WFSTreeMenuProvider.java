package org.geosdi.geoplatform.gui.client.config.provider.tree.menu;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.ModelStringProvider;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.action.menu.tree.WFSZoomToLayerExtentAction;
import org.geosdi.geoplatform.gui.client.config.annotation.tree.WFSLayerTreeStore;
import org.geosdi.geoplatform.gui.client.model.tree.WFSRootLayerTreeNode;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreeStore;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSTreeMenuProvider implements Provider<Menu> {

    private final WFSZoomToLayerExtentAction wfsZoomToLayerExtentAction;

    @Inject
    public WFSTreeMenuProvider(WFSZoomToLayerExtentAction wfsZoomToLayerExtentAction) {
        this.wfsZoomToLayerExtentAction = wfsZoomToLayerExtentAction;
    }

    @Override
    public Menu get() {
        Menu menu = new Menu();
        MenuItem item = new MenuItem(this.wfsZoomToLayerExtentAction.getTitle());
        item.addSelectionListener(this.wfsZoomToLayerExtentAction);
        item.setIcon(this.wfsZoomToLayerExtentAction.getImage());
        menu.add(item);
        return menu;
    }
}