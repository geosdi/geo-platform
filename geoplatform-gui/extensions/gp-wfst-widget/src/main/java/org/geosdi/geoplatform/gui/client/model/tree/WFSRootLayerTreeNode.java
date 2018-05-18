package org.geosdi.geoplatform.gui.client.model.tree;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.configuration.composite.GPTreeCompositeType;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.GPFolderClientInfo;
import org.geosdi.geoplatform.gui.model.tree.AbstractRootTreeNode;
import org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

import java.util.List;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSRootLayerTreeNode extends GPBeanTreeModel {

    private final GPBeanTreeModel father;

    public WFSRootLayerTreeNode(GPBeanTreeModel theFather){
        this.father = theFather;
        super.setLabel(this.father.getLabel());
        super.setId(this.father.getId());
        super.setzIndex(this.father.getzIndex());
    }

    @Override
    public AbstractImagePrototype getIcon() {
        return this.father.getIcon();
    }

    @Override
    public GPTreeCompositeType getTreeCompositeType() {
        return this.father.getTreeCompositeType();
    }

    @Override
    public TreeStatusEnum getTreeStatus() {
        return this.father.getTreeStatus();
    }

    @Override
    public void accept(IVisitor visitor) {
    }

    @Override
    public String toString() {
        return "WFSRootLayerTreeNode{} " + super.toString();
    }
}
