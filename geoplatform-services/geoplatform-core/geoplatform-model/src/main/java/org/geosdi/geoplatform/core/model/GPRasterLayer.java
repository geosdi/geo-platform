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

package org.geosdi.geoplatform.core.model;

import lombok.Getter;
import lombok.Setter;
import org.geosdi.geoplatform.core.model.attribution.GPLayerAttribution;
import org.geosdi.geoplatform.core.model.temporal.GPTemporalLayer;
import org.geosdi.geoplatform.gui.shared.GPLayerType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;
import static org.geosdi.geoplatform.gui.shared.GPLayerType.WMS;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 *
 */
@XmlRootElement(name = "RasterLayer")
@XmlAccessorType(FIELD)
@Entity(name = "RasterLayer")
@Table(name = "gp_raster_layer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "raster_layer")
public class GPRasterLayer extends GPLayer {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1852288981980627642L;
    //
    @Getter
    @Column
    private float opacity = 1.0f;
    // The character , separated list of styles
    @Column(length = 500, columnDefinition = "TEXT")
    private String styles;
    //
    @Getter
    @Setter
    @Embedded
    private GPLayerInfo layerInfo;
    //
    @Getter
    @Setter
    @Column(name = "max_scale")
    private Float maxScale;
    //
    @Getter
    @Setter
    @Column(name = "min_scale")
    private Float minScale;
    @Getter
    @Setter
    @Embedded
    private GPTemporalLayer temporalLayer;
    @Getter
    @Setter
    @Embedded
    private GPLayerAttribution layerAttribution;
    //
    @Getter
    @Setter
    @ManyToOne(optional = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GPFolder folder;
    //
    @Getter
    @Setter
    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Index(name = "RASTER_PROJECT_ID_INDEX")
    private GPProject project;

    public GPRasterLayer() {
        super.setLayerType(WMS);
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(float opacity) {
        if (opacity < 0.0f || opacity > 1.0f) {
            throw new IllegalArgumentException("The opacity must be between 0.0 and 1.0");
        }
        this.opacity = opacity;
    }

    /**
     * @return the styles
     */
    public List<String> getStyles() {
        return ((this.styles != null) ? asList(styles.split(",")) : new ArrayList<String>(0));
    }

    /**
     * @param theStyles the styles to set
     */
    public void setStyles(List<String> theStyles) {
        this.styles = ((theStyles != null) ? theStyles.stream().collect(joining(",")) : null);
    }

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    public boolean isTemporalLayer() {
        return ((this.temporalLayer != null) && (this.temporalLayer.isTemporalLayer()));
    }

    /**
     * @return {@link Boolean}
     */
    @XmlTransient
    public boolean isSetAttribution() {
        return ((this.layerAttribution != null) && (this.layerAttribution.isAvailable()));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        str.append(super.toString());
        str.append(", opacity = ").append(opacity);
        str.append(", styles = ").append(styles);
        str.append(", layerInfo = ").append(layerInfo);
        str.append(", maxScale = ").append(maxScale);
        str.append(", minScale = ").append(minScale);
        str.append(", isTemporal = ").append((this.isTemporalLayer() ? "YES" : "NO"));
        return str.append("}").toString();
    }
}
