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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
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
 * direction descriptions are specified by a compass point code, a keyword, a textual description or a reference to a description.
 * A gml:compassPoint is specified by a simple enumeration.  	
 * In addition, thre elements to contain text-based descriptions of direction are provided.  
 * If the direction is specified using a term from a list, gml:keyword should be used, and the list indicated using the value of the codeSpace attribute. 
 * if the direction is decribed in prose, gml:direction or gml:reference should be used, allowing the value to be included inline or by reference.
 * 
 * <p>Classe Java per DirectionDescriptionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DirectionDescriptionType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element name="compassPoint" type="{http://www.opengis.net/gml}CompassPointEnumeration"/>
 *         <element name="keyword" type="{http://www.opengis.net/gml}CodeType"/>
 *         <element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="reference" type="{http://www.opengis.net/gml}ReferenceType"/>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DirectionDescriptionType", propOrder = {
    "compassPoint",
    "keyword",
    "description",
    "reference"
})
public class DirectionDescriptionType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlSchemaType(name = "string")
    protected CompassPointEnumeration compassPoint;
    protected CodeType keyword;
    protected String description;
    protected ReferenceType reference;

    /**
     * Recupera il valore della proprietà compassPoint.
     * 
     * @return
     *     possible object is
     *     {@link CompassPointEnumeration }
     *     
     */
    public CompassPointEnumeration getCompassPoint() {
        return compassPoint;
    }

    /**
     * Imposta il valore della proprietà compassPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link CompassPointEnumeration }
     *     
     */
    public void setCompassPoint(CompassPointEnumeration value) {
        this.compassPoint = value;
    }

    public boolean isSetCompassPoint() {
        return (this.compassPoint!= null);
    }

    /**
     * Recupera il valore della proprietà keyword.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getKeyword() {
        return keyword;
    }

    /**
     * Imposta il valore della proprietà keyword.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setKeyword(CodeType value) {
        this.keyword = value;
    }

    public boolean isSetKeyword() {
        return (this.keyword!= null);
    }

    /**
     * Recupera il valore della proprietà description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Imposta il valore della proprietà description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    public boolean isSetDescription() {
        return (this.description!= null);
    }

    /**
     * Recupera il valore della proprietà reference.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getReference() {
        return reference;
    }

    /**
     * Imposta il valore della proprietà reference.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setReference(ReferenceType value) {
        this.reference = value;
    }

    public boolean isSetReference() {
        return (this.reference!= null);
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
            CompassPointEnumeration theCompassPoint;
            theCompassPoint = this.getCompassPoint();
            strategy.appendField(locator, this, "compassPoint", buffer, theCompassPoint, this.isSetCompassPoint());
        }
        {
            CodeType theKeyword;
            theKeyword = this.getKeyword();
            strategy.appendField(locator, this, "keyword", buffer, theKeyword, this.isSetKeyword());
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription, this.isSetDescription());
        }
        {
            ReferenceType theReference;
            theReference = this.getReference();
            strategy.appendField(locator, this, "reference", buffer, theReference, this.isSetReference());
        }
        return buffer;
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DirectionDescriptionType that = ((DirectionDescriptionType) object);
        {
            CompassPointEnumeration lhsCompassPoint;
            lhsCompassPoint = this.getCompassPoint();
            CompassPointEnumeration rhsCompassPoint;
            rhsCompassPoint = that.getCompassPoint();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "compassPoint", lhsCompassPoint), LocatorUtils.property(thatLocator, "compassPoint", rhsCompassPoint), lhsCompassPoint, rhsCompassPoint, this.isSetCompassPoint(), that.isSetCompassPoint())) {
                return false;
            }
        }
        {
            CodeType lhsKeyword;
            lhsKeyword = this.getKeyword();
            CodeType rhsKeyword;
            rhsKeyword = that.getKeyword();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "keyword", lhsKeyword), LocatorUtils.property(thatLocator, "keyword", rhsKeyword), lhsKeyword, rhsKeyword, this.isSetKeyword(), that.isSetKeyword())) {
                return false;
            }
        }
        {
            String lhsDescription;
            lhsDescription = this.getDescription();
            String rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription, this.isSetDescription(), that.isSetDescription())) {
                return false;
            }
        }
        {
            ReferenceType lhsReference;
            lhsReference = this.getReference();
            ReferenceType rhsReference;
            rhsReference = that.getReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "reference", lhsReference), LocatorUtils.property(thatLocator, "reference", rhsReference), lhsReference, rhsReference, this.isSetReference(), that.isSetReference())) {
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
            CompassPointEnumeration theCompassPoint;
            theCompassPoint = this.getCompassPoint();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "compassPoint", theCompassPoint), currentHashCode, theCompassPoint, this.isSetCompassPoint());
        }
        {
            CodeType theKeyword;
            theKeyword = this.getKeyword();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "keyword", theKeyword), currentHashCode, theKeyword, this.isSetKeyword());
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription, this.isSetDescription());
        }
        {
            ReferenceType theReference;
            theReference = this.getReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "reference", theReference), currentHashCode, theReference, this.isSetReference());
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
        if (draftCopy instanceof DirectionDescriptionType) {
            final DirectionDescriptionType copy = ((DirectionDescriptionType) draftCopy);
            {
                java.lang.Boolean compassPointShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCompassPoint());
                if (compassPointShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CompassPointEnumeration sourceCompassPoint;
                    sourceCompassPoint = this.getCompassPoint();
                    CompassPointEnumeration copyCompassPoint = ((CompassPointEnumeration) strategy.copy(LocatorUtils.property(locator, "compassPoint", sourceCompassPoint), sourceCompassPoint, this.isSetCompassPoint()));
                    copy.setCompassPoint(copyCompassPoint);
                } else {
                    if (compassPointShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.compassPoint = null;
                    }
                }
            }
            {
                java.lang.Boolean keywordShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetKeyword());
                if (keywordShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CodeType sourceKeyword;
                    sourceKeyword = this.getKeyword();
                    CodeType copyKeyword = ((CodeType) strategy.copy(LocatorUtils.property(locator, "keyword", sourceKeyword), sourceKeyword, this.isSetKeyword()));
                    copy.setKeyword(copyKeyword);
                } else {
                    if (keywordShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.keyword = null;
                    }
                }
            }
            {
                java.lang.Boolean descriptionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDescription());
                if (descriptionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceDescription;
                    sourceDescription = this.getDescription();
                    String copyDescription = ((String) strategy.copy(LocatorUtils.property(locator, "description", sourceDescription), sourceDescription, this.isSetDescription()));
                    copy.setDescription(copyDescription);
                } else {
                    if (descriptionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.description = null;
                    }
                }
            }
            {
                java.lang.Boolean referenceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetReference());
                if (referenceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType sourceReference;
                    sourceReference = this.getReference();
                    ReferenceType copyReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "reference", sourceReference), sourceReference, this.isSetReference()));
                    copy.setReference(copyReference);
                } else {
                    if (referenceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.reference = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new DirectionDescriptionType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof DirectionDescriptionType) {
            final DirectionDescriptionType target = this;
            final DirectionDescriptionType leftObject = ((DirectionDescriptionType) left);
            final DirectionDescriptionType rightObject = ((DirectionDescriptionType) right);
            {
                java.lang.Boolean compassPointShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCompassPoint(), rightObject.isSetCompassPoint());
                if (compassPointShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CompassPointEnumeration lhsCompassPoint;
                    lhsCompassPoint = leftObject.getCompassPoint();
                    CompassPointEnumeration rhsCompassPoint;
                    rhsCompassPoint = rightObject.getCompassPoint();
                    CompassPointEnumeration mergedCompassPoint = ((CompassPointEnumeration) strategy.merge(LocatorUtils.property(leftLocator, "compassPoint", lhsCompassPoint), LocatorUtils.property(rightLocator, "compassPoint", rhsCompassPoint), lhsCompassPoint, rhsCompassPoint, leftObject.isSetCompassPoint(), rightObject.isSetCompassPoint()));
                    target.setCompassPoint(mergedCompassPoint);
                } else {
                    if (compassPointShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.compassPoint = null;
                    }
                }
            }
            {
                java.lang.Boolean keywordShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetKeyword(), rightObject.isSetKeyword());
                if (keywordShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CodeType lhsKeyword;
                    lhsKeyword = leftObject.getKeyword();
                    CodeType rhsKeyword;
                    rhsKeyword = rightObject.getKeyword();
                    CodeType mergedKeyword = ((CodeType) strategy.merge(LocatorUtils.property(leftLocator, "keyword", lhsKeyword), LocatorUtils.property(rightLocator, "keyword", rhsKeyword), lhsKeyword, rhsKeyword, leftObject.isSetKeyword(), rightObject.isSetKeyword()));
                    target.setKeyword(mergedKeyword);
                } else {
                    if (keywordShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.keyword = null;
                    }
                }
            }
            {
                java.lang.Boolean descriptionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDescription(), rightObject.isSetDescription());
                if (descriptionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsDescription;
                    lhsDescription = leftObject.getDescription();
                    String rhsDescription;
                    rhsDescription = rightObject.getDescription();
                    String mergedDescription = ((String) strategy.merge(LocatorUtils.property(leftLocator, "description", lhsDescription), LocatorUtils.property(rightLocator, "description", rhsDescription), lhsDescription, rhsDescription, leftObject.isSetDescription(), rightObject.isSetDescription()));
                    target.setDescription(mergedDescription);
                } else {
                    if (descriptionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.description = null;
                    }
                }
            }
            {
                java.lang.Boolean referenceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetReference(), rightObject.isSetReference());
                if (referenceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ReferenceType lhsReference;
                    lhsReference = leftObject.getReference();
                    ReferenceType rhsReference;
                    rhsReference = rightObject.getReference();
                    ReferenceType mergedReference = ((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "reference", lhsReference), LocatorUtils.property(rightLocator, "reference", rhsReference), lhsReference, rhsReference, leftObject.isSetReference(), rightObject.isSetReference()));
                    target.setReference(mergedReference);
                } else {
                    if (referenceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.reference = null;
                    }
                }
            }
        }
    }

}
