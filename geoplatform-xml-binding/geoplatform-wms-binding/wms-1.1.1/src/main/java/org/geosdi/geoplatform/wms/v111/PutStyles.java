//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.24 alle 07:06:40 PM CET 
//


package org.geosdi.geoplatform.wms.v111;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "format",
    "dcpType"
})
@XmlRootElement(name = "PutStyles")
public class PutStyles implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "Format", required = true)
    protected List<Format> format;
    @XmlElement(name = "DCPType", required = true)
    protected List<DCPType> dcpType;

    /**
     * Gets the value of the format property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the format property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormat().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Format }
     * 
     * 
     */
    public List<Format> getFormat() {
        if (format == null) {
            format = new ArrayList<Format>();
        }
        return this.format;
    }

    /**
     * Gets the value of the dcpType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dcpType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDCPType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DCPType }
     * 
     * 
     */
    public List<DCPType> getDCPType() {
        if (dcpType == null) {
            dcpType = new ArrayList<DCPType>();
        }
        return this.dcpType;
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
        {
            List<Format> theFormat;
            theFormat = (((this.format!= null)&&(!this.format.isEmpty()))?this.getFormat():null);
            strategy.appendField(locator, this, "format", buffer, theFormat, ((this.format!= null)&&(!this.format.isEmpty())));
        }
        {
            List<DCPType> theDCPType;
            theDCPType = (((this.dcpType!= null)&&(!this.dcpType.isEmpty()))?this.getDCPType():null);
            strategy.appendField(locator, this, "dcpType", buffer, theDCPType, ((this.dcpType!= null)&&(!this.dcpType.isEmpty())));
        }
        return buffer;
    }

    public void setFormat(List<Format> value) {
        this.format = null;
        if (value!= null) {
            List<Format> draftl = this.getFormat();
            draftl.addAll(value);
        }
    }

    public void setDCPType(List<DCPType> value) {
        this.dcpType = null;
        if (value!= null) {
            List<DCPType> draftl = this.getDCPType();
            draftl.addAll(value);
        }
    }

}
