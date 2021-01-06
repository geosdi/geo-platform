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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Classe Java per FileType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="FileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}rangeParameters"/>
 *         &lt;choice>
 *           &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *           &lt;element name="fileReference" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;/choice>
 *         &lt;element name="fileStructure" type="{http://www.opengis.net/gml}FileValueModelType"/>
 *         &lt;element name="mimeType" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="compression" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
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
            String theFileName;
            theFileName = this.getFileName();
            strategy.appendField(locator, this, "fileName", buffer, theFileName);
        }
        {
            String theFileReference;
            theFileReference = this.getFileReference();
            strategy.appendField(locator, this, "fileReference", buffer, theFileReference);
        }
        {
            FileValueModelType theFileStructure;
            theFileStructure = this.getFileStructure();
            strategy.appendField(locator, this, "fileStructure", buffer, theFileStructure);
        }
        {
            String theMimeType;
            theMimeType = this.getMimeType();
            strategy.appendField(locator, this, "mimeType", buffer, theMimeType);
        }
        {
            String theCompression;
            theCompression = this.getCompression();
            strategy.appendField(locator, this, "compression", buffer, theCompression);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FileType)) {
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
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rangeParameters", lhsRangeParameters), LocatorUtils.property(thatLocator, "rangeParameters", rhsRangeParameters), lhsRangeParameters, rhsRangeParameters)) {
                return false;
            }
        }
        {
            String lhsFileName;
            lhsFileName = this.getFileName();
            String rhsFileName;
            rhsFileName = that.getFileName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fileName", lhsFileName), LocatorUtils.property(thatLocator, "fileName", rhsFileName), lhsFileName, rhsFileName)) {
                return false;
            }
        }
        {
            String lhsFileReference;
            lhsFileReference = this.getFileReference();
            String rhsFileReference;
            rhsFileReference = that.getFileReference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fileReference", lhsFileReference), LocatorUtils.property(thatLocator, "fileReference", rhsFileReference), lhsFileReference, rhsFileReference)) {
                return false;
            }
        }
        {
            FileValueModelType lhsFileStructure;
            lhsFileStructure = this.getFileStructure();
            FileValueModelType rhsFileStructure;
            rhsFileStructure = that.getFileStructure();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fileStructure", lhsFileStructure), LocatorUtils.property(thatLocator, "fileStructure", rhsFileStructure), lhsFileStructure, rhsFileStructure)) {
                return false;
            }
        }
        {
            String lhsMimeType;
            lhsMimeType = this.getMimeType();
            String rhsMimeType;
            rhsMimeType = that.getMimeType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mimeType", lhsMimeType), LocatorUtils.property(thatLocator, "mimeType", rhsMimeType), lhsMimeType, rhsMimeType)) {
                return false;
            }
        }
        {
            String lhsCompression;
            lhsCompression = this.getCompression();
            String rhsCompression;
            rhsCompression = that.getCompression();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "compression", lhsCompression), LocatorUtils.property(thatLocator, "compression", rhsCompression), lhsCompression, rhsCompression)) {
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
            String theFileName;
            theFileName = this.getFileName();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fileName", theFileName), currentHashCode, theFileName);
        }
        {
            String theFileReference;
            theFileReference = this.getFileReference();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fileReference", theFileReference), currentHashCode, theFileReference);
        }
        {
            FileValueModelType theFileStructure;
            theFileStructure = this.getFileStructure();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "fileStructure", theFileStructure), currentHashCode, theFileStructure);
        }
        {
            String theMimeType;
            theMimeType = this.getMimeType();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "mimeType", theMimeType), currentHashCode, theMimeType);
        }
        {
            String theCompression;
            theCompression = this.getCompression();
            currentHashCode = strategy.hashCode(LocatorUtils.property(locator, "compression", theCompression), currentHashCode, theCompression);
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
        if (draftCopy instanceof FileType) {
            final FileType copy = ((FileType) draftCopy);
            if (this.isSetRangeParameters()) {
                RangeParametersType sourceRangeParameters;
                sourceRangeParameters = this.getRangeParameters();
                RangeParametersType copyRangeParameters = ((RangeParametersType) strategy.copy(LocatorUtils.property(locator, "rangeParameters", sourceRangeParameters), sourceRangeParameters));
                copy.setRangeParameters(copyRangeParameters);
            } else {
                copy.rangeParameters = null;
            }
            if (this.isSetFileName()) {
                String sourceFileName;
                sourceFileName = this.getFileName();
                String copyFileName = ((String) strategy.copy(LocatorUtils.property(locator, "fileName", sourceFileName), sourceFileName));
                copy.setFileName(copyFileName);
            } else {
                copy.fileName = null;
            }
            if (this.isSetFileReference()) {
                String sourceFileReference;
                sourceFileReference = this.getFileReference();
                String copyFileReference = ((String) strategy.copy(LocatorUtils.property(locator, "fileReference", sourceFileReference), sourceFileReference));
                copy.setFileReference(copyFileReference);
            } else {
                copy.fileReference = null;
            }
            if (this.isSetFileStructure()) {
                FileValueModelType sourceFileStructure;
                sourceFileStructure = this.getFileStructure();
                FileValueModelType copyFileStructure = ((FileValueModelType) strategy.copy(LocatorUtils.property(locator, "fileStructure", sourceFileStructure), sourceFileStructure));
                copy.setFileStructure(copyFileStructure);
            } else {
                copy.fileStructure = null;
            }
            if (this.isSetMimeType()) {
                String sourceMimeType;
                sourceMimeType = this.getMimeType();
                String copyMimeType = ((String) strategy.copy(LocatorUtils.property(locator, "mimeType", sourceMimeType), sourceMimeType));
                copy.setMimeType(copyMimeType);
            } else {
                copy.mimeType = null;
            }
            if (this.isSetCompression()) {
                String sourceCompression;
                sourceCompression = this.getCompression();
                String copyCompression = ((String) strategy.copy(LocatorUtils.property(locator, "compression", sourceCompression), sourceCompression));
                copy.setCompression(copyCompression);
            } else {
                copy.compression = null;
            }
        }
        return draftCopy;
    }

    public Object createNewInstance() {
        return new FileType();
    }

    public void mergeFrom(Object left, Object right) {
        final MergeStrategy strategy = JAXBMergeStrategy.INSTANCE;
        mergeFrom(null, null, left, right, strategy);
    }

    public void mergeFrom(ObjectLocator leftLocator, ObjectLocator rightLocator, Object left, Object right, MergeStrategy strategy) {
        if (right instanceof FileType) {
            final FileType target = this;
            final FileType leftObject = ((FileType) left);
            final FileType rightObject = ((FileType) right);
            {
                RangeParametersType lhsRangeParameters;
                lhsRangeParameters = leftObject.getRangeParameters();
                RangeParametersType rhsRangeParameters;
                rhsRangeParameters = rightObject.getRangeParameters();
                target.setRangeParameters(((RangeParametersType) strategy.merge(LocatorUtils.property(leftLocator, "rangeParameters", lhsRangeParameters), LocatorUtils.property(rightLocator, "rangeParameters", rhsRangeParameters), lhsRangeParameters, rhsRangeParameters)));
            }
            {
                String lhsFileName;
                lhsFileName = leftObject.getFileName();
                String rhsFileName;
                rhsFileName = rightObject.getFileName();
                target.setFileName(((String) strategy.merge(LocatorUtils.property(leftLocator, "fileName", lhsFileName), LocatorUtils.property(rightLocator, "fileName", rhsFileName), lhsFileName, rhsFileName)));
            }
            {
                String lhsFileReference;
                lhsFileReference = leftObject.getFileReference();
                String rhsFileReference;
                rhsFileReference = rightObject.getFileReference();
                target.setFileReference(((String) strategy.merge(LocatorUtils.property(leftLocator, "fileReference", lhsFileReference), LocatorUtils.property(rightLocator, "fileReference", rhsFileReference), lhsFileReference, rhsFileReference)));
            }
            {
                FileValueModelType lhsFileStructure;
                lhsFileStructure = leftObject.getFileStructure();
                FileValueModelType rhsFileStructure;
                rhsFileStructure = rightObject.getFileStructure();
                target.setFileStructure(((FileValueModelType) strategy.merge(LocatorUtils.property(leftLocator, "fileStructure", lhsFileStructure), LocatorUtils.property(rightLocator, "fileStructure", rhsFileStructure), lhsFileStructure, rhsFileStructure)));
            }
            {
                String lhsMimeType;
                lhsMimeType = leftObject.getMimeType();
                String rhsMimeType;
                rhsMimeType = rightObject.getMimeType();
                target.setMimeType(((String) strategy.merge(LocatorUtils.property(leftLocator, "mimeType", lhsMimeType), LocatorUtils.property(rightLocator, "mimeType", rhsMimeType), lhsMimeType, rhsMimeType)));
            }
            {
                String lhsCompression;
                lhsCompression = leftObject.getCompression();
                String rhsCompression;
                rhsCompression = rightObject.getCompression();
                target.setCompression(((String) strategy.merge(LocatorUtils.property(leftLocator, "compression", lhsCompression), LocatorUtils.property(rightLocator, "compression", rhsCompression), lhsCompression, rhsCompression)));
            }
        }
    }

    public FileType withRangeParameters(RangeParametersType value) {
        setRangeParameters(value);
        return this;
    }

    public FileType withFileName(String value) {
        setFileName(value);
        return this;
    }

    public FileType withFileReference(String value) {
        setFileReference(value);
        return this;
    }

    public FileType withFileStructure(FileValueModelType value) {
        setFileStructure(value);
        return this;
    }

    public FileType withMimeType(String value) {
        setMimeType(value);
        return this;
    }

    public FileType withCompression(String value) {
        setCompression(value);
        return this;
    }

}
