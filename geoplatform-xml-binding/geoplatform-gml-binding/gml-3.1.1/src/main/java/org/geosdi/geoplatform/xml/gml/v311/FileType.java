/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. This program is distributed in the 
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR 
 * A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. You should have received a copy of the GNU General 
 * Public License along with this program. If not, see http://www.gnu.org/licenses/ 
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is 
 * making a combined work based on this library. Thus, the terms and 
 * conditions of the GNU General Public License cover the whole combination. 
 * 
 * As a special exception, the copyright holders of this library give you permission 
 * to link this library with independent modules to produce an executable, regardless 
 * of the license terms of these independent modules, and to copy and distribute 
 * the resulting executable under terms of your choice, provided that you also meet, 
 * for each linked independent module, the terms and conditions of the license of 
 * that module. An independent module is a module which is not derived from or 
 * based on this library. If you modify this library, you may extend this exception 
 * to your version of the library, but you are not obligated to do so. If you do not 
 * wish to do so, delete this exception statement from your version. 
 *
 */
package org.geosdi.geoplatform.xml.gml.v311;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for FileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}rangeParameters"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
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
    "fileStructure",
    "mimeType",
    "compression"
})
public class FileType {

    @XmlElement(required = true)
    protected RangeParametersType rangeParameters;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String fileName;
    @XmlElement(required = true)
    protected FileValueModelType fileStructure;
    @XmlSchemaType(name = "anyURI")
    protected String mimeType;
    @XmlSchemaType(name = "anyURI")
    protected String compression;

    public FileType() {
    }

    /**
     * Gets the value of the rangeParameters property.
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
     * Sets the value of the rangeParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeParametersType }
     *     
     */
    public void setRangeParameters(RangeParametersType value) {
        this.rangeParameters = value;
    }

    /**
     * Gets the value of the fileName property.
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
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the fileStructure property.
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
     * Sets the value of the fileStructure property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileValueModelType }
     *     
     */
    public void setFileStructure(FileValueModelType value) {
        this.fileStructure = value;
    }

    /**
     * Gets the value of the mimeType property.
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
     * Sets the value of the mimeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Gets the value of the compression property.
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
     * Sets the value of the compression property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompression(String value) {
        this.compression = value;
    }

    @Override
    public String toString() {
        return "FileType{ " + "rangeParameters = " + rangeParameters
                + ", fileName = " + fileName
                + ", fileStructure = " + fileStructure
                + ", mimeType = " + mimeType
                + ", compression = " + compression + '}';
    }
}
