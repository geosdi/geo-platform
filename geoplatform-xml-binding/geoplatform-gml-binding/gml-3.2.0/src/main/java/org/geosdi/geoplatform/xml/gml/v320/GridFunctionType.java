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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlList;
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
 * <p>Classe Java per GridFunctionType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="GridFunctionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sequenceRule" type="{http://www.opengis.net/gml}SequenceRuleType" minOccurs="0"/>
 *         &lt;element name="startPoint" type="{http://www.opengis.net/gml}integerList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the startPoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStartPoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getStartPoint() {
        if (startPoint == null) {
            startPoint = new ArrayList<BigInteger>();
        }
        return this.startPoint;
    }

    public boolean isSetStartPoint() {
        return ((this.startPoint!= null)&&(!this.startPoint.isEmpty()));
    }

    public void unsetStartPoint() {
        this.startPoint = null;
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
        {
            SequenceRuleType theSequenceRule;
            theSequenceRule = this.getSequenceRule();
            strategy.appendField(locator, this, "sequenceRule", buffer, theSequenceRule);
        }
        {
            List<BigInteger> theStartPoint;
            theStartPoint = this.getStartPoint();
            strategy.appendField(locator, this, "startPoint", buffer, theStartPoint);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GridFunctionType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sequenceRule", lhsSequenceRule), LocatorUtils.property(thatLocator, "sequenceRule", rhsSequenceRule), lhsSequenceRule, rhsSequenceRule)) {
                return false;
            }
        }
        {
            List<BigInteger> lhsStartPoint;
            lhsStartPoint = this.getStartPoint();
            List<BigInteger> rhsStartPoint;
            rhsStartPoint = that.getStartPoint();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "startPoint", lhsStartPoint), LocatorUtils.property(thatLocator, "startPoint", rhsStartPoint), lhsStartPoint, rhsStartPoint)) {
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
        int currentHashCode = 1;
        {
            SequenceRuleType theSequenceRule;
            theSequenceRule = this.getSequenceRule();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "sequenceRule", theSequenceRule), currentHashCode, theSequenceRule);
        }
        {
            List<BigInteger> theStartPoint;
            theStartPoint = this.getStartPoint();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "startPoint", theStartPoint), currentHashCode, theStartPoint);
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
        if (draftCopy instanceof GridFunctionType) {
            final GridFunctionType copy = ((GridFunctionType) draftCopy);
            if (this.isSetSequenceRule()) {
                SequenceRuleType sourceSequenceRule;
                sourceSequenceRule = this.getSequenceRule();
                SequenceRuleType copySequenceRule = ((SequenceRuleType) strategy.copy(LocatorUtils.property(locator, "sequenceRule", sourceSequenceRule), sourceSequenceRule));
                copy.setSequenceRule(copySequenceRule);
            } else {
                copy.sequenceRule = null;
            }
            if (this.isSetStartPoint()) {
                List<BigInteger> sourceStartPoint;
                sourceStartPoint = this.getStartPoint();
                @SuppressWarnings("unchecked")
                List<BigInteger> copyStartPoint = ((List<BigInteger> ) strategy.copy(LocatorUtils.property(locator, "startPoint", sourceStartPoint), sourceStartPoint));
                copy.unsetStartPoint();
                List<BigInteger> uniqueStartPointl = copy.getStartPoint();
                uniqueStartPointl.addAll(copyStartPoint);
            } else {
                copy.unsetStartPoint();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new GridFunctionType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof GridFunctionType) {
            final GridFunctionType target = this;
            final GridFunctionType leftObject = ((GridFunctionType) left);
            final GridFunctionType rightObject = ((GridFunctionType) right);
            {
                SequenceRuleType lhsSequenceRule;
                lhsSequenceRule = leftObject.getSequenceRule();
                SequenceRuleType rhsSequenceRule;
                rhsSequenceRule = rightObject.getSequenceRule();
                target.setSequenceRule(((SequenceRuleType) strategy.merge(LocatorUtils.property(leftLocator, "sequenceRule", lhsSequenceRule), LocatorUtils.property(rightLocator, "sequenceRule", rhsSequenceRule), lhsSequenceRule, rhsSequenceRule)));
            }
            {
                List<BigInteger> lhsStartPoint;
                lhsStartPoint = leftObject.getStartPoint();
                List<BigInteger> rhsStartPoint;
                rhsStartPoint = rightObject.getStartPoint();
                target.unsetStartPoint();
                List<BigInteger> uniqueStartPointl = target.getStartPoint();
                uniqueStartPointl.addAll(((List<BigInteger> ) strategy.merge(LocatorUtils.property(leftLocator, "startPoint", lhsStartPoint), LocatorUtils.property(rightLocator, "startPoint", rhsStartPoint), lhsStartPoint, rhsStartPoint)));
            }
        }
    }

    public void setStartPoint(List<BigInteger> value) {
        List<BigInteger> draftl = this.getStartPoint();
        draftl.addAll(value);
    }

    public GridFunctionType withSequenceRule(SequenceRuleType value) {
        setSequenceRule(value);
        return this;
    }

    public GridFunctionType withStartPoint(BigInteger... values) {
        if (values!= null) {
            for (BigInteger value: values) {
                getStartPoint().add(value);
            }
        }
        return this;
    }

    public GridFunctionType withStartPoint(Collection<BigInteger> values) {
        if (values!= null) {
            getStartPoint().addAll(values);
        }
        return this;
    }

    public GridFunctionType withStartPoint(List<BigInteger> value) {
        setStartPoint(value);
        return this;
    }

}
