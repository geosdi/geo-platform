//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.30 alle 03:34:58 PM CET 
//


package org.geosdi.geoplatform.xml.xlink.v100;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per arcType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="arcType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;group ref="{http://www.w3.org/1999/xlink}arcModel"/&gt;
 *       &lt;attGroup ref="{http://www.w3.org/1999/xlink}arcAttrs"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "arcType", propOrder = {
    "locatorTitle"
})
public class ArcType implements ToString2
{

    @XmlElement(name = "title")
    protected List<TitleEltType> locatorTitle;
    /**
     * 
     * 
     */
    @XmlAttribute(name = "type", namespace = "http://www.w3.org/1999/xlink", required = true)
    public final static TypeType TYPE = TypeType.ARC;
    @XmlAttribute(name = "arcrole", namespace = "http://www.w3.org/1999/xlink")
    protected String arcrole;
    @XmlAttribute(name = "title", namespace = "http://www.w3.org/1999/xlink")
    protected String title;
    @XmlAttribute(name = "show", namespace = "http://www.w3.org/1999/xlink")
    protected ShowType show;
    @XmlAttribute(name = "actuate", namespace = "http://www.w3.org/1999/xlink")
    protected ActuateType actuate;
    @XmlAttribute(name = "from", namespace = "http://www.w3.org/1999/xlink")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String from;
    @XmlAttribute(name = "to", namespace = "http://www.w3.org/1999/xlink")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String to;

    /**
     * Gets the value of the locatorTitle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locatorTitle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocatorTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TitleEltType }
     * 
     * 
     */
    public List<TitleEltType> getLocatorTitle() {
        if (locatorTitle == null) {
            locatorTitle = new ArrayList<TitleEltType>();
        }
        return this.locatorTitle;
    }

    public boolean isSetLocatorTitle() {
        return ((this.locatorTitle!= null)&&(!this.locatorTitle.isEmpty()));
    }

    public void unsetLocatorTitle() {
        this.locatorTitle = null;
    }

    /**
     * Recupera il valore della proprietà arcrole.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArcrole() {
        return arcrole;
    }

    /**
     * Imposta il valore della proprietà arcrole.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArcrole(String value) {
        this.arcrole = value;
    }

    public boolean isSetArcrole() {
        return (this.arcrole!= null);
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
     * Recupera il valore della proprietà show.
     * 
     * @return
     *     possible object is
     *     {@link ShowType }
     *     
     */
    public ShowType getShow() {
        return show;
    }

    /**
     * Imposta il valore della proprietà show.
     * 
     * @param value
     *     allowed object is
     *     {@link ShowType }
     *     
     */
    public void setShow(ShowType value) {
        this.show = value;
    }

    public boolean isSetShow() {
        return (this.show!= null);
    }

    /**
     * Recupera il valore della proprietà actuate.
     * 
     * @return
     *     possible object is
     *     {@link ActuateType }
     *     
     */
    public ActuateType getActuate() {
        return actuate;
    }

    /**
     * Imposta il valore della proprietà actuate.
     * 
     * @param value
     *     allowed object is
     *     {@link ActuateType }
     *     
     */
    public void setActuate(ActuateType value) {
        this.actuate = value;
    }

    public boolean isSetActuate() {
        return (this.actuate!= null);
    }

    /**
     * Recupera il valore della proprietà from.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrom() {
        return from;
    }

    /**
     * Imposta il valore della proprietà from.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrom(String value) {
        this.from = value;
    }

    public boolean isSetFrom() {
        return (this.from!= null);
    }

    /**
     * 
     *                     from and to have default behavior when values are missing
     *                 
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTo() {
        return to;
    }

    /**
     * Imposta il valore della proprietà to.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTo(String value) {
        this.to = value;
    }

    public boolean isSetTo() {
        return (this.to!= null);
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
            List<TitleEltType> theLocatorTitle;
            theLocatorTitle = (this.isSetLocatorTitle()?this.getLocatorTitle():null);
            strategy.appendField(locator, this, "locatorTitle", buffer, theLocatorTitle, this.isSetLocatorTitle());
        }
        {
            TypeType theTYPE;
            theTYPE = ArcType.TYPE;
            strategy.appendField(locator, this, "type", buffer, theTYPE, true);
        }
        {
            String theArcrole;
            theArcrole = this.getArcrole();
            strategy.appendField(locator, this, "arcrole", buffer, theArcrole, this.isSetArcrole());
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            ShowType theShow;
            theShow = this.getShow();
            strategy.appendField(locator, this, "show", buffer, theShow, this.isSetShow());
        }
        {
            ActuateType theActuate;
            theActuate = this.getActuate();
            strategy.appendField(locator, this, "actuate", buffer, theActuate, this.isSetActuate());
        }
        {
            String theFrom;
            theFrom = this.getFrom();
            strategy.appendField(locator, this, "from", buffer, theFrom, this.isSetFrom());
        }
        {
            String theTo;
            theTo = this.getTo();
            strategy.appendField(locator, this, "to", buffer, theTo, this.isSetTo());
        }
        return buffer;
    }

    public void setLocatorTitle(List<TitleEltType> value) {
        this.locatorTitle = null;
        if (value!= null) {
            List<TitleEltType> draftl = this.getLocatorTitle();
            draftl.addAll(value);
        }
    }

}