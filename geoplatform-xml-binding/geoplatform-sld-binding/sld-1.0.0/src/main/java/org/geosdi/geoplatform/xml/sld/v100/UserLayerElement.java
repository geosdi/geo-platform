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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.7 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2015.08.25 alle 11:12:35 PM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/sld}Name" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}RemoteOWS" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}LayerFeatureConstraints"/>
 *         &lt;element ref="{http://www.opengis.net/sld}UserStyle" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "remoteOWS",
    "layerFeatureConstraints",
    "userStyle"
})
@XmlRootElement(name = "UserLayer")
public class UserLayerElement implements ToString
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "RemoteOWS")
    protected RemoteOWSElement remoteOWS;
    @XmlElement(name = "LayerFeatureConstraints", required = true)
    protected LayerFeatureConstraintsElement layerFeatureConstraints;
    @XmlElement(name = "UserStyle", required = true)
    protected List<UserStyleElement> userStyle;

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

    public boolean isSetName() {
        return (this.name!= null);
    }

    /**
     * Recupera il valore della proprietà remoteOWS.
     * 
     * @return
     *     possible object is
     *     {@link RemoteOWSElement }
     *     
     */
    public RemoteOWSElement getRemoteOWS() {
        return remoteOWS;
    }

    /**
     * Imposta il valore della proprietà remoteOWS.
     * 
     * @param value
     *     allowed object is
     *     {@link RemoteOWSElement }
     *     
     */
    public void setRemoteOWS(RemoteOWSElement value) {
        this.remoteOWS = value;
    }

    public boolean isSetRemoteOWS() {
        return (this.remoteOWS!= null);
    }

    /**
     * Recupera il valore della proprietà layerFeatureConstraints.
     * 
     * @return
     *     possible object is
     *     {@link LayerFeatureConstraintsElement }
     *     
     */
    public LayerFeatureConstraintsElement getLayerFeatureConstraints() {
        return layerFeatureConstraints;
    }

    /**
     * Imposta il valore della proprietà layerFeatureConstraints.
     * 
     * @param value
     *     allowed object is
     *     {@link LayerFeatureConstraintsElement }
     *     
     */
    public void setLayerFeatureConstraints(LayerFeatureConstraintsElement value) {
        this.layerFeatureConstraints = value;
    }

    public boolean isSetLayerFeatureConstraints() {
        return (this.layerFeatureConstraints!= null);
    }

    /**
     * Gets the value of the userStyle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userStyle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserStyle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserStyleElement }
     * 
     * 
     */
    public List<UserStyleElement> getUserStyle() {
        if (userStyle == null) {
            userStyle = new ArrayList<UserStyleElement>();
        }
        return this.userStyle;
    }

    public boolean isSetUserStyle() {
        return ((this.userStyle!= null)&&(!this.userStyle.isEmpty()));
    }

    public void unsetUserStyle() {
        this.userStyle = null;
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
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            RemoteOWSElement theRemoteOWS;
            theRemoteOWS = this.getRemoteOWS();
            strategy.appendField(locator, this, "remoteOWS", buffer, theRemoteOWS);
        }
        {
            LayerFeatureConstraintsElement theLayerFeatureConstraints;
            theLayerFeatureConstraints = this.getLayerFeatureConstraints();
            strategy.appendField(locator, this, "layerFeatureConstraints", buffer, theLayerFeatureConstraints);
        }
        {
            List<UserStyleElement> theUserStyle;
            theUserStyle = (this.isSetUserStyle()?this.getUserStyle():null);
            strategy.appendField(locator, this, "userStyle", buffer, theUserStyle);
        }
        return buffer;
    }

    public void setUserStyle(List<UserStyleElement> value) {
        this.userStyle = null;
        List<UserStyleElement> draftl = this.getUserStyle();
        draftl.addAll(value);
    }

}
