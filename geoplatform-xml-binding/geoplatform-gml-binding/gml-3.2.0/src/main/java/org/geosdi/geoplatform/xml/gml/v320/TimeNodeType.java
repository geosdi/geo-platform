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
 * <p>Classe Java per TimeNodeType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="TimeNodeType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractTimeTopologyPrimitiveType">
 *       &lt;sequence>
 *         &lt;element name="previousEdge" type="{http://www.opengis.net/gml}TimeEdgePropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nextEdge" type="{http://www.opengis.net/gml}TimeEdgePropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="position" type="{http://www.opengis.net/gml}TimeInstantPropertyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeNodeType", propOrder = {
    "previousEdge",
    "nextEdge",
    "position"
})
public class TimeNodeType
    extends AbstractTimeTopologyPrimitiveType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected List<TimeEdgePropertyType> previousEdge;
    protected List<TimeEdgePropertyType> nextEdge;
    protected TimeInstantPropertyType position;

    /**
     * Gets the value of the previousEdge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the previousEdge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreviousEdge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeEdgePropertyType }
     * 
     * 
     */
    public List<TimeEdgePropertyType> getPreviousEdge() {
        if (previousEdge == null) {
            previousEdge = new ArrayList<TimeEdgePropertyType>();
        }
        return this.previousEdge;
    }

    public boolean isSetPreviousEdge() {
        return ((this.previousEdge!= null)&&(!this.previousEdge.isEmpty()));
    }

    public void unsetPreviousEdge() {
        this.previousEdge = null;
    }

    /**
     * Gets the value of the nextEdge property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nextEdge property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNextEdge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeEdgePropertyType }
     * 
     * 
     */
    public List<TimeEdgePropertyType> getNextEdge() {
        if (nextEdge == null) {
            nextEdge = new ArrayList<TimeEdgePropertyType>();
        }
        return this.nextEdge;
    }

    public boolean isSetNextEdge() {
        return ((this.nextEdge!= null)&&(!this.nextEdge.isEmpty()));
    }

    public void unsetNextEdge() {
        this.nextEdge = null;
    }

    /**
     * Recupera il valore della proprietà position.
     * 
     * @return
     *     possible object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public TimeInstantPropertyType getPosition() {
        return position;
    }

    /**
     * Imposta il valore della proprietà position.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public void setPosition(TimeInstantPropertyType value) {
        this.position = value;
    }

    public boolean isSetPosition() {
        return (this.position!= null);
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
            List<TimeEdgePropertyType> thePreviousEdge;
            thePreviousEdge = this.getPreviousEdge();
            strategy.appendField(locator, this, "previousEdge", buffer, thePreviousEdge);
        }
        {
            List<TimeEdgePropertyType> theNextEdge;
            theNextEdge = this.getNextEdge();
            strategy.appendField(locator, this, "nextEdge", buffer, theNextEdge);
        }
        {
            TimeInstantPropertyType thePosition;
            thePosition = this.getPosition();
            strategy.appendField(locator, this, "position", buffer, thePosition);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TimeNodeType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TimeNodeType that = ((TimeNodeType) object);
        {
            List<TimeEdgePropertyType> lhsPreviousEdge;
            lhsPreviousEdge = this.getPreviousEdge();
            List<TimeEdgePropertyType> rhsPreviousEdge;
            rhsPreviousEdge = that.getPreviousEdge();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "previousEdge", lhsPreviousEdge), LocatorUtils.property(thatLocator, "previousEdge", rhsPreviousEdge), lhsPreviousEdge, rhsPreviousEdge)) {
                return false;
            }
        }
        {
            List<TimeEdgePropertyType> lhsNextEdge;
            lhsNextEdge = this.getNextEdge();
            List<TimeEdgePropertyType> rhsNextEdge;
            rhsNextEdge = that.getNextEdge();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nextEdge", lhsNextEdge), LocatorUtils.property(thatLocator, "nextEdge", rhsNextEdge), lhsNextEdge, rhsNextEdge)) {
                return false;
            }
        }
        {
            TimeInstantPropertyType lhsPosition;
            lhsPosition = this.getPosition();
            TimeInstantPropertyType rhsPosition;
            rhsPosition = that.getPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "position", lhsPosition), LocatorUtils.property(thatLocator, "position", rhsPosition), lhsPosition, rhsPosition)) {
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
            List<TimeEdgePropertyType> thePreviousEdge;
            thePreviousEdge = this.getPreviousEdge();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "previousEdge", thePreviousEdge), currentHashCode, thePreviousEdge);
        }
        {
            List<TimeEdgePropertyType> theNextEdge;
            theNextEdge = this.getNextEdge();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nextEdge", theNextEdge), currentHashCode, theNextEdge);
        }
        {
            TimeInstantPropertyType thePosition;
            thePosition = this.getPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "position", thePosition), currentHashCode, thePosition);
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
        if (draftCopy instanceof TimeNodeType) {
            final TimeNodeType copy = ((TimeNodeType) draftCopy);
            if (this.isSetPreviousEdge()) {
                List<TimeEdgePropertyType> sourcePreviousEdge;
                sourcePreviousEdge = this.getPreviousEdge();
                @SuppressWarnings("unchecked")
                List<TimeEdgePropertyType> copyPreviousEdge = ((List<TimeEdgePropertyType> ) strategy.copy(LocatorUtils.property(locator, "previousEdge", sourcePreviousEdge), sourcePreviousEdge));
                copy.unsetPreviousEdge();
                List<TimeEdgePropertyType> uniquePreviousEdgel = copy.getPreviousEdge();
                uniquePreviousEdgel.addAll(copyPreviousEdge);
            } else {
                copy.unsetPreviousEdge();
            }
            if (this.isSetNextEdge()) {
                List<TimeEdgePropertyType> sourceNextEdge;
                sourceNextEdge = this.getNextEdge();
                @SuppressWarnings("unchecked")
                List<TimeEdgePropertyType> copyNextEdge = ((List<TimeEdgePropertyType> ) strategy.copy(LocatorUtils.property(locator, "nextEdge", sourceNextEdge), sourceNextEdge));
                copy.unsetNextEdge();
                List<TimeEdgePropertyType> uniqueNextEdgel = copy.getNextEdge();
                uniqueNextEdgel.addAll(copyNextEdge);
            } else {
                copy.unsetNextEdge();
            }
            if (this.isSetPosition()) {
                TimeInstantPropertyType sourcePosition;
                sourcePosition = this.getPosition();
                TimeInstantPropertyType copyPosition = ((TimeInstantPropertyType) strategy.copy(LocatorUtils.property(locator, "position", sourcePosition), sourcePosition));
                copy.setPosition(copyPosition);
            } else {
                copy.position = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new TimeNodeType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeNodeType) {
            final TimeNodeType target = this;
            final TimeNodeType leftObject = ((TimeNodeType) left);
            final TimeNodeType rightObject = ((TimeNodeType) right);
            {
                List<TimeEdgePropertyType> lhsPreviousEdge;
                lhsPreviousEdge = leftObject.getPreviousEdge();
                List<TimeEdgePropertyType> rhsPreviousEdge;
                rhsPreviousEdge = rightObject.getPreviousEdge();
                target.unsetPreviousEdge();
                List<TimeEdgePropertyType> uniquePreviousEdgel = target.getPreviousEdge();
                uniquePreviousEdgel.addAll(((List<TimeEdgePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "previousEdge", lhsPreviousEdge), LocatorUtils.property(rightLocator, "previousEdge", rhsPreviousEdge), lhsPreviousEdge, rhsPreviousEdge)));
            }
            {
                List<TimeEdgePropertyType> lhsNextEdge;
                lhsNextEdge = leftObject.getNextEdge();
                List<TimeEdgePropertyType> rhsNextEdge;
                rhsNextEdge = rightObject.getNextEdge();
                target.unsetNextEdge();
                List<TimeEdgePropertyType> uniqueNextEdgel = target.getNextEdge();
                uniqueNextEdgel.addAll(((List<TimeEdgePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "nextEdge", lhsNextEdge), LocatorUtils.property(rightLocator, "nextEdge", rhsNextEdge), lhsNextEdge, rhsNextEdge)));
            }
            {
                TimeInstantPropertyType lhsPosition;
                lhsPosition = leftObject.getPosition();
                TimeInstantPropertyType rhsPosition;
                rhsPosition = rightObject.getPosition();
                target.setPosition(((TimeInstantPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "position", lhsPosition), LocatorUtils.property(rightLocator, "position", rhsPosition), lhsPosition, rhsPosition)));
            }
        }
    }

    public void setPreviousEdge(List<TimeEdgePropertyType> value) {
        List<TimeEdgePropertyType> draftl = this.getPreviousEdge();
        draftl.addAll(value);
    }

    public void setNextEdge(List<TimeEdgePropertyType> value) {
        List<TimeEdgePropertyType> draftl = this.getNextEdge();
        draftl.addAll(value);
    }

    public TimeNodeType withPreviousEdge(TimeEdgePropertyType... values) {
        if (values!= null) {
            for (TimeEdgePropertyType value: values) {
                getPreviousEdge().add(value);
            }
        }
        return this;
    }

    public TimeNodeType withPreviousEdge(Collection<TimeEdgePropertyType> values) {
        if (values!= null) {
            getPreviousEdge().addAll(values);
        }
        return this;
    }

    public TimeNodeType withNextEdge(TimeEdgePropertyType... values) {
        if (values!= null) {
            for (TimeEdgePropertyType value: values) {
                getNextEdge().add(value);
            }
        }
        return this;
    }

    public TimeNodeType withNextEdge(Collection<TimeEdgePropertyType> values) {
        if (values!= null) {
            getNextEdge().addAll(values);
        }
        return this;
    }

    public TimeNodeType withPosition(TimeInstantPropertyType value) {
        setPosition(value);
        return this;
    }

    public TimeNodeType withPreviousEdge(List<TimeEdgePropertyType> value) {
        setPreviousEdge(value);
        return this;
    }

    public TimeNodeType withNextEdge(List<TimeEdgePropertyType> value) {
        setNextEdge(value);
        return this;
    }

    @Override
    public TimeNodeType withComplex(ReferenceType value) {
        setComplex(value);
        return this;
    }

    @Override
    public TimeNodeType withRelatedTime(RelatedTimeType... values) {
        if (values!= null) {
            for (RelatedTimeType value: values) {
                getRelatedTime().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeNodeType withRelatedTime(Collection<RelatedTimeType> values) {
        if (values!= null) {
            getRelatedTime().addAll(values);
        }
        return this;
    }

    @Override
    public TimeNodeType withRelatedTime(List<RelatedTimeType> value) {
        setRelatedTime(value);
        return this;
    }

    @Override
    public TimeNodeType withMetaDataProperty(MetaDataPropertyType... values) {
        if (values!= null) {
            for (MetaDataPropertyType value: values) {
                getMetaDataProperty().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeNodeType withMetaDataProperty(Collection<MetaDataPropertyType> values) {
        if (values!= null) {
            getMetaDataProperty().addAll(values);
        }
        return this;
    }

    @Override
    public TimeNodeType withDescription(StringOrRefType value) {
        setDescription(value);
        return this;
    }

    @Override
    public TimeNodeType withDescriptionReference(ReferenceType value) {
        setDescriptionReference(value);
        return this;
    }

    @Override
    public TimeNodeType withIdentifier(CodeWithAuthorityType value) {
        setIdentifier(value);
        return this;
    }

    @Override
    public TimeNodeType withName(CodeType... values) {
        if (values!= null) {
            for (CodeType value: values) {
                getName().add(value);
            }
        }
        return this;
    }

    @Override
    public TimeNodeType withName(Collection<CodeType> values) {
        if (values!= null) {
            getName().addAll(values);
        }
        return this;
    }

    @Override
    public TimeNodeType withId(String value) {
        setId(value);
        return this;
    }

    @Override
    public TimeNodeType withMetaDataProperty(List<MetaDataPropertyType> value) {
        setMetaDataProperty(value);
        return this;
    }

    @Override
    public TimeNodeType withName(List<CodeType> value) {
        setName(value);
        return this;
    }

}
