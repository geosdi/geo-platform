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
// Generato il: 2019.01.25 alle 11:53:13 AM CET 
//


package org.geosdi.geoplatform.wms.v130;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per anonymous complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Service"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Capability"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.3.0" /&gt;
 *       &lt;attribute name="updateSequence" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "service",
        "capability"
})
@XmlRootElement(name = "WMS_Capabilities")
public class WMSCapabilities implements ToString2 {

    @XmlElement(name = "Service", required = true)
    protected Service service;
    @XmlElement(name = "Capability", required = true)
    protected Capability capability;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "updateSequence")
    protected String updateSequence;

    /**
     * Recupera il valore della proprietà service.
     *
     * @return possible object is
     * {@link Service }
     */
    public Service getService() {
        return service;
    }

    /**
     * Imposta il valore della proprietà service.
     *
     * @param value allowed object is
     *              {@link Service }
     */
    public void setService(Service value) {
        this.service = value;
    }

    /**
     * Recupera il valore della proprietà capability.
     *
     * @return possible object is
     * {@link Capability }
     */
    public Capability getCapability() {
        return capability;
    }

    /**
     * Imposta il valore della proprietà capability.
     *
     * @param value allowed object is
     *              {@link Capability }
     */
    public void setCapability(Capability value) {
        this.capability = value;
    }

    /**
     * Recupera il valore della proprietà version.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersion() {
        if (version == null) {
            return "1.3.0";
        } else {
            return version;
        }
    }

    /**
     * Imposta il valore della proprietà version.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Recupera il valore della proprietà updateSequence.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUpdateSequence() {
        return updateSequence;
    }

    /**
     * Imposta il valore della proprietà updateSequence.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUpdateSequence(String value) {
        this.updateSequence = value;
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
            Service theService;
            theService = this.getService();
            strategy.appendField(locator, this, "service", buffer, theService, (this.service != null));
        }
        {
            Capability theCapability;
            theCapability = this.getCapability();
            strategy.appendField(locator, this, "capability", buffer, theCapability, (this.capability != null));
        }
        {
            String theVersion;
            theVersion = this.getVersion();
            strategy.appendField(locator, this, "version", buffer, theVersion, (this.version != null));
        }
        {
            String theUpdateSequence;
            theUpdateSequence = this.getUpdateSequence();
            strategy.appendField(locator, this, "updateSequence", buffer, theUpdateSequence, (this.updateSequence != null));
        }
        return buffer;
    }

}
