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

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/ows}DescriptionType">
 *       <sequence>
 *         <element name="ServiceType" type="{http://www.opengis.net/ows}CodeType"/>
 *         <element name="ServiceTypeVersion" type="{http://www.opengis.net/ows}VersionType" maxOccurs="unbounded"/>
 *         <element ref="{http://www.opengis.net/ows}Fees" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows}AccessConstraints" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceType",
    "serviceTypeVersion",
    "fees",
    "accessConstraints"
})
@XmlRootElement(name = "ServiceIdentification")
public class ServiceIdentification
    extends DescriptionType
    implements ToString
{

    /**
     * A service type name from a registry of services. For example, the values of the codeSpace URI and name and code string may be "OGC" and "catalogue." This type name is normally used for machine-to-machine communication.
     * 
     */
    @XmlElement(name = "ServiceType", required = true)
    protected CodeType serviceType;
    /**
     * Unordered list of one or more versions of this service type implemented by this server. This information is not adequate for version negotiation, and shall not be used for that purpose.
     * 
     */
    @XmlElement(name = "ServiceTypeVersion", required = true)
    protected List<String> serviceTypeVersion;
    /**
     * If this element is omitted, no meaning is implied.
     * 
     */
    @XmlElement(name = "Fees")
    protected String fees;
    /**
     * Unordered list of access constraints applied to assure the protection of privacy or intellectual property, and any other restrictions on retrieving or using data from or otherwise using this server. The reserved value NONE (case insensitive) shall be used to mean no access constraints are imposed. If this element is omitted, no meaning is implied.
     * 
     */
    @XmlElement(name = "AccessConstraints")
    protected List<String> accessConstraints;

    /**
     * A service type name from a registry of services. For example, the values of the codeSpace URI and name and code string may be "OGC" and "catalogue." This type name is normally used for machine-to-machine communication.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getServiceType() {
        return serviceType;
    }

    /**
     * Imposta il valore della proprietà serviceType.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     * @see #getServiceType()
     */
    public void setServiceType(CodeType value) {
        this.serviceType = value;
    }

    /**
     * Unordered list of one or more versions of this service type implemented by this server. This information is not adequate for version negotiation, and shall not be used for that purpose.
     * 
     * Gets the value of the serviceTypeVersion property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the serviceTypeVersion property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getServiceTypeVersion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the serviceTypeVersion property.
     */
    public List<String> getServiceTypeVersion() {
        if (serviceTypeVersion == null) {
            serviceTypeVersion = new ArrayList<>();
        }
        return this.serviceTypeVersion;
    }

    /**
     * If this element is omitted, no meaning is implied.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFees() {
        return fees;
    }

    /**
     * Imposta il valore della proprietà fees.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getFees()
     */
    public void setFees(String value) {
        this.fees = value;
    }

    /**
     * Unordered list of access constraints applied to assure the protection of privacy or intellectual property, and any other restrictions on retrieving or using data from or otherwise using this server. The reserved value NONE (case insensitive) shall be used to mean no access constraints are imposed. If this element is omitted, no meaning is implied.
     * 
     * Gets the value of the accessConstraints property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the accessConstraints property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAccessConstraints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the accessConstraints property.
     */
    public List<String> getAccessConstraints() {
        if (accessConstraints == null) {
            accessConstraints = new ArrayList<>();
        }
        return this.accessConstraints;
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
        super.appendFields(locator, buffer, strategy);
        {
            CodeType theServiceType;
            theServiceType = this.getServiceType();
            strategy.appendField(locator, this, "serviceType", buffer, theServiceType, (this.serviceType!= null));
        }
        {
            List<String> theServiceTypeVersion;
            theServiceTypeVersion = (((this.serviceTypeVersion!= null)&&(!this.serviceTypeVersion.isEmpty()))?this.getServiceTypeVersion():null);
            strategy.appendField(locator, this, "serviceTypeVersion", buffer, theServiceTypeVersion, ((this.serviceTypeVersion!= null)&&(!this.serviceTypeVersion.isEmpty())));
        }
        {
            String theFees;
            theFees = this.getFees();
            strategy.appendField(locator, this, "fees", buffer, theFees, (this.fees!= null));
        }
        {
            List<String> theAccessConstraints;
            theAccessConstraints = (((this.accessConstraints!= null)&&(!this.accessConstraints.isEmpty()))?this.getAccessConstraints():null);
            strategy.appendField(locator, this, "accessConstraints", buffer, theAccessConstraints, ((this.accessConstraints!= null)&&(!this.accessConstraints.isEmpty())));
        }
        return buffer;
    }

    public void setServiceTypeVersion(List<String> value) {
        this.serviceTypeVersion = null;
        if (value!= null) {
            List<String> draftl = this.getServiceTypeVersion();
            draftl.addAll(value);
        }
    }

    public void setAccessConstraints(List<String> value) {
        this.accessConstraints = null;
        if (value!= null) {
            List<String> draftl = this.getAccessConstraints();
            draftl.addAll(value);
        }
    }

}
