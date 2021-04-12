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
package org.geosdi.geoplatform.gui.client.model;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.i18n.LayerModuleConstants;
import org.geosdi.geoplatform.gui.client.model.state.LayerStateEnum;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientRasterInfo;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractRasterTreeModel;
import org.geosdi.geoplatform.gui.model.tree.TreeStatusEnum;
import org.geosdi.geoplatform.gui.model.tree.state.IGPLayerTreeState;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class RasterTreeNode extends AbstractRasterTreeModel implements GPRasterBean {

    private static final long serialVersionUID = 8265365333381641340L;

    public enum GPRasterKeyValue {

        OPACITY(LayerModuleConstants.INSTANCE.RasterTreeNode_opacityText()),
        MAX_SCALE(LayerModuleConstants.INSTANCE.RasterTreeNode_maxScaleText()),
        MIN_SCALE(LayerModuleConstants.INSTANCE.RasterTreeNode_minScaleText()),
        SINGLE_TILE_REQUEST(LayerModuleConstants.INSTANCE.RasterTreeNode_singleTileRequestText());
        //
        private final String value;

        GPRasterKeyValue(String theValue) {
            this.value = theValue;
        }

        @Override
        public String toString() {
            return this.value;
        }

    }

    //
    private float opacity = 1.0f;
    private Float maxScale;
    private Float minScale;
    private boolean singleTileRequest;

    public RasterTreeNode() {
    }

    /**
     * @param layer
     * @Constructor
     *
     */
    public RasterTreeNode(ClientRasterInfo layer) {
        super(layer);
        super.setLabel(layer.getTitle()); // Label of a vector is different
        this.setAlias(layer.getAlias());
        this.setStyles(layer.getStyles());
        this.setOpacity(layer.getOpacity());
        this.setMaxScale(layer.getMaxScale());
        this.setMinScale(layer.getMinScale());
        this.setSingleTileRequest(layer.isSingleTileRequest());
        this.setDimension(layer.getDimension());
        this.setLogoURLBean(layer.getLogoURLBean());
        this.setExtent(layer.getExtent());
    }

    /**
     * @Constructor
     *
     * @param UUID
     */
    public RasterTreeNode(String UUID) {
        super.setUUID(UUID);
    }

    @Override
    public boolean isSingleTileRequest() {
        return singleTileRequest;
    }

    @Override
    public final void setSingleTileRequest(boolean singleTileRequest) {
        this.singleTileRequest = singleTileRequest;
        set(GPRasterKeyValue.SINGLE_TILE_REQUEST.toString(), this.singleTileRequest);
    }

    /**
     * @return the opacity
     */
    @Override
    public float getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    @Override
    public final void setOpacity(float opacity) {
        this.opacity = opacity;
        set(GPRasterKeyValue.OPACITY.toString(), this.opacity);
    }

    /**
     * @return the maxScale
     */
    @Override
    public Float getMaxScale() {
        return maxScale;
    }

    /**
     * @param maxScale the maxScale to set
     */
    @Override
    public final void setMaxScale(Float maxScale) {
        this.maxScale = maxScale;
        super.set(GPRasterKeyValue.MAX_SCALE.toString(), this.maxScale);
    }

    /**
     * @return the minScale
     */
    @Override
    public Float getMinScale() {
        return minScale;
    }

    /**
     * @param minScale the minScale to set
     */
    @Override
    public final void setMinScale(Float minScale) {
        this.minScale = minScale;
        super.set(GPRasterKeyValue.MIN_SCALE.toString(), this.minScale);
    }

    @Override
    public AbstractImagePrototype getIcon() {
        return this.getState().getIcon();
    }

    @Override
    public IGPLayerTreeState getState() {
        return super.state = (super.state == null) ? LayerStateEnum.RASTER_NO_OP.getValue() : super.state;
    }

    @Override
    public void setRefreshTime(int refreshTime) {
        this.getState().setRefreshTime(refreshTime, this);
    }

    @Override
    public void setCqlFilter(String cqlFilter) {
        super.setCqlFilter(cqlFilter);
        this.getState().setCqlFilter(cqlFilter, this);
    }

    @Override
    public void setTimeFilter(String timeFilter) {
        super.setTimeFilter(timeFilter);
        this.getState().setTimeFilter(timeFilter, this);
    }



    @Override
    public TreeStatusEnum getTreeStatus() {
        return TreeStatusEnum.RASTER_SELECTED;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitRaster(this);
    }

    @Override
    public String toString() {
        return "RasterTreeNode{" + "opacity=" + opacity + ", maxScale=" + maxScale + ", minScale=" + minScale + ", singleTileRequest=" + singleTileRequest + '}';
    }
}