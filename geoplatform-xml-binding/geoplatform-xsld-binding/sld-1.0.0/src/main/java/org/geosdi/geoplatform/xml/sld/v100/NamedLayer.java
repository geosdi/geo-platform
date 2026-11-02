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
// Generato il: 2026.11.02 alle 02:50:57 PM CET
//

package org.geosdi.geoplatform.xml.sld.v100;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
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
 *         <element ref="{http://www.opengis.net/sld}Name"/>
 *         <element ref="{http://www.opengis.net/sld}LayerFeatureConstraints" minOccurs="0"/>
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element ref="{http://www.opengis.net/sld}NamedStyle"/>
 *           <element ref="{http://www.opengis.net/sld}UserStyle"/>
 *         </choice>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NamedLayer", propOrder = {
    "name",
    "layerFeatureConstraints",
    "namedStyleOrUserStyle"
})
@XmlRootElement(name = "NamedLayer")
public class NamedLayer implements ToString
{

    @XmlElement(name = "Name", required = true)
    protected String name;
    /**
     * LayerFeatureConstraints define what features &amp; feature types are
     *         referenced in a layer.
     * 
     */
    @XmlElement(name = "LayerFeatureConstraints")
    protected LayerFeatureConstraints layerFeatureConstraints;
    @XmlElements({
        @XmlElement(name = "NamedStyle", type = NamedStyle.class),
        @XmlElement(name = "UserStyle", type = UserStyle.class)
    })
    protected List<Object> namedStyleOrUserStyle;

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
     * LayerFeatureConstraints define what features &amp; feature types are
     *         referenced in a layer.
     * 
     * @return
     *     possible object is
     *     {@link LayerFeatureConstraints }
     *     
     */
    public LayerFeatureConstraints getLayerFeatureConstraints() {
        return layerFeatureConstraints;
    }

    /**
     * Imposta il valore della proprietà layerFeatureConstraints.
     * 
     * @param value
     *     allowed object is
     *     {@link LayerFeatureConstraints }
     *     
     * @see #getLayerFeatureConstraints()
     */
    public void setLayerFeatureConstraints(LayerFeatureConstraints value) {
        this.layerFeatureConstraints = value;
    }

    public boolean isSetLayerFeatureConstraints() {
        return (this.layerFeatureConstraints!= null);
    }

    /**
     * Gets the value of the namedStyleOrUserStyle property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the namedStyleOrUserStyle property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getNamedStyleOrUserStyle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedStyle }
     * {@link UserStyle }
     * </p>
     * 
     * 
     * @return
     *     The value of the namedStyleOrUserStyle property.
     */
    public List<Object> getNamedStyleOrUserStyle() {
        if (namedStyleOrUserStyle == null) {
            namedStyleOrUserStyle = new ArrayList<>();
        }
        return this.namedStyleOrUserStyle;
    }

    public boolean isSetNamedStyleOrUserStyle() {
        return ((this.namedStyleOrUserStyle!= null)&&(!this.namedStyleOrUserStyle.isEmpty()));
    }

    public void unsetNamedStyleOrUserStyle() {
        this.namedStyleOrUserStyle = null;
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
            strategy.appendField(locator, this, "name", buffer, theName, this.isSetName());
        }
        {
            LayerFeatureConstraints theLayerFeatureConstraints;
            theLayerFeatureConstraints = this.getLayerFeatureConstraints();
            strategy.appendField(locator, this, "layerFeatureConstraints", buffer, theLayerFeatureConstraints, this.isSetLayerFeatureConstraints());
        }
        {
            List<Object> theNamedStyleOrUserStyle;
            theNamedStyleOrUserStyle = (this.isSetNamedStyleOrUserStyle()?this.getNamedStyleOrUserStyle():null);
            strategy.appendField(locator, this, "namedStyleOrUserStyle", buffer, theNamedStyleOrUserStyle, this.isSetNamedStyleOrUserStyle());
        }
        return buffer;
    }

    public void setNamedStyleOrUserStyle(List<Object> value) {
        this.namedStyleOrUserStyle = null;
        if (value!= null) {
            List<Object> draftl = this.getNamedStyleOrUserStyle();
            draftl.addAll(value);
        }
    }

}
