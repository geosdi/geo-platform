//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.25 alle 11:53:13 AM CET 
//


package org.geosdi.geoplatform.wms.v130;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Get"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}Post" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "get",
    "post"
})
@XmlRootElement(name = "HTTP")
public class HTTP implements ToString2
{

    @XmlElement(name = "Get", required = true)
    protected Get get;
    @XmlElement(name = "Post")
    protected Post post;

    /**
     * Recupera il valore della proprietà get.
     * 
     * @return
     *     possible object is
     *     {@link Get }
     *     
     */
    public Get getGet() {
        return get;
    }

    /**
     * Imposta il valore della proprietà get.
     * 
     * @param value
     *     allowed object is
     *     {@link Get }
     *     
     */
    public void setGet(Get value) {
        this.get = value;
    }

    /**
     * Recupera il valore della proprietà post.
     * 
     * @return
     *     possible object is
     *     {@link Post }
     *     
     */
    public Post getPost() {
        return post;
    }

    /**
     * Imposta il valore della proprietà post.
     * 
     * @param value
     *     allowed object is
     *     {@link Post }
     *     
     */
    public void setPost(Post value) {
        this.post = value;
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
            Get theGet;
            theGet = this.getGet();
            strategy.appendField(locator, this, "get", buffer, theGet, (this.get!= null));
        }
        {
            Post thePost;
            thePost = this.getPost();
            strategy.appendField(locator, this, "post", buffer, thePost, (this.post!= null));
        }
        return buffer;
    }

}
