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

import org.geosdi.geoplatform.xml.ows.v110.BoundingBoxType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Identifies the form of this input or output value, and provides supporting information.
 * <p>
 * <p>Classe Java per DataType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="DataType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="ComplexData" type="{http://www.opengis.net/wps/1.0.0}ComplexDataType"/&gt;
 *         &lt;element name="LiteralData" type="{http://www.opengis.net/wps/1.0.0}LiteralDataType"/&gt;
 *         &lt;element name="BoundingBoxData" type="{http://www.opengis.net/ows/1.1}BoundingBoxType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataType", propOrder = {
        "complexData",
        "literalData",
        "boundingBoxData"
})
public class DataType implements ToString2 {

    @XmlElement(name = "ComplexData")
    protected ComplexDataType complexData;
    @XmlElement(name = "LiteralData")
    protected LiteralDataType literalData;
    @XmlElement(name = "BoundingBoxData")
    protected BoundingBoxType boundingBoxData;

    /**
     * Recupera il valore della proprietà complexData.
     *
     * @return possible object is
     * {@link ComplexDataType }
     */
    public ComplexDataType getComplexData() {
        return complexData;
    }

    /**
     * Imposta il valore della proprietà complexData.
     *
     * @param value allowed object is
     *              {@link ComplexDataType }
     */
    public void setComplexData(ComplexDataType value) {
        this.complexData = value;
    }

    public boolean isSetComplexData() {
        return (this.complexData != null);
    }

    /**
     * Recupera il valore della proprietà literalData.
     *
     * @return possible object is
     * {@link LiteralDataType }
     */
    public LiteralDataType getLiteralData() {
        return literalData;
    }

    /**
     * Imposta il valore della proprietà literalData.
     *
     * @param value allowed object is
     *              {@link LiteralDataType }
     */
    public void setLiteralData(LiteralDataType value) {
        this.literalData = value;
    }

    public boolean isSetLiteralData() {
        return (this.literalData != null);
    }

    /**
     * Recupera il valore della proprietà boundingBoxData.
     *
     * @return possible object is
     * {@link BoundingBoxType }
     */
    public BoundingBoxType getBoundingBoxData() {
        return boundingBoxData;
    }

    /**
     * Imposta il valore della proprietà boundingBoxData.
     *
     * @param value allowed object is
     *              {@link BoundingBoxType }
     */
    public void setBoundingBoxData(BoundingBoxType value) {
        this.boundingBoxData = value;
    }

    public boolean isSetBoundingBoxData() {
        return (this.boundingBoxData != null);
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
            ComplexDataType theComplexData;
            theComplexData = this.getComplexData();
            strategy.appendField(locator, this, "complexData", buffer, theComplexData, this.isSetComplexData());
        }
        {
            LiteralDataType theLiteralData;
            theLiteralData = this.getLiteralData();
            strategy.appendField(locator, this, "literalData", buffer, theLiteralData, this.isSetLiteralData());
        }
        {
            BoundingBoxType theBoundingBoxData;
            theBoundingBoxData = this.getBoundingBoxData();
            strategy.appendField(locator, this, "boundingBoxData", buffer, theBoundingBoxData, this.isSetBoundingBoxData());
        }
        return buffer;
    }

}
