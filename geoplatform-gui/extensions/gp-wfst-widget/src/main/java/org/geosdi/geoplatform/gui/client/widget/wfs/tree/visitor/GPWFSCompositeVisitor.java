package org.geosdi.geoplatform.gui.client.widget.wfs.tree.visitor;

import org.geosdi.geoplatform.gui.client.model.tree.GPWFSLayerTreeNode;
import org.geosdi.geoplatform.gui.client.model.tree.GPWFSRootLayerTreeNode;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPWFSCompositeVisitor {

    /**
     * @param root
     * @param <Root>
     */
    <Root extends GPWFSRootLayerTreeNode> void visit(Root root);

    /**
     * @param child
     */
    <Child extends GPWFSLayerTreeNode> void visit(Child child);
}
