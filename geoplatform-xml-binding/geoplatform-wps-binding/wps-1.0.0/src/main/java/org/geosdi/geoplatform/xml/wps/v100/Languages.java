//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per anonymous complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Default"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element ref="{http://www.opengis.net/ows/1.1}Language"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Supported" type="{http://www.opengis.net/wps/1.0.0}LanguagesType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "_default",
        "supported"
})
@XmlRootElement(name = "Languages")
public class Languages implements ToString2 {

    @XmlElement(name = "Default", required = true)
    protected Languages.Default _default;
    @XmlElement(name = "Supported", required = true)
    protected LanguagesType supported;

    /**
     * Recupera il valore della proprietà default.
     *
     * @return possible object is
     * {@link Languages.Default }
     */
    public Languages.Default getDefault() {
        return _default;
    }

    /**
     * Imposta il valore della proprietà default.
     *
     * @param value allowed object is
     *              {@link Languages.Default }
     */
    public void setDefault(Languages.Default value) {
        this._default = value;
    }

    public boolean isSetDefault() {
        return (this._default != null);
    }

    /**
     * Recupera il valore della proprietà supported.
     *
     * @return possible object is
     * {@link LanguagesType }
     */
    public LanguagesType getSupported() {
        return supported;
    }

    /**
     * Imposta il valore della proprietà supported.
     *
     * @param value allowed object is
     *              {@link LanguagesType }
     */
    public void setSupported(LanguagesType value) {
        this.supported = value;
    }

    public boolean isSetSupported() {
        return (this.supported != null);
    }

    public String toString() {
        final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE;
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
            Languages.Default theDefault;
            theDefault = this.getDefault();
            strategy.appendField(locator, this, "_default", buffer, theDefault, this.isSetDefault());
        }
        {
            LanguagesType theSupported;
            theSupported = this.getSupported();
            strategy.appendField(locator, this, "supported", buffer, theSupported, this.isSetSupported());
        }
        return buffer;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * <p>
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * <p>
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element ref="{http://www.opengis.net/ows/1.1}Language"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "language"
    })
    public static class Default implements ToString2 {

        @XmlElement(name = "Language", namespace = "http://www.opengis.net/ows/1.1", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "language")
        protected String language;

        /**
         * Identifier of the default language supported by the service.  This language identifier shall be as specified in IETF RFC 4646.
         *
         * @return possible object is
         * {@link String }
         */
        public String getLanguage() {
            return language;
        }

        /**
         * Imposta il valore della proprietà language.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setLanguage(String value) {
            this.language = value;
        }

        public boolean isSetLanguage() {
            return (this.language != null);
        }

        public String toString() {
            final ToStringStrategy2 strategy = JAXBToStringStrategy.INSTANCE;
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
                String theLanguage;
                theLanguage = this.getLanguage();
                strategy.appendField(locator, this, "language", buffer, theLanguage, this.isSetLanguage());
            }
            return buffer;
        }

    }

}
