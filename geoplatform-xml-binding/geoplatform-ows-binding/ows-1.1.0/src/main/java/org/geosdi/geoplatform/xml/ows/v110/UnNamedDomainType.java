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
// Generato il: 2026.11.02 alle 02:51:17 PM CET
//

package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Valid domain (or allowed set of values) of one quantity, with needed metadata but without a quantity name or identifier.
 * 
 * <p>Classe Java per UnNamedDomainType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="UnNamedDomainType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <group ref="{http://www.opengis.net/ows/1.1}PossibleValues"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}DefaultValue" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}Meaning" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}DataType" minOccurs="0"/>
 *         <group ref="{http://www.opengis.net/ows/1.1}ValuesUnit" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}Metadata" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnNamedDomainType", propOrder = {
    "allowedValues",
    "anyValue",
    "noValues",
    "valuesReference",
    "defaultValue",
    "meaning",
    "dataType",
    "uom",
    "referenceSystem",
    "metadata"
})
@XmlSeeAlso({
    DomainType.class
})
public class UnNamedDomainType implements ToString
{

    /**
     * List of all the valid values and/or ranges of values for this quantity. For numeric quantities, signed values should be ordered from negative infinity to positive infinity.
     * 
     */
    @XmlElement(name = "AllowedValues")
    protected AllowedValues allowedValues;
    /**
     * Specifies that any value is allowed for this parameter.
     * 
     */
    @XmlElement(name = "AnyValue")
    protected AnyValue anyValue;
    /**
     * Specifies that no values are allowed for this parameter or quantity.
     * 
     */
    @XmlElement(name = "NoValues")
    protected NoValues noValues;
    /**
     * Reference to externally specified list of all the valid values and/or ranges of values for this quantity. (Informative: This element was simplified from the metaDataProperty element in GML 3.0.)
     * 
     */
    @XmlElement(name = "ValuesReference")
    protected ValuesReference valuesReference;
    /**
     * Optional default value for this quantity, which should be included when this quantity has a default value.
     * 
     */
    @XmlElement(name = "DefaultValue")
    protected ValueType defaultValue;
    /**
     * Meaning metadata should be referenced or included for each quantity.
     * 
     */
    @XmlElement(name = "Meaning")
    protected DomainMetadataType meaning;
    /**
     * This data type metadata should be referenced or included for each quantity.
     * 
     */
    @XmlElement(name = "DataType")
    protected DomainMetadataType dataType;
    /**
     * Identifier of unit of measure of this set of values. Should be included then this set of values has units (and not a more complete reference system).
     * 
     */
    @XmlElement(name = "UOM")
    protected DomainMetadataType uom;
    /**
     * Identifier of reference system used by this set of values. Should be included then this set of values has a reference system (not just units).
     * 
     */
    @XmlElement(name = "ReferenceSystem")
    protected DomainMetadataType referenceSystem;
    /**
     * Optional unordered list of other metadata about this quantity. A list of required and optional other metadata elements for this quantity should be specified in the Implementation Specification for this service.
     * 
     */
    @XmlElement(name = "Metadata")
    protected List<MetadataType> metadata;

    /**
     * List of all the valid values and/or ranges of values for this quantity. For numeric quantities, signed values should be ordered from negative infinity to positive infinity.
     * 
     * @return
     *     possible object is
     *     {@link AllowedValues }
     *     
     */
    public AllowedValues getAllowedValues() {
        return allowedValues;
    }

    /**
     * Imposta il valore della proprietà allowedValues.
     * 
     * @param value
     *     allowed object is
     *     {@link AllowedValues }
     *     
     * @see #getAllowedValues()
     */
    public void setAllowedValues(AllowedValues value) {
        this.allowedValues = value;
    }

    /**
     * Specifies that any value is allowed for this parameter.
     * 
     * @return
     *     possible object is
     *     {@link AnyValue }
     *     
     */
    public AnyValue getAnyValue() {
        return anyValue;
    }

    /**
     * Imposta il valore della proprietà anyValue.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyValue }
     *     
     * @see #getAnyValue()
     */
    public void setAnyValue(AnyValue value) {
        this.anyValue = value;
    }

    /**
     * Specifies that no values are allowed for this parameter or quantity.
     * 
     * @return
     *     possible object is
     *     {@link NoValues }
     *     
     */
    public NoValues getNoValues() {
        return noValues;
    }

    /**
     * Imposta il valore della proprietà noValues.
     * 
     * @param value
     *     allowed object is
     *     {@link NoValues }
     *     
     * @see #getNoValues()
     */
    public void setNoValues(NoValues value) {
        this.noValues = value;
    }

    /**
     * Reference to externally specified list of all the valid values and/or ranges of values for this quantity. (Informative: This element was simplified from the metaDataProperty element in GML 3.0.)
     * 
     * @return
     *     possible object is
     *     {@link ValuesReference }
     *     
     */
    public ValuesReference getValuesReference() {
        return valuesReference;
    }

    /**
     * Imposta il valore della proprietà valuesReference.
     * 
     * @param value
     *     allowed object is
     *     {@link ValuesReference }
     *     
     * @see #getValuesReference()
     */
    public void setValuesReference(ValuesReference value) {
        this.valuesReference = value;
    }

