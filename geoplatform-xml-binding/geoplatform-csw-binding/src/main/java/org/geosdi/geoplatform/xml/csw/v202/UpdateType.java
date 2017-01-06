/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2017 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-b10 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.04.18 at 12:36:36 PM CEST 
//


package org.geosdi.geoplatform.xml.csw.v202;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.w3c.dom.Element;


/**
 * 
 *             Update statements may replace an entire record or only update part 
 *             of a record:
 *             1) To replace an existing record, include a new instance of the 
 *                record;
 *             2) To update selected properties of an existing record, include
 *                a set of RecordProperty elements. The scope of the update
 *                statement  is determined by the Constraint element.
 *                The 'handle' is a local identifier for the action.
 *          
 * 
 * <p>Java class for UpdateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;any namespace='##other'/>
 *           &lt;sequence>
 *             &lt;element ref="{http://www.opengis.net/cat/csw/2.0.2}RecordProperty" maxOccurs="unbounded"/>
 *             &lt;element ref="{http://www.opengis.net/cat/csw/2.0.2}Constraint"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="handle" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateType", propOrder = {
    "any",
    "recordProperty",
    "constraint"
})
public class UpdateType implements ToString
{

    @XmlAnyElement
    protected Element any;
    @XmlElement(name = "RecordProperty")
    protected List<RecordPropertyType> recordProperty;
    @XmlElement(name = "Constraint")
    protected QueryConstraintType constraint;
    @XmlAttribute(name = "handle")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String handle;

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Element }
     *     
     */
    public Element getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Element }
     *     
     */
    public void setAny(Element value) {
        this.any = value;
    }

    public boolean isSetAny() {
        return (this.any!= null);
    }

    /**
     * Gets the value of the recordProperty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recordProperty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecordProperty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RecordPropertyType }
     * 
     * 
     */
    public List<RecordPropertyType> getRecordProperty() {
        if (recordProperty == null) {
            recordProperty = new ArrayList<RecordPropertyType>();
        }
        return this.recordProperty;
    }

    public boolean isSetRecordProperty() {
        return ((this.recordProperty!= null)&&(!this.recordProperty.isEmpty()));
    }

    public void unsetRecordProperty() {
        this.recordProperty = null;
    }

    /**
     * Gets the value of the constraint property.
     * 
     * @return
     *     possible object is
     *     {@link QueryConstraintType }
     *     
     */
    public QueryConstraintType getConstraint() {
        return constraint;
    }

    /**
     * Sets the value of the constraint property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryConstraintType }
     *     
     */
    public void setConstraint(QueryConstraintType value) {
        this.constraint = value;
    }

    public boolean isSetConstraint() {
        return (this.constraint!= null);
    }

    /**
     * Gets the value of the handle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Sets the value of the handle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandle(String value) {
        this.handle = value;
    }

    public boolean isSetHandle() {
        return (this.handle!= null);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            Element theAny;
            theAny = this.getAny();
            strategy.appendField(locator, this, "any", buffer, theAny);
        }
        {
            List<RecordPropertyType> theRecordProperty;
            theRecordProperty = (this.isSetRecordProperty()?this.getRecordProperty():null);
            strategy.appendField(locator, this, "recordProperty", buffer, theRecordProperty);
        }
        {
            QueryConstraintType theConstraint;
            theConstraint = this.getConstraint();
            strategy.appendField(locator, this, "constraint", buffer, theConstraint);
        }
        {
            String theHandle;
            theHandle = this.getHandle();
            strategy.appendField(locator, this, "handle", buffer, theHandle);
        }
        return buffer;
    }

    public void setRecordProperty(List<RecordPropertyType> value) {
        this.recordProperty = null;
        List<RecordPropertyType> draftl = this.getRecordProperty();
        draftl.addAll(value);
    }

}
