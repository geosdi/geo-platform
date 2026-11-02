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
 * <p>Classe Java per TimeNodeType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeNodeType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractTimeTopologyPrimitiveType">
 *       <sequence>
 *         <element name="previousEdge" type="{http://www.opengis.net/gml}TimeEdgePropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="nextEdge" type="{http://www.opengis.net/gml}TimeEdgePropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="position" type="{http://www.opengis.net/gml}TimeInstantPropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the previousEdge property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPreviousEdge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeEdgePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the previousEdge property.
     */
    public List<TimeEdgePropertyType> getPreviousEdge() {
        if (previousEdge == null) {
            previousEdge = new ArrayList<>();
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
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the nextEdge property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getNextEdge().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeEdgePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the nextEdge property.
     */
    public List<TimeEdgePropertyType> getNextEdge() {
        if (nextEdge == null) {
            nextEdge = new ArrayList<>();
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
            List<TimeEdgePropertyType> thePreviousEdge;
            thePreviousEdge = (this.isSetPreviousEdge()?this.getPreviousEdge():null);
            strategy.appendField(locator, this, "previousEdge", buffer, thePreviousEdge, this.isSetPreviousEdge());
        }
        {
            List<TimeEdgePropertyType> theNextEdge;
            theNextEdge = (this.isSetNextEdge()?this.getNextEdge():null);
            strategy.appendField(locator, this, "nextEdge", buffer, theNextEdge, this.isSetNextEdge());
        }
        {
            TimeInstantPropertyType thePosition;
            thePosition = this.getPosition();
            strategy.appendField(locator, this, "position", buffer, thePosition, this.isSetPosition());
        }
        return buffer;
    }

    public void setPreviousEdge(List<TimeEdgePropertyType> value) {
        this.previousEdge = null;
        if (value!= null) {
            List<TimeEdgePropertyType> draftl = this.getPreviousEdge();
            draftl.addAll(value);
        }
    }

    public void setNextEdge(List<TimeEdgePropertyType> value) {
        this.nextEdge = null;
        if (value!= null) {
            List<TimeEdgePropertyType> draftl = this.getNextEdge();
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
        final TimeNodeType that = ((TimeNodeType) object);
        {
            List<TimeEdgePropertyType> lhsPreviousEdge;
            lhsPreviousEdge = (this.isSetPreviousEdge()?this.getPreviousEdge():null);
            List<TimeEdgePropertyType> rhsPreviousEdge;
            rhsPreviousEdge = (that.isSetPreviousEdge()?that.getPreviousEdge():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "previousEdge", lhsPreviousEdge), LocatorUtils.property(thatLocator, "previousEdge", rhsPreviousEdge), lhsPreviousEdge, rhsPreviousEdge, this.isSetPreviousEdge(), that.isSetPreviousEdge())) {
                return false;
            }
        }
        {
            List<TimeEdgePropertyType> lhsNextEdge;
            lhsNextEdge = (this.isSetNextEdge()?this.getNextEdge():null);
            List<TimeEdgePropertyType> rhsNextEdge;
            rhsNextEdge = (that.isSetNextEdge()?that.getNextEdge():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nextEdge", lhsNextEdge), LocatorUtils.property(thatLocator, "nextEdge", rhsNextEdge), lhsNextEdge, rhsNextEdge, this.isSetNextEdge(), that.isSetNextEdge())) {
                return false;
            }
        }
        {
            TimeInstantPropertyType lhsPosition;
            lhsPosition = this.getPosition();
            TimeInstantPropertyType rhsPosition;
            rhsPosition = that.getPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "position", lhsPosition), LocatorUtils.property(thatLocator, "position", rhsPosition), lhsPosition, rhsPosition, this.isSetPosition(), that.isSetPosition())) {
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
            List<TimeEdgePropertyType> thePreviousEdge;
            thePreviousEdge = (this.isSetPreviousEdge()?this.getPreviousEdge():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "previousEdge", thePreviousEdge), currentHashCode, thePreviousEdge, this.isSetPreviousEdge());
        }
        {
            List<TimeEdgePropertyType> theNextEdge;
            theNextEdge = (this.isSetNextEdge()?this.getNextEdge():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nextEdge", theNextEdge), currentHashCode, theNextEdge, this.isSetNextEdge());
        }
        {
            TimeInstantPropertyType thePosition;
            thePosition = this.getPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "position", thePosition), currentHashCode, thePosition, this.isSetPosition());
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
        if (draftCopy instanceof TimeNodeType) {
            final TimeNodeType copy = ((TimeNodeType) draftCopy);
            {
                java.lang.Boolean previousEdgeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPreviousEdge());
                if (previousEdgeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeEdgePropertyType> sourcePreviousEdge;
                    sourcePreviousEdge = (this.isSetPreviousEdge()?this.getPreviousEdge():null);
                    @SuppressWarnings("unchecked")
                    List<TimeEdgePropertyType> copyPreviousEdge = ((List<TimeEdgePropertyType> ) strategy.copy(LocatorUtils.property(locator, "previousEdge", sourcePreviousEdge), sourcePreviousEdge, this.isSetPreviousEdge()));
                    copy.setPreviousEdge(copyPreviousEdge);
                } else {
                    if (previousEdgeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetPreviousEdge();
                    }
                }
            }
            {
                java.lang.Boolean nextEdgeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNextEdge());
                if (nextEdgeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeEdgePropertyType> sourceNextEdge;
                    sourceNextEdge = (this.isSetNextEdge()?this.getNextEdge():null);
                    @SuppressWarnings("unchecked")
                    List<TimeEdgePropertyType> copyNextEdge = ((List<TimeEdgePropertyType> ) strategy.copy(LocatorUtils.property(locator, "nextEdge", sourceNextEdge), sourceNextEdge, this.isSetNextEdge()));
                    copy.setNextEdge(copyNextEdge);
                } else {
                    if (nextEdgeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetNextEdge();
                    }
                }
            }
            {
                java.lang.Boolean positionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetPosition());
                if (positionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeInstantPropertyType sourcePosition;
                    sourcePosition = this.getPosition();
                    TimeInstantPropertyType copyPosition = ((TimeInstantPropertyType) strategy.copy(LocatorUtils.property(locator, "position", sourcePosition), sourcePosition, this.isSetPosition()));
                    copy.setPosition(copyPosition);
                } else {
                    if (positionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.position = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimeNodeType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeNodeType) {
            final TimeNodeType target = this;
            final TimeNodeType leftObject = ((TimeNodeType) left);
            final TimeNodeType rightObject = ((TimeNodeType) right);
            {
                java.lang.Boolean previousEdgeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPreviousEdge(), rightObject.isSetPreviousEdge());
                if (previousEdgeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeEdgePropertyType> lhsPreviousEdge;
                    lhsPreviousEdge = (leftObject.isSetPreviousEdge()?leftObject.getPreviousEdge():null);
                    List<TimeEdgePropertyType> rhsPreviousEdge;
                    rhsPreviousEdge = (rightObject.isSetPreviousEdge()?rightObject.getPreviousEdge():null);
                    List<TimeEdgePropertyType> mergedPreviousEdge = ((List<TimeEdgePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "previousEdge", lhsPreviousEdge), LocatorUtils.property(rightLocator, "previousEdge", rhsPreviousEdge), lhsPreviousEdge, rhsPreviousEdge, leftObject.isSetPreviousEdge(), rightObject.isSetPreviousEdge()));
                    target.setPreviousEdge(mergedPreviousEdge);
                } else {
                    if (previousEdgeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetPreviousEdge();
                    }
                }
            }
            {
                java.lang.Boolean nextEdgeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNextEdge(), rightObject.isSetNextEdge());
                if (nextEdgeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<TimeEdgePropertyType> lhsNextEdge;
                    lhsNextEdge = (leftObject.isSetNextEdge()?leftObject.getNextEdge():null);
                    List<TimeEdgePropertyType> rhsNextEdge;
                    rhsNextEdge = (rightObject.isSetNextEdge()?rightObject.getNextEdge():null);
                    List<TimeEdgePropertyType> mergedNextEdge = ((List<TimeEdgePropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "nextEdge", lhsNextEdge), LocatorUtils.property(rightLocator, "nextEdge", rhsNextEdge), lhsNextEdge, rhsNextEdge, leftObject.isSetNextEdge(), rightObject.isSetNextEdge()));
                    target.setNextEdge(mergedNextEdge);
                } else {
                    if (nextEdgeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetNextEdge();
                    }
                }
            }
            {
                java.lang.Boolean positionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetPosition(), rightObject.isSetPosition());
                if (positionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeInstantPropertyType lhsPosition;
                    lhsPosition = leftObject.getPosition();
                    TimeInstantPropertyType rhsPosition;
                    rhsPosition = rightObject.getPosition();
                    TimeInstantPropertyType mergedPosition = ((TimeInstantPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "position", lhsPosition), LocatorUtils.property(rightLocator, "position", rhsPosition), lhsPosition, rhsPosition, leftObject.isSetPosition(), rightObject.isSetPosition()));
                    target.setPosition(mergedPosition);
                } else {
                    if (positionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.position = null;
                    }
                }
            }
        }
    }

}
