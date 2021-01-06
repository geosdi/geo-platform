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
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per TimeOrdinalEraType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TimeOrdinalEraType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}DefinitionType">
 *       &lt;sequence>
 *         &lt;element name="relatedTime" type="{http://www.opengis.net/gml}RelatedTimeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="start" type="{http://www.opengis.net/gml}TimeNodePropertyType"/>
 *         &lt;element name="end" type="{http://www.opengis.net/gml}TimeNodePropertyType"/>
 *         &lt;element name="extent" type="{http://www.opengis.net/gml}TimePeriodPropertyType" minOccurs="0"/>
 *         &lt;element name="member" type="{http://www.opengis.net/gml}TimeOrdinalEraPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="group" type="{http://www.opengis.net/gml}ReferenceType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeOrdinalEraType", propOrder = {
    "relatedTime",
    "start",
    "end",
    "extent",
    "member",
    "group"
})
public class TimeOrdinalEraType
    extends DefinitionType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<RelatedTimeType> relatedTime;
    @XmlElement(required = true)
    protected TimeNodePropertyType start;
    @XmlElement(required = true)
    protected TimeNodePropertyType end;
    protected TimePeriodPropertyType extent;
    protected List<TimeOrdinalEraPropertyType> member;
    protected ReferenceType group;

    /**
     * Gets the value of the relatedTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedTimeType }
     * 
     * 
     */
    public List<RelatedTimeType> getRelatedTime() {
        if (relatedTime == null) {
            relatedTime = new ArrayList<RelatedTimeType>();
        }
        return this.relatedTime;
    }

    public boolean isSetRelatedTime() {
        return ((this.relatedTime!= null)&&(!this.relatedTime.isEmpty()));
    }

    public void unsetRelatedTime() {
        this.relatedTime = null;
    }

    /**
     * Recupera il valore della proprietà start.
     * 
     * @return
     *     possible object is
     *     {@link TimeNodePropertyType }
     *     
     */
    public TimeNodePropertyType getStart() {
        return start;
    }

    /**
     * Imposta il valore della proprietà start.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeNodePropertyType }
     *     
     */
    public void setStart(TimeNodePropertyType value) {
        this.start = value;
    }

    public boolean isSetStart() {
        return (this.start!= null);
    }

    /**
     * Recupera il valore della proprietà end.
     * 
     * @return
     *     possible object is
     *     {@link TimeNodePropertyType }
     *     
     */
    public TimeNodePropertyType getEnd() {
        return end;
    }

    /**
     * Imposta il valore della proprietà end.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeNodePropertyType }
     *     
     */
    public void setEnd(TimeNodePropertyType value) {
        this.end = value;
    }

    public boolean isSetEnd() {
        return (this.end!= null);
    }

    /**
     * Recupera il valore della proprietà extent.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriodPropertyType }
     *     
     */
    public TimePeriodPropertyType getExtent() {
        return extent;
    }

    /**
     * Imposta il valore della proprietà extent.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriodPropertyType }
     *     
     */
    public void setExtent(TimePeriodPropertyType value) {
        this.extent = value;
    }

    public boolean isSetExtent() {
        return (this.extent!= null);
    }

    /**
     * Gets the value of the member property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the member property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeOrdinalEraPropertyType }
     * 
     * 
     */
    public List<TimeOrdinalEraPropertyType> getMember() {
        if (member == null) {
            member = new ArrayList<TimeOrdinalEraPropertyType>();
        }
        return this.member;
    }

    public boolean isSetMember() {
        return ((this.member!= null)&&(!this.member.isEmpty()));
    }

    public void unsetMember() {
        this.member = null;
    }

    /**
     * Recupera il valore della proprietà group.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceType }
     *     
     */
    public ReferenceType getGroup() {
        return group;
    }

    /**
     * Imposta il valore della proprietà group.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceType }
     *     
     */
    public void setGroup(ReferenceType value) {
        this.group = value;
    }

    public boolean isSetGroup() {
        return (this.group!= null);
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
            List<RelatedTimeType> theRelatedTime;
            theRelatedTime = this.getRelatedTime();
            strategy.appendField(locator, this, "relatedTime", buffer, theRelatedTime);
        }
        {
            TimeNodePropertyType theStart;
            theStart = this.getStart();
            strategy.appendField(locator, this, "start", buffer, theStart);
        }
        {
            TimeNodePropertyType theEnd;
            theEnd = this.getEnd();
            strategy.appendField(locator, this, "end", buffer, theEnd);
        }
        {
            TimePeriodPropertyType theExtent;
            theExtent = this.getExtent();
            strategy.appendField(locator, this, "extent", buffer, theExtent);
        }
        {
            List<TimeOrdinalEraPropertyType> theMember;
            theMember = this.getMember();
            strategy.appendField(locator, this, "member", buffer, theMember);
        }
        {
            ReferenceType theGroup;
            theGroup = this.getGroup();
            strategy.appendField(locator, this, "group", buffer, theGroup);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TimeOrdinalEraType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TimeOrdinalEraType that = ((TimeOrdinalEraType) object);
        {
            List<RelatedTimeType> lhsRelatedTime;
            lhsRelatedTime = this.getRelatedTime();
            List<RelatedTimeType> rhsRelatedTime;
            rhsRelatedTime = that.getRelatedTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "relatedTime", lhsRelatedTime), LocatorUtils.property(thatLocator, "relatedTime", rhsRelatedTime), lhsRelatedTime, rhsRelatedTime)) {
                return false;
            }
        }
        {
            TimeNodePropertyType lhsStart;
            lhsStart = this.getStart();
            TimeNodePropertyType rhsStart;
            rhsStart = that.getStart();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "start", lhsStart), LocatorUtils.property(thatLocator, "start", rhsStart), lhsStart, rhsStart)) {
                return false;
            }
        }
        {
            TimeNodePropertyType lhsEnd;
            lhsEnd = this.getEnd();
            TimeNodePropertyType rhsEnd;
            rhsEnd = that.getEnd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "end", lhsEnd), LocatorUtils.property(thatLocator, "end", rhsEnd), lhsEnd, rhsEnd)) {
                return false;
            }
        }
        {
            TimePeriodPropertyType lhsExtent;
            lhsExtent = this.getExtent();
            TimePeriodPropertyType rhsExtent;
            rhsExtent = that.getExtent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "extent", lhsExtent), LocatorUtils.property(thatLocator, "extent", rhsExtent), lhsExtent, rhsExtent)) {
                return false;
            }
        }
        {
            List<TimeOrdinalEraPropertyType> lhsMember;
            lhsMember = this.getMember();
            List<TimeOrdinalEraPropertyType> rhsMember;
            rhsMember = that.getMember();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "member", lhsMember), LocatorUtils.property(thatLocator, "member", rhsMember), lhsMember, rhsMember)) {
                return false;
            }
        }
        {
            ReferenceType lhsGroup;
            lhsGroup = this.getGroup();
            ReferenceType rhsGroup;
            rhsGroup = that.getGroup();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "group", lhsGroup), LocatorUtils.property(thatLocator, "group", rhsGroup), lhsGroup, rhsGroup)) {
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
            List<RelatedTimeType> theRelatedTime;
            theRelatedTime = this.getRelatedTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "relatedTime", theRelatedTime), currentHashCode, theRelatedTime);
        }
        {
            TimeNodePropertyType theStart;
            theStart = this.getStart();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "start", theStart), currentHashCode, theStart);
        }
        {
            TimeNodePropertyType theEnd;
            theEnd = this.getEnd();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "end", theEnd), currentHashCode, theEnd);
        }
        {
            TimePeriodPropertyType theExtent;
            theExtent = this.getExtent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "extent", theExtent), currentHashCode, theExtent);
        }
        {
            List<TimeOrdinalEraPropertyType> theMember;
            theMember = this.getMember();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "member", theMember), currentHashCode, theMember);
        }
        {
            ReferenceType theGroup;
            theGroup = this.getGroup();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "group", theGroup), currentHashCode, theGroup);
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
        if (draftCopy instanceof TimeOrdinalEraType) {
            final TimeOrdinalEraType copy = ((TimeOrdinalEraType) draftCopy);
            if (this.isSetRelatedTime()) {
                List<RelatedTimeType> sourceRelatedTime;
                sourceRelatedTime = this.getRelatedTime();
                @SuppressWarnings("unchecked")
                List<RelatedTimeType> copyRelatedTime = ((List<RelatedTimeType> ) strategy.copy(LocatorUtils.property(locator, "relatedTime", sourceRelatedTime), sourceRelatedTime));
                copy.unsetRelatedTime();
                List<RelatedTimeType> uniqueRelatedTimel = copy.getRelatedTime();
                uniqueRelatedTimel.addAll(copyRelatedTime);
            } else {
                copy.unsetRelatedTime();
            }
            if (this.isSetStart()) {
                TimeNodePropertyType sourceStart;
                sourceStart = this.getStart();
                TimeNodePropertyType copyStart = ((TimeNodePropertyType) strategy.copy(LocatorUtils.property(locator, "start", sourceStart), sourceStart));
                copy.setStart(copyStart);
            } else {
                copy.start = null;
            }
            if (this.isSetEnd()) {
                TimeNodePropertyType sourceEnd;
                sourceEnd = this.getEnd();
                TimeNodePropertyType copyEnd = ((TimeNodePropertyType) strategy.copy(LocatorUtils.property(locator, "end", sourceEnd), sourceEnd));
                copy.setEnd(copyEnd);
            } else {
                copy.end = null;
            }
            if (this.isSetExtent()) {
                TimePeriodPropertyType sourceExtent;
                sourceExtent = this.getExtent();
                TimePeriodPropertyType copyExtent = ((TimePeriodPropertyType) strategy.copy(LocatorUtils.property(locator, "extent", sourceExtent), sourceExtent));
                copy.setExtent(copyExtent);
            } else {
                copy.extent = null;
            }
            if (this.isSetMember()) {
                List<TimeOrdinalEraPropertyType> sourceMember;
                sourceMember = this.getMember();
                @SuppressWarnings("unchecked")
                List<TimeOrdinalEraPropertyType> copyMember = ((List<TimeOrdinalEraPropertyType> ) strategy.copy(LocatorUtils.property(locator, "member", sourceMember), sourceMember));
                copy.unsetMember();
                List<TimeOrdinalEraPropertyType> uniqueMemberl = copy.getMember();
                uniqueMemberl.addAll(copyMember);
            } else {
                copy.unsetMember();
            }
            if (this.isSetGroup()) {
                ReferenceType sourceGroup;
                sourceGroup = this.getGroup();
                ReferenceType copyGroup = ((ReferenceType) strategy.copy(LocatorUtils.property(locator, "group", sourceGroup), sourceGroup));
                copy.setGroup(copyGroup);
            } else {
                copy.group = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TimeOrdinalEraType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeOrdinalEraType) {
            final TimeOrdinalEraType target = this;
            final TimeOrdinalEraType leftObject = ((TimeOrdinalEraType) left);
            final TimeOrdinalEraType rightObject = ((TimeOrdinalEraType) right);
            {
                List<RelatedTimeType> lhsRelatedTime;
                lhsRelatedTime = leftObject.getRelatedTime();
                List<RelatedTimeType> rhsRelatedTime;
                rhsRelatedTime = rightObject.getRelatedTime();
                target.unsetRelatedTime();
                List<RelatedTimeType> uniqueRelatedTimel = target.getRelatedTime();
                uniqueRelatedTimel.addAll(((List<RelatedTimeType> ) strategy.merge(LocatorUtils.property(leftLocator, "relatedTime", lhsRelatedTime), LocatorUtils.property(rightLocator, "relatedTime", rhsRelatedTime), lhsRelatedTime, rhsRelatedTime)));
            }
            {
                TimeNodePropertyType lhsStart;
                lhsStart = leftObject.getStart();
                TimeNodePropertyType rhsStart;
                rhsStart = rightObject.getStart();
                target.setStart(((TimeNodePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "start", lhsStart), LocatorUtils.property(rightLocator, "start", rhsStart), lhsStart, rhsStart)));
            }
            {
                TimeNodePropertyType lhsEnd;
                lhsEnd = leftObject.getEnd();
                TimeNodePropertyType rhsEnd;
                rhsEnd = rightObject.getEnd();
                target.setEnd(((TimeNodePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "end", lhsEnd), LocatorUtils.property(rightLocator, "end", rhsEnd), lhsEnd, rhsEnd)));
            }
            {
                TimePeriodPropertyType lhsExtent;
                lhsExtent = leftObject.getExtent();
                TimePeriodPropertyType rhsExtent;
                rhsExtent = rightObject.getExtent();
                target.setExtent(((TimePeriodPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "extent", lhsExtent), LocatorUtils.property(rightLocator, "extent", rhsExtent), lhsExtent, rhsExtent)));
            }
            {
                List<TimeOrdinalEraPropertyType> lhsMember;
                lhsMember = leftObject.getMember();
                List<TimeOrdinalEraPropertyType> rhsMember;
                rhsMember = rightObject.getMember();
                target.unsetMember();
                List<TimeOrdinalEraPropertyType> uniqueMemberl = target.getMember();
                uniqueMemberl.addAll(((List<TimeOrdinalEraPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "member", lhsMember), LocatorUtils.property(rightLocator, "member", rhsMember), lhsMember, rhsMember)));
            }
            {
                ReferenceType lhsGroup;
                lhsGroup = leftObject.getGroup();
                ReferenceType rhsGroup;
                rhsGroup = rightObject.getGroup();
                target.setGroup(((ReferenceType) strategy.merge(LocatorUtils.property(leftLocator, "group", lhsGroup), LocatorUtils.property(rightLocator, "group", rhsGroup), lhsGroup, rhsGroup)));
            }
        }
    }

    public void setRelatedTime(List<RelatedTimeType> value) {
        List<RelatedTimeType> draftl = this.getRelatedTime();
        draftl.addAll(value);
    }

    public void setMember(List<TimeOrdinalEraPropertyType> value) {
        List<TimeOrdinalEraPropertyType> draftl = this.getMember();
        draftl.addAll(value);
    }

    public TimeOrdinalEraType withRelatedTime(RelatedTimeType... values) {
        if (values!= null) {
            for (RelatedTimeType value: values) {
                getRelatedTime().add(value);
            }
        }
        return this;
    }

    public TimeOrdinalEraType withRelatedTime(Collection<RelatedTimeType> values) {
        if (values!= null) {
            getRelatedTime().addAll(values);
        }
        return this;
    }

    public TimeOrdinalEraType withStart(TimeNodePropertyType value) {
        setStart(value);
        return this;
    }

    public TimeOrdinalEraType withEnd(TimeNodePropertyType value) {
        setEnd(value);
        return this;
    }

    public TimeOrdinalEraType withExtent(TimePeriodPropertyType value) {
        setExtent(value);
        return this;
    }

    public TimeOrdinalEraType withMember(TimeOrdinalEraPropertyType... values) {
        if (values!= null) {
            for (TimeOrdinalEraPropertyType value: values) {
                getMember().add(value);
            }
        }
        return this;
    }

    public TimeOrdinalEraType withMember(Collection<TimeOrdinalEraPropertyType> values) {
        if (values!= null) {
            getMember().addAll(values);
        }
        return this;
    }

    public TimeOrdinalEraType withGroup(ReferenceType value) {
        setGroup(value);
        return this;
    }

    public TimeOrdinalEraType withRelatedTime(List<RelatedTimeType> value) {
        setRelatedTime(value);
        return this;
    }

    public TimeOrdinalEraType withMember(List<TimeOrdinalEraPropertyType> value) {
        setMember(value);
        return this;
    }

    @Override
    public TimeOrdinalEraType withRemarks(String value) {
        setRemarks(value);
        return this;
    }

    @Override
    public TimeOrdinalEraType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeOrdinalEraType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TimeOrdinalEraType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TimeOrdinalEraType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TimeOrdinalEraType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TimeOrdinalEraType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeOrdinalEraType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TimeOrdinalEraType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TimeOrdinalEraType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TimeOrdinalEraType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
