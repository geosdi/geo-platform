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

import java.math.BigInteger;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * Description of an input to a process. 
 * 
 * In this use, the DescriptionType shall describe this process input.
 * 
 * <p>Classe Java per InputDescriptionType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="InputDescriptionType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/wps/1.0.0}DescriptionType">
 *       <sequence>
 *         <group ref="{http://www.opengis.net/wps/1.0.0}InputFormChoice"/>
 *       </sequence>
 *       <attribute name="minOccurs" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       <attribute name="maxOccurs" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" />
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputDescriptionType", propOrder = {
    "complexData",
    "literalData",
    "boundingBoxData"
})
public class InputDescriptionType
    extends DescriptionType
    implements ToString
{

    /**
     * Indicates that this Input shall be a complex data structure (such as a GML document), and provides a list of Formats, Encodings, and Schemas supported for this Input. The value of this ComplexData structure can be input either embedded in the Execute request or remotely accessible to the server.  The client can select from among the identified combinations of Formats, Encodings, and Schemas to specify the form of the Input. This allows for complete specification of particular versions of GML, or image formats.
     * 
     */
    @XmlElement(name = "ComplexData", namespace = "")
    protected SupportedComplexDataInputType complexData;
    /**
     * Indicates that this Input shall be a simple numeric value or character string that is embedded in the execute request, and describes the possible values.
     * 
     */
    @XmlElement(name = "LiteralData", namespace = "")
    protected LiteralInputType literalData;
    /**
     * Indicates that this Input shall be a BoundingBox data structure that is embedded in the execute request, and provides a list of the Coordinate Reference System support for this Bounding Box.
     * 
     */
    @XmlElement(name = "BoundingBoxData", namespace = "")
    protected SupportedCRSsType boundingBoxData;
    /**
     * The minimum number of times that values for this parameter are required in an Execute request.  If "0", this data input is optional. If greater than "0" then this process input is required.
     * 
     */
    @XmlAttribute(name = "minOccurs", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minOccurs;
    /**
     * The maximum number of times that values for this parameter are permitted in an Execute request. If "1" then this parameter may appear only once in an Execute request.  If greater than "1", then this input parameter may appear that many times in an Execute request.
     * 
     */
    @XmlAttribute(name = "maxOccurs", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxOccurs;

    /**
     * Indicates that this Input shall be a complex data structure (such as a GML document), and provides a list of Formats, Encodings, and Schemas supported for this Input. The value of this ComplexData structure can be input either embedded in the Execute request or remotely accessible to the server.  The client can select from among the identified combinations of Formats, Encodings, and Schemas to specify the form of the Input. This allows for complete specification of particular versions of GML, or image formats.
     * 
     * @return
     *     possible object is
     *     {@link SupportedComplexDataInputType }
     *     
     */
    public SupportedComplexDataInputType getComplexData() {
        return complexData;
    }

    /**
     * Imposta il valore della proprietà complexData.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportedComplexDataInputType }
     *     
     * @see #getComplexData()
     */
    public void setComplexData(SupportedComplexDataInputType value) {
        this.complexData = value;
    }

    public boolean isSetComplexData() {
        return (this.complexData!= null);
    }

    /**
     * Indicates that this Input shall be a simple numeric value or character string that is embedded in the execute request, and describes the possible values.
     * 
     * @return
     *     possible object is
     *     {@link LiteralInputType }
     *     
     */
    public LiteralInputType getLiteralData() {
        return literalData;
    }

    /**
     * Imposta il valore della proprietà literalData.
     * 
     * @param value
     *     allowed object is
     *     {@link LiteralInputType }
     *     
     * @see #getLiteralData()
     */
    public void setLiteralData(LiteralInputType value) {
        this.literalData = value;
    }

    public boolean isSetLiteralData() {
        return (this.literalData!= null);
    }

    /**
     * Indicates that this Input shall be a BoundingBox data structure that is embedded in the execute request, and provides a list of the Coordinate Reference System support for this Bounding Box.
     * 
     * @return
     *     possible object is
     *     {@link SupportedCRSsType }
     *     
     */
    public SupportedCRSsType getBoundingBoxData() {
        return boundingBoxData;
    }

    /**
     * Imposta il valore della proprietà boundingBoxData.
     * 
     * @param value
     *     allowed object is
     *     {@link SupportedCRSsType }
     *     
     * @see #getBoundingBoxData()
     */
    public void setBoundingBoxData(SupportedCRSsType value) {
        this.boundingBoxData = value;
    }

    public boolean isSetBoundingBoxData() {
        return (this.boundingBoxData!= null);
    }

    /**
     * The minimum number of times that values for this parameter are required in an Execute request.  If "0", this data input is optional. If greater than "0" then this process input is required.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinOccurs() {
        return minOccurs;
    }

    /**
     * Imposta il valore della proprietà minOccurs.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getMinOccurs()
     */
    public void setMinOccurs(BigInteger value) {
        this.minOccurs = value;
    }

    public boolean isSetMinOccurs() {
        return (this.minOccurs!= null);
    }

    /**
     * The maximum number of times that values for this parameter are permitted in an Execute request. If "1" then this parameter may appear only once in an Execute request.  If greater than "1", then this input parameter may appear that many times in an Execute request.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxOccurs() {
        return maxOccurs;
    }

    /**
     * Imposta il valore della proprietà maxOccurs.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     * @see #getMaxOccurs()
     */
    public void setMaxOccurs(BigInteger value) {
        this.maxOccurs = value;
    }

    public boolean isSetMaxOccurs() {
        return (this.maxOccurs!= null);
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
            SupportedComplexDataInputType theComplexData;
            theComplexData = this.getComplexData();
            strategy.appendField(locator, this, "complexData", buffer, theComplexData, this.isSetComplexData());
        }
        {
            LiteralInputType theLiteralData;
            theLiteralData = this.getLiteralData();
            strategy.appendField(locator, this, "literalData", buffer, theLiteralData, this.isSetLiteralData());
        }
        {
            SupportedCRSsType theBoundingBoxData;
            theBoundingBoxData = this.getBoundingBoxData();
            strategy.appendField(locator, this, "boundingBoxData", buffer, theBoundingBoxData, this.isSetBoundingBoxData());
        }
        {
            BigInteger theMinOccurs;
            theMinOccurs = this.getMinOccurs();
            strategy.appendField(locator, this, "minOccurs", buffer, theMinOccurs, this.isSetMinOccurs());
        }
        {
            BigInteger theMaxOccurs;
            theMaxOccurs = this.getMaxOccurs();
            strategy.appendField(locator, this, "maxOccurs", buffer, theMaxOccurs, this.isSetMaxOccurs());
        }
        return buffer;
    }

}
