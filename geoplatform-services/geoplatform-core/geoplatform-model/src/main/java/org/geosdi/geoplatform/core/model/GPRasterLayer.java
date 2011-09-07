//<editor-fold defaultstate="collapsed" desc="License">
/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//</editor-fold>
package org.geosdi.geoplatform.core.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 * 
 */
@XmlRootElement(name = "RasterLayer")
@Entity(name = "RasterLayer")
@Table(name = "gp_raster_layer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "raster_layer")
public class GPRasterLayer extends GPLayer {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1852288981980627642L;
    //
    @Embedded
    private GPLayerInfo layerInfo;
    //
    @Column
    private float opacity = 1.0f;    
    //
    @ManyToOne(optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPFolder folder;

    /**
     * @return the opacity
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * @param opacity
     *              the opacity to set
     */
    public void setOpacity(float opacity) {
        if (opacity < 0.0f || opacity > 1.0f) {
            throw new IllegalArgumentException("The opacity must be between 0.0 and 1.0");
        }
        this.opacity = opacity;
    }

    /**
     * @return the layerInfo
     */
    public GPLayerInfo getLayerInfo() {
        return layerInfo;
    }

    /**
     * @param layerInfo
     *            the layerInfo to set
     */
    public void setLayerInfo(GPLayerInfo layerInfo) {
        this.layerInfo = layerInfo;
    }

    /**
     * @return the folder
     */
    @Override
    public GPFolder getFolder() {
        return folder;
    }

    /**
     * @param folder
     *            the bbox to folder
     */
    @Override
    public void setFolder(GPFolder folder) {
        this.folder = folder;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("GPRasterLayer {");
        str.append(super.toString());
        str.append(", opacity=").append(opacity);
        str.append(", layerInfo=").append(layerInfo).append("}");
        return str.toString();
    }
}
