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

import java.util.ArrayList;
import java.util.List;
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
 *         <choice minOccurs="0">
 *           <element ref="{http://www.opengis.net/sld}GraphicFill"/>
 *           <element ref="{http://www.opengis.net/sld}GraphicStroke"/>
 *         </choice>
 *         <element ref="{http://www.opengis.net/sld}CssParameter" maxOccurs="unbounded" minOccurs="0"/>
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
    "graphicFill",
    "graphicStroke",
    "cssParameter"
})
@XmlRootElement(name = "Stroke")
public class Stroke implements ToString
{

    /**
     * A "GraphicFill" defines repeated-graphic filling (stippling)
     *         pattern for an area geometry.
     * 
     */
    @XmlElement(name = "GraphicFill")
    protected GraphicFill graphicFill;
    /**
     * A "GraphicStroke" defines a repated-linear graphic pattern to be used
     *         for stroking a line.
     * 
     */
    @XmlElement(name = "GraphicStroke")
    protected GraphicStroke graphicStroke;
    /**
     * A "CssParameter" refers to an SVG/CSS graphical-formatting
     *         parameter.  The parameter is identified using the "name" attribute
     *         and the content of the element gives the SVG/CSS-coded value.
     * 
     */
    @XmlElement(name = "CssParameter")
    protected List<CssParameter> cssParameter;

    /**
     * A "GraphicFill" defines repeated-graphic filling (stippling)
     *         pattern for an area geometry.
     * 
     * @return
     *     possible object is
     *     {@link GraphicFill }
     *     
     */
    public GraphicFill getGraphicFill() {
        return graphicFill;
    }

    /**
     * Imposta il valore della proprietà graphicFill.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphicFill }
     *     
     * @see #getGraphicFill()
     */
    public void setGraphicFill(GraphicFill value) {
        this.graphicFill = value;
    }

    public boolean isSetGraphicFill() {
        return (this.graphicFill!= null);
    }

    /**
     * A "GraphicStroke" defines a repated-linear graphic pattern to be used
     *         for stroking a line.
     * 
     * @return
     *     possible object is
     *     {@link GraphicStroke }
     *     
     */
    public GraphicStroke getGraphicStroke() {
        return graphicStroke;
    }

    /**
     * Imposta il valore della proprietà graphicStroke.
     * 
     * @param value
     *     allowed object is
     *     {@link GraphicStroke }
     *     
     * @see #getGraphicStroke()
     */
    public void setGraphicStroke(GraphicStroke value) {
        this.graphicStroke = value;
    }

    public boolean isSetGraphicStroke() {
        return (this.graphicStroke!= null);
    }

    /**
     * A "CssParameter" refers to an SVG/CSS graphical-formatting
     *         parameter.  The parameter is identified using the "name" attribute
     *         and the content of the element gives the SVG/CSS-coded value.
     * 
     * Gets the value of the cssParameter property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the cssParameter property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCssParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CssParameter }
     * </p>
     * 
     * 
     * @return
     *     The value of the cssParameter property.
     */
    public List<CssParameter> getCssParameter() {
        if (cssParameter == null) {
            cssParameter = new ArrayList<>();
        }
        return this.cssParameter;
    }

    public boolean isSetCssParameter() {
        return ((this.cssParameter!= null)&&(!this.cssParameter.isEmpty()));
    }

    public void unsetCssParameter() {
        this.cssParameter = null;
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
            GraphicFill theGraphicFill;
            theGraphicFill = this.getGraphicFill();
            strategy.appendField(locator, this, "graphicFill", buffer, theGraphicFill, this.isSetGraphicFill());
        }
        {
            GraphicStroke theGraphicStroke;
            theGraphicStroke = this.getGraphicStroke();
            strategy.appendField(locator, this, "graphicStroke", buffer, theGraphicStroke, this.isSetGraphicStroke());
        }
        {
            List<CssParameter> theCssParameter;
            theCssParameter = (this.isSetCssParameter()?this.getCssParameter():null);
            strategy.appendField(locator, this, "cssParameter", buffer, theCssParameter, this.isSetCssParameter());
        }
        return buffer;
    }

    public void setCssParameter(List<CssParameter> value) {
        this.cssParameter = null;
        if (value!= null) {
            List<CssParameter> draftl = this.getCssParameter();
            draftl.addAll(value);
        }
    }

}
