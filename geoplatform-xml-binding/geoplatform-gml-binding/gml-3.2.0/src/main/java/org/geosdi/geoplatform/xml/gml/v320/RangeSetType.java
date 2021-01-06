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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
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
 * <p>Classe Java per RangeSetType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RangeSetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/gml}ValueArray" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.opengis.net/gml}AbstractScalarValueList" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.opengis.net/gml}DataBlock"/>
 *         &lt;element ref="{http://www.opengis.net/gml}File"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RangeSetType", propOrder = {
    "valueArray",
    "abstractScalarValueList",
    "dataBlock",
    "file"
})
public class RangeSetType
    implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(name = "ValueArray")
    protected List<ValueArrayType> valueArray;
    @XmlElementRef(name = "AbstractScalarValueList", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<?>> abstractScalarValueList;
    @XmlElement(name = "DataBlock")
    protected DataBlockType dataBlock;
    @XmlElement(name = "File")
    protected FileType file;

    /**
     * Gets the value of the valueArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueArrayType }
     * 
     * 
     */
    public List<ValueArrayType> getValueArray() {
        if (valueArray == null) {
            valueArray = new ArrayList<ValueArrayType>();
        }
        return this.valueArray;
    }

    public boolean isSetValueArray() {
        return ((this.valueArray!= null)&&(!this.valueArray.isEmpty()));
    }

    public void unsetValueArray() {
        this.valueArray = null;
    }

    /**
     * Gets the value of the abstractScalarValueList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abstractScalarValueList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbstractScalarValueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link MeasureOrNilReasonListType }{@code >}
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}
     * {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}
     * {@link JAXBElement }{@code <}{@link CodeOrNilReasonListType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getAbstractScalarValueList() {
        if (abstractScalarValueList == null) {
            abstractScalarValueList = new ArrayList<JAXBElement<?>>();
        }
        return this.abstractScalarValueList;
    }

    public boolean isSetAbstractScalarValueList() {
        return ((this.abstractScalarValueList!= null)&&(!this.abstractScalarValueList.isEmpty()));
    }

    public void unsetAbstractScalarValueList() {
        this.abstractScalarValueList = null;
    }

    /**
     * Recupera il valore della proprietà dataBlock.
     * 
     * @return
     *     possible object is
     *     {@link DataBlockType }
     *     
     */
    public DataBlockType getDataBlock() {
        return dataBlock;
    }

    /**
     * Imposta il valore della proprietà dataBlock.
     * 
     * @param value
     *     allowed object is
     *     {@link DataBlockType }
     *     
     */
    public void setDataBlock(DataBlockType value) {
        this.dataBlock = value;
    }

    public boolean isSetDataBlock() {
        return (this.dataBlock!= null);
    }

    /**
     * Recupera il valore della proprietà file.
     * 
     * @return
     *     possible object is
     *     {@link FileType }
     *     
     */
    public FileType getFile() {
        return file;
    }

    /**
     * Imposta il valore della proprietà file.
     * 
     * @param value
     *     allowed object is
     *     {@link FileType }
     *     
     */
    public void setFile(FileType value) {
        this.file = value;
    }

    public boolean isSetFile() {
        return (this.file!= null);
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
            List<ValueArrayType> theValueArray;
            theValueArray = this.getValueArray();
            strategy.appendField(locator, this, "valueArray", buffer, theValueArray);
        }
        {
            List<JAXBElement<?>> theAbstractScalarValueList;
            theAbstractScalarValueList = this.getAbstractScalarValueList();
            strategy.appendField(locator, this, "abstractScalarValueList", buffer, theAbstractScalarValueList);
        }
        {
            DataBlockType theDataBlock;
            theDataBlock = this.getDataBlock();
            strategy.appendField(locator, this, "dataBlock", buffer, theDataBlock);
        }
        {
            FileType theFile;
            theFile = this.getFile();
            strategy.appendField(locator, this, "file", buffer, theFile);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RangeSetType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RangeSetType that = ((RangeSetType) object);
        {
            List<ValueArrayType> lhsValueArray;
            lhsValueArray = this.getValueArray();
            List<ValueArrayType> rhsValueArray;
            rhsValueArray = that.getValueArray();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueArray", lhsValueArray), LocatorUtils.property(thatLocator, "valueArray", rhsValueArray), lhsValueArray, rhsValueArray)) {
                return false;
            }
        }
        {
            List<JAXBElement<?>> lhsAbstractScalarValueList;
            lhsAbstractScalarValueList = this.getAbstractScalarValueList();
            List<JAXBElement<?>> rhsAbstractScalarValueList;
            rhsAbstractScalarValueList = that.getAbstractScalarValueList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractScalarValueList", lhsAbstractScalarValueList), LocatorUtils.property(thatLocator, "abstractScalarValueList", rhsAbstractScalarValueList), lhsAbstractScalarValueList, rhsAbstractScalarValueList)) {
                return false;
            }
        }
        {
            DataBlockType lhsDataBlock;
            lhsDataBlock = this.getDataBlock();
            DataBlockType rhsDataBlock;
            rhsDataBlock = that.getDataBlock();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataBlock", lhsDataBlock), LocatorUtils.property(thatLocator, "dataBlock", rhsDataBlock), lhsDataBlock, rhsDataBlock)) {
                return false;
            }
        }
        {
            FileType lhsFile;
            lhsFile = this.getFile();
            FileType rhsFile;
            rhsFile = that.getFile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "file", lhsFile), LocatorUtils.property(thatLocator, "file", rhsFile), lhsFile, rhsFile)) {
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
            List<ValueArrayType> theValueArray;
            theValueArray = this.getValueArray();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueArray", theValueArray), currentHashCode, theValueArray);
        }
        {
            List<JAXBElement<?>> theAbstractScalarValueList;
            theAbstractScalarValueList = this.getAbstractScalarValueList();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractScalarValueList", theAbstractScalarValueList), currentHashCode, theAbstractScalarValueList);
        }
        {
            DataBlockType theDataBlock;
            theDataBlock = this.getDataBlock();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dataBlock", theDataBlock), currentHashCode, theDataBlock);
        }
        {
            FileType theFile;
            theFile = this.getFile();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "file", theFile), currentHashCode, theFile);
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
        if (draftCopy instanceof RangeSetType) {
            final RangeSetType copy = ((RangeSetType) draftCopy);
            if (this.isSetValueArray()) {
                List<ValueArrayType> sourceValueArray;
                sourceValueArray = this.getValueArray();
                @SuppressWarnings("unchecked")
                List<ValueArrayType> copyValueArray = ((List<ValueArrayType> ) strategy.copy(LocatorUtils.property(locator, "valueArray", sourceValueArray), sourceValueArray));
                copy.unsetValueArray();
                List<ValueArrayType> uniqueValueArrayl = copy.getValueArray();
                uniqueValueArrayl.addAll(copyValueArray);
            } else {
                copy.unsetValueArray();
            }
            if (this.isSetAbstractScalarValueList()) {
                List<JAXBElement<?>> sourceAbstractScalarValueList;
                sourceAbstractScalarValueList = this.getAbstractScalarValueList();
                @SuppressWarnings("unchecked")
                List<JAXBElement<?>> copyAbstractScalarValueList = ((List<JAXBElement<?>> ) strategy.copy(LocatorUtils.property(locator, "abstractScalarValueList", sourceAbstractScalarValueList), sourceAbstractScalarValueList));
                copy.unsetAbstractScalarValueList();
                List<JAXBElement<?>> uniqueAbstractScalarValueListl = copy.getAbstractScalarValueList();
                uniqueAbstractScalarValueListl.addAll(copyAbstractScalarValueList);
            } else {
                copy.unsetAbstractScalarValueList();
            }
            if (this.isSetDataBlock()) {
                DataBlockType sourceDataBlock;
                sourceDataBlock = this.getDataBlock();
                DataBlockType copyDataBlock = ((DataBlockType) strategy.copy(LocatorUtils.property(locator, "dataBlock", sourceDataBlock), sourceDataBlock));
                copy.setDataBlock(copyDataBlock);
            } else {
                copy.dataBlock = null;
            }
            if (this.isSetFile()) {
                FileType sourceFile;
                sourceFile = this.getFile();
                FileType copyFile = ((FileType) strategy.copy(LocatorUtils.property(locator, "file", sourceFile), sourceFile));
                copy.setFile(copyFile);
            } else {
                copy.file = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new RangeSetType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof RangeSetType) {
            final RangeSetType target = this;
            final RangeSetType leftObject = ((RangeSetType) left);
            final RangeSetType rightObject = ((RangeSetType) right);
            {
                List<ValueArrayType> lhsValueArray;
                lhsValueArray = leftObject.getValueArray();
                List<ValueArrayType> rhsValueArray;
                rhsValueArray = rightObject.getValueArray();
                target.unsetValueArray();
                List<ValueArrayType> uniqueValueArrayl = target.getValueArray();
                uniqueValueArrayl.addAll(((List<ValueArrayType> ) strategy.merge(LocatorUtils.property(leftLocator, "valueArray", lhsValueArray), LocatorUtils.property(rightLocator, "valueArray", rhsValueArray), lhsValueArray, rhsValueArray)));
            }
            {
                List<JAXBElement<?>> lhsAbstractScalarValueList;
                lhsAbstractScalarValueList = leftObject.getAbstractScalarValueList();
                List<JAXBElement<?>> rhsAbstractScalarValueList;
                rhsAbstractScalarValueList = rightObject.getAbstractScalarValueList();
                target.unsetAbstractScalarValueList();
                List<JAXBElement<?>> uniqueAbstractScalarValueListl = target.getAbstractScalarValueList();
                uniqueAbstractScalarValueListl.addAll(((List<JAXBElement<?>> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractScalarValueList", lhsAbstractScalarValueList), LocatorUtils.property(rightLocator, "abstractScalarValueList", rhsAbstractScalarValueList), lhsAbstractScalarValueList, rhsAbstractScalarValueList)));
            }
            {
                DataBlockType lhsDataBlock;
                lhsDataBlock = leftObject.getDataBlock();
                DataBlockType rhsDataBlock;
                rhsDataBlock = rightObject.getDataBlock();
                target.setDataBlock(((DataBlockType) strategy.merge(LocatorUtils.property(leftLocator, "dataBlock", lhsDataBlock), LocatorUtils.property(rightLocator, "dataBlock", rhsDataBlock), lhsDataBlock, rhsDataBlock)));
            }
            {
                FileType lhsFile;
                lhsFile = leftObject.getFile();
                FileType rhsFile;
                rhsFile = rightObject.getFile();
                target.setFile(((FileType) strategy.merge(LocatorUtils.property(leftLocator, "file", lhsFile), LocatorUtils.property(rightLocator, "file", rhsFile), lhsFile, rhsFile)));
            }
        }
    }

    public void setValueArray(List<ValueArrayType> value) {
        List<ValueArrayType> draftl = this.getValueArray();
        draftl.addAll(value);
    }

    public void setAbstractScalarValueList(List<JAXBElement<?>> value) {
        List<JAXBElement<?>> draftl = this.getAbstractScalarValueList();
        draftl.addAll(value);
    }

    public RangeSetType withValueArray(ValueArrayType... values) {
        if (values!= null) {
            for (ValueArrayType value: values) {
                getValueArray().add(value);
            }
        }
        return this;
    }

    public RangeSetType withValueArray(Collection<ValueArrayType> values) {
        if (values!= null) {
            getValueArray().addAll(values);
        }
        return this;
    }

    public RangeSetType withAbstractScalarValueList(JAXBElement<?> ... values) {
        if (values!= null) {
            for (JAXBElement<?> value: values) {
                getAbstractScalarValueList().add(value);
            }
        }
        return this;
    }

    public RangeSetType withAbstractScalarValueList(Collection<JAXBElement<?>> values) {
        if (values!= null) {
            getAbstractScalarValueList().addAll(values);
        }
        return this;
    }

    public RangeSetType withDataBlock(DataBlockType value) {
        setDataBlock(value);
        return this;
    }

    public RangeSetType withFile(FileType value) {
        setFile(value);
        return this;
    }

    public RangeSetType withValueArray(List<ValueArrayType> value) {
        setValueArray(value);
        return this;
    }

    public RangeSetType withAbstractScalarValueList(List<JAXBElement<?>> value) {
        setAbstractScalarValueList(value);
        return this;
    }

}
