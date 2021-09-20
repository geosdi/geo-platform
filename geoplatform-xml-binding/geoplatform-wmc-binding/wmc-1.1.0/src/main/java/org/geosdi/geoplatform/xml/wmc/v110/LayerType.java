//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:33:43 AM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Classe Java per LayerType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="LayerType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Server" type="{http://www.opengis.net/context}ServerType"/&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Abstract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataURL" type="{http://www.opengis.net/context}URLType" minOccurs="0"/&gt;
 *         &lt;element name="MetadataURL" type="{http://www.opengis.net/context}URLType" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}MinScaleDenominator" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}MaxScaleDenominator" minOccurs="0"/&gt;
 *         &lt;element name="SRS" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="FormatList" type="{http://www.opengis.net/context}FormatListType" minOccurs="0"/&gt;
 *         &lt;element name="StyleList" type="{http://www.opengis.net/context}StyleListType" minOccurs="0"/&gt;
 *         &lt;element name="DimensionList" type="{http://www.opengis.net/context}DimensionListType" minOccurs="0"/&gt;
 *         &lt;element name="Extension" type="{http://www.opengis.net/context}ExtensionType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="queryable" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="hidden" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LayerType", propOrder = {"server", "name", "title", "_abstract", "dataURL", "metadataURL",
        "minScaleDenominator", "maxScaleDenominator", "srs", "formatList", "styleList", "dimensionList", "extension"})
@XmlRootElement(name = "Layer")
public class LayerType implements ToString2 {

