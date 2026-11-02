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
 * <p>Classe Java per GridFunctionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="GridFunctionType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="sequenceRule" type="{http://www.opengis.net/gml}SequenceRuleType" minOccurs="0"/>
 *         <element name="startPoint" type="{http://www.opengis.net/gml}integerList" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GridFunctionType", propOrder = {
    "sequenceRule",
    "startPoint"
})
public class GridFunctionType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    protected SequenceRuleType sequenceRule;
    @XmlList
    protected List<BigInteger> startPoint;

    /**
     * Recupera il valore della proprietà sequenceRule.
     * 
     * @return
     *     possible object is
     *     {@link SequenceRuleType }
     *     
     */
    public SequenceRuleType getSequenceRule() {
        return sequenceRule;
    }

    /**
     * Imposta il valore della proprietà sequenceRule.
     * 
     * @param value
     *     allowed object is
     *     {@link SequenceRuleType }
     *     
     */
    public void setSequenceRule(SequenceRuleType value) {
        this.sequenceRule = value;
    }

    public boolean isSetSequenceRule() {
        return (this.sequenceRule!= null);
    }

    /**
     * Gets the value of the startPoint property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the startPoint property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getStartPoint().add(newItem);
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
     *     The value of the startPoint property.
     */
    public List<BigInteger> getStartPoint() {
        if (startPoint == null) {
            startPoint = new ArrayList<>();
        }
        return this.startPoint;
    }

    public boolean isSetStartPoint() {
        return ((this.startPoint!= null)&&(!this.startPoint.isEmpty()));
    }

    public void unsetStartPoint() {
        this.startPoint = null;
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
            SequenceRuleType theSequenceRule;
            theSequenceRule = this.getSequenceRule();
            strategy.appendField(locator, this, "sequenceRule", buffer, theSequenceRule, this.isSetSequenceRule());
        }
        {
            List<BigInteger> theStartPoint;
            theStartPoint = (this.isSetStartPoint()?this.getStartPoint():null);
            strategy.appendField(locator, this, "startPoint", buffer, theStartPoint, this.isSetStartPoint());
        }
        return buffer;
    }

    public void setStartPoint(List<BigInteger> value) {
        this.startPoint = null;
        if (value!= null) {
            List<BigInteger> draftl = this.getStartPoint();
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
        final GridFunctionType that = ((GridFunctionType) object);
        {
            SequenceRuleType lhsSequenceRule;
            lhsSequenceRule = this.getSequenceRule();
            SequenceRuleType rhsSequenceRule;
            rhsSequenceRule = that.getSequenceRule();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sequenceRule", lhsSequenceRule), LocatorUtils.property(thatLocator, "sequenceRule", rhsSequenceRule), lhsSequenceRule, rhsSequenceRule, this.isSetSequenceRule(), that.isSetSequenceRule())) {
                return false;
            }
        }
        {
            List<BigInteger> lhsStartPoint;
            lhsStartPoint = (this.isSetStartPoint()?this.getStartPoint():null);
            List<BigInteger> rhsStartPoint;
            rhsStartPoint = (that.isSetStartPoint()?that.getStartPoint():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "startPoint", lhsStartPoint), LocatorUtils.property(thatLocator, "startPoint", rhsStartPoint), lhsStartPoint, rhsStartPoint, this.isSetStartPoint(), that.isSetStartPoint())) {
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
            SequenceRuleType theSequenceRule;
            theSequenceRule = this.getSequenceRule();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sequenceRule", theSequenceRule), currentHashCode, theSequenceRule, this.isSetSequenceRule());
        }
        {
            List<BigInteger> theStartPoint;
            theStartPoint = (this.isSetStartPoint()?this.getStartPoint():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "startPoint", theStartPoint), currentHashCode, theStartPoint, this.isSetStartPoint());
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
        if (draftCopy instanceof GridFunctionType) {
            final GridFunctionType copy = ((GridFunctionType) draftCopy);
            {
                java.lang.Boolean sequenceRuleShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSequenceRule());
                if (sequenceRuleShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    SequenceRuleType sourceSequenceRule;
                    sourceSequenceRule = this.getSequenceRule();
                    SequenceRuleType copySequenceRule = ((SequenceRuleType) strategy.copy(LocatorUtils.property(locator, "sequenceRule", sourceSequenceRule), sourceSequenceRule, this.isSetSequenceRule()));
                    copy.setSequenceRule(copySequenceRule);
                } else {
                    if (sequenceRuleShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.sequenceRule = null;
                    }
                }
            }
            {
                java.lang.Boolean startPointShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetStartPoint());
                if (startPointShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<BigInteger> sourceStartPoint;
                    sourceStartPoint = (this.isSetStartPoint()?this.getStartPoint():null);
                    @SuppressWarnings("unchecked")
                    List<BigInteger> copyStartPoint = ((List<BigInteger> ) strategy.copy(LocatorUtils.property(locator, "startPoint", sourceStartPoint), sourceStartPoint, this.isSetStartPoint()));
                    copy.setStartPoint(copyStartPoint);
                } else {
                    if (startPointShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetStartPoint();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new GridFunctionType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof GridFunctionType) {
            final GridFunctionType target = this;
            final GridFunctionType leftObject = ((GridFunctionType) left);
            final GridFunctionType rightObject = ((GridFunctionType) right);
            {
                java.lang.Boolean sequenceRuleShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSequenceRule(), rightObject.isSetSequenceRule());
                if (sequenceRuleShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    SequenceRuleType lhsSequenceRule;
                    lhsSequenceRule = leftObject.getSequenceRule();
                    SequenceRuleType rhsSequenceRule;
                    rhsSequenceRule = rightObject.getSequenceRule();
                    SequenceRuleType mergedSequenceRule = ((SequenceRuleType) strategy.merge(LocatorUtils.property(leftLocator, "sequenceRule", lhsSequenceRule), LocatorUtils.property(rightLocator, "sequenceRule", rhsSequenceRule), lhsSequenceRule, rhsSequenceRule, leftObject.isSetSequenceRule(), rightObject.isSetSequenceRule()));
                    target.setSequenceRule(mergedSequenceRule);
                } else {
                    if (sequenceRuleShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.sequenceRule = null;
                    }
                }
            }
            {
                java.lang.Boolean startPointShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetStartPoint(), rightObject.isSetStartPoint());
                if (startPointShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<BigInteger> lhsStartPoint;
                    lhsStartPoint = (leftObject.isSetStartPoint()?leftObject.getStartPoint():null);
                    List<BigInteger> rhsStartPoint;
                    rhsStartPoint = (rightObject.isSetStartPoint()?rightObject.getStartPoint():null);
                    List<BigInteger> mergedStartPoint = ((List<BigInteger> ) strategy.merge(LocatorUtils.property(leftLocator, "startPoint", lhsStartPoint), LocatorUtils.property(rightLocator, "startPoint", rhsStartPoint), lhsStartPoint, rhsStartPoint, leftObject.isSetStartPoint(), rightObject.isSetStartPoint()));
                    target.setStartPoint(mergedStartPoint);
                } else {
                    if (startPointShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetStartPoint();
                    }
                }
            }
        }
    }

}
