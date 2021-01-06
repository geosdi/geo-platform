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
//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:12:35 PM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/sld}SymbolizerType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/sld}Geometry" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Opacity" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}ChannelSelection" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}OverlapBehavior" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}ColorMap" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}ContrastEnhancement" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}ShadedRelief" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}ImageOutline" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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

    @XmlElement(name = "Geometry")
    protected GeometryElement geometry;
    @XmlElement(name = "Opacity")
    protected ParameterValueType opacity;
    @XmlElement(name = "ChannelSelection")
    protected ChannelSelectionElement channelSelection;
    @XmlElement(name = "OverlapBehavior")
    protected OverlapBehaviorElement overlapBehavior;
    @XmlElement(name = "ColorMap")
    protected ColorMapElement colorMap;
    @XmlElement(name = "ContrastEnhancement")
    protected ContrastEnhancementElement contrastEnhancement;
    @XmlElement(name = "ShadedRelief")
    protected ShadedReliefElement shadedRelief;
    @XmlElement(name = "ImageOutline")
    protected ImageOutlineElement imageOutline;

    /**
     * Recupera il valore della proprietà geometry.
     * 
     * @return
     *     possible object is
     *     {@link GeometryElement }
     *     
     */
    public GeometryElement getGeometry() {
        return geometry;
    }

    /**
     * Imposta il valore della proprietà geometry.
     * 
     * @param value
     *     allowed object is
     *     {@link GeometryElement }
     *     
     */
    public void setGeometry(GeometryElement value) {
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
     * Recupera il valore della proprietà channelSelection.
     * 
     * @return
     *     possible object is
     *     {@link ChannelSelectionElement }
     *     
     */
    public ChannelSelectionElement getChannelSelection() {
        return channelSelection;
    }

    /**
     * Imposta il valore della proprietà channelSelection.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelSelectionElement }
     *     
     */
    public void setChannelSelection(ChannelSelectionElement value) {
        this.channelSelection = value;
    }

    public boolean isSetChannelSelection() {
        return (this.channelSelection!= null);
    }

    /**
     * Recupera il valore della proprietà overlapBehavior.
     * 
     * @return
     *     possible object is
     *     {@link OverlapBehaviorElement }
     *     
     */
    public OverlapBehaviorElement getOverlapBehavior() {
        return overlapBehavior;
    }

    /**
     * Imposta il valore della proprietà overlapBehavior.
     * 
     * @param value
     *     allowed object is
     *     {@link OverlapBehaviorElement }
     *     
     */
    public void setOverlapBehavior(OverlapBehaviorElement value) {
        this.overlapBehavior = value;
    }

    public boolean isSetOverlapBehavior() {
        return (this.overlapBehavior!= null);
    }

    /**
     * Recupera il valore della proprietà colorMap.
     * 
     * @return
     *     possible object is
     *     {@link ColorMapElement }
     *     
     */
    public ColorMapElement getColorMap() {
        return colorMap;
    }

    /**
     * Imposta il valore della proprietà colorMap.
     * 
     * @param value
     *     allowed object is
     *     {@link ColorMapElement }
     *     
     */
    public void setColorMap(ColorMapElement value) {
        this.colorMap = value;
    }

    public boolean isSetColorMap() {
        return (this.colorMap!= null);
    }

    /**
     * Recupera il valore della proprietà contrastEnhancement.
     * 
     * @return
     *     possible object is
     *     {@link ContrastEnhancementElement }
     *     
     */
    public ContrastEnhancementElement getContrastEnhancement() {
        return contrastEnhancement;
    }

    /**
     * Imposta il valore della proprietà contrastEnhancement.
     * 
     * @param value
     *     allowed object is
     *     {@link ContrastEnhancementElement }
     *     
     */
    public void setContrastEnhancement(ContrastEnhancementElement value) {
        this.contrastEnhancement = value;
    }

    public boolean isSetContrastEnhancement() {
        return (this.contrastEnhancement!= null);
    }

    /**
     * Recupera il valore della proprietà shadedRelief.
     * 
     * @return
     *     possible object is
     *     {@link ShadedReliefElement }
     *     
     */
    public ShadedReliefElement getShadedRelief() {
        return shadedRelief;
    }

    /**
     * Imposta il valore della proprietà shadedRelief.
     * 
     * @param value
     *     allowed object is
     *     {@link ShadedReliefElement }
     *     
     */
    public void setShadedRelief(ShadedReliefElement value) {
        this.shadedRelief = value;
    }

    public boolean isSetShadedRelief() {
        return (this.shadedRelief!= null);
    }

    /**
     * Recupera il valore della proprietà imageOutline.
     * 
     * @return
     *     possible object is
     *     {@link ImageOutlineElement }
     *     
     */
    public ImageOutlineElement getImageOutline() {
        return imageOutline;
    }

    /**
     * Imposta il valore della proprietà imageOutline.
     * 
     * @param value
     *     allowed object is
     *     {@link ImageOutlineElement }
     *     
     */
    public void setImageOutline(ImageOutlineElement value) {
        this.imageOutline = value;
    }

    public boolean isSetImageOutline() {
        return (this.imageOutline!= null);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            GeometryElement theGeometry;
            theGeometry = this.getGeometry();
            strategy.appendField(locator, this, "geometry", buffer, theGeometry);
        }
        {
            ParameterValueType theOpacity;
            theOpacity = this.getOpacity();
            strategy.appendField(locator, this, "opacity", buffer, theOpacity);
        }
        {
            ChannelSelectionElement theChannelSelection;
            theChannelSelection = this.getChannelSelection();
            strategy.appendField(locator, this, "channelSelection", buffer, theChannelSelection);
        }
        {
            OverlapBehaviorElement theOverlapBehavior;
            theOverlapBehavior = this.getOverlapBehavior();
            strategy.appendField(locator, this, "overlapBehavior", buffer, theOverlapBehavior);
        }
        {
            ColorMapElement theColorMap;
            theColorMap = this.getColorMap();
            strategy.appendField(locator, this, "colorMap", buffer, theColorMap);
        }
        {
            ContrastEnhancementElement theContrastEnhancement;
            theContrastEnhancement = this.getContrastEnhancement();
            strategy.appendField(locator, this, "contrastEnhancement", buffer, theContrastEnhancement);
        }
        {
            ShadedReliefElement theShadedRelief;
            theShadedRelief = this.getShadedRelief();
            strategy.appendField(locator, this, "shadedRelief", buffer, theShadedRelief);
        }
        {
            ImageOutlineElement theImageOutline;
            theImageOutline = this.getImageOutline();
            strategy.appendField(locator, this, "imageOutline", buffer, theImageOutline);
        }
        return buffer;
    }

}
