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
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import org.geosdi.geoplatform.xml.ows.v110.AllowedValues;
import org.geosdi.geoplatform.xml.ows.v110.AnyValue;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Description of a process input that consists of a simple literal value (e.g., "2.1"). (Informative: This type is a subset of the ows:UnNamedDomainType defined in owsDomaintype.xsd.)
 * <p>
 * <p>Classe Java per LiteralInputType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="LiteralInputType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/wps/1.0.0}LiteralOutputType"&gt;
 *       &lt;sequence&gt;
 *         &lt;group ref="{http://www.opengis.net/wps/1.0.0}LiteralValuesChoice"/&gt;
 *         &lt;element name="DefaultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LiteralInputType", propOrder = {
        "allowedValues",
        "anyValue",
        "valuesReference",
        "defaultValue"
})
public class LiteralInputType extends LiteralOutputType implements ToString2 {

    @XmlElement(name = "AllowedValues", namespace = "http://www.opengis.net/ows/1.1")
    protected AllowedValues allowedValues;
    @XmlElement(name = "AnyValue", namespace = "http://www.opengis.net/ows/1.1")
    protected AnyValue anyValue;
    @XmlElement(name = "ValuesReference", namespace = "")
    protected ValuesReferenceType valuesReference;
    @XmlElement(name = "DefaultValue", namespace = "")
    protected String defaultValue;

    /**
     * Indicates that there are a finite set of values and ranges allowed for this input, and contains list of all the valid values and/or ranges of values. Notice that these values and ranges can be displayed to a human client.
     *
     * @return possible object is
     * {@link AllowedValues }
     */
    public AllowedValues getAllowedValues() {
        return allowedValues;
    }

    /**
     * Imposta il valore della proprietà allowedValues.
     *
     * @param value allowed object is
     *              {@link AllowedValues }
     */
    public void setAllowedValues(AllowedValues value) {
        this.allowedValues = value;
    }

    public boolean isSetAllowedValues() {
        return (this.allowedValues != null);
    }

    /**
     * Indicates that any value is allowed for this input. This element shall be included when there are no restrictions, except for data type, on the allowable value of this input.
     *
     * @return possible object is
     * {@link AnyValue }
     */
    public AnyValue getAnyValue() {
        return anyValue;
    }

    /**
     * Imposta il valore della proprietà anyValue.
     *
     * @param value allowed object is
     *              {@link AnyValue }
     */
    public void setAnyValue(AnyValue value) {
        this.anyValue = value;
    }

    public boolean isSetAnyValue() {
        return (this.anyValue != null);
    }

    /**
     * Recupera il valore della proprietà valuesReference.
     *
     * @return possible object is
     * {@link ValuesReferenceType }
     */
    public ValuesReferenceType getValuesReference() {
        return valuesReference;
    }

    /**
     * Imposta il valore della proprietà valuesReference.
     *
     * @param value allowed object is
     *              {@link ValuesReferenceType }
     */
    public void setValuesReference(ValuesReferenceType value) {
        this.valuesReference = value;
    }

    public boolean isSetValuesReference() {
        return (this.valuesReference != null);
    }

    /**
     * Recupera il valore della proprietà defaultValue.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * Imposta il valore della proprietà defaultValue.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDefaultValue(String value) {
        this.defaultValue = value;
    }

    public boolean isSetDefaultValue() {
        return (this.defaultValue != null);
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
            AllowedValues theAllowedValues;
            theAllowedValues = this.getAllowedValues();
            strategy.appendField(locator, this, "allowedValues", buffer, theAllowedValues, this.isSetAllowedValues());
        }
        {
            AnyValue theAnyValue;
            theAnyValue = this.getAnyValue();
            strategy.appendField(locator, this, "anyValue", buffer, theAnyValue, this.isSetAnyValue());
        }
        {
            ValuesReferenceType theValuesReference;
            theValuesReference = this.getValuesReference();
            strategy.appendField(locator, this, "valuesReference", buffer, theValuesReference, this.isSetValuesReference());
        }
        {
            String theDefaultValue;
            theDefaultValue = this.getDefaultValue();
            strategy.appendField(locator, this, "defaultValue", buffer, theDefaultValue, this.isSetDefaultValue());
        }
        return buffer;
    }

}
