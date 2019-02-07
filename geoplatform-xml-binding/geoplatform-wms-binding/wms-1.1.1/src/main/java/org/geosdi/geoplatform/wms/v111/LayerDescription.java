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
    "query"
})
@XmlRootElement(name = "LayerDescription")
public class LayerDescription implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlAttribute(name = "name", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(name = "wfs")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String wfs;
    @XmlAttribute(name = "owsType")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String owsType;
    @XmlAttribute(name = "owsURL")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String owsURL;
    @XmlElement(name = "Query")
    protected List<Query> query;

    /**
     * Recupera il valore della proprietà name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Recupera il valore della proprietà wfs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWfs() {
        return wfs;
    }

    /**
     * Imposta il valore della proprietà wfs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWfs(String value) {
        this.wfs = value;
    }

    /**
     * Recupera il valore della proprietà owsType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwsType() {
        return owsType;
    }

    /**
     * Imposta il valore della proprietà owsType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwsType(String value) {
        this.owsType = value;
    }

    /**
     * Recupera il valore della proprietà owsURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwsURL() {
        return owsURL;
    }

    /**
     * Imposta il valore della proprietà owsURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwsURL(String value) {
        this.owsURL = value;
    }

    /**
     * Gets the value of the query property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the query property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQuery().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Query }
     * 
     * 
     */
    public List<Query> getQuery() {
        if (query == null) {
            query = new ArrayList<Query>();
        }
        return this.query;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, (this.name!= null));
        }
        {
            String theWfs;
            theWfs = this.getWfs();
            strategy.appendField(locator, this, "wfs", buffer, theWfs, (this.wfs!= null));
        }
        {
            String theOwsType;
            theOwsType = this.getOwsType();
            strategy.appendField(locator, this, "owsType", buffer, theOwsType, (this.owsType!= null));
        }
        {
            String theOwsURL;
            theOwsURL = this.getOwsURL();
            strategy.appendField(locator, this, "owsURL", buffer, theOwsURL, (this.owsURL!= null));
        }
        {
            List<Query> theQuery;
            theQuery = (((this.query!= null)&&(!this.query.isEmpty()))?this.getQuery():null);
            strategy.appendField(locator, this, "query", buffer, theQuery, ((this.query!= null)&&(!this.query.isEmpty())));
        }
        return buffer;
    }

    public void setQuery(List<Query> value) {
        this.query = null;
        if (value!= null) {
            List<Query> draftl = this.getQuery();
            draftl.addAll(value);
        }
    }

}
