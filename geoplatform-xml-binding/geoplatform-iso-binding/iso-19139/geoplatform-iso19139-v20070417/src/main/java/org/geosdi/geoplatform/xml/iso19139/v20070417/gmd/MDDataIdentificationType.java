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
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per MD_DataIdentification_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_DataIdentification_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gmd}AbstractMD_Identification_Type">
 *       <sequence>
 *         <element name="spatialRepresentationType" type="{http://www.isotc211.org/2005/gmd}MD_SpatialRepresentationTypeCode_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="spatialResolution" type="{http://www.isotc211.org/2005/gmd}MD_Resolution_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="language" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded"/>
 *         <element name="characterSet" type="{http://www.isotc211.org/2005/gmd}MD_CharacterSetCode_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="topicCategory" type="{http://www.isotc211.org/2005/gmd}MD_TopicCategoryCode_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="environmentDescription" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="extent" type="{http://www.isotc211.org/2005/gmd}EX_Extent_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="supplementalInformation" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_DataIdentification_Type", propOrder = {
    "spatialRepresentationType",
    "spatialResolution",
    "language",
    "characterSet",
    "topicCategory",
    "environmentDescription",
    "extent",
    "supplementalInformation"
})
public class MDDataIdentificationType
    extends AbstractMDIdentificationType
    implements ToString
{

    protected List<MDSpatialRepresentationTypeCodePropertyType> spatialRepresentationType;
    protected List<MDResolutionPropertyType> spatialResolution;
    @XmlElement(required = true)
    protected List<CharacterStringPropertyType> language;
    protected List<MDCharacterSetCodePropertyType> characterSet;
    protected List<MDTopicCategoryCodePropertyType> topicCategory;
    protected CharacterStringPropertyType environmentDescription;
    protected List<EXExtentPropertyType> extent;
    protected CharacterStringPropertyType supplementalInformation;

    /**
     * Gets the value of the spatialRepresentationType property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the spatialRepresentationType property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSpatialRepresentationType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDSpatialRepresentationTypeCodePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the spatialRepresentationType property.
     */
    public List<MDSpatialRepresentationTypeCodePropertyType> getSpatialRepresentationType() {
        if (spatialRepresentationType == null) {
            spatialRepresentationType = new ArrayList<>();
        }
        return this.spatialRepresentationType;
    }

    public boolean isSetSpatialRepresentationType() {
        return ((this.spatialRepresentationType!= null)&&(!this.spatialRepresentationType.isEmpty()));
    }

    public void unsetSpatialRepresentationType() {
        this.spatialRepresentationType = null;
    }

    /**
     * Gets the value of the spatialResolution property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the spatialResolution property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSpatialResolution().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDResolutionPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the spatialResolution property.
     */
    public List<MDResolutionPropertyType> getSpatialResolution() {
        if (spatialResolution == null) {
            spatialResolution = new ArrayList<>();
        }
        return this.spatialResolution;
    }

    public boolean isSetSpatialResolution() {
        return ((this.spatialResolution!= null)&&(!this.spatialResolution.isEmpty()));
    }

    public void unsetSpatialResolution() {
        this.spatialResolution = null;
    }

    /**
     * Gets the value of the language property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the language property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getLanguage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CharacterStringPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the language property.
     */
    public List<CharacterStringPropertyType> getLanguage() {
        if (language == null) {
            language = new ArrayList<>();
        }
        return this.language;
    }

    public boolean isSetLanguage() {
        return ((this.language!= null)&&(!this.language.isEmpty()));
    }

    public void unsetLanguage() {
        this.language = null;
    }

    /**
     * Gets the value of the characterSet property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the characterSet property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getCharacterSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDCharacterSetCodePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the characterSet property.
     */
    public List<MDCharacterSetCodePropertyType> getCharacterSet() {
        if (characterSet == null) {
            characterSet = new ArrayList<>();
        }
        return this.characterSet;
    }

    public boolean isSetCharacterSet() {
        return ((this.characterSet!= null)&&(!this.characterSet.isEmpty()));
    }

    public void unsetCharacterSet() {
        this.characterSet = null;
    }

    /**
     * Gets the value of the topicCategory property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the topicCategory property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTopicCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDTopicCategoryCodePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the topicCategory property.
     */
    public List<MDTopicCategoryCodePropertyType> getTopicCategory() {
        if (topicCategory == null) {
            topicCategory = new ArrayList<>();
        }
        return this.topicCategory;
    }

    public boolean isSetTopicCategory() {
        return ((this.topicCategory!= null)&&(!this.topicCategory.isEmpty()));
    }

    public void unsetTopicCategory() {
        this.topicCategory = null;
    }

    /**
     * Recupera il valore della proprietà environmentDescription.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getEnvironmentDescription() {
        return environmentDescription;
    }

    /**
     * Imposta il valore della proprietà environmentDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setEnvironmentDescription(CharacterStringPropertyType value) {
        this.environmentDescription = value;
    }

    public boolean isSetEnvironmentDescription() {
        return (this.environmentDescription!= null);
    }

    /**
     * Gets the value of the extent property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the extent property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getExtent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EXExtentPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the extent property.
     */
    public List<EXExtentPropertyType> getExtent() {
        if (extent == null) {
            extent = new ArrayList<>();
        }
        return this.extent;
    }

    public boolean isSetExtent() {
        return ((this.extent!= null)&&(!this.extent.isEmpty()));
    }

    public void unsetExtent() {
        this.extent = null;
    }

    /**
     * Recupera il valore della proprietà supplementalInformation.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getSupplementalInformation() {
        return supplementalInformation;
    }

    /**
     * Imposta il valore della proprietà supplementalInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setSupplementalInformation(CharacterStringPropertyType value) {
        this.supplementalInformation = value;
    }

    public boolean isSetSupplementalInformation() {
        return (this.supplementalInformation!= null);
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
            List<MDSpatialRepresentationTypeCodePropertyType> theSpatialRepresentationType;
            theSpatialRepresentationType = (this.isSetSpatialRepresentationType()?this.getSpatialRepresentationType():null);
            strategy.appendField(locator, this, "spatialRepresentationType", buffer, theSpatialRepresentationType, this.isSetSpatialRepresentationType());
        }
        {
            List<MDResolutionPropertyType> theSpatialResolution;
            theSpatialResolution = (this.isSetSpatialResolution()?this.getSpatialResolution():null);
            strategy.appendField(locator, this, "spatialResolution", buffer, theSpatialResolution, this.isSetSpatialResolution());
        }
        {
            List<CharacterStringPropertyType> theLanguage;
            theLanguage = (this.isSetLanguage()?this.getLanguage():null);
            strategy.appendField(locator, this, "language", buffer, theLanguage, this.isSetLanguage());
        }
        {
            List<MDCharacterSetCodePropertyType> theCharacterSet;
            theCharacterSet = (this.isSetCharacterSet()?this.getCharacterSet():null);
            strategy.appendField(locator, this, "characterSet", buffer, theCharacterSet, this.isSetCharacterSet());
        }
        {
            List<MDTopicCategoryCodePropertyType> theTopicCategory;
            theTopicCategory = (this.isSetTopicCategory()?this.getTopicCategory():null);
            strategy.appendField(locator, this, "topicCategory", buffer, theTopicCategory, this.isSetTopicCategory());
        }
        {
            CharacterStringPropertyType theEnvironmentDescription;
            theEnvironmentDescription = this.getEnvironmentDescription();
            strategy.appendField(locator, this, "environmentDescription", buffer, theEnvironmentDescription, this.isSetEnvironmentDescription());
        }
        {
            List<EXExtentPropertyType> theExtent;
            theExtent = (this.isSetExtent()?this.getExtent():null);
            strategy.appendField(locator, this, "extent", buffer, theExtent, this.isSetExtent());
        }
        {
            CharacterStringPropertyType theSupplementalInformation;
            theSupplementalInformation = this.getSupplementalInformation();
            strategy.appendField(locator, this, "supplementalInformation", buffer, theSupplementalInformation, this.isSetSupplementalInformation());
        }
        return buffer;
    }

    public void setSpatialRepresentationType(List<MDSpatialRepresentationTypeCodePropertyType> value) {
        this.spatialRepresentationType = null;
        if (value!= null) {
            List<MDSpatialRepresentationTypeCodePropertyType> draftl = this.getSpatialRepresentationType();
            draftl.addAll(value);
        }
    }

    public void setSpatialResolution(List<MDResolutionPropertyType> value) {
        this.spatialResolution = null;
        if (value!= null) {
            List<MDResolutionPropertyType> draftl = this.getSpatialResolution();
            draftl.addAll(value);
        }
    }

    public void setLanguage(List<CharacterStringPropertyType> value) {
        this.language = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getLanguage();
            draftl.addAll(value);
        }
    }

    public void setCharacterSet(List<MDCharacterSetCodePropertyType> value) {
        this.characterSet = null;
        if (value!= null) {
            List<MDCharacterSetCodePropertyType> draftl = this.getCharacterSet();
            draftl.addAll(value);
        }
    }

    public void setTopicCategory(List<MDTopicCategoryCodePropertyType> value) {
        this.topicCategory = null;
        if (value!= null) {
            List<MDTopicCategoryCodePropertyType> draftl = this.getTopicCategory();
            draftl.addAll(value);
        }
    }

    public void setExtent(List<EXExtentPropertyType> value) {
        this.extent = null;
        if (value!= null) {
            List<EXExtentPropertyType> draftl = this.getExtent();
            draftl.addAll(value);
        }
    }

}
