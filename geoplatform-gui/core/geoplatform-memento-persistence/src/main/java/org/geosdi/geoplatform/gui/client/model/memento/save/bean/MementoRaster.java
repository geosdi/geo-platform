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
package org.geosdi.geoplatform.gui.client.model.memento.save.bean;

import org.geosdi.geoplatform.gui.action.ISave;
import org.geosdi.geoplatform.gui.model.logo.GPAttributionLogoURLBean;
import org.geosdi.geoplatform.gui.model.temporal.dimension.GPTemporalDimensionBean;
import org.geosdi.geoplatform.gui.model.temporal.extent.GPTemporalExtentBean;
import org.geosdi.geoplatform.gui.model.tree.AbstractRasterTreeModel;
import org.geosdi.geoplatform.gui.observable.Observable;
import org.geosdi.geoplatform.gui.observable.Observer;

import java.util.List;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
public class MementoRaster extends AbstractMementoLayer<AbstractRasterTreeModel> implements Observer {

    private static final long serialVersionUID = 4343601977419367986L;
    //
    private GPTemporalDimensionBean dimension;
    private GPTemporalExtentBean extent;
    private List<String> styles;
    private GPAttributionLogoURLBean logoURLBean;

    public MementoRaster() {
    }

    //TODO: How to provide the GPLayerInfo?
    //private GPLayerInfo layerInfo;
    //
    public MementoRaster(ISave saveAction) {
        super(saveAction);
    }

    /**
     * @return {@link GPTemporalDimensionBean}
     */
    public GPTemporalDimensionBean getDimension() {
        return dimension;
    }

    /**
     * @param theDimension
     */
    public void setDimension(GPTemporalDimensionBean theDimension) {
        this.dimension = theDimension;
    }

    /**
     * @return {@link GPTemporalExtentBean}
     */
    public GPTemporalExtentBean getExtent() {
        return extent;
    }

    /**
     * @param theExtent
     */
    public void setExtent(GPTemporalExtentBean theExtent) {
        this.extent = theExtent;
    }

    /**
     * @return the styles
     */
    public List<String> getStyles() {
        return styles;
    }

    /**
     * @param styles the styles to set
     */
    public void setStyles(List<String> styles) {
        this.styles = styles;
    }

    /**
     * @return {@link Boolean}
     */
    public boolean isTemporalLayer() {
        return ((this.extent != null) && (this.extent.isTemporal()));
    }


    public GPAttributionLogoURLBean getLogoURLBean() {
        return logoURLBean;
    }

    public void setLogoURLBean(GPAttributionLogoURLBean logoURLBean) {
        this.logoURLBean = logoURLBean;
    }

    public boolean isSetAttribution() {
        return this.logoURLBean != null && this.logoURLBean.getOnlineResource() != null;
    }

    @Override
    public void update(Observable o, Object arg) {
        super.setIdBaseElement((Long) arg);
    }


    @Override
    public String toString() {
        return "MementoRaster{" + "dimension=" + dimension + ", extent=" + extent + ", styles=" + styles + ", logoURLBean=" + logoURLBean + '}';
    }
}
