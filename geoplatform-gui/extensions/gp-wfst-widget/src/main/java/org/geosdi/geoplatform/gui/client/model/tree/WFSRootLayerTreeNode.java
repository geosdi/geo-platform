package org.geosdi.geoplatform.gui.client.model.tree;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.configuration.composite.GPTreeCompositeType;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSRootLayerTreeNode extends GPBeanTreeModel implements GPWFSRootLayerTreeNode {

    private GPBeanTreeModel twin;

    /**
     * @param theTwin
     */
    @Override
    public void bind(GPBeanTreeModel theTwin) {
        checkArgument(theTwin != null, "The Parameter Twin must not be null.");
        this.twin = theTwin;
        super.setLabel(this.twin.getLabel());
        super.setId(this.twin.getId());
        super.setzIndex(this.twin.getzIndex());
    }

    /**
     * @param childres
     */
    @Override
    public void addChildrens(List<GPRasterBean> childres) {
        checkArgument(childres != null, "The Parameter childrens must not be null.");
        for (GPRasterBean layer : childres) {
            if (layer instanceof GPLayerTreeModel) {
                WFSLayerTreeNode child = new WFSLayerTreeNode((GPLayerTreeModel) layer);
                child.setParent(this);
                super.add(child);
            }
        }
    }

    @Override
    public AbstractImagePrototype getIcon() {
        return this.twin.getIcon();
    }

    @Override
    public GPTreeCompositeType getTreeCompositeType() {
        return this.twin.getTreeCompositeType();
    }

    @Override
    public TreeStatusEnum getTreeStatus() {
        return this.twin.getTreeStatus();
    }

    @Override
    public void accept(IVisitor visitor) {
    }

    @Override
    public String toString() {
        return "WFSRootLayerTreeNode{" +
                "children = " + children +
                '}';
    }
}