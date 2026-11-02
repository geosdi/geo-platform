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
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
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
 * <p>Classe Java per CompositeValueType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CompositeValueType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGMLType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}valueComponent" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}valueComponents" minOccurs="0"/>
 *       </sequence>
 *       <attGroup ref="{http://www.opengis.net/gml}AggregationAttributeGroup"/>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompositeValueType", propOrder = {
    "valueComponent",
    "valueComponents"
})
@XmlSeeAlso({
    ValueArrayType.class
})
public class CompositeValueType
    extends AbstractGMLType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * Property that refers to, or contains, a Value.
     * 
     */
    protected List<ValuePropertyType> valueComponent;
    /**
     * Property that contains Values.
     * 
     */
    protected ValueArrayPropertyType valueComponents;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * Property that refers to, or contains, a Value.
     * 
     * Gets the value of the valueComponent property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the valueComponent property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getValueComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValuePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the valueComponent property.
     */
    public List<ValuePropertyType> getValueComponent() {
        if (valueComponent == null) {
            valueComponent = new ArrayList<>();
        }
        return this.valueComponent;
    }

    public boolean isSetValueComponent() {
        return ((this.valueComponent!= null)&&(!this.valueComponent.isEmpty()));
    }

    public void unsetValueComponent() {
        this.valueComponent = null;
    }

    /**
     * Property that contains Values.
     * 
     * @return
     *     possible object is
     *     {@link ValueArrayPropertyType }
     *     
     */
    public ValueArrayPropertyType getValueComponents() {
        return valueComponents;
    }

    /**
     * Imposta il valore della proprietà valueComponents.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueArrayPropertyType }
     *     
     * @see #getValueComponents()
     */
    public void setValueComponents(ValueArrayPropertyType value) {
        this.valueComponents = value;
    }

    public boolean isSetValueComponents() {
        return (this.valueComponents!= null);
    }

    /**
     * Recupera il valore della proprietà aggregationType.
     * 
     * @return
     *     possible object is
     *     {@link AggregationType }
     *     
     */
    public AggregationType getAggregationType() {
        return aggregationType;
    }

    /**
     * Imposta il valore della proprietà aggregationType.
     * 
     * @param value
     *     allowed object is
     *     {@link AggregationType }
     *     
     */
    public void setAggregationType(AggregationType value) {
        this.aggregationType = value;
    }

    public boolean isSetAggregationType() {
        return (this.aggregationType!= null);
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
            List<ValuePropertyType> theValueComponent;
            theValueComponent = (this.isSetValueComponent()?this.getValueComponent():null);
            strategy.appendField(locator, this, "valueComponent", buffer, theValueComponent, this.isSetValueComponent());
        }
        {
            ValueArrayPropertyType theValueComponents;
            theValueComponents = this.getValueComponents();
            strategy.appendField(locator, this, "valueComponents", buffer, theValueComponents, this.isSetValueComponents());
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType, this.isSetAggregationType());
        }
        return buffer;
    }

    public void setValueComponent(List<ValuePropertyType> value) {
        this.valueComponent = null;
        if (value!= null) {
            List<ValuePropertyType> draftl = this.getValueComponent();
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final CompositeValueType that = ((CompositeValueType) object);
        {
            List<ValuePropertyType> lhsValueComponent;
            lhsValueComponent = (this.isSetValueComponent()?this.getValueComponent():null);
            List<ValuePropertyType> rhsValueComponent;
            rhsValueComponent = (that.isSetValueComponent()?that.getValueComponent():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueComponent", lhsValueComponent), LocatorUtils.property(thatLocator, "valueComponent", rhsValueComponent), lhsValueComponent, rhsValueComponent, this.isSetValueComponent(), that.isSetValueComponent())) {
                return false;
            }
        }
        {
            ValueArrayPropertyType lhsValueComponents;
            lhsValueComponents = this.getValueComponents();
            ValueArrayPropertyType rhsValueComponents;
            rhsValueComponents = that.getValueComponents();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueComponents", lhsValueComponents), LocatorUtils.property(thatLocator, "valueComponents", rhsValueComponents), lhsValueComponents, rhsValueComponents, this.isSetValueComponents(), that.isSetValueComponents())) {
                return false;
            }
        }
        {
            AggregationType lhsAggregationType;
            lhsAggregationType = this.getAggregationType();
            AggregationType rhsAggregationType;
            rhsAggregationType = that.getAggregationType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(thatLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType, this.isSetAggregationType(), that.isSetAggregationType())) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            List<ValuePropertyType> theValueComponent;
            theValueComponent = (this.isSetValueComponent()?this.getValueComponent():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueComponent", theValueComponent), currentHashCode, theValueComponent, this.isSetValueComponent());
        }
        {
            ValueArrayPropertyType theValueComponents;
            theValueComponents = this.getValueComponents();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueComponents", theValueComponents), currentHashCode, theValueComponents, this.isSetValueComponents());
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "aggregationType", theAggregationType), currentHashCode, theAggregationType, this.isSetAggregationType());
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof CompositeValueType) {
            final CompositeValueType copy = ((CompositeValueType) draftCopy);
            {
                java.lang.Boolean valueComponentShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValueComponent());
                if (valueComponentShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<ValuePropertyType> sourceValueComponent;
                    sourceValueComponent = (this.isSetValueComponent()?this.getValueComponent():null);
                    @SuppressWarnings("unchecked")
                    List<ValuePropertyType> copyValueComponent = ((List<ValuePropertyType> ) strategy.copy(LocatorUtils.property(locator, "valueComponent", sourceValueComponent), sourceValueComponent, this.isSetValueComponent()));
                    copy.setValueComponent(copyValueComponent);
                } else {
                    if (valueComponentShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetValueComponent();
                    }
                }
            }
            {
                java.lang.Boolean valueComponentsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValueComponents());
                if (valueComponentsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ValueArrayPropertyType sourceValueComponents;
                    sourceValueComponents = this.getValueComponents();
                    ValueArrayPropertyType copyValueComponents = ((ValueArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "valueComponents", sourceValueComponents), sourceValueComponents, this.isSetValueComponents()));
                    copy.setValueComponents(copyValueComponents);
                } else {
                    if (valueComponentsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.valueComponents = null;
                    }
                }
            }
            {
                java.lang.Boolean aggregationTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAggregationType());
                if (aggregationTypeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    AggregationType sourceAggregationType;
                    sourceAggregationType = this.getAggregationType();
                    AggregationType copyAggregationType = ((AggregationType) strategy.copy(LocatorUtils.property(locator, "aggregationType", sourceAggregationType), sourceAggregationType, this.isSetAggregationType()));
                    copy.setAggregationType(copyAggregationType);
                } else {
                    if (aggregationTypeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.aggregationType = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new CompositeValueType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof CompositeValueType) {
            final CompositeValueType target = this;
            final CompositeValueType leftObject = ((CompositeValueType) left);
            final CompositeValueType rightObject = ((CompositeValueType) right);
            {
                java.lang.Boolean valueComponentShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValueComponent(), rightObject.isSetValueComponent());
                if (valueComponentShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<ValuePropertyType> lhsValueComponent;
                    lhsValueComponent = (leftObject.isSetValueComponent()?leftObject.getValueComponent():null);
                    List<ValuePropertyType> rhsValueComponent;
                    rhsValueComponent = (rightObject.isSetValueComponent()?rightObject.getValueComponent():null);
                    List<ValuePropertyType> mergedValueComponent = ((List<ValuePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "valueComponent", lhsValueComponent), LocatorUtils.property(rightLocator, "valueComponent", rhsValueComponent), lhsValueComponent, rhsValueComponent, leftObject.isSetValueComponent(), rightObject.isSetValueComponent()));
                    target.setValueComponent(mergedValueComponent);
                } else {
                    if (valueComponentShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetValueComponent();
                    }
                }
            }
            {
                java.lang.Boolean valueComponentsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValueComponents(), rightObject.isSetValueComponents());
                if (valueComponentsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ValueArrayPropertyType lhsValueComponents;
                    lhsValueComponents = leftObject.getValueComponents();
                    ValueArrayPropertyType rhsValueComponents;
                    rhsValueComponents = rightObject.getValueComponents();
                    ValueArrayPropertyType mergedValueComponents = ((ValueArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "valueComponents", lhsValueComponents), LocatorUtils.property(rightLocator, "valueComponents", rhsValueComponents), lhsValueComponents, rhsValueComponents, leftObject.isSetValueComponents(), rightObject.isSetValueComponents()));
                    target.setValueComponents(mergedValueComponents);
                } else {
                    if (valueComponentsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.valueComponents = null;
                    }
                }
            }
            {
                java.lang.Boolean aggregationTypeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAggregationType(), rightObject.isSetAggregationType());
                if (aggregationTypeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    AggregationType lhsAggregationType;
                    lhsAggregationType = leftObject.getAggregationType();
                    AggregationType rhsAggregationType;
                    rhsAggregationType = rightObject.getAggregationType();
                    AggregationType mergedAggregationType = ((AggregationType) strategy.merge(LocatorUtils.property(leftLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(rightLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType, leftObject.isSetAggregationType(), rightObject.isSetAggregationType()));
                    target.setAggregationType(mergedAggregationType);
                } else {
                    if (aggregationTypeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.aggregationType = null;
                    }
                }
            }
        }
    }

}