    @XmlElement(name = "Server", required = true)
    protected ServerType server;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "DataURL")
    protected URLType dataURL;
    @XmlElement(name = "MetadataURL")
    protected URLType metadataURL;
    @XmlElement(name = "MinScaleDenominator", namespace = "http://www.opengis.net/sld")
    protected Double minScaleDenominator;
    @XmlElement(name = "MaxScaleDenominator", namespace = "http://www.opengis.net/sld")
    protected Double maxScaleDenominator;
    @XmlElement(name = "SRS")
    protected List<String> srs;
    @XmlElement(name = "FormatList")
    protected FormatListType formatList;
    @XmlElement(name = "StyleList")
    protected StyleListType styleList;
    @XmlElement(name = "DimensionList")
    protected DimensionListType dimensionList;
    @XmlElement(name = "Extension")
    protected ExtensionType extension;
    @XmlAttribute(name = "queryable", required = true)
    protected boolean queryable;
    @XmlAttribute(name = "hidden", required = true)
    protected boolean hidden;

    /**
     * Recupera il valore della proprietà server.
     *
     * @return possible object is
     * {@link ServerType }
     */
    public ServerType getServer() {
        return server;
    }

    /**
     * Imposta il valore della proprietà server.
     *
     * @param value allowed object is
     * {@link ServerType }
     */
    public void setServer(ServerType value) {
        this.server = value;
    }

    public boolean isSetServer() {
        return (this.server != null);
    }

    /**
     * Recupera il valore della proprietà name.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name != null);
    }

    /**
     * Recupera il valore della proprietà title.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title != null);
    }

    /**
     * Recupera il valore della proprietà abstract.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    public boolean isSetAbstract() {
        return (this._abstract != null);
    }

    /**
     * Recupera il valore della proprietà dataURL.
     *
     * @return possible object is
     * {@link URLType }
     */
    public URLType getDataURL() {
        return dataURL;
    }

    /**
     * Imposta il valore della proprietà dataURL.
     *
     * @param value allowed object is
     * {@link URLType }
     */
    public void setDataURL(URLType value) {
        this.dataURL = value;
    }

    public boolean isSetDataURL() {
        return (this.dataURL != null);
    }

    /**
     * Recupera il valore della proprietà metadataURL.
     *
     * @return possible object is
     * {@link URLType }
     */
    public URLType getMetadataURL() {
        return metadataURL;
    }

    /**
     * Imposta il valore della proprietà metadataURL.
     *
     * @param value allowed object is
     * {@link URLType }
     */
    public void setMetadataURL(URLType value) {
        this.metadataURL = value;
    }

    public boolean isSetMetadataURL() {
        return (this.metadataURL != null);
    }

    /**
     * Recupera il valore della proprietà minScaleDenominator.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getMinScaleDenominator() {
        return minScaleDenominator;
    }

    /**
     * Imposta il valore della proprietà minScaleDenominator.
     *
     * @param value allowed object is
     * {@link Double }
     */
    public void setMinScaleDenominator(Double value) {
        this.minScaleDenominator = value;
    }

    public boolean isSetMinScaleDenominator() {
        return (this.minScaleDenominator != null);
    }

    /**
     * Recupera il valore della proprietà maxScaleDenominator.
     *
     * @return possible object is
     * {@link Double }
     */
    public Double getMaxScaleDenominator() {
        return maxScaleDenominator;
    }

    /**
     * Imposta il valore della proprietà maxScaleDenominator.
     *
     * @param value allowed object is
     * {@link Double }
     */
    public void setMaxScaleDenominator(Double value) {
        this.maxScaleDenominator = value;
    }

    public boolean isSetMaxScaleDenominator() {
        return (this.maxScaleDenominator != null);
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
     * {@link String }
     */
    public List<String> getSRS() {
        if (srs == null) {
            srs = new ArrayList<String>();
        }
        return this.srs;
    }

    public boolean isSetSRS() {
        return ((this.srs != null) && (!this.srs.isEmpty()));
    }

    public void unsetSRS() {
        this.srs = null;
    }

    /**
     * Recupera il valore della proprietà formatList.
     *
     * @return possible object is
     * {@link FormatListType }
     */
    public FormatListType getFormatList() {
        return formatList;
    }

    /**
     * Imposta il valore della proprietà formatList.
     *
     * @param value allowed object is
     * {@link FormatListType }
     */
    public void setFormatList(FormatListType value) {
        this.formatList = value;
    }

    public boolean isSetFormatList() {
        return (this.formatList != null);
    }

    /**
     * Recupera il valore della proprietà styleList.
     *
     * @return possible object is
     * {@link StyleListType }
     */
    public StyleListType getStyleList() {
        return styleList;
    }

    /**
     * Imposta il valore della proprietà styleList.
     *
     * @param value allowed object is
     * {@link StyleListType }
     */
    public void setStyleList(StyleListType value) {
        this.styleList = value;
    }

    public boolean isSetStyleList() {
        return (this.styleList != null);
    }

    /**
     * Recupera il valore della proprietà dimensionList.
     *
     * @return possible object is
     * {@link DimensionListType }
     */
    public DimensionListType getDimensionList() {
        return dimensionList;
    }

    /**
     * Imposta il valore della proprietà dimensionList.
     *
     * @param value allowed object is
     * {@link DimensionListType }
     */
    public void setDimensionList(DimensionListType value) {
        this.dimensionList = value;
    }

    public boolean isSetDimensionList() {
        return (this.dimensionList != null);
    }

    /**
     * Recupera il valore della proprietà extension.
     *
     * @return possible object is
     * {@link ExtensionType }
     */
    public ExtensionType getExtension() {
        return extension;
    }

    /**
     * Imposta il valore della proprietà extension.
     *
     * @param value allowed object is
     * {@link ExtensionType }
     */
    public void setExtension(ExtensionType value) {
        this.extension = value;
    }

    public boolean isSetExtension() {
        return (this.extension != null);
    }

    /**
     * Recupera il valore della proprietà queryable.
     */
    public boolean isQueryable() {
        return queryable;
    }

    /**
     * Imposta il valore della proprietà queryable.
     */
    public void setQueryable(boolean value) {
        this.queryable = value;
    }

    public boolean isSetQueryable() {
        return true;
    }

    /**
     * Recupera il valore della proprietà hidden.
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Imposta il valore della proprietà hidden.
     */
    public void setHidden(boolean value) {
        this.hidden = value;
    }

    public boolean isSetHidden() {
        return true;
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
            ServerType theServer;
            theServer = this.getServer();
            strategy.appendField(locator, this, "server", buffer, theServer, this.isSetServer());
        }
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, this.isSetAbstract());
        }
        {
            URLType theDataURL;
            theDataURL = this.getDataURL();
            strategy.appendField(locator, this, "dataURL", buffer, theDataURL, this.isSetDataURL());
        }
        {
            URLType theMetadataURL;
            theMetadataURL = this.getMetadataURL();
            strategy.appendField(locator, this, "metadataURL", buffer, theMetadataURL, this.isSetMetadataURL());
        }
        {
            Double theMinScaleDenominator;
            theMinScaleDenominator = this.getMinScaleDenominator();
            strategy.appendField(locator, this, "minScaleDenominator", buffer, theMinScaleDenominator,
                    this.isSetMinScaleDenominator());
        }
        {
            Double theMaxScaleDenominator;
            theMaxScaleDenominator = this.getMaxScaleDenominator();
            strategy.appendField(locator, this, "maxScaleDenominator", buffer, theMaxScaleDenominator,
                    this.isSetMaxScaleDenominator());
        }
        {
            List<String> theSRS;
            theSRS = (this.isSetSRS() ? this.getSRS() : null);
            strategy.appendField(locator, this, "srs", buffer, theSRS, this.isSetSRS());
        }
        {
            FormatListType theFormatList;
            theFormatList = this.getFormatList();
            strategy.appendField(locator, this, "formatList", buffer, theFormatList, this.isSetFormatList());
        }
        {
            StyleListType theStyleList;
            theStyleList = this.getStyleList();
            strategy.appendField(locator, this, "styleList", buffer, theStyleList, this.isSetStyleList());
        }
        {
            DimensionListType theDimensionList;
            theDimensionList = this.getDimensionList();
            strategy.appendField(locator, this, "dimensionList", buffer, theDimensionList, this.isSetDimensionList());
        }
        {
            ExtensionType theExtension;
            theExtension = this.getExtension();
            strategy.appendField(locator, this, "extension", buffer, theExtension, this.isSetExtension());
        }
        {
            boolean theQueryable;
            theQueryable = this.isQueryable();
            strategy.appendField(locator, this, "queryable", buffer, theQueryable, true);
        }
        {
            boolean theHidden;
            theHidden = this.isHidden();
            strategy.appendField(locator, this, "hidden", buffer, theHidden, true);
        }
        return buffer;
    }

    public void setSRS(List<String> value) {
        this.srs = null;
        if (value != null) {
            List<String> draftl = this.getSRS();
            draftl.addAll(value);
        }
    }

}
