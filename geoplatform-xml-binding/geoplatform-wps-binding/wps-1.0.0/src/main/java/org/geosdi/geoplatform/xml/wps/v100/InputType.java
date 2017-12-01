//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import org.geosdi.geoplatform.xml.ows.v110.CodeType;
import org.geosdi.geoplatform.xml.ows.v110.LanguageStringType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Value of one input to a process.
 * <p>
 * <p>Classe Java per InputType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="InputType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Identifier"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Title" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Abstract" minOccurs="0"/&gt;
 *         &lt;group ref="{http://www.opengis.net/wps/1.0.0}InputDataFormChoice"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputType", propOrder = {
        "identifier",
        "title",
        "_abstract",
        "reference",
        "data"
})
public class InputType implements ToString2 {

    @XmlElement(name = "Identifier", namespace = "http://www.opengis.net/ows/1.1", required = true)
    protected CodeType identifier;
    @XmlElement(name = "Title", namespace = "http://www.opengis.net/ows/1.1")
    protected LanguageStringType title;
    @XmlElement(name = "Abstract", namespace = "http://www.opengis.net/ows/1.1")
    protected LanguageStringType _abstract;
    @XmlElement(name = "Reference")
    protected InputReferenceType reference;
    @XmlElement(name = "Data")
    protected DataType data;

    /**
     * Unambiguous identifier or name of a process, unique for this server, or unambiguous identifier or name of an output, unique for this process.
     *
     * @return possible object is
     * {@link CodeType }
     */
    public CodeType getIdentifier() {
        return identifier;
    }

    /**
     * Imposta il valore della proprietà identifier.
     *
     * @param value allowed object is
     *              {@link CodeType }
     */
    public void setIdentifier(CodeType value) {
        this.identifier = value;
    }

    public boolean isSetIdentifier() {
        return (this.identifier != null);
    }

    /**
     * Title of a process or output, normally available for display to a human.
     *
     * @return possible object is
     * {@link LanguageStringType }
     */
    public LanguageStringType getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     *
     * @param value allowed object is
     *              {@link LanguageStringType }
     */
    public void setTitle(LanguageStringType value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title != null);
    }

    /**
     * Brief narrative description of a process or output, normally available for display to a human.
     *
     * @return possible object is
     * {@link LanguageStringType }
     */
    public LanguageStringType getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     *
     * @param value allowed object is
     *              {@link LanguageStringType }
     */
    public void setAbstract(LanguageStringType value) {
        this._abstract = value;
    }

    public boolean isSetAbstract() {
        return (this._abstract != null);
    }

    /**
     * Recupera il valore della proprietà reference.
     *
     * @return possible object is
     * {@link InputReferenceType }
     */
    public InputReferenceType getReference() {
        return reference;
    }

    /**
     * Imposta il valore della proprietà reference.
     *
     * @param value allowed object is
     *              {@link InputReferenceType }
     */
    public void setReference(InputReferenceType value) {
        this.reference = value;
    }

    public boolean isSetReference() {
        return (this.reference != null);
    }

    /**
     * Recupera il valore della proprietà data.
     *
     * @return possible object is
     * {@link DataType }
     */
    public DataType getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietà data.
     *
     * @param value allowed object is
     *              {@link DataType }
     */
    public void setData(DataType value) {
        this.data = value;
    }

    public boolean isSetData() {
        return (this.data != null);
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
            CodeType theIdentifier;
            theIdentifier = this.getIdentifier();
            strategy.appendField(locator, this, "identifier", buffer, theIdentifier, this.isSetIdentifier());
        }
        {
            LanguageStringType theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            LanguageStringType theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, this.isSetAbstract());
        }
        {
            InputReferenceType theReference;
            theReference = this.getReference();
            strategy.appendField(locator, this, "reference", buffer, theReference, this.isSetReference());
        }
        {
            DataType theData;
            theData = this.getData();
            strategy.appendField(locator, this, "data", buffer, theData, this.isSetData());
        }
        return buffer;
    }

}
