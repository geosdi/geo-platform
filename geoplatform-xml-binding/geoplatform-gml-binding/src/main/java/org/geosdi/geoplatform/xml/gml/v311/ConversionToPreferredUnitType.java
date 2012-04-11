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
import javax.xml.bind.annotation.XmlType;

/**
 * Relation of a unit to the preferred unit for this quantity type, specified by an arithmetic conversion (scaling and/or offset). A preferred unit is either a base unit or a derived unit selected for all units of one quantity type. The mandatory attribute "uom" shall reference the preferred unit that this conversion applies to. The conversion is specified by one of two alternative elements: "factor" or "formula".
 * 
 * <p>Java class for ConversionToPreferredUnitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConversionToPreferredUnitType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}UnitOfMeasureType">
 *       &lt;choice>
 *         &lt;element name="factor" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="formula" type="{http://www.opengis.net/gml}FormulaType"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConversionToPreferredUnitType", propOrder = {
    "factor",
    "formula"
})
public class ConversionToPreferredUnitType
        extends UnitOfMeasureType {

    protected Double factor;
    protected FormulaType formula;

    public ConversionToPreferredUnitType() {
    }

    /**
     * Gets the value of the factor property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFactor() {
        return factor;
    }

    /**
     * Sets the value of the factor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFactor(Double value) {
        this.factor = value;
    }

    /**
     * Gets the value of the formula property.
     * 
     * @return
     *     possible object is
     *     {@link FormulaType }
     *     
     */
    public FormulaType getFormula() {
        return formula;
    }

    /**
     * Sets the value of the formula property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormulaType }
     *     
     */
    public void setFormula(FormulaType value) {
        this.formula = value;
    }

    @Override
    public String toString() {
        return "ConversionToPreferredUnitType{ " + "factor = "
                + factor + ", formula = " + formula + '}';
    }
}
