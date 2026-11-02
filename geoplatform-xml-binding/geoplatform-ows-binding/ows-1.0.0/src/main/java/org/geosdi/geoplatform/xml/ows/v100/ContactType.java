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
// Generato il: 2026.11.02 alle 03:48:08 PM CET
//

package org.geosdi.geoplatform.xml.ows.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * For OWS use in the service metadata document, the optional hoursOfService and contactInstructions elements were retained, as possibly being useful in the ServiceProvider section.
 * 
 * <p>Classe Java per ContactType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="ContactType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Phone" type="{http://www.opengis.net/ows}TelephoneType" minOccurs="0"/>
 *         <element name="Address" type="{http://www.opengis.net/ows}AddressType" minOccurs="0"/>
 *         <element name="OnlineResource" type="{http://www.opengis.net/ows}OnlineResourceType" minOccurs="0"/>
 *         <element name="HoursOfService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ContactInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactType", propOrder = {
    "phone",
    "address",
    "onlineResource",
    "hoursOfService",
    "contactInstructions"
})
public class ContactType implements ToString
{

    /**
     * Telephone numbers at which the organization or individual may be contacted.
     * 
     */
    @XmlElement(name = "Phone")
    protected TelephoneType phone;
    /**
     * Physical and email address at which the organization or individual may be contacted.
     * 
     */
    @XmlElement(name = "Address")
    protected AddressType address;
    /**
     * On-line information that can be used to contact the individual or organization. OWS specifics: The xlink:href attribute in the xlink:simpleLink attribute group shall be used to reference this resource. Whenever practical, the xlink:href attribute with type anyURI should be a URL from which more contact information can be electronically retrieved. The xlink:title attribute with type "string" can be used to name this set of information. The other attributes in the xlink:simpleLink attribute group should not be used.
     * 
     */
    @XmlElement(name = "OnlineResource")
    protected OnlineResourceType onlineResource;
    /**
     * Time period (including time zone) when individuals can contact the organization or individual.
     * 
     */
    @XmlElement(name = "HoursOfService")
    protected String hoursOfService;
    /**
     * Supplemental instructions on how or when to contact the individual or organization.
     * 
     */
    @XmlElement(name = "ContactInstructions")
    protected String contactInstructions;

    /**
     * Telephone numbers at which the organization or individual may be contacted.
     * 
     * @return
     *     possible object is
     *     {@link TelephoneType }
     *     
     */
    public TelephoneType getPhone() {
        return phone;
    }

    /**
     * Imposta il valore della proprietà phone.
     * 
     * @param value
     *     allowed object is
     *     {@link TelephoneType }
     *     
     * @see #getPhone()
     */
    public void setPhone(TelephoneType value) {
        this.phone = value;
    }

    /**
     * Physical and email address at which the organization or individual may be contacted.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getAddress() {
        return address;
    }

    /**
     * Imposta il valore della proprietà address.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     * @see #getAddress()
     */
    public void setAddress(AddressType value) {
        this.address = value;
    }

    /**
     * On-line information that can be used to contact the individual or organization. OWS specifics: The xlink:href attribute in the xlink:simpleLink attribute group shall be used to reference this resource. Whenever practical, the xlink:href attribute with type anyURI should be a URL from which more contact information can be electronically retrieved. The xlink:title attribute with type "string" can be used to name this set of information. The other attributes in the xlink:simpleLink attribute group should not be used.
     * 
     * @return
     *     possible object is
     *     {@link OnlineResourceType }
     *     
     */
    public OnlineResourceType getOnlineResource() {
        return onlineResource;
    }

    /**
     * Imposta il valore della proprietà onlineResource.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineResourceType }
     *     
     * @see #getOnlineResource()
     */
    public void setOnlineResource(OnlineResourceType value) {
        this.onlineResource = value;
    }

    /**
     * Time period (including time zone) when individuals can contact the organization or individual.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoursOfService() {
        return hoursOfService;
    }

    /**
     * Imposta il valore della proprietà hoursOfService.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getHoursOfService()
     */
    public void setHoursOfService(String value) {
        this.hoursOfService = value;
    }

    /**
     * Supplemental instructions on how or when to contact the individual or organization.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactInstructions() {
        return contactInstructions;
    }

    /**
     * Imposta il valore della proprietà contactInstructions.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getContactInstructions()
     */
    public void setContactInstructions(String value) {
        this.contactInstructions = value;
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
            TelephoneType thePhone;
            thePhone = this.getPhone();
            strategy.appendField(locator, this, "phone", buffer, thePhone, (this.phone!= null));
        }
        {
            AddressType theAddress;
            theAddress = this.getAddress();
            strategy.appendField(locator, this, "address", buffer, theAddress, (this.address!= null));
        }
        {
            OnlineResourceType theOnlineResource;
            theOnlineResource = this.getOnlineResource();
            strategy.appendField(locator, this, "onlineResource", buffer, theOnlineResource, (this.onlineResource!= null));
        }
        {
            String theHoursOfService;
            theHoursOfService = this.getHoursOfService();
            strategy.appendField(locator, this, "hoursOfService", buffer, theHoursOfService, (this.hoursOfService!= null));
        }
        {
            String theContactInstructions;
            theContactInstructions = this.getContactInstructions();
            strategy.appendField(locator, this, "contactInstructions", buffer, theContactInstructions, (this.contactInstructions!= null));
        }
        return buffer;
    }

}
