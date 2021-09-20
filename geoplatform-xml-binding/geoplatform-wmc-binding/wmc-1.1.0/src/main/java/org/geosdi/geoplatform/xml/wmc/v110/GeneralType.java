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


/**
 * <p>Classe Java per GeneralType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="GeneralType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Window" type="{http://www.opengis.net/context}WindowType" minOccurs="0"/&gt;
 *         &lt;element name="BoundingBox" type="{http://www.opengis.net/context}BoundingBoxType"/&gt;
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="KeywordList" type="{http://www.opengis.net/context}KeywordListType" minOccurs="0"/&gt;
 *         &lt;element name="Abstract" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LogoURL" type="{http://www.opengis.net/context}URLType" minOccurs="0"/&gt;
 *         &lt;element name="DescriptionURL" type="{http://www.opengis.net/context}URLType" minOccurs="0"/&gt;
 *         &lt;element name="ContactInformation" type="{http://www.opengis.net/context}ContactInformationType" minOccurs="0"/&gt;
 *         &lt;element name="Extension" type="{http://www.opengis.net/context}ExtensionType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneralType", propOrder = {"window", "boundingBox", "title", "keywordList", "_abstract", "logoURL",
        "descriptionURL", "contactInformation", "extension"})
@XmlRootElement(name = "General")
public class GeneralType implements ToString2 {

    @XmlElement(name = "Window")
    protected WindowType window;
    @XmlElement(name = "BoundingBox", required = true)
    protected BoundingBoxType boundingBox;
    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "KeywordList")
    protected KeywordListType keywordList;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "LogoURL")
    protected URLType logoURL;
    @XmlElement(name = "DescriptionURL")
    protected URLType descriptionURL;
    @XmlElement(name = "ContactInformation")
    protected ContactInformationType contactInformation;
    @XmlElement(name = "Extension")
    protected ExtensionType extension;

    /**
     * Recupera il valore della proprietà window.
     *
     * @return possible object is
     * {@link WindowType }
     */
    public WindowType getWindow() {
        return window;
    }

    /**
     * Imposta il valore della proprietà window.
     *
     * @param value allowed object is
     * {@link WindowType }
     */
    public void setWindow(WindowType value) {
        this.window = value;
    }

    public boolean isSetWindow() {
        return (this.window != null);
    }

    /**
     * Recupera il valore della proprietà boundingBox.
     *
     * @return possible object is
     * {@link BoundingBoxType }
     */
    public BoundingBoxType getBoundingBox() {
        return boundingBox;
    }

    /**
     * Imposta il valore della proprietà boundingBox.
     *
     * @param value allowed object is
     * {@link BoundingBoxType }
     */
    public void setBoundingBox(BoundingBoxType value) {
        this.boundingBox = value;
    }

    public boolean isSetBoundingBox() {
        return (this.boundingBox != null);
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
     * Recupera il valore della proprietà keywordList.
     *
     * @return possible object is
     * {@link KeywordListType }
     */
    public KeywordListType getKeywordList() {
        return keywordList;
    }

    /**
     * Imposta il valore della proprietà keywordList.
     *
     * @param value allowed object is
     * {@link KeywordListType }
     */
    public void setKeywordList(KeywordListType value) {
        this.keywordList = value;
    }

    public boolean isSetKeywordList() {
        return (this.keywordList != null);
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
     * Recupera il valore della proprietà logoURL.
     *
     * @return possible object is
     * {@link URLType }
     */
    public URLType getLogoURL() {
        return logoURL;
    }

    /**
     * Imposta il valore della proprietà logoURL.
     *
     * @param value allowed object is
     * {@link URLType }
     */
    public void setLogoURL(URLType value) {
        this.logoURL = value;
    }

    public boolean isSetLogoURL() {
        return (this.logoURL != null);
    }

    /**
     * Recupera il valore della proprietà descriptionURL.
     *
     * @return possible object is
     * {@link URLType }
     */
    public URLType getDescriptionURL() {
        return descriptionURL;
    }

    /**
     * Imposta il valore della proprietà descriptionURL.
     *
     * @param value allowed object is
     * {@link URLType }
     */
    public void setDescriptionURL(URLType value) {
        this.descriptionURL = value;
    }

    public boolean isSetDescriptionURL() {
        return (this.descriptionURL != null);
    }

    /**
     * Recupera il valore della proprietà contactInformation.
     *
     * @return possible object is
     * {@link ContactInformationType }
     */
    public ContactInformationType getContactInformation() {
        return contactInformation;
    }

    /**
     * Imposta il valore della proprietà contactInformation.
     *
     * @param value allowed object is
     * {@link ContactInformationType }
     */
    public void setContactInformation(ContactInformationType value) {
        this.contactInformation = value;
    }

    public boolean isSetContactInformation() {
        return (this.contactInformation != null);
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
            WindowType theWindow;
            theWindow = this.getWindow();
            strategy.appendField(locator, this, "window", buffer, theWindow, this.isSetWindow());
        }
        {
            BoundingBoxType theBoundingBox;
            theBoundingBox = this.getBoundingBox();
            strategy.appendField(locator, this, "boundingBox", buffer, theBoundingBox, this.isSetBoundingBox());
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            KeywordListType theKeywordList;
            theKeywordList = this.getKeywordList();
            strategy.appendField(locator, this, "keywordList", buffer, theKeywordList, this.isSetKeywordList());
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, this.isSetAbstract());
        }
        {
            URLType theLogoURL;
            theLogoURL = this.getLogoURL();
            strategy.appendField(locator, this, "logoURL", buffer, theLogoURL, this.isSetLogoURL());
        }
        {
            URLType theDescriptionURL;
            theDescriptionURL = this.getDescriptionURL();
            strategy.appendField(locator, this, "descriptionURL", buffer, theDescriptionURL,
                    this.isSetDescriptionURL());
        }
        {
            ContactInformationType theContactInformation;
            theContactInformation = this.getContactInformation();
            strategy.appendField(locator, this, "contactInformation", buffer, theContactInformation,
                    this.isSetContactInformation());
        }
        {
            ExtensionType theExtension;
            theExtension = this.getExtension();
            strategy.appendField(locator, this, "extension", buffer, theExtension, this.isSetExtension());
        }
        return buffer;
    }

}
