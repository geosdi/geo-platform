//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:33:43 AM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.geosdi.geoplatform.xml.sld.v100.FeatureTypeStyle;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SLDType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="SLDType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LegendURL" type="{http://www.opengis.net/context}URLType" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="OnlineResource" type="{http://www.opengis.net/context}OnlineResourceType"/&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}StyledLayerDescriptor"/&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}FeatureTypeStyle"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLDType", propOrder = {"name", "title", "legendURL", "onlineResource", "styledLayerDescriptor", "featureTypeStyle"})
public class SLDType implements ToString2 {

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "LegendURL")
    protected URLType legendURL;
    @XmlElement(name = "OnlineResource")
    protected OnlineResourceType onlineResource;
    @XmlElement(name = "StyledLayerDescriptor", namespace = "http://www.opengis.net/sld")
    protected StyledLayerDescriptor styledLayerDescriptor;
    @XmlElement(name = "FeatureTypeStyle", namespace = "http://www.opengis.net/sld")
    protected FeatureTypeStyle featureTypeStyle;

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
     * Recupera il valore della proprietà legendURL.
     *
     * @return possible object is
     * {@link URLType }
     */
    public URLType getLegendURL() {
        return legendURL;
    }

    /**
     * Imposta il valore della proprietà legendURL.
     *
     * @param value allowed object is
     * {@link URLType }
     */
    public void setLegendURL(URLType value) {
        this.legendURL = value;
    }

    public boolean isSetLegendURL() {
        return (this.legendURL != null);
    }

    /**
     * Recupera il valore della proprietà onlineResource.
     *
     * @return possible object is
     * {@link OnlineResourceType }
     */
    public OnlineResourceType getOnlineResource() {
        return onlineResource;
    }

    /**
     * Imposta il valore della proprietà onlineResource.
     *
     * @param value allowed object is
     * {@link OnlineResourceType }
     */
    public void setOnlineResource(OnlineResourceType value) {
        this.onlineResource = value;
    }

    public boolean isSetOnlineResource() {
        return (this.onlineResource != null);
    }

    /**
     * Recupera il valore della proprietà styledLayerDescriptor.
     *
     * @return possible object is
     * {@link StyledLayerDescriptor }
     */
    public StyledLayerDescriptor getStyledLayerDescriptor() {
        return styledLayerDescriptor;
    }

    /**
     * Imposta il valore della proprietà styledLayerDescriptor.
     *
     * @param value allowed object is
     * {@link StyledLayerDescriptor }
     */
    public void setStyledLayerDescriptor(StyledLayerDescriptor value) {
        this.styledLayerDescriptor = value;
    }

    public boolean isSetStyledLayerDescriptor() {
        return (this.styledLayerDescriptor != null);
    }

    /**
     * Recupera il valore della proprietà featureTypeStyle.
     *
     * @return possible object is
     * {@link FeatureTypeStyle }
     */
    public FeatureTypeStyle getFeatureTypeStyle() {
        return featureTypeStyle;
    }

    /**
     * Imposta il valore della proprietà featureTypeStyle.
     *
     * @param value allowed object is
     * {@link FeatureTypeStyle }
     */
    public void setFeatureTypeStyle(FeatureTypeStyle value) {
        this.featureTypeStyle = value;
    }

    public boolean isSetFeatureTypeStyle() {
        return (this.featureTypeStyle != null);
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
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            URLType theLegendURL;
            theLegendURL = this.getLegendURL();
            strategy.appendField(locator, this, "legendURL", buffer, theLegendURL, this.isSetLegendURL());
        }
        {
            OnlineResourceType theOnlineResource;
            theOnlineResource = this.getOnlineResource();
            strategy.appendField(locator, this, "onlineResource", buffer, theOnlineResource,
                    this.isSetOnlineResource());
        }
        {
            StyledLayerDescriptor theStyledLayerDescriptor;
            theStyledLayerDescriptor = this.getStyledLayerDescriptor();
            strategy.appendField(locator, this, "styledLayerDescriptor", buffer, theStyledLayerDescriptor,
                    this.isSetStyledLayerDescriptor());
        }
        {
            FeatureTypeStyle theFeatureTypeStyle;
            theFeatureTypeStyle = this.getFeatureTypeStyle();
            strategy.appendField(locator, this, "featureTypeStyle", buffer, theFeatureTypeStyle,
                    this.isSetFeatureTypeStyle());
        }
        return buffer;
    }

}
