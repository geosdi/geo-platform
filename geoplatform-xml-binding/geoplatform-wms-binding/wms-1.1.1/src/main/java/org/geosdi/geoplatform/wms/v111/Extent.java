//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.24 alle 07:06:40 PM CET 
//


package org.geosdi.geoplatform.wms.v111;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "Extent")
public class Extent implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(name = "default")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String _default;
    @XmlAttribute(name = "nearestValue")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String nearestValue;
    @XmlAttribute(name = "multipleValues")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String multipleValues;
    @XmlAttribute(name = "current")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String current;
    @XmlValue
    protected String value;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà default.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefault() {
        return _default;
    }

    /**
     * Imposta il valore della proprietà default.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefault(String value) {
        this._default = value;
    }

    /**
     * Recupera il valore della proprietà nearestValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNearestValue() {
        if (nearestValue == null) {
            return "0";
        } else {
            return nearestValue;
        }
    }

    /**
     * Imposta il valore della proprietà nearestValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNearestValue(String value) {
        this.nearestValue = value;
    }

    /**
     * Recupera il valore della proprietà multipleValues.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMultipleValues() {
        if (multipleValues == null) {
            return "0";
        } else {
            return multipleValues;
        }
    }

    /**
     * Imposta il valore della proprietà multipleValues.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMultipleValues(String value) {
        this.multipleValues = value;
    }

    /**
     * Recupera il valore della proprietà current.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrent() {
        if (current == null) {
            return "0";
        } else {
            return current;
        }
    }

    /**
     * Imposta il valore della proprietà current.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrent(String value) {
        this.current = value;
    }

    /**
     * Recupera il valore della proprietà value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getvalue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setvalue(String value) {
        this.value = value;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, (this.name!= null));
        }
        {
            String theDefault;
            theDefault = this.getDefault();
            strategy.appendField(locator, this, "_default", buffer, theDefault, (this._default!= null));
        }
        {
            String theNearestValue;
            theNearestValue = this.getNearestValue();
            strategy.appendField(locator, this, "nearestValue", buffer, theNearestValue, (this.nearestValue!= null));
        }
        {
            String theMultipleValues;
            theMultipleValues = this.getMultipleValues();
            strategy.appendField(locator, this, "multipleValues", buffer, theMultipleValues, (this.multipleValues!= null));
        }
        {
            String theCurrent;
            theCurrent = this.getCurrent();
            strategy.appendField(locator, this, "current", buffer, theCurrent, (this.current!= null));
        }
        {
            String thevalue;
            thevalue = this.getvalue();
            strategy.appendField(locator, this, "value", buffer, thevalue, (this.value!= null));
        }
        return buffer;
    }

}
