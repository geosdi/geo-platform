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

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per ProcessBriefType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="ProcessBriefType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/wps/1.0.0}DescriptionType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Profile" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wps/1.0.0}WSDL" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute ref="{http://www.opengis.net/wps/1.0.0}processVersion use="required""/&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessBriefType", propOrder = {
        "profile",
        "wsdl"
})
@XmlSeeAlso({
        ProcessDescriptionType.class
})
public class ProcessBriefType extends DescriptionType implements ToString2 {

    @XmlElement(name = "Profile")
    @XmlSchemaType(name = "anyURI")
    protected List<String> profile;
    @XmlElement(name = "WSDL")
    protected WSDL wsdl;
    @XmlAttribute(name = "processVersion", namespace = "http://www.opengis.net/wps/1.0.0", required = true)
    protected String processVersion;

    /**
     * Gets the value of the profile property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the profile property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProfile().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getProfile() {
        if (profile == null) {
            profile = new ArrayList<String>();
        }
        return this.profile;
    }

    public boolean isSetProfile() {
        return ((this.profile != null) && (!this.profile.isEmpty()));
    }

    public void unsetProfile() {
        this.profile = null;
    }

    /**
     * Location of a WSDL document which describes this process.
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
     * Recupera il valore della proprietà processVersion.
     *
     * @return possible object is
     * {@link String }
     */
    public String getProcessVersion() {
        return processVersion;
    }

    /**
     * Imposta il valore della proprietà processVersion.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setProcessVersion(String value) {
        this.processVersion = value;
    }

    public boolean isSetProcessVersion() {
        return (this.processVersion != null);
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
            List<String> theProfile;
            theProfile = (this.isSetProfile() ? this.getProfile() : null);
            strategy.appendField(locator, this, "profile", buffer, theProfile, this.isSetProfile());
        }
        {
            WSDL theWSDL;
            theWSDL = this.getWSDL();
            strategy.appendField(locator, this, "wsdl", buffer, theWSDL, this.isSetWSDL());
        }
        {
            String theProcessVersion;
            theProcessVersion = this.getProcessVersion();
            strategy.appendField(locator, this, "processVersion", buffer, theProcessVersion, this.isSetProcessVersion());
        }
        return buffer;
    }

    public void setProfile(List<String> value) {
        this.profile = null;
        if (value != null) {
            List<String> draftl = this.getProfile();
            draftl.addAll(value);
        }
    }

}
