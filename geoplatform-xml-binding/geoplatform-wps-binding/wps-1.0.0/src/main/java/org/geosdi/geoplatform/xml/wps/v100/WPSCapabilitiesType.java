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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.geosdi.geoplatform.xml.ows.v110.CapabilitiesBaseType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per WPSCapabilitiesType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="WPSCapabilitiesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/ows/1.1}CapabilitiesBaseType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/wps/1.0.0}ProcessOfferings"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wps/1.0.0}Languages"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wps/1.0.0}WSDL" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="service" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" fixed="WPS" /&gt;
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang use="required""/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WPSCapabilitiesType", propOrder = {
        "processOfferings",
        "languages",
        "wsdl"
})
public class WPSCapabilitiesType extends CapabilitiesBaseType implements ToString2 {

    @XmlElement(name = "ProcessOfferings", required = true)
    protected ProcessOfferings processOfferings;
    @XmlElement(name = "Languages", required = true)
    protected Languages languages;
    @XmlElement(name = "WSDL")
    protected WSDL wsdl;
    @XmlAttribute(name = "service", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String service;
    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace", required = true)
    protected String lang;

    /**
     * Recupera il valore della proprietà processOfferings.
     *
     * @return possible object is
     * {@link ProcessOfferings }
     */
    public ProcessOfferings getProcessOfferings() {
        return processOfferings;
    }

    /**
     * Imposta il valore della proprietà processOfferings.
     *
     * @param value allowed object is
     *              {@link ProcessOfferings }
     */
    public void setProcessOfferings(ProcessOfferings value) {
        this.processOfferings = value;
    }

    public boolean isSetProcessOfferings() {
        return (this.processOfferings != null);
    }

    /**
     * List of the default and other languages supported by this service.
     *
     * @return possible object is
     * {@link Languages }
     */
    public Languages getLanguages() {
        return languages;
    }

    /**
     * Imposta il valore della proprietà languages.
     *
     * @param value allowed object is
     *              {@link Languages }
     */
    public void setLanguages(Languages value) {
        this.languages = value;
    }

    public boolean isSetLanguages() {
        return (this.languages != null);
    }

    /**
     * Location of a WSDL document which describes the entire service.
     *
     * @return possible object is
     * {@link WSDL }
     */
    public WSDL getWSDL() {
        return wsdl;
    }

    /**
     * Imposta il valore della proprietà wsdl.
     *
     * @param value allowed object is
     *              {@link WSDL }
     */
    public void setWSDL(WSDL value) {
        this.wsdl = value;
    }

    public boolean isSetWSDL() {
        return (this.wsdl != null);
    }

    /**
     * Recupera il valore della proprietà service.
     *
     * @return possible object is
     * {@link String }
     */
    public String getService() {
        if (service == null) {
            return "WPS";
        } else {
            return service;
        }
    }

    /**
     * Imposta il valore della proprietà service.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setService(String value) {
        this.service = value;
    }

    public boolean isSetService() {
        return (this.service != null);
    }

    /**
     * Recupera il valore della proprietà lang.
     *
     * @return possible object is
     * {@link String }
     */
    public String getLang() {
        return lang;
    }

    /**
     * Imposta il valore della proprietà lang.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLang(String value) {
        this.lang = value;
    }

    public boolean isSetLang() {
        return (this.lang != null);
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
        super.appendFields(locator, buffer, strategy);
        {
            ProcessOfferings theProcessOfferings;
            theProcessOfferings = this.getProcessOfferings();
            strategy.appendField(locator, this, "processOfferings", buffer, theProcessOfferings, this.isSetProcessOfferings());
        }
        {
            Languages theLanguages;
            theLanguages = this.getLanguages();
            strategy.appendField(locator, this, "languages", buffer, theLanguages, this.isSetLanguages());
        }
        {
            WSDL theWSDL;
            theWSDL = this.getWSDL();
            strategy.appendField(locator, this, "wsdl", buffer, theWSDL, this.isSetWSDL());
        }
        {
            String theService;
            theService = this.getService();
            strategy.appendField(locator, this, "service", buffer, theService, this.isSetService());
        }
        {
            String theLang;
            theLang = this.getLang();
            strategy.appendField(locator, this, "lang", buffer, theLang, this.isSetLang());
        }
        return buffer;
    }

}
