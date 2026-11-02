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
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * General metadata identifying and describing a set of data. This type shall be extended if needed for each specific OWS to include additional metadata for each type of dataset. If needed, this type should first be restricted for each specific OWS to change the multiplicity (or optionality) of some elements.
 * 
 * <p>Classe Java per IdentificationType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="IdentificationType">
 *   <complexContent>
 *     <extension base="{http://www.opengis.net/ows}DescriptionType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/ows}Identifier" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows}BoundingBox" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows}OutputFormat" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows}AvailableCRS" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/ows}Metadata" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </extension>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentificationType", propOrder = {
    "identifier",
    "boundingBox",
    "outputFormat",
    "availableCRS",
    "metadata"
})
public class IdentificationType
    extends DescriptionType
    implements ToString
{

    /**
     * Optional unique identifier or name of this dataset.
     * 
     */
    @XmlElement(name = "Identifier")
    protected CodeType identifier;
    /**
     * Unordered list of zero or more bounding boxes whose union describes the extent of this dataset.
     * 
     */
    @XmlElementRef(name = "BoundingBox", namespace = "http://www.opengis.net/ows", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends BoundingBoxType>> boundingBox;
    /**
     * Unordered list of zero or more references to data formats supported for server outputs.
     * 
     */
    @XmlElement(name = "OutputFormat")
    protected List<String> outputFormat;
    /**
     * Unordered list of zero or more available coordinate reference systems.
     * 
     */
    @XmlElementRef(name = "AvailableCRS", namespace = "http://www.opengis.net/ows", type = JAXBElement.class, required = false)
    protected List<JAXBElement<String>> availableCRS;
    /**
     * Optional unordered list of additional metadata about this data(set). A list of optional metadata elements for this data identification could be specified in the Implementation Specification for this service.
     * 
     */
    @XmlElement(name = "Metadata")
    protected List<MetadataType> metadata;

    /**
     * Optional unique identifier or name of this dataset.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getIdentifier() {
        return identifier;
    }

    /**
     * Imposta il valore della proprietà identifier.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     * @see #getIdentifier()
     */
    public void setIdentifier(CodeType value) {
        this.identifier = value;
    }

    /**
     * Unordered list of zero or more bounding boxes whose union describes the extent of this dataset.
     * 
     * Gets the value of the boundingBox property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the boundingBox property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getBoundingBox().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link BoundingBoxType }{@code >}
     * {@link JAXBElement }{@code <}{@link WGS84BoundingBoxType }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the boundingBox property.
     */
    public List<JAXBElement<? extends BoundingBoxType>> getBoundingBox() {
        if (boundingBox == null) {
            boundingBox = new ArrayList<>();
        }
        return this.boundingBox;
    }

    /**
     * Unordered list of zero or more references to data formats supported for server outputs.
     * 
     * Gets the value of the outputFormat property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the outputFormat property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getOutputFormat().add(newItem);
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
     *     The value of the outputFormat property.
     */
    public List<String> getOutputFormat() {
        if (outputFormat == null) {
            outputFormat = new ArrayList<>();
        }
        return this.outputFormat;
    }

    /**
     * Unordered list of zero or more available coordinate reference systems.
     * 
     * Gets the value of the availableCRS property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the availableCRS property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getAvailableCRS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * </p>
     * 
     * 
     * @return
     *     The value of the availableCRS property.
     */
    public List<JAXBElement<String>> getAvailableCRS() {
        if (availableCRS == null) {
            availableCRS = new ArrayList<>();
        }
        return this.availableCRS;
    }

    /**
     * Optional unordered list of additional metadata about this data(set). A list of optional metadata elements for this data identification could be specified in the Implementation Specification for this service.
     * 
     * Gets the value of the metadata property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the metadata property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getMetadata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadataType }
     * </p>
     * 
     * 
     * @return
     *     The value of the metadata property.
     */
    public List<MetadataType> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<>();
        }
        return this.metadata;
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
            CodeType theIdentifier;
            theIdentifier = this.getIdentifier();
            strategy.appendField(locator, this, "identifier", buffer, theIdentifier, (this.identifier!= null));
        }
        {
            List<JAXBElement<? extends BoundingBoxType>> theBoundingBox;
            theBoundingBox = (((this.boundingBox!= null)&&(!this.boundingBox.isEmpty()))?this.getBoundingBox():null);
            strategy.appendField(locator, this, "boundingBox", buffer, theBoundingBox, ((this.boundingBox!= null)&&(!this.boundingBox.isEmpty())));
        }
        {
            List<String> theOutputFormat;
            theOutputFormat = (((this.outputFormat!= null)&&(!this.outputFormat.isEmpty()))?this.getOutputFormat():null);
            strategy.appendField(locator, this, "outputFormat", buffer, theOutputFormat, ((this.outputFormat!= null)&&(!this.outputFormat.isEmpty())));
        }
        {
            List<JAXBElement<String>> theAvailableCRS;
            theAvailableCRS = (((this.availableCRS!= null)&&(!this.availableCRS.isEmpty()))?this.getAvailableCRS():null);
            strategy.appendField(locator, this, "availableCRS", buffer, theAvailableCRS, ((this.availableCRS!= null)&&(!this.availableCRS.isEmpty())));
        }
        {
            List<MetadataType> theMetadata;
            theMetadata = (((this.metadata!= null)&&(!this.metadata.isEmpty()))?this.getMetadata():null);
            strategy.appendField(locator, this, "metadata", buffer, theMetadata, ((this.metadata!= null)&&(!this.metadata.isEmpty())));
        }
        return buffer;
    }

    public void setBoundingBox(List<JAXBElement<? extends BoundingBoxType>> value) {
        this.boundingBox = null;
        if (value!= null) {
            List<JAXBElement<? extends BoundingBoxType>> draftl = this.getBoundingBox();
            draftl.addAll(value);
        }
    }

    public void setOutputFormat(List<String> value) {
        this.outputFormat = null;
        if (value!= null) {
            List<String> draftl = this.getOutputFormat();
            draftl.addAll(value);
        }
    }

    public void setAvailableCRS(List<JAXBElement<String>> value) {
        this.availableCRS = null;
        if (value!= null) {
            List<JAXBElement<String>> draftl = this.getAvailableCRS();
            draftl.addAll(value);
        }
    }

    public void setMetadata(List<MetadataType> value) {
        this.metadata = null;
        if (value!= null) {
            List<MetadataType> draftl = this.getMetadata();
            draftl.addAll(value);
        }
    }

}
