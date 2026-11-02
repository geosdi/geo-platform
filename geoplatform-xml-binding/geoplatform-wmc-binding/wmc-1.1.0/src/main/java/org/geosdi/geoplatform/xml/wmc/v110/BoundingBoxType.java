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
// Generato il: 2026.11.02 alle 02:51:01 PM CET
//

package org.geosdi.geoplatform.xml.wmc.v110;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per BoundingBoxType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="BoundingBoxType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="SRS" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       <attribute name="minx" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       <attribute name="miny" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       <attribute name="maxx" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       <attribute name="maxy" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoundingBoxType")
public class BoundingBoxType implements ToString
{

    @XmlAttribute(name = "SRS", required = true)
    protected String srs;
    @XmlAttribute(name = "minx", required = true)
    protected BigDecimal minx;
    @XmlAttribute(name = "miny", required = true)
    protected BigDecimal miny;
    @XmlAttribute(name = "maxx", required = true)
    protected BigDecimal maxx;
    @XmlAttribute(name = "maxy", required = true)
    protected BigDecimal maxy;

    /**
     * Recupera il valore della proprietà srs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRS() {
        return srs;
    }

    /**
     * Imposta il valore della proprietà srs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRS(String value) {
        this.srs = value;
    }

    public boolean isSetSRS() {
        return (this.srs!= null);
    }

    /**
     * Recupera il valore della proprietà minx.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinx() {
        return minx;
    }

    /**
     * Imposta il valore della proprietà minx.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinx(BigDecimal value) {
        this.minx = value;
    }

    public boolean isSetMinx() {
        return (this.minx!= null);
    }

    /**
     * Recupera il valore della proprietà miny.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMiny() {
        return miny;
    }

    /**
     * Imposta il valore della proprietà miny.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMiny(BigDecimal value) {
        this.miny = value;
    }

    public boolean isSetMiny() {
        return (this.miny!= null);
    }

    /**
     * Recupera il valore della proprietà maxx.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxx() {
        return maxx;
    }

    /**
     * Imposta il valore della proprietà maxx.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxx(BigDecimal value) {
        this.maxx = value;
    }

    public boolean isSetMaxx() {
        return (this.maxx!= null);
    }

    /**
     * Recupera il valore della proprietà maxy.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaxy() {
        return maxy;
    }

    /**
     * Imposta il valore della proprietà maxy.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaxy(BigDecimal value) {
        this.maxy = value;
    }

    public boolean isSetMaxy() {
        return (this.maxy!= null);
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
            String theSRS;
            theSRS = this.getSRS();
            strategy.appendField(locator, this, "srs", buffer, theSRS, this.isSetSRS());
        }
        {
            BigDecimal theMinx;
            theMinx = this.getMinx();
            strategy.appendField(locator, this, "minx", buffer, theMinx, this.isSetMinx());
        }
        {
            BigDecimal theMiny;
            theMiny = this.getMiny();
            strategy.appendField(locator, this, "miny", buffer, theMiny, this.isSetMiny());
        }
        {
            BigDecimal theMaxx;
            theMaxx = this.getMaxx();
            strategy.appendField(locator, this, "maxx", buffer, theMaxx, this.isSetMaxx());
        }
        {
            BigDecimal theMaxy;
            theMaxy = this.getMaxy();
            strategy.appendField(locator, this, "maxy", buffer, theMaxy, this.isSetMaxy());
        }
        return buffer;
    }

}
