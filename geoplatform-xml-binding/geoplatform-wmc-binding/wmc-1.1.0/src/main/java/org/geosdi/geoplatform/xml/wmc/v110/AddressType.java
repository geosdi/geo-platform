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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per AddressType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="AddressType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AddressType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StateOrProvince" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PostCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", propOrder = {"addressType", "address", "city", "stateOrProvince", "postCode", "country"})
public class AddressType implements ToString2 {

    @XmlElement(name = "AddressType")
    protected String addressType;
    @XmlElement(name = "Address")
    protected String address;
    @XmlElement(name = "City")
    protected String city;
    @XmlElement(name = "StateOrProvince")
    protected String stateOrProvince;
    @XmlElement(name = "PostCode")
    protected String postCode;
    @XmlElement(name = "Country")
    protected String country;

    /**
     * Recupera il valore della proprietà addressType.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAddressType() {
        return addressType;
    }

    /**
     * Imposta il valore della proprietà addressType.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setAddressType(String value) {
        this.addressType = value;
    }

    public boolean isSetAddressType() {
        return (this.addressType != null);
    }

    /**
     * Recupera il valore della proprietà address.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAddress() {
        return address;
    }

    /**
     * Imposta il valore della proprietà address.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setAddress(String value) {
        this.address = value;
    }

    public boolean isSetAddress() {
        return (this.address != null);
    }

    /**
     * Recupera il valore della proprietà city.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCity() {
        return city;
    }

    /**
     * Imposta il valore della proprietà city.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setCity(String value) {
        this.city = value;
    }

    public boolean isSetCity() {
        return (this.city != null);
    }

    /**
     * Recupera il valore della proprietà stateOrProvince.
     *
     * @return possible object is
     * {@link String }
     */
    public String getStateOrProvince() {
        return stateOrProvince;
    }

    /**
     * Imposta il valore della proprietà stateOrProvince.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setStateOrProvince(String value) {
        this.stateOrProvince = value;
    }

    public boolean isSetStateOrProvince() {
        return (this.stateOrProvince != null);
    }

    /**
     * Recupera il valore della proprietà postCode.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Imposta il valore della proprietà postCode.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setPostCode(String value) {
        this.postCode = value;
    }

    public boolean isSetPostCode() {
        return (this.postCode != null);
    }

    /**
     * Recupera il valore della proprietà country.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCountry() {
        return country;
    }

    /**
     * Imposta il valore della proprietà country.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setCountry(String value) {
        this.country = value;
    }

    public boolean isSetCountry() {
        return (this.country != null);
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
            String theAddressType;
            theAddressType = this.getAddressType();
            strategy.appendField(locator, this, "addressType", buffer, theAddressType, this.isSetAddressType());
        }
        {
            String theAddress;
            theAddress = this.getAddress();
            strategy.appendField(locator, this, "address", buffer, theAddress, this.isSetAddress());
        }
        {
            String theCity;
            theCity = this.getCity();
            strategy.appendField(locator, this, "city", buffer, theCity, this.isSetCity());
        }
        {
            String theStateOrProvince;
            theStateOrProvince = this.getStateOrProvince();
            strategy.appendField(locator, this, "stateOrProvince", buffer, theStateOrProvince,
                    this.isSetStateOrProvince());
        }
        {
            String thePostCode;
            thePostCode = this.getPostCode();
            strategy.appendField(locator, this, "postCode", buffer, thePostCode, this.isSetPostCode());
        }
        {
            String theCountry;
            theCountry = this.getCountry();
            strategy.appendField(locator, this, "country", buffer, theCountry, this.isSetCountry());
        }
        return buffer;
    }

}
