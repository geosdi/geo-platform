//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:22:41 PM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.geosdi.geoplatform.xml.wmc.v110.ol.ExtensionElement;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This allows additional elements to be added to support non WMS Layer services.
 * <p/>
 * <p>Classe Java per ExtensionType complex type.
 * <p/>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p/>
 * <pre>
 * &lt;complexType name="ExtensionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;any/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlRootElement(name = "Extension")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensionType", propOrder = {
        "any"
})
public class ExtensionType
        implements ToString {

    @XmlAnyElement(lax = true)
    protected List<JAXBElement<ExtensionElement>> any;

    /**
     * @return {@link List< ExtensionElement >}
     */
    public List<JAXBElement<ExtensionElement>> getAny() {
        return (isSetAny() ? this.any : new ArrayList<JAXBElement<ExtensionElement>>());
    }

    /**
     * @param value
     */
    public void setAny(List<JAXBElement<ExtensionElement>> value) {
        this.any = value;
    }

    public boolean isSetAny() {
        return (this.any != null);
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
            Object theAny;
            theAny = this.getAny();
            strategy.appendField(locator, this, "any", buffer, theAny);
        }
        return buffer;
    }

}
