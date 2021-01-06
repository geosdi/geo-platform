/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.gui.client.model.tree;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.widget.wfs.tree.visitor.GPWFSCompositeVisitor;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.temporal.dimension.GPTemporalDimensionBean;
import org.geosdi.geoplatform.gui.model.temporal.extent.GPTemporalExtentBean;
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
    private GPTemporalDimensionBean dimension;
    private GPTemporalExtentBean extent;

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
        if (theModel instanceof GPRasterBean) {
            this.minScale = ((GPRasterBean) theModel).getMinScale();
            this.maxScale = ((GPRasterBean) theModel).getMaxScale();
            this.opacity = ((GPRasterBean) theModel).getOpacity();
            this.dimension = ((GPRasterBean) theModel).getDimension();
            this.extent = ((GPRasterBean) theModel).getExtent();
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
    public boolean isSingleTileRequest() {
        return false;
    }

    @Override
    public void setSingleTileRequest(boolean singleTileRequest) {

    }

    @Override
    public float getOpacity() {
        return this.opacity;
    }

    @Override
    public void setOpacity(float opacity) {

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

    /**
     * @return {@link GPTemporalDimensionBean}
     */
    @Override
    public GPTemporalDimensionBean getDimension() {
        return this.dimension;
    }

    /**
     * @param theDimension
     */
    @Override
    public void setDimension(GPTemporalDimensionBean theDimension) {

    }

    /**
     * @return {@link GPTemporalExtentBean}
     */
    @Override
    public GPTemporalExtentBean getExtent() {
        return this.extent;
    }

    /**
     * @param theTemporalExtent
     */
    @Override
    public void setExtent(GPTemporalExtentBean theTemporalExtent) {

    }

    /**
     * @return {@link Boolean}
     */
    @Override
    public boolean isTemporalLayer() {
        return ((this.extent != null) && (this.extent.isTemporal()));
    }
}