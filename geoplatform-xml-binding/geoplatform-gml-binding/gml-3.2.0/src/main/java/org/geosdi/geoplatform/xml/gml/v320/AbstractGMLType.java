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
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
 * <p>Classe Java per AbstractGMLType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="AbstractGMLType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <group ref="{http://www.opengis.net/gml}StandardObjectProperties"/>
 *       </sequence>
 *       <attribute ref="{http://www.opengis.net/gml}id use="required""/>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
    AbstractTopologyType.class,
    CompositeValueType.class,
    AbstractGeometryType.class,
    AbstractTimeObjectType.class,
    AbstractFeatureType.class,
    DefinitionBaseType.class
})
public abstract class AbstractGMLType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<MetaDataPropertyType> metaDataProperty;
    /**
     * The value of this property is a text description of the object. gml:description uses gml:StringOrRefType as its content model, so it may contain a simple text string content, or carry a reference to an external description. The use of gml:description to reference an external description has been deprecated and replaced by the gml:descriptionReference property.
     * 
     */
    protected StringOrRefType description;
    /**
     * The value of this property is a remote text description of the object. The xlink:href attribute of the gml:descriptionReference property references the external description.
     * 
     */
    protected ReferenceType descriptionReference;
    /**
     * Often, a special identifier is assigned to an object by the maintaining authority with the intention that it is used in references to the object For such cases, the codeSpace shall be provided. That identifier is usually unique either globally or within an application domain. gml:identifier is a pre-defined property for such identifiers.
     * 
     */
    protected CodeWithAuthorityType identifier;
    /**
     * The gml:name property provides a label or identifier for the object, commonly a descriptive name. An object may have several names, typically assigned by different authorities. gml:name uses the gml:CodeType content model.  The authority for a name is indicated by the value of its (optional) codeSpace attribute.  The name may or may not be unique, as determined by the rules of the organization responsible for the codeSpace.  In common usage there will be one name per authority, so a processing application may select the name from its preferred codeSpace.
     * 
     */
    protected List<CodeType> name;
    /**
     * The attribute gml:id supports provision of a handle for the XML element representing a GML Object. Its use is mandatory for all GML objects. It is of XML type ID, so is constrained to be unique in the XML document within which it occurs.
     * 
     */
    @XmlAttribute(name = "id", namespace = "http://www.opengis.net/gml", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the metaDataProperty property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the metaDataProperty property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMetaDataProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetaDataPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the metaDataProperty property.
     */
    public List<MetaDataPropertyType> getMetaDataProperty() {
        if (metaDataProperty == null) {
            metaDataProperty = new ArrayList<>();
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
     * The value of this property is a text description of the object. gml:description uses gml:StringOrRefType as its content model, so it may contain a simple text string content, or carry a reference to an external description. The use of gml:description to reference an external description has been deprecated and replaced by the gml:descriptionReference property.
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
     * @see #getDescription()
     */
    public void setDescription(StringOrRefType value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * The value of this property is a remote text description of the object. The xlink:href attribute of the gml:descriptionReference property references the external description.
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
     * @see #getDescriptionReference()
     */
    public void setDescriptionReference(ReferenceType value) {
        this.descriptionReference = value;
    }

    public boolean isSetDescriptionReference() {
        return (this.descriptionReference!= null);
    }

    /**
     * Often, a special identifier is assigned to an object by the maintaining authority with the intention that it is used in references to the object For such cases, the codeSpace shall be provided. That identifier is usually unique either globally or within an application domain. gml:identifier is a pre-defined property for such identifiers.
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
     * @see #getIdentifier()
     */
    public void setIdentifier(CodeWithAuthorityType value) {
        this.identifier = value;
    }

    public boolean isSetIdentifier() {
        return (this.identifier!= null);
    }

    /**
     * The gml:name property provides a label or identifier for the object, commonly a descriptive name. An object may have several names, typically assigned by different authorities. gml:name uses the gml:CodeType content model.  The authority for a name is indicated by the value of its (optional) codeSpace attribute.  The name may or may not be unique, as determined by the rules of the organization responsible for the codeSpace.  In common usage there will be one name per authority, so a processing application may select the name from its preferred codeSpace.
     * 
     * Gets the value of the name property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the name property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeType }
     * </p>
     * 
     * 
     * @return
     *     The value of the name property.
     */
    public List<CodeType> getName() {
        if (name == null) {
            name = new ArrayList<>();
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
     * The attribute gml:id supports provision of a handle for the XML element representing a GML Object. Its use is mandatory for all GML objects. It is of XML type ID, so is constrained to be unique in the XML document within which it occurs.
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
     * @see #getId()
     */
    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id!= null);
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
            List<MetaDataPropertyType> theMetaDataProperty;
            theMetaDataProperty = (this.isSetMetaDataProperty()?this.getMetaDataProperty():null);
            strategy.appendField(locator, this, "metaDataProperty", buffer, theMetaDataProperty, this.isSetMetaDataProperty());
        }
        {
            StringOrRefType theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription, this.isSetDescription());
        }
        {
            ReferenceType theDescriptionReference;
            theDescriptionReference = this.getDescriptionReference();
            strategy.appendField(locator, this, "descriptionReference", buffer, theDescriptionReference, this.isSetDescriptionReference());
        }
        {
            CodeWithAuthorityType theIdentifier;
            theIdentifier = this.getIdentifier();
            strategy.appendField(locator, this, "identifier", buffer, theIdentifier, this.isSetIdentifier());
        }
        {
            List<CodeType> theName;
            theName = (this.isSetName()?this.getName():null);
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            String theId;
            theId = this.getId();
            strategy.appendField(locator, this, "id", buffer, theId, this.isSetId());
        }
        return buffer;
    }

    public void setMetaDataProperty(List<MetaDataPropertyType> value) {
        this.metaDataProperty = null;
        if (value!= null) {
            List<MetaDataPropertyType> draftl = this.getMetaDataProperty();
            draftl.addAll(value);
        }
    }

    public void setName(List<CodeType> value) {
        this.name = null;
        if (value!= null) {
            List<CodeType> draftl = this.getName();
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
        final AbstractGMLType that = ((AbstractGMLType) object);
        {
            List<MetaDataPropertyType> lhsMetaDataProperty;
            lhsMetaDataProperty = (this.isSetMetaDataProperty()?this.getMetaDataProperty():null);
            List<MetaDataPropertyType> rhsMetaDataProperty;
            rhsMetaDataProperty = (that.isSetMetaDataProperty()?that.getMetaDataProperty():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "metaDataProperty", lhsMetaDataProperty), LocatorUtils.property(thatLocator, "metaDataProperty", rhsMetaDataProperty), lhsMetaDataProperty, rhsMetaDataProperty, this.isSetMetaDataProperty(), that.isSetMetaDataProperty())) {
                return false;
            }
        }
        {
            StringOrRefType lhsDescription;
            lhsDescription = this.getDescription();
            StringOrRefType rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription, this.isSetDescription(), that.isSetDescription())) {
                return false;
            }
        }
        {
            ReferenceType lhsDescriptionReference;
            lhsDescriptionReference = this.getDescriptionReference();
            ReferenceType rhsDescriptionReference;
            rhsDescriptionReference = that.getDescriptionReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "descriptionReference", lhsDescriptionReference), LocatorUtils.property(thatLocator, "descriptionReference", rhsDescriptionReference), lhsDescriptionReference, rhsDescriptionReference, this.isSetDescriptionReference(), that.isSetDescriptionReference())) {
                return false;
            }
        }
        {
            CodeWithAuthorityType lhsIdentifier;
            lhsIdentifier = this.getIdentifier();
            CodeWithAuthorityType rhsIdentifier;
            rhsIdentifier = that.getIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "identifier", lhsIdentifier), LocatorUtils.property(thatLocator, "identifier", rhsIdentifier), lhsIdentifier, rhsIdentifier, this.isSetIdentifier(), that.isSetIdentifier())) {
                return false;
            }
        }
        {
            List<CodeType> lhsName;
            lhsName = (this.isSetName()?this.getName():null);
            List<CodeType> rhsName;
            rhsName = (that.isSetName()?that.getName():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName, this.isSetName(), that.isSetName())) {
                return false;
            }
        }
        {
            String lhsId;
            lhsId = this.getId();
            String rhsId;
            rhsId = that.getId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "id", lhsId), LocatorUtils.property(thatLocator, "id", rhsId), lhsId, rhsId, this.isSetId(), that.isSetId())) {
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
            List<MetaDataPropertyType> theMetaDataProperty;
            theMetaDataProperty = (this.isSetMetaDataProperty()?this.getMetaDataProperty():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "metaDataProperty", theMetaDataProperty), currentHashCode, theMetaDataProperty, this.isSetMetaDataProperty());
        }
        {
            StringOrRefType theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription, this.isSetDescription());
        }
        {
            ReferenceType theDescriptionReference;
            theDescriptionReference = this.getDescriptionReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "descriptionReference", theDescriptionReference), currentHashCode, theDescriptionReference, this.isSetDescriptionReference());
        }
        {
            CodeWithAuthorityType theIdentifier;
            theIdentifier = this.getIdentifier();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "identifier", theIdentifier), currentHashCode, theIdentifier, this.isSetIdentifier());
        }
        {
            List<CodeType> theName;
            theName = (this.isSetName()?this.getName():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "name", theName), currentHashCode, theName, this.isSetName());
        }
        {
            String theId;
            theId = this.getId();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "id", theId), currentHashCode, theId, this.isSetId());
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
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        if (target instanceof AbstractGMLType) {
            final AbstractGMLType copy = ((AbstractGMLType) target);
            {
                java.lang.Boolean metaDataPropertyShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMetaDataProperty());
                if (metaDataPropertyShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<MetaDataPropertyType> sourceMetaDataProperty;
                    sourceMetaDataProperty = (this.isSetMetaDataProperty()?this.getMetaDataProperty():null);
                    @SuppressWarnings("unchecked")
                    List<MetaDataPropertyType> copyMetaDataProperty = ((List<MetaDataPropertyType> ) strategy.copy(LocatorUtils.property(locator, "metaDataProperty", sourceMetaDataProperty), sourceMetaDataProperty, this.isSetMetaDataProperty()));
                    copy.setMetaDataProperty(copyMetaDataProperty);
                } else {
                    if (metaDataPropertyShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetMetaDataProperty();
                    }
                }
            }
            {
                java.lang.Boolean descriptionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDescription());
                if (descriptionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType sourceDescription;
                    sourceDescription = this.getDescription();
                    StringOrRefType copyDescription = ((StringOrRefType) strategy.copy(LocatorUtils.property(locator, "description", sourceDescription), sourceDescription, this.isSetDescription()));
                    copy.setDescription(copyDescription);
                } else {
                    if (descriptionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.description = null;
                    }
                }
            }
            {
                java.lang.Boolean descriptionReferenceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDescriptionReference());
                if (descriptionReferenceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType sourceDescriptionReference;
                    sourceDescriptionReference = this.getDescriptionReference();
                    ReferenceType copyDescriptionReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "descriptionReference", sourceDescriptionReference), sourceDescriptionReference, this.isSetDescriptionReference()));
                    copy.setDescriptionReference(copyDescriptionReference);
                } else {
                    if (descriptionReferenceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.descriptionReference = null;
                    }
                }
            }
            {
                java.lang.Boolean identifierShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIdentifier());
                if (identifierShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType sourceIdentifier;
                    sourceIdentifier = this.getIdentifier();
                    CodeWithAuthorityType copyIdentifier = ((CodeWithAuthorityType) strategy.copy(LocatorUtils.property(locator, "identifier", sourceIdentifier), sourceIdentifier, this.isSetIdentifier()));
                    copy.setIdentifier(copyIdentifier);
                } else {
                    if (identifierShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.identifier = null;
                    }
                }
            }
            {
                java.lang.Boolean nameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetName());
                if (nameShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<CodeType> sourceName;
                    sourceName = (this.isSetName()?this.getName():null);
                    @SuppressWarnings("unchecked")
                    List<CodeType> copyName = ((List<CodeType> ) strategy.copy(LocatorUtils.property(locator, "name", sourceName), sourceName, this.isSetName()));
                    copy.setName(copyName);
                } else {
                    if (nameShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetName();
                    }
                }
            }
            {
                java.lang.Boolean idShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetId());
                if (idShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceId;
                    sourceId = this.getId();
                    String copyId = ((String) strategy.copy(LocatorUtils.property(locator, "id", sourceId), sourceId, this.isSetId()));
                    copy.setId(copyId);
                } else {
                    if (idShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.id = null;
                    }
                }
            }
        }
        return target;
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof AbstractGMLType) {
            final AbstractGMLType target = this;
            final AbstractGMLType leftObject = ((AbstractGMLType) left);
            final AbstractGMLType rightObject = ((AbstractGMLType) right);
            {
                java.lang.Boolean metaDataPropertyShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMetaDataProperty(), rightObject.isSetMetaDataProperty());
                if (metaDataPropertyShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<MetaDataPropertyType> lhsMetaDataProperty;
                    lhsMetaDataProperty = (leftObject.isSetMetaDataProperty()?leftObject.getMetaDataProperty():null);
                    List<MetaDataPropertyType> rhsMetaDataProperty;
                    rhsMetaDataProperty = (rightObject.isSetMetaDataProperty()?rightObject.getMetaDataProperty():null);
                    List<MetaDataPropertyType> mergedMetaDataProperty = ((List<MetaDataPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "metaDataProperty", lhsMetaDataProperty), LocatorUtils.property(rightLocator, "metaDataProperty", rhsMetaDataProperty), lhsMetaDataProperty, rhsMetaDataProperty, leftObject.isSetMetaDataProperty(), rightObject.isSetMetaDataProperty()));
                    target.setMetaDataProperty(mergedMetaDataProperty);
                } else {
                    if (metaDataPropertyShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetMetaDataProperty();
                    }
                }
            }
            {
                java.lang.Boolean descriptionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDescription(), rightObject.isSetDescription());
                if (descriptionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    StringOrRefType lhsDescription;
                    lhsDescription = leftObject.getDescription();
                    StringOrRefType rhsDescription;
                    rhsDescription = rightObject.getDescription();
                    StringOrRefType mergedDescription = ((StringOrRefType) strategy.merge(LocatorUtils.property(leftLocator, "description", lhsDescription), LocatorUtils.property(rightLocator, "description", rhsDescription), lhsDescription, rhsDescription, leftObject.isSetDescription(), rightObject.isSetDescription()));
                    target.setDescription(mergedDescription);
                } else {
                    if (descriptionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.description = null;
                    }
                }
            }
            {
                java.lang.Boolean descriptionReferenceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDescriptionReference(), rightObject.isSetDescriptionReference());
                if (descriptionReferenceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType lhsDescriptionReference;
                    lhsDescriptionReference = leftObject.getDescriptionReference();
                    ReferenceType rhsDescriptionReference;
                    rhsDescriptionReference = rightObject.getDescriptionReference();
                    ReferenceType mergedDescriptionReference = ((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "descriptionReference", lhsDescriptionReference), LocatorUtils.property(rightLocator, "descriptionReference", rhsDescriptionReference), lhsDescriptionReference, rhsDescriptionReference, leftObject.isSetDescriptionReference(), rightObject.isSetDescriptionReference()));
                    target.setDescriptionReference(mergedDescriptionReference);
                } else {
                    if (descriptionReferenceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.descriptionReference = null;
                    }
                }
            }
            {
                java.lang.Boolean identifierShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIdentifier(), rightObject.isSetIdentifier());
                if (identifierShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CodeWithAuthorityType lhsIdentifier;
                    lhsIdentifier = leftObject.getIdentifier();
                    CodeWithAuthorityType rhsIdentifier;
                    rhsIdentifier = rightObject.getIdentifier();
                    CodeWithAuthorityType mergedIdentifier = ((CodeWithAuthorityType) strategy.merge(LocatorUtils.property(leftLocator, "identifier", lhsIdentifier), LocatorUtils.property(rightLocator, "identifier", rhsIdentifier), lhsIdentifier, rhsIdentifier, leftObject.isSetIdentifier(), rightObject.isSetIdentifier()));
                    target.setIdentifier(mergedIdentifier);
                } else {
                    if (identifierShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.identifier = null;
                    }
                }
            }
            {
                java.lang.Boolean nameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetName(), rightObject.isSetName());
                if (nameShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<CodeType> lhsName;
                    lhsName = (leftObject.isSetName()?leftObject.getName():null);
                    List<CodeType> rhsName;
                    rhsName = (rightObject.isSetName()?rightObject.getName():null);
                    List<CodeType> mergedName = ((List<CodeType> ) strategy.merge(LocatorUtils.property(leftLocator, "name", lhsName), LocatorUtils.property(rightLocator, "name", rhsName), lhsName, rhsName, leftObject.isSetName(), rightObject.isSetName()));
                    target.setName(mergedName);
                } else {
                    if (nameShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetName();
                    }
                }
            }
            {
                java.lang.Boolean idShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetId(), rightObject.isSetId());
                if (idShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsId;
                    lhsId = leftObject.getId();
                    String rhsId;
                    rhsId = rightObject.getId();
                    String mergedId = ((String) strategy.merge(LocatorUtils.property(leftLocator, "id", lhsId), LocatorUtils.property(rightLocator, "id", rhsId), lhsId, rhsId, leftObject.isSetId(), rightObject.isSetId()));
                    target.setId(mergedId);
                } else {
                    if (idShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.id = null;
                    }
                }
            }
        }
    }

}
