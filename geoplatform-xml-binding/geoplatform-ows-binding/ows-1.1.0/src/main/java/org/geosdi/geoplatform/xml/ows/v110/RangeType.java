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

package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
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
 * A range of values of a numeric parameter. This range can be continuous or discrete, defined by a fixed spacing between adjacent valid values. If the MinimumValue or MaximumValue is not included, there is no value limit in that direction. Inclusion of the specified minimum and maximum values in the range shall be defined by the rangeClosure.
 * 
 * <p>Classe Java per RangeType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="RangeType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/ows/1.1}MinimumValue" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}MaximumValue" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}Spacing" minOccurs="0"/>
 *       </sequence>
 *       <attribute ref="{http://www.opengis.net/ows/1.1}rangeClosure"/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RangeType", propOrder = {
    "minimumValue",
    "maximumValue",
    "spacing"
})
public class RangeType implements ToString
{

    /**
     * Minimum value of this numeric parameter.
     * 
     */
    @XmlElement(name = "MinimumValue")
    protected ValueType minimumValue;
    /**
     * Maximum value of this numeric parameter.
     * 
     */
    @XmlElement(name = "MaximumValue")
    protected ValueType maximumValue;
    /**
     * Shall be included when the allowed values are NOT continuous in this range. Shall not be included when the allowed values are continuous in this range.
     * 
     */
    @XmlElement(name = "Spacing")
    protected ValueType spacing;
    /**
     * Shall be included unless the default value applies.
     * 
     */
    @XmlAttribute(name = "rangeClosure", namespace = "http://www.opengis.net/ows/1.1")
    protected List<String> rangeClosure;

    /**
     * Minimum value of this numeric parameter.
     * 
     * @return
     *     possible object is
     *     {@link ValueType }
     *     
     */
    public ValueType getMinimumValue() {
        return minimumValue;
    }

    /**
     * Imposta il valore della proprietà minimumValue.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueType }
     *     
     * @see #getMinimumValue()
     */
    public void setMinimumValue(ValueType value) {
        this.minimumValue = value;
    }

    /**
     * Maximum value of this numeric parameter.
     * 
     * @return
     *     possible object is
     *     {@link ValueType }
     *     
     */
    public ValueType getMaximumValue() {
        return maximumValue;
    }

    /**
     * Imposta il valore della proprietà maximumValue.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueType }
     *     
     * @see #getMaximumValue()
     */
    public void setMaximumValue(ValueType value) {
        this.maximumValue = value;
    }

    /**
     * Shall be included when the allowed values are NOT continuous in this range. Shall not be included when the allowed values are continuous in this range.
     * 
     * @return
     *     possible object is
     *     {@link ValueType }
     *     
     */
    public ValueType getSpacing() {
        return spacing;
    }

    /**
     * Imposta il valore della proprietà spacing.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueType }
     *     
     * @see #getSpacing()
     */
    public void setSpacing(ValueType value) {
        this.spacing = value;
    }

    /**
     * Shall be included unless the default value applies.
     * 
     * Gets the value of the rangeClosure property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the rangeClosure property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRangeClosure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the rangeClosure property.
     */
    public List<String> getRangeClosure() {
        if (rangeClosure == null) {
            rangeClosure = new ArrayList<>();
        }
        return this.rangeClosure;
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
            ValueType theMinimumValue;
            theMinimumValue = this.getMinimumValue();
            strategy.appendField(locator, this, "minimumValue", buffer, theMinimumValue, (this.minimumValue!= null));
        }
        {
            ValueType theMaximumValue;
            theMaximumValue = this.getMaximumValue();
            strategy.appendField(locator, this, "maximumValue", buffer, theMaximumValue, (this.maximumValue!= null));
        }
        {
            ValueType theSpacing;
            theSpacing = this.getSpacing();
            strategy.appendField(locator, this, "spacing", buffer, theSpacing, (this.spacing!= null));
        }
        {
            List<String> theRangeClosure;
            theRangeClosure = (((this.rangeClosure!= null)&&(!this.rangeClosure.isEmpty()))?this.getRangeClosure():null);
            strategy.appendField(locator, this, "rangeClosure", buffer, theRangeClosure, ((this.rangeClosure!= null)&&(!this.rangeClosure.isEmpty())));
        }
        return buffer;
    }

    public void setRangeClosure(List<String> value) {
        this.rangeClosure = null;
        if (value!= null) {
            List<String> draftl = this.getRangeClosure();
            draftl.addAll(value);
        }
    }

}
