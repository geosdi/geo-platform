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
// Generato il: 2026.11.02 alle 03:48:06 PM CET
//

package org.geosdi.geoplatform.xml.gfc;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.TypeNamePropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.UnitOfMeasurePropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Characteristic of a feature type.
 * 
 * <p>Classe Java per FC_FeatureAttribute_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="FC_FeatureAttribute_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gfc}AbstractFC_PropertyType_Type">
 *       <sequence>
 *         <element name="code" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="valueMeasurementUnit" type="{http://www.isotc211.org/2005/gco}UnitOfMeasure_PropertyType" minOccurs="0"/>
 *         <element name="valueType" type="{http://www.isotc211.org/2005/gco}TypeName_PropertyType" minOccurs="0"/>
 *         <element name="listedValue" type="{http://www.isotc211.org/2005/gfc}FC_ListedValue_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FC_FeatureAttribute_Type", propOrder = {
    "code",
    "valueMeasurementUnit",
    "valueType",
    "listedValue"
})
public class FCFeatureAttributeType
    extends AbstractFCPropertyTypeType
    implements ToString
{

    protected CharacterStringPropertyType code;
    protected UnitOfMeasurePropertyType valueMeasurementUnit;
    protected TypeNamePropertyType valueType;
    protected List<FCListedValuePropertyType> listedValue;

    /**
     * Recupera il valore della proprietà code.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getCode() {
        return code;
    }

    /**
     * Imposta il valore della proprietà code.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setCode(CharacterStringPropertyType value) {
        this.code = value;
    }

    public boolean isSetCode() {
        return (this.code!= null);
    }

    /**
     * Recupera il valore della proprietà valueMeasurementUnit.
     * 
     * @return
     *     possible object is
     *     {@link UnitOfMeasurePropertyType }
     *     
     */
    public UnitOfMeasurePropertyType getValueMeasurementUnit() {
        return valueMeasurementUnit;
    }

    /**
     * Imposta il valore della proprietà valueMeasurementUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitOfMeasurePropertyType }
     *     
     */
    public void setValueMeasurementUnit(UnitOfMeasurePropertyType value) {
        this.valueMeasurementUnit = value;
    }

    public boolean isSetValueMeasurementUnit() {
        return (this.valueMeasurementUnit!= null);
    }

    /**
     * Recupera il valore della proprietà valueType.
     * 
     * @return
     *     possible object is
     *     {@link TypeNamePropertyType }
     *     
     */
    public TypeNamePropertyType getValueType() {
        return valueType;
    }

    /**
     * Imposta il valore della proprietà valueType.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeNamePropertyType }
     *     
     */
    public void setValueType(TypeNamePropertyType value) {
        this.valueType = value;
    }

    public boolean isSetValueType() {
        return (this.valueType!= null);
    }

    /**
     * Gets the value of the listedValue property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the listedValue property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getListedValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FCListedValuePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the listedValue property.
     */
    public List<FCListedValuePropertyType> getListedValue() {
        if (listedValue == null) {
            listedValue = new ArrayList<>();
        }
        return this.listedValue;
    }

    public boolean isSetListedValue() {
        return ((this.listedValue!= null)&&(!this.listedValue.isEmpty()));
    }

    public void unsetListedValue() {
        this.listedValue = null;
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
            CharacterStringPropertyType theCode;
            theCode = this.getCode();
            strategy.appendField(locator, this, "code", buffer, theCode, this.isSetCode());
        }
        {
            UnitOfMeasurePropertyType theValueMeasurementUnit;
            theValueMeasurementUnit = this.getValueMeasurementUnit();
            strategy.appendField(locator, this, "valueMeasurementUnit", buffer, theValueMeasurementUnit, this.isSetValueMeasurementUnit());
        }
        {
            TypeNamePropertyType theValueType;
            theValueType = this.getValueType();
            strategy.appendField(locator, this, "valueType", buffer, theValueType, this.isSetValueType());
        }
        {
            List<FCListedValuePropertyType> theListedValue;
            theListedValue = (this.isSetListedValue()?this.getListedValue():null);
            strategy.appendField(locator, this, "listedValue", buffer, theListedValue, this.isSetListedValue());
        }
        return buffer;
    }

    public void setListedValue(List<FCListedValuePropertyType> value) {
        this.listedValue = null;
        if (value!= null) {
            List<FCListedValuePropertyType> draftl = this.getListedValue();
            draftl.addAll(value);
        }
    }

}
