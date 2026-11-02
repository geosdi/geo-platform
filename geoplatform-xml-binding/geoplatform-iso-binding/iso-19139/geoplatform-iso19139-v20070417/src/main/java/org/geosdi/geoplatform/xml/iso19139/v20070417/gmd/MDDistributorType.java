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
// Generato il: 2026.11.02 alle 03:48:01 PM CET
//

package org.geosdi.geoplatform.xml.iso19139.v20070417.gmd;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Information about the distributor
 * 
 * <p>Classe Java per MD_Distributor_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_Distributor_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="distributorContact" type="{http://www.isotc211.org/2005/gmd}CI_ResponsibleParty_PropertyType"/>
 *         <element name="distributionOrderProcess" type="{http://www.isotc211.org/2005/gmd}MD_StandardOrderProcess_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="distributorFormat" type="{http://www.isotc211.org/2005/gmd}MD_Format_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="distributorTransferOptions" type="{http://www.isotc211.org/2005/gmd}MD_DigitalTransferOptions_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_Distributor_Type", propOrder = {
    "distributorContact",
    "distributionOrderProcess",
    "distributorFormat",
    "distributorTransferOptions"
})
public class MDDistributorType
    extends AbstractObjectType
    implements ToString
{

    @XmlElement(required = true)
    protected CIResponsiblePartyPropertyType distributorContact;
    protected List<MDStandardOrderProcessPropertyType> distributionOrderProcess;
    protected List<MDFormatPropertyType> distributorFormat;
    protected List<MDDigitalTransferOptionsPropertyType> distributorTransferOptions;

    /**
     * Recupera il valore della proprietà distributorContact.
     * 
     * @return
     *     possible object is
     *     {@link CIResponsiblePartyPropertyType }
     *     
     */
    public CIResponsiblePartyPropertyType getDistributorContact() {
        return distributorContact;
    }

    /**
     * Imposta il valore della proprietà distributorContact.
     * 
     * @param value
     *     allowed object is
     *     {@link CIResponsiblePartyPropertyType }
     *     
     */
    public void setDistributorContact(CIResponsiblePartyPropertyType value) {
        this.distributorContact = value;
    }

    public boolean isSetDistributorContact() {
        return (this.distributorContact!= null);
    }

    /**
     * Gets the value of the distributionOrderProcess property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the distributionOrderProcess property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDistributionOrderProcess().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDStandardOrderProcessPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the distributionOrderProcess property.
     */
    public List<MDStandardOrderProcessPropertyType> getDistributionOrderProcess() {
        if (distributionOrderProcess == null) {
            distributionOrderProcess = new ArrayList<>();
        }
        return this.distributionOrderProcess;
    }

    public boolean isSetDistributionOrderProcess() {
        return ((this.distributionOrderProcess!= null)&&(!this.distributionOrderProcess.isEmpty()));
    }

    public void unsetDistributionOrderProcess() {
        this.distributionOrderProcess = null;
    }

    /**
     * Gets the value of the distributorFormat property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the distributorFormat property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDistributorFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDFormatPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the distributorFormat property.
     */
    public List<MDFormatPropertyType> getDistributorFormat() {
        if (distributorFormat == null) {
            distributorFormat = new ArrayList<>();
        }
        return this.distributorFormat;
    }

    public boolean isSetDistributorFormat() {
        return ((this.distributorFormat!= null)&&(!this.distributorFormat.isEmpty()));
    }

    public void unsetDistributorFormat() {
        this.distributorFormat = null;
    }

    /**
     * Gets the value of the distributorTransferOptions property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the distributorTransferOptions property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDistributorTransferOptions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDDigitalTransferOptionsPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the distributorTransferOptions property.
     */
    public List<MDDigitalTransferOptionsPropertyType> getDistributorTransferOptions() {
        if (distributorTransferOptions == null) {
            distributorTransferOptions = new ArrayList<>();
        }
        return this.distributorTransferOptions;
    }

    public boolean isSetDistributorTransferOptions() {
        return ((this.distributorTransferOptions!= null)&&(!this.distributorTransferOptions.isEmpty()));
    }

    public void unsetDistributorTransferOptions() {
        this.distributorTransferOptions = null;
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
            CIResponsiblePartyPropertyType theDistributorContact;
            theDistributorContact = this.getDistributorContact();
            strategy.appendField(locator, this, "distributorContact", buffer, theDistributorContact, this.isSetDistributorContact());
        }
        {
            List<MDStandardOrderProcessPropertyType> theDistributionOrderProcess;
            theDistributionOrderProcess = (this.isSetDistributionOrderProcess()?this.getDistributionOrderProcess():null);
            strategy.appendField(locator, this, "distributionOrderProcess", buffer, theDistributionOrderProcess, this.isSetDistributionOrderProcess());
        }
        {
            List<MDFormatPropertyType> theDistributorFormat;
            theDistributorFormat = (this.isSetDistributorFormat()?this.getDistributorFormat():null);
            strategy.appendField(locator, this, "distributorFormat", buffer, theDistributorFormat, this.isSetDistributorFormat());
        }
        {
            List<MDDigitalTransferOptionsPropertyType> theDistributorTransferOptions;
            theDistributorTransferOptions = (this.isSetDistributorTransferOptions()?this.getDistributorTransferOptions():null);
            strategy.appendField(locator, this, "distributorTransferOptions", buffer, theDistributorTransferOptions, this.isSetDistributorTransferOptions());
        }
        return buffer;
    }

    public void setDistributionOrderProcess(List<MDStandardOrderProcessPropertyType> value) {
        this.distributionOrderProcess = null;
        if (value!= null) {
            List<MDStandardOrderProcessPropertyType> draftl = this.getDistributionOrderProcess();
            draftl.addAll(value);
        }
    }

    public void setDistributorFormat(List<MDFormatPropertyType> value) {
        this.distributorFormat = null;
        if (value!= null) {
            List<MDFormatPropertyType> draftl = this.getDistributorFormat();
            draftl.addAll(value);
        }
    }

    public void setDistributorTransferOptions(List<MDDigitalTransferOptionsPropertyType> value) {
        this.distributorTransferOptions = null;
        if (value!= null) {
            List<MDDigitalTransferOptionsPropertyType> draftl = this.getDistributorTransferOptions();
            draftl.addAll(value);
        }
    }

}
