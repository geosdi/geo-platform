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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * direction descriptions are specified by a compass point code, a keyword, a textual description or a reference to a description.
 * A gml:compassPoint is specified by a simple enumeration.  	
 * In addition, thre elements to contain text-based descriptions of direction are provided.  
 * If the direction is specified using a term from a list, gml:keyword should be used, and the list indicated using the value of the codeSpace attribute. 
 * if the direction is decribed in prose, gml:direction or gml:reference should be used, allowing the value to be included inline or by reference.
 * 
 * <p>Classe Java per DirectionDescriptionType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DirectionDescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="compassPoint" type="{http://www.opengis.net/gml}CompassPointEnumeration"/>
 *         &lt;element name="keyword" type="{http://www.opengis.net/gml}CodeType"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reference" type="{http://www.opengis.net/gml}ReferenceType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
public class DirectionDescriptionType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

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
            CompassPointEnumeration theCompassPoint;
            theCompassPoint = this.getCompassPoint();
            strategy.appendField(locator, this, "compassPoint", buffer, theCompassPoint);
        }
        {
            CodeType theKeyword;
            theKeyword = this.getKeyword();
            strategy.appendField(locator, this, "keyword", buffer, theKeyword);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            ReferenceType theReference;
            theReference = this.getReference();
            strategy.appendField(locator, this, "reference", buffer, theReference);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DirectionDescriptionType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "compassPoint", lhsCompassPoint), LocatorUtils.property(thatLocator, "compassPoint", rhsCompassPoint), lhsCompassPoint, rhsCompassPoint)) {
                return false;
            }
        }
        {
            CodeType lhsKeyword;
            lhsKeyword = this.getKeyword();
            CodeType rhsKeyword;
            rhsKeyword = that.getKeyword();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "keyword", lhsKeyword), LocatorUtils.property(thatLocator, "keyword", rhsKeyword), lhsKeyword, rhsKeyword)) {
                return false;
            }
        }
        {
            String lhsDescription;
            lhsDescription = this.getDescription();
            String rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            ReferenceType lhsReference;
            lhsReference = this.getReference();
            ReferenceType rhsReference;
            rhsReference = that.getReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "reference", lhsReference), LocatorUtils.property(thatLocator, "reference", rhsReference), lhsReference, rhsReference)) {
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
            CompassPointEnumeration theCompassPoint;
            theCompassPoint = this.getCompassPoint();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "compassPoint", theCompassPoint), currentHashCode, theCompassPoint);
        }
        {
            CodeType theKeyword;
            theKeyword = this.getKeyword();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "keyword", theKeyword), currentHashCode, theKeyword);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "description", theDescription), currentHashCode, theDescription);
        }
        {
            ReferenceType theReference;
            theReference = this.getReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "reference", theReference), currentHashCode, theReference);
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
        if (draftCopy instanceof DirectionDescriptionType) {
            final DirectionDescriptionType copy = ((DirectionDescriptionType) draftCopy);
            if (this.isSetCompassPoint()) {
                CompassPointEnumeration sourceCompassPoint;
                sourceCompassPoint = this.getCompassPoint();
                CompassPointEnumeration copyCompassPoint = ((CompassPointEnumeration) strategy.copy(LocatorUtils.property(locator, "compassPoint", sourceCompassPoint), sourceCompassPoint));
                copy.setCompassPoint(copyCompassPoint);
            } else {
                copy.compassPoint = null;
            }
            if (this.isSetKeyword()) {
                CodeType sourceKeyword;
                sourceKeyword = this.getKeyword();
                CodeType copyKeyword = ((CodeType) strategy.copy(LocatorUtils.property(locator, "keyword", sourceKeyword), sourceKeyword));
                copy.setKeyword(copyKeyword);
            } else {
                copy.keyword = null;
            }
            if (this.isSetDescription()) {
                String sourceDescription;
                sourceDescription = this.getDescription();
                String copyDescription = ((String) strategy.copy(LocatorUtils.property(locator, "description", sourceDescription), sourceDescription));
                copy.setDescription(copyDescription);
            } else {
                copy.description = null;
            }
            if (this.isSetReference()) {
                ReferenceType sourceReference;
                sourceReference = this.getReference();
                ReferenceType copyReference = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "reference", sourceReference), sourceReference));
                copy.setReference(copyReference);
            } else {
                copy.reference = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DirectionDescriptionType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof DirectionDescriptionType) {
            final DirectionDescriptionType target = this;
            final DirectionDescriptionType leftObject = ((DirectionDescriptionType) left);
            final DirectionDescriptionType rightObject = ((DirectionDescriptionType) right);
            {
                CompassPointEnumeration lhsCompassPoint;
                lhsCompassPoint = leftObject.getCompassPoint();
                CompassPointEnumeration rhsCompassPoint;
                rhsCompassPoint = rightObject.getCompassPoint();
                target.setCompassPoint(((CompassPointEnumeration) strategy.merge(LocatorUtils.property(leftLocator, "compassPoint", lhsCompassPoint), LocatorUtils.property(rightLocator, "compassPoint", rhsCompassPoint), lhsCompassPoint, rhsCompassPoint)));
            }
            {
                CodeType lhsKeyword;
                lhsKeyword = leftObject.getKeyword();
                CodeType rhsKeyword;
                rhsKeyword = rightObject.getKeyword();
                target.setKeyword(((CodeType) strategy.merge(LocatorUtils.property(leftLocator, "keyword", lhsKeyword), LocatorUtils.property(rightLocator, "keyword", rhsKeyword), lhsKeyword, rhsKeyword)));
            }
            {
                String lhsDescription;
                lhsDescription = leftObject.getDescription();
                String rhsDescription;
                rhsDescription = rightObject.getDescription();
                target.setDescription(((String) strategy.merge(LocatorUtils.property(leftLocator, "description", lhsDescription), LocatorUtils.property(rightLocator, "description", rhsDescription), lhsDescription, rhsDescription)));
            }
            {
                ReferenceType lhsReference;
                lhsReference = leftObject.getReference();
                ReferenceType rhsReference;
                rhsReference = rightObject.getReference();
                target.setReference(((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "reference", lhsReference), LocatorUtils.property(rightLocator, "reference", rhsReference), lhsReference, rhsReference)));
            }
        }
    }

    public DirectionDescriptionType withCompassPoint(CompassPointEnumeration value) {
        setCompassPoint(value);
        return this;
    }

    public DirectionDescriptionType withKeyword(CodeType value) {
        setKeyword(value);
        return this;
    }

    public DirectionDescriptionType withDescription(String value) {
        setDescription(value);
        return this;
    }

    public DirectionDescriptionType withReference(ReferenceType value) {
        setReference(value);
        return this;
    }

}
