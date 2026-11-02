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
 * <p>Classe Java per DataBlockType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="DataBlockType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}rangeParameters"/>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml}tupleList"/>
 *           <element ref="{http://www.opengis.net/gml}doubleOrNilReasonTupleList"/>
 *         </choice>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataBlockType", propOrder = {
    "rangeParameters",
    "tupleList",
    "doubleOrNilReasonTupleList"
})
public class DataBlockType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected RangeParametersType rangeParameters;
    /**
     * gml:CoordinatesType consists of a list of coordinate tuples, with each coordinate tuple separated by the ts or tuple separator (whitespace), and each coordinate in the tuple by the cs or coordinate separator (comma).
     * The gml:tupleList encoding is effectively “band-interleaved”.
     * 
     */
    protected CoordinatesType tupleList;
    /**
     * gml:doubleOrNilReasonList consists of a list of gml:doubleOrNilReason values, each separated by a whitespace. The gml:doubleOrNilReason values are grouped into tuples where the dimension of each tuple in the list is equal to the number of range parameters.
     * 
     */
    @XmlList
    protected List<String> doubleOrNilReasonTupleList;

    /**
     * Recupera il valore della proprietà rangeParameters.
     * 
     * @return
     *     possible object is
     *     {@link RangeParametersType }
     *     
     */
    public RangeParametersType getRangeParameters() {
        return rangeParameters;
    }

    /**
     * Imposta il valore della proprietà rangeParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeParametersType }
     *     
     */
    public void setRangeParameters(RangeParametersType value) {
        this.rangeParameters = value;
    }

    public boolean isSetRangeParameters() {
        return (this.rangeParameters!= null);
    }

    /**
     * gml:CoordinatesType consists of a list of coordinate tuples, with each coordinate tuple separated by the ts or tuple separator (whitespace), and each coordinate in the tuple by the cs or coordinate separator (comma).
     * The gml:tupleList encoding is effectively “band-interleaved”.
     * 
     * @return
     *     possible object is
     *     {@link CoordinatesType }
     *     
     */
    public CoordinatesType getTupleList() {
        return tupleList;
    }

    /**
     * Imposta il valore della proprietà tupleList.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinatesType }
     *     
     * @see #getTupleList()
     */
    public void setTupleList(CoordinatesType value) {
        this.tupleList = value;
    }

    public boolean isSetTupleList() {
        return (this.tupleList!= null);
    }

    /**
     * gml:doubleOrNilReasonList consists of a list of gml:doubleOrNilReason values, each separated by a whitespace. The gml:doubleOrNilReason values are grouped into tuples where the dimension of each tuple in the list is equal to the number of range parameters.
     * 
     * Gets the value of the doubleOrNilReasonTupleList property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the doubleOrNilReasonTupleList property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDoubleOrNilReasonTupleList().add(newItem);
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
     *     The value of the doubleOrNilReasonTupleList property.
     */
    public List<String> getDoubleOrNilReasonTupleList() {
        if (doubleOrNilReasonTupleList == null) {
            doubleOrNilReasonTupleList = new ArrayList<>();
        }
        return this.doubleOrNilReasonTupleList;
    }

    public boolean isSetDoubleOrNilReasonTupleList() {
        return ((this.doubleOrNilReasonTupleList!= null)&&(!this.doubleOrNilReasonTupleList.isEmpty()));
    }

    public void unsetDoubleOrNilReasonTupleList() {
        this.doubleOrNilReasonTupleList = null;
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
            RangeParametersType theRangeParameters;
            theRangeParameters = this.getRangeParameters();
            strategy.appendField(locator, this, "rangeParameters", buffer, theRangeParameters, this.isSetRangeParameters());
        }
        {
            CoordinatesType theTupleList;
            theTupleList = this.getTupleList();
            strategy.appendField(locator, this, "tupleList", buffer, theTupleList, this.isSetTupleList());
        }
        {
            List<String> theDoubleOrNilReasonTupleList;
            theDoubleOrNilReasonTupleList = (this.isSetDoubleOrNilReasonTupleList()?this.getDoubleOrNilReasonTupleList():null);
            strategy.appendField(locator, this, "doubleOrNilReasonTupleList", buffer, theDoubleOrNilReasonTupleList, this.isSetDoubleOrNilReasonTupleList());
        }
        return buffer;
    }

    public void setDoubleOrNilReasonTupleList(List<String> value) {
        this.doubleOrNilReasonTupleList = null;
        if (value!= null) {
            List<String> draftl = this.getDoubleOrNilReasonTupleList();
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
        final DataBlockType that = ((DataBlockType) object);
        {
            RangeParametersType lhsRangeParameters;
            lhsRangeParameters = this.getRangeParameters();
            RangeParametersType rhsRangeParameters;
            rhsRangeParameters = that.getRangeParameters();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rangeParameters", lhsRangeParameters), LocatorUtils.property(thatLocator, "rangeParameters", rhsRangeParameters), lhsRangeParameters, rhsRangeParameters, this.isSetRangeParameters(), that.isSetRangeParameters())) {
                return false;
            }
        }
        {
            CoordinatesType lhsTupleList;
            lhsTupleList = this.getTupleList();
            CoordinatesType rhsTupleList;
            rhsTupleList = that.getTupleList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "tupleList", lhsTupleList), LocatorUtils.property(thatLocator, "tupleList", rhsTupleList), lhsTupleList, rhsTupleList, this.isSetTupleList(), that.isSetTupleList())) {
                return false;
            }
        }
        {
            List<String> lhsDoubleOrNilReasonTupleList;
            lhsDoubleOrNilReasonTupleList = (this.isSetDoubleOrNilReasonTupleList()?this.getDoubleOrNilReasonTupleList():null);
            List<String> rhsDoubleOrNilReasonTupleList;
            rhsDoubleOrNilReasonTupleList = (that.isSetDoubleOrNilReasonTupleList()?that.getDoubleOrNilReasonTupleList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "doubleOrNilReasonTupleList", lhsDoubleOrNilReasonTupleList), LocatorUtils.property(thatLocator, "doubleOrNilReasonTupleList", rhsDoubleOrNilReasonTupleList), lhsDoubleOrNilReasonTupleList, rhsDoubleOrNilReasonTupleList, this.isSetDoubleOrNilReasonTupleList(), that.isSetDoubleOrNilReasonTupleList())) {
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
            RangeParametersType theRangeParameters;
            theRangeParameters = this.getRangeParameters();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rangeParameters", theRangeParameters), currentHashCode, theRangeParameters, this.isSetRangeParameters());
        }
        {
            CoordinatesType theTupleList;
            theTupleList = this.getTupleList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "tupleList", theTupleList), currentHashCode, theTupleList, this.isSetTupleList());
        }
        {
            List<String> theDoubleOrNilReasonTupleList;
            theDoubleOrNilReasonTupleList = (this.isSetDoubleOrNilReasonTupleList()?this.getDoubleOrNilReasonTupleList():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "doubleOrNilReasonTupleList", theDoubleOrNilReasonTupleList), currentHashCode, theDoubleOrNilReasonTupleList, this.isSetDoubleOrNilReasonTupleList());
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
        if (draftCopy instanceof DataBlockType) {
            final DataBlockType copy = ((DataBlockType) draftCopy);
            {
                java.lang.Boolean rangeParametersShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetRangeParameters());
                if (rangeParametersShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    RangeParametersType sourceRangeParameters;
                    sourceRangeParameters = this.getRangeParameters();
                    RangeParametersType copyRangeParameters = ((RangeParametersType) strategy.copy(LocatorUtils.property(locator, "rangeParameters", sourceRangeParameters), sourceRangeParameters, this.isSetRangeParameters()));
                    copy.setRangeParameters(copyRangeParameters);
                } else {
                    if (rangeParametersShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.rangeParameters = null;
                    }
                }
            }
            {
                java.lang.Boolean tupleListShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetTupleList());
                if (tupleListShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    CoordinatesType sourceTupleList;
                    sourceTupleList = this.getTupleList();
                    CoordinatesType copyTupleList = ((CoordinatesType) strategy.copy(LocatorUtils.property(locator, "tupleList", sourceTupleList), sourceTupleList, this.isSetTupleList()));
                    copy.setTupleList(copyTupleList);
                } else {
                    if (tupleListShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.tupleList = null;
                    }
                }
            }
            {
                java.lang.Boolean doubleOrNilReasonTupleListShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDoubleOrNilReasonTupleList());
                if (doubleOrNilReasonTupleListShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<String> sourceDoubleOrNilReasonTupleList;
                    sourceDoubleOrNilReasonTupleList = (this.isSetDoubleOrNilReasonTupleList()?this.getDoubleOrNilReasonTupleList():null);
                    @SuppressWarnings("unchecked")
                    List<String> copyDoubleOrNilReasonTupleList = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "doubleOrNilReasonTupleList", sourceDoubleOrNilReasonTupleList), sourceDoubleOrNilReasonTupleList, this.isSetDoubleOrNilReasonTupleList()));
                    copy.setDoubleOrNilReasonTupleList(copyDoubleOrNilReasonTupleList);
                } else {
                    if (doubleOrNilReasonTupleListShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetDoubleOrNilReasonTupleList();
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new DataBlockType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof DataBlockType) {
            final DataBlockType target = this;
            final DataBlockType leftObject = ((DataBlockType) left);
            final DataBlockType rightObject = ((DataBlockType) right);
            {
                java.lang.Boolean rangeParametersShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetRangeParameters(), rightObject.isSetRangeParameters());
                if (rangeParametersShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    RangeParametersType lhsRangeParameters;
                    lhsRangeParameters = leftObject.getRangeParameters();
                    RangeParametersType rhsRangeParameters;
                    rhsRangeParameters = rightObject.getRangeParameters();
                    RangeParametersType mergedRangeParameters = ((RangeParametersType) strategy.merge(LocatorUtils.property(leftLocator, "rangeParameters", lhsRangeParameters), LocatorUtils.property(rightLocator, "rangeParameters", rhsRangeParameters), lhsRangeParameters, rhsRangeParameters, leftObject.isSetRangeParameters(), rightObject.isSetRangeParameters()));
                    target.setRangeParameters(mergedRangeParameters);
                } else {
                    if (rangeParametersShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.rangeParameters = null;
                    }
                }
            }
            {
                java.lang.Boolean tupleListShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetTupleList(), rightObject.isSetTupleList());
                if (tupleListShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    CoordinatesType lhsTupleList;
                    lhsTupleList = leftObject.getTupleList();
                    CoordinatesType rhsTupleList;
                    rhsTupleList = rightObject.getTupleList();
                    CoordinatesType mergedTupleList = ((CoordinatesType) strategy.merge(LocatorUtils.property(leftLocator, "tupleList", lhsTupleList), LocatorUtils.property(rightLocator, "tupleList", rhsTupleList), lhsTupleList, rhsTupleList, leftObject.isSetTupleList(), rightObject.isSetTupleList()));
                    target.setTupleList(mergedTupleList);
                } else {
                    if (tupleListShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.tupleList = null;
                    }
                }
            }
            {
                java.lang.Boolean doubleOrNilReasonTupleListShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDoubleOrNilReasonTupleList(), rightObject.isSetDoubleOrNilReasonTupleList());
                if (doubleOrNilReasonTupleListShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<String> lhsDoubleOrNilReasonTupleList;
                    lhsDoubleOrNilReasonTupleList = (leftObject.isSetDoubleOrNilReasonTupleList()?leftObject.getDoubleOrNilReasonTupleList():null);
                    List<String> rhsDoubleOrNilReasonTupleList;
                    rhsDoubleOrNilReasonTupleList = (rightObject.isSetDoubleOrNilReasonTupleList()?rightObject.getDoubleOrNilReasonTupleList():null);
                    List<String> mergedDoubleOrNilReasonTupleList = ((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "doubleOrNilReasonTupleList", lhsDoubleOrNilReasonTupleList), LocatorUtils.property(rightLocator, "doubleOrNilReasonTupleList", rhsDoubleOrNilReasonTupleList), lhsDoubleOrNilReasonTupleList, rhsDoubleOrNilReasonTupleList, leftObject.isSetDoubleOrNilReasonTupleList(), rightObject.isSetDoubleOrNilReasonTupleList()));
                    target.setDoubleOrNilReasonTupleList(mergedDoubleOrNilReasonTupleList);
                } else {
                    if (doubleOrNilReasonTupleListShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetDoubleOrNilReasonTupleList();
                    }
                }
            }
        }
    }

}
