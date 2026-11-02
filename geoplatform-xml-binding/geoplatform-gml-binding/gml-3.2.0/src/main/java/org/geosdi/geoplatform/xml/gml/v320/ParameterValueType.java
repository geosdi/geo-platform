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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlList;
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
 * <p>Classe Java per ParameterValueType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ParameterValueType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml}AbstractGeneralParameterValueType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml}value"/>
 *           <element ref="{http://www.opengis.net/gml}dmsAngleValue"/>
 *           <element ref="{http://www.opengis.net/gml}stringValue"/>
 *           <element ref="{http://www.opengis.net/gml}integerValue"/>
 *           <element ref="{http://www.opengis.net/gml}booleanValue"/>
 *           <element ref="{http://www.opengis.net/gml}valueList"/>
 *           <element ref="{http://www.opengis.net/gml}integerValueList"/>
 *           <element ref="{http://www.opengis.net/gml}valueFile"/>
 *         </choice>
 *         <element ref="{http://www.opengis.net/gml}operationParameter"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParameterValueType", propOrder = {
    "value",
    "dmsAngleValue",
    "stringValue",
    "integerValue",
    "booleanValue",
    "valueList",
    "integerValueList",
    "valueFile",
    "operationParameter"
})
public class ParameterValueType
    extends AbstractGeneralParameterValueType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * gml:value is a numeric value of an operation parameter, with its associated unit of measure.
     * 
     */
    protected MeasureType value;
    protected DMSAngleType dmsAngleValue;
    /**
     * gml:stringValue is a character string value of an operation parameter. A string value does not have an associated unit of measure.
     * 
     */
    protected String stringValue;
    /**
     * gml:integerValue is a positive integer value of an operation parameter, usually used for a count. An integer value does not have an associated unit of measure.
     * 
     */
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger integerValue;
    /**
     * gml:booleanValue is a boolean value of an operation parameter. A Boolean value does not have an associated unit of measure.
     * 
     */
    protected java.lang.Boolean booleanValue;
    /**
     * gml:valueList is an ordered sequence of two or more numeric values of an operation parameter list, where each value has the same associated unit of measure. An element of this type contains a space-separated sequence of double values.
     * 
     */
    protected MeasureListType valueList;
    /**
     * gml:integerValueList is an ordered sequence of two or more integer values of an operation parameter list, usually used for counts. These integer values do not have an associated unit of measure. An element of this type contains a space-separated sequence of integer values.
     * 
     */
    @XmlList
    protected List<BigInteger> integerValueList;
    /**
     * gml:valueFile is a reference to a file or a part of a file containing one or more parameter values, each numeric value with its associated unit of measure. When referencing a part of a file, that file shall contain multiple identified parts, such as an XML encoded document. Furthermore, the referenced file or part of a file may reference another part of the same or different files, as allowed in XML documents.
     * 
     */
    @XmlSchemaType(name = "anyURI")
    protected String valueFile;
    /**
     * gml:operationParameter is an association role to the operation parameter of which this is a value.
     * 
     */
    @XmlElementRef(name = "operationParameter", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<OperationParameterPropertyType> operationParameter;

    /**
     * gml:value is a numeric value of an operation parameter, with its associated unit of measure.
     * 
     * @return
     *     possible object is
     *     {@link MeasureType }
     *     
     */
    public MeasureType getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureType }
     *     
     * @see #getValue()
     */
    public void setValue(MeasureType value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value!= null);
    }

    /**
     * Recupera il valore della proprietà dmsAngleValue.
     * 
     * @return
     *     possible object is
     *     {@link DMSAngleType }
     *     
     */
    public DMSAngleType getDmsAngleValue() {
        return dmsAngleValue;
    }

    /**
     * Imposta il valore della proprietà dmsAngleValue.
     * 
     * @param value
     *     allowed object is
     *     {@link DMSAngleType }
     *     
     */
    public void setDmsAngleValue(DMSAngleType value) {
        this.dmsAngleValue = value;
    }

    public boolean isSetDmsAngleValue() {
        return (this.dmsAngleValue!= null);
    }

    /**
     * gml:stringValue is a character string value of an operation parameter. A string value does not have an associated unit of measure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * Imposta il valore della proprietà stringValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getStringValue()
     */
    public void setStringValue(String value) {
        this.stringValue = value;
    }

    public boolean isSetStringValue() {
        return (this.stringValue!= null);
    }

    /**
     * gml:integerValue is a positive integer value of an operation parameter, usually used for a count. An integer value does not have an associated unit of measure.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIntegerValue() {
        return integerValue;
    }

    /**
     * Imposta il valore della proprietà integerValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getIntegerValue()
     */
    public void setIntegerValue(BigInteger value) {
        this.integerValue = value;
    }

    public boolean isSetIntegerValue() {
        return (this.integerValue!= null);
    }

    /**
     * gml:booleanValue is a boolean value of an operation parameter. A Boolean value does not have an associated unit of measure.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Boolean }
     *     
     */
    public java.lang.Boolean isBooleanValue() {
        return booleanValue;
    }

    /**
     * Imposta il valore della proprietà booleanValue.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Boolean }
     *     
     * @see #isBooleanValue()
     */
    public void setBooleanValue(java.lang.Boolean value) {
        this.booleanValue = value;
    }

    public boolean isSetBooleanValue() {
        return (this.booleanValue!= null);
    }

    /**
     * gml:valueList is an ordered sequence of two or more numeric values of an operation parameter list, where each value has the same associated unit of measure. An element of this type contains a space-separated sequence of double values.
     * 
     * @return
     *     possible object is
     *     {@link MeasureListType }
     *     
     */
    public MeasureListType getValueList() {
        return valueList;
    }

    /**
     * Imposta il valore della proprietà valueList.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasureListType }
     *     
     * @see #getValueList()
     */
    public void setValueList(MeasureListType value) {
        this.valueList = value;
    }

    public boolean isSetValueList() {
        return (this.valueList!= null);
    }

    /**
     * gml:integerValueList is an ordered sequence of two or more integer values of an operation parameter list, usually used for counts. These integer values do not have an associated unit of measure. An element of this type contains a space-separated sequence of integer values.
     * 
     * Gets the value of the integerValueList property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the integerValueList property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getIntegerValueList().add(newItem);
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
     *     The value of the integerValueList property.
     */
    public List<BigInteger> getIntegerValueList() {
        if (integerValueList == null) {
            integerValueList = new ArrayList<>();
        }
        return this.integerValueList;
    }

    public boolean isSetIntegerValueList() {
        return ((this.integerValueList!= null)&&(!this.integerValueList.isEmpty()));
    }

    public void unsetIntegerValueList() {
        this.integerValueList = null;
    }

    /**
     * gml:valueFile is a reference to a file or a part of a file containing one or more parameter values, each numeric value with its associated unit of measure. When referencing a part of a file, that file shall contain multiple identified parts, such as an XML encoded document. Furthermore, the referenced file or part of a file may reference another part of the same or different files, as allowed in XML documents.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueFile() {
        return valueFile;
    }

    /**
     * Imposta il valore della proprietà valueFile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getValueFile()
     */
    public void setValueFile(String value) {
        this.valueFile = value;
    }

    public boolean isSetValueFile() {
        return (this.valueFile!= null);
    }

    /**
     * gml:operationParameter is an association role to the operation parameter of which this is a value.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link OperationParameterPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OperationParameterPropertyType }{@code >}
     *     
     */
    public JAXBElement<OperationParameterPropertyType> getOperationParameter() {
        return operationParameter;
    }

    /**
     * Imposta il valore della proprietà operationParameter.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link OperationParameterPropertyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link OperationParameterPropertyType }{@code >}
     *     
     * @see #getOperationParameter()
     */
    public void setOperationParameter(JAXBElement<OperationParameterPropertyType> value) {
        this.operationParameter = value;
    }

    public boolean isSetOperationParameter() {
        return (this.operationParameter!= null);
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
            MeasureType theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            DMSAngleType theDmsAngleValue;
            theDmsAngleValue = this.getDmsAngleValue();
            strategy.appendField(locator, this, "dmsAngleValue", buffer, theDmsAngleValue, this.isSetDmsAngleValue());
        }
        {
            String theStringValue;
            theStringValue = this.getStringValue();
            strategy.appendField(locator, this, "stringValue", buffer, theStringValue, this.isSetStringValue());
        }
        {
            BigInteger theIntegerValue;
            theIntegerValue = this.getIntegerValue();
            strategy.appendField(locator, this, "integerValue", buffer, theIntegerValue, this.isSetIntegerValue());
        }
        {
            java.lang.Boolean theBooleanValue;
            theBooleanValue = this.isBooleanValue();
            strategy.appendField(locator, this, "booleanValue", buffer, theBooleanValue, this.isSetBooleanValue());
        }
        {
            MeasureListType theValueList;
            theValueList = this.getValueList();
            strategy.appendField(locator, this, "valueList", buffer, theValueList, this.isSetValueList());
        }
        {
            List<BigInteger> theIntegerValueList;
            theIntegerValueList = (this.isSetIntegerValueList()?this.getIntegerValueList():null);
            strategy.appendField(locator, this, "integerValueList", buffer, theIntegerValueList, this.isSetIntegerValueList());
        }
        {
            String theValueFile;
            theValueFile = this.getValueFile();
            strategy.appendField(locator, this, "valueFile", buffer, theValueFile, this.isSetValueFile());
        }
        {
            JAXBElement<OperationParameterPropertyType> theOperationParameter;
            theOperationParameter = this.getOperationParameter();
            strategy.appendField(locator, this, "operationParameter", buffer, theOperationParameter, this.isSetOperationParameter());
        }
        return buffer;
    }

    public void setIntegerValueList(List<BigInteger> value) {
        this.integerValueList = null;
        if (value!= null) {
            List<BigInteger> draftl = this.getIntegerValueList();
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
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ParameterValueType that = ((ParameterValueType) object);
        {
            MeasureType lhsValue;
            lhsValue = this.getValue();
            MeasureType rhsValue;
            rhsValue = that.getValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue, this.isSetValue(), that.isSetValue())) {
                return false;
            }
        }
        {
            DMSAngleType lhsDmsAngleValue;
            lhsDmsAngleValue = this.getDmsAngleValue();
            DMSAngleType rhsDmsAngleValue;
            rhsDmsAngleValue = that.getDmsAngleValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dmsAngleValue", lhsDmsAngleValue), LocatorUtils.property(thatLocator, "dmsAngleValue", rhsDmsAngleValue), lhsDmsAngleValue, rhsDmsAngleValue, this.isSetDmsAngleValue(), that.isSetDmsAngleValue())) {
                return false;
            }
        }
        {
            String lhsStringValue;
            lhsStringValue = this.getStringValue();
            String rhsStringValue;
            rhsStringValue = that.getStringValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "stringValue", lhsStringValue), LocatorUtils.property(thatLocator, "stringValue", rhsStringValue), lhsStringValue, rhsStringValue, this.isSetStringValue(), that.isSetStringValue())) {
                return false;
            }
        }
        {
            BigInteger lhsIntegerValue;
            lhsIntegerValue = this.getIntegerValue();
            BigInteger rhsIntegerValue;
            rhsIntegerValue = that.getIntegerValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "integerValue", lhsIntegerValue), LocatorUtils.property(thatLocator, "integerValue", rhsIntegerValue), lhsIntegerValue, rhsIntegerValue, this.isSetIntegerValue(), that.isSetIntegerValue())) {
                return false;
            }
        }
        {
            java.lang.Boolean lhsBooleanValue;
            lhsBooleanValue = this.isBooleanValue();
            java.lang.Boolean rhsBooleanValue;
            rhsBooleanValue = that.isBooleanValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "booleanValue", lhsBooleanValue), LocatorUtils.property(thatLocator, "booleanValue", rhsBooleanValue), lhsBooleanValue, rhsBooleanValue, this.isSetBooleanValue(), that.isSetBooleanValue())) {
                return false;
            }
        }
        {
            MeasureListType lhsValueList;
            lhsValueList = this.getValueList();
            MeasureListType rhsValueList;
            rhsValueList = that.getValueList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueList", lhsValueList), LocatorUtils.property(thatLocator, "valueList", rhsValueList), lhsValueList, rhsValueList, this.isSetValueList(), that.isSetValueList())) {
                return false;
            }
        }
        {
            List<BigInteger> lhsIntegerValueList;
            lhsIntegerValueList = (this.isSetIntegerValueList()?this.getIntegerValueList():null);
            List<BigInteger> rhsIntegerValueList;
            rhsIntegerValueList = (that.isSetIntegerValueList()?that.getIntegerValueList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "integerValueList", lhsIntegerValueList), LocatorUtils.property(thatLocator, "integerValueList", rhsIntegerValueList), lhsIntegerValueList, rhsIntegerValueList, this.isSetIntegerValueList(), that.isSetIntegerValueList())) {
                return false;
            }
        }
        {
            String lhsValueFile;
            lhsValueFile = this.getValueFile();
            String rhsValueFile;
            rhsValueFile = that.getValueFile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueFile", lhsValueFile), LocatorUtils.property(thatLocator, "valueFile", rhsValueFile), lhsValueFile, rhsValueFile, this.isSetValueFile(), that.isSetValueFile())) {
                return false;
            }
        }
        {
            JAXBElement<OperationParameterPropertyType> lhsOperationParameter;
            lhsOperationParameter = this.getOperationParameter();
            JAXBElement<OperationParameterPropertyType> rhsOperationParameter;
            rhsOperationParameter = that.getOperationParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "operationParameter", lhsOperationParameter), LocatorUtils.property(thatLocator, "operationParameter", rhsOperationParameter), lhsOperationParameter, rhsOperationParameter, this.isSetOperationParameter(), that.isSetOperationParameter())) {
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
            MeasureType theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue, this.isSetValue());
        }
        {
            DMSAngleType theDmsAngleValue;
            theDmsAngleValue = this.getDmsAngleValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dmsAngleValue", theDmsAngleValue), currentHashCode, theDmsAngleValue, this.isSetDmsAngleValue());
        }
        {
            String theStringValue;
            theStringValue = this.getStringValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "stringValue", theStringValue), currentHashCode, theStringValue, this.isSetStringValue());
        }
        {
            BigInteger theIntegerValue;
            theIntegerValue = this.getIntegerValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "integerValue", theIntegerValue), currentHashCode, theIntegerValue, this.isSetIntegerValue());
        }
        {
            java.lang.Boolean theBooleanValue;
            theBooleanValue = this.isBooleanValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "booleanValue", theBooleanValue), currentHashCode, theBooleanValue, this.isSetBooleanValue());
        }
        {
            MeasureListType theValueList;
            theValueList = this.getValueList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueList", theValueList), currentHashCode, theValueList, this.isSetValueList());
        }
        {
            List<BigInteger> theIntegerValueList;
            theIntegerValueList = (this.isSetIntegerValueList()?this.getIntegerValueList():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "integerValueList", theIntegerValueList), currentHashCode, theIntegerValueList, this.isSetIntegerValueList());
        }
        {
            String theValueFile;
            theValueFile = this.getValueFile();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueFile", theValueFile), currentHashCode, theValueFile, this.isSetValueFile());
        }
        {
            JAXBElement<OperationParameterPropertyType> theOperationParameter;
            theOperationParameter = this.getOperationParameter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "operationParameter", theOperationParameter), currentHashCode, theOperationParameter, this.isSetOperationParameter());
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
        if (draftCopy instanceof ParameterValueType) {
            final ParameterValueType copy = ((ParameterValueType) draftCopy);
            {
                java.lang.Boolean valueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValue());
                if (valueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType sourceValue;
                    sourceValue = this.getValue();
                    MeasureType copyValue = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue, this.isSetValue()));
                    copy.setValue(copyValue);
                } else {
                    if (valueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.value = null;
                    }
                }
            }
            {
                java.lang.Boolean dmsAngleValueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDmsAngleValue());
                if (dmsAngleValueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DMSAngleType sourceDmsAngleValue;
                    sourceDmsAngleValue = this.getDmsAngleValue();
                    DMSAngleType copyDmsAngleValue = ((DMSAngleType) strategy.copy(LocatorUtils.property(locator, "dmsAngleValue", sourceDmsAngleValue), sourceDmsAngleValue, this.isSetDmsAngleValue()));
                    copy.setDmsAngleValue(copyDmsAngleValue);
                } else {
                    if (dmsAngleValueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.dmsAngleValue = null;
                    }
                }
            }
            {
                java.lang.Boolean stringValueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetStringValue());
                if (stringValueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceStringValue;
                    sourceStringValue = this.getStringValue();
                    String copyStringValue = ((String) strategy.copy(LocatorUtils.property(locator, "stringValue", sourceStringValue), sourceStringValue, this.isSetStringValue()));
                    copy.setStringValue(copyStringValue);
                } else {
                    if (stringValueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.stringValue = null;
                    }
                }
            }
            {
                java.lang.Boolean integerValueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIntegerValue());
                if (integerValueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger sourceIntegerValue;
                    sourceIntegerValue = this.getIntegerValue();
                    BigInteger copyIntegerValue = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "integerValue", sourceIntegerValue), sourceIntegerValue, this.isSetIntegerValue()));
                    copy.setIntegerValue(copyIntegerValue);
                } else {
                    if (integerValueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.integerValue = null;
                    }
                }
            }
            {
                java.lang.Boolean booleanValueShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetBooleanValue());
                if (booleanValueShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    java.lang.Boolean sourceBooleanValue;
                    sourceBooleanValue = this.isBooleanValue();
                    java.lang.Boolean copyBooleanValue = ((java.lang.Boolean) strategy.copy(LocatorUtils.property(locator, "booleanValue", sourceBooleanValue), sourceBooleanValue, this.isSetBooleanValue()));
                    copy.setBooleanValue(copyBooleanValue);
                } else {
                    if (booleanValueShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.booleanValue = null;
                    }
                }
            }
            {
                java.lang.Boolean valueListShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValueList());
                if (valueListShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    MeasureListType sourceValueList;
                    sourceValueList = this.getValueList();
                    MeasureListType copyValueList = ((MeasureListType) strategy.copy(LocatorUtils.property(locator, "valueList", sourceValueList), sourceValueList, this.isSetValueList()));
                    copy.setValueList(copyValueList);
                } else {
                    if (valueListShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.valueList = null;
                    }
                }
            }
            {
                java.lang.Boolean integerValueListShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetIntegerValueList());
                if (integerValueListShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<BigInteger> sourceIntegerValueList;
                    sourceIntegerValueList = (this.isSetIntegerValueList()?this.getIntegerValueList():null);
                    @SuppressWarnings("unchecked")
                    List<BigInteger> copyIntegerValueList = ((List<BigInteger> ) strategy.copy(LocatorUtils.property(locator, "integerValueList", sourceIntegerValueList), sourceIntegerValueList, this.isSetIntegerValueList()));
                    copy.setIntegerValueList(copyIntegerValueList);
                } else {
                    if (integerValueListShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetIntegerValueList();
                    }
                }
            }
            {
                java.lang.Boolean valueFileShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValueFile());
                if (valueFileShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceValueFile;
                    sourceValueFile = this.getValueFile();
                    String copyValueFile = ((String) strategy.copy(LocatorUtils.property(locator, "valueFile", sourceValueFile), sourceValueFile, this.isSetValueFile()));
                    copy.setValueFile(copyValueFile);
                } else {
                    if (valueFileShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.valueFile = null;
                    }
                }
            }
            {
                java.lang.Boolean operationParameterShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetOperationParameter());
                if (operationParameterShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<OperationParameterPropertyType> sourceOperationParameter;
                    sourceOperationParameter = this.getOperationParameter();
                    @SuppressWarnings("unchecked")
                    JAXBElement<OperationParameterPropertyType> copyOperationParameter = ((JAXBElement<OperationParameterPropertyType> ) strategy.copy(LocatorUtils.property(locator, "operationParameter", sourceOperationParameter), sourceOperationParameter, this.isSetOperationParameter()));
                    copy.setOperationParameter(copyOperationParameter);
                } else {
                    if (operationParameterShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.operationParameter = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new ParameterValueType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ParameterValueType) {
            final ParameterValueType target = this;
            final ParameterValueType leftObject = ((ParameterValueType) left);
            final ParameterValueType rightObject = ((ParameterValueType) right);
            {
                java.lang.Boolean valueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValue(), rightObject.isSetValue());
                if (valueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    MeasureType lhsValue;
                    lhsValue = leftObject.getValue();
                    MeasureType rhsValue;
                    rhsValue = rightObject.getValue();
                    MeasureType mergedValue = ((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue, leftObject.isSetValue(), rightObject.isSetValue()));
                    target.setValue(mergedValue);
                } else {
                    if (valueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.value = null;
                    }
                }
            }
            {
                java.lang.Boolean dmsAngleValueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDmsAngleValue(), rightObject.isSetDmsAngleValue());
                if (dmsAngleValueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DMSAngleType lhsDmsAngleValue;
                    lhsDmsAngleValue = leftObject.getDmsAngleValue();
                    DMSAngleType rhsDmsAngleValue;
                    rhsDmsAngleValue = rightObject.getDmsAngleValue();
                    DMSAngleType mergedDmsAngleValue = ((DMSAngleType) strategy.merge(LocatorUtils.property(leftLocator, "dmsAngleValue", lhsDmsAngleValue), LocatorUtils.property(rightLocator, "dmsAngleValue", rhsDmsAngleValue), lhsDmsAngleValue, rhsDmsAngleValue, leftObject.isSetDmsAngleValue(), rightObject.isSetDmsAngleValue()));
                    target.setDmsAngleValue(mergedDmsAngleValue);
                } else {
                    if (dmsAngleValueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.dmsAngleValue = null;
                    }
                }
            }
            {
                java.lang.Boolean stringValueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetStringValue(), rightObject.isSetStringValue());
                if (stringValueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsStringValue;
                    lhsStringValue = leftObject.getStringValue();
                    String rhsStringValue;
                    rhsStringValue = rightObject.getStringValue();
                    String mergedStringValue = ((String) strategy.merge(LocatorUtils.property(leftLocator, "stringValue", lhsStringValue), LocatorUtils.property(rightLocator, "stringValue", rhsStringValue), lhsStringValue, rhsStringValue, leftObject.isSetStringValue(), rightObject.isSetStringValue()));
                    target.setStringValue(mergedStringValue);
                } else {
                    if (stringValueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.stringValue = null;
                    }
                }
            }
            {
                java.lang.Boolean integerValueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIntegerValue(), rightObject.isSetIntegerValue());
                if (integerValueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    BigInteger lhsIntegerValue;
                    lhsIntegerValue = leftObject.getIntegerValue();
                    BigInteger rhsIntegerValue;
                    rhsIntegerValue = rightObject.getIntegerValue();
                    BigInteger mergedIntegerValue = ((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "integerValue", lhsIntegerValue), LocatorUtils.property(rightLocator, "integerValue", rhsIntegerValue), lhsIntegerValue, rhsIntegerValue, leftObject.isSetIntegerValue(), rightObject.isSetIntegerValue()));
                    target.setIntegerValue(mergedIntegerValue);
                } else {
                    if (integerValueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.integerValue = null;
                    }
                }
            }
            {
                java.lang.Boolean booleanValueShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetBooleanValue(), rightObject.isSetBooleanValue());
                if (booleanValueShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    java.lang.Boolean lhsBooleanValue;
                    lhsBooleanValue = leftObject.isBooleanValue();
                    java.lang.Boolean rhsBooleanValue;
                    rhsBooleanValue = rightObject.isBooleanValue();
                    java.lang.Boolean mergedBooleanValue = ((java.lang.Boolean) strategy.merge(LocatorUtils.property(leftLocator, "booleanValue", lhsBooleanValue), LocatorUtils.property(rightLocator, "booleanValue", rhsBooleanValue), lhsBooleanValue, rhsBooleanValue, leftObject.isSetBooleanValue(), rightObject.isSetBooleanValue()));
                    target.setBooleanValue(mergedBooleanValue);
                } else {
                    if (booleanValueShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.booleanValue = null;
                    }
                }
            }
            {
                java.lang.Boolean valueListShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValueList(), rightObject.isSetValueList());
                if (valueListShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    MeasureListType lhsValueList;
                    lhsValueList = leftObject.getValueList();
                    MeasureListType rhsValueList;
                    rhsValueList = rightObject.getValueList();
                    MeasureListType mergedValueList = ((MeasureListType) strategy.merge(LocatorUtils.property(leftLocator, "valueList", lhsValueList), LocatorUtils.property(rightLocator, "valueList", rhsValueList), lhsValueList, rhsValueList, leftObject.isSetValueList(), rightObject.isSetValueList()));
                    target.setValueList(mergedValueList);
                } else {
                    if (valueListShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.valueList = null;
                    }
                }
            }
            {
                java.lang.Boolean integerValueListShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetIntegerValueList(), rightObject.isSetIntegerValueList());
                if (integerValueListShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<BigInteger> lhsIntegerValueList;
                    lhsIntegerValueList = (leftObject.isSetIntegerValueList()?leftObject.getIntegerValueList():null);
                    List<BigInteger> rhsIntegerValueList;
                    rhsIntegerValueList = (rightObject.isSetIntegerValueList()?rightObject.getIntegerValueList():null);
                    List<BigInteger> mergedIntegerValueList = ((List<BigInteger> ) strategy.merge(LocatorUtils.property(leftLocator, "integerValueList", lhsIntegerValueList), LocatorUtils.property(rightLocator, "integerValueList", rhsIntegerValueList), lhsIntegerValueList, rhsIntegerValueList, leftObject.isSetIntegerValueList(), rightObject.isSetIntegerValueList()));
                    target.setIntegerValueList(mergedIntegerValueList);
                } else {
                    if (integerValueListShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetIntegerValueList();
                    }
                }
            }
            {
                java.lang.Boolean valueFileShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValueFile(), rightObject.isSetValueFile());
                if (valueFileShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsValueFile;
                    lhsValueFile = leftObject.getValueFile();
                    String rhsValueFile;
                    rhsValueFile = rightObject.getValueFile();
                    String mergedValueFile = ((String) strategy.merge(LocatorUtils.property(leftLocator, "valueFile", lhsValueFile), LocatorUtils.property(rightLocator, "valueFile", rhsValueFile), lhsValueFile, rhsValueFile, leftObject.isSetValueFile(), rightObject.isSetValueFile()));
                    target.setValueFile(mergedValueFile);
                } else {
                    if (valueFileShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.valueFile = null;
                    }
                }
            }
            {
                java.lang.Boolean operationParameterShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetOperationParameter(), rightObject.isSetOperationParameter());
                if (operationParameterShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    JAXBElement<OperationParameterPropertyType> lhsOperationParameter;
                    lhsOperationParameter = leftObject.getOperationParameter();
                    JAXBElement<OperationParameterPropertyType> rhsOperationParameter;
                    rhsOperationParameter = rightObject.getOperationParameter();
                    JAXBElement<OperationParameterPropertyType> mergedOperationParameter = ((JAXBElement<OperationParameterPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "operationParameter", lhsOperationParameter), LocatorUtils.property(rightLocator, "operationParameter", rhsOperationParameter), lhsOperationParameter, rhsOperationParameter, leftObject.isSetOperationParameter(), rightObject.isSetOperationParameter()));
                    target.setOperationParameter(mergedOperationParameter);
                } else {
                    if (operationParameterShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.operationParameter = null;
                    }
                }
            }
        }
    }

}
