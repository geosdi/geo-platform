/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2019 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.geosdi.geoplatform.core.model.GPFolder;
import org.geosdi.geoplatform.core.model.GPLayerInfo;
import org.geosdi.geoplatform.core.model.GPProject;
import org.geosdi.geoplatform.core.model.GPRasterLayer;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
@XmlRootElement(name = "RasterLayerDTO")
@XmlAccessorType(XmlAccessType.FIELD)
public class RasterLayerDTO extends ShortLayerDTO {
    
    private GPLayerInfo layerInfo;
    //
    private float opacity;
    //
    private Float maxScale;
    //
    private Float minScale;
    //
    @XmlElementWrapper(name = "styleList")
    @XmlElement(name = "style")
    @JsonProperty(value = "styleList")
    private List<String> styleList;
    //
    @XmlElementWrapper(name = "subLayerList")
    @XmlElement(name = "layer")
    private List<RasterLayerDTO> subLayerList;

    //<editor-fold defaultstate="collapsed" desc="Constructor method">
    /**
     * Default constructor
     */
    public RasterLayerDTO() {
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
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter and setter methods">
    /**
     * @return the layerInfo
     */
    public GPLayerInfo getLayerInfo() {
        return layerInfo;
    }

    /**
     * @param layerInfo the layerInfo to set
     */
    public void setLayerInfo(GPLayerInfo layerInfo) {
        this.layerInfo = layerInfo;
    }

    /**
     * @return the opacity
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(float opacity) {
        if (opacity < 0.0f || opacity > 1.0f) {
            throw new IllegalArgumentException(
                    "The opacity must be between 0.0 and 1.0");
        }
        this.opacity = opacity;
    }

    /**
     * @return the maxScale
     */
    public Float getMaxScale() {
        return maxScale;
    }

    /**
     * @param maxScale the maxScale to set
     */
    public void setMaxScale(Float maxScale) {
        this.maxScale = maxScale;
    }

    /**
     * @return the minScale
     */
    public Float getMinScale() {
        return minScale;
    }

    /**
     * @param minScale the minScale to set
     */
    public void setMinScale(Float minScale) {
        this.minScale = minScale;
    }

    /**
     * @return the styleList
     */
    public List<String> getStyleList() {
        return styleList;
    }

    /**
     * @param styleList the styleList to set
     */
    public void setStyleList(List<String> styleList) {
        this.styleList = styleList;
    }

    /**
     * @return the subLayerList
     */
    public List<RasterLayerDTO> getSubLayerList() {
        return subLayerList;
    }

    /**
     * @param subLayerList the subLayerList to set
     */
    public void setSubLayerList(List<RasterLayerDTO> subLayerList) {
        this.subLayerList = subLayerList;
    }
    //</editor-fold>

    public static GPRasterLayer convertToGPRasterLayer(GPProject project,
            GPFolder parent, RasterLayerDTO rasterDTO) {
        
        GPRasterLayer raster = new GPRasterLayer();
        ShortLayerDTO.convertToGPLayer(project, parent, raster, rasterDTO);
        
        raster.setLayerInfo(rasterDTO.getLayerInfo());
        raster.setOpacity(rasterDTO.getOpacity());
        raster.setStyles(rasterDTO.getStyleList());
        raster.setMaxScale(rasterDTO.getMaxScale());
        raster.setMinScale(rasterDTO.getMinScale());
        
        return raster;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "RasterLayerDTO [" + super.toString()
                + ", layerInfo = " + layerInfo
                + ", opacity = " + opacity
                + ", maxScale = " + maxScale
                + ", minScale = " + minScale
                + ", styleList = " + styleList
                + ", subLayerList = " + subLayerList + "]";
    }
    
}
