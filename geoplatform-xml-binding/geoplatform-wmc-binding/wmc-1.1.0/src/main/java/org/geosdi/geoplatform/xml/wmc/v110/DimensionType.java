/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:33:43 AM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per DimensionType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="DimensionType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="units" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="unitSymbol" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="userValue" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="default" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="multipleValues" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="nearestValue" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="current" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DimensionType", propOrder = {"value"})
public class DimensionType implements ToString2 {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "units", required = true)
    protected String units;
    @XmlAttribute(name = "unitSymbol", required = true)
    protected String unitSymbol;
    @XmlAttribute(name = "userValue", required = true)
    protected String userValue;
    @XmlAttribute(name = "default")
    protected String _default;
    @XmlAttribute(name = "multipleValues")
    protected Boolean multipleValues;
    @XmlAttribute(name = "nearestValue")
    protected Boolean nearestValue;
    @XmlAttribute(name = "current")
    protected Boolean current;

    /**
     * Recupera il valore della proprietà value.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSetValue() {
        return (this.value != null);
    }

    /**
     * Recupera il valore della proprietà name.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name != null);
    }

    /**
     * Recupera il valore della proprietà units.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUnits() {
        return units;
    }

    /**
     * Imposta il valore della proprietà units.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setUnits(String value) {
        this.units = value;
    }

    public boolean isSetUnits() {
        return (this.units != null);
    }

    /**
     * Recupera il valore della proprietà unitSymbol.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUnitSymbol() {
        return unitSymbol;
    }

    /**
     * Imposta il valore della proprietà unitSymbol.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setUnitSymbol(String value) {
        this.unitSymbol = value;
    }

    public boolean isSetUnitSymbol() {
        return (this.unitSymbol != null);
    }

    /**
     * Recupera il valore della proprietà userValue.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUserValue() {
        return userValue;
    }

    /**
     * Imposta il valore della proprietà userValue.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setUserValue(String value) {
        this.userValue = value;
    }

    public boolean isSetUserValue() {
        return (this.userValue != null);
    }

    /**
     * Recupera il valore della proprietà default.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Imposta il valore della proprietà default.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setDefault(String value) {
        this._default = value;
    }

    public boolean isSetDefault() {
        return (this._default != null);
    }

    /**
     * Recupera il valore della proprietà multipleValues.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isMultipleValues() {
        return multipleValues;
    }

    /**
     * Imposta il valore della proprietà multipleValues.
     *
     * @param value allowed object is
     * {@link Boolean }
     */
    public void setMultipleValues(boolean value) {
        this.multipleValues = value;
    }

    public boolean isSetMultipleValues() {
        return (this.multipleValues != null);
    }

    public void unsetMultipleValues() {
        this.multipleValues = null;
    }

    /**
     * Recupera il valore della proprietà nearestValue.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isNearestValue() {
        return nearestValue;
    }

    /**
     * Imposta il valore della proprietà nearestValue.
     *
     * @param value allowed object is
     * {@link Boolean }
     */
    public void setNearestValue(boolean value) {
        this.nearestValue = value;
    }

    public boolean isSetNearestValue() {
        return (this.nearestValue != null);
    }

    public void unsetNearestValue() {
        this.nearestValue = null;
    }

    /**
     * Recupera il valore della proprietà current.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isCurrent() {
        return current;
    }

    /**
     * Imposta il valore della proprietà current.
     *
     * @param value allowed object is
     * {@link Boolean }
     */
    public void setCurrent(boolean value) {
        this.current = value;
    }

    public boolean isSetCurrent() {
        return (this.current != null);
    }

    public void unsetCurrent() {
        this.current = null;
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE2;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy2 strategy) {
        {
            String theValue;
            theValue = this.getValue();
            strategy.appendField(locator, this, "value", buffer, theValue, this.isSetValue());
        }
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            String theUnits;
            theUnits = this.getUnits();
            strategy.appendField(locator, this, "units", buffer, theUnits, this.isSetUnits());
        }
        {
            String theUnitSymbol;
            theUnitSymbol = this.getUnitSymbol();
            strategy.appendField(locator, this, "unitSymbol", buffer, theUnitSymbol, this.isSetUnitSymbol());
        }
        {
            String theUserValue;
            theUserValue = this.getUserValue();
            strategy.appendField(locator, this, "userValue", buffer, theUserValue, this.isSetUserValue());
        }
        {
            String theDefault;
            theDefault = this.getDefault();
            strategy.appendField(locator, this, "_default", buffer, theDefault, this.isSetDefault());
        }
        {
            boolean theMultipleValues;
            theMultipleValues = (this.isSetMultipleValues() ? this.isMultipleValues() : false);
            strategy.appendField(locator, this, "multipleValues", buffer, theMultipleValues,
                    this.isSetMultipleValues());
        }
        {
            boolean theNearestValue;
            theNearestValue = (this.isSetNearestValue() ? this.isNearestValue() : false);
            strategy.appendField(locator, this, "nearestValue", buffer, theNearestValue, this.isSetNearestValue());
        }
        {
            boolean theCurrent;
            theCurrent = (this.isSetCurrent() ? this.isCurrent() : false);
            strategy.appendField(locator, this, "current", buffer, theCurrent, this.isSetCurrent());
        }
        return buffer;
    }

}
