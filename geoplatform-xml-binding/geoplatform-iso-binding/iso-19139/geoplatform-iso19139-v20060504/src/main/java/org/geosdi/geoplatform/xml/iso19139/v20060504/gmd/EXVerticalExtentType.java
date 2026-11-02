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
// Generato il: 2026.11.02 alle 03:38:59 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20060504.gmd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.RealPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gsr.SCCRSPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Vertical domain of dataset
 * 
 * <p>Classe Java per EX_VerticalExtent_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="EX_VerticalExtent_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="minimumValue" type="{http://www.isotc211.org/2005/gco}Real_PropertyType"/>
 *         <element name="maximumValue" type="{http://www.isotc211.org/2005/gco}Real_PropertyType"/>
 *         <element name="verticalCRS" type="{http://www.isotc211.org/2005/gsr}SC_CRS_PropertyType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EX_VerticalExtent_Type", propOrder = {
    "minimumValue",
    "maximumValue",
    "verticalCRS"
})
public class EXVerticalExtentType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected RealPropertyType minimumValue;
    @XmlElement(required = true)
    protected RealPropertyType maximumValue;
    @XmlElement(required = true)
    protected SCCRSPropertyType verticalCRS;

    /**
     * Recupera il valore della proprietà minimumValue.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getMinimumValue() {
        return minimumValue;
    }

    /**
     * Imposta il valore della proprietà minimumValue.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setMinimumValue(RealPropertyType value) {
        this.minimumValue = value;
    }

    public boolean isSetMinimumValue() {
        return (this.minimumValue!= null);
    }

    /**
     * Recupera il valore della proprietà maximumValue.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getMaximumValue() {
        return maximumValue;
    }

    /**
     * Imposta il valore della proprietà maximumValue.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setMaximumValue(RealPropertyType value) {
        this.maximumValue = value;
    }

    public boolean isSetMaximumValue() {
        return (this.maximumValue!= null);
    }

    /**
     * Recupera il valore della proprietà verticalCRS.
     * 
     * @return
     *     possible object is
     *     {@link SCCRSPropertyType }
     *     
     */
    public SCCRSPropertyType getVerticalCRS() {
        return verticalCRS;
    }

    /**
     * Imposta il valore della proprietà verticalCRS.
     * 
     * @param value
     *     allowed object is
     *     {@link SCCRSPropertyType }
     *     
     */
    public void setVerticalCRS(SCCRSPropertyType value) {
        this.verticalCRS = value;
    }

    public boolean isSetVerticalCRS() {
        return (this.verticalCRS!= null);
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
            RealPropertyType theMinimumValue;
            theMinimumValue = this.getMinimumValue();
            strategy.appendField(locator, this, "minimumValue", buffer, theMinimumValue, this.isSetMinimumValue());
        }
        {
            RealPropertyType theMaximumValue;
            theMaximumValue = this.getMaximumValue();
            strategy.appendField(locator, this, "maximumValue", buffer, theMaximumValue, this.isSetMaximumValue());
        }
        {
            SCCRSPropertyType theVerticalCRS;
            theVerticalCRS = this.getVerticalCRS();
            strategy.appendField(locator, this, "verticalCRS", buffer, theVerticalCRS, this.isSetVerticalCRS());
        }
        return buffer;
    }

}
