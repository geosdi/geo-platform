//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.12.01 alle 08:55:18 AM CET 
//


package org.geosdi.geoplatform.xml.wps.v100;

import org.geosdi.geoplatform.xml.ows.v110.DomainMetadataType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * Identifies a UOM supported for this input or output.
 * <p>
 * <p>Classe Java per UOMsType complex type.
 * <p>
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;complexType name="UOMsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/ows/1.1}UOM" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UOMsType", propOrder = {
        "uom"
})
public class UOMsType implements ToString2 {

    @XmlElement(name = "UOM", namespace = "http://www.opengis.net/ows/1.1", required = true)
    protected List<DomainMetadataType> uom;

    /**
     * Reference to a UOM supported for this input or output. Gets the value of the uom property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the uom property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUOM().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DomainMetadataType }
     */
    public List<DomainMetadataType> getUOM() {
        if (uom == null) {
            uom = new ArrayList<DomainMetadataType>();
        }
        return this.uom;
    }

    public boolean isSetUOM() {
        return ((this.uom != null) && (!this.uom.isEmpty()));
    }

    public void unsetUOM() {
        this.uom = null;
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
            List<DomainMetadataType> theUOM;
            theUOM = (this.isSetUOM() ? this.getUOM() : null);
            strategy.appendField(locator, this, "uom", buffer, theUOM, this.isSetUOM());
        }
        return buffer;
    }

    public void setUOM(List<DomainMetadataType> value) {
        this.uom = null;
        if (value != null) {
            List<DomainMetadataType> draftl = this.getUOM();
            draftl.addAll(value);
        }
    }

}
