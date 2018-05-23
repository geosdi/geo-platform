package org.geosdi.geoplatform.gui.client.model.tree;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.widget.wfs.tree.visitor.GPWFSCompositeVisitor;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;
import org.geosdi.geoplatform.gui.model.tree.state.IGPLayerTreeState;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public class WFSLayerTreeNode extends GPLayerTreeModel implements GPWFSLayerTreeNode, GPRasterBean {

    private final GPLayerTreeModel model;
    private Float maxScale;
    private Float minScale;
    private float opacity;

    /**
     * @param theModel
     */
    public WFSLayerTreeNode(GPLayerTreeModel theModel) {
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
        super.setChecked(this.model.isChecked());
        if(theModel instanceof GPRasterBean){
            this.minScale = ((GPRasterBean)theModel).getMinScale();
            this.maxScale = ((GPRasterBean)theModel).getMaxScale();
            this.opacity = ((GPRasterBean)theModel).getOpacity();
        }
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

    /**
     * @param visitor
     */
    @Override
    public void accept(GPWFSCompositeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setSingleTileRequest(boolean singleTileRequest) {

    }

    @Override
    public boolean isSingleTileRequest() {
        return false;
    }

    @Override
    public void setOpacity(float opacity) {

    }

    @Override
    public float getOpacity() {
        return this.opacity;
    }

    @Override
    public Float getMaxScale() {
        return this.maxScale;
    }

    @Override
    public void setMaxScale(Float maxScale) {

    }

    @Override
    public Float getMinScale() {
        return this.minScale;
    }

    @Override
    public void setMinScale(Float minScale) {

    }
}