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
// Generato il: 2026.11.02 alle 03:38:59 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20060504.src;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per SV_PlatformNeutralServiceSpecification_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SV_PlatformNeutralServiceSpecification_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/srv}SV_ServiceSpecification_Type">
 *       <sequence>
 *         <element name="serviceType" type="{http://www.isotc211.org/2005/srv}SV_ServiceType_PropertyType"/>
 *         <element name="implSpec" type="{http://www.isotc211.org/2005/srv}SV_PlatformSpecificServiceSpecification_PropertyType"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SV_PlatformNeutralServiceSpecification_Type", propOrder = {
    "serviceType",
    "implSpec"
})
@XmlSeeAlso({
    SVPlatformSpecificServiceSpecificationType.class
})
public class SVPlatformNeutralServiceSpecificationType
    extends SVServiceSpecificationType
    implements ToString
{

    @XmlElement(required = true)
    protected SVServiceTypePropertyType serviceType;
    @XmlElement(required = true)
    protected SVPlatformSpecificServiceSpecificationPropertyType implSpec;

    /**
     * Recupera il valore della proprietà serviceType.
     * 
     * @return
     *     possible object is
     *     {@link SVServiceTypePropertyType }
     *     
     */
    public SVServiceTypePropertyType getServiceType() {
        return serviceType;
    }

    /**
     * Imposta il valore della proprietà serviceType.
     * 
     * @param value
     *     allowed object is
     *     {@link SVServiceTypePropertyType }
     *     
     */
    public void setServiceType(SVServiceTypePropertyType value) {
        this.serviceType = value;
    }

    public boolean isSetServiceType() {
        return (this.serviceType!= null);
    }

    /**
     * Recupera il valore della proprietà implSpec.
     * 
     * @return
     *     possible object is
     *     {@link SVPlatformSpecificServiceSpecificationPropertyType }
     *     
     */
    public SVPlatformSpecificServiceSpecificationPropertyType getImplSpec() {
        return implSpec;
    }

    /**
     * Imposta il valore della proprietà implSpec.
     * 
     * @param value
     *     allowed object is
     *     {@link SVPlatformSpecificServiceSpecificationPropertyType }
     *     
     */
    public void setImplSpec(SVPlatformSpecificServiceSpecificationPropertyType value) {
        this.implSpec = value;
    }

    public boolean isSetImplSpec() {
        return (this.implSpec!= null);
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
            SVServiceTypePropertyType theServiceType;
            theServiceType = this.getServiceType();
            strategy.appendField(locator, this, "serviceType", buffer, theServiceType, this.isSetServiceType());
        }
        {
            SVPlatformSpecificServiceSpecificationPropertyType theImplSpec;
            theImplSpec = this.getImplSpec();
            strategy.appendField(locator, this, "implSpec", buffer, theImplSpec, this.isSetImplSpec());
        }
        return buffer;
    }

}
