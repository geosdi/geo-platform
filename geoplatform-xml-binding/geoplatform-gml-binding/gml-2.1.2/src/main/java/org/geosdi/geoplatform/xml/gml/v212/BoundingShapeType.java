//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 10:30:08 PM CEST 
//


package org.geosdi.geoplatform.xml.gml.v212;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * 
 *         Bounding shapes--a Box or a null element are currently allowed.
 *       
 * 
 * <p>Classe Java per BoundingShapeType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="BoundingShapeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}Box"/>
 *           &lt;element name="null" type="{http://www.opengis.net/gml}NullType"/>
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
@XmlType(name = "BoundingShapeType", propOrder = {
    "box",
    "_null"
})
public class BoundingShapeType
    implements ToString
{

    @XmlElement(name = "Box")
    protected BoxType box;
    @XmlElement(name = "null")
    protected NullType _null;

    /**
     * Recupera il valore della proprietà box.
     * 
     * @return
     *     possible object is
     *     {@link BoxType }
     *     
     */
    public BoxType getBox() {
        return box;
    }

    /**
     * Imposta il valore della proprietà box.
     * 
     * @param value
     *     allowed object is
     *     {@link BoxType }
     *     
     */
    public void setBox(BoxType value) {
        this.box = value;
    }

    public boolean isSetBox() {
        return (this.box!= null);
    }

    /**
     * Recupera il valore della proprietà null.
     * 
     * @return
     *     possible object is
     *     {@link NullType }
     *     
     */
    public NullType getNull() {
        return _null;
    }

    /**
     * Imposta il valore della proprietà null.
     * 
     * @param value
     *     allowed object is
     *     {@link NullType }
     *     
     */
    public void setNull(NullType value) {
        this._null = value;
    }

    public boolean isSetNull() {
        return (this._null!= null);
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
            BoxType theBox;
            theBox = this.getBox();
            strategy.appendField(locator, this, "box", buffer, theBox);
        }
        {
            NullType theNull;
            theNull = this.getNull();
            strategy.appendField(locator, this, "_null", buffer, theNull);
        }
        return buffer;
    }

}
