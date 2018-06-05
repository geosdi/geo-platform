package org.geosdi.geoplatform.gui.client.model.tree;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.widget.wfs.tree.visitor.GPWFSCompositeVisitor;
import org.geosdi.geoplatform.gui.configuration.composite.GPTreeCompositeType;
import org.geosdi.geoplatform.gui.model.GPLayerBean;
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
     * @param childrens
     */
    @Override
    public void addChildrens(List<GPLayerBean> childrens) {
        checkArgument(childrens != null, "The Parameter childrens must not be null.");
        for (GPLayerBean layer : childrens) {
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

    /**
     * @param visitor
     */
    @Override
    public void accept(GPWFSCompositeVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return {@link Child}
     */
    @Override
    public <Child extends GPWFSLayerTreeNode> List<Child> getChildrens() {
        List<Child> childrens = Lists.newArrayList();
        for (ModelData child : super.getChildren()) {
            if ((child != null) && (child instanceof GPWFSLayerTreeNode))
                childrens.add((Child) child);
        }
        return childrens;
    }

    @Override
    public String toString() {
        return "WFSRootLayerTreeNode{" +
                "children = " + children +
                '}';
    }
}