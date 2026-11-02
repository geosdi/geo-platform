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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per FileType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FileType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/gml}rangeParameters"/>
 *         <choice>
 *           <element name="fileName" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *           <element name="fileReference" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         </choice>
 *         <element name="fileStructure" type="{http://www.opengis.net/gml}FileValueModelType"/>
 *         <element name="mimeType" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         <element name="compression" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileType", propOrder = {
    "rangeParameters",
    "fileName",
    "fileReference",
    "fileStructure",
    "mimeType",
    "compression"
})
public class FileType implements Cloneable, CopyTo, Equals, HashCode, MergeFrom, ToString
{

    @XmlElement(required = true)
    protected RangeParametersType rangeParameters;
    @XmlSchemaType(name = "anyURI")
    protected String fileName;
    @XmlSchemaType(name = "anyURI")
    protected String fileReference;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected FileValueModelType fileStructure;
    @XmlSchemaType(name = "anyURI")
    protected String mimeType;
    @XmlSchemaType(name = "anyURI")
    protected String compression;

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
     * Recupera il valore della proprietà fileName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Imposta il valore della proprietà fileName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    public boolean isSetFileName() {
        return (this.fileName!= null);
    }

    /**
     * Recupera il valore della proprietà fileReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileReference() {
        return fileReference;
    }

    /**
     * Imposta il valore della proprietà fileReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileReference(String value) {
        this.fileReference = value;
    }

    public boolean isSetFileReference() {
        return (this.fileReference!= null);
    }

    /**
     * Recupera il valore della proprietà fileStructure.
     * 
     * @return
     *     possible object is
     *     {@link FileValueModelType }
     *     
     */
    public FileValueModelType getFileStructure() {
        return fileStructure;
    }

    /**
     * Imposta il valore della proprietà fileStructure.
     * 
     * @param value
     *     allowed object is
     *     {@link FileValueModelType }
     *     
     */
    public void setFileStructure(FileValueModelType value) {
        this.fileStructure = value;
    }

    public boolean isSetFileStructure() {
        return (this.fileStructure!= null);
    }

