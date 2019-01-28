//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.24 alle 07:06:40 PM CET 
//


package org.geosdi.geoplatform.wms.v111;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "layerDescription"
})
@XmlRootElement(name = "WMS_DescribeLayerResponse")
public class WMSDescribeLayerResponse implements Serializable, ToString2 {

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "version", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String version;
    @XmlElement(name = "LayerDescription")
    protected List<LayerDescription> layerDescription;

    /**
     * Recupera il valore della proprietà version.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * Imposta il valore della proprietà version.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the layerDescription property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the layerDescription property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLayerDescription().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LayerDescription }
     */
    public List<LayerDescription> getLayerDescription() {
        if (layerDescription == null) {
            layerDescription = new ArrayList<LayerDescription>();
        }
        return this.layerDescription;
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
            String theVersion;
            theVersion = this.getVersion();
            strategy.appendField(locator, this, "version", buffer, theVersion, (this.version != null));
        }
        {
            List<LayerDescription> theLayerDescription;
            theLayerDescription = (((this.layerDescription != null) && (!this.layerDescription.isEmpty())) ? this.getLayerDescription() : null);
            strategy.appendField(locator, this, "layerDescription", buffer, theLayerDescription, ((this.layerDescription != null) && (!this.layerDescription.isEmpty())));
        }
        return buffer;
    }

    public void setLayerDescription(List<LayerDescription> value) {
        this.layerDescription = null;
        if (value != null) {
            List<LayerDescription> draftl = this.getLayerDescription();
            draftl.addAll(value);
        }
    }

}
