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
// Generato il: 2015.08.25 alle 10:30:08 PM CEST 
//


package org.geosdi.geoplatform.xml.gml.v212;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * Represents a coordinate tuple in one, two, or three dimensions.
 *
 *
 * <p>Classe Java per CoordType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="CoordType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="Z" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordType", propOrder = {
        "x",
        "y",
        "z"
})
public class CoordType implements ToString {

    @XmlElement(name = "X", required = true)
    protected BigDecimal x;
    @XmlElement(name = "Y")
    protected BigDecimal y;
    @XmlElement(name = "Z")
    protected BigDecimal z;

    /**
     * Recupera il valore della proprietà x.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getX() {
        return x;
    }

    /**
     * Imposta il valore della proprietà x.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setX(BigDecimal value) {
        this.x = value;
    }

    public boolean isSetX() {
        return (this.x != null);
    }

    /**
     * Recupera il valore della proprietà y.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getY() {
        return y;
    }

    /**
     * Imposta il valore della proprietà y.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setY(BigDecimal value) {
        this.y = value;
    }

    public boolean isSetY() {
        return (this.y != null);
    }

    /**
     * Recupera il valore della proprietà z.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getZ() {
        return z;
    }

    /**
     * Imposta il valore della proprietà z.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setZ(BigDecimal value) {
        this.z = value;
    }

    public boolean isSetZ() {
        return (this.z != null);
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
        {
            BigDecimal theX;
            theX = this.getX();
            strategy.appendField(locator, this, "x", buffer, theX);
        }
        {
            BigDecimal theY;
            theY = this.getY();
            strategy.appendField(locator, this, "y", buffer, theY);
        }
        {
            BigDecimal theZ;
            theZ = this.getZ();
            strategy.appendField(locator, this, "z", buffer, theZ);
        }
        return buffer;
    }

}