    /**
     * Recupera il valore della proprietà mimeType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Imposta il valore della proprietà mimeType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    public boolean isSetMimeType() {
        return (this.mimeType!= null);
    }

    /**
     * Recupera il valore della proprietà compression.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompression() {
        return compression;
    }

    /**
     * Imposta il valore della proprietà compression.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompression(String value) {
        this.compression = value;
    }

    public boolean isSetCompression() {
        return (this.compression!= null);
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
            String theFileName;
            theFileName = this.getFileName();
            strategy.appendField(locator, this, "fileName", buffer, theFileName, this.isSetFileName());
        }
        {
            String theFileReference;
            theFileReference = this.getFileReference();
            strategy.appendField(locator, this, "fileReference", buffer, theFileReference, this.isSetFileReference());
        }
        {
            FileValueModelType theFileStructure;
            theFileStructure = this.getFileStructure();
            strategy.appendField(locator, this, "fileStructure", buffer, theFileStructure, this.isSetFileStructure());
        }
        {
            String theMimeType;
            theMimeType = this.getMimeType();
            strategy.appendField(locator, this, "mimeType", buffer, theMimeType, this.isSetMimeType());
        }
        {
            String theCompression;
            theCompression = this.getCompression();
            strategy.appendField(locator, this, "compression", buffer, theCompression, this.isSetCompression());
        }
        return buffer;
    }

    @Override
    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if ((object == null)||(this.getClass()!= object.getClass())) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FileType that = ((FileType) object);
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
            String lhsFileName;
            lhsFileName = this.getFileName();
            String rhsFileName;
            rhsFileName = that.getFileName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fileName", lhsFileName), LocatorUtils.property(thatLocator, "fileName", rhsFileName), lhsFileName, rhsFileName, this.isSetFileName(), that.isSetFileName())) {
                return false;
            }
        }
        {
            String lhsFileReference;
            lhsFileReference = this.getFileReference();
            String rhsFileReference;
            rhsFileReference = that.getFileReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fileReference", lhsFileReference), LocatorUtils.property(thatLocator, "fileReference", rhsFileReference), lhsFileReference, rhsFileReference, this.isSetFileReference(), that.isSetFileReference())) {
                return false;
            }
        }
        {
            FileValueModelType lhsFileStructure;
            lhsFileStructure = this.getFileStructure();
            FileValueModelType rhsFileStructure;
            rhsFileStructure = that.getFileStructure();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fileStructure", lhsFileStructure), LocatorUtils.property(thatLocator, "fileStructure", rhsFileStructure), lhsFileStructure, rhsFileStructure, this.isSetFileStructure(), that.isSetFileStructure())) {
                return false;
            }
        }
        {
            String lhsMimeType;
            lhsMimeType = this.getMimeType();
            String rhsMimeType;
            rhsMimeType = that.getMimeType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mimeType", lhsMimeType), LocatorUtils.property(thatLocator, "mimeType", rhsMimeType), lhsMimeType, rhsMimeType, this.isSetMimeType(), that.isSetMimeType())) {
                return false;
            }
        }
        {
            String lhsCompression;
            lhsCompression = this.getCompression();
            String rhsCompression;
            rhsCompression = that.getCompression();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "compression", lhsCompression), LocatorUtils.property(thatLocator, "compression", rhsCompression), lhsCompression, rhsCompression, this.isSetCompression(), that.isSetCompression())) {
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
            String theFileName;
            theFileName = this.getFileName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fileName", theFileName), currentHashCode, theFileName, this.isSetFileName());
        }
        {
            String theFileReference;
            theFileReference = this.getFileReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fileReference", theFileReference), currentHashCode, theFileReference, this.isSetFileReference());
        }
        {
            FileValueModelType theFileStructure;
            theFileStructure = this.getFileStructure();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fileStructure", theFileStructure), currentHashCode, theFileStructure, this.isSetFileStructure());
        }
        {
            String theMimeType;
            theMimeType = this.getMimeType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mimeType", theMimeType), currentHashCode, theMimeType, this.isSetMimeType());
        }
        {
            String theCompression;
            theCompression = this.getCompression();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "compression", theCompression), currentHashCode, theCompression, this.isSetCompression());
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
        if (draftCopy instanceof FileType) {
            final FileType copy = ((FileType) draftCopy);
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
                java.lang.Boolean fileNameShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFileName());
                if (fileNameShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceFileName;
                    sourceFileName = this.getFileName();
                    String copyFileName = ((String) strategy.copy(LocatorUtils.property(locator, "fileName", sourceFileName), sourceFileName, this.isSetFileName()));
                    copy.setFileName(copyFileName);
                } else {
                    if (fileNameShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.fileName = null;
                    }
                }
            }
            {
                java.lang.Boolean fileReferenceShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFileReference());
                if (fileReferenceShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceFileReference;
                    sourceFileReference = this.getFileReference();
                    String copyFileReference = ((String) strategy.copy(LocatorUtils.property(locator, "fileReference", sourceFileReference), sourceFileReference, this.isSetFileReference()));
                    copy.setFileReference(copyFileReference);
                } else {
                    if (fileReferenceShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.fileReference = null;
                    }
                }
            }
            {
                java.lang.Boolean fileStructureShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetFileStructure());
                if (fileStructureShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    FileValueModelType sourceFileStructure;
                    sourceFileStructure = this.getFileStructure();
                    FileValueModelType copyFileStructure = ((FileValueModelType) strategy.copy(LocatorUtils.property(locator, "fileStructure", sourceFileStructure), sourceFileStructure, this.isSetFileStructure()));
                    copy.setFileStructure(copyFileStructure);
                } else {
                    if (fileStructureShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.fileStructure = null;
                    }
                }
            }
            {
                java.lang.Boolean mimeTypeShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetMimeType());
                if (mimeTypeShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceMimeType;
                    sourceMimeType = this.getMimeType();
                    String copyMimeType = ((String) strategy.copy(LocatorUtils.property(locator, "mimeType", sourceMimeType), sourceMimeType, this.isSetMimeType()));
                    copy.setMimeType(copyMimeType);
                } else {
                    if (mimeTypeShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.mimeType = null;
                    }
                }
            }
            {
                java.lang.Boolean compressionShouldBeCopiedAndSet = strategy.shouldBeCopiedAndSet(locator, this.isSetCompression());
                if (compressionShouldBeCopiedAndSet == java.lang.Boolean.TRUE) {
                    String sourceCompression;
                    sourceCompression = this.getCompression();
                    String copyCompression = ((String) strategy.copy(LocatorUtils.property(locator, "compression", sourceCompression), sourceCompression, this.isSetCompression()));
                    copy.setCompression(copyCompression);
                } else {
                    if (compressionShouldBeCopiedAndSet == java.lang.Boolean.FALSE) {
                        copy.compression = null;
                    }
                }
            }
        }
        return draftCopy;
    }

    @Override
    public Object createNewInstance() {
        return new FileType();
    }

    @Override
    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.getInstance();
        mergeFrom(null, null, left, right, strategy);
    }

    @Override
    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof FileType) {
            final FileType target = this;
            final FileType leftObject = ((FileType) left);
            final FileType rightObject = ((FileType) right);
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
                java.lang.Boolean fileNameShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFileName(), rightObject.isSetFileName());
                if (fileNameShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsFileName;
                    lhsFileName = leftObject.getFileName();
                    String rhsFileName;
                    rhsFileName = rightObject.getFileName();
                    String mergedFileName = ((String) strategy.merge(LocatorUtils.property(leftLocator, "fileName", lhsFileName), LocatorUtils.property(rightLocator, "fileName", rhsFileName), lhsFileName, rhsFileName, leftObject.isSetFileName(), rightObject.isSetFileName()));
                    target.setFileName(mergedFileName);
                } else {
                    if (fileNameShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.fileName = null;
                    }
                }
            }
            {
                java.lang.Boolean fileReferenceShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFileReference(), rightObject.isSetFileReference());
                if (fileReferenceShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsFileReference;
                    lhsFileReference = leftObject.getFileReference();
                    String rhsFileReference;
                    rhsFileReference = rightObject.getFileReference();
                    String mergedFileReference = ((String) strategy.merge(LocatorUtils.property(leftLocator, "fileReference", lhsFileReference), LocatorUtils.property(rightLocator, "fileReference", rhsFileReference), lhsFileReference, rhsFileReference, leftObject.isSetFileReference(), rightObject.isSetFileReference()));
                    target.setFileReference(mergedFileReference);
                } else {
                    if (fileReferenceShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.fileReference = null;
                    }
                }
            }
            {
                java.lang.Boolean fileStructureShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetFileStructure(), rightObject.isSetFileStructure());
                if (fileStructureShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    FileValueModelType lhsFileStructure;
                    lhsFileStructure = leftObject.getFileStructure();
                    FileValueModelType rhsFileStructure;
                    rhsFileStructure = rightObject.getFileStructure();
                    FileValueModelType mergedFileStructure = ((FileValueModelType) strategy.merge(LocatorUtils.property(leftLocator, "fileStructure", lhsFileStructure), LocatorUtils.property(rightLocator, "fileStructure", rhsFileStructure), lhsFileStructure, rhsFileStructure, leftObject.isSetFileStructure(), rightObject.isSetFileStructure()));
                    target.setFileStructure(mergedFileStructure);
                } else {
                    if (fileStructureShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.fileStructure = null;
                    }
                }
            }
            {
                java.lang.Boolean mimeTypeShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetMimeType(), rightObject.isSetMimeType());
                if (mimeTypeShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsMimeType;
                    lhsMimeType = leftObject.getMimeType();
                    String rhsMimeType;
                    rhsMimeType = rightObject.getMimeType();
                    String mergedMimeType = ((String) strategy.merge(LocatorUtils.property(leftLocator, "mimeType", lhsMimeType), LocatorUtils.property(rightLocator, "mimeType", rhsMimeType), lhsMimeType, rhsMimeType, leftObject.isSetMimeType(), rightObject.isSetMimeType()));
                    target.setMimeType(mergedMimeType);
                } else {
                    if (mimeTypeShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.mimeType = null;
                    }
                }
            }
            {
                java.lang.Boolean compressionShouldBeMergedAndSet = strategy.shouldBeMergedAndSet(leftLocator, rightLocator, leftObject.isSetCompression(), rightObject.isSetCompression());
                if (compressionShouldBeMergedAndSet == java.lang.Boolean.TRUE) {
                    String lhsCompression;
                    lhsCompression = leftObject.getCompression();
                    String rhsCompression;
                    rhsCompression = rightObject.getCompression();
                    String mergedCompression = ((String) strategy.merge(LocatorUtils.property(leftLocator, "compression", lhsCompression), LocatorUtils.property(rightLocator, "compression", rhsCompression), lhsCompression, rhsCompression, leftObject.isSetCompression(), rightObject.isSetCompression()));
                    target.setCompression(mergedCompression);
                } else {
                    if (compressionShouldBeMergedAndSet == java.lang.Boolean.FALSE) {
                        target.compression = null;
                    }
                }
            }
        }
    }

}
