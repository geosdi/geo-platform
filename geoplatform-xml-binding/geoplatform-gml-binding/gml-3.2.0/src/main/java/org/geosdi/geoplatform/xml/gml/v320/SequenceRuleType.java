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
// Generato il: 2015.06.18 alle 12:52:42 AM CEST 
//


package org.geosdi.geoplatform.xml.gml.v320;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import org.jvnet.jaxb2_commons.lang.CopyStrategy;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.HashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBMergeStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.MergeFrom;
import org.jvnet.jaxb2_commons.lang.MergeStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * The gml:SequenceRuleType is derived from the gml:SequenceRuleEnumeration through the addition of an axisOrder attribute.  The gml:SequenceRuleEnumeration is an enumerated type. The rule names are defined in ISO 19123. If no rule name is specified the default is “Linear”.
 * 
 * <p>Classe Java per SequenceRuleType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SequenceRuleType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.opengis.net/gml>SequenceRuleEnumeration">
 *       &lt;attribute name="order" type="{http://www.opengis.net/gml}IncrementOrder" />
 *       &lt;attribute name="axisOrder" type="{http://www.opengis.net/gml}AxisDirectionList" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SequenceRuleType", propOrder = {
    "value"
})
public class SequenceRuleType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlValue
    protected SequenceRuleEnumeration value;
    @XmlAttribute(name = "order")
    protected IncrementOrder order;
    @XmlAttribute(name = "axisOrder")
    protected List<String> axisOrder;

    /**
     * Recupera il valore della proprietà value.
     * 
     * @return
     *     possible object is
     *     {@link SequenceRuleEnumeration }
     *     
     */
    public SequenceRuleEnumeration getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     * @param value
     *     allowed object is
     *     {@link SequenceRuleEnumeration }
     *     
     */
    public void setValue(SequenceRuleEnumeration value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Recupera il valore della proprietà order.
     * 
     * @return
     *     possible object is
     *     {@link IncrementOrder }
     *     
     */
    public IncrementOrder getOrder() {
        return order;
    }

    /**
     * Imposta il valore della proprietà order.
     * 
     * @param value
     *     allowed object is
     *     {@link IncrementOrder }
     *     
     */
    public void setOrder(IncrementOrder value) {
        this.order = value;
    }

    public boolean isSetOrder() {
        return (this.order!= null);
    }

    /**
     * Gets the value of the axisOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the axisOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAxisOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAxisOrder() {
        if (axisOrder == null) {
            axisOrder = new ArrayList<String>();
        }
        return this.axisOrder;
    }

    public boolean isSetAxisOrder() {
        return ((this.axisOrder!= null)&&(!this.axisOrder.isEmpty()));
    }

    public void unsetAxisOrder() {
        this.axisOrder = null;
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
            SequenceRuleEnumeration theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue);
        }
        {
            IncrementOrder theOrder;
            theOrder = this.getOrder();
            strategy.appendField(locator, this, "order", buffer, theOrder);
        }
        {
            List<String> theAxisOrder;
            theAxisOrder = this.getAxisOrder();
            strategy.appendField(locator, this, "axisOrder", buffer, theAxisOrder);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SequenceRuleType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SequenceRuleType that = ((SequenceRuleType) object);
        {
            SequenceRuleEnumeration lhsValue;
            lhsValue = this.getValue();
            SequenceRuleEnumeration rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue)) {
                return false;
            }
        }
        {
            IncrementOrder lhsOrder;
            lhsOrder = this.getOrder();
            IncrementOrder rhsOrder;
            rhsOrder = that.getOrder();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "order", lhsOrder), LocatorUtils.property(thatLocator, "order", rhsOrder), lhsOrder, rhsOrder)) {
                return false;
            }
        }
        {
            List<String> lhsAxisOrder;
            lhsAxisOrder = this.getAxisOrder();
            List<String> rhsAxisOrder;
            rhsAxisOrder = that.getAxisOrder();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "axisOrder", lhsAxisOrder), LocatorUtils.property(thatLocator, "axisOrder", rhsAxisOrder), lhsAxisOrder, rhsAxisOrder)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            SequenceRuleEnumeration theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue);
        }
        {
            IncrementOrder theOrder;
            theOrder = this.getOrder();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "order", theOrder), currentHashCode, theOrder);
        }
        {
            List<String> theAxisOrder;
            theAxisOrder = this.getAxisOrder();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "axisOrder", theAxisOrder), currentHashCode, theAxisOrder);
        }
        return currentHashCode;
    }

    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.INSTANCE;
        return this.hashCode(null, strategy);
    }

    public Object clone() {
        return copyTo(createNewInstance());
    }

    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.INSTANCE;
        return copyTo(null, target, strategy);
    }

    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof SequenceRuleType) {
            final SequenceRuleType copy = ((SequenceRuleType) draftCopy);
            if (this.isSetValue()) {
                SequenceRuleEnumeration sourceValue;
                sourceValue = this.getValue();
                SequenceRuleEnumeration copyValue = ((SequenceRuleEnumeration) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue));
                copy.setValue(copyValue);
            } else {
                copy.value = null;
            }
            if (this.isSetOrder()) {
                IncrementOrder sourceOrder;
                sourceOrder = this.getOrder();
                IncrementOrder copyOrder = ((IncrementOrder) strategy.copy(LocatorUtils.property(locator, "order", sourceOrder), sourceOrder));
                copy.setOrder(copyOrder);
            } else {
                copy.order = null;
            }
            if (this.isSetAxisOrder()) {
                List<String> sourceAxisOrder;
                sourceAxisOrder = this.getAxisOrder();
                @SuppressWarnings("unchecked")
                List<String> copyAxisOrder = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "axisOrder", sourceAxisOrder), sourceAxisOrder));
                copy.unsetAxisOrder();
                List<String> uniqueAxisOrderl = copy.getAxisOrder();
                uniqueAxisOrderl.addAll(copyAxisOrder);
            } else {
                copy.unsetAxisOrder();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new SequenceRuleType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof SequenceRuleType) {
            final SequenceRuleType target = this;
            final SequenceRuleType leftObject = ((SequenceRuleType) left);
            final SequenceRuleType rightObject = ((SequenceRuleType) right);
            {
                SequenceRuleEnumeration lhsValue;
                lhsValue = leftObject.getValue();
                SequenceRuleEnumeration rhsValue;
                rhsValue = rightObject.getValue();
                target.setValue(((SequenceRuleEnumeration) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue)));
            }
            {
                IncrementOrder lhsOrder;
                lhsOrder = leftObject.getOrder();
                IncrementOrder rhsOrder;
                rhsOrder = rightObject.getOrder();
                target.setOrder(((IncrementOrder) strategy.merge(LocatorUtils.property(leftLocator, "order", lhsOrder), LocatorUtils.property(rightLocator, "order", rhsOrder), lhsOrder, rhsOrder)));
            }
            {
                List<String> lhsAxisOrder;
                lhsAxisOrder = leftObject.getAxisOrder();
                List<String> rhsAxisOrder;
                rhsAxisOrder = rightObject.getAxisOrder();
                target.unsetAxisOrder();
                List<String> uniqueAxisOrderl = target.getAxisOrder();
                uniqueAxisOrderl.addAll(((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "axisOrder", lhsAxisOrder), LocatorUtils.property(rightLocator, "axisOrder", rhsAxisOrder), lhsAxisOrder, rhsAxisOrder)));
            }
        }
    }

    public void setAxisOrder(List<String> value) {
        List<String> draftl = this.getAxisOrder();
        draftl.addAll(value);
    }

    public SequenceRuleType withValue(SequenceRuleEnumeration value) {
        setValue(value);
        return this;
    }

    public SequenceRuleType withOrder(IncrementOrder value) {
        setOrder(value);
        return this;
    }

    public SequenceRuleType withAxisOrder(String... values) {
        if (values!= null) {
            for (String value: values) {
                getAxisOrder().add(value);
            }
        }
        return this;
    }

    public SequenceRuleType withAxisOrder(Collection<String> values) {
        if (values!= null) {
            getAxisOrder().addAll(values);
        }
        return this;
    }

    public SequenceRuleType withAxisOrder(List<String> value) {
        setAxisOrder(value);
        return this;
    }

}
