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
// Generato il: 2026.11.02 alle 03:38:59 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20060504.gmd;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.BinaryPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Information about the application schema used to build the dataset
 * 
 * <p>Classe Java per MD_ApplicationSchemaInformation_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_ApplicationSchemaInformation_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="name" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType"/>
 *         <element name="schemaLanguage" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="constraintLanguage" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="schemaAscii" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="graphicsFile" type="{http://www.isotc211.org/2005/gco}Binary_PropertyType" minOccurs="0"/>
 *         <element name="softwareDevelopmentFile" type="{http://www.isotc211.org/2005/gco}Binary_PropertyType" minOccurs="0"/>
 *         <element name="softwareDevelopmentFileFormat" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_ApplicationSchemaInformation_Type", propOrder = {
    "name",
    "schemaLanguage",
    "constraintLanguage",
    "schemaAscii",
    "graphicsFile",
    "softwareDevelopmentFile",
    "softwareDevelopmentFileFormat"
})
public class MDApplicationSchemaInformationType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected CICitationPropertyType name;
    @XmlElement(required = true)
    protected CharacterStringPropertyType schemaLanguage;
    @XmlElement(required = true)
    protected CharacterStringPropertyType constraintLanguage;
    protected CharacterStringPropertyType schemaAscii;
    protected BinaryPropertyType graphicsFile;
    protected BinaryPropertyType softwareDevelopmentFile;
    protected CharacterStringPropertyType softwareDevelopmentFileFormat;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link CICitationPropertyType }
     *     
     */
    public CICitationPropertyType getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link CICitationPropertyType }
     *     
     */
    public void setName(CICitationPropertyType value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Recupera il valore della proprietà schemaLanguage.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getSchemaLanguage() {
        return schemaLanguage;
    }

    /**
     * Imposta il valore della proprietà schemaLanguage.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setSchemaLanguage(CharacterStringPropertyType value) {
        this.schemaLanguage = value;
    }

    public boolean isSetSchemaLanguage() {
        return (this.schemaLanguage!= null);
    }

    /**
     * Recupera il valore della proprietà constraintLanguage.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getConstraintLanguage() {
        return constraintLanguage;
    }

    /**
     * Imposta il valore della proprietà constraintLanguage.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setConstraintLanguage(CharacterStringPropertyType value) {
        this.constraintLanguage = value;
    }

    public boolean isSetConstraintLanguage() {
        return (this.constraintLanguage!= null);
    }

    /**
     * Recupera il valore della proprietà schemaAscii.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getSchemaAscii() {
        return schemaAscii;
    }

    /**
     * Imposta il valore della proprietà schemaAscii.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setSchemaAscii(CharacterStringPropertyType value) {
        this.schemaAscii = value;
    }

    public boolean isSetSchemaAscii() {
        return (this.schemaAscii!= null);
    }

    /**
     * Recupera il valore della proprietà graphicsFile.
     * 
     * @return
     *     possible object is
     *     {@link BinaryPropertyType }
     *     
     */
    public BinaryPropertyType getGraphicsFile() {
        return graphicsFile;
    }

    /**
     * Imposta il valore della proprietà graphicsFile.
     * 
     * @param value
     *     allowed object is
     *     {@link BinaryPropertyType }
     *     
     */
    public void setGraphicsFile(BinaryPropertyType value) {
        this.graphicsFile = value;
    }

    public boolean isSetGraphicsFile() {
        return (this.graphicsFile!= null);
    }

    /**
     * Recupera il valore della proprietà softwareDevelopmentFile.
     * 
     * @return
     *     possible object is
     *     {@link BinaryPropertyType }
     *     
     */
    public BinaryPropertyType getSoftwareDevelopmentFile() {
        return softwareDevelopmentFile;
    }

    /**
     * Imposta il valore della proprietà softwareDevelopmentFile.
     * 
     * @param value
     *     allowed object is
     *     {@link BinaryPropertyType }
     *     
     */
    public void setSoftwareDevelopmentFile(BinaryPropertyType value) {
        this.softwareDevelopmentFile = value;
    }

    public boolean isSetSoftwareDevelopmentFile() {
        return (this.softwareDevelopmentFile!= null);
    }

    /**
     * Recupera il valore della proprietà softwareDevelopmentFileFormat.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getSoftwareDevelopmentFileFormat() {
        return softwareDevelopmentFileFormat;
    }

    /**
     * Imposta il valore della proprietà softwareDevelopmentFileFormat.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setSoftwareDevelopmentFileFormat(CharacterStringPropertyType value) {
        this.softwareDevelopmentFileFormat = value;
    }

    public boolean isSetSoftwareDevelopmentFileFormat() {
        return (this.softwareDevelopmentFileFormat!= null);
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
            CICitationPropertyType theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            CharacterStringPropertyType theSchemaLanguage;
            theSchemaLanguage = this.getSchemaLanguage();
            strategy.appendField(locator, this, "schemaLanguage", buffer, theSchemaLanguage, this.isSetSchemaLanguage());
        }
        {
            CharacterStringPropertyType theConstraintLanguage;
            theConstraintLanguage = this.getConstraintLanguage();
            strategy.appendField(locator, this, "constraintLanguage", buffer, theConstraintLanguage, this.isSetConstraintLanguage());
        }
        {
            CharacterStringPropertyType theSchemaAscii;
            theSchemaAscii = this.getSchemaAscii();
            strategy.appendField(locator, this, "schemaAscii", buffer, theSchemaAscii, this.isSetSchemaAscii());
        }
        {
            BinaryPropertyType theGraphicsFile;
            theGraphicsFile = this.getGraphicsFile();
            strategy.appendField(locator, this, "graphicsFile", buffer, theGraphicsFile, this.isSetGraphicsFile());
        }
        {
            BinaryPropertyType theSoftwareDevelopmentFile;
            theSoftwareDevelopmentFile = this.getSoftwareDevelopmentFile();
            strategy.appendField(locator, this, "softwareDevelopmentFile", buffer, theSoftwareDevelopmentFile, this.isSetSoftwareDevelopmentFile());
        }
        {
            CharacterStringPropertyType theSoftwareDevelopmentFileFormat;
            theSoftwareDevelopmentFileFormat = this.getSoftwareDevelopmentFileFormat();
            strategy.appendField(locator, this, "softwareDevelopmentFileFormat", buffer, theSoftwareDevelopmentFileFormat, this.isSetSoftwareDevelopmentFileFormat());
        }
        return buffer;
    }

}