    /**
     * Optional default value for this quantity, which should be included when this quantity has a default value.
     * 
     * @return
     *     possible object is
     *     {@link ValueType }
     *     
     */
    public ValueType getDefaultValue() {
        return defaultValue;
    }

    /**
     * Imposta il valore della proprietà defaultValue.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueType }
     *     
     * @see #getDefaultValue()
     */
    public void setDefaultValue(ValueType value) {
        this.defaultValue = value;
    }

    /**
     * Meaning metadata should be referenced or included for each quantity.
     * 
     * @return
     *     possible object is
     *     {@link DomainMetadataType }
     *     
     */
    public DomainMetadataType getMeaning() {
        return meaning;
    }

    /**
     * Imposta il valore della proprietà meaning.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainMetadataType }
     *     
     * @see #getMeaning()
     */
    public void setMeaning(DomainMetadataType value) {
        this.meaning = value;
    }

    /**
     * This data type metadata should be referenced or included for each quantity.
     * 
     * @return
     *     possible object is
     *     {@link DomainMetadataType }
     *     
     */
    public DomainMetadataType getDataType() {
        return dataType;
    }

    /**
     * Imposta il valore della proprietà dataType.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainMetadataType }
     *     
     * @see #getDataType()
     */
    public void setDataType(DomainMetadataType value) {
        this.dataType = value;
    }

    /**
     * Identifier of unit of measure of this set of values. Should be included then this set of values has units (and not a more complete reference system).
     * 
     * @return
     *     possible object is
     *     {@link DomainMetadataType }
     *     
     */
    public DomainMetadataType getUOM() {
        return uom;
    }

    /**
     * Imposta il valore della proprietà uom.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainMetadataType }
     *     
     * @see #getUOM()
     */
    public void setUOM(DomainMetadataType value) {
        this.uom = value;
    }

    /**
     * Identifier of reference system used by this set of values. Should be included then this set of values has a reference system (not just units).
     * 
     * @return
     *     possible object is
     *     {@link DomainMetadataType }
     *     
     */
    public DomainMetadataType getReferenceSystem() {
        return referenceSystem;
    }

    /**
     * Imposta il valore della proprietà referenceSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainMetadataType }
     *     
     * @see #getReferenceSystem()
     */
    public void setReferenceSystem(DomainMetadataType value) {
        this.referenceSystem = value;
    }

    /**
     * Optional unordered list of other metadata about this quantity. A list of required and optional other metadata elements for this quantity should be specified in the Implementation Specification for this service.
     * 
     * Gets the value of the metadata property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the metadata property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMetadata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadataType }
     * </p>
     * 
     * 
     * @return
     *     The value of the metadata property.
     */
    public List<MetadataType> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<>();
        }
        return this.metadata;
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
            AllowedValues theAllowedValues;
            theAllowedValues = this.getAllowedValues();
            strategy.appendField(locator, this, "allowedValues", buffer, theAllowedValues, (this.allowedValues!= null));
        }
        {
            AnyValue theAnyValue;
            theAnyValue = this.getAnyValue();
            strategy.appendField(locator, this, "anyValue", buffer, theAnyValue, (this.anyValue!= null));
        }
        {
            NoValues theNoValues;
            theNoValues = this.getNoValues();
            strategy.appendField(locator, this, "noValues", buffer, theNoValues, (this.noValues!= null));
        }
        {
            ValuesReference theValuesReference;
            theValuesReference = this.getValuesReference();
            strategy.appendField(locator, this, "valuesReference", buffer, theValuesReference, (this.valuesReference!= null));
        }
        {
            ValueType theDefaultValue;
            theDefaultValue = this.getDefaultValue();
            strategy.appendField(locator, this, "defaultValue", buffer, theDefaultValue, (this.defaultValue!= null));
        }
        {
            DomainMetadataType theMeaning;
            theMeaning = this.getMeaning();
            strategy.appendField(locator, this, "meaning", buffer, theMeaning, (this.meaning!= null));
        }
        {
            DomainMetadataType theDataType;
            theDataType = this.getDataType();
            strategy.appendField(locator, this, "dataType", buffer, theDataType, (this.dataType!= null));
        }
        {
            DomainMetadataType theUOM;
            theUOM = this.getUOM();
            strategy.appendField(locator, this, "uom", buffer, theUOM, (this.uom!= null));
        }
        {
            DomainMetadataType theReferenceSystem;
            theReferenceSystem = this.getReferenceSystem();
            strategy.appendField(locator, this, "referenceSystem", buffer, theReferenceSystem, (this.referenceSystem!= null));
        }
        {
            List<MetadataType> theMetadata;
            theMetadata = (((this.metadata!= null)&&(!this.metadata.isEmpty()))?this.getMetadata():null);
            strategy.appendField(locator, this, "metadata", buffer, theMetadata, ((this.metadata!= null)&&(!this.metadata.isEmpty())));
        }
        return buffer;
    }

    public void setMetadata(List<MetadataType> value) {
        this.metadata = null;
        if (value!= null) {
            List<MetadataType> draftl = this.getMetadata();
            draftl.addAll(value);
        }
    }

}
