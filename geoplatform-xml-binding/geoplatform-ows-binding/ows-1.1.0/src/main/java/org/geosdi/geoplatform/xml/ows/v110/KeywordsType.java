//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 09:03:56 AM CET 
//


package org.geosdi.geoplatform.xml.ows.v110;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * For OWS use, the optional thesaurusName element was omitted as being complex information that could be referenced by the codeSpace attribute of the Type element. 
 * 
 * <p>Classe Java per KeywordsType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="KeywordsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Keyword" type="{http://www.opengis.net/ows/1.1}LanguageStringType" maxOccurs="unbounded"/&gt;
 *         &lt;element name="Type" type="{http://www.opengis.net/ows/1.1}CodeType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KeywordsType", propOrder = {
    "keyword",
    "type"
})
public class KeywordsType implements ToString2
{

    @XmlElement(name = "Keyword", required = true)
    protected List<LanguageStringType> keyword;
    @XmlElement(name = "Type")
    protected CodeType type;

    /**
     * Gets the value of the keyword property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyword property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyword().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LanguageStringType }
     * 
     * 
     */
    public List<LanguageStringType> getKeyword() {
        if (keyword == null) {
            keyword = new ArrayList<LanguageStringType>();
        }
        return this.keyword;
    }

    /**
     * Recupera il valore della proprietà type.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getType() {
        return type;
    }

    /**
     * Imposta il valore della proprietà type.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setType(CodeType value) {
        this.type = value;
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
            List<LanguageStringType> theKeyword;
            theKeyword = (((this.keyword!= null)&&(!this.keyword.isEmpty()))?this.getKeyword():null);
            strategy.appendField(locator, this, "keyword", buffer, theKeyword, ((this.keyword!= null)&&(!this.keyword.isEmpty())));
        }
        {
            CodeType theType;
            theType = this.getType();
            strategy.appendField(locator, this, "type", buffer, theType, (this.type!= null));
        }
        return buffer;
    }

    public void setKeyword(List<LanguageStringType> value) {
        this.keyword = null;
        if (value!= null) {
            List<LanguageStringType> draftl = this.getKeyword();
            draftl.addAll(value);
        }
    }

}
