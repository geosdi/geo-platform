//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:22:41 PM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.sld.v100.FeatureTypeStyleElement;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptorElement;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per SLDType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SLDType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LegendURL" type="{http://www.opengis.net/context}URLType" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="OnlineResource" type="{http://www.opengis.net/context}OnlineResourceType"/>
 *           &lt;element ref="{http://www.opengis.net/sld}StyledLayerDescriptor"/>
 *           &lt;element ref="{http://www.opengis.net/sld}FeatureTypeStyle"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SLDType", propOrder = {
    "name",
    "title",
    "legendURL",
    "onlineResource",
    "styledLayerDescriptor",
    "featureTypeStyle"
})
public class SLDType
    implements ToString
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "LegendURL")
    protected URLType legendURL;
    @XmlElement(name = "OnlineResource")
    protected OnlineResourceType onlineResource;
    @XmlElement(name = "StyledLayerDescriptor", namespace = "http://www.opengis.net/sld")
    protected StyledLayerDescriptorElement styledLayerDescriptor;
    @XmlElement(name = "FeatureTypeStyle", namespace = "http://www.opengis.net/sld")
    protected FeatureTypeStyleElement featureTypeStyle;

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

    public boolean isSetName() {
        return (this.name!= null);
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

    public boolean isSetTitle() {
        return (this.title!= null);
    }

    /**
     * Recupera il valore della proprietà legendURL.
     * 
     * @return
     *     possible object is
     *     {@link URLType }
     *     
     */
    public URLType getLegendURL() {
        return legendURL;
    }

    /**
     * Imposta il valore della proprietà legendURL.
     * 
     * @param value
     *     allowed object is
     *     {@link URLType }
     *     
     */
    public void setLegendURL(URLType value) {
        this.legendURL = value;
    }

    public boolean isSetLegendURL() {
        return (this.legendURL!= null);
    }

    /**
     * Recupera il valore della proprietà onlineResource.
     * 
     * @return
     *     possible object is
     *     {@link OnlineResourceType }
     *     
     */
    public OnlineResourceType getOnlineResource() {
        return onlineResource;
    }

    /**
     * Imposta il valore della proprietà onlineResource.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineResourceType }
     *     
     */
    public void setOnlineResource(OnlineResourceType value) {
        this.onlineResource = value;
    }

    public boolean isSetOnlineResource() {
        return (this.onlineResource!= null);
    }

    /**
     * Recupera il valore della proprietà styledLayerDescriptor.
     * 
     * @return
     *     possible object is
     *     {@link StyledLayerDescriptorElement }
     *     
     */
    public StyledLayerDescriptorElement getStyledLayerDescriptor() {
        return styledLayerDescriptor;
    }

    /**
     * Imposta il valore della proprietà styledLayerDescriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link StyledLayerDescriptorElement }
     *     
     */
    public void setStyledLayerDescriptor(StyledLayerDescriptorElement value) {
        this.styledLayerDescriptor = value;
    }

    public boolean isSetStyledLayerDescriptor() {
        return (this.styledLayerDescriptor!= null);
    }

    /**
     * Recupera il valore della proprietà featureTypeStyle.
     * 
     * @return
     *     possible object is
     *     {@link FeatureTypeStyleElement }
     *     
     */
    public FeatureTypeStyleElement getFeatureTypeStyle() {
        return featureTypeStyle;
    }

    /**
     * Imposta il valore della proprietà featureTypeStyle.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureTypeStyleElement }
     *     
     */
    public void setFeatureTypeStyle(FeatureTypeStyleElement value) {
        this.featureTypeStyle = value;
    }

    public boolean isSetFeatureTypeStyle() {
        return (this.featureTypeStyle!= null);
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle);
        }
        {
            URLType theLegendURL;
            theLegendURL = this.getLegendURL();
            strategy.appendField(locator, this, "legendURL", buffer, theLegendURL);
        }
        {
            OnlineResourceType theOnlineResource;
            theOnlineResource = this.getOnlineResource();
            strategy.appendField(locator, this, "onlineResource", buffer, theOnlineResource);
        }
        {
            StyledLayerDescriptorElement theStyledLayerDescriptor;
            theStyledLayerDescriptor = this.getStyledLayerDescriptor();
            strategy.appendField(locator, this, "styledLayerDescriptor", buffer, theStyledLayerDescriptor);
        }
        {
            FeatureTypeStyleElement theFeatureTypeStyle;
            theFeatureTypeStyle = this.getFeatureTypeStyle();
            strategy.appendField(locator, this, "featureTypeStyle", buffer, theFeatureTypeStyle);
        }
        return buffer;
    }

}
