//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:29:16 AM CEST 
//


package org.geosdi.geoplatform.xml.filter.v100;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per PropertyIsNullType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PropertyIsNullType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.opengis.net/ogc}ComparisonOpsType"&gt;
 *       &lt;choice&gt;
 *         &lt;element ref="{http://www.opengis.net/ogc}PropertyName"/&gt;
 *         &lt;element ref="{http://www.opengis.net/ogc}Literal"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertyIsNullType", propOrder = {
    "propertyName",
    "literal"
})
public class PropertyIsNullType
    extends ComparisonOpsType
    implements ToString2
{

    @XmlElement(name = "PropertyName")
    protected PropertyNameType propertyName;
    @XmlElement(name = "Literal")
    protected LiteralType literal;

    /**
     * Recupera il valore della proprietà propertyName.
     * 
     * @return
     *     possible object is
     *     {@link PropertyNameType }
     *     
     */
    public PropertyNameType getPropertyName() {
        return propertyName;
    }

    /**
     * Imposta il valore della proprietà propertyName.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyNameType }
     *     
     */
    public void setPropertyName(PropertyNameType value) {
        this.propertyName = value;
    }

    public boolean isSetPropertyName() {
        return (this.propertyName!= null);
    }

    /**
     * Recupera il valore della proprietà literal.
     * 
     * @return
     *     possible object is
     *     {@link LiteralType }
     *     
     */
    public LiteralType getLiteral() {
        return literal;
    }

    /**
     * Imposta il valore della proprietà literal.
     * 
     * @param value
     *     allowed object is
     *     {@link LiteralType }
     *     
     */
    public void setLiteral(LiteralType value) {
        this.literal = value;
    }

    public boolean isSetLiteral() {
        return (this.literal!= null);
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
        super.appendFields(locator, buffer, strategy);
        {
            PropertyNameType thePropertyName;
            thePropertyName = this.getPropertyName();
            strategy.appendField(locator, this, "propertyName", buffer, thePropertyName, this.isSetPropertyName());
        }
        {
            LiteralType theLiteral;
            theLiteral = this.getLiteral();
            strategy.appendField(locator, this, "literal", buffer, theLiteral, this.isSetLiteral());
        }
        return buffer;
    }

}
