/**
 * geo-platform
 * Rich webgis framework
 * http://geo-platform.org
 * ====================================================================
 * <p>
 * Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 * <p>
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 * <p>
 * ====================================================================
 * <p>
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 * <p>
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;
import org.geosdi.geoplatform.core.model.attribution.GPLayerAttribution;
import org.geosdi.geoplatform.core.model.temporal.GPTemporalLayer;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.*;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.annotation.meta.When.NEVER;
import static org.geosdi.geoplatform.gui.shared.GPLayerType.RASTER;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@ToString(callSuper = true)
@Getter
@Setter
@XmlRootElement(name = "RasterLayerDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class RasterLayerDTO extends ShortLayerDTO {

    private GPLayerInfo layerInfo;
    private float opacity;
    private Float maxScale;
    private Float minScale;
    private GPTemporalLayer temporalLayer;
    private GPLayerAttribution layerAttribution;
    @XmlElementWrapper(name = "styleList")
    @XmlElement(name = "style")
    @JsonProperty(value = "styleList")
    private List<String> styleList;
    @XmlElementWrapper(name = "subLayerList")
    @XmlElement(name = "layer")
    private List<RasterLayerDTO> subLayerList;

    //<editor-fold defaultstate="collapsed" desc="Constructor method">

    /**
     * Default constructor
     */
    public RasterLayerDTO() {
        super.setLayerType(RASTER);
    }

    /**
     * Constructor with GPRasterLayer as arg
     */
    public RasterLayerDTO(GPRasterLayer rasterLayer) {
        super(rasterLayer);
        this.layerInfo = rasterLayer.getLayerInfo();
        this.opacity = rasterLayer.getOpacity();
        this.styleList = rasterLayer.getStyles();
        this.maxScale = rasterLayer.getMaxScale();
        this.minScale = rasterLayer.getMinScale();
        this.temporalLayer = rasterLayer.getTemporalLayer();
        this.layerAttribution = rasterLayer.getLayerAttribution();
    }
    //</editor-fold>

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

    /**
     * @param project
     * @param parent
     * @param rasterDTO
     * @return {@link GPRasterLayer}
     */
    public static GPRasterLayer convertToGPRasterLayer(@Nonnull(when = NEVER) GPProject project,
            @Nonnull(when = NEVER) GPFolder parent, @Nonnull(when = NEVER) RasterLayerDTO rasterDTO) throws Exception {
        checkArgument(project != null, "The Parameter project must not be null.");
        checkArgument(parent != null, "The Parameter parent must not be null.");
        checkArgument(rasterDTO != null, "The Parameter rasterDTO must not be null.");
        GPRasterLayer raster = new GPRasterLayer();
        ShortLayerDTO.convertToGPLayer(project, parent, raster, rasterDTO);
        raster.setLayerInfo(rasterDTO.getLayerInfo());
        raster.setOpacity(rasterDTO.getOpacity());
        raster.setStyles(rasterDTO.getStyleList());
        raster.setMaxScale(rasterDTO.getMaxScale());
        raster.setMinScale(rasterDTO.getMinScale());
        raster.setTemporalLayer(rasterDTO.getTemporalLayer());
        raster.setLayerAttribution(rasterDTO.getLayerAttribution());
        return raster;
    }
}
