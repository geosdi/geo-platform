/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2026 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.9 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine.
// Generato il: 2026.11.02 alle 02:51:45 PM CET
//

package org.geosdi.geoplatform.wms.v130;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per anonymous complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element ref="{http://www.opengis.net/wms}Name"/>
 *         <element ref="{http://www.opengis.net/wms}Title"/>
 *         <element ref="{http://www.opengis.net/wms}Abstract" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}LegendURL" maxOccurs="unbounded" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}StyleSheetURL" minOccurs="0"/>
 *         <element ref="{http://www.opengis.net/wms}StyleURL" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "title",
    "_abstract",
    "legendURL",
    "styleSheetURL",
    "styleURL"
})
@XmlRootElement(name = "Style")
public class Style implements ToString
{

    /**
     * The Name is typically for machine-to-machine communication.
     * 
     */
    @XmlElement(name = "Name", required = true)
    protected String name;
    /**
     * The Title is for informative display to a human.
     * 
     */
    @XmlElement(name = "Title", required = true)
    protected String title;
    /**
     * The abstract is a longer narrative description of an object.
     * 
     */
    @XmlElement(name = "Abstract")
    protected String _abstract;
    /**
     * A Map Server may use zero or more LegendURL elements to provide an
     *         image(s) of a legend relevant to each Style of a Layer.  The Format
     *         element indicates the MIME type of the legend. Width and height
     *         attributes may be provided to assist client applications in laying out
     *         space to display the legend.
     * 
     */
    @XmlElement(name = "LegendURL")
    protected List<LegendURL> legendURL;
    /**
     * StyleSheeetURL provides symbology information for each Style of a Layer.
     * 
     */
    @XmlElement(name = "StyleSheetURL")
    protected StyleSheetURL styleSheetURL;
    /**
     * A Map Server may use StyleURL to offer more information about the
     *         data or symbology underlying a particular Style. While the semantics
     *         are not well-defined, as long as the results of an HTTP GET request
     *         against the StyleURL are properly MIME-typed, Viewer Clients and
     *         Cascading Map Servers can make use of this. A possible use could be
     *         to allow a Map Server to provide legend information.
     * 
     */
    @XmlElement(name = "StyleURL")
    protected StyleURL styleURL;

    /**
     * The Name is typically for machine-to-machine communication.
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
     * @see #getName()
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * The Title is for informative display to a human.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getTitle()
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * The abstract is a longer narrative description of an object.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     * @see #getAbstract()
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    /**
     * A Map Server may use zero or more LegendURL elements to provide an
     *         image(s) of a legend relevant to each Style of a Layer.  The Format
     *         element indicates the MIME type of the legend. Width and height
     *         attributes may be provided to assist client applications in laying out
     *         space to display the legend.
     * 
     * Gets the value of the legendURL property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the legendURL property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getLegendURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LegendURL }
     * </p>
     * 
     * 
     * @return
     *     The value of the legendURL property.
     */
    public List<LegendURL> getLegendURL() {
        if (legendURL == null) {
            legendURL = new ArrayList<>();
        }
        return this.legendURL;
    }

    /**
     * StyleSheeetURL provides symbology information for each Style of a Layer.
     * 
     * @return
     *     possible object is
     *     {@link StyleSheetURL }
     *     
     */
    public StyleSheetURL getStyleSheetURL() {
        return styleSheetURL;
    }

    /**
     * Imposta il valore della proprietà styleSheetURL.
     * 
     * @param value
     *     allowed object is
     *     {@link StyleSheetURL }
     *     
     * @see #getStyleSheetURL()
     */
    public void setStyleSheetURL(StyleSheetURL value) {
        this.styleSheetURL = value;
    }

    /**
     * A Map Server may use StyleURL to offer more information about the
     *         data or symbology underlying a particular Style. While the semantics
     *         are not well-defined, as long as the results of an HTTP GET request
     *         against the StyleURL are properly MIME-typed, Viewer Clients and
     *         Cascading Map Servers can make use of this. A possible use could be
     *         to allow a Map Server to provide legend information.
     * 
     * @return
     *     possible object is
     *     {@link StyleURL }
     *     
     */
    public StyleURL getStyleURL() {
        return styleURL;
    }

    /**
     * Imposta il valore della proprietà styleURL.
     * 
     * @param value
     *     allowed object is
     *     {@link StyleURL }
     *     
     * @see #getStyleURL()
     */
    public void setStyleURL(StyleURL value) {
        this.styleURL = value;
    }

    @Override
    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.getInstance();
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    @Override
    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    @Override
    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName, (this.name!= null));
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, (this.title!= null));
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, (this._abstract!= null));
        }
        {
            List<LegendURL> theLegendURL;
            theLegendURL = (((this.legendURL!= null)&&(!this.legendURL.isEmpty()))?this.getLegendURL():null);
            strategy.appendField(locator, this, "legendURL", buffer, theLegendURL, ((this.legendURL!= null)&&(!this.legendURL.isEmpty())));
        }
        {
            StyleSheetURL theStyleSheetURL;
            theStyleSheetURL = this.getStyleSheetURL();
            strategy.appendField(locator, this, "styleSheetURL", buffer, theStyleSheetURL, (this.styleSheetURL!= null));
        }
        {
            StyleURL theStyleURL;
            theStyleURL = this.getStyleURL();
            strategy.appendField(locator, this, "styleURL", buffer, theStyleURL, (this.styleURL!= null));
        }
        return buffer;
    }

    public void setLegendURL(List<LegendURL> value) {
        this.legendURL = null;
        if (value!= null) {
            List<LegendURL> draftl = this.getLegendURL();
            draftl.addAll(value);
        }
    }

}
