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


/**
 * Listing of the Coordinate Reference System (CRS) support for this process input or output.
 * <p>
 * <p>Classe Java per SupportedCRSsType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="SupportedCRSsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Default"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CRS" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Supported" type="{http://www.opengis.net/wps/1.0.0}CRSsType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportedCRSsType", propOrder = {
        "_default",
        "supported"
})
public class SupportedCRSsType implements ToString2 {

    @XmlElement(name = "Default", namespace = "", required = true)
    protected SupportedCRSsType.Default _default;
    @XmlElement(name = "Supported", namespace = "", required = true)
    protected CRSsType supported;

    /**
     * Recupera il valore della proprietà default.
     *
     * @return possible object is
     * {@link SupportedCRSsType.Default }
     */
    public SupportedCRSsType.Default getDefault() {
        return _default;
    }

    /**
     * Imposta il valore della proprietà default.
     *
     * @param value allowed object is
     *              {@link SupportedCRSsType.Default }
     */
    public void setDefault(SupportedCRSsType.Default value) {
        this._default = value;
    }

    public boolean isSetDefault() {
        return (this._default != null);
    }

    /**
     * Recupera il valore della proprietà supported.
     *
     * @return possible object is
     * {@link CRSsType }
     */
    public CRSsType getSupported() {
        return supported;
    }

    /**
     * Imposta il valore della proprietà supported.
     *
     * @param value allowed object is
     *              {@link CRSsType }
     */
    public void setSupported(CRSsType value) {
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
            SupportedCRSsType.Default theDefault;
            theDefault = this.getDefault();
            strategy.appendField(locator, this, "_default", buffer, theDefault, this.isSetDefault());
        }
        {
            CRSsType theSupported;
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
     *         &lt;element name="CRS" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "crs"
    })
    public static class Default implements ToString2 {

        @XmlElement(name = "CRS", namespace = "", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String crs;

        /**
         * Recupera il valore della proprietà crs.
         *
         * @return possible object is
         * {@link String }
         */
        public String getCRS() {
            return crs;
        }

        /**
         * Imposta il valore della proprietà crs.
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setCRS(String value) {
            this.crs = value;
        }

        public boolean isSetCRS() {
            return (this.crs != null);
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
                String theCRS;
                theCRS = this.getCRS();
                strategy.appendField(locator, this, "crs", buffer, theCRS, this.isSetCRS());
            }
            return buffer;
        }

    }

}
