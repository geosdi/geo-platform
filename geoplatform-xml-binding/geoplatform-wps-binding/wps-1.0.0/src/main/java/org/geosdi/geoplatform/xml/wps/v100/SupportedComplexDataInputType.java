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
// Generato il: 2026.11.02 alle 02:51:17 PM CET
//

package org.geosdi.geoplatform.xml.wps.v100;

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per SupportedComplexDataInputType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SupportedComplexDataInputType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/wps/1.0.0}SupportedComplexDataType">
 *       <sequence>
 *         <element name="DefaultValue" type="{http://www.opengis.net/wps/1.0.0}ComplexDataType" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="maximumMegabytes" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportedComplexDataInputType", propOrder = {
    "defaultValue"
})
public class SupportedComplexDataInputType
    extends SupportedComplexDataType
    implements ToString
{

    /**
     * Optional default value.
     * 
     */
    @XmlElement(name = "DefaultValue", namespace = "")
    protected ComplexDataType defaultValue;
    /**
     * The maximum file size, in megabytes, of this input.  If the input exceeds this size, the server will return an error instead of processing the inputs.
     * 
     */
    @XmlAttribute(name = "maximumMegabytes")
    protected BigInteger maximumMegabytes;

    /**
     * Optional default value.
     * 
     * @return
     *     possible object is
     *     {@link ComplexDataType }
     *     
     */
    public ComplexDataType getDefaultValue() {
        return defaultValue;
    }

    /**
     * Imposta il valore della proprietà defaultValue.
     * 
     * @param value
     *     allowed object is
     *     {@link ComplexDataType }
     *     
     * @see #getDefaultValue()
     */
    public void setDefaultValue(ComplexDataType value) {
        this.defaultValue = value;
    }

    public boolean isSetDefaultValue() {
        return (this.defaultValue!= null);
    }

    /**
     * The maximum file size, in megabytes, of this input.  If the input exceeds this size, the server will return an error instead of processing the inputs.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumMegabytes() {
        return maximumMegabytes;
    }

    /**
     * Imposta il valore della proprietà maximumMegabytes.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getMaximumMegabytes()
     */
    public void setMaximumMegabytes(BigInteger value) {
        this.maximumMegabytes = value;
    }

    public boolean isSetMaximumMegabytes() {
        return (this.maximumMegabytes!= null);
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
            ComplexDataType theDefaultValue;
            theDefaultValue = this.getDefaultValue();
            strategy.appendField(locator, this, "defaultValue", buffer, theDefaultValue, this.isSetDefaultValue());
        }
        {
            BigInteger theMaximumMegabytes;
            theMaximumMegabytes = this.getMaximumMegabytes();
            strategy.appendField(locator, this, "maximumMegabytes", buffer, theMaximumMegabytes, this.isSetMaximumMegabytes());
        }
        return buffer;
    }

}
