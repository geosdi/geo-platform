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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
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
 * <p>Classe Java per RangeSetType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="RangeSetType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <choice>
 *         <element ref="{http://www.opengis.net/gml}ValueArray" maxOccurs="unbounded"/>
 *         <element ref="{http://www.opengis.net/gml}AbstractScalarValueList" maxOccurs="unbounded"/>
 *         <element ref="{http://www.opengis.net/gml}DataBlock"/>
 *         <element ref="{http://www.opengis.net/gml}File"/>
 *       </choice>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
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
public class RangeSetType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    /**
     * A Value Array is used for homogeneous arrays of primitive and aggregate values.  
     * The member values may be scalars, composites, arrays or lists.
     * ValueArray has the same content model as CompositeValue, but the member values shall be homogeneous.  The element declaration contains a Schematron constraint which expresses this restriction precisely.  Since the members are homogeneous, the gml:referenceSystem (uom, codeSpace) may be specified on the gml:ValueArray itself and inherited by all the members if desired.
     * 
     */
    @XmlElement(name = "ValueArray")
    protected List<ValueArrayType> valueArray;
    /**
     * gml:AbstractScalarValueList is an abstract element which acts as the head of a substitution group which contains gml:BooleanList, gml:CategoryList, gml:CountList and gml:QuantityList, and (transitively) the elements in their substitution groups.
     * 
     */
    @XmlElementRef(name = "AbstractScalarValueList", namespace = "http://www.opengis.net/gml", type = JAXBElement.class, required = false)
    protected List<JAXBElement<?>> abstractScalarValueList;
    /**
     * gml:DataBlock describes the Range as a block of text encoded values similar to a Common Separated Value (CSV) representation.
     * The range set parameterization is described by the property gml:rangeParameters.
     * 
     */
    @XmlElement(name = "DataBlock")
    protected DataBlockType dataBlock;
    /**
     * for efficiency reasons, GML also provides a means of encoding the range set in an arbitrary external encoding, such as a binary file.  This encoding may be “well-known” but this is not required. This mode uses the gml:File element.
     * The values of the coverage (attribute values in the range set) are transmitted in a external file that is referenced from the XML structure described by gml:FileType.  The external file is referenced by the gml:fileReference property that is an anyURI (the gml:fileName property has been deprecated).  This means that the external file may be located remotely from the referencing GML instance. 
     * The gml:compression property points to a definition of a compression algorithm through an anyURI.  This may be a retrievable, computable definition or simply a reference to an unambiguous name for the compression method.
     * The gml:mimeType property points to a definition of the file mime type.
     * The gml:fileStructure property is defined by the gml:FileValueModelType.  This is simple enumerated type restriction on string.  The only value supported in GML is “Record Interleaved”.  Additional values may be supported in future releases of GML.  Note further that all values shall be enclosed in a single file. Multi-file structures for values are not supported in GML.
     * The semantics of the range set is described as above using the gml:rangeParameters property.
     * Note that if any compression algorithm is applied, the structure above applies only to the pre-compression or post-decompression structure of the file.
     * Note that the fields within a record match the gml:valueComponents of the gml:CompositeValue in document order.
     * 
     */
    @XmlElement(name = "File")
    protected FileType file;

    /**
     * A Value Array is used for homogeneous arrays of primitive and aggregate values.  
     * The member values may be scalars, composites, arrays or lists.
     * ValueArray has the same content model as CompositeValue, but the member values shall be homogeneous.  The element declaration contains a Schematron constraint which expresses this restriction precisely.  Since the members are homogeneous, the gml:referenceSystem (uom, codeSpace) may be specified on the gml:ValueArray itself and inherited by all the members if desired.
     * 
     * Gets the value of the valueArray property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the valueArray property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getValueArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValueArrayType }
     * </p>
     * 
     * 
     * @return
     *     The value of the valueArray property.
     */
    public List<ValueArrayType> getValueArray() {
        if (valueArray == null) {
            valueArray = new ArrayList<>();
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
     * gml:AbstractScalarValueList is an abstract element which acts as the head of a substitution group which contains gml:BooleanList, gml:CategoryList, gml:CountList and gml:QuantityList, and (transitively) the elements in their substitution groups.
     * 
     * Gets the value of the abstractScalarValueList property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the abstractScalarValueList property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAbstractScalarValueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Object }{@code >}
     * {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}
     * {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}
     * {@link JAXBElement }{@code <}{@link CodeOrNilReasonListType }{@code >}
     * {@link JAXBElement }{@code <}{@link MeasureOrNilReasonListType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the abstractScalarValueList property.
     */
    public List<JAXBElement<?>> getAbstractScalarValueList() {
        if (abstractScalarValueList == null) {
            abstractScalarValueList = new ArrayList<>();
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
     * gml:DataBlock describes the Range as a block of text encoded values similar to a Common Separated Value (CSV) representation.
     * The range set parameterization is described by the property gml:rangeParameters.
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
     * @see #getDataBlock()
     */
    public void setDataBlock(DataBlockType value) {
        this.dataBlock = value;
    }

    public boolean isSetDataBlock() {
        return (this.dataBlock!= null);
    }

    /**
     * for efficiency reasons, GML also provides a means of encoding the range set in an arbitrary external encoding, such as a binary file.  This encoding may be “well-known” but this is not required. This mode uses the gml:File element.
     * The values of the coverage (attribute values in the range set) are transmitted in a external file that is referenced from the XML structure described by gml:FileType.  The external file is referenced by the gml:fileReference property that is an anyURI (the gml:fileName property has been deprecated).  This means that the external file may be located remotely from the referencing GML instance. 
     * The gml:compression property points to a definition of a compression algorithm through an anyURI.  This may be a retrievable, computable definition or simply a reference to an unambiguous name for the compression method.
     * The gml:mimeType property points to a definition of the file mime type.
     * The gml:fileStructure property is defined by the gml:FileValueModelType.  This is simple enumerated type restriction on string.  The only value supported in GML is “Record Interleaved”.  Additional values may be supported in future releases of GML.  Note further that all values shall be enclosed in a single file. Multi-file structures for values are not supported in GML.
     * The semantics of the range set is described as above using the gml:rangeParameters property.
     * Note that if any compression algorithm is applied, the structure above applies only to the pre-compression or post-decompression structure of the file.
     * Note that the fields within a record match the gml:valueComponents of the gml:CompositeValue in document order.
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
     * @see #getFile()
     */
    public void setFile(FileType value) {
        this.file = value;
    }

    public boolean isSetFile() {
        return (this.file!= null);
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
            List<ValueArrayType> theValueArray;
            theValueArray = (this.isSetValueArray()?this.getValueArray():null);
            strategy.appendField(locator, this, "valueArray", buffer, theValueArray, this.isSetValueArray());
        }
        {
            List<JAXBElement<?>> theAbstractScalarValueList;
            theAbstractScalarValueList = (this.isSetAbstractScalarValueList()?this.getAbstractScalarValueList():null);
            strategy.appendField(locator, this, "abstractScalarValueList", buffer, theAbstractScalarValueList, this.isSetAbstractScalarValueList());
        }
        {
            DataBlockType theDataBlock;
            theDataBlock = this.getDataBlock();
            strategy.appendField(locator, this, "dataBlock", buffer, theDataBlock, this.isSetDataBlock());
        }
        {
            FileType theFile;
            theFile = this.getFile();
            strategy.appendField(locator, this, "file", buffer, theFile, this.isSetFile());
        }
        return buffer;
    }

    public void setValueArray(List<ValueArrayType> value) {
        this.valueArray = null;
        if (value!= null) {
            List<ValueArrayType> draftl = this.getValueArray();
            draftl.addAll(value);
        }
    }

    public void setAbstractScalarValueList(List<JAXBElement<?>> value) {
        this.abstractScalarValueList = null;
        if (value!= null) {
            List<JAXBElement<?>> draftl = this.getAbstractScalarValueList();
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
        final RangeSetType that = ((RangeSetType) object);
        {
            List<ValueArrayType> lhsValueArray;
            lhsValueArray = (this.isSetValueArray()?this.getValueArray():null);
            List<ValueArrayType> rhsValueArray;
            rhsValueArray = (that.isSetValueArray()?that.getValueArray():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "valueArray", lhsValueArray), LocatorUtils.property(thatLocator, "valueArray", rhsValueArray), lhsValueArray, rhsValueArray, this.isSetValueArray(), that.isSetValueArray())) {
                return false;
            }
        }
        {
            List<JAXBElement<?>> lhsAbstractScalarValueList;
            lhsAbstractScalarValueList = (this.isSetAbstractScalarValueList()?this.getAbstractScalarValueList():null);
            List<JAXBElement<?>> rhsAbstractScalarValueList;
            rhsAbstractScalarValueList = (that.isSetAbstractScalarValueList()?that.getAbstractScalarValueList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "abstractScalarValueList", lhsAbstractScalarValueList), LocatorUtils.property(thatLocator, "abstractScalarValueList", rhsAbstractScalarValueList), lhsAbstractScalarValueList, rhsAbstractScalarValueList, this.isSetAbstractScalarValueList(), that.isSetAbstractScalarValueList())) {
                return false;
            }
        }
        {
            DataBlockType lhsDataBlock;
            lhsDataBlock = this.getDataBlock();
            DataBlockType rhsDataBlock;
            rhsDataBlock = that.getDataBlock();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataBlock", lhsDataBlock), LocatorUtils.property(thatLocator, "dataBlock", rhsDataBlock), lhsDataBlock, rhsDataBlock, this.isSetDataBlock(), that.isSetDataBlock())) {
                return false;
            }
        }
        {
            FileType lhsFile;
            lhsFile = this.getFile();
            FileType rhsFile;
            rhsFile = that.getFile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "file", lhsFile), LocatorUtils.property(thatLocator, "file", rhsFile), lhsFile, rhsFile, this.isSetFile(), that.isSetFile())) {
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
            List<ValueArrayType> theValueArray;
            theValueArray = (this.isSetValueArray()?this.getValueArray():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "valueArray", theValueArray), currentHashCode, theValueArray, this.isSetValueArray());
        }
        {
            List<JAXBElement<?>> theAbstractScalarValueList;
            theAbstractScalarValueList = (this.isSetAbstractScalarValueList()?this.getAbstractScalarValueList():null);
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "abstractScalarValueList", theAbstractScalarValueList), currentHashCode, theAbstractScalarValueList, this.isSetAbstractScalarValueList());
        }
        {
            DataBlockType theDataBlock;
            theDataBlock = this.getDataBlock();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "dataBlock", theDataBlock), currentHashCode, theDataBlock, this.isSetDataBlock());
        }
        {
            FileType theFile;
            theFile = this.getFile();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "file", theFile), currentHashCode, theFile, this.isSetFile());
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
        if (draftCopy instanceof RangeSetType) {
            final RangeSetType copy = ((RangeSetType) draftCopy);
            {
                java.lang.Boolean valueArrayShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetValueArray());
                if (valueArrayShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<ValueArrayType> sourceValueArray;
                    sourceValueArray = (this.isSetValueArray()?this.getValueArray():null);
                    @SuppressWarnings("unchecked")
                    List<ValueArrayType> copyValueArray = ((List<ValueArrayType> ) strategy.copy(LocatorUtils.property(locator, "valueArray", sourceValueArray), sourceValueArray, this.isSetValueArray()));
                    copy.setValueArray(copyValueArray);
                } else {
                    if (valueArrayShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetValueArray();
                    }
                }
            }
            {
                java.lang.Boolean abstractScalarValueListShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetAbstractScalarValueList());
                if (abstractScalarValueListShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<?>> sourceAbstractScalarValueList;
                    sourceAbstractScalarValueList = (this.isSetAbstractScalarValueList()?this.getAbstractScalarValueList():null);
                    @SuppressWarnings("unchecked")
                    List<JAXBElement<?>> copyAbstractScalarValueList = ((List<JAXBElement<?>> ) strategy.copy(LocatorUtils.property(locator, "abstractScalarValueList", sourceAbstractScalarValueList), sourceAbstractScalarValueList, this.isSetAbstractScalarValueList()));
                    copy.setAbstractScalarValueList(copyAbstractScalarValueList);
                } else {
                    if (abstractScalarValueListShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.unsetAbstractScalarValueList();
                    }
                }
            }
            {
                java.lang.Boolean dataBlockShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetDataBlock());
                if (dataBlockShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    DataBlockType sourceDataBlock;
                    sourceDataBlock = this.getDataBlock();
                    DataBlockType copyDataBlock = ((DataBlockType) strategy.copy(LocatorUtils.property(locator, "dataBlock", sourceDataBlock), sourceDataBlock, this.isSetDataBlock()));
                    copy.setDataBlock(copyDataBlock);
                } else {
                    if (dataBlockShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.dataBlock = null;
                    }
                }
            }
            {
                java.lang.Boolean fileShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFile());
                if (fileShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    FileType sourceFile;
                    sourceFile = this.getFile();
                    FileType copyFile = ((FileType) strategy.copy(LocatorUtils.property(locator, "file", sourceFile), sourceFile, this.isSetFile()));
                    copy.setFile(copyFile);
                } else {
                    if (fileShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.file = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new RangeSetType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof RangeSetType) {
            final RangeSetType target = this;
            final RangeSetType leftObject = ((RangeSetType) left);
            final RangeSetType rightObject = ((RangeSetType) right);
            {
                java.lang.Boolean valueArrayShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetValueArray(), rightObject.isSetValueArray());
                if (valueArrayShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<ValueArrayType> lhsValueArray;
                    lhsValueArray = (leftObject.isSetValueArray()?leftObject.getValueArray():null);
                    List<ValueArrayType> rhsValueArray;
                    rhsValueArray = (rightObject.isSetValueArray()?rightObject.getValueArray():null);
                    List<ValueArrayType> mergedValueArray = ((List<ValueArrayType> ) strategy.merge(LocatorUtils.property(leftLocator, "valueArray", lhsValueArray), LocatorUtils.property(rightLocator, "valueArray", rhsValueArray), lhsValueArray, rhsValueArray, leftObject.isSetValueArray(), rightObject.isSetValueArray()));
                    target.setValueArray(mergedValueArray);
                } else {
                    if (valueArrayShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetValueArray();
                    }
                }
            }
            {
                java.lang.Boolean abstractScalarValueListShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetAbstractScalarValueList(), rightObject.isSetAbstractScalarValueList());
                if (abstractScalarValueListShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    List<JAXBElement<?>> lhsAbstractScalarValueList;
                    lhsAbstractScalarValueList = (leftObject.isSetAbstractScalarValueList()?leftObject.getAbstractScalarValueList():null);
                    List<JAXBElement<?>> rhsAbstractScalarValueList;
                    rhsAbstractScalarValueList = (rightObject.isSetAbstractScalarValueList()?rightObject.getAbstractScalarValueList():null);
                    List<JAXBElement<?>> mergedAbstractScalarValueList = ((List<JAXBElement<?>> ) strategy.merge(LocatorUtils.property(leftLocator, "abstractScalarValueList", lhsAbstractScalarValueList), LocatorUtils.property(rightLocator, "abstractScalarValueList", rhsAbstractScalarValueList), lhsAbstractScalarValueList, rhsAbstractScalarValueList, leftObject.isSetAbstractScalarValueList(), rightObject.isSetAbstractScalarValueList()));
                    target.setAbstractScalarValueList(mergedAbstractScalarValueList);
                } else {
                    if (abstractScalarValueListShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.unsetAbstractScalarValueList();
                    }
                }
            }
            {
                java.lang.Boolean dataBlockShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetDataBlock(), rightObject.isSetDataBlock());
                if (dataBlockShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    DataBlockType lhsDataBlock;
                    lhsDataBlock = leftObject.getDataBlock();
                    DataBlockType rhsDataBlock;
                    rhsDataBlock = rightObject.getDataBlock();
                    DataBlockType mergedDataBlock = ((DataBlockType) strategy.merge(LocatorUtils.property(leftLocator, "dataBlock", lhsDataBlock), LocatorUtils.property(rightLocator, "dataBlock", rhsDataBlock), lhsDataBlock, rhsDataBlock, leftObject.isSetDataBlock(), rightObject.isSetDataBlock()));
                    target.setDataBlock(mergedDataBlock);
                } else {
                    if (dataBlockShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.dataBlock = null;
                    }
                }
            }
            {
                java.lang.Boolean fileShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFile(), rightObject.isSetFile());
                if (fileShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    FileType lhsFile;
                    lhsFile = leftObject.getFile();
                    FileType rhsFile;
                    rhsFile = rightObject.getFile();
                    FileType mergedFile = ((FileType) strategy.merge(LocatorUtils.property(leftLocator, "file", lhsFile), LocatorUtils.property(rightLocator, "file", rhsFile), lhsFile, rhsFile, leftObject.isSetFile(), rightObject.isSetFile()));
                    target.setFile(mergedFile);
                } else {
                    if (fileShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.file = null;
                    }
                }
            }
        }
    }

}
