package org.geosdi.geoplatform.gui.client.model.tree;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;
import org.geosdi.geoplatform.gui.model.tree.state.IGPLayerTreeState;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSLayerTreeNode extends GPLayerTreeModel {

    private final GPLayerTreeModel model;

    public WFSLayerTreeNode(GPLayerTreeModel theModel){
        this.model = theModel;
        super.setAbstractText(this.model.getAbstractText());
        super.setAlias(this.model.getAlias());
        super.setBbox(this.model.getBbox());
        super.setCqlFilter(this.model.getCqlFilter());
        super.setCrs(this.model.getCrs());
        super.setDataSource(this.model.getDataSource());
        super.setId(this.model.getId());
        super.setLayerType(this.model.getLayerType());
        super.setName(this.model.getName());
        super.setObservable(this.model.getObservable());
        super.setTitle(this.model.getTitle());
        super.setStyles(this.model.getStyles());
        super.setLabel(this.model.getLabel());
        super.setzIndex(this.model.getzIndex());
        super.setTimeFilter(this.model.getTimeFilter());
        super.setVariableTimeFilter(this.model.getVariableTimeFilter());
    }

    @Override
    public IGPLayerTreeState getState() {
        return this.model.getState();
    }

    @Override
    public void setRefreshTime(int refreshTime) {

    }

    @Override
    public AbstractImagePrototype getIcon() {
        return this.model.getIcon();
    }

    @Override
    public TreeStatusEnum getTreeStatus() {
        return TreeStatusEnum.RASTER_SELECTED;
    }

    @Override
    public void accept(IVisitor visitor) {

    }
}
