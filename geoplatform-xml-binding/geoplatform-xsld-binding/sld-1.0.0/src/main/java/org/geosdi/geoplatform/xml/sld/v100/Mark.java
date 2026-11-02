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
import jakarta.xml.bind.annotation.XmlRootElement;
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
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/sld}WellKnownName" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}Fill" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/sld}Stroke" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "wellKnownName",
    "fill",
    "stroke"
})
@XmlRootElement(name = "Mark")
public class Mark implements ToString
{

    @XmlElement(name = "WellKnownName")
    protected String wellKnownName;
    /**
     * A "Fill" specifies the pattern for filling an area geometry.
     *         The allowed CssParameters are: "fill" (color) and "fill-opacity".
     * 
     */
    @XmlElement(name = "Fill")
    protected Fill fill;
    /**
     * A "Stroke" specifies the appearance of a linear geometry.  It is
     *         defined in parallel with SVG strokes.  The following CssParameters
     *         may be used: "stroke" (color), "stroke-opacity", "stroke-width",
     *         "stroke-linejoin", "stroke-linecap", "stroke-dasharray", and
     *         "stroke-dashoffset".
     * 
     */
    @XmlElement(name = "Stroke")
    protected Stroke stroke;

    /**
     * Recupera il valore della proprietà wellKnownName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWellKnownName() {
        return wellKnownName;
    }

    /**
     * Imposta il valore della proprietà wellKnownName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWellKnownName(String value) {
        this.wellKnownName = value;
    }

    public boolean isSetWellKnownName() {
        return (this.wellKnownName!= null);
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

    /**
     * A "Stroke" specifies the appearance of a linear geometry.  It is
     *         defined in parallel with SVG strokes.  The following CssParameters
     *         may be used: "stroke" (color), "stroke-opacity", "stroke-width",
     *         "stroke-linejoin", "stroke-linecap", "stroke-dasharray", and
     *         "stroke-dashoffset".
     * 
     * @return
     *     possible object is
     *     {@link Stroke }
     *     
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * Imposta il valore della proprietà stroke.
     * 
     * @param value
     *     allowed object is
     *     {@link Stroke }
     *     
     * @see #getStroke()
     */
    public void setStroke(Stroke value) {
        this.stroke = value;
    }

    public boolean isSetStroke() {
        return (this.stroke!= null);
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
        {
            String theWellKnownName;
            theWellKnownName = this.getWellKnownName();
            strategy.appendField(locator, this, "wellKnownName", buffer, theWellKnownName, this.isSetWellKnownName());
        }
        {
            Fill theFill;
            theFill = this.getFill();
            strategy.appendField(locator, this, "fill", buffer, theFill, this.isSetFill());
        }
        {
            Stroke theStroke;
            theStroke = this.getStroke();
            strategy.appendField(locator, this, "stroke", buffer, theStroke, this.isSetStroke());
        }
        return buffer;
    }

}
