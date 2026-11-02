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
// Generato il: 2026.11.02 alle 02:51:01 PM CET
//

package org.geosdi.geoplatform.xml.wmc.v110;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.sld.v100.FeatureTypeStyle;
import org.geosdi.geoplatform.xml.sld.v100.StyledLayerDescriptor;
import org.jvnet.jaxb.lang.JAXBToStringStrategy;
import org.jvnet.jaxb.lang.ToString;
import org.jvnet.jaxb.lang.ToStringStrategy;
import org.jvnet.jaxb.locator.ObjectLocator;


/**
 * <p>Classe Java per SLDType complex type.</p>
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.</p>
 * 
 * <pre>{@code
 * <complexType name="SLDType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="Title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="LegendURL" type="{http://www.opengis.net/context}URLType" minOccurs="0"/>
 *         <choice>
 *           <element name="OnlineResource" type="{http://www.opengis.net/context}OnlineResourceType"/>
 *           <element ref="{http://www.opengis.net/sld}StyledLayerDescriptor"/>
 *           <element ref="{http://www.opengis.net/sld}FeatureTypeStyle"/>
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
@XmlType(name = "SLDType", propOrder = {
    "name",
    "title",
    "legendURL",
    "onlineResource",
    "styledLayerDescriptor",
    "featureTypeStyle"
})
public class SLDType implements ToString
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "LegendURL")
    protected URLType legendURL;
    @XmlElement(name = "OnlineResource")
    protected OnlineResourceType onlineResource;
    /**
     * A StyledLayerDescriptor is a sequence of styled layers, represented
     *         at the first level by NamedLayer and UserLayer elements.
     * 
     */
    @XmlElement(name = "StyledLayerDescriptor", namespace = "http://www.opengis.net/sld")
    protected StyledLayerDescriptor styledLayerDescriptor;
    /**
     * A FeatureTypeStyle contains styling information specific to one
     *       feature type.  This is the SLD level that separates the 'layer'
     *       handling from the 'feature' handling.
     * 
     */
    @XmlElement(name = "FeatureTypeStyle", namespace = "http://www.opengis.net/sld")
    protected FeatureTypeStyle featureTypeStyle;

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
     * Recupera il valore della proprietà title.
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
     */
    public void setTitle(String value) {
        this.title = value;
    }

    public boolean isSetTitle() {
        return (this.title!= null);
    }

    /**
     * Recupera il valore della proprietà legendURL.
     * 
     * @return
     *     possible object is
     *     {@link URLType }
     *     
     */
    public URLType getLegendURL() {
        return legendURL;
    }

    /**
     * Imposta il valore della proprietà legendURL.
     * 
     * @param value
     *     allowed object is
     *     {@link URLType }
     *     
     */
    public void setLegendURL(URLType value) {
        this.legendURL = value;
    }

    public boolean isSetLegendURL() {
        return (this.legendURL!= null);
    }

    /**
     * Recupera il valore della proprietà onlineResource.
     * 
     * @return
     *     possible object is
     *     {@link OnlineResourceType }
     *     
     */
    public OnlineResourceType getOnlineResource() {
        return onlineResource;
    }

    /**
     * Imposta il valore della proprietà onlineResource.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineResourceType }
     *     
     */
    public void setOnlineResource(OnlineResourceType value) {
        this.onlineResource = value;
    }

    public boolean isSetOnlineResource() {
        return (this.onlineResource!= null);
    }

    /**
     * A StyledLayerDescriptor is a sequence of styled layers, represented
     *         at the first level by NamedLayer and UserLayer elements.
     * 
     * @return
     *     possible object is
     *     {@link StyledLayerDescriptor }
     *     
     */
    public StyledLayerDescriptor getStyledLayerDescriptor() {
        return styledLayerDescriptor;
    }

    /**
     * Imposta il valore della proprietà styledLayerDescriptor.
     * 
     * @param value
     *     allowed object is
     *     {@link StyledLayerDescriptor }
     *     
     * @see #getStyledLayerDescriptor()
     */
    public void setStyledLayerDescriptor(StyledLayerDescriptor value) {
        this.styledLayerDescriptor = value;
    }

    public boolean isSetStyledLayerDescriptor() {
        return (this.styledLayerDescriptor!= null);
    }

    /**
     * A FeatureTypeStyle contains styling information specific to one
     *       feature type.  This is the SLD level that separates the 'layer'
     *       handling from the 'feature' handling.
     * 
     * @return
     *     possible object is
     *     {@link FeatureTypeStyle }
     *     
     */
    public FeatureTypeStyle getFeatureTypeStyle() {
        return featureTypeStyle;
    }

    /**
     * Imposta il valore della proprietà featureTypeStyle.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureTypeStyle }
     *     
     * @see #getFeatureTypeStyle()
     */
    public void setFeatureTypeStyle(FeatureTypeStyle value) {
        this.featureTypeStyle = value;
    }

    public boolean isSetFeatureTypeStyle() {
        return (this.featureTypeStyle!= null);
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
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle, this.isSetTitle());
        }
        {
            URLType theLegendURL;
            theLegendURL = this.getLegendURL();
            strategy.appendField(locator, this, "legendURL", buffer, theLegendURL, this.isSetLegendURL());
        }
        {
            OnlineResourceType theOnlineResource;
            theOnlineResource = this.getOnlineResource();
            strategy.appendField(locator, this, "onlineResource", buffer, theOnlineResource, this.isSetOnlineResource());
        }
        {
            StyledLayerDescriptor theStyledLayerDescriptor;
            theStyledLayerDescriptor = this.getStyledLayerDescriptor();
            strategy.appendField(locator, this, "styledLayerDescriptor", buffer, theStyledLayerDescriptor, this.isSetStyledLayerDescriptor());
        }
        {
            FeatureTypeStyle theFeatureTypeStyle;
            theFeatureTypeStyle = this.getFeatureTypeStyle();
            strategy.appendField(locator, this, "featureTypeStyle", buffer, theFeatureTypeStyle, this.isSetFeatureTypeStyle());
        }
        return buffer;
    }

}
