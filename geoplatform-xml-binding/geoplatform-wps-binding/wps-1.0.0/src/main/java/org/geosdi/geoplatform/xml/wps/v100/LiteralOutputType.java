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

import org.geosdi.geoplatform.xml.ows.v110.DomainMetadataType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


/**
 * Description of a literal output (or input).
 * <p>
 * <p>Classe Java per LiteralOutputType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="LiteralOutputType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}DataType" minOccurs="0"/&gt;
 *         &lt;element name="UOMs" type="{http://www.opengis.net/wps/1.0.0}SupportedUOMsType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LiteralOutputType", propOrder = {
        "dataType",
        "uoMs"
})
@XmlSeeAlso({
        LiteralInputType.class
})
public class LiteralOutputType implements ToString2 {

    @XmlElement(name = "DataType", namespace = "http://www.opengis.net/ows/1.1")
    protected DomainMetadataType dataType;
    @XmlElement(name = "UOMs", namespace = "")
    protected SupportedUOMsType uoMs;

    /**
     * Data type of this set of values (e.g. integer, real, etc). This data type metadata should be included for each quantity whose data type is not a string.
     *
     * @return possible object is
     * {@link DomainMetadataType }
     */
    public DomainMetadataType getDataType() {
        return dataType;
    }

    /**
     * Imposta il valore della proprietà dataType.
     *
     * @param value allowed object is
     *              {@link DomainMetadataType }
     */
    public void setDataType(DomainMetadataType value) {
        this.dataType = value;
    }

    public boolean isSetDataType() {
        return (this.dataType != null);
    }

    /**
     * Recupera il valore della proprietà uoMs.
     *
     * @return possible object is
     * {@link SupportedUOMsType }
     */
    public SupportedUOMsType getUOMs() {
        return uoMs;
    }

    /**
     * Imposta il valore della proprietà uoMs.
     *
     * @param value allowed object is
     *              {@link SupportedUOMsType }
     */
    public void setUOMs(SupportedUOMsType value) {
        this.uoMs = value;
    }

    public boolean isSetUOMs() {
        return (this.uoMs != null);
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
