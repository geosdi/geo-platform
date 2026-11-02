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

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Classe Java per ObservationType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ObservationType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractFeatureType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}validTime"/>
 *         <element ref="{http://www.opengis.net/gml}using" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}target" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/gml}resultOf"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObservationType", propOrder = {
    "validTime",
    "using",
    "target",
    "resultOf"
})
@XmlSeeAlso({
    DirectedObservationType.class
})
public class ObservationType
    extends AbstractFeatureType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:validTime is a convenience property element.
     * 
     */
    @XmlElement(required = true)
    protected TimePrimitivePropertyType validTime;
    protected ProcedurePropertyType using;
    @XmlElementRef(name = "target", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected JAXBElement<TargetPropertyType> target;
    @XmlElement(required = true)
    protected ResultType resultOf;

    /**
     * gml:validTime is a convenience property element.
     * 
     * @return
     *     possible object is
     *     {@link TimePrimitivePropertyType }
     *     
     */
    public TimePrimitivePropertyType getValidTime() {
        return validTime;
    }

    /**
     * Imposta il valore della proprietà validTime.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePrimitivePropertyType }
     *     
     * @see #getValidTime()
     */
    public void setValidTime(TimePrimitivePropertyType value) {
        this.validTime = value;
    }

    public boolean isSetValidTime() {
        return (this.validTime!= null);
    }

    /**
     * Recupera il valore della proprietà using.
     * 
     * @return
     *     possible object is
     *     {@link ProcedurePropertyType }
     *     
     */
    public ProcedurePropertyType getUsing() {
        return using;
    }

    /**
     * Imposta il valore della proprietà using.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcedurePropertyType }
     *     
     */
    public void setUsing(ProcedurePropertyType value) {
        this.using = value;
    }

    public boolean isSetUsing() {
        return (this.using!= null);
    }

    /**
     * Recupera il valore della proprietà target.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}
     *     
     */
    public JAXBElement<TargetPropertyType> getTarget() {
        return target;
    }

    /**
     * Imposta il valore della proprietà target.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link TargetPropertyType }{@code >}
     *     
     */
    public void setTarget(JAXBElement<TargetPropertyType> value) {
        this.target = value;
    }

    public boolean isSetTarget() {
        return (this.target!= null);
    }

    /**
     * Recupera il valore della proprietà resultOf.
     * 
     * @return
     *     possible object is
     *     {@link ResultType }
     *     
     */
    public ResultType getResultOf() {
        return resultOf;
    }

    /**
     * Imposta il valore della proprietà resultOf.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultType }
     *     
     */
    public void setResultOf(ResultType value) {
        this.resultOf = value;
    }

    public boolean isSetResultOf() {
        return (this.resultOf!= null);
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
            TimePrimitivePropertyType theValidTime;
            theValidTime = this.getValidTime();
            strategy.appendField(locator, this, "validTime", buffer, theValidTime, this.isSetValidTime());
        }
        {
            ProcedurePropertyType theUsing;
            theUsing = this.getUsing();
            strategy.appendField(locator, this, "using", buffer, theUsing, this.isSetUsing());
        }
        {
            JAXBElement<TargetPropertyType> theTarget;
            theTarget = this.getTarget();
            strategy.appendField(locator, this, "target", buffer, theTarget, this.isSetTarget());
        }
        {
            ResultType theResultOf;
            theResultOf = this.getResultOf();
            strategy.appendField(locator, this, "resultOf", buffer, theResultOf, this.isSetResultOf());
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
        final ObservationType that = ((ObservationType) object);
        {
            TimePrimitivePropertyType lhsValidTime;
            lhsValidTime = this.getValidTime();
            TimePrimitivePropertyType rhsValidTime;
            rhsValidTime = that.getValidTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "validTime", lhsValidTime), LocatorUtils.property(thatLocator, "validTime", rhsValidTime), lhsValidTime, rhsValidTime, this.isSetValidTime(), that.isSetValidTime())) {
                return false;
            }
        }
        {
            ProcedurePropertyType lhsUsing;
            lhsUsing = this.getUsing();
            ProcedurePropertyType rhsUsing;
            rhsUsing = that.getUsing();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "using", lhsUsing), LocatorUtils.property(thatLocator, "using", rhsUsing), lhsUsing, rhsUsing, this.isSetUsing(), that.isSetUsing())) {
                return false;
            }
        }
        {
            JAXBElement<TargetPropertyType> lhsTarget;
            lhsTarget = this.getTarget();
            JAXBElement<TargetPropertyType> rhsTarget;
            rhsTarget = that.getTarget();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "target", lhsTarget), LocatorUtils.property(thatLocator, "target", rhsTarget), lhsTarget, rhsTarget, this.isSetTarget(), that.isSetTarget())) {
                return false;
            }
        }
        {
            ResultType lhsResultOf;
            lhsResultOf = this.getResultOf();
            ResultType rhsResultOf;
            rhsResultOf = that.getResultOf();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "resultOf", lhsResultOf), LocatorUtils.property(thatLocator, "resultOf", rhsResultOf), lhsResultOf, rhsResultOf, this.isSetResultOf(), that.isSetResultOf())) {
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
            TimePrimitivePropertyType theValidTime;
            theValidTime = this.getValidTime();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "validTime", theValidTime), currentHashCode, theValidTime, this.isSetValidTime());
        }
        {
            ProcedurePropertyType theUsing;
            theUsing = this.getUsing();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "using", theUsing), currentHashCode, theUsing, this.isSetUsing());
        }
        {
            JAXBElement<TargetPropertyType> theTarget;
            theTarget = this.getTarget();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "target", theTarget), currentHashCode, theTarget, this.isSetTarget());
        }
        {
            ResultType theResultOf;
            theResultOf = this.getResultOf();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "resultOf", theResultOf), currentHashCode, theResultOf, this.isSetResultOf());
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
        if (draftCopy instanceof ObservationType) {
            final ObservationType copy = ((ObservationType) draftCopy);
            {
                java.lang.Boolean validTimeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValidTime());
                if (validTimeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    TimePrimitivePropertyType sourceValidTime;
                    sourceValidTime = this.getValidTime();
                    TimePrimitivePropertyType copyValidTime = ((TimePrimitivePropertyType) strategy.copy(LocatorUtils.property(locator, "validTime", sourceValidTime), sourceValidTime, this.isSetValidTime()));
                    copy.setValidTime(copyValidTime);
                } else {
                    if (validTimeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.validTime = null;
                    }
                }
            }
            {
                java.lang.Boolean usingShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUsing());
                if (usingShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ProcedurePropertyType sourceUsing;
                    sourceUsing = this.getUsing();
                    ProcedurePropertyType copyUsing = ((ProcedurePropertyType) strategy.copy(LocatorUtils.property(locator, "using", sourceUsing), sourceUsing, this.isSetUsing()));
                    copy.setUsing(copyUsing);
                } else {
                    if (usingShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.using = null;
                    }
                }
            }
            {
                java.lang.Boolean targetShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTarget());
                if (targetShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<TargetPropertyType> sourceTarget;
                    sourceTarget = this.getTarget();
                    @SuppressWarnings("unchecked")
                    JAXBElement<TargetPropertyType> copyTarget = ((JAXBElement<TargetPropertyType> ) strategy.copy(LocatorUtils.property(locator, "target", sourceTarget), sourceTarget, this.isSetTarget()));
                    copy.setTarget(copyTarget);
                } else {
                    if (targetShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.target = null;
                    }
                }
            }
            {
                java.lang.Boolean resultOfShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetResultOf());
                if (resultOfShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    ResultType sourceResultOf;
                    sourceResultOf = this.getResultOf();
                    ResultType copyResultOf = ((ResultType) strategy.copy(LocatorUtils.property(locator, "resultOf", sourceResultOf), sourceResultOf, this.isSetResultOf()));
                    copy.setResultOf(copyResultOf);
                } else {
                    if (resultOfShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.resultOf = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new ObservationType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ObservationType) {
            final ObservationType target = this;
            final ObservationType leftObject = ((ObservationType) left);
            final ObservationType rightObject = ((ObservationType) right);
            {
                java.lang.Boolean validTimeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValidTime(), rightObject.isSetValidTime());
                if (validTimeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    TimePrimitivePropertyType lhsValidTime;
                    lhsValidTime = leftObject.getValidTime();
                    TimePrimitivePropertyType rhsValidTime;
                    rhsValidTime = rightObject.getValidTime();
                    TimePrimitivePropertyType mergedValidTime = ((TimePrimitivePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "validTime", lhsValidTime), LocatorUtils.property(rightLocator, "validTime", rhsValidTime), lhsValidTime, rhsValidTime, leftObject.isSetValidTime(), rightObject.isSetValidTime()));
                    target.setValidTime(mergedValidTime);
                } else {
                    if (validTimeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.validTime = null;
                    }
                }
            }
            {
                java.lang.Boolean usingShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUsing(), rightObject.isSetUsing());
                if (usingShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ProcedurePropertyType lhsUsing;
                    lhsUsing = leftObject.getUsing();
                    ProcedurePropertyType rhsUsing;
                    rhsUsing = rightObject.getUsing();
                    ProcedurePropertyType mergedUsing = ((ProcedurePropertyType) strategy.merge(LocatorUtils.property(leftLocator, "using", lhsUsing), LocatorUtils.property(rightLocator, "using", rhsUsing), lhsUsing, rhsUsing, leftObject.isSetUsing(), rightObject.isSetUsing()));
                    target.setUsing(mergedUsing);
                } else {
                    if (usingShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.using = null;
                    }
                }
            }
            {
                java.lang.Boolean targetShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTarget(), rightObject.isSetTarget());
                if (targetShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<TargetPropertyType> lhsTarget;
                    lhsTarget = leftObject.getTarget();
                    JAXBElement<TargetPropertyType> rhsTarget;
                    rhsTarget = rightObject.getTarget();
                    JAXBElement<TargetPropertyType> mergedTarget = ((JAXBElement<TargetPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "target", lhsTarget), LocatorUtils.property(rightLocator, "target", rhsTarget), lhsTarget, rhsTarget, leftObject.isSetTarget(), rightObject.isSetTarget()));
                    target.setTarget(mergedTarget);
                } else {
                    if (targetShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.target = null;
                    }
                }
            }
            {
                java.lang.Boolean resultOfShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetResultOf(), rightObject.isSetResultOf());
                if (resultOfShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    ResultType lhsResultOf;
                    lhsResultOf = leftObject.getResultOf();
                    ResultType rhsResultOf;
                    rhsResultOf = rightObject.getResultOf();
                    ResultType mergedResultOf = ((ResultType) strategy.merge(LocatorUtils.property(leftLocator, "resultOf", lhsResultOf), LocatorUtils.property(rightLocator, "resultOf", rhsResultOf), lhsResultOf, rhsResultOf, leftObject.isSetResultOf(), rightObject.isSetResultOf()));
                    target.setResultOf(mergedResultOf);
                } else {
                    if (resultOfShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.resultOf = null;
                    }
                }
            }
        }
    }

}
