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
// Generato il: 2026.11.02 alle 03:48:01 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Description of the form of the data to be distributed
 * 
 * <p>Classe Java per MD_Format_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_Format_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="name" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="version" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="amendmentNumber" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="specification" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="fileDecompressionTechnique" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="formatDistributor" type="{http://www.isotc211.org/2005/gmd}MD_Distributor_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_Format_Type", propOrder = {
    "name",
    "version",
    "amendmentNumber",
    "specification",
    "fileDecompressionTechnique",
    "formatDistributor"
})
public class MDFormatType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected CharacterStringPropertyType name;
    @XmlElement(required = true)
    protected CharacterStringPropertyType version;
    protected CharacterStringPropertyType amendmentNumber;
    protected CharacterStringPropertyType specification;
    protected CharacterStringPropertyType fileDecompressionTechnique;
    protected List<MDDistributorPropertyType> formatDistributor;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setName(CharacterStringPropertyType value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Recupera il valore della proprietà version.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getVersion() {
        return version;
    }

    /**
     * Imposta il valore della proprietà version.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setVersion(CharacterStringPropertyType value) {
        this.version = value;
    }

    public boolean isSetVersion() {
        return (this.version!= null);
    }

    /**
     * Recupera il valore della proprietà amendmentNumber.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getAmendmentNumber() {
        return amendmentNumber;
    }

    /**
     * Imposta il valore della proprietà amendmentNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setAmendmentNumber(CharacterStringPropertyType value) {
        this.amendmentNumber = value;
    }

    public boolean isSetAmendmentNumber() {
        return (this.amendmentNumber!= null);
    }

    /**
     * Recupera il valore della proprietà specification.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getSpecification() {
        return specification;
    }

    /**
     * Imposta il valore della proprietà specification.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setSpecification(CharacterStringPropertyType value) {
        this.specification = value;
    }

    public boolean isSetSpecification() {
        return (this.specification!= null);
    }

    /**
     * Recupera il valore della proprietà fileDecompressionTechnique.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getFileDecompressionTechnique() {
        return fileDecompressionTechnique;
    }

    /**
     * Imposta il valore della proprietà fileDecompressionTechnique.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setFileDecompressionTechnique(CharacterStringPropertyType value) {
        this.fileDecompressionTechnique = value;
    }

    public boolean isSetFileDecompressionTechnique() {
        return (this.fileDecompressionTechnique!= null);
    }

    /**
     * Gets the value of the formatDistributor property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the formatDistributor property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFormatDistributor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDDistributorPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the formatDistributor property.
     */
    public List<MDDistributorPropertyType> getFormatDistributor() {
        if (formatDistributor == null) {
            formatDistributor = new ArrayList<>();
        }
        return this.formatDistributor;
    }

    public boolean isSetFormatDistributor() {
        return ((this.formatDistributor!= null)&&(!this.formatDistributor.isEmpty()));
    }

    public void unsetFormatDistributor() {
        this.formatDistributor = null;
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
            CharacterStringPropertyType theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            CharacterStringPropertyType theVersion;
            theVersion = this.getVersion();
            strategy.appendField(locator, this, "version", buffer, theVersion, this.isSetVersion());
        }
        {
            CharacterStringPropertyType theAmendmentNumber;
            theAmendmentNumber = this.getAmendmentNumber();
            strategy.appendField(locator, this, "amendmentNumber", buffer, theAmendmentNumber, this.isSetAmendmentNumber());
        }
        {
            CharacterStringPropertyType theSpecification;
            theSpecification = this.getSpecification();
            strategy.appendField(locator, this, "specification", buffer, theSpecification, this.isSetSpecification());
        }
        {
            CharacterStringPropertyType theFileDecompressionTechnique;
            theFileDecompressionTechnique = this.getFileDecompressionTechnique();
            strategy.appendField(locator, this, "fileDecompressionTechnique", buffer, theFileDecompressionTechnique, this.isSetFileDecompressionTechnique());
        }
        {
            List<MDDistributorPropertyType> theFormatDistributor;
            theFormatDistributor = (this.isSetFormatDistributor()?this.getFormatDistributor():null);
            strategy.appendField(locator, this, "formatDistributor", buffer, theFormatDistributor, this.isSetFormatDistributor());
        }
        return buffer;
    }

    public void setFormatDistributor(List<MDDistributorPropertyType> value) {
        this.formatDistributor = null;
        if (value!= null) {
            List<MDDistributorPropertyType> draftl = this.getFormatDistributor();
            draftl.addAll(value);
        }
    }

}
