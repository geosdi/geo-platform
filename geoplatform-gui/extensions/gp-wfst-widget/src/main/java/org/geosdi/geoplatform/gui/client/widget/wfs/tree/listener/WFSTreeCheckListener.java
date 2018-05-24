package org.geosdi.geoplatform.gui.client.widget.wfs.tree.listener;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.google.gwt.core.client.GWT;
import org.geosdi.geoplatform.gui.client.model.tree.WFSLayerTreeNode;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.WFSDisplayLayerMapEvent;
import org.geosdi.geoplatform.gui.client.puregwt.map.event.WFSHideLayerMapEvent;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.puregwt.GPHandlerManager;

import javax.inject.Singleton;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Singleton
public class WFSTreeCheckListener implements Listener<TreePanelEvent<GPBeanTreeModel>> {

    @Override
    public void handleEvent(TreePanelEvent<GPBeanTreeModel> be) {
        if(be.isChecked()) {
            GWT.log("@@@@@@@@@@@@@@@@@CODICE ESEGUITO CHECKED:");
            be.getItem().setChecked(Boolean.TRUE);
            GPHandlerManager.fireEvent(new WFSDisplayLayerMapEvent((WFSLayerTreeNode) be.getItem()));
        }else{
            GWT.log("@@@@@@@@@@@@@@@@@CODICE ESEGUITO UNCHECKED:");
            be.getItem().setChecked(Boolean.FALSE);
            GPHandlerManager.fireEvent(new WFSHideLayerMapEvent((WFSLayerTreeNode) be.getItem()));
        }
    }
}
