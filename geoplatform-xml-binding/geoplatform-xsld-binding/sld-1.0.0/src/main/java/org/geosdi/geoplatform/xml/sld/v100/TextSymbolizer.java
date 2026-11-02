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
 *         <element ref="{http://www.opengis.net/sld}Label" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}Font" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}LabelPlacement" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}Halo" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}Fill" minOccurs="0"/>
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
    "label",
    "font",
    "labelPlacement",
    "halo",
    "fill"
})
public class TextSymbolizer
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
    /**
     * A "Label" specifies the textual content to be rendered.
     * 
     */
    @XmlElement(name = "Label")
    protected ParameterValueType label;
    /**
     * A "Font" element specifies the text font to use.  The allowed
     *         CssParameters are: "font-family", "font-style", "font-weight",
     *         and "font-size".
     * 
     */
    @XmlElement(name = "Font")
    protected Font font;
    /**
     * The "LabelPlacement" specifies where and how a text label should
     *         be rendered relative to a geometry.  The present mechanism is
     *         poorly aligned with CSS/SVG.
     * 
     */
    @XmlElement(name = "LabelPlacement")
    protected LabelPlacement labelPlacement;
    /**
     * A "Halo" fills an extended area outside the glyphs of a rendered
     *         text label to make the label easier to read over a background.
     * 
     */
    @XmlElement(name = "Halo")
    protected Halo halo;
    /**
     * A "Fill" specifies the pattern for filling an area geometry.
     *         The allowed CssParameters are: "fill" (color) and "fill-opacity".
     * 
     */
    @XmlElement(name = "Fill")
    protected Fill fill;

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
     * A "Label" specifies the textual content to be rendered.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getLabel() {
        return label;
    }

    /**
     * Imposta il valore della proprietà label.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     * @see #getLabel()
     */
    public void setLabel(ParameterValueType value) {
        this.label = value;
    }

    public boolean isSetLabel() {
        return (this.label!= null);
    }

    /**
     * A "Font" element specifies the text font to use.  The allowed
     *         CssParameters are: "font-family", "font-style", "font-weight",
     *         and "font-size".
     * 
     * @return
     *     possible object is
     *     {@link Font }
     *     
     */
    public Font getFont() {
        return font;
    }

    /**
     * Imposta il valore della proprietà font.
     * 
     * @param value
     *     allowed object is
     *     {@link Font }
     *     
     * @see #getFont()
     */
    public void setFont(Font value) {
        this.font = value;
    }

    public boolean isSetFont() {
        return (this.font!= null);
    }

    /**
     * The "LabelPlacement" specifies where and how a text label should
     *         be rendered relative to a geometry.  The present mechanism is
     *         poorly aligned with CSS/SVG.
     * 
     * @return
     *     possible object is
     *     {@link LabelPlacement }
     *     
     */
    public LabelPlacement getLabelPlacement() {
        return labelPlacement;
    }

    /**
     * Imposta il valore della proprietà labelPlacement.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelPlacement }
     *     
     * @see #getLabelPlacement()
     */
    public void setLabelPlacement(LabelPlacement value) {
        this.labelPlacement = value;
    }

    public boolean isSetLabelPlacement() {
        return (this.labelPlacement!= null);
    }

    /**
     * A "Halo" fills an extended area outside the glyphs of a rendered
     *         text label to make the label easier to read over a background.
     * 
     * @return
     *     possible object is
     *     {@link Halo }
     *     
     */
    public Halo getHalo() {
        return halo;
    }

    /**
     * Imposta il valore della proprietà halo.
     * 
     * @param value
     *     allowed object is
     *     {@link Halo }
     *     
     * @see #getHalo()
     */
    public void setHalo(Halo value) {
        this.halo = value;
    }

    public boolean isSetHalo() {
        return (this.halo!= null);
    }

    /**
     * A "Fill" specifies the pattern for filling an area geometry.
     *         The allowed CssParameters are: "fill" (color) and "fill-opacity".
     * 
     * @return
     *     possible object is
     *     {@link Fill }
     *     
     */
    public Fill getFill() {
        return fill;
    }

    /**
     * Imposta il valore della proprietà fill.
     * 
     * @param value
     *     allowed object is
     *     {@link Fill }
     *     
     * @see #getFill()
     */
    public void setFill(Fill value) {
        this.fill = value;
    }

    public boolean isSetFill() {
        return (this.fill!= null);
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
            ParameterValueType theLabel;
            theLabel = this.getLabel();
            strategy.appendField(locator, this, "label", buffer, theLabel, this.isSetLabel());
        }
        {
            Font theFont;
            theFont = this.getFont();
            strategy.appendField(locator, this, "font", buffer, theFont, this.isSetFont());
        }
        {
            LabelPlacement theLabelPlacement;
            theLabelPlacement = this.getLabelPlacement();
            strategy.appendField(locator, this, "labelPlacement", buffer, theLabelPlacement, this.isSetLabelPlacement());
        }
        {
            Halo theHalo;
            theHalo = this.getHalo();
            strategy.appendField(locator, this, "halo", buffer, theHalo, this.isSetHalo());
        }
        {
            Fill theFill;
            theFill = this.getFill();
            strategy.appendField(locator, this, "fill", buffer, theFill, this.isSetFill());
        }
        return buffer;
    }

}
