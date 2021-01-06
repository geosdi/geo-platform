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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per DataBlockType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DataBlockType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}rangeParameters"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}tupleList"/>
 *           &lt;element ref="{http://www.opengis.net/gml}doubleOrNilReasonTupleList"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
    protected CoordinatesType tupleList;
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
     * Recupera il valore della proprietà tupleList.
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
     */
    public void setTupleList(CoordinatesType value) {
        this.tupleList = value;
    }

    public boolean isSetTupleList() {
        return (this.tupleList!= null);
    }

    /**
     * Gets the value of the doubleOrNilReasonTupleList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doubleOrNilReasonTupleList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoubleOrNilReasonTupleList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDoubleOrNilReasonTupleList() {
        if (doubleOrNilReasonTupleList == null) {
            doubleOrNilReasonTupleList = new ArrayList<String>();
        }
        return this.doubleOrNilReasonTupleList;
    }

    public boolean isSetDoubleOrNilReasonTupleList() {
        return ((this.doubleOrNilReasonTupleList!= null)&&(!this.doubleOrNilReasonTupleList.isEmpty()));
    }

    public void unsetDoubleOrNilReasonTupleList() {
        this.doubleOrNilReasonTupleList = null;
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
            RangeParametersType theRangeParameters;
            theRangeParameters = this.getRangeParameters();
            strategy.appendField(locator, this, "rangeParameters", buffer, theRangeParameters);
        }
        {
            CoordinatesType theTupleList;
            theTupleList = this.getTupleList();
            strategy.appendField(locator, this, "tupleList", buffer, theTupleList);
        }
        {
            List<String> theDoubleOrNilReasonTupleList;
            theDoubleOrNilReasonTupleList = this.getDoubleOrNilReasonTupleList();
            strategy.appendField(locator, this, "doubleOrNilReasonTupleList", buffer, theDoubleOrNilReasonTupleList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DataBlockType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rangeParameters", lhsRangeParameters), LocatorUtils.property(thatLocator, "rangeParameters", rhsRangeParameters), lhsRangeParameters, rhsRangeParameters)) {
                return false;
            }
        }
        {
            CoordinatesType lhsTupleList;
            lhsTupleList = this.getTupleList();
            CoordinatesType rhsTupleList;
            rhsTupleList = that.getTupleList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "tupleList", lhsTupleList), LocatorUtils.property(thatLocator, "tupleList", rhsTupleList), lhsTupleList, rhsTupleList)) {
                return false;
            }
        }
        {
            List<String> lhsDoubleOrNilReasonTupleList;
            lhsDoubleOrNilReasonTupleList = this.getDoubleOrNilReasonTupleList();
            List<String> rhsDoubleOrNilReasonTupleList;
            rhsDoubleOrNilReasonTupleList = that.getDoubleOrNilReasonTupleList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "doubleOrNilReasonTupleList", lhsDoubleOrNilReasonTupleList), LocatorUtils.property(thatLocator, "doubleOrNilReasonTupleList", rhsDoubleOrNilReasonTupleList), lhsDoubleOrNilReasonTupleList, rhsDoubleOrNilReasonTupleList)) {
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
            RangeParametersType theRangeParameters;
            theRangeParameters = this.getRangeParameters();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "rangeParameters", theRangeParameters), currentHashCode, theRangeParameters);
        }
        {
            CoordinatesType theTupleList;
            theTupleList = this.getTupleList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "tupleList", theTupleList), currentHashCode, theTupleList);
        }
        {
            List<String> theDoubleOrNilReasonTupleList;
            theDoubleOrNilReasonTupleList = this.getDoubleOrNilReasonTupleList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "doubleOrNilReasonTupleList", theDoubleOrNilReasonTupleList), currentHashCode, theDoubleOrNilReasonTupleList);
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
        if (draftCopy instanceof DataBlockType) {
            final DataBlockType copy = ((DataBlockType) draftCopy);
            if (this.isSetRangeParameters()) {
                RangeParametersType sourceRangeParameters;
                sourceRangeParameters = this.getRangeParameters();
                RangeParametersType copyRangeParameters = ((RangeParametersType) strategy.copy(LocatorUtils.property(locator, "rangeParameters", sourceRangeParameters), sourceRangeParameters));
                copy.setRangeParameters(copyRangeParameters);
            } else {
                copy.rangeParameters = null;
            }
            if (this.isSetTupleList()) {
                CoordinatesType sourceTupleList;
                sourceTupleList = this.getTupleList();
                CoordinatesType copyTupleList = ((CoordinatesType) strategy.copy(LocatorUtils.property(locator, "tupleList", sourceTupleList), sourceTupleList));
                copy.setTupleList(copyTupleList);
            } else {
                copy.tupleList = null;
            }
            if (this.isSetDoubleOrNilReasonTupleList()) {
                List<String> sourceDoubleOrNilReasonTupleList;
                sourceDoubleOrNilReasonTupleList = this.getDoubleOrNilReasonTupleList();
                @SuppressWarnings("unchecked")
                List<String> copyDoubleOrNilReasonTupleList = ((List<String> ) strategy.copy(LocatorUtils.property(locator, "doubleOrNilReasonTupleList", sourceDoubleOrNilReasonTupleList), sourceDoubleOrNilReasonTupleList));
                copy.unsetDoubleOrNilReasonTupleList();
                List<String> uniqueDoubleOrNilReasonTupleListl = copy.getDoubleOrNilReasonTupleList();
                uniqueDoubleOrNilReasonTupleListl.addAll(copyDoubleOrNilReasonTupleList);
            } else {
                copy.unsetDoubleOrNilReasonTupleList();
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new DataBlockType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof DataBlockType) {
            final DataBlockType target = this;
            final DataBlockType leftObject = ((DataBlockType) left);
            final DataBlockType rightObject = ((DataBlockType) right);
            {
                RangeParametersType lhsRangeParameters;
                lhsRangeParameters = leftObject.getRangeParameters();
                RangeParametersType rhsRangeParameters;
                rhsRangeParameters = rightObject.getRangeParameters();
                target.setRangeParameters(((RangeParametersType) strategy.merge(LocatorUtils.property(leftLocator, "rangeParameters", lhsRangeParameters), LocatorUtils.property(rightLocator, "rangeParameters", rhsRangeParameters), lhsRangeParameters, rhsRangeParameters)));
            }
            {
                CoordinatesType lhsTupleList;
                lhsTupleList = leftObject.getTupleList();
                CoordinatesType rhsTupleList;
                rhsTupleList = rightObject.getTupleList();
                target.setTupleList(((CoordinatesType) strategy.merge(LocatorUtils.property(leftLocator, "tupleList", lhsTupleList), LocatorUtils.property(rightLocator, "tupleList", rhsTupleList), lhsTupleList, rhsTupleList)));
            }
            {
                List<String> lhsDoubleOrNilReasonTupleList;
                lhsDoubleOrNilReasonTupleList = leftObject.getDoubleOrNilReasonTupleList();
                List<String> rhsDoubleOrNilReasonTupleList;
                rhsDoubleOrNilReasonTupleList = rightObject.getDoubleOrNilReasonTupleList();
                target.unsetDoubleOrNilReasonTupleList();
                List<String> uniqueDoubleOrNilReasonTupleListl = target.getDoubleOrNilReasonTupleList();
                uniqueDoubleOrNilReasonTupleListl.addAll(((List<String> ) strategy.merge(LocatorUtils.property(leftLocator, "doubleOrNilReasonTupleList", lhsDoubleOrNilReasonTupleList), LocatorUtils.property(rightLocator, "doubleOrNilReasonTupleList", rhsDoubleOrNilReasonTupleList), lhsDoubleOrNilReasonTupleList, rhsDoubleOrNilReasonTupleList)));
            }
        }
    }

    public void setDoubleOrNilReasonTupleList(List<String> value) {
        List<String> draftl = this.getDoubleOrNilReasonTupleList();
        draftl.addAll(value);
    }

    public DataBlockType withRangeParameters(RangeParametersType value) {
        setRangeParameters(value);
        return this;
    }

    public DataBlockType withTupleList(CoordinatesType value) {
        setTupleList(value);
        return this;
    }

    public DataBlockType withDoubleOrNilReasonTupleList(String... values) {
        if (values!= null) {
            for (String value: values) {
                getDoubleOrNilReasonTupleList().add(value);
            }
        }
        return this;
    }

    public DataBlockType withDoubleOrNilReasonTupleList(Collection<String> values) {
        if (values!= null) {
            getDoubleOrNilReasonTupleList().addAll(values);
        }
        return this;
    }

    public DataBlockType withDoubleOrNilReasonTupleList(List<String> value) {
        setDoubleOrNilReasonTupleList(value);
        return this;
    }

}
