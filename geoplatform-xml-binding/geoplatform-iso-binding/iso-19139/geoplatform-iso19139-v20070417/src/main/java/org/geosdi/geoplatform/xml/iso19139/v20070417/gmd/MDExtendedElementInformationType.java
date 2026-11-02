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
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.IntegerPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * New metadata element, not found in ISO 19115, which is required to describe geographic data
 * 
 * <p>Classe Java per MD_ExtendedElementInformation_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_ExtendedElementInformation_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="name" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="shortName" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="domainCode" type="{http://www.isotc211.org/2005/gco}Integer_PropertyType" minOccurs="0"/>
 *         <element name="definition" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="obligation" type="{http://www.isotc211.org/2005/gmd}MD_ObligationCode_PropertyType" minOccurs="0"/>
 *         <element name="condition" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="dataType" type="{http://www.isotc211.org/2005/gmd}MD_DatatypeCode_PropertyType"/>
 *         <element name="maximumOccurrence" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="domainValue" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="parentEntity" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded"/>
 *         <element name="rule" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType"/>
 *         <element name="rationale" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="source" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType" maxOccurs="unbounded"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_ExtendedElementInformation_Type", propOrder = {
    "name",
    "shortName",
    "domainCode",
    "definition",
    "obligation",
    "condition",
    "dataType",
    "maximumOccurrence",
    "domainValue",
    "parentEntity",
    "rule",
    "rationale",
    "source"
})
public class MDExtendedElementInformationType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected CharacterStringPropertyType name;
    protected CharacterStringPropertyType shortName;
    protected IntegerPropertyType domainCode;
    @XmlElement(required = true)
    protected CharacterStringPropertyType definition;
    protected MDObligationCodePropertyType obligation;
    protected CharacterStringPropertyType condition;
    @XmlElement(required = true)
    protected MDDatatypeCodePropertyType dataType;
    protected CharacterStringPropertyType maximumOccurrence;
    protected CharacterStringPropertyType domainValue;
    @XmlElement(required = true)
    protected List<CharacterStringPropertyType> parentEntity;
    @XmlElement(required = true)
    protected CharacterStringPropertyType rule;
    protected List<CharacterStringPropertyType> rationale;
    @XmlElement(required = true)
    protected List<CIResponsiblePartyPropertyType> source;

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
     * Recupera il valore della proprietà shortName.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getShortName() {
        return shortName;
    }

    /**
     * Imposta il valore della proprietà shortName.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setShortName(CharacterStringPropertyType value) {
        this.shortName = value;
    }

    public boolean isSetShortName() {
        return (this.shortName!= null);
    }

    /**
     * Recupera il valore della proprietà domainCode.
     * 
     * @return
     *     possible object is
     *     {@link IntegerPropertyType }
     *     
     */
    public IntegerPropertyType getDomainCode() {
        return domainCode;
    }

    /**
     * Imposta il valore della proprietà domainCode.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerPropertyType }
     *     
     */
    public void setDomainCode(IntegerPropertyType value) {
        this.domainCode = value;
    }

    public boolean isSetDomainCode() {
        return (this.domainCode!= null);
    }

    /**
     * Recupera il valore della proprietà definition.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDefinition() {
        return definition;
    }

    /**
     * Imposta il valore della proprietà definition.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDefinition(CharacterStringPropertyType value) {
        this.definition = value;
    }

    public boolean isSetDefinition() {
        return (this.definition!= null);
    }

    /**
     * Recupera il valore della proprietà obligation.
     * 
     * @return
     *     possible object is
     *     {@link MDObligationCodePropertyType }
     *     
     */
    public MDObligationCodePropertyType getObligation() {
        return obligation;
    }

    /**
     * Imposta il valore della proprietà obligation.
     * 
     * @param value
     *     allowed object is
     *     {@link MDObligationCodePropertyType }
     *     
     */
    public void setObligation(MDObligationCodePropertyType value) {
        this.obligation = value;
    }

    public boolean isSetObligation() {
        return (this.obligation!= null);
    }

    /**
     * Recupera il valore della proprietà condition.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getCondition() {
        return condition;
    }

    /**
     * Imposta il valore della proprietà condition.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setCondition(CharacterStringPropertyType value) {
        this.condition = value;
    }

    public boolean isSetCondition() {
        return (this.condition!= null);
    }

    /**
     * Recupera il valore della proprietà dataType.
     * 
     * @return
     *     possible object is
     *     {@link MDDatatypeCodePropertyType }
     *     
     */
    public MDDatatypeCodePropertyType getDataType() {
        return dataType;
    }

    /**
     * Imposta il valore della proprietà dataType.
     * 
     * @param value
     *     allowed object is
     *     {@link MDDatatypeCodePropertyType }
     *     
     */
    public void setDataType(MDDatatypeCodePropertyType value) {
        this.dataType = value;
    }

    public boolean isSetDataType() {
        return (this.dataType!= null);
    }

    /**
     * Recupera il valore della proprietà maximumOccurrence.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getMaximumOccurrence() {
        return maximumOccurrence;
    }

    /**
     * Imposta il valore della proprietà maximumOccurrence.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setMaximumOccurrence(CharacterStringPropertyType value) {
        this.maximumOccurrence = value;
    }

    public boolean isSetMaximumOccurrence() {
        return (this.maximumOccurrence!= null);
    }

    /**
     * Recupera il valore della proprietà domainValue.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getDomainValue() {
        return domainValue;
    }

    /**
     * Imposta il valore della proprietà domainValue.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setDomainValue(CharacterStringPropertyType value) {
        this.domainValue = value;
    }

    public boolean isSetDomainValue() {
        return (this.domainValue!= null);
    }

    /**
     * Gets the value of the parentEntity property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the parentEntity property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getParentEntity().add(newItem);
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
     *     The value of the parentEntity property.
     */
    public List<CharacterStringPropertyType> getParentEntity() {
        if (parentEntity == null) {
            parentEntity = new ArrayList<>();
        }
        return this.parentEntity;
    }

    public boolean isSetParentEntity() {
        return ((this.parentEntity!= null)&&(!this.parentEntity.isEmpty()));
    }

    public void unsetParentEntity() {
        this.parentEntity = null;
    }

    /**
     * Recupera il valore della proprietà rule.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getRule() {
        return rule;
    }

    /**
     * Imposta il valore della proprietà rule.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setRule(CharacterStringPropertyType value) {
        this.rule = value;
    }

    public boolean isSetRule() {
        return (this.rule!= null);
    }

    /**
     * Gets the value of the rationale property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the rationale property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getRationale().add(newItem);
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
     *     The value of the rationale property.
     */
    public List<CharacterStringPropertyType> getRationale() {
        if (rationale == null) {
            rationale = new ArrayList<>();
        }
        return this.rationale;
    }

    public boolean isSetRationale() {
        return ((this.rationale!= null)&&(!this.rationale.isEmpty()));
    }

    public void unsetRationale() {
        this.rationale = null;
    }

    /**
     * Gets the value of the source property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the source property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIResponsiblePartyPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the source property.
     */
    public List<CIResponsiblePartyPropertyType> getSource() {
        if (source == null) {
            source = new ArrayList<>();
        }
        return this.source;
    }

    public boolean isSetSource() {
        return ((this.source!= null)&&(!this.source.isEmpty()));
    }

    public void unsetSource() {
        this.source = null;
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
            CharacterStringPropertyType theShortName;
            theShortName = this.getShortName();
            strategy.appendField(locator, this, "shortName", buffer, theShortName, this.isSetShortName());
        }
        {
            IntegerPropertyType theDomainCode;
            theDomainCode = this.getDomainCode();
            strategy.appendField(locator, this, "domainCode", buffer, theDomainCode, this.isSetDomainCode());
        }
        {
            CharacterStringPropertyType theDefinition;
            theDefinition = this.getDefinition();
            strategy.appendField(locator, this, "definition", buffer, theDefinition, this.isSetDefinition());
        }
        {
            MDObligationCodePropertyType theObligation;
            theObligation = this.getObligation();
            strategy.appendField(locator, this, "obligation", buffer, theObligation, this.isSetObligation());
        }
        {
            CharacterStringPropertyType theCondition;
            theCondition = this.getCondition();
            strategy.appendField(locator, this, "condition", buffer, theCondition, this.isSetCondition());
        }
        {
            MDDatatypeCodePropertyType theDataType;
            theDataType = this.getDataType();
            strategy.appendField(locator, this, "dataType", buffer, theDataType, this.isSetDataType());
        }
        {
            CharacterStringPropertyType theMaximumOccurrence;
            theMaximumOccurrence = this.getMaximumOccurrence();
            strategy.appendField(locator, this, "maximumOccurrence", buffer, theMaximumOccurrence, this.isSetMaximumOccurrence());
        }
        {
            CharacterStringPropertyType theDomainValue;
            theDomainValue = this.getDomainValue();
            strategy.appendField(locator, this, "domainValue", buffer, theDomainValue, this.isSetDomainValue());
        }
        {
            List<CharacterStringPropertyType> theParentEntity;
            theParentEntity = (this.isSetParentEntity()?this.getParentEntity():null);
            strategy.appendField(locator, this, "parentEntity", buffer, theParentEntity, this.isSetParentEntity());
        }
        {
            CharacterStringPropertyType theRule;
            theRule = this.getRule();
            strategy.appendField(locator, this, "rule", buffer, theRule, this.isSetRule());
        }
        {
            List<CharacterStringPropertyType> theRationale;
            theRationale = (this.isSetRationale()?this.getRationale():null);
            strategy.appendField(locator, this, "rationale", buffer, theRationale, this.isSetRationale());
        }
        {
            List<CIResponsiblePartyPropertyType> theSource;
            theSource = (this.isSetSource()?this.getSource():null);
            strategy.appendField(locator, this, "source", buffer, theSource, this.isSetSource());
        }
        return buffer;
    }

    public void setParentEntity(List<CharacterStringPropertyType> value) {
        this.parentEntity = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getParentEntity();
            draftl.addAll(value);
        }
    }

    public void setRationale(List<CharacterStringPropertyType> value) {
        this.rationale = null;
        if (value!= null) {
            List<CharacterStringPropertyType> draftl = this.getRationale();
            draftl.addAll(value);
        }
    }

    public void setSource(List<CIResponsiblePartyPropertyType> value) {
        this.source = null;
        if (value!= null) {
            List<CIResponsiblePartyPropertyType> draftl = this.getSource();
            draftl.addAll(value);
        }
    }

}
