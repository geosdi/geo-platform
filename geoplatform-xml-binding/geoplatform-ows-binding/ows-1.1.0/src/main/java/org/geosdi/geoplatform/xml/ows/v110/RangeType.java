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
// Generato il: 2017.12.01 alle 09:03:56 AM CET 
//


package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * A range of values of a numeric parameter. This range can be continuous or discrete, defined by a fixed spacing between adjacent valid values. If the MinimumValue or MaximumValue is not included, there is no value limit in that direction. Inclusion of the specified minimum and maximum values in the range shall be defined by the rangeClosure. 
 * 
 * <p>Classe Java per RangeType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RangeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}MinimumValue" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}MaximumValue" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Spacing" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute ref="{http://www.opengis.net/ows/1.1}rangeClosure"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RangeType", propOrder = {
    "minimumValue",
    "maximumValue",
    "spacing"
})
public class RangeType implements ToString2
{

    @XmlElement(name = "MinimumValue")
    protected ValueType minimumValue;
    @XmlElement(name = "MaximumValue")
    protected ValueType maximumValue;
    @XmlElement(name = "Spacing")
    protected ValueType spacing;
    @XmlAttribute(name = "rangeClosure", namespace = "http://www.opengis.net/ows/1.1")
    protected List<String> rangeClosure;

    /**
     * Recupera il valore della proprietà minimumValue.
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
     */
    public void setMinimumValue(ValueType value) {
        this.minimumValue = value;
    }

    /**
     * Recupera il valore della proprietà maximumValue.
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
     */
    public void setSpacing(ValueType value) {
        this.spacing = value;
    }

    /**
     * Shall be included unless the default value applies. Gets the value of the rangeClosure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rangeClosure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRangeClosure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRangeClosure() {
        if (rangeClosure == null) {
            rangeClosure = new ArrayList<String>();
        }
        return this.rangeClosure;
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
