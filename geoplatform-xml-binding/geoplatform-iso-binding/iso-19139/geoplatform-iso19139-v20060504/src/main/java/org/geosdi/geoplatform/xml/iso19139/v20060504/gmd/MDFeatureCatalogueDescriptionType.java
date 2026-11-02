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

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.BooleanPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20060504.gco.GenericNamePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Information identifing the feature catalogue
 * 
 * <p>Classe Java per MD_FeatureCatalogueDescription_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_FeatureCatalogueDescription_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gmd}AbstractMD_ContentInformation_Type">
 *       <sequence>
 *         <element name="complianceCode" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType" minOccurs="0"/>
 *         <element name="language" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="includedWithDataset" type="{http://www.isotc211.org/2005/gco}Boolean_PropertyType"/>
 *         <element name="featureTypes" type="{http://www.isotc211.org/2005/gco}GenericName_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="featureCatalogueCitation" type="{http://www.isotc211.org/2005/gmd}CI_Citation_PropertyType" maxOccurs="unbounded"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_FeatureCatalogueDescription_Type", propOrder = {
    "complianceCode",
    "language",
    "includedWithDataset",
    "featureTypes",
    "featureCatalogueCitation"
})
public class MDFeatureCatalogueDescriptionType
    extends AbstractMDContentInformationType
    implements ToString
{

    protected BooleanPropertyType complianceCode;
    protected List<CharacterStringPropertyType> language;
    @XmlElement(required = true)
    protected BooleanPropertyType includedWithDataset;
    protected List<GenericNamePropertyType> featureTypes;
    @XmlElement(required = true)
    protected List<CICitationPropertyType> featureCatalogueCitation;

    /**
     * Recupera il valore della proprietà complianceCode.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getComplianceCode() {
        return complianceCode;
    }

    /**
     * Imposta il valore della proprietà complianceCode.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setComplianceCode(BooleanPropertyType value) {
        this.complianceCode = value;
    }

    public boolean isSetComplianceCode() {
        return (this.complianceCode!= null);
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
     * Recupera il valore della proprietà includedWithDataset.
     * 
     * @return
     *     possible object is
     *     {@link BooleanPropertyType }
     *     
     */
    public BooleanPropertyType getIncludedWithDataset() {
        return includedWithDataset;
    }

    /**
     * Imposta il valore della proprietà includedWithDataset.
     * 
     * @param value
     *     allowed object is
     *     {@link BooleanPropertyType }
     *     
     */
    public void setIncludedWithDataset(BooleanPropertyType value) {
        this.includedWithDataset = value;
    }

    public boolean isSetIncludedWithDataset() {
        return (this.includedWithDataset!= null);
    }

    /**
     * Gets the value of the featureTypes property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the featureTypes property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFeatureTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GenericNamePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the featureTypes property.
     */
    public List<GenericNamePropertyType> getFeatureTypes() {
        if (featureTypes == null) {
            featureTypes = new ArrayList<>();
        }
        return this.featureTypes;
    }

    public boolean isSetFeatureTypes() {
        return ((this.featureTypes!= null)&&(!this.featureTypes.isEmpty()));
    }

    public void unsetFeatureTypes() {
        this.featureTypes = null;
    }

    /**
     * Gets the value of the featureCatalogueCitation property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the featureCatalogueCitation property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getFeatureCatalogueCitation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CICitationPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the featureCatalogueCitation property.
     */
    public List<CICitationPropertyType> getFeatureCatalogueCitation() {
        if (featureCatalogueCitation == null) {
            featureCatalogueCitation = new ArrayList<>();
        }
        return this.featureCatalogueCitation;
    }

    public boolean isSetFeatureCatalogueCitation() {
        return ((this.featureCatalogueCitation!= null)&&(!this.featureCatalogueCitation.isEmpty()));
    }

    public void unsetFeatureCatalogueCitation() {
        this.featureCatalogueCitation = null;
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
            BooleanPropertyType theComplianceCode;
            theComplianceCode = this.getComplianceCode();
            strategy.appendField(locator, this, "complianceCode", buffer, theComplianceCode, this.isSetComplianceCode());
        }
        {
            List<CharacterStringPropertyType> theLanguage;
            theLanguage = (this.isSetLanguage()?this.getLanguage():null);
            strategy.appendField(locator, this, "language", buffer, theLanguage, this.isSetLanguage());
        }
        {
            BooleanPropertyType theIncludedWithDataset;
            theIncludedWithDataset = this.getIncludedWithDataset();
            strategy.appendField(locator, this, "includedWithDataset", buffer, theIncludedWithDataset, this.isSetIncludedWithDataset());
        }
        {
            List<GenericNamePropertyType> theFeatureTypes;
            theFeatureTypes = (this.isSetFeatureTypes()?this.getFeatureTypes():null);
            strategy.appendField(locator, this, "featureTypes", buffer, theFeatureTypes, this.isSetFeatureTypes());
        }
        {
            List<CICitationPropertyType> theFeatureCatalogueCitation;
            theFeatureCatalogueCitation = (this.isSetFeatureCatalogueCitation()?this.getFeatureCatalogueCitation():null);
            strategy.appendField(locator, this, "featureCatalogueCitation", buffer, theFeatureCatalogueCitation, this.isSetFeatureCatalogueCitation());
        }
        return buffer;
    }

    public void setLanguage(List<CharacterStringPropertyType> value) {
        this.language = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getLanguage();
            draftl.addAll(value);
        }
    }

    public void setFeatureTypes(List<GenericNamePropertyType> value) {
        this.featureTypes = null;
        if (value!= null) {
            List<GenericNamePropertyType> draftl = this.getFeatureTypes();
            draftl.addAll(value);
        }
    }

    public void setFeatureCatalogueCitation(List<CICitationPropertyType> value) {
        this.featureCatalogueCitation = null;
        if (value!= null) {
            List<CICitationPropertyType> draftl = this.getFeatureCatalogueCitation();
            draftl.addAll(value);
        }
    }

}
