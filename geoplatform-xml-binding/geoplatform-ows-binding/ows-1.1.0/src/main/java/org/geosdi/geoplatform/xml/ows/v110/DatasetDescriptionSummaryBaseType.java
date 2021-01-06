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
// Generato il: 2017.12.01 alle 09:03:56 AM CET 
//


package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * Typical dataset metadata in typical Contents section of an OWS service metadata (Capabilities) document. This type shall be extended and/or restricted if needed for specific OWS use, to include the specific Dataset  description metadata needed. 
 * 
 * <p>Classe Java per DatasetDescriptionSummaryBaseType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatasetDescriptionSummaryBaseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/ows/1.1}DescriptionType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}WGS84BoundingBox" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Identifier" type="{http://www.opengis.net/ows/1.1}CodeType"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}BoundingBox" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Metadata" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}DatasetDescriptionSummary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatasetDescriptionSummaryBaseType", propOrder = {
    "wgs84BoundingBox",
    "identifier",
    "boundingBox",
    "metadata",
    "datasetDescriptionSummary"
})
public class DatasetDescriptionSummaryBaseType
    extends DescriptionType
    implements ToString2
{

    @XmlElement(name = "WGS84BoundingBox")
    protected List<WGS84BoundingBoxType> wgs84BoundingBox;
    @XmlElement(name = "Identifier", required = true)
    protected CodeType identifier;
    @XmlElementRef(name = "BoundingBox", namespace = "http://www.opengis.net/ows/1.1", type = JAXBElement.class, required = false)
    protected List<JAXBElement<? extends BoundingBoxType>> boundingBox;
    @XmlElement(name = "Metadata")
    protected List<MetadataType> metadata;
    @XmlElementRef(name = "DatasetDescriptionSummary", namespace = "http://www.opengis.net/ows/1.1", type = JAXBElement.class, required = false)
    protected List<JAXBElement<DatasetDescriptionSummaryBaseType>> datasetDescriptionSummary;

    /**
     * Unordered list of zero or more minimum bounding rectangles surrounding coverage data, using the WGS 84 CRS with decimal degrees and longitude before latitude. If no WGS 84 bounding box is recorded for a coverage, any such bounding boxes recorded for a higher level in a hierarchy of datasets shall apply to this coverage. If WGS 84 bounding box(es) are recorded for a coverage, any such bounding boxes recorded for a higher level in a hierarchy of datasets shall be ignored. For each lowest-level coverage in a hierarchy, at least one applicable WGS84BoundingBox shall be either recorded or inherited, to simplify searching for datasets that might overlap a specified region. If multiple WGS 84 bounding boxes are included, this shall be interpreted as the union of the areas of these bounding boxes. Gets the value of the wgs84BoundingBox property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wgs84BoundingBox property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWGS84BoundingBox().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WGS84BoundingBoxType }
     * 
     * 
     */
    public List<WGS84BoundingBoxType> getWGS84BoundingBox() {
        if (wgs84BoundingBox == null) {
            wgs84BoundingBox = new ArrayList<WGS84BoundingBoxType>();
        }
        return this.wgs84BoundingBox;
    }

    /**
     * Recupera il valore della proprietà identifier.
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
     */
    public void setIdentifier(CodeType value) {
        this.identifier = value;
    }

    /**
     * Unordered list of zero or more minimum bounding rectangles surrounding coverage data, in AvailableCRSs.  Zero or more BoundingBoxes are  allowed in addition to one or more WGS84BoundingBoxes to allow more precise specification of the Dataset area in AvailableCRSs. These Bounding Boxes shall not use any CRS not listed as an AvailableCRS. However, an AvailableCRS can be listed without a corresponding Bounding Box. If no such bounding box is recorded for a coverage, any such bounding boxes recorded for a higher level in a hierarchy of datasets shall apply to this coverage. If such bounding box(es) are recorded for a coverage, any such bounding boxes recorded for a higher level in a hierarchy of datasets shall be ignored. If multiple bounding boxes are included with the same CRS, this shall be interpreted as the union of the areas of these bounding boxes. Gets the value of the boundingBox property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boundingBox property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoundingBox().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link BoundingBoxType }{@code >}
     * {@link JAXBElement }{@code <}{@link WGS84BoundingBoxType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends BoundingBoxType>> getBoundingBox() {
        if (boundingBox == null) {
            boundingBox = new ArrayList<JAXBElement<? extends BoundingBoxType>>();
        }
        return this.boundingBox;
    }

    /**
     * Optional unordered list of additional metadata about this dataset. A list of optional metadata elements for this dataset description could be specified in the Implementation Specification for this service. Gets the value of the metadata property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadata property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadataType }
     * 
     * 
     */
    public List<MetadataType> getMetadata() {
        if (metadata == null) {
            metadata = new ArrayList<MetadataType>();
        }
        return this.metadata;
    }

    /**
     * Metadata describing zero or more unordered subsidiary datasets available from this server. Gets the value of the datasetDescriptionSummary property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datasetDescriptionSummary property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatasetDescriptionSummary().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link DatasetDescriptionSummaryBaseType }{@code >}
     * {@link JAXBElement }{@code <}{@link DatasetDescriptionSummaryBaseType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<DatasetDescriptionSummaryBaseType>> getDatasetDescriptionSummary() {
        if (datasetDescriptionSummary == null) {
            datasetDescriptionSummary = new ArrayList<JAXBElement<DatasetDescriptionSummaryBaseType>>();
        }
        return this.datasetDescriptionSummary;
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
        super.appendFields(locator, buffer, strategy);
        {
            List<WGS84BoundingBoxType> theWGS84BoundingBox;
            theWGS84BoundingBox = (((this.wgs84BoundingBox!= null)&&(!this.wgs84BoundingBox.isEmpty()))?this.getWGS84BoundingBox():null);
            strategy.appendField(locator, this, "wgs84BoundingBox", buffer, theWGS84BoundingBox, ((this.wgs84BoundingBox!= null)&&(!this.wgs84BoundingBox.isEmpty())));
        }
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
            List<MetadataType> theMetadata;
            theMetadata = (((this.metadata!= null)&&(!this.metadata.isEmpty()))?this.getMetadata():null);
            strategy.appendField(locator, this, "metadata", buffer, theMetadata, ((this.metadata!= null)&&(!this.metadata.isEmpty())));
        }
        {
            List<JAXBElement<DatasetDescriptionSummaryBaseType>> theDatasetDescriptionSummary;
            theDatasetDescriptionSummary = (((this.datasetDescriptionSummary!= null)&&(!this.datasetDescriptionSummary.isEmpty()))?this.getDatasetDescriptionSummary():null);
            strategy.appendField(locator, this, "datasetDescriptionSummary", buffer, theDatasetDescriptionSummary, ((this.datasetDescriptionSummary!= null)&&(!this.datasetDescriptionSummary.isEmpty())));
        }
        return buffer;
    }

    public void setWGS84BoundingBox(List<WGS84BoundingBoxType> value) {
        this.wgs84BoundingBox = null;
        if (value!= null) {
            List<WGS84BoundingBoxType> draftl = this.getWGS84BoundingBox();
            draftl.addAll(value);
        }
    }

    public void setBoundingBox(List<JAXBElement<? extends BoundingBoxType>> value) {
        this.boundingBox = null;
        if (value!= null) {
            List<JAXBElement<? extends BoundingBoxType>> draftl = this.getBoundingBox();
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

    public void setDatasetDescriptionSummary(List<JAXBElement<DatasetDescriptionSummaryBaseType>> value) {
        this.datasetDescriptionSummary = null;
        if (value!= null) {
            List<JAXBElement<DatasetDescriptionSummaryBaseType>> draftl = this.getDatasetDescriptionSummary();
            draftl.addAll(value);
        }
    }

}
