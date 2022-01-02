/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:31:16 AM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/sld}SymbolizerType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Geometry" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Opacity" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}ChannelSelection" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}OverlapBehavior" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}ColorMap" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}ContrastEnhancement" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}ShadedRelief" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}ImageOutline" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
    implements ToString2
{

    @XmlElement(name = "Geometry")
    protected Geometry geometry;
    @XmlElement(name = "Opacity")
    protected ParameterValueType opacity;
    @XmlElement(name = "ChannelSelection")
    protected ChannelSelection channelSelection;
    @XmlElement(name = "OverlapBehavior")
    protected OverlapBehavior overlapBehavior;
    @XmlElement(name = "ColorMap")
    protected ColorMap colorMap;
    @XmlElement(name = "ContrastEnhancement")
    protected ContrastEnhancement contrastEnhancement;
    @XmlElement(name = "ShadedRelief")
    protected ShadedRelief shadedRelief;
    @XmlElement(name = "ImageOutline")
    protected ImageOutline imageOutline;

    /**
     * Recupera il valore della proprietà geometry.
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
     * Recupera il valore della proprietà channelSelection.
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
     */
    public void setChannelSelection(ChannelSelection value) {
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
     */
    public void setOverlapBehavior(OverlapBehavior value) {
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
     */
    public void setColorMap(ColorMap value) {
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
     */
    public void setContrastEnhancement(ContrastEnhancement value) {
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
     */
    public void setShadedRelief(ShadedRelief value) {
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
     */
    public void setImageOutline(ImageOutline value) {
        this.imageOutline = value;
    }

    public boolean isSetImageOutline() {
        return (this.imageOutline!= null);
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
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
