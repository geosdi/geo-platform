/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
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
