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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Definition of a unit of measure which is related to a preferred unit for this quantity type through a conversion formula.  A method for deriving this unit by algebraic combination of more primitive units, may also be provided.
 * 
 * <p>Java class for ConventionalUnitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConventionalUnitType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}UnitDefinitionType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}conversionToPreferredUnit"/>
 *           &lt;element ref="{http://www.opengis.net/gml}roughConversionToPreferredUnit"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}derivationUnitTerm" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConventionalUnitType", propOrder = {
    "conversionToPreferredUnit",
    "roughConversionToPreferredUnit",
    "derivationUnitTerm"
})
public class ConventionalUnitType
        extends UnitDefinitionType {

    protected ConversionToPreferredUnitType conversionToPreferredUnit;
    protected ConversionToPreferredUnitType roughConversionToPreferredUnit;
    private List<DerivationUnitTermType> derivationUnitTerm;

    public ConventionalUnitType() {
    }

    /**
     * Gets the value of the conversionToPreferredUnit property.
     * 
     * @return
     *     possible object is
     *     {@link ConversionToPreferredUnitType }
     *     
     */
    public ConversionToPreferredUnitType getConversionToPreferredUnit() {
        return conversionToPreferredUnit;
    }

    /**
     * Sets the value of the conversionToPreferredUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConversionToPreferredUnitType }
     *     
     */
    public void setConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        this.conversionToPreferredUnit = value;
    }

    /**
     * Gets the value of the roughConversionToPreferredUnit property.
     * 
     * @return
     *     possible object is
     *     {@link ConversionToPreferredUnitType }
     *     
     */
    public ConversionToPreferredUnitType getRoughConversionToPreferredUnit() {
        return roughConversionToPreferredUnit;
    }

    /**
     * Sets the value of the roughConversionToPreferredUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConversionToPreferredUnitType }
     *     
     */
    public void setRoughConversionToPreferredUnit(ConversionToPreferredUnitType value) {
        this.roughConversionToPreferredUnit = value;
    }

    /**
     * @param derivationUnitTerm the derivationUnitTerm to set
     */
    public void setDerivationUnitTerm(List<DerivationUnitTermType> derivationUnitTerm) {
        this.derivationUnitTerm = derivationUnitTerm;
    }

    /**
     * Gets the value of the derivationUnitTerm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the derivationUnitTerm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDerivationUnitTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DerivationUnitTermType }
     * 
     * 
     */
    public List<DerivationUnitTermType> getDerivationUnitTerm() {
        if (derivationUnitTerm == null) {
            this.derivationUnitTerm = new ArrayList<DerivationUnitTermType>();
        }
        return this.derivationUnitTerm;
    }

    @Override
    public String toString() {
        return "ConventionalUnitType{ " + "conversionToPreferredUnit = "
                + conversionToPreferredUnit
                + ", roughConversionToPreferredUnit = "
                + roughConversionToPreferredUnit + ", derivationUnitTerm = "
                + derivationUnitTerm + '}';
    }
}
