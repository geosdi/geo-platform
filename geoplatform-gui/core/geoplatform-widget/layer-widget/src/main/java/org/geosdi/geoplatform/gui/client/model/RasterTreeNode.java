/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.gui.client.model;

import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import org.geosdi.geoplatform.gui.client.action.menu.factory.TreeContextMenuFactory;
import org.geosdi.geoplatform.gui.client.model.state.LayerStateEnum;
import org.geosdi.geoplatform.gui.configuration.map.client.layer.ClientRasterInfo;
import org.geosdi.geoplatform.gui.model.GPRasterBean;
import org.geosdi.geoplatform.gui.model.tree.GPLayerTreeModel;
import org.geosdi.geoplatform.gui.model.tree.state.IGPLayerTreeState;
import org.geosdi.geoplatform.gui.model.tree.visitor.IVisitor;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 *
 */
public class RasterTreeNode extends GPLayerTreeModel implements GPRasterBean {

    private static final long serialVersionUID = 8265365333381641340L;

    public enum GPRasterKeyValue {

        OPACITY("opacity");
        //
        private String value;

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

    public RasterTreeNode() {
    }

    /**
     * @Constructor
     *
     * @param label
     */
    public RasterTreeNode(ClientRasterInfo layer) {
        super(layer);
        super.setLabel(layer.getTitle()); // Label of a vector is different
        this.setAlias(layer.getAlias());
        this.setStyles(layer.getStyles());
        this.setOpacity(layer.getOpacity());
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
    public void setOpacity(float opacity) {
        this.opacity = opacity;
        set(GPRasterKeyValue.OPACITY.toString(), this.opacity);
    }

    /**
     * (non-Javadoc)
     *
     * @see org.geosdi.geoplatform.gui.model.tree.GPBeanTreeModel#getIcon()
     */
    @Override
    public AbstractImagePrototype getIcon() {
        return this.getState().getIcon();
    }

    @Override
    public IGPLayerTreeState getState() {
        if (super.state == null) {
            super.state = LayerStateEnum.RASTER_NO_OP.getValue();
        }
        return super.state;
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
    public Menu getTreeContextMenu() {
        return TreeContextMenuFactory.getLayerContextMenu();
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitRaster(this);
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RasterTreeNode [getLabel()=" + getLabel()
                + ", getDataSource()=" + getDataSource()
                + ", getCrs()=" + getCrs() + ", getBbox()=" + getBbox()
                + ", getLayerType()=" + getLayerType() + ", getzIndex()="
                + getzIndex() + "]";
    }
}
