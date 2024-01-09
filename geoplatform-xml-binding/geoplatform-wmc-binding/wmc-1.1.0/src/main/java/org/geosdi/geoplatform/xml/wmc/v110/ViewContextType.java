/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2024 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
// Generato il: 2021.09.20 alle 09:33:43 AM CEST 
//


package org.geosdi.geoplatform.xml.wmc.v110;

import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString2;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy2;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

import javax.xml.bind.annotation.*;


/**
 * <p>Classe Java per ViewContextType complex type.
 *
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 *
 * <pre>
 * &lt;complexType name="ViewContextType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="General" type="{http://www.opengis.net/context}GeneralType"/&gt;
 *         &lt;element name="LayerList" type="{http://www.opengis.net/context}LayerListType"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="1.1.0" /&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewContextType", propOrder = {"general", "layerList"})
@XmlRootElement(name = "ViewContextType")
public class ViewContextType implements ToString2 {

    @XmlElement(name = "General", required = true)
    protected GeneralType general;
    @XmlElement(name = "LayerList", required = true)
    protected LayerListType layerList;
    /**
     *
     */
    @XmlAttribute(name = "version", required = true)
    public final static String VERSION = "1.1.0";
    @XmlAttribute(name = "id", required = true)
    protected String id;

    /**
     * Recupera il valore della proprietà general.
     *
     * @return possible object is
     * {@link GeneralType }
     */
    public GeneralType getGeneral() {
        return general;
    }

    /**
     * Imposta il valore della proprietà general.
     *
     * @param value allowed object is
     * {@link GeneralType }
     */
    public void setGeneral(GeneralType value) {
        this.general = value;
    }

    public boolean isSetGeneral() {
        return (this.general != null);
    }

    /**
     * Recupera il valore della proprietà layerList.
     *
     * @return possible object is
     * {@link LayerListType }
     */
    public LayerListType getLayerList() {
        return layerList;
    }

    /**
     * Imposta il valore della proprietà layerList.
     *
     * @param value allowed object is
     * {@link LayerListType }
     */
    public void setLayerList(LayerListType value) {
        this.layerList = value;
    }

    public boolean isSetLayerList() {
        return (this.layerList != null);
    }

    /**
     * Recupera il valore della proprietà id.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     *
     * @param value allowed object is
     * {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    public boolean isSetId() {
        return (this.id != null);
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
            GeneralType theGeneral;
            theGeneral = this.getGeneral();
            strategy.appendField(locator, this, "general", buffer, theGeneral, this.isSetGeneral());
        }
        {
            LayerListType theLayerList;
            theLayerList = this.getLayerList();
            strategy.appendField(locator, this, "layerList", buffer, theLayerList, this.isSetLayerList());
        }
        {
            String theVERSION;
            theVERSION = ViewContextType.VERSION;
            strategy.appendField(locator, this, "version", buffer, theVERSION, true);
        }
        {
            String theId;
            theId = this.getId();
            strategy.appendField(locator, this, "id", buffer, theId, this.isSetId());
        }
        return buffer;
    }

}
