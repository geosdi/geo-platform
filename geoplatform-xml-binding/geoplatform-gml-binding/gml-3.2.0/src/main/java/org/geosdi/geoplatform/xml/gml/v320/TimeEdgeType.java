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
import jakarta.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per TimeEdgeType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeEdgeType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractTimeTopologyPrimitiveType">
 *       <sequence>
 *         <element name="start" type="{http://www.opengis.net/gml}TimeNodePropertyType"/>
 *         <element name="end" type="{http://www.opengis.net/gml}TimeNodePropertyType"/>
 *         <element name="extent" type="{http://www.opengis.net/gml}TimePeriodPropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeEdgeType", propOrder = {
    "start",
    "end",
    "extent"
})
public class TimeEdgeType
    extends AbstractTimeTopologyPrimitiveType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected TimeNodePropertyType start;
    @XmlElement(required = true)
    protected TimeNodePropertyType end;
    protected TimePeriodPropertyType extent;

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
            TimeNodePropertyType theStart;
            theStart = this.getStart();
            strategy.appendField(locator, this, "start", buffer, theStart, this.isSetStart());
        }
        {
            TimeNodePropertyType theEnd;
            theEnd = this.getEnd();
            strategy.appendField(locator, this, "end", buffer, theEnd, this.isSetEnd());
        }
        {
            TimePeriodPropertyType theExtent;
            theExtent = this.getExtent();
            strategy.appendField(locator, this, "extent", buffer, theExtent, this.isSetExtent());
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TimeEdgeType that = ((TimeEdgeType) object);
        {
            TimeNodePropertyType lhsStart;
            lhsStart = this.getStart();
            TimeNodePropertyType rhsStart;
            rhsStart = that.getStart();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "start", lhsStart), LocatorUtils.property(thatLocator, "start", rhsStart), lhsStart, rhsStart, this.isSetStart(), that.isSetStart())) {
                return false;
            }
        }
        {
            TimeNodePropertyType lhsEnd;
            lhsEnd = this.getEnd();
            TimeNodePropertyType rhsEnd;
            rhsEnd = that.getEnd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "end", lhsEnd), LocatorUtils.property(thatLocator, "end", rhsEnd), lhsEnd, rhsEnd, this.isSetEnd(), that.isSetEnd())) {
                return false;
            }
        }
        {
            TimePeriodPropertyType lhsExtent;
            lhsExtent = this.getExtent();
            TimePeriodPropertyType rhsExtent;
            rhsExtent = that.getExtent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "extent", lhsExtent), LocatorUtils.property(thatLocator, "extent", rhsExtent), lhsExtent, rhsExtent, this.isSetExtent(), that.isSetExtent())) {
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
            TimeNodePropertyType theStart;
            theStart = this.getStart();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "start", theStart), currentHashCode, theStart, this.isSetStart());
        }
        {
            TimeNodePropertyType theEnd;
            theEnd = this.getEnd();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "end", theEnd), currentHashCode, theEnd, this.isSetEnd());
        }
        {
            TimePeriodPropertyType theExtent;
            theExtent = this.getExtent();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "extent", theExtent), currentHashCode, theExtent, this.isSetExtent());
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
        if (draftCopy instanceof TimeEdgeType) {
            final TimeEdgeType copy = ((TimeEdgeType) draftCopy);
            {
                java.lang.Boolean startShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetStart());
                if (startShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeNodePropertyType sourceStart;
                    sourceStart = this.getStart();
                    TimeNodePropertyType copyStart = ((TimeNodePropertyType) strategy.copy(LocatorUtils.property(locator, "start", sourceStart), sourceStart, this.isSetStart()));
                    copy.setStart(copyStart);
                } else {
                    if (startShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.start = null;
                    }
                }
            }
            {
                java.lang.Boolean endShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEnd());
                if (endShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeNodePropertyType sourceEnd;
                    sourceEnd = this.getEnd();
                    TimeNodePropertyType copyEnd = ((TimeNodePropertyType) strategy.copy(LocatorUtils.property(locator, "end", sourceEnd), sourceEnd, this.isSetEnd()));
                    copy.setEnd(copyEnd);
                } else {
                    if (endShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.end = null;
                    }
                }
            }
            {
                java.lang.Boolean extentShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetExtent());
                if (extentShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimePeriodPropertyType sourceExtent;
                    sourceExtent = this.getExtent();
                    TimePeriodPropertyType copyExtent = ((TimePeriodPropertyType) strategy.copy(LocatorUtils.property(locator, "extent", sourceExtent), sourceExtent, this.isSetExtent()));
                    copy.setExtent(copyExtent);
                } else {
                    if (extentShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.extent = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimeEdgeType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeEdgeType) {
            final TimeEdgeType target = this;
            final TimeEdgeType leftObject = ((TimeEdgeType) left);
            final TimeEdgeType rightObject = ((TimeEdgeType) right);
            {
                java.lang.Boolean startShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetStart(), rightObject.isSetStart());
                if (startShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeNodePropertyType lhsStart;
                    lhsStart = leftObject.getStart();
                    TimeNodePropertyType rhsStart;
                    rhsStart = rightObject.getStart();
                    TimeNodePropertyType mergedStart = ((TimeNodePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "start", lhsStart), LocatorUtils.property(rightLocator, "start", rhsStart), lhsStart, rhsStart, leftObject.isSetStart(), rightObject.isSetStart()));
                    target.setStart(mergedStart);
                } else {
                    if (startShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.start = null;
                    }
                }
            }
            {
                java.lang.Boolean endShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEnd(), rightObject.isSetEnd());
                if (endShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeNodePropertyType lhsEnd;
                    lhsEnd = leftObject.getEnd();
                    TimeNodePropertyType rhsEnd;
                    rhsEnd = rightObject.getEnd();
                    TimeNodePropertyType mergedEnd = ((TimeNodePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "end", lhsEnd), LocatorUtils.property(rightLocator, "end", rhsEnd), lhsEnd, rhsEnd, leftObject.isSetEnd(), rightObject.isSetEnd()));
                    target.setEnd(mergedEnd);
                } else {
                    if (endShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.end = null;
                    }
                }
            }
            {
                java.lang.Boolean extentShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetExtent(), rightObject.isSetExtent());
                if (extentShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimePeriodPropertyType lhsExtent;
                    lhsExtent = leftObject.getExtent();
                    TimePeriodPropertyType rhsExtent;
                    rhsExtent = rightObject.getExtent();
                    TimePeriodPropertyType mergedExtent = ((TimePeriodPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "extent", lhsExtent), LocatorUtils.property(rightLocator, "extent", rhsExtent), lhsExtent, rhsExtent, leftObject.isSetExtent(), rightObject.isSetExtent()));
                    target.setExtent(mergedExtent);
                } else {
                    if (extentShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.extent = null;
                    }
                }
            }
        }
    }

}
