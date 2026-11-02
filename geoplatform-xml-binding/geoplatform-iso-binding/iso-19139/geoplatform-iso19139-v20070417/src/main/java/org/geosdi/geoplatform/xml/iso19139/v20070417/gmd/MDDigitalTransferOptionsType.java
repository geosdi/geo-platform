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
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.CharacterStringPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.RealPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Technical means and media by which a dataset is obtained from the distributor
 * 
 * <p>Classe Java per MD_DigitalTransferOptions_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_DigitalTransferOptions_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gco}AbstractObject_Type">
 *       <sequence>
 *         <element name="unitsOfDistribution" type="{http://www.isotc211.org/2005/gco}CharacterString_PropertyType" minOccurs="0"/>
 *         <element name="transferSize" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" minOccurs="0"/>
 *         <element name="onLine" type="{http://www.isotc211.org/2005/gmd}CI_OnlineResource_PropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="offLine" type="{http://www.isotc211.org/2005/gmd}MD_Medium_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_DigitalTransferOptions_Type", propOrder = {
    "unitsOfDistribution",
    "transferSize",
    "onLine",
    "offLine"
})
public class MDDigitalTransferOptionsType
    extends AbstractObjectType
    implements ToString
{

    protected CharacterStringPropertyType unitsOfDistribution;
    protected RealPropertyType transferSize;
    protected List<CIOnlineResourcePropertyType> onLine;
    protected MDMediumPropertyType offLine;

    /**
     * Recupera il valore della proprietà unitsOfDistribution.
     * 
     * @return
     *     possible object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public CharacterStringPropertyType getUnitsOfDistribution() {
        return unitsOfDistribution;
    }

    /**
     * Imposta il valore della proprietà unitsOfDistribution.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterStringPropertyType }
     *     
     */
    public void setUnitsOfDistribution(CharacterStringPropertyType value) {
        this.unitsOfDistribution = value;
    }

    public boolean isSetUnitsOfDistribution() {
        return (this.unitsOfDistribution!= null);
    }

    /**
     * Recupera il valore della proprietà transferSize.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getTransferSize() {
        return transferSize;
    }

    /**
     * Imposta il valore della proprietà transferSize.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setTransferSize(RealPropertyType value) {
        this.transferSize = value;
    }

    public boolean isSetTransferSize() {
        return (this.transferSize!= null);
    }

    /**
     * Gets the value of the onLine property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the onLine property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getOnLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CIOnlineResourcePropertyType }
     * </p>
     * 
     * 
     * @return
     *     The value of the onLine property.
     */
    public List<CIOnlineResourcePropertyType> getOnLine() {
        if (onLine == null) {
            onLine = new ArrayList<>();
        }
        return this.onLine;
    }

    public boolean isSetOnLine() {
        return ((this.onLine!= null)&&(!this.onLine.isEmpty()));
    }

    public void unsetOnLine() {
        this.onLine = null;
    }

    /**
     * Recupera il valore della proprietà offLine.
     * 
     * @return
     *     possible object is
     *     {@link MDMediumPropertyType }
     *     
     */
    public MDMediumPropertyType getOffLine() {
        return offLine;
    }

    /**
     * Imposta il valore della proprietà offLine.
     * 
     * @param value
     *     allowed object is
     *     {@link MDMediumPropertyType }
     *     
     */
    public void setOffLine(MDMediumPropertyType value) {
        this.offLine = value;
    }

    public boolean isSetOffLine() {
        return (this.offLine!= null);
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
            CharacterStringPropertyType theUnitsOfDistribution;
            theUnitsOfDistribution = this.getUnitsOfDistribution();
            strategy.appendField(locator, this, "unitsOfDistribution", buffer, theUnitsOfDistribution, this.isSetUnitsOfDistribution());
        }
        {
            RealPropertyType theTransferSize;
            theTransferSize = this.getTransferSize();
            strategy.appendField(locator, this, "transferSize", buffer, theTransferSize, this.isSetTransferSize());
        }
        {
            List<CIOnlineResourcePropertyType> theOnLine;
            theOnLine = (this.isSetOnLine()?this.getOnLine():null);
            strategy.appendField(locator, this, "onLine", buffer, theOnLine, this.isSetOnLine());
        }
        {
            MDMediumPropertyType theOffLine;
            theOffLine = this.getOffLine();
            strategy.appendField(locator, this, "offLine", buffer, theOffLine, this.isSetOffLine());
        }
        return buffer;
    }

    public void setOnLine(List<CIOnlineResourcePropertyType> value) {
        this.onLine = null;
        if (value!= null) {
            List<CIOnlineResourcePropertyType> draftl = this.getOnLine();
            draftl.addAll(value);
        }
    }

}
