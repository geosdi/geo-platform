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
// Generato il: 2026.11.02 alle 03:48:04 PM CET
//

package org.geosdi.geoplatform.xml.gml.v321;

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
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per ParameterValueType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ParameterValueType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/gml/3.2}AbstractGeneralParameterValueType">
 *       <sequence>
 *         <choice>
 *           <element ref="{http://www.opengis.net/gml/3.2}value"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}dmsAngleValue"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}stringValue"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}integerValue"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}booleanValue"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}valueList"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}integerValueList"/>
 *           <element ref="{http://www.opengis.net/gml/3.2}valueFile"/>
 *         </choice>
 *         <element ref="{http://www.opengis.net/gml/3.2}operationParameter"/>
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
    implements ToString
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
    @XmlElementRef(name = "operationParameter", namespace = "http://www.opengis.net/gml/3.2", type = JAXBElement.class)
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

}
