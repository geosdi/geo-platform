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
// Generated on: 2012.10.26 at 03:16:42 PM CEST 
//


package org.geosdi.geoplatform.xml.wfs.v110;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * 
 *             The TransactionType defines the Transaction operation.  A
 *             Transaction element contains one or more Insert, Update
 *             Delete and Native elements that allow a client application
 *             to create, modify or remove feature instances from the 
 *             feature repository that a Web Feature Service controls.
 *          
 * 
 * <p>Java class for TransactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/wfs}BaseRequestType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/wfs}LockId" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{http://www.opengis.net/wfs}Insert"/>
 *           &lt;element ref="{http://www.opengis.net/wfs}Update"/>
 *           &lt;element ref="{http://www.opengis.net/wfs}Delete"/>
 *           &lt;element ref="{http://www.opengis.net/wfs}Native"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="releaseAction" type="{http://www.opengis.net/wfs}AllSomeType" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "Transaction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionType", propOrder = {
    "lockId",
    "insertOrUpdateOrDelete"
})
public class TransactionType
    extends BaseRequestType
    implements ToString
{

    @XmlElement(name = "LockId")
    protected String lockId;
    @XmlElements({
        @XmlElement(name = "Insert", type = InsertElementType.class),
        @XmlElement(name = "Update", type = UpdateElementType.class),
        @XmlElement(name = "Delete", type = DeleteElementType.class),
        @XmlElement(name = "Native", type = NativeType.class)
    })
    protected List<Object> insertOrUpdateOrDelete;
    @XmlAttribute(name = "releaseAction")
    protected AllSomeType releaseAction;

    /**
     * 
     *                         In order for a client application to operate upon
     *                         locked feature instances, the Transaction request
     *                         must include the LockId element.  The content of
     *                         this element must be the lock identifier the client
     *                         application obtained from a previous
     *                         GetFeatureWithLock or LockFeature operation.
     * 
     *                         If the correct lock identifier is specified the Web
     *                         Feature Service knows that the client application may
     *                         operate upon the locked feature instances.
     * 
     *                         No LockId element needs to be specified to operate upon
     *                         unlocked features.
     *                      
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLockId() {
        return lockId;
    }

    /**
     * Sets the value of the lockId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLockId(String value) {
        this.lockId = value;
    }

    /**
     * Gets the value of the insertOrUpdateOrDelete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the insertOrUpdateOrDelete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInsertOrUpdateOrDelete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InsertElementType }
     * {@link UpdateElementType }
     * {@link DeleteElementType }
     * {@link NativeType }
     * 
     * 
     */
    public List<Object> getInsertOrUpdateOrDelete() {
        if (insertOrUpdateOrDelete == null) {
            insertOrUpdateOrDelete = new ArrayList<Object>();
        }
        return this.insertOrUpdateOrDelete;
    }

    /**
     * Gets the value of the releaseAction property.
     * 
     * @return
     *     possible object is
     *     {@link AllSomeType }
     *     
     */
    public AllSomeType getReleaseAction() {
        return releaseAction;
    }

    /**
     * Sets the value of the releaseAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllSomeType }
     *     
     */
    public void setReleaseAction(AllSomeType value) {
        this.releaseAction = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            String theLockId;
            theLockId = this.getLockId();
            strategy.appendField(locator, this, "lockId", buffer, theLockId);
        }
        {
            List<Object> theInsertOrUpdateOrDelete;
            theInsertOrUpdateOrDelete = (((this.insertOrUpdateOrDelete!= null)&&(!this.insertOrUpdateOrDelete.isEmpty()))?this.getInsertOrUpdateOrDelete():null);
            strategy.appendField(locator, this, "insertOrUpdateOrDelete", buffer, theInsertOrUpdateOrDelete);
        }
        {
            AllSomeType theReleaseAction;
            theReleaseAction = this.getReleaseAction();
            strategy.appendField(locator, this, "releaseAction", buffer, theReleaseAction);
        }
        return buffer;
    }

    public void setInsertOrUpdateOrDelete(List<Object> value) {
        this.insertOrUpdateOrDelete = null;
        List<Object> draftl = this.getInsertOrUpdateOrDelete();
        draftl.addAll(value);
    }

}
