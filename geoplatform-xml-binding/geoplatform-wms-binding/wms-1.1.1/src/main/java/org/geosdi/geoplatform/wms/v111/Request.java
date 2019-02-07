//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.24 alle 07:06:40 PM CET 
//


package org.geosdi.geoplatform.wms.v111;

import java.io.Serializable;
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
    "getCapabilities",
    "getMap",
    "getFeatureInfo",
    "describeLayer",
    "getLegendGraphic",
    "getStyles",
    "putStyles"
})
@XmlRootElement(name = "Request")
public class Request implements Serializable, ToString2
{

    private final static long serialVersionUID = 1L;
    @XmlElement(name = "GetCapabilities", required = true)
    protected GetCapabilities getCapabilities;
    @XmlElement(name = "GetMap", required = true)
    protected GetMap getMap;
    @XmlElement(name = "GetFeatureInfo")
    protected GetFeatureInfo getFeatureInfo;
    @XmlElement(name = "DescribeLayer")
    protected DescribeLayer describeLayer;
    @XmlElement(name = "GetLegendGraphic")
    protected GetLegendGraphic getLegendGraphic;
    @XmlElement(name = "GetStyles")
    protected GetStyles getStyles;
    @XmlElement(name = "PutStyles")
    protected PutStyles putStyles;

    /**
     * Recupera il valore della proprietà getCapabilities.
     * 
     * @return
     *     possible object is
     *     {@link GetCapabilities }
     *     
     */
    public GetCapabilities getGetCapabilities() {
        return getCapabilities;
    }

    /**
     * Imposta il valore della proprietà getCapabilities.
     * 
     * @param value
     *     allowed object is
     *     {@link GetCapabilities }
     *     
     */
    public void setGetCapabilities(GetCapabilities value) {
        this.getCapabilities = value;
    }

    /**
     * Recupera il valore della proprietà getMap.
     * 
     * @return
     *     possible object is
     *     {@link GetMap }
     *     
     */
    public GetMap getGetMap() {
        return getMap;
    }

    /**
     * Imposta il valore della proprietà getMap.
     * 
     * @param value
     *     allowed object is
     *     {@link GetMap }
     *     
     */
    public void setGetMap(GetMap value) {
        this.getMap = value;
    }

    /**
     * Recupera il valore della proprietà getFeatureInfo.
     * 
     * @return
     *     possible object is
     *     {@link GetFeatureInfo }
     *     
     */
    public GetFeatureInfo getGetFeatureInfo() {
        return getFeatureInfo;
    }

    /**
     * Imposta il valore della proprietà getFeatureInfo.
     * 
     * @param value
     *     allowed object is
     *     {@link GetFeatureInfo }
     *     
     */
    public void setGetFeatureInfo(GetFeatureInfo value) {
        this.getFeatureInfo = value;
    }

    /**
     * Recupera il valore della proprietà describeLayer.
     * 
     * @return
     *     possible object is
     *     {@link DescribeLayer }
     *     
     */
    public DescribeLayer getDescribeLayer() {
        return describeLayer;
    }

    /**
     * Imposta il valore della proprietà describeLayer.
     * 
     * @param value
     *     allowed object is
     *     {@link DescribeLayer }
     *     
     */
    public void setDescribeLayer(DescribeLayer value) {
        this.describeLayer = value;
    }

    /**
     * Recupera il valore della proprietà getLegendGraphic.
     * 
     * @return
     *     possible object is
     *     {@link GetLegendGraphic }
     *     
     */
    public GetLegendGraphic getGetLegendGraphic() {
        return getLegendGraphic;
    }

    /**
     * Imposta il valore della proprietà getLegendGraphic.
     * 
     * @param value
     *     allowed object is
     *     {@link GetLegendGraphic }
     *     
     */
    public void setGetLegendGraphic(GetLegendGraphic value) {
        this.getLegendGraphic = value;
    }

    /**
     * Recupera il valore della proprietà getStyles.
     * 
     * @return
     *     possible object is
     *     {@link GetStyles }
     *     
     */
    public GetStyles getGetStyles() {
        return getStyles;
    }

    /**
     * Imposta il valore della proprietà getStyles.
     * 
     * @param value
     *     allowed object is
     *     {@link GetStyles }
     *     
     */
    public void setGetStyles(GetStyles value) {
        this.getStyles = value;
    }

    /**
     * Recupera il valore della proprietà putStyles.
     * 
     * @return
     *     possible object is
     *     {@link PutStyles }
     *     
     */
    public PutStyles getPutStyles() {
        return putStyles;
    }

    /**
     * Imposta il valore della proprietà putStyles.
     * 
     * @param value
     *     allowed object is
     *     {@link PutStyles }
     *     
     */
    public void setPutStyles(PutStyles value) {
        this.putStyles = value;
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
            GetCapabilities theGetCapabilities;
            theGetCapabilities = this.getGetCapabilities();
            strategy.appendField(locator, this, "getCapabilities", buffer, theGetCapabilities, (this.getCapabilities!= null));
        }
        {
            GetMap theGetMap;
            theGetMap = this.getGetMap();
            strategy.appendField(locator, this, "getMap", buffer, theGetMap, (this.getMap!= null));
        }
        {
            GetFeatureInfo theGetFeatureInfo;
            theGetFeatureInfo = this.getGetFeatureInfo();
            strategy.appendField(locator, this, "getFeatureInfo", buffer, theGetFeatureInfo, (this.getFeatureInfo!= null));
        }
        {
            DescribeLayer theDescribeLayer;
            theDescribeLayer = this.getDescribeLayer();
            strategy.appendField(locator, this, "describeLayer", buffer, theDescribeLayer, (this.describeLayer!= null));
        }
        {
            GetLegendGraphic theGetLegendGraphic;
            theGetLegendGraphic = this.getGetLegendGraphic();
            strategy.appendField(locator, this, "getLegendGraphic", buffer, theGetLegendGraphic, (this.getLegendGraphic!= null));
        }
        {
            GetStyles theGetStyles;
            theGetStyles = this.getGetStyles();
            strategy.appendField(locator, this, "getStyles", buffer, theGetStyles, (this.getStyles!= null));
        }
        {
            PutStyles thePutStyles;
            thePutStyles = this.getPutStyles();
            strategy.appendField(locator, this, "putStyles", buffer, thePutStyles, (this.putStyles!= null));
        }
        return buffer;
    }

}
