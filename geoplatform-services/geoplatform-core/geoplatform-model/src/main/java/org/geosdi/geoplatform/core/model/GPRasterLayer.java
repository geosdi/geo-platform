/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.core.model.attribution.GPLayerAttribution;
import org.geosdi.geoplatform.core.model.temporal.GPTemporalLayer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

import static jakarta.xml.bind.annotation.XmlAccessType.FIELD;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static org.geosdi.geoplatform.gui.shared.GPLayerType.WMS;

/**
 * @author Francesco Izzi - CNR IMAA - geoSDI
 */
@XmlRootElement(name = "RasterLayer")
@XmlAccessorType(FIELD)
@Entity(name = "RasterLayer")
@Table(name = "gp_raster_layer")
@PrimaryKeyJoinColumn(name = "gp_layer_id")
@OnDelete(action = OnDeleteAction.CASCADE)
//@DiscriminatorValue("GPRasterLayer")
@ToString(callSuper = true)
public class GPRasterLayer extends GPLayer implements IGPRasterLayer {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1852288981980627642L;
    //
    @Getter
    @Column
    private float opacity = 1.0f;
    // The character , separated list of styles
    @Column
    @Lob
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
    @Override
    public List<String> getStyles() {
        return ((this.styles != null) ? asList(styles.split(",")) : new ArrayList<String>(0));
    }

    /**
     * @param theStyles the styles to set
     */
    @Override
    public void setStyles(List<String> theStyles) {
        this.styles = ((theStyles != null) ? theStyles.stream().collect(joining(",")) : null);
    }
}