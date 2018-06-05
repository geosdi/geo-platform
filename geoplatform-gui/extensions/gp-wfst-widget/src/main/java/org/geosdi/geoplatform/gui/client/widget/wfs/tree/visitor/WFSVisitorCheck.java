package org.geosdi.geoplatform.gui.client.widget.wfs.tree.visitor;

import org.geosdi.geoplatform.gui.client.model.tree.GPWFSLayerTreeNode;
import org.geosdi.geoplatform.gui.client.model.tree.GPWFSRootLayerTreeNode;
import org.geosdi.geoplatform.gui.client.widget.tree.GPTreePanel;

import static com.google.common.base.Preconditions.checkArgument;
import static java.lang.Boolean.TRUE;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class WFSVisitorCheck implements GPWFSCompositeVisitor {

    private final GPTreePanel tree;

    /**
     * @param theTree
     */
    public WFSVisitorCheck(GPTreePanel theTree) {
        checkArgument(theTree != null, "The Parameter Tree must not be null.");
        this.tree = theTree;
    }

    /**
     * @param root
     */
    @Override
    public <Root extends GPWFSRootLayerTreeNode> void visit(Root root) {
        checkArgument(root != null, "The Parameter root must not be null.");
        for (GPWFSLayerTreeNode layerTreeNode : root.getChildrens()) {
            layerTreeNode.accept(this);
        }
    }

    /**
     * @param child
     */
    @Override
    public <Child extends GPWFSLayerTreeNode> void visit(Child child) {
        checkArgument(child != null, "The Parameter layerTreeNode must not be null.");
        if (child.isChecked()) {
            this.tree.setChecked(child, TRUE);
        }
    }
}