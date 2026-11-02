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
 *         <choice maxOccurs="unbounded" minOccurs="0">
 *           <element ref="{http://www.opengis.net/sld}ExternalGraphic"/>
 *           <element ref="{http://www.opengis.net/sld}Mark"/>
 *         </choice>
 *         <sequence>
 *           <element ref="{http://www.opengis.net/sld}Opacity" minOccurs="0"/>
 *           <element ref="{http://www.opengis.net/sld}Size" minOccurs="0"/>
 *           <element ref="{http://www.opengis.net/sld}Rotation" minOccurs="0"/>
 *         </sequence>
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
    "externalGraphicOrMark",
    "opacity",
    "size",
    "rotation"
})
@XmlRootElement(name = "Graphic")
public class Graphic implements ToString
{

    @XmlElements({
        @XmlElement(name = "ExternalGraphic", type = ExternalGraphic.class),
        @XmlElement(name = "Mark", type = Mark.class)
    })
    protected List<Object> externalGraphicOrMark;
    @XmlElement(name = "Opacity")
    protected ParameterValueType opacity;
    @XmlElement(name = "Size")
    protected ParameterValueType size;
    @XmlElement(name = "Rotation")
    protected ParameterValueType rotation;

    /**
     * Gets the value of the externalGraphicOrMark property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore, any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the externalGraphicOrMark property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getExternalGraphicOrMark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExternalGraphic }
     * {@link Mark }
     * </p>
     * 
     * 
     * @return
     *     The value of the externalGraphicOrMark property.
     */
    public List<Object> getExternalGraphicOrMark() {
        if (externalGraphicOrMark == null) {
            externalGraphicOrMark = new ArrayList<>();
        }
        return this.externalGraphicOrMark;
    }

    public boolean isSetExternalGraphicOrMark() {
        return ((this.externalGraphicOrMark!= null)&&(!this.externalGraphicOrMark.isEmpty()));
    }

    public void unsetExternalGraphicOrMark() {
        this.externalGraphicOrMark = null;
    }

    /**
     * Recupera il valore della proprietà opacity.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getOpacity() {
        return opacity;
    }

    /**
     * Imposta il valore della proprietà opacity.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setOpacity(ParameterValueType value) {
        this.opacity = value;
    }

    public boolean isSetOpacity() {
        return (this.opacity!= null);
    }

    /**
     * Recupera il valore della proprietà size.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getSize() {
        return size;
    }

    /**
     * Imposta il valore della proprietà size.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setSize(ParameterValueType value) {
        this.size = value;
    }

    public boolean isSetSize() {
        return (this.size!= null);
    }

    /**
     * Recupera il valore della proprietà rotation.
     * 
     * @return
     *     possible object is
     *     {@link ParameterValueType }
     *     
     */
    public ParameterValueType getRotation() {
        return rotation;
    }

    /**
     * Imposta il valore della proprietà rotation.
     * 
     * @param value
     *     allowed object is
     *     {@link ParameterValueType }
     *     
     */
    public void setRotation(ParameterValueType value) {
        this.rotation = value;
    }

    public boolean isSetRotation() {
        return (this.rotation!= null);
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
            List<Object> theExternalGraphicOrMark;
            theExternalGraphicOrMark = (this.isSetExternalGraphicOrMark()?this.getExternalGraphicOrMark():null);
            strategy.appendField(locator, this, "externalGraphicOrMark", buffer, theExternalGraphicOrMark, this.isSetExternalGraphicOrMark());
        }
        {
            ParameterValueType theOpacity;
            theOpacity = this.getOpacity();
            strategy.appendField(locator, this, "opacity", buffer, theOpacity, this.isSetOpacity());
        }
        {
            ParameterValueType theSize;
            theSize = this.getSize();
            strategy.appendField(locator, this, "size", buffer, theSize, this.isSetSize());
        }
        {
            ParameterValueType theRotation;
            theRotation = this.getRotation();
            strategy.appendField(locator, this, "rotation", buffer, theRotation, this.isSetRotation());
        }
        return buffer;
    }

    public void setExternalGraphicOrMark(List<Object> value) {
        this.externalGraphicOrMark = null;
        if (value!= null) {
            List<Object> draftl = this.getExternalGraphicOrMark();
            draftl.addAll(value);
        }
    }

}
