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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;
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
 * posList instances (and other instances with the content model specified by DirectPositionListType) hold the coordinates for a sequence of direct positions within the same coordinate reference system (CRS).
 * if no srsName attribute is given, the CRS shall be specified as part of the larger context this geometry element is part of, typically a geometric object like a point, curve, etc. 
 * The optional attribute count specifies the number of direct positions in the list. If the attribute count is present then the attribute srsDimension shall be present, too.
 * The number of entries in the list is equal to the product of the dimensionality of the coordinate reference system (i.e. it is a derived value of the coordinate reference system definition) and the number of direct positions.
 * 
 * <p>Classe Java per DirectPositionListType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DirectPositionListType">
 *   <simpleContent>
 *     <extension base="<http://www.opengis.net/gml>doubleList">
 *       <attGroup ref="{http://www.opengis.net/gml}SRSReferenceGroup"/>
 *       <attribute name="count" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DirectPositionListType", propOrder = {
    "value"
})
public class DirectPositionListType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * A type for a list of values of the respective simple type.
     * 
     */
    @XmlValue
    protected List<Double> value;
    @XmlAttribute(name = "count")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger count;
    @XmlAttribute(name = "srsName")
    @XmlSchemaType(name = "anyURI")
    protected String srsName;
    @XmlAttribute(name = "srsDimension")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger srsDimension;
    @XmlAttribute(name = "axisLabels")
    protected List<String> axisLabels;
    @XmlAttribute(name = "uomLabels")
    protected List<String> uomLabels;

    /**
     * A type for a list of values of the respective simple type.
     * 
     * Gets the value of the value property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the value property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Double }
     * </p>
     * 
     * 
     * @return
     *     The value of the value property.
     */
    public List<Double> getValue() {
        if (value == null) {
            value = new ArrayList<>();
        }
        return this.value;
    }

    public boolean isSetValue() {
        return ((this.value!= null)&&(!this.value.isEmpty()));
    }

    public void unsetValue() {
        this.value = null;
    }

    /**
     * Recupera il valore della proprietà count.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCount() {
        return count;
    }

    /**
     * Imposta il valore della proprietà count.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCount(BigInteger value) {
        this.count = value;
    }

    public boolean isSetCount() {
        return (this.count!= null);
    }

    /**
     * Recupera il valore della proprietà srsName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrsName() {
        return srsName;
    }

    /**
     * Imposta il valore della proprietà srsName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrsName(String value) {
        this.srsName = value;
    }

    public boolean isSetSrsName() {
        return (this.srsName!= null);
    }

    /**
     * Recupera il valore della proprietà srsDimension.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSrsDimension() {
        return srsDimension;
    }

    /**
     * Imposta il valore della proprietà srsDimension.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSrsDimension(BigInteger value) {
        this.srsDimension = value;
    }

    public boolean isSetSrsDimension() {
        return (this.srsDimension!= null);
    }

    /**
     * Gets the value of the axisLabels property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the axisLabels property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAxisLabels().add(newItem);
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
     *     The value of the axisLabels property.
     */
    public List<String> getAxisLabels() {
        if (axisLabels == null) {
            axisLabels = new ArrayList<>();
        }
        return this.axisLabels;
    }

    public boolean isSetAxisLabels() {
        return ((this.axisLabels!= null)&&(!this.axisLabels.isEmpty()));
    }

    public void unsetAxisLabels() {
        this.axisLabels = null;
    }

    /**
     * Gets the value of the uomLabels property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the uomLabels property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getUomLabels().add(newItem);
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
     *     The value of the uomLabels property.
     */
    public List<String> getUomLabels() {
        if (uomLabels == null) {
            uomLabels = new ArrayList<>();
        }
        return this.uomLabels;
    }

    public boolean isSetUomLabels() {
        return ((this.uomLabels!= null)&&(!this.uomLabels.isEmpty()));
    }

    public void unsetUomLabels() {
        this.uomLabels = null;
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
            List<Double> theValue;
            theValue = (this.isSetValue()?this.getValue():null);
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            BigInteger theCount;
            theCount = this.getCount();
            strategy.appendField(locator, this, "count", buffer, theCount, this.isSetCount());
        }
        {
            String theSrsName;
            theSrsName = this.getSrsName();
            strategy.appendField(locator, this, "srsName", buffer, theSrsName, this.isSetSrsName());
        }
        {
            BigInteger theSrsDimension;
            theSrsDimension = this.getSrsDimension();
            strategy.appendField(locator, this, "srsDimension", buffer, theSrsDimension, this.isSetSrsDimension());
        }
        {
            List<String> theAxisLabels;
            theAxisLabels = (this.isSetAxisLabels()?this.getAxisLabels():null);
            strategy.appendField(locator, this, "axisLabels", buffer, theAxisLabels, this.isSetAxisLabels());
        }
        {
            List<String> theUomLabels;
            theUomLabels = (this.isSetUomLabels()?this.getUomLabels():null);
            strategy.appendField(locator, this, "uomLabels", buffer, theUomLabels, this.isSetUomLabels());
        }
        return buffer;
    }

    public void setValue(List<Double> value) {
        this.value = null;
        if (value!= null) {
            List<Double> draftl = this.getValue();
            draftl.addAll(value);
        }
    }

    public void setAxisLabels(List<String> value) {
        this.axisLabels = null;
        if (value!= null) {
            List<String> draftl = this.getAxisLabels();
            draftl.addAll(value);
        }
    }

    public void setUomLabels(List<String> value) {
        this.uomLabels = null;
        if (value!= null) {
            List<String> draftl = this.getUomLabels();
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
        final DirectPositionListType that = ((DirectPositionListType) object);
        {
            List<Double> lhsValue;
            lhsValue = (this.isSetValue()?this.getValue():null);
            List<Double> rhsValue;
            rhsValue = (that.isSetValue()?that.getValue():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            BigInteger lhsCount;
            lhsCount = this.getCount();
            BigInteger rhsCount;
            rhsCount = that.getCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "count", lhsCount), LocatorUtils.property(thatLocator, "count", rhsCount), lhsCount, rhsCount, this.isSetCount(), that.isSetCount())) {
                return false;
            }
        }
        {
            String lhsSrsName;
            lhsSrsName = this.getSrsName();
            String rhsSrsName;
            rhsSrsName = that.getSrsName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "srsName", lhsSrsName), LocatorUtils.property(thatLocator, "srsName", rhsSrsName), lhsSrsName, rhsSrsName, this.isSetSrsName(), that.isSetSrsName())) {
                return false;
            }
        }
        {
            BigInteger lhsSrsDimension;
            lhsSrsDimension = this.getSrsDimension();
            BigInteger rhsSrsDimension;
            rhsSrsDimension = that.getSrsDimension();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "srsDimension", lhsSrsDimension), LocatorUtils.property(thatLocator, "srsDimension", rhsSrsDimension), lhsSrsDimension, rhsSrsDimension, this.isSetSrsDimension(), that.isSetSrsDimension())) {
                return false;
            }
        }
        {
            List<String> lhsAxisLabels;
            lhsAxisLabels = (this.isSetAxisLabels()?this.getAxisLabels():null);
            List<String> rhsAxisLabels;
            rhsAxisLabels = (that.isSetAxisLabels()?that.getAxisLabels():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "axisLabels", lhsAxisLabels), LocatorUtils.property(thatLocator, "axisLabels", rhsAxisLabels), lhsAxisLabels, rhsAxisLabels, this.isSetAxisLabels(), that.isSetAxisLabels())) {
                return false;
            }
        }
        {
            List<String> lhsUomLabels;
            lhsUomLabels = (this.isSetUomLabels()?this.getUomLabels():null);
            List<String> rhsUomLabels;
            rhsUomLabels = (that.isSetUomLabels()?that.getUomLabels():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "uomLabels", lhsUomLabels), LocatorUtils.property(thatLocator, "uomLabels", rhsUomLabels), lhsUomLabels, rhsUomLabels, this.isSetUomLabels(), that.isSetUomLabels())) {
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
            List<Double> theValue;
            theValue = (this.isSetValue()?this.getValue():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            BigInteger theCount;
            theCount = this.getCount();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "count", theCount), currentHashCode, theCount, this.isSetCount());
        }
        {
            String theSrsName;
            theSrsName = this.getSrsName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "srsName", theSrsName), currentHashCode, theSrsName, this.isSetSrsName());
        }
        {
            BigInteger theSrsDimension;
            theSrsDimension = this.getSrsDimension();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "srsDimension", theSrsDimension), currentHashCode, theSrsDimension, this.isSetSrsDimension());
        }
        {
            List<String> theAxisLabels;
            theAxisLabels = (this.isSetAxisLabels()?this.getAxisLabels():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "axisLabels", theAxisLabels), currentHashCode, theAxisLabels, this.isSetAxisLabels());
        }
        {
            List<String> theUomLabels;
            theUomLabels = (this.isSetUomLabels()?this.getUomLabels():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "uomLabels", theUomLabels), currentHashCode, theUomLabels, this.isSetUomLabels());
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
        if (draftCopy instanceof DirectPositionListType) {
            final DirectPositionListType copy = ((DirectPositionListType) draftCopy);
            {
                java.lang.Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<Double> sourceValue;
                    sourceValue = (this.isSetValue()?this.getValue():null);
                    @SuppressWarnings("unchecked")
                    List<Double> copyValue = ((List<Double> ) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetValue();
                    }
                }
            }
            {
                java.lang.Boolean countShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCount());
                if (countShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceCount;
                    sourceCount = this.getCount();
                    BigInteger copyCount = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "count", sourceCount), sourceCount, this.isSetCount()));
                    copy.setCount(copyCount);
                } else {
                    if (countShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.count = null;
                    }
                }
            }
            {
                java.lang.Boolean srsNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSrsName());
                if (srsNameShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceSrsName;
                    sourceSrsName = this.getSrsName();
                    String copySrsName = ((String) strategy.copy(LocatorUtils.property(locator, "srsName", sourceSrsName), sourceSrsName, this.isSetSrsName()));
                    copy.setSrsName(copySrsName);
                } else {
                    if (srsNameShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.srsName = null;
                    }
                }
            }
            {
                java.lang.Boolean srsDimensionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetSrsDimension());
                if (srsDimensionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceSrsDimension;
                    sourceSrsDimension = this.getSrsDimension();
                    BigInteger copySrsDimension = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "srsDimension", sourceSrsDimension), sourceSrsDimension, this.isSetSrsDimension()));
                    copy.setSrsDimension(copySrsDimension);
                } else {
                    if (srsDimensionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.srsDimension = null;
                    }
                }
            }
            {
                java.lang.Boolean axisLabelsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAxisLabels());
                if (axisLabelsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceAxisLabels;
                    sourceAxisLabels = (this.isSetAxisLabels()?this.getAxisLabels():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyAxisLabels = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "axisLabels", sourceAxisLabels), sourceAxisLabels, this.isSetAxisLabels()));
                    copy.setAxisLabels(copyAxisLabels);
                } else {
                    if (axisLabelsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetAxisLabels();
                    }
                }
            }
            {
                java.lang.Boolean uomLabelsShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetUomLabels());
                if (uomLabelsShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceUomLabels;
                    sourceUomLabels = (this.isSetUomLabels()?this.getUomLabels():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyUomLabels = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "uomLabels", sourceUomLabels), sourceUomLabels, this.isSetUomLabels()));
                    copy.setUomLabels(copyUomLabels);
                } else {
                    if (uomLabelsShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetUomLabels();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new DirectPositionListType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof DirectPositionListType) {
            final DirectPositionListType target = this;
            final DirectPositionListType leftObject = ((DirectPositionListType) left);
            final DirectPositionListType rightObject = ((DirectPositionListType) right);
            {
                java.lang.Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<Double> lhsValue;
                    lhsValue = (leftObject.isSetValue()?leftObject.getValue():null);
                    List<Double> rhsValue;
                    rhsValue = (rightObject.isSetValue()?rightObject.getValue():null);
                    List<Double> mergedValue = ((List<Double> ) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetValue();
                    }
                }
            }
            {
                java.lang.Boolean countShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCount(), rightObject.isSetCount());
                if (countShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsCount;
                    lhsCount = leftObject.getCount();
                    BigInteger rhsCount;
                    rhsCount = rightObject.getCount();
                    BigInteger mergedCount = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "count", lhsCount), LocatorUtils.property(rightLocator, "count", rhsCount), lhsCount, rhsCount, leftObject.isSetCount(), rightObject.isSetCount()));
                    target.setCount(mergedCount);
                } else {
                    if (countShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.count = null;
                    }
                }
            }
            {
                java.lang.Boolean srsNameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSrsName(), rightObject.isSetSrsName());
                if (srsNameShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsSrsName;
                    lhsSrsName = leftObject.getSrsName();
                    String rhsSrsName;
                    rhsSrsName = rightObject.getSrsName();
                    String mergedSrsName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "srsName", lhsSrsName), LocatorUtils.property(rightLocator, "srsName", rhsSrsName), lhsSrsName, rhsSrsName, leftObject.isSetSrsName(), rightObject.isSetSrsName()));
                    target.setSrsName(mergedSrsName);
                } else {
                    if (srsNameShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.srsName = null;
                    }
                }
            }
            {
                java.lang.Boolean srsDimensionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetSrsDimension(), rightObject.isSetSrsDimension());
                if (srsDimensionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsSrsDimension;
                    lhsSrsDimension = leftObject.getSrsDimension();
                    BigInteger rhsSrsDimension;
                    rhsSrsDimension = rightObject.getSrsDimension();
                    BigInteger mergedSrsDimension = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "srsDimension", lhsSrsDimension), LocatorUtils.property(rightLocator, "srsDimension", rhsSrsDimension), lhsSrsDimension, rhsSrsDimension, leftObject.isSetSrsDimension(), rightObject.isSetSrsDimension()));
                    target.setSrsDimension(mergedSrsDimension);
                } else {
                    if (srsDimensionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.srsDimension = null;
                    }
                }
            }
            {
                java.lang.Boolean axisLabelsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAxisLabels(), rightObject.isSetAxisLabels());
                if (axisLabelsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsAxisLabels;
                    lhsAxisLabels = (leftObject.isSetAxisLabels()?leftObject.getAxisLabels():null);
                    List<String> rhsAxisLabels;
                    rhsAxisLabels = (rightObject.isSetAxisLabels()?rightObject.getAxisLabels():null);
                    List<String> mergedAxisLabels = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "axisLabels", lhsAxisLabels), LocatorUtils.property(rightLocator, "axisLabels", rhsAxisLabels), lhsAxisLabels, rhsAxisLabels, leftObject.isSetAxisLabels(), rightObject.isSetAxisLabels()));
                    target.setAxisLabels(mergedAxisLabels);
                } else {
                    if (axisLabelsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetAxisLabels();
                    }
                }
            }
            {
                java.lang.Boolean uomLabelsShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetUomLabels(), rightObject.isSetUomLabels());
                if (uomLabelsShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsUomLabels;
                    lhsUomLabels = (leftObject.isSetUomLabels()?leftObject.getUomLabels():null);
                    List<String> rhsUomLabels;
                    rhsUomLabels = (rightObject.isSetUomLabels()?rightObject.getUomLabels():null);
                    List<String> mergedUomLabels = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "uomLabels", lhsUomLabels), LocatorUtils.property(rightLocator, "uomLabels", rhsUomLabels), lhsUomLabels, rhsUomLabels, leftObject.isSetUomLabels(), rightObject.isSetUomLabels()));
                    target.setUomLabels(mergedUomLabels);
                } else {
                    if (uomLabelsShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetUomLabels();
                    }
                }
            }
        }
    }

}
