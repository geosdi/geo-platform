/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Returns a representation of the matching entry. If there is no 
 *          matching record, the response message must be empty.
 * 
 * <p>Java class for GetRecordByIdResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetRecordByIdResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/cat/csw/2.0.2}AbstractRecord" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRecordByIdResponseType", propOrder = {
    "abstractRecord",
    "any"
})
public class GetRecordByIdResponseType
    implements ToString
{

    @XmlElementRef(name = "AbstractRecord", namespace = "http://www.opengis.net/cat/csw/2.0.2", type = JAXBElement.class)
    protected List<JAXBElement<? extends AbstractRecordType>> abstractRecord;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the abstractRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abstractRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbstractRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link AbstractRecordType }{@code >}
     * {@link JAXBElement }{@code <}{@link SummaryRecordType }{@code >}
     * {@link JAXBElement }{@code <}{@link BriefRecordType }{@code >}
     * {@link JAXBElement }{@code <}{@link RecordType }{@code >}
     * {@link JAXBElement }{@code <}{@link DCMIRecordType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends AbstractRecordType>> getAbstractRecord() {
        if (abstractRecord == null) {
            abstractRecord = new ArrayList<JAXBElement<? extends AbstractRecordType>>();
        }
        return this.abstractRecord;
    }

    public boolean isSetAbstractRecord() {
        return ((this.abstractRecord!= null)&&(!this.abstractRecord.isEmpty()));
    }

    public void unsetAbstractRecord() {
        this.abstractRecord = null;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    public boolean isSetAny() {
        return ((this.any!= null)&&(!this.any.isEmpty()));
    }

    public void unsetAny() {
        this.any = null;
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
            List<JAXBElement<? extends AbstractRecordType>> theAbstractRecord;
            theAbstractRecord = (this.isSetAbstractRecord()?this.getAbstractRecord():null);
            strategy.appendField(locator, this, "abstractRecord", buffer, theAbstractRecord);
        }
        {
            List<Object> theAny;
            theAny = (this.isSetAny()?this.getAny():null);
            strategy.appendField(locator, this, "any", buffer, theAny);
        }
        return buffer;
    }

    public void setAbstractRecord(List<JAXBElement<? extends AbstractRecordType>> value) {
        this.abstractRecord = null;
        List<JAXBElement<? extends AbstractRecordType>> draftl = this.getAbstractRecord();
        draftl.addAll(value);
    }

    public void setAny(List<Object> value) {
        this.any = null;
        List<Object> draftl = this.getAny();
        draftl.addAll(value);
    }

}
