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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per GridEnvelopeType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="GridEnvelopeType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="low" type="{http://www.opengis.net/gml}integerList"/>
 *         <element name="high" type="{http://www.opengis.net/gml}integerList"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GridEnvelopeType", propOrder = {
    "low",
    "high"
})
public class GridEnvelopeType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlList
    @XmlElement(required = true)
    protected List<BigInteger> low;
    @XmlList
    @XmlElement(required = true)
    protected List<BigInteger> high;

    /**
     * Gets the value of the low property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the low property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getLow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * </p>
     * 
     * 
     * @return
     *     The value of the low property.
     */
    public List<BigInteger> getLow() {
        if (low == null) {
            low = new ArrayList<>();
        }
        return this.low;
    }

    public boolean isSetLow() {
        return ((this.low!= null)&&(!this.low.isEmpty()));
    }

    public void unsetLow() {
        this.low = null;
    }

    /**
     * Gets the value of the high property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the high property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getHigh().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * </p>
     * 
     * 
     * @return
     *     The value of the high property.
     */
    public List<BigInteger> getHigh() {
        if (high == null) {
            high = new ArrayList<>();
        }
        return this.high;
    }

    public boolean isSetHigh() {
        return ((this.high!= null)&&(!this.high.isEmpty()));
    }

    public void unsetHigh() {
        this.high = null;
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
            List<BigInteger> theLow;
            theLow = (this.isSetLow()?this.getLow():null);
            strategy.appendField(locator, this, "low", buffer, theLow, this.isSetLow());
        }
        {
            List<BigInteger> theHigh;
            theHigh = (this.isSetHigh()?this.getHigh():null);
            strategy.appendField(locator, this, "high", buffer, theHigh, this.isSetHigh());
        }
        return buffer;
    }

    public void setLow(List<BigInteger> value) {
        this.low = null;
        if (value!= null) {
            List<BigInteger> draftl = this.getLow();
            draftl.addAll(value);
        }
    }

    public void setHigh(List<BigInteger> value) {
        this.high = null;
        if (value!= null) {
            List<BigInteger> draftl = this.getHigh();
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
        final GridEnvelopeType that = ((GridEnvelopeType) object);
        {
            List<BigInteger> lhsLow;
            lhsLow = (this.isSetLow()?this.getLow():null);
            List<BigInteger> rhsLow;
            rhsLow = (that.isSetLow()?that.getLow():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "low", lhsLow), LocatorUtils.property(thatLocator, "low", rhsLow), lhsLow, rhsLow, this.isSetLow(), that.isSetLow())) {
                return false;
            }
        }
        {
            List<BigInteger> lhsHigh;
            lhsHigh = (this.isSetHigh()?this.getHigh():null);
            List<BigInteger> rhsHigh;
            rhsHigh = (that.isSetHigh()?that.getHigh():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "high", lhsHigh), LocatorUtils.property(thatLocator, "high", rhsHigh), lhsHigh, rhsHigh, this.isSetHigh(), that.isSetHigh())) {
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
            List<BigInteger> theLow;
            theLow = (this.isSetLow()?this.getLow():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "low", theLow), currentHashCode, theLow, this.isSetLow());
        }
        {
            List<BigInteger> theHigh;
            theHigh = (this.isSetHigh()?this.getHigh():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "high", theHigh), currentHashCode, theHigh, this.isSetHigh());
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
        if (draftCopy instanceof GridEnvelopeType) {
            final GridEnvelopeType copy = ((GridEnvelopeType) draftCopy);
            {
                java.lang.Boolean lowShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetLow());
                if (lowShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<BigInteger> sourceLow;
                    sourceLow = (this.isSetLow()?this.getLow():null);
                    @SuppressWarnings("unchecked")
                    List<BigInteger> copyLow = ((List<BigInteger> ) strategy.copy(LocatorUtils.property(locator, "low", sourceLow), sourceLow, this.isSetLow()));
                    copy.setLow(copyLow);
                } else {
                    if (lowShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetLow();
                    }
                }
            }
            {
                java.lang.Boolean highShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetHigh());
                if (highShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<BigInteger> sourceHigh;
                    sourceHigh = (this.isSetHigh()?this.getHigh():null);
                    @SuppressWarnings("unchecked")
                    List<BigInteger> copyHigh = ((List<BigInteger> ) strategy.copy(LocatorUtils.property(locator, "high", sourceHigh), sourceHigh, this.isSetHigh()));
                    copy.setHigh(copyHigh);
                } else {
                    if (highShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetHigh();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new GridEnvelopeType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof GridEnvelopeType) {
            final GridEnvelopeType target = this;
            final GridEnvelopeType leftObject = ((GridEnvelopeType) left);
            final GridEnvelopeType rightObject = ((GridEnvelopeType) right);
            {
                java.lang.Boolean lowShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetLow(), rightObject.isSetLow());
                if (lowShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<BigInteger> lhsLow;
                    lhsLow = (leftObject.isSetLow()?leftObject.getLow():null);
                    List<BigInteger> rhsLow;
                    rhsLow = (rightObject.isSetLow()?rightObject.getLow():null);
                    List<BigInteger> mergedLow = ((List<BigInteger> ) strategy.merge(LocatorUtils.property(leftLocator, "low", lhsLow), LocatorUtils.property(rightLocator, "low", rhsLow), lhsLow, rhsLow, leftObject.isSetLow(), rightObject.isSetLow()));
                    target.setLow(mergedLow);
                } else {
                    if (lowShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetLow();
                    }
                }
            }
            {
                java.lang.Boolean highShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetHigh(), rightObject.isSetHigh());
                if (highShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<BigInteger> lhsHigh;
                    lhsHigh = (leftObject.isSetHigh()?leftObject.getHigh():null);
                    List<BigInteger> rhsHigh;
                    rhsHigh = (rightObject.isSetHigh()?rightObject.getHigh():null);
                    List<BigInteger> mergedHigh = ((List<BigInteger> ) strategy.merge(LocatorUtils.property(leftLocator, "high", lhsHigh), LocatorUtils.property(rightLocator, "high", rhsHigh), lhsHigh, rhsHigh, leftObject.isSetHigh(), rightObject.isSetHigh()));
                    target.setHigh(mergedHigh);
                } else {
                    if (highShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetHigh();
                    }
                }
            }
        }
    }

}
