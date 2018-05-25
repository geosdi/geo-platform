package org.geosdi.geoplatform.gui.client.widget.wfs.tree.listener;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import org.geosdi.geoplatform.gui.client.model.tree.WFSLayerTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.WFSDisplayLayerMapEvent;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.WFSHideLayerMapEvent;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

import javax.inject.Singleton;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Singleton
public class WFSTreeCheckListener implements Listener<TreePanelEvent<GPBeanTreeModel>> {

    @Override
    public void handleEvent(TreePanelEvent<GPBeanTreeModel> be) {
        if (be.isChecked()) {
            be.getItem().setChecked(TRUE);
            GPHandlerManager.fireEvent(new WFSDisplayLayerMapEvent((WFSLayerTreeNode) be.getItem()));
        } else {
            be.getItem().setChecked(FALSE);
            GPHandlerManager.fireEvent(new WFSHideLayerMapEvent((WFSLayerTreeNode) be.getItem()));
        }
    }
}
