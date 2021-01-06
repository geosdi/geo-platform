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
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 * <p>Classe Java per AbstractGMLType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AbstractGMLType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.opengis.net/gml}StandardObjectProperties"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.opengis.net/gml}id use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGMLType", propOrder = {
    "metaDataProperty",
    "description",
    "descriptionReference",
    "identifier",
    "name"
})
@XmlSeeAlso({
    BagType.class,
    ArrayType.class,
    AbstractTimeSliceType.class,
    AbstractTimeObjectType.class,
    CompositeValueType.class,
    AbstractGeometryType.class,
    AbstractTopologyType.class,
    AbstractFeatureType.class,
    DefinitionBaseType.class
})
public abstract class AbstractGMLType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<MetaDataPropertyType> metaDataProperty;
    protected StringOrRefType description;
    protected ReferenceType descriptionReference;
    protected CodeWithAuthorityType identifier;
    protected List<CodeType> name;
    @XmlAttribute(name = "id", namespace = "http://www.opengis.net/gml", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the metaDataProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metaDataProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetaDataProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetaDataPropertyType }
     * 
     * 
     */
    public List<MetaDataPropertyType> getMetaDataProperty() {
        if (metaDataProperty == null) {
            metaDataProperty = new ArrayList<MetaDataPropertyType>();
        }
        return this.metaDataProperty;
    }

    public boolean isSetMetaDataProperty() {
        return ((this.metaDataProperty!= null)&&(!this.metaDataProperty.isEmpty()));
    }

    public void unsetMetaDataProperty() {
        this.metaDataProperty = null;
    }

    /**
     * Recupera il valore della proprietà description.
     * 
     * @return
     *     possible object is
     *     {@link StringOrRefType }
     *     
     */
    public StringOrRefType getDescription() {
        return description;
    }

    /**
     * Imposta il valore della proprietà description.
     * 
     * @param value
     *     allowed object is
     *     {@link StringOrRefType }
     *     
     */
    public void setDescription(StringOrRefType value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Recupera il valore della proprietà descriptionReference.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getDescriptionReference() {
        return descriptionReference;
    }

    /**
     * Imposta il valore della proprietà descriptionReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setDescriptionReference(ReferenceType value) {
        this.descriptionReference = value;
    }

    public boolean isSetDescriptionReference() {
        return (this.descriptionReference!= null);
    }

    /**
     * Recupera il valore della proprietà identifier.
     * 
     * @return
     *     possible object is
     *     {@link CodeWithAuthorityType }
     *     
     */
    public CodeWithAuthorityType getIdentifier() {
        return identifier;
    }

    /**
     * Imposta il valore della proprietà identifier.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeWithAuthorityType }
     *     
     */
    public void setIdentifier(CodeWithAuthorityType value) {
        this.identifier = value;
    }

    public boolean isSetIdentifier() {
        return (this.identifier!= null);
    }

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeType }
     * 
     * 
     */
    public List<CodeType> getName() {
        if (name == null) {
            name = new ArrayList<CodeType>();
        }
        return this.name;
    }

    public boolean isSetName() {
        return ((this.name!= null)&&(!this.name.isEmpty()));
    }

    public void unsetName() {
        this.name = null;
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id!= null);
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
            List<MetaDataPropertyType> theMetaDataProperty;
            theMetaDataProperty = this.getMetaDataProperty();
            strategy.appendField(locator, this, "metaDataProperty", buffer, theMetaDataProperty);
        }
        {
            StringOrRefType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            ReferenceType theDescriptionReference;
            theDescriptionReference = this.getDescriptionReference();
            strategy.appendField(locator, this, "descriptionReference", buffer, theDescriptionReference);
        }
        {
            CodeWithAuthorityType theIdentifier;
            theIdentifier = this.getIdentifier();
            strategy.appendField(locator, this, "identifier", buffer, theIdentifier);
        }
        {
            List<CodeType> theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theId;
            theId = this.getId();
            strategy.appendField(locator, this, "id", buffer, theId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AbstractGMLType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AbstractGMLType that = ((AbstractGMLType) object);
        {
            List<MetaDataPropertyType> lhsMetaDataProperty;
            lhsMetaDataProperty = this.getMetaDataProperty();
            List<MetaDataPropertyType> rhsMetaDataProperty;
            rhsMetaDataProperty = that.getMetaDataProperty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "metaDataProperty", lhsMetaDataProperty), LocatorUtils.property(thatLocator, "metaDataProperty", rhsMetaDataProperty), lhsMetaDataProperty, rhsMetaDataProperty)) {
                return false;
            }
        }
        {
            StringOrRefType lhsDescription;
            lhsDescription = this.getDescription();
            StringOrRefType rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            ReferenceType lhsDescriptionReference;
            lhsDescriptionReference = this.getDescriptionReference();
            ReferenceType rhsDescriptionReference;
            rhsDescriptionReference = that.getDescriptionReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "descriptionReference", lhsDescriptionReference), LocatorUtils.property(thatLocator, "descriptionReference", rhsDescriptionReference), lhsDescriptionReference, rhsDescriptionReference)) {
                return false;
            }
        }
        {
            CodeWithAuthorityType lhsIdentifier;
            lhsIdentifier = this.getIdentifier();
            CodeWithAuthorityType rhsIdentifier;
            rhsIdentifier = that.getIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "identifier", lhsIdentifier), LocatorUtils.property(thatLocator, "identifier", rhsIdentifier), lhsIdentifier, rhsIdentifier)) {
                return false;
            }
        }
        {
            List<CodeType> lhsName;
            lhsName = this.getName();
            List<CodeType> rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            String lhsId;
            lhsId = this.getId();
            String rhsId;
            rhsId = that.getId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId)) {
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
            List<MetaDataPropertyType> theMetaDataProperty;
            theMetaDataProperty = this.getMetaDataProperty();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "metaDataProperty", theMetaDataProperty), currentHashCode, theMetaDataProperty);
        }
        {
            StringOrRefType theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            ReferenceType theDescriptionReference;
            theDescriptionReference = this.getDescriptionReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "descriptionReference", theDescriptionReference), currentHashCode, theDescriptionReference);
        }
        {
            CodeWithAuthorityType theIdentifier;
            theIdentifier = this.getIdentifier();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "identifier", theIdentifier), currentHashCode, theIdentifier);
        }
        {
            List<CodeType> theName;
            theName = this.getName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName);
        }
        {
            String theId;
            theId = this.getId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId);
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        if (target instanceof AbstractGMLType) {
            final AbstractGMLType copy = ((AbstractGMLType) target);
            if (this.isSetMetaDataProperty()) {
                List<MetaDataPropertyType> sourceMetaDataProperty;
                sourceMetaDataProperty = this.getMetaDataProperty();
                @SuppressWarnings("unchecked")
                List<MetaDataPropertyType> copyMetaDataProperty = ((List<MetaDataPropertyType> ) strategy.copy(LocatorUtils.property(locator, "metaDataProperty", sourceMetaDataProperty), sourceMetaDataProperty));
                copy.unsetMetaDataProperty();
                List<MetaDataPropertyType> uniqueMetaDataPropertyl = copy.getMetaDataProperty();
                uniqueMetaDataPropertyl.addAll(copyMetaDataProperty);
            } else {
                copy.unsetMetaDataProperty();
            }
            if (this.isSetDescription()) {
                StringOrRefType sourceDescription;
                sourceDescription = this.getDescription();
                StringOrRefType copyDescription = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "description", sourceDescription), sourceDescription));
                copy.setDescription(copyDescription);
            } else {
                copy.description = null;
            }
            if (this.isSetDescriptionReference()) {
                ReferenceType sourceDescriptionReference;
                sourceDescriptionReference = this.getDescriptionReference();
                ReferenceType copyDescriptionReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "descriptionReference", sourceDescriptionReference), sourceDescriptionReference));
                copy.setDescriptionReference(copyDescriptionReference);
            } else {
                copy.descriptionReference = null;
            }
            if (this.isSetIdentifier()) {
                CodeWithAuthorityType sourceIdentifier;
                sourceIdentifier = this.getIdentifier();
                CodeWithAuthorityType copyIdentifier = ((CodeWithAuthorityType) strategy.copy(LocatorUtils.property(locator, "identifier", sourceIdentifier), sourceIdentifier));
                copy.setIdentifier(copyIdentifier);
            } else {
                copy.identifier = null;
            }
            if (this.isSetName()) {
                List<CodeType> sourceName;
                sourceName = this.getName();
                @SuppressWarnings("unchecked")
                List<CodeType> copyName = ((List<CodeType> ) strategy.copy(LocatorUtils.property(locator, "name", sourceName), sourceName));
                copy.unsetName();
                List<CodeType> uniqueNamel = copy.getName();
                uniqueNamel.addAll(copyName);
            } else {
                copy.unsetName();
            }
            if (this.isSetId()) {
                String sourceId;
                sourceId = this.getId();
                String copyId = ((String) strategy.copy(LocatorUtils.property(locator, "id", sourceId), sourceId));
                copy.setId(copyId);
            } else {
                copy.id = null;
            }
        }
        return target;
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AbstractGMLType) {
            final AbstractGMLType target = this;
            final AbstractGMLType leftObject = ((AbstractGMLType) left);
            final AbstractGMLType rightObject = ((AbstractGMLType) right);
            {
                List<MetaDataPropertyType> lhsMetaDataProperty;
                lhsMetaDataProperty = leftObject.getMetaDataProperty();
                List<MetaDataPropertyType> rhsMetaDataProperty;
                rhsMetaDataProperty = rightObject.getMetaDataProperty();
                target.unsetMetaDataProperty();
                List<MetaDataPropertyType> uniqueMetaDataPropertyl = target.getMetaDataProperty();
                uniqueMetaDataPropertyl.addAll(((List<MetaDataPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "metaDataProperty", lhsMetaDataProperty), LocatorUtils.property(rightLocator, "metaDataProperty", rhsMetaDataProperty), lhsMetaDataProperty, rhsMetaDataProperty)));
            }
            {
                StringOrRefType lhsDescription;
                lhsDescription = leftObject.getDescription();
                StringOrRefType rhsDescription;
                rhsDescription = rightObject.getDescription();
                target.setDescription(((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "description", lhsDescription), LocatorUtils.property(rightLocator, "description", rhsDescription), lhsDescription, rhsDescription)));
            }
            {
                ReferenceType lhsDescriptionReference;
                lhsDescriptionReference = leftObject.getDescriptionReference();
                ReferenceType rhsDescriptionReference;
                rhsDescriptionReference = rightObject.getDescriptionReference();
                target.setDescriptionReference(((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "descriptionReference", lhsDescriptionReference), LocatorUtils.property(rightLocator, "descriptionReference", rhsDescriptionReference), lhsDescriptionReference, rhsDescriptionReference)));
            }
            {
                CodeWithAuthorityType lhsIdentifier;
                lhsIdentifier = leftObject.getIdentifier();
                CodeWithAuthorityType rhsIdentifier;
                rhsIdentifier = rightObject.getIdentifier();
                target.setIdentifier(((CodeWithAuthorityType) strategy.merge(LocatorUtils.property(leftLocator, "identifier", lhsIdentifier), LocatorUtils.property(rightLocator, "identifier", rhsIdentifier), lhsIdentifier, rhsIdentifier)));
            }
            {
                List<CodeType> lhsName;
                lhsName = leftObject.getName();
                List<CodeType> rhsName;
                rhsName = rightObject.getName();
                target.unsetName();
                List<CodeType> uniqueNamel = target.getName();
                uniqueNamel.addAll(((List<CodeType> ) strategy.merge(LocatorUtils.property(leftLocator, "name", lhsName), LocatorUtils.property(rightLocator, "name", rhsName), lhsName, rhsName)));
            }
            {
                String lhsId;
                lhsId = leftObject.getId();
                String rhsId;
                rhsId = rightObject.getId();
                target.setId(((String) strategy.merge(LocatorUtils.property(leftLocator, "id", lhsId), LocatorUtils.property(rightLocator, "id", rhsId), lhsId, rhsId)));
            }
        }
    }

    public void setMetaDataProperty(List<MetaDataPropertyType> value) {
        List<MetaDataPropertyType> draftl = this.getMetaDataProperty();
        draftl.addAll(value);
    }

    public void setName(List<CodeType> value) {
        List<CodeType> draftl = this.getName();
        draftl.addAll(value);
    }

    public AbstractGMLType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    public AbstractGMLType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    public AbstractGMLType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    public AbstractGMLType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    public AbstractGMLType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    public AbstractGMLType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    public AbstractGMLType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    public AbstractGMLType withId(String value) {
        setId(value);
        return this;
    }

    public AbstractGMLType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    public AbstractGMLType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
