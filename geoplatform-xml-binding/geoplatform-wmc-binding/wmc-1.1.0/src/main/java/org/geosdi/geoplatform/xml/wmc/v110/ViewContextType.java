//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:22:41 PM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per ViewContextType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ViewContextType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="General" type="{http://www.opengis.net/context}GeneralType"/>
 *         &lt;element name="LayerList" type="{http://www.opengis.net/context}LayerListType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.1.0" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewContextType", propOrder = {
    "general",
    "layerList"
})
public class ViewContextType
    implements ToString
{

    @XmlElement(name = "General", required = true)
    protected GeneralType general;
    @XmlElement(name = "LayerList", required = true)
    protected LayerListType layerList;
    /**
     * 
     * 
     */
    @XmlAttribute(name = "version", required = true)
    public final static String VERSION = "1.1.0";
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Recupera il valore della proprietà general.
     * 
     * @return
     *     possible object is
     *     {@link GeneralType }
     *     
     */
    public GeneralType getGeneral() {
        return general;
    }

    /**
     * Imposta il valore della proprietà general.
     * 
     * @param value
     *     allowed object is
     *     {@link GeneralType }
     *     
     */
    public void setGeneral(GeneralType value) {
        this.general = value;
    }

    public boolean isSetGeneral() {
        return (this.general!= null);
    }

    /**
     * Recupera il valore della proprietà layerList.
     * 
     * @return
     *     possible object is
     *     {@link LayerListType }
     *     
     */
    public LayerListType getLayerList() {
        return layerList;
    }

    /**
     * Imposta il valore della proprietà layerList.
     * 
     * @param value
     *     allowed object is
     *     {@link LayerListType }
     *     
     */
    public void setLayerList(LayerListType value) {
        this.layerList = value;
    }

    public boolean isSetLayerList() {
        return (this.layerList!= null);
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id!= null);
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
            GeneralType theGeneral;
            theGeneral = this.getGeneral();
            strategy.appendField(locator, this, "general", buffer, theGeneral);
        }
        {
            LayerListType theLayerList;
            theLayerList = this.getLayerList();
            strategy.appendField(locator, this, "layerList", buffer, theLayerList);
        }
        {
            String theVERSION;
            theVERSION = ViewContextType.VERSION;
            strategy.appendField(locator, this, "version", buffer, theVERSION);
        }
        {
            String theId;
            theId = this.getId();
            strategy.appendField(locator, this, "id", buffer, theId);
        }
        return buffer;
    }

}
