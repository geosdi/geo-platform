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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
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
 * <p>Classe Java per ParameterValueType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ParameterValueType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeneralParameterValueType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}value"/>
 *           &lt;element ref="{http://www.opengis.net/gml}dmsAngleValue"/>
 *           &lt;element ref="{http://www.opengis.net/gml}stringValue"/>
 *           &lt;element ref="{http://www.opengis.net/gml}integerValue"/>
 *           &lt;element ref="{http://www.opengis.net/gml}booleanValue"/>
 *           &lt;element ref="{http://www.opengis.net/gml}valueList"/>
 *           &lt;element ref="{http://www.opengis.net/gml}integerValueList"/>
 *           &lt;element ref="{http://www.opengis.net/gml}valueFile"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}operationParameter"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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

    protected MeasureType value;
    protected DMSAngleType dmsAngleValue;
    protected String stringValue;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger integerValue;
    protected java.lang.Boolean booleanValue;
    protected MeasureListType valueList;
    @XmlList
    protected List<BigInteger> integerValueList;
    @XmlSchemaType(name = "anyURI")
    protected String valueFile;
    @XmlElementRef(name = "operationParameter", namespace = "http://www.opengis.net/gml", type = JAXBElement.class)
    protected JAXBElement<OperationParameterPropertyType> operationParameter;

    /**
     * Recupera il valore della proprietà value.
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
     * Recupera il valore della proprietà stringValue.
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
     */
    public void setStringValue(String value) {
        this.stringValue = value;
    }

    public boolean isSetStringValue() {
        return (this.stringValue!= null);
    }

    /**
     * Recupera il valore della proprietà integerValue.
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
     */
    public void setIntegerValue(BigInteger value) {
        this.integerValue = value;
    }

    public boolean isSetIntegerValue() {
        return (this.integerValue!= null);
    }

    /**
     * Recupera il valore della proprietà booleanValue.
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
     */
    public void setBooleanValue(java.lang.Boolean value) {
        this.booleanValue = value;
    }

    public boolean isSetBooleanValue() {
        return (this.booleanValue!= null);
    }

    /**
     * Recupera il valore della proprietà valueList.
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
     */
    public void setValueList(MeasureListType value) {
        this.valueList = value;
    }

    public boolean isSetValueList() {
        return (this.valueList!= null);
    }

    /**
     * Gets the value of the integerValueList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the integerValueList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIntegerValueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getIntegerValueList() {
        if (integerValueList == null) {
            integerValueList = new ArrayList<BigInteger>();
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
     * Recupera il valore della proprietà valueFile.
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
     */
    public void setValueFile(String value) {
        this.valueFile = value;
    }

    public boolean isSetValueFile() {
        return (this.valueFile!= null);
    }

    /**
     * Recupera il valore della proprietà operationParameter.
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
     */
    public void setOperationParameter(JAXBElement<OperationParameterPropertyType> value) {
        this.operationParameter = value;
    }

    public boolean isSetOperationParameter() {
        return (this.operationParameter!= null);
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
        super.appendFields(locator, buffer, strategy);
        {
            MeasureType theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue);
        }
        {
            DMSAngleType theDmsAngleValue;
            theDmsAngleValue = this.getDmsAngleValue();
            strategy.appendField(locator, this, "dmsAngleValue", buffer, theDmsAngleValue);
        }
        {
            String theStringValue;
            theStringValue = this.getStringValue();
            strategy.appendField(locator, this, "stringValue", buffer, theStringValue);
        }
        {
            BigInteger theIntegerValue;
            theIntegerValue = this.getIntegerValue();
            strategy.appendField(locator, this, "integerValue", buffer, theIntegerValue);
        }
        {
            java.lang.Boolean theBooleanValue;
            theBooleanValue = this.isBooleanValue();
            strategy.appendField(locator, this, "booleanValue", buffer, theBooleanValue);
        }
        {
            MeasureListType theValueList;
            theValueList = this.getValueList();
            strategy.appendField(locator, this, "valueList", buffer, theValueList);
        }
        {
            List<BigInteger> theIntegerValueList;
            theIntegerValueList = this.getIntegerValueList();
            strategy.appendField(locator, this, "integerValueList", buffer, theIntegerValueList);
        }
        {
            String theValueFile;
            theValueFile = this.getValueFile();
            strategy.appendField(locator, this, "valueFile", buffer, theValueFile);
        }
        {
            JAXBElement<OperationParameterPropertyType> theOperationParameter;
            theOperationParameter = this.getOperationParameter();
            strategy.appendField(locator, this, "operationParameter", buffer, theOperationParameter);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ParameterValueType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "value", lhsValue), LocatorUtils.property(thatLocator, "value", rhsValue), lhsValue, rhsValue)) {
                return false;
            }
        }
        {
            DMSAngleType lhsDmsAngleValue;
            lhsDmsAngleValue = this.getDmsAngleValue();
            DMSAngleType rhsDmsAngleValue;
            rhsDmsAngleValue = that.getDmsAngleValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dmsAngleValue", lhsDmsAngleValue), LocatorUtils.property(thatLocator, "dmsAngleValue", rhsDmsAngleValue), lhsDmsAngleValue, rhsDmsAngleValue)) {
                return false;
            }
        }
        {
            String lhsStringValue;
            lhsStringValue = this.getStringValue();
            String rhsStringValue;
            rhsStringValue = that.getStringValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "stringValue", lhsStringValue), LocatorUtils.property(thatLocator, "stringValue", rhsStringValue), lhsStringValue, rhsStringValue)) {
                return false;
            }
        }
        {
            BigInteger lhsIntegerValue;
            lhsIntegerValue = this.getIntegerValue();
            BigInteger rhsIntegerValue;
            rhsIntegerValue = that.getIntegerValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "integerValue", lhsIntegerValue), LocatorUtils.property(thatLocator, "integerValue", rhsIntegerValue), lhsIntegerValue, rhsIntegerValue)) {
                return false;
            }
        }
        {
            java.lang.Boolean lhsBooleanValue;
            lhsBooleanValue = this.isBooleanValue();
            java.lang.Boolean rhsBooleanValue;
            rhsBooleanValue = that.isBooleanValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "booleanValue", lhsBooleanValue), LocatorUtils.property(thatLocator, "booleanValue", rhsBooleanValue), lhsBooleanValue, rhsBooleanValue)) {
                return false;
            }
        }
        {
            MeasureListType lhsValueList;
            lhsValueList = this.getValueList();
            MeasureListType rhsValueList;
            rhsValueList = that.getValueList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueList", lhsValueList), LocatorUtils.property(thatLocator, "valueList", rhsValueList), lhsValueList, rhsValueList)) {
                return false;
            }
        }
        {
            List<BigInteger> lhsIntegerValueList;
            lhsIntegerValueList = this.getIntegerValueList();
            List<BigInteger> rhsIntegerValueList;
            rhsIntegerValueList = that.getIntegerValueList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "integerValueList", lhsIntegerValueList), LocatorUtils.property(thatLocator, "integerValueList", rhsIntegerValueList), lhsIntegerValueList, rhsIntegerValueList)) {
                return false;
            }
        }
        {
            String lhsValueFile;
            lhsValueFile = this.getValueFile();
            String rhsValueFile;
            rhsValueFile = that.getValueFile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueFile", lhsValueFile), LocatorUtils.property(thatLocator, "valueFile", rhsValueFile), lhsValueFile, rhsValueFile)) {
                return false;
            }
        }
        {
            JAXBElement<OperationParameterPropertyType> lhsOperationParameter;
            lhsOperationParameter = this.getOperationParameter();
            JAXBElement<OperationParameterPropertyType> rhsOperationParameter;
            rhsOperationParameter = that.getOperationParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "operationParameter", lhsOperationParameter), LocatorUtils.property(thatLocator, "operationParameter", rhsOperationParameter), lhsOperationParameter, rhsOperationParameter)) {
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
        int currentHashCode = super.hashCode(locator, strategy);
        {
            MeasureType theValue;
            theValue = this.getValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "value", theValue), currentHashCode, theValue);
        }
        {
            DMSAngleType theDmsAngleValue;
            theDmsAngleValue = this.getDmsAngleValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dmsAngleValue", theDmsAngleValue), currentHashCode, theDmsAngleValue);
        }
        {
            String theStringValue;
            theStringValue = this.getStringValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "stringValue", theStringValue), currentHashCode, theStringValue);
        }
        {
            BigInteger theIntegerValue;
            theIntegerValue = this.getIntegerValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "integerValue", theIntegerValue), currentHashCode, theIntegerValue);
        }
        {
            java.lang.Boolean theBooleanValue;
            theBooleanValue = this.isBooleanValue();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "booleanValue", theBooleanValue), currentHashCode, theBooleanValue);
        }
        {
            MeasureListType theValueList;
            theValueList = this.getValueList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueList", theValueList), currentHashCode, theValueList);
        }
        {
            List<BigInteger> theIntegerValueList;
            theIntegerValueList = this.getIntegerValueList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "integerValueList", theIntegerValueList), currentHashCode, theIntegerValueList);
        }
        {
            String theValueFile;
            theValueFile = this.getValueFile();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueFile", theValueFile), currentHashCode, theValueFile);
        }
        {
            JAXBElement<OperationParameterPropertyType> theOperationParameter;
            theOperationParameter = this.getOperationParameter();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "operationParameter", theOperationParameter), currentHashCode, theOperationParameter);
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
        super.copyTo(locator, draftCopy, strategy);
        if (draftCopy instanceof ParameterValueType) {
            final ParameterValueType copy = ((ParameterValueType) draftCopy);
            if (this.isSetValue()) {
                MeasureType sourceValue;
                sourceValue = this.getValue();
                MeasureType copyValue = ((MeasureType) strategy.copy(LocatorUtils.property(locator, "value", sourceValue), sourceValue));
                copy.setValue(copyValue);
            } else {
                copy.value = null;
            }
            if (this.isSetDmsAngleValue()) {
                DMSAngleType sourceDmsAngleValue;
                sourceDmsAngleValue = this.getDmsAngleValue();
                DMSAngleType copyDmsAngleValue = ((DMSAngleType) strategy.copy(LocatorUtils.property(locator, "dmsAngleValue", sourceDmsAngleValue), sourceDmsAngleValue));
                copy.setDmsAngleValue(copyDmsAngleValue);
            } else {
                copy.dmsAngleValue = null;
            }
            if (this.isSetStringValue()) {
                String sourceStringValue;
                sourceStringValue = this.getStringValue();
                String copyStringValue = ((String) strategy.copy(LocatorUtils.property(locator, "stringValue", sourceStringValue), sourceStringValue));
                copy.setStringValue(copyStringValue);
            } else {
                copy.stringValue = null;
            }
            if (this.isSetIntegerValue()) {
                BigInteger sourceIntegerValue;
                sourceIntegerValue = this.getIntegerValue();
                BigInteger copyIntegerValue = ((BigInteger) strategy.copy(LocatorUtils.property(locator, "integerValue", sourceIntegerValue), sourceIntegerValue));
                copy.setIntegerValue(copyIntegerValue);
            } else {
                copy.integerValue = null;
            }
            if (this.isSetBooleanValue()) {
                java.lang.Boolean sourceBooleanValue;
                sourceBooleanValue = this.isBooleanValue();
                java.lang.Boolean copyBooleanValue = ((java.lang.Boolean) strategy.copy(LocatorUtils.property(locator, "booleanValue", sourceBooleanValue), sourceBooleanValue));
                copy.setBooleanValue(copyBooleanValue);
            } else {
                copy.booleanValue = null;
            }
            if (this.isSetValueList()) {
                MeasureListType sourceValueList;
                sourceValueList = this.getValueList();
                MeasureListType copyValueList = ((MeasureListType) strategy.copy(LocatorUtils.property(locator, "valueList", sourceValueList), sourceValueList));
                copy.setValueList(copyValueList);
            } else {
                copy.valueList = null;
            }
            if (this.isSetIntegerValueList()) {
                List<BigInteger> sourceIntegerValueList;
                sourceIntegerValueList = this.getIntegerValueList();
                @SuppressWarnings("unchecked")
                List<BigInteger> copyIntegerValueList = ((List<BigInteger> ) strategy.copy(LocatorUtils.property(locator, "integerValueList", sourceIntegerValueList), sourceIntegerValueList));
                copy.unsetIntegerValueList();
                List<BigInteger> uniqueIntegerValueListl = copy.getIntegerValueList();
                uniqueIntegerValueListl.addAll(copyIntegerValueList);
            } else {
                copy.unsetIntegerValueList();
            }
            if (this.isSetValueFile()) {
                String sourceValueFile;
                sourceValueFile = this.getValueFile();
                String copyValueFile = ((String) strategy.copy(LocatorUtils.property(locator, "valueFile", sourceValueFile), sourceValueFile));
                copy.setValueFile(copyValueFile);
            } else {
                copy.valueFile = null;
            }
            if (this.isSetOperationParameter()) {
                JAXBElement<OperationParameterPropertyType> sourceOperationParameter;
                sourceOperationParameter = this.getOperationParameter();
                @SuppressWarnings("unchecked")
                JAXBElement<OperationParameterPropertyType> copyOperationParameter = ((JAXBElement<OperationParameterPropertyType> ) strategy.copy(LocatorUtils.property(locator, "operationParameter", sourceOperationParameter), sourceOperationParameter));
                copy.setOperationParameter(copyOperationParameter);
            } else {
                copy.operationParameter = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new ParameterValueType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        super.mergeFrom(leftLocator, rightLocator, left, right, strategy);
        if (right instanceof ParameterValueType) {
            final ParameterValueType target = this;
            final ParameterValueType leftObject = ((ParameterValueType) left);
            final ParameterValueType rightObject = ((ParameterValueType) right);
            {
                MeasureType lhsValue;
                lhsValue = leftObject.getValue();
                MeasureType rhsValue;
                rhsValue = rightObject.getValue();
                target.setValue(((MeasureType) strategy.merge(LocatorUtils.property(leftLocator, "value", lhsValue), LocatorUtils.property(rightLocator, "value", rhsValue), lhsValue, rhsValue)));
            }
            {
                DMSAngleType lhsDmsAngleValue;
                lhsDmsAngleValue = leftObject.getDmsAngleValue();
                DMSAngleType rhsDmsAngleValue;
                rhsDmsAngleValue = rightObject.getDmsAngleValue();
                target.setDmsAngleValue(((DMSAngleType) strategy.merge(LocatorUtils.property(leftLocator, "dmsAngleValue", lhsDmsAngleValue), LocatorUtils.property(rightLocator, "dmsAngleValue", rhsDmsAngleValue), lhsDmsAngleValue, rhsDmsAngleValue)));
            }
            {
                String lhsStringValue;
                lhsStringValue = leftObject.getStringValue();
                String rhsStringValue;
                rhsStringValue = rightObject.getStringValue();
                target.setStringValue(((String) strategy.merge(LocatorUtils.property(leftLocator, "stringValue", lhsStringValue), LocatorUtils.property(rightLocator, "stringValue", rhsStringValue), lhsStringValue, rhsStringValue)));
            }
            {
                BigInteger lhsIntegerValue;
                lhsIntegerValue = leftObject.getIntegerValue();
                BigInteger rhsIntegerValue;
                rhsIntegerValue = rightObject.getIntegerValue();
                target.setIntegerValue(((BigInteger) strategy.merge(LocatorUtils.property(leftLocator, "integerValue", lhsIntegerValue), LocatorUtils.property(rightLocator, "integerValue", rhsIntegerValue), lhsIntegerValue, rhsIntegerValue)));
            }
            {
                java.lang.Boolean lhsBooleanValue;
                lhsBooleanValue = leftObject.isBooleanValue();
                java.lang.Boolean rhsBooleanValue;
                rhsBooleanValue = rightObject.isBooleanValue();
                target.setBooleanValue(((java.lang.Boolean) strategy.merge(LocatorUtils.property(leftLocator, "booleanValue", lhsBooleanValue), LocatorUtils.property(rightLocator, "booleanValue", rhsBooleanValue), lhsBooleanValue, rhsBooleanValue)));
            }
            {
                MeasureListType lhsValueList;
                lhsValueList = leftObject.getValueList();
                MeasureListType rhsValueList;
                rhsValueList = rightObject.getValueList();
                target.setValueList(((MeasureListType) strategy.merge(LocatorUtils.property(leftLocator, "valueList", lhsValueList), LocatorUtils.property(rightLocator, "valueList", rhsValueList), lhsValueList, rhsValueList)));
            }
            {
                List<BigInteger> lhsIntegerValueList;
                lhsIntegerValueList = leftObject.getIntegerValueList();
                List<BigInteger> rhsIntegerValueList;
                rhsIntegerValueList = rightObject.getIntegerValueList();
                target.unsetIntegerValueList();
                List<BigInteger> uniqueIntegerValueListl = target.getIntegerValueList();
                uniqueIntegerValueListl.addAll(((List<BigInteger> ) strategy.merge(LocatorUtils.property(leftLocator, "integerValueList", lhsIntegerValueList), LocatorUtils.property(rightLocator, "integerValueList", rhsIntegerValueList), lhsIntegerValueList, rhsIntegerValueList)));
            }
            {
                String lhsValueFile;
                lhsValueFile = leftObject.getValueFile();
                String rhsValueFile;
                rhsValueFile = rightObject.getValueFile();
                target.setValueFile(((String) strategy.merge(LocatorUtils.property(leftLocator, "valueFile", lhsValueFile), LocatorUtils.property(rightLocator, "valueFile", rhsValueFile), lhsValueFile, rhsValueFile)));
            }
            {
                JAXBElement<OperationParameterPropertyType> lhsOperationParameter;
                lhsOperationParameter = leftObject.getOperationParameter();
                JAXBElement<OperationParameterPropertyType> rhsOperationParameter;
                rhsOperationParameter = rightObject.getOperationParameter();
                target.setOperationParameter(((JAXBElement<OperationParameterPropertyType> ) strategy.merge(LocatorUtils.property(leftLocator, "operationParameter", lhsOperationParameter), LocatorUtils.property(rightLocator, "operationParameter", rhsOperationParameter), lhsOperationParameter, rhsOperationParameter)));
            }
        }
    }

    public void setIntegerValueList(List<BigInteger> value) {
        List<BigInteger> draftl = this.getIntegerValueList();
        draftl.addAll(value);
    }

    public ParameterValueType withValue(MeasureType value) {
        setValue(value);
        return this;
    }

    public ParameterValueType withDmsAngleValue(DMSAngleType value) {
        setDmsAngleValue(value);
        return this;
    }

    public ParameterValueType withStringValue(String value) {
        setStringValue(value);
        return this;
    }

    public ParameterValueType withIntegerValue(BigInteger value) {
        setIntegerValue(value);
        return this;
    }

    public ParameterValueType withBooleanValue(java.lang.Boolean value) {
        setBooleanValue(value);
        return this;
    }

    public ParameterValueType withValueList(MeasureListType value) {
        setValueList(value);
        return this;
    }

    public ParameterValueType withIntegerValueList(BigInteger... values) {
        if (values!= null) {
            for (BigInteger value: values) {
                getIntegerValueList().add(value);
            }
        }
        return this;
    }

    public ParameterValueType withIntegerValueList(Collection<BigInteger> values) {
        if (values!= null) {
            getIntegerValueList().addAll(values);
        }
        return this;
    }

    public ParameterValueType withValueFile(String value) {
        setValueFile(value);
        return this;
    }

    public ParameterValueType withOperationParameter(JAXBElement<OperationParameterPropertyType> value) {
        setOperationParameter(value);
        return this;
    }

    public ParameterValueType withIntegerValueList(List<BigInteger> value) {
        setIntegerValueList(value);
        return this;
    }

}
