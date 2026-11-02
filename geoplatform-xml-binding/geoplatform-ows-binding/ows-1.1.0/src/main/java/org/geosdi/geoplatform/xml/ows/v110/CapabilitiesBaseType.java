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
// Generato il: 2026.11.02 alle 02:51:17 PM CET
//

package org.geosdi.geoplatform.xml.ows.v110;

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
 * XML encoded GetCapabilities operation response. This document provides clients with service metadata about a specific service instance, usually including metadata about the tightly-coupled data served. If the server does not implement the updateSequence parameter, the server shall always return the complete Capabilities document, without the updateSequence parameter. When the server implements the updateSequence parameter and the GetCapabilities operation request included the updateSequence parameter with the current value, the server shall return this element with only the "version" and "updateSequence" attributes. Otherwise, all optional elements shall be included or not depending on the actual value of the Contents parameter in the GetCapabilities operation request. This base type shall be extended by each specific OWS to include the additional contents needed.
 * 
 * <p>Classe Java per CapabilitiesBaseType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="CapabilitiesBaseType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/ows/1.1}ServiceIdentification" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}ServiceProvider" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows/1.1}OperationsMetadata" minOccurs="0"/>
 *       </sequence>
 *       <attribute name="version" use="required" type="{http://www.opengis.net/ows/1.1}VersionType" />
 *       <attribute name="updateSequence" type="{http://www.opengis.net/ows/1.1}UpdateSequenceType" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CapabilitiesBaseType", propOrder = {
    "serviceIdentification",
    "serviceProvider",
    "operationsMetadata"
})
public class CapabilitiesBaseType implements ToString
{

    /**
     * General metadata for this specific server. This XML Schema of this section shall be the same for all OWS.
     * 
     */
    @XmlElement(name = "ServiceIdentification")
    protected ServiceIdentification serviceIdentification;
    /**
     * Metadata about the organization that provides this specific service instance or server.
     * 
     */
    @XmlElement(name = "ServiceProvider")
    protected ServiceProvider serviceProvider;
    /**
     * Metadata about the operations and related abilities specified by this service and implemented by this server, including the URLs for operation requests. The basic contents of this section shall be the same for all OWS types, but individual services can add elements and/or change the optionality of optional elements.
     * 
     */
    @XmlElement(name = "OperationsMetadata")
    protected OperationsMetadata operationsMetadata;
    @XmlAttribute(name = "version", required = true)
    protected String version;
    /**
     * Service metadata document version, having values that are "increased" whenever any change is made in service metadata document. Values are selected by each server, and are always opaque to clients. When not supported by server, server shall not return this attribute.
     * 
     */
    @XmlAttribute(name = "updateSequence")
    protected String updateSequence;

    /**
     * General metadata for this specific server. This XML Schema of this section shall be the same for all OWS.
     * 
     * @return
     *     possible object is
     *     {@link ServiceIdentification }
     *     
     */
    public ServiceIdentification getServiceIdentification() {
        return serviceIdentification;
    }

    /**
     * Imposta il valore della proprietà serviceIdentification.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceIdentification }
     *     
     * @see #getServiceIdentification()
     */
    public void setServiceIdentification(ServiceIdentification value) {
        this.serviceIdentification = value;
    }

    /**
     * Metadata about the organization that provides this specific service instance or server.
     * 
     * @return
     *     possible object is
     *     {@link ServiceProvider }
     *     
     */
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    /**
     * Imposta il valore della proprietà serviceProvider.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceProvider }
     *     
     * @see #getServiceProvider()
     */
    public void setServiceProvider(ServiceProvider value) {
        this.serviceProvider = value;
    }

    /**
     * Metadata about the operations and related abilities specified by this service and implemented by this server, including the URLs for operation requests. The basic contents of this section shall be the same for all OWS types, but individual services can add elements and/or change the optionality of optional elements.
     * 
     * @return
     *     possible object is
     *     {@link OperationsMetadata }
     *     
     */
    public OperationsMetadata getOperationsMetadata() {
        return operationsMetadata;
    }

    /**
     * Imposta il valore della proprietà operationsMetadata.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationsMetadata }
     *     
     * @see #getOperationsMetadata()
     */
    public void setOperationsMetadata(OperationsMetadata value) {
        this.operationsMetadata = value;
    }

    /**
     * Recupera il valore della proprietà version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Imposta il valore della proprietà version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Service metadata document version, having values that are "increased" whenever any change is made in service metadata document. Values are selected by each server, and are always opaque to clients. When not supported by server, server shall not return this attribute.
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
            ServiceIdentification theServiceIdentification;
            theServiceIdentification = this.getServiceIdentification();
            strategy.appendField(locator, this, "serviceIdentification", buffer, theServiceIdentification, (this.serviceIdentification!= null));
        }
        {
            ServiceProvider theServiceProvider;
            theServiceProvider = this.getServiceProvider();
            strategy.appendField(locator, this, "serviceProvider", buffer, theServiceProvider, (this.serviceProvider!= null));
        }
        {
            OperationsMetadata theOperationsMetadata;
            theOperationsMetadata = this.getOperationsMetadata();
            strategy.appendField(locator, this, "operationsMetadata", buffer, theOperationsMetadata, (this.operationsMetadata!= null));
        }
        {
            String theVersion;
            theVersion = this.getVersion();
            strategy.appendField(locator, this, "version", buffer, theVersion, (this.version!= null));
        }
        {
            String theUpdateSequence;
            theUpdateSequence = this.getUpdateSequence();
            strategy.appendField(locator, this, "updateSequence", buffer, theUpdateSequence, (this.updateSequence!= null));
        }
        return buffer;
    }

}
