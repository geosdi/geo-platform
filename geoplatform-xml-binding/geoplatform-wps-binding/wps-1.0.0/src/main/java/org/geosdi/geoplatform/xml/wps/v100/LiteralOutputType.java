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

package org.geosdi.geoplatform.xml.wps.v100;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.ows.v110.DomainMetadataType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Description of a literal output (or input).
 * 
 * <p>Classe Java per LiteralOutputType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="LiteralOutputType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/ows/1.1}DataType" minOccurs="0"/>
 *         <element name="UOMs" type="{http://www.opengis.net/wps/1.0.0}SupportedUOMsType" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LiteralOutputType", propOrder = {
    "dataType",
    "uoMs"
})
@XmlSeeAlso({
    LiteralInputType.class
})
public class LiteralOutputType implements ToString
{

    /**
     * Data type of this set of values (e.g. integer, real, etc). This data type metadata should be included for each quantity whose data type is not a string.
     * 
     */
    @XmlElement(name = "DataType", namespace = "http://www.opengis.net/ows/1.1")
    protected DomainMetadataType dataType;
    /**
     * List of supported units of measure for this input or output. This element should be included when this literal has a unit of measure (e.g., "meters", without a more complete reference system). Not necessary for a count, which has no units.
     * 
     */
    @XmlElement(name = "UOMs", namespace = "")
    protected SupportedUOMsType uoMs;

    /**
     * Data type of this set of values (e.g. integer, real, etc). This data type metadata should be included for each quantity whose data type is not a string.
     * 
     * @return
     *     possible object is
     *     {@link DomainMetadataType }
     *     
     */
    public DomainMetadataType getDataType() {
        return dataType;
    }

    /**
     * Imposta il valore della proprietà dataType.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainMetadataType }
     *     
     * @see #getDataType()
     */
    public void setDataType(DomainMetadataType value) {
        this.dataType = value;
    }

    public boolean isSetDataType() {
        return (this.dataType!= null);
    }

    /**
     * List of supported units of measure for this input or output. This element should be included when this literal has a unit of measure (e.g., "meters", without a more complete reference system). Not necessary for a count, which has no units.
     * 
     * @return
     *     possible object is
     *     {@link SupportedUOMsType }
     *     
     */
    public SupportedUOMsType getUOMs() {
        return uoMs;
    }

    /**
     * Imposta il valore della proprietà uoMs.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportedUOMsType }
     *     
     * @see #getUOMs()
     */
    public void setUOMs(SupportedUOMsType value) {
        this.uoMs = value;
    }

    public boolean isSetUOMs() {
        return (this.uoMs!= null);
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
            DomainMetadataType theDataType;
            theDataType = this.getDataType();
            strategy.appendField(locator, this, "dataType", buffer, theDataType, this.isSetDataType());
        }
        {
            SupportedUOMsType theUOMs;
            theUOMs = this.getUOMs();
            strategy.appendField(locator, this, "uoMs", buffer, theUOMs, this.isSetUOMs());
        }
        return buffer;
    }

}
