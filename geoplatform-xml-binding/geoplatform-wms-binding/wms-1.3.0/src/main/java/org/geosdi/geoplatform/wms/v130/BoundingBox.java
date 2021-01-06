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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.25 alle 11:53:13 AM CET 
//


package org.geosdi.geoplatform.wms.v130;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
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
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="CRS" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="minx" use="required" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="miny" use="required" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="maxx" use="required" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="maxy" use="required" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="resx" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="resy" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "BoundingBox")
public class BoundingBox implements ToString2
{

    @XmlAttribute(name = "CRS", required = true)
    protected String crs;
    @XmlAttribute(name = "minx", required = true)
    protected double minx;
    @XmlAttribute(name = "miny", required = true)
    protected double miny;
    @XmlAttribute(name = "maxx", required = true)
    protected double maxx;
    @XmlAttribute(name = "maxy", required = true)
    protected double maxy;
    @XmlAttribute(name = "resx")
    protected Double resx;
    @XmlAttribute(name = "resy")
    protected Double resy;

    /**
     * Recupera il valore della proprietà crs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCRS() {
        return crs;
    }

    /**
     * Imposta il valore della proprietà crs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCRS(String value) {
        this.crs = value;
    }

    /**
     * Recupera il valore della proprietà minx.
     * 
     */
    public double getMinx() {
        return minx;
    }

    /**
     * Imposta il valore della proprietà minx.
     * 
     */
    public void setMinx(double value) {
        this.minx = value;
    }

    /**
     * Recupera il valore della proprietà miny.
     * 
     */
    public double getMiny() {
        return miny;
    }

    /**
     * Imposta il valore della proprietà miny.
     * 
     */
    public void setMiny(double value) {
        this.miny = value;
    }

    /**
     * Recupera il valore della proprietà maxx.
     * 
     */
    public double getMaxx() {
        return maxx;
    }

    /**
     * Imposta il valore della proprietà maxx.
     * 
     */
    public void setMaxx(double value) {
        this.maxx = value;
    }

    /**
     * Recupera il valore della proprietà maxy.
     * 
     */
    public double getMaxy() {
        return maxy;
    }

    /**
     * Imposta il valore della proprietà maxy.
     * 
     */
    public void setMaxy(double value) {
        this.maxy = value;
    }

    /**
     * Recupera il valore della proprietà resx.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getResx() {
        return resx;
    }

    /**
     * Imposta il valore della proprietà resx.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setResx(Double value) {
        this.resx = value;
    }

    /**
     * Recupera il valore della proprietà resy.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getResy() {
        return resy;
    }

    /**
     * Imposta il valore della proprietà resy.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setResy(Double value) {
        this.resy = value;
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
        {
            String theCRS;
            theCRS = this.getCRS();
            strategy.appendField(locator, this, "crs", buffer, theCRS, (this.crs!= null));
        }
        {
            double theMinx;
            theMinx = this.getMinx();
            strategy.appendField(locator, this, "minx", buffer, theMinx, true);
        }
        {
            double theMiny;
            theMiny = this.getMiny();
            strategy.appendField(locator, this, "miny", buffer, theMiny, true);
        }
        {
            double theMaxx;
            theMaxx = this.getMaxx();
            strategy.appendField(locator, this, "maxx", buffer, theMaxx, true);
        }
        {
            double theMaxy;
            theMaxy = this.getMaxy();
            strategy.appendField(locator, this, "maxy", buffer, theMaxy, true);
        }
        {
            Double theResx;
            theResx = this.getResx();
            strategy.appendField(locator, this, "resx", buffer, theResx, (this.resx!= null));
        }
        {
            Double theResy;
            theResy = this.getResy();
            strategy.appendField(locator, this, "resy", buffer, theResy, (this.resy!= null));
        }
        return buffer;
    }

}
