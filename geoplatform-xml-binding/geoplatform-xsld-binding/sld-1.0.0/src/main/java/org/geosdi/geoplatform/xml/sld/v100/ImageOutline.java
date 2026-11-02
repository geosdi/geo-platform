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
 *       <choice>
 *         <element ref="{http://www.opengis.net/sld}LineSymbolizer"/>
 *         <element ref="{http://www.opengis.net/sld}PolygonSymbolizer"/>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "lineSymbolizer",
    "polygonSymbolizer"
})
@XmlRootElement(name = "ImageOutline")
public class ImageOutline implements ToString
{

    /**
     * A LineSymbolizer is used to render a "stroke" along a linear geometry.
     * 
     */
    @XmlElement(name = "LineSymbolizer")
    protected LineSymbolizer lineSymbolizer;
    /**
     * A "PolygonSymbolizer" specifies the rendering of a polygon or
     *         area geometry, including its interior fill and border stroke.
     * 
     */
    @XmlElement(name = "PolygonSymbolizer")
    protected PolygonSymbolizer polygonSymbolizer;

    /**
     * A LineSymbolizer is used to render a "stroke" along a linear geometry.
     * 
     * @return
     *     possible object is
     *     {@link LineSymbolizer }
     *     
     */
    public LineSymbolizer getLineSymbolizer() {
        return lineSymbolizer;
    }

    /**
     * Imposta il valore della proprietà lineSymbolizer.
     * 
     * @param value
     *     allowed object is
     *     {@link LineSymbolizer }
     *     
     * @see #getLineSymbolizer()
     */
    public void setLineSymbolizer(LineSymbolizer value) {
        this.lineSymbolizer = value;
    }

    public boolean isSetLineSymbolizer() {
        return (this.lineSymbolizer!= null);
    }

    /**
     * A "PolygonSymbolizer" specifies the rendering of a polygon or
     *         area geometry, including its interior fill and border stroke.
     * 
     * @return
     *     possible object is
     *     {@link PolygonSymbolizer }
     *     
     */
    public PolygonSymbolizer getPolygonSymbolizer() {
        return polygonSymbolizer;
    }

    /**
     * Imposta il valore della proprietà polygonSymbolizer.
     * 
     * @param value
     *     allowed object is
     *     {@link PolygonSymbolizer }
     *     
     * @see #getPolygonSymbolizer()
     */
    public void setPolygonSymbolizer(PolygonSymbolizer value) {
        this.polygonSymbolizer = value;
    }

    public boolean isSetPolygonSymbolizer() {
        return (this.polygonSymbolizer!= null);
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
            LineSymbolizer theLineSymbolizer;
            theLineSymbolizer = this.getLineSymbolizer();
            strategy.appendField(locator, this, "lineSymbolizer", buffer, theLineSymbolizer, this.isSetLineSymbolizer());
        }
        {
            PolygonSymbolizer thePolygonSymbolizer;
            thePolygonSymbolizer = this.getPolygonSymbolizer();
            strategy.appendField(locator, this, "polygonSymbolizer", buffer, thePolygonSymbolizer, this.isSetPolygonSymbolizer());
        }
        return buffer;
    }

}
