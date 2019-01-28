//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.01.25 alle 11:53:13 AM CET 
//


package org.geosdi.geoplatform.wms.v130;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="westBoundLongitude" type="{http://www.opengis.net/wms}longitudeType"/&gt;
 *         &lt;element name="eastBoundLongitude" type="{http://www.opengis.net/wms}longitudeType"/&gt;
 *         &lt;element name="southBoundLatitude" type="{http://www.opengis.net/wms}latitudeType"/&gt;
 *         &lt;element name="northBoundLatitude" type="{http://www.opengis.net/wms}latitudeType"/&gt;
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
    "westBoundLongitude",
    "eastBoundLongitude",
    "southBoundLatitude",
    "northBoundLatitude"
})
@XmlRootElement(name = "EX_GeographicBoundingBox")
public class EXGeographicBoundingBox implements ToString2
{

    protected double westBoundLongitude;
    protected double eastBoundLongitude;
    protected double southBoundLatitude;
    protected double northBoundLatitude;

    /**
     * Recupera il valore della proprietà westBoundLongitude.
     * 
     */
    public double getWestBoundLongitude() {
        return westBoundLongitude;
    }

    /**
     * Imposta il valore della proprietà westBoundLongitude.
     * 
     */
    public void setWestBoundLongitude(double value) {
        this.westBoundLongitude = value;
    }

    /**
     * Recupera il valore della proprietà eastBoundLongitude.
     * 
     */
    public double getEastBoundLongitude() {
        return eastBoundLongitude;
    }

    /**
     * Imposta il valore della proprietà eastBoundLongitude.
     * 
     */
    public void setEastBoundLongitude(double value) {
        this.eastBoundLongitude = value;
    }

    /**
     * Recupera il valore della proprietà southBoundLatitude.
     * 
     */
    public double getSouthBoundLatitude() {
        return southBoundLatitude;
    }

    /**
     * Imposta il valore della proprietà southBoundLatitude.
     * 
     */
    public void setSouthBoundLatitude(double value) {
        this.southBoundLatitude = value;
    }

    /**
     * Recupera il valore della proprietà northBoundLatitude.
     * 
     */
    public double getNorthBoundLatitude() {
        return northBoundLatitude;
    }

    /**
     * Imposta il valore della proprietà northBoundLatitude.
     * 
     */
    public void setNorthBoundLatitude(double value) {
        this.northBoundLatitude = value;
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
            double theWestBoundLongitude;
            theWestBoundLongitude = this.getWestBoundLongitude();
            strategy.appendField(locator, this, "westBoundLongitude", buffer, theWestBoundLongitude, true);
        }
        {
            double theEastBoundLongitude;
            theEastBoundLongitude = this.getEastBoundLongitude();
            strategy.appendField(locator, this, "eastBoundLongitude", buffer, theEastBoundLongitude, true);
        }
        {
            double theSouthBoundLatitude;
            theSouthBoundLatitude = this.getSouthBoundLatitude();
            strategy.appendField(locator, this, "southBoundLatitude", buffer, theSouthBoundLatitude, true);
        }
        {
            double theNorthBoundLatitude;
            theNorthBoundLatitude = this.getNorthBoundLatitude();
            strategy.appendField(locator, this, "northBoundLatitude", buffer, theNorthBoundLatitude, true);
        }
        return buffer;
    }

}
