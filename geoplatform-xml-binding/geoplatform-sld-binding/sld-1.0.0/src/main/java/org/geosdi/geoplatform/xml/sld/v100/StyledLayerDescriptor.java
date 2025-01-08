/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2025 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.09.20 alle 09:31:16 AM CEST 
//


package org.geosdi.geoplatform.xml.sld.v100;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element ref="{http://www.opengis.net/sld}Name" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Title" minOccurs="0"/&gt;
 *         &lt;element ref="{http://www.opengis.net/sld}Abstract" minOccurs="0"/&gt;
 *         &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}NamedLayer"/&gt;
 *           &lt;element ref="{http://www.opengis.net/sld}UserLayer"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.0.0" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"name", "title", "_abstract", "namedLayerOrUserLayer"})
@XmlRootElement(name = "StyledLayerDescriptor")
public class StyledLayerDescriptor implements ToString2 {

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElements({@XmlElement(name = "NamedLayer", type = NamedLayer.class),
            @XmlElement(name = "UserLayer", type = UserLayer.class)})
    protected List<Object> namedLayerOrUserLayer;
    /**
     *
     */
    @XmlAttribute(name = "version", required = true)
    public final static String VERSION = "1.0.0";

    /**
     * Recupera il valore della proprietà name.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il valore della proprietà name.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name != null);
    }

    /**
     * Recupera il valore della proprietà title.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTitle() {
        return title;
    }

    /**
     * Imposta il valore della proprietà title.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title != null);
    }

    /**
     * Recupera il valore della proprietà abstract.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * Imposta il valore della proprietà abstract.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    public boolean isSetAbstract() {
        return (this._abstract != null);
    }

    /**
     * Gets the value of the namedLayerOrUserLayer property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the namedLayerOrUserLayer property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNamedLayerOrUserLayer().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedLayer }
     * {@link UserLayer }
     */
    public List<Object> getNamedLayerOrUserLayer() {
        if (namedLayerOrUserLayer == null) {
            namedLayerOrUserLayer = new ArrayList<Object>();
        }
        return this.namedLayerOrUserLayer;
    }

    public boolean isSetNamedLayerOrUserLayer() {
        return ((this.namedLayerOrUserLayer != null) && (!this.namedLayerOrUserLayer.isEmpty()));
    }

    public void unsetNamedLayerOrUserLayer() {
        this.namedLayerOrUserLayer = null;
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
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract, this.isSetAbstract());
        }
        {
            List<Object> theNamedLayerOrUserLayer;
            theNamedLayerOrUserLayer = (this.isSetNamedLayerOrUserLayer() ? this.getNamedLayerOrUserLayer() : null);
            strategy.appendField(locator, this, "namedLayerOrUserLayer", buffer, theNamedLayerOrUserLayer,
                    this.isSetNamedLayerOrUserLayer());
        }
        {
            String theVERSION;
            theVERSION = StyledLayerDescriptor.VERSION;
            strategy.appendField(locator, this, "version", buffer, theVERSION, true);
        }
        return buffer;
    }

    public void setNamedLayerOrUserLayer(List<Object> value) {
        this.namedLayerOrUserLayer = null;
        if (value != null) {
            List<Object> draftl = this.getNamedLayerOrUserLayer();
            draftl.addAll(value);
        }
    }
}