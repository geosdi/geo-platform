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
package org.geosdi.geoplatform.response;

import org.geosdi.geoplatform.core.model.GPRasterLayer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * @author Vincenzo Monteverde <vincenzo.monteverde@geosdi.org>
 */
public class RasterPropertiesDTO extends ShortLayerPropertiesDTO {

    private static final long serialVersionUID = -8575603239213317626L;
    //
    private Float opacity;
    //
    private Float maxScale;
    //
    private Float minScale;
    //
    @XmlElementWrapper(name = "styleList")
    @XmlElement(name = "style")
    private List<String> styleList;

    public RasterPropertiesDTO() {
    }

    public RasterPropertiesDTO(GPRasterLayer layer) {
        super(layer);
        this.opacity = layer.getOpacity();
        this.maxScale = layer.getMaxScale();
        this.minScale = layer.getMinScale();
        this.styleList = layer.getStyles();
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
            throw new IllegalArgumentException("The opacity must be between 0.0 and 1.0");
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
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ShortRasterPropertiesDTO {" + super.toString()
                + ", opacity = " + opacity
                + ", maxScale = " + maxScale
                + ", minScale = " + minScale
                + ", styles = " + styleList + "}";
    }
}