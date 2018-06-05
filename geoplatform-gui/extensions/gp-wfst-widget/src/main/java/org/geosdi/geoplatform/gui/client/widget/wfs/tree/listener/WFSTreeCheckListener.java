package org.geosdi.geoplatform.gui.client.widget.wfs.tree.listener;

import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import org.geosdi.geoplatform.gui.client.puregwt.wfs.WFSGPHandlerManager;
import org.geosdi.geoplatform.gui.impl.map.event.DisplayLayerMapEvent;
import org.geosdi.geoplatform.gui.impl.map.event.HideLayerMapEvent;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;

import javax.inject.Singleton;
import java.util.logging.Logger;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
@Singleton
public class WFSTreeCheckListener implements Listener<TreePanelEvent<GPBeanTreeModel>> {

    private final Logger logger = Logger.getLogger("WFSTreeCheckListener");

    @Override
    public void handleEvent(TreePanelEvent<GPBeanTreeModel> be) {
        logger.info("####################WFS.CheckChange on Layer : " + be.getItem().getLabel());
        if (be.isChecked()) {
            be.getItem().setChecked(TRUE);
            WFSGPHandlerManager.fireEvent(new DisplayLayerMapEvent((GPLayerBean) be.getItem()));
        } else {
            be.getItem().setChecked(FALSE);
            WFSGPHandlerManager.fireEvent(new HideLayerMapEvent((GPLayerBean) be.getItem()));
        }
    }
}
