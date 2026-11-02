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

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.IntegerPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.RealPropertyType;
import org.geosdi.geoplatform.xml.iso19139.v20070417.gco.UomLengthPropertyType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per MD_Band_Type complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="MD_Band_Type">
 *   <complexContent>
 *     <extension base="{http://www.isotc211.org/2005/gmd}MD_RangeDimension_Type">
 *       <sequence>
 *         <element name="maxValue" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" minOccurs="0"/>
 *         <element name="minValue" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" minOccurs="0"/>
 *         <element name="units" type="{http://www.isotc211.org/2005/gco}UomLength_PropertyType" minOccurs="0"/>
 *         <element name="peakResponse" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" minOccurs="0"/>
 *         <element name="bitsPerValue" type="{http://www.isotc211.org/2005/gco}Integer_PropertyType" minOccurs="0"/>
 *         <element name="toneGradation" type="{http://www.isotc211.org/2005/gco}Integer_PropertyType" minOccurs="0"/>
 *         <element name="scaleFactor" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" minOccurs="0"/>
 *         <element name="offset" type="{http://www.isotc211.org/2005/gco}Real_PropertyType" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MD_Band_Type", propOrder = {
    "maxValue",
    "minValue",
    "units",
    "peakResponse",
    "bitsPerValue",
    "toneGradation",
    "scaleFactor",
    "offset"
})
public class MDBandType
    extends MDRangeDimensionType
    implements ToString
{

    protected RealPropertyType maxValue;
    protected RealPropertyType minValue;
    protected UomLengthPropertyType units;
    protected RealPropertyType peakResponse;
    protected IntegerPropertyType bitsPerValue;
    protected IntegerPropertyType toneGradation;
    protected RealPropertyType scaleFactor;
    protected RealPropertyType offset;

    /**
     * Recupera il valore della proprietà maxValue.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getMaxValue() {
        return maxValue;
    }

    /**
     * Imposta il valore della proprietà maxValue.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setMaxValue(RealPropertyType value) {
        this.maxValue = value;
    }

    public boolean isSetMaxValue() {
        return (this.maxValue!= null);
    }

    /**
     * Recupera il valore della proprietà minValue.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getMinValue() {
        return minValue;
    }

    /**
     * Imposta il valore della proprietà minValue.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setMinValue(RealPropertyType value) {
        this.minValue = value;
    }

    public boolean isSetMinValue() {
        return (this.minValue!= null);
    }

    /**
     * Recupera il valore della proprietà units.
     * 
     * @return
     *     possible object is
     *     {@link UomLengthPropertyType }
     *     
     */
    public UomLengthPropertyType getUnits() {
        return units;
    }

    /**
     * Imposta il valore della proprietà units.
     * 
     * @param value
     *     allowed object is
     *     {@link UomLengthPropertyType }
     *     
     */
    public void setUnits(UomLengthPropertyType value) {
        this.units = value;
    }

    public boolean isSetUnits() {
        return (this.units!= null);
    }

    /**
     * Recupera il valore della proprietà peakResponse.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getPeakResponse() {
        return peakResponse;
    }

    /**
     * Imposta il valore della proprietà peakResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setPeakResponse(RealPropertyType value) {
        this.peakResponse = value;
    }

    public boolean isSetPeakResponse() {
        return (this.peakResponse!= null);
    }

    /**
     * Recupera il valore della proprietà bitsPerValue.
     * 
     * @return
     *     possible object is
     *     {@link IntegerPropertyType }
     *     
     */
    public IntegerPropertyType getBitsPerValue() {
        return bitsPerValue;
    }

    /**
     * Imposta il valore della proprietà bitsPerValue.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerPropertyType }
     *     
     */
    public void setBitsPerValue(IntegerPropertyType value) {
        this.bitsPerValue = value;
    }

    public boolean isSetBitsPerValue() {
        return (this.bitsPerValue!= null);
    }

    /**
     * Recupera il valore della proprietà toneGradation.
     * 
     * @return
     *     possible object is
     *     {@link IntegerPropertyType }
     *     
     */
    public IntegerPropertyType getToneGradation() {
        return toneGradation;
    }

    /**
     * Imposta il valore della proprietà toneGradation.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegerPropertyType }
     *     
     */
    public void setToneGradation(IntegerPropertyType value) {
        this.toneGradation = value;
    }

    public boolean isSetToneGradation() {
        return (this.toneGradation!= null);
    }

    /**
     * Recupera il valore della proprietà scaleFactor.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getScaleFactor() {
        return scaleFactor;
    }

    /**
     * Imposta il valore della proprietà scaleFactor.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setScaleFactor(RealPropertyType value) {
        this.scaleFactor = value;
    }

    public boolean isSetScaleFactor() {
        return (this.scaleFactor!= null);
    }

    /**
     * Recupera il valore della proprietà offset.
     * 
     * @return
     *     possible object is
     *     {@link RealPropertyType }
     *     
     */
    public RealPropertyType getOffset() {
        return offset;
    }

    /**
     * Imposta il valore della proprietà offset.
     * 
     * @param value
     *     allowed object is
     *     {@link RealPropertyType }
     *     
     */
    public void setOffset(RealPropertyType value) {
        this.offset = value;
    }

    public boolean isSetOffset() {
        return (this.offset!= null);
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
            RealPropertyType theMaxValue;
            theMaxValue = this.getMaxValue();
            strategy.appendField(locator, this, "maxValue", buffer, theMaxValue, this.isSetMaxValue());
        }
        {
            RealPropertyType theMinValue;
            theMinValue = this.getMinValue();
            strategy.appendField(locator, this, "minValue", buffer, theMinValue, this.isSetMinValue());
        }
        {
            UomLengthPropertyType theUnits;
            theUnits = this.getUnits();
            strategy.appendField(locator, this, "units", buffer, theUnits, this.isSetUnits());
        }
        {
            RealPropertyType thePeakResponse;
            thePeakResponse = this.getPeakResponse();
            strategy.appendField(locator, this, "peakResponse", buffer, thePeakResponse, this.isSetPeakResponse());
        }
        {
            IntegerPropertyType theBitsPerValue;
            theBitsPerValue = this.getBitsPerValue();
            strategy.appendField(locator, this, "bitsPerValue", buffer, theBitsPerValue, this.isSetBitsPerValue());
        }
        {
            IntegerPropertyType theToneGradation;
            theToneGradation = this.getToneGradation();
            strategy.appendField(locator, this, "toneGradation", buffer, theToneGradation, this.isSetToneGradation());
        }
        {
            RealPropertyType theScaleFactor;
            theScaleFactor = this.getScaleFactor();
            strategy.appendField(locator, this, "scaleFactor", buffer, theScaleFactor, this.isSetScaleFactor());
        }
        {
            RealPropertyType theOffset;
            theOffset = this.getOffset();
            strategy.appendField(locator, this, "offset", buffer, theOffset, this.isSetOffset());
        }
        return buffer;
    }

}
