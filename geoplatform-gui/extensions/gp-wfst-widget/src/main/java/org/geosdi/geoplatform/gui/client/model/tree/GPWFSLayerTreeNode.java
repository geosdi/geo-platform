package org.geosdi.geoplatform.gui.client.model.tree;

import com.extjs.gxt.ui.client.data.ModelData;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWFSLayerTreeNode extends GPWFSTreeComposite, ModelData {

    /**
     * @return {@link Boolean}
     */
    boolean isChecked();
}
