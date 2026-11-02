/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 02:50:57 PM CET
//

package org.geosdi.geoplatform.xml.sld.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/sld}SymbolizerType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/sld}Geometry" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}Opacity" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}ChannelSelection" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}OverlapBehavior" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}ColorMap" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}ContrastEnhancement" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}ShadedRelief" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}ImageOutline" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "geometry",
    "opacity",
    "channelSelection",
    "overlapBehavior",
    "colorMap",
    "contrastEnhancement",
    "shadedRelief",
    "imageOutline"
})
public class RasterSymbolizer
    extends SymbolizerType
    implements ToString
{

    /**
     * A Geometry gives reference to a (the) geometry property of a
     *         feature to be used for rendering.
     * 
     */
    @XmlElement(name = "Geometry")
    protected Geometry geometry;
    @XmlElement(name = "Opacity")
    protected ParameterValueType opacity;
    /**
     * "ChannelSelection" specifies the false-color channel selection
     *         for a multi-spectral raster source.
     * 
     */
    @XmlElement(name = "ChannelSelection")
    protected ChannelSelection channelSelection;
    /**
     * "OverlapBehavior" tells a system how to behave when multiple
     *         raster images in a layer overlap each other, for example with
     *         satellite-image scenes.
     * 
     */
    @XmlElement(name = "OverlapBehavior")
    protected OverlapBehavior overlapBehavior;
    /**
     * A "ColorMap" defines either the colors of a pallet-type raster
     *         source or the mapping of numeric pixel values to colors.
     * 
     */
    @XmlElement(name = "ColorMap")
    protected ColorMap colorMap;
    /**
     * "ContrastEnhancement" defines the 'stretching' of contrast for a
     *         channel of a false-color image or for a whole grey/color image.
     *         Contrast enhancement is used to make ground features in images
     *         more visible.
     * 
     */
    @XmlElement(name = "ContrastEnhancement")
    protected ContrastEnhancement contrastEnhancement;
    /**
     * "ShadedRelief" specifies the application of relief shading
     *         (or "hill shading") to a DEM raster to give it somewhat of a
     *         three-dimensional effect and to make elevation changes more
     *         visible.
     * 
     */
    @XmlElement(name = "ShadedRelief")
    protected ShadedRelief shadedRelief;
    /**
     * "ImageOutline" specifies how individual source rasters in
     *         a multi-raster set (such as a set of satellite-image scenes)
     *         should be outlined to make the individual-image locations visible.
     * 
     */
    @XmlElement(name = "ImageOutline")
    protected ImageOutline imageOutline;

    /**
     * A Geometry gives reference to a (the) geometry property of a
     *         feature to be used for rendering.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * Imposta il valore della proprietà geometry.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     * @see #getGeometry()
     */
    public void setGeometry(Geometry value) {
        this.geometry = value;
    }

    public boolean isSetGeometry() {
        return (this.geometry!= null);
    }

    /**
     * Recupera il valore della proprietà opacity.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getOpacity() {
        return opacity;
    }

    /**
     * Imposta il valore della proprietà opacity.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setOpacity(ParameterValueType value) {
        this.opacity = value;
    }

    public boolean isSetOpacity() {
        return (this.opacity!= null);
    }

    /**
     * "ChannelSelection" specifies the false-color channel selection
     *         for a multi-spectral raster source.
     * 
     * @return
     *     possible object is
     *     {@link ChannelSelection }
     *     
     */
    public ChannelSelection getChannelSelection() {
        return channelSelection;
    }

    /**
     * Imposta il valore della proprietà channelSelection.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelSelection }
     *     
     * @see #getChannelSelection()
     */
    public void setChannelSelection(ChannelSelection value) {
        this.channelSelection = value;
    }

    public boolean isSetChannelSelection() {
        return (this.channelSelection!= null);
    }

    /**
     * "OverlapBehavior" tells a system how to behave when multiple
     *         raster images in a layer overlap each other, for example with
     *         satellite-image scenes.
     * 
     * @return
     *     possible object is
     *     {@link OverlapBehavior }
     *     
     */
    public OverlapBehavior getOverlapBehavior() {
        return overlapBehavior;
    }

    /**
     * Imposta il valore della proprietà overlapBehavior.
     * 
     * @param value
     *     allowed object is
     *     {@link OverlapBehavior }
     *     
     * @see #getOverlapBehavior()
     */
    public void setOverlapBehavior(OverlapBehavior value) {
        this.overlapBehavior = value;
    }

    public boolean isSetOverlapBehavior() {
        return (this.overlapBehavior!= null);
    }

    /**
     * A "ColorMap" defines either the colors of a pallet-type raster
     *         source or the mapping of numeric pixel values to colors.
     * 
     * @return
     *     possible object is
     *     {@link ColorMap }
     *     
     */
    public ColorMap getColorMap() {
        return colorMap;
    }

    /**
     * Imposta il valore della proprietà colorMap.
     * 
     * @param value
     *     allowed object is
     *     {@link ColorMap }
     *     
     * @see #getColorMap()
     */
    public void setColorMap(ColorMap value) {
        this.colorMap = value;
    }

    public boolean isSetColorMap() {
        return (this.colorMap!= null);
    }

    /**
     * "ContrastEnhancement" defines the 'stretching' of contrast for a
     *         channel of a false-color image or for a whole grey/color image.
     *         Contrast enhancement is used to make ground features in images
     *         more visible.
     * 
     * @return
     *     possible object is
     *     {@link ContrastEnhancement }
     *     
     */
    public ContrastEnhancement getContrastEnhancement() {
        return contrastEnhancement;
    }

    /**
     * Imposta il valore della proprietà contrastEnhancement.
     * 
     * @param value
     *     allowed object is
     *     {@link ContrastEnhancement }
     *     
     * @see #getContrastEnhancement()
     */
    public void setContrastEnhancement(ContrastEnhancement value) {
        this.contrastEnhancement = value;
    }

    public boolean isSetContrastEnhancement() {
        return (this.contrastEnhancement!= null);
    }

    /**
     * "ShadedRelief" specifies the application of relief shading
     *         (or "hill shading") to a DEM raster to give it somewhat of a
     *         three-dimensional effect and to make elevation changes more
     *         visible.
     * 
     * @return
     *     possible object is
     *     {@link ShadedRelief }
     *     
     */
    public ShadedRelief getShadedRelief() {
        return shadedRelief;
    }

    /**
     * Imposta il valore della proprietà shadedRelief.
     * 
     * @param value
     *     allowed object is
     *     {@link ShadedRelief }
     *     
     * @see #getShadedRelief()
     */
    public void setShadedRelief(ShadedRelief value) {
        this.shadedRelief = value;
    }

    public boolean isSetShadedRelief() {
        return (this.shadedRelief!= null);
    }

    /**
     * "ImageOutline" specifies how individual source rasters in
     *         a multi-raster set (such as a set of satellite-image scenes)
     *         should be outlined to make the individual-image locations visible.
     * 
     * @return
     *     possible object is
     *     {@link ImageOutline }
     *     
     */
    public ImageOutline getImageOutline() {
        return imageOutline;
    }

    /**
     * Imposta il valore della proprietà imageOutline.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageOutline }
     *     
     * @see #getImageOutline()
     */
    public void setImageOutline(ImageOutline value) {
        this.imageOutline = value;
    }

    public boolean isSetImageOutline() {
        return (this.imageOutline!= null);
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            Geometry theGeometry;
            theGeometry = this.getGeometry();
            strategy.appendField(locator, this, "geometry", buffer, theGeometry, this.isSetGeometry());
        }
        {
            ParameterValueType theOpacity;
            theOpacity = this.getOpacity();
            strategy.appendField(locator, this, "opacity", buffer, theOpacity, this.isSetOpacity());
        }
        {
            ChannelSelection theChannelSelection;
            theChannelSelection = this.getChannelSelection();
            strategy.appendField(locator, this, "channelSelection", buffer, theChannelSelection, this.isSetChannelSelection());
        }
        {
            OverlapBehavior theOverlapBehavior;
            theOverlapBehavior = this.getOverlapBehavior();
            strategy.appendField(locator, this, "overlapBehavior", buffer, theOverlapBehavior, this.isSetOverlapBehavior());
        }
        {
            ColorMap theColorMap;
            theColorMap = this.getColorMap();
            strategy.appendField(locator, this, "colorMap", buffer, theColorMap, this.isSetColorMap());
        }
        {
            ContrastEnhancement theContrastEnhancement;
            theContrastEnhancement = this.getContrastEnhancement();
            strategy.appendField(locator, this, "contrastEnhancement", buffer, theContrastEnhancement, this.isSetContrastEnhancement());
        }
        {
            ShadedRelief theShadedRelief;
            theShadedRelief = this.getShadedRelief();
            strategy.appendField(locator, this, "shadedRelief", buffer, theShadedRelief, this.isSetShadedRelief());
        }
        {
            ImageOutline theImageOutline;
            theImageOutline = this.getImageOutline();
            strategy.appendField(locator, this, "imageOutline", buffer, theImageOutline, this.isSetImageOutline());
        }
        return buffer;
    }

}
