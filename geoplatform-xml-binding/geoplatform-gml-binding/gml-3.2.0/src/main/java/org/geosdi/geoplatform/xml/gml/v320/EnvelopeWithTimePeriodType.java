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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per EnvelopeWithTimePeriodType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="EnvelopeWithTimePeriodType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}EnvelopeType">
 *       <sequence>
 *         <element name="beginPosition" type="{http://www.opengis.net/gml}TimePositionType"/>
 *         <element name="endPosition" type="{http://www.opengis.net/gml}TimePositionType"/>
 *       </sequence>
 *       <attribute name="frame" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="#ISO-8601" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeWithTimePeriodType", propOrder = {
    "beginPosition",
    "endPosition"
})
public class EnvelopeWithTimePeriodType
    extends EnvelopeType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected TimePositionType beginPosition;
    @XmlElement(required = true)
    protected TimePositionType endPosition;
    @XmlAttribute(name = "frame")
    @XmlSchemaType(name = "anyURI")
    protected String frame;

    /**
     * Recupera il valore della proprietà beginPosition.
     * 
     * @return
     *     possible object is
     *     {@link TimePositionType }
     *     
     */
    public TimePositionType getBeginPosition() {
        return beginPosition;
    }

    /**
     * Imposta il valore della proprietà beginPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePositionType }
     *     
     */
    public void setBeginPosition(TimePositionType value) {
        this.beginPosition = value;
    }

    public boolean isSetBeginPosition() {
        return (this.beginPosition!= null);
    }

    /**
     * Recupera il valore della proprietà endPosition.
     * 
     * @return
     *     possible object is
     *     {@link TimePositionType }
     *     
     */
    public TimePositionType getEndPosition() {
        return endPosition;
    }

    /**
     * Imposta il valore della proprietà endPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePositionType }
     *     
     */
    public void setEndPosition(TimePositionType value) {
        this.endPosition = value;
    }

    public boolean isSetEndPosition() {
        return (this.endPosition!= null);
    }

    /**
     * Recupera il valore della proprietà frame.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrame() {
        if (frame == null) {
            return "#ISO-8601";
        } else {
            return frame;
        }
    }

    /**
     * Imposta il valore della proprietà frame.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrame(String value) {
        this.frame = value;
    }

    public boolean isSetFrame() {
        return (this.frame!= null);
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
            TimePositionType theBeginPosition;
            theBeginPosition = this.getBeginPosition();
            strategy.appendField(locator, this, "beginPosition", buffer, theBeginPosition, this.isSetBeginPosition());
        }
        {
            TimePositionType theEndPosition;
            theEndPosition = this.getEndPosition();
            strategy.appendField(locator, this, "endPosition", buffer, theEndPosition, this.isSetEndPosition());
        }
        {
            String theFrame;
            theFrame = this.getFrame();
            strategy.appendField(locator, this, "frame", buffer, theFrame, this.isSetFrame());
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
        final EnvelopeWithTimePeriodType that = ((EnvelopeWithTimePeriodType) object);
        {
            TimePositionType lhsBeginPosition;
            lhsBeginPosition = this.getBeginPosition();
            TimePositionType rhsBeginPosition;
            rhsBeginPosition = that.getBeginPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "beginPosition", lhsBeginPosition), LocatorUtils.property(thatLocator, "beginPosition", rhsBeginPosition), lhsBeginPosition, rhsBeginPosition, this.isSetBeginPosition(), that.isSetBeginPosition())) {
                return false;
            }
        }
        {
            TimePositionType lhsEndPosition;
            lhsEndPosition = this.getEndPosition();
            TimePositionType rhsEndPosition;
            rhsEndPosition = that.getEndPosition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "endPosition", lhsEndPosition), LocatorUtils.property(thatLocator, "endPosition", rhsEndPosition), lhsEndPosition, rhsEndPosition, this.isSetEndPosition(), that.isSetEndPosition())) {
                return false;
            }
        }
        {
            String lhsFrame;
            lhsFrame = this.getFrame();
            String rhsFrame;
            rhsFrame = that.getFrame();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "frame", lhsFrame), LocatorUtils.property(thatLocator, "frame", rhsFrame), lhsFrame, rhsFrame, this.isSetFrame(), that.isSetFrame())) {
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
            TimePositionType theBeginPosition;
            theBeginPosition = this.getBeginPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "beginPosition", theBeginPosition), currentHashCode, theBeginPosition, this.isSetBeginPosition());
        }
        {
            TimePositionType theEndPosition;
            theEndPosition = this.getEndPosition();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "endPosition", theEndPosition), currentHashCode, theEndPosition, this.isSetEndPosition());
        }
        {
            String theFrame;
            theFrame = this.getFrame();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "frame", theFrame), currentHashCode, theFrame, this.isSetFrame());
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
        if (draftCopy instanceof EnvelopeWithTimePeriodType) {
            final EnvelopeWithTimePeriodType copy = ((EnvelopeWithTimePeriodType) draftCopy);
            {
                java.lang.Boolean beginPositionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBeginPosition());
                if (beginPositionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType sourceBeginPosition;
                    sourceBeginPosition = this.getBeginPosition();
                    TimePositionType copyBeginPosition = ((TimePositionType) strategy.copy(LocatorUtils.property(locator, "beginPosition", sourceBeginPosition), sourceBeginPosition, this.isSetBeginPosition()));
                    copy.setBeginPosition(copyBeginPosition);
                } else {
                    if (beginPositionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.beginPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean endPositionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEndPosition());
                if (endPositionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType sourceEndPosition;
                    sourceEndPosition = this.getEndPosition();
                    TimePositionType copyEndPosition = ((TimePositionType) strategy.copy(LocatorUtils.property(locator, "endPosition", sourceEndPosition), sourceEndPosition, this.isSetEndPosition()));
                    copy.setEndPosition(copyEndPosition);
                } else {
                    if (endPositionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.endPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean frameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFrame());
                if (frameShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceFrame;
                    sourceFrame = this.getFrame();
                    String copyFrame = ((String) strategy.copy(LocatorUtils.property(locator, "frame", sourceFrame), sourceFrame, this.isSetFrame()));
                    copy.setFrame(copyFrame);
                } else {
                    if (frameShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.frame = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new EnvelopeWithTimePeriodType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof EnvelopeWithTimePeriodType) {
            final EnvelopeWithTimePeriodType target = this;
            final EnvelopeWithTimePeriodType leftObject = ((EnvelopeWithTimePeriodType) left);
            final EnvelopeWithTimePeriodType rightObject = ((EnvelopeWithTimePeriodType) right);
            {
                java.lang.Boolean beginPositionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBeginPosition(), rightObject.isSetBeginPosition());
                if (beginPositionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType lhsBeginPosition;
                    lhsBeginPosition = leftObject.getBeginPosition();
                    TimePositionType rhsBeginPosition;
                    rhsBeginPosition = rightObject.getBeginPosition();
                    TimePositionType mergedBeginPosition = ((TimePositionType) strategy.merge(LocatorUtils.property(leftLocator, "beginPosition", lhsBeginPosition), LocatorUtils.property(rightLocator, "beginPosition", rhsBeginPosition), lhsBeginPosition, rhsBeginPosition, leftObject.isSetBeginPosition(), rightObject.isSetBeginPosition()));
                    target.setBeginPosition(mergedBeginPosition);
                } else {
                    if (beginPositionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.beginPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean endPositionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEndPosition(), rightObject.isSetEndPosition());
                if (endPositionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimePositionType lhsEndPosition;
                    lhsEndPosition = leftObject.getEndPosition();
                    TimePositionType rhsEndPosition;
                    rhsEndPosition = rightObject.getEndPosition();
                    TimePositionType mergedEndPosition = ((TimePositionType) strategy.merge(LocatorUtils.property(leftLocator, "endPosition", lhsEndPosition), LocatorUtils.property(rightLocator, "endPosition", rhsEndPosition), lhsEndPosition, rhsEndPosition, leftObject.isSetEndPosition(), rightObject.isSetEndPosition()));
                    target.setEndPosition(mergedEndPosition);
                } else {
                    if (endPositionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.endPosition = null;
                    }
                }
            }
            {
                java.lang.Boolean frameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFrame(), rightObject.isSetFrame());
                if (frameShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsFrame;
                    lhsFrame = leftObject.getFrame();
                    String rhsFrame;
                    rhsFrame = rightObject.getFrame();
                    String mergedFrame = ((String) strategy.merge(LocatorUtils.property(leftLocator, "frame", lhsFrame), LocatorUtils.property(rightLocator, "frame", rhsFrame), lhsFrame, rhsFrame, leftObject.isSetFrame(), rightObject.isSetFrame()));
                    target.setFrame(mergedFrame);
                } else {
                    if (frameShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.frame = null;
                    }
                }
            }
        }
    }

}
