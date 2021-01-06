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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.geosdi.geoplatform.xml.filter.v100.FilterType;
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
 *         &lt;element ref="{http://www.opengis.net/sld}Title" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Abstract" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}LegendGraphic" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element ref="{http://www.opengis.net/ogc}Filter"/>
 *           &lt;element ref="{http://www.opengis.net/sld}ElseFilter"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/sld}MinScaleDenominator" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}MaxScaleDenominator" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/sld}Symbolizer" maxOccurs="unbounded"/>
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
    "title",
    "_abstract",
    "legendGraphic",
    "filter",
    "elseFilter",
    "minScaleDenominator",
    "maxScaleDenominator",
    "symbolizer"
})
@XmlRootElement(name = "Rule")
public class RuleElement
    implements ToString
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Title")
    protected String title;
    @XmlElement(name = "Abstract")
    protected String _abstract;
    @XmlElement(name = "LegendGraphic")
    protected LegendGraphicElement legendGraphic;
    @XmlElement(name = "Filter", namespace = "http://www.opengis.net/ogc")
    protected FilterType filter;
    @XmlElement(name = "ElseFilter")
    protected ElseFilterElement elseFilter;
    @XmlElement(name = "MinScaleDenominator")
    protected Double minScaleDenominator;
    @XmlElement(name = "MaxScaleDenominator")
    protected Double maxScaleDenominator;
    @XmlElementRef(name = "Symbolizer", namespace = "http://www.opengis.net/sld", type = JAXBElement.class)
    protected List<JAXBElement<? extends SymbolizerType>> symbolizer;

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
     * Recupera il valore della proprietà abstract.
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
     */
    public void setAbstract(String value) {
        this._abstract = value;
    }

    public boolean isSetAbstract() {
        return (this._abstract!= null);
    }

    /**
     * Recupera il valore della proprietà legendGraphic.
     * 
     * @return
     *     possible object is
     *     {@link LegendGraphicElement }
     *     
     */
    public LegendGraphicElement getLegendGraphic() {
        return legendGraphic;
    }

    /**
     * Imposta il valore della proprietà legendGraphic.
     * 
     * @param value
     *     allowed object is
     *     {@link LegendGraphicElement }
     *     
     */
    public void setLegendGraphic(LegendGraphicElement value) {
        this.legendGraphic = value;
    }

    public boolean isSetLegendGraphic() {
        return (this.legendGraphic!= null);
    }

    /**
     * Recupera il valore della proprietà filter.
     * 
     * @return
     *     possible object is
     *     {@link FilterType }
     *     
     */
    public FilterType getFilter() {
        return filter;
    }

    /**
     * Imposta il valore della proprietà filter.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterType }
     *     
     */
    public void setFilter(FilterType value) {
        this.filter = value;
    }

    public boolean isSetFilter() {
        return (this.filter!= null);
    }

    /**
     * Recupera il valore della proprietà elseFilter.
     * 
     * @return
     *     possible object is
     *     {@link ElseFilterElement }
     *     
     */
    public ElseFilterElement getElseFilter() {
        return elseFilter;
    }

    /**
     * Imposta il valore della proprietà elseFilter.
     * 
     * @param value
     *     allowed object is
     *     {@link ElseFilterElement }
     *     
     */
    public void setElseFilter(ElseFilterElement value) {
        this.elseFilter = value;
    }

    public boolean isSetElseFilter() {
        return (this.elseFilter!= null);
    }

    /**
     * Recupera il valore della proprietà minScaleDenominator.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinScaleDenominator() {
        return minScaleDenominator;
    }

    /**
     * Imposta il valore della proprietà minScaleDenominator.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinScaleDenominator(Double value) {
        this.minScaleDenominator = value;
    }

    public boolean isSetMinScaleDenominator() {
        return (this.minScaleDenominator!= null);
    }

    /**
     * Recupera il valore della proprietà maxScaleDenominator.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxScaleDenominator() {
        return maxScaleDenominator;
    }

    /**
     * Imposta il valore della proprietà maxScaleDenominator.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxScaleDenominator(Double value) {
        this.maxScaleDenominator = value;
    }

    public boolean isSetMaxScaleDenominator() {
        return (this.maxScaleDenominator!= null);
    }

    /**
     * Gets the value of the symbolizer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the symbolizer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSymbolizer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link PolygonSymbolizer }{@code >}
     * {@link JAXBElement }{@code <}{@link TextSymbolizer }{@code >}
     * {@link JAXBElement }{@code <}{@link PointSymbolizer }{@code >}
     * {@link JAXBElement }{@code <}{@link RasterSymbolizer }{@code >}
     * {@link JAXBElement }{@code <}{@link SymbolizerType }{@code >}
     * {@link JAXBElement }{@code <}{@link LineSymbolizer }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends SymbolizerType>> getSymbolizer() {
        if (symbolizer == null) {
            symbolizer = new ArrayList<JAXBElement<? extends SymbolizerType>>();
        }
        return this.symbolizer;
    }

    public boolean isSetSymbolizer() {
        return ((this.symbolizer!= null)&&(!this.symbolizer.isEmpty()));
    }

    public void unsetSymbolizer() {
        this.symbolizer = null;
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
            String theTitle;
            theTitle = this.getTitle();
            strategy.appendField(locator, this, "title", buffer, theTitle);
        }
        {
            String theAbstract;
            theAbstract = this.getAbstract();
            strategy.appendField(locator, this, "_abstract", buffer, theAbstract);
        }
        {
            LegendGraphicElement theLegendGraphic;
            theLegendGraphic = this.getLegendGraphic();
            strategy.appendField(locator, this, "legendGraphic", buffer, theLegendGraphic);
        }
        {
            FilterType theFilter;
            theFilter = this.getFilter();
            strategy.appendField(locator, this, "filter", buffer, theFilter);
        }
        {
            ElseFilterElement theElseFilter;
            theElseFilter = this.getElseFilter();
            strategy.appendField(locator, this, "elseFilter", buffer, theElseFilter);
        }
        {
            Double theMinScaleDenominator;
            theMinScaleDenominator = this.getMinScaleDenominator();
            strategy.appendField(locator, this, "minScaleDenominator", buffer, theMinScaleDenominator);
        }
        {
            Double theMaxScaleDenominator;
            theMaxScaleDenominator = this.getMaxScaleDenominator();
            strategy.appendField(locator, this, "maxScaleDenominator", buffer, theMaxScaleDenominator);
        }
        {
            List<JAXBElement<? extends SymbolizerType>> theSymbolizer;
            theSymbolizer = (this.isSetSymbolizer()?this.getSymbolizer():null);
            strategy.appendField(locator, this, "symbolizer", buffer, theSymbolizer);
        }
        return buffer;
    }

    public void setSymbolizer(List<JAXBElement<? extends SymbolizerType>> value) {
        this.symbolizer = null;
        List<JAXBElement<? extends SymbolizerType>> draftl = this.getSymbolizer();
        draftl.addAll(value);
    }

}
