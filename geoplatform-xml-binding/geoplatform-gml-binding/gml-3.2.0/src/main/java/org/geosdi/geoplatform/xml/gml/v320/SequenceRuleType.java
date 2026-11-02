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
// Generato il: 2026.11.02 alle 03:38:19 PM CET
//

package org.geosdi.geoplatform.xml.gml.v320;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
import org.jvnet.jaxb.lang.CopyStrategy;
import org.jvnet.jaxb.lang.CopyTo;
import org.jvnet.jaxb.lang.Equals;
import org.jvnet.jaxb.lang.EqualsStrategy;
import org.jvnet.jaxb.lang.HashCode;
import org.jvnet.jaxb.lang.HashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBCopyStrategy;
import org.jvnet.jaxb.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb.lang.JAXBHashCodeStrategy;
import org.jvnet.jaxb.lang.JAXBMergeStrategy;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.MergeFrom;
import org.jvnet.jaxb.lang.MergeStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;
import org.jvnet.jaxb.locator.util.LocatorUtils;


/**
 * The gml:SequenceRuleType is derived from the gml:SequenceRuleEnumeration through the addition of an axisOrder attribute.  The gml:SequenceRuleEnumeration is an enumerated type. The rule names are defined in ISO 19123. If no rule name is specified the default is “Linear”.
 * 
 * <p>Classe Java per SequenceRuleType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SequenceRuleType">
 *   <simpleContent>
 *     <extension base="<http://www.opengis.net/gml>SequenceRuleEnumeration">
 *       <attribute name="order" type="{http://www.opengis.net/gml}IncrementOrder" />
 *       <attribute name="axisOrder" type="{http://www.opengis.net/gml}AxisDirectionList" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SequenceRuleType", propOrder = {
    "value"
})
public class SequenceRuleType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the axisOrder property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAxisOrder().add(newItem);
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
     *     The value of the axisOrder property.
     */
    public List<String> getAxisOrder() {
        if (axisOrder == null) {
            axisOrder = new ArrayList<>();
        }
        return this.axisOrder;
    }

    public boolean isSetAxisOrder() {
        return ((this.axisOrder!= null)&&(!this.axisOrder.isEmpty()));
    }

    public void unsetAxisOrder() {
        this.axisOrder = null;
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
            SequenceRuleEnumeration theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            IncrementOrder theOrder;
            theOrder = this.getOrder();
            strategy.appendField(locator, this, "order", buffer, theOrder, this.isSetOrder());
        }
        {
            List<String> theAxisOrder;
            theAxisOrder = (this.isSetAxisOrder()?this.getAxisOrder():null);
            strategy.appendField(locator, this, "axisOrder", buffer, theAxisOrder, this.isSetAxisOrder());
        }
        return buffer;
    }

    public void setAxisOrder(List<String> value) {
        this.axisOrder = null;
        if (value!= null) {
            List<String> draftl = this.getAxisOrder();
            draftl.addAll(value);
        }
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            IncrementOrder lhsOrder;
            lhsOrder = this.getOrder();
            IncrementOrder rhsOrder;
            rhsOrder = that.getOrder();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "order", lhsOrder), LocatorUtils.property(thatLocator, "order", rhsOrder), lhsOrder, rhsOrder, this.isSetOrder(), that.isSetOrder())) {
                return false;
            }
        }
        {
            List<String> lhsAxisOrder;
            lhsAxisOrder = (this.isSetAxisOrder()?this.getAxisOrder():null);
            List<String> rhsAxisOrder;
            rhsAxisOrder = (that.isSetAxisOrder()?that.getAxisOrder():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "axisOrder", lhsAxisOrder), LocatorUtils.property(thatLocator, "axisOrder", rhsAxisOrder), lhsAxisOrder, rhsAxisOrder, this.isSetAxisOrder(), that.isSetAxisOrder())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
        return equals(null, null, object, strategy);
    }

    @Override
    public int hashCode(ObjectLocator locator, HashCodeStrategy strategy) {
        int currentHashCode = 1;
        {
            SequenceRuleEnumeration theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            IncrementOrder theOrder;
            theOrder = this.getOrder();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "order", theOrder), currentHashCode, theOrder, this.isSetOrder());
        }
        {
            List<String> theAxisOrder;
            theAxisOrder = (this.isSetAxisOrder()?this.getAxisOrder():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "axisOrder", theAxisOrder), currentHashCode, theAxisOrder, this.isSetAxisOrder());
        }
        return currentHashCode;
    }

    @Override
    public int hashCode() {
        final HashCodeStrategy strategy = JAXBHashCodeStrategy.getInstance();
        return this.hashCode(null, strategy);
    }

    @Override
    public Object clone() {
        return copyTo(createNewInstance());
    }

    @Override
    public Object copyTo(Object target) {
        final CopyStrategy strategy = JAXBCopyStrategy.getInstance();
        return copyTo(null, target, strategy);
    }

    @Override
    public Object copyTo(ObjectLocator locator, Object target, CopyStrategy strategy) {
        final Object draftCopy = ((target == null)?createNewInstance():target);
        if (draftCopy instanceof SequenceRuleType) {
            final SequenceRuleType copy = ((SequenceRuleType) draftCopy);
            {
                java.lang.Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    SequenceRuleEnumeration sourceValue;
                    sourceValue = this.getValue();
                    SequenceRuleEnumeration copyValue = ((SequenceRuleEnumeration) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.value = null;
                    }
                }
            }
            {
                java.lang.Boolean orderShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOrder());
                if (orderShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    IncrementOrder sourceOrder;
                    sourceOrder = this.getOrder();
                    IncrementOrder copyOrder = ((IncrementOrder) strategy.copy(LocatorUtils.property(locator, "order", sourceOrder), sourceOrder, this.isSetOrder()));
                    copy.setOrder(copyOrder);
                } else {
                    if (orderShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.order = null;
                    }
                }
            }
            {
                java.lang.Boolean axisOrderShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAxisOrder());
                if (axisOrderShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceAxisOrder;
                    sourceAxisOrder = (this.isSetAxisOrder()?this.getAxisOrder():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyAxisOrder = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "axisOrder", sourceAxisOrder), sourceAxisOrder, this.isSetAxisOrder()));
                    copy.setAxisOrder(copyAxisOrder);
                } else {
                    if (axisOrderShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetAxisOrder();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new SequenceRuleType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof SequenceRuleType) {
            final SequenceRuleType target = this;
            final SequenceRuleType leftObject = ((SequenceRuleType) left);
            final SequenceRuleType rightObject = ((SequenceRuleType) right);
            {
                java.lang.Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    SequenceRuleEnumeration lhsValue;
                    lhsValue = leftObject.getValue();
                    SequenceRuleEnumeration rhsValue;
                    rhsValue = rightObject.getValue();
                    SequenceRuleEnumeration mergedValue = ((SequenceRuleEnumeration) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.value = null;
                    }
                }
            }
            {
                java.lang.Boolean orderShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOrder(), rightObject.isSetOrder());
                if (orderShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    IncrementOrder lhsOrder;
                    lhsOrder = leftObject.getOrder();
                    IncrementOrder rhsOrder;
                    rhsOrder = rightObject.getOrder();
                    IncrementOrder mergedOrder = ((IncrementOrder) strategy.merge(LocatorUtils.property(leftLocator, "order", lhsOrder), LocatorUtils.property(rightLocator, "order", rhsOrder), lhsOrder, rhsOrder, leftObject.isSetOrder(), rightObject.isSetOrder()));
                    target.setOrder(mergedOrder);
                } else {
                    if (orderShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.order = null;
                    }
                }
            }
            {
                java.lang.Boolean axisOrderShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAxisOrder(), rightObject.isSetAxisOrder());
                if (axisOrderShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsAxisOrder;
                    lhsAxisOrder = (leftObject.isSetAxisOrder()?leftObject.getAxisOrder():null);
                    List<String> rhsAxisOrder;
                    rhsAxisOrder = (rightObject.isSetAxisOrder()?rightObject.getAxisOrder():null);
                    List<String> mergedAxisOrder = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "axisOrder", lhsAxisOrder), LocatorUtils.property(rightLocator, "axisOrder", rhsAxisOrder), lhsAxisOrder, rhsAxisOrder, leftObject.isSetAxisOrder(), rightObject.isSetAxisOrder()));
                    target.setAxisOrder(mergedAxisOrder);
                } else {
                    if (axisOrderShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetAxisOrder();
                    }
                }
            }
        }
    }

}
