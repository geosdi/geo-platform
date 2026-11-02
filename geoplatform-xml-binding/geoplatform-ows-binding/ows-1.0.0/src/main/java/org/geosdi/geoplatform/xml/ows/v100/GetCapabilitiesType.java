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
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * XML encoded GetCapabilities operation request. This operation allows clients to retrieve service metadata about a specific service instance. In this XML encoding, no "request" parameter is included, since the element name specifies the specific operation. This base type shall be extended by each specific OWS to include the additional required "service" attribute, with the correct value for that OWS.
 * 
 * <p>Classe Java per GetCapabilitiesType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="GetCapabilitiesType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="AcceptVersions" type="{http://www.opengis.net/ows}AcceptVersionsType" minOccurs="0"/>
 *         <element name="Sections" type="{http://www.opengis.net/ows}SectionsType" minOccurs="0"/>
 *         <element name="AcceptFormats" type="{http://www.opengis.net/ows}AcceptFormatsType" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="updateSequence" type="{http://www.opengis.net/ows}UpdateSequenceType" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetCapabilitiesType", propOrder = {
    "acceptVersions",
    "sections",
    "acceptFormats"
})
public class GetCapabilitiesType implements ToString
{

    /**
     * When omitted, server shall return latest supported version.
     * 
     */
    @XmlElement(name = "AcceptVersions")
    protected AcceptVersionsType acceptVersions;
    /**
     * When omitted or not supported by server, server shall return complete service metadata (Capabilities) document.
     * 
     */
    @XmlElement(name = "Sections")
    protected SectionsType sections;
    /**
     * When omitted or not supported by server, server shall return service metadata document using the MIME type "text/xml".
     * 
     */
    @XmlElement(name = "AcceptFormats")
    protected AcceptFormatsType acceptFormats;
    /**
     * When omitted or not supported by server, server shall return latest complete service metadata document.
     * 
     */
    @XmlAttribute(name = "updateSequence")
    protected String updateSequence;

    /**
     * When omitted, server shall return latest supported version.
     * 
     * @return
     *     possible object is
     *     {@link AcceptVersionsType }
     *     
     */
    public AcceptVersionsType getAcceptVersions() {
        return acceptVersions;
    }

    /**
     * Imposta il valore della proprietà acceptVersions.
     * 
     * @param value
     *     allowed object is
     *     {@link AcceptVersionsType }
     *     
     * @see #getAcceptVersions()
     */
    public void setAcceptVersions(AcceptVersionsType value) {
        this.acceptVersions = value;
    }

    /**
     * When omitted or not supported by server, server shall return complete service metadata (Capabilities) document.
     * 
     * @return
     *     possible object is
     *     {@link SectionsType }
     *     
     */
    public SectionsType getSections() {
        return sections;
    }

    /**
     * Imposta il valore della proprietà sections.
     * 
     * @param value
     *     allowed object is
     *     {@link SectionsType }
     *     
     * @see #getSections()
     */
    public void setSections(SectionsType value) {
        this.sections = value;
    }

    /**
     * When omitted or not supported by server, server shall return service metadata document using the MIME type "text/xml".
     * 
     * @return
     *     possible object is
     *     {@link AcceptFormatsType }
     *     
     */
    public AcceptFormatsType getAcceptFormats() {
        return acceptFormats;
    }

    /**
     * Imposta il valore della proprietà acceptFormats.
     * 
     * @param value
     *     allowed object is
     *     {@link AcceptFormatsType }
     *     
     * @see #getAcceptFormats()
     */
    public void setAcceptFormats(AcceptFormatsType value) {
        this.acceptFormats = value;
    }

    /**
     * When omitted or not supported by server, server shall return latest complete service metadata document.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateSequence() {
        return updateSequence;
    }

    /**
     * Imposta il valore della proprietà updateSequence.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getUpdateSequence()
     */
    public void setUpdateSequence(String value) {
        this.updateSequence = value;
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
            AcceptVersionsType theAcceptVersions;
            theAcceptVersions = this.getAcceptVersions();
            strategy.appendField(locator, this, "acceptVersions", buffer, theAcceptVersions, (this.acceptVersions!= null));
        }
        {
            SectionsType theSections;
            theSections = this.getSections();
            strategy.appendField(locator, this, "sections", buffer, theSections, (this.sections!= null));
        }
        {
            AcceptFormatsType theAcceptFormats;
            theAcceptFormats = this.getAcceptFormats();
            strategy.appendField(locator, this, "acceptFormats", buffer, theAcceptFormats, (this.acceptFormats!= null));
        }
        {
            String theUpdateSequence;
            theUpdateSequence = this.getUpdateSequence();
            strategy.appendField(locator, this, "updateSequence", buffer, theUpdateSequence, (this.updateSequence!= null));
        }
        return buffer;
    }

}
