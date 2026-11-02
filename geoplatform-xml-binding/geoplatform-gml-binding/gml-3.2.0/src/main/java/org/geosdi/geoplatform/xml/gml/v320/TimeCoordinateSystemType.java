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
 * <p>Classe Java per TimeCoordinateSystemType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="TimeCoordinateSystemType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}TimeReferenceSystemType">
 *       <sequence>
 *         <choice>
 *           <element name="originPosition" type="{http://www.opengis.net/gml}TimePositionType"/>
 *           <element name="origin" type="{http://www.opengis.net/gml}TimeInstantPropertyType"/>
 *         </choice>
 *         <element name="interval" type="{http://www.opengis.net/gml}TimeIntervalLengthType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeCoordinateSystemType", propOrder = {
    "originPosition",
    "origin",
    "interval"
})
public class TimeCoordinateSystemType
    extends TimeReferenceSystemType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected TimePositionType originPosition;
    protected TimeInstantPropertyType origin;
    @XmlElement(required = true)
    protected TimeIntervalLengthType interval;

    /**
     * Recupera il valore della proprietà originPosition.
     * 
     * @return
     *     possible object is
     *     {@link TimePositionType }
     *     
     */
    public TimePositionType getOriginPosition() {
        return originPosition;
    }

    /**
     * Imposta il valore della proprietà originPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePositionType }
     *     
     */
    public void setOriginPosition(TimePositionType value) {
        this.originPosition = value;
    }

    public boolean isSetOriginPosition() {
        return (this.originPosition!= null);
    }

    /**
     * Recupera il valore della proprietà origin.
     * 
     * @return
     *     possible object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public TimeInstantPropertyType getOrigin() {
        return origin;
    }

    /**
     * Imposta il valore della proprietà origin.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeInstantPropertyType }
     *     
     */
    public void setOrigin(TimeInstantPropertyType value) {
        this.origin = value;
    }

    public boolean isSetOrigin() {
        return (this.origin!= null);
    }

    /**
     * Recupera il valore della proprietà interval.
     * 
     * @return
     *     possible object is
     *     {@link TimeIntervalLengthType }
     *     
     */
    public TimeIntervalLengthType getInterval() {
        return interval;
    }

    /**
     * Imposta il valore della proprietà interval.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeIntervalLengthType }
     *     
     */
    public void setInterval(TimeIntervalLengthType value) {
        this.interval = value;
    }

    public boolean isSetInterval() {
        return (this.interval!= null);
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
            TimePositionType theOriginPosition;
            theOriginPosition = this.getOriginPosition();
            strategy.appendField(locator, this, "originPosition", buffer, theOriginPosition, this.isSetOriginPosition());
        }
        {
            TimeInstantPropertyType theOrigin;
            theOrigin = this.getOrigin();
            strategy.appendField(locator, this, "origin", buffer, theOrigin, this.isSetOrigin());
        }
        {
            TimeIntervalLengthType theInterval;
            theInterval = this.getInterval();
            strategy.appendField(locator, this, "interval", buffer, theInterval, this.isSetInterval());
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
        final TimeCoordinateSystemType that = ((TimeCoordinateSystemType) object);
        {
            TimePositionType lhsOriginPosition;
            lhsOriginPosition = this.getOriginPosition();
            TimePositionType rhsOriginPosition;
            rhsOriginPosition = that.getOriginPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "originPosition", lhsOriginPosition), LocatorUtils.property(thatLocator, "originPosition", rhsOriginPosition), lhsOriginPosition, rhsOriginPosition, this.isSetOriginPosition(), that.isSetOriginPosition())) {
                return false;
            }
        }
        {
            TimeInstantPropertyType lhsOrigin;
            lhsOrigin = this.getOrigin();
            TimeInstantPropertyType rhsOrigin;
            rhsOrigin = that.getOrigin();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "origin", lhsOrigin), LocatorUtils.property(thatLocator, "origin", rhsOrigin), lhsOrigin, rhsOrigin, this.isSetOrigin(), that.isSetOrigin())) {
                return false;
            }
        }
        {
            TimeIntervalLengthType lhsInterval;
            lhsInterval = this.getInterval();
            TimeIntervalLengthType rhsInterval;
            rhsInterval = that.getInterval();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "interval", lhsInterval), LocatorUtils.property(thatLocator, "interval", rhsInterval), lhsInterval, rhsInterval, this.isSetInterval(), that.isSetInterval())) {
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
            TimePositionType theOriginPosition;
            theOriginPosition = this.getOriginPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "originPosition", theOriginPosition), currentHashCode, theOriginPosition, this.isSetOriginPosition());
        }
        {
            TimeInstantPropertyType theOrigin;
            theOrigin = this.getOrigin();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "origin", theOrigin), currentHashCode, theOrigin, this.isSetOrigin());
        }
        {
            TimeIntervalLengthType theInterval;
            theInterval = this.getInterval();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "interval", theInterval), currentHashCode, theInterval, this.isSetInterval());
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
        if (draftCopy instanceof TimeCoordinateSystemType) {
            final TimeCoordinateSystemType copy = ((TimeCoordinateSystemType) draftCopy);
            {
                java.lang.Boolean originPositionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOriginPosition());
                if (originPositionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType sourceOriginPosition;
                    sourceOriginPosition = this.getOriginPosition();
                    TimePositionType copyOriginPosition = ((TimePositionType) strategy.copy(LocatorUtils.property(locator, "originPosition", sourceOriginPosition), sourceOriginPosition, this.isSetOriginPosition()));
                    copy.setOriginPosition(copyOriginPosition);
                } else {
                    if (originPositionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.originPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean originShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOrigin());
                if (originShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeInstantPropertyType sourceOrigin;
                    sourceOrigin = this.getOrigin();
                    TimeInstantPropertyType copyOrigin = ((TimeInstantPropertyType) strategy.copy(LocatorUtils.property(locator, "origin", sourceOrigin), sourceOrigin, this.isSetOrigin()));
                    copy.setOrigin(copyOrigin);
                } else {
                    if (originShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.origin = null;
                    }
                }
            }
            {
                java.lang.Boolean intervalShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetInterval());
                if (intervalShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimeIntervalLengthType sourceInterval;
                    sourceInterval = this.getInterval();
                    TimeIntervalLengthType copyInterval = ((TimeIntervalLengthType) strategy.copy(LocatorUtils.property(locator, "interval", sourceInterval), sourceInterval, this.isSetInterval()));
                    copy.setInterval(copyInterval);
                } else {
                    if (intervalShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.interval = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new TimeCoordinateSystemType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof TimeCoordinateSystemType) {
            final TimeCoordinateSystemType target = this;
            final TimeCoordinateSystemType leftObject = ((TimeCoordinateSystemType) left);
            final TimeCoordinateSystemType rightObject = ((TimeCoordinateSystemType) right);
            {
                java.lang.Boolean originPositionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOriginPosition(), rightObject.isSetOriginPosition());
                if (originPositionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType lhsOriginPosition;
                    lhsOriginPosition = leftObject.getOriginPosition();
                    TimePositionType rhsOriginPosition;
                    rhsOriginPosition = rightObject.getOriginPosition();
                    TimePositionType mergedOriginPosition = ((TimePositionType) strategy.merge(LocatorUtils.property(leftLocator, "originPosition", lhsOriginPosition), LocatorUtils.property(rightLocator, "originPosition", rhsOriginPosition), lhsOriginPosition, rhsOriginPosition, leftObject.isSetOriginPosition(), rightObject.isSetOriginPosition()));
                    target.setOriginPosition(mergedOriginPosition);
                } else {
                    if (originPositionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.originPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean originShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOrigin(), rightObject.isSetOrigin());
                if (originShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeInstantPropertyType lhsOrigin;
                    lhsOrigin = leftObject.getOrigin();
                    TimeInstantPropertyType rhsOrigin;
                    rhsOrigin = rightObject.getOrigin();
                    TimeInstantPropertyType mergedOrigin = ((TimeInstantPropertyType) strategy.merge(LocatorUtils.property(leftLocator, "origin", lhsOrigin), LocatorUtils.property(rightLocator, "origin", rhsOrigin), lhsOrigin, rhsOrigin, leftObject.isSetOrigin(), rightObject.isSetOrigin()));
                    target.setOrigin(mergedOrigin);
                } else {
                    if (originShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.origin = null;
                    }
                }
            }
            {
                java.lang.Boolean intervalShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetInterval(), rightObject.isSetInterval());
                if (intervalShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimeIntervalLengthType lhsInterval;
                    lhsInterval = leftObject.getInterval();
                    TimeIntervalLengthType rhsInterval;
                    rhsInterval = rightObject.getInterval();
                    TimeIntervalLengthType mergedInterval = ((TimeIntervalLengthType) strategy.merge(LocatorUtils.property(leftLocator, "interval", lhsInterval), LocatorUtils.property(rightLocator, "interval", rhsInterval), lhsInterval, rhsInterval, leftObject.isSetInterval(), rightObject.isSetInterval()));
                    target.setInterval(mergedInterval);
                } else {
                    if (intervalShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.interval = null;
                    }
                }
            }
        }
    }

}
