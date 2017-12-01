//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import org.geosdi.geoplatform.xml.ows.v110.LanguageStringType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


/**
 * Definition of a format, encoding,  schema, and unit-of-measure for an output to be returned from a process.
 * <p>
 * In this use, the DescriptionType shall describe this process input or output.
 * <p>
 * <p>Classe Java per DocumentOutputDefinitionType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="DocumentOutputDefinitionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/wps/1.0.0}OutputDefinitionType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Title" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}Abstract" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="asReference" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentOutputDefinitionType", propOrder = {
        "title",
        "_abstract"
})
public class DocumentOutputDefinitionType extends OutputDefinitionType implements ToString2 {

    @XmlElement(name = "Title", namespace = "http://www.opengis.net/ows/1.1")
    protected LanguageStringType title;
    @XmlElement(name = "Abstract", namespace = "http://www.opengis.net/ows/1.1")
    protected LanguageStringType _abstract;
    @XmlAttribute(name = "asReference")
    protected Boolean asReference;

    /**
     * Title of the process output, normally available for display to a human. This element should be used if the client wishes to customize the Title in the execute response. This element should not be used if the Title provided for this output in the ProcessDescription is adequate.
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
     * Brief narrative description of a process output, normally available for display to a human. This element should be used if the client wishes to customize the Abstract in the execute response. This element should not be used if the Abstract provided for this output in the ProcessDescription is adequate.
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
     * Recupera il valore della proprietà asReference.
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isAsReference() {
        if (asReference == null) {
            return false;
        } else {
            return asReference;
        }
    }

    /**
     * Imposta il valore della proprietà asReference.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setAsReference(boolean value) {
        this.asReference = value;
    }

    public boolean isSetAsReference() {
        return (this.asReference != null);
    }

    public void unsetAsReference() {
        this.asReference = null;
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
        super.appendFields(locator, buffer, strategy);
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
            boolean theAsReference;
            theAsReference = (this.isSetAsReference() ? this.isAsReference() : false);
            strategy.appendField(locator, this, "asReference", buffer, theAsReference, this.isSetAsReference());
        }
        return buffer;
    }

}
