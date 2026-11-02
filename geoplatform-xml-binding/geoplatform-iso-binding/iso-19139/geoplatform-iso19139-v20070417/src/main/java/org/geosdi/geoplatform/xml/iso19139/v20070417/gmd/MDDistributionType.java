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
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.AbstractObjectType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Information about the distributor of and options for obtaining the dataset
 * 
 * <p>Classe Java per MD_Distribution_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_Distribution_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="distributionFormat" type="{http://www.isotc211.org/2005/gmd}MD_Format_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="distributor" type="{http://www.isotc211.org/2005/gmd}MD_Distributor_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="transferOptions" type="{http://www.isotc211.org/2005/gmd}MD_DigitalTransferOptions_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_Distribution_Type", propOrder = {
    "distributionFormat",
    "distributor",
    "transferOptions"
})
public class MDDistributionType
    extends AbstractObjectType
    implements ToString
{

    protected List<MDFormatPropertyType> distributionFormat;
    protected List<MDDistributorPropertyType> distributor;
    protected List<MDDigitalTransferOptionsPropertyType> transferOptions;

    /**
     * Gets the value of the distributionFormat property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the distributionFormat property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDistributionFormat().add(newItem);
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
     *     The value of the distributionFormat property.
     */
    public List<MDFormatPropertyType> getDistributionFormat() {
        if (distributionFormat == null) {
            distributionFormat = new ArrayList<>();
        }
        return this.distributionFormat;
    }

    public boolean isSetDistributionFormat() {
        return ((this.distributionFormat!= null)&&(!this.distributionFormat.isEmpty()));
    }

    public void unsetDistributionFormat() {
        this.distributionFormat = null;
    }

    /**
     * Gets the value of the distributor property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the distributor property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getDistributor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MDDistributorPropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the distributor property.
     */
    public List<MDDistributorPropertyType> getDistributor() {
        if (distributor == null) {
            distributor = new ArrayList<>();
        }
        return this.distributor;
    }

    public boolean isSetDistributor() {
        return ((this.distributor!= null)&&(!this.distributor.isEmpty()));
    }

    public void unsetDistributor() {
        this.distributor = null;
    }

    /**
     * Gets the value of the transferOptions property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the transferOptions property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTransferOptions().add(newItem);
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
     *     The value of the transferOptions property.
     */
    public List<MDDigitalTransferOptionsPropertyType> getTransferOptions() {
        if (transferOptions == null) {
            transferOptions = new ArrayList<>();
        }
        return this.transferOptions;
    }

    public boolean isSetTransferOptions() {
        return ((this.transferOptions!= null)&&(!this.transferOptions.isEmpty()));
    }

    public void unsetTransferOptions() {
        this.transferOptions = null;
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
            List<MDFormatPropertyType> theDistributionFormat;
            theDistributionFormat = (this.isSetDistributionFormat()?this.getDistributionFormat():null);
            strategy.appendField(locator, this, "distributionFormat", buffer, theDistributionFormat, this.isSetDistributionFormat());
        }
        {
            List<MDDistributorPropertyType> theDistributor;
            theDistributor = (this.isSetDistributor()?this.getDistributor():null);
            strategy.appendField(locator, this, "distributor", buffer, theDistributor, this.isSetDistributor());
        }
        {
            List<MDDigitalTransferOptionsPropertyType> theTransferOptions;
            theTransferOptions = (this.isSetTransferOptions()?this.getTransferOptions():null);
            strategy.appendField(locator, this, "transferOptions", buffer, theTransferOptions, this.isSetTransferOptions());
        }
        return buffer;
    }

    public void setDistributionFormat(List<MDFormatPropertyType> value) {
        this.distributionFormat = null;
        if (value!= null) {
            List<MDFormatPropertyType> draftl = this.getDistributionFormat();
            draftl.addAll(value);
        }
    }

    public void setDistributor(List<MDDistributorPropertyType> value) {
        this.distributor = null;
        if (value!= null) {
            List<MDDistributorPropertyType> draftl = this.getDistributor();
            draftl.addAll(value);
        }
    }

    public void setTransferOptions(List<MDDigitalTransferOptionsPropertyType> value) {
        this.transferOptions = null;
        if (value!= null) {
            List<MDDigitalTransferOptionsPropertyType> draftl = this.getTransferOptions();
            draftl.addAll(value);
        }
    }

}
