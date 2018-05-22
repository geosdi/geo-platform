package org.geosdi.geoplatform.gui.client.config.provider.tree;

import org.geosdi.geoplatform.gui.client.model.tree.WFSRootLayerTreeNode;

import javax.inject.Provider;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSRootLayerTreeNodeProvider implements Provider<WFSRootLayerTreeNode> {

    @Override
    public WFSRootLayerTreeNode get() {
        return new WFSRootLayerTreeNode();
    }
}