//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.25 alle 11:53:13 AM CET 
//


package org.geosdi.geoplatform.wms.v130;

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
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}GetCapabilities"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}GetMap"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}GetFeatureInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/wms}_ExtendedOperation" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "getCapabilities",
    "getMap",
    "getFeatureInfo",
    "extendedOperation"
})
@XmlRootElement(name = "Request")
public class Request implements ToString2
{

    @XmlElement(name = "GetCapabilities", required = true)
    protected OperationType getCapabilities;
    @XmlElement(name = "GetMap", required = true)
    protected OperationType getMap;
    @XmlElement(name = "GetFeatureInfo")
    protected OperationType getFeatureInfo;
    @XmlElement(name = "_ExtendedOperation")
    protected List<OperationType> extendedOperation;

    /**
     * Recupera il valore della proprietà getCapabilities.
     * 
     * @return
     *     possible object is
     *     {@link OperationType }
     *     
     */
    public OperationType getGetCapabilities() {
        return getCapabilities;
    }

    /**
     * Imposta il valore della proprietà getCapabilities.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationType }
     *     
     */
    public void setGetCapabilities(OperationType value) {
        this.getCapabilities = value;
    }

    /**
     * Recupera il valore della proprietà getMap.
     * 
     * @return
     *     possible object is
     *     {@link OperationType }
     *     
     */
    public OperationType getGetMap() {
        return getMap;
    }

    /**
     * Imposta il valore della proprietà getMap.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationType }
     *     
     */
    public void setGetMap(OperationType value) {
        this.getMap = value;
    }

    /**
     * Recupera il valore della proprietà getFeatureInfo.
     * 
     * @return
     *     possible object is
     *     {@link OperationType }
     *     
     */
    public OperationType getGetFeatureInfo() {
        return getFeatureInfo;
    }

    /**
     * Imposta il valore della proprietà getFeatureInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationType }
     *     
     */
    public void setGetFeatureInfo(OperationType value) {
        this.getFeatureInfo = value;
    }

    /**
     * Gets the value of the extendedOperation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extendedOperation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtendedOperation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperationType }
     * 
     * 
     */
    public List<OperationType> getExtendedOperation() {
        if (extendedOperation == null) {
            extendedOperation = new ArrayList<OperationType>();
        }
        return this.extendedOperation;
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
            OperationType theGetCapabilities;
            theGetCapabilities = this.getGetCapabilities();
            strategy.appendField(locator, this, "getCapabilities", buffer, theGetCapabilities, (this.getCapabilities!= null));
        }
        {
            OperationType theGetMap;
            theGetMap = this.getGetMap();
            strategy.appendField(locator, this, "getMap", buffer, theGetMap, (this.getMap!= null));
        }
        {
            OperationType theGetFeatureInfo;
            theGetFeatureInfo = this.getGetFeatureInfo();
            strategy.appendField(locator, this, "getFeatureInfo", buffer, theGetFeatureInfo, (this.getFeatureInfo!= null));
        }
        {
            List<OperationType> theExtendedOperation;
            theExtendedOperation = (((this.extendedOperation!= null)&&(!this.extendedOperation.isEmpty()))?this.getExtendedOperation():null);
            strategy.appendField(locator, this, "extendedOperation", buffer, theExtendedOperation, ((this.extendedOperation!= null)&&(!this.extendedOperation.isEmpty())));
        }
        return buffer;
    }

    public void setExtendedOperation(List<OperationType> value) {
        this.extendedOperation = null;
        if (value!= null) {
            List<OperationType> draftl = this.getExtendedOperation();
            draftl.addAll(value);
        }
    }

}
