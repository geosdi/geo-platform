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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
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
 * <p>Classe Java per CompositeValueType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CompositeValueType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGMLType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}valueComponent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}valueComponents" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}AggregationAttributeGroup"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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

    protected List<ValuePropertyType> valueComponent;
    protected ValueArrayPropertyType valueComponents;
    @XmlAttribute(name = "aggregationType")
    protected AggregationType aggregationType;

    /**
     * Gets the value of the valueComponent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueComponent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValuePropertyType }
     * 
     * 
     */
    public List<ValuePropertyType> getValueComponent() {
        if (valueComponent == null) {
            valueComponent = new ArrayList<ValuePropertyType>();
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
     * Recupera il valore della proprietà valueComponents.
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
        super.appendFields(locator, buffer, strategy);
        {
            List<ValuePropertyType> theValueComponent;
            theValueComponent = this.getValueComponent();
            strategy.appendField(locator, this, "valueComponent", buffer, theValueComponent);
        }
        {
            ValueArrayPropertyType theValueComponents;
            theValueComponents = this.getValueComponents();
            strategy.appendField(locator, this, "valueComponents", buffer, theValueComponents);
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            strategy.appendField(locator, this, "aggregationType", buffer, theAggregationType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CompositeValueType)) {
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
            lhsValueComponent = this.getValueComponent();
            List<ValuePropertyType> rhsValueComponent;
            rhsValueComponent = that.getValueComponent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueComponent", lhsValueComponent), LocatorUtils.property(thatLocator, "valueComponent", rhsValueComponent), lhsValueComponent, rhsValueComponent)) {
                return false;
            }
        }
        {
            ValueArrayPropertyType lhsValueComponents;
            lhsValueComponents = this.getValueComponents();
            ValueArrayPropertyType rhsValueComponents;
            rhsValueComponents = that.getValueComponents();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueComponents", lhsValueComponents), LocatorUtils.property(thatLocator, "valueComponents", rhsValueComponents), lhsValueComponents, rhsValueComponents)) {
                return false;
            }
        }
        {
            AggregationType lhsAggregationType;
            lhsAggregationType = this.getAggregationType();
            AggregationType rhsAggregationType;
            rhsAggregationType = that.getAggregationType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(thatLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType)) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            List<ValuePropertyType> theValueComponent;
            theValueComponent = this.getValueComponent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueComponent", theValueComponent), currentHashCode, theValueComponent);
        }
        {
            ValueArrayPropertyType theValueComponents;
            theValueComponents = this.getValueComponents();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueComponents", theValueComponents), currentHashCode, theValueComponents);
        }
        {
            AggregationType theAggregationType;
            theAggregationType = this.getAggregationType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "aggregationType", theAggregationType), currentHashCode, theAggregationType);
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof CompositeValueType) {
            final CompositeValueType copy = ((CompositeValueType) draftCopy);
            if (this.isSetValueComponent()) {
                List<ValuePropertyType> sourceValueComponent;
                sourceValueComponent = this.getValueComponent();
                @SuppressWarnings("unchecked")
                List<ValuePropertyType> copyValueComponent = ((List<ValuePropertyType> ) strategy.copy(LocatorUtils.property(locator, "valueComponent", sourceValueComponent), sourceValueComponent));
                copy.unsetValueComponent();
                List<ValuePropertyType> uniqueValueComponentl = copy.getValueComponent();
                uniqueValueComponentl.addAll(copyValueComponent);
            } else {
                copy.unsetValueComponent();
            }
            if (this.isSetValueComponents()) {
                ValueArrayPropertyType sourceValueComponents;
                sourceValueComponents = this.getValueComponents();
                ValueArrayPropertyType copyValueComponents = ((ValueArrayPropertyType) strategy.copy(LocatorUtils.property(locator, "valueComponents", sourceValueComponents), sourceValueComponents));
                copy.setValueComponents(copyValueComponents);
            } else {
                copy.valueComponents = null;
            }
            if (this.isSetAggregationType()) {
                AggregationType sourceAggregationType;
                sourceAggregationType = this.getAggregationType();
                AggregationType copyAggregationType = ((AggregationType) strategy.copy(LocatorUtils.property(locator, "aggregationType", sourceAggregationType), sourceAggregationType));
                copy.setAggregationType(copyAggregationType);
            } else {
                copy.aggregationType = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new CompositeValueType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof CompositeValueType) {
            final CompositeValueType target = this;
            final CompositeValueType leftObject = ((CompositeValueType) left);
            final CompositeValueType rightObject = ((CompositeValueType) right);
            {
                List<ValuePropertyType> lhsValueComponent;
                lhsValueComponent = leftObject.getValueComponent();
                List<ValuePropertyType> rhsValueComponent;
                rhsValueComponent = rightObject.getValueComponent();
                target.unsetValueComponent();
                List<ValuePropertyType> uniqueValueComponentl = target.getValueComponent();
                uniqueValueComponentl.addAll(((List<ValuePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "valueComponent", lhsValueComponent), LocatorUtils.property(rightLocator, "valueComponent", rhsValueComponent), lhsValueComponent, rhsValueComponent)));
            }
            {
                ValueArrayPropertyType lhsValueComponents;
                lhsValueComponents = leftObject.getValueComponents();
                ValueArrayPropertyType rhsValueComponents;
                rhsValueComponents = rightObject.getValueComponents();
                target.setValueComponents(((ValueArrayPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "valueComponents", lhsValueComponents), LocatorUtils.property(rightLocator, "valueComponents", rhsValueComponents), lhsValueComponents, rhsValueComponents)));
            }
            {
                AggregationType lhsAggregationType;
                lhsAggregationType = leftObject.getAggregationType();
                AggregationType rhsAggregationType;
                rhsAggregationType = rightObject.getAggregationType();
                target.setAggregationType(((AggregationType) strategy.merge(LocatorUtils.property(leftLocator, "aggregationType", lhsAggregationType), LocatorUtils.property(rightLocator, "aggregationType", rhsAggregationType), lhsAggregationType, rhsAggregationType)));
            }
        }
    }

    public void setValueComponent(List<ValuePropertyType> value) {
        List<ValuePropertyType> draftl = this.getValueComponent();
        draftl.addAll(value);
    }

    public CompositeValueType withValueComponent(ValuePropertyType... values) {
        if (values!= null) {
            for (ValuePropertyType value: values) {
                getValueComponent().add(value);
            }
        }
        return this;
    }

    public CompositeValueType withValueComponent(Collection<ValuePropertyType> values) {
        if (values!= null) {
            getValueComponent().addAll(values);
        }
        return this;
    }

    public CompositeValueType withValueComponents(ValueArrayPropertyType value) {
        setValueComponents(value);
        return this;
    }

    public CompositeValueType withAggregationType(AggregationType value) {
        setAggregationType(value);
        return this;
    }

    public CompositeValueType withValueComponent(List<ValuePropertyType> value) {
        setValueComponent(value);
        return this;
    }

    @Override
    public CompositeValueType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public CompositeValueType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public CompositeValueType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public CompositeValueType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public CompositeValueType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public CompositeValueType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public CompositeValueType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public CompositeValueType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public CompositeValueType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public CompositeValueType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
