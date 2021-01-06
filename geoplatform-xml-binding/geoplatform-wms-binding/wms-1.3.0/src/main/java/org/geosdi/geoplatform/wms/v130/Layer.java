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
// Generato il: 2019.01.25 alle 11:53:13 AM CET 
//


package org.geosdi.geoplatform.wms.v130;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Name" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Title"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Abstract" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}KeywordList" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}CRS" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}EX_GeographicBoundingBox" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}BoundingBox" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Dimension" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Attribution" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}AuthorityURL" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Identifier" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}MetadataURL" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}DataURL" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}FeatureListURL" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Style" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}MinScaleDenominator" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}MaxScaleDenominator" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Layer" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="queryable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="0" /&gt;
 *       &lt;attribute name="cascaded" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" /&gt;
 *       &lt;attribute name="opaque" type="{http://www.w3.org/2001/XMLSchema}boolean" default="0" /&gt;
 *       &lt;attribute name="noSubsets" type="{http://www.w3.org/2001/XMLSchema}boolean" default="0" /&gt;
 *       &lt;attribute name="fixedWidth" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" /&gt;
 *       &lt;attribute name="fixedHeight" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "title",
    "_abstract",
    "keywordList",
    "crs",
    "exGeographicBoundingBox",
    "boundingBox",
    "dimension",
    "attribution",
    "authorityURL",
    "identifier",
    "metadataURL",
    "dataURL",
    "featureListURL",
    "style",
    "minScaleDenominator",
    "maxScaleDenominator",
    "layer"
})
@XmlRootElement(name = "Layer")
public class Layer implements ToString2
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "KeywordList")
    protected KeywordList keywordList;
    @XmlElement(name = "CRS")
    protected List<String> crs;
    @XmlElement(name = "EX_GeographicBoundingBox")
    protected EXGeographicBoundingBox exGeographicBoundingBox;
    @XmlElement(name = "BoundingBox")
    protected List<BoundingBox> boundingBox;
    @XmlElement(name = "Dimension")
    protected List<Dimension> dimension;
    @XmlElement(name = "Attribution")
    protected Attribution attribution;
    @XmlElement(name = "AuthorityURL")
    protected List<AuthorityURL> authorityURL;
    @XmlElement(name = "Identifier")
    protected List<Identifier> identifier;
    @XmlElement(name = "MetadataURL")
    protected List<MetadataURL> metadataURL;
    @XmlElement(name = "DataURL")
    protected List<DataURL> dataURL;
    @XmlElement(name = "FeatureListURL")
    protected List<FeatureListURL> featureListURL;
    @XmlElement(name = "Style")
    protected List<Style> style;
    @XmlElement(name = "MinScaleDenominator")
    protected Double minScaleDenominator;
    @XmlElement(name = "MaxScaleDenominator")
    protected Double maxScaleDenominator;
    @XmlElement(name = "Layer")
    protected List<Layer> layer;
    @XmlAttribute(name = "queryable")
    protected Boolean queryable;
    @XmlAttribute(name = "cascaded")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger cascaded;
    @XmlAttribute(name = "opaque")
    protected Boolean opaque;
    @XmlAttribute(name = "noSubsets")
    protected Boolean noSubsets;
    @XmlAttribute(name = "fixedWidth")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger fixedWidth;
    @XmlAttribute(name = "fixedHeight")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger fixedHeight;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà title.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Recupera il valore della proprietà abstract.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    /**
     * Recupera il valore della proprietà keywordList.
     * 
     * @return
     *     possible object is
     *     {@link KeywordList }
     *     
     */
    public KeywordList getKeywordList() {
        return keywordList;
    }

    /**
     * Imposta il valore della proprietà keywordList.
     * 
     * @param value
     *     allowed object is
     *     {@link KeywordList }
     *     
     */
    public void setKeywordList(KeywordList value) {
        this.keywordList = value;
    }

    /**
     * Gets the value of the crs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCRS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCRS() {
        if (crs == null) {
            crs = new ArrayList<String>();
        }
        return this.crs;
    }

    /**
     * Recupera il valore della proprietà exGeographicBoundingBox.
     * 
     * @return
     *     possible object is
     *     {@link EXGeographicBoundingBox }
     *     
     */
    public EXGeographicBoundingBox getEXGeographicBoundingBox() {
        return exGeographicBoundingBox;
    }

    /**
     * Imposta il valore della proprietà exGeographicBoundingBox.
     * 
     * @param value
     *     allowed object is
     *     {@link EXGeographicBoundingBox }
     *     
     */
    public void setEXGeographicBoundingBox(EXGeographicBoundingBox value) {
        this.exGeographicBoundingBox = value;
    }

    /**
     * Gets the value of the boundingBox property.
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
     * {@link BoundingBox }
     * 
     * 
     */
    public List<BoundingBox> getBoundingBox() {
        if (boundingBox == null) {
            boundingBox = new ArrayList<BoundingBox>();
        }
        return this.boundingBox;
    }

    /**
     * Gets the value of the dimension property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dimension property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDimension().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dimension }
     * 
     * 
     */
    public List<Dimension> getDimension() {
        if (dimension == null) {
            dimension = new ArrayList<Dimension>();
        }
        return this.dimension;
    }

    /**
     * Recupera il valore della proprietà attribution.
     * 
     * @return
     *     possible object is
     *     {@link Attribution }
     *     
     */
    public Attribution getAttribution() {
        return attribution;
    }

    /**
     * Imposta il valore della proprietà attribution.
     * 
     * @param value
     *     allowed object is
     *     {@link Attribution }
     *     
     */
    public void setAttribution(Attribution value) {
        this.attribution = value;
    }

    /**
     * Gets the value of the authorityURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the authorityURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuthorityURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AuthorityURL }
     * 
     * 
     */
    public List<AuthorityURL> getAuthorityURL() {
        if (authorityURL == null) {
            authorityURL = new ArrayList<AuthorityURL>();
        }
        return this.authorityURL;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Identifier }
     * 
     * 
     */
    public List<Identifier> getIdentifier() {
        if (identifier == null) {
            identifier = new ArrayList<Identifier>();
        }
        return this.identifier;
    }

    /**
     * Gets the value of the metadataURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metadataURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetadataURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetadataURL }
     * 
     * 
     */
    public List<MetadataURL> getMetadataURL() {
        if (metadataURL == null) {
            metadataURL = new ArrayList<MetadataURL>();
        }
        return this.metadataURL;
    }

    /**
     * Gets the value of the dataURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataURL }
     * 
     * 
     */
    public List<DataURL> getDataURL() {
        if (dataURL == null) {
            dataURL = new ArrayList<DataURL>();
        }
        return this.dataURL;
    }

    /**
     * Gets the value of the featureListURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the featureListURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFeatureListURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FeatureListURL }
     * 
     * 
     */
    public List<FeatureListURL> getFeatureListURL() {
        if (featureListURL == null) {
            featureListURL = new ArrayList<FeatureListURL>();
        }
        return this.featureListURL;
    }

    /**
     * Gets the value of the style property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the style property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStyle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Style }
     * 
     * 
     */
    public List<Style> getStyle() {
        if (style == null) {
            style = new ArrayList<Style>();
        }
        return this.style;
    }

    /**
     * Recupera il valore della proprietà minScaleDenominator.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinScaleDenominator() {
        return minScaleDenominator;
    }

    /**
     * Imposta il valore della proprietà minScaleDenominator.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinScaleDenominator(Double value) {
        this.minScaleDenominator = value;
    }

    /**
     * Recupera il valore della proprietà maxScaleDenominator.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxScaleDenominator() {
        return maxScaleDenominator;
    }

    /**
     * Imposta il valore della proprietà maxScaleDenominator.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxScaleDenominator(Double value) {
        this.maxScaleDenominator = value;
    }

    /**
     * Gets the value of the layer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the layer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLayer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Layer }
     * 
     * 
     */
    public List<Layer> getLayer() {
        if (layer == null) {
            layer = new ArrayList<Layer>();
        }
        return this.layer;
    }

    /**
     * Recupera il valore della proprietà queryable.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isQueryable() {
        if (queryable == null) {
            return false;
        } else {
            return queryable;
        }
    }

    /**
     * Imposta il valore della proprietà queryable.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setQueryable(Boolean value) {
        this.queryable = value;
    }

    /**
     * Recupera il valore della proprietà cascaded.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCascaded() {
        return cascaded;
    }

    /**
     * Imposta il valore della proprietà cascaded.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCascaded(BigInteger value) {
        this.cascaded = value;
    }

    /**
     * Recupera il valore della proprietà opaque.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isOpaque() {
        if (opaque == null) {
            return false;
        } else {
            return opaque;
        }
    }

    /**
     * Imposta il valore della proprietà opaque.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOpaque(Boolean value) {
        this.opaque = value;
    }

    /**
     * Recupera il valore della proprietà noSubsets.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isNoSubsets() {
        if (noSubsets == null) {
            return false;
        } else {
            return noSubsets;
        }
    }

    /**
     * Imposta il valore della proprietà noSubsets.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoSubsets(Boolean value) {
        this.noSubsets = value;
    }

    /**
     * Recupera il valore della proprietà fixedWidth.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFixedWidth() {
        return fixedWidth;
    }

    /**
     * Imposta il valore della proprietà fixedWidth.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFixedWidth(BigInteger value) {
        this.fixedWidth = value;
    }

    /**
     * Recupera il valore della proprietà fixedHeight.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFixedHeight() {
        return fixedHeight;
    }

    /**
     * Imposta il valore della proprietà fixedHeight.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFixedHeight(BigInteger value) {
        this.fixedHeight = value;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, (this.name!= null));
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, (this.title!= null));
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, (this._abstract!= null));
        }
        {
            KeywordList theKeywordList;
            theKeywordList = this.getKeywordList();
            strategy.appendField(locator, this, "keywordList", buffer, theKeywordList, (this.keywordList!= null));
        }
        {
            List<String> theCRS;
            theCRS = (((this.crs!= null)&&(!this.crs.isEmpty()))?this.getCRS():null);
            strategy.appendField(locator, this, "crs", buffer, theCRS, ((this.crs!= null)&&(!this.crs.isEmpty())));
        }
        {
            EXGeographicBoundingBox theEXGeographicBoundingBox;
            theEXGeographicBoundingBox = this.getEXGeographicBoundingBox();
            strategy.appendField(locator, this, "exGeographicBoundingBox", buffer, theEXGeographicBoundingBox, (this.exGeographicBoundingBox!= null));
        }
        {
            List<BoundingBox> theBoundingBox;
            theBoundingBox = (((this.boundingBox!= null)&&(!this.boundingBox.isEmpty()))?this.getBoundingBox():null);
            strategy.appendField(locator, this, "boundingBox", buffer, theBoundingBox, ((this.boundingBox!= null)&&(!this.boundingBox.isEmpty())));
        }
        {
            List<Dimension> theDimension;
            theDimension = (((this.dimension!= null)&&(!this.dimension.isEmpty()))?this.getDimension():null);
            strategy.appendField(locator, this, "dimension", buffer, theDimension, ((this.dimension!= null)&&(!this.dimension.isEmpty())));
        }
        {
            Attribution theAttribution;
            theAttribution = this.getAttribution();
            strategy.appendField(locator, this, "attribution", buffer, theAttribution, (this.attribution!= null));
        }
        {
            List<AuthorityURL> theAuthorityURL;
            theAuthorityURL = (((this.authorityURL!= null)&&(!this.authorityURL.isEmpty()))?this.getAuthorityURL():null);
            strategy.appendField(locator, this, "authorityURL", buffer, theAuthorityURL, ((this.authorityURL!= null)&&(!this.authorityURL.isEmpty())));
        }
        {
            List<Identifier> theIdentifier;
            theIdentifier = (((this.identifier!= null)&&(!this.identifier.isEmpty()))?this.getIdentifier():null);
            strategy.appendField(locator, this, "identifier", buffer, theIdentifier, ((this.identifier!= null)&&(!this.identifier.isEmpty())));
        }
        {
            List<MetadataURL> theMetadataURL;
            theMetadataURL = (((this.metadataURL!= null)&&(!this.metadataURL.isEmpty()))?this.getMetadataURL():null);
            strategy.appendField(locator, this, "metadataURL", buffer, theMetadataURL, ((this.metadataURL!= null)&&(!this.metadataURL.isEmpty())));
        }
        {
            List<DataURL> theDataURL;
            theDataURL = (((this.dataURL!= null)&&(!this.dataURL.isEmpty()))?this.getDataURL():null);
            strategy.appendField(locator, this, "dataURL", buffer, theDataURL, ((this.dataURL!= null)&&(!this.dataURL.isEmpty())));
        }
        {
            List<FeatureListURL> theFeatureListURL;
            theFeatureListURL = (((this.featureListURL!= null)&&(!this.featureListURL.isEmpty()))?this.getFeatureListURL():null);
            strategy.appendField(locator, this, "featureListURL", buffer, theFeatureListURL, ((this.featureListURL!= null)&&(!this.featureListURL.isEmpty())));
        }
        {
            List<Style> theStyle;
            theStyle = (((this.style!= null)&&(!this.style.isEmpty()))?this.getStyle():null);
            strategy.appendField(locator, this, "style", buffer, theStyle, ((this.style!= null)&&(!this.style.isEmpty())));
        }
        {
            Double theMinScaleDenominator;
            theMinScaleDenominator = this.getMinScaleDenominator();
            strategy.appendField(locator, this, "minScaleDenominator", buffer, theMinScaleDenominator, (this.minScaleDenominator!= null));
        }
        {
            Double theMaxScaleDenominator;
            theMaxScaleDenominator = this.getMaxScaleDenominator();
            strategy.appendField(locator, this, "maxScaleDenominator", buffer, theMaxScaleDenominator, (this.maxScaleDenominator!= null));
        }
        {
            List<Layer> theLayer;
            theLayer = (((this.layer!= null)&&(!this.layer.isEmpty()))?this.getLayer():null);
            strategy.appendField(locator, this, "layer", buffer, theLayer, ((this.layer!= null)&&(!this.layer.isEmpty())));
        }
        {
            boolean theQueryable;
            theQueryable = ((this.queryable!= null)?this.isQueryable():false);
            strategy.appendField(locator, this, "queryable", buffer, theQueryable, (this.queryable!= null));
        }
        {
            BigInteger theCascaded;
            theCascaded = this.getCascaded();
            strategy.appendField(locator, this, "cascaded", buffer, theCascaded, (this.cascaded!= null));
        }
        {
            boolean theOpaque;
            theOpaque = ((this.opaque!= null)?this.isOpaque():false);
            strategy.appendField(locator, this, "opaque", buffer, theOpaque, (this.opaque!= null));
        }
        {
            boolean theNoSubsets;
            theNoSubsets = ((this.noSubsets!= null)?this.isNoSubsets():false);
            strategy.appendField(locator, this, "noSubsets", buffer, theNoSubsets, (this.noSubsets!= null));
        }
        {
            BigInteger theFixedWidth;
            theFixedWidth = this.getFixedWidth();
            strategy.appendField(locator, this, "fixedWidth", buffer, theFixedWidth, (this.fixedWidth!= null));
        }
        {
            BigInteger theFixedHeight;
            theFixedHeight = this.getFixedHeight();
            strategy.appendField(locator, this, "fixedHeight", buffer, theFixedHeight, (this.fixedHeight!= null));
        }
        return buffer;
    }

    public void setCRS(List<String> value) {
        this.crs = null;
        if (value!= null) {
            List<String> draftl = this.getCRS();
            draftl.addAll(value);
        }
    }

    public void setBoundingBox(List<BoundingBox> value) {
        this.boundingBox = null;
        if (value!= null) {
            List<BoundingBox> draftl = this.getBoundingBox();
            draftl.addAll(value);
        }
    }

    public void setDimension(List<Dimension> value) {
        this.dimension = null;
        if (value!= null) {
            List<Dimension> draftl = this.getDimension();
            draftl.addAll(value);
        }
    }

    public void setAuthorityURL(List<AuthorityURL> value) {
        this.authorityURL = null;
        if (value!= null) {
            List<AuthorityURL> draftl = this.getAuthorityURL();
            draftl.addAll(value);
        }
    }

    public void setIdentifier(List<Identifier> value) {
        this.identifier = null;
        if (value!= null) {
            List<Identifier> draftl = this.getIdentifier();
            draftl.addAll(value);
        }
    }

    public void setMetadataURL(List<MetadataURL> value) {
        this.metadataURL = null;
        if (value!= null) {
            List<MetadataURL> draftl = this.getMetadataURL();
            draftl.addAll(value);
        }
    }

    public void setDataURL(List<DataURL> value) {
        this.dataURL = null;
        if (value!= null) {
            List<DataURL> draftl = this.getDataURL();
            draftl.addAll(value);
        }
    }

    public void setFeatureListURL(List<FeatureListURL> value) {
        this.featureListURL = null;
        if (value!= null) {
            List<FeatureListURL> draftl = this.getFeatureListURL();
            draftl.addAll(value);
        }
    }

    public void setStyle(List<Style> value) {
        this.style = null;
        if (value!= null) {
            List<Style> draftl = this.getStyle();
            draftl.addAll(value);
        }
    }

    public void setLayer(List<Layer> value) {
        this.layer = null;
        if (value!= null) {
            List<Layer> draftl = this.getLayer();
            draftl.addAll(value);
        }
    }

}
