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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlList;
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
 * <p>Classe Java per BoundingShapeType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="BoundingShapeType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml}Envelope"/>
 *           <element ref="{http://www.opengis.net/gml}Null"/>
 *         </choice>
 *       </sequence>
 *       <attribute name="nilReason" type="{http://www.opengis.net/gml}NilReasonType" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoundingShapeType", propOrder = {
    "envelope",
    "_null"
})
public class BoundingShapeType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * Envelope defines an extent using a pair of positions defining opposite corners in arbitrary dimensions. The first direct position is the "lower corner" (a coordinate position consisting of all the minimal ordinates for each dimension for all points within the envelope), the second one the "upper corner" (a coordinate position consisting of all the maximal ordinates for each dimension for all points within the envelope).
     * The use of the properties “coordinates” and “pos” has been deprecated. The explicitly named properties “lowerCorner” and “upperCorner” shall be used instead.
     * 
     */
    @XmlElementRef(name = "Envelope", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<? extends EnvelopeType> envelope;
    @XmlList
    @XmlElement(name = "Null")
    protected List<String> _null;
    @XmlAttribute(name = "nilReason")
    protected List<String> nilReason;

    /**
     * Envelope defines an extent using a pair of positions defining opposite corners in arbitrary dimensions. The first direct position is the "lower corner" (a coordinate position consisting of all the minimal ordinates for each dimension for all points within the envelope), the second one the "upper corner" (a coordinate position consisting of all the maximal ordinates for each dimension for all points within the envelope).
     * The use of the properties “coordinates” and “pos” has been deprecated. The explicitly named properties “lowerCorner” and “upperCorner” shall be used instead.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link EnvelopeType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnvelopeWithTimePeriodType }{@code >}
     *     
     */
    public JAXBElement<? extends EnvelopeType> getEnvelope() {
        return envelope;
    }

    /**
     * Imposta il valore della proprietà envelope.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link EnvelopeType }{@code >}
     *     {@link JAXBElement }{@code <}{@link EnvelopeWithTimePeriodType }{@code >}
     *     
     * @see #getEnvelope()
     */
    public void setEnvelope(JAXBElement<? extends EnvelopeType> value) {
        this.envelope = value;
    }

    public boolean isSetEnvelope() {
        return (this.envelope!= null);
    }

    /**
     * Gets the value of the null property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the null property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getNull().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the null property.
     */
    public List<String> getNull() {
        if (_null == null) {
            _null = new ArrayList<>();
        }
        return this._null;
    }

    public boolean isSetNull() {
        return ((this._null!= null)&&(!this._null.isEmpty()));
    }

    public void unsetNull() {
        this._null = null;
    }

    /**
     * Gets the value of the nilReason property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the nilReason property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getNilReason().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the nilReason property.
     */
    public List<String> getNilReason() {
        if (nilReason == null) {
            nilReason = new ArrayList<>();
        }
        return this.nilReason;
    }

    public boolean isSetNilReason() {
        return ((this.nilReason!= null)&&(!this.nilReason.isEmpty()));
    }

    public void unsetNilReason() {
        this.nilReason = null;
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
            JAXBElement<? extends EnvelopeType> theEnvelope;
            theEnvelope = this.getEnvelope();
            strategy.appendField(locator, this, "envelope", buffer, theEnvelope, this.isSetEnvelope());
        }
        {
            List<String> theNull;
            theNull = (this.isSetNull()?this.getNull():null);
            strategy.appendField(locator, this, "_null", buffer, theNull, this.isSetNull());
        }
        {
            List<String> theNilReason;
            theNilReason = (this.isSetNilReason()?this.getNilReason():null);
            strategy.appendField(locator, this, "nilReason", buffer, theNilReason, this.isSetNilReason());
        }
        return buffer;
    }

    public void setNull(List<String> value) {
        this._null = null;
        if (value!= null) {
            List<String> draftl = this.getNull();
            draftl.addAll(value);
        }
    }

    public void setNilReason(List<String> value) {
        this.nilReason = null;
        if (value!= null) {
            List<String> draftl = this.getNilReason();
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
        final BoundingShapeType that = ((BoundingShapeType) object);
        {
            JAXBElement<? extends EnvelopeType> lhsEnvelope;
            lhsEnvelope = this.getEnvelope();
            JAXBElement<? extends EnvelopeType> rhsEnvelope;
            rhsEnvelope = that.getEnvelope();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "envelope", lhsEnvelope), LocatorUtils.property(thatLocator, "envelope", rhsEnvelope), lhsEnvelope, rhsEnvelope, this.isSetEnvelope(), that.isSetEnvelope())) {
                return false;
            }
        }
        {
            List<String> lhsNull;
            lhsNull = (this.isSetNull()?this.getNull():null);
            List<String> rhsNull;
            rhsNull = (that.isSetNull()?that.getNull():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "_null", lhsNull), LocatorUtils.property(thatLocator, "_null", rhsNull), lhsNull, rhsNull, this.isSetNull(), that.isSetNull())) {
                return false;
            }
        }
        {
            List<String> lhsNilReason;
            lhsNilReason = (this.isSetNilReason()?this.getNilReason():null);
            List<String> rhsNilReason;
            rhsNilReason = (that.isSetNilReason()?that.getNilReason():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nilReason", lhsNilReason), LocatorUtils.property(thatLocator, "nilReason", rhsNilReason), lhsNilReason, rhsNilReason, this.isSetNilReason(), that.isSetNilReason())) {
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
            JAXBElement<? extends EnvelopeType> theEnvelope;
            theEnvelope = this.getEnvelope();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "envelope", theEnvelope), currentHashCode, theEnvelope, this.isSetEnvelope());
        }
        {
            List<String> theNull;
            theNull = (this.isSetNull()?this.getNull():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "_null", theNull), currentHashCode, theNull, this.isSetNull());
        }
        {
            List<String> theNilReason;
            theNilReason = (this.isSetNilReason()?this.getNilReason():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "nilReason", theNilReason), currentHashCode, theNilReason, this.isSetNilReason());
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
        if (draftCopy instanceof BoundingShapeType) {
            final BoundingShapeType copy = ((BoundingShapeType) draftCopy);
            {
                java.lang.Boolean envelopeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetEnvelope());
                if (envelopeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends EnvelopeType> sourceEnvelope;
                    sourceEnvelope = this.getEnvelope();
                    @SuppressWarnings("unchecked")
                    JAXBElement<? extends EnvelopeType> copyEnvelope = ((JAXBElement<? extends EnvelopeType> ) strategy.copy(LocatorUtils.property(locator, "envelope", sourceEnvelope), sourceEnvelope, this.isSetEnvelope()));
                    copy.setEnvelope(copyEnvelope);
                } else {
                    if (envelopeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.envelope = null;
                    }
                }
            }
            {
                java.lang.Boolean _nullShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNull());
                if (_nullShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceNull;
                    sourceNull = (this.isSetNull()?this.getNull():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyNull = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "_null", sourceNull), sourceNull, this.isSetNull()));
                    copy.setNull(copyNull);
                } else {
                    if (_nullShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetNull();
                    }
                }
            }
            {
                java.lang.Boolean nilReasonShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetNilReason());
                if (nilReasonShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceNilReason;
                    sourceNilReason = (this.isSetNilReason()?this.getNilReason():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyNilReason = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "nilReason", sourceNilReason), sourceNilReason, this.isSetNilReason()));
                    copy.setNilReason(copyNilReason);
                } else {
                    if (nilReasonShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetNilReason();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new BoundingShapeType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof BoundingShapeType) {
            final BoundingShapeType target = this;
            final BoundingShapeType leftObject = ((BoundingShapeType) left);
            final BoundingShapeType rightObject = ((BoundingShapeType) right);
            {
                java.lang.Boolean envelopeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetEnvelope(), rightObject.isSetEnvelope());
                if (envelopeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<? extends EnvelopeType> lhsEnvelope;
                    lhsEnvelope = leftObject.getEnvelope();
                    JAXBElement<? extends EnvelopeType> rhsEnvelope;
                    rhsEnvelope = rightObject.getEnvelope();
                    JAXBElement<? extends EnvelopeType> mergedEnvelope = ((JAXBElement<? extends EnvelopeType> ) strategy.merge(LocatorUtils.property(leftLocator, "envelope", lhsEnvelope), LocatorUtils.property(rightLocator, "envelope", rhsEnvelope), lhsEnvelope, rhsEnvelope, leftObject.isSetEnvelope(), rightObject.isSetEnvelope()));
                    target.setEnvelope(mergedEnvelope);
                } else {
                    if (envelopeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.envelope = null;
                    }
                }
            }
            {
                java.lang.Boolean _nullShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNull(), rightObject.isSetNull());
                if (_nullShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsNull;
                    lhsNull = (leftObject.isSetNull()?leftObject.getNull():null);
                    List<String> rhsNull;
                    rhsNull = (rightObject.isSetNull()?rightObject.getNull():null);
                    List<String> mergedNull = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "_null", lhsNull), LocatorUtils.property(rightLocator, "_null", rhsNull), lhsNull, rhsNull, leftObject.isSetNull(), rightObject.isSetNull()));
                    target.setNull(mergedNull);
                } else {
                    if (_nullShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetNull();
                    }
                }
            }
            {
                java.lang.Boolean nilReasonShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetNilReason(), rightObject.isSetNilReason());
                if (nilReasonShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsNilReason;
                    lhsNilReason = (leftObject.isSetNilReason()?leftObject.getNilReason():null);
                    List<String> rhsNilReason;
                    rhsNilReason = (rightObject.isSetNilReason()?rightObject.getNilReason():null);
                    List<String> mergedNilReason = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "nilReason", lhsNilReason), LocatorUtils.property(rightLocator, "nilReason", rhsNilReason), lhsNilReason, rhsNilReason, leftObject.isSetNilReason(), rightObject.isSetNilReason()));
                    target.setNilReason(mergedNilReason);
                } else {
                    if (nilReasonShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetNilReason();
                    }
                }
            }
        }
    }

}
