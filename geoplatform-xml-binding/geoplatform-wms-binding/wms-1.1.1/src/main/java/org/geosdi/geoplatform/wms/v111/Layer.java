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
// Generato il: 2019.01.24 alle 07:06:40 PM CET 
//


package org.geosdi.geoplatform.wms.v111;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "title",
    "_abstract",
    "keywordList",
    "srs",
    "latLonBoundingBox",
    "boundingBox",
    "dimension",
    "extent",
    "attribution",
    "authorityURL",
    "identifier",
    "metadataURL",
    "dataURL",
    "featureListURL",
    "style",
    "scaleHint",
    "layer"
})
@XmlRootElement(name = "Layer")
public class Layer implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "queryable")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String queryable;
    @XmlAttribute(name = "cascaded")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String cascaded;
    @XmlAttribute(name = "opaque")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String opaque;
    @XmlAttribute(name = "noSubsets")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String noSubsets;
    @XmlAttribute(name = "fixedWidth")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String fixedWidth;
    @XmlAttribute(name = "fixedHeight")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String fixedHeight;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "KeywordList")
    protected KeywordList keywordList;
    @XmlElement(name = "SRS")
    protected List<SRS> srs;
    @XmlElement(name = "LatLonBoundingBox")
    protected LatLonBoundingBox latLonBoundingBox;
    @XmlElement(name = "BoundingBox")
    protected List<BoundingBox> boundingBox;
    @XmlElement(name = "Dimension")
    protected List<Dimension> dimension;
    @XmlElement(name = "Extent")
    protected List<Extent> extent;
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
    @XmlElement(name = "ScaleHint")
    protected ScaleHint scaleHint;
    @XmlElement(name = "Layer")
    protected List<Layer> layer;

    /**
     * Recupera il valore della proprietà queryable.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryable() {
        if (queryable == null) {
            return "0";
        } else {
            return queryable;
        }
    }

    /**
     * Imposta il valore della proprietà queryable.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryable(String value) {
        this.queryable = value;
    }

    /**
     * Recupera il valore della proprietà cascaded.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCascaded() {
        return cascaded;
    }

    /**
     * Imposta il valore della proprietà cascaded.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCascaded(String value) {
        this.cascaded = value;
    }

    /**
     * Recupera il valore della proprietà opaque.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpaque() {
        if (opaque == null) {
            return "0";
        } else {
            return opaque;
        }
    }

    /**
     * Imposta il valore della proprietà opaque.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpaque(String value) {
        this.opaque = value;
    }

    /**
     * Recupera il valore della proprietà noSubsets.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoSubsets() {
        if (noSubsets == null) {
            return "0";
        } else {
            return noSubsets;
        }
    }

    /**
     * Imposta il valore della proprietà noSubsets.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoSubsets(String value) {
        this.noSubsets = value;
    }

    /**
     * Recupera il valore della proprietà fixedWidth.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedWidth() {
        return fixedWidth;
    }

    /**
     * Imposta il valore della proprietà fixedWidth.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedWidth(String value) {
        this.fixedWidth = value;
    }

    /**
     * Recupera il valore della proprietà fixedHeight.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFixedHeight() {
        return fixedHeight;
    }

    /**
     * Imposta il valore della proprietà fixedHeight.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFixedHeight(String value) {
        this.fixedHeight = value;
    }

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
     * Gets the value of the srs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the srs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSRS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SRS }
     * 
     * 
     */
    public List<SRS> getSRS() {
        if (srs == null) {
            srs = new ArrayList<SRS>();
        }
        return this.srs;
    }

    /**
     * Recupera il valore della proprietà latLonBoundingBox.
     * 
     * @return
     *     possible object is
     *     {@link LatLonBoundingBox }
     *     
     */
    public LatLonBoundingBox getLatLonBoundingBox() {
        return latLonBoundingBox;
    }

    /**
     * Imposta il valore della proprietà latLonBoundingBox.
     * 
     * @param value
     *     allowed object is
     *     {@link LatLonBoundingBox }
     *     
     */
    public void setLatLonBoundingBox(LatLonBoundingBox value) {
        this.latLonBoundingBox = value;
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
     * Gets the value of the extent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Extent }
     * 
     * 
     */
    public List<Extent> getExtent() {
        if (extent == null) {
            extent = new ArrayList<Extent>();
        }
        return this.extent;
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
     * Recupera il valore della proprietà scaleHint.
     * 
     * @return
     *     possible object is
     *     {@link ScaleHint }
     *     
     */
    public ScaleHint getScaleHint() {
        return scaleHint;
    }

    /**
     * Imposta il valore della proprietà scaleHint.
     * 
     * @param value
     *     allowed object is
     *     {@link ScaleHint }
     *     
     */
    public void setScaleHint(ScaleHint value) {
        this.scaleHint = value;
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
            String theQueryable;
            theQueryable = this.getQueryable();
            strategy.appendField(locator, this, "queryable", buffer, theQueryable, (this.queryable!= null));
        }
        {
            String theCascaded;
            theCascaded = this.getCascaded();
            strategy.appendField(locator, this, "cascaded", buffer, theCascaded, (this.cascaded!= null));
        }
        {
            String theOpaque;
            theOpaque = this.getOpaque();
            strategy.appendField(locator, this, "opaque", buffer, theOpaque, (this.opaque!= null));
        }
        {
            String theNoSubsets;
            theNoSubsets = this.getNoSubsets();
            strategy.appendField(locator, this, "noSubsets", buffer, theNoSubsets, (this.noSubsets!= null));
        }
        {
            String theFixedWidth;
            theFixedWidth = this.getFixedWidth();
            strategy.appendField(locator, this, "fixedWidth", buffer, theFixedWidth, (this.fixedWidth!= null));
        }
        {
            String theFixedHeight;
            theFixedHeight = this.getFixedHeight();
            strategy.appendField(locator, this, "fixedHeight", buffer, theFixedHeight, (this.fixedHeight!= null));
        }
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
            List<SRS> theSRS;
            theSRS = (((this.srs!= null)&&(!this.srs.isEmpty()))?this.getSRS():null);
            strategy.appendField(locator, this, "srs", buffer, theSRS, ((this.srs!= null)&&(!this.srs.isEmpty())));
        }
        {
            LatLonBoundingBox theLatLonBoundingBox;
            theLatLonBoundingBox = this.getLatLonBoundingBox();
            strategy.appendField(locator, this, "latLonBoundingBox", buffer, theLatLonBoundingBox, (this.latLonBoundingBox!= null));
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
            List<Extent> theExtent;
            theExtent = (((this.extent!= null)&&(!this.extent.isEmpty()))?this.getExtent():null);
            strategy.appendField(locator, this, "extent", buffer, theExtent, ((this.extent!= null)&&(!this.extent.isEmpty())));
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
            ScaleHint theScaleHint;
            theScaleHint = this.getScaleHint();
            strategy.appendField(locator, this, "scaleHint", buffer, theScaleHint, (this.scaleHint!= null));
        }
        {
            List<Layer> theLayer;
            theLayer = (((this.layer!= null)&&(!this.layer.isEmpty()))?this.getLayer():null);
            strategy.appendField(locator, this, "layer", buffer, theLayer, ((this.layer!= null)&&(!this.layer.isEmpty())));
        }
        return buffer;
    }

    public void setSRS(List<SRS> value) {
        this.srs = null;
        if (value!= null) {
            List<SRS> draftl = this.getSRS();
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

    public void setExtent(List<Extent> value) {
        this.extent = null;
        if (value!= null) {
            List<Extent> draftl = this.getExtent();
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
